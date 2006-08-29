/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.internal.dialogs.PropertyDialog;
import org.eclipse.ui.internal.dialogs.PropertyPageContributorManager;
import org.eclipse.ui.internal.dialogs.PropertyPageManager;

/**
 * @author shongxum
 */
public class ViewPropertyAction extends Action {

	private TreeViewer mViewer;

	/**
	 * Constructor
	 */
	public ViewPropertyAction(Viewer viewer) {
		mViewer = (TreeViewer) viewer;
		setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ServersView.action.showproperties")); //$NON-NLS-1$
	}

	/*
	 * Facility method added in here so that dependency to eclipse internal api
	 * is kept to a minimum
	 */
	public static boolean hasContributors(Object selected) {
		if (selected == null || !(selected instanceof IAdaptable))
			return false;
		return PropertyPageContributorManager.getManager().hasContributorsFor(
				selected);
	}

	/*
	 * TODO: This should be fixed in the future, shouldn't call into internal
	 * packages, and LabelRetargetAction seems not applicable in here too. Has
	 * to figure out a way to retarget this action to "Properties" action.
	 * 
	 * @see org.eclipse.ui.IAction#run()
	 */
	public void run() {
		Object selected = getSelectedObject();
		if (selected == null || !(selected instanceof IAdaptable))
			return;

		IAdaptable adaptable = (IAdaptable) selected;
		PropertyPageManager pageManager = new PropertyPageManager();
		String title = ConnectivityUIPlugin.getDefault().getResourceString(
				"properties.dialog"); //$NON-NLS-1$

		// load pages for the selection
		// fill the manager with contributions from the matching contributors
		PropertyPageContributorManager.getManager().contribute(pageManager,
				adaptable);

		PropertyDialog propertyDialog = new PropertyDialog(mViewer.getControl()
				.getShell(), pageManager, mViewer.getSelection());
		propertyDialog.create();
		propertyDialog.getShell().setText(title);
		propertyDialog.open();

		mViewer.setSelection(mViewer.getSelection());
	}

	public Object getSelectedObject() {
		ISelection selection = mViewer.getSelection();
		Object selectedObj = null;
		if (selection == null)
			return null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			selectedObj = structuredSelection.getFirstElement();
		}
		return selectedObj;
	}
}