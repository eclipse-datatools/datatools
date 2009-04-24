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
 * Privilege detail table meta data
 * 
 * @author Idull
 */
public class ASATablePrivilegesDetailTableMetaData extends PrivilegesDetailTableMetaData
{
    public static final String[] COLUMN_NAMES     = new String[]
                                                  {
        Messages.TableMetaData_grantee, Messages.Privileges_Select, Messages.Privileges_Insert,
        Messages.Privileges_Update, Messages.Privileges_Delete,
        Messages.Privileges_Alter, Messages.Privileges_Reference
                                                  };
    public static final int[]    COLUMN_LENGTH    = new int[]
                                                  {
        180, 60, 60, 60, 60, 60, 80
                                                  };
    public static final String[] COLUMN_ACTIONS   = new String[]
                                                  {
        "", PrivilegesConstants.SELECT_PRIVILEGE, PrivilegesConstants.INSERT_PRIVILEGE,
        PrivilegesConstants.UPDATE_PRIVILEGE, PrivilegesConstants.DELETE_PRIVILEGE,
        PrivilegesConstants.ALTER_PRIVILEGE, PrivilegesConstants.REFERENCE_PRIVILEGE//$NON-NLS-1$
                                                  };

    public static final int      SELECT_COLUMN    = 1;
    public static final int      INSERT_COLUMN    = 2;
    public static final int      UPDATE_COLUMN    = 3;
    public static final int      DELETE_COLUMN    = 4;
    public static final int      ALTER_COLUMN     = 5;
    public static final int      REFERENCE_COLUMN = 6;

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
