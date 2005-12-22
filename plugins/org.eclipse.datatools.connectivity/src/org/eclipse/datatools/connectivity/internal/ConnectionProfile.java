/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich, shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.datatools.connectivity.ConnectEvent;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConfigurationType;
import org.eclipse.datatools.connectivity.IConnectListener;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.ProfileRule;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPropertyListener;

/**
 * @author rcernich, shongxum
 */
public class ConnectionProfile extends PlatformObject implements
		IConnectionProfile {

	// (String id,Properties props)
	private Map mPropertiesMap = new HashMap();
	private String mName;
	private String mDescription;
	private String mParentProfile = ""; //$NON-NLS-1$
	private boolean mAutoConnect = false;
	private String mProfileId;
	private IConnectionProfileProvider mProvider = null;
	private boolean mConnected = false;
	private ListenerList mPropertyListeners = new ListenerList(2);
	private ListenerList mConnectListeners = new ListenerList(2);
	private boolean mIsCreating;
	private String mInstanceID;

	public ConnectionProfile(String name, String desc, String providerID) {
		this(name, desc, providerID, "", false);
	}

	public ConnectionProfile(String name, String desc, String providerID,
								String parentProfile) {
		this(name, desc, providerID, parentProfile, false);
	}

	public ConnectionProfile(String name, String desc, String providerID,
								String parentProfile, boolean autoConnect) {
		this(name, desc, providerID, parentProfile, autoConnect, null);
	}

	public ConnectionProfile(String name, String desc, String providerID,
								String parentProfile, boolean autoConnect,
								String instanceID) {
		mName = name;
		mDescription = desc;
		mProfileId = providerID;
		mProvider = ConnectionProfileManager.getInstance().getProvider(
				mProfileId);
		mParentProfile = parentProfile;
		mAutoConnect = autoConnect;
		mIsCreating = true;
		mInstanceID = instanceID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getName()
	 */
	public String getName() {
		return mName;
	}

	/*
	 * For internal use only
	 */
	public void setName(String name) {
		mName = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getDescription()
	 */
	public String getDescription() {
		return mDescription;
	}

	/**
	 * @return Returns the mAutoConnect.
	 */
	public boolean isAutoConnect() {
		return mAutoConnect;
	}

	/**
	 * @param autoConnect The mAutoConnect to set.
	 */
	public void setAutoConnect(boolean autoConnect) {
		mAutoConnect = autoConnect;
	}

	/*
	 * For internal use only
	 */
	public void setDescription(String desc) {
		mDescription = desc;
	}

	public String getInstanceID() {
		if (mInstanceID == null) {
			mInstanceID = UUID.createUUID().toString();
		}
		return mInstanceID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getParentProfile()
	 */
	public IConnectionProfile getParentProfile() {
		if (mParentProfile == null || mParentProfile.length() == 0) {
			return null;
		}
		return ProfileManager.getInstance().getProfileByName(mParentProfile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getBaseProperties()
	 */
	public Properties getBaseProperties() {
		return getProperties(mProfileId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#setBaseProperties(java.util.Properties)
	 */
	public void setBaseProperties(Properties props) {
		setProperties(mProfileId, props);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProperties(java.lang.String)
	 */
	public Properties getProperties(String type) {
		Properties retVal = new Properties();
		Properties props = (Properties) mPropertiesMap.get(type);
		if (props != null) {
			retVal.putAll(props);
		}
		return retVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#setProperties(java.lang.String,
	 *      java.util.Properties)
	 */
	public void setProperties(String type, Properties props) {
		internalSetProperties(type, props);
		notifyManager();
		firePropertyChangedEvent(PROP_PROPERTIES);
	}

	public void internalSetProperties(String type, Properties props) {
		Properties newProps = new Properties();
		newProps.putAll(props);
		mPropertiesMap.put(type, newProps);
		ProfileManager.getInstance().setDirty(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#addPropertyListener(org.eclipse.ui.IPropertyListener)
	 */
	public void addPropertyListener(IPropertyListener listener) {
		mPropertyListeners.add(listener);
	}

	/**
	 * For internal use only, clients should use IConnectionProfile interface.
	 * This is a workaround for a deadlock case which rarely happens.
	 * 
	 * @param listener
	 */
	public void addPropertyListener(IPropertyListener listener,
			boolean insertBefore) {
		if (!insertBefore) {
			mPropertyListeners.add(listener);
		}
		else {
			Object[] listeners = mPropertyListeners.getListeners();
			mPropertyListeners.clear();
			mPropertyListeners.add(listener);
			for (int i = 0; i < listeners.length; i++) {
				mPropertyListeners.add(listeners[i]);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#removePropertyListener(org.eclipse.ui.IPropertyListener)
	 */
	public void removePropertyListener(IPropertyListener listener) {
		mPropertyListeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProviderName()
	 */
	public String getProviderName() {
		return mProvider.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProviderId()
	 */
	public String getProviderId() {
		return mProfileId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getCategory()
	 */
	public ICategory getCategory() {
		return mProvider.getCategory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getConfigurationType()
	 */
	public IConfigurationType getConfigurationType() {
		return mProvider.getConfigurationType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProfileExtensions()
	 */
	public Map getProfileExtensions() {
		return mProvider.getProfileExtensions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#createConnection(java.lang.String)
	 */
	public IConnection createConnection(String factoryId) {
		return mProvider.getConnectionFactory(factoryId).createConnection(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#createConnection(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public IConnection createConnection(String factoryId, String uid, String pwd) {
		return mProvider.getConnectionFactory(factoryId).createConnection(this,
				uid, pwd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProvider()
	 */
	public IConnectionProfileProvider getProvider() {
		return mProvider;
	}

	public void setConnected(boolean connected) {
		if (connected) {
			connect();
		}
		else {
			disconnect();
		}
	}

	public boolean isConnected() {
		return mConnected;
	}

	/* package */void internalSetConnected(boolean connected) {
		mConnected = connected;
	}

	public IStatus connect() {
		final IStatus result[] = new IStatus[1];
		BusyIndicator.showWhile(null, new Runnable() {

			public void run() {
				Display display = Display.getCurrent();
				if (display == null) {
					Job connectJob = new ConnectJob();
					connectJob.schedule();
					try {
						connectJob.join();
					}
					catch (InterruptedException e) {
					}
					result[0] = connectJob.getResult();
				}
				else {
					// Rapid calls to connect()/disconnect() can cause deadlock.
					// This is because there may be RefreshProfileJobs for this
					// profile in the job queue. Those refresh jobs are
					// synchronized with the UI thread. If we start this job
					// from a UI thread and then join it, we prevent the refresh
					// jobs from finishing (we're effectively blocking the UI
					// thread). We won't block and will make sure the current UI
					// thread continues to process any waiting work/events.
					JobChangeListener listener = new JobChangeListener();
					connect(listener);
					while (!listener.finished) {
						display.readAndDispatch();
						try {
							Thread.sleep(100);
						}
						catch (InterruptedException e) {
						}
					}
					result[0] = listener.result;
				}
			}
		});

		return result[0];
	}

	public void connect(IJobChangeListener listener) {
		Job connectJob = new ConnectJob();

		if (listener != null) {
			connectJob.addJobChangeListener(listener);
		}

		connectJob.schedule();
	}

	public IStatus disconnect() {
		final IStatus result[] = new IStatus[1];
		BusyIndicator.showWhile(null, new Runnable() {

			public void run() {
				Display display = Display.getCurrent();
				if (display == null) {
					Job disconnectJob = new DisconnectJob();
					disconnectJob.schedule();
					try {
						disconnectJob.join();
					}
					catch (InterruptedException e) {
					}
					result[0] = disconnectJob.getResult();
				}
				else {
					// Rapid calls to connect()/disconnect() can cause deadlock.
					// This is because there may be RefreshProfileJobs for this
					// profile in the job queue. Those refresh jobs are
					// synchronized with the UI thread. If we start this job
					// from a UI thread and then join it, we prevent the refresh
					// jobs from finishing (we're effectively blocking the UI
					// thread). We won't block and will make sure the current UI
					// thread continues to process any waiting work/events.
					JobChangeListener listener = new JobChangeListener();
					disconnect(listener);
					while (!listener.finished) {
						display.readAndDispatch();
						Thread.yield();
					}
					result[0] = listener.result;
				}
			}
		});

		return result[0];
	}

	public void disconnect(IJobChangeListener listener) {
		Job disconnectJob = new DisconnectJob();

		if (listener != null) {
			disconnectJob.addJobChangeListener(listener);
		}

		disconnectJob.schedule();
	}

	public void addConnectListener(IConnectListener listener) {
		mConnectListeners.add(listener);
	}

	public void removeConnectListener(IConnectListener listener) {
		mConnectListeners.remove(listener);
	}

	protected void firePropertyChangedEvent(int id) {
		Object[] listeners = mPropertyListeners.getListeners();
		for (int index = 0, count = listeners.length; index < count; ++index) {
			try {
				((IPropertyListener) listeners[index])
						.propertyChanged(this, id);
			}
			catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	private void notifyManager() {
		if (!mIsCreating)
			try {
				ProfileManager.getInstance().modifyProfile(this);
			}
			catch (ConnectionProfileException e) {
				ConnectivityPlugin.getDefault().log(e);
			}
	}

	public void setCreated() {
		mIsCreating = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof ConnectionProfile) {
			return obj == this
					|| ((ConnectionProfile) obj).getName().equals(getName());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getName().hashCode();
	}

	private static class JobChangeListener extends JobChangeAdapter {

		public IStatus result;
		public boolean finished = false;

		public void done(IJobChangeEvent event) {
			finished = true;
			result = event.getResult();
		}
	}

	public class ConnectJob extends Job {

		/**
		 * @param name
		 */
		public ConnectJob() {
			super(ConnectivityPlugin.getDefault().getResourceString(
					"ConnectJob.name", //$NON-NLS-1$
					new Object[] { ConnectionProfile.this.getName()}));
			setUser(true);
			setSystem(false);
			setRule(new ProfileRule(ConnectionProfile.this));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.internal.jobs.InternalJob#run(org.eclipse.core.runtime.IProgressMonitor)
		 */
		protected IStatus run(IProgressMonitor monitor) {
			if (isConnected()) {
				return Status.OK_STATUS;
			}

			IStatus retVal;

			monitor.beginTask(getName(), mConnectListeners.size() + 1);

			mConnected = true;

			List statuses = new ArrayList();
			boolean someOK = false;
			int severity = 0;
			ConnectEvent event = new ConnectEvent(ConnectionProfile.this);
			Object[] listeners = mConnectListeners.getListeners();
			for (int index = 0, count = listeners.length; index < count; ++index) {
				try {
					((IConnectListener) listeners[index]).openConnection(event);
					someOK = true;
					statuses.add(Status.OK_STATUS);
				}
				catch (CoreException e) {
					IStatus status = e.getStatus();
					statuses.add(status);
					someOK = someOK || (status.getSeverity() == IStatus.OK)
							|| (status.getSeverity() == IStatus.INFO);
					severity |= status.getSeverity();
				}
				monitor.worked(1);
			}

			// For backward compatibility with setConnected()
			firePropertyChangedEvent(PROP_CONNECTED);
			monitor.worked(1);

			if (severity == IStatus.OK) { // all OK
				retVal = Status.OK_STATUS;
			}
			else if (someOK) {
				retVal = new ConnectMultiStatus(IStatus.ERROR,
						(IStatus[]) statuses.toArray(new IStatus[statuses
								.size()]), ConnectivityPlugin.getDefault()
								.getResourceString(
										"ConnectJob.status.info", //$NON-NLS-1$
										new Object[] { ConnectionProfile.this
												.getName()}));
			}
			else {
				retVal = new ConnectMultiStatus(IStatus.ERROR,
						(IStatus[]) statuses.toArray(new IStatus[statuses
								.size()]), ConnectivityPlugin.getDefault()
								.getResourceString(
										"ConnectJob.status.error", //$NON-NLS-1$
										new Object[] { ConnectionProfile.this
												.getName()}));
				mConnected = false;

				// For backward compatibility with setConnected()
				firePropertyChangedEvent(PROP_CONNECTED);
			}

			return retVal;
		}

		public boolean belongsTo(Object family) {
			return ConnectionProfile.this.equals(family);
		}
	}

	public class DisconnectJob extends Job {

		/**
		 * @param name
		 */
		public DisconnectJob() {
			super(ConnectivityPlugin.getDefault().getResourceString(
					"DisconnectJob.name", //$NON-NLS-1$
					new Object[] { ConnectionProfile.this.getName()}));
			setUser(true);
			setSystem(false);
			setRule(new ProfileRule(ConnectionProfile.this));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.internal.jobs.InternalJob#run(org.eclipse.core.runtime.IProgressMonitor)
		 */
		protected IStatus run(IProgressMonitor monitor) {
			if (!isConnected()) {
				return Status.OK_STATUS;
			}

			IStatus retVal;

			monitor.beginTask(getName(), mConnectListeners.size() + 1);

			Object[] listeners = mConnectListeners.getListeners();
			ConnectEvent event = new ConnectEvent(ConnectionProfile.this);
			for (int index = 0, count = listeners.length; index < count; ++index) {
				if (!((IConnectListener) listeners[index]).okToClose(event)) {
					monitor.setCanceled(true);
					return Status.CANCEL_STATUS;
				}
			}

			mConnected = false;

			List statuses = new ArrayList();
			boolean someOK = false;
			int severity = 0;
			for (int index = 0, count = listeners.length; index < count; ++index) {
				try {
					((IConnectListener) listeners[index])
							.closeConnection(event);
				}
				catch (CoreException e) {
					IStatus status = e.getStatus();
					statuses.add(status);
					someOK = someOK && status.getSeverity() == IStatus.OK
							|| status.getSeverity() == IStatus.INFO;
					severity |= status.getSeverity();
				}
				monitor.worked(1);
			}

			// For backward compatibility with setConnected()
			firePropertyChangedEvent(PROP_CONNECTED);
			monitor.worked(1);

			if (severity == IStatus.OK) { // all OK
				retVal = Status.OK_STATUS;
			}
			else if (someOK) {
				retVal = new ConnectMultiStatus(IStatus.INFO,
						(IStatus[]) statuses.toArray(new IStatus[statuses
								.size()]), ConnectivityPlugin.getDefault()
								.getResourceString(
										"DisconnectJob.status.info", //$NON-NLS-1$
										new Object[] { ConnectionProfile.this
												.getName()}));
			}
			else {
				retVal = new ConnectMultiStatus(IStatus.ERROR,
						(IStatus[]) statuses.toArray(new IStatus[statuses
								.size()]), ConnectivityPlugin.getDefault()
								.getResourceString(
										"DisconnectJob.status.error", //$NON-NLS-1$
										new Object[] { ConnectionProfile.this
												.getName()}));
			}

			return retVal;
		}

		public boolean belongsTo(Object family) {
			return ConnectionProfile.this.equals(family);
		}
	}

	private static class ConnectMultiStatus extends MultiStatus {

		public ConnectMultiStatus(int severity, IStatus[] newChildren,
									String message) {
			super(
					ConnectivityPlugin.getDefault().getBundle()
							.getSymbolicName(), -1, newChildren, message, null);
			setSeverity(severity);
		}
	}
}