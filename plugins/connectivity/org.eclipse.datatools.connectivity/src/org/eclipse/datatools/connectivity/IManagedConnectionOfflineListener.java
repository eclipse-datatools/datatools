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

/**
 * Extends the base interface by adding callbacks specific to working offline.
 * 
 * @author rcernich
 * 
 * Created on May 8, 2007
 */
public interface IManagedConnectionOfflineListener extends
		IManagedConnectionListener {

	/**
	 * A client (or the user) has requested the "live" connection managed by the
	 * associated IManagedConnection be closed, replaced with an offline
	 * version. This callback gives other clients the option of cancelling the
	 * operation (e.g. they still require access to the "live" connection).
	 * 
	 * @param event
	 * 
	 * @return false to prevent the connection from being closed.
	 */
	boolean okToDetach(ConnectEvent event);

	/**
	 * The "live" connection manged by the associated IManagedConnection is
	 * about to be closed. Clients may use this opportunity to perform clean up
	 * operations on the connection prior to it being closed. (Note, this method
	 * is called after okToDetach() and prior to workingOffline().)
	 * 
	 * @param event
	 */
	void aboutToDetach(ConnectEvent event);

	/**
	 * The "live" connection manged by the associated IManagedConnection has
	 * been closed or the client (or user) has chosen to open a the connection
	 * in "offline" mode.
	 * 
	 * @param event
	 */
	void workingOffline(ConnectEvent event);

	/**
	 * The "offline" connection manged by the associated IManagedConnection is
	 * about to be replaced with a "live" connection. Clients may use this
	 * opportunity to perform any operations on the connection prior to it being
	 * opened. (Note, this method is called prior to attached().)
	 * 
	 * @param event
	 */
	void aboutToAttach(ConnectEvent event);

}
