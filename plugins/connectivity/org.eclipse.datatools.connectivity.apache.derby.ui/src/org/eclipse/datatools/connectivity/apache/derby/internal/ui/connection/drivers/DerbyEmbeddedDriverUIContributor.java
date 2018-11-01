/*******************************************************************************
 * Copyright (c) 2004-2006, 2007, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 *     IBM Corporation - migrated to new wizard framework
 *     IBM Corporation - defect fix #213266
 *     IBM Corporation - defect fix #223677
 ******************************************************************************/
package org.eclipse.datatools.connectivity.apache.derby.internal.ui.connection.drivers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.internal.ui.DelimitedStringList;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributor;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributorInformation;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class DerbyEmbeddedDriverUIContributor implements IDriverUIContributor,
		Listener, ModifyListener {

	private static final String CUI_NEWCW_GENERAL_TAB_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.general.tab"); //$NON-NLS-1$

	private static final String CUI_NEWCW_OPTIONAL_TAB_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.optional.tab"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DBLOCATION_LBL_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.0"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DBBROWSE_BTN_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.1"); //$NON-NLS-1$

	private static final String CUI_NEWCW_CREATEDB_BTN_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.2"); //$NON-NLS-1$

	private static final String CUI_NEWCW_UPGRADEDB_BTN_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.3"); //$NON-NLS-1$

	private static final String CUI_NEWCW_USERNAME_LBL_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.userName.label"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PASSWORD_LBL_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.password.label"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SAVE_PASSWORD_LBL_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.persistpassword.label"); //$NON-NLS-1$

	private static final String CUI_NEWCW_CONNECTIONURL_LBL_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.url.label"); //$NON-NLS-1$

	private static final String CUI_NEWCW_OPTIONAL_PROPERTIES_LBL_UI_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.optionalproperties.label"); //$NON-NLS-1$	

	private static final String CUI_NEWCW_DRIVER_NAME_SUMMARY_DATA_TEXT_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.summary.driverName.label"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DATABASE_LOCATION_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_DATABASE_LOCATION_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.summary.userName.label"); //$NON-NLS-1$

	private static final String CUI_NEWCW_URL_SUMMARY_DATA_TEXT_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.summary.url.label"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_CREATE_DATABASE_SUMMARY_DATA_TEXT_ = TextProcessor.process(Messages
			.getString("CUI_NEWCW_CREATE_DATABASE_SUMMARY_DATA_TEXT_")); //$NON-NLS-1$

	private static final String CUI_NEWCW_UPGRADE_DATABASE_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_UPGRADE_DATABASE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_OPTIONAL_CONNECTION_PROPERTIES_SUMMARY_DATA_TEXT_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.summary.connProps.label"); //$NON-NLS-1$

	private static final String CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_FILE_BROWSER_TITLE_TEXT_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.FileDialog.title.filebrowse"); //$NON-NLS-1$

	private static final String CUI_NEWCW_FILE_BROWSER_MESSAGE_TEXT_ = Messages
			.getString("DerbyEmbeddedDriverUIContributor.FileDialog.msg.filebrowse"); //$NON-NLS-1$

	private static final String CUI_NEWCW_VALIDATE_DBLOCATION_REQ_UI_ = Messages
			.getString("CUI_NEWCW_VALIDATE_DBLOCATION_REQ_UI_"); //$NON-NLS-1$

	private static final String CREATE_EQUALS_TRUE_TEXT = "create=true"; //$NON-NLS-1$

	private static final String UPGRADE_EQUALS_TRUE_TEXT = "upgrade=true"; //$NON-NLS-1$

	private ScrolledComposite parentComposite;

	private Label databaseLocationLabel;

	private Combo databaseLocationCombo;

	private Button browseDatabaseLocation;

	private Button createCheck;

	private Button upgradeCheck;

	private Label usernameLabel;

	private Text usernameText;

	private Label passwordLabel;

	private Text passwordText;

	private Button savePasswordButton;

	private Label urlLabel;

	private Text urlText;

	private DelimitedStringList optionalConnectionProperties;

	private DialogPage parentPage;

	private IDriverUIContributorInformation contributorInformation;

	private Properties properties;

	private String databaseName;

	private boolean isReadOnly = false;
	
	public boolean determineContributorCompletion() {
		boolean isComplete = true;
		if (databaseLocationCombo.getText().equals("")) //$NON-NLS-1$
		{
			parentPage.setErrorMessage(CUI_NEWCW_VALIDATE_DBLOCATION_REQ_UI_); //$NON-NLS-1$
			isComplete = false;
		}

		if (isComplete) {
			parentPage.setErrorMessage(null);
		}
		return isComplete;
	}

	public Composite getContributedDriverUI(Composite parent, boolean isReadOnly) {
		if (parentComposite == null || parentComposite.isDisposed() || (this.isReadOnly != isReadOnly)) {
			this.isReadOnly = isReadOnly;
			int additionalStyles = SWT.NONE;
			if (isReadOnly){
				additionalStyles = SWT.READ_ONLY;
			}
			
			parentComposite = new ScrolledComposite(parent, SWT.H_SCROLL
					| SWT.V_SCROLL);
			parentComposite.setExpandHorizontal(true);
			parentComposite.setExpandVertical(true);
			parentComposite.setLayout(new GridLayout());

			TabFolder baseComposite = new TabFolder(parentComposite, SWT.TOP);

			TabItem generalTab = new TabItem(baseComposite, SWT.None);
			generalTab.setText(CUI_NEWCW_GENERAL_TAB_UI_); //$NON-NLS-1$

			TabItem optionalTab = new TabItem(baseComposite, SWT.None);
			optionalTab.setText(CUI_NEWCW_OPTIONAL_TAB_UI_); //$NON-NLS-1$

			Composite generalComposite = new Composite(baseComposite, SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.numColumns = 3;
			generalComposite.setLayout(layout);
			generalTab.setControl(generalComposite);

			Composite optionalComposite = new Composite(baseComposite, SWT.NULL);
			layout = new GridLayout();
			layout.numColumns = 1;
			optionalComposite.setLayout(layout);
			optionalTab.setControl(optionalComposite);

			GridData gd;

			databaseLocationLabel = new Label(generalComposite, SWT.NONE);
			databaseLocationLabel.setText(CUI_NEWCW_DBLOCATION_LBL_UI_);

			Composite textAndBrowseComposite = new Composite(generalComposite,
					SWT.NULL);
			GridLayout subCompositeLayout = new GridLayout(2, false);
			subCompositeLayout.marginLeft = -5;
			subCompositeLayout.marginRight = -5;
			subCompositeLayout.marginTop = -5;
			subCompositeLayout.marginBottom = -5;
			textAndBrowseComposite.setLayout(subCompositeLayout);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			textAndBrowseComposite.setLayoutData(gd);

			databaseLocationCombo = new Combo(textAndBrowseComposite,
					SWT.SINGLE | SWT.BORDER);
			databaseLocationCombo.setEnabled(!isReadOnly);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			databaseLocationCombo.setLayoutData(gd);

			browseDatabaseLocation = new Button(textAndBrowseComposite,
					SWT.PUSH);
			browseDatabaseLocation.setEnabled(!isReadOnly);
			browseDatabaseLocation.setText(CUI_NEWCW_DBBROWSE_BTN_UI_);
			browseDatabaseLocation.setLayoutData(new GridData(
					GridData.HORIZONTAL_ALIGN_END));
			browseDatabaseLocation.addListener(SWT.Selection, new Listener() {

				public void handleEvent(Event event) {
					DerbyEmbeddedDriverUIContributor.this.browseForDBFolder();
				}

			});
			usernameLabel = new Label(generalComposite, SWT.NONE);
			usernameLabel.setText(CUI_NEWCW_USERNAME_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			usernameLabel.setLayoutData(gd);

			usernameText = new Text(generalComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			usernameText.setLayoutData(gd);

			passwordLabel = new Label(generalComposite, SWT.NONE);
			passwordLabel.setText(CUI_NEWCW_PASSWORD_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			passwordLabel.setLayoutData(gd);

			passwordText = new Text(generalComposite, SWT.SINGLE | SWT.BORDER
					| SWT.PASSWORD | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			passwordText.setLayoutData(gd);

			urlLabel = new Label(generalComposite, SWT.NONE);
			urlLabel.setText(CUI_NEWCW_CONNECTIONURL_LBL_UI_);

			urlText = new Text(generalComposite, SWT.SINGLE | SWT.BORDER
					| SWT.READ_ONLY);
			gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gd.horizontalSpan = 2;
			urlText.setLayoutData(gd);

			createCheck = new Button(generalComposite, SWT.CHECK);
			createCheck.setText(CUI_NEWCW_CREATEDB_BTN_UI_);
			createCheck.setSelection(true);
			gd = new GridData();
			gd.horizontalSpan = 3;
			createCheck.setLayoutData(gd);

			upgradeCheck = new Button(generalComposite, SWT.CHECK);
			upgradeCheck.setText(CUI_NEWCW_UPGRADEDB_BTN_UI_);
			upgradeCheck.setSelection(false);
			gd = new GridData();
			gd.horizontalSpan = 3;
			upgradeCheck.setLayoutData(gd);

			this.savePasswordButton = new Button(generalComposite, SWT.CHECK);
			this.savePasswordButton.setText(CUI_NEWCW_SAVE_PASSWORD_LBL_UI_); //$NON-NLS-1$
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 3;
			gd.grabExcessHorizontalSpace = true;
			savePasswordButton.setLayoutData(gd);

			Label optionalPropertiesLabel = new Label(optionalComposite,
					SWT.NULL);
			GridData gdata = new GridData(GridData.FILL_HORIZONTAL);
			gdata.horizontalSpan = 2;
			optionalPropertiesLabel.setLayoutData(gdata);
			optionalPropertiesLabel
					.setText(CUI_NEWCW_OPTIONAL_PROPERTIES_LBL_UI_); //$NON-NLS-1$

			optionalConnectionProperties = new DelimitedStringList(
					optionalComposite, SWT.NONE, isReadOnly);
			gdata = new GridData(GridData.FILL_HORIZONTAL);
			gdata.horizontalSpan = 2;
			optionalConnectionProperties.setLayoutData(gdata);

			parentComposite.setContent(baseComposite);
			parentComposite.setMinSize(baseComposite.computeSize(SWT.DEFAULT,
					SWT.DEFAULT));

			initialize();
		}

		return parentComposite;
	}

	private void initialize() {
		updateURL();
		addListeners();
	}

	public List getSummaryData() {
		List summaryData = new ArrayList();
		String driverID = this.properties
				.getProperty(ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID);
		DriverInstance driverInstance = DriverManager.getInstance()
				.getDriverInstanceByID(driverID);

		summaryData.add(new String[] {
				CUI_NEWCW_DRIVER_NAME_SUMMARY_DATA_TEXT_, //$NON-NLS-1$
				driverInstance.getName() });
		summaryData.add(new String[] {
				CUI_NEWCW_DATABASE_LOCATION_SUMMARY_DATA_TEXT_,
				this.databaseLocationCombo.getText().trim() });

		summaryData.add(new String[] { CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_,
				this.usernameText.getText().trim() });
		summaryData.add(new String[] { CUI_NEWCW_URL_SUMMARY_DATA_TEXT_,
				this.urlText.getText().trim() });
		summaryData.add(new String[] {
				CUI_NEWCW_CREATE_DATABASE_SUMMARY_DATA_TEXT_,
				createCheck.getSelection() ? CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_
						: CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ });
		summaryData.add(new String[] {
				CUI_NEWCW_UPGRADE_DATABASE_SUMMARY_DATA_TEXT_,
				upgradeCheck.getSelection() ? CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_
						: CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ });
		summaryData
				.add(new String[] {
						CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_,
						savePasswordButton.getSelection() ? CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_
								: CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ });
		summaryData.add(new String[] {
				CUI_NEWCW_OPTIONAL_CONNECTION_PROPERTIES_SUMMARY_DATA_TEXT_, //$NON-NLS-1$
				optionalConnectionProperties.getSelection() });
		return summaryData;
	}

	public void loadProperties() {
		removeListeners();
		DerbyEmbeddedJDBCURL url = new DerbyEmbeddedJDBCURL(this.properties
				.getProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID));
		databaseLocationCombo.setText(url.getNode());
		updateDatabaseName();
		String username = this.properties
				.getProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID);
		if (username != null) {
			usernameText.setText(username);
		}
		String password = this.properties
				.getProperty(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID);
		if (password != null) {
			passwordText.setText(password);
		}
		String savePassword = this.properties
				.getProperty(IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID);
		if ((savePassword != null)
				&& Boolean.valueOf(savePassword) == Boolean.TRUE) {
			savePasswordButton.setSelection(true);
		}
		if (!(url.getProperties().indexOf(CREATE_EQUALS_TRUE_TEXT) > -1)) {
			createCheck.setSelection(false);
		}
		if ((url.getProperties().indexOf(UPGRADE_EQUALS_TRUE_TEXT) > -1)) {
			upgradeCheck.setSelection(true);
		}

		String connectionProperties = this.properties
				.getProperty(IJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID);
		if (connectionProperties != null) {
			this.optionalConnectionProperties
					.setSelection(connectionProperties);
		}
		updateURL();
		addListeners();
		setConnectionInformation();
	}

	protected void updateDatabaseName() {
		if (databaseLocationCombo.getText() != null
				&& !databaseLocationCombo.getText().equals("")) { //$NON-NLS-1$
			databaseName = databaseLocationCombo.getText()
					.substring(
							databaseLocationCombo.getText().lastIndexOf(
									File.separator) + 1);
		}
	}

	public void setDialogPage(DialogPage parentPage) {
		this.parentPage = parentPage;
	}

	public void setConnectionInformation() {
		updateDatabaseName();
		properties.setProperty(
				IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID,
				databaseName);
		properties.setProperty(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID,
				this.passwordText.getText());
		properties.setProperty(
				IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID, String
						.valueOf(savePasswordButton.getSelection()));
		properties.setProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID,
				this.usernameText.getText());
		properties.setProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID,
				this.urlText.getText().trim());
		properties.setProperty(
				IJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID,
				this.optionalConnectionProperties.getSelection());
		this.contributorInformation.setProperties(properties);
	}

	public void setDriverUIContributorInformation(
			IDriverUIContributorInformation contributorInformation) {
		this.contributorInformation = contributorInformation;
		this.properties = contributorInformation.getProperties();
	}

	public void handleEvent(Event event) {
		if (isReadOnly){
			if (event.widget == savePasswordButton){
				savePasswordButton.setSelection(!savePasswordButton.getSelection());
			} else if (event.widget == createCheck){
				createCheck.setSelection(!createCheck.getSelection());
			} else if (event.widget == upgradeCheck){
				upgradeCheck.setSelection(!upgradeCheck.getSelection());
			}	
		} else {
			updateURL();
			setConnectionInformation();
		}
	}

	public void addListeners() {
		databaseLocationCombo.addListener(SWT.Modify, this);
		browseDatabaseLocation.addListener(SWT.Selection, this);
		usernameText.addListener(SWT.Modify, this);
		passwordText.addListener(SWT.Modify, this);
		savePasswordButton.addListener(SWT.Selection, this);
		createCheck.addListener(SWT.Selection, this);
		upgradeCheck.addListener(SWT.Selection, this);
		optionalConnectionProperties.addModifyListener(this);
	}

	protected void removeListeners() {
		databaseLocationCombo.removeListener(SWT.Modify, this);
		browseDatabaseLocation.removeListener(SWT.Selection, this);
		usernameText.removeListener(SWT.Modify, this);
		passwordText.removeListener(SWT.Modify, this);
		savePasswordButton.removeListener(SWT.Selection, this);
		createCheck.removeListener(SWT.Selection, this);
		upgradeCheck.removeListener(SWT.Selection, this);
		optionalConnectionProperties.removeModifyListener(this);
	}

	protected void updateURL() {
		String url = "jdbc:derby:" + databaseLocationCombo.getText(); //$NON-NLS-1$
		url += !createCheck.getSelection() ? "" : ";create=true"; //$NON-NLS-1$ //$NON-NLS-2$
		url += !upgradeCheck.getSelection() ? "" : ";upgrade=true"; //$NON-NLS-1$ //$NON-NLS-2$
		urlText.setText(url);
	}

	protected class DerbyEmbeddedJDBCURL {
		protected String subprotocol = ""; //$NON-NLS-1$

		protected String node = ""; //$NON-NLS-1$

		protected String properties = ""; //$NON-NLS-1$

		/**
		 * @param url
		 */
		public DerbyEmbeddedJDBCURL(String url) {
			parseURL(url);
		}

		/**
		 * @return Returns the node.
		 */
		public String getNode() {
			return node;
		}

		/**
		 * @return Returns the subprotocol.
		 */
		public String getSubprotocol() {
			return subprotocol;
		}

		protected void parseURL(String url) {
			try {
				String remainingURL = url.substring(url.indexOf(':') + 1);
				this.subprotocol = remainingURL.substring(0, remainingURL
						.indexOf(':'));
				remainingURL = remainingURL
						.substring(remainingURL.indexOf(':') + 1);
				if (remainingURL.indexOf(';') > -1) {
					this.node = remainingURL.substring(0, remainingURL
							.indexOf(';'));
					remainingURL = remainingURL.substring(remainingURL
							.indexOf(';') + 1);
					this.properties = remainingURL;
				} else {
					this.node = remainingURL;
				}
			} catch (Exception e) {
			}
		}

		/**
		 * @return Returns the properties.
		 */
		public String getProperties() {
			return properties;
		}
	}

	/**
	 * Browse for the database folder
	 */
	private void browseForDBFolder() {
		DirectoryDialog dialog = new DirectoryDialog(databaseLocationCombo
				.getShell());
		dialog.setText(CUI_NEWCW_FILE_BROWSER_TITLE_TEXT_); //$NON-NLS-1$
		dialog.setMessage(CUI_NEWCW_FILE_BROWSER_MESSAGE_TEXT_); //$NON-NLS-1$

		String dirName = databaseLocationCombo.getText();

		File path = new File(dirName);
		if (path.exists()) {
			dialog.setFilterPath(new Path(dirName).toOSString());
		}

		String selectedDirectory = dialog.open();
		if (selectedDirectory != null) {
			databaseLocationCombo.setText(selectedDirectory);
		}
	}

	public void modifyText(ModifyEvent e) {
		handleEvent(new Event());
	}
}
