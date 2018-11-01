/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 * @author rcernich
 * 
 * Created on Jun 1, 2005
 */
public class ConnectionProfileConstants {

	/*
	 * Connection factory used for ping operations. This factory is also used
	 * for defining server version properties (if supported).
	 */
	public static final String PING_FACTORY_ID = "org.eclipse.datatools.connectivity.connectionFactory.pingFactory"; //$NON-NLS-1$

	// Properties
	public static final String PROP_PREFIX = "org.eclipse.datatools.connectivity."; //$NON-NLS-1$

	// Properties common to all profiles
	public static final String PROP_UID = PROP_PREFIX + "uid"; //$NON-NLS-1$
	public static final String PROP_PWD = PROP_PREFIX + "pwd"; //$NON-NLS-1$
	public static final String PROP_DRIVER_DEFINITION_ID = PROP_PREFIX
			+ "driverDefinitionID"; //$NON-NLS-1$

	// Version caching
	/**
	 * The profile extension ID used to store filter settings.
	 */
	public static final String VERSION_INFO_PROFILE_EXTENSION_ID = "org.eclipse.datatools.connectivity.versionInfo"; //$NON-NLS-1$

	public static final String PROP_SERVER_PREFIX = "server."; //$NON-NLS-1$

	public static final String PROP_SERVER_VERSION = PROP_SERVER_PREFIX + "version"; //$NON-NLS-1$
	public static final String PROP_SERVER_NAME = PROP_SERVER_PREFIX + "name"; //$NON-NLS-1$

	public static final String PROP_TECHNOLOGY_PREFIX = "technology."; //$NON-NLS-1$

	public static final String PROP_TECHNOLOGY_NAME_PREFIX = PROP_TECHNOLOGY_PREFIX + "name."; //$NON-NLS-1$
	public static final String PROP_TECHNOLOGY_VERSION_PREFIX = PROP_TECHNOLOGY_PREFIX + "version."; //$NON-NLS-1$

	public static final String UNKNOWN_VERSION = ConnectivityPlugin
			.getDefault().getResourceString(
					"ConnectionProfileConstants.unknownVersion"); //$NON-NLS-1$

	/**
	 * @deprecated
	 */
	public static final String PROP_TECHNOLOGY_VERSION_SUFFIX = ".version"; //$NON-NLS-1$
	/**
	 * @deprecated
	 */
	public static final String PROP_TECHNOLOGY_NAME_SUFFIX = ".mame"; //$NON-NLS-1$

	public static final String createTechnologyVersionKey(String technology) {
		return PROP_TECHNOLOGY_VERSION_PREFIX + technology;
	}

	public static final String createTechnologyNameKey(String technology) {
		return PROP_TECHNOLOGY_NAME_PREFIX + technology;
	}

	// Not intended to be instantiated
	private ConnectionProfileConstants() {
	}

}