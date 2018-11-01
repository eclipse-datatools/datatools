/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IOfflineConnection;
import org.eclipse.datatools.connectivity.IOfflineConnectionFactory;

/**
 * @author ledunnel
 * 
 */
public class ConnectionFactory implements IOfflineConnectionFactory {

	/**
	 * 
	 */
	public ConnectionFactory() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	public IConnection createConnection(IConnectionProfile profile) {
		IOfflineConnection connection = new ConnectionInfoImpl(profile, getClass(), true);
		return connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile,
	 *      java.lang.String, java.lang.String)
	 */
	public IConnection createConnection(IConnectionProfile profile, String uid,
			String pwd) {
		return createConnection(profile);
	}

	public boolean canWorkOffline(IConnectionProfile profile) {
		// check to see if offline data has been cached for this profile
		return ConnectionInfoImpl.getConnectionFile(profile.getName()).exists();
	}

	public IOfflineConnection createConnection(IConnectionProfile profile, IProgressMonitor monitor) {
		IOfflineConnection connection = (IOfflineConnection) createConnection(profile);
		monitor.done();
		return connection;
	}

	public IOfflineConnection createOfflineConnection(IConnectionProfile profile, IProgressMonitor monitor) {
		// create a connection using offline data
		IOfflineConnection connection = new ConnectionInfoImpl(profile, getClass(), false);
		monitor.done();
		return connection;
	}

}
