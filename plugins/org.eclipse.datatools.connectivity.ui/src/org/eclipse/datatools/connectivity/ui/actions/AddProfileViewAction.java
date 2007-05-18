/*******************************************************************************
 * Copyright (c) 2005-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IProfileListener;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPCategoryWizardNode;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardSelectionPage;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizard;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizardCategoryFilter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
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

	private String categoryID;
	private IConnectionProfile parentProfile;
	private int returnCode;
	private IConnectionProfile addedProfile;

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
		this(category.getId());
	}
	
	/**
	 * @param categoryID
	 */
	public AddProfileViewAction ( String categoryID ) {
		this.categoryID = categoryID;
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
		IWizard wizard = getDefaultWizard(new String(), categoryID);
		WizardDialog wizardDialog;

		ViewerFilter viewerFilter = new NewCPWizardCategoryFilter(categoryID);
		if (wizard == null) {
			wizard = new NewCPWizard(viewerFilter,parentProfile);
		}
		wizardDialog = new WizardDialog(ConnectivityUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
		wizardDialog.setBlockOnOpen(true);
		
		InternalProfileListener listener = new InternalProfileListener();
		ProfileManager.getInstance().addProfileListener(listener);
		
		returnCode = wizardDialog.open();
		
		addedProfile = listener.cachedProfile;
		
		ProfileManager.getInstance().removeProfileListener(listener);
		
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
		setCategory(category == null ? null : category.getId());
	}
	
	/**
	 * @param categoryID
	 */
	public void setCategory ( String categoryID ) {
		this.categoryID = categoryID;
	}

	public void setParentProfile(IConnectionProfile profile) {
		parentProfile = profile;
	}
	
	public IConnectionProfile getParentProfile() {
		return parentProfile;
	}
	
	/**
	 * @return
	 */
	public int getWizardReturnCode() {
		return this.returnCode;
	}
	
	/**
	 * @return
	 */
	public IConnectionProfile getAddedProfile() {
		return this.addedProfile;
	}
	
	private IWizard getDefaultWizard(String parentCategoryID, String categoryID) {
		List wizardNodes = new CPWizardSelectionPage(new String())
				.getCatagoryItems(parentCategoryID);
		if (categoryID != null) {
			ViewerFilter filter = new NewCPWizardCategoryFilter(categoryID);
			for (Iterator it = wizardNodes.iterator(); it.hasNext();) {
				if (!filter.select(null, null, it.next())) {
					it.remove();
				}
			}
		}
		if (wizardNodes == null || wizardNodes.size() == 0) {
			return null;
		}
		if (wizardNodes.size() > 1) {
			return null;
		}
		IWizardNode wizardNode = (IWizardNode) wizardNodes.get(0);
		if (wizardNode instanceof CPCategoryWizardNode) {
			IWizard wizard = getDefaultWizard(
					((CPCategoryWizardNode) wizardNode).getProvider()
							.getCategory(), null);
			if (wizard == null) {
				return wizardNode.getWizard();
			}
			else {
				return wizard;
			}
		}
		return wizardNode.getWizard();
	}
	
	/**
	 * Internal listener to listen for the new profile
	 * @author brianf
	 *
	 */
	private class InternalProfileListener implements IProfileListener {

		protected IConnectionProfile cachedProfile;
		
		public void profileAdded(IConnectionProfile profile) {
			cachedProfile = profile;
		}

		public void profileChanged(IConnectionProfile profile) {
			// ignore
		}

		public void profileDeleted(IConnectionProfile profile) {
			// ignore
		}
	}
}