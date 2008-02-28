/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.ibm.informix.internal.ui.drivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributor;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributorInformation;
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
import org.eclipse.swt.widgets.Text;

public class InformixDriverUIContributor implements IDriverUIContributor,
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

	private static final String CUI_NEWCW_CONNECTIONURL_LBL_UI_ = Messages
			.getString("CUI_NEWCW_CONNECTIONURL_LBL_UI_"); //$NON-NLS-1$

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

	private Text databaseText;

	private Label hostLabel;

	private Text hostText;

	private Label portLabel;

	private Text portText;

	private Label serverLabel;

	private Text serverText;

	private Label usernameLabel;

	private Text usernameText;

	private Label passwordLabel;

	private Text passwordText;

	private Button savePasswordButton;

	private Label urlLabel;

	private Text urlText;

	private DialogPage parentPage;

	private ScrolledComposite parentComposite;

	private Properties properties;
	
	private boolean isReadOnly = false;

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

			Composite baseComposite = new Composite(parentComposite, SWT.NONE);
			GridLayout layout = new GridLayout();
			layout.numColumns = 3;
			baseComposite.setLayout(layout);

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
			this.savePasswordButton.setText(CUI_NEWCW_SAVE_PASSWORD_LBL_UI_); //$NON-NLS-1$
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 3;
			gd.grabExcessHorizontalSpace = true;
			savePasswordButton.setLayoutData(gd);

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

			parentComposite.setContent(baseComposite);
			parentComposite.setMinSize(baseComposite.computeSize(SWT.DEFAULT,
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
		summaryData.add(new String[] { CUI_NEWCW_URL_SUMMARY_DATA_TEXT_,
				this.urlText.getText().trim() });

		return summaryData;
	}

	public void loadProperties() {
		removeListeners();
		InformixJDBCURL url = new InformixJDBCURL(this.properties
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

	private void updateURL() {
		String url = "jdbc:informix-sqli://" + hostText.getText() + ":" //$NON-NLS-1$ //$NON-NLS-2$
				+ portText.getText() + "/" + databaseText.getText() //$NON-NLS-1$
				+ ":INFORMIXSERVER=" + serverText.getText() //$NON-NLS-1$
				+ ";DELIMIDENT=y;"; //$NON-NLS-1$      
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
	}

	private void removeListeners() {
		databaseText.removeListener(SWT.Modify, this);
		hostText.removeListener(SWT.Modify, this);
		portText.removeListener(SWT.Modify, this);
		serverText.removeListener(SWT.Modify, this);
		usernameText.removeListener(SWT.Modify, this);
		passwordText.removeListener(SWT.Modify, this);
		savePasswordButton.removeListener(SWT.Selection, this);
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
		this.contributorInformation.setProperties(properties);
	}

	private class InformixJDBCURL {
		private String subprotocol = ""; //$NON-NLS-1$

		private String node = ""; //$NON-NLS-1$

		private String port = ""; //$NON-NLS-1$

		private String server = ""; //$NON-NLS-1$

		private String databaseName = ""; //$NON-NLS-1$

		private String properties = ""; //$NON-NLS-1$

		/**
		 * @param url
		 */
		public InformixJDBCURL(String url) {
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
					this.server = remainingURL.substring(new String(
							"INFORMIXSERVER=").length(), remainingURL //$NON-NLS-1$
							.indexOf(';')); //$NON-NLS-1$
					this.properties = remainingURL;
				} else {
					this.databaseName = remainingURL;
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
		 * @return Returns the server.
		 */
		public String getServer() {
			return server;
		}

		/**
		 * @return Returns the properties.
		 */
		public String getProperties() {
			return properties;
		}
	}
}
