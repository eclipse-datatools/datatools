/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

public class WorkOfflineActionProvider extends CommonActionProvider {

	public static final String OFFLINE_MENU_GROUP = "slotOffline"; //$NON-NLS-1$

	private WorkOfflineAction mWorkOfflineAction;
	private SaveOfflineAction mSaveOfflineAction;

	public void init(ICommonActionExtensionSite aSite) {
		super.init(aSite);
		mWorkOfflineAction = new WorkOfflineAction();
		mWorkOfflineAction.setSelectionProvider(aSite.getViewSite()
				.getSelectionProvider());

		mSaveOfflineAction = new SaveOfflineAction();
		mSaveOfflineAction.setSelectionProvider(aSite.getViewSite()
				.getSelectionProvider());
	}

	public void dispose() {
		mWorkOfflineAction.dispose();
		mWorkOfflineAction = null;
		mSaveOfflineAction.dispose();
		mSaveOfflineAction = null;
		super.dispose();
	}

	public void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
		IStructuredSelection selection = getContext().getSelection() instanceof IStructuredSelection ? (IStructuredSelection) getContext()
				.getSelection()
				: StructuredSelection.EMPTY;
		mWorkOfflineAction.selectionChanged(selection);
		mSaveOfflineAction.selectionChanged(selection);
		menu.appendToGroup(OFFLINE_MENU_GROUP, mWorkOfflineAction);
		menu.appendToGroup(OFFLINE_MENU_GROUP, mSaveOfflineAction);
	}

}
