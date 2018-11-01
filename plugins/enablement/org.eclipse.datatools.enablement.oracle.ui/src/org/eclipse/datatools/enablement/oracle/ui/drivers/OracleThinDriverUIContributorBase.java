/*
 *************************************************************************
 * Copyright (c) 2007, 2010 IBM Corporation and others. 
 * All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 *               Actuate Corporation - re-factored to an extendable base class and
 *                  added the optional properties tab
 *************************************************************************
 */

package org.eclipse.datatools.enablement.oracle.ui.drivers;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributor;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributorInformation;
import org.eclipse.datatools.connectivity.ui.wizards.OptionalPropertiesPane;
import org.eclipse.datatools.enablement.internal.oracle.IOracleDriverDefinitionConstants;
import org.eclipse.datatools.enablement.oracle.internal.ui.drivers.Messages;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

/**
 * Base class implementation that contributes UI controls to collect user input on 
 * Oracle JDBC connection properties.
 * Extenders may extend the nested URL handler class to format and parse their own JDBC URL
 * based on the user input.  Extenders are not expected to extend the UI controls.
 */
public class OracleThinDriverUIContributorBase implements IDriverUIContributor, Listener
{
    private static final String CUI_NEWCW_DATABASE_LBL_UI_ = TextProcessor.process(Messages
            .getString("CUI_NEWCW_DATABASE_LBL_UI_")); //$NON-NLS-1$

