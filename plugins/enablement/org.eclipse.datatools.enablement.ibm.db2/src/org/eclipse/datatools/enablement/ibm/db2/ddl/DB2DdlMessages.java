/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.ddl;

import org.eclipse.osgi.util.NLS;

public final class DB2DdlMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.enablement.ibm.db2.ddl.DB2DdlMessages";//$NON-NLS-1$

	private DB2DdlMessages() {
	}

	public static String FE_ALIAS_TABLE_NOT_EXIST;
	public static String FE_DISTINCT_TYPE_HAS_NO_SOURCE_TYPE;
	public static String FE_VIEW_HAS_NO_BODY;
	public static String FE_ROUTINE_SOURCE_EMPTY;
	public static String FE_PARENT_TABLLE_OR_KEY_DO_NOT_EXIST;
	public static String FE_TRIGGER_ACTION_EMPTY;
			
	static {
		NLS.initializeMessages(BUNDLE_NAME, DB2DdlMessages.class);
	}
}
