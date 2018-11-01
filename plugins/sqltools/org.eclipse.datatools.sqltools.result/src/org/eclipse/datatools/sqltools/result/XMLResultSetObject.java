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

import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Represent an XML result set.
 * 
 * @author Dafan Yang
 *
 */
public class XMLResultSetObject implements IResultSetObject
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long   serialVersionUID = 1L;
    private List                _rows;
    private IResultSetRow       _row;
    private static final String XML_COLUMN_NAME  = "XML_RESULT_SET";
    private static final int    XML_COUMN_SIZE   = 32768;

    public XMLResultSetObject(String xmlString)
    {
        _rows = new ArrayList(1);
        if (xmlString == null)
        {
            xmlString = "";
        }
        _row = new ResultSetRow(new Object[]
        {
            xmlString
        });
        _rows.add(_row);
    }

    public int getColumnCount()
    {
        return 1;
    }

    public String[] getColumnNames()
    {
        return new String[]
        {
            XML_COLUMN_NAME
        };
    }

    public String getColumnName(int index)
    {
        return XML_COLUMN_NAME;
    }

    public int[] getColumnDisplaySizes()
    {
        return new int[]
        {
            XML_COUMN_SIZE
        };
    }

    public int getColumnDisplaySize(int index)
    {
        return XML_COUMN_SIZE;
    }

    public int[] getColumnSQLTypes()
    {
        return new int[]
        {
            Types.VARCHAR
        };
    }

    public int getColumnSQLType(int index)
    {
        return Types.VARCHAR;
    }

    public int getRowCount()
    {
        return 1;
    }

    public int getTotalRowCount()
    {
        return 1;
    }

    public IResultSetRow getRowData(int row)
    {
        return _row;
    }

    public Iterator getAllRecords()
    {
        return _rows.iterator();
    }

    public Iterator getDisplayRecords()
    {
        return _rows.iterator();
    }

    public boolean isAllResultLoaded()
    {
        return true;
    }

    public void dispose()
    {
        //do nothing
    }
}
