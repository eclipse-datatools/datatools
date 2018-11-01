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
package org.eclipse.datatools.enablement.sybase.util;
/**
 * 
 * @author Hui Cao
 * 
 */
public class SQLUtil
{

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
     * 
     * @param statement
     * @return the expression of check constraint
     */
    public static String parseSearchStatement(String statement)
    {
        String defStr = "";
        if (statement != null)
        {
            defStr = statement.trim();
            int leftParenthesis = defStr.indexOf("(");
            int righParenthesis = defStr.lastIndexOf(")");
            if (leftParenthesis >= 0 && righParenthesis > leftParenthesis)
            {
                defStr = defStr.substring(leftParenthesis + 1, righParenthesis);
            }
        }
        return defStr.trim();
    }
}
