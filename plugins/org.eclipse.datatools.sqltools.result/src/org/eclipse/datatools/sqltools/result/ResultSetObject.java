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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.result.internal.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.internal.utils.SQLUtil;
import org.eclipse.jface.util.Assert;

/**
 * A standard implementation of <code>IResultSetObject</code> with cache mechanism. The consumer can use constructor
 * <code>ResultSetObject(List rows, String[] columnNames, int[] columnTypes, int[] columnDisplaySizes)</code> to
 * construct an instance of <code>IResultSetObject</code> given meta data and row data. An usage example is given below:
 * <pre>
 * IResultSetRow row1 = new ResultSetRow(new String[]
 * {
 *     &quot;1&quot;, &quot;21&quot;, &quot;Jack&quot;
 * });
 * IResultSetRow row2 = new ResultSetRow(new String[]
 * {
 *     &quot;2&quot;, &quot;23&quot;, &quot;Micheal&quot;
 * });
 * ArrayList rows = new ArrayList();
 * rows.add(row1);
 * rows.add(row2);
 * IResultSetObject rs = new ResultSetObject(rows, 
 * new String[]
 * {
 *     &quot;Id&quot;, &quot;Age&quot;, &quot;Name&quot;
 * }, 
 * new int[]
 * {
 *     Types.CHAR, Types.CHAR, Types.CHAR
 * }, 
 * new int[]
 * {
 *     8, 8, 21
 * });
 * </pre>
 * 
 * @see org.eclipse.datatools.sqltools.result.ResultSetRow
 * @author Dafan Yang
 */
public class ResultSetObject implements IResultSetObject
{
    private String[]       _columnNames;
    private List           _rows;
    private int[]          _columnTypes;
    private int[]          _columnDisplaySizes;
    // total row count loaded into memory
    private int            _rowCountLoaded;
    // total row count of the JDBC result set
    private int            _totalRowCount;
    private File           _backupFile;
    private static ILogger _log = ResultsViewPlugin.getLogger(null);

