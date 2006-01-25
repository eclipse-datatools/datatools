/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.db.generic;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.eclipse.datatools.connectivity.DriverConnectionBase;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.IDBDriverDefinitionConstants;

public class JDBCConnection extends DriverConnectionBase {

	public static final String TECHNOLOGY_ROOT_KEY = "jdbc"; //$NON-NLS-1$
	public static final String TECHNOLOGY_NAME = GenericDBPlugin.getDefault()
			.getResourceString("JDBCConnection.technologyName"); //$NON-NLS-1$

	private Version mTechVersion = Version.NULL_VERSION;
	private Version mServerVersion = Version.NULL_VERSION;
	private String mServerName;

	public JDBCConnection(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
		open();
	}

	protected Object createConnection(ClassLoader cl) throws Throwable {
		Properties props = getConnectionProfile().getBaseProperties();
		String driverClass = getDriverDefinition().getProperty(
				IDBDriverDefinitionConstants.DRIVER_CLASS_PROP_ID);
		String connectURL = props
				.getProperty(IDBDriverDefinitionConstants.URL_PROP_ID);
		String uid = props
				.getProperty(IDBDriverDefinitionConstants.USERNAME_PROP_ID);
		String pwd = props
				.getProperty(IDBDriverDefinitionConstants.PASSWORD_PROP_ID);

		Properties connectionProps = new Properties();

		if (uid != null) {
			connectionProps.setProperty("user", uid);
		}
		if (pwd != null) {
			connectionProps.setProperty("password", pwd);
		}

		Driver jdbcDriver = (Driver) cl.loadClass(driverClass).newInstance();
		return jdbcDriver.connect(connectURL, connectionProps);
	}

	public void close() {
		Connection connection = (Connection) getRawConnection();
		if (connection != null) {
			try {
				connection.close();
			}
			catch (SQLException e) {
				// RJC Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getProviderName() {
		return mServerName;
	}

	public Version getProviderVersion() {
		return mServerVersion;
	}

	protected String getTechnologyRootKey() {
		return TECHNOLOGY_ROOT_KEY;
	}

	public String getTechnologyName() {
		return TECHNOLOGY_NAME;
	}

	public Version getTechnologyVersion() {
		return mTechVersion;
	}

	protected void initVersions() {
		try {
			DatabaseMetaData dbmd = ((Connection) getRawConnection())
					.getMetaData();
			try {
				mServerName = dbmd.getDatabaseProductName();
			}
			catch (Throwable e) {
			}
			try {
				String versionString = dbmd.getDatabaseProductVersion();
				if (versionString.indexOf('/') > 0) {
					// Special handling for ASE
					String versionComps[] = versionString.split("/", 4);
					if (versionComps.length > 2) {
						versionString = versionComps[1];
						if (versionComps.length > 3) {
							versionString += '.' + (versionComps[2]
									.startsWith("EBF") ? versionComps[2]
									.substring(3).trim() : versionComps[2]);
						}
						if (versionComps[0].length() > 0
								&& !versionComps[0].equals(mServerName)) {
							// Special case for ASIQ
							mServerName = versionComps[0];
						}
					}
				}
				mServerVersion = Version.valueOf(versionString);
			}
			catch (Throwable e) {
			}
			try {
				mTechVersion = new Version(dbmd.getJDBCMajorVersion(), dbmd
						.getJDBCMinorVersion(), 0, new String());
			}
			catch (Throwable e) {
			}
		}
		catch (SQLException e) {
		}
	}

}
