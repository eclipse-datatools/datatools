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

public abstract class DriverConnectionBase extends VersionProviderConnection {

	private DriverInstance mDriver;
	private Object mConnection;
	private Throwable mConnectException;

	public DriverConnectionBase(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
	}

	public void open() {
		if (mConnection != null) {
			close();
		}

		mConnection = null;
		mConnectException = null;

		internalCreateConnection();
	}

	public Object getRawConnection() {
		return mConnection;
	}

	public Throwable getConnectException() {
		return mConnectException;
	}

	protected abstract Object createConnection(ClassLoader cl) throws Throwable;
	
	protected abstract void initVersions();

	protected ClassLoader getParentClassLoader() {
		return null;
	}

	private void internalCreateConnection() {
		try {
			DriverInstance driver = getDriverDefinition();
			ClassLoader driverCL = driver
					.createClassLoader(getParentClassLoader());
			
			mConnection = createConnection(driverCL);

			initVersions();
			updateVersionCache();
		}
		catch (Throwable t) {
			mConnectException = t;
			clearVersionCache();
		}
	}

	protected DriverInstance getDriverDefinition() throws Exception {
		if (mDriver == null) {
			String driverID = getConnectionProfile()
					.getBaseProperties()
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
