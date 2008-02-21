/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.DelimitedStringList;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

public class OtherDriverUIContributor implements IDriverUIContributor,
		Listener, ModifyListener {
	private ScrolledComposite scrolledComposite = null;

	private Text databaseNameText;

	private Text urlText;

	private Text usernameText;

	private Text passwordText;

	private Button savePasswordButton;

	private DelimitedStringList optionalConnectionProperties;

	private IDriverUIContributorInformation contributorInformation;

	private Properties properties;

	private DialogPage parentPage;
	
	private boolean isReadOnly = false;

	public Composite getContributedDriverUI(Composite parent, boolean isReadOnly) {
		if ((scrolledComposite == null) || scrolledComposite.isDisposed() || (this.isReadOnly != isReadOnly)) {
			this.isReadOnly = isReadOnly;
			int additionalStyles = SWT.NONE;
			if (isReadOnly){
				additionalStyles = SWT.READ_ONLY;
			}
			
			scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL
					| SWT.V_SCROLL);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			scrolledComposite.setLayout(new GridLayout());

			TabFolder parentComposite = new TabFolder(scrolledComposite,
					SWT.TOP);

			TabItem generalTab = new TabItem(parentComposite, SWT.None);
			generalTab.setText(ConnectivityUIPlugin.getDefault()
					.getResourceString("OtherDriverUIContributor.generaltab")); //$NON-NLS-1$

			TabItem optionalTab = new TabItem(parentComposite, SWT.None);
			optionalTab.setText(ConnectivityUIPlugin.getDefault()
					.getResourceString("OtherDriverUIContributor.optionaltab")); //$NON-NLS-1$

			Composite generalComposite = new Composite(parentComposite,
					SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.numColumns = 2;
			generalComposite.setLayout(layout);
			generalTab.setControl(generalComposite);

			Composite optionalComposite = new Composite(parentComposite,
					SWT.NULL);
			layout = new GridLayout();
			layout.numColumns = 1;
			optionalComposite.setLayout(layout);
			optionalTab.setControl(optionalComposite);

			Label databaseNameLabel = new Label(generalComposite, SWT.NULL);
			databaseNameLabel.setLayoutData(new GridData());
			databaseNameLabel.setText(ConnectivityUIPlugin.getDefault()
					.getResourceString(
							"OtherDriverUIContributor.databaseName.label")); //$NON-NLS-1$

			databaseNameText = new Text(generalComposite, SWT.BORDER | additionalStyles);
			databaseNameText.setLayoutData(new GridData(
					GridData.FILL_HORIZONTAL));

			Label urlLabel = new Label(generalComposite, SWT.NULL);
			urlLabel.setLayoutData(new GridData());
			urlLabel.setText(ConnectivityUIPlugin.getDefault()
					.getResourceString("OtherDriverUIContributor.url.label")); //$NON-NLS-1$

			urlText = new Text(generalComposite, SWT.BORDER | additionalStyles);
			urlText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			Label usernameLabel = new Label(generalComposite, SWT.NULL);
			usernameLabel.setLayoutData(new GridData());
			usernameLabel.setText(ConnectivityUIPlugin.getDefault()
					.getResourceString(
							"OtherDriverUIContributor.userName.label")); //$NON-NLS-1$

			usernameText = new Text(generalComposite, SWT.BORDER | additionalStyles);
			usernameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			Label passwordLabel = new Label(generalComposite, SWT.NULL);
			passwordLabel.setLayoutData(new GridData());
			passwordLabel.setText(ConnectivityUIPlugin.getDefault()
					.getResourceString(
							"OtherDriverUIContributor.password.label")); //$NON-NLS-1$

			passwordText = new Text(generalComposite, SWT.BORDER | SWT.PASSWORD | additionalStyles);
			passwordText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			savePasswordButton = new Button(generalComposite, SWT.CHECK);
			savePasswordButton.setText(ConnectivityUIPlugin.getDefault()
					.getResourceString(
							"OtherDriverUIContributor.savePassword.label")); //$NON-NLS-1$
			GridData gd = new GridData();
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
			optionalPropertiesLabel.setText(ConnectivityUIPlugin.getDefault()
					.getResourceString(
							"OtherDriverUIContributor.optionalProps.label")); //$NON-NLS-1$

			this.optionalConnectionProperties = new DelimitedStringList(
					optionalComposite, SWT.NONE, isReadOnly);
			gdata = new GridData(GridData.FILL_HORIZONTAL);
			gdata.horizontalSpan = 2;
			this.optionalConnectionProperties.setLayoutData(gdata);

			scrolledComposite.setContent(parentComposite);
			scrolledComposite.setMinSize(parentComposite.computeSize(
					SWT.DEFAULT, SWT.DEFAULT));
			initialize();
		}
		return scrolledComposite;
	}

	public void setConnectionInformation() {
		properties.setProperty(
				IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID,
				this.databaseNameText.getText().trim());
		properties.setProperty(IJDBCDriverDefinitionConstants.PASSWORD_PROP_ID,
				this.passwordText.getText());
		properties.setProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID,
				this.usernameText.getText());
		properties.setProperty(
				IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID, String
						.valueOf(savePasswordButton.getSelection()));
		properties.setProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID,
				this.urlText.getText().trim());
		properties.setProperty(
				IJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID,
				this.optionalConnectionProperties.getSelection());
		this.contributorInformation.setProperties(properties);
	}

	private void removeListeners() {
		databaseNameText.removeListener(SWT.Modify, this);
		urlText.removeListener(SWT.Modify, this);
		usernameText.removeListener(SWT.Modify, this);
		passwordText.removeListener(SWT.Modify, this);
		savePasswordButton.removeListener(SWT.Selection, this);
		optionalConnectionProperties.removeModifyListener(this);
	}

	private void addListeners() {
		databaseNameText.addListener(SWT.Modify, this);
		urlText.addListener(SWT.Modify, this);
		usernameText.addListener(SWT.Modify, this);
		passwordText.addListener(SWT.Modify, this);
		savePasswordButton.addListener(SWT.Selection, this);
		optionalConnectionProperties.addModifyListener(this);
	}

	private void initialize() {
		addListeners();
	}

	public void handleEvent(Event event) {
		if (isReadOnly){
			if (event.widget == savePasswordButton){
				savePasswordButton.setSelection(!savePasswordButton.getSelection());
			}
		} else {
			setConnectionInformation();
		}
	}

	public boolean determineContributorCompletion() {
		boolean isComplete = true;
		if (databaseNameText.getText().trim().length() < 1) { //$NON-NLS-1$
			parentPage
					.setErrorMessage(ConnectivityUIPlugin
							.getDefault()
							.getResourceString(
									"OtherDriverUIContributor.errormessage.requiresdatabase")); //$NON-NLS-1$
			isComplete = false;
		} else if (urlText.getText().trim().length() < 1) {
			parentPage
					.setErrorMessage(ConnectivityUIPlugin
							.getDefault()
							.getResourceString(
									"OtherDriverUIContributor.errormessage.requiresurl")); //$NON-NLS-1$
			isComplete = false;
		} else if (usernameText.getText().trim().length() < 1) {
			parentPage
					.setErrorMessage(ConnectivityUIPlugin
							.getDefault()
							.getResourceString(
									"OtherDriverUIContributor.errormessage.requiresusername")); //$NON-NLS-1$
			isComplete = false;
		} else if (OtherDriverUIContributor.this.optionalConnectionProperties
				.getWarning() != null) {
			parentPage
					.setErrorMessage(OtherDriverUIContributor.this.optionalConnectionProperties
							.getWarning());
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
	}

	public void loadProperties() {
		removeListeners();

		String databaseName = this.properties
				.getProperty(IJDBCDriverDefinitionConstants.DATABASE_NAME_PROP_ID);
		if (databaseName != null) {
			databaseNameText.setText(databaseName);
		}

		String url = this.properties
				.getProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID);
		if (url != null) {
			urlText.setText(url);
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

		String connectionProperties = this.properties
				.getProperty(IJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID);
		if (connectionProperties != null) {
			this.optionalConnectionProperties
					.setSelection(connectionProperties);
		}

		addListeners();
		setConnectionInformation();
	}

	public List getSummaryData() {
		List summaryData = new ArrayList();
		summaryData.add(new String[] {
				ConnectivityUIPlugin.getDefault().getResourceString(
						"OtherDriverUIContributor.summaryData.database"), //$NON-NLS-1$
				this.databaseNameText.getText().trim() });
		summaryData.add(new String[] {
				ConnectivityUIPlugin.getDefault().getResourceString(
						"OtherDriverUIContributor.summaryData.username"), //$NON-NLS-1$
				this.usernameText.getText().trim() });
		summaryData.add(new String[] {
				ConnectivityUIPlugin.getDefault().getResourceString(
						"OtherDriverUIContributor.summaryData.url"), //$NON-NLS-1$
				this.urlText.getText().trim() });
		summaryData
				.add(new String[] {
						ConnectivityUIPlugin
								.getDefault()
								.getResourceString(
										"OtherDriverUIContributor.summaryData.savePassword"),
						savePasswordButton.getSelection() ? ConnectivityUIPlugin
								.getDefault()
								.getResourceString(
										"OtherDriverUIContributor.summaryData.true")
								: ConnectivityUIPlugin
										.getDefault()
										.getResourceString(
												"OtherDriverUIContributor.summaryData.false") });
		return summaryData;
	}

	public void modifyText(ModifyEvent e) {
		handleEvent(new Event());
	}
}