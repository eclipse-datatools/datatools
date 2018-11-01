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
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.ui.ProfileImageRegistry;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.ibm.icu.text.MessageFormat;

public class RepositoriesDropList extends DropListViewer {

	private RepositoriesFilter mRepositoriesFilter;

	public RepositoriesDropList(ConnectionProfileProvider profileProvider,
								Composite parent) {
		super(parent);
		setContentProvider(new RepositoriesContentProvider());
		setLabelProvider(new RepositoriesLabelProvider());
		mRepositoriesFilter = new RepositoriesFilter(profileProvider);
		addFilter(mRepositoriesFilter);
	}

	public boolean hasRepositories() {
		return mRepositoriesFilter.hasRepositories();
	}

	private static class RepositoriesContentProvider implements
			IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			return (Object[]) inputElement;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

	private static class RepositoriesLabelProvider extends LabelProvider {

		public Image getImage(Object element) {
			IConnectionProfile profile = (IConnectionProfile) element;
			return ProfileImageRegistry.getInstance().getProfileImage(
					profile.getProvider());
		}

		public String getText(Object element) {
			IConnectionProfile profile = (IConnectionProfile) element;
			StringBuffer sb = new StringBuffer(profile.getName());
			if (profile.getConnectionState() != IConnectionProfile.DISCONNECTED_STATE) {
				Properties props = profile
						.getProperties(ConnectionProfileConstants.VERSION_INFO_PROFILE_EXTENSION_ID);
				String serverName = props
						.getProperty(ConnectionProfileConstants.PROP_SERVER_NAME);
				Version serverVersion = Version
						.valueOf(props
								.getProperty(ConnectionProfileConstants.PROP_SERVER_VERSION));
				if ((serverName != null && serverName.length() > 0)
						|| serverVersion != Version.NULL_VERSION) {
					String versionStr = MessageFormat
							.format(
									ConnectivityUIPlugin
											.getDefault()
											.getResourceString(
													"CommonLabelProviderBase.label.version"), //$NON-NLS-1$
									new String[] { profile.getName(),
											serverName,
											serverVersion.toString()});

					sb = new StringBuffer(versionStr);
				}
			}
			return sb.toString();
		}

	}

	private class RepositoriesFilter extends ViewerFilter {

		private ConnectionProfileProvider mProfileProvider;
		private boolean mHasRepositories;

		public RepositoriesFilter(ConnectionProfileProvider profileProvider) {
			mProfileProvider = profileProvider;
		}

		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			ConnectionProfile repo = (ConnectionProfile) element;
			IManagedConnection managedConnection = repo
					.getManagedConnection(IConnectionProfileRepository.class
							.getName());
			IConnectionProfileRepository repoConn = managedConnection
					.isConnected() ? ((IConnectionProfileRepository) managedConnection
					.getConnection().getRawConnection())
					: null;
			boolean retVal = repoConn != null
					&& !repoConn.isReadOnly()
					&& (mProfileProvider == null || (mProfileProvider
							.compatibleWithRepository(repo) && repoConn
							.supportsProfileType(mProfileProvider.getId())));
			mHasRepositories |= retVal;
			return retVal;
		}

		public boolean hasRepositories() {
			return mHasRepositories;
		}

	}

}
