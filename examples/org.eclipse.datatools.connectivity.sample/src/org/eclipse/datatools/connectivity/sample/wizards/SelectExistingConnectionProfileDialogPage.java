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

import java.util.Arrays;
import java.util.Properties;
import java.util.Vector;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.db.generic.IDBDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.ui.dse.dialogs.ConnectionDisplayProperty;
import org.eclipse.datatools.connectivity.ui.dse.dialogs.ExistingConnectionProfilesDialogPage;

public class SelectExistingConnectionProfileDialogPage extends
		ExistingConnectionProfilesDialogPage {

	protected ConnectionDisplayProperty[] updateConnectionDisplayProperties(
			IConnectionProfile connectionProfile,
			ConnectionDisplayProperty[] defaultDisplayProperties) {
		ConnectionDisplayProperty[] properties = null;
		Vector propertiesCollection = new Vector();
		propertiesCollection.addAll(Arrays.asList(defaultDisplayProperties));

		Properties baseProperties = connectionProfile.getBaseProperties();

		propertiesCollection
				.add(new ConnectionDisplayProperty(
						"Database",
						(String) baseProperties
								.get(IDBDriverDefinitionConstants.DATABASE_NAME_PROP_ID)));
		propertiesCollection
				.add(new ConnectionDisplayProperty(
						"JDBC Driver Class",
						(String) baseProperties
								.get(IDBDriverDefinitionConstants.DRIVER_CLASS_PROP_ID)));
		propertiesCollection.add(new ConnectionDisplayProperty(
				"Class Location", getJarList(connectionProfile)));
		propertiesCollection.add(new ConnectionDisplayProperty(
				"Connection URL", (String) baseProperties
						.get(IDBDriverDefinitionConstants.URL_PROP_ID)));
		propertiesCollection.add(new ConnectionDisplayProperty("User ID",
				(String) baseProperties
						.get(IDBDriverDefinitionConstants.USERNAME_PROP_ID)));
		properties = new ConnectionDisplayProperty[propertiesCollection.size()];
		propertiesCollection.toArray(properties);

		return properties;
	}

	protected IConnectionProfile[] getConnectionsToDisplay() {
		String vendor = "Derby";
		Vector filteredProfilesCollection = new Vector();
		IConnectionProfile[] filteredProfiles = new IConnectionProfile[] {};
		IConnectionProfile[] allProfiles = ProfileManager.getInstance()
				.getProfiles(false);
		final int infoLength = allProfiles.length;
		if (infoLength > 0) {
			for (int index = 0; index < infoLength; index++) {
				String vendorPropertyValue = ((String) allProfiles[index]
						.getBaseProperties()
						.get(
								IDBDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID));
				if ((vendorPropertyValue != null)
						&& (vendorPropertyValue.equals(vendor))) {
					filteredProfilesCollection.add(allProfiles[index]);
				}
				filteredProfiles = new IConnectionProfile[filteredProfilesCollection
						.size()];
				filteredProfilesCollection.copyInto(filteredProfiles);
			}
		}
		return filteredProfiles;
	}

	private String getJarList(IConnectionProfile connectionProfile) {
		String jarList = "";
		DriverInstance driverInstance = null;
		String driverID = connectionProfile.getBaseProperties().getProperty(
				ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID);
		if (driverID != null) {
			driverInstance = DriverManager.getInstance().getDriverInstanceByID(
					driverID);
			if (driverInstance != null) {
				jarList = driverInstance.getJarList();
			}
		}
		return jarList;
	}
}
