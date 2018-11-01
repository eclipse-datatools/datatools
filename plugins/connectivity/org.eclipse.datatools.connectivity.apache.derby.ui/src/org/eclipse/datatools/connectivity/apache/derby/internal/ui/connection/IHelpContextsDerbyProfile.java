/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.apache.derby.internal.ui.connection;

public interface IHelpContextsDerbyProfile {

	/**
	 * TPS_helpKey_constants_for_plug-in: org.eclipse.datatools.connectivity.db.derby
	 */

	/*
	 * DERBY_PROFILE_WIZARD_PAGE = the wizard page that collects Derby Embedded profile
	 * details such as the path to the database, user id, password, etc.
	 */
	public static final String DERBY_PROFILE_WIZARD_PAGE = "DERBY_PROFILE_WIZARD_PAGE";

	/*
	 * DERBY_PROFILE_PROPERTY_PAGE = the property page that collects and allows editing of
	 * Derby Embedded profile details such as the path to the database, user id, password, etc.
	 */
	public static final String DERBY_PROFILE_PROPERTY_PAGE = "DERBY_PROFILE_PROPERTY_PAGE";
	
	/*
	 * DERBY_PROFILE_WIZARD = the actual New Derby Embedded Profile wizard
	 */
	public static final String DERBY_PROFILE_WIZARD = "DERBY_PROFILE_WIZARD";
}
