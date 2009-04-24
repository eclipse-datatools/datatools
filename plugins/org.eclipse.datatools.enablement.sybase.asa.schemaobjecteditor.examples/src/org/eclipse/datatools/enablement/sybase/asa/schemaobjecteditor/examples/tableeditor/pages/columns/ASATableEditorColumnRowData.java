/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.columns;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;

/**
 * Row data for Adaptive Server Anywhere table
 * 
 * @author Idull
 */
public class ASATableEditorColumnRowData extends RowData
{
    /**
     * The column instance bundled with this row
     */
    private SybaseASABaseColumn _column;
    private boolean             _isDirty = false;
    private boolean             _isNewlyAdded = false;
    /**
     * If user inputs an invalid data type, the wrong string is stored here
     */
    private String              _dataTypeString;
    
    public ASATableEditorColumnRowData()
    {
        super();
    }

    public ASATableEditorColumnRowData(ITableData table, int type, Object[] data)
    {
        super(table, type, data);
    }

    public SybaseASABaseColumn getColumn()
    {
        return _column;
    }

    public void setColumn(SybaseASABaseColumn _column)
    {
        this._column = _column;
    }
    
    public boolean isDrity()
    {
        return _isDirty;
    }
    
    public void markDirty(boolean dirty)
    {
        _isDirty = dirty;
        if(_isDirty)
        {
            updateValue(ASATableEditorColumnsTableData.MARKER_COLUMN, "*"); //$NON-NLS-1$
        }
        else
        {
            updateValue(ASATableEditorColumnsTableData.MARKER_COLUMN, ""); //$NON-NLS-1$
        }
    }

    public boolean isNewlyAdded()
    {
        return _isNewlyAdded;
    }

    public void setIsNewlyAdded(boolean newlyAdded)
    {
        _isNewlyAdded = newlyAdded;
    }

    public String getDataTypeString()
    {
        return _dataTypeString;
    }

    public void setDataTypeString(String typeString)
    {
        _dataTypeString = typeString;
    }
}
