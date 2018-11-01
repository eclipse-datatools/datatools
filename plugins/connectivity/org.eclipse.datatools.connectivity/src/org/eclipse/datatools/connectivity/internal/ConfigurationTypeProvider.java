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
package org.eclipse.datatools.connectivity.internal;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.IConfigurationType;

/**
 * @author shongxum
 */
public class ConfigurationTypeProvider implements IConfigurationType {

	public static final String ATTR_ID = "id"; //$NON-NLS-1$
	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	private String mName;
	private String mId;

	/**
	 * @param element
	 */
	public ConfigurationTypeProvider(IConfigurationElement element) {
		init(element);
	}

	/**
	 * @param element
	 */
	private void init(IConfigurationElement element) {
		mId = element.getAttribute(ATTR_ID);
		mName = element.getAttribute(ATTR_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConfigurationType#getId()
	 */
	public String getId() {
		return mId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConfigurationType#getName()
	 */
	public String getName() {
		return mName;
	}

}
