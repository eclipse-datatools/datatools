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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.helper.DatabaseHelper;
import org.eclipse.datatools.modelbase.sql.schema.helper.SchemaHelper;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLDBProposal;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;

import com.ibm.icu.util.StringTokenizer;

public class SQLCompletionEngine {

    private SQLCompletionProposalFactory fProposalFactory = null;
    private IDBContext fDBContext;  // RATLC01136221 bgp 10Jan2007
    private SQLBuilderDBProposalsService fSQLDBProposalsService;  // [wsdbu00055322] bgp 04May2006 // RATLC01136221 bgp 10Jan2007

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public SQLCompletionEngine() {
        super();

        fProposalFactory = new SQLCompletionProposalFactory();
    }

    // [wsdbu00055322] bgp 05May2006
    /**
     * Attempts to make the given token list more complete by adding
     * a schema name if it is missing.  The token list contains the "context"
     * for a "." (auto-triggered) request for database object content assist proposals.
     * To be useful to the DB proposals service, the token list should
     * contain a schema name or a schema name and table name.  This routine attempts 
     * to supply the schema name if it is missing.
     * 
     *  @param tokenList the token list to start with
     *  @return an updated token list
     */
    private List addSchemaNameIfNeeded(List tokenList) {
        List improvedTokenList = tokenList;
        // RATLC01136221 bgp 15Jan2007 - begin
        SQLDomainModel domainModel = null;
        String defaultSchemaName = null; 
        QueryStatement statement = null;
        
        IDBContext dbContext = this.getDBContext();
        if (dbContext != null && dbContext.getDomainModel() != null) {
             domainModel = dbContext.getDomainModel();
             defaultSchemaName = domainModel.getCurrentSchema();
             statement = domainModel.getSQLStatement();
        }
        else {
        	/*  If there is no domainModel we can't do anything, so bail out. */
        	return improvedTokenList;
        }
    
        /* We only need to try to update the token list if it contains
         * one entry.  When it has two or more entries we assume it is
         * fully specified with the schema name and table name.
         */
        if (tokenList.size() == 1 ) {
        	List tableExprList = StatementHelper.getTablesForStatement( statement );
        	Set schemaNameSet = new HashSet();
        	
        	/* Get the token list entry, if there is one. */
            String entry = "";
            String upperEntry = "";
            if (tokenList.size() > 0) {  // RATLC01136221 bgp 12Jan2007
                entry = (String) tokenList.get(0);
                upperEntry = entry.toUpperCase();
            }
            
        	if (tableExprList.isEmpty()) {
        		/* Determine if the entry is a table or schema name. */
                Database db = domainModel.getDatabase();
        		Schema defaultSchema = DatabaseHelper.findSchema(db, defaultSchemaName);
                // TODO: what about mixed-case names?
        		if (defaultSchema != null) { // bug 217356
        		    Table table = SchemaHelper.findTable(defaultSchema, upperEntry);
        		    if (table != null){
        		        improvedTokenList.clear();
        		        improvedTokenList.add(defaultSchemaName);
        		        improvedTokenList.add(upperEntry);
        		    }
        		}
        	}
            // RATLC01136221 bgp 15Jan2007 - end
        	else {
        		/* Build a set of the schema names used in the query,
        		 * including the default schema name.
        		 */
        		schemaNameSet.add(defaultSchemaName);
        		Iterator tableExprListIter = tableExprList.iterator();
        		while (tableExprListIter.hasNext()) {
        			TableExpression tableExpr = (TableExpression) tableExprListIter.next();
        			String schemaName = TableHelper.getSchemaNameForTableExpression(tableExpr);
        			if (schemaName != null) {
        				schemaNameSet.add(schemaName);
        			}
        		}

        		/* If the entry is in our schema set then we don't
        		 * need to do anything more.  Otherwise carry on.
        		 */
        		if (!schemaNameSet.contains(upperEntry)) {
        			/* Check if the entry is a table alias or table name.  
        			 * If so, replace it with the schema and table name that
        			 * it corresponds to.
        			 */
        			tableExprListIter = tableExprList.iterator();
        			String realTableName = null;
        			String realSchemaName = null;
        			while (tableExprListIter.hasNext() && realTableName == null) {
        				TableExpression tableExpr = (TableExpression) tableExprListIter.next();
        				String exposedName = TableHelper.getExposedTableName(tableExpr); 
        				if (exposedName.equalsIgnoreCase(entry)) {
        					realTableName = tableExpr.getName();
        					realSchemaName = TableHelper.getSchemaNameForTableExpression(tableExpr);
        					improvedTokenList.clear();
        					improvedTokenList.add(realSchemaName);
        					improvedTokenList.add(realTableName);
        				}
        			}
        		}
        	}
        }
            
        return improvedTokenList;
    }
    
