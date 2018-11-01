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

import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class SQLPartitionScanner extends RuleBasedPartitionScanner {

    public final static String SQL_DEFAULT = "__default_sql_block";
    public final static String SQL_COMMENT = "__sql_comment__";
    public final static String SQL_QUOTES = "__sql_quotes__";
    public final static String SQL_QUOTED_LITERAL       = "__sql_quoted_literal__"; //$NON-NLS-1$
    public final static String SQL_DELIMITED_IDENTIFIER = "__sql_delimited_identifier__"; //$NON-NLS-1$

    // DML Statements
    public final static String SQL_SELECT = "__sql__select__statement__";
    public final static String SQL_INSERT = "__sql__insert__statement__";
    public final static String SQL_UPDATE = "__sql__update__statement__";
    public final static String SQL_DELETE = "__sql__delete__statement__";
    public final static String SQL_WITH = "__sql__with__statement__";

    // DDL Statements
    public final static String SQL_CREATE = "__sql__create__statement__";
    public final static String SQL_DROP = "__sql__drop__statement__";
    public final static String SQL_ALTER = "__sql__alter__statement__";

    // Access control statements
    public final static String SQL_GRANT = "__sql__grant__statement__";
    public final static String SQL_REVOKE = "__sql__revoke__statement__";

    // Transaction control statements
    public final static String SQL_COMMIT = "__sql__commit__statement__";
    public final static String SQL_ROLLBACK = "__sql__rollback__statement__";
    public final static String SQL_SET = "__sql__set__statement__";

    // Connection
    public final static String SQL_CONNECT = "__sql__connect__statement__";
    public final static String SQL_DISCONNECT = "__sql__disconnect__statement__";

    // Comment
    public final static String SQL_COMMENT_ST = "__sql__comment__statement__";

    // End script
    public final static String SQL_TERMINATE = "__sql__terminate__statement__";

    // Catalog
    public final static String SQL_CATALOG = "__sql__catalog__statement__";
    // Uncatalog
    public final static String SQL_UNCATALOG = "__sql__uncatalog__statement__";

    // Signal
    public final static String SQL_SIGNAL = "__sql__signal__statement__";

    // unknown SQL command
    public final static String SQL_UNKNOWNSQL = "__sql__unknownsql__statement__";

    /**
     * SQLPartitionScanner constructor comment.
     */
    public SQLPartitionScanner() {
        super();

        List rules = new ArrayList();
        IToken token = null;

        // SQL_COMMENT.
        token = new Token(SQL_COMMENT);
        rules.add(new EndOfLineRule("--", token));

        //		token = new Token(SQL_COMMENT);
        //		rules.add(new CaseControlledMultiLineRule("/*", "*/", token));	

        // SQL_QUOTES.
        //		token = new Token(SQL_QUOTES);
        //		rules.add(new SingleLineRule("'","'", token));

        // SQL_SELECT
        token = new Token(SQL_SELECT);
        rules.add(new CaseControlledMultiLineRule("select", ";", token));

        token = new Token(SQL_SELECT);
        rules.add(new CaseControlledMultiLineRule("with", ";", token));

        // SQL_INSERT
        token = new Token(SQL_INSERT);
        rules.add(new CaseControlledMultiLineRule("insert", ";", token));

        // SQL_UPDATE
        token = new Token(SQL_UPDATE);
        rules.add(new CaseControlledMultiLineRule("update", ";", token));

        // SQL_DELETE
        token = new Token(SQL_DELETE);
        rules.add(new CaseControlledMultiLineRule("delete", ";", token));

        // SQL_CREATE
        token = new Token(SQL_CREATE);
        CaseControlledMultiLineRule tmpRule = new CaseControlledMultiLineRule("create", ";", token);
        {
            String tmpStringArray[] = { "trigger" };
            tmpRule.setIncludeInnerBlockCommands(tmpStringArray);
        }
        {
            String tmpStringArray[] = { "declare", "begin" };
            tmpRule.setInnerBlocksStartSequence(tmpStringArray);
        }
        {
            String tmpStringArray[] = { "end", "end" };
            tmpRule.setInnerBlocksEndSequence(tmpStringArray);
        }
        rules.add(tmpRule);

        // SQL_DROP
        token = new Token(SQL_DROP);
        rules.add(new CaseControlledMultiLineRule("drop", ";", token));

        // SQL_ALTER
        token = new Token(SQL_ALTER);
        rules.add(new CaseControlledMultiLineRule("alter", ";", token));

        // SQL_GRANT
        token = new Token(SQL_GRANT);
        rules.add(new CaseControlledMultiLineRule("grant", ";", token));

        // SQL_REVOKE
        token = new Token(SQL_REVOKE);
        rules.add(new CaseControlledMultiLineRule("revoke", ";", token));

        // SQL_COMMIT
        token = new Token(SQL_COMMIT);
        rules.add(new CaseControlledMultiLineRule("commit", ";", token));

        // SQL_ROLLBACK
        token = new Token(SQL_ROLLBACK);
        rules.add(new CaseControlledMultiLineRule("rollback", ";", token));

        // SQL_SET
        token = new Token(SQL_SET);
        rules.add(new CaseControlledMultiLineRule("set", ";", token));

        // SQL_CONNECT
        token = new Token(SQL_CONNECT);
        rules.add(new CaseControlledMultiLineRule("connect", ";", token));

        // SQL_DISCONNECT
        token = new Token(SQL_DISCONNECT);
        rules.add(new CaseControlledMultiLineRule("disconnect", ";", token));

        // SQL_COMMENT_ST
        token = new Token(SQL_COMMENT_ST);
        rules.add(new CaseControlledMultiLineRule("comment", ";", token));

        // SQL_TERMINATE
        token = new Token(SQL_TERMINATE);
        rules.add(new CaseControlledMultiLineRule("terminate", ";", token));

        // SQL_CATALOG
        token = new Token(SQL_CATALOG);
        rules.add(new CaseControlledMultiLineRule("catalog", ";", token));

        // SQL_UNCATALOG
        token = new Token(SQL_UNCATALOG);
        rules.add(new CaseControlledMultiLineRule("uncatalog", ";", token));

        // SQL_SIGNAL
        token = new Token(SQL_SIGNAL);
        rules.add(new CaseControlledMultiLineRule("signal", ";", token));

        for (int i = 0; i < 26; i++) { // for all 26 characters 
            char[] chars = { (char) (i + 97) };
            String start = new String(chars);
            token = new Token(SQL_UNKNOWNSQL);
            rules.add(new CaseControlledMultiLineRule(start, ";", token));
        }

        IPredicateRule[] result = new IPredicateRule[rules.size()];
        rules.toArray(result);
        setPredicateRules(result);
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
            regions = TextUtilities.computePartitioning( doc, SQLBuilderPlugin.SQL_PARTITIONING, 0, doc.getLength(), false );
        }
        catch ( BadLocationException e ) {
            // ignore
        }
        
        return regions;
    }

}