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
package org.eclipse.datatools.connectivity.sample.cp.ui.properties;

import java.util.Properties;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sample.cp.IFileProfileConstants;
import org.eclipse.datatools.connectivity.ui.wizards.ProfileDetailsPropertyPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class FileProfilePropetyPage extends ProfileDetailsPropertyPage {

	private Text mPathText;

	/**
	 * Constructor for FileProfilePropetyPage.
	 */
	public FileProfilePropetyPage() {
		super();
		noDefaultAndApplyButton();
	}

	protected void createCustomContents(Composite parent) {
		Composite content = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(3, false);
		content.setLayout(layout);

		Label label;

		label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText("Directory Path:");

		mPathText = new Text(content, SWT.BORDER);
		mPathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		mPathText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				handlePathModify();
			}
		});

		Button button = new Button(content, SWT.PUSH);
		button.setText("Browse...");
		button.setLayoutData(new GridData());
		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});

		initControls();
	}

	public Properties collectProperties() {
		Properties props = new Properties();
		props.setProperty(IFileProfileConstants.FILE_PATH, mPathText.getText());
		return props;
	}

	private void initControls() {
		IConnectionProfile profile = getConnectionProfile();
		Assert.isTrue(profile.getProviderId().equals(
				IFileProfileConstants.FILE_PROFILE_ID));
		String path = profile.getBaseProperties().getProperty(
				IFileProfileConstants.FILE_PATH);
		if (path != null) {
			mPathText.setText(path);
		}
		validate();
		setErrorMessage(null);
	}

	private void handleBrowse() {
		DirectoryDialog dialog = new DirectoryDialog(getShell());
		String path = dialog.open();
		if (path != null) {
			mPathText.setText(path);
		}
	}

	private void handlePathModify() {
		validate();
	}

	private void validate() {
		String errorMessage;
		String path = mPathText.getText();
		if (path == null || path.length() == 0) {
			errorMessage = "Please specify server path.";
		}
		else {
			errorMessage = null;
		}
		setErrorMessage(errorMessage);
		setValid(errorMessage == null);
	}

}