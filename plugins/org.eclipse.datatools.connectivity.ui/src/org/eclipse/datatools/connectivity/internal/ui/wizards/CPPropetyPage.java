/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PropertyPage;

public class CPPropetyPage extends PropertyPage {

	private Text txtProfileName;

	private Text txtProfileDesc;

	private Button btnAutoConnect;

	/**
	 * Constructor for FileProfilePropetyPage.
	 */
	public CPPropetyPage() {
		super();
		noDefaultAndApplyButton();
	}

	/**
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Label label;
		GridData gd;
		Composite content = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2, false);
		content.setLayout(layout);

		label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizardPage.cp_name")); //$NON-NLS-1$

		txtProfileName = new Text(content, SWT.BORDER);
		txtProfileName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtProfileName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				validate();
			}
		});

		label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizardPage.cp_desc")); //$NON-NLS-1$

		txtProfileDesc = new Text(content, SWT.BORDER);
		txtProfileDesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		btnAutoConnect = new Button(content, SWT.CHECK);
		btnAutoConnect.setText(ConnectivityUIPlugin.getDefault()
				.getResourceString(
						"NewConnectionProfileWizardPage.Button.AutoConnect1")); //$NON-NLS-1$

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.horizontalIndent = 20;
		btnAutoConnect.setLayoutData(gd);

		initControls();
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
				IHelpConstants.CONTEXT_ID_CP_PROPERTY_PAGE);

		return content;
	}

	public boolean performOk() {
		IConnectionProfile profile = getConnectionProfile();
		try {
			ProfileManager.getInstance().modifyProfile(profile,
					txtProfileName.getText(), txtProfileDesc.getText(),
					new Boolean(btnAutoConnect.getSelection()));
		}
		catch (ConnectionProfileException e) {
			ExceptionHandler.showException(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString("dialog.exception.title"), //$NON-NLS-1$
					ConnectivityUIPlugin.getDefault().getResourceString(
							"dialog.exception.message", //$NON-NLS-1$
							new Object[] { e.getMessage()}), e); //$NON-NLS-1$
		}
		return true;
	}

	private void initControls() {
		IConnectionProfile profile = getConnectionProfile();

		txtProfileName.setText(profile.getName());
		txtProfileDesc.setText(profile.getDescription());

		boolean showAutoConnect = profile.getProvider()
				.needsMaintainConnection();
		if (!showAutoConnect)

		{
			btnAutoConnect.setSelection(true);
			btnAutoConnect.setEnabled(false);
			btnAutoConnect.setVisible(false);
		}
		else {
			btnAutoConnect.setSelection(profile.isAutoConnect());
		}

		setErrorMessage(null);
	}

	private IConnectionProfile getConnectionProfile() {
		IAdaptable element = getElement();
		IConnectionProfile profile = (IConnectionProfile) element
				.getAdapter(IConnectionProfile.class);
		return profile;
	}

	private void validate() {
		String errorMessage = null;

		if (txtProfileName.getText().length() == 0) {
			errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.Status.NoName"); //$NON-NLS-1$
		}
		else {
			if (!getConnectionProfile().getName().equals(
					txtProfileName.getText()))
				if (ProfileManager.getInstance().getProfileByName(
						txtProfileName.getText()) != null)
					errorMessage = ConnectivityUIPlugin
							.getDefault()
							.getResourceString(
									"NewConnectionProfileWizardPage.Status.DuplicateName"); //$NON-NLS-1$		
		}

		setErrorMessage(errorMessage);
		setValid(errorMessage == null);
	}
}
