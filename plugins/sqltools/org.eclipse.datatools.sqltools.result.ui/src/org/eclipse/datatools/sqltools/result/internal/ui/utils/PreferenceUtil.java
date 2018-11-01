/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.utils;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Utility class for preference handling
 * @author Jeremy Lindop
 */
public class PreferenceUtil {

	/**
	 * 
	 * Gets either a boolean preference setting from a preference store or
	 * gets the default boolean from the preference store, according to whether
	 * the <code>usePreference</code> parameter is set to true or false.
	 * 
	 * @param store Preference store.
	 * @param preference Preference constant.
	 * @param usePreference True indicates the preference setting should be retrieved. False indicates the default should be retrieved.
	 * @return boolean preference value.
	 */
	public static boolean getBoolean(IPreferenceStore store, String preference, boolean usePreference){
		if (usePreference){
			return store.getBoolean(preference);
		}
		else {
			return store.getDefaultBoolean(preference);
		}
	}

	/**
	 * 
	 * Gets either an int preference setting from a preference store or
	 * gets the default int from the preference store, according to whether
	 * the <code>usePreference</code> parameter is set to true or false.
	 * 
	 * @param store Preference store.
	 * @param preference Preference constant.
	 * @param usePreference True indicates the preference setting should be retrieved. False indicates the default should be retrieved.
	 * @return boolean preference value.
	 */
	public static int getInt(IPreferenceStore store, String preference, boolean usePreference){
		if (usePreference){
			return store.getInt(preference);
		}
		else {
			return store.getDefaultInt(preference);
		}
	}

	/**
	 * 
	 * Gets either a String preference setting from a preference store or
	 * gets the default String from the preference store, according to whether
	 * the <code>usePreference</code> parameter is set to true or false.
	 * 
	 * @param store Preference store.
	 * @param preference Preference constant.
	 * @param usePreference True indicates the preference setting should be retrieved. False indicates the default should be retrieved.
	 * @return boolean preference value.
	 */
	public static String getString(IPreferenceStore store, String preference, boolean usePreference){
		if (usePreference){
			return store.getString(preference);
		}
		else {
			return store.getDefaultString(preference);
		}
	}

}
