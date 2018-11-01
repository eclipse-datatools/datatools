/*******************************************************************************
 * Copyright (c) 2005-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.navigator.actions;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.IExtensionStateModel;
import org.eclipse.ui.navigator.INavigatorContentService;

/**
 * @author shongxum, brianf
 * 
 */
public class ShowCategoryAction implements IViewActionDelegate {

	IViewPart view = null;
	Object currentInput = ResourcesPlugin.getWorkspace().getRoot();
	boolean currentState = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
		this.view = view;
		if (view instanceof CommonNavigator) {
			IExtensionStateModel stateModel = ((CommonNavigator)view).getNavigatorContentService().findStateModel(ConnectivityUIPlugin.SERVERS_VIEW_CONTENT_EXTENSION_ID);
			//set default show_category stateprop to true
			stateModel.setBooleanProperty(ConnectivityUIPlugin.PROP_SHOW_CATEGORIES,
					currentState);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		currentState = action.isChecked();
		changeShowCategorySetting(currentState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		action.setEnabled(true);
		action.setChecked(currentState);
	}

	/**
	 * Set the show category setting on the content provider.
	 * 
	 * @param flag
	 */
	private void changeShowCategorySetting(boolean flag) {
		if (view instanceof CommonNavigator) {
			CommonNavigator commonNav = (CommonNavigator) view;
			if (currentInput == null) {
				// do nothing
			}
			else if (currentInput instanceof ICategory
					|| currentInput instanceof ProfileManager
					|| currentInput instanceof IWorkspaceRoot
					|| currentInput instanceof IConnectionProfile) {
				INavigatorContentService ncs = commonNav
						.getNavigatorContentService();
				IExtensionStateModel stateModel = ncs
						.findStateModel(ConnectivityUIPlugin.SERVERS_VIEW_CONTENT_EXTENSION_ID);

				stateModel.setBooleanProperty(ConnectivityUIPlugin.PROP_SHOW_CATEGORIES,
						flag);

				commonNav.getCommonViewer().refresh();
				if (flag)
					commonNav.getCommonViewer().expandToLevel(2);
			}
		}
	}
}