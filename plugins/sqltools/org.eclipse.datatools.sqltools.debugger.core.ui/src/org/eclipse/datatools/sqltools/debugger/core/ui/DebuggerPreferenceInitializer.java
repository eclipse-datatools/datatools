/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.debugger.core.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerPreferenceConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;

public class DebuggerPreferenceInitializer extends AbstractPreferenceInitializer
{

    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = DebuggerCoreUIPlugin.getDefault().getPreferenceStore();
        // debugger
        store.setDefault(DebuggerPreferenceConstants.PROMPT_SETTING_BRAKEPOINT_DISABLE, MessageDialogWithToggle.PROMPT);

        // external client detection interval, which only makes sense for ASE for now
        store.setDefault(DebuggerPreferenceConstants.EXTERNAL_CLIENT_REFRESH_INTERVAL, "5000");

        // referenced tables view
        store.setDefault(DebuggerPreferenceConstants.TABLE_VIEW_DISPLAY_NULL, "NULL");
        store.setDefault(DebuggerPreferenceConstants.TABLE_VIEW_MAX_ROW_TO_DISPLAY, "500");
        store.setDefault(DebuggerPreferenceConstants.TABLE_VIEW_SHOW_ROW_NUMBER, "true");
        store.setDefault(DebuggerPreferenceConstants.TABLE_VIEW_AUTO_REFRESH, "false");
    }
}
