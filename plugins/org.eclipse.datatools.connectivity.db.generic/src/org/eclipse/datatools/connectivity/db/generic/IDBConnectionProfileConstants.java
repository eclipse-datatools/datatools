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

/**
 * Constant definitions for property keys used to identify DB connection profile
 * properties.
 */
public interface IDBConnectionProfileConstants extends
		IDBDriverDefinitionConstants {

	/**
	 * The connection profile ID for the generic DB connection profile type.
	 */
	public static final String CONNECTION_PROFILE_ID = "org.eclipse.datatools.connectivity.db.generic.connectionProfile";

	/**
	 * Property used to store JDBC connection properties (i.e. properties passed
	 * as a <code>Properties</code> object to <code>java.sql.Driver.connect()</code>).
	 */
	public static final String CONNECTION_PROPERTIES_PROP_ID = PROP_PREFIX + "connectionProperties";

}
