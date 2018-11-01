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
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;

public class SQLCommentScanner extends org.eclipse.jface.text.rules.RuleBasedScanner {

    private SQLColourProvider fColourProvider = null;
    private org.eclipse.jface.text.TextAttribute fComment = null;

    /**
     * Constructor.
     */
    public SQLCommentScanner(SQLColourProvider colourProvider) {
        super();

        // Set the colour provider here
        fColourProvider = colourProvider;
        createTextAttributes();

        // Rule for detecting comments, which - start with "--" and proceed to
        // end-of-line
        IToken comment = new Token(fComment);
        List rules = new ArrayList();

        // Add rule for single line comments.
        rules.add(new EndOfLineRule("--", comment));

        // Add generic whitespace rule.
        rules.add(new WhitespaceRule(new SQLWhitespaceDetector()));

        IRule[] result = new IRule[rules.size()];
        rules.toArray(result);
        setRules(result);

    }

    /**
     */
    protected void createTextAttributes() {
        // create the necessary text attributes here
        fComment = new TextAttribute(fColourProvider.getColor(SQLColourProvider.COMMENT));
    }
}