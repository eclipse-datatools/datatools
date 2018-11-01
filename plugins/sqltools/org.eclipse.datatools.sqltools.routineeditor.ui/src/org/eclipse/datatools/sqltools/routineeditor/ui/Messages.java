/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.routineeditor.internal.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String sqleditor_SQLEditor_saveToDBActionError;
	public static String sqleditor_SQLEditor_refreshError;
	public static String sqleditor_SQLEditor_pasteSQLError;
	public static String sqleditor_SQLEditor_sqlFromPainter;
	public static String sqleditor_SQLEditorInput_initError;
	public static String sqleditor_SQLEditor_pasteSQLvariablePair;
	public static String sqleditor_SQLEditor_pasteSQLjdbcString;
	public static String sqleditor_SQLEditor_pasteSQLvarString;
	public static String sqleditor_SQLEditor_pasteSQLvariableIncorrectSyntax;
	public static String sqlEditor_SQLEditorContributor_bundleNull;
	public static String sqlEditor_SQLEditorDocumentProvider_failGetSource;
	public static String sqlEditor_SQLEditorDocumentProvider_saveDbFail;
	public static String sqlEditor_SQLEditorDocumentProvider_getTextFileBuffer;
	public static String RoutineAnnotationModel_resourcechanged;
    public static String SQLEditor_error_save_title;
    public static String SQLEditor_error_save_notsupport;
    public static String SQLEditor_profile_disconnected;
    public static String SQLEditor_profile_information;
    public static String Editor_error_save_message;
    public static String Editor_error_save_title;
    public static String Editor_warning_save_delete;
	public static String plugin_internal_error;

	public static String SaveScopeResourcesHandler_1;
	public static String SaveScopeResourcesHandler_2;
	public static String SaveScopeResourcesHandler_3;


	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}