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
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.DriverConnectionBase;
import org.eclipse.datatools.connectivity.IConnectionProfile;

public class JDBCConnection extends DriverConnectionBase {

	public JDBCConnection(IConnectionProfile profile) {
		super(profile);
	}

	protected Object createConnection() throws Throwable {
		Properties props = getConnectionProfile().getBaseProperties();
		String driverClass = getDriverDefinition().getProperty(
				IDBDriverDefinitionConstants.PROP_DRIVER_CLASS);
		String connectURL = props
				.getProperty(IDBConnectionProfileConstants.PROP_CONNECT_URL);
		String uid = props.getProperty(ConnectionProfileConstants.PROP_UID);
		String pwd = props.getProperty(ConnectionProfileConstants.PROP_PWD);

		Properties connectionProps = new Properties();

		if (uid != null) {
			connectionProps.put("user", uid);
		}
		if (pwd != null) {
			connectionProps.put("password", pwd);
		}

		Driver jdbcDriver = (Driver) Class.forName(driverClass).newInstance();
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

}
