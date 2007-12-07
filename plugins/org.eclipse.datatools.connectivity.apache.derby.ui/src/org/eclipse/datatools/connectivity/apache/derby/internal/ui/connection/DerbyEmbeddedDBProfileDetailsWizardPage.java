/*******************************************************************************
 * Copyright (c) 2004-2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.apache.derby.internal.ui.connection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.apache.derby.internal.ui.DerbyUIPlugin;
import org.eclipse.datatools.connectivity.apache.internal.derby.connection.IDerbyConnectionProfileConstants;
import org.eclipse.datatools.connectivity.db.generic.IDBDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.internal.ui.DelimitedStringList;
import org.eclipse.datatools.connectivity.internal.ui.DriverListCombo;
import org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This class gathers the properties for the derby embedded DB connection profile.
 * 
 * @author brianf
 */
public class DerbyEmbeddedDBProfileDetailsWizardPage 
	extends ConnectionProfileDetailsPage
	implements IContextProvider {

	// ui pieces
	private Text mDatabaseNameText;

	private Text mURLText;

	private Text mDBUIDText;

	private Text mDBPWDText;

	private Button mSaveDBPWDCheckbox;

	private DelimitedStringList mDBConnProps;

	final DriverListCombo combo = new DriverListCombo();
	
	private Button mCreateDBCheckbox;
	
	private Button mUpgradeDBCheckbox;

	private Text mDBFilePathText;

	private Button mBrowseFilePathButton;

	// stashed driver instance
	private DriverInstance mDriverInstance;
	
	private String mDriverCategory;

	/**
	 * Constructor
	 * 
	 * @param pageName
	 */
	public DerbyEmbeddedDBProfileDetailsWizardPage(String pageName) {
		super(pageName);
		setTitle(Messages.getString(
				"DerbyEmbeddedDBProfileDetailsWizardPage.title")); //$NON-NLS-1$
		setDescription(Messages.getString(
				"DerbyEmbeddedDBProfileDetailsWizardPage.msg")); //$NON-NLS-1$
		setDriverCategory(IDerbyConnectionProfileConstants.DERBY_CATEGORY_ID);
		this.combo.setFilter("templateIDStartsWith = " + //$NON-NLS-1$
				IDerbyConnectionProfileConstants.DERBY_ENABLEMENT_DRIVER_PREFIX + "," +  //$NON-NLS-1$
				"templateIDEndsWith = " + //$NON-NLS-1$
				IDerbyConnectionProfileConstants.DERBY_ENABLEMENT_DRIVER_SUFFIX);
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
		this.combo.setLabelText(Messages.getString(
				"DerbyEmbeddedDBProfileDetailsWizardPage.driverCombo.label")); //$NON-NLS-1$
		this.combo.setCategory(getDriverCategory());
		this.combo.setNullDriverIsValid(false);
		this.combo.createContents(content);
		if (this.combo.getErrorMessage() != null) {
			setErrorMessage(this.combo.getErrorMessage());
		}

		// set up the fields
		this.mDatabaseNameText = (Text) createLabelTextPair(
				content,
				Messages.getString(
						"DerbyEmbeddedDBProfileDetailsWizardPage.databaseName.label"), //$NON-NLS-1$
				this.mURLText, SWT.BORDER, GridData.FILL_HORIZONTAL);

		Label label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(Messages.getString("DerbyEmbeddedDBProfileDetailsWizardPage.0")); //$NON-NLS-1$

		Composite textAndBrowseComposite = new Composite(content, SWT.NULL);
		GridLayout subCompositeLayout = new GridLayout(2, false);
		subCompositeLayout.marginLeft = -5;
		subCompositeLayout.marginRight = -5;
		subCompositeLayout.marginTop = -5;
		subCompositeLayout.marginBottom = -5;
		textAndBrowseComposite.setLayout(subCompositeLayout);
		textAndBrowseComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.mDBFilePathText = new Text(textAndBrowseComposite, SWT.BORDER);
		this.mDBFilePathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.mBrowseFilePathButton = new Button(textAndBrowseComposite, SWT.PUSH);
		this.mBrowseFilePathButton.setText(Messages.getString("DerbyEmbeddedDBProfileDetailsWizardPage.1")); //$NON-NLS-1$
		this.mBrowseFilePathButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		this.mDBUIDText = (Text) createLabelTextPair(content, Messages.getString(
						"DerbyEmbeddedDBProfileDetailsWizardPage.userName.label"), //$NON-NLS-1$
				this.mDBUIDText, SWT.BORDER, GridData.FILL_HORIZONTAL);

		this.mDBPWDText = (Text) createLabelTextPair(content, Messages.getString(
						"DerbyEmbeddedDBProfileDetailsWizardPage.password.label"), //$NON-NLS-1$
				this.mDBPWDText, SWT.BORDER | SWT.PASSWORD, GridData.FILL_HORIZONTAL);

		this.mURLText = (Text) createLabelTextPair(content, Messages.getString(
			"DerbyEmbeddedDBProfileDetailsWizardPage.url.label"), //$NON-NLS-1$
			this.mURLText, SWT.BORDER | SWT.READ_ONLY, GridData.FILL_HORIZONTAL);

		this.mCreateDBCheckbox = new Button(content, SWT.CHECK);
		this.mCreateDBCheckbox.setText(Messages.getString("DerbyEmbeddedDBProfileDetailsWizardPage.2")); //$NON-NLS-1$
		this.mCreateDBCheckbox.setLayoutData(new GridData(GridData.BEGINNING,
				GridData.CENTER, true, false, 2, 1));

		this.mUpgradeDBCheckbox = new Button(content, SWT.CHECK);
		this.mUpgradeDBCheckbox.setText(Messages.getString("DerbyEmbeddedDBProfileDetailsWizardPage.3")); //$NON-NLS-1$
		this.mUpgradeDBCheckbox.setLayoutData(new GridData(GridData.BEGINNING,
				GridData.CENTER, true, false, 2, 1));

		this.mSaveDBPWDCheckbox = new Button(content, SWT.CHECK);
		this.mSaveDBPWDCheckbox.setText(Messages.getString(
						"DerbyEmbeddedDBProfileDetailsWizardPage.persistpassword.label")); //$NON-NLS-1$
		this.mSaveDBPWDCheckbox.setLayoutData(new GridData(GridData.BEGINNING,
				GridData.CENTER, true, false, 2, 1));

		// spacer
		Composite spacer = new Composite(content, SWT.NULL);
		GridData gdata = new GridData(GridData.FILL_HORIZONTAL);
		gdata.horizontalSpan = 2;
		gdata.heightHint = 20;
		spacer.setLayoutData(gdata);

		// followed by the optional properties
		label = new Label(content, SWT.NULL);
		gdata = new GridData(GridData.FILL_HORIZONTAL);
		gdata.horizontalSpan = 2;
		label.setLayoutData(gdata);
		label.setText(Messages.getString(
				"DerbyEmbeddedDBProfileDetailsWizardPage.optionalProps.label")); //$NON-NLS-1$

		this.mDBConnProps = new DelimitedStringList(content, SWT.NONE);
		gdata = new GridData(GridData.FILL_HORIZONTAL);
		gdata.horizontalSpan = 2;
		this.mDBConnProps.setLayoutData(gdata);

//		// now set up the initial instance
//		if (this.mDriverInstance == null)
//			this.mDriverInstance = this.combo.getSelectedDriverInstance();

		this.mDBConnProps.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				setErrorMessage(null);
				if (DerbyEmbeddedDBProfileDetailsWizardPage.this.mDBConnProps.getWarning() != null) {
					setErrorMessage(DerbyEmbeddedDBProfileDetailsWizardPage.this.mDBConnProps
							.getWarning());
				}
			}

		});

		// add a change listener to the combo box so
		// we know when a new driver has been selected
		this.combo.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				DerbyEmbeddedDBProfileDetailsWizardPage.this.mDriverInstance = DerbyEmbeddedDBProfileDetailsWizardPage.this.combo
						.getSelectedDriverInstance();
				setErrorMessage(null);
				if (DerbyEmbeddedDBProfileDetailsWizardPage.this.combo.getErrorMessage() != null) {
					setErrorMessage(DerbyEmbeddedDBProfileDetailsWizardPage.this.combo
							.getErrorMessage());
				}
				DerbyEmbeddedDBProfileDetailsWizardPage.this.mDatabaseNameText
						.setText(getDriverDatabaseName());

				DerbyEmbeddedDBProfileDetailsWizardPage.this.mURLText
						.setText(getDriverURL());
				DerbyEmbeddedDBProfileDetailsWizardPage.this.updatePropsFromURL();

				String username = DerbyEmbeddedDBProfileDetailsWizardPage.this
						.getPropertyFromDriverInstance(IDBDriverDefinitionConstants.USERNAME_PROP_ID);
				if (username == null || username.trim().length() == 0)
					username = new String();
				DerbyEmbeddedDBProfileDetailsWizardPage.this.mDBUIDText.setText(username);

				String password = DerbyEmbeddedDBProfileDetailsWizardPage.this
						.getPropertyFromDriverInstance(IDBDriverDefinitionConstants.PASSWORD_PROP_ID);
				if (password == null || password.trim().length() == 0)
					password = new String();
				DerbyEmbeddedDBProfileDetailsWizardPage.this.mDBPWDText.setText(password);

				setPageComplete(isValid());
			}

		});
		
		this.mDBFilePathText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				DerbyEmbeddedDBProfileDetailsWizardPage.this.updateURLFromProps();
			}
		});
		
		this.mCreateDBCheckbox.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				DerbyEmbeddedDBProfileDetailsWizardPage.this.updateURLFromProps();
			}

			public void widgetSelected(SelectionEvent e) {
				DerbyEmbeddedDBProfileDetailsWizardPage.this.updateURLFromProps();
			}
		});
		
		this.mUpgradeDBCheckbox.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				DerbyEmbeddedDBProfileDetailsWizardPage.this.updateURLFromProps();
			}

			public void widgetSelected(SelectionEvent e) {
				DerbyEmbeddedDBProfileDetailsWizardPage.this.updateURLFromProps();
			}
		});

		this.mBrowseFilePathButton.addSelectionListener( new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				DerbyEmbeddedDBProfileDetailsWizardPage.this.browseForDBFolder();
			}

			public void widgetSelected(SelectionEvent e) {
				DerbyEmbeddedDBProfileDetailsWizardPage.this.browseForDBFolder();
			}
		});

		this.combo.selectFirstItem();
		// now set up the initial instance
		if (this.mDriverInstance == null)
			this.mDriverInstance = this.combo.getSelectedDriverInstance();
		if (this.mDriverInstance != null) {
			this.mURLText.setText(getDriverURL());
		}
	}
	
	/**
	 * Browse for the database folder
	 */
	private void browseForDBFolder() {
		DirectoryDialog dialog = new DirectoryDialog(getShell());
		dialog.setText(Messages
				.getString("DerbyEmbeddedDBProfileDetailsWizardPage.FileDialog.title.filebrowse")); //$NON-NLS-1$
		dialog.setMessage(Messages
				.getString("DerbyEmbeddedDBProfileDetailsWizardPage.FileDialog.msg.filebrowse")); //$NON-NLS-1$

		String dirName = mDBFilePathText.getText();

		File path = new File(dirName);
		if (path.exists()) {
			dialog.setFilterPath(new Path(dirName).toOSString());
		}

		String selectedDirectory = dialog.open();
		if (selectedDirectory != null) {
			this.mDBFilePathText.setText(selectedDirectory);
		}
	}

	/**
	 * Update the UI components from the URL
	 */
	private void updatePropsFromURL() {
		if (this.mDBFilePathText != null && this.mURLText != null && this.mCreateDBCheckbox != null && this.mUpgradeDBCheckbox != null) {
			String url = this.mURLText.getText();
			String[] chunks = parseString(url, ";"); //$NON-NLS-1$
			if (chunks.length > 0) {
				String[] chunks2 = parseString(chunks[0], ":"); //$NON-NLS-1$
				if (chunks2.length > 2) {
					String filepath = chunks2[2];
					if (chunks2.length > 3) {
						filepath = filepath + ":" + chunks2[3]; //$NON-NLS-1$
					}
					this.mDBFilePathText.setText(filepath);
				}
			}
			if (chunks.length > 1) {
				for (int i = 1; i < chunks.length; i++) {
					if (chunks[i].startsWith("create=")) //$NON-NLS-1$
						this.mCreateDBCheckbox.setSelection(true);
					else if (chunks[i].startsWith("upgrade=")) //$NON-NLS-1$
						this.mUpgradeDBCheckbox.setSelection(true);
				}
			}
		}
	}
	
	/**
	 * Update the URL from the UI components
	 */
	private void updateURLFromProps() {
		if (this.mDBFilePathText != null && this.mURLText != null && this.mCreateDBCheckbox != null && this.mUpgradeDBCheckbox != null) {
			String url = this.mURLText.getText();
			String filePath = this.mDBFilePathText.getText();
			boolean createDB = this.mCreateDBCheckbox.getSelection();
			boolean upgradeDB = this.mUpgradeDBCheckbox.getSelection();
			//jdbc:derby:C:\DerbyDatabases\MyDB;create=true
			String[] chunks = parseString(url, ";"); //$NON-NLS-1$
			if (chunks.length > 0) {
				String[] chunks2 = parseString(chunks[0], ":"); //$NON-NLS-1$
				if (chunks2.length > 2) {
					url = chunks2[0] + ":" + chunks2[1] + ":" + filePath; //$NON-NLS-1$ //$NON-NLS-2$
					if (createDB) {
						url = url + ";" + "create=true"; //$NON-NLS-1$ //$NON-NLS-2$
					}
					if (upgradeDB) {
						url = url + ";" + "upgrade=true"; //$NON-NLS-1$ //$NON-NLS-2$
					}
					this.mURLText.setText(url);
				}
			}
		}
	}

	/**
	 * Parse a string into a string array based on a token
	 * @param str_list
	 * @param token
	 * @return
	 */
	private  String[] parseString(String str_list, String token) {
		StringTokenizer tk = new StringTokenizer(str_list, token);
		String[] pieces = new String[tk.countTokens()];
		int index = 0;
		while (tk.hasMoreTokens())
			pieces[index++] = tk.nextToken();
		return pieces;
	}

	/**
	 * @param propertyID
	 * @return
	 */
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
						Messages.getString(
										"DerbyEmbeddedDBProfileDetailsWizardPage.summary.driverName.label"), //$NON-NLS-1$
						mDriverInstance.getName() });

		data
				.add(new String[] {
						Messages.getString(
										"DerbyEmbeddedDBProfileDetailsWizardPage.summary.connProps.label"), //$NON-NLS-1$
						getDBConnProps() });

		data
				.add(new String[] {
						Messages.getString(
										"DerbyEmbeddedDBProfileDetailsWizardPage.summary.userName.label"), //$NON-NLS-1$
						getDBUID() });

		StringBuffer pwdMask = new StringBuffer();
		if (getDBPWD() != null && getDBPWD().length() > 0) {
			for (int i = 0, count = getDBPWD().length(); i < count; ++i) {
				pwdMask = pwdMask.append('*');
			}
		}

		data
				.add(new String[] {
						Messages.getString(
										"DerbyEmbeddedDBProfileDetailsWizardPage.summary.password.label"), //$NON-NLS-1$
						pwdMask.toString() });

		data.add(new String[] {
				Messages.getString(
						"DerbyEmbeddedDBProfileDetailsWizardPage.summary.url.label"), //$NON-NLS-1$
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

	
	/**
	 * @return
	 */
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

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(DerbyUIPlugin.getDefault().getBundle().getSymbolicName());

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpContextsDerbyProfile.DERBY_PROFILE_WIZARD_PAGE, DerbyUIPlugin.getDefault().getBundle().getSymbolicName()));
	}
}