    // RATLC01136221 bgp 10Jan2007 - new method, derived from computeProposals 
    /**
     * Computes and returns a list of database (catalog) objects (that is, schema, table,
     * and column names) as an array of CompletionProposal objects, based on the current
     * location in the given document.
     * 
     * @param doc the current document
     * @param partition the current partition in the document
     * @param docOffset the location (offset) in the document where content assist
     * is wanted
     * @param dbContext the database context (that is, the current database connection)
     * @return the array of content assist proposals
     */
    public ICompletionProposal[] computeDBProposals(IDocument doc, ITypedRegion partition, int docOffset, IDBContext dbcontext) {
        ICompletionProposal[] dbProposalArray = null;
        
        /* Don't return any DB proposals if a statement type has not been identified.
         */
        if (partition.getType().equals(IDocument.DEFAULT_CONTENT_TYPE)) {
            return null;
        }

        /* If the document location is bad, bail out. */
        if (docOffset < 0)
            return dbProposalArray;
        
        this.setDBContext(dbcontext);
        
        /* Get a list of schemas, tables, or columns as content assist proposals 
         * (SQLDBProposal objects).
         */
        List proposalList = null;
        String contextString = "";
        String filterString = "";
        try {
            List dbContextList = new ArrayList();
          
            if (docOffset > 0 ) {
                /* Get document offset to the start of "word" where content assist
                 * is requested.
                 */
                int wordOffset = getPartitionOffset( doc, partition, docOffset, partition.getOffset() );
                contextString = doc.get( wordOffset, docOffset - wordOffset );
                filterString = contextString;

                /* If prefix contains a "dot", then we have a "DB context" and we 
                 * can treat the word preceeding the dot as a schema or table name (or table alias).
                 * Get a list of content assist proposals from the SQL model.
                 */
                if (contextString.indexOf(".") > -1) {
                    StringTokenizer tokenizer = new StringTokenizer(contextString, "."); //$NON-NLS-1$
                    while (tokenizer.hasMoreTokens()) {
                        dbContextList.add(tokenizer.nextToken());
                    }

                    /* If last token didn't end in a dot, it is a filter,
                     * not a db context.  That is, in the context string 
                     * "myschema.mytable.c", the final "c" is a filter
                     * meaning give columns beginning with c.  Get the filter
                     * string and remove the filter string from the DB context list.
                     */
                    if (!contextString.endsWith(".")) {
                        filterString = (String) dbContextList.remove( dbContextList.size()-1 );
                    }
                    else {
                        filterString = "";
                    }
                    
                    dbContextList = addSchemaNameIfNeeded( dbContextList );
                    if (dbContextList.size() == 1 || dbContextList.size() == 2) {
                        contextString = (String) dbContextList.get(0) + ".";
                        if (dbContextList.size() == 2) {
                            contextString = contextString + dbContextList.get(1) + ".";
                        }
                    }
                }

                /* Even when the context string does not contain a dot, we still
                 * want to have the schema name in the context list, so that we will
                 * get tables from the default schema in the proposal list. 
                 */
                else {
                    dbContextList = addSchemaNameIfNeeded( dbContextList );
                }
            }
            
            /* Get the DB proposals. */
            proposalList = this.getDBObjectProposals( dbContextList );
        }
        catch (BadLocationException exc) {
        }

        Vector dbProposals = filterProposals( proposalList, filterString, false );
        String replacementStr = null;
        String displayStr = null;
        
        /* Load the list of SQLDBProposal objects into an array of ICompletionProposal objects. */
        dbProposalArray = new ICompletionProposal[dbProposals.size()];
        int dbProposalArrayIndex = 0;
        int proposalType = SQLCompletionProposal.PROPOSAL_TYPE_UNSPECIFIED;  // RATLC01136221 bgp 15Jan2007
        for (int i = 0; i < dbProposals.size(); i++) {
            Image image = null;
            Object proposal = dbProposals.get(i);
            displayStr = dbProposals.get( i ).toString();
            replacementStr = displayStr;
           
            if (dbProposals.get( i ) instanceof SQLBuilderDBProposal) {
                SQLBuilderDBProposal dbProposal = (SQLBuilderDBProposal) dbProposals.get( i );
                String parentNameUC = dbProposal.getParentName().toUpperCase();
                String prefixStringUC = contextString.toUpperCase();
                StringTokenizer strTokens = new StringTokenizer( parentNameUC, "." ); //$NON-NLS-1$
                
                /* Translate the proposal type codes used in the SQLDBProposal class
                 * to the codes used in the SQLCompletionProposal class. */
                int dbProposalType = dbProposal.getType();
                switch (dbProposalType) {
                case SQLDBProposal.SCHEMA_OBJTYPE:
                    proposalType = SQLCompletionProposal.PROPOSAL_TYPE_SCHEMA;
                    break;
                case SQLDBProposal.TABLE_OBJTYPE:
                    proposalType = SQLCompletionProposal.PROPOSAL_TYPE_TABLE;
                    break;
                case SQLDBProposal.TABLECOLUMN_OBJTYPE:
                    proposalType = SQLCompletionProposal.PROPOSAL_TYPE_COLUMN;
                    break;
                }

                /* If we have a column proposal, get the datatype information
                 * in the SQLBuilderDBProposal's "extraInfo" field and add it 
                 * to the display string.
                 */
                if (dbProposal instanceof SQLBuilderDBProposal && dbProposalType == SQLDBProposal.TABLECOLUMN_OBJTYPE) {
                    SQLBuilderDBProposal sbProposal = (SQLBuilderDBProposal) dbProposal;
                    String extraInfo = sbProposal.getExtraInfo();
                    if (extraInfo != null && extraInfo.length() > 0) {
                        displayStr = displayStr + " - " + extraInfo;
                    }
                }
                
                /* For an ambiguous case where user enters "SCHEMA." and
                 * there is also a table called SCHEMA then the list of proposals
                 * also includes all columns in the SCHEMA.SCHEMA table.
                 * If user select COL1, then we want to append SCHEMA.COL1
                 * following user's input "SCHEMA."
                 */
                if (strTokens.countTokens() > 1 && parentNameUC.startsWith( prefixStringUC )) {
                    String preceedingNames = parentNameUC.replaceFirst( prefixStringUC, "" ); //$NON-NLS-1$
                    replacementStr = preceedingNames + "." + dbProposal.toString(); //$NON-NLS-1$
                }

                image = ((SQLDBProposal) dbProposals.get( i )).getImage();
                //additionalInfo = ((SQLDBProposal) dbProposals.get( i )).getParentName() + "." //$NON-NLS-1$
                //        + ((SQLDBProposal) dbProposals.get( i )).getName();
            }
            
            if (dbProposals.size() <= proposalList.size()) {
                if (replacementStr != null && docOffset >= 0) {
                    int replacementLen = filterString.length();
                    int replacementOffset = docOffset - replacementLen;
                    SQLCompletionProposal sqlProposal = new SQLCompletionProposal(
                            replacementStr, 
                            replacementOffset, 
                            replacementOffset, replacementLen, image, displayStr, null);
                    sqlProposal.setProposalType(proposalType);
                    dbProposalArray[dbProposalArrayIndex] = sqlProposal;        
                    dbProposalArrayIndex++;
                }
            }
        }
        
        return dbProposalArray;
    }
    
