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
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

/**
 * The SQLPartitionScanner is a RulesBasedPartitionScanner.  The SQL document 
 * partitions are computed dynamically as events signal that the document has 
 * changed. The document partitions are based on tokens that represent comments
 * and SQL code sections.
 */
public class SQLPartitionScanner extends RuleBasedPartitionScanner implements ISQLPartitions{

    // Define constants for SQL DML Statements.
    public final static String SQL_SELECT = "__sql_select_statement__"; //$NON-NLS-1$
    public final static String SQL_INSERT = "__sql_insert_statement__"; //$NON-NLS-1$
    public final static String SQL_UPDATE = "__sql_update_statement__"; //$NON-NLS-1$
    public final static String SQL_DELETE = "__sql_delete_statement__"; //$NON-NLS-1$
    public final static String SQL_MERGE  = "__sql_merge_statement__"; //$NON-NLS-1$

    // Define constants for SQL DDL Statements.
    public final static String SQL_CREATE = "__sql_create_statement__"; //$NON-NLS-1$
    public final static String SQL_DROP   = "__sql_drop_statement__"; //$NON-NLS-1$
    public final static String SQL_ALTER  = "__sql_alter_statement__"; //$NON-NLS-1$

    // Define constants for SQL access control statements.
    public final static String SQL_GRANT  = "__sql_grant_statement__"; //$NON-NLS-1$
    public final static String SQL_REVOKE = "__sql_revoke_statement__"; //$NON-NLS-1$

    // Define constants for SQL transaction control statements.
    public final static String SQL_COMMIT   = "__sql_commit_statement__"; //$NON-NLS-1$
    public final static String SQL_ROLLBACK = "__sql_rollback_statement__"; //$NON-NLS-1$
    public final static String SQL_SET      = "__sql_set_statement__"; //$NON-NLS-1$
    
    // Define constants for SQL connection statements.
    public final static String SQL_CONNECT    = "__sql_connect_statement__"; //$NON-NLS-1$
    public final static String SQL_DISCONNECT = "__sql_disconnect_statement__"; //$NON-NLS-1$
    
    // Define constants for SQL miscellaneous statements.
    public final static String SQL_COMMENT_ON = "__sql_comment_on_statement__"; //$NON-NLS-1$
    public final static String SQL_TERMINATE  = "__sql_terminate_statement__"; //$NON-NLS-1$
    public final static String SQL_CATALOG    = "__sql_catalog_statement__"; //$NON-NLS-1$
    public final static String SQL_UNCATALOG  = "__sql_uncatalog_statement__"; //$NON-NLS-1$
    public final static String SQL_SIGNAL     = "__sql_signal_statement__"; //$NON-NLS-1$
    
    // Define a constant for SQL not otherwise covered.
    public final static String SQL_UNKNOWNSQL = "__sql__unknownsql_statement__"; //$NON-NLS-1$

    public final static String[] SQL_PARTITION_TYPES= new String[] {
    	IDocument.DEFAULT_CONTENT_TYPE,
    	SQL_CODE,
        SQL_COMMENT,
        SQL_MULTILINE_COMMENT,
        SQL_STRING,
        SQL_DOUBLE_QUOTES_IDENTIFIER,
    };
    
    // Syntax higlight
    List                  _rules            = new ArrayList();
    IToken                _sqlCode          = new Token(SQL_CODE);
    IToken                _comment          = new Token(SQL_COMMENT);
    IToken                _multilineComment = new Token(SQL_MULTILINE_COMMENT);
    IToken				  _sqlDoubleQuotesIdentifier = new Token(SQL_DOUBLE_QUOTES_IDENTIFIER);
    IToken				  _sqlString        = new Token(SQL_STRING);


    /**
     * Detector for empty comments.
     */
    static class EmptyCommentDetector implements IWordDetector
    {

        /*
         * @see IWordDetector#isWordStart
         */
        public boolean isWordStart(char c)
        {
            return (c == '/');
        }

        /*
         * @see IWordDetector#isWordPart
         */
        public boolean isWordPart(char c)
        {
            return (c == '*' || c == '/');
        }
    }

