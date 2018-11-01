/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Initialize SQL Builder preferences.
 * 
 * @author Jeremy Lindop
 */
public class SQLBuilderPreferenceInitializer  extends AbstractPreferenceInitializer {

    /**
     * Initializes the preferences to the default values. These values are used the first time the preference
     * page is displayed or when the user presses the Defaults button in the preferences page.
     */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = SQLBuilderPlugin.getPlugin().getPreferenceStore(); 
		store.setDefault(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_IN_SQL, SQLBuilderPreferenceConstants.DEFAULT_OMIT_CURRENT_SCHEMA_IN_SQL);
		store.setDefault(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_USE_AUID, SQLBuilderPreferenceConstants.DEFAULT_OMIT_CURRENT_SCHEMA_USE_AUID);
		store.setDefault(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_CURRENT_SCHEMA, SQLBuilderPreferenceConstants.DEFAULT_OMIT_CURRENT_SCHEMA_CURRENT_SCHEMA);
	}

}
