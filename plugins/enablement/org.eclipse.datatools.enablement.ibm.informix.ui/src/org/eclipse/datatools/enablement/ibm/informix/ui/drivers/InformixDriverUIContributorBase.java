/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 *               Actuate Corporation - re-factored to an extendable base class and
 *                  added the optional properties tab
 ******************************************************************************/
package org.eclipse.datatools.enablement.ibm.informix.ui.drivers;

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
import org.eclipse.datatools.enablement.ibm.informix.internal.ui.drivers.Messages;
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

public class InformixDriverUIContributorBase implements IDriverUIContributor,
		Listener {

	private static final String CUI_NEWCW_DATABASE_LBL_UI_ = Messages
			.getString("CUI_NEWCW_DATABASE_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_HOST_LBL_UI_ = Messages
			.getString("CUI_NEWCW_HOST_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PORT_LBL_UI_ = Messages
			.getString("CUI_NEWCW_PORT_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SERVER_LBL_UI_ = Messages
			.getString("CUI_NEWCW_SERVER_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_USERNAME_LBL_UI_ = Messages
			.getString("CUI_NEWCW_USERNAME_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PASSWORD_LBL_UI_ = Messages
			.getString("CUI_NEWCW_PASSWORD_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SAVE_PASSWORD_LBL_UI_ = Messages
			.getString("CUI_NEWCW_SAVE_PASSWORD_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_ = Messages
			.getString("CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_"); //$NON-NLS-1$
	
	private static final String CUI_NEWCW_CONNECTIONURL_LBL_UI_ = Messages
			.getString("CUI_NEWCW_CONNECTIONURL_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$
	
	private static final String CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_SERVER_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_SERVER_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

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

	protected Text databaseText;

	private Label hostLabel;

	protected Text hostText;

	private Label portLabel;

	protected Text portText;

	private Label serverLabel;

	protected Text serverText;

	private Label usernameLabel;

	private Text usernameText;

	private Label passwordLabel;

	private Label defaultSchemaLabel;

	private Text defaultSchemaText;
	
	private Text passwordText;

	private Button savePasswordButton;

	private Label urlLabel;

	protected Text urlText;

	protected DialogPage parentPage;

	private ScrolledComposite parentComposite;

    protected OptionalPropertiesPane optionalPropsComposite;

    private Properties properties;
	private String urlOptionalParameters=""; //$NON-NLS-1$
	
    protected boolean isReadOnly = false;

	public boolean determineContributorCompletion() {
		boolean isComplete = true;
		if (databaseText.getText().trim().length() < 1) {
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
		} else if (serverText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_SERVER_REQ_UI_")); //$NON-NLS-1$
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

			databaseText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalAlignment = GridData.FILL;
			gd.horizontalSpan = 2;
			databaseText.setLayoutData(gd);

			hostLabel = new Label(baseComposite, SWT.NONE);
			hostLabel.setText(CUI_NEWCW_HOST_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 1;
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
			gd.horizontalSpan = 1;
			portLabel.setLayoutData(gd);

			portText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			portText.setLayoutData(gd);

			serverLabel = new Label(baseComposite, SWT.NONE);
			serverLabel.setText(CUI_NEWCW_SERVER_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 1;
			serverLabel.setLayoutData(gd);

			serverText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			serverText.setLayoutData(gd);

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

			this.savePasswordButton = new Button(baseComposite, SWT.CHECK);
			this.savePasswordButton.setText(CUI_NEWCW_SAVE_PASSWORD_LBL_UI_);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 3;
			gd.grabExcessHorizontalSpace = true;
			savePasswordButton.setLayoutData(gd);

			defaultSchemaLabel = new Label(baseComposite, SWT.NONE);
			defaultSchemaLabel.setText(CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			defaultSchemaLabel.setLayoutData(gd);

			defaultSchemaText = new Text(baseComposite, SWT.SINGLE
					| SWT.BORDER | additionalStyles);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			defaultSchemaText.setLayoutData(gd);
			
			urlLabel = new Label(baseComposite, SWT.NONE);
			urlLabel.setText(CUI_NEWCW_CONNECTIONURL_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 1;
			urlLabel.setLayoutData(gd);

			urlText = new Text(baseComposite, SWT.MULTI | SWT.BORDER
					| SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			gd.widthHint = 190;
			gd.heightHint = 90;
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
		summaryData.add(new String[] { CUI_NEWCW_SERVER_SUMMARY_DATA_TEXT_,
				this.serverText.getText().trim() });
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
		InformixJDBCURL url = getURLHandle(this.properties
				.getProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID));
		hostText.setText(url.getNode());
		portText.setText(url.getPort());
		serverText.setText(url.getServer());
		databaseText.setText(url.getDatabaseName());

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
			updateURL();
			setConnectionInformation();
		}
	}

	private void initialize() {
		updateURL();
		addListeners();
	}

	protected void updateURL() {
        String url = getURLHandle(hostText.getText().trim(), portText.getText().trim(), 
                                databaseText.getText().trim(), serverText.getText().trim())
                            .formatURL();
        url += getURLOptionalParameters();
        urlText.setText(url);
	}

	private void addListeners() {
		databaseText.addListener(SWT.Modify, this);
		hostText.addListener(SWT.Modify, this);
		portText.addListener(SWT.Modify, this);
		serverText.addListener(SWT.Modify, this);
		usernameText.addListener(SWT.Modify, this);
		passwordText.addListener(SWT.Modify, this);
		savePasswordButton.addListener(SWT.Selection, this);
		defaultSchemaText.addListener(SWT.Modify, this);
	}

	private void removeListeners() {
		databaseText.removeListener(SWT.Modify, this);
		hostText.removeListener(SWT.Modify, this);
		portText.removeListener(SWT.Modify, this);
		serverText.removeListener(SWT.Modify, this);
		usernameText.removeListener(SWT.Modify, this);
		passwordText.removeListener(SWT.Modify, this);
		savePasswordButton.removeListener(SWT.Selection, this);
		defaultSchemaText.removeListener(SWT.Modify, this);
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
		properties.setProperty(
				IJDBCConnectionProfileConstants.DEFAULT_SCHEMA_PROP_ID,
				this.defaultSchemaText.getText().trim());
		properties.setProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID,
				this.urlText.getText().trim());
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
     * Returns an Informix JDBC URL handle that parses the specified JDBC URL.
     * Extenders may override to return an extended URL handle for its own JDBC URL format.
     * @param url   JDBC connection URL 
     * @return  an URL handle that provides accessors for each of the attributes found in the specified URL. 
     */
	protected InformixJDBCURL getURLHandle( String url ) {
        return new InformixJDBCURL( url );
    }
    
    /**
     * Returns an Informix JDBC URL handle for the specified URL attributes.
     * @param node  TCP/IP address or TCP/IP host name of the server to which to connect
     * @param port  the number of the TCP/IP port.
     * @param databaseName  the name of the database to which to connect
     * @param server    the server name of the primary database server
     * @return  an URL handle that can format an Informix JDBC connection URL based on the specified attributes
     */
    protected InformixJDBCURL getURLHandle(String node, String port, String databaseName, String server) {
        return new InformixJDBCURL( node, port, databaseName, server );
    }

    /**
     * An Informix JDBC URL handle that formats and parses a driver-specific JDBC connection URL.
     */
	protected class InformixJDBCURL {
		protected String subprotocol = ""; //$NON-NLS-1$

		protected String node = ""; //$NON-NLS-1$

		protected String port = ""; //$NON-NLS-1$

		protected String server = ""; //$NON-NLS-1$

		protected String databaseName = ""; //$NON-NLS-1$

		protected String urlProperties = ""; //$NON-NLS-1$

		/**
		 * @param url
		 */
		public InformixJDBCURL(String url) {
			parseURL(url);
		}

        public InformixJDBCURL(String node, String port, String databaseName, String server) {
            this.node = node;
            this.port = port;
            this.databaseName = databaseName;
            this.server = server;
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
         * @return Returns the server.
         */
        public String getServer() {
            return server;
        }

        /**
         * @return Returns the URL properties.
         */
        public String getProperties() {
            return urlProperties;
        }

        /**
         * Formats the driver URL based on values specified in 
         * constructor {@link InformixJDBCURL(String, String, String, String)}.
         */
        protected String formatURL() {
            String url = "jdbc:informix-sqli://" + getNode() + ":" //$NON-NLS-1$ //$NON-NLS-2$
                            + getPort() + "/" + getDatabaseName() //$NON-NLS-1$
                            + ":INFORMIXSERVER=" + getServer() //$NON-NLS-1$
                            + ";DELIMIDENT=y;"; //$NON-NLS-1$      
            return url;
        }        

        /**
         * Parses the specified URL string into URL attributes that can be accessed 
         * by the corresponding getter methods.
         */
		protected void parseURL(String url) {
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
					if (remainingURL.indexOf("INFORMIXSERVER=") > -1){ //$NON-NLS-1$
						
						if(remainingURL.contains(";")){ //$NON-NLS-1$
						this.server = remainingURL.substring(new String(
									"INFORMIXSERVER=").length(), remainingURL //$NON-NLS-1$
									.indexOf(';'));
						}else
							
							this.server = remainingURL.substring(remainingURL.indexOf('=')+1);
						
						}
				
					this.urlProperties = remainingURL;
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
							if((!userParameter.startsWith("INFORMIXSERVER")) && (!userParameter.startsWith("DELIMIDENT"))) //$NON-NLS-1$ //$NON-NLS-2$
								userOptionalParameters +=	userParameter+";"; //$NON-NLS-1$
						}
					}

					setURLOptionalParameters(userOptionalParameters);
				}
			} catch (Exception e) {
			}
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
