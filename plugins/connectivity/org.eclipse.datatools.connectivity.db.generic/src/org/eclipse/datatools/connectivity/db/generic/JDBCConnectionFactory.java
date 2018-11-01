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


/**
 * <code>org.eclipse.datatools.connectivity.IConnectionFactory</code> for
 * creating <code>java.sql.Connection</code> connections.
 * 
 * To use this class in a custom DB connection profile, you must make sure your
 * profile contains the following properties from
 * <code>org.eclipse.datatools.connectivity.db.generic.IDBConnectionProfileConstants</code>:
 * DRIVER_CLASS_PROP_ID, URL_PROP_ID, as well as
 * <code>org.eclipse.datatools.connectivity.ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID</code>.
 * 
 * The following properties from
 * <code>org.eclipse.datatools.connectivity.db.generic.IDBConnectionProfileConstants</code>
 * are optional: USERNAME_PROP_ID, PASSWORD_PROP_ID,
 * CONNECTION_PROPERTIES_PROP_ID
 * 
 * @author rcernich
 * 
 * Created on Oct 17, 2005
 */
public class JDBCConnectionFactory extends org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnectionFactory {

}
