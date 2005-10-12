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
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.IProfileExtensionProvider;
import org.eclipse.jface.util.Assert;

/**
 * @author rcernich
 * 
 * Created on Jan 16, 2004
 */
public class ProfileExtensionProvider implements IProfileExtensionProvider {

	public static final String ATTR_ID = "id"; //$NON-NLS-1$
	public static final String ATTR_PROFILE = "profile"; //$NON-NLS-1$
	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	private String mId;
	private String mName;
	private String mProfile;

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

	private void init(IConfigurationElement element) {
		Assert.isTrue(ConnectionProfileManager.EXT_ELEM_PROFILE_EXTENSION
				.equals(element.getName()));

		mId = element.getAttribute(ATTR_ID);
		mName = element.getAttribute(ATTR_NAME);
		mProfile = element.getAttribute(ATTR_PROFILE);
	}

}
