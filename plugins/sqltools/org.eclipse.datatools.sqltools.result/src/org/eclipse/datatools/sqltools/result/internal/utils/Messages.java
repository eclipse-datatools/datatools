/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.utils;

import java.lang.reflect.Field;

import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;
import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = ResultsViewPlugin.BUNDLE_NAME;

	private Messages() {
		// Do not instantiate
	}

//	public static String MultipleTabsModeSection_warning;
//    public static String MultipleTabsModeSection_warning_info;
//	public static String ResultFormatWizardPage_all_files;
//    public static String ResultHistoryPage_no_column_selected;
//    public static String ResultHistorySection_status;
//	public static String ResultHistoryPage_operation;
//	public static String ResultHistoryPage_frequency;
//	public static String ResultHistorySection_query_expression;
//	public static String ResultHistorySection_operation;
//	public static String ResultHistorySection_frequency;
//	public static String ResultHistorySection_action_type;
//	public static String ResultHistorySection_consumer_name;
//	public static String ResultHistorySection_connection_profile;
//	public static String ResultHistoryPage_action_type;
//	public static String ResultHistoryPage_auto_persist;
//	public static String ResultHistoryPage_consumer_name;
//	public static String ResultHistoryPage_tooltip_status;
//	public static String ResultHistoryPage_tooltip_date;
//	public static String ResultHistoryPage_tooltip_consume;
//	public static String ResultHistoryPage_tooltip_profile;
//	public static String ResultHistorySection_date;
//	public static String ResultHistoryPage_status;
//	public static String ResultHistoryPage_date;
//	public static String ResultSection_status;
//	public static String ResultSection_message;
//	public static String ResultSection_result;
//	public static String ResultSection_warnning_rowshidden;
//	public static String ResultSection_resultset_tooltip;
//	public static String ResultSetLabelProvider_longdata;
//	public static String ResultSection_resultset_tooltip1;
//	public static String MultipleTabsGridSection_parameter_name;
//	public static String MultipleTabsGridSection_parameter_type;
//	public static String MultipleTabsTextSection_parameter_name;
//	public static String MultipleTabsTextSection_parameter_type;
//	public static String MultipleTabsTextSection_parameter_value;
//	public static String MultipleTabsTextSection_parameter_value_out;
//	public static String MultipleTabsGridSection_parameter_datatype;
//	public static String MultipleTabsTextSection_parameter_datatype;
//	public static String MultipleTabsGridSection_value;
//	public static String MultipleTabsGridSection_value_out;
//	public static String MultipleTabsGridSection_parameter;
//	public static String MultipleTabsTextSection_parameter;
//	public static String MultipleTabsModeSection_message;
//	public static String MultipleTabsModeSection_tabs_hidden;
//    public static String ResultSetViewer_copy_rows;
//	public static String ResultsView_preference;
//	public static String ResultsView_tooltip;
//	public static String ResultsView_layout_vertical;
//	public static String ResultsView_layout_horizontal;
//	public static String ResultView_filter;
//	public static String ResultView_filtertip;
//	public static String ReExecuteAction_name;
//	public static String ResultView_filter_info;
	public static String OperationCommand_status_critical;
	public static String OperationCommand_status_warning;
	public static String OperationCommand_status_started;
	public static String OperationCommand_status_running;
	public static String OperationCommand_status_succeeded;
	public static String OperationCommand_status_failed;
	public static String OperationCommand_status_terminated;
	public static String OperationCommand_status_unknown;
	public static String OperationCommand_unknown_action;
	public static String OperationCommand_action_execute;
	public static String OperationCommand_action_create;
	public static String OperationCommand_action_debug;
	public static String OperationCommand_action_deploy;
	public static String OperationCommand_action_drop;
	public static String OperationCommand_action_edit;
	public static String OperationCommand_action_export;
	public static String OperationCommand_action_extract;
	public static String OperationCommand_action_import;
	public static String OperationCommand_action_load;
	public static String OperationCommand_action_before_run;
	public static String OperationCommand_action_after_run;
	public static String OperationCommand_action_run;
	public static String OperationCommand_action_validate;
	public static String OperationCommand_action_browse;
