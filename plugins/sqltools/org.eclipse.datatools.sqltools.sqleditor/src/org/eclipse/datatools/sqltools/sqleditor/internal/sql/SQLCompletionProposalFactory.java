/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.sqltools.editor.contentassist.ISQLDBProposalsService;
import org.eclipse.datatools.sqltools.editor.contentassist.SQLDBProposalsRequest;

public final class SQLCompletionProposalFactory {
    
    private final static String[] fStatements = {};
    private final static String[] fContextInformation = {};
    private ArrayList fDBProposalList = new ArrayList();
    private ISQLDBProposalsService fDBProposalsService;

    /**
     * Constructs an instance of this class. This is the default constructor.
     */
    public SQLCompletionProposalFactory() {
        super();
    }

    /**
     * Gets proposals based on SQL statement type. This is to be implemented in
     * the future if we want to provide proposals based on statement type. For
     * example valid keywords for SELECT statement
     * 
     * @return array of proposals based on statement type
     */
    public String[] getStatementProposals() {
        return fStatements;
    }

    /**
     * Gets context information as an array of strings.
     * 
     * @return the context information array
     */
    public String[] getContextInformation() {
        return fContextInformation;
    }

    /**
     * Gets a list of <code>DBProposal</code> objects.
     * 
     * @param tokenList list of tokens entered by user to invoke the content assist. For example if user
     * entered schema1.table1 then the ArrayList consists of 2 tokens schema1, and table1.
     * @return the list of <code>DBProposal</code> objects
     */
    public List getDBObjectProposals( SQLDBProposalsRequest request ) {
        
        fDBProposalList.clear();
        if (fDBProposalsService != null) {            
            // Load DB proposals            
            if (fDBProposalsService.populate( request )) {
                fDBProposalList.addAll(fDBProposalsService.getDBProposals());                
            }           
        }

        return fDBProposalList;
    }

    /**
     * Sets the <code>DBProposalsService</code> to use.
     *      
     * @param dbProposalsService the <code>DBProposalsService</code> to use
     */
    public void setFactoryDBContext( ISQLDBProposalsService dbProposalsService ) {
        fDBProposalsService = dbProposalsService;
    }
    
    /**
     * Get the <code>DBProposalsService</code> to use.
     *      
     * @return IDBProposalsService the <code>DBProposalsService</code> to use
     */
    public ISQLDBProposalsService getDBProposalsService( ) {
        return fDBProposalsService;
    }
}