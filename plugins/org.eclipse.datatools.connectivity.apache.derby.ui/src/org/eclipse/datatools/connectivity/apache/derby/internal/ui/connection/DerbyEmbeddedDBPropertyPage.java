/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 * 				 brianf - customized for Derby url requirements
 ******************************************************************************/
package org.eclipse.datatools.connectivity.apache.derby.internal.ui.connection;

import java.io.File;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.apache.derby.internal.ui.DerbyUIPlugin;
import org.eclipse.datatools.connectivity.apache.internal.derby.connection.IDerbyConnectionProfileConstants;
import org.eclipse.datatools.connectivity.db.generic.IDBConnectionProfileConstants;
import org.eclipse.datatools.connectivity.db.generic.IDBDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.DelimitedStringList;
import org.eclipse.datatools.connectivity.internal.ui.DriverListCombo;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.ui.wizards.ProfileDetailsPropertyPage;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.preference.PreferencePage;
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
 * Property page for Derby Embedded profiles
 *
 */
public class DerbyEmbeddedDBPropertyPage 
	extends ProfileDetailsPropertyPage
	implements IContextProvider {

	private static final String EMPTY_STRING = new String();

	// UI components
	private Text mDatabaseNameText;
	private Text mDBUIDText;
	private Text mDBPWDText;
	private Text mURLText;
	private Button mSaveDBPWDCheckbox;
	private Button mCreateDBCheckbox;
	private Button mUpgradeDBCheckbox;
	private Text mDBFilePathText;
	private Button mBrowseFilePathButton;
	final DriverListCombo combo = new DriverListCombo();
	private DelimitedStringList mDBConnProps;

	// other components
	private DriverInstance mDriverInstance;
	private String mDriverCategory;

	/**
	 * Constructor
	 */
	public DerbyEmbeddedDBPropertyPage() {
		super();
		noDefaultAndApplyButton();
		setDriverCategory(IDerbyConnectionProfileConstants.DERBY_CATEGORY_ID);
		this.combo.setFilter("templateIDStartsWith = " + //$NON-NLS-1$
				IDerbyConnectionProfileConstants.DERBY_ENABLEMENT_DRIVER_PREFIX + "," +  //$NON-NLS-1$
				"templateIDEndsWith = " + //$NON-NLS-1$
				IDerbyConnectionProfileConstants.DERBY_ENABLEMENT_DRIVER_SUFFIX);
	}

	/**
	 * @see PreferencePage#createContents(Composite)
	 */
	protected void createCustomContents(Composite parent) {
		Composite content = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2, false);
		content.setLayout(layout);

		this.combo.setLabelText(Messages.getString(
				"DerbyEmbeddedDBProfileDetailsWizardPage.driverCombo.label")); //$NON-NLS-1$
		this.combo.setCategory(getDriverCategory());
		this.combo.setNullDriverIsValid(false);
		this.combo.createContents(content);

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

		this.mURLText = (Text) createLabelTextPair(content, Messages.getString(
						"DerbyEmbeddedDBProfileDetailsWizardPage.url.label"), //$NON-NLS-1$
				this.mURLText, SWT.BORDER, GridData.FILL_HORIZONTAL);
		this.mDBUIDText = (Text) createLabelTextPair(content, Messages.getString(
						"DerbyEmbeddedDBProfileDetailsWizardPage.userName.label"), //$NON-NLS-1$ 
				this.mDBUIDText, SWT.BORDER, GridData.FILL_HORIZONTAL); //$NON-NLS-1$

		this.mDBPWDText = (Text) createLabelTextPair(content, Messages.getString(
						"DerbyEmbeddedDBProfileDetailsWizardPage.password.label"), //$NON-NLS-1$, 
				this.mDBPWDText, SWT.BORDER | SWT.PASSWORD, GridData.FILL_HORIZONTAL); //$NON-NLS-1$

		this.mCreateDBCheckbox = new Button(content, SWT.CHECK);
		this.mCreateDBCheckbox.setText(Messages.getString("DerbyEmbeddedDBProfileDetailsWizardPage.2")); //$NON-NLS-1$
		this.mCreateDBCheckbox.setLayoutData(new GridData(GridData.BEGINNING,
				GridData.CENTER, true, false, 2, 1));

		this.mUpgradeDBCheckbox = new Button(content, SWT.CHECK);
		this.mUpgradeDBCheckbox.setText(Messages.getString("DerbyEmbeddedDBProfileDetailsWizardPage.3")); //$NON-NLS-1$
		this.mUpgradeDBCheckbox.setLayoutData(new GridData(GridData.BEGINNING,
				GridData.CENTER, true, false, 2, 1));

		this.mSaveDBPWDCheckbox = new Button(content, SWT.CHECK);
		this.mSaveDBPWDCheckbox.setText(Messages
				.getString(
						"DerbyEmbeddedDBProfileDetailsWizardPage.persistpassword.label")); //$NON-NLS-1$
		this.mSaveDBPWDCheckbox.setLayoutData(new GridData(GridData.BEGINNING,
				GridData.CENTER, true, false, 2, 1));

		Composite spacer = new Composite(content, SWT.NULL);
		GridData gdata = new GridData(GridData.FILL_HORIZONTAL);
		gdata.horizontalSpan = 2;
		gdata.heightHint = 20;
		spacer.setLayoutData(gdata);

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

		this.mDBConnProps.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				setErrorMessage(null);
				if (DerbyEmbeddedDBPropertyPage.this.mDBConnProps.getWarning() != null) {
					setErrorMessage(DerbyEmbeddedDBPropertyPage.this.mDBConnProps
							.getWarning());
				}
			}

		});

		this.combo.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				DerbyEmbeddedDBPropertyPage.this.mDriverInstance = DerbyEmbeddedDBPropertyPage.this.combo
						.getSelectedDriverInstance();
				setErrorMessage(null);
				if (DerbyEmbeddedDBPropertyPage.this.combo.getErrorMessage() != null) {
					setErrorMessage(DerbyEmbeddedDBPropertyPage.this.combo
							.getErrorMessage());
				}
				else
					DerbyEmbeddedDBPropertyPage.this.mURLText
							.setText(getDriverURL());
				String username = DerbyEmbeddedDBPropertyPage.this
						.getPropertyFromDriverInstance(IDBDriverDefinitionConstants.USERNAME_PROP_ID);
				if (username == null || username.trim().length() == 0)
					username = new String();
				String oldUsername = DerbyEmbeddedDBPropertyPage.this.mDBUIDText
						.getText();
				if (oldUsername == null || oldUsername.trim().length() == 0)
					oldUsername = new String();
				if (oldUsername.length() == 0)
					DerbyEmbeddedDBPropertyPage.this.mDBUIDText
							.setText(username);

				String password = DerbyEmbeddedDBPropertyPage.this
						.getPropertyFromDriverInstance(IDBDriverDefinitionConstants.PASSWORD_PROP_ID);
				if (password == null || password.trim().length() == 0)
					password = new String();
				String oldPassword = DerbyEmbeddedDBPropertyPage.this.mDBPWDText
						.getText();
				if (oldPassword == null || oldPassword.trim().length() == 0)
					oldPassword = new String();
				if (oldPassword.length() == 0)
					DerbyEmbeddedDBPropertyPage.this.mDBPWDText
							.setText(password);
			}

		});

		initControls();

		this.mDBFilePathText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				DerbyEmbeddedDBPropertyPage.this.updateURLFromProps();
			}
		});
		
		this.mCreateDBCheckbox.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				DerbyEmbeddedDBPropertyPage.this.updateURLFromProps();
			}

			public void widgetSelected(SelectionEvent e) {
				DerbyEmbeddedDBPropertyPage.this.updateURLFromProps();
			}
		});
		
		this.mUpgradeDBCheckbox.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				DerbyEmbeddedDBPropertyPage.this.updateURLFromProps();
			}

			public void widgetSelected(SelectionEvent e) {
				DerbyEmbeddedDBPropertyPage.this.updateURLFromProps();
			}
		});
		
		this.mBrowseFilePathButton.addSelectionListener( new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				DerbyEmbeddedDBPropertyPage.this.browseForDBFolder();
			}

			public void widgetSelected(SelectionEvent e) {
				DerbyEmbeddedDBPropertyPage.this.browseForDBFolder();
			}
		});

		// return content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	protected Properties collectProperties() {
		Properties props = new Properties();

		String driverID = EMPTY_STRING;

		if (this.combo.getSelectedDriverInstance() != null) {
			DriverInstance instance = this.combo.getSelectedDriverInstance();
			driverID = instance.getId();
		}

		props.setProperty(ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID,
				driverID);
		props.setProperty(
				IDBConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID,
				this.mDBConnProps.getSelection());	
		props.setProperty(IDBDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID, getPropertyFromDriverInstance(IDBDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID));		
		props.setProperty(IDBDriverDefinitionConstants.DATABASE_VERSION_PROP_ID, getPropertyFromDriverInstance(IDBDriverDefinitionConstants.DATABASE_VERSION_PROP_ID));		
		props.setProperty(IDBDriverDefinitionConstants.DRIVER_CLASS_PROP_ID, getPropertyFromDriverInstance(IDBDriverDefinitionConstants.DRIVER_CLASS_PROP_ID));	
		props.setProperty(IDBDriverDefinitionConstants.DATABASE_NAME_PROP_ID, this.mDatabaseNameText
				.getText());
		props.setProperty(IDBDriverDefinitionConstants.PASSWORD_PROP_ID, this.mDBPWDText
				.getText());
		props.setProperty(IDBDriverDefinitionConstants.USERNAME_PROP_ID, this.mDBUIDText
				.getText());
		props.setProperty(IDBDriverDefinitionConstants.URL_PROP_ID,
				this.mURLText.getText());
		props.setProperty(
				IDBConnectionProfileConstants.SAVE_PASSWORD_PROP_ID, String
						.valueOf(this.mSaveDBPWDCheckbox.getSelection()));

		return props;
	}

	/**
	 * 
	 */
	private void initControls() {
		IConnectionProfile profile = getConnectionProfile();
		String driverID = profile.getBaseProperties().getProperty(
				ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID);
		if (driverID != null) {
			this.combo.setSelectionToID(driverID);
		}

		String connectionProps = profile.getBaseProperties().getProperty(
				IDBConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID);
		if (connectionProps != null) {
			this.mDBConnProps.setSelection(connectionProps);
		}

		String databaseName = profile.getBaseProperties().getProperty(
				IDBDriverDefinitionConstants.DATABASE_NAME_PROP_ID);
		if (databaseName != null) {
			this.mDatabaseNameText.setText(databaseName);
		}
		
		String databasePwd = profile.getBaseProperties().getProperty(
				IDBDriverDefinitionConstants.PASSWORD_PROP_ID);
		if (databasePwd != null) {
			this.mDBPWDText.setText(databasePwd);
		}

		String databaseUid = profile.getBaseProperties().getProperty(
				IDBDriverDefinitionConstants.USERNAME_PROP_ID);
		if (databaseUid != null) {
			this.mDBUIDText.setText(databaseUid);
		}

		String urlText = profile.getBaseProperties().getProperty(
				IDBDriverDefinitionConstants.URL_PROP_ID);
		if (urlText != null) {
			this.mURLText.setText(urlText);
			this.mURLText.setEnabled(false);
		}

		this.mSaveDBPWDCheckbox.setSelection(Boolean.valueOf(
				profile.getBaseProperties().getProperty(
						IDBConnectionProfileConstants.SAVE_PASSWORD_PROP_ID,
						Boolean.FALSE.toString())).booleanValue());

		this.updatePropsFromURL();

		setErrorMessage(null);
		if (this.combo.getErrorMessage() != null) {
			setErrorMessage(this.combo.getErrorMessage());
		}
	}
	
	/**
	 * Browse for a DB folder
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
	 * Based on the URL, update the UI components accordingly
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
	 * Based on the state of the UI components, reset the URL
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
	 * Parse a string into an array based on a token
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
	 * @param parent
	 * @param labelText
	 * @param ctl
	 * @param style
	 * @param gData
	 * @return
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

	/**
	 * @return
	 */
	public String getDriverURL() {
		return this.mDriverInstance
				.getProperty(IDBDriverDefinitionConstants.URL_PROP_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferencePage#isValid()
	 */
	public boolean isValid() {
		if (getErrorMessage() != null) {
			return false;
		}
		return super.isValid();
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

	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_CP_PROPERTY_PAGE, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));
		return contents;
	}
}
