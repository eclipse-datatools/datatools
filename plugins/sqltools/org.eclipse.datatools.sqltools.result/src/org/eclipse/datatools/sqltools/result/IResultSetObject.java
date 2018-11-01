/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

import java.io.Serializable;
import java.util.Iterator;

/**
 * The <code>IResultSetObject</code> represents a result set queried from database or represents a tabular data set. One
 * <code>IResultSetObject</code> instance contains two kinds of data: meta data and row data.
 * <ul>
 * <li>Meta data: column name, column size, column type
 * <li>Row data: a list of <code>IResultSetRow</code> instances
 * </ul>
 * <p>
 * Three types of methods are defined in this interface: <ul>
 * <li>Methods to get the meta data of a result set
 * <li>Methods to get the row data of a result set
 * <li>Method to release the resource related with a result set </ul>
 * 
 * <p>
 * Also, notice that for performance consideration, we allow the implementation of this interface to cache some rows
 * into a file.
 * 
 * @see org.eclipse.datatools.sqltools.result.IResultSetRow
 * @author Dafan Yang
 */
public interface IResultSetObject extends Serializable
{
    /**
     * Returns the column count
     * 
     * @return column count
     */
    public int getColumnCount();

    /**
     * Returns the column names
     * 
     * @return column names
     */
    public String[] getColumnNames();

    /**
     * Returns the column name at given index (based on 1 --- follows the JDBC convention)
     * 
     * @param index the column index
     * @return column name
     */
    public String getColumnName(int index);

    /**
     * Returns display size of all columns
     * 
     * @return columns display sizes
     */
    public int[] getColumnDisplaySizes();

    /**
     * Returns column display size at the given column (based on 1 --- follows the JDBC convention)
     * 
     * @return column display size
     */
    public int getColumnDisplaySize(int index);

    /**
     * Returns column SQL types.
     * 
     * @return column types
     */
    public int[] getColumnSQLTypes();

    /**
     * Returns column SQL data type at the given column (based on 1 --- follows the JDBC convention)
     * 
     * @param index column index
     * @return column type
     */
    public int getColumnSQLType(int index);

    /**
     * Returns number of rows in result (Loaded in memory)
     * 
     * @return row count
     */
    public int getRowCount();

    /**
     * Returns total row count (Include cached rows)
     * 
     * @return total row count
     */

    public int getTotalRowCount();

    /**
     * Returns row data of given row index (based on 0)
     * 
     * @param row the row index
     * @return the row data at the given row index
     */
    public IResultSetRow getRowData(int row);

    /**
     * Returns all result (In memory and file), each element's type should be <code>IResultSetRow</code>
     * 
     * @see IResultSetRow
     * @return an <code>Iterator</code> instance over all records
     */
    public Iterator getAllRecords();

    /**
     * Returns the records to display (Loaded in memory), each element's type should be <code>IResultSetRow</code>
     * 
     * @see IResultSetRow
     * @return an <code>Iterator</code> instance over all display record
     */
    public Iterator getDisplayRecords();

    /**
     * Checks if some result rows are stored into a temporary file
     * 
     * @return <code>true</code> if there are no cached rows
     */
    public boolean isAllResultLoaded();

    /**
     * Disposes resource after resultset is closed;
     * 
     */
    public void dispose();
}
