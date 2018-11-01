/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.sql.Connection;
import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;

/**
 * This class extends SQLEditorConnectionInfo so that a ConnectionInfo object
 * can be constructed from a ConnectionProfile.
 * 
 * @author Jeremy Lindop
 */
public class SQLBuilderConnectionInfo extends SQLEditorConnectionInfo {

	protected IConnectionProfile _connectionProfile = null;
	protected static final String CANNOT_ENCODE = "CANNOT ENCODE SQLBuilderConnectionInfo BECAUSE IT WAS CONSTRUCTED FROM A ConnectionProfile, NOT A ConnectionInfo.";
	
	/**
	 * Constructs a <code>SQLBuilderConnectionInfo</code> by
	 * <code>ConnectionProfile</code>.
	 * 
	 * @param connectionProfile
	 *            <code>IConnectionProfile</code>
	 */
	public SQLBuilderConnectionInfo( IConnectionProfile connectionProfile) {
		super(ProfileUtil.getDatabaseVendorDefinitionId(connectionProfile));
		_connectionProfile = connectionProfile;
		
		this.setConnectionProfileName(_connectionProfile.getName());
		
		Properties props = _connectionProfile.getBaseProperties();
		Object objUserName = props.get("org.eclipse.datatools.connectivity.db.username");
		if (objUserName != null && objUserName instanceof String){
			this.setDefaultSchemaName((String)objUserName);
		}
		
		Object objDbName = props.get("org.eclipse.datatools.connectivity.db.databaseName");
		if (objDbName != null && objDbName instanceof String){
			this.setDatabaseName((String)objDbName);
		}
		
	}

	
	public IConnectionProfile getConnectionProfile() {
		if (_connectionProfile != null){
			return _connectionProfile;
		}
		else {
			return super.getConnectionProfile();
		}
	}

	public String getConnectionProfileName() {
		if (_connectionProfile != null){
			return _connectionProfile.getName();
		}
		else {
			return super.getConnectionProfileName();
		}
	}
	
	public Database getDatabase() {
		if (_connectionProfile != null){
			// This code comes from ProfileUtil.getDatabase()
            IManagedConnection mc = _connectionProfile.getManagedConnection(ConnectionInfo.class.getName());
            //during the profile connected event notification, 
            //IManagedConnection is connected while IConnectionProfile is not 
            if (!mc.isConnected())
            {
            	_connectionProfile.connect();
            }
            IConnection ic = mc.getConnection();
            if (ic == null)
            {
                return null;
            }
            Object rawConn = ic.getRawConnection();
            if (rawConn instanceof ConnectionInfo)
            {
                ConnectionInfo ci = (ConnectionInfo)rawConn;
                return ci.getSharedDatabase();
            }
            else {
            	return null;
            }
		}
		else {
			return super.getDatabase();
		}
	}


    /**
     * Retrieves the sharable connection from IControlConnection, which in turn delegates to the connectivity layer.
     */
	public Connection getSharedConnection() {
		if (_connectionProfile != null){
			// This code is taken from ProfileUtil.getReusableConnection
		   	if (!_connectionProfile.isConnected())
	    	{
	    		return null;
	    	}
	    	IManagedConnection managedConn = _connectionProfile.getManagedConnection("java.sql.Connection");
	    	if (managedConn == null || !managedConn.isConnected())
	    	{
	    		return null;
	    	}
	    	
	    	IConnection iconn = managedConn.getConnection();
	    	Connection conn = (Connection)iconn.getRawConnection();
	    	_sharedConn = conn;
		}
		else {
			_sharedConn = super.getSharedConnection();
		}
		return _sharedConn;
	}

	/**
	 * Encodes the given <code>SQLBuilderConnectionInfo</code> object for persistence
	 * @see decode()
	 * @param info
	 * @return A encoded String
	 */
	public String encode()
	{
		return super.encode();
	}
	
}
