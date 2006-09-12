/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardNode;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizard;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ProfileWizardProvider;
import org.eclipse.datatools.connectivity.ui.wizards.IWizardCategoryProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * Ideally, this class should be splitted into two, one is for Action, the other
 * is for View Action.
 * 
 * @author shongxum, brianf
 */
public class AddProfileViewAction extends Action implements IViewActionDelegate {

	private ICategory category;

	/**
	 * 
	 */
	public AddProfileViewAction() {
		setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ServersView.action.newCP")); //$NON-NLS-1$
	}

	/**
	 * @param category
	 */
	public AddProfileViewAction(ICategory category) {
		this();
		this.category = category;
	}
	
	/**
	 * @param categoryID
	 */
	public AddProfileViewAction ( String categoryID ) {
		this();
		this.category = 
			ConnectionProfileManager.getInstance().getCategory(categoryID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart viewpart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		NewCPWizard wizard;
		WizardDialog wizardDialog;

		ViewerFilter viewerFilter = new ViewerFilter() {

			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				if (category == null)
					return true;
				CPWizardNode wizardNode = (CPWizardNode) element;
				if (!(wizardNode.getProvider() instanceof IWizardCategoryProvider)) {
					ICategory cat = ConnectionProfileManager.getInstance()
							.getProvider(
									((ProfileWizardProvider) wizardNode
											.getProvider()).getProfile())
							.getCategory();
					// Only display wizards belong to a specific category or a
					// parent category
					while (cat != null) {
						if (cat.getId().equals(category.getId()))
							return true;
						else
							cat = cat.getParent();
					}
				}
				else {
					if (((IWizardCategoryProvider) wizardNode.getProvider())
							.getId().equals(category.getId()))
						return true;
				}
				return false;
			}
		};
		wizard = new NewCPWizard(viewerFilter);
		wizardDialog = new WizardDialog(ConnectivityUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
		wizardDialog.setBlockOnOpen(true);
		wizardDialog.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}
	
	/**
	 * @param category
	 */
	public void setCategory ( ICategory category ) {
		this.category = category;
	}
	
	/**
	 * @param categoryID
	 */
	public void setCategory ( String categoryID ) {
		this.category = 
			ConnectionProfileManager.getInstance().getCategory(categoryID);
	}

	/**
	 * @return
	 */
	public ICategory getCategory () {
		return this.category;
	}
	
}