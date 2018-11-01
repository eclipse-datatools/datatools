/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.IProfileExtensionProvider;
import org.eclipse.datatools.connectivity.IPropertiesPersistenceHook;

/**
 * @author rcernich
 * 
 * Created on Jan 16, 2004
 */
public class ProfileExtensionProvider implements IProfileExtensionProvider {

	public static final String ATTR_ID = "id"; //$NON-NLS-1$
	public static final String ATTR_PROFILE = "profile"; //$NON-NLS-1$
	public static final String ATTR_NAME = "name"; //$NON-NLS-1$
	public static final String ATTR_PROPERTIES_PERSISTENCE_HOOK = "propertiesPersistenceHook"; //$NON-NLS-1$

	private String mId;
	private String mName;
	private String mProfile;
	private IPropertiesPersistenceHook mPropertiesPersistenceHook;
	private IConfigurationElement mElement;

	public ProfileExtensionProvider(IConfigurationElement element) {
		super();
		init(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IProfileExtensionProvider#getConnectionProfileProvider()
	 */
	public IConnectionProfileProvider getConnectionProfileProvider() {
		return ConnectionProfileManager.getInstance().getProvider(mProfile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IProfileExtensionProvider#getId()
	 */
	public String getId() {
		return mId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IProfileExtensionProvider#getName()
	 */
	public String getName() {
		return mName;
	}
	
	public IPropertiesPersistenceHook getPropertiesPersistenceHook() {
		loadPropertiesPersistenceHook();
		return mPropertiesPersistenceHook;
	}

	private void init(IConfigurationElement element) {
		Assert.isTrue(ConnectionProfileManager.EXT_ELEM_PROFILE_EXTENSION
				.equals(element.getName()));

		mElement = element;
		mId = element.getAttribute(ATTR_ID);
		mName = element.getAttribute(ATTR_NAME);
		mProfile = element.getAttribute(ATTR_PROFILE);
	}
	
	private void loadPropertiesPersistenceHook() {
		if (mPropertiesPersistenceHook == null) {
			mPropertiesPersistenceHook = ConnectionProfileProvider.DEFAULT_PROPERTIES_PERSISTENCE_HOOK;
			if (mElement.getAttribute(ATTR_PROPERTIES_PERSISTENCE_HOOK) != null) {
				try {
					mPropertiesPersistenceHook = (IPropertiesPersistenceHook) mElement
							.createExecutableExtension(ATTR_PROPERTIES_PERSISTENCE_HOOK);
				}
				catch (CoreException e) {
					if (ConnectionProfileManager.DEBUG_CONNECTION_PROFILE_EXTENSION) {
						System.err
								.println(ConnectivityPlugin
										.getDefault()
										.getResourceString(
												"trace.error.propertiesPersistenceHook", //$NON-NLS-1$
												new Object[] { mProfile, mId}));
						e.printStackTrace();
					}
				}
			}
		}
	}

}