    /**
     * Constructs a ResultSetObject instance from a JDBC ResultSet object
     * 
     * @param resultset the JDBC result set object
     * @param maxRowCount to limit the max row count
     * @param maxDisplayRowCount to limit the max display row count
     * @exception SQLException - if a database access error occurs
     */
    public ResultSetObject(ResultSet resultset, int maxRowCount, int maxDisplayRowCount) throws SQLException
    {
        ObjectOutputStream oos = null;
        try
        {
            ResultSetMetaData meta = resultset.getMetaData();
            int columnCount = meta.getColumnCount();
            _totalRowCount = 0;
            _rows = new ArrayList();
            _columnNames = new String[columnCount];
            _columnDisplaySizes = new int[columnCount];
            _columnTypes = new int[columnCount];
            
            for (int i = 0; i < columnCount; i++)
            {
                _columnNames[i] = meta.getColumnName(i + 1);
                _columnDisplaySizes[i] = meta.getColumnDisplaySize(i + 1);
                _columnTypes[i] = meta.getColumnType(i + 1);
            }

            IResultSetRow row = null;
            while (resultset.next() && (_totalRowCount < maxRowCount || maxRowCount == 0))
            {
                row = new ResultSetRow(columnCount);
                for (int i = 0; i < columnCount; i++)
                {
                    row.setData(resultset.getObject(i + 1), i);
                }
                _totalRowCount++;
                
                if (_totalRowCount < maxDisplayRowCount)
                {
                    _rows.add(row);
                }
                else if (_totalRowCount == maxDisplayRowCount)
                {
                    _rows.add(row);

                    if (_backupFile == null)
                    {
                        File dir = new File(ResultsViewPlugin.getDefault().getTempDir());
                        if (!dir.exists())
                        {
                            dir.mkdir();
                        }
                        _backupFile = File.createTempFile(String.valueOf(resultset.hashCode()), ".result", dir);
                        _backupFile.deleteOnExit();
                        if (_backupFile.exists())
                        {
                            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_backupFile)));
                        }
                    }
                    for (Iterator iter = _rows.iterator(); iter.hasNext();)
                    {
                        SQLUtil.saveResultToStream(oos, iter.next());
                    }
                }
                else if (_totalRowCount > maxDisplayRowCount)
                {
                    SQLUtil.saveResultToStream(oos, row);
                }
            }
            _rowCountLoaded = _rows.size();
        }
        catch (SQLException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            if (_backupFile != null && _backupFile.exists())
            {
                _backupFile.delete();
            }
        }
        finally
        {
            try
            {
                if (oos != null)
                {
                    oos.close();
                    oos = null;
                }
            }
            catch (IOException e)
            {
                // ignore
            }

        }
    }

    /**
     * Constructs a ResultSetObject instance using a list of ResultSetRow instances. Notice that we will perform
     * restrict validation during the construction: 
     * <ul>
     * <li>All the parameters should not be null 
     * <li>The length of each array should be the same 
     * <li>The row object should not be null and must be an instance of IResultSetRow
     * </ul>
     * @param rows a list of IResultSetRow objects
     * @param columnNames column name array
     * @param columnTypes column type array (refer java.sql.Types)
     * @param columnDisplaySizes column display size array
     */
    public ResultSetObject(List rows, String[] columnNames, int[] columnTypes, int[] columnDisplaySizes)
    {
        Assert.isTrue(!(rows == null || columnNames == null || columnDisplaySizes == null || columnTypes == null));
        int columnCount = columnNames.length;
        Assert.isTrue(!(columnTypes.length != columnCount || columnDisplaySizes.length != columnCount));

        Iterator iter = rows.iterator();
        while (iter.hasNext())
        {
            Object obj = iter.next();
            Assert.isNotNull(obj);
            Assert.isTrue(obj instanceof IResultSetRow);
        }
        for (int i = 0; i < columnCount; i++)
        {
            if (columnDisplaySizes[i] < 0)
            {
                columnDisplaySizes[i] = 0;
            }
            if (columnNames[i] == null)
            {
                columnNames[i] = "";
            }
        }

        _rows = rows;
        _columnNames = columnNames;
        _columnTypes = columnTypes;
        _columnDisplaySizes = columnDisplaySizes;
        _rowCountLoaded = _rows.size();
        _totalRowCount = _rowCountLoaded;
    }

    public int getColumnCount()
    {
        return _columnNames.length;
    }

    public String[] getColumnNames()
    {
        return _columnNames;
    }

    public String getColumnName(int index)
    {
        if ((index < 1) || (index > _columnNames.length))
        {
            return null;
        }
        return _columnNames[index - 1];
    }

    public int[] getColumnDisplaySizes()
    {
        return _columnDisplaySizes;
    }

    public int getColumnDisplaySize(int index)
    {
        if ((index < 1) || (index > _columnDisplaySizes.length))
        {
            return 0;
        }
        return _columnDisplaySizes[index - 1];
    }

    
    public int[] getColumnSQLTypes()
    {
        return _columnTypes;
    }

    public int getColumnSQLType(int index)
    {
        // If specify a wrong index, we return Types.CHAR
        if ((index < 1) || (index > _columnTypes.length))
        {
            return Types.CHAR;
        }
        return _columnTypes[index - 1];
    }

    public int getRowCount()
    {
        return _rowCountLoaded;
    }

    public int getTotalRowCount()
    {
        return _totalRowCount;
    }

    public IResultSetRow getRowData(int row)
    {
        if ((row < 0) || (row > _rows.size() - 1))
        {
            return null;
        }
        return (IResultSetRow) _rows.get(row);
    }

    public Iterator getAllRecords()
    {
        if (_backupFile == null)
        {
            return _rows.iterator();
        }
        Iterator iter = new BackupRecord(_backupFile);
        return iter;
    }

    public Iterator getDisplayRecords()
    {
        return _rows.iterator();
    }

    public boolean isAllResultLoaded()
    {
        return _totalRowCount == _rowCountLoaded;
    }

    public void dispose()
    {
        if (_backupFile != null && _backupFile.exists())
        {
            _backupFile.delete();
        }
    }

    /**
     * Iterator over a cache file
     * 
     * @author Dafan Yang
     */
    private class BackupRecord implements Iterator
    {

        File              _backupFile;
        ObjectInputStream _ois;
        Object            _nextRecord = null;
        int               _index      = 0;

        /**
         * 
         */
        public BackupRecord(File backupFile)
        {
            _backupFile = backupFile;
            if (backupFile != null && backupFile.exists())
            {
                try
                {
                    _ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(backupFile)));
                    _nextRecord = SQLUtil.getResultFromStream(_ois);
                }
                catch (Exception e)
                {
                    _log.error("ResultSetObjectImpl.error.iterator", e);
                }
            }
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext()
        {
            // by definition, hasNext should not perform any fetch operation, which should be the job of next().
            return (_nextRecord != null);
        }

        public Object next()
        {
            // cache the current record and fetch the next
            Object current = _nextRecord;
            if (_ois != null)
            {
                IResultSetRow row = (IResultSetRow) SQLUtil.getResultFromStream(_ois);
                _nextRecord = row;
                _index++;
                // when there're no rows, close the stream
                if (_nextRecord == null)
                {
                    try
                    {
                        _ois.close();
                        _ois = null;
                    }
                    catch (IOException e)
                    {
                        _log.error("ResultSetObjectImpl.error.next", e);
                    }
                }
            }

            return current;
        }
    }
}
