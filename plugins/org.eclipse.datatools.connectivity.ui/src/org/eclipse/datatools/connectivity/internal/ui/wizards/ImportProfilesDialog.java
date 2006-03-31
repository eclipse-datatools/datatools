/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.internal.security.SecurityManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author shongxum
 */
public class ImportProfilesDialog extends Dialog {

	private Text txtFile;

	private Button btnOverwrite;

	private Throwable mException;

	private IConnectionProfile[] mProfiles;

	private boolean mOverwrite;

	public ImportProfilesDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 20;
		gridLayout.numColumns = 3;
		container.setLayout(gridLayout);
		{
			Text text = new Text(container, SWT.BORDER | SWT.MULTI
					| SWT.READ_ONLY | SWT.WRAP);
			text.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ImportProfilesDialog.text.text")); //$NON-NLS-1$
			final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.heightHint = 60;
			gridData.horizontalSpan = 3;
			text.setLayoutData(gridData);
		}
		{
			final Label label = new Label(container, SWT.NONE);
			final GridData gridData = new GridData(
					GridData.HORIZONTAL_ALIGN_FILL);
			gridData.horizontalSpan = 3;
			gridData.widthHint = 495;
			label.setLayoutData(gridData);
		}
		{
			final Label label = new Label(container, SWT.NONE);
			final GridData gridData = new GridData();
			gridData.horizontalIndent = 5;
			label.setLayoutData(gridData);
			label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ImportProfilesDialog.label.text")); //$NON-NLS-1$
		}
		{
			txtFile = new Text(container, SWT.BORDER);
			final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.widthHint = 243;
			txtFile.setLayoutData(gridData);
		}
		{
			final Button button = new Button(container, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					txtFile.setText(new FileDialog(getShell()).open());
				}
			});
			button
					.setLayoutData(new GridData(
							GridData.HORIZONTAL_ALIGN_CENTER));
			button.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ImportProfilesDialog.button.text")); //$NON-NLS-1$
		}
		{
			btnOverwrite = new Button(container, SWT.CHECK);
			final GridData gridData = new GridData(GridData.GRAB_HORIZONTAL);
			gridData.horizontalIndent = 10;
			gridData.horizontalSpan = 3;
			btnOverwrite.setLayoutData(gridData);
			btnOverwrite
					.setText(ConnectivityUIPlugin.getDefault()
							.getResourceString(
									"ImportProfilesDialog.btnOverwrite.text")); //$NON-NLS-1$
		}

		return container;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ImportProfilesDialog.null.title")); //$NON-NLS-1$
	}

	public Throwable getException() {
		return mException;
	}

	public IConnectionProfile[] getProfiles() {
		return mProfiles;
	}

	public boolean isOverwritten() {
		return mOverwrite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		if (txtFile.getText().length() == 0) {
			MessageDialog.openError(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString("dialog.title.error"), //$NON-NLS-1$
					ConnectivityUIPlugin.getDefault().getResourceString(
							"actions.export.nofile")); //$NON-NLS-1$
			return;
		}
		try {
			byte[] bytes = new byte[5];
			char[] xml = {'<','?','x','m','l'};
			FileInputStream fis = new FileInputStream(txtFile.getText());
			fis.read(bytes);
			fis.close();
			boolean isXML = true;
			for (int i = 0; isXML && i < 5; ++i) {
				isXML = bytes[i] == xml[i];
			}
			if (isXML) {
				// not encrpyted
				mProfiles = ConnectionProfileMgmt.loadCPs(new File(txtFile
						.getText()), null);
			}
			else {
				// encrypted
				mProfiles = ConnectionProfileMgmt.loadCPs(new File(txtFile
						.getText()), SecurityManager.getInstance().getDefaultCipherProvider());
			}
		}
		catch (Exception e) {
			mException = e;
		}
		mOverwrite = btnOverwrite.getSelection();
		super.okPressed();
	}
}