/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.privileges;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    
    
    private Messages()
    {
    }

    public static String ASAPrivilegesEditorPage_permission_detail;
    public static String ASAPrivilegesEditorPage_privileges;
    public static String TableMetaData_grantee;
    public static String PrivilegePage_legends;
    public static String PrivilegePage_granted;
    public static String PrivilegePage_grant_with_option;
    public static String PrivilegePage_inherited;
    public static String PrivilegePage_revoke_inherited;
    public static String Privileges_Select;
    public static String Privileges_Insert;
    public static String Privileges_Update;
    public static String Privileges_Delete;
    public static String Privileges_Alter;
    public static String Privileges_Reference;
    
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
    
}
