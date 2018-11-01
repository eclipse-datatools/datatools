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
package org.eclipse.datatools.connectivity.sample.ftp.properties;

import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sample.ftp.FTPProfileMessages;
import org.eclipse.datatools.connectivity.sample.ftp.IFtpProfileConstants;
import org.eclipse.datatools.connectivity.ui.wizards.ProfileDetailsPropertyPage;


/**
 * FTP Profile properties
 * @author brianf
 */
public class FtpProfilePropertyPage extends ProfileDetailsPropertyPage {

	// ui pieces
	private Text mServerText;
	private Text mUIDText;
	private Text mPWDText;

	/**
	 * Constructor for FtpProfilePropertyPage.
	 */
	public FtpProfilePropertyPage() {
		super();
		noDefaultAndApplyButton();
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

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	public boolean performOk() {
		IConnectionProfile profile = getConnectionProfile();
		Properties props = new Properties();
		props.setProperty(IFtpProfileConstants.FTP_SERVER, getServerName());
		props.setProperty(IFtpProfileConstants.FTP_UID, getUserName());
		props.setProperty(IFtpProfileConstants.FTP_PWD, getPassword());
		profile.setBaseProperties(props);
		return true;
	}

	/**
	 * Initialize the controls from the profile
	 */
	private void initControls() {
		IConnectionProfile profile = getConnectionProfile();
		String server = profile.getBaseProperties().getProperty(IFtpProfileConstants.FTP_SERVER);
		if (server != null) {
			setServerName(server);
		}
		String uid = profile.getBaseProperties().getProperty(IFtpProfileConstants.FTP_UID);
		if (uid != null) {
			setUserName(uid);
		}
		String pwd = profile.getBaseProperties().getProperty(IFtpProfileConstants.FTP_PWD);
		if (pwd != null) {
			setPassword(pwd);
		}
		setErrorMessage(null);
	}

	protected Properties collectProperties() {
		Properties props = new Properties();
		props.setProperty(IFtpProfileConstants.FTP_SERVER, getServerName());
		props.setProperty(IFtpProfileConstants.FTP_UID, getUserName());
		props.setProperty(IFtpProfileConstants.FTP_PWD, getPassword());
		return props;
	}

	protected void createCustomContents(Composite parent) {
		Composite content = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2, false);
		content.setLayout(layout);

		Label label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(FTPProfileMessages.getString("propertypage.label.servername")); //$NON-NLS-1$

		this.mServerText = new Text(content, SWT.BORDER);
		this.mServerText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.mServerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// empty
			}
		});

		label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(FTPProfileMessages.getString("propertypage.label.username")); //$NON-NLS-1$

		this.mUIDText = new Text(content, SWT.BORDER);
		this.mUIDText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.mUIDText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// empty
			}
		});

		label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(FTPProfileMessages.getString("propertypage.label.password")); //$NON-NLS-1$

		this.mPWDText = new Text(content, SWT.BORDER);
		this.mPWDText.setEchoChar('*');
		this.mPWDText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.mPWDText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// empty
			}
		});

		initControls();
	}
}