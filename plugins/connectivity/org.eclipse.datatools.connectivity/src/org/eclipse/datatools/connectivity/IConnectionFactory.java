/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

/**
 * This interface is used for creating connections to connection profiles. This
 * interface must be implemented by connection factory extensions.
 * 
 * @author rcernich
 * 
 * Created on Jan 16, 2004
 */
public interface IConnectionFactory {

	/**
	 * This method creates a connection to a server based on the properties
	 * specified by the connection profile. This method uses the user id and
	 * password specified in the profile.
	 * 
	 * @param profile the profile to connect to.
	 * 
	 * @return a connection if successful
	 */
	IConnection createConnection(IConnectionProfile profile);

	/**
	 * This method creates a connection to a server based on the properties
	 * specified by the connection profile. This method uses the user id and
	 * password specified in the method call.
	 * 
	 * @param profile the profile to connect to.
	 * @param uid the user id to use
	 * @param pwd the password to use
	 * 
	 * @return a connection if successful
	 */
	IConnection createConnection(IConnectionProfile profile, String uid,
			String pwd);

}
