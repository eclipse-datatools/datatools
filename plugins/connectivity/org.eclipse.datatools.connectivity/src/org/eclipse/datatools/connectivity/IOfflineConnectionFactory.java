/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Adds offline capabilities to an IConnectionFactory. Connection profiles that
 * have connection factories defined for them that implement this interface will
 * have the additional "Work offline..." and "Save offline..." methods.
 * 
 * @author rcernich
 * 
 * Created on Apr 2, 2007
 */
public interface IOfflineConnectionFactory extends IConnectionFactory {

	/**
	 * Creates an offline connection.
	 * 
	 * @param profile
	 * @param monitor for communicating progress
	 * 
	 * @return
	 */
	IOfflineConnection createOfflineConnection(IConnectionProfile profile,
			IProgressMonitor monitor);

	/**
	 * This method creates a connection to a server based on the properties
	 * specified by the connection profile. This method uses the user id and
	 * password specified in the profile.
	 * 
	 * @param profile the profile to connect to.
	 * @param monitor for communicating progress
	 * 
	 * @return a connection if successful
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactory#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	IOfflineConnection createConnection(IConnectionProfile profile,
			IProgressMonitor monitor);

	/**
	 * This method is used by the framework to determine whether or not data for
	 * a particular connection profile has been saved for offline use.
	 * 
	 * @param profile the profile
	 * 
	 * @return true if data has been cached for the specified profile (i.e. if
	 *         an offline connection will contain any data).
	 */
	boolean canWorkOffline(IConnectionProfile profile);

}
