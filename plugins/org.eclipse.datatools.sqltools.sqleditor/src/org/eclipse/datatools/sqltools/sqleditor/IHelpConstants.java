/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor;

import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;

/**
 * 
 * This file contains all the context help ID that are used by following DTP plugins:
 * 
 * TPS_helpKey_constants_for_plug-in: org.eclipse.datatools.sqltools.sqleditor
 * 
 * @author renj
 */
public interface IHelpConstants
{
    /**
     * Prefix for all context help ID.
     */
    public static final String PREFIX                                = SQLEditorPlugin.PLUGIN_ID + ".";                 

    /**
     * Used for creating a new SQL scrapbook.
     */
    public static final String NEW_SQL_FILE                          = PREFIX + "new_sql_file";

    /**
     * Used for SQL Editor.
     */
    public static final String SQLEDITOR                             = PREFIX + "sqleditor";

    /**
     * Used for SQL scrapbook's Select Profile for the Editor dialog.
     */
    public static final String ATTACHING_PROFILE                     = PREFIX + "attaching_profile";

    /**
     * Used for launch configuration of Eclipse Data Tools
     */
    public static final String LAUNCH_CONFIGURATION_MAIN             = PREFIX + "launch_configuration_main";

    /**
     * Used for Eclipse Data Tools' configure parameters dialog.
     */
    public static final String SP_LAUNCH_CONFIGURATION_PARAMETERS    = PREFIX + "sp_launch_configuration_parameters";

    /**
     * Used for Eclipse Data Tools' configure parameters dialog of event.
     */
    public static final String EVENT_LAUNCH_CONFIGURATION_PARAMETERS = PREFIX + "event_launch_configuration_parameters";

    /**
     * Used for Database Development preference page.
     */
    public static final String PREFERENCES_DATABASE_DEVELOPMENT      = PREFIX + "preferences_database_development";

    /**
     * Used for SQL Editor preference page.
     */
    public static final String PREFERENCES_SQL_EDITOR                = PREFIX + "preferences_sql_editor";

    /**
     * Used for Code Assist preference page.
     */
    public static final String PREFERENCES_CODE_ASSIST               = PREFIX + "preferences_code_assist";

    /**
     * Used for SQL File/Scrapbooks preference page.
     */
    public static final String PREFERENCES_SQL_FILES                 = PREFIX + "preferences_sql_files";

    /**
     * Used for Templates preference page.
     */
    public static final String PREFERENCES_TEMPLATES                 = PREFIX + "preferences_templates";

    /**
     * Used for Toggle Comment action in SQL Editor.
     */
    public static final String TOGGLE_COMMENT_ACTION                 = PREFIX + "toggle_comment_action";

    /**
     * Used for Execute All in SQL Editor.
     */
    public static final String EXECUTE_ALL_ACTION                    = PREFIX + "execute_all_action";

    /**
     * Used for Execute Selected Text in SQL Editor.
     */
    public static final String EXECUTE_SELECTED_TEXT_ACTION          = PREFIX + "execute_selected_text_action";

    /**
     * Used for Get Execution Plan in SQL Editor.
     */
    public static final String GET_EXECUTION_PLAN_ACTION             = PREFIX + "get_execution_plan_action";

    /**
     * Used for Run action in SQL Editor.
     */
    public static final String RUN_ACTION                            = PREFIX + "run_action";

    /**
     * Used for Debug action in SQL Editor.
     */
    public static final String DEBUG_ACTION                          = PREFIX + "debug_action";

    /**
     * Used for Save to Server in SQL Editor.
     */
    public static final String SAVE_TO_DATABASE_ACTION               = PREFIX + "save_to_database_action";

    /**
     * Used for Refresh From Server in SQL Editor.
     */
    public static final String REFRESH_FROM_DATABASE_ACTION          = PREFIX + "refresh_from_database_action";

    /**
     * Used for Set Connection Info action in SQL Editor.
     */
    public static final String ATTACH_CONNECTION_PROFILE_ACTION      = PREFIX + "attach_connection_profile_action";

    /**
     * Used for Save as Template action in SQL Editor.
     */
    public static final String SAVE_AS_TEMPLATE_ACTION               = PREFIX + "save_as_template_action";

    /**
     * Used for New Template dialog.
     */
    public static final String EDIT_TEMPLATE_DIALOG                  = PREFIX + "edit_template_dialog";
}