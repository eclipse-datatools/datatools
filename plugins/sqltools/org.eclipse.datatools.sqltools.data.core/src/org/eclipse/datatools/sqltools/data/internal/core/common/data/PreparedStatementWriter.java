/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.core.common.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;



public class PreparedStatementWriter {

    public static void write(PreparedStatement pst, int column, int type, Object val) throws SQLException, IOException
    {        
        if (val==null) {
            if (type==Types.DISTINCT)
                pst.setString(column+1, null);
            else
                pst.setNull(column+1, type);
            return;
        }
        
        if (type==Types.OTHER || type==DataCorePlugin.Types_SQLXML)
        	pst.setString(column+1, val.toString());
        else if (type==Types.CLOB)
            pst.setCharacterStream(column+1, new StringReader((String)val), ((String)val).length());
        else if (type==Types.BLOB)
            pst.setBinaryStream(column+1, new ByteArrayInputStream((byte[])val), ((byte[])val).length);
        else if (val instanceof Short)
            pst.setShort(column+1, ((Short)val).shortValue());
        else if (type == Types.BIGINT)
        	pst.setLong(column+1, ((BigInteger)val).longValue());
        else
            pst.setObject(column+1, val);
        

       
//        if (type==Types.TINYINT)
//            pst.setByte(column+1, ((Byte)val).byteValue());
//        else if (type==Types.SMALLINT)
//            pst.setShort(column+1, ((Short)val).shortValue());
//        else if (type==Types.INTEGER)
//            pst.setInt(column+1, ((Integer)val).intValue());
//        else if (type==Types.BIGINT)
//            pst.setLong(column+1, ((Long)val).longValue());
//        else if (type==Types.REAL)
//            pst.setFloat(column+1, ((Float)val).floatValue());
//        else if (type==Types.FLOAT)
//            pst.setDouble(column+1, ((Double)val).doubleValue());
//        else if (type==Types.DOUBLE)
//            pst.setDouble(column+1, ((Double)val).doubleValue());
//        else if (type==Types.DECIMAL)
//            pst.setBigDecimal(column+1, (BigDecimal)val);
//        else if (type==Types.NUMERIC)
//            pst.setBigDecimal(column+1, (BigDecimal)val);
//        else if (type==Types.BIT)
//            pst.setBoolean(column+1, ((Boolean)val).booleanValue());
//        else if (type==Types.CHAR)
//            pst.setString(column+1, (String)val);
//        else if (type==Types.VARCHAR)
//            pst.setString(column+1, (String)val);
//        else if (type==Types.LONGVARCHAR)
//            pst.setString(column+1, (String)val);
//        else if (type==Types.BINARY)
//            pst.setBytes(column+1, (byte[])val);
//        else if (type==Types.VARBINARY)
//            pst.setBytes(column+1, (byte[])val);
//        else if (type==Types.LONGVARBINARY)
//            pst.setBytes(column+1, (byte[])val);
//        else if (type==Types.DATE)
//            pst.setDate(column+1, (Date)val);
//        else if (type==Types.TIME)
//            pst.setTime(column+1, (Time)val);
//        else if (type==Types.TIMESTAMP)
//            pst.setTimestamp(column+1, (Timestamp)val);
//        else if (type==Types.CLOB)
//            pst.setCharacterStream(column+1, new StringReader((String)val), ((String)val).length());
//        else if (type==Types.BLOB)
//            pst.setBinaryStream(column+1, new ByteArrayInputStream((byte[])val), ((byte[])val).length);
//        else if (type==Types.ARRAY || type==Types.REF || type==Types.STRUCT
//                || type==Types.JAVA_OBJECT || type==Types.OTHER)
//            pst.setString(column+1, null);
//        else
//            pst.setString(column+1, null);

    }
  

}
