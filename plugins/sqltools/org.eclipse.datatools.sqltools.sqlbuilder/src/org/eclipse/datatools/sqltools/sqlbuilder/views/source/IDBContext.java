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


import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

/**
 * Copyright International Business Machines Corporation 2001. All rights
 * reserved.
 */
public interface IDBContext {

    /**
     * 
     * @return boolean
     */
    boolean canConnect();

    /**
     * 
     * @return boolean
     */
    public void connect();

    /**
     * 
     * @param statement
     *            java.lang.String
     */
    void executeStatement(String statement);

    /**
     * 
     * @param statements
     *            java.lang.String[]
     */
    void executeStatements(String[] statements);

    /**
     * 
     * @return java.lang.String
     */
    String getDatabaseName();

    /**
     * 
     * @return java.lang.String[]
     */
    public String[] getQualifiedColumnNames();

    /**
     * 
     * @return java.lang.String[]
     */
    String[] getQualifiedTableNames();

    /**
     * 
     * @return java.lang.String[]
     */
    String[] getSchemaTableNames(String tableName);

    /**
     * 
     * @return java.lang.String[]
     */
    String[] getSchemataNames();

    /**
     * 
     * @return java.lang.String[]
     * @param tableName
     *            java.lang.String
     */
    String[] getTableColumnNames(String tableName);

    /**
     * 
     * @return boolean
     */
    boolean isConnected();

    // [wsdbu00055322] bgp 03May2006
    /**
     * Gets the DB proposals service associated with this database context.
     * 
     * @return the SQLDBProposalsService object
     */
    SQLBuilderDBProposalsService getDBProposalsService(); // RATLC01136221 bgp 10Jan2007
    
    // [wsdbu00055322] bgp 05May2006
    /**
     * Gets the domain model for the current SQL statement.  The
     * domain model contains the connection info, current statement
     * model, and much more.
     *  
     * @return the statement domain model
     */
    SQLDomainModel getDomainModel();
}