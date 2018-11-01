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
package org.eclipse.datatools.connectivity.sample.popup.actions;

import java.io.File;
import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class CreateConnection implements IObjectActionDelegate {

	public CreateConnection() {
		super();
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		// Create new driver instance
		String jarlist = System.getProperty("user.home") + File.separator
				+ "derby.jar";
		String uniqueDriverInstanceName = generateUniqueDriverDefinitionName();
		DriverInstance driverInstance = DriverManager
				.getInstance()
				.createNewDriverInstance(
						"org.eclipse.datatools.connectivity.db.derby101.genericDriverTemplate",
						uniqueDriverInstanceName, jarlist);

		// Create new connection profile using the new driver instance
		String uniqueConnectionProfileName = generateUniqueConnectionProfileName();

		Properties connectionProfileProperties = driverInstance
				.getPropertySet().getBaseProperties();
		String databaseName = "SAMPLE";
		connectionProfileProperties.setProperty(
				ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID,
				driverInstance.getId());
		connectionProfileProperties.setProperty(
				IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID, databaseName);
		connectionProfileProperties.setProperty(
				IJDBCDriverDefinitionConstants.USERNAME_PROP_ID, "");
		connectionProfileProperties.setProperty(
				IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID, "");
		String databaseLocation = System.getProperty("user.home")
				+ File.separator + "DerbyDB" + File.separator;
		connectionProfileProperties.setProperty(
				IJDBCDriverDefinitionConstants.URL_PROP_ID, "jdbc:derby:"
						+ databaseLocation + databaseName + ";create=true");
		try {
			ProfileManager
					.getInstance()
					.createProfile(
							uniqueConnectionProfileName,
							"",
							"org.eclipse.datatools.connectivity.db.derby.embedded.connectionProfile",
							connectionProfileProperties, "", false);
		} catch (ConnectionProfileException e) {
			e.printStackTrace();
		}
	}

	private String generateUniqueDriverDefinitionName() {
		String baseDriverInstanceName = "SampleDriver";
		int index = 1;
		String testName = baseDriverInstanceName + String.valueOf(index);
		while (DriverManager.getInstance().getDriverInstanceByName(testName) != null) {
			index++;
			testName = baseDriverInstanceName + String.valueOf(index);
		}
		return testName;
	}

	private String generateUniqueConnectionProfileName() {
		String baseConnectionProfileName = "SampleConnection";
		int index = 1;
		String testName = baseConnectionProfileName + String.valueOf(index);
		while (ProfileManager.getInstance().getProfileByName(testName) != null) {
			index++;
			testName = baseConnectionProfileName + String.valueOf(index);
		}
		return testName;
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
