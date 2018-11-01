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

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
public class CaseControlledWordRule extends WordRule {

    private boolean caseSensitive = false;
    private StringBuffer fBuffer = new StringBuffer();

    /**
     * CaseControlledWordRule constructor comment.
     * 
     * @param detector
     *            org.eclipse.jface.text.rules.IWordDetector
     */
    public CaseControlledWordRule(IWordDetector detector) {
        super(detector);
    }

    /**
     * Constructor.
     * 
     * @param detector
     *            org.eclipse.jface.text.rules.IWordDetector
     * @param defaultToken
     *            org.eclipse.jface.text.rules.IToken
     */
    public CaseControlledWordRule(IWordDetector detector, IToken defaultToken) {
        super(detector, defaultToken);
    }

    /**
     *  
     * @param detector
     *            org.eclipse.jface.text.rules.IWordDetector
     * @param defaultToken
     *            org.eclipse.jface.text.rules.IToken
     * @param caseSensitive
     *            boolean
     */
    public CaseControlledWordRule(IWordDetector detector, IToken defaultToken, boolean caseSensitive) {

        this(detector, defaultToken);
        setCaseSensitive(caseSensitive);

    }

    /**
     * 
     * @return org.eclipse.jface.text.rules.IToken
     * @param scanner
     *            org.eclipse.jface.text.rules.ICharacterScanner
     */
    public IToken evaluate(ICharacterScanner scanner) {

        int c = scanner.read();
        if (fDetector.isWordStart((char) c)) {
            if (fColumn == UNDEFINED || (fColumn == scanner.getColumn() - 1)) {

                fBuffer.setLength(0);
                do {
                    fBuffer.append((char) c);
                    c = scanner.read();
                } while (fDetector.isWordPart((char) c));
                scanner.unread();

                IToken token;

                if (isCaseSensitive()) {
                    token = (IToken) fWords.get(fBuffer.toString());
                } else {
                    // All keywords stored in upper case format now
                    String keyString = fBuffer.toString().toUpperCase();
                    token = (IToken) fWords.get(keyString);
                }

                if (token != null)
                    return token;

                if (fDefaultToken.isUndefined())
                    unreadBuffer(scanner);

                return fDefaultToken;
            }
        }

        scanner.unread();
        return Token.UNDEFINED;
    }

    /**
     * 
     * @return boolean
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * 
     * @param newCaseSensitive
     *            boolean
     */
    public void setCaseSensitive(boolean newCaseSensitive) {
        caseSensitive = newCaseSensitive;
    }
}