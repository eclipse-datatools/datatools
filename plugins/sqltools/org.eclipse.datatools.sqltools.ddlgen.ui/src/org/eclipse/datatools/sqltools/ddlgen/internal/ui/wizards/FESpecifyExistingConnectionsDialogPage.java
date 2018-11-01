/*******************************************************************************
 * Copyright (c) 2001, 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.ddlgen.internal.ui.wizards;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.ui.dse.dialogs.ConnectionDisplayProperty;
import org.eclipse.datatools.connectivity.ui.dse.dialogs.ExistingConnectionProfilesDialogPage;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.util.ResourceLoader;
import org.eclipse.swt.widgets.Event;

public class FESpecifyExistingConnectionsDialogPage extends
		ExistingConnectionProfilesDialogPage {
	private String allowedVendor = null;

	private String allowedVersion = null;

	private FESpecifyExistingConnectionsWizardPage page;

	/**
	 * @param pageName
	 */
	public FESpecifyExistingConnectionsDialogPage(
			FESpecifyExistingConnectionsWizardPage page,
			boolean isShowProperties, boolean isPropertiesSectionExpanded) {
		super(isShowProperties, isPropertiesSectionExpanded);
		this.page = page;
	}

	/**
	 * @param allowedProduct
	 *            The allowedProduct to set.
	 */
	public void setAllowedVendor(String allowedVendor) {
		this.allowedVendor = allowedVendor;
		initializeValues();
	}

	/**
	 * @param allowedVersion
	 *            The allowedVersion to set.
	 */
	public void setAllowedVersion(String allowedVersion) {
		this.allowedVersion = allowedVersion;
		initializeValues();
	}

	protected ConnectionDisplayProperty[] updateConnectionDisplayProperties(
			IConnectionProfile connectionProfile,
			ConnectionDisplayProperty[] defaultDisplayProperties) {
		ConnectionDisplayProperty[] properties = null;
		Vector propertiesCollection = new Vector();
		propertiesCollection.addAll(Arrays.asList(defaultDisplayProperties));

		Properties baseProperties = connectionProfile.getBaseProperties();

		propertiesCollection
				.add(new ConnectionDisplayProperty(
						ResourceLoader.INSTANCE
		        	    .queryString("FEWizard.SpecifyExistingConnectionPage.databasePropertyName"),
						(String) baseProperties
								.get(IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID)));
		propertiesCollection
				.add(new ConnectionDisplayProperty(
						ResourceLoader.INSTANCE
		        	    .queryString("FEWizard.SpecifyExistingConnectionPage.jdbcDriverClassPropertyName"),
						(String) baseProperties
								.get(IJDBCDriverDefinitionConstants.DRIVER_CLASS_PROP_ID)));
		propertiesCollection.add(new ConnectionDisplayProperty(
				ResourceLoader.INSTANCE
        	    .queryString("FEWizard.SpecifyExistingConnectionPage.classLocationPropertyName"), getJarList(connectionProfile)));
		propertiesCollection.add(new ConnectionDisplayProperty(
				ResourceLoader.INSTANCE
        	    .queryString("FEWizard.SpecifyExistingConnectionPage.connectionURLPropertyName"), (String) baseProperties
						.get(IJDBCDriverDefinitionConstants.URL_PROP_ID)));
		propertiesCollection.add(new ConnectionDisplayProperty(ResourceLoader.INSTANCE
        	    .queryString("FEWizard.SpecifyExistingConnectionPage.userIDPropertyName"),
				(String) baseProperties
						.get(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID)));
		properties = new ConnectionDisplayProperty[propertiesCollection.size()];
		propertiesCollection.toArray(properties);

		return properties;
	}

	public void handleEvent(Event event) {
		super.handleEvent(event);
		page.handleEvent(event);
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

	protected IConnectionProfile[] getConnectionsToDisplay() {
		Vector filteredProfilesCollection = new Vector();
		IConnectionProfile[] filteredProfiles = new IConnectionProfile[] {};
		IConnectionProfile[] allProfiles = ProfileManager.getInstance()
				.getProfiles(false);
		final int infoLength = allProfiles.length;
		if (infoLength > 0) {
			for (int index = 0; index < infoLength; index++) {
				Map factories = allProfiles[index].getProvider()
						.getConnectionFactories();
				if ((factories != null)
						&& (factories.containsKey("java.sql.Connection"))) {
					Properties properties = allProfiles[index]
							.getBaseProperties();
					String vendor = properties
							.getProperty(IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID);
					String version = properties
							.getProperty(IJDBCDriverDefinitionConstants.DATABASE_VERSION_PROP_ID);
					if ((vendor != null) && (version != null)
							&& (vendor.equals(allowedVendor))) {
						filteredProfilesCollection.add(allProfiles[index]);
					}
				}
				filteredProfiles = new IConnectionProfile[filteredProfilesCollection
						.size()];
				filteredProfilesCollection.copyInto(filteredProfiles);
			}
		}
		return filteredProfiles;
	}
}
