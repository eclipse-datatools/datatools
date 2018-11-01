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
package org.eclipse.datatools.sqltools.debugger.core.internal;

/**
 * Preference constants used in the DMP preference store. Clients should only read the DMP preference store using these
 * values. Clients are not allowed to modify the preference store programmatically.
 * 
 * @author Hui Cao
 * 
 */
public class DebuggerPreferenceConstants
{

    // begin: preference page ids
    public static final String PAGE_SQLDEBUG                           = DebuggerCorePlugin.PLUGIN_ID + ".preferences.SQLDebug";
    // end: preference page ids


    //SQL debug preference constants
    public static final String PROMPT_SETTING_BRAKEPOINT_DISABLE                = "debug.prompt.settingBreakPointDisable"; 

    // Table view
    public static final String TABLE_VIEW_SHOW_ROW_NUMBER              = "PreferenceConstants.tableview.show_row_number";
    public static final String TABLE_VIEW_MAX_ROW_TO_DISPLAY           = "PreferenceConstants.tableview.max_row";
    public static final String TABLE_VIEW_DISPLAY_NULL                 = "PreferenceConstants.tableview.display_null";
    public static final String TABLE_VIEW_AUTO_REFRESH                 = "PreferenceConstants.tableview.auto_refresh";

    public static final String EXTERNAL_CLIENT_REFRESH_INTERVAL = "EXTERNAL_CLIENT_REFRESH_INTERVAL";
}
