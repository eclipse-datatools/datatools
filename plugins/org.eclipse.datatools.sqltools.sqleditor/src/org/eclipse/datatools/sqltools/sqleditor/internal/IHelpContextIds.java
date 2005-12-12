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
 * Help context ids for the SQL Editor plugin.
 * <p>
 * This interface contains constants only; it is not intended to be implemented or extended.
 * </p>
 * 
 * 
 * @author Li Huang
 *  
 */
public interface IHelpContextIds
{
    public static final String PREFIX                                = SQLEditorPlugin.PLUGIN_ID + ".";                  //$NON-NLS-1$

    public static final String PLAN_VIEW                             = PREFIX + "plan_view";                            //$NON-NLS-1$

    public static final String RESULT_VIEW                           = PREFIX + "result_view";

    public static final String SAVE_RESULT_DIALOG                    = PREFIX + "save_result_dialog";

    public static final String SAVE_ALL_RESULTS_DIALOG               = PREFIX + "save_all_results_dialog";

    public static final String NEW_SQL_FILE                          = PREFIX + "new_sql_file";

    public static final String SQLEDITOR                             = PREFIX + "sqleditor";

    public static final String SQL_WIZARD_STORED_PROCEDURE           = PREFIX + "sql_wizard_stored_procedure";

    public static final String SQL_WIZARD_FUNCTION                   = PREFIX + "sql_wizard_function";

    public static final String SQL_WIZARD_TRIGGER                    = PREFIX + "sql_wizard_trigger";

    public static final String SQL_WIZARD_EVENT                      = PREFIX + "sql_wizard_event";

    public static final String MESSAGE_SELECTION_MESSAGE_WIZARD      = PREFIX + "message_selection_message_wizard";     //$NON-NLS-1$

    public static final String MESSAGE_WIZARD_MSGSEND                = PREFIX + "message_wizard_msgsend";

    public static final String MESSAGE_WIZARD_MSGRECV                = PREFIX + "message_wizard_msgrecv";

    public static final String MESSAGE_WIZARD_REGISTER_SUB           = PREFIX + "message_wizard_register_sub";

    public static final String MESSAGE_WIZARD_MSGPUBLISH             = PREFIX + "message_wizard_msgpublish";

    public static final String MESSAGE_WIZARD_MSGSUBSCRIBE           = PREFIX + "message_wizard_msgsubscribe";

    public static final String MESSAGE_WIZARD_MSGCONSUME             = PREFIX + "message_wizard_msgconsume";

    public static final String MESSAGE_WIZARD_MSGUNSUBSCRIBE         = PREFIX + "message_wizard_msgunsubscribe";

    public static final String MESSAGE_WIZARD_MSGPROPLIST            = PREFIX + "message_wizard_msgproplist";

    public static final String MESSAGE_WIZARD_MSGPROPCOUNT           = PREFIX + "message_wizard_msgpropcount";

    public static final String MESSAGE_WIZARD_MSGPROPNAME            = PREFIX + "message_wizard_msgpropname";

    public static final String MESSAGE_WIZARD_MSGPROPVALUE           = PREFIX + "message_wizard_msgpropvalue";

    public static final String MESSAGE_WIZARD_MSGPROPTYPE            = PREFIX + "message_wizard_msgproptype";

    public static final String ATTACHING_PROFILE                     = PREFIX + "attaching_profile";

    public static final String LAUNCH_CONFIGURATION_MAIN             = PREFIX + "launch_configuration_main";

    public static final String SP_LAUNCH_CONFIGURATION_PARAMETERS    = PREFIX + "sp_launch_configuration_parameters";

    public static final String EVENT_LAUNCH_CONFIGURATION_PARAMETERS = PREFIX + "event_launch_configuration_parameters";

    public static final String DEBUG_ASA_TRIGGER_ROWS                = PREFIX + "debug_asa_trigger_rows";

    public static final String DEBUG_EXTERNAL_CLIENTS                = PREFIX + "debug_external_clients";

