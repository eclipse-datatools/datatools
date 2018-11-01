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

/**
 * The <code>IResultSetRow</code> represents a row in <code>IResultSetObject</code>.
 * 
 * @see org.eclipse.datatools.sqltools.result.IResultSetObject
 * @author Dafan Yang
 */
public interface IResultSetRow extends Serializable
{
    /**
     * Returns the data of this row
     * 
     * @return the row values
     */
    public Object[] getData();

    /**
     * Returns the row data at the given column (the index is based on 0)
     * 
     * @param index the column index
     * @return the data at the given column
     */
    public Object getData(int index);

    /**
     * Sets the row data for this row
     * 
     * @param values data of this row
     */
    public void setData(Object[] values);

    /**
     * Sets the row data of at the given column (the index is based on 0)
     * 
     * @param obj the column data
     * @param index the column index
     */
    public void setData(Object obj, int index);
}
