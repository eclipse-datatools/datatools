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
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLColorProvider;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLWordDetector;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * This class implements a RuleBaseScanner for SQL source code text.
 */
public class SQLCodeScanner extends RuleBasedScanner {

    
    private IToken commentToken;
	private IToken stringToken;
	private IToken keywordToken;
	private IToken datatypeToken;
	private IToken functionToken;
	private IToken delimitedIdentifierToken;
	private IToken otherToken;
	private IToken multilineCommentToken;
	private List rules;

	/**
     * This class determines if a character is a whitespace character.
     */
    public class SQLWhiteSpaceDetector implements IWhitespaceDetector {

        /**
         * Determines if the given character is a whitespace character.
         * @see org.eclipse.jface.text.rules.IWhitespaceDetector#isWhitespace(char)
         */
        public boolean isWhitespace( char c ) {
            return Character.isWhitespace( c );
        }

    } // end inner class
    
    /**
     * Constructs an instance of this class using the given color provider.
     */
    public SQLCodeScanner( SQLColorProvider colorProvider ) {
		/* On "high contrast" displays the default text color (black) is a problem,
         * since the normal high contrast background is black.  ("High contrast" is
         * a Windows feature that helps vision-impaired people.)  When high contrast mode is enabled,
         * use different colors that show up better against a black background.
         */
        if (Display.getDefault().getHighContrast() == true) {
            commentToken    = new Token( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_HC_COMMENT_COLOR )));
    		multilineCommentToken = new Token( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_HC_MULTILINE_COMMENT_COLOR )));
            stringToken     = new Token( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_HC_QUOTED_LITERAL_COLOR )));
            keywordToken    = new Token( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_HC_KEYWORD_COLOR), null, SWT.BOLD));
            datatypeToken   = new Token( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_HC_TYPE_COLOR), null, SWT.BOLD ));
            functionToken   = new Token( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_HC_IDENTIFIER_COLOR )));
//            identifierToken = new Token( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_HC_IDENTIFIER_COLOR )));
            delimitedIdentifierToken = new Token( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_HC_DELIMITED_IDENTIFIER_COLOR )));
            otherToken      = new Token( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_HC_DEFAULT_COLOR )));
        }
        else {
    		commentToken 	= colorProvider.createToken(SQLColorProvider.SQL_COMMENT);
    		multilineCommentToken = colorProvider.createToken(SQLColorProvider.SQL_MULTILINE_COMMENT);
    		stringToken 	= colorProvider.createToken(SQLColorProvider.SQL_QUOTED_LITERAL);
    		keywordToken 	= colorProvider.createToken(SQLColorProvider.SQL_KEYWORD);
    		datatypeToken 	= colorProvider.createToken(SQLColorProvider.SQL_TYPE);
    		functionToken 	= colorProvider.createToken(SQLColorProvider.SQL_IDENTIFIER);
//          identifierToken = new Token( new TextAttribute( colorProvider.getColor( SQLColorProvider.SQL_IDENTIFIER_COLOR )));
    		delimitedIdentifierToken 	= colorProvider.createToken(SQLColorProvider.SQL_DELIMITED_IDENTIFIER);
    		otherToken 		= colorProvider.createToken(SQLColorProvider.SQL_DEFAULT);
        }

        setDefaultReturnToken( otherToken );

        rules = new ArrayList();
		// Add rule for single-line comments.
        rules.add( new EndOfLineRule( "--", commentToken )); //$NON-NLS-1$

        // Add rules for delimited identifiers and string literals.
        rules.add( new SingleLineRule( "'", "'", stringToken, (char) 0 )); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        rules.add( new SingleLineRule( "\"", "\"", delimitedIdentifierToken, (char) 0 )); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        // Add rules for multi-line comments
        rules.add(new NestedMultiLineRule("/*", "*/", multilineCommentToken, (char) 0, true));

        // Add generic whitespace rule.
        rules.add( new WhitespaceRule( new SQLWhiteSpaceDetector() ));

        // Convert the list of rules to an array and return it.
        IRule[] result = new IRule[ rules.size() ];
        rules.toArray( result );
        setRules( result );
    }

    public void setSQLSyntax(ISQLSyntax sqlSyntax)
    {
    	if (sqlSyntax == null)
    	{
    		return; //do nothing
    	}
    	ArrayList sqlRules = new ArrayList();
        // Define a word rule and add SQL keywords to it.
        WordRule wordRule = new WordRule( new SQLWordDetector(), otherToken );
        String[] reservedWords = sqlSyntax.getReservedwords();
        for (int i = 0; i < reservedWords.length; i++) {
            wordRule.addWord( reservedWords[i].toLowerCase(), keywordToken );
            wordRule.addWord( reservedWords[i].toUpperCase(), keywordToken );
        }
        //TODO render unreserved keywords in the same way with reserved keywords, should let user decide via preference
        String[] unreservedWords = sqlSyntax.getUnreservedwords();
        for (int i = 0; i < unreservedWords.length; i++)
        {
            wordRule.addWord(unreservedWords[i].toLowerCase(), keywordToken);
            wordRule.addWord(unreservedWords[i].toUpperCase(), keywordToken);
        }
        
        // Add the SQL datatype names to the word rule.
        String[] datatypes = sqlSyntax.getTypes();
        for (int i = 0; i < datatypes.length; i++) {
            wordRule.addWord( datatypes[i].toLowerCase(), datatypeToken );
            wordRule.addWord( datatypes[i].toUpperCase(), datatypeToken );
        }

        // Add the SQL function names to the word rule.
        String[] functions = sqlSyntax.getFunctions();
        for (int i = 0; i< functions.length; i++) {
            wordRule.addWord( functions[i].toLowerCase(), functionToken );
            wordRule.addWord( functions[i].toUpperCase(), functionToken );
        }
        
        // Add the SQL constants to the word rule.
        String[] constants = sqlSyntax.getConstants();
        for (int i = 0; i < constants.length; i++)
        {
            wordRule.addWord(constants[i].toLowerCase(), datatypeToken);
            wordRule.addWord( constants[i].toUpperCase(), datatypeToken );
        }

        // Add the word rule to the list of rules.
        sqlRules.addAll(rules);
        sqlRules.add(wordRule);

        IRule[] result = new IRule[sqlRules.size()];
        sqlRules.toArray(result);

        setRules(result);
    }
    
} // end class
