/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.db.generic;

public interface IDBDriverDefinitionConstants {
	public static final String PROP_PREFIX = "org.eclipse.datatools.connectivity.db."; //$NON-NLS-1$

	public static final String DATABASE_CATEGORY_ID = "org.eclipse.datatools.connectivity.db.driverCategory"; //$NON-NLS-1$
	public static final String DATABASE_VENDOR_PROP_ID = PROP_PREFIX + "vendor"; //$NON-NLS-1$
	public static final String DATABASE_VERSION_PROP_ID = PROP_PREFIX + "version"; //$NON-NLS-1$
	
	public static final String DRIVER_CLASS_PROP_ID = PROP_PREFIX + "driverClass"; //$NON-NLS-1$
	public static final String DATABASE_NAME_PROP_ID = PROP_PREFIX + "databaseName"; //$NON-NLS-1$
	public static final String URL_PROP_ID = PROP_PREFIX + "URL"; //$NON-NLS-1$
	public static final String USERNAME_PROP_ID = PROP_PREFIX + "username"; //$NON-NLS-1$
	public static final String PASSWORD_PROP_ID = PROP_PREFIX + "password"; //$NON-NLS-1$
}