    private static final String CUI_NEWCW_HOST_LBL_UI_ = Messages
            .getString("CUI_NEWCW_HOST_LBL_UI_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_PORT_LBL_UI_ = Messages
            .getString("CUI_NEWCW_PORT_LBL_UI_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_USERNAME_LBL_UI_ = Messages
            .getString("CUI_NEWCW_USERNAME_LBL_UI_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_PASSWORD_LBL_UI_ = Messages
            .getString("CUI_NEWCW_PASSWORD_LBL_UI_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_SAVE_PASSWORD_LBL_UI_ = Messages
            .getString("CUI_NEWCW_SAVE_PASSWORD_LBL_UI_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_CONNECTIONURL_LBL_UI_ = Messages
            .getString("CUI_NEWCW_CONNECTIONURL_LBL_UI_"); //$NON-NLS-1$

    private static final String CATALOG_LBL_UI_ = Messages
            .getString("CATALOG_LBL_UI_"); //$NON-NLS-1$

    private static final String ALL_CATALOGS = Messages
            .getString("ALL_CATALOGS_OPTION_UI_"); //$NON-NLS-1$

    private static final String DBA_CATALOG = Messages
            .getString("DBA_CATALOG_OPTION_UI_"); //$NON-NLS-1$

    private static final String USER_CATALOG = Messages
            .getString("USER_CATALOG_OPTION_UI_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_ = Messages
            .getString("CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_ = Messages
            .getString("CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_ = Messages
            .getString("CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_CATALOG_SUMMARY_DATA_TEXT_ = Messages
            .getString("CUI_NEWCW_CATALOG_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_ = Messages
            .getString("CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_ = Messages
            .getString("CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_ = Messages
            .getString("CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ = Messages
            .getString("CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

    private static final String CUI_NEWCW_URL_SUMMARY_DATA_TEXT_ = Messages
            .getString("CUI_NEWCW_URL_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

    private IDriverUIContributorInformation contributorInformation;

    private Label databaseLabel;

    protected Button svcBtn;
    protected Button sidBtn;
    protected boolean isUsingService;

    protected Text databaseText;
    private Text serviceText;
    private Text sidText;

    private Label hostLabel;

    protected Text hostText;

    private Label portLabel;

    protected Text portText;

    private Label usernameLabel;

    private Text usernameText;

    private Label passwordLabel;

    private Text passwordText;

    private Button savePasswordButton;

    private Label urlLabel;

    protected Text urlText;

    private Label catalogLabel;

    private Combo catalogCombo;

    protected DialogPage parentPage;

    private ScrolledComposite parentComposite;

    protected OptionalPropertiesPane optionalPropsComposite;

    private Properties properties;
    
    protected boolean isReadOnly = false;

    public boolean determineContributorCompletion() {
        boolean isComplete = true;
        if (databaseText.getText().trim().length() < 1) {
        	if (!isDialogUsingService()) {
	            parentPage.setErrorMessage(Messages
	                    .getString("CUI_NEWCW_VALIDATE_DATABASE_REQ_UI_")); //$NON-NLS-1$
        	} else {
                parentPage.setErrorMessage(Messages
                        .getString("CUI_NEWCW_VALIDATE_SERVICE_REQ_UI_")); //$NON-NLS-1$
        	}
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
        } else if (!optionalPropsComposite.validateControl(parentPage)) {
            isComplete = false;
        } 

        if (isComplete) {
            parentPage.setErrorMessage(null);
        }
        return isComplete;
    }

    public Composite getContributedDriverUI(Composite parent, boolean isReadOnlyUI) {
        if ((parentComposite == null) || parentComposite.isDisposed() || (this.isReadOnly != isReadOnlyUI)) {
            GridData gd;

            this.isReadOnly = isReadOnlyUI;
            int additionalStyles = SWT.NONE;
            if (isReadOnlyUI){
                additionalStyles = SWT.READ_ONLY;
            }
            
            parentComposite = new ScrolledComposite(parent, SWT.H_SCROLL
                    | SWT.V_SCROLL);
            parentComposite.setExpandHorizontal(true);
            parentComposite.setExpandVertical(true);
            parentComposite.setLayout(new GridLayout());

            TabFolder tabComposite = new TabFolder(parentComposite, SWT.TOP);

            // add general tab
            TabItem generalTab = new TabItem(tabComposite, SWT.None);
            generalTab.setText(ConnectivityUIPlugin.getDefault()
                    .getResourceString("CommonDriverUIContributor.generaltab")); //$NON-NLS-1$

            Composite baseComposite = new Composite(tabComposite, SWT.NULL);
            GridLayout layout = new GridLayout();
            layout.numColumns = 3;
            baseComposite.setLayout(layout);
            generalTab.setControl(baseComposite);

            databaseLabel = new Label(baseComposite, SWT.NONE);
            databaseLabel.setText(CUI_NEWCW_DATABASE_LBL_UI_);
            gd = new GridData();
            gd.verticalAlignment = GridData.BEGINNING;
            databaseLabel.setLayoutData(gd);

			svcBtn = new Button( baseComposite, SWT.RADIO );
			svcBtn.setText( "Service Name" ); // Carl NON-NLS
			svcBtn.setSelection(true);
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.horizontalSpan = 1;
    		svcBtn.setLayoutData(gd);

            serviceText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.horizontalSpan = 1;
            gd.grabExcessHorizontalSpace = true;
    		serviceText.setLayoutData(gd);

            databaseText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.horizontalSpan = 1;
            gd.grabExcessHorizontalSpace = false;
            gd.widthHint = 0;
    		databaseText.setLayoutData(gd);
    		databaseText.setEnabled(false);
    		databaseText.setVisible(false);


			sidBtn = new Button( baseComposite, SWT.RADIO );
			sidBtn.setText( "SID" );
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.horizontalSpan = 1;
    		sidBtn.setLayoutData(gd);

            sidText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.horizontalSpan = 1;
            gd.grabExcessHorizontalSpace = true;
    		sidText.setLayoutData(gd);
    		sidText.setEnabled(false);

            urlLabel = new Label(baseComposite, SWT.NONE);
            urlLabel.setText(CUI_NEWCW_CONNECTIONURL_LBL_UI_);
            gd = new GridData();
            gd.verticalAlignment = GridData.BEGINNING;
            urlLabel.setLayoutData(gd);

            urlText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER
                    | SWT.READ_ONLY);
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalSpan = 2;
            urlText.setLayoutData(gd);

            
            hostLabel = new Label(baseComposite, SWT.NONE);
            hostLabel.setText(CUI_NEWCW_HOST_LBL_UI_);
            gd = new GridData();
            gd.verticalAlignment = GridData.BEGINNING;
            hostLabel.setLayoutData(gd);

            hostText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.horizontalSpan = 2;
            gd.grabExcessHorizontalSpace = true;
            hostText.setLayoutData(gd);

            portLabel = new Label(baseComposite, SWT.NONE);
            portLabel.setText(CUI_NEWCW_PORT_LBL_UI_);
            gd = new GridData();
            gd.verticalAlignment = GridData.BEGINNING;
            portLabel.setLayoutData(gd);

            portText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalSpan = 2;
            portText.setLayoutData(gd);

            usernameLabel = new Label(baseComposite, SWT.NONE);
            usernameLabel.setText(CUI_NEWCW_USERNAME_LBL_UI_);
            gd = new GridData();
            gd.verticalAlignment = GridData.BEGINNING;
            usernameLabel.setLayoutData(gd);

            usernameText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalSpan = 2;
            usernameText.setLayoutData(gd);

            passwordLabel = new Label(baseComposite, SWT.NONE);
            passwordLabel.setText(CUI_NEWCW_PASSWORD_LBL_UI_);
            gd = new GridData();
            gd.verticalAlignment = GridData.BEGINNING;
            passwordLabel.setLayoutData(gd);

            passwordText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER
                    | SWT.PASSWORD | additionalStyles);
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalSpan = 2;
            passwordText.setLayoutData(gd);

            Label label = new Label(baseComposite, SWT.NONE);
            
            this.savePasswordButton = new Button(baseComposite, SWT.CHECK);
            this.savePasswordButton.setText(CUI_NEWCW_SAVE_PASSWORD_LBL_UI_);
            gd = new GridData();
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.BEGINNING;
            gd.horizontalSpan = 2;
            gd.grabExcessHorizontalSpace = true;
            savePasswordButton.setLayoutData(gd);

            catalogLabel = new Label(baseComposite, SWT.NONE);
            catalogLabel.setText(CATALOG_LBL_UI_);
            gd = new GridData();
            gd.verticalAlignment = GridData.BEGINNING;
            catalogLabel.setLayoutData(gd);

            catalogCombo = new Combo(baseComposite, SWT.SINGLE | SWT.BORDER
                    | SWT.READ_ONLY);
            catalogCombo.setEnabled(!isReadOnlyUI);
            catalogCombo.add(ALL_CATALOGS);
            catalogCombo.add(DBA_CATALOG);
            catalogCombo.add(USER_CATALOG);
            gd = new GridData();
            catalogCombo.setLayoutData(gd);

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

    private void initialize() {
        updateURL();
        addListeners();
    }

    private void addListeners() {
        serviceText.addListener(SWT.Modify, this);
        sidText.addListener(SWT.Modify, this);
        databaseText.addListener(SWT.Modify, this);
        hostText.addListener(SWT.Modify, this);
        portText.addListener(SWT.Modify, this);
        usernameText.addListener(SWT.Modify, this);
        passwordText.addListener(SWT.Modify, this);
        savePasswordButton.addListener(SWT.Selection, this);
        catalogCombo.addListener(SWT.Modify, this);
        svcBtn.addListener(SWT.Selection, this);
        sidBtn.addListener(SWT.Selection, this);
    }

    private void removeListeners() {
        serviceText.removeListener(SWT.Modify, this);
        sidText.removeListener(SWT.Modify, this);
        databaseText.removeListener(SWT.Modify, this);
        hostText.removeListener(SWT.Modify, this);
        portText.removeListener(SWT.Modify, this);
        usernameText.removeListener(SWT.Modify, this);
        passwordText.removeListener(SWT.Modify, this);
        savePasswordButton.removeListener(SWT.Selection, this);
        catalogCombo.removeListener(SWT.Modify, this);
        svcBtn.removeListener(SWT.Selection, this);
        sidBtn.removeListener(SWT.Selection, this);
    }

    protected void updateURL() {
        String url = getURLHandle(hostText.getText().trim(), portText.getText().trim(), databaseText.getText().trim())
                        .formatURL();
        urlText.setText(url);
    }

    public List getSummaryData() {
        List summaryData = new ArrayList();

        summaryData.add(new String[] { CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_,
                this.databaseText.getText().trim() });
        summaryData.add(new String[] { CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_,
                this.hostText.getText().trim() });
        summaryData.add(new String[] { CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_,
                this.portText.getText().trim() });
        summaryData.add(new String[] { CUI_NEWCW_CATALOG_SUMMARY_DATA_TEXT_,
                this.catalogCombo.getText().trim() });
        summaryData.add(new String[] { CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_,
                this.usernameText.getText().trim() });
        summaryData
                .add(new String[] {
                        CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_,
                        savePasswordButton.getSelection() ? CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_
                                : CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_ });
        summaryData.add(new String[] { CUI_NEWCW_URL_SUMMARY_DATA_TEXT_,
                this.urlText.getText().trim() });

        return summaryData;
    }

    public void loadProperties() {
        removeListeners();
        OracleJDBCURL url = getURLHandle(this.properties
                .getProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID));
        hostText.setText(url.getNode());
        portText.setText(url.getPort());
        databaseText.setText(url.getDatabaseName());

        this.isUsingService = url.isUrlUsingService();
		this.svcBtn.setSelection(this.isUsingService);
		this.sidBtn.setSelection(!this.isUsingService);
		serviceText.setText(this.isUsingService ? url.getDatabaseName() : ""); //NON-NLS-1$
		sidText.setText(!this.isUsingService ? url.getDatabaseName() : ""); //NON-NLS-1$

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
        String catalog = USER_CATALOG;
        String catalogSetting = this.properties
                .getProperty(IOracleDriverDefinitionConstants.CATALOG_TYPE_PROPERTY_ID);
        if (catalogSetting != null && catalogSetting
                .equals(IOracleDriverDefinitionConstants.CATALOG_TYPE_VALUE_USER)) {
            catalog = USER_CATALOG;
        } else if (catalogSetting != null && catalogSetting
                .equals(IOracleDriverDefinitionConstants.CATALOG_TYPE_VALUE_DBA)) {
            catalog = DBA_CATALOG;
        } else if (catalogSetting != null && catalogSetting
                .equals(IOracleDriverDefinitionConstants.CATALOG_TYPE_VALUE_ALL)) {
            catalog = ALL_CATALOGS;
        }
        catalogCombo.select(catalogCombo.indexOf(catalog));
        
        // load optional connection properties
        optionalPropsComposite.loadProperties();

        updateURL();
        addListeners();
        setConnectionInformation();
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

    public void handleEvent(Event event) {
        if (isReadOnly){
            if (event.widget == savePasswordButton){
                savePasswordButton.setSelection(!savePasswordButton.getSelection());
            }
        } else {
        	if (event.widget == svcBtn) {
        		serviceText.setEnabled(true);
        		databaseText.setText(serviceText.getText());
        		sidText.setEnabled(false);
        		this.isUsingService = true;
        	}
        	else if (event.widget == sidBtn) {
        		serviceText.setEnabled(false);
        		databaseText.setText(sidText.getText());
        		sidText.setEnabled(true);
        		this.isUsingService = false;
        	}
        	else if (event.widget == sidText) {
        		databaseText.setText(sidText.getText());
        	}
        	else if (event.widget == serviceText) {
        		databaseText.setText(serviceText.getText());
        	}
            updateURL();
            setConnectionInformation();
        }
    }

    private void setConnectionInformation() {
        properties.setProperty(
                IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID,
                this.databaseText.getText().trim());
        properties.setProperty(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID,
                this.passwordText.getText());
        properties.setProperty(
                IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID, String
                        .valueOf(savePasswordButton.getSelection()));
        properties.setProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID,
                this.usernameText.getText());
        properties.setProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID,
                this.urlText.getText().trim());

        String catalog = IOracleDriverDefinitionConstants.CATALOG_TYPE_VALUE_USER;
        String catalogSetting = catalogCombo.getText();
        if (catalogSetting.equals(USER_CATALOG)) {
            catalog = IOracleDriverDefinitionConstants.CATALOG_TYPE_VALUE_USER;
        } else if (catalogSetting.equals(DBA_CATALOG)) {
            catalog = IOracleDriverDefinitionConstants.CATALOG_TYPE_VALUE_DBA;
        } else if (catalogSetting.equals(ALL_CATALOGS)) {
            catalog = IOracleDriverDefinitionConstants.CATALOG_TYPE_VALUE_ALL;
        }
        properties.setProperty(
                IOracleDriverDefinitionConstants.CATALOG_TYPE_PROPERTY_ID,
                catalog);
        
        optionalPropsComposite.setConnectionInformation();

        this.contributorInformation.setProperties(properties);
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
     * Returns an Oracle JDBC URL handle that parses the specified JDBC URL.
     * Extenders may override to return an extended URL handle for its own JDBC URL format.
     * @param url   JDBC connection URL 
     * @return  an URL handle that provides accessors for each of the attributes found in the specified URL. 
     */
    protected OracleJDBCURL getURLHandle( String url ) {
        return new OracleJDBCURL( url );
    }
    
    /**
     * Returns an Oracle JDBC URL handle for the specified URL attributes.
     * @param node  TCP/IP address or TCP/IP host name of the server to which to connect
     * @param port  the number of the TCP/IP port.
     * @param sid   Oracle System Identifier that refers to the instance of the Oracle database running on the server
     * @return  an URL handle that can format an Oracle JDBC connection URL based on the specified attributes
     */
    protected OracleJDBCURL getURLHandle(String node, String port, String sid) {
        return new OracleJDBCURL( node, port, sid );
    }

	/**
	 * @return the isDialogUsingService
	 */
	protected boolean isDialogUsingService() {
		return this.isUsingService;
	}

	/**
	 * @param isDialogUsingService the isDialogUsingService to set
	 */
	protected void setDialogUsingService(boolean isDialogUsingService) {
		this.isUsingService = isDialogUsingService;
	}

    /**
     * An Oracle JDBC URL handle that formats and parses a driver-specific JDBC conection URL.
     */
    protected class OracleJDBCURL {
    	protected String subprotocol = ""; //$NON-NLS-1$

        protected String node = ""; //$NON-NLS-1$

        protected String port = ""; //$NON-NLS-1$

        protected String databaseName = ""; //$NON-NLS-1$

        protected String urlProperties = ""; //$NON-NLS-1$

        protected boolean isUrlUsingService = true;

        private static final String URL_SEPARATOR = ":"; //$NON-NLS-1$

    	protected static final String URL_ATTRIBUTE_NAME_SERVICE = "ServiceName"; //$NON-NLS-1$

    	protected static final String URL_ATTRIBUTE_NAME_SID = "SID"; //$NON-NLS-1$

        public OracleJDBCURL(String url) {
            parseURL(url);
        }

        public OracleJDBCURL(String node, String port, String databaseName ) {
            this.node = node;
            this.port = port;
            this.databaseName = databaseName;
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
         * Formats the driver URL based on values specified in 
         * constructor {@link OracleJDBCURL(String, String, String)}.
         */
        protected String formatURL() {
            String url = "jdbc:oracle:thin:@" + getNode() + URL_SEPARATOR //$NON-NLS-1$
                        + getPort() + URL_SEPARATOR + getDatabaseName();
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
                        .substring(remainingURL.indexOf(':') + 1);
                this.subprotocol = this.subprotocol
                        + remainingURL.substring(0, remainingURL.indexOf(':'));
                remainingURL = remainingURL
                        .substring(remainingURL.indexOf(':') + 2);
                this.node = remainingURL
                        .substring(0, remainingURL.indexOf(':'));
                remainingURL = remainingURL
                        .substring(remainingURL.indexOf(':') + 1);

                this.port = remainingURL
                        .substring(0, remainingURL.indexOf(':'));
                remainingURL = remainingURL
                        .substring(remainingURL.indexOf(':') + 1);
                this.databaseName = remainingURL;
            } catch (Exception e) {
            }
        }

		/**
		 * @return the isUsingService
		 */
		protected boolean isUrlUsingService() {
			return this.isUrlUsingService;
		}

		/**
		 * @param isUsingService the isUsingService to set
		 */
		protected void setUrlUsingService(boolean isUsingService) {
			this.isUrlUsingService = isUsingService;
		}

		protected void parseAttributes(String remainingURL) throws IOException {
			StringReader reader = new StringReader(remainingURL);
			Properties props = new Properties();
			props.load(reader);
			if (props.containsKey(URL_ATTRIBUTE_NAME_SERVICE))
			{
				this.databaseName = props.getProperty( URL_ATTRIBUTE_NAME_SERVICE );
				props.remove( URL_ATTRIBUTE_NAME_SERVICE );
				this.setUrlUsingService( true );
			}
			else if (props.containsKey(URL_ATTRIBUTE_NAME_SID))
			{
				this.databaseName = props.getProperty( URL_ATTRIBUTE_NAME_SID );
				props.remove( URL_ATTRIBUTE_NAME_SID );
				this.setUrlUsingService( false );
			}

			if ( !props.isEmpty() )
			{
				// Any database name has been removed (sid or servicename)
				this.urlProperties = props.toString();
			}
		}

    }

}

