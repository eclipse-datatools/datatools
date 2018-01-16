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
package org.eclipse.datatools.connectivity.sample.cp.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.sample.cp.IFileProfileConstants;
import org.eclipse.datatools.connectivity.sample.cp.IFileProfilePropertyConstants;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.jface.wizard.Wizard;

/**
 * @see Wizard
 */
public class FileConnectionProfileNewWizard extends NewConnectionProfileWizard {

	private FilePathPropertyWizardPage mPropPage;

	/**
	 * 
	 */
	public FileConnectionProfileNewWizard() {
		setWindowTitle("New File Server Connection Profile");
		setNeedsProgressMonitor(true);
		mProviderID = IFileProfileConstants.FILE_PROFILE_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addCustomPages() {
		mPropPage = new FilePathPropertyWizardPage("propPage");
		addPage(mPropPage);
	}

	public Properties getProfileProperties() {
		Properties props = new Properties();
		props.setProperty(IFileProfilePropertyConstants.FILE_PATH, mPropPage
				.getProperty());
		return props;
	}

}