/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
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
 * IManagedConnection notifies clients of changes to the connection being
 * managed through this listener interface.
 * 
 * @author rcernich
 * 
 * Created on Jan 23, 2006
 */
public interface IManagedConnectionListener {

	/**
	 * The connection managed by the associated IManagedConnection has been
	 * opened.
	 * 
	 * @param event
	 */
	void opened(ConnectEvent event);

	/**
	 * The connection manged by the associated IManagedConnection has been
	 * modifed. The context object in the event may specify details as to what
	 * on the server has been modified.
	 * 
	 * @param event
	 */
	void modified(ConnectEvent event);

	/**
	 * A client (or the user) has requested the connection managed by the
	 * associated IManagedConnection be closed. This callback gives other
	 * clients the option of cancelling the operation (e.g. they still require
	 * access to the connection).
	 * 
	 * @param event
	 * 
	 * @return false to prevent the connection from being closed.
	 */
	boolean okToClose(ConnectEvent event);

	/**
	 * The connection manged by the associated IManagedConnection is about to be
	 * closed. Clients may use this opportunity to perform clean up operations
	 * on the connection prior to it being closed. (Note, this method is called
	 * after okToClose() and prior to closed().)
	 * 
	 * @param event
	 */
	void aboutToClose(ConnectEvent event);

	/**
	 * The connection manged by the associated IManagedConnection has been
	 * closed.
	 * 
	 * @param event
	 */
	void closed(ConnectEvent event);

}
