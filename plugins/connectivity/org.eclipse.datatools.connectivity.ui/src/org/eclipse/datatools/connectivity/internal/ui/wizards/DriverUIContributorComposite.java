/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Actuate Corporation - bug fix
 * 
 *******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributor;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributorInformation;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class DriverUIContributorComposite {

	private Group group = null;
	private StackLayout stackLayout = null;
	private DialogPage wizardPage;
	private IDriverUIContributor uiContributor = null;
	private IDriverUIContributorInformation contributorInformation;
	private boolean isReadOnly = false;

	public DriverUIContributorComposite(Composite parent,
			DialogPage wizardPage,
			IDriverUIContributorInformation contributorInformation, boolean isReadOnly) {
		createControls(parent);
		this.wizardPage = wizardPage;
		this.contributorInformation = contributorInformation;
		this.isReadOnly = isReadOnly;
	}

	public void createControls(Composite parent) {
		Composite baseComposite = new Composite(parent, SWT.NONE);
		baseComposite.setLayout(new GridLayout());
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = 350;
		gridData.widthHint = 400;
		baseComposite.setLayoutData(gridData);

		group = new Group(baseComposite, SWT.NONE);
		group.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"DriverUIContributorComposite.group")); //$NON-NLS-1$
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		stackLayout = new StackLayout();
		stackLayout.marginHeight = 3;
		stackLayout.marginWidth = 3;
		group.setLayout(stackLayout);
	}

	public void setDriverTemplateID(String driverTemplateID) {
		uiContributor = DriverUIContributorRegistry.getInstance()
				.getDriverUIContributor(driverTemplateID);
		Composite contributedComposite = uiContributor
				.getContributedDriverUI(group, isReadOnly);
		contributedComposite.setParent(group);
		uiContributor.setDialogPage(wizardPage);
		uiContributor.setDriverUIContributorInformation(contributorInformation);
		uiContributor.determineContributorCompletion();
		stackLayout.topControl = contributedComposite;
		group.layout();
		uiContributor.loadProperties();
	}

	public boolean determineContributorCompletion() {
		boolean isComplete = false;
		if ((uiContributor != null)) {
			isComplete = uiContributor.determineContributorCompletion();
		}
		return isComplete;
	}

	public List getSummaryData() {
		List summaryData = new ArrayList();
		summaryData = uiContributor.getSummaryData();
		return summaryData;
	}
}
