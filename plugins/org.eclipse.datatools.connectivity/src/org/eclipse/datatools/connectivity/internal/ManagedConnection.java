/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.datatools.connectivity.ConnectEvent;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IManagedConnectionListener;
import org.eclipse.datatools.connectivity.IServerVersionProvider;
import org.eclipse.datatools.connectivity.Version;

public class ManagedConnection implements IManagedConnection {

	private IConnection mConnection;
	private String mFactoryID;
	private Set mListeners = new HashSet();

	public ManagedConnection(String factoryID) {
		super();
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
		CreateConnectionJob connectJob = new CreateConnectionJob(
				getConnectionProfile().getProvider().getConnectionFactory(
						getFactoryID()), getConnectionProfile(), null);
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
		CreateConnectionJob connectJob = new CreateConnectionJob(
				getConnectionProfile().getProvider().getConnectionFactory(
						getFactoryID()), getConnectionProfile(), null);
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
		return mConnection.getConnectionProfile();
	}

	/* package */void setConnection(IConnection connection) {
		mConnection = connection;
		if (isConnected()) {
			ConnectEvent event = new ConnectEvent(getConnectionProfile(), this);
			for (Iterator it = new ArrayList(mListeners).iterator(); it
					.hasNext();) {
				try {
					((IManagedConnectionListener) it.next()).opened(event);
				}
				catch (Exception e) {
					ConnectivityPlugin.getDefault().log(e);
				}
			}
		}
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

}
