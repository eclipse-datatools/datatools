/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sample.wizards;

import java.sql.Connection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.SameShellProvider;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class SampleConnectionWizard extends Wizard {

	private SelectExistingConnectionWizardPage myExistingConnectionPage;

	private static final String EXISTING_CONNECTION_PAGE_NAME = "org.eclipse.datatools.connectivity.sample.SelectExistingConnectionWizardPage";

	private static final String WIZARD_TITLE = "Connectivity Sample Wizard";

	public SampleConnectionWizard() {
		super();
		setWindowTitle(WIZARD_TITLE);
	}

	public boolean performFinish() {
		IConnectionProfile connectionProfile = myExistingConnectionPage
				.getSelectedConnection();
		if (connectionProfile != null) {
			Connection connection = getActiveConnection(connectionProfile);
			try {
				if (connection != null && !connection.isClosed()) {
					new DisplayMessage(WIZARD_TITLE, connectionProfile
							.getName()
							+ " : Keywords : "
							+ connection.getMetaData().getSQLKeywords()).run();
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private Connection getActiveConnection(IConnectionProfile connectionProfile) {
		Connection activeConnection = null;			
		IManagedConnection connection = null;
		IStatus connectionStatus = null;
		
		if (connectionProfile.getConnectionState() == IConnectionProfile.CONNECTED_STATE) {
			activeConnection = (Connection) connectionProfile.getManagedConnection("java.sql.Connection").getConnection().getRawConnection();
		} else {
			connectionStatus = connectionProfile.connectWithoutJob();
			if (connectionProfile.getConnectionState() == IConnectionProfile.CONNECTED_STATE) {
				return (Connection) connectionProfile.getManagedConnection("java.sql.Connection").getConnection().getRawConnection();
			}
			while (connectionProfile.getConnectionState() != IConnectionProfile.CONNECTED_STATE) {		
				// display error message
				new DisplayMessage(WIZARD_TITLE, connectionStatus
						.getChildren()[0].getException().getLocalizedMessage()).run();

				// Prompt to fix properties
				PropertyDialogAction propertyDialogAction = new PropertyDialogAction(
						new SameShellProvider(this.getShell()),
						new SampleWizardSelectionProvider(connectionProfile));

				StructuredSelection selection = new StructuredSelection(
						connectionProfile);
				propertyDialogAction.selectionChanged(selection);
				if (propertyDialogAction.isApplicableForSelection()) {
					PreferenceDialog dialog = propertyDialogAction
							.createDialog();
					String initialPage = ((ConnectionProfileProvider) connectionProfile
							.getProvider()).getPropertiesPersistenceHook()
							.getConnectionPropertiesPageID();
					if (initialPage != null) {
						((IWorkbenchPreferenceContainer) dialog).openPage(
								initialPage, null);
					}
					if (dialog.open() == Dialog.CANCEL) {
						return activeConnection;
					}
				}
				connectionStatus = connectionProfile.connectWithoutJob();
				connection = connectionProfile.getManagedConnection("java.sql.Connection");
			}
			activeConnection = (Connection) connection.getConnection().getRawConnection();
		}

		return activeConnection;
	}

	private class SampleWizardSelectionProvider implements ISelectionProvider {

		private IStructuredSelection selection;

		public SampleWizardSelectionProvider(
				IConnectionProfile connectionProfile) {
			selection = new StructuredSelection(connectionProfile);
		}

		public void addSelectionChangedListener(
				ISelectionChangedListener listener) {
		}

		public ISelection getSelection() {
			return selection;
		}

		public void removeSelectionChangedListener(
				ISelectionChangedListener listener) {
		}

		public void setSelection(ISelection selection) {
		}
	}

	public void addPages() {
		super.addPages();

		myExistingConnectionPage = new SelectExistingConnectionWizardPage(
				EXISTING_CONNECTION_PAGE_NAME);
		addPage(myExistingConnectionPage);
	}

	public boolean canFinish() {
		boolean canFinish = false;
		if (myExistingConnectionPage.getSelectedConnection() != null) {
			canFinish = true;
		}
		return canFinish;
	}

	public class DisplayMessage implements Runnable {
		String title, message;

		public DisplayMessage(String title, String message) {
			this.title = title;
			this.message = message;
		}

		public void run() {
			MessageDialog.openInformation(getShell(), title, message);
		}
	}
}
