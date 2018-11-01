/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

import java.io.IOException;
import java.sql.Connection;

import org.eclipse.core.resources.IProject;
import org.eclipse.datatools.modelbase.sql.schema.Database;


public interface ConnectionInfo extends org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo {
	
	public static final String CONNECTION = "connection/"; //$NON-NLS-1$
	public static final String INFO_FILE_EXTENSION = "info"; //$NON-NLS-1$
	public static final String FILTER = "filter"; //$NON-NLS-1$
	
	/**
	 * Change the connection info name.
	 * @param name - a user friendly name. It cannot be null.
	 */
	public void setName(String name);
	
	/**
	 * specify the extra class path to load the driver class.
	 * @param path semicolon separated class path
	 */
	public void   setLoadingPath(String path);
	
	/**
	 * 
	 * @param className the driver class full name.
	 */
	public void   setDriverClassName(String className);
	
	/**
	 * 
	 * @param url JDBC connection URL
	 */
	public void   setURL(String url);
	
	/**
	 * Helper function for property "user". The value can be set directly using Properties
	 * @param id value of property "user"
	 */
	public void setUserName(String id);

	/**
	 * Helper function for property "password". The value can be set directly using Properties
	 * @param password value of property "password"
	 */
	public void setPassword(String password);
		
	public void setDatabaseName(String databasename);
	
	/**
	 * Flag for auto-detecting vendor and version when connect.
	 */
	public void discoverDatabaseDefinitionWhenConnect();

	/**
	 * Helper function to create a JDBC connection.
	 * @return a JDBC connetion
	 */
	//	 TODO Restore once new connection manager is implemented
//	public Connection connect() throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException;

	/**
	 * Share a connection. If there is a shared connection already, an IllegalStateException will be thrown.
	 * If the function succeeds, notification will be sent out to all registered listeners
	 * @param connection a live JDBC connection
	 */
	public void setSharedConnection(Connection connection);
	
	/**
	 * Remove the shared connection. If no shared connection set, an IllegalStateException will be thrown.
	 * If the function succeeds, notification will be sent out to all registered listeners
	 */
	public void removeSharedConnection();
	
	/**
	 * Share a database associated to this connection info. If there is a shared database already,
	 * an IllegalStateException will be thrown.
	 * If the function succeeds, notification will be sent out to all registered listeners
	 * @param database
	 */
	public void setSharedDatabase(Database database);
	
	/**
	 * Remove the shared database. If no shared database set, an IllegalStateException will be thrown.
	 * If the function succeeds, notification will be sent out to all registered listeners
	 */
	public void removeSharedDatabase();
	
	/**
	 * Register a listener to sharing events.
	 * @param listener
	 */
	public boolean addConnectionSharingListener(ConnectionSharingListener listener);
	
	/**
	 * Remove a listener
	 * @param listener
	 */
	public boolean removeConnectionSharingListener(ConnectionSharingListener listener);
	
	/**
	 * Cache a database in the workspace. It could be a time consuming task.
	 * @param database
	 */
	public void cacheDatabase(Database database) throws IOException;
	
	/**
	 * set a filter.
	 * @param key
	 * @param filter
	 */
	public void addFilter(String key, ConnectionFilter filter);

	/**
	 * return the filter
	 * @param key
	 * @return
	 */
	public ConnectionFilter getFilter(String key);
	
	
	/**
	 * remove the filter
	 * @param key
	 * @return
	 */
	public void removeFilter(String key);
	
	/**
	 * Add a dependent project. If the project is already in the list, it will be ignored.
	 * @param proj
	 */
	public void addDependentProject(IProject proj);
	
	/**
	 * Remove a dependent project. If the project is not in the list, it will be ignored.
	 * @param proj
	 */
	public void removeDependentProject(IProject proj);	

	/**
	 * Add a filterListener
	 * @param listener
	 * @return
	 */
	public boolean addFilterListener(ConnectionFilterListener listener);

	/**
	 * Remove filterListener
	 * @param listener
	 * @return
	 */
	public boolean removeFilterListener(ConnectionFilterListener listener);


	/**
	 * Get database product version
	 * @return
	 */
    public String getDatabaseProductVersion();

}
