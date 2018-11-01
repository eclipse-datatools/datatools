/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.ddl;

import org.eclipse.osgi.util.NLS;

public final class LUWDdlMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.enablement.ibm.db2.luw.ddl.LUWDdlMessages";//$NON-NLS-1$

	private LUWDdlMessages() {
	}
	
	//FE 
	public static String FE_TABLESPACE_NO_CONTAINER;
	public static String FE_DATATYPE_IS_NULL;
	public static String FE_REFERENCED_PARENT_KEY_DOES_NOT_EXIST;
	public static String FE_REFERENCED_PARENT_TABLE_DOES_NOT_EXIST;
	public static String FE_VIEW_HAS_NO_BODY;
	public static String FE_MQT_HAS_NO_BODY;
	public static String FE_BUFFERPOOL_INVAILD_SIZE_VALUE;
	public static String FE_GENERATED_COLUMN_HAS_NO_EXPRESSION;
	public static String FE_ALTER_TABLE_ADD_COLUMN_IDENTITY_OPTIONS;
	public static String FE_INVALID_MODEL;
	public static String FE_INDEX_MEMBERS_NOT_SPECIFIED;

	static {
		NLS.initializeMessages(BUNDLE_NAME, LUWDdlMessages.class);
	}
}
