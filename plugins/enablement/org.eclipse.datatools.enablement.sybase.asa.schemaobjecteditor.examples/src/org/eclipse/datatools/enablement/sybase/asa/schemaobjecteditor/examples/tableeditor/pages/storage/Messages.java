/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.storage;

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

    public static String ASAStorageEditorPage_204_default;
    public static String ASAStorageEditorPage_byte_reserved_for_table;
    public static String ASAStorageEditorPage_bytes_reserved;
    public static String ASAStorageEditorPage_bytes_reserved_label;
    public static String ASAStorageEditorPage_dbspace_name;
    public static String ASAStorageEditorPage_default;
    public static String ASAStorageEditorPage_default_tip;
    public static String ASAStorageEditorPage_file;
    public static String ASAStorageEditorPage_percentage;
    public static String ASAStorageEditorPage_percentage_tip;
    public static String ASAStorageEditorPage_physical_storage;
    public static String ASAStorageEditorPage_reserved_bytes;
    public static String ASAStorageEditorPage_storage;
    
}
