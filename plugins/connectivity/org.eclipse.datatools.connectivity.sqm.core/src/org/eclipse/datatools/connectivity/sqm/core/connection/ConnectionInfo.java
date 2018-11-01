/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.connection;

import java.sql.Connection;
import java.util.Iterator;
import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.schema.Database;


/**
 * Public ConnectionInfo interface - extended internally
 */
public interface ConnectionInfo {
	
	/**
	 * A user friendly name of the connection info.
	 * @return the connection info name - null means it is not named.
	 */
	public String getName();
	
	/**
	 * @return the database definition associated to this connection info
	 */
	public DatabaseDefinition getDatabaseDefinition();
	
	/**
	 * 
	 * @return the JDBC driver class full name.
	 */
	public String getDriverClassName();
	
	/**
	 * 
	 * @return JDBC connection URL
	 */
	public String getURL();
	
	/**
	 * Helper function for property "user". The value can be accessed directly using Properties
	 * @return value of property "user" 
	 */
	public String getUserName();
	
	/**
	 * Helper function for property "password". The value can be accessed directly using Properties
	 * @return value of property "password" 
	 */
	public String getPassword();

	/*
	 * the following 2 method are used as a work around for some databases 
	 */
	public String getDatabaseName();
	
	public String getIdentifierQuoteString();

	/**
	 * 
	 * @return the properties for JDBC connection
	 */
	public Properties getProperties();
	
	/**
	 * Retrive the shared connection.
	 * @return if no shared connection set, return null.
	 */
	public Connection getSharedConnection();
	
	/**
	 * retrieve the shared database
	 * @return if no shared databsae set, return null
	 */		
	public Database getSharedDatabase();
	
	/**
	 * Load a database from workspace cache.
	 * @return
	 */
	public Database getCachedDatabase();
	
	public long getCachedDatabaseTimestamp();

	/**
	 * return all the connection filters
	 *
	 */
	public Iterator getFilters();
	
	/**
	 * return the profile from the connection
	 * @return IConnectionProfile
	 */
	public IConnectionProfile getConnectionProfile();

}
