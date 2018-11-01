/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.util;

import java.sql.Types;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.modelbase.sql.query.helper.DataTypeHelper;
import org.eclipse.datatools.sqltools.sql.reference.IDatatype;

/**
 * Contains various SQL processing utilities.
 * @author Hui Cao
 * 
 */
public class SQLUtil
{
    public static final int NO_QUOTES=0;
    public static final int MATCHING_SINGLE_QUOTES=1;
    public static final int MATCHING_DOUBLE_QUOTES=2;
    public static final int NO_MATCHING_QUOTES=3;

    public static final Pattern ID_PATTERN = Pattern.compile("((\\Q[\\E([^\"]|(\"\"))+\\Q]\\E|[^\\s\"\\Q.\\E]+|\"([^\"]|(\"\"))+\")\\Q.\\E?)");
    public static final Pattern STRING_PATTERN = Pattern.compile("(([^\\s'\"]+)|('([^']|(''))+')|(\"([^\"]|(\"\"))+\"))");
    public static int MAX_NAME_LENGTH_FOR_MESSAGE_DIALOGS = 30;


    /**
     * "objstr" is a string representing a database object. Possibly in following formats: <ll>
     * <li>objname</li>
     * <li>ownername.objname</li>
     * <li>databasename.ownername.objname</li>
     * <li>databasename..objname</li>
     * </ll>
     * 
     * This method will try to figure it out, and return a string array. with the first element be the last segment in
     * "objstr".
     * 
     * In case of invalid objstr, will return null
     * 
     * NOTE: it is allowed to have whitespace in objname/ownername/databasename, in that case, the name is quoted in
     * "'". See SQL grammer for detail.
     * 
     * @param objstr a string identifying a database object.
     * @return string array
     */
    public static String[] parseDatabaseObject(String objstr)
    {
        ArrayList ids = new ArrayList();
        Matcher m = ID_PATTERN.matcher(objstr);
        while (m.find())
        {
            ids.add(0, SQLUtil.unquote(m.group(2)));
        }
        return (String[])ids.toArray(new String[ids.size()]);
    }

    public static int findQuotes(String content)
    {
        //void NullPointerException
        if(content == null)
        {
            return SQLUtil.NO_QUOTES;
        }
        else if(content.indexOf('\'') < 0 && content.indexOf('\"') < 0)
        {
            return SQLUtil.NO_QUOTES;
        }
        else
        {
            if(content.indexOf("'") == 0 && content.lastIndexOf("'") == content.length() - 1)
            {
                return SQLUtil.MATCHING_SINGLE_QUOTES;
            }
            else if(content.indexOf("\"") == 0 && content.lastIndexOf("\"") == content.length() - 1)
            {
                return SQLUtil.MATCHING_DOUBLE_QUOTES;
            }
            else
            {
                return SQLUtil.NO_MATCHING_QUOTES;
            }
        }
    }

    /**
     * Surrounds the input string with quoteChar and doubles every quoteChar inside the string.
     * 
     * @param in the input string
     * @param quoteChar quotation character, usually it's ' or "
     * @return quoted string
     */
    public static String quote(String in, char quoteChar)
    {
        StringBuffer buffer = new StringBuffer(in.length() + 8);
        buffer.append(quoteChar);
        int len = in.length();
        for (int i = 0; i < len; i++)
        {
            char c = in.charAt(i);
            if (c == quoteChar)
            buffer.append(c);
            buffer.append(c);
        }

        buffer.append(quoteChar);
        return buffer.toString();
    }
    
    /**
     * surround content with quoteMark and double every quoteMark inside content
     * 
     * @param content
     * @param quoteMark
     * @return
     */
    public static String quote(String content, String quoteMark)
    {
        return quoteMark + content.replaceAll(quoteMark, quoteMark + quoteMark) + quoteMark;
    }

    /**
     * Removes the surrounding quotation mark (' or ") and restores 2 successive quotation marks to 1.
     * 
     * @param quoted quoted string
     * @return unquoted string
     */
    public static String unquote(String quoted)
    {
        String content = quoted;
        if (quoted.indexOf("'") == 0 && quoted.lastIndexOf("'") == quoted.length() - 1)
        {
            content = quoted.substring(1, quoted.length() - 1).replaceAll("''", "'");
        }
        else if (quoted.indexOf("\"") == 0 && quoted.lastIndexOf("\"") == quoted.length() - 1)
        {
            content = quoted.substring(1, quoted.length() - 1).replaceAll("\"\"", "\"");
        }
        return content;
    }


    /**
     * Describe the sql string by truncating the characters after <code>length-3</code> and appending ellipses if it's longer than <code>length</code>.
     * @param sql
     * @param length
     * @return
     */
    public static String describeSQL(String sql, int length)
    {
        sql = sql.trim().replaceAll(System.getProperty("line.separator"), " ");
        sql = sql.replaceAll("\t", " ");
        if (sql.length() <= length)
        {
            return sql;
        }
        return sql.substring(0, length - 3).concat("...");
    }

