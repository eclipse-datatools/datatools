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

import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
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
					disConnectSubProfiles(profile);
					profile.disconnect(null);
				}
			}
		}
	}

	private void disConnectSubProfiles(IConnectionProfile parent) {
		if (!parent.getCategory().getId().equals(
				IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID)) {
			return;
		}
		IConnectionProfile[] profiles = InternalProfileManager.getInstance()
				.getProfiles(true);
		for (int i = 0; i < profiles.length; i++) {
			ConnectionProfile profile = (ConnectionProfile) profiles[i];
			IConnectionProfileRepository repository = profile.getRepository();
			if (repository != null) {
				if (repository.getRepositoryProfile() != null
						&& repository.getRepositoryProfile() == parent
						&& profile.isConnected()) {
					profile.disconnect();
					//notify listeners to remove the contentExtension from the mProfileToExtension map.
					InternalProfileManager.getInstance().fireProfileDeleted(profile);
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
				if (profile.getConnectionState() == IConnectionProfile.DISCONNECTED_STATE)
					return false;
			}
		}
		return true;
	}
}