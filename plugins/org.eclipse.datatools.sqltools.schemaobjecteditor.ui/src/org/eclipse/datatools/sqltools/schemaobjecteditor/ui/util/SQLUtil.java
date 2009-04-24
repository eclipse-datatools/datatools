/**
 * Created on 2004-7-23
 * 
 * Copyright Sybase, Inc. All rights reserved.
 */
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util;

public class SQLUtil
{
	
	/**
     * remove the surrounding quotation mark and restore 2 successive quotation marks to 1
     * 
     * @param quoted
     * @return
     */
    public static String unquote(String quoted)
    {
        String content = quoted;
        if (quoted.indexOf("'") == 0 && quoted.lastIndexOf("'") == quoted.length() - 1 && quoted.length()>1)
        {
            content = quoted.substring(1, quoted.length() - 1).replaceAll("''", "'");
        }
        else if (quoted.indexOf("\"") == 0 && quoted.lastIndexOf("\"") == quoted.length() - 1 && quoted.length()>1)
        {
            content = quoted.substring(1, quoted.length() - 1).replaceAll("\"\"", "\"");
        }
        else if (quoted.indexOf("[") == 0 && quoted.lastIndexOf("]") == quoted.length() - 1 && quoted.length()>1)
        {
            content = quoted.substring(1, quoted.length() - 1);
        }
        return content;
    }
}
