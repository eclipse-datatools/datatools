/*******************************************************************************
 * Copyright (c) 2007, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Brian Fitzpatrick - initial API and implementation
 * 			IBM Corporation - refactored plug-in
 ******************************************************************************/
package org.eclipse.datatools.enablement.hsqldb.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author brianf
 *
 */
public class Messages {
	
	private static final String BUNDLE_NAME = "org.eclipse.datatools.enablement.hsqldb.ui.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	/**
	 * 
	 */
	private Messages() {
	}

	/**
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
