/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IProfileListener;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.ui.RefreshProfileJob;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.navigator.CommonViewer;

public class CPRepositoryContentProvider implements ITreeContentProvider {

	private Viewer mViewer;
	private Set mRepositories = new HashSet();

	private IProfileListener mProfileListener = new IProfileListener() {

		public void profileAdded(IConnectionProfile profile) {
			if (isChildProfile(profile)) {
				profile.addPropertySetListener(mPropertyListener);
				refreshViewer(profile, true);
			}
		}

		public void profileDeleted(IConnectionProfile profile) {
			if (isChildProfile(profile)) {
				profile.removePropertySetListener(mPropertyListener);
				refreshViewer(profile, false);
			}
		}

		public void profileChanged(IConnectionProfile profile) {
			if (isChildProfile(profile)) {
				refreshViewer(profile, false);
			}
		}

		private boolean isChildProfile(IConnectionProfile profile) {
			if (profile.getParentProfile() == null || !profile.isConnected()) {
				return false;
			}
			IManagedConnection imc = profile
					.getManagedConnection(IConnectionProfileRepository.class
							.getName());
			return imc != null && mRepositories.contains(imc.getConnection());
		}

	};
	private IPropertySetListener mPropertyListener = new IPropertySetListener() {

		public void propertySetChanged(IPropertySetChangeEvent event) {
			handleProfilePropertyChanged(event);
		}
	};

	public CPRepositoryContentProvider() {
		super();
		ProfileManager.getInstance().addProfileListener(mProfileListener);
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IConnectionProfileRepository) {
			if (!mRepositories.contains(parentElement)) {
				for (Iterator it = Arrays.asList(
						((IConnectionProfileRepository) parentElement)
								.getProfiles()).iterator(); it.hasNext();) {
					((IConnectionProfile) it.next())
							.addPropertySetListener(mPropertyListener);
				}
				mRepositories.add(parentElement);
			}
			return ((IConnectionProfileRepository) parentElement).getProfiles();
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		if (element instanceof IConnectionProfileRepository) {
			return ((IConnectionProfileRepository) element)
					.getRepositoryProfile();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
		ProfileManager.getInstance().removeProfileListener(mProfileListener);
		for (Iterator repIt = mRepositories.iterator(); repIt.hasNext();) {
			for (Iterator it = Arrays
					.asList(
							((IConnectionProfileRepository) repIt.next())
									.getProfiles()).iterator(); it.hasNext();) {
				((IConnectionProfile) it.next())
						.removePropertySetListener(mPropertyListener);
			}
		}
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		mViewer = viewer;
	}

	protected void refreshViewer(final IConnectionProfile profile,
			final boolean expand) {
		if (mViewer instanceof TreeViewer && profile != null) {
			RefreshProfileJob.scheduleRefreshProfileJob(profile,
					(TreeViewer) mViewer);
		}
		else {
			Runnable runner = new Runnable() {

				public void run() {
					((CommonViewer) mViewer).refresh();
				}
			};
			mViewer.getControl().getDisplay().syncExec(runner);
		}
	}

	protected void handleProfilePropertyChanged(IPropertySetChangeEvent event) {
		if (IConnectionProfile.CONNECTION_PROFILE_PROPERTY_SET.equals(event
				.getPropertySetType())
				&& event
						.getChangedProperty(IConnectionProfile.CONNECTED_PROPERTY_ID) != null) {
			refreshViewer(event.getConnectionProfile(), false);
		}
	}

}
