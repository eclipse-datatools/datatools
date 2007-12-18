/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.internal.zseries;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnection;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;

public class JDBCZSeriesJDBCConnection extends JDBCConnection {

	private Version techVersion = Version.NULL_VERSION;
	private Version serverVersion = Version.NULL_VERSION;
	private String serverName;
	
	public JDBCZSeriesJDBCConnection(IConnectionProfile profile, Class factoryClass) {
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
				Properties props = getConnectionProfile().getBaseProperties();
				String vendor = (String)props.get(IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID);
				String version = (String)props.get(IJDBCDriverDefinitionConstants.DATABASE_VERSION_PROP_ID);
				DatabaseDefinitionRegistry dbDefRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
				DatabaseDefinition dbDef = dbDefRegistry.getDefinition(vendor, version);
				serverName = dbDef.getProductDisplayString() + " " + dbDef.getVersionDisplayString();
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
}
