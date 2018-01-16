/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.privileges;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesDetailTableMetaData;

/**
 * Meta data for ASA privileges tree table
 * 
 * @author Idull
 * 
 */
public class ASAColumnPrivilegesDetailTableMetaData extends PrivilegesDetailTableMetaData
{
    public static final String[] COLUMN_NAMES   = new String[]
                                                {
        Messages.TableMetaData_grantee, Messages.Privileges_Select, Messages.Privileges_Update,
        Messages.Privileges_Reference
                                                };
    public static final int[]    COLUMN_LENGTH  = new int[]
                                                {
        180, 80, 80, 90
                                                };
    public static final String[] COLUMN_ACTIONS = new String[]
                                                {
        "", PrivilegesConstants.SELECT_PRIVILEGE, PrivilegesConstants.UPDATE_PRIVILEGE,
        PrivilegesConstants.REFERENCE_PRIVILEGE//$NON-NLS-1$
                                                };

    public int[] getColumnLengths()
    {
        return COLUMN_LENGTH;
    }

    public String[] getColumnNames()
    {
        return COLUMN_NAMES;
    }

    public int getColumnsCount()
    {
        return COLUMN_NAMES.length;
    }

    public String[] getColumnActions()
    {
        return COLUMN_ACTIONS;
    }
}
