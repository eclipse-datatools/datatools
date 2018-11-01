/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.datatools.connectivity.internal.ui.SharedImages;
import org.eclipse.datatools.connectivity.ui.wizards.IWizardCategoryProvider;

/**
 * @author shongxum
 */
public class NewCategoryWizard extends BaseWizard {

	private CPWizardSelectionPage mProfilePage;

	private IWizardCategoryProvider mCategory;

	public NewCategoryWizard() {
		super();
		setDefaultPageImageDescriptor(SharedImages.DESC_WIZBAN);
		setForcePreviousAndNextButtons(true);
	}

	public void initWizardCategory(IWizardCategoryProvider category) {
		mCategory = category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		super.addPages();
		mProfilePage = new CPWizardSelectionPage(CPWizardSelectionPage.class
				.getName()
				+ mCategory.getName(), null, mCategory.getId());
		mProfilePage.setTitle(mCategory.getWizardTitle());
		mProfilePage.setDescription(mCategory.getWizardDescription());
		addPage(mProfilePage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		return true;
	}
}