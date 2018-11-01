/*******************************************************************************
 * Copyright (c) 2001, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Actuate Corporation - added messages
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.util;

import org.eclipse.osgi.util.NLS;

public class GenericCatalogMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.connectivity.sqm.internal.core.l10n.GenericCatalog";//$NON-NLS-1$

	private GenericCatalogMessages() {
	}

	public static String DEFN_MISSING_ATTRIBUTE_VALUES;
    public static String FE_VIEW_HAS_NO_BODY;
	public static String FE_TRIGGER_ACTION_EMPTY;
	public static String FE_PARENT_TABLLE_OR_KEY_DO_NOT_EXIST;
	public static String FE_REFERENCE_CONSTAINT_HAS_NO_KEY;
	public static String FE_INDEX_HAS_NO_MEMBER;
	public static String FE_ELEMENT_HAS_NO_TYPE;
    public static String RDBCorePlugin_NO_DEFAULT_WORKSPACE;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, GenericCatalogMessages.class);
	}
}
