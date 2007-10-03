/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sample.wizards;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
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
					connection.close();
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
		// This method currently uses a non-managed connection because the
		// behavior of the existing API for managed connections is not suitable
		// for a wizard. A defect
		// has been opened and when the new API is available, this sample will
		// be updated to use it. See
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=205250

		Connection activeConnection = null;
		IConnection connection = connectionProfile
				.createConnection("java.sql.Connection");
		if (connection.getRawConnection() != null) {
			activeConnection = (Connection) connection.getRawConnection();
		} else {
			// Prompt to fix properties
			while (connection.getRawConnection() == null) {
				// display error message
				new DisplayMessage(WIZARD_TITLE, connection
						.getConnectException().getLocalizedMessage()).run();

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
				connection = connectionProfile
						.createConnection("java.sql.Connection");
			}
			activeConnection = (Connection) connection.getRawConnection();
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
