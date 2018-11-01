/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.ui;

import java.lang.reflect.Field;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS
{

    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    private Messages()
    {
        // Do not instantiate
    }

    public static String ResourceAndContainerGroup_folder_empty;
    public static String ResourceAndContainerGroup_project_noexist;
    public static String ResourceAndContainerGroup_file_exists;
    public static String ResourceAndContainerGroup_name_exists;
    public static String ResourceAndContainerGroup_name_empty;
    public static String ResourceAndContainerGroup_invalid_name;
    public static String ContainerSelectionGroup_message;
    public static String ContainerSelectionGroup_folder_select;
    public static String SaveAsDialog_error;
    public static String SaveAsDialog_error_msg;
    public static String SaveAsDialog_message;
    public static String SaveAsDialog_title;
    public static String SaveAsDialog_filetype_label;
    public static String SaveAsDialog_overwrite;
    public static String SaveAsDialog_question;
    public static String SaveAsDialog_filename;
    public static String SaveAsDialog_export_error;

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    /**
     * Gets a resource string by field name. This is useful when the field name is constructed ad hoc.
     * 
     * @param fieldName
     * @return
     */
    public static String getString(String fieldName)
    {
        Class c = Messages.class;
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            if (fields[i].getName().equals(fieldName))
            {
                try
                {
                    return (String) fields[i].get(null);
                }
                catch (Exception ex)
                {
                    return "!" + fieldName + "!";
                }
            }
        }
        return "!" + fieldName + "!";
    }

}
