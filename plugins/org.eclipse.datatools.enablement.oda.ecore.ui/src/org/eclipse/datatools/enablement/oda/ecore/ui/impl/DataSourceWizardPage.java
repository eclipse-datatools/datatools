/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.ui.impl;

import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.connectivity.oda.design.ui.pages.impl.DefaultDataSourcePageHelper;
import org.eclipse.datatools.enablement.oda.ecore.Constants;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DataSourceWizardPage extends org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage {

	private Properties properties = new Properties();
	private Text modelPathControl;
	protected boolean isWorkspacePath = false;

	public DataSourceWizardPage(final String pageName) {
		super(pageName);

		// TODO: Probably shouldn't use messages from
		// DefaultDataSourcePageHelper?
		setMessage(DefaultDataSourcePageHelper.DEFAULT_MESSAGE);
	}

	public DataSourceWizardPage(final String pageName, final String title, final ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setMessage(DefaultDataSourcePageHelper.DEFAULT_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage
	 * #collectCustomProperties()
	 */
	@Override
	public Properties collectCustomProperties() {
		return properties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage
	 * #createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageCustomControl(final Composite parent) {
		final Group locationGroup = new Group(parent, SWT.NONE);
		locationGroup.setText("Model Location");

		final Button filesystemRadioButton = new Button(locationGroup, SWT.RADIO);
		filesystemRadioButton.setText("Use Model from Filesystem");
		filesystemRadioButton.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).create());
		filesystemRadioButton.setSelection(true);
		filesystemRadioButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				isWorkspacePath = false;
			}
		});

		final Button workspaceRadioButton = new Button(locationGroup, SWT.RADIO);
		workspaceRadioButton.setText("Use Model in Workspace");
		workspaceRadioButton.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).create());
		workspaceRadioButton.setSelection(false);
		workspaceRadioButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				isWorkspacePath = true;
			}
		});

		final Label label = new Label(locationGroup, SWT.NONE);
		label.setText("&Model:");

		modelPathControl = new Text(locationGroup, SWT.BORDER | SWT.SINGLE);
		modelPathControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		modelPathControl.addModifyListener(new ModifyListener() {

			public void modifyText(final ModifyEvent e) {
				dialogChanged();
			}
		});

		final Button browseButton = new Button(locationGroup, SWT.PUSH);
		browseButton.setText("Browse...");
		browseButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				handleBrowseForModel();
			}
		});
		GridLayoutFactory.swtDefaults().numColumns(3).generateLayout(locationGroup);

		setPingButtonVisible(true);
		setPingButtonEnabled(false);
		dialogChanged();
	}

	private void handleBrowseForModel() {
		String path = null;
		if (!isWorkspacePath) {
			final FileDialog filesystemDialog = new FileDialog(modelPathControl.getShell());
			path = filesystemDialog.open();
		} else {
			final String msg = "Select serialized model";
			final IFile[] result = WorkspaceResourceDialog.openFileSelection(modelPathControl.getShell(), null, msg,
					false, null, null);
			if (result.length > 0) {
				path = result[0].getFullPath().toPortableString();
			}
		}
		if (path == null) {
			path = "";
		}
		modelPathControl.setText(path);
		properties.setProperty(Constants.CONNECTION_DIRECTORY_PATH, path);
		properties.setProperty(Constants.CONNECTION_DIRECTORY_ISWORKSPACE, Boolean.toString(isWorkspacePath));
	}

	private void dialogChanged() {
		final String uriText = modelPathControl.getText();
		String message = null;
		if (uriText.length() == 0) {
			message = "Ecore model must be specified";
		}
		try {
			URI.createURI(uriText);
		} catch (final IllegalArgumentException e) {
			message = "Invalid URI";
		}
		updateStatus(message);
	}

	private void updateStatus(final String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
		setPingButtonEnabled(message == null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage
	 * #setInitialProperties(java.util.Properties)
	 */
	@Override
	public void setInitialProperties(final Properties dataSourceProps) {
		properties = new Properties();
	}

}
