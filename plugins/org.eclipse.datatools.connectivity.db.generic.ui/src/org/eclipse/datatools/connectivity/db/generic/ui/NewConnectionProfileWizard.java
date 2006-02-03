/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.db.generic.ui;



import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.db.generic.GenericDBPlugin;
import org.eclipse.datatools.connectivity.db.generic.IDBConnectionProfileConstants;
import org.eclipse.datatools.connectivity.db.generic.IDBDriverDefinitionConstants;
import org.eclipse.jface.wizard.Wizard;

/**
 * @see Wizard
 */
public class NewConnectionProfileWizard
		extends
		org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard {

	protected GenericDBProfileDetailsWizardPage mPropPage;

	public NewConnectionProfileWizard() {
		setWindowTitle(GenericDBPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizard.title")); //$NON-NLS-1$
	}

    /**
     * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#addCustomPages()
     */
	public void addCustomPages() {
		mPropPage = new GenericDBProfileDetailsWizardPage("detailsPage"); //$NON-NLS-1$
		addPage(mPropPage);
	}

	/** 
     * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#getProfileProperties()
	 */
	public Properties getProfileProperties() {
		Properties props = new Properties();
		props.setProperty(ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID,
				this.mPropPage.getDriverID());
		props.setProperty(
				IDBConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID,
				this.mPropPage.getDBConnProps());
		props.setProperty(IDBDriverDefinitionConstants.DRIVER_CLASS_PROP_ID, this.mPropPage
				.getDriverClass());
		props.setProperty(IDBDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID, this.mPropPage
				.getVendor());		
		props.setProperty(IDBDriverDefinitionConstants.DATABASE_VERSION_PROP_ID, this.mPropPage
				.getVersion());			
		props.setProperty(IDBDriverDefinitionConstants.DATABASE_NAME_PROP_ID, this.mPropPage
				.getDatabaseName());
		props.setProperty(IDBDriverDefinitionConstants.PASSWORD_PROP_ID, this.mPropPage
				.getDBPWD());
		props.setProperty(IDBDriverDefinitionConstants.USERNAME_PROP_ID, this.mPropPage
				.getDBUID());
		props.setProperty(IDBDriverDefinitionConstants.URL_PROP_ID,
				this.mPropPage.getURL());
		return props;
	}
}