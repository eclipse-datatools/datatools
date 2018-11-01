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

import org.eclipse.datatools.enablement.oda.ecore.ui.i18n.Messages;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;

public class DataSourceWizardPage extends org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage {

	private Properties publicProperties;
	private DataSourceSelectionPageHelper helper;

	public DataSourceWizardPage(final String pageName) {
		super(pageName);
		setMessage(Messages.getString("DataSourceWizardPage.message.default"));
	}

	public DataSourceWizardPage(final String pageName, final String title, final ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setMessage(Messages.getString("DataSourceWizardPage.message.default"));
	}

	@Override
	public void createPageCustomControl(final Composite parent) {
		if (helper == null) {
			helper = new DataSourceSelectionPageHelper(this);
		}
		helper.createCustomControl(parent);
		setPingButtonVisible(true);
		helper.initCustomControl(publicProperties);
		helper.dialogChanged();
	}

	@Override
	public void setInitialProperties(final Properties dataSourceProps) {
		publicProperties = dataSourceProps;
		if (helper == null) {
			return;
		}
		helper.initCustomControl(publicProperties);
	}

	@Override
	public Properties collectCustomProperties() {
		if (helper != null) {
			return helper.collectCustomProperties(publicProperties);
		}
		return publicProperties != null ? publicProperties : new Properties();
	}

	@Override
	public void setPageComplete(final boolean complete) {
		super.setPageComplete(complete);
		setPingButtonEnabled(complete);
	}

	@Override
	public void refresh() {
		enableAllControls(getControl(), isSessionEditable());
	}
}
