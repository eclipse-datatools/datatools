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

import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

/**
 * @author shongxum, brianf
 */
public class DisconnectAction implements IActionDelegate {
	
	private IStructuredSelection mSelection = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		if (mSelection != null && allProfilesInSelectionAreConnected(mSelection)) {
			List list = mSelection.toList();
			if (list == null || list.size() == 0) 
				return;
			
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof IConnectionProfile) {
					IConnectionProfile profile = (IConnectionProfile) list.get(i);
					profile.disconnect(null);
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		IStructuredSelection sel = (IStructuredSelection) selection;
		mSelection = sel;
		boolean flag = checkSelectionForProfiles(mSelection);
		boolean allAreConnected = false;
		if (flag) {
			allAreConnected = allProfilesInSelectionAreConnected(mSelection);
		}
		action.setEnabled(flag && allAreConnected);
	}

	private boolean checkSelectionForProfiles ( IStructuredSelection selection ) {
		List list = selection.toList();
		if (list == null || list.size() == 0) 
			return false;
		
		for (int i = 0; i < list.size(); i++) {
			if (!(list.get(i) instanceof IConnectionProfile)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean allProfilesInSelectionAreConnected ( IStructuredSelection selection ) {
		List list = selection.toList();
		if (list == null || list.size() == 0) 
			return false;
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof IConnectionProfile) {
				IConnectionProfile profile = (IConnectionProfile) list.get(i);
				if (!profile.isConnected())
					return false;
			}
		}
		return true;
	}
}