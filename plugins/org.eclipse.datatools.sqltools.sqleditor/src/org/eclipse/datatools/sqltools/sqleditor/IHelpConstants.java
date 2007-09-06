/**
 * Created on 2007-9-6
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
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
    public static final String PREFIX                                = SQLEditorPlugin.PLUGIN_ID + ".";                  //$NON-NLS-1$

    public static final String NEW_SQL_FILE                          = PREFIX + "new_sql_file";

    public static final String SQLEDITOR                             = PREFIX + "sqleditor";

    public static final String ATTACHING_PROFILE                     = PREFIX + "attaching_profile";

    public static final String LAUNCH_CONFIGURATION_MAIN             = PREFIX + "launch_configuration_main";

    public static final String SP_LAUNCH_CONFIGURATION_PARAMETERS    = PREFIX + "sp_launch_configuration_parameters";

    public static final String EVENT_LAUNCH_CONFIGURATION_PARAMETERS = PREFIX + "event_launch_configuration_parameters";

    public static final String PREFERENCES_DATABASE_DEVELOPMENT      = PREFIX + "preferences_database_development";

    public static final String PREFERENCES_SQL_EDITOR                = PREFIX + "preferences_sql_editor";

    public static final String PREFERENCES_CODE_ASSIST               = PREFIX + "preferences_code_assist";

    public static final String PREFERENCES_SQL_FILES                 = PREFIX + "preferences_sql_files";

    public static final String PREFERENCES_TEMPLATES                 = PREFIX + "preferences_templates";

    //actions
    public static final String TOGGLE_COMMENT_ACTION                 = PREFIX + "toggle_comment_action";

    public static final String EXECUTE_ALL_ACTION                    = PREFIX + "execute_all_action";

    public static final String EXECUTE_SELECTED_TEXT_ACTION          = PREFIX + "execute_selected_text_action";

    public static final String GET_EXECUTION_PLAN_ACTION             = PREFIX + "get_execution_plan_action";

    public static final String RUN_ACTION                            = PREFIX + "run_action";

    public static final String DEBUG_ACTION                          = PREFIX + "debug_action";

    public static final String SAVE_TO_DATABASE_ACTION               = PREFIX + "save_to_database_action";

    public static final String REFRESH_FROM_DATABASE_ACTION          = PREFIX + "refresh_from_database_action";

    public static final String ATTACH_CONNECTION_PROFILE_ACTION      = PREFIX + "attach_connection_profile_action";

    public static final String SAVE_AS_TEMPLATE_ACTION               = PREFIX + "save_as_template_action";

    public static final String EDIT_TEMPLATE_DIALOG                  = PREFIX + "edit_template_dialog";
}