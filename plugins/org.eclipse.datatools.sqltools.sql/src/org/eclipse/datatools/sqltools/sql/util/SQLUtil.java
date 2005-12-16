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


}
