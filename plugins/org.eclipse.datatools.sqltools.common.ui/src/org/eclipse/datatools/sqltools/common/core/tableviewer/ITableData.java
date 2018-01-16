/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.core.tableviewer;

import java.util.Vector;

/**
 * 
 * @author lihuang
 * 
 */
public interface ITableData
{
    /**
     * Return whether the table data is read-only.
     * 
     * @return <code>true</code> if the table data is read-only
     */
    public boolean isReadonly();

    /**
     * Return whether new rowdata is allowed to insert into tabldata.
     * Note: if isReadonly() is true, this method should be set as false;
     * 
     * @return <code>true</code> if new rowdata is allowed to insert into tabldata.
     */
    public boolean isNewRowDataAllowed();

    /**
     * Return column count.
     * 
     * @return
     */
    public int getColumnCount();

    /**
     * Return column name by column index.
     * @param col
     * @return
     */
    public String getColumnName(int col);

    /**
     * Return column type by column index.
     * Type is generic SQL types, called JDBC types (<code> java.sql.Types </code>).
     * 
     * @param col
     * @return
     */
    public int getColumnType(int col);

    /**
     * Return column header by column index.
     * @param col
     * @return
     */
    public String getColumnHeader(int col);

    /**
     * Return RowData vector.
     * 
     * @return
     */
    public Vector getRows();

    /**
     * Insert a empty row.
     * 
     * Here is an example of implement this method.
     * 
     * <pre>
     *  Object data[] = new Object[getColumnCount()];
     *  IRowData row = new RowData(this, AbstractRowData.STATE_INSERTED, data);
     *  rows.add(row);
     *  return row;
     * </pre>
     *  
     * @return
     */
    public IRowData insertRow();

    /**
     * Insert the specified rowData object as a component in this tableData at the specified row.
     * <br>
     * The row must be a value greater than or equal to <code>0</code> 
     * and less than or equal to the current column count of the tableData. (If the
     * row is equal to the current column count of the tableData, the new rowData
     * is appended to the tableData.)
     * 
     * @param rowData the rowData to insert.
     * @param row where to insert the new rowData.
     */
    public void insertRow(IRowData rowData, int row);

    /**
     * Delete a row.
     * 
     * @param row
     */
    public void deleteRow(IRowData row);

    /**
     * Save the content of the table data to source.
     * The source is database, file or other object.
     * 
     * @return
     * @throws Exception
     */
    public boolean save() throws Exception;

    /**
     * Revert the content of table data.
     *
     */
    public void revert();

    /**
     * Reset the data of rows to the original status.
     * 
     */
    public void resetRowsToOriginal();

    public void dispose();

    public void fireUpdated(IRowData row, int colIndex, Object oldVal, Object newVal);

    public void addTableDataChangeListener(ITableDataChangeListener listener);

    public void removeTableDataChangeListener(ITableDataChangeListener listener);
}
