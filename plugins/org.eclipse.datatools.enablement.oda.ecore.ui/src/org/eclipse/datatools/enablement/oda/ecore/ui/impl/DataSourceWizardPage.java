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

import org.eclipse.datatools.connectivity.oda.design.ui.pages.impl.DefaultDataSourcePageHelper;
import org.eclipse.datatools.enablement.oda.ecore.Constants;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DataSourceWizardPage extends org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage {

	private Properties properties = new Properties();
	private Text modelFileControl;

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
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#collectCustomProperties()
	 */
	@Override
	public Properties collectCustomProperties() {
		return properties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageCustomControl(final Composite parent) {
		// Create a ScrolledComposite as the child of the parent wizard page.
		final ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);

		// Create a Composite with the ScrolledComposite as the parent.
		final Composite content = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(content);

		// Set up control listener to monitor the resizing of the properties
		// pane.
		scrolledComposite.addControlListener(new ControlAdapter() {

			@Override
			public void controlResized(final ControlEvent e) {
				final Rectangle r = scrolledComposite.getClientArea();
				scrolledComposite.setMinSize(content.computeSize(r.width, SWT.DEFAULT));
			}
		});

		createModelEntryControls(content);
	}

	private void createModelEntryControls(final Composite content) {
		final GridLayout layout = new GridLayout();
		content.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;

		final Label label = new Label(content, SWT.NULL);
		label.setText("&Ecore Model:");

		modelFileControl = new Text(content, SWT.BORDER | SWT.SINGLE);
		modelFileControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		modelFileControl.addModifyListener(new ModifyListener() {

			public void modifyText(final ModifyEvent e) {
				dialogChanged();
			}
		});

		final Button button = new Button(content, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				handleBrowseForModel();
			}
		});
		setPingButtonVisible(true);
		setPingButtonEnabled(false);
		dialogChanged();
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowseForModel() {
		// TODO: Replace this use of ResourceDialog with in-place buttons
		// (shouldn't need to pop a dialog here)
		final ResourceDialog dialog = new ResourceDialog(getShell(), "Select Ecore model", SWT.OPEN | SWT.SINGLE);
		final int result = dialog.open();
		if (result == Window.OK) {
			final String uriText = dialog.getURIText();
			if (uriText != null) {
				modelFileControl.setText(uriText);
				properties.setProperty(Constants.CONNECTION_ECORE_MODEL_URI_STRING, uriText);
			}
		}
	}

	private void dialogChanged() {
		final String uriText = modelFileControl.getText();
		String message = null;
		if (uriText.length() == 0) {
			message = "Ecore model must be specified";
			return;
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
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#setInitialProperties(java.util.Properties)
	 */
	@Override
	public void setInitialProperties(final Properties dataSourceProps) {
		properties = new Properties();
	}

}
