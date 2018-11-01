/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author Hui Cao
 * 
 */
public final class Messages extends NLS {

	private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String ProceduralObjectEditorHandler_refresh;
    public static String ProceduralObjectEditorHandler_refresh_editor;
    public static String ProceduralObjectEditorHandler_refresh_q;
    public static String ProceduralObjectEditorHandler_save_error;
    public static String ProceduralObjectEditorHandler_save_error_debug;
    public static String SourcePage_page_name;
	public static String SourcePage_invalid_content;
	public static String SourcePage_owner_changed;
    public static String SourcePage_warning;
    public static String SourcePage_error;
    public static String SourcePage_warning_msg;
    public static String SourcePage_no_name;
    public static String SourcePage_syntax_error;
    public static String ProceduralObjectEditorHandler_cannot_find_proc;
    public static String ProceduralObjectEditorHandler_general;
    public static String ToggleBreakpoint_not_support;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}