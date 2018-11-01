/*******************************************************************************
 * Copyright (c) 2005, 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 *      IBM Corporation - migrated to new wizard framework
 ******************************************************************************/
package org.eclipse.datatools.connectivity.db.generic;

import org.eclipse.datatools.connectivity.IConnectionProfile;

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
public class JDBCConnection extends org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnection {
	public JDBCConnection(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
	}
}