    private static final char[] HexChars = 
    {
        '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    }
    ;

    /**
     * @param bytes The array of bytes to convert to ASCII hex form.
     * @return	    An ASCII hexadecimal numeric string representing the
     * 		    specified array of bytes.
     */
    public static final String toHexString(byte[] bytes) 
    {
        StringBuffer sb = new StringBuffer();
        sb.append("0x");
        int i;
        for (i=0; i < bytes.length; i++) 
        {
            sb.append(HexChars[(bytes[i] >> 4) & 0xf]);
            sb.append(HexChars[bytes[i] & 0xf]);
        }
        return new String(sb);
    }
    
    public static String[] splitDotStr(String input)
    {
        int i = 0, count = 0;
        while (i < input.length())
        {
            if (input.charAt(i) == '.')
            {
                count++;
            }
            i++;
        }

        String[] tokens = new String[count + 1];

        i = 0;
        int start = 0, end = 0, j = 0;
        while (i < input.length())
        {
            if (input.charAt(i) == '.')
            {
                if (start < i)
                {
                    tokens[j] = input.substring(start, i);
                }
                else
                {
                    tokens[j] = null;
                }
                start = i + 1;
                j++;
            }
            i++;
        }
        if (start <= i - 1)
        {
            tokens[j] = input.substring(start, i);
        }
        else
        {
            //Hui Cao: change "" to null to be consistent with previous tokens
            tokens[j] = null; //$NON-NLS-1$
        }

        return tokens;
    }

    private static String[]  _allCurrencySymbols;
	/**
     * Returns all the currency symbols
     * 
     * @return
     */
    public synchronized static String[] getAvaiableCurrencySymbols()
    {
        if (_allCurrencySymbols == null)
        {
            Locale[] locals = Locale.getAvailableLocales();
            ArrayList list = new ArrayList();
            for (int i = 0; i < locals.length; i++)
            {
                list.add(NumberFormat.getInstance(locals[i]).getCurrency().getSymbol(locals[i]));
            }
            _allCurrencySymbols = (String[]) list.toArray(new String[list.size()]);
            return _allCurrencySymbols;
        }
        else
        {
            return _allCurrencySymbols;
        }
    }

    /**
     * Returns whether the 2 Strings are equal by ignoring the surrounding quotes
     * @param s1
     * @param s2
     * @param caseSensitive whether to consider case
     * @return
     */
    public static boolean equalsIgnoreQuote(String s1, String s2, boolean caseSensitive)
    {
        if (caseSensitive)
        {
            boolean e = s1.equals(s2);
            if (!e)
            {
                s1 = unquote(s1);
                s2 = unquote(s2);
                e = s1.equals(s2);
            }
            return e;
        }
        else
        {
            boolean e = s1.equalsIgnoreCase(s2);
            if (!e)
            {
                if (s1.equals(unquote(s2)) || s2.equals(unquote(s1)))
                {
                    return true;
                }
                s1 = unquote(s1);
                s2 = unquote(s2);
                e = s1.equalsIgnoreCase(s2);
            }
            return e;
        }
    }

    public static boolean isBinaryType(IDatatype datatype)
    {
        if(datatype.isUDT())
        {
            return isBinaryType(datatype.getBaseType().toString());
        }
        else
        {
            return isBinaryType(datatype.toString());
        }

    }

    public static boolean isBinaryType(String datatype)
    {
        String strType = datatype;
        int position = strType.indexOf('(');
        if(position > 0)
        {
            strType = strType.substring(0,position);
        }
        int _sqlDataType = convert2SQLType(strType);
        return _sqlDataType == Types.BINARY || _sqlDataType == Types.LONGVARBINARY || _sqlDataType == Types.VARBINARY;
    }


    public static boolean isNumericType(String datatype)
    {
        int position = datatype.indexOf('(');
        if(position > 0)
        {
            datatype = datatype.substring(0,position);
        }
        int _sqlDataType = convert2SQLType(datatype);
        return _sqlDataType == Types.BIGINT || _sqlDataType == Types.DECIMAL || _sqlDataType == Types.DOUBLE|| _sqlDataType == Types.FLOAT
            || _sqlDataType == Types.INTEGER|| _sqlDataType == Types.NUMERIC|| _sqlDataType == Types.REAL|| _sqlDataType == Types.SMALLINT
            || _sqlDataType == Types.TINYINT;
    }

    public static int convert2SQLType(String datatype) {
    	return DataTypeHelper. getJDBCTypeForNamedType(datatype);
	}

	public static boolean isNumericType(int datatype)
    {
        return datatype == Types.BIGINT || datatype == Types.DECIMAL || datatype == Types.DOUBLE|| datatype == Types.FLOAT
            || datatype == Types.INTEGER|| datatype == Types.NUMERIC|| datatype == Types.REAL|| datatype == Types.SMALLINT
            || datatype == Types.TINYINT;
    }

    public static boolean isNumericType(IDatatype datatype)
    {
        if(datatype.isUDT())
        {
            return isNumericType(datatype.getBaseType().toString());
        }
        else
        {
            return isNumericType(datatype.toString());
        }
    }

    public static boolean isStringType(String datatype)
    {
        String strType = datatype;
        int position = strType.indexOf('(');
        if(position > 0)
        {
            strType = strType.substring(0,position);
        }
        //TODO test all types
        int _sqlDataType = convert2SQLType(strType);
        return isStringType(_sqlDataType);
    }

    public static boolean isStringType(IDatatype datatype)
    {
        if(datatype.isUDT())
        {
            return isStringType(datatype.getBaseType().toString());
        }
        else
        {
            return isStringType(datatype.toString());
        }
    }

    public static boolean isStringType(int sqlType)
    {
        return sqlType == Types.CHAR || sqlType == Types.VARCHAR || sqlType == Types.LONGVARCHAR
            || sqlType == Types.DATE || sqlType == Types.TIME || sqlType == Types.TIMESTAMP;
    }

}
