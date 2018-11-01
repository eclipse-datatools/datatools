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
package org.eclipse.datatools.sqltools.result.internal.filters;

import java.util.StringTokenizer;

import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * 
 * @author Dafan Yang
 */
public class ResultsFilterHelper
{
    /**
     * End users may never use this char in profile name
     */
    private static final char   PROFILE_DELIMITER_CHAR = 1;
    private static final String PROFILE_DELIMITER      = Character.toString(PROFILE_DELIMITER_CHAR);

    /**
     * Tests if the given profile is filtered out or not
     * @param profileName the given profile
     * @return <code>true</code> if the given profile is selected to be filtered out, <code>false</code> otherwise
     */
    public static boolean isFilteredOut(String profileName)
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        String filterStr = store.getString(PreferenceConstants.PROFILE_FILTERS_FILTERED_PROFILES);
        StringTokenizer st = new StringTokenizer(filterStr, PROFILE_DELIMITER);
        while (st.hasMoreTokens())
        {
            String profile = st.nextToken();
            if (profile.equals(profileName))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Stores the filters when the user changes them. Notice that we are using one string to store the status of the
     * filter: save all the profiles which are filtered out (not selected)
     * 
     * @param profiles the profiles list which are selected to be filtered out
     */
    public static void saveFilters(String[] profiles)
    {
        if (profiles == null)
        {
            return;
        }
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < profiles.length; i++)
        {
            sb.append(profiles[i]);
            if (profiles.length != (i + 1))
            {
                sb.append(PROFILE_DELIMITER);
            }
        }

        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        store.setValue(PreferenceConstants.PROFILE_FILTERS_FILTERED_PROFILES, sb.toString());
    }
}
