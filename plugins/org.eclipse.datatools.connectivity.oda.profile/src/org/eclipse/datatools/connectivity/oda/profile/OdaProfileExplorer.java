/*
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.profile;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * The ODA Profile Explorer is a singleton class that provides
 * the services to explore ODA connection profile instances
 * and their main content.
 * The <code>OdaProfileExplorer</code> singleton instance is retrieved 
 * using the <code>getInstance()</code> method.
 */
public class OdaProfileExplorer
{
    private static OdaProfileExplorer sm_instance = null;

    /**
     * Static method to return the singleton instance.
     * @return
     */
    public static synchronized OdaProfileExplorer getInstance()
    {
        if( sm_instance == null )
            sm_instance = new OdaProfileExplorer();
        return sm_instance;
    }

    protected OdaProfileExplorer()
    {
    }

    /**
     * Returns a collection of identifiers of all connection profile 
     * instances of a given ODA data source extension type.
     * The profile instances are searched in the current 
     * profile storage location with default file name.
     * It also caches the matching profiles and saves in the
     * current profile storage file for subsequent use.
     * @param odaDataSourceId   the unique id of the data source element
     *                      in an ODA data source extension.
     * @return  a <code>Map</code> containing the instance Id
     *          and display name of all existing profiles of given odaDataSourceId.
     *          The connection profiles' instance Id and display name
     *          are stored as the key and value strings in the returned <code>Map</code> instance.
     *          Returns an empty collection if there are 
     *          no matching connection profiles found in default store.
     * @throws OdaException if unable to read from default storageFile, 
     *                      or to cache the found profiles
     */
    public Map getProfiles( String odaDataSourceId )
        throws OdaException
    {
        // use the current storage location and default store filename
        File profileStore = 
            ConnectionProfileMgmt.getStorageLocation().append( 
                    ConnectionProfileMgmt.FILENAME ).toFile();
        return getProfiles( odaDataSourceId, profileStore );
    }

    /**
     * Returns a collection of identifiers of all connection profile 
     * instances of a given ODA data source extension type.
     * The profile instances are searched in the given profile storage file.
     * It also caches the matching profiles and saves in the
     * current profile storage file for subsequent use.
     * @param odaDataSourceId   the unique id of the data source element
     *                      in an ODA data source extension.
     * @param storageFile   a file that stores profile instances
     * @return  a <code>Map</code> containing the instance Id
     *          and display name of all existing profiles of given odaDataSourceId.
     *          The connection profiles' instance Id and display name
     *          are stored as the key and value strings in the returned <code>Map</code> instance.
     *          Returns an empty collection if there are 
     *          no matching connection profiles found in default store.
     * @throws OdaException if unable to read from given storageFile,
     *                      or to cache the found profiles
     */
    public Map getProfiles( String odaDataSourceId, File storageFile ) 
        throws OdaException
    {
        // first load all profiles found in given storage file
        IConnectionProfile[] profilesInFile = loadProfiles( storageFile );

        // return those profile ids that match given oda data source type,
        // and add them in the ProfileManager's profiles cache
        Properties profileIds = new Properties();
        for( int i = 0; i < profilesInFile.length; i++ ) 
        {
            // ODA profiles use the odaDataSourceId as its profile identifier
            if( ! profilesInFile[i].getProviderId().equals( odaDataSourceId ) )
                continue;   // not a match
            
            // found a profile for the given type of odaDataSourceId

            IConnectionProfile matchedProfile = profilesInFile[i];
            
            String profileDisplayName = matchedProfile.getDescription();
            if( profileDisplayName == null || profileDisplayName.length() == 0 )
                profileDisplayName = matchedProfile.getName();
            
            profileIds.setProperty( matchedProfile.getInstanceID(), 
                                    profileDisplayName );
            
            // adds matched profile to ProfileManager's profiles cache
            addProfileToCache( matchedProfile );
        }
        
        return profileIds;
    }

    /*
	 * Reads and returns the profiles found in given storage file.
     * @param storageFile
     * @return
     * @throws OdaException
     */
    private IConnectionProfile[] loadProfiles( File storageFile ) 
        throws OdaException
    {
        IConnectionProfile[] profilesInFile;
        try
        {
            profilesInFile = ConnectionProfileMgmt.loadCPs( storageFile );
        }
        catch( Exception ex )
        {
            throw new OdaException( ex );
        }
        return profilesInFile;
    }
    
    /*
     * Adds given profile to ProfileManager's profiles cache
     */
    private void addProfileToCache( IConnectionProfile profile )
        throws OdaException
    {
        try
        {
            ProfileManager.getInstance().addProfile( profile, 
                                true /*replaceExisting*/ );
        }
        catch( ConnectionProfileException ex )
        {
            throw new OdaException( ex );
        }        
    }
    
    /**
     * Returns the profile properties with values collected
     * in a connection profile instance.
     * @param profileInstanceId the instance id of a connection profile
     * @return  the properties of a connection profile instance
     * @throws IllegalArgumentException if instance is not found.
     */
    public Properties getProfileProperties( String profileInstanceId )
    {
        IConnectionProfile profile = getProfile( profileInstanceId );        
        return profile.getBaseProperties();
    }

    /**
     * Returns the profile in cache with given instance id.
     * @param profileInstanceId     the unique instance id of a profile
     *                              in cache, as found in the collection
     *                              returned by the getProfiles methods
     * @return  the instance of matching profile
     * @throws IllegalArgumentException if instance is not found.
     */
    public IConnectionProfile getProfile( String profileInstanceId )
    {
        IConnectionProfile profile =
            ProfileManager.getInstance().getProfileByInstanceID( profileInstanceId );
        if( profile == null )
            throw new IllegalArgumentException( profileInstanceId );
        return profile;
    }
    
    /**
     * Finds and returns the profile instance in given storage file
     * that matches the given profile instance name.
     * @param profileName   The unique name of a connection profile instance.
     * @param storageFile   a file that stores profile instances
     * @return  the matching profile instance, or null if not found
     */
    public IConnectionProfile getProfileByName( String profileName,
                                                File storageFile )
        throws OdaException
    {
        // first load all profiles found in given storage file
        IConnectionProfile[] profilesInFile = loadProfiles( storageFile );

        for( int i = 0; i < profilesInFile.length; i++ ) 
        {
            if( profilesInFile[i].getName().equals( profileName ) )
                return profilesInFile[i];   // a match
        }
        return null;    // no match is found
    }

}
