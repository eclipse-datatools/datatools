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

import java.util.Vector;

import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;


public class ConnectionContext implements IDBContext {

    private String fDBName = null;
    private Vector fQualifiedTableNames = null;
    private Vector fQualifiedColumnNames = null;
    // [wsdbu00055322] bgp 03May2006
    private SQLBuilderDBProposalsService fDBProposalsService = null; // RATLC01136221 bgp 10Jan2007
    private SQLDomainModel fDomainModel = null;

    /**
     * ConnectionContext constructor comment.
     */
    public ConnectionContext() {
        super();
        fQualifiedTableNames = new Vector();
        fQualifiedColumnNames = new Vector();

    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:11:23 PM)
     * @return boolean
     */
    public boolean canConnect() {
        return true;
    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:11:23 PM)
     * @return boolean
     */
    public void connect() {

        // MG** - under construction, to be competed post GA	

    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:11:23 PM)
     * @param statement java.lang.String
     */
    public void executeStatement(String statement) {

        // MG** under construction - complete post GA	

    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:11:23 PM)
     * @param statements java.lang.String[]
     */
    public void executeStatements(java.lang.String[] statements) {

        // MG** under construction - complete post GA	

    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:11:23 PM)
     * @return java.lang.String
     */
    public String getDatabaseName() {
        return fDBName;
    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:26:49 PM)
     * @return java.lang.String[]
     */
    public String[] getQualifiedColumnNames() {

        String[] columnNames = new String[fQualifiedColumnNames.size()];

        for (int i = 0; i < fQualifiedColumnNames.size(); i++) {
            columnNames[i] = (String) fQualifiedColumnNames.elementAt(i);
        }

        return columnNames;

    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:29:41 PM)
     * @return java.lang.String[]
     */
    public String[] getQualifiedTableNames() {

        String[] tableNames = new String[fQualifiedTableNames.size()];

        for (int i = 0; i < fQualifiedTableNames.size(); i++) {
            tableNames[i] = (String) fQualifiedTableNames.elementAt(i);
        }

        return tableNames;
    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:11:23 PM)
     * @return java.lang.String[]
     */
    public String[] getSchemaTableNames(String tableName) {
        return null;
    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:11:23 PM)
     * @return java.lang.String[]
     */
    public String[] getSchemataNames() {
        return null;
    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:11:23 PM)
     * @return java.lang.String[]
     * @param tableName java.lang.String
     */
    public String[] getTableColumnNames(String tableName) {
        return null;
    }

    /**
     * Insert the method's description here.
     * Creation date: (9/29/2001 9:11:23 PM)
     * @return boolean
     */
    public boolean isConnected() {
        return false;
    }
    
    // [wsdbu00055322] bgp 03May2006
    /**
     * Gets the DB proposals service associated with the connection.
     * The DB proposals service is used for content assist.
     * 
     * @return the current SQLDBProposalsService object
     */
    public SQLBuilderDBProposalsService getDBProposalsService() {  // RATLC01136221 bgp 10Jan2007
        return fDBProposalsService;
    }
    
    // [wsdbu00055322] bgp 03May2006
    /**
     * Sets the DB proposals service associated with the connection to
     * the given object.  The DB proposals service is used for content assist.
     * 
     * @param dbProposalsService the SQLDBProposalsService object to use
     */
    public void setDBProposalsService(SQLBuilderDBProposalsService dbProposalsService) { // RATLC01136221 bgp 10Jan2007
        fDBProposalsService = dbProposalsService;
    }
 
    // [wsdbu00055322] bgp 05May2006
    /**
     * Gets the domain model for the current SQL statement.  The
     * domain model contains the connection info, current statement
     * model, and much more.
     *  
     * @return the statement domain model
     */
    public SQLDomainModel getDomainModel() {
        return fDomainModel;
    }

    // [wsdbu00055322] bgp 05May2006
    /**
     * Sets the domain model for the current SQL statement.  The
     * domain model contains the connection info, current statement
     * model, and much more.
     *  
     * @param domainModel the statement domain model
     */
    public void setDomainModel(SQLDomainModel domainModel) {
        fDomainModel = domainModel;
    }

}