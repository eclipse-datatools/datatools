/*******************************************************************************
 * Copyright (c) 2006-2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.ConnectEvent;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IManagedConnectionListener;
import org.eclipse.datatools.connectivity.IManagedConnectionOfflineListener;
import org.eclipse.datatools.connectivity.IOfflineConnection;
import org.eclipse.datatools.connectivity.IServerVersionProvider;
import org.eclipse.datatools.connectivity.Version;

public class ManagedConnection implements IManagedConnection {

	private IConnectionProfile mProfile;
	private IConnection mConnection;
	private String mFactoryID;
	private Set mListeners = new HashSet();

	public ManagedConnection(IConnectionProfile profile, String factoryID) {
		super();
		mProfile = profile;
		mFactoryID = factoryID;
	}

	public void addConnectionListener(IManagedConnectionListener listener) {
		mListeners.add(listener);
	}

	public void removeConnectionListener(IManagedConnectionListener listener) {
		mListeners.remove(listener);
	}

	public void fireModifiedEvent(Object context) {
		if (!isConnected()) {
			return;
		}

		ConnectEvent event = new ConnectEvent(getConnectionProfile(), this,
				context);
		for (Iterator it = new ArrayList(mListeners).iterator(); it.hasNext();) {
			try {
				((IManagedConnectionListener) it.next()).modified(event);
			}
			catch (Exception e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
	}

	public IConnection cloneConnection() {
		if (!isConnected()) {
			throw new IllegalStateException();
		}
		CloneConnectionJob connectJob = new CloneConnectionJob();
		connectJob.schedule();
		try {
			connectJob.join();
		}
		catch (InterruptedException e) {
		}
		return connectJob.getConnection();
	}

	public void cloneConnection(IJobChangeListener listener) {
		if (!isConnected()) {
			throw new IllegalStateException();
		}
		if (listener == null) {
			throw new IllegalArgumentException();
		}
		CloneConnectionJob connectJob = new CloneConnectionJob();
		connectJob.addJobChangeListener(listener);
		connectJob.schedule();
	}

	public String getFactoryID() {
		return mFactoryID;
	}

	public boolean isConnected() {
		return mConnection != null && mConnection.getConnectException() == null;
	}

	public IConnection getConnection() {
		if (mConnection == null) {
			return null;
		}
		if (mConnection instanceof IServerVersionProvider) {
			return new RestrictedVersionProviderConnection();
		}
		return new RestrictedConnection();
	}

	public IConnectionProfile getConnectionProfile() {
		return mProfile;
	}

	public boolean isWorkingOffline() {
		return isConnected() && mConnection instanceof IOfflineConnection
				&& ((IOfflineConnection) mConnection).isWorkingOffline();
	}
	
	/* package */InternalConnectionFactoryProvider getConnectionFactoryProvider() {
		return (InternalConnectionFactoryProvider) getConnectionProfile()
				.getProvider().getConnectionFactory(getFactoryID());
	}
	
	/* package */boolean supportsWorkOfflineMode() {
		return getConnectionFactoryProvider().supportsWorkOfflineMode();
	}
	
	/* package */boolean canWorkOffline() {
		return supportsWorkOfflineMode()
				&& (isConnected() || getConnectionFactoryProvider()
						.canWorkOffline(mProfile));
	}
	
	/* package */void createConnection(IProgressMonitor monitor)
			throws CoreException {
		ConnectEvent event = new ConnectEvent(getConnectionProfile(), this);
		if (isWorkingOffline()) {
			for (Iterator it = new ArrayList(mListeners).iterator(); it
					.hasNext();) {
				IManagedConnectionListener listener = (IManagedConnectionListener) it
						.next();
				if (listener instanceof IManagedConnectionOfflineListener) {
					try {
						((IManagedConnectionOfflineListener) listener)
								.aboutToAttach(event);
					}
					catch (Exception e) {
						ConnectivityPlugin.getDefault().log(e);
					}
				}
			}

			((IOfflineConnection) mConnection).attach(monitor);
		}
		else {
			if (supportsWorkOfflineMode()) {
				mConnection = getConnectionFactoryProvider().createConnection(mProfile, monitor);
			}
			else {
				mConnection = getConnectionProfile().createConnection(mFactoryID);
			}
		}

		for (Iterator it = new ArrayList(mListeners).iterator(); it.hasNext();) {
			try {
				((IManagedConnectionListener) it.next()).opened(event);
			}
			catch (Exception e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
		monitor.done();
	}
	
	/* package */void workOffline(IProgressMonitor monitor)
			throws CoreException {
		if (!supportsWorkOfflineMode()) {
			Status status = new Status(Status.ERROR, 
			        ConnectivityPlugin.getSymbolicName(), -1,
					ConnectivityPlugin.getDefault().getResourceString(
							"ManagedConnection_offline_not_supported_error", //$NON-NLS-1$
							new Object[] { getConnectionFactoryProvider()
									.getName()}), null);
			throw new CoreException(status);
		}

		ConnectEvent event = new ConnectEvent(getConnectionProfile(), this);
		if (isConnected()) {
			for (Iterator it = new ArrayList(mListeners).iterator(); it
					.hasNext();) {
				IManagedConnectionListener listener = (IManagedConnectionListener) it
						.next();
				if (listener instanceof IManagedConnectionOfflineListener) {
					try {
						((IManagedConnectionOfflineListener) listener)
								.aboutToDetach(event);
					}
					catch (Exception e) {
						ConnectivityPlugin.getDefault().log(e);
					}
				}
			}

			((IOfflineConnection) mConnection).detach(monitor);
		}
		else {
			mConnection = getConnectionFactoryProvider()
					.createOfflineConnection(getConnectionProfile(), monitor);
		}

		for (Iterator it = new ArrayList(mListeners).iterator(); it.hasNext();) {
			IManagedConnectionListener listener = (IManagedConnectionListener) it
					.next();
			if (listener instanceof IManagedConnectionOfflineListener) {
				try {
					((IManagedConnectionOfflineListener) listener)
							.workingOffline(event);
				}
				catch (Exception e) {
					ConnectivityPlugin.getDefault().log(e);
				}
			}
		}
		monitor.done();
	}
	
	/* package */void save(IProgressMonitor monitor) throws CoreException {
		if (!supportsWorkOfflineMode()) {
			Status status = new Status(Status.ERROR, 
			        ConnectivityPlugin.getSymbolicName(), -1,
					ConnectivityPlugin.getDefault().getResourceString(
							"ManagedConnection_offline_not_supported_error", //$NON-NLS-1$
							new Object[] { getConnectionFactoryProvider()
									.getName()}), null);
			throw new CoreException(status);
		}

		if (!isConnected()) {
			Status status = new Status(
					Status.ERROR,
					ConnectivityPlugin.getSymbolicName(), -1,
					ConnectivityPlugin.getDefault().getResourceString(
									"ManagedConnection_save_not_connected_error"), //$NON-NLS-1$
					null);
			throw new CoreException(status);
		}

		((IOfflineConnection) mConnection).save(monitor);

		monitor.done();
	}

	/* package */boolean okToClose() {
		boolean okToClose = true;
		if (mConnection == null || mConnection.getConnectException() != null) {
			return okToClose;
		}

		ConnectEvent event = new ConnectEvent(getConnectionProfile(), this);
		for (Iterator it = new ArrayList(mListeners).iterator(); okToClose
				&& it.hasNext();) {
			try {
				okToClose = ((IManagedConnectionListener) it.next()).okToClose(event);
			}
			catch (Exception e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
		return okToClose;
	}

	/* package */boolean okToDetach() {
		if (!canWorkOffline()) {
			return okToClose();
		}

		boolean okToDetach = true;
		ConnectEvent event = new ConnectEvent(getConnectionProfile(), this);
		for (Iterator it = new ArrayList(mListeners).iterator(); okToDetach
				&& it.hasNext();) {
			IManagedConnectionListener listener = (IManagedConnectionListener) it
					.next();
			if (listener instanceof IManagedConnectionOfflineListener) {
				try {
					okToDetach = ((IManagedConnectionOfflineListener) listener)
							.okToDetach(event);
				}
				catch (Exception e) {
					ConnectivityPlugin.getDefault().log(e);
				}
			}
		}
		return okToDetach;
	}

	/* package */void close() {
		if (mConnection == null) {
			return;
		}

		if (mConnection.getConnectException() != null) {
			mConnection.close();
			mConnection = null;
			return;
		}

		ConnectEvent event = new ConnectEvent(getConnectionProfile(), this);
		for (Iterator it = new ArrayList(mListeners).iterator(); it.hasNext();) {
			try {
				((IManagedConnectionListener) it.next()).aboutToClose(event);
			}
			catch (Exception e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
		mConnection.close();
		mConnection = null;
		for (Iterator it = new ArrayList(mListeners).iterator(); it.hasNext();) {
			try {
				((IManagedConnectionListener) it.next()).closed(event);
			}
			catch (Exception e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
	}
	
	/*package*/ void dispose() {
		if (isConnected()) {
			try {
				close();
			}
			catch (Exception e) {
			}
		}
		mListeners.clear();
		mProfile = null;
	}
	
	private class RestrictedConnection implements IConnection {

		public void close() {
			throw new UnsupportedOperationException();
		}

		public Throwable getConnectException() {
			return mConnection.getConnectException();
		}

		public IConnectionProfile getConnectionProfile() {
			return mConnection.getConnectionProfile();
		}

		public Object getRawConnection() {
			return mConnection.getRawConnection();
		}
	}
	
	private class RestrictedVersionProviderConnection extends
			RestrictedConnection implements IServerVersionProvider {

		public String getProviderName() {
			return ((IServerVersionProvider) mConnection).getProviderName();
		}

		public Version getProviderVersion() {
			return ((IServerVersionProvider) mConnection).getProviderVersion();
		}

		public String getTechnologyName() {
			return ((IServerVersionProvider) mConnection).getTechnologyName();
		}

		public Version getTechnologyVersion() {
			return ((IServerVersionProvider) mConnection)
					.getTechnologyVersion();
		}

	}
	
	private class CloneConnectionJob extends Job implements ICloneConnectionJob {
		
		private IConnection mConnection;

		public CloneConnectionJob() {
			super(ConnectivityPlugin.getDefault().getResourceString(
					"CreateConnectionJob.name", //$NON-NLS-1$
					new Object[] { getConnectionFactoryProvider().getName(),
							getConnectionProfile().getName()}));
			setUser(true);
		}

		public IConnection getConnection() {
			return mConnection;
		}

		protected IStatus run(IProgressMonitor monitor) {
			IStatus status = Status.OK_STATUS;
			monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);
			try {
				if (isWorkingOffline()) {
					mConnection = getConnectionFactoryProvider()
							.createOfflineConnection(getConnectionProfile(),
									monitor);
				}
				else {
					mConnection = getConnectionFactoryProvider()
							.createConnection(getConnectionProfile());
				}
				if (mConnection.getConnectException() != null) {
					status = new Status(IStatus.ERROR, 
					        ConnectivityPlugin.getSymbolicName(), -1,
							ConnectivityPlugin.getDefault().getResourceString(
									"CreateConnectionJob.error", //$NON-NLS-1$
									new Object[] {
											getConnectionFactoryProvider()
													.getName(),
											getConnectionProfile().getName(),
											mConnection.getConnectException()
													.getMessage()}),
							mConnection.getConnectException());
				}
			}
			catch (Exception e) {
                status = new Status(IStatus.ERROR, 
				        ConnectivityPlugin.getSymbolicName(), -1,
						ConnectivityPlugin.getDefault().getResourceString(
								"CreateConnectionJob.error", //$NON-NLS-1$
								new Object[] {
										getConnectionFactoryProvider()
												.getName(),
										getConnectionProfile().getName(),
										e.getMessage()}), e);
			}
			monitor.done();
			return status;
		}
		
	}

}
