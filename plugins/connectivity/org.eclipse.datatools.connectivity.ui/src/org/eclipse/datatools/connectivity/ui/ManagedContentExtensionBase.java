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
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;

/**
 * Base implementation for IContentExtension.  Connection life cycle management
 * is delegated to the IConnectionProfile.getManagedConnection().
 * 
 * @author rcernich
 * 
 * Created on Jun 20, 2005
 */
public abstract class ManagedContentExtensionBase extends PlatformObject
		implements IContentExtension {

	private IConnectionProfile mConnectionProfile;
	private String mFactoryID;

	/**
	 * @param profile
	 */
	public ManagedContentExtensionBase(IConnectionProfile profile,
										String factoryID) {
		super();
		mConnectionProfile = profile;
		mFactoryID = factoryID;
	}

	public String getFactoryID() {
		return mFactoryID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IContentExtension#getConnectionProfile()
	 */
	public IConnectionProfile getConnectionProfile() {
		return mConnectionProfile;
	}

	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IContentExtension#openConnection()
	 */
	public void openConnection() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IContentExtension#closeConnection()
	 */
	public void closeConnection() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IContentExtension#getConnection()
	 */
	public IConnection getConnection() {
		IManagedConnection imc = getConnectionProfile().getManagedConnection(getFactoryID());
		return imc == null ? null : imc.getConnection();
	}

}