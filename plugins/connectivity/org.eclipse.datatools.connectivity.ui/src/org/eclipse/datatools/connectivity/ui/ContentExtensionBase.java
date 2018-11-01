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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.ConnectAdapter;
import org.eclipse.datatools.connectivity.ConnectEvent;
import org.eclipse.datatools.connectivity.IConnectListener;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.ui.IActionFilter;

/**
 * Base implementation for IContentExtension. Registers a connect listener with
 * the profile to manage the life cycle of the connection wrapped by this
 * object.
 * 
 * @author shongxum
 */
public abstract class ContentExtensionBase extends PlatformObject implements
		IContentExtension {

	private IConnectionProfile mConnectionProfile;

	private IConnectListener mProfileConnectListener = new ConnectAdapter() {

		public void openConnection(ConnectEvent event) throws CoreException {
			ContentExtensionBase.this.openConnection();
			IConnection connection = getConnection();
			if (connection == null) {
				// RJC: What to do here?
			}
			else {
				Throwable exception = connection.getConnectException();
				if (exception != null) {
					ContentExtensionBase.this.closeConnection();
					throw new CoreException(new Status(IStatus.ERROR,
							ConnectivityUIPlugin.getDefault().getBundle()
									.getSymbolicName(),
							ConnectivityUIPlugin.INTERNAL_ERROR,
							ConnectivityUIPlugin.getDefault()
									.getResourceString(
											"error.contentExtension", //$NON-NLS-1$
											new String[] {
													getLabel(),
													getConnectionProfile()
															.getName(),
													exception.getMessage()}),
							exception));
				}
			}
		}

		public void closeConnection(ConnectEvent event) throws CoreException {
			ContentExtensionBase.this.closeConnection();
		}
	};

	/**
	 * @param contentProvider
	 * @param labelProvider
	 */
	public ContentExtensionBase(IConnectionProfile profile) {
		mConnectionProfile = profile;
		mConnectionProfile.addConnectListener(mProfileConnectListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IContentExtension#getConnectionProfile()
	 */
	public IConnectionProfile getConnectionProfile() {
		return mConnectionProfile;
	}

	/**
	 * Extenders should open a connection
	 * 
	 * @see org.eclipse.datatools.connectivity.IContentExtension#openConnection()
	 */
	public abstract void openConnection();

	/**
	 * Extenders should make sure the IConnection is closed.
	 * 
	 * @see org.eclipse.datatools.connectivity.ui.IContentExtension#closeConnection()
	 */
	public abstract void closeConnection();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IContentExtension#isConnected()
	 */
	public abstract IConnection getConnection();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IContentExtension#dispose()
	 */
	public void dispose() {
		closeConnection();
		getConnectionProfile().removeConnectListener(mProfileConnectListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		if (adapter == IActionFilter.class)
			return new IActionFilter() {

				public boolean testAttribute(Object target, String name,
						String value) {
					if (target == null
							|| !(target instanceof IContentExtension)) {
						return false;
					}
					if (name
							.equals(ConnectivityUIPlugin.FILTER_PROPERTY_CONTEN_EXTENSION_STATE)) {
						return getConnection() != null
								&& getConnection().getConnectException() != null;
					}
					else {
						return false;
					}
				}
			};
		return super.getAdapter(adapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IContentExtension#isVisible()
	 */
	public boolean isVisible() {
		return true;
	}
}