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
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;

public class SQLEntityScanner extends org.eclipse.jface.text.rules.RuleBasedScanner {

    private SQLColourProvider fColourProvider = null;
    private org.eclipse.jface.text.TextAttribute fEntity = null;

    /**
     * DDLEntityDamagerRepairer constructor comment.
     */
    public SQLEntityScanner(SQLColourProvider colourProvider) {
        super();
        fColourProvider = colourProvider;
        createTextAttributes();

        // Rule for detecting entities - databases, schemas, tables, which -
        // start with "\"" and end with "\""
        IToken entity = new Token(fEntity);

        List rules = new ArrayList();

        // Add rule for single line comments.
        rules.add(new SingleLineRule("\"", "\"", entity)); //$NON-NLS-1$ //$NON-NLS-2$

        // Add generic whitespace rule.
        rules.add(new WhitespaceRule(new SQLWhitespaceDetector()));

        IRule[] result = new IRule[rules.size()];
        rules.toArray(result);
        setRules(result);
    }

    /**
     * 
     */
    protected void createTextAttributes() {
        // create the necessary text attributes here
        fEntity = new TextAttribute(fColourProvider.getColor(SQLColourProvider.ENTITY));
    }
}