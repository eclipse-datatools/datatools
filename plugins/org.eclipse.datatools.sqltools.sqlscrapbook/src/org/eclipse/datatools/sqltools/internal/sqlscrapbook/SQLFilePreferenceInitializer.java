/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Performs SQL Editor default preference value initialization.
 * 
 * @author Hui Cao
 * 
 */
public class SQLFilePreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * Initializes the preference controls to the default values. These values
	 * are used the first time the preference page is displayed or when the user
	 * presses the Defaults button in the preferences page.
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = SqlscrapbookPlugin.getDefault()
				.getPreferenceStore();

		store.setDefault(SQLFilePreferenceConstants.FAIL_TO_CONNECT_DATABASE,
				true);
		store.setDefault(SQLFilePreferenceConstants.DEFAULT_OPEN, true);
	}

}
