/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
    public static String        DefaultSchemaObjectEditorHandler_existing_errors;
    public static String        DefaultSchemaObjectEditorHandler_modifying;
    public static String        DefaultSchemaObjectEditorHandler_page;
    public static String        DefaultSchemaObjectEditorHandler_refreshing;
    public static String        DefaultSchemaObjectEditorHandler_refreshing_schema_editor;
    public static String        DefaultSchemaObjectEditorHandler_subtask_name;
    public static String        DefaultSchemaObjectEditorHandler_validation_fail;
    public static String        DefaultSchemaObjectEditorHandler_validation_problem;
    public static String        ErrorPage_error;
    public static String        ErrorPage_error_msg;
    public static String        SavePreviewDialog_cancel;
    public static String        SavePreviewDialog_copy;
    public static String        SavePreviewDialog_execute;
    public static String        SavePreviewDialog_execution_failed_msg;
    public static String        SavePreviewDialog_noname_sql;
    public static String        SavePreviewDialog_not_show_again;
    public static String        SavePreviewDialog_preview;
    public static String        SavePreviewDialog_problem;
    public static String        SavePreviewDialog_save_as;
    public static String        SavePreviewDialog_select_all;
    public static String        SchemaObjectEditor_error;
    public static String        SchemaObjectEditor_error_id;
    public static String        SchemaObjectEditor_no_handler;
    public static String        SchemaObjectEditor_no_page;
    public static String        SchemaObjectEditor_preferences;
    public static String        SchemaObjectEditor_save_to_server;
    public static String        SchemaObjectEditorInput_name;
    public static String        SchemaObjectEditorPage_help_name;
    public static String        SchemaObjectEditorPage_help_tip;
    public static String        SchemaObjectEditor_pagechange_error;
    public static String        SQLExecutionJobListener_refreshing;
    public static String        MainSQLObjectLostPromoptSavingTitle;
    public static String        MainSQLObjectLostPromoptSavingMessage;

    public static String        AbstractSchemaObjectEditModel_fatal_error;
    public static String        AbstractSchemaObjectEditModel_main_object_lost;
}
