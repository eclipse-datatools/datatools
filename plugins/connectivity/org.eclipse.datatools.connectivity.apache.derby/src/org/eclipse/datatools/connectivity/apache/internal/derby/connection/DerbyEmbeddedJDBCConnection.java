/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 *      IBM Corporation - migrated to new wizard framework
 ******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnection;

/**
 * This class is used to represent Derby embedded connections. There are a
 * couple of particulars to Derby embedded connections which require special
 * treatement.
 * 
 * One, Derby only supports a single connection per JVM when operating as an
 * embedded DB. This class ensures that only one instance of an embedded DB
 * connection exists at any given time.
 * 
 * Two, java.sql.Connection.close() is not supported by the Derby embedded JDBC
 * driver. Embedded connections must be closed using the following:
 * java.sql.Driver.getConnection("&lt;url&gt;;shutdown=true").
 * 
 * @author rcernich
 * 
 * Created on Jan 30, 2006
 */
public class DerbyEmbeddedJDBCConnection extends JDBCConnection {

	/**
	 * Maps connection URLs to connections.
	 */
	private static Map sDerbyConnections = new HashMap();
	/**
	 * Maps connections to integers. The integer represents the number of
	 * DerbyEmbeddedJDBCConnection objects referencing the connection
	 */
	private static Map sConnectionReferenceCount = new HashMap();

	public DerbyEmbeddedJDBCConnection(IConnectionProfile profile,
										Class factoryClass) {
		super(profile, factoryClass);
	}

	public void close() {
		Connection connection = (Connection) getRawConnection();
		if (connection == null) {
			return;
		}
		synchronized (sDerbyConnections) {
			int count = ((Integer) sConnectionReferenceCount.get(connection))
					.intValue();
			if (count == 1) {
				/*
				 * If this is the last reference to the connection, close the
				 * connection.
				 */
				String baseDBURL = getBaseDBURL();
				try {
					/* The particulars of closing the connection. */
					String driverClass = getDriverDefinition().getProperty(
							IJDBCDriverDefinitionConstants.DRIVER_CLASS_PROP_ID);
					Driver driver = (Driver) connection.getClass()
							.getClassLoader().loadClass(driverClass)
							.newInstance();
					driver.connect(baseDBURL + ";shutdown=true",  //$NON-NLS-1$
							new Properties());
				}
				catch (InstantiationException e) {
					/*
					 * We shouldn't see this, because we needed this to create
					 * the connection
					 */
				}
				catch (IllegalAccessException e) {
					/*
					 * We shouldn't see this, because we needed this to create
					 * the connection
					 */
				}
				catch (ClassNotFoundException e) {
					/*
					 * We shouldn't see this, because we needed this to create
					 * the connection
					 */
				}
				catch (SQLException e) {
					// Successfully closed the connection
					sConnectionReferenceCount.remove(connection);
					sDerbyConnections.remove(baseDBURL);
				}
				catch (Exception e) {
					/*
					 * Can't get the driver. This might happen if the user
					 * modified or deleted the driver definition in the time
					 * since this connection was created.
					 */
				}
			}
			else {
				/* Otherwise, just decrement the reference count. */
				sConnectionReferenceCount.put(connection, new Integer(--count));
			}
		}
	}

	protected Object createConnection(ClassLoader cl) throws Throwable {
		Connection connection;
		synchronized (sDerbyConnections) {
			String dbName = getBaseDBURL();
			if (sDerbyConnections.containsKey(dbName)) {
				/*
				 * Get the existing connection and increment the reference
				 * count.
				 */
				connection = (Connection) sDerbyConnections.get(dbName);
				int count = ((Integer) sConnectionReferenceCount
						.get(connection)).intValue();
				sConnectionReferenceCount.put(connection, new Integer(++count));
			}
			else {
				/* Create the connection and initialize the referencing scheme. */
				connection = (Connection) super.createConnection(cl);
				sDerbyConnections.put(dbName, connection);
				sConnectionReferenceCount.put(connection, new Integer(1));
			}
		}
		return connection;
	}

	/**
	 * We're only concerned with the base part of the URL. This should work for
	 * every Derby URL form except those using the databaseName parameter to
	 * specify the database name.
	 * 
	 * @return the base URL
	 */
	private String getBaseDBURL() {
		String baseURL;
		String connectURL = getConnectionProfile().getBaseProperties()
				.getProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID);
		int propertyStart = connectURL.indexOf(';');
		if (propertyStart < 0) {
			baseURL = connectURL;
		}
		else {
			baseURL = connectURL.substring(0, propertyStart);
		}
		return baseURL;
	}

}
