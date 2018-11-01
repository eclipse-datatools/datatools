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
 * The base interface for working with connections created from connection
 * profiles.
 * 
 * @author rcernich
 * 
 * Created on Jan 15, 2004
 */
public interface IConnection {
	
	/**
	 * Returns the native object representing the connection to the server. For
	 * example, a java.sql.Connection object for a JDBC connection.
	 * 
	 * @return an object representing the native connection to the server.
	 */
	Object getRawConnection();

	/**
	 * Closes the underlying native connection object. This causes other users
	 * of this connection to be notified of this connection's closure.
	 */
	void close();

	/**
	 * Return exception in connection
	 * 
	 * @return exception thrown when connecting to the server
	 */
	Throwable getConnectException();
	
	/**
	 * @return the connection profile used to create this connection.
	 */
	IConnectionProfile getConnectionProfile();
	
}