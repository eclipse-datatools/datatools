/*******************************************************************************
 * Copyright 2007, 2014 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 *               Actuate Corporation - re-factored to an extendable base class and
 *                  added the optional properties tab
 ******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.internal.ui.drivers;

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
import org.eclipse.datatools.enablement.ibm.db2.luw.internal.ui.util.ResourceLoader;
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

import com.ibm.icu.text.MessageFormat;

public class DB2LUWKerberosDriverUIContributor implements IDriverUIContributor,
		IIBMJDBCDriverProvider, Listener {

	private static final String EMPTY = ""; //$NON-NLS-1$
	
	private String CUI_NEWCW_DATABASE_LBL_UI_ = ResourceLoader.CUI_NEWCW_DATABASE_LBL_UI_;

	private static final String CUI_NEWCW_HOST_LBL_UI_ = ResourceLoader.CUI_NEWCW_HOST_LBL_UI_;

	private static final String CUI_NEWCW_PORT_LBL_UI_ = ResourceLoader.CUI_NEWCW_PORT_LBL_UI_;

	private static final String CUI_NEWCW_CONNECTIONURL_LBL_UI_ = ResourceLoader.CUI_NEWCW_CONNECTIONURL_LBL_UI_;

	private static final String CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_ = ResourceLoader.CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_;

	private static final String CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_ = ResourceLoader.CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_;
	
	private static final String CUI_NEWCW_DRIVER_OPTIONS_TAB_UI_ = org.eclipse.datatools.enablement.ibm.internal.ui.drivers.Messages
			.getString("CUI_NEWCW_DRIVER_OPTIONS_TAB_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_TRACING_OPTIONS_TAB_UI_ = org.eclipse.datatools.enablement.ibm.internal.ui.drivers.Messages
			.getString("CUI_NEWCW_TRACING_OPTIONS_TAB_UI_"); //$NON-NLS-1$

	private static final String SECURITY_MECHANISM_PROPERTY_NAME = "securityMechanism"; //$NON-NLS-1$

	private static final String SECURITY_MECHANISM_PROPERTY_VALUE = "11"; //$NON-NLS-1$

	private static final String CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_ = ResourceLoader.CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_;

	private static final String CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_ = ResourceLoader.CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_;

	private static final String CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_ = ResourceLoader.CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_;

	private static final String CUI_NEWCW_URL_SUMMARY_DATA_TEXT_ = ResourceLoader.CUI_NEWCW_URL_SUMMARY_DATA_TEXT_;

	private static final String CUI_NEWCW_KERBOROS_KINIT_AUTHENTICATION_ = ResourceLoader.CUI_NEWCW_KERBOROS_KINIT_AUTHENTICATION;
	
	private static final String CUI_NEWCW_USERNAME_LBL_UI_ = ResourceLoader.CUI_NEWCW_USERNAME_LBL_UI_;
	
	private static final String CUI_NEWCW_PASSWORD_LBL_UI_ = ResourceLoader.CUI_NEWCW_PASSWORD_LBL_UI_;
	
	private static final String CUI_NEWCW_VALIDATE_USERID_REQ_UI_ = ResourceLoader.CUI_NEWCW_VALIDATE_USERID_REQ_UI_;
	
	private static final String CUI_NEWCW_VALIDATE_PASSWORD_REQ_UI_ = ResourceLoader.CUI_NEWCW_VALIDATE_PASSWORD_REQ_UI_;
	
	private IBMJDBCDriverTracingOptionsPane tracingOptionsComposite;

	private Label databaseLabel;

	private Text databaseText;

	private Label hostLabel;

	private Text hostText;

	private Label portLabel;
	
	private String urlOptionalParameters="";
	
	private Button kinitAuthenticationButton;
	
	private Button savePasswordButton;
	
	private Label userNameLabel;
	
	private Label passwordLabel;
	
	private Text userNameText;
	
	private Text passwordText;
	
	private Text portText;

	private Label defaultSchemaLabel;

	private Text defaultSchemaText;
	
	private Label urlLabel;

	private Text urlText;

	private DialogPage parentPage;

	private ScrolledComposite parentComposite;

	private IDriverUIContributorInformation contributorInformation;

	private Properties properties;
	
	private boolean isReadOnly = false;
	
	protected OptionalPropertiesPane optionalPropsComposite;

	public Composite getContributedDriverUI(Composite parent, boolean isReadOnly) {
		if ((parentComposite == null) || parentComposite.isDisposed()
				|| (this.isReadOnly != isReadOnly)) {
			GridData gd;

			this.isReadOnly = isReadOnly;
			int additionalStyles = SWT.NONE;
			if (isReadOnly) {
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
			hostLabel.setLayoutData(gd);

			hostText = new Text(driverOptionsComposite, SWT.SINGLE | SWT.BORDER
					| additionalStyles);
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

			portText = new Text(driverOptionsComposite, SWT.SINGLE | SWT.BORDER
					| additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			portText.setLayoutData(gd);

			kinitAuthenticationButton = new Button(driverOptionsComposite, SWT.CHECK);
			kinitAuthenticationButton.setText(CUI_NEWCW_KERBOROS_KINIT_AUTHENTICATION_);
			gd = new GridData();
			gd.horizontalSpan = 3;
			gd.verticalAlignment = GridData.BEGINNING;
			kinitAuthenticationButton.setLayoutData(gd);
			
			userNameLabel = new Label(driverOptionsComposite, SWT.NONE);
			userNameLabel.setText(CUI_NEWCW_USERNAME_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			userNameLabel.setLayoutData(gd);

			userNameText = new Text(driverOptionsComposite, SWT.SINGLE | SWT.BORDER
					| additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			userNameText.setLayoutData(gd);

			passwordLabel = new Label(driverOptionsComposite, SWT.NONE);
			passwordLabel.setText(CUI_NEWCW_PASSWORD_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			passwordLabel.setLayoutData(gd);
			
			passwordText = new Text(driverOptionsComposite, SWT.SINGLE | SWT.BORDER
					| SWT.PASSWORD | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			passwordText.setLayoutData(gd);

			this.savePasswordButton = new Button(driverOptionsComposite,
					SWT.CHECK);
			this.savePasswordButton.setText(ResourceLoader.CUI_NEWCW_SAVE_PASSWORD_LBL_UI_); //$NON-NLS-1$
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
	
	 /**
     * Creates an OptionalPropertiesPane.  
     * Extenders may override to return an extended composite.
     */
    protected OptionalPropertiesPane createOptionalPropertiesPane( Composite parent, int style, 
            boolean useReadOnlyControls )
    {
        return new OptionalPropertiesPane( parent, style, useReadOnlyControls );
    }
    
	private void enableUserProperties()
	{
		boolean selection = kinitAuthenticationButton.getSelection();
		userNameLabel.setEnabled(!selection);
		passwordLabel.setEnabled(!selection);
		userNameText.setEnabled(!selection);
		passwordText.setEnabled(!selection);
		savePasswordButton.setEnabled(!selection);
		if (selection)
		{	
			String userName = userNameText.getText();
			String password = passwordText.getText();
			if (userName != null && !userName.equals(EMPTY) || password != null && !password.equals(EMPTY))
			{
				userNameText.removeListener(SWT.Modify, this);
				passwordText.removeListener(SWT.Modify, this);
				savePasswordButton.removeListener(SWT.Selection, this);
				userNameText.setText(EMPTY);
				passwordText.setText(EMPTY);
				savePasswordButton.setSelection(false);
				userNameText.addListener(SWT.Modify, this);
				passwordText.addListener(SWT.Modify, this);
				savePasswordButton.addListener(SWT.Selection, this);
			}
		}
	}
	
	public void setConnectionInformation() {
		properties.setProperty(
				IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID,
				this.databaseText.getText().trim());
		properties.setProperty(
				IJDBCConnectionProfileConstants.DEFAULT_SCHEMA_PROP_ID,
				this.defaultSchemaText.getText().trim());
		properties.setProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID,
				this.urlText.getText().trim());
		
		if (!this.kinitAuthenticationButton.getSelection())
		{
			properties.setProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID, this.userNameText.getText());
			properties.setProperty(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID, this.passwordText.getText());		
			properties.setProperty(IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID, String.valueOf(savePasswordButton.getSelection()));		}
		else
		{
			properties.remove(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID);
			properties.remove(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID);
			properties.remove(IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID);
		}
	
		optionalPropsComposite.setConnectionInformation();
		
		this.contributorInformation.setProperties(properties);
	}

	public void updateURL() {
		String url = "jdbc:db2://" + formatNode(hostText.getText().trim()) + ":" //$NON-NLS-1$ //$NON-NLS-2$
				+ portText.getText().trim()
				+ "/" + databaseText.getText().trim() //$NON-NLS-1$
				+ ":retrieveMessagesFromServerOnGetMessage=true;" //$NON-NLS-1$
				+ SECURITY_MECHANISM_PROPERTY_NAME + "=" //$NON-NLS-1$
				+ SECURITY_MECHANISM_PROPERTY_VALUE + ";" //$NON-NLS-1$
				+ tracingOptionsComposite.getTracingURLProperties();
		url += getURLOptionalParameters();
		urlText.setText(url);
	}

	private String formatNode(String node)
    {
    	if (node.indexOf(":") > -1) {
    		return "[" + node + "]";
    	}
    	return node;
    }
	
	private void removeListeners() {
		savePasswordButton.removeListener(SWT.Selection, this);
		kinitAuthenticationButton.removeListener(SWT.Selection, this);
		databaseText.removeListener(SWT.Modify, this);
		hostText.removeListener(SWT.Modify, this);
		portText.removeListener(SWT.Modify, this);
		userNameText.removeListener(SWT.Modify, this);
		passwordText.removeListener(SWT.Modify, this);
		defaultSchemaText.removeListener(SWT.Modify, this);
	}

	private void addListeners() {
		savePasswordButton.addListener(SWT.Selection, this);
		kinitAuthenticationButton.addListener(SWT.Selection, this);
		databaseText.addListener(SWT.Modify, this);
		hostText.addListener(SWT.Modify, this);
		portText.addListener(SWT.Modify, this);
		userNameText.addListener(SWT.Modify, this);
		passwordText.addListener(SWT.Modify, this);
		defaultSchemaText.addListener(SWT.Modify, this);
	}

	private void initialize() {
		updateURL();
		addListeners();
	}

	public void handleEvent(Event event) {
		if (isReadOnly) {
			if (event.widget == savePasswordButton) 
			{
				savePasswordButton.setSelection(!savePasswordButton.getSelection());
			}
		} else {
			enableUserProperties ();
			updateURL();
			setConnectionInformation();
		}
	}

	public boolean determineContributorCompletion() {
		boolean isComplete = true;
		if (databaseText.getText().trim().length() < 1) {
			parentPage
					.setErrorMessage(MessageFormat
							.format(
									(ResourceLoader.CUI_NEWCW_VALIDATE_DATABASE_REQ_UI_),
									new Object[] { ResourceLoader.CUI_NEWCW_DEFDBNAME_VAL_UI_ }));
			isComplete = false;
		} else if (hostText.getText().trim().length() < 1) {
			parentPage
					.setErrorMessage(ResourceLoader.CUI_NEWCW_VALIDATE_HOST_REQ_UI_);
			isComplete = false;
		} else if (portText.getText().trim().length() < 1) {
			parentPage
					.setErrorMessage(ResourceLoader.CUI_NEWCW_VALIDATE_PORT_REQ_UI_);
			isComplete = false;
		} 
		else if (!kinitAuthenticationButton.getSelection() && userNameText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(CUI_NEWCW_VALIDATE_USERID_REQ_UI_); //$NON-NLS-1$
			isComplete = false;
		} else if (!kinitAuthenticationButton.getSelection() &&  passwordText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(CUI_NEWCW_VALIDATE_PASSWORD_REQ_UI_); //$NON-NLS-1$
			isComplete = false;
		}		
		else if (!tracingOptionsComposite.validateControl(parentPage)) {
			isComplete = false;
		}  else if (!optionalPropsComposite.validateControl(parentPage)) {
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
		String defaultSchema = this.properties
		.getProperty(IJDBCConnectionProfileConstants.DEFAULT_SCHEMA_PROP_ID);
		if (defaultSchema != null) {
			defaultSchemaText.setText(defaultSchema);
		}
		DB2JDBCURL url = new DB2JDBCURL(this.properties
				.getProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID));
		hostText.setText(url.getNode());
		portText.setText(url.getPort());
		databaseText.setText(url.getDatabaseName());
		tracingOptionsComposite.loadProperties(url.getProperties());

		String username = this.properties.getProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID);
		if (username != null) 
		{
			userNameText.setText(username);
			this.kinitAuthenticationButton.setSelection(false);
		}
		else
		{
			this.kinitAuthenticationButton.setSelection(true);
			userNameText.setEnabled(false);
		}
		
		String password = this.properties.getProperty(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID);
		if (password != null) 
		{
			passwordText.setText(password);
			String savePassword = this.properties.getProperty(IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID);
			if ((savePassword != null) && Boolean.valueOf(savePassword) == Boolean.TRUE) 
			{
				savePasswordButton.setSelection(true);
			}
		}
		else if (this.kinitAuthenticationButton.getSelection())
		{
			passwordText.setEnabled(false);
			savePasswordButton.setEnabled(false);
		}
		
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
		if (!this.kinitAuthenticationButton.getSelection())
		{
			summaryData.add(new String[] { ResourceLoader.CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_,
					this.userNameText.getText().trim() });
			summaryData
					.add(new String[] {
							ResourceLoader.CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_,
							savePasswordButton.getSelection() ? ResourceLoader.CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_
									: ResourceLoader.CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ });
		}
		summaryData.add(new String[] { CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_,
				this.defaultSchemaText.getText().trim() });
		summaryData.add(new String[] { CUI_NEWCW_URL_SUMMARY_DATA_TEXT_,
				this.urlText.getText().trim() });
		return summaryData;
	}

	private class DB2JDBCURL {
		private String subprotocol = ""; //$NON-NLS-1$

		private String node = ""; //$NON-NLS-1$

		private String port = ""; //$NON-NLS-1$

		private String databaseName = ""; //$NON-NLS-1$

		private String properties = ""; //$NON-NLS-1$
		
		private String urlOptionalParameters=""; 

		/**
		 * @param url
		 */
		public DB2JDBCURL(String url) {
			if (url != null) {
				parseURL(url);
			}
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
				setURLOptionalParameters("");
				String remainingURL = url.substring(url.indexOf(':') + 1);
				this.subprotocol = remainingURL.substring(0, remainingURL
						.indexOf(':'));
				remainingURL = remainingURL
						.substring(remainingURL.indexOf(':') + 3);
				this.node = remainingURL
						.substring(0, remainingURL.indexOf('/'));
				
				if (node.indexOf('[') > -1 && node.indexOf("]:") > -1) {
					port = node.substring(node.indexOf("]:") + 2);
					node = node.substring(1, node.indexOf("]:"));							
				} else if (node.indexOf(':') > -1) {
					port = node.substring(node.indexOf(':') + 1);
					node = node.substring(0, node.indexOf(':'));
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
						if(remainingURL.indexOf(';') > -1){
							this.databaseName = remainingURL.substring(0,remainingURL.indexOf(';'));
							remainingURL=remainingURL.substring(remainingURL.indexOf(';')+1);
						}
						else{
							this.databaseName = remainingURL;
							remainingURL="";
						}
				}
				
				String userOptionalParameters="";
			     String userParameter = "";
			     if(remainingURL!=null && remainingURL.length()>0)
			     {
				     StringTokenizer st = new StringTokenizer(remainingURL, ";");
				     int tokenLength = st.countTokens();
				     for(int i=0; i< tokenLength; i++)
				     {  
				    	 userParameter = st.nextToken();
				    	 if(userParameter!=null && userParameter.length()>0){
				    		 
				    		 if(!(userParameter.startsWith("retrieveMessagesFromServerOnGetMessage") 
				    				 || userParameter.equals("securityMechanism=11")
				    				 || userParameter.startsWith("traceFile")
				    				 || userParameter.startsWith("traceFileAppend")
				    				 || userParameter.startsWith("traceLevel")
				    				 || userParameter.startsWith("traceDirectory")))
				    			 userOptionalParameters +=	userParameter+";"; 
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
