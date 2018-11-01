/*******************************************************************************
 * Copyright (c) 2006, 2007, 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.connectivity;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.db.generic.JDBCConnection;

/**
 * JDBC connection object to provide support for Ingres databases.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresJDBCConnection extends JDBCConnection {

	private String mServerName;
	
	private Version mServerVersion;
	
	private Version mTechVersion;

	/**
	 * Constructor
	 * 
	 * @param profile
	 * @param factoryClass
	 */
	public IngresJDBCConnection(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnection#getProviderName()
	 */
	public String getProviderName() {
		return mServerName;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnection#getProviderVersion()
	 */
	public Version getProviderVersion() {
		return mServerVersion;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnection#getTechnologyVersion()
	 */
	public Version getTechnologyVersion() {
		return mTechVersion;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnection#initVersions()
	 */
	protected void initVersions() {
		try {
			DatabaseMetaData dbmd = ((Connection) getRawConnection())
					.getMetaData();
			try {
				mServerName = dbmd.getDatabaseProductName();
			} catch (Throwable e) {
			}
			try {
				String versionString = dbmd.getDatabaseProductVersion();
				mServerVersion = VersionHelper.valueOf(versionString);
			} catch (Throwable e) {
			}
			try {
				mTechVersion = new Version(dbmd.getJDBCMajorVersion(), dbmd
						.getJDBCMinorVersion(), 0, "");
			} catch (Throwable e) {
			}
		} catch (SQLException e) {
		}
	}

}
