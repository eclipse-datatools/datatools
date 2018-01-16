/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
