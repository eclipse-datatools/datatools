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
package org.eclipse.datatools.sqltools.sqlbuilder.views.source;

public class SQLWordDetector implements org.eclipse.jface.text.rules.IWordDetector {

    /**
     * Constructor.
     */
    public SQLWordDetector() {
        super();
    }

    /**
     * Returns whether the specified character is valid as a subsequent
     * character in a word.
     */
    public boolean isWordPart(char c) {
        // base on SQLTokenizer.getToken(...)
        if (Character.isLetterOrDigit(c) || c == '#' || c == '_' || c == '@' || c == '$')
            return true;

        return false;
    }

    /**
     * Returns whether the specified character is valid as the first character
     * in a word.
     */
    public boolean isWordStart(char c) {
        // base on SQLTokenizer.getToken(...)
        if (Character.isLetter(c) || c == '#' || c == '_' || c == '@' || c == '$')
            return true;

        return false;
    }
}