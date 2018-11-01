/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.internal.iseries;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnection;
import org.eclipse.datatools.connectivity.exceptions.DBNotStartException;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;

public class JDBCISeriesJDBCConnection extends JDBCConnection {

	private Version techVersion = Version.NULL_VERSION;
	private Version serverVersion = Version.NULL_VERSION;
	private String serverName;
	
	public JDBCISeriesJDBCConnection(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
	}
	
	public String getProviderName() {
		return serverName;
	}

	public Version getProviderVersion() {
		return serverVersion;
	}

	public Version getTechnologyVersion() {
		return techVersion;
	}

	protected void initVersions() {
		try {
			DatabaseMetaData dbmd = ((Connection) this.getRawConnection())
					.getMetaData();
			try {
				DatabaseDefinitionRegistry dbDefRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();			
				DatabaseDefinition dbDef = dbDefRegistry.recognize((Connection) this.getRawConnection());
				if (dbDef != null) {
					serverName = dbDef.getProductDisplayString() + " " + dbDef.getVersionDisplayString();
				}
			} catch (Exception e) {
			}
			try {
				techVersion = new Version(dbmd.getJDBCMajorVersion(), dbmd
						.getJDBCMinorVersion(), 0, new String());
			} catch (Exception e) {
			}
		} catch (SQLException e) {
		}
	}
	
	public Throwable getConnectException() {
	    Throwable exception = super.getConnectException();
	    if((exception != null)
	            &&(exception instanceof SQLException)
	            &&(((SQLException)exception).getErrorCode()==-4499)
	            &&(exception.getCause() instanceof java.net.ConnectException)){
	        exception = new DBNotStartException(exception);
	    }
	    return exception ;
	}
}
