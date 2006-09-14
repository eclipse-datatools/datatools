/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.core.tableviewer;

/**
 * 
 * @author lihuang
 * 
 */
public interface IRowData
{

    /**
     * Return ITableData object.
     * 
     * @return
     */
    public ITableData getTableData();

    /**
     * Return value by column index in this row.
     * The type of value is <code>java.sql.Types</code>
     * 
     * The type can be gotten by 'rowData.getTableData().getColumnType(col)'.
     * 
     * @param col
     * @return
     */
    public Object getValue(int col);

    /**
     * Update value of this row by column index.
     * 
     * @param col column index.
     * @param value 
     */
    public void updateValue(int col, Object value);

    /**
     * Returns row's state
     * @return
     */    
    public int getState();

    /**
     * Sets row's state
     * @param state
     */
    public void setState(int state);
}
