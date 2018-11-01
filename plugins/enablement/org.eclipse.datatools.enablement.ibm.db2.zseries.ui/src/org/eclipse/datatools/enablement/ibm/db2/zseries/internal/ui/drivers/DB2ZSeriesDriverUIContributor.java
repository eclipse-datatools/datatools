/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.zseries.internal.ui.drivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributor;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributorInformation;
import org.eclipse.datatools.connectivity.ui.wizards.OptionalPropertiesPane;
import org.eclipse.datatools.enablement.ibm.db2.internal.zseries.IZSeriesDriverDefinitionConstants;
import org.eclipse.datatools.enablement.ibm.internal.ui.drivers.IBMJDBCDriverTracingOptionsPane;
import org.eclipse.datatools.enablement.ibm.internal.ui.drivers.IIBMJDBCDriverProvider;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class DB2ZSeriesDriverUIContributor implements IDriverUIContributor,
		IIBMJDBCDriverProvider, Listener {
	protected String CUI_NEWCW_DATABASE_LBL_UI_ = Messages
			.getString("CUI_NEWCW_LOCATION_LBL_UI_");

	private static final String CUI_NEWCW_HOST_LBL_UI_ = Messages
			.getString("CUI_NEWCW_HOST_LBL_UI_");

	private static final String CUI_NEWCW_PORT_LBL_UI_ = Messages
			.getString("CUI_NEWCW_PORT_LBL_UI_");

	private static final String CUI_NEWCW_CONNECTIONURL_LBL_UI_ = Messages
			.getString("CUI_NEWCW_CONNECTIONURL_LBL_UI_");

	private static final String CUI_NEWCW_TBCREATOR_LBL_UI = Messages
			.getString("CUI_NEWCW_TBCREATOR_LBL_UI");

	private static final String CUI_NEWCW_USERNAME_LBL_UI_ = Messages
			.getString("CUI_NEWCW_USERNAME_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PASSWORD_LBL_UI_ = Messages
			.getString("CUI_NEWCW_PASSWORD_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SAVE_PASSWORD_LBL_UI_ = Messages
			.getString("CUI_NEWCW_SAVE_PASSWORD_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_ = Messages
			.getString("CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_"); //$NON-NLS-1$
	
	private static final String CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$
	
	private static final String CUI_NEWCW_DRIVER_OPTIONS_TAB_UI_ = org.eclipse.datatools.enablement.ibm.internal.ui.drivers.Messages
			.getString("CUI_NEWCW_DRIVER_OPTIONS_TAB_UI_");

	private static final String CUI_NEWCW_TRACING_OPTIONS_TAB_UI_ = org.eclipse.datatools.enablement.ibm.internal.ui.drivers.Messages
			.getString("CUI_NEWCW_TRACING_OPTIONS_TAB_UI_");

	private static final String CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_RETRIEVE_OBJECTS_RESTRICTION_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_RETRIEVE_OBJECTS_RESTRICTION_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_URL_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_URL_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private IDriverUIContributorInformation contributorInformation;

	private IBMJDBCDriverTracingOptionsPane tracingOptionsComposite;

	private Label databaseLabel;

	private Text databaseText;

	private Label hostLabel;

	private Text hostText;

	private Label portLabel;

	private Text portText;

	private Label usernameLabel;

	private Text usernameText;

	private Label passwordLabel;

	private Text passwordText;

	private Label defaultSchemaLabel;

	private Text defaultSchemaText;
	
	private Button savePasswordButton;

	private Label urlLabel;

	private Text urlText;

	private DialogPage parentPage;

	private ScrolledComposite parentComposite;

	private Button retrieveObjectsRestrictionCheckBox;

	protected String DEFAULT_DATABASE_TEXT = ""; //$NON-NLS-1$

	protected String DEFAULT_HOST_TEXT = ""; //$NON-NLS-1$

	protected String DEFAULT_PORT_TEXT = "446"; //$NON-NLS-1$

	private Properties properties;
	private String urlOptionalParameters=""; //$NON-NLS-1$
	
	private boolean isReadOnly = false;
	
	protected OptionalPropertiesPane optionalPropsComposite;

	public boolean determineContributorCompletion() {
		boolean isComplete = true;
		if (databaseText.getText().trim().length() < 1) { //$NON-NLS-1$
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_DATABASE_REQ_UI_")); //$NON-NLS-1$
			isComplete = false;
		} else if (hostText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_HOST_REQ_UI_")); //$NON-NLS-1$
			isComplete = false;
		} else if (portText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_PORT_REQ_UI_")); //$NON-NLS-1$
			isComplete = false;
		} else if (usernameText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_USERID_REQ_UI_")); //$NON-NLS-1$
			isComplete = false;
		} else if (passwordText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_PASSWORD_REQ_UI_")); //$NON-NLS-1$
			isComplete = false;
		} else if (!tracingOptionsComposite.validateControl(parentPage)) {
			isComplete = false;
		} else if (!optionalPropsComposite.validateControl(parentPage)) {
            isComplete = false;
        } 

		if (isComplete) {
			parentPage.setErrorMessage(null);
		}
		return isComplete;
	}

	public Composite getContributedDriverUI(Composite parent, boolean isReadOnly) {
		if ((parentComposite == null) || parentComposite.isDisposed() || (this.isReadOnly != isReadOnly)) {
			GridData gd;

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

			TabFolder tabComposite = new TabFolder(parentComposite, SWT.TOP);

			TabItem driverOptionsTab = new TabItem(tabComposite, SWT.None);
			driverOptionsTab.setText(CUI_NEWCW_DRIVER_OPTIONS_TAB_UI_);

			TabItem tracingOptionsTab = new TabItem(tabComposite, SWT.None);
			tracingOptionsTab.setText(CUI_NEWCW_TRACING_OPTIONS_TAB_UI_);

			Composite driverOptionsComposite = new Composite(tabComposite,
					SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.numColumns = 3;
			driverOptionsComposite.setLayout(layout);
			driverOptionsTab.setControl(driverOptionsComposite);

			tracingOptionsComposite = new IBMJDBCDriverTracingOptionsPane(
					tabComposite, SWT.NULL, this, isReadOnly);
			tracingOptionsTab.setControl(tracingOptionsComposite);

			databaseLabel = new Label(driverOptionsComposite, SWT.NONE);
			databaseLabel.setText(CUI_NEWCW_DATABASE_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			databaseLabel.setLayoutData(gd);

			databaseText = new Text(driverOptionsComposite, SWT.SINGLE
					| SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalAlignment = GridData.FILL;
			gd.horizontalSpan = 2;
			databaseText.setLayoutData(gd);

			hostLabel = new Label(driverOptionsComposite, SWT.NONE);
			hostLabel.setText(CUI_NEWCW_HOST_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 1;
			hostLabel.setLayoutData(gd);

			hostText = new Text(driverOptionsComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 2;
			gd.grabExcessHorizontalSpace = true;
			hostText.setLayoutData(gd);

			portLabel = new Label(driverOptionsComposite, SWT.NONE);
			portLabel.setText(CUI_NEWCW_PORT_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 1;
			portLabel.setLayoutData(gd);

			portText = new Text(driverOptionsComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			portText.setLayoutData(gd);

			retrieveObjectsRestrictionCheckBox = new Button(
					driverOptionsComposite, SWT.CHECK);
			retrieveObjectsRestrictionCheckBox
					.setText(CUI_NEWCW_TBCREATOR_LBL_UI);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 3;
			retrieveObjectsRestrictionCheckBox.setLayoutData(gd);

			usernameLabel = new Label(driverOptionsComposite, SWT.NONE);
			usernameLabel.setText(CUI_NEWCW_USERNAME_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			usernameLabel.setLayoutData(gd);

			usernameText = new Text(driverOptionsComposite, SWT.SINGLE
					| SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			usernameText.setLayoutData(gd);

			passwordLabel = new Label(driverOptionsComposite, SWT.NONE);
			passwordLabel.setText(CUI_NEWCW_PASSWORD_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			passwordLabel.setLayoutData(gd);

			passwordText = new Text(driverOptionsComposite, SWT.SINGLE
					| SWT.BORDER | SWT.PASSWORD | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			passwordText.setLayoutData(gd);

			this.savePasswordButton = new Button(driverOptionsComposite,
					SWT.CHECK);
			this.savePasswordButton.setText(CUI_NEWCW_SAVE_PASSWORD_LBL_UI_); //$NON-NLS-1$
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 3;
			gd.grabExcessHorizontalSpace = true;
			savePasswordButton.setLayoutData(gd);

			defaultSchemaLabel = new Label(driverOptionsComposite, SWT.NONE);
			defaultSchemaLabel.setText(CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			defaultSchemaLabel.setLayoutData(gd);

			defaultSchemaText = new Text(driverOptionsComposite, SWT.SINGLE
					| SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			defaultSchemaText.setLayoutData(gd);
			
			urlLabel = new Label(driverOptionsComposite, SWT.NONE);
			urlLabel.setText(CUI_NEWCW_CONNECTIONURL_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 1;
			urlLabel.setLayoutData(gd);

			urlText = new Text(driverOptionsComposite, SWT.MULTI | SWT.BORDER
					| SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			gd.widthHint = 190;
			gd.heightHint = 45;
			urlText.setLayoutData(gd);
			
			// add optional properties tab
	        TabItem optionalPropsTab = new TabItem( tabComposite, SWT.None );
	        optionalPropsTab.setText( ConnectivityUIPlugin.getDefault()
	                .getResourceString( "CommonDriverUIContributor.optionaltab" ) ); //$NON-NLS-1$
            optionalPropsComposite = createOptionalPropertiesPane( tabComposite, SWT.NULL, isReadOnly );
            optionalPropsTab.setControl( optionalPropsComposite );

			parentComposite.setContent(tabComposite);
			parentComposite.setMinSize(tabComposite.computeSize(SWT.DEFAULT,
					SWT.DEFAULT));

			initialize();
		}

		return parentComposite;
	}

	public List getSummaryData() {
		List summaryData = new ArrayList();

		summaryData.add(new String[] { CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_,
				this.databaseText.getText().trim() });
		summaryData.add(new String[] { CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_,
				this.hostText.getText().trim() });
		summaryData.add(new String[] { CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_,
				this.portText.getText().trim() });
		summaryData
				.add(new String[] {
						CUI_NEWCW_RETRIEVE_OBJECTS_RESTRICTION_SUMMARY_DATA_TEXT_,
						retrieveObjectsRestrictionCheckBox.getSelection() ? CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_
								: CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ });
		summaryData.add(new String[] { CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_,
				this.usernameText.getText().trim() });
		summaryData
				.add(new String[] {
						CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_,
						savePasswordButton.getSelection() ? CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_
								: CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ });
		summaryData.add(new String[] {
				CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_,
				this.defaultSchemaText.getText().trim() });
		summaryData.add(new String[] { CUI_NEWCW_URL_SUMMARY_DATA_TEXT_,
				this.urlText.getText().trim() });
		return summaryData;
	}

	public void loadProperties() {
		removeListeners();
		DB2JDBCURL url = new DB2JDBCURL(this.properties
				.getProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID));
		hostText.setText(url.getNode());
		portText.setText(url.getPort());
		databaseText.setText(url.getDatabaseName());

		String tbCreator = this.properties
				.getProperty(IZSeriesDriverDefinitionConstants.FILTER_ON_TBCREATOR_PROPERTY_ID);
		if ((tbCreator != null)
				&& tbCreator
						.equals(IZSeriesDriverDefinitionConstants.FILTER_ON_TBCREATOR_VALUE_TRUE)) {
			retrieveObjectsRestrictionCheckBox.setSelection(true);
		}
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
		String defaultSchema = this.properties
		.getProperty(IJDBCConnectionProfileConstants.DEFAULT_SCHEMA_PROP_ID);
		if (defaultSchema != null) {
			defaultSchemaText.setText(defaultSchema);
		}
		tracingOptionsComposite.loadProperties(url.getProperties());
		
		// load optional connection properties
		optionalPropsComposite.loadProperties();
		
		updateURL();
		addListeners();
		setConnectionInformation();
	}

	private void initialize() {
		updateURL();
		addListeners();
	}

	public void setDialogPage(DialogPage parentPage) {
		this.parentPage = parentPage;
	}

	public void setDriverUIContributorInformation(
			IDriverUIContributorInformation contributorInformation) {
		this.contributorInformation = contributorInformation;
		this.properties = contributorInformation.getProperties();
		optionalPropsComposite.setDriverUIContributorInformation( contributorInformation );
	}

	public void setConnectionInformation() {
		properties.setProperty(
				IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID,
				this.databaseText.getText().trim());
		properties
				.setProperty(
						IZSeriesDriverDefinitionConstants.FILTER_ON_TBCREATOR_PROPERTY_ID,
						String.valueOf(this.retrieveObjectsRestrictionCheckBox
								.getSelection()));
		properties.setProperty(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID,
				this.passwordText.getText());
		properties.setProperty(
				IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID, String
						.valueOf(savePasswordButton.getSelection()));
		properties.setProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID,
				this.usernameText.getText());
		properties.setProperty(
				IJDBCConnectionProfileConstants.DEFAULT_SCHEMA_PROP_ID,
				this.defaultSchemaText.getText().trim());
		properties.setProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID,
				this.urlText.getText().trim());
		
        optionalPropsComposite.setConnectionInformation();
        
		this.contributorInformation.setProperties(properties);
	}

	public void updateURL() {
		String url = "jdbc:db2://" + hostText.getText().trim() + ":" //$NON-NLS-1$ //$NON-NLS-2$
				+ portText.getText().trim()
				+ "/" + databaseText.getText().trim() //$NON-NLS-1$
				+ ":retrieveMessagesFromServerOnGetMessage=true;emulateParameterMetaDataForZCalls=1;" //$NON-NLS-1$
				+ tracingOptionsComposite.getTracingURLProperties();
		url += getURLOptionalParameters();
		urlText.setText(url);
	}

	public void handleEvent(Event event) {
		if (isReadOnly){
			if (event.widget == savePasswordButton){
				savePasswordButton.setSelection(!savePasswordButton.getSelection());
			} else if 	(event.widget == retrieveObjectsRestrictionCheckBox){
				retrieveObjectsRestrictionCheckBox.setSelection(!retrieveObjectsRestrictionCheckBox.getSelection());
			}
		} else {
			updateURL();
			setConnectionInformation();
		}
	}

	private void addListeners() {
		databaseText.addListener(SWT.Modify, this);
		hostText.addListener(SWT.Modify, this);
		portText.addListener(SWT.Modify, this);
		usernameText.addListener(SWT.Modify, this);
		passwordText.addListener(SWT.Modify, this);
		savePasswordButton.addListener(SWT.Selection, this);
		defaultSchemaText.addListener(SWT.Modify, this);
		retrieveObjectsRestrictionCheckBox.addListener(SWT.Selection, this);
	}

	private void removeListeners() {
		databaseText.removeListener(SWT.Modify, this);
		hostText.removeListener(SWT.Modify, this);
		portText.removeListener(SWT.Modify, this);
		usernameText.removeListener(SWT.Modify, this);
		passwordText.removeListener(SWT.Modify, this);
		savePasswordButton.removeListener(SWT.Selection, this);
		defaultSchemaText.removeListener(SWT.Modify, this);
		retrieveObjectsRestrictionCheckBox.removeListener(SWT.Selection, this);
	}
	
	/**
	 * Creates an OptionalPropertiesPane.  
	 * Extenders may override to return an extended composite.
	 */
	protected OptionalPropertiesPane createOptionalPropertiesPane( Composite parent, int style, 
            boolean useReadOnlyControls )
	{
	    return new OptionalPropertiesPane( parent, style, useReadOnlyControls );
	}

	protected class DB2JDBCURL {
		private String subprotocol = ""; //$NON-NLS-1$

		private String node = ""; //$NON-NLS-1$

		private String port = ""; //$NON-NLS-1$

		private String databaseName = ""; //$NON-NLS-1$

		private String properties = ""; //$NON-NLS-1$

		/**
		 * @param url
		 */
		public DB2JDBCURL(String url) {
			parseURL(url);
		}

		/**
		 * @return Returns the databaseName.
		 */
		public String getDatabaseName() {
			return databaseName;
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

		private void parseURL(String url) {
			try {
				setURLOptionalParameters(""); //$NON-NLS-1$
				String remainingURL = url.substring(url.indexOf(':') + 1);
				this.subprotocol = remainingURL.substring(0, remainingURL
						.indexOf(':'));
				remainingURL = remainingURL
						.substring(remainingURL.indexOf(':') + 3);
				this.node = remainingURL
						.substring(0, remainingURL.indexOf('/'));
				if (this.node.indexOf(':') > -1) {
					this.port = this.node.substring(this.node.indexOf(':') + 1);
					this.node = this.node.substring(0, this.node.indexOf(':'));
				}
				remainingURL = remainingURL
						.substring(remainingURL.indexOf('/') + 1);
				if (remainingURL.indexOf(':') > -1) {
					this.databaseName = remainingURL.substring(0, remainingURL
							.indexOf(':'));
					remainingURL = remainingURL.substring(remainingURL
							.indexOf(':') + 1);
					this.properties = remainingURL;
				} else {
					this.databaseName = remainingURL;
				}
				String userOptionalParameters=""; //$NON-NLS-1$
				String userParameter = ""; //$NON-NLS-1$
				if(remainingURL!=null && remainingURL.length()>0)
				{
					StringTokenizer st = new StringTokenizer(remainingURL, ";"); //$NON-NLS-1$
					int tokenLength = st.countTokens();
					for(int i=0; i< tokenLength; i++)
					{
						userParameter = st.nextToken();
						if(userParameter!=null && userParameter.length()>0){
							if((!userParameter.startsWith("retrieveMessagesFromServerOnGetMessage"))&&(!userParameter.startsWith("emulateParameterMetaDataForZCalls"))) //$NON-NLS-1$ //$NON-NLS-2$
								userOptionalParameters +=	userParameter+";"; //$NON-NLS-1$
																			
						}
					
				}

					setURLOptionalParameters(userOptionalParameters);
				}
			} catch (Exception e) {
			}
		}

		/**
		 * @return Returns the port.
		 */
		public String getPort() {
			return port;
		}

		/**
		 * @return Returns the properties.
		 */
		public String getProperties() {
			return properties;
		}
	}
	/**
	 *  Sets the URL optional properties.
	 */
	public void setURLOptionalParameters(String connProp)
	{
		this.urlOptionalParameters = connProp;
	}
	
	/**
	 * @return Returns the URL optional properties.
	 */
	public String getURLOptionalParameters()
	{
		return this.urlOptionalParameters;
	}
}
