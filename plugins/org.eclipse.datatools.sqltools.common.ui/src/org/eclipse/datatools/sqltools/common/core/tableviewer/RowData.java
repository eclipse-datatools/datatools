/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.core.tableviewer;

/**
 * 
 * Common functions for row data of table.
 * <p>
 * Clients implementing row data should subclass this class.
 * </p>
 * 
 * 
 * @author lihuang
 * 
 */
public class RowData implements IRowData, Cloneable
{

    // Possible state transitions ( OLD STATE - action - NEW STATE)
    // load - ORIGINAL
    // insert - INSERTED
    // ORIGINAL - update - UPDATED
    // ORIGINAL - delete - DELETED
    // UPDATED - delete - DELETED
    // INSERTED - delete
    // UPDATED - save - ORIGINAL
    // INSERTED - save - ORIGINAL
    // INSERTED - revert
    // UPDATED - revert - ORIGINAL
    // DELETED - revert - ORIGINAL

    protected ITableData    _tableData;

    protected int           _state;
    public static final int STATE_ORIGINAL = 0;
    public static final int STATE_UPDATED  = 1;
    public static final int STATE_DELETED  = 2;
    public static final int STATE_INSERTED = 3;

    protected Object[]      _newData;
    protected Object[]      _oldData;

    public RowData()
    {
    }

    public RowData(ITableData table, int type, Object[] data)
    {
        this._tableData = table;
        this._state = type;
        this._newData = data;

        if (type == STATE_ORIGINAL)
        {
            _oldData = (Object[]) data.clone();
        }
    }

    public ITableData getTableData()
    {
        return _tableData;
    }

    public Object getValue(int col)
    {
        return _newData[col];
    }

    public int getState()
    {
        return _state;
    }

    public void setState(int state)
    {
        this._state = state;
    }

    public void updateValue(int col, Object value)
    {
        if (_state == STATE_ORIGINAL)
        {
            _state = STATE_UPDATED;
        }
        Object oldVal = _newData[col];
        _newData[col] = value;
        getTableData().fireUpdated(this, col, oldVal, value);
    }

    public void save(TableDataSaveStatus status) throws Exception
    {
        switch (_state)
        {
            case STATE_UPDATED:
                doUpdate(status);
                break;
            case STATE_INSERTED:
                doInsert(status);
                break;
            case STATE_DELETED:
                doDelete(status);
                break;
            case STATE_ORIGINAL:
                break;
            default:
                break;
        }
    }

    protected void doInsert(TableDataSaveStatus status)throws Exception
    {

    }

    protected void doUpdate(TableDataSaveStatus status)throws Exception
    {

    }
    protected void doDelete(TableDataSaveStatus status)throws Exception
    {

    }

    /**
     * When the modifications have been saved , this method is called on rows of type TYPE_UPDATED or TYPE_INSERTED to
     * reflect the state of the source.
     */
    public void resetToOriginal()
    {
        _state = STATE_ORIGINAL;
        _oldData = (Object[]) _newData.clone();
    }

    public void revertToOriginal()
    {
        _state = STATE_ORIGINAL;
        _newData = (Object[]) _oldData.clone();
    }

    /**
     * Implements this method to support copy/paste functions. Note: we don't clone value of each column.
     * WARN: Every subclass of this class which contains new data member need to override this method
     */
    public Object clone() throws CloneNotSupportedException
    {
        Object result = super.clone();
        RowData row = (RowData)result;
        row._newData = (Object[])_newData.clone();
        if (_oldData != null)
        {
            row._oldData = (Object[])_oldData.clone();
        }
        return result;
    }

    /**
     * When pasting the row to a new table, we need to set the table data
     * @param data
     */
    public void setTableData(ITableData data)
    {
        _tableData = data;
    }
    
    public void updateValueWithoutNotification(int col, Object value)
    {
        if (_state == STATE_ORIGINAL)
        {
            _state = STATE_UPDATED;
        }
        _newData[col] = value;
    }
}
