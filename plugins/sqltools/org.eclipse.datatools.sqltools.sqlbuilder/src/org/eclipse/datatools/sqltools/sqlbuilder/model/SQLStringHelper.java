/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.util.Vector;

/**
 */
public class SQLStringHelper {

    public static final String lineSeparator = System.getProperties().getProperty("line.separator");
    private static final String ALPHANUMERIC_UNDERSCORE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";

    public SQLStringHelper() {
    }

    /**
     * This removes unnecessary blanks from an SQLStatement string
     * and returns the updated string.  Leaves blanks in literal values.
     */

    public static String trimBlanks(Object input) {
        String result = input.toString();

        // Get rid of any '\n' we generated
        result = change(result, System.getProperties().getProperty("line.separator"), " ");

        // Prepare to remove all unnecessary blanks.  First save the literal values and replace them with
        // a tag.  (We don't want to strip blanks in literal values)
        Vector literalVector = new Vector();
        String tempPattern = "#TAG#";
        result = stringReplace(result, "'", "'", tempPattern, literalVector);

        while (result.indexOf("  ") != -1) {
            result = change(result, "  ", " ");
        }

        result = stringReplace(result, tempPattern, literalVector);

        return result;
    }

    /* Searches for the given pattern in the given string and replaces it with a tag.  The matching patterns
     * which are found are stored in the litVector.
     * The pattern is specified by the startDelimiter and endDelimiter.
     * Returns the modified string.
     */
    public static String stringReplace(String string, String startDelimiter, String endDelimiter, String tag, Vector litVector) {
        int startSearchIndex = 0;
        int startLitIndex;
        int endLitIndex;

        while (string.indexOf(startDelimiter, startSearchIndex) != -1) {
            startLitIndex = string.indexOf(startDelimiter, startSearchIndex);
            endLitIndex = string.indexOf(endDelimiter, startLitIndex + 1);
            if (endLitIndex == -1) // defect 266757
                endLitIndex = startLitIndex;

            // Replace literal value with tag
            char[] litValue = new char[endLitIndex + 1 - startLitIndex];

            string.getChars(startLitIndex, endLitIndex + 1, litValue, 0);
            litVector.addElement(litValue);

            StringBuffer sb = new StringBuffer(string);
            sb = sb.replace(startLitIndex, endLitIndex + 1, tag);
            string = new String(sb);

            startSearchIndex = startLitIndex + tag.length();
        }

        return string;
    }

    /* Searches for the given pattern in the given string and replaces it with the first element of the given vector.
     * Once elements of the vector has been used up, all remaining patterns which still exists are left alone.
     * Returns the modified string.
     */
    public static String stringReplace(String string, String pattern, Vector newVector) {
        int startSearchIndex = 0;
        int startLitIndex;
        int endLitIndex;
        boolean exit = false;

        int length = pattern.length();
        String startDelimiter = pattern.substring(0, 1);
        String endDelimiter = pattern.substring(length - 1, length);

        while (string.indexOf(startDelimiter, startSearchIndex) != -1 && !exit) {
            if (newVector.size() >= 1) {
                startLitIndex = string.indexOf(startDelimiter, startSearchIndex);
                endLitIndex = string.indexOf(endDelimiter, startLitIndex + 1);

                // Did we find an exact match
                char[] litValue = new char[endLitIndex + 1 - startLitIndex];
                string.getChars(startLitIndex, endLitIndex + 1, litValue, 0);

                // Compare our pattern to the substring
                if (pattern.equals(new String(litValue))) {
                    String tag = new String((char[]) newVector.firstElement());
                    newVector.remove(0);

                    StringBuffer sb = new StringBuffer(string);
                    sb = sb.replace(startLitIndex, endLitIndex + 1, tag);
                    string = new String(sb);

                    startSearchIndex = startLitIndex + tag.length();
                }
                else {
                    // Not a match.  Move our search index to the next character
                    startSearchIndex++;
                }
            }
            else {
                // Problem, not enough values in newVector to continue replacing
                exit = true;
            }
        }

        return string;
    }

    //
    // return true if all chars in srcString are in {a...z,} or {A...Z} {0...9} OR {_}

    public static boolean isAlphanumericOrUnderscore(String srcString) {
        return (lastIndexOfAnyBut(srcString, ALPHANUMERIC_UNDERSCORE) == -1);
    }

    public static int lastIndexOfAnyBut(String srcString, String validString) {
        int result = -1;
        int srcLen = srcString.length();

        // walk backward to find if a char within srcString is in validString
        for (int i = srcLen - 1; i >= 0; i--) {
            // not found, stop it
            if (validString.indexOf(srcString.charAt(i)) == -1) {
                result = i;
                break;
            }

        }

        return result;
    }

    // change all occurrences of oldPat to newPat
    private static String change(String in, String oldPat, String newPat) {
        if (oldPat.length() == 0)
            return in;
        if (oldPat.length() == 1 && newPat.length() == 1)
            return in.replace(oldPat.charAt(0), newPat.charAt(0));

        int lastIndex = 0;
        int newIndex = 0;
        StringBuffer newString = new StringBuffer();
        for (;;) {
            newIndex = in.indexOf(oldPat, lastIndex);
            if (newIndex != -1) {
                newString.append(in.substring(lastIndex, newIndex) + newPat);
                lastIndex = newIndex + oldPat.length();
            }
            else {
                newString.append(in.substring(lastIndex));
                break;
            }
        }
        return newString.toString();
    }

}