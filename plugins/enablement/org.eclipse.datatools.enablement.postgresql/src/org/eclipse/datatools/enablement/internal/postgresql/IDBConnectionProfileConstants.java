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

/**
 * Constant definitions for property keys used to identify DB connection profile
 * properties.
 */
public interface IDBConnectionProfileConstants extends
		IDBDriverDefinitionConstants {

	/**
	 * The connection profile ID for the generic DB connection profile type.
	 */
	public static final String CONNECTION_PROFILE_ID = "org.eclipse.datatools.connectivity.db.generic.connectionProfile"; //$NON-NLS-1$

	/**
	 * The property page ID for the generic DB connection properties.
	 */
	public static final String CONNECTION_PROPERTY_PAGE_ID = "org.eclipse.datatools.connectivity.db.generic.profileProperties"; //$NON-NLS-1$

	/**
	 * Property used to store JDBC connection properties (i.e. properties passed
	 * as a <code>Properties</code> object to <code>java.sql.Driver.connect()</code>).
	 */
	public static final String CONNECTION_PROPERTIES_PROP_ID = PROP_PREFIX + "connectionProperties"; //$NON-NLS-1$

	/**
	 * Property used to store the persistence setting for the password.  A value
	 * of true indicates the password will be persisted within the workspace.
	 */
	public static final String SAVE_PASSWORD_PROP_ID = PROP_PREFIX + "savePWD"; //$NON-NLS-1$

}
