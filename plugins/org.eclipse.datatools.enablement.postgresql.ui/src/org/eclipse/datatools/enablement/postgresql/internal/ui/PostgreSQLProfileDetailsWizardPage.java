/*******************************************************************************
 * Copyright (c) 2004-2005, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 * 			IBM Corporation - defect fix #213266
 ******************************************************************************/
package org.eclipse.datatools.enablement.postgresql.internal.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.datatools.connectivity.db.generic.GenericDBPlugin;
import org.eclipse.datatools.connectivity.db.generic.IDBDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.internal.ui.DelimitedStringList;
import org.eclipse.datatools.connectivity.internal.ui.DriverListCombo;
import org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This class gathers the properties for the generic DB connection profile.
 * 
 * @author brianf
 */
public class PostgreSQLProfileDetailsWizardPage extends ConnectionProfileDetailsPage {

	// ui pieces
	private Text mDatabaseNameText;

	private Text mURLText;

	private Text mDBUIDText;

	private Text mDBPWDText;

	private Button mSaveDBPWDCheckbox;

	private DelimitedStringList mDBConnProps;

	final DriverListCombo combo = new DriverListCombo();

	// stashed driver instance
	private DriverInstance mDriverInstance;
	
	private String mDriverCategory;

	/**
	 * Constructor
	 * 
	 * @param pageName
	 */
	public PostgreSQLProfileDetailsWizardPage(String pageName) {
		super(pageName);
		setTitle(GenericDBPlugin.getDefault().getResourceString(
				"GenericDBProfileDetailsWizardPage.title")); //$NON-NLS-1$
		setDescription(GenericDBPlugin.getDefault().getResourceString(
				"GenericDBProfileDetailsWizardPage.msg")); //$NON-NLS-1$
		setDriverCategory(IDBDriverDefinitionConstants.DATABASE_CATEGORY_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createCustomControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2, false);
		content.setLayout(layout);

		// set up the drivers combo
		this.combo.setLabelText(GenericDBPlugin.getDefault().getResourceString(
				"GenericDBProfileDetailsWizardPage.driverCombo.label")); //$NON-NLS-1$
		this.combo.setCategory(getDriverCategory());
		this.combo.setNullDriverIsValid(false);
		this.combo.createContents(content);
		if (this.combo.getErrorMessage() != null) {
			setErrorMessage(this.combo.getErrorMessage());
		}

		// set up the fields
		this.mDatabaseNameText = (Text) createLabelTextPair(
				content,
				GenericDBPlugin.getDefault().getResourceString(
						"GenericDBProfileDetailsWizardPage.databaseName.label"), //$NON-NLS-1$
				this.mURLText, SWT.BORDER, GridData.FILL_HORIZONTAL);
		this.mURLText = (Text) createLabelTextPair(content, GenericDBPlugin
				.getDefault().getResourceString(
						"GenericDBProfileDetailsWizardPage.url.label"), //$NON-NLS-1$
				this.mURLText, SWT.BORDER, GridData.FILL_HORIZONTAL);
		this.mDBUIDText = (Text) createLabelTextPair(content, GenericDBPlugin
				.getDefault().getResourceString(
						"GenericDBProfileDetailsWizardPage.userName.label"), //$NON-NLS-1$
				this.mDBUIDText, SWT.BORDER, GridData.FILL_HORIZONTAL);

		this.mDBPWDText = (Text) createLabelTextPair(content, GenericDBPlugin
				.getDefault().getResourceString(
						"GenericDBProfileDetailsWizardPage.password.label"), //$NON-NLS-1$
				this.mDBPWDText, SWT.BORDER | SWT.PASSWORD, GridData.FILL_HORIZONTAL);

		this.mSaveDBPWDCheckbox = new Button(content, SWT.CHECK);
		this.mSaveDBPWDCheckbox.setText(GenericDBPlugin.getDefault()
				.getResourceString(
						"GenericDBProfileDetailsWizardPage.persistpassword.label")); //$NON-NLS-1$
		this.mSaveDBPWDCheckbox.setLayoutData(new GridData(GridData.BEGINNING,
				GridData.CENTER, true, false, 2, 1));

		// spacer
		Composite spacer = new Composite(content, SWT.NULL);
		GridData gdata = new GridData(GridData.FILL_HORIZONTAL);
		gdata.horizontalSpan = 2;
		gdata.heightHint = 20;
		spacer.setLayoutData(gdata);

		// followed by the optional properties
		Label label = new Label(content, SWT.NULL);
		gdata = new GridData(GridData.FILL_HORIZONTAL);
		gdata.horizontalSpan = 2;
		label.setLayoutData(gdata);
		label.setText(GenericDBPlugin.getDefault().getResourceString(
				"GenericDBProfileDetailsWizardPage.optionalProps.label")); //$NON-NLS-1$

		this.mDBConnProps = new DelimitedStringList(content, SWT.NONE);
		gdata = new GridData(GridData.FILL_HORIZONTAL);
		gdata.horizontalSpan = 2;
		this.mDBConnProps.setLayoutData(gdata);

		// now set up the initial instance
		if (this.mDriverInstance == null)
			this.mDriverInstance = this.combo.getSelectedDriverInstance();

		if (this.mDriverInstance != null) {
			this.mURLText.setText(getDriverURL());
		}

		this.mDBConnProps.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				setErrorMessage(null);
				if (PostgreSQLProfileDetailsWizardPage.this.mDBConnProps.getWarning() != null) {
					setErrorMessage(PostgreSQLProfileDetailsWizardPage.this.mDBConnProps
							.getWarning());
				}
			}

		});

		// add a change listener to the combo box so
		// we know when a new driver has been selected
		this.combo.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				PostgreSQLProfileDetailsWizardPage.this.mDriverInstance = PostgreSQLProfileDetailsWizardPage.this.combo
						.getSelectedDriverInstance();
				setErrorMessage(null);
				if (PostgreSQLProfileDetailsWizardPage.this.combo.getErrorMessage() != null) {
					setErrorMessage(PostgreSQLProfileDetailsWizardPage.this.combo
							.getErrorMessage());
				}
				PostgreSQLProfileDetailsWizardPage.this.mDatabaseNameText
						.setText(getDriverDatabaseName());

				PostgreSQLProfileDetailsWizardPage.this.mURLText
						.setText(getDriverURL());

				String username = PostgreSQLProfileDetailsWizardPage.this
						.getPropertyFromDriverInstance(IDBDriverDefinitionConstants.USERNAME_PROP_ID);
				if (username == null || username.trim().length() == 0)
					username = new String();
				PostgreSQLProfileDetailsWizardPage.this.mDBUIDText.setText(username);

				String password = PostgreSQLProfileDetailsWizardPage.this
						.getPropertyFromDriverInstance(IDBDriverDefinitionConstants.PASSWORD_PROP_ID);
				if (password == null || password.trim().length() == 0)
					password = new String();
				PostgreSQLProfileDetailsWizardPage.this.mDBPWDText.setText(password);

				setPageComplete(isValid());
			}

		});
	}

	private String getPropertyFromDriverInstance(String propertyID) {
		String returnStr = new String();
		if (this.mDriverInstance != null
				&& this.mDriverInstance.getProperty(propertyID) != null) {
			returnStr = this.mDriverInstance.getProperty(propertyID);
		}
		return returnStr;
	}

	/**
	 * Get the sample URL from the driver instance.
	 * 
	 * @return
	 */
	public String getDriverURL() {
		if (this.mDriverInstance != null
				&& this.mDriverInstance
						.getProperty(IDBDriverDefinitionConstants.URL_PROP_ID) != null)
			return this.mDriverInstance
					.getProperty(IDBDriverDefinitionConstants.URL_PROP_ID);
		return new String();
	}

	/**
	 * Get the sample database name from the driver instance.
	 * 
	 * @return
	 */
	public String getDriverDatabaseName() {
		if (this.mDriverInstance != null
				&& this.mDriverInstance
						.getProperty(IDBDriverDefinitionConstants.DATABASE_NAME_PROP_ID) != null)
			return this.mDriverInstance
					.getProperty(IDBDriverDefinitionConstants.DATABASE_NAME_PROP_ID);
		return new String();
	}

	/**
	 * Get the vendor
	 * 
	 * @return
	 */
	public String getVendor() {
		String vendor = ""; //$NON-NLS-1$
		if (this.mDriverInstance != null) {
			vendor = this.mDriverInstance
					.getProperty(IDBDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID);
		}
		return vendor;
	}

	/**
	 * Get the version
	 * 
	 * @return
	 */
	public String getVersion() {
		String version = ""; //$NON-NLS-1$
		if (this.mDriverInstance != null) {
			version = this.mDriverInstance
					.getProperty(IDBDriverDefinitionConstants.DATABASE_VERSION_PROP_ID);
		}
		return version;
	}

	/**
	 * Get the driver class
	 * 
	 * @return
	 */
	public String getDriverClass() {
		String driverClass = ""; //$NON-NLS-1$
		if (this.mDriverInstance != null) {
			driverClass = this.mDriverInstance
					.getProperty(IDBDriverDefinitionConstants.DRIVER_CLASS_PROP_ID);
		}
		return driverClass;
	}
	
	/**
	 * Get the user name
	 * 
	 * @return
	 */
	public String getDBUID() {
		return this.mDBUIDText.getText();
	}

	/**
	 * Get the password
	 * 
	 * @return
	 */
	public String getDBPWD() {
		return this.mDBPWDText.getText();
	}

	/**
	 * Get the password
	 * 
	 * @return
	 */
	public boolean getSaveDBPWD() {
		return this.mSaveDBPWDCheckbox.getSelection();
	}

	/**
	 * Get the optional connection properties
	 * 
	 * @return
	 */
	public String getDBConnProps() {
		return this.mDBConnProps.getSelection();
	}

	/**
	 * Get the database name
	 * 
	 * @return
	 */
	public String getDatabaseName() {
		return this.mDatabaseNameText.getText();
	}

	/**
	 * Get the URL for the CP
	 * 
	 * @return
	 */
	public String getURL() {
		return this.mURLText.getText();
	}

	/**
	 * Get the driver ID
	 * 
	 * @return
	 */
	public String getDriverID() {
		return this.mDriverInstance.getId();
	}

	/*
	 * Create a label and a text box side by side.
	 */
	private Control createLabelTextPair(Composite parent, String labelText,
			Control ctl, int style, int gData) {
		Label label = new Label(parent, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(labelText);

		ctl = new Text(parent, style);
		ctl.setLayoutData(new GridData(gData));

		return ctl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.suade.common.ui.wizards.ISummaryDataSource#getSummaryData()
	 */
	public List getSummaryData() {
		List data = new ArrayList();

		data
				.add(new String[] {
						GenericDBPlugin
								.getDefault()
								.getResourceString(
										"GenericDBProfileDetailsWizardPage.summary.driverName.label"), //$NON-NLS-1$
						mDriverInstance.getName() });

		data
				.add(new String[] {
						GenericDBPlugin
								.getDefault()
								.getResourceString(
										"GenericDBProfileDetailsWizardPage.summary.connProps.label"), //$NON-NLS-1$
						getDBConnProps() });

		data
				.add(new String[] {
						GenericDBPlugin
								.getDefault()
								.getResourceString(
										"GenericDBProfileDetailsWizardPage.summary.userName.label"), //$NON-NLS-1$
						getDBUID() });

		StringBuffer pwdMask = new StringBuffer();
		if (getDBPWD() != null && getDBPWD().length() > 0) {
			for (int i = 0, count = getDBPWD().length(); i < count; ++i) {
				pwdMask = pwdMask.append('*');
			}
		}

		data
				.add(new String[] {
						GenericDBPlugin
								.getDefault()
								.getResourceString(
										"GenericDBProfileDetailsWizardPage.summary.password.label"), //$NON-NLS-1$
						pwdMask.toString() });

		data.add(new String[] {
				GenericDBPlugin.getDefault().getResourceString(
						"GenericDBProfileDetailsWizardPage.summary.url.label"), //$NON-NLS-1$
				getURL() });

		return data;
	}

	private boolean isValid() {
		if (getErrorMessage() != null) {
			setPageComplete(false);
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#setErrorMessage(java.lang.String)
	 */
	public void setErrorMessage(String newMessage) {
		super.setErrorMessage(newMessage);
		isValid();
	}

	
	public String getDriverCategory() {
		return mDriverCategory;
	}

	
	/**
	 * Sets the driver category that should be used for displaying available
	 * driver defnitions.
	 * 
	 * @param driverCategory
	 */
	public void setDriverCategory(String driverCategory) {
		mDriverCategory = driverCategory;
		if (combo != null) {
			combo.setCategory(mDriverCategory);
		}
	}

}
