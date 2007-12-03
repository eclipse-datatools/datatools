/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.iseries.internal.ui.drivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.ui.wizards.IConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributor;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributorInformation;
import org.eclipse.datatools.enablement.ibm.internal.ui.drivers.IIBMJDBCDriverProvider;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class DB2ISeriesToolboxDriverUIContributor implements
		IDriverUIContributor, IIBMJDBCDriverProvider, Listener {

	private static final String CUI_NEWCW_HOST_LBL_UI_ = Messages
			.getString("CUI_NEWCW_HOST_LBL_UI_");

	private static final String CUI_NEWCW_USERNAME_LBL_UI_ = Messages
			.getString("CUI_NEWCW_USERNAME_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_PASSWORD_LBL_UI_ = Messages
			.getString("CUI_NEWCW_PASSWORD_LBL_UI_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_CONNECTIONURL_LBL_UI_ = Messages
			.getString("CUI_NEWCW_CONNECTIONURL_LBL_UI_");

	private static final String CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private static final String CUI_NEWCW_URL_SUMMARY_DATA_TEXT_ = Messages
			.getString("CUI_NEWCW_URL_SUMMARY_DATA_TEXT_"); //$NON-NLS-1$

	private Label hostLabel;

	private Text hostText;

	private Label usernameLabel;

	private Text usernameText;

	private Label passwordLabel;

	private Text passwordText;

	private Label urlLabel;

	private Text urlText;

	private DialogPage parentPage;

	private ScrolledComposite parentComposite;

	private IDriverUIContributorInformation contributorInformation;

	private Properties properties;

	public boolean determineContributorCompletion() {
		boolean isComplete = true;
		if (hostText.getText().trim().length() < 1) {
			parentPage.setErrorMessage(Messages
					.getString("CUI_NEWCW_VALIDATE_HOST_REQ_UI_")); //$NON-NLS-1$
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

	public Composite getContributedDriverUI(Composite parent) {
		if ((parentComposite == null) || parentComposite.isDisposed()) {
			GridData gd;

			parentComposite = new ScrolledComposite(parent, SWT.H_SCROLL
					| SWT.V_SCROLL);
			parentComposite.setExpandHorizontal(true);
			parentComposite.setExpandVertical(true);
			parentComposite.setLayout(new GridLayout());

			Composite baseComposite = new Composite(parentComposite, SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.numColumns = 3;
			baseComposite.setLayout(layout);

			hostLabel = new Label(baseComposite, SWT.NONE);
			hostLabel.setText(CUI_NEWCW_HOST_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			hostLabel.setLayoutData(gd);

			hostText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.horizontalSpan = 2;
			gd.grabExcessHorizontalSpace = true;
			hostText.setLayoutData(gd);

			usernameLabel = new Label(baseComposite, SWT.NONE);
			usernameLabel.setText(CUI_NEWCW_USERNAME_LBL_UI_);
			gd = new GridData();
			gd.verticalAlignment = GridData.BEGINNING;
			usernameLabel.setLayoutData(gd);

			usernameText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER);
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
					| SWT.PASSWORD);
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.BEGINNING;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			passwordText.setLayoutData(gd);

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

		summaryData.add(new String[] { CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_,
				this.hostText.getText().trim() });
		summaryData.add(new String[] { CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_,
				this.usernameText.getText().trim() });
		summaryData.add(new String[] { CUI_NEWCW_URL_SUMMARY_DATA_TEXT_,
				this.urlText.getText().trim() });
		return summaryData;
	}

	public void loadProperties() {
		removeListeners();
		DB2iSeriesJDBCURL url = new DB2iSeriesJDBCURL(this.properties
				.getProperty(IDriverDefinitionConstants.URL_PROP_ID));
		hostText.setText(url.getNode());
		String username = this.properties
				.getProperty(IDriverDefinitionConstants.USERNAME_PROP_ID);
		if (username != null) {
			usernameText.setText(username);
		}
		String password = this.properties
				.getProperty(IDriverDefinitionConstants.PASSWORD_PROP_ID);
		if (password != null) {
			passwordText.setText(password);
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

	public void setConnectionInformation() {
		properties.setProperty(
				IDriverDefinitionConstants.DATABASE_NAME_PROP_ID, this.hostText
						.getText().trim());
		properties.setProperty(IDriverDefinitionConstants.PASSWORD_PROP_ID,
				this.passwordText.getText());
		properties.setProperty(
				IConnectionProfileConstants.SAVE_PASSWORD_PROP_ID, String
						.valueOf(false));
		properties.setProperty(IDriverDefinitionConstants.USERNAME_PROP_ID,
				this.usernameText.getText());
		properties.setProperty(IDriverDefinitionConstants.URL_PROP_ID,
				this.urlText.getText().trim());
		this.contributorInformation.setProperties(properties);
	}

	public void updateURL() {
		String url = "jdbc:as400:" + hostText.getText().trim() + ";prompt=false"; //$NON-NLS-1$ //$NON-NLS-2$
		urlText.setText(url);
	}

	public void handleEvent(Event event) {
		updateURL();
		setConnectionInformation();
	}

	private void addListeners() {
		usernameText.addListener(SWT.Modify, this);
		passwordText.addListener(SWT.Modify, this);
		hostText.addListener(SWT.Modify, this);
	}

	private void removeListeners() {
		usernameText.removeListener(SWT.Modify, this);
		passwordText.removeListener(SWT.Modify, this);
		hostText.removeListener(SWT.Modify, this);
	}

	private class DB2iSeriesJDBCURL {
		private String subprotocol = ""; //$NON-NLS-1$

		private String node = ""; //$NON-NLS-1$

		private String properties = ""; //$NON-NLS-1$

		/**
		 * @param url
		 */
		public DB2iSeriesJDBCURL(String url) {
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

		private void parseURL(String url) {
			try {
				String remainingURL = url.substring(url.indexOf(':') + 1);
				this.subprotocol = remainingURL.substring(0, remainingURL
						.indexOf(':'));
				remainingURL = remainingURL
						.substring(remainingURL.indexOf(':') + 1);
				this.node = remainingURL
						.substring(0, remainingURL.indexOf(';'));
				remainingURL = remainingURL
						.substring(remainingURL.indexOf(';') + 1);
				this.properties = remainingURL;
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
}
