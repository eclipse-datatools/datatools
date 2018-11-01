/*******************************************************************************
 * Copyright (c) 2007, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Actuate Corporation - fix for Bugzilla 303465
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.DriverListCombo;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.wizards.DriverUIContributorComposite;
import org.eclipse.datatools.connectivity.sqm.core.SQMServices;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class ExtensibleProfileDetailsWizardPage extends
		ConnectionProfileDetailsPage implements IDriverUIContributorInformation {

	private String driverCategoryID = ""; //$NON-NLS-1$

	private DriverListCombo driverCombo = null;

	private DriverUIContributorComposite contributedUIComposite = null;

	private Properties properties = null;

	/**
	 * Requires that a mapping from the driver category ID be made from the provider ID via
	 * the mappings extension point.
	 */
	public ExtensibleProfileDetailsWizardPage(String wizardPageName) {
		super(wizardPageName);
		setTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"ExtensibleProfileDetailsWizardPage.title")); //$NON-NLS-1$
		setDescription(ConnectivityUIPlugin.getDefault().getResourceString(
				"ExtensibleProfileDetailsWizardPage.description")); //$NON-NLS-1$
	}
	
	public ExtensibleProfileDetailsWizardPage(String wizardPageName,
			String driverCategoryID) {
		super(wizardPageName);
		this.driverCategoryID = driverCategoryID;
		setTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"ExtensibleProfileDetailsWizardPage.title")); //$NON-NLS-1$
		setDescription(ConnectivityUIPlugin.getDefault().getResourceString(
				"ExtensibleProfileDetailsWizardPage.description")); //$NON-NLS-1$
	}

	public void createCustomControl(Composite parent) {
		/*
		 * This bit of code uses the new provider ID mapping functionality added
		 * as an experimental API in DTP 1.7.
		 * <p><strong>EXPERIMENTAL</strong>. This code has been added as
		 * part of a work in progress. There is no guarantee that this API will
		 * work or that it will remain the same. Please do not use this API without
		 * consulting with the DTP Connectivity team.</p>
		 */
		IWizard wiz = getWizard();
		if (wiz instanceof ExtensibleNewConnectionProfileWizard) {
			ExtensibleNewConnectionProfileWizard wizard = (ExtensibleNewConnectionProfileWizard) wiz;
			String tempDriverCategoryID = SQMServices.getProviderIDMappingRegistry().getCategoryIDforProviderID(wizard.getProfileProviderID());
			if (tempDriverCategoryID != null && tempDriverCategoryID.trim().length() > 0)
				this.driverCategoryID = tempDriverCategoryID;
		}

		parent.setLayout(new GridLayout());

		driverCombo = new DriverListCombo();
		driverCombo
				.setLabelText(ConnectivityUIPlugin
						.getDefault()
						.getResourceString(
								"ExtensibleProfileDetailsWizardPage.driverCombo.label")); //$NON-NLS-1$
		driverCombo.setCategory(this.driverCategoryID);
		driverCombo.setNullDriverIsValid(false);
		driverCombo.createContents(parent);

		contributedUIComposite = new DriverUIContributorComposite(parent, this,
				this, false);

		driverCombo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				handleDriverComboSelectionChangeEvent(e);
			}
		});
		if (driverCombo.getCombo().getItemCount() > 0) {
			driverCombo.getCombo().select(0);
		} else if (driverCombo.getErrorMessage() != null) {
			setMessage(driverCombo.getErrorMessage(), DialogPage.INFORMATION);//ErrorMessage(driverCombo.getErrorMessage());
		}
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpConstants.GENERIC_DB_PROFILE_WIZARD_PAGE, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));
	}

	private void handleDriverComboSelectionChangeEvent(ChangeEvent e) {
		if (driverCombo.getErrorMessage() != null) {
			setErrorMessage(driverCombo.getErrorMessage());
		}
		else {
			setMessage(null);
		}
		if (driverCombo.getSelectedDriverInstance() != null) {
			this.properties = copyProperties(driverCombo
					.getSelectedDriverInstance().getPropertySet()
					.getBaseProperties());
			this.properties.setProperty(
					ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID,
					driverCombo.getSelectedDriverID());
		}
		contributedUIComposite.setDriverTemplateID(driverCombo
				.getSelectedDriverID());
	}

	public boolean determinePageCompletion() {
		boolean isComplete = contributedUIComposite != null && 
		            contributedUIComposite.determineContributorCompletion();
		if (isComplete) {
			setErrorMessage(null);
		}
		this.setPingButtonEnabled(isComplete);
		this.setPageComplete(isComplete);
		return isComplete;
	}

	public Properties getProperties() {
		return this.properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
		setPageComplete(determinePageCompletion());
	}

	public List getSummaryData() {
		List summaryData = new ArrayList();
		summaryData = contributedUIComposite.getSummaryData();
		return summaryData;
	}

	private Properties copyProperties(Properties properties) {
		Properties copy = new Properties();
		Enumeration propertyKeys = properties.keys();
		while (propertyKeys.hasMoreElements()) {
			Object key = propertyKeys.nextElement();
			copy.put(key, properties.get(key));
		}
		return copy;
	}

	public boolean isPageComplete() {
		if (driverCombo == null) // means this control hasn't been instantiated yet
			return false;
		if (driverCombo != null && driverCombo.getSelectedDriverID() == null)
			return false;
		if (getErrorMessage() != null)
			return false;
		return super.isPageComplete();
	}

	@Override
	public void dispose() {
		if (this.driverCombo != null) {
			this.driverCombo.dispose();
		}
		super.dispose();
	}
}
