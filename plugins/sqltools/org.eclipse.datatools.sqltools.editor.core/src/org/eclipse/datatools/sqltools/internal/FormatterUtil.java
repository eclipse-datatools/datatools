/**
 * Created on 2004-9-28
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.internal;

/**
 * @author Hui Cao
 *  
 */
public class FormatterUtil
{

    /**
     * Indent char is a space char but not a line delimiters.
     * <code>== Character.isWhitespace(ch) && ch != '\n' && ch != '\r'</code>
     */
    public static boolean isIndentChar(char ch)
    {
        return Character.isWhitespace(ch) && !isLineDelimiterChar(ch);
    }

    /**
     * Line delimiter chars are '\n' and '\r'.
     */
    public static boolean isLineDelimiterChar(char ch)
    {
        return ch == '\n' || ch == '\r';
    }

    /**
     * Returns the indent of the given string.
     * 
     * @param line the text line
     * @param tabWidth the width of the '\t' character.
     */
    public static int computeIndent(String line, int tabWidth)
    {
        int result = 0;
        int blanks = 0;
        int size = line.length();
        for (int i = 0; i < size; i++)
        {
            char c = line.charAt(i);
            if (c == '\t')
            {
                result++;
                blanks = 0;
            }
            else if (isIndentChar(c))
            {
                blanks++;
                if (blanks == tabWidth)
                {
                    result++;
                    blanks = 0;
                }
            }
            else
            {
                return result;
            }
        }
        return result;
    }

    /**
     * This method is used for removing the double quotation marks
     * from the string which starts and ends with double quotation marks.
     * For example:
     * "string" --> string
     * @param string
     * @return
     */
    public static String removeDoubleQuotation(String string)
    {
        if(string.charAt(0) == '"' && string.charAt(string.length()-1) == '"')
        {
            return string.substring(1, string.length()-1);
        }
        return string;
    }
}
