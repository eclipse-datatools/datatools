/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.ui.IActionFilter;


/**
 * Constants used when evalutating connection profile state through
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

}
