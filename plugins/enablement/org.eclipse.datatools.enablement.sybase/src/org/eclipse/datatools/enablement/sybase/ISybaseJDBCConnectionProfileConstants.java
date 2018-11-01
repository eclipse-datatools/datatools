/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.enablement.sybase;

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;


public interface ISybaseJDBCConnectionProfileConstants extends IJDBCConnectionProfileConstants{

	public static final String SYBASE_CATEGORY_ID = "org.eclipse.datatools.enablement.sybase"; //$NON-NLS-1$

	public static final String PROP_PREFIX = "org.eclipse.datatools.enablement.sybase.";//$NON-NLS-1$
	public static final String PROP_CATALOG = PROP_PREFIX + "catalog"; //$NON-NLS-1$
	public static final String PROP_SCHEMA = PROP_PREFIX + "schema"; //$NON-NLS-1$
	public static final String PROP_SPNAME = PROP_PREFIX + "spName"; //$NON-NLS-1$
	public static final String PROP_HOST = PROP_PREFIX + "host"; //$NON-NLS-1$
	public static final String PROP_PORT = PROP_PREFIX + "port"; //$NON-NLS-1$
	public static final String PROP_MULTI_DB_IS_SUPPORTED = PROP_PREFIX + "multiDBIsSupported"; //$NON-NLS-1$
	public static final String PROP_DB_NAME = DATABASE_NAME_PROP_ID;
	public static final String PROP_DRIVER_XACONNECTCLASS = PROP_PREFIX + "xaConnectClass"; //$NON-NLS-1$
}
