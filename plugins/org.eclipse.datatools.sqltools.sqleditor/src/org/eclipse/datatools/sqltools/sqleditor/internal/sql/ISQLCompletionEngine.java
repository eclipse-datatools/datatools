/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

/**
 * Compute proposals.
 * 
 * @author Hetty Dougherty
 *  
 */
public interface ISQLCompletionEngine {

    /**
     * Compute proposals
     * 
     * @param doc The document
     * @param partition document partition
     * @param documentOffset current offset in the document
     *  
     */
    public ICompletionProposal[] computeProposals( IDocument doc, ITypedRegion partition, int documentOffset );
}