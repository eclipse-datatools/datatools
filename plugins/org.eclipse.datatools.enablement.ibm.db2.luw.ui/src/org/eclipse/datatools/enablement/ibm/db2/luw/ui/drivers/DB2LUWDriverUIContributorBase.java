/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 *               Actuate Corporation - re-factored to an extendable base class and
 *                  added the optional properties tab
 ******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.ui.drivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributor;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributorInformation;
import org.eclipse.datatools.connectivity.ui.wizards.OptionalPropertiesPane;
import org.eclipse.datatools.enablement.ibm.db2.luw.internal.ui.drivers.Messages;
import org.eclipse.datatools.enablement.ibm.internal.ui.drivers.IBMJDBCDriverTracingOptionsPane;
import org.eclipse.datatools.enablement.ibm.internal.ui.drivers.IIBMJDBCDriverProvider;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

import com.ibm.icu.text.MessageFormat;

public class DB2LUWDriverUIContributorBase implements IDriverUIContributor,
		IIBMJDBCDriverProvider, Listener {

	protected String CUI_NEWCW_DATABASE_LBL_UI_ = Messages
			.getString("CUI_NEWCW_DATABASE_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_HOST_LBL_UI_ = Messages
			.getString("CUI_NEWCW_HOST_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PORT_LBL_UI_ = Messages
			.getString("CUI_NEWCW_PORT_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_CONNECTIONURL_LBL_UI_ = Messages
			.getString("CUI_NEWCW_CONNECTIONURL_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_CLIENTAUTHENTICATION_BTN_UI_ = Messages
			.getString("CUI_NEWCW_CLIENTAUTHENTICATION_BTN_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_USERNAME_LBL_UI_ = Messages
			.getString("CUI_NEWCW_USERNAME_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PASSWORD_LBL_UI_ = Messages
			.getString("CUI_NEWCW_PASSWORD_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SAVE_PASSWORD_LBL_UI_ = Messages
			.getString("CUI_NEWCW_SAVE_PASSWORD_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_ = Messages
			.getString("CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DRIVER_OPTIONS_TAB_UI_ = org.eclipse.datatools.enablement.ibm.internal.ui.drivers.Messages
			.getString("CUI_NEWCW_DRIVER_OPTIONS_TAB_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_TRACING_OPTIONS_TAB_UI_ = org.eclipse.datatools.enablement.ibm.internal.ui.drivers.Messages
			.getString("CUI_NEWCW_TRACING_OPTIONS_TAB_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_USE_CLIENT_AUTHENICATION_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_USE_CLIENT_AUTHENICATION_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_URL_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_URL_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CLIENT_AUTHETICATION_TEXT = "securityMechanism=4;"; //$NON-NLS-1$

	private IBMJDBCDriverTracingOptionsPane tracingOptionsComposite;

	protected OptionalPropertiesPane optionalPropsComposite;
	
	private Label databaseLabel;

	protected Text databaseText;

	private Label hostLabel;

	protected Text hostText;

	private Label portLabel;

	protected Text portText;

	private Label usernameLabel;

	private Text usernameText;

	private Label passwordLabel;

	private Label defaultSchemaLabel;

	private Text defaultSchemaText;

	private Text passwordText;

	private Button savePasswordButton;

	private Label urlLabel;

	protected Text urlText;

	protected Button clientAuthenticationCheckbox;

	protected DialogPage parentPage;

	private ScrolledComposite parentComposite;

	private IDriverUIContributorInformation contributorInformation;

    private Properties properties;

	protected boolean isReadOnly = false;
	
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
 
            // add general tab
			TabItem driverOptionsTab = new TabItem(tabComposite, SWT.None);
			driverOptionsTab.setText(CUI_NEWCW_DRIVER_OPTIONS_TAB_UI_);

            // add tracing options tab if applicable
			if( includeTracingOptionsTab() )
			{
    			TabItem tracingOptionsTab = new TabItem(tabComposite, SWT.None);
    			tracingOptionsTab.setText(CUI_NEWCW_TRACING_OPTIONS_TAB_UI_);
                tracingOptionsComposite = new IBMJDBCDriverTracingOptionsPane(
                        tabComposite, SWT.NULL, this, isReadOnly);
                tracingOptionsTab.setControl(tracingOptionsComposite);
			}
			
            // add optional properties tab
	        TabItem optionalPropsTab = new TabItem( tabComposite, SWT.None );
	        optionalPropsTab.setText( ConnectivityUIPlugin.getDefault()
	                .getResourceString( "CommonDriverUIContributor.optionaltab" ) ); //$NON-NLS-1$
            optionalPropsComposite = createOptionalPropertiesPane( tabComposite, SWT.NULL, isReadOnly );
            optionalPropsTab.setControl( optionalPropsComposite );

            // add general tab content
            Composite driverOptionsComposite = new Composite(tabComposite,
					SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.numColumns = 3;
			driverOptionsComposite.setLayout(layout);
			driverOptionsTab.setControl(driverOptionsComposite);

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
			portLabel.setLayoutData(gd);

			portText = new Text(driverOptionsComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			portText.setLayoutData(gd);

			clientAuthenticationCheckbox = new Button(driverOptionsComposite,
					SWT.CHECK);
			clientAuthenticationCheckbox
					.setText(CUI_NEWCW_CLIENTAUTHENTICATION_BTN_UI_);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 3;
			gd.grabExcessHorizontalSpace = true;
			clientAuthenticationCheckbox.setLayoutData(gd);
			clientAuthenticationCheckbox
					.addSelectionListener(new SelectionListener() {
						public void widgetDefaultSelected(SelectionEvent e) {

						}

						public void widgetSelected(SelectionEvent e) {
							if (!DB2LUWDriverUIContributorBase.this.isReadOnly){
								if (((Button) e.widget).getSelection()) {
									enableAuthenticationControls(false);
								} else {
									enableAuthenticationControls(true);
								}
							}
						}
					});

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
			this.savePasswordButton.setText(CUI_NEWCW_SAVE_PASSWORD_LBL_UI_);
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
			urlLabel.setLayoutData(gd);

			urlText = new Text(driverOptionsComposite, SWT.MULTI | SWT.BORDER
					| SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			gd.widthHint = 190;
			gd.heightHint = 90;
			urlText.setLayoutData(gd);

			parentComposite.setContent(tabComposite);
			parentComposite.setMinSize(tabComposite.computeSize(SWT.DEFAULT,
					SWT.DEFAULT));

			initialize();
		}
		return parentComposite;
	}

	protected void enableAuthenticationControls(boolean enabled) {
		usernameLabel.setEnabled(enabled);
		usernameText.setEnabled(enabled);
		passwordLabel.setEnabled(enabled);
		passwordText.setEnabled(enabled);
		savePasswordButton.setEnabled(enabled);
	}

	public void setConnectionInformation() {
		properties.setProperty(
				IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID,
				this.databaseText.getText().trim());
		if (!this.clientAuthenticationCheckbox.getSelection()) {
			properties.setProperty(
					IJDBCDriverDefinitionConstants.USERNAME_PROP_ID,
					this.usernameText.getText());
			properties.setProperty(
					IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID,
					this.passwordText.getText());
			properties.setProperty(
					IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID,
					String.valueOf(savePasswordButton.getSelection()));

		} else {
			properties.setProperty(
					IJDBCDriverDefinitionConstants.USERNAME_PROP_ID, System
							.getProperty("user.name")); //$NON-NLS-1$
			properties.remove(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID);
			properties.setProperty(
					IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID,
					String.valueOf(false));
		}

		properties.setProperty(
				IJDBCConnectionProfileConstants.DEFAULT_SCHEMA_PROP_ID,
				this.defaultSchemaText.getText().trim());
		properties.setProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID,
				this.urlText.getText().trim());
		
        optionalPropsComposite.setConnectionInformation();

		this.contributorInformation.setProperties(properties);
	}

	public void updateURL() {
	    String tracingProperties = includeTracingOptionsTab() && tracingOptionsComposite != null ? 
	            tracingOptionsComposite.getTracingURLProperties() : ""; //$NON-NLS-1$
        String url = getURLHandle(hostText.getText().trim(), 
                                portText.getText().trim(), 
                                databaseText.getText().trim(),
                                clientAuthenticationCheckbox.getSelection(), 
                                tracingProperties)
                            .formatURL();
		urlText.setText(url);
	}

	private void removeListeners() {
		databaseText.removeListener(SWT.Modify, this);
		hostText.removeListener(SWT.Modify, this);
		portText.removeListener(SWT.Modify, this);
		usernameText.removeListener(SWT.Modify, this);
		passwordText.removeListener(SWT.Modify, this);
		savePasswordButton.removeListener(SWT.Selection, this);
		defaultSchemaText.removeListener(SWT.Modify, this);
		clientAuthenticationCheckbox.removeListener(SWT.Selection, this);
	}

	private void addListeners() {
		databaseText.addListener(SWT.Modify, this);
		hostText.addListener(SWT.Modify, this);
		portText.addListener(SWT.Modify, this);
		usernameText.addListener(SWT.Modify, this);
		passwordText.addListener(SWT.Modify, this);
		savePasswordButton.addListener(SWT.Selection, this);
		defaultSchemaText.addListener(SWT.Modify, this);
		clientAuthenticationCheckbox.addListener(SWT.Selection, this);
	}

	private void initialize() {
		updateURL();
		addListeners();
	}

	public void handleEvent(Event event) {
		if (isReadOnly){
			if (event.widget == savePasswordButton){
				savePasswordButton.setSelection(!savePasswordButton.getSelection());
			} else if 	(event.widget == clientAuthenticationCheckbox){
				clientAuthenticationCheckbox.setSelection(!clientAuthenticationCheckbox.getSelection());
			}
		} else {
			updateURL();
			setConnectionInformation();
		}
	}

	public boolean determineContributorCompletion() {
		boolean isComplete = true;
		if (databaseText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(MessageFormat.format(Messages
					.getString("CUI_NEWCW_VALIDATE_DATABASE_REQ_UI_"), //$NON-NLS-1$
					new Object[] { Messages
							.getString("CUI_NEWCW_DEFDBNAME_VAL_UI_") })); //$NON-NLS-1$
			isComplete = false;
		} else if (hostText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_HOST_REQ_UI_")); //$NON-NLS-1$
			isComplete = false;
		} else if (portText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_PORT_REQ_UI_")); //$NON-NLS-1$
			isComplete = false;
		} else if (!clientAuthenticationCheckbox.getSelection()
				&& usernameText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_USERID_REQ_UI_")); //$NON-NLS-1$
			isComplete = false;
		} else if (!clientAuthenticationCheckbox.getSelection()
				&& passwordText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_PASSWORD_REQ_UI_")); //$NON-NLS-1$
			isComplete = false;

		} else if ( tracingOptionsComposite != null && !tracingOptionsComposite.validateControl(parentPage)) {
			isComplete = false;
        } else if (!optionalPropsComposite.validateControl(parentPage)) {
            isComplete = false;
        } 

		if (isComplete) {
			parentPage.setErrorMessage(null);
		}
		return isComplete;
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

	public void loadProperties() {
		removeListeners();
		DB2JDBCURL url = getURLHandle(this.properties
				.getProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID));
		hostText.setText(url.getNode());
		portText.setText(url.getPort());
		databaseText.setText(url.getDatabaseName());
		if (url.useClientAuthentication()) {
			clientAuthenticationCheckbox.setSelection(true);
			enableAuthenticationControls(false);
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
		if (tracingOptionsComposite != null)
			tracingOptionsComposite.loadProperties(url.getProperties());

		// load optional connection properties
		optionalPropsComposite.loadProperties();

		updateURL();
		addListeners();
		setConnectionInformation();
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
						CUI_NEWCW_USE_CLIENT_AUTHENICATION_SUMMARY_DATA_TEXT_,
						clientAuthenticationCheckbox.getSelection() ? CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_
								: CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ });
		if (!clientAuthenticationCheckbox.getSelection()) {
			summaryData.add(new String[] {
					CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_,
					this.usernameText.getText().trim() });
			summaryData
					.add(new String[] {
							CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_,
							savePasswordButton.getSelection() ? CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_
									: CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ });
		}
		summaryData.add(new String[] {
				CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_,
				this.defaultSchemaText.getText().trim() });
		summaryData.add(new String[] { CUI_NEWCW_URL_SUMMARY_DATA_TEXT_,
				this.urlText.getText().trim() });
		return summaryData;
	}

	/**
	 * Indicates whether this contributor should include the tracing options tab in the UI.
	 * Extenders may override to control whether to include the optional tab.
	 * @return true to include the tracing options tab; false otherwise.  Default is true.
	 */
	protected boolean includeTracingOptionsTab()
	{
	    return true;
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
	
    /**
     * Returns a DB2 JDBC URL handle that parses the specified JDBC URL.
     * Extenders may override to return an extended URL handle for its own JDBC URL format.
     * @param url   JDBC connection URL 
     * @return  an URL handle that provides accessors for each of the attributes found in the specified URL. 
     */
    protected DB2JDBCURL getURLHandle( String url ) {
        return new DB2JDBCURL( url );
    }
    
    /**
     * Returns a DB2 JDBC URL handle for the specified URL attributes.
     * @param node  TCP/IP address or TCP/IP host name of the server to which to connect
     * @param port  the number of the TCP/IP port.
     * @param databaseName  the name of the database to which to connect
     * @param useClientAuthentication indicates whether to rely on the client to authenticate the user,
     *          i.e. use the user ID of the user logged onto the system 
     *          on which the driver is running when establishing a connection.
     * @param tracingProperties trace logging URL properties; may be empty if none is specified
     * @return  an URL handle that can format a DB2 JDBC connection URL based on the specified attributes
     */
    protected DB2JDBCURL getURLHandle(String node, String port, String databaseName,
                                    boolean useClientAuthentication, String tracingProperties) {
        return new DB2JDBCURL( node, port, databaseName, useClientAuthentication, tracingProperties );
    }
	
    /**
     * A DB2 JDBC URL handle that formats and parses a driver-specific JDBC connection URL.
     */
	protected class DB2JDBCURL {
	    protected String subprotocol = ""; //$NON-NLS-1$

	    protected String node = ""; //$NON-NLS-1$

	    protected String port = ""; //$NON-NLS-1$

	    protected String databaseName = ""; //$NON-NLS-1$
	    
	    protected String urlProperties = ""; //$NON-NLS-1$

        protected boolean useClientAuthentication = false;

		/**
		 * @param url
		 */
		public DB2JDBCURL(String url) {
			if (url != null) {
				parseURL(url);
			}
		}

        public DB2JDBCURL(String node, String port, String databaseName, 
                boolean useClientAuthentication, String tracingProperties) {
            this.node = node;
            this.port = port;
            this.databaseName = databaseName;
            this.useClientAuthentication = useClientAuthentication;
            this.urlProperties = tracingProperties;
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
            return urlProperties;
        }

        /**
         * @return  Returns whether to use client authentication to connect.
         */
        public boolean useClientAuthentication() {
            return useClientAuthentication;
        }
        
        /**
         * Formats the driver URL based on values specified in 
         * constructor {@link DB2JDBCURL(String, String, String, boolean, String)}.
         */
        protected String formatURL() {
            String url = "jdbc:db2://" + getNode() + ":" //$NON-NLS-1$ //$NON-NLS-2$
                            + getPort()
                            + "/" + getDatabaseName() //$NON-NLS-1$
                            + ":retrieveMessagesFromServerOnGetMessage=true;"; //$NON-NLS-1$
            if( useClientAuthentication() )
                url += CLIENT_AUTHETICATION_TEXT;
            url += getProperties();
            return url;
        }        

        /**
         * Parses the specified URL string into URL attributes that can be accessed 
         * by the corresponding getter methods.
         */
		protected void parseURL(String url) {
			try {
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
					this.urlProperties = remainingURL;
					this.useClientAuthentication = (this.urlProperties.indexOf(CLIENT_AUTHETICATION_TEXT) > -1 );
				} else {
					this.databaseName = remainingURL;
				}
			} catch (Exception e) {
			}
		}
	}
}
