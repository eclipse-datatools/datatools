/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.core.tableviewer;

import java.sql.Types;

/**
 * 
 * @author lihuang
 * 
 */
public class DataSerializer
{

    protected static final int[] NEEDS_QUOTE =
    {
        Types.BIT, Types.BLOB, Types.CLOB, Types.CHAR, Types.VARCHAR, Types.LONGVARCHAR, Types.BINARY, Types.VARBINARY,
            Types.LONGVARBINARY, Types.DATE, Types.TIME, Types.TIMESTAMP
    }
    ;

    public static String serialize(Object val, int type)
    {
        if (val == null)
        {
            return null;
        }

        if (type == Types.TINYINT)
        {
            return val.toString();
        }
        else if (type == Types.SMALLINT)
        {
            return val.toString();
        }
        else if (type == Types.INTEGER)
        {
            return val.toString();
        }
        else if (type == Types.BIGINT)
        {
            return val.toString();
        }
        else if (type == Types.REAL)
        {
            return val.toString();
        }
        else if (type == Types.FLOAT)
        {
            return val.toString();
        }
        else if (type == Types.DOUBLE)
        {
            return val.toString();
        }
        else if (type == Types.DECIMAL)
        {
            return val.toString();
        }
        else if (type == Types.NUMERIC)
        {
            return val.toString();
        }
        else if (type == Types.BIT)
        {
            return val.toString();
        }
        else if (type == Types.CHAR)
        {
            return val.toString();
        }
        else if (type == Types.VARCHAR)
        {
            return val.toString();
        }
        else if (type == Types.LONGVARCHAR)
        {
            return val.toString();
        }
        else if (type == Types.BINARY)
        {
            return serializeBytes((byte[]) val);
        }
        else if (type == Types.VARBINARY)
        {
            return serializeBytes((byte[]) val);
        }
        else if (type == Types.LONGVARBINARY)
        {
            return serializeBytes((byte[]) val);
        }
        else if (type == Types.DATE)
        {
            return val.toString();
        }
        else if (type == Types.TIME)
        {
            return val.toString();
        }
        else if (type == Types.TIMESTAMP)
        {
            return val.toString();
        }
        else if (type == Types.CLOB)
        {
            return val.toString();
        }
        else if (type == Types.BLOB)
        {
            return serializeBytes((byte[]) val);
        }
        else if (type == Types.ARRAY || type == Types.REF || type == Types.STRUCT || type == Types.JAVA_OBJECT
        || type == Types.OTHER)
        {
            return val.toString();
        }
        else
        {
            return val.toString();
        }
    }

    public static String write(Object val, int type, String stringDelim)
    {
        String s = serialize(val, type);
        if (s != null && needsQuote(type))
        {
            s = stringDelim + doubleStringDelim(s, stringDelim) + stringDelim;
        }
        return s;
    }

    protected static String serializeBytes(byte[] bytes)
    {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i)
        {
            int n = bytes[i];
            if (n < 0)
            {
                n += 256;
            }
            sb.append(Integer.toString(n / 16, 16));
            sb.append(Integer.toString(n % 16, 16));
        }
        return "0x"+sb.toString();
    }

    public static boolean needsQuote(int type)
    {
        for (int i = 0; i < NEEDS_QUOTE.length; ++i)
        {
            if (NEEDS_QUOTE[i] == type)
            {
                return true;
            }
        }
        return false;
    }

    public static String doubleStringDelim(String s, String delim)
    {
        if (delim == null || delim.length() == 0)
        {
            return s;
        }

        int i = s.indexOf(delim);
        if (i != -1)
        {
            StringBuffer sb = new StringBuffer(s);
            int j;
            for (; i != -1; i = sb.toString().indexOf(delim, j))
            {
                sb = sb.insert(i, delim);
                j = i + 2 * delim.length();
            }
            return sb.toString();
        }
        else
        {
            return s;
        }
    }
}
