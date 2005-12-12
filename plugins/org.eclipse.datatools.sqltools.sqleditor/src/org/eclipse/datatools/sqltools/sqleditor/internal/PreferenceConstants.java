/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal;

import org.eclipse.datatools.sqltools.sqleditor.SQLEditorPlugin;

/**
 * Preference constants used in the SQL Editor preference store. 
 * 
 * @author Hui Cao
 * 
 */
public class PreferenceConstants
{

    // begin: preference page ids
    public static final String PAGE_GENERAL                            = SQLEditorPlugin.PLUGIN_ID + ".GeneralPreferencePage";
    public static final String PAGE_LOGGING                            = SQLEditorPlugin.PLUGIN_ID + ".LoggingPreferencePage";
    public static final String PAGE_TEMPLATE                           = SQLEditorPlugin.PLUGIN_ID + ".TemplatesPreferencePage";
    public static final String PAGE_PERSPECTIVE                        = SQLEditorPlugin.PLUGIN_ID + ".PerspectivePage";
    public static final String PAGE_SQLFILE                            = SQLEditorPlugin.PLUGIN_ID + ".sqlfile";
    public static final String PAGE_CODEASSIST                         = SQLEditorPlugin.PLUGIN_ID + ".codeassist";
    public static final String PAGE_EXPORT                             = SQLEditorPlugin.PLUGIN_ID + ".ExportFormatPreferencePage";
    public static final String PAGE_SQLEDITOR                          = SQLEditorPlugin.PLUGIN_ID + ".SQLEditor";
    public static final String PAGE_SQLDEBUG                           = SQLEditorPlugin.PLUGIN_ID + ".SQLDebug";
    public static final String PAGE_RESULT                             = SQLEditorPlugin.PLUGIN_ID + ".sqlresultsview";
    public static final String PAGE_CONNECTIONOPTIONS                  = SQLEditorPlugin.PLUGIN_ID + ".connectionleveloptions";
    public static final String PAGE_MISC                               = SQLEditorPlugin.PLUGIN_ID + ".miscpage";
    public static final String PAGE_PLAN                               = SQLEditorPlugin.PLUGIN_ID + ".queryplan";
    // end: preference page ids


    /**
     * A named preference that defines whether hint to make hover sticky should be shown.
     */
    public static final String EDITOR_SHOW_TEXT_HOVER_AFFORDANCE = "PreferenceConstants.EDITOR_SHOW_TEXT_HOVER_AFFORDANCE"; //$NON-NLS-1$

    /**
     * A named preference that defines what the target database type is in portability check.
     */
    public static final String EDITOR_PORTABILITY_CHECK_TARGET   = "PreferenceConstants.EDITOR_PORTABILITY_CHECK_TARGET";

}
