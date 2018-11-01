/*
* Copyright (c) 2005. IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License 2.0
* which accompanies this distribution, and is available at
* https://www.eclipse.org/legal/epl-2.0/
*
* Contributors:
*     IBM Corporation - initial API and implementation
*/

package org.eclipse.datatools.sqltools.editor.contentassist;

import java.util.List;

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;

/**
 * Provides services to retrieve, construct list of proposals of database
 * objects (schemas, tables, columns) based on a given connection info.
 * 
 * @author Hetty Dougherty
 *  
 */
public interface ISQLDBProposalsService {    

    /**
     * Gets the <code>ISQLEditorConnectionInfo</code> used to provide content assist.
     * 
     * @return the current <code>ISQLEditorConnectionInfo</code> object
     */
    ISQLEditorConnectionInfo getSQLEditorConnectionInfo();

    /**
     * Sets the <code>ISQLEditorConnectionInfo</code> used to provide content assist.
     */
    public void setSQLEditorConnectionInfo(ISQLEditorConnectionInfo connectionInfo);
    
    /**
	 * Populates the list database of database object proposals (schemas,
	 * tables, columns) using given list of tokens (DB identifiers) indicating
	 * the start of the expression for which the user wants DB proposals. For
	 * example, if the user provides the list (MYSCHEMA, TABLE1), the list of
	 * proposals will be the columns of table MYSCHEMA.TABLE1. Retrieve the list
	 * using getDBProposals().
	 * 
	 * @param tokenList
	 *            list of tokens indicating the start of the expression for
	 *            which the user wants DB proposals
	 * @param scope
	 *            the scope at current position indicating what kind of
	 *            information is required.
	 * @see ISQLParserConstants
	 * @return true if database objects have loaded, for example as a result of
	 *         reestablishing a connection, otherwise false
	 */
    public boolean populate( SQLDBProposalsRequest request );

    /**
     * Gets the list of <code>SQLDBProposal</code> objects for the content assist proposals.
     * Call populate to populate this list.
     * 
     * @return the list of proposals
     * @see populate( List tokenList )
     */
    List getDBProposals();
}
