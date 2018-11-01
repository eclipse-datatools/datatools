/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.profile.internal;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.profile.Constants;
import org.eclipse.datatools.connectivity.oda.util.manifest.ConnectionProfileProperty;

/**
 * Internal factory of an ODA connection profile instance.
 */
public class OdaProfileFactory
{
    private static final String NAME_SEPARATOR = " "; //$NON-NLS-1$

    // logging variables
    private static final String sm_className = OdaProfileFactory.class.getName();
    private static Logger sm_logger;
    
    /**
     * Creates a persisted ODA connection profile instance.
     * @param name  profile name; its name may be adjusted for uniqueness among all the profiles
     *          managed by the DTP profile manager
     * @param description   optional description of the profile
     * @param odaDataSourceId   an ODA data source id as specified in an oda.dataSource extension
     * @param profileProperties    connection properties to be stored as profile properties
     * @return  a new instance of {@link IConnectionProfile} that persists in the DTP default profile store file
     * @throws OdaException
     */
    public static IConnectionProfile createProfile(
            String name, String description, String odaDataSourceId, Properties profileProperties )
        throws OdaException
    {
        String uniqueName = getUniqueProfileName( name );
        
        // if profileProperties contains an entry for the profile provider id, use it instead of
        // the specified odaDataSourceId
        String profileProviderId = profileProperties.getProperty( Constants.DB_PROFILE_PROVIDER_ID );
        if( profileProviderId != null )
            odaDataSourceId = profileProviderId;
        
        try
        {
            IConnectionProfile newProfile =
                ProfileManager.getInstance().createProfile( uniqueName, description, 
                    odaDataSourceId, profileProperties );
            return new OdaConnectionProfile( newProfile );
        }
        catch( ConnectionProfileException ex )
        {
            getLogger().logp( Level.WARNING, sm_className, "createProfile(String,String,String,Properties)",  //$NON-NLS-1$
                    "Unable to create a persistent profile instance.", ex ); //$NON-NLS-1$
            throw new OdaException( ex );
        }
    }

    /**
     * Creates a transient ODA connection profile instance.
     * @param connProperties    connection properties to be stored as profile properties,
     *              including a property entry for the profile provider id
     * @return  a new instance of {@link IConnectionProfile} that are non persistent;
     *          or null if the specified properties do not contain expected property entries
     * @throws OdaException
     */
    public static IConnectionProfile createTransientProfile( Properties connProperties ) 
        throws OdaException
    {
        final String methodName = "createTransientProfile(Properties)"; //$NON-NLS-1$
        
        if( ConnectionProfileProperty.hasProfileName( connProperties ) )
        {
            getLogger().logp( Level.FINE, sm_className, methodName,
                "The connection properties contain an external profile name reference; " + //$NON-NLS-1$
                "not expected to use for a transient profile." ); //$NON-NLS-1$
            return null;
        }
        
        String profileProviderId = ( connProperties != null ) ? 
                connProperties.getProperty( Constants.DB_PROFILE_PROVIDER_ID ) : null;
        if( profileProviderId == null || profileProviderId.length() == 0 )
        {
            getLogger().logp( Level.FINE, sm_className, methodName,
                    "No profile provider id specified in the connection properties." ); //$NON-NLS-1$
            return null;
        }

        return createTransientProfile( profileProviderId, connProperties );
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
            getLogger().logp( Level.WARNING, sm_className, "createTransientProfile(String,Properties)",  //$NON-NLS-1$
                    "Unable to create a transient profile instance.", ex ); //$NON-NLS-1$
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
    
    private static Logger getLogger()
    {
        if( sm_logger == null )
        {
            synchronized( OdaProfileFactory.class )
            {
                if( sm_logger == null )
                    sm_logger = Logger.getLogger( sm_className );
            }
        }
        return sm_logger;
    }

}