//	public static String RedoAction_title;
//    public static String SingleTabDisplayAction_single_tab;
//    public static String SingleWindowGridSection_warning_info;
//    public static String SQLResultsViewPage_limit_tables_number;
//    public static String SQLResultsViewPage_limit_tables_tooltip;
//    public static String SQLResultsViewPage_too_many_rows;
//    public static String SQLResultsViewPage_too_many_tabs;
//    public static String SQLResultsViewPage_too_many_tables;
//    public static String TextModeDisplayAction_textmode_tip;
//	public static String UndoAction_title;
//	public static String TerminateInstanceAction_terminate;
//	public static String TerminateInstanceAction_terminate_tooltip;
//	public static String RemoveAllResultAction_remove_all_results;
//	public static String RemoveAllResultAction_remove_all_results_tooltip;
//	public static String RemoveResultAction_remove;
//	public static String RemoveResultAction_remove_result;
//	public static String CommonTextViewer_action_cut;
//	public static String CommonTextViewer_action_copy;
//	public static String CommonTextViewer_action_paste;
//	public static String CommonTextViewer_action_delete;
//	public static String CommonTextViewer_action_selectall;
	public static String StatusTextProvider_operation_success;
	public static String StatusTextProvider_action_type;
	public static String StatusTextProvider_profile_name;
	public static String StatusTextProvider_database;
	public static String StatusTextProvider_time;
	public static String StatusTextProvider_update_count_complex;
	public static String StatusTextProvider_update_count_single;
