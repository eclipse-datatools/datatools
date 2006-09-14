/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.core.tableviewer;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

/**
 * 
 * @author lihuang
 * 
 */
public class DataDeserializer
{

    public static Object deserialize(String val, int type)
    {
        if (val == null)
        {
            return null;
        }

        if (type == Types.TINYINT)
        {
            return new Byte(val);
        }
        else if (type == Types.SMALLINT)
        {
            return new Short(val);
        }
        else if (type == Types.INTEGER)
        {
            return new Integer(val);
        }
        else if (type == Types.BIGINT)
        {
            return new Long(val);
        }
        else if (type == Types.REAL)
        {
            return new Float(val);
        }
        else if (type == Types.FLOAT)
        {
            return new Double(val);
        }
        else if (type == Types.DOUBLE)
        {
            return new Double(val);
        }
        else if (type == Types.DECIMAL)
        {
            return new BigDecimal(val);
        }
        else if (type == Types.NUMERIC)
        {
            return new BigDecimal(val);
        }
        else if (type == Types.BIT)
        {
            if("1".equals(val))
            {
                return new Boolean(true);
            }
            if("0".equals(val))
            {
                return new Boolean(false);
            }
            return new Boolean(val);
        }
        else if (type == Types.CHAR)
        {
            return val;
        }
        else if (type == Types.VARCHAR)
        {
            return val;
        }
        else if (type == Types.LONGVARCHAR)
        {
            return val;
        }
        else if (type == Types.BINARY)
        {
            return deserializeBytes(val);
        }
        else if (type == Types.VARBINARY)
        {
            return deserializeBytes(val);
        }
        else if (type == Types.LONGVARBINARY)
        {
            return deserializeBytes(val);
        }
        else if (type == Types.DATE)
        {
            return java.sql.Date.valueOf(val);
        }
        else if (type == Types.TIME)
        {
            return Time.valueOf(val);
        }
        else if (type == Types.TIMESTAMP)
        {
            return Timestamp.valueOf(val);
        }
        else if (type == Types.CLOB)
        {
            return val;
        }
        else if (type == Types.BLOB)
        {
            return deserializeBytes(val);
        }
        else if (type == Types.ARRAY || type == Types.REF || type == Types.STRUCT || type == Types.JAVA_OBJECT
        || type == Types.OTHER)
        {
            return null;
        }
        else
        {
            return null;
        }
    }

    protected static byte[] deserializeBytes(String s)
    {
        if(s.startsWith("0x"))
        {
            s=s.replaceFirst("0x","");
        }
        if (s.length() % 2 == 1)
        {
            throw new IllegalArgumentException(Messages.DataDeserializer_invalid_binary_data);  
        }

        byte[] bytes = new byte[s.length() / 2];
        for (int i = 0; i < bytes.length; ++i)
        {
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }
}
