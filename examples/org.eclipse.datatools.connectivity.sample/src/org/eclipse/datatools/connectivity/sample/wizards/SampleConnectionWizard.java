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

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

public class SampleConnectionWizard extends Wizard {
	
	private SelectExistingConnectionWizardPage myExistingConnectionPage;
	
	private static final String EXISTING_CONNECTION_PAGE_NAME = "org.eclipse.datatools.connectivity.sample.SelectExistingConnectionWizardPage";
	
	public SampleConnectionWizard() {
		super();
		setWindowTitle("Connectivity Sample Wizard");
	}
	
	public boolean performFinish() {
			IConnectionProfile connectionProfile = myExistingConnectionPage
					.getSelectedConnection();
			if (connectionProfile != null) {
				Connection connection = getActiveConnection(connectionProfile);
				try {
					if (connection != null && !connection.isClosed()) {
						new DisplayMessage(
								"Connectivity Sample Wizard",
								connectionProfile.getName()
										+ " : "
										+ connection.getMetaData()
												.getSQLKeywords()).run();
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			} 
		return true;
	}
	
	private Connection getActiveConnection(IConnectionProfile connectionProfile){
		Connection activeConnection = null;
		if (connectionProfile.getConnectionState() == IConnectionProfile.CONNECTED_STATE){
			activeConnection = (Connection)connectionProfile.getManagedConnection("java.sql.Connection").getConnection().getRawConnection();
		} else if ((connectionProfile.getConnectionState() == IConnectionProfile.DISCONNECTED_STATE) ||
				(connectionProfile.getConnectionState() == IConnectionProfile.WORKING_OFFLINE_STATE)){
			// TODO Check if info is complete
		}
		
		return activeConnection;		
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
