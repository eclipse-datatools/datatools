/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ui;
/**
 * 
 * @author Cong Chen
 */
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class SybasePreferenceInitializer extends AbstractPreferenceInitializer
{

    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = SybaseUIPlugin.getDefault().getPreferenceStore();

        store.setDefault(SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_SCHEMA, false);
        store.setDefault(SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_OWNER, true);
    }

}
