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

public interface IDBDriverDefinitionConstants {
	
	public static final String DATABASE_CATEGORY_ID = "org.eclipse.datatools.connectivity.db.driverCategory"; //$NON-NLS-1$

	public static final String PROP_PREFIX = "org.eclipse.datatools.connectivity.db."; //$NON-NLS-1$

	public static final String PROP_DRIVER_CLASS = PROP_PREFIX + "driverClass"; //$NON-NLS-1$
	public static final String PROP_DB_DEFINITION_FILE = PROP_PREFIX
			+ "dbdefinition"; //$NON-NLS-1$
	public static final String PROP_DEFAULT_URL = PROP_PREFIX + "defaultURL"; //$NON-NLS-1$
	public static final String PROP_DEFAULT_UID = PROP_PREFIX + "defaultUID"; //$NON-NLS-1$
	public static final String PROP_DEFAULT_PWD = PROP_PREFIX + "defaultPWD"; //$NON-NLS-1$

}
