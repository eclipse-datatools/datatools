/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf & mdow - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.ftp.wizards;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.internal.ui.wizards.ISummaryDataSource;
import org.eclipse.datatools.connectivity.internal.ui.wizards.SummaryWizardPage;
import org.eclipse.datatools.connectivity.sample.ftp.FTPProfileMessages;
import org.eclipse.datatools.connectivity.sample.ftp.IFtpProfileConstants;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.jface.wizard.Wizard;


/**
 * This class is the wizard for creating new ftp connection profiles.
 * 
 * @author mdow
 * @see Wizard
 */
public class FtpConnectionProfileWizard extends NewConnectionProfileWizard {

	// The file property page
	private FtpPropertyWizardPage mPropPage;

	// The summary page
	private SummaryWizardPage mSummaryPage;
		
	private Wizard mParentWizard;
	
	private boolean showProjectPage = true;

	/**
	 * Default constructor
	 */
	public FtpConnectionProfileWizard() {
		super();
		setWindowTitle(FTPProfileMessages.getString("wizard.title")); //$NON-NLS-1$
//		setDefaultPageImageDescriptor(FtpProfilePlugin.getDefault().getImageDescriptor("syb_banner.gif")); //$NON-NLS-1$
	}
	
	public void setShowProjectPage ( boolean flag ) {
		this.showProjectPage = flag;
	}
	
	public boolean getShowProjectPage() {
		return this.showProjectPage;
	}

	/**
	 * @see Wizard#performFinish
	 */
	public boolean performFinish() {
		
		boolean flag = super.performFinish();
		
		if (!flag) return flag;
				
		return flag;
	}

	/**
	 * @param parent
	 */
	public void setParentWizard(Wizard parent) {
		this.mParentWizard = parent;
	}

	/**
	 * @return
	 */
	public Wizard getParentWizard() {
		return this.mParentWizard;
	}

	public List getSummaryData() {
		List data = new ArrayList();
		if (this.mParentWizard != null && this.mParentWizard instanceof ISummaryDataSource) {
			ISummaryDataSource wiz = (ISummaryDataSource) this.mParentWizard;
			data.addAll(wiz.getSummaryData());
		}
		data.addAll(super.getSummaryData());
		return data;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#addCustomPages()
	 */
	public void addCustomPages() {
		this.mPropPage = new FtpPropertyWizardPage("PropertyPage"); //$NON-NLS-1$
		addPage(this.mPropPage);

		this.mSummaryPage = new SummaryWizardPage(this);
		addPage(this.mSummaryPage);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard#getProfileProperties()
	 */
	public Properties getProfileProperties() {
		Properties props = new Properties();
		props.setProperty(IFtpProfileConstants.FTP_SERVER, this.mPropPage.getServerName());
		props.setProperty(IFtpProfileConstants.FTP_UID, this.mPropPage.getUserName());
		props.setProperty(IFtpProfileConstants.FTP_PWD, this.mPropPage.getPassword());
		
		return props;
	}
}