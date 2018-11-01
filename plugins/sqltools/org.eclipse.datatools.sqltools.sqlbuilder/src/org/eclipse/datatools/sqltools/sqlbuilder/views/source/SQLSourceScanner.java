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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

public class SQLSourceScanner extends org.eclipse.jface.text.rules.RuleBasedScanner implements SQLKeywords {

    private TextAttribute fKeyword = null;
    private TextAttribute fComment = null;
    private TextAttribute fDefaultTextAttribute = null;
    private TextAttribute fString = null;
    private TextAttribute fDoubleQuotesString = null;
    private SQLColourProvider fColourProvider = null;

    /**
     * Constructor.
     */
    public SQLSourceScanner(SQLColourProvider colourProvider) {

        fColourProvider = colourProvider;

        createTextAttributes();

        IToken keyword = new Token(fKeyword);
        IToken comment = new Token(fComment);
        IToken other = new Token(fDefaultTextAttribute);
        IToken string = new Token(fString);
        IToken doubleQuotesString = new Token(fDoubleQuotesString);

        List rules = new ArrayList();

        // For now show numbers, tablenames, etc as normal text
        // Add generic number rule.
        rules.add(new NumberRule(other));

        // table and database names
        rules.add(new MultiLineRule("'", "'", string)); //$NON-NLS-1$ //$NON-NLS-2$
        rules.add(new MultiLineRule("\"", "\"", doubleQuotesString)); //$NON-NLS-1$ //$NON-NLS-2$
        rules.add(new EndOfLineRule("--", comment)); //$NON-NLS-1$

        // Add generic whitespace rule.
        rules.add(new WhitespaceRule(new SQLWhitespaceDetector()));

        // Add word rule for keywords - not case sensitive by default.
        WordRule wordRule = new CaseControlledWordRule(new SQLWordDetector(), other);

        for (int i = 0; i < fSQLKeywordList.length; i++) {
            wordRule.addWord(fSQLKeywordList[i], keyword);
        }

        rules.add(wordRule);

        // Add generic number rule.
        rules.add(new NumberRule(other));

        IRule[] result = new IRule[rules.size()];
        rules.toArray(result);
        setRules(result);
    }

    /**
     * 
     */
    protected void createTextAttributes() {
        // create the necessary text attributes here
        fKeyword = new TextAttribute(fColourProvider.getColor(SQLColourProvider.KEYWORD));
        fComment = new TextAttribute(fColourProvider.getColor(SQLColourProvider.COMMENT));
        fDefaultTextAttribute = new TextAttribute(fColourProvider.getColor(SQLColourProvider.DEFAULT));
        fString = new TextAttribute(fColourProvider.getColor(SQLColourProvider.STRING));
        fDoubleQuotesString = new TextAttribute(fColourProvider.getColor(SQLColourProvider.DEFAULT));
    }
}