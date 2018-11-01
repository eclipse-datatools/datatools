/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers.models;

/**
 * Provider for the content provider to act as a trigger.
 * 
 * @author brianf
 */
public class DriversProvider {

	// Local instance
	private static DriversProvider provider;

	/**
	 * Hidden constructor
	 */
	private DriversProvider() {
		// empty
	}

	/**
	 * Returns an instance of the provider
	 * @return DriversProvider
	 */
	public static DriversProvider getInstance() {
		if (provider == null)
			provider = new DriversProvider();
		return provider;
	}
}
