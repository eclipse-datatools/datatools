/*******************************************************************************
 * Copyright (c) 2004-2005, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 * 		IBM Corporation - defect fix #213266
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.cp.ui.wizards;

import org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage;
import org.eclipse.jface.resource.ImageDescriptor;
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

/**
 * @author rcernich
 * 
 * Created on Mar 11, 2004
 */
public class FilePathPropertyWizardPage extends ConnectionProfileDetailsPage {

	private Text mPathText;

	/**
	 * @param pageName
	 */
	public FilePathPropertyWizardPage(String pageName) {
		super(pageName);
	}

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public FilePathPropertyWizardPage(String pageName, String title,
										ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createCustomControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(3, false);
		content.setLayout(layout);

		Label label = new Label(content, SWT.NULL);
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
	}

	public String getProperty() {
		return mPathText.getText();
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
		if (path == null) {
			errorMessage = "Please specify server path.";
		}
		else {
			errorMessage = null;
		}
		setErrorMessage(errorMessage);
		setPageComplete(errorMessage == null);
	}

}
