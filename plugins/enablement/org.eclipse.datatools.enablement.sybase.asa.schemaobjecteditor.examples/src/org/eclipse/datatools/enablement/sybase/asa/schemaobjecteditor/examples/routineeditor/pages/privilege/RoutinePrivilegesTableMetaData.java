/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.privilege;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesDetailTableMetaData;

/**
 * 
 * @author Hui Cao
 * 
 */
public class RoutinePrivilegesTableMetaData extends PrivilegesDetailTableMetaData
{
    public static final String[] COLUMN_NAMES     = new String[]
                                                  {
        Messages.RoutinePrivilegesTableMetaData_grantee, Messages.RoutinePrivilegesTableMetaData_execute_privilege
                                                  };
    public static final int[]    COLUMN_LENGTH    = new int[]
                                                  {
        150, 100
                                                  };
    public static final String[] COLUMN_ACTIONS   = new String[]
                                                  {
        "", RoutinePrivilegesConstants.EXECUTE_PRIVILEGE //$NON-NLS-1$
                                                  };

    public static final int      EXECUTE_COLUMN    = 1;

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
