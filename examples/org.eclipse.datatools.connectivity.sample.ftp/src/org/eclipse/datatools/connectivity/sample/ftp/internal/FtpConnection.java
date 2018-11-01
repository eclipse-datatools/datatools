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
package org.eclipse.datatools.connectivity.sample.ftp.internal;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IServerVersionProvider;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.sample.ftp.FTPProfileMessages;
import org.eclipse.datatools.connectivity.sample.ftp.IFtpProfileConstants;

/**
 * @author mdow
 */
public class FtpConnection implements IConnection, IServerVersionProvider {

	private static int counter = 0;
	
	private Throwable mException;
	
	private FTPClient mFtpClient;
	
	private FTPClientObject mFtpClientObject;
	
	private IConnectionProfile mProfile;

	/**
	 * Constructor
	 * @param profile
	 */
	public FtpConnection(IConnectionProfile profile) {
		this.mProfile = profile;
		Properties props = profile.getBaseProperties();

		String server = props.getProperty(IFtpProfileConstants.FTP_SERVER);
		String port = props.getProperty(IFtpProfileConstants.FTP_PORT);
		String user = props.getProperty(IFtpProfileConstants.FTP_UID);
		String pass = props.getProperty(IFtpProfileConstants.FTP_PWD);

        try {
            int reply;
            this.mFtpClient = new FTPClient();
            this.mFtpClientObject = new FTPClientObject(profile,
                    this.mFtpClient);
            if (port != null && port.length() != 0)
            	this.mFtpClient.setDefaultPort(new Integer(port).intValue());
            this.mFtpClient.setDefaultTimeout(2 * 60 * 1000);
            this.mFtpClient.setDataTimeout(2 * 60 * 1000);
            this.mFtpClient.connect(server);
            if (!this.mFtpClient.login(user, pass)) {
                throw new Exception(mFtpClient.getReplyString());
            }
            reply = this.mFtpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                this.mFtpClient.disconnect();
                throw new Exception(FTPProfileMessages
                        .getString("FtpConnection.errormessage")); //$NON-NLS-1$
            }
        } catch (Exception e) {
            this.mException = e;
            return;
        }
        this.mFtpClient.enterLocalPassiveMode();
        FtpConnection.counter++;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IConnection#getRawConnection()
	 */
	public Object getRawConnection() {
		return this.mFtpClientObject;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IConnection#close()
	 */
	public void close() {
		if (this.mFtpClient != null) {
			if (this.mFtpClient.isConnected()) {
				try {
					this.mFtpClient.disconnect();
					FtpConnection.counter--;
				} catch (IOException e) {
					// Guess we can't disconnect.
					e.printStackTrace();
				}
			}
			this.mFtpClient = null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IConnection#getConnectException()
	 */
	public Throwable getConnectException() {
		return this.mException;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IConnection#getConnectionProfile()
	 */
	public IConnectionProfile getConnectionProfile() {
		return this.mProfile;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IServerVersionProvider#getProviderName()
	 */
	public String getProviderName() {
		try {
			if (mFtpClient != null && mFtpClient.isConnected())
				return mFtpClient.getSystemName();
		} catch (IOException e) {
			// do nothing
		}
		return "Not Available";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IServerVersionProvider#getProviderVersion()
	 */
	public Version getProviderVersion() {
		return new Version(0,0,0,"0");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IServerVersionProvider#getTechnologyName()
	 */
	public String getTechnologyName() {
		return "FTP";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IServerVersionProvider#getTechnologyVersion()
	 */
	public Version getTechnologyVersion() {
		return new Version(0,0,0,"0");
	}
}