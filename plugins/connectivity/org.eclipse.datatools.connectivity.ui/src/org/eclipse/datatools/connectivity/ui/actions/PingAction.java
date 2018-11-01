/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.actions;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.datatools.connectivity.internal.ProfileExtensionProvider;
import org.eclipse.datatools.connectivity.ui.PingJob;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.SameShellProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

/**
 * @author shongxum, brianf
 */
public class PingAction implements IActionDelegate, ISelectionProvider {

	private Shell mParentShell = null;

	private IConnectionProfile mConnectionProfile = null;

	/**
	 * 
	 */
	public PingAction() {
		Display display = Display.getCurrent();
		mParentShell = display.getActiveShell();

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		PropertyDialogAction propertyDialogAction = new PropertyDialogAction(
				new SameShellProvider(mParentShell), this);
		propertyDialogAction
				.selectionChanged((IStructuredSelection) getSelection());
		if (!mConnectionProfile.arePropertiesComplete()
				&& propertyDialogAction.isApplicableForSelection()) {
			PreferenceDialog dialog = propertyDialogAction.createDialog();
			String initialPage = getInitialPropertyPageID();
			if (initialPage != null) {
				((IWorkbenchPreferenceContainer) dialog).openPage(
						initialPage, null);
			}
			if (dialog.open() == Dialog.CANCEL) {
				return;
			}
		}
		new PingJob(mParentShell, mConnectionProfile).schedule();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		mConnectionProfile = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1) {
				Object selectedResource = structuredSelection.getFirstElement();
				if (selectedResource instanceof IConnectionProfile) {
					mConnectionProfile = (IConnectionProfile) selectedResource;
					action.setEnabled(mConnectionProfile.getProvider().getConnectionFactories().containsKey(
							ConnectionProfileConstants.PING_FACTORY_ID));						
				}
			}
		}
	}

	protected String getInitialPropertyPageID() {
		if (!mConnectionProfile.arePropertiesComplete(mConnectionProfile.getProviderId())) {
			return ((ConnectionProfileProvider)mConnectionProfile.getProvider()).getPropertiesPersistenceHook().getConnectionPropertiesPageID();
		}
		for (Iterator it = mConnectionProfile.getProfileExtensions().entrySet().iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry)it.next();
			String extID = (String)entry.getKey();
			if (!mConnectionProfile.arePropertiesComplete(extID)) {
				return ((ProfileExtensionProvider)entry.getValue()).getPropertiesPersistenceHook().getConnectionPropertiesPageID();
			}
		}
		return null;
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
	}

	public ISelection getSelection() {
		return new StructuredSelection(mConnectionProfile);
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
	}

	public void setSelection(ISelection selection) {
	}
}