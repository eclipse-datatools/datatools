/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sample.wizards;

import org.eclipse.jface.wizard.Wizard;

public class SampleConnectionWizard extends Wizard {
	
	private SelectExistingConnectionWizardPage myExistingConnectionPage;
	
	private static final String EXISTING_CONNECTION_PAGE_NAME = "org.eclipse.datatools.connectivity.sample.SelectExistingConnectionWizardPage";
	
	public SampleConnectionWizard() {
		super();
		setWindowTitle("Connectivity Sample Wizard");
	}
	
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}
    public void addPages() {
		super.addPages();

		myExistingConnectionPage = new SelectExistingConnectionWizardPage(
				EXISTING_CONNECTION_PAGE_NAME);
		addPage(myExistingConnectionPage);
    }
}
