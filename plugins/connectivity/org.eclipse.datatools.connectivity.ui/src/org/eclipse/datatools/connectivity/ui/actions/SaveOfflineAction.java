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

import java.util.Iterator;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

public class SaveOfflineAction extends BaseSelectionListenerAction implements
		IObjectActionDelegate, IActionDelegate2 {

	private ISelectionProvider mSelectionProvider;

	public SaveOfflineAction() {
		super(ConnectivityUIPlugin.getDefault().getResourceString(
				"DATATOOLS.SERVER.UI.EXPLORER.UPDATE_OFFLINE"));//$NON-NLS-1$
		setToolTipText(getText());
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		setSelectionProvider(targetPart.getSite().getSelectionProvider());
	}

	public void setSelectionProvider(ISelectionProvider selectionProvider) {
		if (mSelectionProvider != null) {
			mSelectionProvider.removeSelectionChangedListener(this);
		}
		mSelectionProvider = selectionProvider;
		if (mSelectionProvider != null) {
			mSelectionProvider.addSelectionChangedListener(this);
			ISelection selection = mSelectionProvider.getSelection();
			selectionChanged(selection instanceof IStructuredSelection ? (IStructuredSelection) selection
					: StructuredSelection.EMPTY);
		}
	}

	public void run() {
		if (!isEnabled()) {
			return;
		}

		for (Iterator it = getStructuredSelection().iterator(); it.hasNext();) {
			((IConnectionProfile) it.next()).saveWorkOfflineData(null);
		}
	}

	protected boolean updateSelection(IStructuredSelection selection) {
		boolean enabled = !selection.isEmpty();
		for (Iterator it = selection.iterator(); enabled && it.hasNext();) {
			Object obj = it.next();
			enabled = obj instanceof IConnectionProfile
					&& ((IConnectionProfile) obj).supportsWorkOfflineMode()
					&& ((IConnectionProfile) obj).getConnectionState() == IConnectionProfile.CONNECTED_STATE;
		}
		return enabled && super.updateSelection(selection);
	}

	public void run(IAction action) {
		run();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection)
			selectionChanged((IStructuredSelection) selection);
		else
			selectionChanged(StructuredSelection.EMPTY);
	}

	public void dispose() {
		if (mSelectionProvider != null) {
			mSelectionProvider.removeSelectionChangedListener(this);
		}
		mSelectionProvider = null;
	}

	public void init(IAction action) {
	}

	public void runWithEvent(IAction action, Event event) {
		run();
	}

}
