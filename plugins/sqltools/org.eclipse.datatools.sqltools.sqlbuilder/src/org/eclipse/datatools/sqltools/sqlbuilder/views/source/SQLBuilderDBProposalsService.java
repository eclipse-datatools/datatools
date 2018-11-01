/*******************************************************************************
 * Copyright © 2006, 2007 IBM Corporation and others.
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.editor.contentassist.ISQLDBProposalsService;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.DatabaseHelper;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLDBProposal;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLDBProposalsService;
import org.eclipse.emf.common.util.EList;

/**
 * This class extends SQLDBProposalsService in the SQLEditor package in order
 * to override the "populate" method.  In the SQL Builder context we don't
 * want to do a "reestablishConnection" action, since that momentarily pops up
 * a progress dialog even when the connection is already established.  This 
 * interferes with the content assist popup and causes the SQL validation
 * to kick in.
 * 
 * @author bgp
 */
public class SQLBuilderDBProposalsService/* extends SQLDBProposalsService */{

    protected static int PROPOSAL_TYPE_INVALID = -1;
    protected static int PROPOSAL_TYPE_TABLES  = 1;
    protected static int PROPOSAL_TYPE_COLUMNS = 2;
    
    private ISQLEditorConnectionInfo fConnInfo;
    private List fDBProposalList;
    private String fImpliedSchemaName;
    
// RATLC01136221 bgp 10Jan2007 - new class
    /* Comparator for ordering SQLDBProposal objects. */
    private static class SQLDBProposalComparator implements Comparator {

        public int compare(Object o1, Object o2) {
            SQLDBProposal c1 = (SQLDBProposal) o1;
            SQLDBProposal c2 = (SQLDBProposal) o2;
            return c1.getName().compareTo(c2.getName());
        }
    };
    private Comparator fComparator;

    /**
     * Constructs an instance of this class with the given
     * <code>ISQLEditorConnectionInfo</code> object.
     * 
     * @param connInfo the <code>ISQLEditorConnectionInfo</code> object to use
     */
    public SQLBuilderDBProposalsService( ISQLEditorConnectionInfo connInfo ) {
        this( connInfo, null );
    }

