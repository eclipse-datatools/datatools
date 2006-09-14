/*******************************************************************************
* Copyright (c) 2004, 2005 Sybase, Inc. and others.

* AAll rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     Sybase, Inc. - initial API and implementation
*******************************************************************************/
package org.eclipse.datatools.sqltools.core;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.core.messages";//$NON-NLS-1$

private Messages() {
// Do not instantiate
}

public static String DefaultDBFactory_version;
public static String NotSupportedSettingException_cause;
public static String plugin_internal_error;
public static String ProcIdentifierImpl_map_cant_be_null;
public static String ProcIdentifierImpl_invalid_identifier_string;
public static String DefaultDBFactory_vendor;
public static String DefaultSQLSyntax_exception_splitSQL;

static {
NLS.initializeMessages(BUNDLE_NAME, Messages.class);
}
}