    /**
     * Word rule for empty comments.
     */
    static class EmptyCommentRule extends WordRule implements IPredicateRule
    {

        private IToken _fSuccessToken;

        /**
         * Constructor for EmptyCommentRule.
         * 
         * @param defaultToken
         */
        public EmptyCommentRule(IToken successToken)
        {
            super(new EmptyCommentDetector());
            _fSuccessToken = successToken;
            addWord("/**/", _fSuccessToken); //$NON-NLS-1$
        }

        /*
         * @see IPredicateRule#evaluate(ICharacterScanner, boolean)
         */
        public IToken evaluate(ICharacterScanner scanner, boolean resume)
        {
            return evaluate(scanner);
        }

        /*
         * @see IPredicateRule#getSuccessToken()
         */
        public IToken getSuccessToken()
        {
            return _fSuccessToken;
        }
    }

    /**
     * Constructor for SQLPartitionScanner. Creates rules to parse comment partitions in an SQL document. In the
     * constructor, is defined the entire set of rules used to parse the SQL document, in an instance of an
     * IPredicateRule. The coonstructor calls setPredicateRules method which associates the rules to the scanner and
     * makes the document ready for parsing.
     */
    public SQLPartitionScanner()
    {
        super();

        initRules();
        setupRules();
    }

	private void setupRules() {
		IPredicateRule[] result = new IPredicateRule[_rules.size()];
        _rules.toArray(result);
        setPredicateRules(result);
	}

	private void initRules() {
		//Add rule for identifier which is enclosed in double quotes.
        _rules.add(new MultiLineRule("\"", "\"", _sqlDoubleQuotesIdentifier, (char) 0));

        //Add rule for SQL string.
        _rules.add(new MultiLineRule("'", "'", _sqlString, (char) 0, true));

        //comments
        _rules.add( new EndOfLineRule( "--", _comment));
        
        // Add special case word rule.
        EmptyCommentRule wordRule = new EmptyCommentRule(_multilineComment);
        _rules.add(wordRule);

        // Add rules for multi-line comments
        _rules.add(new NestedMultiLineRule("/*", "*/", _multilineComment, (char) 0, true));
	}

    /**
	 * Constructs an instance of this class using an instance of ISQLSyntax.
	 * Creates rules based on the ISQLSyntax object to parse comment partitions
	 * in an SQL document.
	 */
    public SQLPartitionScanner(ISQLSyntax sqlSyntax) {
    	initRules();
    	//database specific rules
    	setCommentsScanner(sqlSyntax);
        setupRules();

    }

    private void setCommentsScanner(ISQLSyntax sqlSyntax)
    {
        String[] singleLineComments = sqlSyntax.getSingleLineComments();

        for (int i = 0; i < singleLineComments.length; i++)
        {
            // Add rule for single line comments.
            _rules.add(new EndOfLineRule(singleLineComments[i], _comment));
        }
    }

    /**
     * Return the String ranging from the start of the current partition to the current scanning position. Some rules
     * (@see NestedMultiLineRule) need this information to calculate the comment nesting depth.
     *  
     */
    public String getScannedPartitionString()
    {
        try
        {
            String scanned = fDocument.get(fPartitionOffset, fOffset - fPartitionOffset);
            return scanned;
        }
        catch (Exception e)
        {
            // Do nothing
        }
        return "";
    }


    /**
     * Gets the partitions of the given document as an array of 
     * <code>ITypedRegion</code> objects.  There is a distinct non-overlapping partition
     * for each comment line, string literal, delimited identifier, and "everything else"
     * (that is, SQL code other than a string literal or delimited identifier).
     * 
     * @param doc the document to parse into partitions
     * @return an array containing the document partion regions
     */
    public static ITypedRegion[] getDocumentRegions( IDocument doc ) {
        ITypedRegion[] regions = null;
        try {
            regions = TextUtilities.computePartitioning( doc, SQLPartitionScanner.SQL_PARTITIONING, 0, doc.getLength(), false );
        }
        catch ( BadLocationException e ) {
            // ignore
        }
        
        return regions;
    }

} // end class
