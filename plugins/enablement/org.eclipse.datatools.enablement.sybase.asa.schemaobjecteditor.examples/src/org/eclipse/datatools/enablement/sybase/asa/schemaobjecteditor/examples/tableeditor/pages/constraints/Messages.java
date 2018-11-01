/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.constraints;

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

    public static String ASAConstraintsEditorPage_constraints;
    public static String ClusteredIndexSelectionDialog_all_indexes;
    public static String ClusteredIndexSelectionDialog_all_indexes_tip;
    public static String ClusteredIndexSelectionDialog_clustered;
    public static String ClusteredIndexSelectionDialog_columns;
    public static String ClusteredIndexSelectionDialog_name;
    public static String ClusteredIndexSelectionDialog_set_clustered_index;
    public static String ClusteredIndexSelectionDialog_set_clustered_index_info;
    public static String ClusteredIndexSelectionDialog_set_clustered_index_title;
    public static String ClusteredIndexSelectionDialog_type;
    public static String SybaseASAIndexWrapper_fk;
    public static String SybaseASAIndexWrapper_index;
    public static String SybaseASAIndexWrapper_pk;
    public static String SybaseASAIndexWrapper_uniqueconstraint;
    public static String ASASQLConstraintsBlock_add_fk;
    
    
    public static String SQLConstraintsBlock_add_column_ck;
    public static String SQLConstraintsBlock_add_fk;
    public static String SQLConstraintsBlock_add_pk;
    public static String SQLConstraintsBlock_add_table_ck;
    public static String SQLConstraintsBlock_add_tip;
    public static String SQLConstraintsBlock_add_unique;
    public static String SQLConstraintsBlock_column_ck;
    public static String SQLConstraintsBlock_delete;
    public static String SQLConstraintsBlock_delete_tip;
    public static String SQLConstraintsBlock_table_ck;
    public static String SQLConstraintsBlock_to_be_implemented;
    public static String SQLConstraintsTreeViewerInput_column_ck;
    public static String SQLConstraintsTreeViewerInput_ck;
    public static String SQLConstraintsTreeViewerInput_constraints;
    public static String SQLConstraintsTreeViewerInput_fk;
    public static String SQLConstraintsTreeViewerInput_pk;
    public static String SQLConstraintsTreeViewerInput_unique_constraint;


    public static String SQLConstraintsBlock_add;
    public static String SQLConstraintsBlock_collapse_all;
    public static String SQLConstraintsBlock_constraints;
    public static String SQLConstraintsBlock_constraints_description;
    public static String SQLConstraintsBlock_h_layout;
    public static String SQLConstraintsBlock_information;
    public static String SQLConstraintsBlock_question;
    public static String SQLConstraintsBlock_remove;
    public static String SQLConstraintsBlock_remove_confirmation;
    public static String SQLConstraintsBlock_v_layout;
}
