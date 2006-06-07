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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.ConnectEvent;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConfigurationType;
import org.eclipse.datatools.connectivity.IConnectListener;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.ProfileRule;

/**
 * @author rcernich, shongxum
 */
public class ConnectionProfile extends PlatformObject implements
		IConnectionProfile {

    static final String PROPERTY_PREFIX = "org.eclipse.ui.workbench.progress"; //$NON-NLS-1$

    public static final QualifiedName NO_IMMEDIATE_ERROR_PROMPT_PROPERTY = new QualifiedName(
            PROPERTY_PREFIX, "delayErrorPrompt"); //$NON-NLS-1$
    
    // (String id,Properties props)
	private Map mPropertiesMap = new HashMap();
	private String mName;
	private String mDescription;
	private String mParentProfile = ""; //$NON-NLS-1$
	private boolean mAutoConnect = false;
	private String mProfileId;
	private ConnectionProfileProvider mProvider = null;
	private boolean mConnected = false;
	private ListenerList mConnectListeners = new ListenerList();
	private ListenerList mPropertySetListeners = new ListenerList();
	private boolean mIsCreating;
	private String mInstanceID;
	private Map mFactoryIDToManagedConnection;

	public ConnectionProfile(String name, String desc, String providerID) {
		this(name, desc, providerID, "", false); //$NON-NLS-1$
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
		mProvider = (ConnectionProfileProvider) ConnectionProfileManager
				.getInstance().getProvider(mProfileId);
		mParentProfile = parentProfile;
		mAutoConnect = autoConnect;
		mIsCreating = true;
		mInstanceID = instanceID;
		
		initManagedConnections();
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
		return InternalProfileManager.getInstance().getProfileByName(mParentProfile);
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
		Properties oldProps = (Properties)mPropertiesMap.get(type);
		internalSetProperties(type, props);
		notifyManager();
		firePropertySetChangeEvent(type,oldProps,props);
	}

	public void internalSetProperties(String type, Properties props) {
		Properties newProps = new Properties();
		newProps.putAll(props);
		mPropertiesMap.put(type, newProps);
		InternalProfileManager.getInstance().setDirty(true);
	}

	public boolean arePropertiesComplete() {
		boolean retVal = mProvider.getPropertiesPersistenceHook()
				.arePropertiesComplete(
						(Properties) mPropertiesMap.get(mProfileId));
		for (Iterator it = getProfileExtensions().entrySet().iterator(); retVal
				&& it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			ProfileExtensionProvider pep = (ProfileExtensionProvider) entry
					.getValue();
			retVal = pep.getPropertiesPersistenceHook().arePropertiesComplete(
					(Properties) mPropertiesMap.get(entry.getKey()));
		}
		return retVal;
	}

	public boolean arePropertiesComplete(String type) {
		if (mProvider.getId().equals(type)) {
			return mProvider.getPropertiesPersistenceHook()
					.arePropertiesComplete(getBaseProperties());
		}
		ProfileExtensionProvider pep = (ProfileExtensionProvider) mProvider
				.getProfileExtensions().get(type);
		if (pep != null) {
			return pep.getPropertiesPersistenceHook().arePropertiesComplete(
					(Properties) mPropertiesMap.get(type));
		}
		return true;
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

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IConnectionProfile#getSharedConnection(java.lang.String)
	 */
	public IManagedConnection getManagedConnection(String type) {
		return (IManagedConnection) mFactoryIDToManagedConnection.get(type);
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
		/*
		 * Cancel any jobs currently associated with this profile. Specifically,
		 * we want to make sure any RefreshProfileJobs are cancelled to prevent
		 * deadlock in the UI thread.
		 */
		Platform.getJobManager().cancel(this);

		Job connectJob = new ConnectJob();
		connectJob.schedule();
		try {
			connectJob.join();
		}
		catch (InterruptedException e) {
		}

		return connectJob.getResult();
	}

	public void connect(IJobChangeListener listener) {
		Job connectJob = new ConnectJob();

		if (listener != null) {
			connectJob.addJobChangeListener(listener);
		}

		connectJob.schedule();
	}

	public IStatus disconnect() {
		/*
		 * Cancel any jobs currently associated with this profile. Specifically,
		 * we want to make sure any RefreshProfileJobs are cancelled to prevent
		 * deadlock in the UI thread.
		 */
		Platform.getJobManager().cancel(this);

		Job disconnectJob = new DisconnectJob();
		disconnectJob.schedule();
		try {
			disconnectJob.join();
		}
		catch (InterruptedException e) {
		}

		return disconnectJob.getResult();
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

	public void addPropertySetListener(IPropertySetListener listener) {
		mPropertySetListeners.add(listener);
	}

	public void removePropertySetListener(IPropertySetListener listener) {
		mPropertySetListeners.remove(listener);
	}

	protected void firePropertySetChangeEvent(String type,Properties oldProps, Properties newProps) {
		if (oldProps == null) {
			oldProps = new Properties();
		}
		IPropertySetChangeEvent event = new PropertySetChangeEvent(this, type, oldProps, newProps);
		firePropertySetChangeEvent(event);
	}

	protected void firePropertySetChangeEvent(IPropertySetChangeEvent event) {
		if (event.getChangedProperties().size() == 0) {
			return;
		}

		Object[] listeners = mPropertySetListeners.getListeners();
		for (int index = 0, count = listeners.length; index < count; ++index) {
			try {
				((IPropertySetListener) listeners[index])
						.propertySetChanged(event);
			}
			catch (Throwable e) {
				ConnectivityPlugin.getDefault().log(e);
			}
		}
	}
	
	/*package*/Map getPropertiesMap() {
		return Collections.unmodifiableMap(mPropertiesMap);
	}

	private void notifyManager() {
		if (!mIsCreating)
			try {
				InternalProfileManager.getInstance().modifyProfile(this);
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
					|| ((ConnectionProfile) obj).getInstanceID().equals(getInstanceID());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getInstanceID().hashCode();
	}
	
	/*package*/ void dispose() {
		Job disconnectJob = new DisconnectJob(true);
		disconnectJob.schedule();
		try {
			disconnectJob.join();
		}
		catch (InterruptedException e) {
		}
		mPropertySetListeners.clear();
		mConnectListeners.clear();
		for (Iterator it = mFactoryIDToManagedConnection.values()
				.iterator(); it.hasNext();) {
			ManagedConnection managedConnection = (ManagedConnection) it
					.next();
			managedConnection.dispose();
		}
		mFactoryIDToManagedConnection.clear();
	}
	
	private void initManagedConnections() {
		Map connectionFactories = mProvider.getConnectionFactories();
		connectionFactories.remove(ConnectionProfileConstants.PING_FACTORY_ID);
		mFactoryIDToManagedConnection = new HashMap(connectionFactories.size());
		for (Iterator it = connectionFactories.keySet().iterator(); it.hasNext();) {
			String factoryID = (String)it.next();
			mFactoryIDToManagedConnection.put(factoryID,new ManagedConnection(factoryID));
		}
	}

	private void addFailureMarker(IStatus result) {
		IResource resource = ResourcesPlugin.getWorkspace().getRoot();
		Map map = new HashMap(3);
		map.put(IMarker.MESSAGE, ConnectivityPlugin.getDefault().getResourceString(
				"marker.error", new String[] { getName(),result.getMessage()})); //$NON-NLS-1$
		map.put(IMarker.SEVERITY, new Integer(IMarker.SEVERITY_ERROR));
		map.put(IMarker.LOCATION, getName());
		map.put(IMarker.TRANSIENT, Boolean.TRUE.toString());

		try {
			IMarker marker = resource
					.createMarker("org.eclipse.datatools.connectivity.ui.profileFailure"); //$NON-NLS-1$
			marker.setAttributes(map);
		}
		catch (CoreException e) {
		}
	}

	private void removeOldFailureMarkers() {
		IResource resource = ResourcesPlugin.getWorkspace().getRoot();
		try {
			IMarker[] markers = resource.findMarkers(
					"org.eclipse.datatools.connectivity.ui.profileFailure", true, //$NON-NLS-1$
					IResource.DEPTH_INFINITE);
			for (int i = 0; i < markers.length; i++) {
				if (markers[i].getAttribute(IMarker.LOCATION, new String())
						.equals(getName())) {
					markers[i].delete();
				}
			}
		}
		catch (CoreException e) {
		}
	}

	public class ConnectJob extends Job {

		/**
		 * 
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
			Map connectionFactories = mProvider.getConnectionFactories();
			connectionFactories.remove(ConnectionProfileConstants.PING_FACTORY_ID);

			monitor.beginTask(getName(), mConnectListeners.size()
					+ connectionFactories.size() + 1);

			// Create a group monitor
			IProgressMonitor group = Platform.getJobManager()
					.createProgressGroup();
			group.beginTask(getName(), IProgressMonitor.UNKNOWN);

			// Create shared connections
			List connectionJobs = new ArrayList(connectionFactories.size());
			for (Iterator it = connectionFactories.values().iterator(); it
					.hasNext();) {
				CreateConnectionJob connectionJob = new CreateConnectionJob(
						(IConnectionFactoryProvider) it.next(),
						ConnectionProfile.this, this);
				connectionJob.setProgressGroup(group, IProgressMonitor.UNKNOWN);
				connectionJob.setProperty(ConnectionProfile.NO_IMMEDIATE_ERROR_PROMPT_PROPERTY,Boolean.TRUE);
				connectionJob.schedule();
				connectionJobs.add(connectionJob);
			}

			// Notify any connect listeners
			List connectionEventJobs = new ArrayList(mConnectListeners.size());
			ConnectEvent event = new ConnectEvent(ConnectionProfile.this);
			Object[] listeners = mConnectListeners.getListeners();
			for (int index = 0, count = listeners.length; index < count; ++index) {
				OpenConnectionEventJob connectionEventJob = new OpenConnectionEventJob(
						(IConnectListener) listeners[index], event, this);
				connectionEventJob.setProgressGroup(group, IProgressMonitor.UNKNOWN);
				connectionEventJob.setProperty(ConnectionProfile.NO_IMMEDIATE_ERROR_PROMPT_PROPERTY,Boolean.TRUE);
				connectionEventJob.schedule();
				connectionEventJobs.add(connectionEventJob);
			}

			// Wait for everyone to connect
			try {
				Platform.getJobManager().join(this, null);
			}
			catch (OperationCanceledException e) {
				// TODO: RJC: Cleanup any connections that got created
				e.printStackTrace();
			}
			catch (InterruptedException e) {
				// TODO: What exaclty???
				e.printStackTrace();
			}
			
			removeOldFailureMarkers();

			List statuses = new ArrayList(connectionJobs.size());
			boolean someOK = false;
			int severity = 0;
			for (Iterator it = connectionJobs.iterator(); it.hasNext();) {
				CreateConnectionJob connectionJob = (CreateConnectionJob) it
						.next();
				IStatus status = connectionJob.getResult();
				String factoryID = connectionJob.getConnectionFactoryProvider()
						.getId();
				if (status.getSeverity() == IStatus.ERROR) {
					addFailureMarker(status);
				}
				else {
					someOK = someOK || (status.getSeverity() == IStatus.OK)
							|| (status.getSeverity() == IStatus.INFO);
				}
				severity |= status.getSeverity();
				statuses.add(status);

				ManagedConnection managedConnection = (ManagedConnection) mFactoryIDToManagedConnection
						.get(factoryID);
				managedConnection.setConnection(connectionJob.getConnection());
			}

			if (someOK) {
				// Changes the connected flag since somebody actually connected
				mConnected = true;
				// Notify any property listeners of a state change
				firePropertySetChangeEvent(new PropertySetChangeEvent(
						ConnectionProfile.this,
						CONNECTION_PROFILE_PROPERTY_SET, CONNECTED_PROPERTY_ID,
						Boolean.toString(false), Boolean.toString(true)));
			}
			monitor.worked(1);

			if (severity == IStatus.OK) { // all OK
				retVal = Status.OK_STATUS;
			}
			else {
				retVal = new ConnectMultiStatus(IStatus.ERROR,
						(IStatus[]) statuses.toArray(new IStatus[statuses
								.size()]), ConnectivityPlugin.getDefault()
								.getResourceString(
										"ConnectJob.status.error", //$NON-NLS-1$
										new Object[] { ConnectionProfile.this
												.getName()}));
			}
			
			monitor.done();

			return retVal;
		}

		public boolean belongsTo(Object family) {
			return ConnectionProfile.this.equals(family);
		}
	}
	
	public class OpenConnectionEventJob extends Job {

		private IConnectListener mListener;
		private ConnectEvent mEvent;
		private Object mFamily;

		public OpenConnectionEventJob(IConnectListener listener,
										ConnectEvent event, Object family) {
			super(ConnectivityPlugin.getDefault().getResourceString(
					"OpenConnectionEventJob.name", //$NON-NLS-1$
					new Object[] { event.getConnectionProfile().getName()}));
			setSystem(true);
			mListener = listener;
			mEvent = event;
			mFamily = family;
		}

		public boolean belongsTo(Object family) {
			return mFamily != null && family == mFamily;
		}

		protected IStatus run(IProgressMonitor monitor) {
			IStatus status = Status.OK_STATUS;
			monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);
			try {
				mListener.openConnection(mEvent);
			}
			catch (CoreException e) {
				status = e.getStatus();
			}
			monitor.done();
			return status;
		}
	}

	public class DisconnectJob extends Job {

		private boolean mForce;
		
		public DisconnectJob() {
			this(false);
		}
		
		/**
		 * @param force
		 */
		public DisconnectJob(boolean force) {
			super(ConnectivityPlugin.getDefault().getResourceString(
					"DisconnectJob.name", //$NON-NLS-1$
					new Object[] { ConnectionProfile.this.getName()}));
			mForce = force;
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

			monitor.beginTask(getName(), mConnectListeners.size()
					+ mFactoryIDToManagedConnection.size() + 1);

			// Check to make sure nobody is using the connection
			Object[] listeners = mConnectListeners.getListeners();
			ConnectEvent event = new ConnectEvent(ConnectionProfile.this);
			if (!mForce) {
				for (Iterator it = mFactoryIDToManagedConnection.values()
						.iterator(); it.hasNext();) {
					if (!((ManagedConnection) it.next()).okToClose()) {
						monitor.setCanceled(true);
						return Status.CANCEL_STATUS;
					}
				}

				for (int index = 0, count = listeners.length; index < count; ++index) {
					try {
						if (!((IConnectListener) listeners[index])
								.okToClose(event)) {
							monitor.setCanceled(true);
							return Status.CANCEL_STATUS;
						}
					}
					catch (Exception e) {
						ConnectivityPlugin.getDefault().log(e);
					}
				}
			}

			// Create a group monitor
			IProgressMonitor group = Platform.getJobManager()
					.createProgressGroup();
			group.beginTask(getName(), IProgressMonitor.UNKNOWN);

			// Close shared connections
			List connectionJobs = new ArrayList(mFactoryIDToManagedConnection
					.size());
			for (Iterator it = mFactoryIDToManagedConnection.values().iterator(); it
					.hasNext();) {
				ManagedConnection mc = (ManagedConnection) it.next();
				if (!mc.isConnected()) {
					continue;
				}
				CloseManagedConnectionJob connectionJob = new CloseManagedConnectionJob(
						mc, this);
				connectionJob.setProgressGroup(group, IProgressMonitor.UNKNOWN);
				connectionJob.setProperty(ConnectionProfile.NO_IMMEDIATE_ERROR_PROMPT_PROPERTY,Boolean.TRUE);
				connectionJob.schedule();
				connectionJobs.add(connectionJob);
			}

			// Notify any connect listeners
			List connectionEventJobs = new ArrayList(mConnectListeners.size());
			for (int index = 0, count = listeners.length; index < count; ++index) {
				CloseConnectionEventJob connectionEventJob = new CloseConnectionEventJob(
						(IConnectListener) listeners[index], event, this);
				connectionEventJob.setProgressGroup(group, IProgressMonitor.UNKNOWN);
				connectionEventJob.setProperty(ConnectionProfile.NO_IMMEDIATE_ERROR_PROMPT_PROPERTY,Boolean.TRUE);
				connectionEventJob.schedule();
				connectionEventJobs.add(connectionEventJob);
			}

			// Wait for everyone to connect
			try {
				Platform.getJobManager().join(this, null);
			}
			catch (OperationCanceledException e) {
				// TODO: RJC: This puts us in a weird state, should we recreate
				// any connections that were closed
				return Status.CANCEL_STATUS;
			}
			catch (InterruptedException e) {
				// TODO: What exaclty???
			}

			List statuses = new ArrayList(connectionJobs.size());
			int severity = 0;
			for (Iterator it = connectionJobs.iterator(); it.hasNext();) {
				CloseManagedConnectionJob connectionJob = (CloseManagedConnectionJob) it
						.next();
				IStatus status = connectionJob.getResult();
				severity |= status.getSeverity();
				statuses.add(status);
			}

			// Changes the connected flag
			mConnected = false;
			// Notify any property listeners of a state change
			firePropertySetChangeEvent(new PropertySetChangeEvent(
					ConnectionProfile.this,
					CONNECTION_PROFILE_PROPERTY_SET, CONNECTED_PROPERTY_ID,
					Boolean.toString(true), Boolean.toString(false)));

			monitor.worked(1);

			if (severity == IStatus.OK) { // all OK
				retVal = Status.OK_STATUS;
			}
			else {
				retVal = new ConnectMultiStatus(IStatus.ERROR,
						(IStatus[]) statuses.toArray(new IStatus[statuses
								.size()]), ConnectivityPlugin.getDefault()
								.getResourceString(
										"DisconnectJob.status.info", //$NON-NLS-1$
										new Object[] { ConnectionProfile.this
												.getName()}));
			}

			monitor.done();

			return retVal;
		}

		public boolean belongsTo(Object family) {
			return ConnectionProfile.this.equals(family);
		}
	}

	public class CloseConnectionEventJob extends Job {

		private IConnectListener mListener;
		private ConnectEvent mEvent;
		private Object mFamily;

		public CloseConnectionEventJob(IConnectListener listener,
										ConnectEvent event, Object family) {
			super(ConnectivityPlugin.getDefault().getResourceString(
					"OpenConnectionEventJob.name", //$NON-NLS-1$
					new Object[] { event.getConnectionProfile().getName()}));
			setSystem(true);
			mListener = listener;
			mEvent = event;
			mFamily = family;
		}

		public boolean belongsTo(Object family) {
			return mFamily != null && family == mFamily;
		}

		protected IStatus run(IProgressMonitor monitor) {
			IStatus status = Status.OK_STATUS;
			monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);
			try {
				mListener.closeConnection(mEvent);
			}
			catch (CoreException e) {
				status = e.getStatus();
			}
			monitor.done();
			return status;
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