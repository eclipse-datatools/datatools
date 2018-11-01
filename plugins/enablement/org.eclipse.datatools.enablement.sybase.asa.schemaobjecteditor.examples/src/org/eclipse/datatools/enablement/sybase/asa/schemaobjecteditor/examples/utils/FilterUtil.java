/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils;

import org.eclipse.ui.internal.misc.StringMatcher;

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
     * @param pattern the pattern to match text against
     * @param value the value to be matched
     * @return
     */
    public static boolean isMatch(String pattern, String value)
    {
        return isMatch(pattern, value, true, false);
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
     * @param pattern the pattern to match text against
     * @param value the value to be matched
     * @param ignoreCase if true, case is ignored
     * @param ignoreWildCards if true, wild cards and their escape sequences are ignored
     *        (everything is taken literally).
     * @return
     */
    public static boolean isMatch(String pattern, String value, boolean ignoreCase, boolean ignoreWildCards)
    {
        if (pattern == null || "".equals(pattern)) //$NON-NLS-1$
        {
            return true;
        }
        StringMatcher matcher = new StringMatcher(pattern, ignoreCase, ignoreWildCards);
        if (matcher.match(value))
        {
            return true;
        }
        StringMatcher matcherWithStart = new StringMatcher(pattern + "*", ignoreCase, ignoreWildCards);//$NON-NLS-1$
        if (matcherWithStart.match(value))
        {
            return true;
        }
        return false;
    }
}
