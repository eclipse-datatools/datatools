/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.columns;

import java.sql.Types;
import java.util.Vector;

import org.eclipse.datatools.sqltools.common.core.tableviewer.AbstractTableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableDataChangeListener;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;

/**
 * Table data for Adaptive Server Anywhere table
 * 
 * @author Idull
 */
public class ASATableEditorColumnsTableData extends AbstractTableData
{
    public static final String[] COLUMN_NAMES    = new String[]
                                                 {
        "", Messages.ASATableEditorColumnsTableData_pk, Messages.ASATableEditorColumnsTableData_column_name, Messages.ASATableEditorColumnsTableData_data_type, Messages.ASATableEditorColumnsTableData_nullable, Messages.ASATableEditorColumnsTableData_default, Messages.ASATableEditorColumnsTableData_unique, Messages.ASATableEditorColumnsTableData_comment //$NON-NLS-1$
                                                 };
    public static final int[]    COLUMN_LENGTH   = new int[]
                                                 {
        20, 95, 120, 115, 75, 110, 60, 110
                                                 };
    public static final int      MARKER_COLUMN   = 0;
    public static final int      PRI_KEY_COLUMN  = 1;
    public static final int      NAME_COLUMN     = 2;
    public static final int      TYPE_COLUMN     = 3;
    public static final int      NULLABLE_COLUMN = 4;
    public static final int      DEFAULT_COLUMN  = 5;
    public static final int      UNIQUE_COLUMN   = 6;
    public static final int      COMMENT_COLUMN  = 7;

    public int getColumnCount()
    {
        return COLUMN_NAMES.length;
    }

    public String getColumnHeader(int col)
    {
        return COLUMN_NAMES[col];
    }

    public String getColumnName(int col)
    {
        return COLUMN_NAMES[col];
    }

    public int getColumnType(int col)
    {
        switch (col)
        {
            case PRI_KEY_COLUMN:
            case NULLABLE_COLUMN:
            case UNIQUE_COLUMN:
                return Types.BOOLEAN;
            default:
                return Types.CHAR;
        }
    }

    public Vector getRows()
    {
        return _rows;
    }

    public boolean isReadonly()
    {
        return false;
    }

    public boolean save() throws Exception
    {
        return false;
    }

    public void dispose()
    {

    }

    public IRowData insertRow()
    {
        Object data[] = new Object[getColumnCount()];
        IRowData row = new ASATableEditorColumnRowData(this, RowData.STATE_INSERTED, data);
        row.updateValue(NULLABLE_COLUMN, "true"); //$NON-NLS-1$
        ((ASATableEditorColumnRowData) row).markDirty(true);
        ((ASATableEditorColumnRowData) row).setIsNewlyAdded(true);
        _rows.add(row);

        Object[] listeners = _listenerList.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((ITableDataChangeListener) listeners[i]).rowAdded(row);
        }

        return row;
    }
}