    public static final String DEBUG_GLOBAL_VARIABLES                = PREFIX + "debug_global_variables";

    public static final String DEBUG_BREAKPOINT_PROPERTIES           = PREFIX + "debug_breakpoint_properties";

    public static final String ENABLE_ASE_FEATURES                   = PREFIX + "enable_ase_features";

    public static final String PREFERENCES_DATABASE_DEVELOPMENT      = PREFIX + "preferences_database_development";

    public static final String PREFERENCES_PERSPECTIVE               = PREFIX + "preferences_perspective";

    public static final String PREFERENCES_SQL_EDITOR                = PREFIX + "preferences_sql_editor";

    public static final String PREFERENCE_SQL_DEBUGGER                     = PREFIX + "sql_debugger_reference_page";

    public static final String PREFERENCES_CODE_ASSIST               = PREFIX + "preferences_code_assist";

    public static final String PREFERENCES_SQL_FILES                 = PREFIX + "preferences_sql_files";

    public static final String PREFERENCES_TEMPLATES                 = PREFIX + "preferences_templates";

    public static final String PREFERENCES_XML_EXPORT_FORMAT         = PREFIX + "preferences_xml_export_format";

    //actions
    public static final String TOGGLE_COMMENT_ACTION                 = PREFIX + "toggle_comment_action";

    public static final String EXECUTE_ALL_ACTION                    = PREFIX + "execute_all_action";

    public static final String EXECUTE_SELECTED_TEXT_ACTION          = PREFIX + "execute_selected_text_action";

    public static final String GET_EXECUTION_PLAN_ACTION             = PREFIX + "get_execution_plan_action";

    public static final String RUN_ACTION                            = PREFIX + "run_action";

    public static final String DEBUG_ACTION                          = PREFIX + "debug_action";

    public static final String INSERT_EVENT_ACTION                   = PREFIX + "insert_event_action";

    public static final String INSERT_FUNCTION_ACTION                = PREFIX + "insert_function_action";

    public static final String INSERT_PROCEDURE_ACTION               = PREFIX + "insert_procedure_action";

    public static final String INSERT_TRIGGER_ACTION                 = PREFIX + "insert_trigger_action";

    public static final String VISUAL_SQL_SELECT_ACTION              = PREFIX + "visual_sql_select_action";

    public static final String VISUAL_SQL_UPDATE_ACTION              = PREFIX + "visual_sql_update_action";

    public static final String VISUAL_SQL_INSERT_ACTION              = PREFIX + "visual_sql_insert_action";

    public static final String VISUAL_SQL_DELETE_ACTION              = PREFIX + "visual_sql_delete_action";

    public static final String REAL_TIME_MESSAGING_WIZARD_ACTION     = PREFIX + "real_time_messaging_wizard_action";

    public static final String SAVE_TO_DATABASE_ACTION               = PREFIX + "save_to_database_action";

    public static final String REFRESH_FROM_DATABASE_ACTION          = PREFIX + "refresh_from_database_action";

    public static final String ATTACH_CONNECTION_PROFILE_ACTION      = PREFIX + "attach_connection_profile_action";

    public static final String SAVE_AS_TEMPLATE_ACTION               = PREFIX + "save_as_template_action";

    public static final String ENABLE_ASE_FEATURES_ACTION            = PREFIX + "enable_ase_features_action";

    public static final String SQL_RESULTS_REMOVE_ACTION             = PREFIX + "sql_results_remove_action";

    public static final String REMOVE_ALL_FINISHED_RESULTS_ACTION    = PREFIX + "remove_all_finished_results_action";

    public static final String SQL_RESULTS_SAVE_LOG_ACTION           = PREFIX + "sql_results_save_log_action";

    public static final String SQL_RESULTS_TERMINATE_ACTION          = PREFIX + "sql_results_terminate_action";

    public static final String SQL_RESULTS_SAVE_RESULT_ACTION        = PREFIX + "sql_results_save_result_action";

