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

import org.apache.commons.net.ftp.FTPClient;

import org.eclipse.datatools.connectivity.IConnectionProfile;

/**
 * Wrapper class for FTPClient
 */
public class FTPClientObject {
	private IConnectionProfile profile;
	private FTPClient ftpClient;
	
	/**
	 * @param profile
	 * @param ftpClient
	 */
	public FTPClientObject(IConnectionProfile profile, FTPClient ftpClient) {
		super();
		this.profile = profile;
		this.ftpClient = ftpClient;
	}
	/**
	 * @return Returns the ftpClient.
	 */
	public FTPClient getFtpClient() {
		return ftpClient;
	}
	/**
	 * @return Returns the profile.
	 */
	public IConnectionProfile getProfile() {
		return profile;
	}
}
