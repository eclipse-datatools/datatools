/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.actions;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.enablement.sybase.ISybaseJDBCConnectionProfileConstants;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAProfileMessages;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.navigator.CommonNavigator;

/**
 * Exposes a dialog to create multiple profiles if an ASA profile
 * exposes multiple databases.
 * 
 * @author brianf
 */
public class ProfileASAMultiDBAction implements IObjectActionDelegate {

	private IStructuredSelection mSelection = null;
	private Shell mShell = null;
	
	/**
	 * 
	 */
	public ProfileASAMultiDBAction() {
		// empty
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		StructuredSelection ssel = (StructuredSelection) this.mSelection;
		if (!ssel.isEmpty()) {
			Object element = ssel.getFirstElement();
			if (element instanceof IConnectionProfile) {
				IConnectionProfile profile = (IConnectionProfile) element;
				MultiASADialog dlg = new MultiASADialog(this.mShell);
				dlg.setProfile(profile);
				int rtn = dlg.open();
				if (rtn == Window.OK) {
					performFinish( profile, dlg.getDBNames());
				}
			}
		}
	}
	
	/**
	 * @param profile
	 * @param dbnames
	 * @return
	 */
	public boolean performFinish(IConnectionProfile profile, String[] dbnames) {
		boolean success = false;
		Properties props = profile.getBaseProperties();
		if (dbnames != null && dbnames.length > 0) {
			List names = Arrays.asList(dbnames);
			Connection conn = JDBCHelper.getConnectionForTempProfile(this.mShell, profile);
			String defaultName = JDBCHelper.getDefaultCatalogNameForProfile(conn);
			String host = props.getProperty(ISybaseJDBCConnectionProfileConstants.PROP_HOST);
			String port = props.getProperty(ISybaseJDBCConnectionProfileConstants.PROP_PORT);
			if (defaultName != null && defaultName.length() > 0) {
				if (names.contains(defaultName)) {
					String updatedUrl = JDBCASAPlugin.makeDriverURL(host, port, defaultName);
					props.setProperty(ISybaseJDBCConnectionProfileConstants.PROP_DB_NAME, defaultName);
					props.setProperty(ISybaseJDBCConnectionProfileConstants.URL_PROP_ID, updatedUrl);
					props.setProperty(ISybaseJDBCConnectionProfileConstants.DATABASE_NAME_PROP_ID, defaultName);
					profile.setBaseProperties(props);
					try {
						ProfileManager.getInstance().modifyProfile(profile);
					} catch (ConnectionProfileException e) {
						ExceptionHandler.showException(this.mShell, 
								JDBCASAProfileMessages.format("ProfileASAMultiDBAction.error.failedprofileupdate", //$NON-NLS-1$
										new String[] {profile.getName()}),
								e.getLocalizedMessage(), e);
						return false;
					}
				}
			}
			for (int i = 0; i < names.size(); i++) {
				String iter_dbname = (String) names.get(i);
				if (defaultName != null && defaultName.equals(iter_dbname))
					continue;
				String updatedUrl = JDBCASAPlugin.makeDriverURL(host, port, iter_dbname);
				props.setProperty(ISybaseJDBCConnectionProfileConstants.PROP_DB_NAME, iter_dbname);
				props.setProperty(ISybaseJDBCConnectionProfileConstants.URL_PROP_ID, updatedUrl);
				props.setProperty(ISybaseJDBCConnectionProfileConstants.DATABASE_NAME_PROP_ID, iter_dbname);
				try {
					String parent = ""; //$NON-NLS-1$
					if (profile.getParentProfile() != null) {
						parent = profile.getParentProfile().getName();
					}
					String newprofilename = profile.getName() + "." + iter_dbname; //$NON-NLS-1$
					boolean createIt = true;
					if (ProfileManager.getInstance().getProfileByName(newprofilename) != null) {
						boolean answer = MessageDialog.openQuestion(this.mShell, 
								JDBCASAProfileMessages.getString("ProfileASAMultiDBAction.error.title.profilealreadyexists"),  //$NON-NLS-1$
								JDBCASAProfileMessages.format("ProfileASAMultiDBAction.msg.profilealreadyexists", //$NON-NLS-1$
										new String[] {newprofilename}));
						if (answer) {
							createIt = false;
							IConnectionProfile oldProfile = ProfileManager.getInstance().getProfileByName(newprofilename);
							oldProfile.setBaseProperties(props);
							try {
								ProfileManager.getInstance().modifyProfile(oldProfile);
							} catch (ConnectionProfileException e) {
								ExceptionHandler.showException(this.mShell, 
										JDBCASAProfileMessages.format("ProfileASAMultiDBAction.error.failedprofileupdate", //$NON-NLS-1$
												new String[] {newprofilename}),
										e.getLocalizedMessage(), e);
								return false;
							}
						}
						else {
							continue;
						}
					}
					if (createIt) {
						ProfileManager.getInstance().createProfile(
								newprofilename,
								profile.getDescription(), profile.getProviderId(),
								props, parent,
								profile.isAutoConnect());
					}
				}
				catch (ConnectionProfileException e) {
					ExceptionHandler.showException(this.mShell, ConnectivityUIPlugin
							.getDefault().getResourceString(
									"NewConnectionProfileWizard.create.failure"), //$NON-NLS-1$
							e.getLocalizedMessage(), e);
					return false;
				}
			}
			success = true;
		}		
		return success;
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		if (targetPart instanceof CommonNavigator) {
			this.mShell = ((CommonNavigator) targetPart).getSite().getShell();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.mSelection = (IStructuredSelection) selection;
	}
}