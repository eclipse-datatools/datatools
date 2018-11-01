/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import org.eclipse.core.runtime.CoreException;

/**
 * Used by UI components to synchronize connectivity of connection profiles.
 * When a request is made to open or close a profile, any component referencing
 * that profile is notifed through this interface. In the event a close is
 * requested, a check is made prior to issuing the close call to see if any open
 * connections are in use.
 * 
 * @author rcernich
 * 
 * @deprecated
 * 
 * Created on May 24, 2005
 */
public interface IConnectListener {

	/**
	 * Return false if the connection should be kept open
	 * 
	 * @param event
	 * @return true if the connection can be closed; false if it should be left
	 *         open.
	 */
	boolean okToClose(ConnectEvent event);

	/**
	 * The listener should open a connection to the server.
	 * 
	 * @param event
	 * @throws CoreException if an error occurs opening the connection.
	 */
	void openConnection(ConnectEvent event) throws CoreException;

	/**
	 * The listener should close its connection to the server.
	 * 
	 * @param event
	 * @throws CoreException if an error occurs closing the connection.
	 */
	void closeConnection(ConnectEvent event) throws CoreException;

}