    // RATLC01136221 bgp 10Jan2007 - new method, derived from computeProposals
    /**
     * Computes and returns a list of syntax elements (keywords and operators) as an 
     * array of CompletionProposal objects, based on the current location in the given document.
     * 
     * @param doc the current document
     * @param partition the current partition in the document
     * @param docOffset the location (offset) in the document where content assist
     * is wanted
     * @return the array of content assist proposals
     */
    public ICompletionProposal[] computeSyntaxProposals(IDocument doc, ITypedRegion partition, int docOffset )
    {
        ICompletionProposal[] syntaxProposalArray = null;
        String[] proposals = null;
        String partitionId = partition.getType();
        
        /* If the document location is bad, bail out. */
        if (docOffset < 0)
            return syntaxProposalArray;

        /* Get an icon to use for syntax proposals. */
        Image image = SQLBuilderPlugin.getSQLImage( "icons/SQL_keyword.gif" ); //$NON-NLS-1$  // RATLC01136221 bgp 12Jan2007
        
        /* Get syntactic content assist proposals that are specific to the current
         * statement type.
         */
        syntaxProposalArray = null;
        if (partitionId == SQLPartitionScanner.SQL_SELECT) {
            proposals = fProposalFactory.getSelectProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "SELECT ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                // RATLC01136221 bgp 14Jan2007 - begin
                SQLCompletionProposal sqlProposal = null;
                if (docOffset > offset)
                    sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, image, tmpStr, info);
                else
                    sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, 0, image, tmpStr, info);
                sqlProposal.setProposalType(SQLCompletionProposal.PROPOSAL_TYPE_SYNTAX);
                syntaxProposalArray[i] = sqlProposal;
                // RATLC01136221 bgp 14Jan2007 - end
            }
        } else if (partitionId == SQLPartitionScanner.SQL_INSERT) {
            proposals = fProposalFactory.getInsertProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "INSERT ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                // RATLC01136221 bgp 14Jan2007 - begin
                SQLCompletionProposal sqlProposal = null;
                if (docOffset > offset)
                    sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, image, tmpStr, info);
                else
                    sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, 0, image, tmpStr, info);
                sqlProposal.setProposalType(SQLCompletionProposal.PROPOSAL_TYPE_SYNTAX);
                syntaxProposalArray[i] = sqlProposal;
                // RATLC01136221 bgp 14Jan2007 - end
            }
        } else if (partitionId == SQLPartitionScanner.SQL_UPDATE) {
            proposals = fProposalFactory.getUpdateProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "UPDATE ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                // RATLC01136221 bgp 14Jan2007 - begin
                SQLCompletionProposal sqlProposal = null;
                if (docOffset > offset)
                    sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, image, tmpStr, info);
                else
                    sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, 0, image, tmpStr, info);
                sqlProposal.setProposalType(SQLCompletionProposal.PROPOSAL_TYPE_SYNTAX);
                syntaxProposalArray[i] = sqlProposal;
                // RATLC01136221 bgp 14Jan2007 - end
            }
        } else if (partitionId == SQLPartitionScanner.SQL_DELETE) {
            proposals = fProposalFactory.getDeleteProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "DELETE ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                // RATLC01136221 bgp 14Jan2007 - begin
                SQLCompletionProposal sqlProposal = null;
                if (docOffset > offset)
                    sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, image, tmpStr, info);
                else
                    sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, 0, image, tmpStr, info);
                sqlProposal.setProposalType(SQLCompletionProposal.PROPOSAL_TYPE_SYNTAX);
                syntaxProposalArray[i] = sqlProposal;
                // RATLC01136221 bgp 14Jan2007 - end
            }
        } else if (partitionId == SQLPartitionScanner.SQL_CREATE) {
            proposals = fProposalFactory.getCreateProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "CREATE ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_DROP) {
            proposals = fProposalFactory.getDropProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "DROP ", 0); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_ALTER) {
            proposals = fProposalFactory.getAlterProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "ALTER ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_GRANT) {
            proposals = fProposalFactory.getGrantProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "GRANT ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_REVOKE) {
            proposals = fProposalFactory.getRevokeProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "REVOKE ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_COMMIT) {
            proposals = fProposalFactory.getCommitProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "COMMIT", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_ROLLBACK) {
            proposals = fProposalFactory.getRollbackProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "ROLLBACK", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_SET) {
            proposals = fProposalFactory.getSetProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "SET ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_CONNECT) {
            proposals = fProposalFactory.getConnectProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "CONNECT", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_DISCONNECT) {
            proposals = fProposalFactory.getDisconnectProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "DISCONNECT ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_CATALOG) {
            proposals = fProposalFactory.getCatalogProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "CATALOG ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_UNCATALOG) {
            proposals = fProposalFactory.getUncatalogProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "UNCATALOG ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else if (partitionId == SQLPartitionScanner.SQL_COMMENT_ST) {
            proposals = fProposalFactory.getCommentOnProposals();
            int offset = getPartitionOffset(doc, partition, docOffset, "COMMENT ON ", 1); //$NON-NLS-1$
            String statement = null;

            try {
                if (docOffset > offset)
                    statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                if (docOffset > offset)
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, null, tmpStr, info);
                else
                    syntaxProposalArray[i] = new SQLCompletionProposal(tmpStr, offset, offset, 0, null, tmpStr, info);
            }
        } else //if(partitionId == IDocument.DEFAULT_CONTENT_TYPE)
        {
            try {
                if (docOffset == 0 || doc.getChar(docOffset - 1) != '.')
                    proposals = fProposalFactory.getStatementProposals();
            }
            catch (BadLocationException exc) {
            }

            // Get document offset to the start of "word" where content assist
            // is requested.
            int offset = getPartitionOffset(doc, docOffset, partition.getOffset());
            String statement = null;

            try {
                statement = doc.get(offset, docOffset - offset);
            } catch (BadLocationException x) {
            }

            Vector pList = sortProposals(proposals, statement, false);
            syntaxProposalArray = new ICompletionProposal[pList.size()];
            for (int i = 0; i < pList.size(); i++) {
                String tmpStr = (String) pList.get(i);
                IContextInformation info = null;
                // RATLC01136221 bgp 16Jan2007 - begin
                SQLCompletionProposal sqlProposal = null;
                if (pList.size() < proposals.length)
                    sqlProposal = new SQLCompletionProposal(tmpStr, offset, offset, docOffset - offset, image, tmpStr, info);
                else
                    sqlProposal = new SQLCompletionProposal(tmpStr, docOffset, offset, docOffset - offset, image, tmpStr, info);
                sqlProposal.setProposalType(SQLCompletionProposal.PROPOSAL_TYPE_SYNTAX);
                syntaxProposalArray[i] = sqlProposal;
                // RATLC01136221 bgp 16Jan2007 - end
            }
        }
        
        return syntaxProposalArray;
    }

    // [wsdbu00055322] bgp 03May2006
    /**
     * Filters the given list of proposals based on the given prefix string.
     * 
     * @param proposalList a list of proposals to be filtered
     * @param prefixString the string to be used to filter
     * @param showAll when true, include all proposals when the filter
     * appears to filter out all proposals
     * @return the sorted list of proposals
     */
    public Vector filterProposals( List proposalList, String prefixString, boolean showAll ) {
    
        Vector proposalVec = new Vector();
        if (proposalList == null)
            return proposalVec;
    
        if (prefixString != null && prefixString.length() > 0) {
            
            // TODO: Is this part needed, since we don't pass a prefixString that
            // contains a dot
            char triggerChar = prefixString.charAt( prefixString.length() - 1 );
            String precedingName = null;
            if (triggerChar == '.') {
                precedingName = prefixString.substring( 0, prefixString.length() - 1 );
                if (!precedingName.equals( "" )) { //$NON-NLS-1$
    
                    ListIterator listIterator = proposalList.listIterator();
                    while (listIterator.hasNext()) {
                        Object proposal = listIterator.next();
                        if (proposal instanceof SQLDBProposal) {
                            String parentNameUC = ((SQLDBProposal) proposal).getParentName().toUpperCase();
                            String precedingNameUC = precedingName.toUpperCase();
                            if (parentNameUC.endsWith( precedingNameUC )) {
                                proposalVec.add( proposal );
                            }
                        }
                    }
                }
            }
            else {
                String proposalText;
                for (int i = 0; i < proposalList.size(); i++) {
                    Object proposal = proposalList.get(i);
                    if (proposal instanceof Object[]){
                        proposalText = (String)((Object[])proposal)[0];
                    } else if (proposal instanceof SQLDBProposal) {
                        proposalText = ((SQLDBProposal)proposal).getName();
                    }
                    else {
                        proposalText = proposal.toString(); 
                    }
                    if (proposalText.toUpperCase().trim().indexOf( prefixString.toUpperCase() ) != 0)

                            continue;
                        proposalVec.add( proposalList.get( i ) );
                    }

                }
            }
        else
             proposalVec.addAll( proposalList );
         
        if (showAll && proposalVec.size() == 0) {
            proposalVec.addAll( proposalList );
        }
    
        return proposalVec;
    }
  
    /**
     * Returns document offset to the start of the "word" where content assist
     * is requested.
     * 
     * @param doc
     *            IDocument
     * @param partition
     *            ITypedRegion
     * @param docOffset
     *            int
     * @param offset
     *            int
     */
    public int getPartitionOffset(IDocument doc, int docOffset, int offset) {
        boolean loop = true;

        int offset1 = docOffset - 1;
        try {

            while (loop && offset <= offset1) {
                switch (doc.getChar(offset1)) {
                case 10: // linefeed
                case 32: // space
                case 13: // return
                case 9: // tab
                {
                    loop = false;
                }
                    break;
                default: {
                    offset1--;
                }
                    break;
                }
            }
        } catch (BadLocationException x) {
        }

        offset = offset1 + 1;

        return offset;
    }

    /**
     * Returns the document offset to the start of the "word" where content
     * assist is requested.
     * 
     * @param doc the current document
     * @param partition document partition region. A region consists of offset,
     *            length, and type.
     * @param docOffset offset into the document
     * @param offset offset in the document to start of the name preceeding the
     *            activation character
     */
    public int getPartitionOffset( IDocument doc, ITypedRegion partition, int docOffset, int offset ) {
        boolean loop = true;

        int offset1 = docOffset - 1;
        try {
            while (loop && offset <= offset1) {
                switch (doc.getChar( offset1 )) {
                    case 10: // linefeed
                    case 32: // space
                    case 13: // return
                    case 9: { // tab
                        loop = false;
                    }
                        break;
                    default: {
                        offset1--;
                    }
                        break;
                }
            }
        }
        catch (BadLocationException x) {
        }

        offset = offset1 + 1;

        return offset;
    }

    /**
     * @param doc
     *            IDocument
     * @param partition
     *            ITypedRegion
     * @param docOffset
     *            int
     * @param leadingString
     *            String
     * @param position
     *            1: from current position 0: after leadingString
     */
    public int getPartitionOffset(IDocument doc, ITypedRegion partition, int docOffset, String leadingString, int position) {
        int offset = partition.getOffset() + leadingString.length();

        if (docOffset <= offset)
            return offset;

        switch (position) {
        case 0: {
        }
            break;
        default: {
            boolean loop = true;
            int offset1 = docOffset - 1;
            try {
                while (loop && offset <= offset1) {
                    switch (doc.getChar(offset1)) {
                    case 10: // linefeed
                    case 32: // space
                    case 13: // return
                    case 9: // tab
                    {
                        loop = false;
                    }
                        break;
                    default: {
                        offset1--;
                    }
                        break;
                    }
                }
            } catch (BadLocationException x) {
            }

            offset = offset1 + 1;
        }
            break;
        }

        return getPartitionOffset(doc, docOffset, offset);
    }

    public static boolean showContextInformation(IDocument doc, ITypedRegion partition, int docOffset, String leadingString) {
        int offset = partition.getOffset() + leadingString.length();

        if (docOffset <= offset)
            return true;

        boolean loop = true;
        int offset1 = docOffset;
        try {
            while (loop && offset <= offset1) {
                switch (doc.getChar(offset1)) {
                case 10: // linefeed
                case 32: // space
                case 13: // return
                case 9: // tab
                {
                    offset1--;
                }
                    break;
                default: {
                    loop = false;
                }
                    break;
                }
            }
        } catch (BadLocationException x) {
        }

        if (offset1 <= offset)
            return true;

        return false;
    }

    /**
     * 
     * @param proposals
     *            String []
     * @param sortingString
     *            String
     */
    public Vector sortProposals(String proposals[], String sortingString, boolean showAll) {
        Vector aList = new Vector();

        if (sortingString != null && sortingString.length() > 0) {
            for (int i = 0; i < proposals.length; i++) {
                if (proposals[i].toUpperCase().trim().indexOf(sortingString.toUpperCase()) != 0)
                    continue;
                aList.add(proposals[i]);
            }
        } else
            showAll = true;

        if (showAll && aList.size() == 0) {
            for (int i = 0; i < proposals.length; i++)
                aList.add(proposals[i]);
        }

        return aList;
    }
    
    // [wsdbu00055322] bgp 05May2006
    // [wsdbu00083212] bgp 22May2006
    // RATLC01136221 bgp 14Jan2007 - rewritten to use SQLBuilderDBProposal objects
    /**
     * Gets a list of DBProposal objects from the available columns of the 
     * tables in the query statement.
     * 
     * @return the available columns list
     */
    public List getAvailableColumns() {
        List dbProposalsList = new ArrayList();
        
        IDBContext dbContext = getDBContext();
        if (dbContext != null && dbContext.getDomainModel() != null) {
            SQLDomainModel domainModel = dbContext.getDomainModel();
            
            //TODO: SQL doesn't parse when it has a partial value specified 
            // like "SELECT * FROM EMPLOYEE WHERE E".  So no DB proposals are
            // provided if any prefix is given. 
            // Hopefully the new parser will fix this problem
            
            /* Get the current statement and get a list of table
             * expressions referenced in that statement.
             */
            QueryStatement statement = domainModel.getSQLStatement();
            List tableExprList = StatementHelper.getTablesForStatement( statement );
            
            /* Create a list of column names for the "table in database"
             * objects in the table expression list.  If there is more
             * than one table, qualify the column names with the table name.
             */
            boolean multiTable = (tableExprList.size() > 1) ? true : false;
            Iterator tableExprListIter = tableExprList.iterator();
            while (tableExprListIter.hasNext()) {
                TableExpression tableExpr = (TableExpression) tableExprListIter.next();
                String exposedTableName = TableHelper.getExposedTableName(tableExpr);
                if (tableExpr instanceof TableInDatabase) {
                    /* Get the SQL model table object associated with the query model table object. */
                    TableInDatabase tableInDB = (TableInDatabase) tableExpr;
                    Table dbTable = tableInDB.getDatabaseTable();
                    
                    /* Get a list of columns for the table, and for each column create a
                     * SQLBuilderDBProposal object. */
                    if (dbTable != null) {
                        List colList = dbTable.getColumns();
                        Iterator colListIter = colList.iterator();
                        while (colListIter.hasNext()) {
                            Column colObj = (Column) colListIter.next();
                            SQLBuilderDBProposal proposal = new SQLBuilderDBProposal(colObj);
                            proposal.setParentName(exposedTableName);
                            if (multiTable == true) {
                                String name = proposal.getName();
                                name = exposedTableName + "." + name;
                                proposal.setName(name);
                            }
                            dbProposalsList.add(proposal);
                        }
                    }
                }
            }
        }
                
        return dbProposalsList;
    }
    
    // RATLC01136221 bgp 15Jan2007 - new method 
    /**
     * Gets a list of SQLDBProposal objects representing the tables in the current schema.
     * 
     * @return the available tables list
     */
    public List getAvailableTables() {
        List dbProposalsList = new ArrayList();
        
        IDBContext dbContext = getDBContext();
        if (dbContext != null && dbContext.getDomainModel() != null) {
            SQLDomainModel domainModel = dbContext.getDomainModel();
            Database db = domainModel.getDatabase();
            String schemaName = domainModel.getCurrentSchema();
            if (db != null && schemaName != null) {
                Schema defaultSchema = DatabaseHelper.findSchema(db, schemaName);
                if (defaultSchema != null) {
                    List tableList = defaultSchema.getTables();
                    Iterator tblListIter = tableList.iterator();
                    while (tblListIter.hasNext()) {
                        Table tableObj = (Table) tblListIter.next();
                        SQLDBProposal proposal = new SQLBuilderDBProposal(tableObj);
                        dbProposalsList.add(proposal);
                    }
                }
            }
        } 
        
        return dbProposalsList;
    }
        
    // [wsdbu00083212] bgp 22May2006
    /**
     * Gets a list of available column objects for the given schema and table name.
     * The table must be a table referenced in the FROM clause of a SELECT query 
     * or the target table of a INSERT, UPDATE, or DELETE query.  (Note that the
     * returned list is a list of SQL model column objects, not column name strings.)
     *  
     * @param schemaName the schema name of the table for which columns are wanted
     * @param tableName the table name for which columns are wanted
     * @return the list of available column objects
     */
    public List getAvailableColumnsForTable(String schemaName, String tableName) {
        List colObjList = new ArrayList();
        
        IDBContext dbContext = getDBContext();
        if (dbContext != null && dbContext.getDomainModel() != null) {
            SQLDomainModel domainModel = dbContext.getDomainModel();
            
            /* Get the current statement and get a list of table
             * expressions referenced in that statement.
             */
            QueryStatement statement = domainModel.getSQLStatement();
            List tableExprList = StatementHelper.getTablesForStatement( statement );
            
            /* Get the table expression with the given schema and table name. */
            TableExpression tableExpr = TableHelper.findTableExpressionInTableExpressionList(schemaName, tableName, tableExprList);
            if (tableExpr instanceof TableInDatabase) {
                /* Get the SQL model table object associated with the table expression. */
                TableInDatabase tableInDB = (TableInDatabase) tableExpr;
                Table tableObj = tableInDB.getDatabaseTable();
                if (tableObj != null) {
                    /* Get the column objects belonging to the table. */
                    colObjList = tableObj.getColumns();
                }
            } 
        }
        
        return colObjList;
    }
    
    // [wsdbu00055322] bgp 03May2006
    // [wsdbu00083212] bgp 22May2006
    /**
     * Gets a list of SQLDBProposal objects.
     * 
     * @param dbContextList a list of identifiers that give a context for the content
     * assist.  For example if user entered schema1.table1 then typed ".", then the list 
     * consists of two tokens, schema1 and table1.
     * @return the list of DB proposals
     */
    public List getDBObjectProposals( List dbContextList ) {
        List dbProposalsList = new ArrayList();
        
        SQLBuilderDBProposalsService dbProposalsService = getSQLDBProposalsService();
        if (dbProposalsService == null) {
            IDBContext dbContext = getDBContext();
            dbProposalsService = dbContext.getDBProposalsService();
            setSQLDBProposalsService( dbProposalsService );
        }
        
        if (dbContextList != null && dbContextList.size() > 0) {
            /* If a DB context list exists and contains two items (a schema name and table name)
             * then column proposals are wanted.  First see if we can get them 
             * from the query model rather than getting them from the database.
             */
            if (dbContextList.size() == 2) {
                String schemaName = (String) dbContextList.get(0);
                String tableName = (String) dbContextList.get(1);
                List colObjList = getAvailableColumnsForTable(schemaName, tableName);
                if (colObjList.size() > 0) {
                    Iterator colObjListIter = colObjList.iterator();
                    while (colObjListIter.hasNext()) {
                        Column colObj = (Column) colObjListIter.next();
                        SQLDBProposal proposal = new SQLBuilderDBProposal(colObj);
                        dbProposalsList.add(proposal);
                    }
                }
            }

            /* If we didn't get column proposals from the query model, then
            * go ahead and ask the DBProposalService for proposals.  It will
            * return schema names, table names, or column names, depending on
            * what's in the DB context list.
            */
            if (dbProposalsList.size() == 0) {
                if (dbProposalsService != null) {            
                    /* Load the DB proposals. */
                    if (dbProposalsService.populate( dbContextList )) {
                        dbProposalsList.addAll(dbProposalsService.getDBProposals());                
                    }           
                }                
            }
        }
        /* When we have no DB context list at all, get all available columns and schemas. */
        else {
            /* First get columns from the query model, if available. */
            dbProposalsList = getAvailableColumns();
            
            /* Add available tables to the list. */ 
            dbProposalsList.addAll(getAvailableTables());  // RATLC01136221 bgp 15Jan2007
            
            /* Get the available schemas from the DBProposalsService and add
             * them to the list. */
            if (dbProposalsService.populate( (List) null )) {
                dbProposalsList.addAll( dbProposalsService.getDBProposals());
            }
        }
        
        return dbProposalsList;
    }

    // [wsdbu00055322] bgp 05May2006
    /**
     * Gets the current DB context object.  The DB context has database 
     * connection information and the proposal service to use.
     * 
     * @return the current DB context object
     */
    public IDBContext getDBContext() {
        return fDBContext;
    }

    // [wsdbu00055322] bgp 05May2006
    /**
     * Sets the current DB context to the given object.  The DB context
     * has database connection information and the proposal service to use.
     * 
     * @param dbContext the DB context to set
     */
    public void setDBContext(IDBContext dbContext) {
        fDBContext = dbContext;
    }
    
    //[wsdbu00055322] bgp 04May2006
    /**
     * Gets the current SQLDBProposalsService object.  The
     * SQLDBProposalsService provides lists of database objects (tables,
     * columns).
     * 
     * @return the current SQLDBProposalsService object
     */
    public SQLBuilderDBProposalsService getSQLDBProposalsService() {
        return fSQLDBProposalsService;
    }
    
    // [wsdbu00055322] bgp 04May2006
    /**
     * Sets the current SQLDBProposalsService to the given object.  The
     * SQLDBProposalsService provides lists of database objects (tables,
     * columns).
     * 
     * @param sqlDBProposalsService the SQLDBProposalsService object to set
     */
    public void setSQLDBProposalsService(SQLBuilderDBProposalsService sqlDBProposalsService) {
        fSQLDBProposalsService = sqlDBProposalsService;
    }
}
