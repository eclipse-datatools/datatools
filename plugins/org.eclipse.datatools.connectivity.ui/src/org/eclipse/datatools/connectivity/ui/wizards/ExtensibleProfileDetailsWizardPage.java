/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
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
import org.eclipse.datatools.connectivity.internal.ui.wizards.DriverUIContributorComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class ExtensibleProfileDetailsWizardPage extends
		ConnectionProfileDetailsPage implements IDriverUIContributorInformation {

	private String driverCategoryID = ""; //$NON-NLS-1$

	private DriverListCombo driverCombo = null;

	private DriverUIContributorComposite contributedUIComposite = null;

	private Properties properties = null;

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
			setErrorMessage(driverCombo.getErrorMessage());
		}
	}

	private void handleDriverComboSelectionChangeEvent(ChangeEvent e) {
		if (driverCombo.getErrorMessage() != null) {
			setErrorMessage(driverCombo.getErrorMessage());
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
		boolean isComplete = contributedUIComposite
				.determineContributorCompletion();
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
}
