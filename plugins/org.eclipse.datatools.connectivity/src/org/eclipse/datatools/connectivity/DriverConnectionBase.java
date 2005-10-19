/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;

public abstract class DriverConnectionBase implements IConnection {

	private IConnectionProfile mProfile;
	private DriverInstance mDriver;
	private Object mConnection;
	private Throwable mConnectException;

	public DriverConnectionBase(IConnectionProfile profile) {
		super();
	}

	public void open(IConnectionProfile profile) {
		if (mConnection != null) {
			close();
		}

		mConnection = null;
		mConnectException = null;

		mProfile = profile;

		internalCreateConnection();
	}

	public Object getRawConnection() {
		return mConnection;
	}

	public Throwable getConnectException() {
		return mConnectException;
	}

	protected IConnectionProfile getConnectionProfile() {
		return mProfile;
	}

	protected abstract Object createConnection() throws Throwable;

	protected ClassLoader getParentClassLoader() {
		return null;
	}

	private void internalCreateConnection() {
		ClassLoader oldCL = Thread.currentThread().getContextClassLoader();

		try {
			DriverInstance driver = getDriverDefinition();
			ClassLoader driverCL = driver
					.createClassLoader(getParentClassLoader());
			if (driverCL != null) {
				Thread.currentThread().setContextClassLoader(driverCL);
			}
			mConnection = createConnection();
		}
		catch (Throwable t) {
			mConnectException = t;
		}
		finally {
			Thread.currentThread().setContextClassLoader(oldCL);
		}
	}

	protected DriverInstance getDriverDefinition() throws Exception {
		if (mDriver == null) {
			String driverID = getConnectionProfile().getBaseProperties()
					.getProperty(
							ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID);
			if (driverID == null) {
				throw new Exception("Driver definition not specified.");
			}

			mDriver = DriverManager.getInstance().getDriverInstanceByID(
					driverID);
			if (mDriver == null) {
				throw new Exception("Driver definition could not be found.");
			}
		}

		return mDriver;
	}

}