//	public static String IResultConstants_plain_format;
//	public static String IResultConstants_csv_format;
//	public static String IResultConstants_html_format;
//	public static String IResultConstants_xml_format;
//	public static String IResultConstants_text_ext;
//	public static String IResultConstants_csv_ext;
//	public static String IResultConstants_html_ext;
//	public static String IResultConstants_xml_ext;
//	public static String IResultConstants_all_ext;
//	public static String IResultConstants_text_name;
//	public static String IResultConstants_csv_name;
//	public static String IResultConstants_html_name;
//	public static String IResultConstants_xml_name;
//	public static String IResultConstants_all_name;
//	public static String SQLResultsViewPage_displaywindow;
//	public static String SQLResultsViewPage_displaywindow_singlewindow;
//	public static String SQLResultsViewPage_maxdisplayrows_tooltip;
//	public static String SQLResultsViewPage_displaywindow_multiplewindows;
//	public static String SQLResultsViewPage_displaymode;
//	public static String SQLResultsViewPage_other_option;
//	public static String SQLResultsViewPage_split_message;
//	public static String SQLResultsViewPage_splitmessages_tooltip;
//	public static String SQLResultsViewPage_limit_tabs;
//	public static String SQLResultsViewPage_limit_tabs_tooltip;
//	public static String SQLResultsViewPage_displaymode_text;
//	public static String SQLResultsViewPage_displaymode_grid;
//	public static String SQLResultsViewPage_resultsetoptions;
//	public static String SQLResultsViewPage_singlewindow_tooltip;
//	public static String SQLResultsViewPage_multipletabs_tooltip;
//	public static String SQLResultsViewPage_textmode_tooltip;
//	public static String SQLResultsViewPage_girdmode_tooltip;
//	public static String SQLResultsViewPage_resultsetoptions_showheadings;
//	public static String SQLResultsViewPage_resultsetoptions_showrownumber;
//	public static String SQLResultsViewPage_resultsetoptions_showrowcount;
//	public static String SQLResultsViewPage_resultsetoptions_maxrowcount;
//	public static String SQLResultsViewPage_resultsetoptions_maxdisplayrowcount;
//	public static String SQLResultsViewPage_resultsetoptions_nulldisplaystr;
//	public static String SQLResultsViewPage_resultsetoptions_lessthanzero;
//	public static String SQLResultsViewPage_resultsetoptions_displaybiggerthanmax;
//	public static String SQLResultsViewPage_resultsetoptions_invalidnumberformat;
//	public static String SQLResultsViewPage_resultsetoptions_maxrowcount_tooltip;
//	public static String ExportFormatPage_title;
//	public static String ExportOptions_title;
//	public static String ExportFormatPage_xmlexportformat_group;
//	public static String ExportFormatPage_xmlexportformat_addxmlheader;
//	public static String ExportFormatPage_xmlexportformat_xmlheader;
//	public static String ExportFormatPage_xmlexportformat_addxmlroottag;
//	public static String ExportFormatPage_xmlexportformat_xmlroottag;
//	public static String ExportFormatPage_xmlexportformat_xmlheader_text;
//	public static String ExportFormatPage_xmlexportformat_xmlroottag_text;
//	public static String ExportFormatPage_columndelimiter_group;
//	public static String ExportFormatPage_columndelimiter_outputformat;
//	public static String ExportFormatPage_columndelimiter_delimiter;
//	public static String ExportFormatPage_columndelimiter_items_columnaligned;
//	public static String ExportFormatPage_columndelimiter_items_commaseparated;
//	public static String ExportFormatPage_columndelimiter_items_tabdelimited;
//	public static String ExportFormatPage_columndelimiter_items_userdefined;
//	public static String ExportFormatPage_columndelimiter_items_csvseparated;
//	public static String ExportFormatPage_columndelimiter_userdefineddelimiter;
//	public static String ExportFormatPage_fileencoding_group;
//	public static String ExportFormatPage_fileencoding_outputencoding;
//	public static String ExportFormatPage_fileencoding_defaultencoding;
//	public static String ExportFormatPage_fileencoding_otherencoding;
//	public static String ExportFormatPage_fileencoding_numDefaultEncodings;
//	public static String ExportFormatPage_fileencoding_defaultEncoding1;
//	public static String ExportFormatPage_fileencoding_defaultEncoding2;
//	public static String ExportFormatPage_fileencoding_defaultEncoding3;
//	public static String ExportFormatPage_fileencoding_defaultEncoding4;
//	public static String ExportFormatPage_fileencoding_defaultEncoding5;
//	public static String ExportFormatPage_fileencoding_defaultEncoding6;
//	public static String ExportFormatPage_fileencoding_unsupportedEncoding;
//	public static String ResultFormatWizardPage_title;
//	public static String ResultFormatWizardPage_description;
//	public static String ResultFormatWizardPage_label_filename;
//	public static String ResultFormatWizardPage_button_browse;
//	public static String ResultFormatWizardPage_dialog_text;
//	public static String ResultFormatWizardPage_errormessage_choosefile;
//	public static String ResultExportWizard_please_select_file_format;
//	public static String ResultExportWizard_failed_to_export_result_set;
//	public static String ResultFormatWizardPage_label_format;
//	public static String Save_name;
//	public static String Export_name;
//	public static String Print_name;
//	public static String ResultSetAction_Title;
//	public static String AllResultSetAction_Title;
//	public static String SaveAllResultSetAction_Title;
//	public static String ExportAllResultSetAction_Title;
//	public static String PrintAllResultSetAction_Title;
//	public static String SaveResultSetDialog_saveResult_text;
//	public static String SaveResultSetDialog_saveResult_title;
//	public static String SaveResultSetDialog_saveAllResults_text;
//	public static String SaveResultSetDialog_saveAllResults_title;
//	public static String ResultExportWizard_exportResult_title;
//	public static String ResultExportWizard_overwrite;
//	public static String ResultExportWizard_question;
//	public static String ResultExportWizard_exportAllResults_title;
//	public static String SaveAllResultSetAction_Dialog_Title;
//	public static String SaveAllResultSetAction_export_all_results;
//	public static String SaveResultSetDialog_filetype_label;
//	public static String SaveResultSetDialog_error;
//	public static String SaveResultSetDialog_message;
//	public static String SaveResultSetDialog_overwrite;
//	public static String SaveResultSetDialog_question;
//	public static String SaveResultSetDialog_filename;
//	public static String SaveResultSetDialog_export_error;
//	public static String SaveResultInstanceAction_save_log;
//	public static String SaveResultInstanceAction_save_history;
//	public static String SaveResultInstanceAction_save_hisotry_title;
//	public static String SaveResultInstanceAction_save_error;
//	public static String SaveResultSetDialog_export_error_msg;
//	public static String SaveResultSetDialog_please_select_file_format;
//	public static String SaveResultInstanceAction_export_error;
//	public static String SaveResultInstanceAction_can_not_save;
//	public static String ResultsFilterDialog_finishstatus;
//	public static String ResultsFilterDialog_succeeded;
//	public static String ResultsFilterDialog_succeeded_tip;
//	public static String ResultsFilterDialog_criticalerror;
//	public static String ResultsFilterDialog_failed;
//	public static String ResultsFilterDialog_warning;
//	public static String ResultsFilterDialog_failed_tip;
//	public static String ResultsFilterDialog_terminated;
//	public static String ResultsFilterDialog_terminated_tip;
//	public static String ResultsFilterDialog_limit;
//	public static String ResultsFilterDialog_limit_tip;
//	public static String ResultsFilterDialog_profilename;
//	public static String ResultsFilterDialog_profileprovider;
//	public static String ResultsFilterDialog_warning_tooltip;
//	public static String ResultsFilterDialog_profiledesc;
//	public static String ResultsFilterDialog_profiles_tip;
//	public static String ResultsFilterDialog_dialogtitle;
//	public static String ResultsFilterDialog_unknownprofile;
//	public static String ResultsFilterDialog_unknownprofile_tooltip;
//	public static String ResultsFilterDialog_criticalerror_tooltip;
	public static String Parameter_constructor_error;
