/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.swt.widgets.Composite;

public class ExtensibleNewConnectionProfileWizard extends
		NewConnectionProfileWizard {

	private ExtensibleProfileDetailsWizardPage wizardPage = null;

	private boolean isWizardPageCreated = true;

	public ExtensibleNewConnectionProfileWizard(
			ExtensibleProfileDetailsWizardPage detailsWizardPage) {
		super();
		wizardPage = detailsWizardPage;
		setWindowTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"ExtensibleNewConnectionProfileWizard.title")); //$NON-NLS-1$
	}

	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp(getShell(), HelpUtil.getContextId(
				IHelpConstants.GENERIC_DB_PROFILE_WIZARD,
				ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));
	}

	public void addCustomPages() {
		addPage(wizardPage);
		setSkipProfileNamePage(true);
	}

	public Properties getProfileProperties() {
		return wizardPage.getProperties();
	}

	public NewConnectionProfileWizardPage getProfilePage() {
		return mProfilePage;
	}

	public boolean canFinish() {
		// This guarantees the Ping button is correctly enabled/disabled.
		if (isWizardPageCreated) {
			isWizardPageCreated = false;
			wizardPage.determinePageCompletion();
		}
		return super.canFinish();
	}
}
