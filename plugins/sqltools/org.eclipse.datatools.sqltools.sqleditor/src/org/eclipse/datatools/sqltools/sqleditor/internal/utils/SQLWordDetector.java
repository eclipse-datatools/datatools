/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.utils;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * This class determines whether a given character is valid as part of an SQL keyword 
 * in the current context.
 */
public class SQLWordDetector implements IWordDetector {

    /**
     * Returns whether the specified character is valid as the first 
     * character in a word.
     * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
     */
    public boolean isWordStart( char c ) {
//        String[] reservedWords = SQLCodeScanner.getSQLKeywords();
//        for (int i = 0; i < reservedWords.length; i++) {
//            if ( (reservedWords[i].charAt(0) == c)
//              || (reservedWords[i].toLowerCase().charAt(0) == c) ) {
//                return true;
//            }
//        }   
//
//        String[] datatypes = SQLCodeScanner.getSQLDatatypes();
//        for (int i = 0; i < datatypes.length; i++) {
//            if ( (datatypes[i].charAt(0) == c)
//              || (datatypes[i].toLowerCase().charAt(0) == c) ) {
//                return true;
//            }
//        }
//
//        return false;
        if (Character.isJavaIdentifierStart(c))
        {
            return true;
        }
        if (c == '@')
        {
            return true;
        }
        return false;

    }

    /**
     * Returns whether the specified character is valid as a subsequent character 
     * in a word.
     * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
     */
    public boolean isWordPart( char c ) {
//        String[] keywords = SQLCodeScanner.getSQLKeywords();
//        for (int i = 0; i < keywords.length; i++) {
//            if ( (keywords[i].indexOf(c) != -1)
//              || (keywords[i].toLowerCase().indexOf(c) != -1) ) {
//                return true;
//            }
//        }
//
//        String[] datatypes = SQLCodeScanner.getSQLDatatypes();
//        for (int i = 0; i < datatypes.length; i++) {
//            if ( (datatypes[i].indexOf(c) != -1)
//              || (datatypes[i].toLowerCase().indexOf(c) != -1) ) {
//                return true;
//            }
//        }
    	if (Character.isJavaIdentifierPart(c))
        {
            return true;
        }
        return false;
    }

} // end class
