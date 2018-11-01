/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.core.dbitem;

import java.sql.Types;
import java.util.Vector;

import org.eclipse.datatools.sqltools.common.core.tableviewer.AbstractTableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.Messages;

/**
 * Row data for parameters dialog.
 * 
 * @author Dafan Yang
 */
public class ParametersData extends AbstractTableData
{
    private static final String[] COLUMN_NAMES  = new String[]
    {
        Messages.ParameterTableViewer_name, Messages.ParameterTableViewer_type,
        Messages.ParameterTableViewer_null, Messages.ParameterTableViewer_value,
        Messages.ParameterTableViewer_inout
    }
    ;
    public static final int[]     COLUMN_LENGTH = new int[]
    {
        90, 80, 45, 142, 65
    }
    ;
    public static final int       NAME_COLUMN   = 0;
    public static final int       TYPE_COLUMN   = 1;
    public static final int       NULL_COLUMN   = 2;
    public static final int       VALUE_COLUMN  = 3;
    public static final int       INOUT_COLUMN  = 4;
    private ParameterWrapper[]    _wrappers;

    public ParametersData(ParameterWrapper[] wrappers)
    {
        super();
        _wrappers = wrappers;
        //for CR499556-1
        buildRows();
    }
    
    private void buildRows() {
    	for (int i = 0; i < _wrappers.length; i++)
        {
            Object[] rowData = new Object[getColumnCount()];
            ParameterDescriptor pd = _wrappers[i].getParameterDescriptor();
            rowData[ParametersData.NAME_COLUMN] = pd != null ? pd.getName() : "";
            String typeName = "";
            if (pd != null)
            {
                if (pd.getSqlTypeNameFromParser() != null)
                {
                    typeName = pd.getSqlTypeNameFromParser();
                }
                else
                {
                    typeName = pd.getTypeName();
                }
            }
            rowData[ParametersData.TYPE_COLUMN] = typeName;
            rowData[ParametersData.NULL_COLUMN] = _wrappers[i].isNull() ? "true" : "false";
            rowData[ParametersData.VALUE_COLUMN] = _wrappers[i].getValue();
            rowData[ParametersData.INOUT_COLUMN] = pd != null ? pd.getParamTypeAsString() : "";
            _wrappers[i].setData(rowData);
            _wrappers[i].setTableData(this);
            _rows.add(_wrappers[i]);
        }
    }

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
        if (col == NULL_COLUMN)
        {
            return Types.BOOLEAN;
        }
        return Types.CHAR;
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
        // do nothing
    }

    public boolean isNewRowDataAllowed()
    {
        return false;
    }
}
