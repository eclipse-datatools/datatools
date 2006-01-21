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
import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.profile.wizards.NewDataSourceWizard;
import org.eclipse.jface.wizard.IWizard;

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

    public static OdaProfileExplorer getInstance()
    {
        if( sm_instance == null )
            sm_instance = new OdaProfileExplorer();
        return sm_instance;
    }

    private OdaProfileExplorer()
    {
    }

    /**
     * Returns a collection of identifiers of all connection profile 
     * instances of a given ODA data source extension type.
     * The profile instances are searched in the current 
     * profile storage location with default file name.
     * It also caches the matching profiles and saves in the
     * current profile storage file for subsequent use.
     * <br>The connection profiles' instance Id and display name
     * are stored as the key and value in the returned Properties instance.
     * Returns an empty <code>Properties</code> if there are 
     * no matching connection profiles found in default store.
     * @param odaDataSourceId   the unique id of the data source element
     *                      in an ODA data source extension.
     * @return  a <code>Properties</code> containing the instanceId
     *          and display name of all existing profiles of given odaDataSourceId.
     * @throws OdaException if unable to read from default storageFile, 
     *                      or to cache the found profiles
     */
    public Properties getProfiles( String odaDataSourceId )
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
     * given profile storage file for subsequent use.
     * <br>The connection profiles' instance Id and display name
     * are stored as the key and value in the returned Properties instance.
     * Returns an empty <code>Properties</code> if there are 
     * no matching connection profiles found in given storageFile.
     * @param odaDataSourceId   the unique id of the data source element
     *                      in an ODA data source extension.
     * @param storageFile   a file that stores profile instances
     * @return  a <code>Properties</code> containing the instanceId
     *          and display name of all existing profiles of given odaDataSourceId.
     * @throws OdaException if unable to read from given storageFile,
     *                      or to cache the found profiles
     */
    public Properties getProfiles( String odaDataSourceId,
                                   File storageFile ) 
        throws OdaException
    {
        // first load all profiles found in given storage file
        IConnectionProfile[] profilesInFile;
        try
        {
            profilesInFile = ConnectionProfileMgmt.loadCPs( storageFile );
        }
        catch( Exception ex )
        {
             throw newOdaException( ex );
        }

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
            throw newOdaException( ex );
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
     * Returns the ODA custom wizard provided by an
     * ODA data source designer that implements the 
     * connection profile extension point.
     * @param profileInstanceId the instance id of a connection profile
     * @return  the ODA custom wizard, or null if no wizard is provided
     * @throws OdaException if the profile provider has not implemented 
     *                      the expected Oda wizard type
     */
    public NewDataSourceWizard getCustomWizard( String profileInstanceId )
        throws OdaException
    {
        // ODA profiles use the odaDataSourceId as its profile identifier
        String odaDataSourceId = getProfile( profileInstanceId ).getProviderId();
        IWizard dataSourceWizard =
            ConnectionProfileManager.getInstance().getNewWizard( odaDataSourceId );
        
        if( dataSourceWizard == null )
            return null;    // has not implemented a new data source wizard
        
        if( dataSourceWizard instanceof NewDataSourceWizard == false )
            throw newOdaException( "invalid.oda.wizard", odaDataSourceId ); //$NON-NLS-1$

        return (NewDataSourceWizard) dataSourceWizard;       
    }
    
    /*
     * Returns the profile in cache with given instance id.
     * @throws IllegalArgumentException if instance is not found.
     */
    IConnectionProfile getProfile( String profileInstanceId )
    {
        IConnectionProfile profile =
            ProfileManager.getInstance().getProfileByInstanceID( profileInstanceId );
        if( profile == null )
            throw new IllegalArgumentException( profileInstanceId );
        return profile;
    }
    
    private OdaException newOdaException( String messageId, Object msgArgument )
    {
        // TODO - get localized message text
        return new OdaException( messageId );
    }
    
    private OdaException newOdaException( Throwable cause )
    {
        OdaException ex = new OdaException();
        ex.initCause( cause );
        return ex;
    }
}
