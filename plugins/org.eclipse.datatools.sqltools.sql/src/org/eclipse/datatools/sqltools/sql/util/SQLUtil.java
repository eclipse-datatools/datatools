/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.util;

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
}