//	public static String ResultSetObject_constructor_error;
//	public static String Debugger_Images_malformedURLException;
//	public static String ResultsViewAPI_append_resultset_error;
//	public static String ResultsViewAPI_checkview_error;
//	public static String ResultsViewAPI_notwellformed_xml;
//	public static String LongDataDialog_title;
//	public static String LongDataDialog_columnName;
//	public static String LongDataDialog_datatype;
//	public static String ResourceAndContainerGroup_folder_empty;
//	public static String ResourceAndContainerGroup_project_noexist;
//	public static String ResourceAndContainerGroup_file_exists;
//	public static String ResourceAndContainerGroup_name_exists;
//	public static String ResourceAndContainerGroup_name_empty;
//	public static String ResourceAndContainerGroup_invalid_name;
//	public static String ContainerSelectionGroup_message;
//	public static String ContainerSelectionGroup_folder_select;
//	public static String ResultHistoryLuceneIndex_io_error;
//	public static String ResultHistoryLuceneIndex_parser_error;
//	public static String ResultHistoryPage_columns_group_name;
//	public static String ResultHistoryPage_connection_profile;
//	public static String ResultHistoryPage_tooltip_operation;
//	public static String ResultHistoryPage_tooltip_frequency;
//	public static String ResultHistoryPage_tooltip_actiontype;
//	public static String ResultHistoryPage_tooltip_auto_persist;
	public static String StatusLogger_no_bundle;
	public static String StatusLogger_possible_args;
	public static String StatusLogger_no_bundle_1;
	public static String StatusLogger_possible_args_1;
//	public static String ResultHistorySection_query_expression_tooltip;
//	public static String ResultsViewPlugin_load_history_error;
//	public static String ResultsViewPlugin_persist_history_error;
//	public static String SingleWindowTextSection_inout_params;
//	public static String SingleWindowGridSection_inout_params;
//	public static String ResultExportWizard_export_error;
//	public static String ResultViewLabelProvider_unknown;
//	public static String ResultSetViewerPage_title;
//	public static String ResultSetViewerPage_group;
//	public static String ResultSetViewerPage_select_viewer;
//	public static String ResultSetViewerPage_viewer_tooltip;
//	public static String ResultSetViewerPage_defaultViewer;
    public static String ResultSection_Status_ElapsedTime;
    
	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	/**
	 * Gets a resource string by field name. This is useful when the field name is constructed ad hoc.
	 * @param fieldName
	 * @return
	 */
	public static String getString(String fieldName)
    {
        Class c = Messages.class;
        Field[] fields = c.getDeclaredFields();
        for(int i=0;i<fields.length;i++)
        {
            if(fields[i].getName().equals(fieldName))
            {
                try
                {
                    return (String)fields[i].get(null);
                }
                catch(Exception ex)
                {
                    return "!" + fieldName + "!";
                }
            }
        }
        return "!" + fieldName + "!";
    }	

}