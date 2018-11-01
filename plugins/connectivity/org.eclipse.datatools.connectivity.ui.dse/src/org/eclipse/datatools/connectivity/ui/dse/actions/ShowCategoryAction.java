/*******************************************************************************
 * Copyright (c) 2005-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 * 			brianf - adding handler support
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.dse.actions;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandlerWithState;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import org.eclipse.core.commands.State;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.ui.dse.DSEPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.IExtensionStateModel;
import org.eclipse.ui.navigator.INavigatorContentService;

/**
 * @author shongxum, brianf
 * 
 */
public class ShowCategoryAction extends AbstractHandlerWithState 
	implements IViewActionDelegate, IElementUpdater {

	IViewPart view = null;
	boolean hasBeenSet = false;
	Object currentInput = ResourcesPlugin.getWorkspace().getRoot();
	boolean currentState = true;
	
	public static String HANDLER_ID = 
		"org.eclipse.datatools.connectivity.commands.showcategory"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
		this.view = view;
		if (view instanceof CommonNavigator && !hasBeenSet) {
			IExtensionStateModel stateModel = ((CommonNavigator)view).getNavigatorContentService().findStateModel(DSEPlugin.SERVERS_VIEW_CONTENT_EXTENSION_ID);
			//set default show_category stateprop to true
			stateModel.setBooleanProperty(DSEPlugin.PROP_SHOW_CATEGORIES,
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
		setCurrentState(currentState);
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
	 * Get the current state
	 * @return
	 */
	private boolean getCurrentState() {
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
					.findStateModel(DSEPlugin.SERVERS_VIEW_CONTENT_EXTENSION_ID);

				return stateModel.getBooleanProperty(DSEPlugin.PROP_SHOW_CATEGORIES);
			}
		}
		return true; // default to true
	}

	/**
	 * Set the internal state to the passed in flag
	 * @param flag
	 */
	private void setCurrentState ( boolean flag ) {
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
				.findStateModel(DSEPlugin.SERVERS_VIEW_CONTENT_EXTENSION_ID);

				stateModel.setBooleanProperty(DSEPlugin.PROP_SHOW_CATEGORIES,
						flag);

				commonNav.getCommonViewer().refresh();
				if (flag)
					commonNav.getCommonViewer().expandToLevel(2);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart part = HandlerUtil.getActivePart(event);
		if (part == null && view == null) 
			return null;
		else if (part instanceof IViewPart && view == null)
			init((IViewPart)part);
		else if (view != null) {
			hasBeenSet = true;
		}
		if (!hasBeenSet) {
			hasBeenSet = true;
		}
		else {
			currentState = !getCurrentState();
		}

		setCurrentState(currentState);

		// update our radio button states ... get the service from
		// a place that's most appropriate
		ICommandService service = (ICommandService) HandlerUtil
			.getActiveWorkbenchWindowChecked(event).getService(
				ICommandService.class);
		service.refreshElements(event.getCommand().getId(), null);
		super.fireHandlerChanged(new HandlerEvent(this, false, false));
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 */
	public boolean isEnabled() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#isHandled()
	 */
	public boolean isHandled() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.commands.IElementUpdater#updateElement(org.eclipse.ui.menus.UIElement, java.util.Map)
	 */
	public void updateElement(UIElement element, Map parameters) {
		element.setChecked(getCurrentState());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandlerWithState#handleStateChange(org.eclipse.core.commands.State, java.lang.Object)
	 */
	public void handleStateChange(State arg0, Object arg1) {
		if (arg0 != null) {
			if (arg0.getValue() instanceof Boolean) {
				setCurrentState(((Boolean)arg0.getValue()).booleanValue());
			}
		}
	}
}