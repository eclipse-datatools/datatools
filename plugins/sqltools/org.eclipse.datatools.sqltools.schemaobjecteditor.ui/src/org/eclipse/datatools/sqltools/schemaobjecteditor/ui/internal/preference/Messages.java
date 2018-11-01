/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.preference;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
    public static String        SchemaObjectEditorPreferencePage_add;
    public static String        SchemaObjectEditorPreferencePage_add_all;
    public static String        SchemaObjectEditorPreferencePage_always_show_preview;
    public static String        SchemaObjectEditorPreferencePage_available_editors;
    public static String        SchemaObjectEditorPreferencePage_available_pages;
    public static String        SchemaObjectEditorPreferencePage_can_not_down;
    public static String        SchemaObjectEditorPreferencePage_can_not_remove;
    public static String        SchemaObjectEditorPreferencePage_can_not_up;
    public static String        SchemaObjectEditorPreferencePage_databaseType;
    public static String        SchemaObjectEditorPreferencePage_editors;
    public static String        SchemaObjectEditorPreferencePage_erroe;
    public static String        SchemaObjectEditorPreferencePage_error;
    public static String        SchemaObjectEditorPreferencePage_latest_version_open_tooltip;
    public static String        SchemaObjectEditorPreferencePage_move_down;
    public static String        SchemaObjectEditorPreferencePage_move_up;
    public static String        SchemaObjectEditorPreferencePage_must_be_first;
    public static String        SchemaObjectEditorPreferencePage_must_be_last;
    public static String        SchemaObjectEditorPreferencePage_no_page;
    public static String        SchemaObjectEditorPreferencePage_pagesSetting;
    public static String        SchemaObjectEditorPreferencePage_question;
    public static String        SchemaObjectEditorPreferencePage_remove;
    public static String        SchemaObjectEditorPreferencePage_remove_all;
    public static String        SchemaObjectEditorPreferencePage_save_modification;
    public static String        SchemaObjectEditorPreferencePage_selected_pages;
    public static String        SchemaObjectEditorPreferencePage_use_latest_version_to_open;
    public static String        SchemaObjectEditorPreferencePage_check_existence_when_activated;
    public static String        SchemaObjectEditorPreferencePage_check_existence_when_activated_tooltip;
    public static String        SchemaObjectEditorPreferencePage_open_file_after_saveas;
    public static String        SchemaObjectEditorPreferencePage_open_file_after_saveas_tooltip;
}
