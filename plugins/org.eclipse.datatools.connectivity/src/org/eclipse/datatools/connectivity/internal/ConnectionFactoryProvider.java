/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
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
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.util.SafeRunnable;

/**
 * @author rcernich
 * 
 * Created on Jan 16, 2004
 */
public class ConnectionFactoryProvider implements IConnectionFactoryProvider {

	public static final String ATTR_ID = "id"; //$NON-NLS-1$

	public static final String ATTR_PROFILE = "profile"; //$NON-NLS-1$

	public static final String ATTR_CLASS = "class"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	private String mId;

	private String mName;

	private String mProfile;

	private IConnectionFactory mFactory;

	private IConfigurationElement mElement;

	public ConnectionFactoryProvider(IConfigurationElement element) {
		super();
		init(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactoryProvider#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	public IConnection createConnection(IConnectionProfile profile) {
		return createConnection(profile, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactoryProvider#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile,
	 *      java.lang.String, java.lang.String)
	 */
	public IConnection createConnection(IConnectionProfile profile, String uid,
			String pwd) {
		initFactory();
		if (mFactory != null)
			if (uid != null)
				return mFactory.createConnection(profile, uid, pwd);
			else
				return mFactory.createConnection(profile);
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactoryProvider#getConnectionProfileProvider()
	 */
	public IConnectionProfileProvider getConnectionProfileProvider() {
		return ConnectionProfileManager.getInstance().getProvider(mProfile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactoryProvider#getId()
	 */
	public String getId() {
		return mId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactoryProvider#getName()
	 */
	public String getName() {
		return mName;
	}

	private void init(IConfigurationElement element) {
		Assert.isTrue(ConnectionProfileManager.EXT_ELEM_CONNECTION_FACTORY
				.equals(element.getName()));

		mElement = element;

		mId = element.getAttribute(ATTR_ID);
		mName = element.getAttribute(ATTR_NAME);
		mProfile = element.getAttribute(ATTR_PROFILE);
	}

	public Class getConnectionFactoryClass() {
		initFactory();
		return mFactory.getClass();
	}

	private void initFactory() {
		if (mFactory != null)
			return;
		final IConnectionFactory[] result = new IConnectionFactory[1];
		ISafeRunnable code = new SafeRunnable(ConnectivityPlugin.getDefault()
				.getResourceString(
						"dialog.title.error.loadconnectionfactory", //$NON-NLS-1$
						new Object[] { mElement.getDeclaringExtension()
								.getNamespace()})) {

			/*
			 * @see org.eclipse.core.runtime.ISafeRunnable#run()
			 */
			public void run() throws Exception {
				result[0] = (IConnectionFactory) mElement
						.createExecutableExtension(ATTR_CLASS);
			}

		};
		Platform.run(code);
		mFactory = result[0];
	}

}