/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.cp.ui.actions;

import java.io.File;

import org.eclipse.datatools.connectivity.ui.navigator.actions.RefreshActionProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

public class RefreshDirectoryActionProvider extends RefreshActionProvider {

	private RefreshDirectoryAction addRefreshAction;
	
	public RefreshDirectoryActionProvider() {
		super();
	}
		
	public void fillContextMenu(IMenuManager manager) {
		IStructuredSelection selection = 
			(IStructuredSelection) getContext().getSelection();
		if (selection.getFirstElement() instanceof File) {
			File file = (File) selection.getFirstElement();
			if (file.isDirectory()) {
				this.addRefreshAction.setActionContext(getContext());
				this.addRefreshAction.setViewer(getViewer());
				manager.appendToGroup("slot3", this.addRefreshAction);
			}
		}
		return;
	}

	public void fillActionBars(IActionBars bars) {
		bars.setGlobalActionHandler(ActionFactory.REFRESH.getId(),
				this.addRefreshAction);
        updateActionBars();
        return;
	}

	public void init(ICommonActionExtensionSite config) {
		super.init(config);
		makeActions();
	}

    /**
     * 
     */
    private void makeActions() {
		this.addRefreshAction = new RefreshDirectoryAction();
		this.addRefreshAction.setActionDefinitionId("org.eclipse.ui.file.refresh"); //$NON-NLS-1$
		this.addRefreshAction.setText("Refresh Directory");
		this.addRefreshAction.setViewer(getViewer());

    }

	/* (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
	 */
	public void updateActionBars() {
		this.addRefreshAction.setActionContext(getContext());
		this.addRefreshAction.setViewer(getViewer());
	}
}
