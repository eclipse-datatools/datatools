/*******************************************************************************
 * Copyright (c) 2005, 2010 Sybase, Inc. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.utils;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;

/**
 * SQL related utilities
 * 
 * @author Dafan Yang
 */
public class SQLUtil
{
    protected static ILogger _log = ResultsViewPlugin.getLogger(null);

    /**
     * Saves result set to output stream
     * 
     * @param oos stream to write
     * @param result result object
     * @throws IOException - if an IO access error occurs
     */
    public static void saveResultToStream(ObjectOutputStream oos, Object result) throws IOException
    {
        if (oos != null)
        {
            oos.writeObject(result);
            oos.flush();
        }
    }

    /**
     * Returns Serializable object file
     * 
     * @param ois file to store object
     * @return Serializable object
     */
    public static Object getResultFromStream(ObjectInputStream ois)
    {
        Object result = null;
        try
        {
            result = ois.readObject();
        }
        catch (EOFException e)
        {
            // igore EOFEXception and no need to log
            return null;
        }
        catch (IOException e)
        {
            _log.error(e);
            return null;
        }
        catch (ClassNotFoundException e)
        {
            _log.error(e);
            return null;
        }
        return result;
    }
    
    /**
     * Check if the given SQL type is numeric data type
     * @param datatype
     * @return
     */
    public static boolean isNumericType(int datatype)
    {
        return datatype == Types.BIGINT || datatype == Types.DECIMAL || datatype == Types.DOUBLE|| datatype == Types.FLOAT
            || datatype == Types.INTEGER|| datatype == Types.NUMERIC|| datatype == Types.REAL|| datatype == Types.SMALLINT
            || datatype == Types.TINYINT;
    }
    
    /**
     * Converts the data type id to data type string
     * 
     * @param datatype
     * @return
     */
    public static String convertToString(int datatype)
    {
        switch (datatype)
        {
            case Types.ARRAY:
                return "ARRAY";
            case Types.BIGINT:
                return "BIGINT";
            case Types.BINARY:
                return "BINARY";
            case Types.BIT:
                return "BIT";
            case Types.BLOB:
                return "BLOB";
            case Types.BOOLEAN:
                return "BOOLEAN";
            case Types.CHAR:
                return "CHAR";
            case Types.CLOB:
                return "CLOB";
            case Types.DATALINK:
                return "DATALINK";
            case Types.DATE:
                return "DATE";
            case Types.DECIMAL:
                return "DECIMAL";
            case Types.DISTINCT:
                return "DISTINCT";
            case Types.DOUBLE:
                return "DOUBLE";
            case Types.FLOAT:
                return "FLOAT";
            case Types.INTEGER:
                return "INTEGER";
            case Types.LONGVARBINARY:
                return "LONGVARBINARY";
            case Types.LONGVARCHAR:
                return "LONGVARCHAR";
            case Types.NULL:
                return "NULL";
            case Types.NUMERIC:
                return "NUMERIC";
            case Types.REAL:
                return "REAL";
            case Types.OTHER:
                return "OTHER";
            case Types.REF:
                return "REF";
            case Types.SMALLINT:
                return "SMALLINT";
            case Types.STRUCT:
                return "STRUCT";
            case Types.TIME:
                return "TIME";
            case Types.TIMESTAMP:
                return "TIMESTAMP";
            case Types.TINYINT:
                return "TINYINT";
            case Types.VARBINARY:
                return "VARBINARY";
            case Types.VARCHAR:
                return "VARCHAR";
            default:
                return "UNKNOWN";
        }
    }
    
    public static Object getObjectByTypeCoercion(ResultSet resultSet, int index, int dataType) throws SQLException
    {
    	switch (dataType)
    	{
            case Types.TIMESTAMP:
                return resultSet.getTimestamp(index);
            case Types.CLOB:                
                return resultSet.getString(index);
            case Types.BLOB:
                return resultSet.getString(index);
    		default:
                return resultSet.getObject(index);
    	}
    }
}
