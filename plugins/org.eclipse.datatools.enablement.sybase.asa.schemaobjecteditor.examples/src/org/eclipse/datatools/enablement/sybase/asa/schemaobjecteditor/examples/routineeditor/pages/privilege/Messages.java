/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.privilege;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author Hui Cao
 * 
 */
public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    
    
    private Messages()
    {
    }

    public static String ProceduralObjectPrivilegePage_granted;
    public static String ProceduralObjectPrivilegePage_inherited;
    public static String ProceduralObjectPrivilegePage_legends;
    public static String ProceduralObjectPrivilegePage_page_name;
    
    public static String RoutinePrivilegesTableMetaData_execute_privilege;
    public static String RoutinePrivilegesTableMetaData_grantee;
    
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
    
}
