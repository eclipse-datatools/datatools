/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.derby.core.services;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.db.derby.core.DerbyControlConnection;

/**
 * @author Hui Cao
 *
 */
public class DerbyConnectionService extends ConnectionService
{

	public IControlConnection createControlConnection(DatabaseIdentifier databaseIdentifier) throws SQLException {
		return new DerbyControlConnection(EditorCorePlugin.getControlConnectionManager(), databaseIdentifier);
	}

	public Connection createConnection(String profileName, String dbName) {
		try {
			Connection jdbcConn = ProfileUtil.getReusableConnection(new DatabaseIdentifier(profileName, dbName));
	    	IConnectionProfile profile = ProfileUtil.getProfile(profileName);
	    	IManagedConnection managedConn = profile.getManagedConnection(ConnectionInfo.class.getName());
	    	IConnection iConn = managedConn.getConnection();
	    	ConnectionInfo connInfo = (ConnectionInfo)iConn.getRawConnection();
	    	
	    	//this must be an embeded derby connection
	    	if (jdbcConn == connInfo.getSharedConnection())
	    	{
	    		return jdbcConn;
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.createConnection(profileName, dbName);
	}


}
