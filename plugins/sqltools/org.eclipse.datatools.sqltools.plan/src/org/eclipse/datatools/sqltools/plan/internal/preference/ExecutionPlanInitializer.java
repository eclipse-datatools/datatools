/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.internal.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Initializer for execution plan preferences
 * @author Dafan Yang
 */
public class ExecutionPlanInitializer extends AbstractPreferenceInitializer
{
    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = PlanViewPlugin.getDefault().getPreferenceStore();
        store.setDefault(PreferenceConstants.VERTICAL_LAYOUT_PLAN_VIEW, false);
        store.setDefault(PreferenceConstants.HORIZONTAL_LAYOUT_PLAN_VIEW, true);
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_DEFAULT_ENCODEING, true);
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING, false);
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING_SELECTION, -1);
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING, System.getProperty("file.encoding", "UTF-8"));
    }
}
