/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Point;

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
     * @param selection the range of the current selection in coordinates of this viewer's document
     *  
     */
    public ICompletionProposal[] computeProposals( IDocument doc, ITypedRegion partition, int documentOffset, Point selection );
    
    /**
     * Compute ContextInformation
     * 
     * @param doc The document
     * @param partition document partition
     * @param documentOffset current offset in the document
     * @param selection the range of the current selection in coordinates of this viewer's document
     *  
     */
    public IContextInformation[] computeContextInformation( IDocument doc, ITypedRegion partition, int documentOffset, Point selection );
}