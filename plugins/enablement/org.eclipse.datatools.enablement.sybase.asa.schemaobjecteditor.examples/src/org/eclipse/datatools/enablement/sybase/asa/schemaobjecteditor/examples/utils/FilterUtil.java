/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils;

import java.util.regex.Pattern;

/**
 * Util Class for Filter
 * 
 * @author Wan Hui
 */
public class FilterUtil
{
    /**
     * Checks the whether the value matches the pattern casesensitive and not ignore wildcards     * 
     * This util method takes in a String object that is a simple 
     * pattern which may contain '*' for 0 and many characters and
     * '?' for exactly one character.  
     *
     * Literal '*' and '?' characters must be escaped in the pattern 
     * e.g., "\*" means literal "*", etc.
     *
     * Escaping any other character (including the escape character itself), 
     * just results in that character in the pattern.
     * e.g., "\a" means "a" and "\\" means "\"
     *
     * If invoking the StringMatcher with string literals in Java, don't forget
     * escape characters are represented by "\\".
     * 
     * @param patternString the pattern to match text against
     * @param value the value to be matched
     * @return
     */
    public static boolean isMatch(String patternString, String value)
    {
        return isMatch(patternString, value, true, false);
    }

    /**
     * 
     * This util method takes in a String object that is a simple 
     * pattern which may contain '*' for 0 and many characters and
     * '?' for exactly one character.  
     *
     * Literal '*' and '?' characters must be escaped in the pattern 
     * e.g., "\*" means literal "*", etc.
     *
     * Escaping any other character (including the escape character itself), 
     * just results in that character in the pattern.
     * e.g., "\a" means "a" and "\\" means "\"
     *
     * If invoking the StringMatcher with string literals in Java, don't forget
     * escape characters are represented by "\\".
     * 
     * @param patternString the pattern to match text against
     * @param value the value to be matched
     * @param ignoreCase if true, case is ignored
     * @param ignoreWildCards if true, wild cards and their escape sequences are ignored
     *        (everything is taken literally).
     * @return
     */
    public static boolean isMatch(String patternString, String value, boolean ignoreCase, boolean ignoreWildCards)
    {
        if (patternString == null || "".equals(patternString)) //$NON-NLS-1$
        {
            return true;
        }
        Pattern pattern = getPattern(patternString, ignoreCase, ignoreWildCards);
        if (pattern.matcher(value).matches())
        {
            return true;
        }
        pattern = getPattern(patternString + "*", ignoreCase, ignoreWildCards); //$NON-NLS-1$
        if (pattern.matcher(value).matches())
        {
            return true;
        }
        return false;
    }

    private static Pattern getPattern(String patternString, boolean ignoreCase, boolean ignoreWildCards)
    {
        Pattern pattern;
        if (ignoreWildCards && ignoreCase) {
            pattern = Pattern.compile(patternString, Pattern.LITERAL | Pattern.CASE_INSENSITIVE);
        } else if (ignoreWildCards) {
            pattern = Pattern.compile(patternString, Pattern.LITERAL);
        } else if (ignoreCase) {
            pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(patternString);
        }
        return pattern;
    }
}
