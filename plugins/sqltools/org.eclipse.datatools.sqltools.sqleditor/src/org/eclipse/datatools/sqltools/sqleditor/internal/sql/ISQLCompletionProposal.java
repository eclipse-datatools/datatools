/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

/**
 * @author Li Huang
 *
 * A completion proposal with a relevance value.
 * The relevance value is used to sort the completion proposals. Proposals with higher relevance
 * should be listed before proposals with lower relevance.
 * 
 * @see org.eclipse.jface.text.contentassist.ICompletionProposal
 */
public interface ISQLCompletionProposal extends ICompletionProposal
{

    /**
	 * Returns the relevance of this completion proposal.
	 * <p> 
	 * The relevance is used to determine if this proposal is more
	 * relevant than another proposal.</p>
	 * 
	 * @return the relevance of this completion proposal
	 */
    int getRelevance();
}
