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
package org.eclipse.datatools.enablement.sybase.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Hui Cao
 * 
 */
public abstract class AbstractQuickSQLParser
{
    public static final int CREATE_PROC_HEADER_PATTERN = 1;
    public static final int CREATE_FUNC_HEADER_PATTERN = 2;
    public static final int CREATE_TRIGGER_HEADER_PATTERN = 3;
    public static final int CREATE_EVENT_HEADER_PATTERN = 4;
    
    private static String SEPARATOR = "(\r\n|\r|\n|\u0085|\u2028|\u2029)";
    private static Pattern p = Pattern.compile("(.*"+SEPARATOR+")");
    
    
    /**
     * Matches the input string against the given pattern and returns the matching
     * strings. 
     * @param input string to be parsed
     * @param pattern pattern constant
     * @return null or the matching strings
     */
    public abstract boolean match(String input, int pattern);
    
    /**
     * 
     * @param input
     * @param tokens
     * @return start and end index: start points to the first character of the token; end points to the last + 1 character.
     */
    public int[] find(String input, String[] tokens)
    {
        String[][] ts = new String[tokens.length][1];
        for (int i = 0; i < tokens.length; i++)
        {
            ts[i][0] = tokens[i];
        }
        return find(input, ts);
    }

    public abstract int[] find(String input, String[][] tokens);
    
    public abstract Token[] getTokens(String input, String[][] tokens);
    
    public abstract String[][] getParameters(String input);

    public abstract String[] getDatatypeInfo(String input);
    
    /**
     * Gets the start index of a Token for a String input. Clients of this API should create a new parser instead of
     * using the shared instance to avoid concurrent access.
     */
     public int getStartIndex(Token t)
     {
         //calculate index
         Matcher m = p.matcher(getInput());
         int i = 1;
         int count = 0;
         while (m.find() && i < t.beginLine)
         {
             i ++;
             count = m.end(1);//Returns the index of the last character matched, plus one. (number of characters)
         }
         return count + t.beginColumn - 1;
     }

     /**
     * Gets the end index of a Token for a String input. Clients of this API should create a new parser instead of
     * using the shared instance to avoid concurrent access.
     */
     public int getEndIndex(Token t)
     {
         //calculate index
         Matcher m = p.matcher(getInput());
         int i = 1;
         int count = 0;
         while (m.find() && i < t.endLine)
         {
             i ++;
             count = m.end(1);//Returns the index of the last character matched, plus one.
         }
         return count + t.endColumn;
     }
     
     public abstract String getInput();

}
