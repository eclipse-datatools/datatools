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
package org.eclipse.datatools.connectivity.ui.dse.actions;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.ui.dse.views.ConnectionProfileContentProvider;
import org.eclipse.datatools.connectivity.ui.dse.views.DataSourceExplorerView;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.internal.extensions.NavigatorContentProvider;

/**
 * @author shongxum, brianf
 * 
 */
public class ShowCategoryAction implements IViewActionDelegate {

	DataSourceExplorerView mView = null;
	IViewPart view = null;
	Object currentInput = ResourcesPlugin.getWorkspace().getRoot();
	boolean currentState = true;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
		if (view instanceof DataSourceExplorerView)
			mView = (DataSourceExplorerView) view;
		this.view = view;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		if (mView != null) {
			boolean check = action.isChecked();
			mView.setShowCategory(check);
		}
		else {
			boolean check = action.isChecked();
			changeShowCategorySetting(check);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		action.setEnabled(true);
	}
	
	/**
	 * Set the show category setting on the content provider.
	 * @param flag
	 */
	private void changeShowCategorySetting ( boolean flag ) {
		if (view instanceof CommonNavigator) {
			CommonNavigator commonNav = (CommonNavigator) view;
			if (currentInput == null) {
				// do nothing
			}
			else if (currentInput instanceof ICategory ||
					currentInput instanceof ProfileManager ||
					currentInput instanceof IWorkspaceRoot ||
					currentInput instanceof IConnectionProfile ) {
				ITreeContentProvider[] providers = 
					commonNav.getNavigatorContentService().findRootContentProviders(currentInput);
				if (providers != null && providers.length > 0) {
					for (int i = 0; i < providers.length; i++) {
						NavigatorContentProvider ncp = (NavigatorContentProvider) providers[i];
						if (ncp.getDelegateContentProvider() instanceof ConnectionProfileContentProvider ) {
							ConnectionProfileContentProvider provider =
								(ConnectionProfileContentProvider) ncp.getDelegateContentProvider();
							provider.setShowCategories(flag);
						}
					}
				}
				commonNav.getCommonViewer().refresh();
				if (flag)
					commonNav.getCommonViewer().expandToLevel(2);
			}
		}
	}
}