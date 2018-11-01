/*******************************************************************************
 * Copyright (c) 2005-2008 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *    brianf - updated to add read only repository filter
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.ui.IActionFilter;


/**
 * Constants used when evaluating connection profile state through
 * IActionFilter or IPropertyTester
 * 
 * TODO: remove duplicate entries.
 */
public interface IConnectionProfileActionFilter extends IActionFilter {
	
	/**
	 * Used to filter profiles with a specific connection factory
	 */
	public static final String PROFILE_PROPERTY_FACTORY_ID = "org.eclipse.datatools.connectivity.profile.property.factoryID"; //$NON-NLS-1$
	public static final String FACTORY_ID = "factoryID"; //$NON-NLS-1$

	/**
	 * Used to filter profiles that are connected (or not)
	 */
	public static final String PROFILE_PROPERTY_IS_CONNECTED = "org.eclipse.datatools.connectivity.profile.property.isConnected"; //$NON-NLS-1$
	public static final String IS_CONNECTED = "isConnected"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String PROFILE_PROPERTY_MAINTAIN_CONNECTION = "org.eclipse.datatools.connectivity.profile.property.maintainConnection"; //$NON-NLS-1$
	public static final String MAINTAIN_CONNECTION = "maintainConnection"; //$NON-NLS-1$

	/**
	 * Used to filter profiles with a ping connection factory
	 */
	public static final String PROFILE_PROPERTY_SUPPORTS_PING = "org.eclipse.datatools.connectivity.profile.property.supportsPing"; //$NON-NLS-1$
	public static final String SUPPORTS_PING = "supportsPing"; //$NON-NLS-1$

	/**
	 * Used to filter profiles that have extended properties defined
	 */
	public static final String PROFILE_PROPERTY_HAS_EXTENDED_PROPERTIES = "org.eclipse.datatools.connectivity.profile.property.hasExtendedProperties"; //$NON-NLS-1$
	public static final String HAS_EXTENDED_PROPERTIES = "hasExtendedProperties"; //$NON-NLS-1$

	/**
	 * Used to filter a specific type of profile
	 */
	public static final String PROFILE_PROPERTY_PROFILE_TYPE_ID = "org.eclipse.datatools.profile.property.id"; //$NON-NLS-1$
	public static final String TYPE_ID = "id"; //$NON-NLS-1$

	/**
	 * Used to filter profiles with a specific property extension
	 */
	public static final String PROFILE_PROPERTY_PROPERTY_EXTENSION_ID = "org.eclipse.datatools.connectivity.profile.property.extensionID"; //$NON-NLS-1$
	public static final String EXTENSION_ID = "extensionID"; //$NON-NLS-1$

	/**
	 * Used to filter profiles within a specific category
	 */
	public static final String PROFILE_PROPERTY_CATEGORY_ID = "org.eclipse.datatools.connectivity.profile.property.categoryID"; //$NON-NLS-1$
	public static final String CATEGORY_ID = "categoryID"; //$NON-NLS-1$

	/**
	 * Used to filter profiles displayed within a specific view
	 */
	public static final String PROFILE_PROPERTY_VIEW_ID = "org.eclipse.datatools.connectivity.profile.property.containingViewID"; //$NON-NLS-1$
	public static final String VIEW_ID = "containingViewID"; //$NON-NLS-1$

	/**
	 * Used to filter profiles with a specific connection state (DISCONNECTED,
	 * CONNECTED, WORKING_OFFLINE)
	 */
	public static final String PROFILE_PROPERTY_CONNECTION_STATE = "org.eclipse.datatools.connectivity.profile.property.connectionState"; //$NON-NLS-1$
	public static final String CONNECTION_STATE = "connectionState"; //$NON-NLS-1$

	public static final String PROFILE_PROPERTY_DB_VENDOR = "org.eclipse.datatools.connectivity.profile.property.dbVendor";	//$NON-NLS-1$
	public static final String DB_VENDOR = "dbVendor";	//$NON-NLS-1$

	public static final String PROFILE_PROPERTY_DB_VERSION = "org.eclipse.datatools.connectivity.profile.property.dbVersion";	//$NON-NLS-1$
	public static final String DB_VERSION = "dbVersion";	//$NON-NLS-1$
	
	public static final String PROFILE_PROPERTY_REPOSITORY_IS_READ_ONLY = "org.eclipse.datatools.connectivity.profile.property.repositoryIsReadOnly";//$NON-NLS-1$
	public static final String REPOSITORY_IS_READ_ONLY = "repositoryIsReadOnly";//$NON-NLS-1$

	public static final String PROFILE_PROPERTY_CAN_WORK_OFFLINE = "org.eclipse.datatools.connectivity.profile.property.canWorkOffline";//$NON-NLS-1$
	public static final String CAN_WORK_OFFLINE = "canWorkOffline";//$NON-NLS-1$
}
