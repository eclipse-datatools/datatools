/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.sqltools.sql.parser.ISQLSyntax;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

/**
 * The SQLPartitionScanner is a RulesBasedPartitionScanner.  The SQL document 
 * partitions are computed dynamically as events signal that the document has 
 * changed. The document partitions are based on tokens that represent comments
 * and SQL code sections.
 */
public class SQLPartitionScanner extends RuleBasedPartitionScanner {
    // Define constants for SQL comments, literals, and identifiers.
    public final static String SQL_DEFAULT              = "__default_sql_block__"; //$NON-NLS-1$
    public final static String SQL_COMMENT              = "__sql_comment__"; //$NON-NLS-1$
    public final static String SQL_MULTILINE_COMMENT    = "__sql_multiline_comment";
    public final static String SQL_QUOTED_LITERAL       = "__sql_quoted_literal__"; //$NON-NLS-1$
    public final static String SQL_DELIMITED_IDENTIFIER = "__sql_delimited_identifier__"; //$NON-NLS-1$

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
        SQL_COMMENT,
        SQL_QUOTED_LITERAL,
        SQL_DELIMITED_IDENTIFIER,
    };
    
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
            regions = TextUtilities.computePartitioning( doc, SQLEditorPlugin.SQL_PARTITIONING, 0, doc.getLength(), false );
        }
        catch ( BadLocationException e ) {
            // ignore
        }
        
        return regions;
    }

    /**
     * Constructs an instance of this class.  Creates rules to parse comment 
     * partitions in an SQL document.  This is the default constructor.
     */
    public SQLPartitionScanner() {
        super();
        
        List rules= new ArrayList();

        // Add rules for comments, quoted literals, and delimited identifiers.
        rules.add( new EndOfLineRule( "--", new Token( SQL_COMMENT )));
        rules.add( new SingleLineRule( "'", "'", new Token( SQL_QUOTED_LITERAL ), '\\' ));
        rules.add( new SingleLineRule( "\"", "\"", new Token( SQL_DELIMITED_IDENTIFIER ), '\\' ));

        IPredicateRule[] result= new IPredicateRule[ rules.size() ];
        rules.toArray( result );
        setPredicateRules( result );
    }
        
    /**
	 * Constructs an instance of this class using an instance of ISQLSyntax.
	 * Creates rules based on the ISQLSyntax object to parse comment partitions
	 * in an SQL document.
	 */
    public SQLPartitionScanner(ISQLSyntax sqlSyntax) {
    	super();
    	
    	List rules= new ArrayList();
    	
    	// Add rules for comments, quoted literals, and delimited identifiers.
    	rules.add( new EndOfLineRule( "--", new Token( SQL_COMMENT )));
    	rules.add( new SingleLineRule( "'", "'", new Token( SQL_QUOTED_LITERAL ), '\\' ));
    	rules.add( new SingleLineRule( "\"", "\"", new Token( SQL_DELIMITED_IDENTIFIER ), '\\' ));
    	
    	//database specific rules
    	if (sqlSyntax != null)
    	{
            String[] singleLineComments = sqlSyntax.getSingleLineComments();
            for (int i = 0; i < singleLineComments.length; i++)
            {
                // Add rule for single line comments.
                rules.add(new EndOfLineRule(singleLineComments[i], new Token( SQL_COMMENT )));
            }
    	}
    	
    	IPredicateRule[] result= new IPredicateRule[ rules.size() ];
    	rules.toArray( result );
    	setPredicateRules( result );
    }
    
} // end class
