/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
     * Used for creating a new SQL scrapbook.
     */
    public static final String NEW_SQL_FILE                          = "new_sql_file";

    /**
     * Used for SQL Editor.
     */
    public static final String SQLEDITOR                             = "org.eclipse.datatools.sqltools.sqleditor.sql_file_editor";//to workaround 212003

    /**
     * Used for SQL scrapbook's Select Profile for the Editor dialog.
     */
    public static final String ATTACHING_PROFILE                     = "attaching_profile";

    /**
     * Used for launch configuration of Eclipse Data Tools
     */
    public static final String LAUNCH_CONFIGURATION_MAIN             = "launch_configuration_main";

    /**
     * Used for Eclipse Data Tools' configure parameters dialog.
     */
    public static final String SP_LAUNCH_CONFIGURATION_PARAMETERS    = "sp_launch_configuration_parameters";

    /**
     * Used for Eclipse Data Tools' configure parameters dialog of event.
     */
    public static final String EVENT_LAUNCH_CONFIGURATION_PARAMETERS = "event_launch_configuration_parameters";

    /**
     * Used for Database Development preference page.
     */
    public static final String PREFERENCES_DATABASE_DEVELOPMENT      = "preferences_database_development";

    /**
     * Used for SQL Editor preference page.
     */
    public static final String PREFERENCES_SQL_EDITOR                = "preferences_sql_editor";

    /**
     * Used for Code Assist preference page.
     */
    public static final String PREFERENCES_CODE_ASSIST               = "preferences_code_assist";

    /**
     * Used for SQL File/Scrapbooks preference page.
     */
    public static final String PREFERENCES_SQL_FILES                 = "preferences_sql_files";

    /**
     * Used for Templates preference page.
     */
    public static final String PREFERENCES_TEMPLATES                 = "preferences_templates";

    /**
     * Used for Syntax Coloring preference page.
     */
    public static final String PREFERENCES_SYNTAX_COLORING           = "preferences_syntax_coloring";

    /**
     * Used for Toggle Comment action in SQL Editor.
     */
    public static final String TOGGLE_COMMENT_ACTION                 = "toggle_comment_action";

    /**
     * Used for Execute All in SQL Editor.
     */
    public static final String EXECUTE_ALL_ACTION                    = "execute_all_action";

    /**
     * Used for Execute Selected Text in SQL Editor.
     */
    public static final String EXECUTE_SELECTED_TEXT_ACTION          = "execute_selected_text_action";
    
    /**
     * Used for Execute Selected Text As One Statement in SQL Editor
     */
    public static final String EXECUTE_SQL_AS_ONE_STATEMENT_ACTION   = "execute_selected_as_one_statement_action";
    
    /**
     * Used for Execute Current Text in SQL Editor
     */
    public static final String EXECUTE_CURRENT_SQL_ACTION            = "execute_current_sql_action";

    /**
     * Used for Get Execution Plan in SQL Editor.
     */
    public static final String GET_EXECUTION_PLAN_ACTION             = "get_execution_plan_action";

    /**
     * Used for Run action in SQL Editor.
     */
    public static final String RUN_ACTION                            = "run_action";

    /**
     * Used for Debug action in SQL Editor.
     */
    public static final String DEBUG_ACTION                          = "debug_action";

    /**
     * Used for Save to Server in SQL Editor.
     */
    public static final String SAVE_TO_DATABASE_ACTION               = "save_to_database_action";

    /**
     * Used for Refresh From Server in SQL Editor.
     */
    public static final String REFRESH_FROM_DATABASE_ACTION          = "refresh_from_database_action";

    /**
     * Used for Set Connection Info action in SQL Editor.
     */
    public static final String ATTACH_CONNECTION_PROFILE_ACTION      = "attach_connection_profile_action";

    /**
     * Used for Save as Template action in SQL Editor.
     */
    public static final String SAVE_AS_TEMPLATE_ACTION               = "save_as_template_action";

    /**
     * Used for New Template dialog.
     */
    public static final String EDIT_TEMPLATE_DIALOG                  = "edit_template_dialog";
    
    /**
     * Used for goto matching token action.
     */
    public static final String GOTO_MATCHING_TOKEN_ACTION          = "goto_matching_token_action";
}