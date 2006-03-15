/*
* Copyright (c) 2005. IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     IBM Corporation - initial API and implementation
*/

package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * Provides database identifier proposal services for the editor, to support code
 * completion.  These are services to construct and return a list of proposals of 
 * database objects (tables, columns) based on a given database connection.
 * 
 * This class implements the <code>ISQLDBProposalsService</code> interface.
 * 
 * @author Hetty Dougherty
 * @author bgp
 */
public class SQLDBProposalsService implements ISQLDBProposalsService {

    protected static int PROPOSAL_TYPE_INVALID = -1;
    protected static int PROPOSAL_TYPE_TABLES  = 1;
    protected static int PROPOSAL_TYPE_COLUMNS = 2;
    
    private ISQLEditorConnectionInfo fConnInfo;
    private List fDBProposalList;
    private List fTokenList;
    

    /**
     * Constructs an instance of this class with the given
     * <code>ISQLEditorConnectionInfo</code> object.
     * 
     * @param connInfo the <code>ISQLEditorConnectionInfo</code> object to use
     */
    public SQLDBProposalsService( ISQLEditorConnectionInfo connInfo) {
        super();       
        
        fConnInfo = connInfo;
        fDBProposalList = new ArrayList();
        fTokenList = null;
    }

    /**
     * Returns a value indicating whether tables only, columns only, or both
     * tables and columns are needed for the proposal list by examining the 
     * given list of tokens indicating the start of the expression for which
     * DB proposals are wanted.  The returned value contains the constants
     * PROPOSAL_TYPE_TABLES and PROPOSAL_TYPE_COLUMNS either alone or "ORed"
     * together, or PROPOSAL_TYPE_INVALID.
     * 
     * @param tokenList list of tokens indicating the start of the expression for 
     * which the user wants DB proposals
     * @return 
     */
    protected int determineProposalType( List tokenList ) {
        int proposalType = PROPOSAL_TYPE_INVALID;
        if (tokenList.size() == 1) {            
            if (this.fConnInfo.getDefaultSchemaName() == null) {
                proposalType = SQLDBProposalsService.PROPOSAL_TYPE_TABLES;                
            } else {    
                proposalType = SQLDBProposalsService.PROPOSAL_TYPE_TABLES | SQLDBProposalsService.PROPOSAL_TYPE_COLUMNS;                
            }    
        } else  if (tokenList.size() == 2) {
            proposalType = SQLDBProposalsService.PROPOSAL_TYPE_COLUMNS;            
        }   
        
        return proposalType;
    }

   
    /**
     * Gets the <code>ISQLEditorConnectionInfo</code> used to provide content assist.  
     * Implements <code>ISQLDBProposalsService</code> interface.
     * 
     * @return the current <code>ISQLEditorConnectionInfo</code> object
     * @see ISQLDBProposalsService#getConnectionInfo()
     */
    public ISQLEditorConnectionInfo getConnectionInfo() {
        return fConnInfo;
    }

    /**
     * Gets the list of <code>SQLDBProposal</code> objects for the content assist proposals.
     * Implements <code>ISQLDBProposalsService</code> interface.
     * 
     * @return the list of proposals
     * @see ISQLDBProposalsService#getDBProposals()
     */
    public List getDBProposals() {
        return fDBProposalList;      
    }
    
    /**
     * Gets a <code>Schema</code> object with the given name from the given the 
     * database.
     *
     * @param database the database to search for the named schema 
     * @param schemaName the name of the schema to find
     * @return the <code>Schema</code> object with the given name
     */
    protected Schema getSchema( Database database, String schemaName) {
        EList schemaList = database.getSchemas();
        Schema schema = null;
        for (int i = 0; i < schemaList.size(); i++) {
            Schema thisSchema = (Schema)schemaList.get(i);
            if (thisSchema.getName().equalsIgnoreCase( schemaName )) {
                schema = thisSchema;
                break;                        
            }
        }
        return schema;
    }

   
    /**
     * Gets the current token list. The token list is a of strings indicating the start of the expression for 
     * which the user wants DB proposals
     * 
     * @return the token list 
     */
    public List getTokenList() {
        return fTokenList;
    }
    
    /**
     * Creates and stores a list of <code>SQLDBProposal</code> objects for each 
     * column in the given table.  Retrieve the list using getDBProposals().
     * The current list of proposals, if any, is cleared.
     * 
     * @param table the <code>Table</code> object for which columns are needed
     */
   protected void loadColumns( Table table ) {
        loadColumns( table, true );
    }
    
    /**
     * Creates and stores a list of <code>SQLDBProposal</code> objects for each 
     * column in the given table.  Retrieve the list using getDBProposals().
     * 
     * @param table the <code>Table</code> object for which columns are needed     
     * @param clear when true, clear the existing list of proposals before loading
     */
   protected void loadColumns( Table table, boolean clear ) {
        if (table != null) {
            if (clear) {               
                fDBProposalList.clear();
            }
            
            EList columns = table.getColumns();
            for (int j = 0; j < columns.size(); j++) {
                Column column = (Column) columns.get( j );                
                fDBProposalList.add( new SQLDBProposal( column ) );
            }
        }
    }

