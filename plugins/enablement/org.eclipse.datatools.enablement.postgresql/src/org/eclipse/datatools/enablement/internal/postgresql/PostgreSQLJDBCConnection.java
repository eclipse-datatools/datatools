/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.internal.postgresql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.eclipse.datatools.connectivity.DriverConnectionBase;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.Version;

/**
 * NON-API
 * 
 * IConnection implementation for <code>java.sql.Connection</code> objects.
 * This object is responsible for openening and closing JDBC connections.
 * 
 * The property keys specified in
 * <code>org.eclipse.datatools.connectivity.db.generic.IDBConnectionProfileConstants</code>
 * are used to create the connection.
 * 
 * Version information is provided by using <code>java.sql.DatabaseMetaData</code>.
 */
public class PostgreSQLJDBCConnection extends DriverConnectionBase {

	public static final String TECHNOLOGY_ROOT_KEY = "postgresql_jdbc"; //$NON-NLS-1$
	public static final String TECHNOLOGY_NAME = "PostgreSQL JDBC Connection"; //$NON-NLS-1$

	private Version mTechVersion = Version.NULL_VERSION;
	private Version mServerVersion = Version.NULL_VERSION;
	private String mServerName;

	public PostgreSQLJDBCConnection(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
		open();
	}

	protected Object createConnection(ClassLoader cl) throws Throwable {
		Properties props = getConnectionProfile().getBaseProperties();
		Properties connectionProps = new Properties();
		
		String driverClass = getDriverDefinition().getProperty(
				IDBConnectionProfileConstants.DRIVER_CLASS_PROP_ID);
		String connectURL = props
				.getProperty(IDBConnectionProfileConstants.URL_PROP_ID);
		String uid = props
				.getProperty(IDBConnectionProfileConstants.USERNAME_PROP_ID);
		String pwd = props
				.getProperty(IDBConnectionProfileConstants.PASSWORD_PROP_ID);
		String nameValuePairs = props
				.getProperty(IDBConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID);
		String propDelim = ",";//$NON-NLS-1$

		if (uid != null) {
			connectionProps.setProperty("user", uid); //$NON-NLS-1$
		}
		if (pwd != null) {
			connectionProps.setProperty("password", pwd); //$NON-NLS-1$
		}

		if (nameValuePairs != null && nameValuePairs.length() > 0) {
			String[] pairs = parseString(nameValuePairs, ","); //$NON-NLS-1$
			String addPairs = ""; //$NON-NLS-1$
			for (int i = 0; i < pairs.length; i++) {
				String[] namevalue = parseString(pairs[i], "="); //$NON-NLS-1$
				connectionProps.setProperty(namevalue[0], namevalue[1]);
				if (i == 0 || i < pairs.length - 1) {
					addPairs = addPairs + propDelim;
				}
				addPairs = addPairs + pairs[i];
			}
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
					String versionComps[] = versionString.split("/", 4); //$NON-NLS-1$
					if (versionComps.length > 2) {
						versionString = versionComps[1];
						if (versionComps.length > 3) {
							versionString += '.' + (versionComps[2]
									.startsWith("EBF") ? versionComps[2] //$NON-NLS-1$
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

	/**
	 * @param str_list
	 * @param token
	 * @return
	 */
	protected String[] parseString(String str_list, String token) {
		StringTokenizer tk = new StringTokenizer(str_list, token);
		String[] pieces = new String[tk.countTokens()];
		int index = 0;
		while (tk.hasMoreTokens())
			pieces[index++] = tk.nextToken();
		return pieces;
	}
}
