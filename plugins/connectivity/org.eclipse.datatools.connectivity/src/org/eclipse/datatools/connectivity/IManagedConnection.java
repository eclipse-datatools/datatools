/*******************************************************************************
 * Copyright (c) 2006-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import org.eclipse.core.runtime.jobs.IJobChangeListener;

/**
 * Managed connections represent connection instances whose life cycle is
 * managed by the connection profile it is associated with through the connect()
 * and disconnnect() methods on IConnectionProfile.
 * 
 * The managed connection object exists throughout the life cycle of the
 * connection profile it is associated with, however it may not always be in a
 * connected state (e.g. getConnection() may return null if the profile is not
 * in a connected state or it may return a connection with an error if an error
 * occurred trying to establish the connection). This allows a consumer to
 * register a listener at anytime.
 * 
 * Clients should register a connection listener so that they may be notified of
 * life cycle events specific to this connection (e.g. open, close).
 * 
 * @author rcernich
 * 
 * Created on Jan 19, 2006
 */
public interface IManagedConnection {

	/**
	 * @return true if this object is managing an active connection.
	 */
	boolean isConnected();

	/**
	 * @return the connection object being managed.
	 */
	IConnection getConnection();

	/**
	 * @return the factory ID of the connection factory used to create the
	 *         connection being managed.
	 */
	String getFactoryID();

	/**
	 * Adds a connection listener to this object.
	 * 
	 * @param listener
	 */
	void addConnectionListener(IManagedConnectionListener listener);

	/**
	 * Removes a connection listener from this object.
	 * 
	 * @param listener
	 */
	void removeConnectionListener(IManagedConnectionListener listener);

	/**
	 * Used to notify other clients that a change has been made to the server.
	 * This gives other clients the opportunity to perform any necessary
	 * updates.
	 * 
	 * @param context the context of the change; may be null
	 */
	void fireModifiedEvent(Object context);

	/**
	 * Creates a copy of the connection being managed. By gentlemen's agreement,
	 * managed connection should be treated as read-only connections. However,
	 * this agreement need not apply to connections where changes to the server
	 * are immediately available to other clients (e.g. non-transactional
	 * connections).
	 * 
	 * @return a copy of the connection being managed.
	 */
	IConnection cloneConnection();

	/**
	 * Creates a copy of the connection being managed. By gentlemen's agreement,
	 * managed connection should be treated as read-only connections. However,
	 * this agreement need not apply to connections where changes to the server
	 * are immediately available to other clients (e.g. non-transactional
	 * connections).
	 * 
	 * The connection should be retrieved from the Job by the listener using
	 * {@link ICloneConnectionJob#getConnection()} after the Job has finished.
	 * 
	 * @param listener cannot be null
	 */
	void cloneConnection(IJobChangeListener listener);

	/**
	 * @return true if the active connection represents an offline connection.
	 */
	boolean isWorkingOffline();
	
	/**
	 * Interface implemented by the Job used in
	 * {@link #cloneConnection(IJobChangeListener)}. This interface is provided
	 * to give the consumer access to the created {@link IConnection}.
	 */
	public interface ICloneConnectionJob {

		/**
		 * @return the connection object created by the Job
		 */
		IConnection getConnection();
	}

}