    /**
     * Creates and stores a list of <code>SQLDBProposal</code> objects for the available
     * schemas, tables, and columns of the currently connected database.  Retrieve the 
     * list using getDBProposals().  The current list of proposals, if any, is cleared.
     * 
     * @param tokenList list of strings indicating the start of the expression for 
     * which the user wants DB proposals
     */
    protected void loadDBProposals( List tokenList ) {
        fDBProposalList.clear();
        Database database = this.fConnInfo.getDatabase();
        String fImpliedSchemaName = this.fConnInfo.getDefaultSchemaName();
        if (database != null) {        
            int proposalsType = determineProposalType( tokenList );            

            Schema schema = getSchema(database, (String) tokenList.get(0)); 
            if ((proposalsType & SQLDBProposalsService.PROPOSAL_TYPE_TABLES) == SQLDBProposalsService.PROPOSAL_TYPE_TABLES) {                        
                loadTables( schema, false );
            }
             
            if ((proposalsType & SQLDBProposalsService.PROPOSAL_TYPE_COLUMNS) == SQLDBProposalsService.PROPOSAL_TYPE_COLUMNS) {
                String tableToken = null;
                if (tokenList.size() == 1 && fImpliedSchemaName != null) {
                    schema = getSchema( database, fImpliedSchemaName );
                    tableToken = (String)tokenList.get(0);
                } else if (tokenList.size() >= 0) {
                    tableToken = (String)tokenList.get(1);
                }
                
                if (tableToken != null && tableToken.trim().length() > 0) {
                    Table table = null;                    
                    EList tables = schema.getTables();
                    for (int i = 0; i < tables.size() ; i++) {
                        table = (Table)tables.get(i);
                        loadColumns( table, false );  
                    }                   
                }    
            }
        }     
    }
    
    /**
     * Creates and stores a list of <code>SQLDBProposal</code> objects for each 
     * table associated with the given schema.  Retrieve the list using getDBProposals().
     * The current list of proposals, if any, is cleared.
     * 
     * @param schema the <code>Schema</code> object for which tables are needed
     */
   protected void loadTables( Schema schema ) {
        loadTables( schema, true );
    }

    /**
     * Creates and stores a list of <code>SQLDBProposal</code> objects for each 
     * table associated with the given schema.  Retrieve the list using getDBProposals().
     * 
     * @param schema the <code>Schema</code> object for which tables are needed
     * @param clear when true, clear the existing list of proposals before loading
     */
   protected void loadTables( Schema schema, boolean clear ) {
        if (schema != null) {
            if (clear){               
                fDBProposalList.clear();
            }
            EList tables = schema.getTables();

            for (int i = 0; i < tables.size(); i++) {
                Table table = (Table) tables.get( i );                
                fDBProposalList.add( new SQLDBProposal( table ) );
            }
        }
    }

   
    /**
     * Populates the list database of database object proposals (schemas, tables, 
     * columns) using given list of tokens (DB identifiers) indicating the start of
     * the expression for which the user wants DB proposals. For example, if the
     * user provides the list (MYSCHEMA, TABLE1), the list of proposals will be the
     * columns of table MYSCHEMA.TABLE1.  Retrieve the list using getDBProposals().  
     * Implements <code>ISQLDBProposalsService</code> interface.
     * 
     * @param tokenList list of strings indicating the start of the expression for 
     * which the user wants DB proposals 
     * @return true if database objects were loaded, for example
     *         as a result of reestablishing a connection
     * @see ISQLDBProposalsService#populate()
     */
    public boolean populate( List tokenList ) {
        boolean loaded = false;

        /* If there isn't a context for the DB proposals, don't do anything. */
        if (tokenList.size() > 0) {
            /* Connect or reconnect to DB Server if necessary. */
            ISQLEditorConnectionInfo connInfo = getConnectionInfo();
            boolean connected = false;
            if (connInfo != null && connInfo.getConnectionProfile() !=null)
            {
            	connected = connInfo.getConnectionProfile().isConnected();
            }
            if (connected == true) {
                loaded = true;
                
                /* Get the proposals from the connected database. */
                fTokenList = tokenList;
                try {
                    IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
                    progressService.runInUI(
                            PlatformUI.getWorkbench().getProgressService(),
                            new IRunnableWithProgress() {
                                public void run(IProgressMonitor monitor) {
                                    List tknList = getTokenList();
                                    loadDBProposals( tknList ); 
                                }
                            },
                            null
                    );
                }
                catch(InterruptedException e) {
                    /* do nothing */
                }
                catch(InvocationTargetException e) {
                    /* do nothing */
                }
            }
        }
      
        return loaded;
    }
   
	public ISQLEditorConnectionInfo getSQLEditorConnectionInfo() {
		return fConnInfo;
	}
    
}