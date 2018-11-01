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

import org.eclipse.datatools.connectivity.sample.ftp.FTPProfileMessages;
import org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * FTP Profile Property wizard page
 * @author mdow & brianf
 */
public class FtpPropertyWizardPage extends ConnectionProfileDetailsPage {

	// various UI components
	private Text mServerText;
	private Text mUIDText;
	private Text mPWDText;

	/**
	 * Constructor
	 * @param pageName
	 */
	public FtpPropertyWizardPage(String pageName) {
		super(pageName);
		setTitle(FTPProfileMessages.getString("wizardpage.title")); //$NON-NLS-1$
		setDescription(FTPProfileMessages.getString("wizardpage.text")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage#createCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createCustomControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2, false);
		content.setLayout(layout);

		Label label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(FTPProfileMessages.getString("wizardpage.label.servername")); //$NON-NLS-1$

		this.mServerText = new Text(content, SWT.BORDER);
		this.mServerText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.mServerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});

		label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(FTPProfileMessages.getString("wizardpage.label.username")); //$NON-NLS-1$

		this.mUIDText = new Text(content, SWT.BORDER);
		this.mUIDText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.mUIDText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});

		label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(FTPProfileMessages.getString("wizardpage.label.password")); //$NON-NLS-1$

		this.mPWDText = new Text(content, SWT.BORDER);
		this.mPWDText.setEchoChar('*');
		this.mPWDText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.mPWDText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});
		
//		setControl(content);
		setPageComplete(false);
	}

	/**
	 * Return the name of the FTP Server
	 * @return
	 */
	public String getServerName() {
		return this.mServerText.getText();
	}

	/**
	 * Set the name of the FTP Server
	 * @param server
	 */
	public void setServerName(String server) {
		this.mServerText.setText(server);
	}

	/**
	 * Return the user name for the FTP Server
	 * @return
	 */
	public String getUserName() {
		return this.mUIDText.getText();
	}

	/**
	 * Set the user name for the FTP Server
	 * @param user
	 */
	public void setUserName(String user) {
		this.mUIDText.setText(user);
	}

	/**
	 * Return the password for the FTP Server
	 * @return
	 */
	public String getPassword() {
		return this.mPWDText.getText();
	}

	/**
	 * Set the password for the FTP Server
	 * @param pwd
	 */
	public void setPassword(String pwd) {
		this.mPWDText.setText(pwd);
	}

	/**
	 * Validate the profile
	 */
	private void validate() {
		String server = getServerName();
		String uid = getUserName();
		String pwd = getPassword();
		
		if (server != null && uid != null && pwd != null) {
			setPageComplete(true);
		}
		else {
			setPageComplete(false);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.internal.ui.wizards.BaseWizardPage#getSummaryData()
	 */
	public List getSummaryData() {
		List data = new ArrayList();
		data.add(new String[] {FTPProfileMessages.getString("wizardpage.summary.label.servername"), //$NON-NLS-1$ 
				this.getServerName()});
		data.add(new String[] {FTPProfileMessages.getString("wizardpage.summary.label.username"),  //$NON-NLS-1$
				this.getUserName()});
		String pwd = this.getPassword();
		String mask = ""; //$NON-NLS-1$
		for (int i = 0; i < pwd.length(); i++) {
			mask = mask + "*"; //$NON-NLS-1$
		}
		data.add(new String[] {FTPProfileMessages.getString("wizardpage.summary.label.password"),  //$NON-NLS-1$
				mask});
		return data;
	}

}
