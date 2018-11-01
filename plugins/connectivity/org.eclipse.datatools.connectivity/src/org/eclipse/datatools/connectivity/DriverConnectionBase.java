/*******************************************************************************
 * Copyright (c) 2005, 2010 Sybase, Inc. and others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 *      Actuate Corporation - fix for Bugzilla 305757
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * Base implementation for a connection that uses the driver framework.
 * Sub-classes should invoke open() from their constructor to create the
 * connection.
 * 
 * This class takes care of working with the driver management framework,
 * including setting up the class loader required for locating connection
 * classes.
 * 
 * This class depends on the
 * <code>org.eclipse.datatools.connectivity.ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID</code>
 * property being set on the connection profile.
 * 
 * @author rcernich
 */
public abstract class DriverConnectionBase extends VersionProviderConnection {

	protected DriverInstance mDriver;
	protected Object mConnection;
	protected Throwable mConnectException;

	public DriverConnectionBase(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
	}

	/**
	 * opens a connection to the server identified by the connection profile.
	 */
	public void open() {
		if (mConnection != null) {
			close();
		}

		mConnection = null;
		mConnectException = null;

		internalCreateConnection();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IConnection#getRawConnection()
	 */
	public Object getRawConnection() {
		return mConnection;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IConnection#getConnectException()
	 */
	public Throwable getConnectException() {
		return mConnectException;
	}

	/**
	 * Creates the connection to the server identified by the connection
	 * profile.
	 * 
	 * @param cl created from the driver definition (if a class path was
	 *        specified)
	 * @return the new connection
	 * @throws Throwable any error that occurred when trying to create the
	 *         connection
	 */
	protected abstract Object createConnection(ClassLoader cl) throws Throwable;
	
	/**
	 * Initialize version information from the server.
	 */
	protected abstract void initVersions();

	/**
	 * Returns the parent class loader that should be used as the parent to the
	 * class loader created from the driver definition.
	 * 
	 * @return the parent class loader
	 */
	protected ClassLoader getParentClassLoader() {
		return null;
	}

	private void internalCreateConnection() {
		try {
			DriverInstance driver = getDriverDefinition();
			ClassLoader parentCL = getParentClassLoader();
			ClassLoader driverCL = parentCL == null ? driver.getClassLoader()
					: driver.createClassLoader(parentCL);
			
			mConnection = createConnection(driverCL);

			if (mConnection == null) {
				// Connect attempt failed without throwing an exception.
				// We'll generate one for them.
				throw new Exception(ConnectivityPlugin.getDefault().getResourceString("DriverConnectionBase.error.unknown")); //$NON-NLS-1$
			}

			initVersions();
			updateVersionCache();
		}
		catch (Throwable t) {
			mConnectException = t;
			clearVersionCache();
		}
	}

	/**
	 * @return the driver definition referenced by the connection profile.
	 * 
	 * @throws Exception if the driver cannot be located.
	 */
	protected DriverInstance getDriverDefinition() throws Exception {
		if (mDriver == null) {
			String driverID = getDriverDefinitionId();
			if (driverID == null) {
			    String messageId = "DriverConnectionBase.error.driverDefinitionNotSpecified"; //$NON-NLS-1$
				throw new Exception(ConnectivityPlugin.getDefault().getResourceString(messageId), 
				        new Throwable( messageId ));
			}

			mDriver = DriverManager.getInstance().getDriverInstanceByID(
					driverID);
			if (mDriver == null) {
                String messageId = "DriverConnectionBase.error.driverDefinitionNotFound"; //$NON-NLS-1$
				throw new Exception(ConnectivityPlugin.getDefault().getResourceString(messageId),
				        new Throwable( messageId ));
			}
		}

		return mDriver;
	}

	protected String getDriverDefinitionId() {
        IConnectionProfile profile = getConnectionProfile();
        if( profile == null )
            return null;
        return profile.getBaseProperties().getProperty(
                ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID);
	}
	
}
