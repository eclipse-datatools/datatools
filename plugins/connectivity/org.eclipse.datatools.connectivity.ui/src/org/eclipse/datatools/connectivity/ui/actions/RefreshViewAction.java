/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonNavigator;

/**
 * @author shongxum, brianf
 * 
 */
public class RefreshViewAction extends Action implements IObjectActionDelegate {

	protected Object m_selobj = null;
	protected IViewPart view = null;
	private StructuredViewer aViewer;
	
	/**
	 * Constructor
	 * @param viewer
	 */
	public RefreshViewAction ( StructuredViewer viewer ) {
		super();
		setText(ConnectivityUIPlugin.getDefault().getResourceString("DSE.Refresh.label"));//$NON-NLS-1$
		setToolTipText(this.getText());
		this.setActionDefinitionId(ActionFactory.REFRESH.getId());
		this.setAccelerator(SWT.F5);
		this.aViewer = viewer;
	}
	
	/**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
	public void run() {
    	if (this.aViewer != null) {
    		selectionChanged(this, this.aViewer.getSelection());
    	}

    	if (m_selobj != null) {
			if (view != null && view instanceof CommonNavigator ) {
				CommonNavigator nav = (CommonNavigator) view;
				nav.getCommonViewer().refresh(m_selobj);
			}
			else if (aViewer != null) {
				aViewer.refresh(m_selobj);
			}
		}
	}

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.dse.actions.NavigatorActionBase#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
		m_selobj = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1) {
				m_selobj = structuredSelection.getFirstElement();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		if (targetPart instanceof CommonNavigator) {
			this.view = (IViewPart) targetPart;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}

}
