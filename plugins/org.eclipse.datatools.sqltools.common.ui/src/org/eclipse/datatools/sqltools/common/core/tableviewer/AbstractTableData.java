/**
 * Created on 2005-11-8
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.core.tableviewer;

import java.util.Vector;

import org.eclipse.core.runtime.ListenerList;

/**
 * 
 * @author lihuang
 * 
 */
public abstract class AbstractTableData implements ITableData
{

    /** Vector for IRowData objects */
    protected Vector  _rows = new Vector();

    protected ListenerList<ITableDataChangeListener> _listenerList = new ListenerList<>();
    /**
     * Set IRowData objects.
     * Each member of rows is <code>IRowData</code> object.
     * @param rows
     */
    public void setRows(Vector rows)
    {
        _rows = rows;
    }

    public void deleteRow(IRowData row)
    {
        if (((RowData) row).getState() == RowData.STATE_INSERTED || ((RowData) row).getState() == RowData.STATE_ORIGINAL || ((RowData) row).getState() == RowData.STATE_UPDATED)
        {
            _rows.remove(row);
        }
        else
        {
            ((RowData) row).setState(RowData.STATE_DELETED);
        }

        for (ITableDataChangeListener listener : _listenerList)
        {
            listener.rowDeleted(row);
        }
    }

    public IRowData insertRow()
    {
        Object data[] = new Object[getColumnCount()];
        IRowData row = new RowData(this, RowData.STATE_INSERTED, data);
        _rows.add(row);

        for (ITableDataChangeListener listener : _listenerList)
        {
            listener.rowAdded(row);
        }

        return row;
    }

    public void insertRow(IRowData rowData, int row)
    {
        _rows.insertElementAt(rowData, row);
        for (ITableDataChangeListener listener : _listenerList)
        {
            listener.rowAdded(rowData);
        }
    }

    public void revert()
    {
        int i = 0;
        while (i < _rows.size())
        {
            RowData row = (RowData) _rows.elementAt(i);
            if (row.getState() == RowData.STATE_UPDATED || row.getState() == RowData.STATE_DELETED)
            {
                row.revertToOriginal();
                ++i;
            }
            else if (row.getState() == RowData.STATE_INSERTED)
            {
                _rows.remove(i);
            }
            else if (row.getState() == RowData.STATE_ORIGINAL)
            {
                ++i;
            }
        }
    }

    public void resetRowsToOriginal()
    {
        int i = 0;
        while (i < _rows.size())
        {
            RowData row = (RowData) _rows.elementAt(i);
            if (row.getState() == RowData.STATE_UPDATED || row.getState() == RowData.STATE_INSERTED)
            {
                row.resetToOriginal();
                ++i;
            }
            else if (row.getState() == RowData.STATE_DELETED)
            {
                _rows.remove(i);
            }
            else if (row.getState() == RowData.STATE_ORIGINAL)
            {
                ++i;
            }
        }
    }

    public void fireUpdated(IRowData row, int colIndex, Object oldVal, Object newVal)
    {
        for (ITableDataChangeListener listener : _listenerList)
        {
            listener.rowDataUpdated(row, colIndex, oldVal, newVal);
        }
    }

    public void addTableDataChangeListener(ITableDataChangeListener listener)
    {
        if(listener == null)
        {
            return;
        }
        if(listener instanceof ITableDataChangeListener)
        {
            _listenerList.add(listener);
        }
    }

    public void removeTableDataChangeListener(ITableDataChangeListener listener)
    {
        if(listener == null)
        {
            return;
        }
        if(listener instanceof ITableDataChangeListener)
        {
            _listenerList.remove(listener);
        }
    }

    public boolean isNewRowDataAllowed()
    {
        return true;
    }
    public abstract boolean isReadonly();

    public abstract int getColumnCount();

    public abstract String getColumnName(int col);

    public abstract int getColumnType(int col);

    public abstract String getColumnHeader(int col);

    public abstract Vector getRows();

    public abstract boolean save() throws Exception;
}
