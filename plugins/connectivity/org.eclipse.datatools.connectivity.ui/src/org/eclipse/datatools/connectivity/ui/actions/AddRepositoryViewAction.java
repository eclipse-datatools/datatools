/*******************************************************************************
 * Copyright (c) 2007-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;

/**
 * Adds a new profile repository
 * @author rcernich
 *
 */
public class AddRepositoryViewAction extends AddProfileViewAction {

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.actions.AddProfileViewAction#run()
	 */
	public void run() {
		setCategory(IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID);
		super.run();
	} 
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.actions.AddProfileViewAction#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		//ignore selection change here because we should always enable repository view
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.actions.AddProfileViewAction#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		setCategory(IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID);
		super.setUseSelection(false);
		return super.execute(event);
	}
}
