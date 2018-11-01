/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.drivers.jdbc;

/**
 * Constant definitions for property keys used to identify DB driver definition
 * properties.
 */
public interface IJDBCDriverDefinitionConstants {
	/**
	 * Base category ID for DB driver definitions.
	 */
	public static final String DATABASE_CATEGORY_ID = "org.eclipse.datatools.connectivity.db.driverCategory"; //$NON-NLS-1$

	/**
	 * Property prefix used for property keys
	 */
	public static final String PROP_PREFIX = "org.eclipse.datatools.connectivity.db."; //$NON-NLS-1$

	/**
	 * Property used to store the DB vendor name.  This property is used in
	 * resolving the dbdefinition referenced by the driver.
	 */
	public static final String DATABASE_VENDOR_PROP_ID = PROP_PREFIX + "vendor"; //$NON-NLS-1$

	/**
	 * Property used to store the DB version.  This property is used in
	 * resolving the dbdefinition referenced by the driver.
	 */
	public static final String DATABASE_VERSION_PROP_ID = PROP_PREFIX + "version"; //$NON-NLS-1$
	
	/**
	 * Property used to store the driver class name.  This property is used in
	 * creating JDBC connections.
	 */
	public static final String DRIVER_CLASS_PROP_ID = PROP_PREFIX + "driverClass"; //$NON-NLS-1$

	/**
	 * Property used to store the default DB name value.  This property is used
	 * by the generic DB connection profile wizard during initialization.  This
	 * property is also used by the DB connection profile.
	 */
	public static final String DATABASE_NAME_PROP_ID = PROP_PREFIX + "databaseName"; //$NON-NLS-1$

	/**
	 * Property used to store the default connection URL.  This property is used
	 * the generic DB connection profile wizard during initialization.  This
	 * property is also used by the DB connection profile.
	 */
	public static final String URL_PROP_ID = PROP_PREFIX + "URL"; //$NON-NLS-1$
	
	/**
	 * Property used to store the default user name.  This property is used
	 * the generic DB connection profile wizard during initialization.  This
	 * property is also used by the DB connection profile.
	 */
	public static final String USERNAME_PROP_ID = PROP_PREFIX + "username"; //$NON-NLS-1$
	
	/**
	 * Property used to store the default password.  This property is used
	 * the generic DB connection profile wizard during initialization.  This
	 * property is also used by the DB connection profile.
	 */
	public static final String PASSWORD_PROP_ID = PROP_PREFIX + "password"; //$NON-NLS-1$
}
