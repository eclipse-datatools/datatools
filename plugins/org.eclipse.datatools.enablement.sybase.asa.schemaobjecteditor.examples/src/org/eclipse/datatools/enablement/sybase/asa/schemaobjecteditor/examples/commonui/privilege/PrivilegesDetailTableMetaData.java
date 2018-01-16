/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

/**
 * The meta data for privileges detail page
 * 
 * @author Idull
 */
public abstract class PrivilegesDetailTableMetaData
{
    /**
     * The "Grantee" column must be the first column and is not editable
     */
    public static final int GRANTEE_COLUMN = 0;

    /**
     * Returns the column names
     * 
     * @return
     */
    public abstract String[] getColumnNames();

    /**
     * Returns the length of all the column
     * 
     * @return
     */
    public abstract int[] getColumnLengths();

    /**
     * Returns the column count
     * 
     * @return
     */
    public abstract int getColumnsCount();

    /**
     * Returns the column actions, corresponding to the column name.
     * <p>
     * Remain empty for "Grantee" column
     * 
     * @return
     */
    public abstract String[] getColumnActions();

    /**
     * Returns the action string corresponding to the given column
     * 
     * @param columnName
     * @return
     */
    public String getAction(String columnName)
    {
        return getColumnActions()[getColumnIndex(columnName)];
    }

    public String getAction(int colIndex)
    {
        if(colIndex < 0 || colIndex >= getColumnsCount())
        {
            return "";
        }
        return getColumnActions()[colIndex];
    }
    
    public String getColumnName(int colIndex)
    {
        if(colIndex < 0 || colIndex >= getColumnsCount())
        {
            return "";
        }
        return getColumnNames()[colIndex];
    }

    public int getColumnLength(int colIndex)
    {
        if(colIndex < 0 || colIndex >= getColumnsCount())
        {
            return 0;
        }
        return getColumnLengths()[colIndex];
    }

    public int getColumnIndex(String name)
    {
        for (int i = 0; i < getColumnsCount(); i++)
        {
            if (name.equals(getColumnNames()[i]))
            {
                return i;
            }
        }
        return 0;
    }
}
