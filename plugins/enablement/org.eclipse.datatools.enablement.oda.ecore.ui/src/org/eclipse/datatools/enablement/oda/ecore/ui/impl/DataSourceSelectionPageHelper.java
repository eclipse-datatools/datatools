/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.ui.impl;

import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.enablement.oda.ecore.Constants;
import org.eclipse.datatools.enablement.oda.ecore.ui.i18n.Messages;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DataSourceSelectionPageHelper {

	private Text modelPathControl;
	private Button workspaceRadioButton;
	private Button filesystemRadioButton;
	private Button uriLiteralURIRadioButton;
	private final IUpdateStrategy updateStrategy;
	private Button browseButton;

	DataSourceSelectionPageHelper(final WizardPage page) {
		updateStrategy = new WizardUpdateStrategy(page);
	}

	DataSourceSelectionPageHelper(final PreferencePage page) {
		updateStrategy = new PreferenceUpdateStrategy(page);
	}

	void createCustomControl(final Composite parent) {
		final Group locationGroup = new Group(parent, SWT.NONE);
		locationGroup.setText(Messages.getString("DataSourceWizardPage.group.modelLocation")); //$NON-NLS-1$

		filesystemRadioButton = new Button(locationGroup, SWT.RADIO);
		filesystemRadioButton.setText(Messages.getString("DataSourceWizardPage.button.useFilesystem")); //$NON-NLS-1$
		GridDataFactory.fillDefaults().span(3, 1).applyTo(filesystemRadioButton);
		filesystemRadioButton.setSelection(true);

		workspaceRadioButton = new Button(locationGroup, SWT.RADIO);
		workspaceRadioButton.setText(Messages.getString("DataSourceWizardPage.button.useWorkspace")); //$NON-NLS-1$
		GridDataFactory.fillDefaults().span(3, 1).applyTo(workspaceRadioButton);
		workspaceRadioButton.setSelection(false);

		uriLiteralURIRadioButton = new Button(locationGroup, SWT.RADIO);
		uriLiteralURIRadioButton.setText(Messages.getString("DataSourceWizardPage.button.useLiteralURI")); //$NON-NLS-1$
		GridDataFactory.fillDefaults().span(3, 1).applyTo(uriLiteralURIRadioButton);
		uriLiteralURIRadioButton.setSelection(false);

		final Label label = new Label(locationGroup, SWT.NONE);
		label.setText(Messages.getString("DataSourceWizardPage.label.model")); //$NON-NLS-1$

		modelPathControl = new Text(locationGroup, SWT.BORDER | SWT.SINGLE);
		modelPathControl.addModifyListener(new ModifyListener() {

			public void modifyText(final ModifyEvent e) {
				dialogChanged();
			}
		});

		browseButton = new Button(locationGroup, SWT.PUSH);
		browseButton.setText(Messages.getString("DataSourceWizardPage.button.browse")); //$NON-NLS-1$
		browseButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				handleBrowseForModel();
			}
		});
		filesystemRadioButton.addSelectionListener(new ControlEnabler(browseButton, true));
		workspaceRadioButton.addSelectionListener(new ControlEnabler(browseButton, true));
		uriLiteralURIRadioButton.addSelectionListener(new ControlEnabler(browseButton, false));
		GridLayoutFactory.fillDefaults().numColumns(3).generateLayout(locationGroup);
	}

	void initCustomControl(final Properties properties) {
		if (properties == null || properties.isEmpty() || !properties.containsKey(Constants.CONNECTION_MODEL_URI)) {
			return;
		}
		final String literalURI = properties.getProperty(Constants.CONNECTION_MODEL_URI);
		final URI uri = URI.createURI(literalURI);
		final String pathText;
		if (uri.isFile()) {
			pathText = uri.toFileString();
			filesystemRadioButton.setSelection(true);
			workspaceRadioButton.setSelection(false);
			uriLiteralURIRadioButton.setSelection(false);
		} else if (uri.isPlatformResource()) {
			pathText = uri.toPlatformString(true);
			workspaceRadioButton.setSelection(true);
			filesystemRadioButton.setSelection(false);
			uriLiteralURIRadioButton.setSelection(false);
		} else {
			pathText = literalURI;
			uriLiteralURIRadioButton.setSelection(true);
			workspaceRadioButton.setSelection(false);
			filesystemRadioButton.setSelection(false);
			browseButton.setEnabled(false);
		}
		modelPathControl.setText(pathText);
		dialogChanged();
	}

	Properties collectCustomProperties(final Properties properties) {
		final Properties toReturn = properties == null ? new Properties() : properties;
		final String pathText = modelPathControl.getText();
		final URI uri;
		if (filesystemRadioButton.getSelection()) {
			uri = URI.createFileURI(pathText);
		} else if (workspaceRadioButton.getSelection()) {
			uri = URI.createPlatformResourceURI(pathText, true);
		} else {
			uri = URI.createURI(pathText);
		}
		toReturn.setProperty(Constants.CONNECTION_MODEL_URI, uri.toString());
		return toReturn;
	}

	void dialogChanged() {
		final String uriText = modelPathControl.getText();
		String message = null;
		if (uriText.length() == 0) {
			message = Messages.getString("DataSourceWizardPage.message.missingModel"); //$NON-NLS-1$
		}
		try {
			URI.createURI(uriText);
		} catch (final IllegalArgumentException e) {
			message = Messages.getString("DataSourceWizardPage.message.invalidUri"); //$NON-NLS-1$
		}
		updateStrategy.updateStatus(message);
	}

	private void handleBrowseForModel() {
		String path = null;
		if (filesystemRadioButton.getSelection()) {
			final FileDialog filesystemDialog = new FileDialog(modelPathControl.getShell());
			path = filesystemDialog.open();
		} else {
			final String msg = Messages.getString("DataSourceWizardPage.dialog.workspace"); //$NON-NLS-1$
			final IFile[] result = WorkspaceResourceDialog.openFileSelection(modelPathControl.getShell(), null, msg, false,
					null, null);
			if (result.length > 0) {
				path = result[0].getFullPath().toPortableString();
			}
		}
		if (path == null) {
			path = ""; //$NON-NLS-1$
		}
		modelPathControl.setText(path);
	}

	private static class ControlEnabler extends SelectionAdapter {
		private final boolean enableOrDisable;
		private final Control control;

		ControlEnabler(final Control control, final boolean enableOrDisable) {
			this.control = control;
			this.enableOrDisable = enableOrDisable;
		}

		@Override
		public void widgetSelected(final SelectionEvent e) {
			control.setEnabled(enableOrDisable);
		}
	}

	private static interface IUpdateStrategy {
		void updateStatus(String message);
	}

	private static final class WizardUpdateStrategy implements IUpdateStrategy {

		private final WizardPage page;

		WizardUpdateStrategy(final WizardPage page) {
			this.page = page;
		}

		public void updateStatus(final String message) {
			page.setErrorMessage(message);
			page.setPageComplete(message == null);
		}
	}

	private static final class PreferenceUpdateStrategy implements IUpdateStrategy {

		private final PreferencePage page;

		PreferenceUpdateStrategy(final PreferencePage page) {
			this.page = page;
		}

		public void updateStatus(final String message) {
			page.setErrorMessage(message);
			page.setValid(message == null);
		}
	}
}
