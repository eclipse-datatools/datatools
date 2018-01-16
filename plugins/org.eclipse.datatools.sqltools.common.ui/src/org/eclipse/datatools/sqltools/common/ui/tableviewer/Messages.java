/**
 * Created on 2005-11-23
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import org.eclipse.osgi.util.NLS;


public final class Messages extends NLS 
{

    private static final String BUNDLE_NAME = Messages.class.getPackage().getName()+".messages";//$NON-NLS-1$

    private Messages() 
    {
        // Do not instantiate
    }

    public static String AccessibleTableViewer_confirm_delete;
    public static String AccessibleTableViewer_sure_to_delete;
    public static String AccessibleTableViewer_fail_to_save;
    public static String AccessibleTableViewer_save;
    public static String AccessibleTableViewer_save_changes;
    public static String AccessibleTableViewer_fail_to_refresh;
    public static String TableDataCellModifier_error;
    public static String TableDataLabelProvider_new_row;
    public static String TableDataLabelProvider_null;
    public static String TableDataCellModifier_data_type_conform;
    public static String TableDataCellModifier_error_in_update;
    public static String AddRowAction_text;
    public static String CopyRowAction_text;
    public static String CutRowAction_text;
    public static String PasteRowAction_text;
    public static String ReomveAllRowAction_text;
    public static String ReomveRowAction_text;
    public static String PasteRowAction_can_not_paste_info;
    public static String PasteRowAction_can_not_paste_title;
    public static String CopyRowAction_can_not_copy_info;
    public static String CopyRowAction_can_not_copy_title;

    static 
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}
