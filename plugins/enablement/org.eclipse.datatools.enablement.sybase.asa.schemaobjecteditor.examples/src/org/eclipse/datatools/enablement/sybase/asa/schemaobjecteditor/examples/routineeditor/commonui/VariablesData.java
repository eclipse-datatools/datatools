/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import java.sql.Types;
import java.util.Vector;

import org.eclipse.datatools.sqltools.common.core.tableviewer.AbstractTableData;

/**
 * 
 * @author Dafan Yang
 *
 */
public class VariablesData extends AbstractTableData
{
    private static final String[] COLUMN_NAMES  = new String[]
    {
        "",
        Messages.wizard_createSP_page2_parameter_label_name,
        Messages.wizard_createSP_page2_parameter_label_datatype
    }
    ;
    public static final int[]     COLUMN_LENGTH = new int[]
    {
        20, 200, 205
    }
    ;
    public static final int       DIRTY_COLUMN  = 0;
    public static final int       NAME_COLUMN   = 1;
    public static final int       TYPE_COLUMN   = 2;

    public boolean isReadonly()
    {
        return false;
    }

    public int getColumnCount()
    {
        return COLUMN_NAMES.length;
    }

    public String getColumnName(int col)
    {
        return COLUMN_NAMES[col];
    }

    public int getColumnType(int col)
    {
        return Types.VARCHAR;
    }

    public String getColumnHeader(int col)
    {
        return COLUMN_NAMES[col];
    }

    public Vector getRows()
    {
        return _rows;
    }

    public boolean save() throws Exception
    {
        return false;
    }

    public void dispose()
    {
        // do nothing for now
    }

}
