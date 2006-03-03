/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.SharedImages;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.internal.ui.wizards.BaseWizard;
import org.eclipse.datatools.connectivity.internal.ui.wizards.SummaryWizardPage;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Base connection profile wizard
 * 
 * @author shongxum
 */
public abstract class NewConnectionProfileWizard extends BaseWizard implements
		INewWizard, ICPWizard {

	protected NewConnectionProfileWizardPage mProfilePage;
	protected SummaryWizardPage mSummaryPage;
	protected String mProviderID;

	public NewConnectionProfileWizard() {
		setWindowTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizard.title")); //$NON-NLS-1$
		setDefaultPageImageDescriptor(SharedImages.DESC_WIZBAN);
	}

	/**
	 * Clients need to make "return super.performFinish" as the last line of
	 * their code if they want to override this method
	 * 
	 * @see Wizard#performFinish
	 */
	public boolean performFinish() {
		try {
			ProfileManager.getInstance().createProfile(
					mProfilePage.getProfileName(),
					mProfilePage.getProfileDescription(), mProviderID,
					getProfileProperties(), getParentProfile(),
					mProfilePage.getAutoConnect());
		}
		catch (ConnectionProfileException e) {
			ExceptionHandler.showException(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString(
							"NewConnectionProfileWizard.create.failure"), e //$NON-NLS-1$
					.getLocalizedMessage(), e);
			return false;
		}
		return true;
	}

	/**
	 * @see Wizard#init
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// Do nothing
	}

	/**
	 * @see org.eclipse.datatools.connectivity.ui.wizards.ICPWizard#setProviderID(java.lang.String)
	 */
	public void initProviderID(String providerID) {
		mProviderID = providerID;
	}

	/**
	 * Clients needs to add their customized pages here
	 */
	public abstract void addCustomPages();

	public String getProfileProviderID() {
		return mProviderID;
	}

	public String getProfileName() {
		return mProfilePage.getProfileName();
	}

	public String getProfileDescription() {
		return mProfilePage.getProfileDescription();
	}

	public boolean getProfileIsAutoConnect() {
		return mProfilePage.getAutoConnect();
	}

	/**
	 * Clients need provide implementation to get extra profile properties
	 * 
	 * @return
	 */
	public abstract Properties getProfileProperties();

	/**
	 * Clients may override this method to return the real parent profile
	 * 
	 * @return
	 */
	public String getParentProfile() {
		return ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		super.addPages();

		mProfilePage = new NewConnectionProfileWizardPage();
		addPage(mProfilePage);

		addCustomPages();

		mSummaryPage = new SummaryWizardPage(this);
		addPage(mSummaryPage);
	}

}