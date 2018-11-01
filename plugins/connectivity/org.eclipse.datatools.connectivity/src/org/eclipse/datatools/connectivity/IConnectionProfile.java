/*******************************************************************************
 * Copyright (c) 2004-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeListener;

/**
 * The interface used when working with an instance of a connection profile.
 * 
 * @author rcernich
 * 
 * Created on Jan 14, 2004
 */
public interface IConnectionProfile {
	
	/**
	 * Property set ID for common connection profile properties
	 */
	static final String CONNECTION_PROFILE_PROPERTY_SET = IConnectionProfile.class.getName();

	/**
	 * Property ID for boolean "connected" state.
	 * @deprecated use {@link #CONNECTION_STATE_PROPERTY_ID}
	 */
	static final String CONNECTED_PROPERTY_ID = "connected"; //$NON-NLS-1$

	/**
	 * Property ID for "connection" state.
	 */
	static final String CONNECTION_STATE_PROPERTY_ID = "connectionState"; //$NON-NLS-1$

	/**
	 * Property ID for profile name.
	 */
	static final String NAME_PROPERTY_ID = "name"; //$NON-NLS-1$

	/**
	 * Property ID for profile description.
	 */
	static final String DESCRIPTION_PROPERTY_ID = "description"; //$NON-NLS-1$

	/**
	 * Property ID for profile auto-connect.
	 */
	static final String AUTO_CONNECT_PROPERTY_ID = "autoConnect"; //$NON-NLS-1$

	/**
	 * Property ID for profile instance ID.
	 */
	static final String INSTANCE_ID_PROPERTY_ID = "instanceID"; //$NON-NLS-1$
	
	/**
	 * Transient marker for profile instance.
	 */
	static final String TRANSIENT_PROPERTY_ID = "isTransient"; //$NON-NLS-1$

	// Connection states
	
	/**
	 * When the connection profile is in this state, no active connections to
	 * the server exist.
	 */
	static final int DISCONNECTED_STATE = 0;

	/**
	 * When the connection profile is in this state, active connections to the
	 * server exist.
	 */
	static final int CONNECTED_STATE = 1;

	/**
	 * When the connection profile is in this state, no active connections to
	 * the server exist. However, cached server data has been loaded into memory
	 * and is available for use.
	 */
	static final int WORKING_OFFLINE_STATE = 2;

	// General profile methods

	/**
	 * @return the name of this connection profile
	 */
	String getName();

	/**
	 * @return the description of this connection profile
	 */
	String getDescription();

	/**
	 * @return whether it's autoconnect or not
	 */
	boolean isAutoConnect();
	
	/**
	 * @return the static ID for this connection profile
	 */
	String getInstanceID();

	/**
	 * This method returns the connection profile that this connection profile
	 * derives from. For example, a JMS queue session may depend on an instance
	 * of a JNDI connection profile.
	 * 
	 * @return the connection profile this connection profile is derived from.
	 */
	IConnectionProfile getParentProfile();

	/**
	 * This method returns the properties managed by the base connection profile
	 * extension.
	 * 
	 * @return a properties object containing properties defined by the base
	 *         connection profile extension.
	 */
	Properties getBaseProperties();

	/**
	 * This method sets the properties managed by the base connection profile
	 * extension.
	 * 
	 * @param props the properties to set as the base properties.
	 */
	void setBaseProperties(Properties props);

	/**
	 * This method returns the properties associated with a connection profile
	 * profile extension. The type specified must match the id of a profile
	 * extension or the base profile.
	 * 
	 * @param type the type of properties.
	 * 
	 * @return the properties managed by the specified profile extension.
	 */
	Properties getProperties(String type);

	/**
	 * This method sets the properties managed by a specific profile extension.
	 * The type specified must match the id of a profile extension or the base
	 * profile.
	 * 
	 * @param type the type of properties.
	 * @param props the properties to set.
	 */
	void setProperties(String type, Properties props);
	
