/*******************************************************************************
 * Copyright (c) 2005 -- 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.sqltools.sqleditor.internal.sql;


import java.util.StringTokenizer;

import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.jface.text.formatter.IFormattingStrategy;



/**
* The formatting strategy that transforms SQL keywords to upper case
*/
public class SQLWordStrategy implements IFormattingStrategy
{
    private ISQLSyntax                                        sqlSyntax;

    /**
   * According to profileName to determine which the database syntax keywords highlighted.
   * 
   * @param _profileName
   */
    public SQLWordStrategy(ISQLSyntax syntax)
    {
        sqlSyntax = syntax;
    }

    /**
   * @see org.eclipse.jface.text.formatter.IFormattingStrategy#formatterStarts(String)
   */
    public void formatterStarts(String initialIndentation)
    {
    }

    /**
   * @see org.eclipse.jface.text.formatter.IFormattingStrategy#format(String, boolean, String, int[])
   */
    public String format(String content, boolean isLineStart, String indentation, int[] positions)
    {
    	if (sqlSyntax == null)
    	{
    		return allToUpper(content);
    	}
        return keyWordsToUpper(content);
    }

    private String allToUpper( String content ) {
        String newContent = content.toUpperCase();
        return newContent;
    }
    
    /**
   * Method keyWordsToUpper.
   * 
   * @param content
   * @return String
   */
    private String keyWordsToUpper(String content)
    {
        StringTokenizer st = new StringTokenizer(content, " \n", true);
        String token = "";
        String newContent = "";
        boolean done;
        while (st.hasMoreTokens())
        {
            token = st.nextToken();
            done = false;
            for (int i = 0; i < sqlSyntax.getAllWords().length; i++)
            {
                String[] sqlWords = (String[]) sqlSyntax.getAllWords()[i];
                for (int j = 0; j < sqlWords.length; j++)
                {
                    if (token.equals(" ") | token.equals("\n"))
                    {
                        break;
                    }
                    if (token.toUpperCase().equals(sqlWords[j]))
                    {
                        token = token.toUpperCase();
                        done = true;
                        break;
                    }
                }
                if (done = true)
                {
                    break;
                }
            }
            newContent = newContent + token;
        }
        return newContent;
    }

    /**
   * @see org.eclipse.jface.text.formatter.IFormattingStrategy#formatterStops()
   */
    public void formatterStops()
    {
    }
}
