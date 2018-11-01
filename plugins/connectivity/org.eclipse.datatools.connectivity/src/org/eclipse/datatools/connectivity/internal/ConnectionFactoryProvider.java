/*******************************************************************************
 * Copyright (c) 2004-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 *              IBM Corporation - fix for defect 222818
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.IOfflineConnection;
import org.eclipse.datatools.connectivity.IOfflineConnectionFactory;

/**
 * @author rcernich
 * 
 * Created on Jan 16, 2004
 */
public class ConnectionFactoryProvider implements InternalConnectionFactoryProvider {

	public static final String ATTR_ID = "id"; //$NON-NLS-1$

	public static final String ATTR_PROFILE = "profile"; //$NON-NLS-1$

	public static final String ATTR_CLASS = "class"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$
	
	public static final String ATTR_PRIORITY = "priority"; //$NON-NLS-1$

	private String mId;

	private String mName;
	
	private String mPriority;

	private String mProfile;
	
	private String mClassAttr;

	private IConnectionFactory mFactory;

	private IConfigurationElement mElement;

	public ConnectionFactoryProvider(IConfigurationElement element) {
		super();
		init(element);
	}
	
	public ConnectionFactoryProvider(IConfigurationElement element, String factoryID, String profileID, String classAttr, String priority) {
		super();
		mElement = element;
		mId = factoryID;
		mProfile = profileID;
		mClassAttr = classAttr;
		mPriority = priority;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactoryProvider#createConnection(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	public IConnection createConnection(IConnectionProfile profile) {
		initFactory();
		if (supportsWorkOfflineMode()) {
			return createConnection(profile, new NullProgressMonitor());
		}
		else if (mFactory != null) {
			return mFactory.createConnection(profile);
		}
		return null;
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
				return createConnection(profile);
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectionFactoryProvider#getPriority()
	 */
	public String getPriority() {
		return mPriority;
	}

	private void init(IConfigurationElement element) {
		Assert.isTrue(ConnectionProfileManager.EXT_ELEM_CONNECTION_FACTORY
				.equals(element.getName()));

		mElement = element;

		mId = element.getAttribute(ATTR_ID);
		mName = element.getAttribute(ATTR_NAME);
		mProfile = element.getAttribute(ATTR_PROFILE);
		mClassAttr = ATTR_CLASS;
		mPriority = element.getAttribute(ATTR_PRIORITY);
	}

	public Class getConnectionFactoryClass() {
		initFactory();
		return mFactory.getClass();
	}

	public boolean supportsWorkOfflineMode() {
		initFactory();
		return mFactory != null
				&& mFactory instanceof IOfflineConnectionFactory;
	}

	public boolean canWorkOffline(IConnectionProfile profile) {
		return supportsWorkOfflineMode()
				&& ((IOfflineConnectionFactory) mFactory)
						.canWorkOffline(profile);
	}

	public IOfflineConnection createConnection(IConnectionProfile profile,
			IProgressMonitor monitor) {
		if (supportsWorkOfflineMode()) {
			return ((IOfflineConnectionFactory) mFactory).createConnection(
					profile, monitor);
		}
		return null;
	}

	public IOfflineConnection createOfflineConnection(
			IConnectionProfile profile, IProgressMonitor monitor) {
		if (supportsWorkOfflineMode()) {
			return ((IOfflineConnectionFactory) mFactory)
					.createOfflineConnection(profile, monitor);
		}
		return null;
	}

	private void initFactory() {
		if (mFactory != null)
			return;
		final IConnectionFactory[] result = new IConnectionFactory[1];
		ISafeRunnable code = new ISafeRunnable() {

			/*
			 * @see org.eclipse.core.runtime.ISafeRunnable#run()
			 */
			public void run() throws Exception {
				result[0] = (IConnectionFactory) mElement
						.createExecutableExtension(mClassAttr);
			}

			public void handleException(Throwable exception) {
				ConnectivityPlugin.getDefault().log(exception);
			}

		};
		SafeRunner.run(code);
		mFactory = result[0];
	}
}