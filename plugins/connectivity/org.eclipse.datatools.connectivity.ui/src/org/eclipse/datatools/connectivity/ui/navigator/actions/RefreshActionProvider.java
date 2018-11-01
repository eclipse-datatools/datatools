/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.navigator.actions;

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.LocalRepositoryNode;
import org.eclipse.datatools.connectivity.ui.actions.RefreshViewAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

/**
 * Adds a refresh menu item to all DSE objects. This refresh
 * action can then be overridden as appropriate by profile
 * implementers.
 * 
 * @author brianf
 *
 */
public class RefreshActionProvider extends CommonActionProvider {

	private StructuredViewer aViewer;
    private IStructuredSelection selection;
    private RefreshViewAction addRefreshAction;
    
    /**
     * Constructor
     */
    public RefreshActionProvider() {
		super();
	}
    
    /**
     * 
     */
    private void makeActions() {
		addRefreshAction = new RefreshViewAction(aViewer);
		addRefreshAction.setActionDefinitionId("org.eclipse.ui.file.refresh"); //$NON-NLS-1$
    }

	/**
	 * @param selection
	 */
	private void setSelection ( IStructuredSelection selection ) {
        this.selection = selection;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ICommonActionProvider#setActionContext(org.eclipse.ui.actions.ActionContext)
	 */
	public void setActionContext(ActionContext aContext) {
        if (aContext.getSelection() instanceof IStructuredSelection)
        {
        	setSelection((IStructuredSelection) aContext.getSelection());
        }
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ICommonActionProvider#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager manager) {
		boolean hasSelection = false;
		if (selection != null && selection.getFirstElement() != null)
			hasSelection = true;
		if (hasSelection ) {
			
			// add the refresh action to everything, but only 
			// enable it by default for ICategory or IConnectionProfile nodes
			boolean isCategory = selection.getFirstElement() instanceof ICategory;
			boolean isProfile = selection.getFirstElement() instanceof IConnectionProfile;
			boolean isLocalRepoNode = selection.getFirstElement() instanceof LocalRepositoryNode;
			if (!isCategory && !isProfile && !isLocalRepoNode) {
				addRefreshAction.setEnabled(false);
			}
			else {
				addRefreshAction.setEnabled(true);
			}
			
			manager.insertAfter("slot3", //$NON-NLS-1$
					(IAction) addRefreshAction);
		}

		return;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ICommonActionProvider#fillActionBars(org.eclipse.ui.IActionBars)
	 */
	public void fillActionBars(IActionBars bars) {
		bars.setGlobalActionHandler(ActionFactory.REFRESH.getId(),
				addRefreshAction);
        updateActionBars();
        return;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
	 */
	public void init(ICommonActionExtensionSite aConfig) {
        this.aViewer = aConfig.getStructuredViewer();
        makeActions();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionGroup#setContext(org.eclipse.ui.actions.ActionContext)
	 */
	public void setContext(ActionContext context) {
		super.setContext(context);
		if (context != null && context.getSelection() instanceof IStructuredSelection)
        {
        	setSelection((IStructuredSelection) context.getSelection());
        }
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
	 */
	public void updateActionBars() {
		IStructuredSelection selection = (IStructuredSelection) getContext()
				.getSelection();

		addRefreshAction.selectionChanged(addRefreshAction, selection);
	}
	
	public StructuredViewer getViewer() {
		return this.aViewer;
	}
}
