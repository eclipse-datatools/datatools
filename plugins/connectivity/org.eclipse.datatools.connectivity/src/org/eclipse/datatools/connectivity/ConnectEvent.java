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

import java.util.EventObject;

/**
 * @author rcernich
 * 
 * Created on May 24, 2005
 */
public class ConnectEvent extends EventObject {

	private IManagedConnection mConnection;
	private Object mContext;

	/**
	 * 
	 */
	public ConnectEvent(IConnectionProfile profile) {
		this(profile, null);
	}

	public ConnectEvent(IConnectionProfile profile, IManagedConnection connection) {
		this(profile, connection, null);
	}

	public ConnectEvent(IConnectionProfile profile, IManagedConnection connection,
						Object context) {
		super(profile);
		mConnection = connection;
		mContext = context;
	}

	/**
	 * @return the connection profile associated with this event
	 */
	public IConnectionProfile getConnectionProfile() {
		return (IConnectionProfile) getSource();
	}

	/**
	 * @return the managed connection associated with this event
	 */
	public IManagedConnection getConnection() {
		return mConnection;
	}

	/**
	 * @return the connection specific context associated with this event
	 */
	public Object getContext() {
		return mContext;
	}

}