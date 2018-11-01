/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui;

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

    public static String ColumnDefaultValueInputDialog_can_not_set_computed_value;
    public static String ColumnDefaultValueInputDialog_computed_value;
    public static String ColumnDefaultValueInputDialog_computed_value_tip;
    public static String ColumnDefaultValueInputDialog_default_tip;
    public static String ColumnDefaultValueInputDialog_default_value;
    public static String ColumnDefaultValueInputDialog_default_value_2;
    public static String ColumnDefaultValueInputDialog_default_value_for_ASA_table;
    public static String ColumnDefaultValueInputDialog_default_value_radio;
    public static String ColumnDefaultValueInputDialog_input_compute;
    public static String ColumnDefaultValueInputDialog_input_default;
    public static String ColumnDefaultValueInputDialog_literal_string;
    public static String ColumnDefaultValueInputDialog_literal_tip;
    public static String ColumnDefaultValueInputDialog_no_default;
    public static String ColumnDefaultValueInputDialog_no_default_tip;
    public static String ColumnDefaultValueInputDialog_not_valid_number;
    public static String ColumnDefaultValueInputDialog_partition_size;
    public static String ColumnDefaultValueInputDialog_partition_size_tip;
    public static String ColumnDefaultValueInputDialog_select_a_system_default;
    public static String ColumnDefaultValueInputDialog_should_be_positive;
    public static String ColumnDefaultValueInputDialog_system_defined;
    public static String ColumnDefaultValueInputDialog_system_defined_default_tip;
    public static String ColumnDefaultValueInputDialog_ud_default_tip;
    public static String ColumnDefaultValueInputDialog_user_defined;
    
    public static String ListSchemaObjectsSection_add;
    public static String ListSchemaObjectsSection_add_title;
    public static String ListSchemaObjectsSection_delete;
    public static String ListSchemaObjectsSection_delete_confirm_1;
    public static String ListSchemaObjectsSection_delete_confirm_2;
    public static String ListSchemaObjectsSection_delete_title;
    public static String ListSchemaObjectsSection_edit;
    public static String ListSchemaObjectsSection_index;
    public static String ListSchemaObjectsSection_save_editor;
    public static String ListSchemaObjectsSection_trigger;
    public static String ListSchemaObjectsSection_can_not_edit;
    public static String ListSchemaObjectsSection_edit_title;
}
