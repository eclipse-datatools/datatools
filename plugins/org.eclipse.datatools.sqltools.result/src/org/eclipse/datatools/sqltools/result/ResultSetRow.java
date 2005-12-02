/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

import java.io.Serializable;

/**
 * The <code>ResultSetRow</code> is a standard implementation of <code>IResultSetRow</code>.
 * <p>
 * There are two ways to initiate an instance of <code>ResultSetRow</code>, two examples are given below:
 * <p>
 * Example I:
 * 
 * <pre>
 * Object[] data = new Object[3];
 * data[0] = new Integer(1);
 * data[1] = new Integer(23);
 * data[2] = new String(&quot;Jack&quot;);
 * IResultSetRow row = new ResultSetRow(data);
 * </pre>
 * 
 * <p>
 * Example II:
 * 
 * <pre>
 * IResultSetRow row = new ResultSetRow(3);
 * row.setData(new Integer(1), 0);
 * row.setData(new Integer(23), 1);
 * row.setData(new String(&quot;Jack&quot;), 2);
 * </pre>
 * 
 * @author Dafan Yang
 */
public class ResultSetRow implements IResultSetRow, Serializable
{
    private static final long serialVersionUID = -8798205166177300372L;
    private Object[]          _values;

    /**
     * Constructs a result set row given the row values
     * 
     * @param values data of this row
     */
    public ResultSetRow(Object[] values)
    {
        _values = values;
    }

    /**
     * Constructs a result set row, specify the column count.
     * 
     * @param columnCount the column count (must be greater than or equals to 0)
     * @exception NegativeArraySizeException - if a negative column count is given
     */
    public ResultSetRow(int columnCount)
    {
        _values = new Object[columnCount];
    }

    /**
     * Returns the data of this result set row
     * 
     * @return the row data
     */
    public Object[] getData()
    {
        return _values;
    }

    /**
     * Returns the data object at the given column
     * 
     * @param index the column index
     * @return the data at the given column
     */
    public Object getData(int index)
    {
        if (_values == null)
        {
            return null;
        }
        if ((index < 0) || (index > _values.length - 1))
        {
            return null;
        }
        return _values[index];
    }

    /**
     * Sets the data for this result set row.
     * 
     * @param values data of this row
     */
    public void setData(Object[] values)
    {
        _values = values;
    }

    /**
     * Sets the data of this result set at given column
     * 
     * @param obj data of this row at given column
     * @param index column index (based on 0)
     * @exception ArrayIndexOutOfBoundsException - if the index is bigger than the column count or smaller than 0
     */
    public void setData(Object obj, int index)
    {
        _values[index] = obj;
    }
}