    public static final String SQL_RESULTS_EXPORT_ALL_RESULTS_ACTION = PREFIX + "sql_results_export_all_results_action";

    public static final String REMOVE_CURRENT_PLAN_ACTION            = PREFIX + "remove_current_plan_action";

    public static final String REMOVE_ALL_PLANS_ACTION               = PREFIX + "remove_all_plans_action";

    public static final String SAVE_PLANS_TO_A_FILE_ACTION           = PREFIX + "save_plans_to_a_file_action";

    public static final String LOAD_PLANS_FROM_A_FILE_ACTION         = PREFIX + "load_plans_from_a_file_action";

    public static final String SHOW_PREVIOUS_PLANS_ACTION            = PREFIX + "show_previous_plans_action";

    public static final String ATTACH_CLIENT_CONNECTION_ACTION       = PREFIX + "attach_client_connection_action";

    public static final String DETACH_CLIENT_CONNECTION_ACTION       = PREFIX + "detach_client_connection_action";

    public static final String REFRESH_CLIENT_CONNECTION_ACTION      = PREFIX + "refresh_client_connection_action";

    public static final String ASE_CONNECTION_LEVEL_OPTION_SECTION   = PREFIX + "ase_connection_level_option_section";

    public static final String ASA_CONNECTION_LEVEL_OPTION_SECTION   = PREFIX + "asa_connection_level_option_section";

    public static final String ASIQ_CONNECTION_LEVEL_OPTION_SECTION  = PREFIX + "asiq_connection_level_option_section";

    public static final String CREATE_TEMP_TABLE_ACTION              = PREFIX + "create_temp_table_action";

    public static final String CREATE_TEMP_TABLE_WIZARD_MAINPAGE     = PREFIX + "create_temp_table_wizard_mainpage";

    public static final String CREATE_TEMP_TABLE_WIZARD_SUMMARYPAGE  = PREFIX + "create_temp_table_wizard_summarypage";

    public static final String PREFERENCES_MISCELlANEOUS_PAGE        = PREFIX + "preference_miscellaneous_page";

    public static final String SQL_RESULT_VIEW_PREFERENCE            = PREFIX + "sql_result_view_preference";

    public static final String SQL_RESULTS_FILTER_DIALOG             = PREFIX + "sql_results_filter_dialog";

    public static final String SQL_RESULT_TEXT_SAVE_DIALOG           = PREFIX + "sql_result_text_save_dialog";

    public static final String SQL_RESULTS_SAVE_ACTION        		 = PREFIX + "sql_results_save_action";

    public static final String QUERY_PLAN_PREFERENCE        		 = PREFIX + "query_plan_preference";

    public static final String SQL_XML_WIZARD_ACTION        		 = PREFIX + "sql_xml_wizard_action";

    public static final String XML_EXTRACT_PAGE                      = PREFIX + "xml_extract_page";

    public static final String XML_TEST_PAGE                         = PREFIX + "xml_test_page";

    public static final String XML_PARSE_PAGE                        = PREFIX + "xml_parse_page";

    public static final String XML_REPRESENTATION_PAGE               = PREFIX + "xml_representation_page";

    public static final String XML_FORXML_MAPPING_PAGE               = PREFIX + "xml_forxml_mapping_page";

    public static final String XML_RESULT_OPTION_PAGE                = PREFIX + "xml_result_option_page";

    public static final String XML_DTD_OPTION_PAGE                   = PREFIX + "xml_dtd_option_page";

    public static final String XML_OUTPUT_PARAMS_PAGE                = PREFIX + "xml_output_params_page";

    public static final String XML_FORSQLJ_PAGE                      = PREFIX + "xml_forsqlj_page";

    public static final String XML_FORSQLJ_SCHEMA_PAGE               = PREFIX + "xml_forsql_schema_page";

    public static final String XML_FORSQLJ_RESULT_PAGE               = PREFIX + "xml_forsql_result_page";

    public static final String TABLES_VIEW                           = PREFIX + "sql_debugger_tables_view";

}
