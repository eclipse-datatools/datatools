/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.columns;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    
    
    private Messages()
    {
    }
    
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    public static String ASAColumnsEditorPage_add;
    public static String ASAColumnsEditorPage_also_remove_constraints;
    public static String ASAColumnsEditorPage_always_remove;
    public static String ASAColumnsEditorPage_can_not_remove;
    public static String ASAColumnsEditorPage_can_not_remove_desp;
    public static String ASAColumnsEditorPage_can_not_rename;
    public static String ASAColumnsEditorPage_columns;
    public static String ASAColumnsEditorPage_columns_section;
    public static String ASAColumnsEditorPage_columns_tip;
    public static String ASAColumnsEditorPage_comment_of_table;
    public static String ASAColumnsEditorPage_comment_tip;
    public static String ASAColumnsEditorPage_continue;
    public static String ASAColumnsEditorPage_delete;
    public static String ASAColumnsEditorPage_error;
    public static String ASAColumnsEditorPage_owner;
    public static String ASAColumnsEditorPage_referenced_by_fk;
    public static String ASAColumnsEditorPage_remove_constraints;
    public static String ASAColumnsEditorPage_remove_fks;
    public static String ASAColumnsEditorPage_table_name;
    public static String ASAColumnsEditorPage_table_name_tip;
    public static String ASAColumnsEditorPage_warning;
    public static String ASAColumnsEditorPage_view_data;
    public static String ASAColumnsEditorPage_view_data_desp;
    
    public static String ASATableEditorColumnsTableData_column_name;
    public static String ASATableEditorColumnsTableData_comment;
    public static String ASATableEditorColumnsTableData_data_type;
    public static String ASATableEditorColumnsTableData_default;
    public static String ASATableEditorColumnsTableData_nullable;
    public static String ASATableEditorColumnsTableData_pk;
    public static String ASATableEditorColumnsTableData_unique;
    public static String ASATableEditorColumnsViewer_add;
    public static String ASATableEditorColumnsViewer_copy;
    public static String ASATableEditorColumnsViewer_cut;
    public static String ASATableEditorColumnsViewer_delete;
    public static String ASATableEditorColumnsViewer_invalid_type;
    public static String ASATableEditorColumnsViewer_invalid_data_type;
    public static String ASATableEditorColumnsViewer_paste;
    public static String ASATableEditorColumnsViewer_warning;
    public static String PasteRowAction_can_not_paste_info;
    public static String PasteRowAction_can_not_paste_title;
    
    public static String ASATableEditorColumnsViewerCellModifier_remove_constraints;
    public static String ASATableEditorColumnsViewerCellModifier_constraints_remove_also;
    public static String ASATableEditorColumnsViewerCellModifier_continue;
}
