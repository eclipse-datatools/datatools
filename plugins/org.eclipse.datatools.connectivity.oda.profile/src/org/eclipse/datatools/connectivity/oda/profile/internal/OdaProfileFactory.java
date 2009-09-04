/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.profile.internal;

import java.io.File;
import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Internal factory of an ODA connection profile instance.
 */
public class OdaProfileFactory
{
    private static final String NAME_SEPARATOR = " "; //$NON-NLS-1$
    
    /**
     * Creates an ODA connection profile instance.
     * @param name  profile name; its name may be adjusted for uniqueness among all the profiles
     *          managed by the DTP profile manager
     * @param description   optional description of the profile
     * @param odaDataSourceId   an ODA data source id as specified in an oda.dataSource extension
     * @param baseProperties    connection properties to be stored as profile properties
     * @return  a new instance of {@link IConnectionProfile} that persists in the DTP default profile store file
     * @throws OdaException
     */
    public static IConnectionProfile createProfile(
            String name, String description, String odaDataSourceId, Properties baseProperties )
        throws OdaException
    {
        String uniqueName = getUniqueProfileName( name );
        try
        {
            IConnectionProfile newProfile =
                ProfileManager.getInstance().createProfile( uniqueName, description, 
                    odaDataSourceId, baseProperties );
            return new OdaConnectionProfile( newProfile );
        }
        catch( ConnectionProfileException ex )
        {
            throw new OdaException( ex );
        }
    }

    /**
     * Creates a transient ODA connection profile instance.
     * @param odaDataSourceId   an ODA data source id as specified in an oda.dataSource extension
     * @param profileProperties    connection properties to be stored as profile properties
     * @return  a new instance of {@link IConnectionProfile} that are non persistent
     * @throws OdaException
     */
    public static IConnectionProfile createTransientProfile( String odaDataSourceId, Properties profileProperties )
        throws OdaException
    {
        try
        {
            IConnectionProfile newProfile =
                ProfileManager.getInstance().createTransientProfile( odaDataSourceId, profileProperties );
            return new OdaConnectionProfile( newProfile );
        }
        catch( ConnectionProfileException ex )
        {
            throw new OdaException( ex );
        }
    }

    public static boolean isProfileNameUsed( String profileName )
    {
        IConnectionProfile existingProfile = 
            InternalProfileManager.getInstance().getProfileByName( profileName, false );
        return ( existingProfile != null );
    }
    
    private static String getUniqueProfileName( String profileName )
    {
        if( ! isProfileNameUsed( profileName ) )
            return profileName;     // use as is
        
        // append a number to name till an unique name is found
        int nameCounter = 1;
        String newName = formatProfileName( profileName, nameCounter );
        while( isProfileNameUsed( newName ) )
        {
            newName = formatProfileName( profileName, ++nameCounter );
        }
        return newName;
    }
    
    private static String formatProfileName( String profileName, int nameCounter )
    {
        return profileName + NAME_SEPARATOR + nameCounter;
    }
   
    /**
     * Returns the default profile store file used by the DTP Data Source Explorer.
     * @return
     */
    public static File defaultProfileStoreFile()
    {
        // TODO - refactor to have ProfileManager provide an API method directly 
        return ConnectionProfileMgmt.getStorageLocation().append( 
                            ConnectionProfileMgmt.FILENAME ).toFile();
    }

}
