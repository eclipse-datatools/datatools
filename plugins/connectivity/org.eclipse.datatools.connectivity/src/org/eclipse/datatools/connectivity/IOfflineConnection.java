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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * An offline connection is used to present an offline view of the server to the
 * user. This may involve loading a model that was persisted the last time the
 * user connected to the server (as in the case of ConnectionInfo connections
 * saving a SQL model for the database) or some other provider defined action.
 * 
 * @author rcernich
 * 
 * Created on Apr 2, 2007
 */
public interface IOfflineConnection extends IConnection {

	/**
	 * Attach this connection to the server. In other words, try to connect to
	 * the server and update the state of this connection.
	 * 
	 * It is up to the provider to determine how to merge offline changes with
	 * the server.
	 * 
	 * @param monitor
	 * 
	 * @throws CoreException
	 */
	void attach(IProgressMonitor monitor) throws CoreException;

	/**
	 * Detach this connection from the server. If this connection does not hold
	 * an active connection to the server, no action is taken.
	 * 
	 * The provider may persist the current state of this connection when this
	 * method is invoked. (The provider may perform this task in close() as
	 * well.)
	 * 
	 * @param monitor
	 * 
	 * @throws CoreException
	 */
	void detach(IProgressMonitor monitor) throws CoreException;

	/**
	 * Called by the framework when the user invokes save offline.
	 * 
	 * @param monitor
	 * 
	 * @throws CoreException
	 */
	void save(IProgressMonitor monitor) throws CoreException;

	/**
	 * @return true if the connection is currently detached from the server
	 *         (i.e. it's an offline connection)
	 */
	boolean isWorkingOffline();

}