	/**
	 * Returns true if all property sets associated with this profile are
	 * complete; false if more information is required (e.g. authentication
	 * information is missing). This call is not intended to verify that the
	 * information will allow for a successful connection, just that the
	 * information is complete enough to make an attempt.
	 * 
	 * @return true if the property set is complete; false if additional
	 *         information must be specified.
	 */
	boolean arePropertiesComplete();

	/**
	 * Returns true if the specified property set type associated with this
	 * profile is complete; false if more information is required (e.g.
	 * authentication information is missing). This call is not intended to
	 * verify that the information will allow for a successful connection, just
	 * that the information is complete enough to make an attempt.
	 * 
	 * @param type the type of properties
	 * 
	 * @return true if the property set is complete; false if additional
	 *         information must be specified.
	 */
	boolean arePropertiesComplete(String type);

	/**
	 * Sets the connected state for this profile. The connected state is used by
	 * content extensions and other consumers of the framework as a hint
	 * indicating that they should create a connection to the profile.
	 * 
	 * @param connected the connected state.
	 * 
	 * @deprecated Use one of the connect() methods instead. This method now
	 *             delegates to connect().
	 */
	void setConnected(boolean connected);

	/**
	 * @return true if consumers should create connections to this profile
	 * 
	 * @deprecated Use {@link #getConnectionState()}
	 */
	boolean isConnected();
	
	/**
	 * @return the connection state of this profile. May be one of:
	 *         {@link #DISCONNECTED_STATE}, {@link #CONNECTED_STATE},
	 *         {@link #WORKING_OFFLINE_STATE}
	 */
	int getConnectionState();

	/**
	 * This method blocks until all registered connect listeners have been
	 * notified to open connections.
	 * 
	 * @return the status of the connect operation. Status.OK_STATUS if all went
	 *         well.
	 */
	IStatus connect();

	/**
	 * This method returns immediately after spawning a Job which notifies all
	 * registered connect listeners to open connections. The caller can pass in
	 * a job listener so it can be notified of the status of the job. The status
	 * returned by the job upon completion can be used to determine whether or
	 * not the connect operation was successful.
	 * 
	 * @param listener a job listener that can be used to notify the caller of
	 *        the state of the job spawned to open the connections. Can be null.
	 */
	void connect(IJobChangeListener listener);
	
	/**
	 * This method blocks until all registered connect listeners have been
	 * notified to open connections.  Unlike connect(), this method will not 
	 * spawn a job or display connection errors.
	 * 
	 * @return the status of the connect operation. Status.OK_STATUS if all went
	 *         well.
	 */
	IStatus connectWithoutJob();

	/**
	 * This method blocks until all registered connect listeners have been
	 * notified to close connections.
	 * 
	 * @return the status of the disconnect operation. Status.OK_STATUS if all
	 *         went well.
	 */
	IStatus disconnect();

	/**
	 * This method returns immediately after spawning a Job which notifies all
	 * registered connect listeners to close connections. The caller can pass in
	 * a job listener so it can be notified of the status of the job. The status
	 * returned by the job upon completion can be used to determine whether or
	 * not the disconnect operation was successful.
	 * 
	 * @param listener a job listener that can be used to notify the caller of
	 *        the state of the job spawned to close the connections. Can be
	 *        null.
	 */
	void disconnect(IJobChangeListener listener);

	/**
	 * This method blocks until all registered connect listeners that support
	 * working offline have been notified to restore offline data.
	 * 
	 * @return the status of the operation. Status.OK_STATUS if all went well.
	 */
	IStatus workOffline();

	/**
	 * This method returns immediately after spawning a Job which notifies all
	 * registered connect listeners that support working offline have been
	 * notified to restore offline data. The caller can pass in a job listener
	 * so it can be notified of the status of the job. The status returned by
	 * the job upon completion can be used to determine whether or not the
	 * operation was successful.
	 * 
	 * @param listener a job listener that can be used to notify the caller of
	 *        the state of the job spawned to restore offline data. Can be null.
	 */
	void workOffline(IJobChangeListener listener);
	
