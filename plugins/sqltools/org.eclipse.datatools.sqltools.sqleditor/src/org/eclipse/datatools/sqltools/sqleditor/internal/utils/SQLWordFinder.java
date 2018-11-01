/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.utils;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;

/**
 * 
 * @author Hui Cao
 *  
 */
public class SQLWordFinder
{

    /**
     * Find a SQL word (including variable) at the offset in the document
     * 
     * @param document
     * @param offset
     * @return
     */
    public static IRegion findWord(IDocument document, int offset)
    {

        int start = -1;
        int end = -1;

        start = getWordStartOffset(document.get(), offset);
        end = getWordEndOffset(document.get(), offset);

        if (start > -1 && end > -1)
        {
            if (start == offset && end == offset)
            {
                return new Region(offset, 0);
            }
            else if (start == offset)
            {
                return new Region(start, end - start);
            }
            else
            {
                return new Region(start + 1, end - start - 1);
            }
        }

        return null;
    }

    public static int getWordStartOffset(String text, int startIndex)
    {
        if (text == null || startIndex >= text.length())
        {
            return -1;
        }
        for (int offset = startIndex; offset >= 0; offset--)
        {
            char c = text.charAt(offset);
            if (isWhiteSpace(c))
            {
                return offset;
            }
        }
        return -1;
    }

    public static int getWordEndOffset(String text, int startIndex)
    {
        int pos = startIndex;
        char c;
        int length = text.length();

        while (pos < length)
        {
            c = text.charAt(pos);
            if (isWhiteSpace(c))
            {
                break;
            }
            ++pos;
        }

        return pos;
    }

    public static boolean isWhiteSpace(char c)
    {
        return Character.isWhitespace(c) || c == '(' || c == ')' || c == ',' || c == ';' || c == '\n' || c == '\r'
                || c == '=' || c == '>' || c == '<' || c == '+' || c == '-' || c == '*' || c == '/' 
                || c == '%' || c == '^' || c == '|' || c == '&'; 
    }

}
