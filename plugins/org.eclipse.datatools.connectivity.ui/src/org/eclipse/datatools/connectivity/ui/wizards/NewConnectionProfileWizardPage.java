/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.wizards.BaseWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * @author shongxum
 */
public class NewConnectionProfileWizardPage extends BaseWizardPage {

	private Text mCPName;

	private Text mCPDesc;

	private Button mAutoConnect;

	private String mCPNameStr;
	private String mCPDescStr;
    private boolean mCPAutoConnectValue = false;
	private boolean _showAutoConnect = true;

    private static final String EMPTY_STRING = "";      //$NON-NLS-1$
    
	/**
	 * Constructor
	 */
	public NewConnectionProfileWizardPage() {
		this("NewConnectionProfileWizardPage"); //$NON-NLS-1$
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public NewConnectionProfileWizardPage(String name) {
		super(name);
		setTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizardPage.title")); //$NON-NLS-1$
		setDescription(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizardPage.desc")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		String providerID = ((NewConnectionProfileWizard) getWizard())
				.getProfileProviderID();
		IConnectionProfileProvider provider = ConnectionProfileManager
				.getInstance().getProvider(providerID);
		_showAutoConnect = provider.needsMaintainConnection();

		GridData gd;
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		composite.setLayout(gl);
		// Connection profile name
		new Label(composite, SWT.NONE).setText(ConnectivityUIPlugin
				.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.cp_name")); //$NON-NLS-1$
		mCPName = new Text(composite, SWT.BORDER);
		mCPName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				handleModify();
			}
		});

		mCPName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// Connection profile description
		new Label(composite, SWT.NONE).setText(ConnectivityUIPlugin
				.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.cp_desc")); //$NON-NLS-1$
		mCPDesc = new Text(composite, SWT.BORDER);
		mCPDesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// Separate
		Label label = new Label(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		mAutoConnect = new Button(composite, SWT.CHECK);
		mAutoConnect.setText(ConnectivityUIPlugin.getDefault()
				.getResourceString(
						"NewConnectionProfileWizardPage.Button.AutoConnect")); //$NON-NLS-1$

		if (!_showAutoConnect) {
			mAutoConnect.setSelection(true);
			mAutoConnect.setVisible(false);
			mAutoConnect.setEnabled(false);
		}
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.horizontalIndent = 20;
		mAutoConnect.setLayoutData(gd);

		if (mCPNameStr != null) {
			mCPName.setText(mCPNameStr);
		}
		if (mCPDescStr != null) {
			mCPDesc.setText(mCPDescStr);
		}

		setControl(composite);
		setPageComplete(false);
		if (mCPName.getText().length() > 0)
			setPageComplete(true);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
				IHelpConstants.CONTEXT_ID_NEW_CONNECTION_PROFILE_PAGE);
	}

	private void handleModify() {
		String errorMessage = null;
		String cpName = mCPName.getText();
		if (cpName == null || cpName.trim().length() == 0) {
			errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.Status.NoName"); //$NON-NLS-1$
		}
		else {
			if (ProfileManager.getInstance().getProfileByName(cpName) != null) {
				errorMessage = ConnectivityUIPlugin
						.getDefault()
						.getResourceString(
								"NewConnectionProfileWizardPage.Status.DuplicateName"); //$NON-NLS-1$
			}
		}
		setErrorMessage(errorMessage);
		setPageComplete(errorMessage == null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			mCPName.setFocus();
		}
	}

	public String getProfileName() {
        return ( mCPName == null || mCPName.isDisposed() ) ? 
                mCPNameStr : mCPName.getText();
	}

	public void setProfileName(String name) {
		this.mCPNameStr = name;
		if ( mCPName != null && ! mCPName.isDisposed() ) {
            if ( name == null )
                name = EMPTY_STRING;
			mCPName.setText(name);
		}
	}

	public String getProfileDescription() {
        return ( mCPDesc == null || mCPDesc.isDisposed() ) ? 
                mCPDescStr : mCPDesc.getText();
	}

	public void setProfileDescription(String desc) {
		this.mCPDescStr = desc;
		if ( mCPDesc != null && ! mCPDesc.isDisposed() ) {
            if ( desc == null )
                desc = EMPTY_STRING;
			mCPDesc.setText(desc);
		}
	}

	public boolean getAutoConnect() {
        return ( mAutoConnect == null || mAutoConnect.isDisposed() ) ? 
                mCPAutoConnectValue : mAutoConnect.getSelection();
	}

	public void setAutoConnect(boolean selection) {
        mCPAutoConnectValue = selection;
        if ( mAutoConnect != null && ! mAutoConnect.isDisposed() )
            mAutoConnect.setSelection(selection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.suade.common.ui.wizards.ISummaryDataSource#getSummaryData()
	 */
	public List getSummaryData() {
		List data = new ArrayList();

		data.add(new String[] {
				ConnectivityUIPlugin.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.summary_cp_name"), //$NON-NLS-1$
				getProfileName()});

		data.add(new String[] {
				ConnectivityUIPlugin.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.summary_cp_desc"), //$NON-NLS-1$
				getProfileDescription()});

		if (_showAutoConnect) {
			data
					.add(new String[] {
							ConnectivityUIPlugin
									.getDefault()
									.getResourceString(
											"NewConnectionProfileWizardPage.summary_autoconnect"), //$NON-NLS-1$
							"" + getAutoConnect()}); //$NON-NLS-1$
		}

		return data;
	}
}
