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
package org.eclipse.datatools.sqltools.result.internal.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.sqltools.result.ResultsConstants;

/**
 * Utility class of connection profile
 * @author Dafan Yang
 */
public class ProfileUtil
{
    /**
     * 
     * This method is used to verify if this profile is database profile.
     * 
     * @param connectProfile the connectino profile
     * @return <code>true</code> if this profile is database profile
     */
    private static boolean isDatabaseProfile(IConnectionProfile connectionProfile)
    {
        if (connectionProfile != null)
        {
            if (connectionProfile.getCategory().getId().equalsIgnoreCase(ResultsConstants.DB_CP_CATEGORY))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all the database connection profiles
     * @return all the database connection profiles
     */
    public static IConnectionProfile[] getProfiles()
    {
        ProfileManager pManager = ProfileManager.getInstance();
        // get all profiles
        IConnectionProfile[] allProfiles = pManager.getProfiles();
        List profileList = new ArrayList();

        for (int i = 0; i < allProfiles.length; i++)
        {
            // Temporaryly add all profile
            profileList.add(allProfiles[i]);
//            if(isDatabaseProfile(allProfiles[i]))
//            {
//                profileList.add(allProfiles[i]);
//            }
        }

        IConnectionProfile[] profiles = (IConnectionProfile[]) profileList.toArray(new IConnectionProfile[profileList
            .size()]);
        return profiles;
    }
}
