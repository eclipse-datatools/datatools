/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: mdow - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.ftp.internal;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionProfile;

/**
 * @author mdow
 */
public class FtpConnectionFactory implements IConnectionFactory {

	/*
	 * Constructor
	 */
	public FtpConnectionFactory() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	public IConnection createConnection(IConnectionProfile profile) {
		FtpConnection connect = new FtpConnection(profile);
//		if (connect.getConnectException() != null) {
//			return null;
//		}
		return connect;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile, java.lang.String, java.lang.String)
	 */
	public IConnection createConnection(IConnectionProfile profile, String uid,
			String pwd) {
		FtpConnection connect = new FtpConnection(profile);
		if (connect.getConnectException() != null) {
			return null;
		}
		return connect;
	}
}