    /**
     * Constructs an instance of this class with the given
     * <code>ISQLEditorConnectionInfo</code> object and default (implied) schema name.
     * 
     * @param connInfo the <code>ISQLEditorConnectionInfo</code> object to use
     * @param schemaName the default (implied) schema name
     */
    public SQLBuilderDBProposalsService( ISQLEditorConnectionInfo connInfo , String schemaName) { 
        fConnInfo = connInfo;
        fImpliedSchemaName = schemaName;
        fDBProposalList = new ArrayList();
        fComparator = new SQLDBProposalComparator(); // RATLC01136221 bgp 10Jan2007
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
            if (fImpliedSchemaName == null) {
                proposalType = PROPOSAL_TYPE_TABLES;                
            } else {    
                proposalType = PROPOSAL_TYPE_TABLES | PROPOSAL_TYPE_COLUMNS;                
            }    
        } else  if (tokenList.size() == 2) {
            proposalType = PROPOSAL_TYPE_COLUMNS;            
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
     * Gets a <code>Schema</code> object with the given name from the given the 
     * database.
     *
     * @param database the database to search for the named schema 
     * @param schemaName the name of the schema to find
     * @return the <code>Schema</code> object with the given name
     */
    protected Schema getSchema( Database database, String schemaName) {
        List schemaList = DatabaseHelper.getSchemaList(database);
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
     * Gets the default (implied) schema name.
     * Implements <code>ISQLDBProposalsService</code> interface.
     *      
     * @return the default (implied) schema name
     * @see ISQLDBProposalsService#getSchemaName()
     */
    public String getSchemaName() {
        return fImpliedSchemaName;
    }

    // RATLC01136221 bgp 10Jan2007 - new method
    /**
     * Gets a comparator for SQLDBProposal objects.
     * @return the comparator
     */
    protected Comparator getSQLDBProposalComparator() {
        return fComparator;
    }
    
    // RATLC01136221 bgp 10Jan2007 - new method
    /**
     * Creates and stores a list of <code>SQLDBProposal</code> objects for each 
     * available schema.  Retrieve the list using getDBProposals().
     * The current list of proposals, if any, is cleared.
     */
   protected void loadSchemas() {
        loadSchemas( true );
    }

    // RATLC01136221 bgp 10Jan2007 - new method
    /**
     * Creates and stores a list of <code>SQLBuilderDBProposal</code> objects for each 
     * available schema.  Retrieve the list using getDBProposals().
     * 
     * @param clear when true, clear the existing list of proposals before loading
     */
   protected void loadSchemas( boolean clear ) {
       List dbProposalList = this.getDBProposals();
       if (clear){               
           dbProposalList.clear();
       }

       /* Get schemas names from the SQL model. */
       List schemaProposalList = new ArrayList();
       ISQLEditorConnectionInfo conn = this.getConnectionInfo();
       Database db = conn.getDatabase();
       List schemaList = DatabaseHelper.getSchemaList(db);
       Iterator schemaListIter = schemaList.iterator();
       while (schemaListIter.hasNext()) {
           Schema schema = (Schema) schemaListIter.next();
           schemaProposalList.add( new SQLBuilderDBProposal( schema ));
       }
       
       /* Order the list of schema names. */
       Comparator c = getSQLDBProposalComparator();
       Collections.sort(schemaProposalList, c);
       
       /* Add the schemas to the master proposal list. */
       dbProposalList.addAll( schemaProposalList );
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
           List dbProposalList = this.getDBProposals();
           if (clear) {               
               dbProposalList.clear();
           }
           
           EList columns = table.getColumns();
           for (int j = 0; j < columns.size(); j++) {
               Column column = (Column) columns.get( j );                
               dbProposalList.add( new SQLBuilderDBProposal( column ) );
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
          List dbProposalList = this.getDBProposals();
          if (clear){               
              dbProposalList.clear();
          }
          EList tables = schema.getTables();

          for (int i = 0; i < tables.size(); i++) {
              Table table = (Table) tables.get( i );                
              dbProposalList.add( new SQLBuilderDBProposal( table ) );
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
     * This method overrides the superclass method to not try to reestablish a
     * connection.  We assume that the connection is available.
     * 
     * @param tokenList list of strings indicating the start of the expression for 
     * which the user wants DB proposals 
     * @return true if DB items were retrieved, otherwise false
     * @see SQLDBProposalsService#populate()
     * @see ISQLDBProposalsService#populate()
     */
    public boolean populate( List tokenList ) {
        boolean populated = false;  // RATLC01136221 bgp 10Jan2007
        
        // RATLC01136221 bgp 10Jan2007 - begin
        /* If we have a DB context (schema or schema.table) then call the superclass
         * to get the list of DB proposals (tables or columns). */
        if (tokenList != null && tokenList.size() > 0) {
            this.loadDBProposals( tokenList ); 
        }
        /* Otherwise simply get a list of available schema names. */
        else {
            this.loadSchemas();
        }
        // RATLC01136221 bgp 10Jan2007 - end
        
        /* Let the caller know if there are any proposals. */
        List dbProposalsList = this.getDBProposals();
        if (dbProposalsList.size() > 0) {
            populated = true;
        }
            
        return populated;
    }
    
    // RATLC01136221 bgp 10Jan2007 - new method
    /**
     * Creates and stores a list of <code>SQLBuilderDBProposal</code> objects for the available
     * schemas, tables, and columns of the currently connected database.  Retrieve the 
     * list using getDBProposals().  The current list of proposals, if any, is cleared.
     * 
     * @param tokenList list of strings indicating the start of the expression for 
     * which the user wants DB proposals
     */
    protected void loadDBProposals( List tokenList ) {
        Database db = getConnectionInfo().getDatabase();
        
        getDBProposals().clear();  
        if (db != null) {        
            int proposalsType = determineProposalType( tokenList );            
            
            Schema schema = getSchema(db, (String) tokenList.get(0)); 
            if ((proposalsType & PROPOSAL_TYPE_TABLES) == PROPOSAL_TYPE_TABLES) {                        
                loadTables( schema, false );
            }
             
            if ((proposalsType & PROPOSAL_TYPE_COLUMNS) == PROPOSAL_TYPE_COLUMNS) {
                String tableToken = null;
                if (tokenList.size() == 1 && getSchemaName() != null) {
                    schema = getSchema( db, getSchemaName() );
                    tableToken = (String)tokenList.get(0);
                } else if (tokenList.size() >= 0) {
                    tableToken = (String)tokenList.get(1);
                }
                
                if (tableToken != null && tableToken.trim().length() > 0) {
                    Table table = null;                    
                    EList tables = schema.getTables();
                    for (int i = 0; i < tables.size() ; i++) {
                        table = (Table)tables.get(i);
                        if (table.getName().equalsIgnoreCase(tableToken)) {
                           loadColumns( table, false ); 
                           break;
                        }
                    }                   
                }    
            }
        }     
    }
    
    /**
     * Sets the default (implied) schema name to the given name.  This is used
     * to qualify tables when the shema is not present.  For example, if 
     * setSchema("ABC") has been is called and the user enters XYZ followed by 
     * ".", then the generated list of DB proposals consists of all tables under the schema IDENTIFIER1 and all columns in IDENTIFIERA.IDENTIFIER1 if such table exists
     *
     * @param schemaName the default (implied) schema name to set
     */
    public void setSchemaName( String schemaName ) {
        if (schemaName != null && schemaName.trim().length() > 0)
            fImpliedSchemaName = schemaName;
        else 
            fImpliedSchemaName = null;
    }
    
}