	/**
	 * @return true if one or more connection factories associated with this
	 *         connection profile's provider support working offline.
	 */
	boolean supportsWorkOfflineMode();
	
	/**
	 * @return true if this connection profile supports working offline and data
	 *         has been saved for working offline.
	 */
	boolean canWorkOffline();
	
	/**
	 * Saves the state of the connection profile for working in an offline mode.
	 * If the connection profile does not support working in an offline mode, no
	 * exception is thrown and the method will return immediately.
	 */
	IStatus saveWorkOfflineData();

	/**
	 * Saves the state of the connection profile for working in an offline mode.
	 * Same as {@link #saveWorkOfflineData()} except that it returns
	 * immediately, without waiting for the save offline data job to complete.
	 */
	void saveWorkOfflineData(IJobChangeListener listener);

	/**
	 * Add a connect listener to this profile.
	 * 
	 * @param listener
	 * 
	 * @deprecated use IManagedConnection.removeConnectionListener()
	 */
	void addConnectListener(IConnectListener listener);

	/**
	 * Remove a connect listener from this profile.
	 * 
	 * @param listener
	 * 
	 * @deprecated use IManagedConnection.removeConnectionListener()
	 */
	void removeConnectListener(IConnectListener listener);
	
	/**
	 * @param type the ID of the connection factory responsible for creating the
	 *        connection
	 * 
	 * @return the managed connection
	 */
	IManagedConnection getManagedConnection(String type);

	/**
	 * Add property listener to this profile. Property events sent:
	 * 
	 * @param listener
	 */
	void addPropertySetListener(IPropertySetListener listener);

	/**
	 * @param listener
	 */
	void removePropertySetListener(IPropertySetListener listener);

	// ConnectionProfileProvider derived methods

	/**
	 * This method is a shortcut for getProvider().getName().
	 * 
	 * @return the name of the provider managing this profile
	 * 
	 * @see IConnectionProfileProvider#getConfigurationType()
	 */
	String getProviderName();

	/**
	 * This method is a shortcut for getProvider().getProviderId().
	 * 
	 * @return the id of the provider managing this profile
	 */
	String getProviderId();

	/**
	 * This method is a shortcut for getProvider().getCategory().
	 * 
	 * @return the category this connection profile belongs to.
	 * 
	 * @see IConnectionProfileProvider#getCategory()
	 */
	ICategory getCategory();

	/**
	 * This method is a shortcut for getProvider().getConfigurationType().
	 * 
	 * @return the configuration type of this connection profile.
	 * 
	 * @see IConnectionProfileProvider#getConfigurationType()
	 * 
	 * @deprecated
	 */
	IConfigurationType getConfigurationType();

	/**
	 * This method is a shortcut for getProvider().getProfileExtensions().
	 * 
	 * @return a list of profile extensions supporting this profile
	 * 
	 * @see IConnectionProfileProvider#getProfileExtensions()
	 */
	Map getProfileExtensions();

	/**
	 * This method is a shortcut for
	 * getProvider()getConnectionFactory(factoryId).createConnection(this).
	 * 
	 * @param factory the connection factory id
	 * 
	 * @return a connection if successful; otherwise null
	 * 
	 * @see IConnectionFactoryProvider#createConnection(IConnectionProfile)
	 */
	IConnection createConnection(String factory);

	/**
	 * This method is a shortcut for
	 * getProvider()getConnectionFactory(factoryId).createConnection(this,uid,pwd).
	 * 
	 * @param factoryId the connection factory id
	 * @param uid the user id
	 * @param pwd the user's password
	 * 
	 * @return a connection if successful; otherwise null
	 * 
	 * @see IConnectionFactoryProvider#createConnection(IConnectionProfile,String,String)
	 */
	IConnection createConnection(String factoryId, String uid, String pwd);

	/**
	 * Returns the provider responsible for managing this connection profile.
	 * 
	 * @return the provider managing this connection profile
	 */
	IConnectionProfileProvider getProvider();

}