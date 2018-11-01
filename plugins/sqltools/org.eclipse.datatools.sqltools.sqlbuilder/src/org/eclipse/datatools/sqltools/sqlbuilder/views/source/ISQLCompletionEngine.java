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

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

public interface ISQLCompletionEngine {

    // RATLC01136221 bgp 10Jan2007 - new method
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
    public ICompletionProposal[] computeDBProposals(IDocument doc, ITypedRegion partition, int documentOffset, IDBContext dbcontext);
    
    // RATLC01136221 bgp 10Jan2007 - new method
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
    public ICompletionProposal[] computeSyntaxProposals(IDocument doc, ITypedRegion partition, int documentOffset );
}