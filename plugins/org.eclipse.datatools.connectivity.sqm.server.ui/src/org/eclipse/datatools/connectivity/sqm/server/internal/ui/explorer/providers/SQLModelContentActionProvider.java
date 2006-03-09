/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.INavigatorContentService;


public class SQLModelContentActionProvider extends CommonActionProvider {
	
	private PropertyDialogAction mPropertiesAction;
	private ICommonViewerSite mViewSite;
	private StructuredViewer mStructViewer;

	public SQLModelContentActionProvider() {
		super();
	}

	public void init(String anExtensionId, IViewPart aViewPart,
			INavigatorContentService aContentService,
			StructuredViewer aStructuredViewer) {
		mPropertiesAction = new PropertyDialogAction(
				mStructViewer.getControl().getShell(), 
				mViewSite.getSelectionProvider());
	}

	public void dispose() {
	}

	public void setActionContext(ActionContext aContext) {
	}

	public void fillContextMenu(IMenuManager aMenu) {
		aMenu.add(new Separator("additions")); //$NON-NLS-1$
		aMenu.add(mPropertiesAction);
		return;
	}

	public void fillActionBars(IActionBars theActionBars) {
		return;
	}

	public void restoreState(IMemento aMemento) {
	}

	public void saveState(IMemento aMemento) {
	}

	public void init(ICommonActionExtensionSite aConfig) {
		this.mViewSite = aConfig.getViewSite();
		this.mStructViewer = aConfig.getStructuredViewer();

		mPropertiesAction = new PropertyDialogAction(
				mStructViewer.getControl().getShell(), 
				mViewSite.getSelectionProvider());
	}

}
