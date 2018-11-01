/*******************************************************************************
 * Copyright (c) 2004-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 * 			IBM Corporation = bug fix #203829
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.navigator;

import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.internal.ui.CCOverLayIconUtility;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.LocalRepositoryNode;
import org.eclipse.datatools.connectivity.internal.ui.SharedImages;
import org.eclipse.datatools.connectivity.ui.ProfileImageRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.eclipse.ui.navigator.IExtensionStateModel;

import com.ibm.icu.text.MessageFormat;

/**
 * @author rcernich, brianf
 */
public class ConnectionProfileLabelProvider extends LabelProvider implements
		ICommonLabelProvider {

	private static String STATUSBAR_CONNECTED = "StatusBar.Connected"; //$NON-NLS-1$
	private static String STATUSBAR_OFFLINE = "StatusBar.WorkingOffline"; //$NON-NLS-1$
	private static String STATUSBAR_DISCONNECTED = "StatusBar.Disconnected"; //$NON-NLS-1$
	private static String STATUSBAR_MSG = "StatusBar.ConnectedMessage"; //$NON-NLS-1$

	public ConnectionProfileLabelProvider() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		Image image;
		if (element instanceof ICategory) {
			image = PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_FOLDER);
		}
		else if(element instanceof LocalRepositoryNode){
			image = SharedImages.get(SharedImages.IMG_LOCAL_REPOSITORY);
		}
		else if (element instanceof IConnectionProfile) {
			IConnectionProfile profile = (IConnectionProfile) element;
			image = ProfileImageRegistry.getInstance().getProfileImage(
					profile.getProvider());
			switch (profile.getConnectionState()) {
			case IConnectionProfile.CONNECTED_STATE: {
				ImageRegistry registry = ConnectivityUIPlugin.getDefault()
						.getImageRegistry();
				image = registry.get(profile.getName().concat(
						SharedImages.IMAGE_RUNNING));
				if (image == null) {
					image = CCOverLayIconUtility.getDefault().addOverlayIcon(
							ProfileImageRegistry.getInstance().getProfileImage(
									profile.getProvider()),
							CCOverLayIconUtility.RUNNING);
					registry.put(profile.getName().concat(
							SharedImages.IMAGE_RUNNING), image);
				}
			}
				break;
			case IConnectionProfile.WORKING_OFFLINE_STATE: {
				ImageRegistry registry = ConnectivityUIPlugin.getDefault()
						.getImageRegistry();
				image = registry.get(profile.getName().concat(
						SharedImages.IMAGE_OFFLINE));
				if (image == null) {
					image = CCOverLayIconUtility.getDefault().addOverlayIcon(
							ProfileImageRegistry.getInstance().getProfileImage(
									profile.getProvider()),
							CCOverLayIconUtility.OFFLINE);
					registry.put(profile.getName().concat(
							SharedImages.IMAGE_OFFLINE), image);
				}
			}
				break;
			}
		}
		else {
			image = null;
		}
		return image;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		String text = null;
		if (element instanceof ProfileManager) {
			text = ConnectivityUIPlugin.getDefault().
					getResourceString("DSE.View.Tooltip"); //$NON-NLS-1$
		}
		else if (element instanceof ICategory) {
			text = ((ICategory) element).getName();
		}
		else if(element instanceof LocalRepositoryNode){
			text = ConnectivityUIPlugin
			.getDefault()
			.getResourceString(
					"CommonLabelProviderBase.label.localRepository"); //$NON-NLS-1$
		}
		else if (element instanceof IConnectionProfile) {
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
				if ((serverName != null && serverName.length() > 0)) {
					String versionStr = null;
					if(serverVersion.compareTo(Version.NULL_VERSION) == 0) {
						versionStr = MessageFormat.format(
								ConnectivityUIPlugin.getDefault().getResourceString("CommonLabelProviderBase.label.version.versionless"),  //$NON-NLS-1$
								new String[] {sb.toString(),
									serverName});
					} else {
					versionStr = MessageFormat
							.format(
									ConnectivityUIPlugin
											.getDefault()
											.getResourceString(
													"CommonLabelProviderBase.label.version"), //$NON-NLS-1$
									new String[] { profile.getName(),
											serverName,
											serverVersion.toString()});
					}
					sb = new StringBuffer(versionStr);
				}
			}
			text = sb.toString();
			
			// for bug 234423, replace all newline \n characters with spaces
			text = text.replace('\n', ' ');
		}
		else {
			text = super.getText(element);
		}
		if (text != null && text.trim().length() > 0)
			text = TextProcessor.process(text);
		return text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.navigator.ICommonLabelProvider#init(org.eclipse.ui.navigator.IExtensionStateModel,
	 *      org.eclipse.jface.viewers.ITreeContentProvider)
	 */
	public void init(IExtensionStateModel aStateModel,
			ITreeContentProvider aContentProvider) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.navigator.IMementoAware#restoreState(org.eclipse.ui.IMemento)
	 */
	public void restoreState(IMemento aMemento) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.navigator.IMementoAware#saveState(org.eclipse.ui.IMemento)
	 */
	public void saveState(IMemento aMemento) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.navigator.IDescriptionProvider#getDescription(java.lang.Object)
	 */
	public String getDescription(Object element) {
		String desc;
		if (element instanceof ICategory) {
			desc = ((ICategory) element).getName();
		}
		else if (element instanceof IConnectionProfile) {
			IConnectionProfile profile = (IConnectionProfile) element;
			Properties props = profile.getBaseProperties();
			String serverName = props
					.getProperty(ConnectionProfileConstants.PROP_SERVER_NAME);
			Version serverVersion = Version
					.valueOf(props
							.getProperty(ConnectionProfileConstants.PROP_SERVER_VERSION));
			if ((serverName != null && serverName.length() > 0)
					|| serverVersion != Version.NULL_VERSION) {
				desc = MessageFormat.format(ConnectivityUIPlugin.getDefault()
						.getResourceString(
								"CommonLabelProviderBase.label.version"), //$NON-NLS-1$
						new String[] { profile.getName(), serverName,
								serverVersion.toString()});

			}
			else {
				desc = profile.getName();
			}

			// now add connected state to the status bar BZ 165333 - BTF
			String connectedState;
			switch (profile.getConnectionState()) {
			case IConnectionProfile.CONNECTED_STATE:
				connectedState = ConnectivityUIPlugin.getDefault()
						.getResourceString(STATUSBAR_CONNECTED);
				break;
			case IConnectionProfile.WORKING_OFFLINE_STATE:
				connectedState = ConnectivityUIPlugin.getDefault()
						.getResourceString(STATUSBAR_OFFLINE);
				break;
			case IConnectionProfile.DISCONNECTED_STATE:
			default:
				connectedState = ConnectivityUIPlugin.getDefault()
						.getResourceString(STATUSBAR_DISCONNECTED);
				break;
			}
			desc = ConnectivityUIPlugin.getDefault().getResourceString(
					STATUSBAR_MSG, new String[] { desc, connectedState});
		}
		else {
			desc = null;
		}
		if (desc != null && desc.trim().length() > 0)
			desc = TextProcessor.process(desc);
		return desc;
	}

	public void init(ICommonContentExtensionSite aConfig) {
	}
}