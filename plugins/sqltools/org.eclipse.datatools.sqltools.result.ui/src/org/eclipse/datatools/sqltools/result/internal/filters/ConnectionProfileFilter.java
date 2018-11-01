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

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.result.internal.model.ResultInstance;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.utils.ProfileUtil;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filters the result history based on connection profile
 * @author Dafan Yang
 */
public class ConnectionProfileFilter extends ViewerFilter
{
    IPreferenceStore _store;
    
    /**
     * Constructor
     * @param store the preference store used to store the filters information
     */
    public ConnectionProfileFilter(IPreferenceStore store)
    {
        super();
        _store = store;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public boolean select(Viewer viewer, Object parentElement, Object element)
    {
        if(element instanceof ResultInstance)
        {
            IConnectionProfile[] profiles = ProfileUtil.getProfiles();
            ResultInstance instance = (ResultInstance)element;
            boolean isUnknownProfile = true;
            for(int i=0;i<profiles.length;i++)
            {
                if(instance.getOperationCommand().getProfileName().equals(profiles[i].getName()))
                {
                    isUnknownProfile =  false;
                    break;
                }
            }
            if(isUnknownProfile)
            {
                if(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_UNKNOWNPROFILE))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return !ResultsFilterHelper.isFilteredOut(instance.getOperationCommand().getProfileName());
            }
        }

        //should not happen
        return false;
    }
}
