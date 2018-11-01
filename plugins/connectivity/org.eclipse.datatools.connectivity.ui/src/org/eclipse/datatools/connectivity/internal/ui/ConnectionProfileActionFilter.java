/*******************************************************************************
 * Copyright (c) 2005-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 * 				brianf - adding new properties (read-only repository)
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.core.expressions.IPropertyTester;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
import org.eclipse.datatools.connectivity.ui.IConnectionProfileActionFilter;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Connection Profile action filter allows you to filter by profile
 * properties in plugin.xml.
 * @author rcernich, brianf
 *
 */
public class ConnectionProfileActionFilter extends PropertyTester 
	implements IConnectionProfileActionFilter, IPropertyTester {

	private static boolean mDebug = ConnectivityPlugin.getDefault().isDebugging();

	/**
	 * Constructor 
	 */
	public ConnectionProfileActionFilter() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionFilter#testAttribute(java.lang.Object, java.lang.String, java.lang.String)
	 */
	public boolean testAttribute(Object target, String name, String value) {
		if (target == null || !(target instanceof IConnectionProfile)) {
			return false;
		}
		IConnectionProfile profile = (IConnectionProfile) target;
		debug("profile testAttribute: name =" + name + ", value = " + value); //$NON-NLS-1$ //$NON-NLS-2$
		if (name.equals(PROFILE_PROPERTY_PROFILE_TYPE_ID) || name.equals(TYPE_ID)) {
			return profile.getProviderId().equals(value);
		}
		else if (name.equals(PROFILE_PROPERTY_CATEGORY_ID) || name.equals(CATEGORY_ID)) {
			return profile.getCategory().getId().equals(value);
		}
		else if (name.equals(PROFILE_PROPERTY_HAS_EXTENDED_PROPERTIES) || name.equals(HAS_EXTENDED_PROPERTIES)) {
			return profile.getProvider().getProfileExtensions().size() != 0;
		}
		else if (name.equals(PROFILE_PROPERTY_PROPERTY_EXTENSION_ID) || name.equals(EXTENSION_ID)) {
			return profile.getProvider().getProfileExtensions().containsKey(
					value);
		}
		else if (name.equals(PROFILE_PROPERTY_MAINTAIN_CONNECTION) || name.equals(MAINTAIN_CONNECTION)) {
			return profile.getProvider().needsMaintainConnection();
		}
		else if (name.equals(PROFILE_PROPERTY_IS_CONNECTED) || name.equals(IS_CONNECTED)) {
			return profile.getConnectionState() == IConnectionProfile.CONNECTED_STATE; //isConnected();
		}
		else if (name.equals(PROFILE_PROPERTY_SUPPORTS_PING) || name.equals(SUPPORTS_PING)) {
			return profile.getProvider().getConnectionFactories().containsKey(
					ConnectionProfileConstants.PING_FACTORY_ID);
		}
		else if (name.equals(PROFILE_PROPERTY_FACTORY_ID) || name.equals(FACTORY_ID)) {
			return profile.getProvider().getConnectionFactories().containsKey(
					value);
		}
		else if (name.equals(PROFILE_PROPERTY_VIEW_ID) || name.equals(VIEW_ID)) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = window.getActivePage();
			IWorkbenchPart part = page.getActivePart();
			if (part instanceof IViewPart) {
				IViewPart view = (IViewPart) part;
				String viewID = view.getViewSite().getId();
				return viewID.equals(value);
			}
			return false;
		}
		else if (name.equals(PROFILE_PROPERTY_CONNECTION_STATE) || name.equals(CONNECTION_STATE)) {
			return profile.getConnectionState() == getConnectionStateFromString(value);
		}
		else if (name.equals(PROFILE_PROPERTY_DB_VENDOR) || name.equals(DB_VENDOR)){
			return profile.getBaseProperties().getProperty("org.eclipse.datatools.connectivity.db.vendor")	//$NON-NLS-1$	
						.equals(value);
		}
		else if (name.equals(PROFILE_PROPERTY_DB_VERSION) || name.equals(DB_VERSION)){
			return profile.getBaseProperties().getProperty("org.eclipse.datatools.connectivity.db.version")	//$NON-NLS-1$	
						.equals(value);
		}
		else if (name.equals(PROFILE_PROPERTY_REPOSITORY_IS_READ_ONLY) || name.equals(REPOSITORY_IS_READ_ONLY)) {
			IManagedConnection imc = profile
				.getManagedConnection(IConnectionProfileRepositoryConstants.REPOSITORY_CONNECTION_FACTORY_ID);
			if (imc != null && imc.isConnected()) {
				IConnectionProfileRepository repo = (IConnectionProfileRepository) imc
						.getConnection().getRawConnection();
				debug(PROFILE_PROPERTY_REPOSITORY_IS_READ_ONLY + ", value = " + repo.isReadOnly()); //$NON-NLS-1$
				return repo.isReadOnly();
			}
			else {
				IConnectionProfileRepository repo = ((ConnectionProfile)profile).getRepository();
				if (repo != null) {
					debug(PROFILE_PROPERTY_REPOSITORY_IS_READ_ONLY + ", value = " + repo.isReadOnly()); //$NON-NLS-1$
					return repo.isReadOnly();
				}
			}
			debug(PROFILE_PROPERTY_REPOSITORY_IS_READ_ONLY + ", value = " + false); //$NON-NLS-1$
			return false;
		}
		else if (name.equals(PROFILE_PROPERTY_CAN_WORK_OFFLINE) || name.equals(CAN_WORK_OFFLINE)) {
			return profile.supportsWorkOfflineMode();
		}
		else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		return testAttribute(receiver, property, expectedValue == null ? null : expectedValue.toString());
	}
	
	/**
	 * @param value
	 * @return
	 */
	private int getConnectionStateFromString(String value) {
		if ("DISCONNECTED".equalsIgnoreCase(value)) {//$NON-NLS-1$
			return IConnectionProfile.DISCONNECTED_STATE;
		}
		else if ("CONNECTED".equalsIgnoreCase(value)) {//$NON-NLS-1$
			return IConnectionProfile.CONNECTED_STATE;
		}
		else if ("WORKING_OFFLINE".equalsIgnoreCase(value)) {//$NON-NLS-1$
			return IConnectionProfile.WORKING_OFFLINE_STATE;
		}
		return -1;
	}

	/**
	 * @param msg
	 */
	public static void debug ( String msg ) {
		if (mDebug)
			System.out.println("Debug: " + msg); //$NON-NLS-1$
	}

}
