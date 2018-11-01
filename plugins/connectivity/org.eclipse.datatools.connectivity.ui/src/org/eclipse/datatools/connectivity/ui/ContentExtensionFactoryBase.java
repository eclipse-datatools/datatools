/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;

/**
 * Extends ContentExtensionBase by implementing the openConnection() and
 * closeConnection() methods using the connection factory specified to 
 * create the connection.
 * 
 * @author shongxum
 */
public abstract class ContentExtensionFactoryBase extends ContentExtensionBase {

	private IConnection mConnection = null;

	private String mFactoryID;

	/**
	 * @param contentProvider
	 * @param labelProvider
	 */
	public ContentExtensionFactoryBase(IConnectionProfile profile,
										String factoryID) {
		super(profile);
		mFactoryID = factoryID;
	}

	/**
	 * Cache the created IConnection in ContentExtensionFactoryBase.
	 * 
	 * @see org.eclipse.datatools.connectivity.ui.ContentExtensionBase#openConnection()
	 */
	public void openConnection() {
		if (mConnection == null) {
			mConnection = getConnectionProfile().createConnection(mFactoryID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.ContentExtensionBase#closeConnection()
	 */
	public void closeConnection() {
		if (mConnection != null) {
			try {
				mConnection.close();
			}
			catch (RuntimeException e) {
				e.printStackTrace();
			}
			mConnection = null;
		}
	}

	/**
	 * Each ContentExtensionFactoryBase manages its own connection, clients
	 * who's calling this api in order to resuse this connection shouldn't try
	 * to close it or modify its state.
	 * 
	 * @see org.eclipse.datatools.connectivity.ui.ContentExtensionBase#isConnected()
	 */
	public IConnection getConnection() {
		return mConnection;
	}

}