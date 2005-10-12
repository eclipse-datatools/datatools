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
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.SharedImages;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * @see Wizard
 */
public class NewCPWizard extends BaseWizard implements INewWizard {

	private CPWizardSelectionPage mProfilePage;

	private ViewerFilter mViewerFilter;

	/**
	 * 
	 */
	public NewCPWizard() {
		setDefaultPageImageDescriptor(SharedImages.DESC_WIZBAN);
		setWindowTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewCPWizard.title")); //$NON-NLS-1$
	}

	public NewCPWizard(ViewerFilter filter) {
		this();
		mViewerFilter = filter;
	}

	/**
	 * @see Wizard#performFinish
	 */
	public boolean performFinish() {
		// mStore.setValue(DONNT_SHOW_INRO, mIntroPage.isHideIntro());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		super.addPages();

		mProfilePage = new CPWizardSelectionPage(CPWizardSelectionPage.class
				.getName(), mViewerFilter);
		addPage(mProfilePage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#needsPreviousAndNextButtons()
	 */
	public boolean needsPreviousAndNextButtons() {
		return true;
	}
}