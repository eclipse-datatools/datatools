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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.actions.popup;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

/**
 * Overrides the refresh menu item for IVirtualNode and
 * SQLObject objects.
 * 
 * @author brianf
 *
 */
public class RefreshActionProvider extends CommonActionProvider {

	private StructuredViewer aViewer;
    private IStructuredSelection selection;
    private RevisedRefreshAction addRefreshAction = null;

    /**
     * Constructor
     */
    public RefreshActionProvider() {
		super();
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
			addRefreshAction = new RevisedRefreshAction(this.aViewer);
			addRefreshAction.setActionDefinitionId("org.eclipse.ui.file.refresh"); //$NON-NLS-1$
			manager.insertAfter("slot3",//$NON-NLS-1$
					addRefreshAction);
		}

		return;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ICommonActionProvider#fillActionBars(org.eclipse.ui.IActionBars)
	 */
	public void fillActionBars(IActionBars bars) {
		bars.setGlobalActionHandler(ActionFactory.REFRESH.getId(),
				addRefreshAction);
        bars.updateActionBars();
        bars.getMenuManager().update();
        return;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
	 */
	public void init(ICommonActionExtensionSite aConfig) {
        this.aViewer = aConfig.getStructuredViewer();
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
}
