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

import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPropertyPage;

public class DataSourcePropertyPage extends DataSourceEditorPage implements IWorkbenchPropertyPage {

	private DataSourceSelectionPageHelper helper;
	private Properties publicProperties;

	@Override
	protected void createAndInitCustomControl(final Composite parent, final Properties properties) {
		this.publicProperties = properties;
		if (helper == null) {
			helper = new DataSourceSelectionPageHelper(this);
		}
		helper.createCustomControl(parent);
		this.setPingButtonVisible(true);
		helper.initCustomControl(publicProperties);
		helper.dialogChanged();
	}

	@Override
	public Properties collectCustomProperties(final Properties properties) {
		if (helper == null) {
			return properties;
		}
		return helper.collectCustomProperties(properties);
	}

	@Override
	public void setValid(final boolean valid) {
		super.setValid(valid);
		setPingButtonEnabled(valid);
	}

	@Override
	protected void refresh(final Properties customConnectionProps) {
		enableAllControls(getControl(), isSessionEditable());
	}
}
