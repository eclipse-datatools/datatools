/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IConnectionProfile;

public class ConnectionFactoryAdapterProvider implements IConnectionFactoryProvider {

	public static final String ATTR_FACTORY = "factory"; //$NON-NLS-1$
	public static final String ATTR_ID = "id"; //$NON-NLS-1$
	public static final String ATTR_PROFILE = "profile"; //$NON-NLS-1$
	public static final String ATTR_NAME = "name"; //$NON-NLS-1$
	public static final String ATTR_CLASS = "class"; //$NON-NLS-1$

	private String mFactoryId;
	private String mName;
	private String mId;
	private String mProfileOverride;
	private IConnectionFactory mConnectionFactory;
	private IConfigurationElement mElement;

	public ConnectionFactoryAdapterProvider(IConfigurationElement element) {
		super();
		init(element);
	}

	public IConfigurationElement getConfigurationElement() {
		return mElement;
	}

	public String getId() {
		return mId;
	}
	
	public String getName() {
		return mName;
	}

	public boolean isDefault() {
		return mProfileOverride == null || mProfileOverride.length() == 0;
	}

	public String getFactoryId() {
		return mFactoryId;
	}

	public String getProfileOverride() {
		return mProfileOverride;
	}

	public Class getConnectionFactoryClass() {
		initFactory();
		if (mConnectionFactory == null) {
			return null;
		}
		return mConnectionFactory.getClass();
	}

	public IConnection createConnection(IConnectionProfile profile) {
		initFactory();
		if (mConnectionFactory == null) {
			return null;
		}
		return mConnectionFactory.createConnection(profile);
	}

	public IConnection createConnection(IConnectionProfile profile, String uid,
			String pwd) {
		initFactory();
		if (mConnectionFactory == null) {
			return null;
		}
		return mConnectionFactory.createConnection(profile, uid, pwd);
	}

	private void init(IConfigurationElement element) {
		mElement = element;
		mName = element.getAttribute(ATTR_NAME);
		mFactoryId = element.getAttribute(ATTR_FACTORY);
		mId = element.getAttribute(ATTR_ID);
		mProfileOverride = element.getAttribute(ATTR_PROFILE);
	}

	private void initFactory() {
		if (mConnectionFactory != null) {
			return;
		}
		final IConnectionFactory[] result = new IConnectionFactory[1];
		ISafeRunnable code = new ISafeRunnable() {

			/*
			 * @see org.eclipse.core.runtime.ISafeRunnable#run()
			 */
			public void run() throws Exception {
				result[0] = (IConnectionFactory) mElement
						.createExecutableExtension(ATTR_CLASS);
			}

			public void handleException(Throwable exception) {
				ConnectivityPlugin
						.getDefault()
						.log(
								new Status(
										Status.ERROR,
										ConnectivityPlugin.getDefault()
												.getBundle().getSymbolicName(),
										-1,
										ConnectivityPlugin
												.getDefault()
												.getResourceString(
														"dialog.title.error.loadconnectionfactory", //$NON-NLS-1$
														new Object[] { mElement
																.getContributor()
																.getName()}),
										exception));
			}

		};
		SafeRunner.run(code);
		mConnectionFactory = result[0];
	}

}
