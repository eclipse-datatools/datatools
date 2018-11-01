/*
 *************************************************************************
 * Copyright (c) 2005, 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.profile;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.services.impl.ProviderUtil;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaProfileFactory;
import org.eclipse.datatools.connectivity.oda.profile.provider.ProfilePropertyProviderImpl;

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

    // logging variables
    private static final String sm_className = OdaProfileExplorer.class.getName();
    private static Logger sm_logger;

    private Map<FileKey, IConnectionProfile[]> m_loadedProfilesByFile;
    private File m_defaultProfileStoreFile;
    
    /**
     * Static method to return the singleton instance.
     * @return
     */
    public static OdaProfileExplorer getInstance()
    {
        if( sm_instance == null )
        {
            synchronized( OdaProfileExplorer.class )
            {
                if( sm_instance == null )
                    sm_instance = new OdaProfileExplorer();
            }
        }
        return sm_instance;
    }
    
    /**
     * Singleton instance release method.
     */
    public static void releaseInstance()
    {
        synchronized( OdaProfileExplorer.class )
        {
            if( sm_instance != null )
            {
                sm_instance.refresh();
                sm_instance = null;
            }
            sm_logger = null;
        }
    }

    /**
     * Returns the class logger.
     */
    private static Logger getLogger()
    {
        if( sm_logger == null )
        {
            synchronized( OdaProfileExplorer.class )
            {
                if( sm_logger == null )
                    sm_logger = Logger.getLogger( sm_className );
            }
        }
        return sm_logger;
    }

    protected OdaProfileExplorer()
    {
    }
    
    /**
     * Refresh the profile explorer, which allows it to get
     * the latest profile instances in a profile storage file.
     * @since DTP 1.6
     */
    public void refresh()
    {
        if( m_loadedProfilesByFile == null || m_loadedProfilesByFile.isEmpty() )
            return;     // done; nothing to reset

        // reset the cached collection of loaded profile instances
        m_loadedProfilesByFile.clear();
    }

    private Map<FileKey, IConnectionProfile[]> getLoadedProfilesMap()
    {
        if( m_loadedProfilesByFile == null )
        {
            synchronized( this )
            {
                if( m_loadedProfilesByFile == null )
                    m_loadedProfilesByFile = Collections.synchronizedMap( new TreeMap<FileKey, IConnectionProfile[]>() );
            }
        }
        return m_loadedProfilesByFile;
    }
    
    private File defaultProfileStoreFile()
    {
        if( m_defaultProfileStoreFile == null )
        {
            synchronized( this )
            {
                if( m_defaultProfileStoreFile == null )
                    m_defaultProfileStoreFile = OdaProfileFactory.defaultProfileStoreFile();
            }
        }
        return m_defaultProfileStoreFile;
    }

    /**
     * Returns a collection of identifiers of all connection profile 
     * instances of a given ODA data source extension type.
     * The profile instances are searched in the current 
     * profile storage location with default file name.
     * It also caches the matching profiles and saves in the
     * current profile storage file for subsequent use.
     * @deprecated  As of DTP 1.6, replaced by {@link #getProfileIdentifiersByOdaProviderId(String, File)}
     */
    public Map getProfiles( String odaDataSourceId )
        throws OdaException
    {
        return getProfileIdentifiersByOdaProviderId( odaDataSourceId, null );
    }

    /**
     * Returns a collection of identifiers of all connection profile 
     * instances of a given ODA data source extension type.
     * The profile instances are searched in the given profile storage file.
     * It also caches the matching profiles and saves in the
     * current profile storage file for subsequent use.
     * @deprecated  As of DTP 1.6, replaced by {@link #getProfileIdentifiersByOdaProviderId(String, File)}
     */
    public Map getProfiles( String odaDataSourceId, File storageFile ) 
        throws OdaException
    {
        return getProfileIdentifiersByOdaProviderId( odaDataSourceId, storageFile );
    }
    
    /**
     * Returns a collection of identifiers of all connection profile instances 
     * of the given ODA data source extension type.
     * The profile instances are searched from the specified profile storage file.
     * @param odaDataSourceId   the unique id of the data source element
     *                      in an ODA data source extension.
     * @param storageFile   a file that stores profile instances; may be null, which 
     *                      will use the default store path of the Connectivity plugin
     * @return  a <code>Map</code> containing the instance Id (as key)
     *          and description (as value) of all existing profiles of given odaDataSourceId;
     *          may be an empty collection if there are 
     *          no matching connection profiles found in specified store.
     * @throws OdaException if unable to read from the given storageFile
     * @since DTP 1.6
     */
    public Map getProfileIdentifiersByOdaProviderId( String odaDataSourceId, File storageFile ) 
        throws OdaException
    {
        return getProfileIdentifiersByCategoryOrProviderId( null, odaDataSourceId, storageFile, true );
    }

    /**
     * Returns a collection of identifiers of all connection profile instances 
     * under the specified profile category id.
     * The profile instances are searched from the specified profile storage file.
     * @param categoryId    the unique id of a connection profile category
     * @param storageFile   a file that stores profile instances; may be null, which 
     *                      will use the default store path of the Connectivity plugin
     * @return  a <code>Map</code> containing the instance Id (as key)
     *          and description (as value) of all existing profiles of given odaDataSourceId;
     *          may be an empty collection if there are 
     *          no matching connection profiles found in specified store.
     * @throws OdaException if unable to read from the given storageFile
     * @since DTP 1.6
     */
    public Map getProfileIdentifiersByCategory( String categoryId, File storageFile ) 
        throws OdaException
    {
        return getProfileIdentifiersByCategoryOrProviderId( categoryId, null, storageFile, true );
    }

    /**
     * Returns a collection of instance id and name of all connection profile instances 
     * under the specified profile category id.
     * The profile instances are searched from the specified profile storage file.
     * @param categoryId    the unique id of a connection profile category
     * @param storageFile   a file that stores profile instances; may be null, which 
     *                      will use the default store path of the Connectivity plugin
     * @return  a <code>Map</code> containing the instance Id (as key),
     *          and name (as value) of all existing profiles of given odaDataSourceId;
     *          may be an empty collection if there are 
     *          no matching connection profiles found in specified store.
     * @throws OdaException if unable to read from the given storageFile
     * @since 3.2.2 (DTP 1.7.2)
     */
    @SuppressWarnings("unchecked")
    public Map<String,String> getProfileIdsAndNamesByCategory( String categoryId, File storageFile ) 
        throws OdaException
    {
        return getProfileIdentifiersByCategoryOrProviderId( categoryId, null, storageFile, false );
    }

    private Map getProfileIdentifiersByCategoryOrProviderId( String categoryId, String odaDataSourceId, 
                    File storageFile, boolean useDescriptionAsPropValue ) 
        throws OdaException
    {
        // first get all profiles found in given storage file, load the file if necessary
        IConnectionProfile[] profilesInFile = loadProfiles( storageFile );

        // return those profile ids that match given oda data source type,
        // and add them in the ProfileManager's profiles cache
        Properties profileIds = new Properties();
        for( int i = 0; i < profilesInFile.length; i++ ) 
        {
            IConnectionProfile aProfile = profilesInFile[i];            

            // check that the profile is under the specified category
            if( categoryId != null )
            {
                if( ! aProfile.getCategory().getId().equals( categoryId ) )
                    continue;   // not a match
            }
            else if( odaDataSourceId != null )  // find a match by odaDataSourceId instead
            {
                // ODA profiles use the odaDataSourceId as its profile identifier
                if( ! aProfile.getProviderId().equals( odaDataSourceId ) )
                    continue;   // not a match
            }
            else
                continue;
            
            // found a profile for the specified category or odaDataSourceId,
            // adds matched profile found in storageFile to the identifiers Map

            addProfileIdentifier( aProfile, profileIds, useDescriptionAsPropValue );
        }
        
        return profileIds;
    }

    /*
	 * Reads and returns the profiles found in given storage file.
     * @param storageFile   connection profile store file; may be null, which 
     *                      will use the default store of the Connectivity plugin
     * @return
     * @throws OdaException
     */
    private IConnectionProfile[] loadProfiles( File storageFile ) 
        throws OdaException
    {
        return loadProfiles( storageFile, null );
    }
    
    private IConnectionProfile[] loadProfiles( File storageFile, String encryptedFileExtension ) 
        throws OdaException
    {
        if( storageFile == null )
        {
            getLogger().fine( "A null storageFile argument is specified. Using default profile storage location instead." ); //$NON-NLS-1$
            storageFile = defaultProfileStoreFile();
            encryptedFileExtension = null;     // disable; use default storage file's own extension instead
            
            // triggers initialization of the default profile store, if not already done
            ProfileManager.getInstance().getProfiles( false );
        }
        
        // first check if specified storage file is already loaded and cached in collection
        IConnectionProfile[] profilesInFile = getLoadedProfiles( storageFile );
        if( profilesInFile != null )
            return profilesInFile;
        
        // no cached profiles for storage file; load the storage file
        try
        {
            profilesInFile = ConnectionProfileMgmt.loadCPsUsingFileExtension( storageFile, encryptedFileExtension );
        }
        catch( Exception ex )
        {
            throw new OdaException( ex );
        }
        
        // save it in the cached collection in a synchronized manner
        return addToCacheByFile( storageFile, profilesInFile );
    }
    
    /**
     * Add the specified profile instances loaded from the specified storage file,
     * to the synchronized collection in cache.
     * This call expects the profiles are first loaded, to minimize the locking on 
     * the cached collection.
     * @param storageFile   the storage file object that serves as the mapping key
     * @param profilesInFile
     * @return  the cached profile instances in the specified file
     */
    private IConnectionProfile[] addToCacheByFile( File storageFile, IConnectionProfile[] profilesInFile )
    {
        IConnectionProfile[] cachedProfiles;
        Map<FileKey, IConnectionProfile[]> loadedProfilesMap = getLoadedProfilesMap();
        FileKey storageFileKey = new FileKey(storageFile);
        synchronized( loadedProfilesMap )
        {
            // in case another thread has added to the same key in between this checking, use
            // the currently cached value
            cachedProfiles = (IConnectionProfile[]) loadedProfilesMap.get( storageFileKey );
            if( cachedProfiles == null )
            {                                   
                // save the specified profiles in cached collection
                cachedProfiles = profilesInFile;
                loadedProfilesMap.put( storageFileKey, cachedProfiles );
            }
        }
        return cachedProfiles;
    }

    private IConnectionProfile[] getLoadedProfiles( File storageFile ) 
    {
        return (IConnectionProfile[]) getLoadedProfilesMap().get( new FileKey(storageFile) );
    }

    /*
     * For use as a key in the Map of profile store File and its corresponding 
     * loaded profile instances.  
     * The File key is comparable based on its file path.
     * @since 3.2.3 (DTP 1.8)
     */
    class FileKey implements Comparable<FileKey>
    {
        private File m_file;
        
        FileKey( File aFile )
        {
            m_file = aFile;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals( Object obj )
        {
            if( ! (obj instanceof FileKey ))
                return false;
    
            return this.compareTo( (FileKey)obj ) == 0;
        }

        public int compareTo( FileKey obj )
        {
            if( this == obj || m_file == obj.getFile() )
                return 0;
            return m_file.compareTo( obj.getFile() );
        }
        
        private File getFile()
        {
            return m_file;
        }
    }
    
    /*
     * Adds given profile instance's identifier to the given collection.
     */
    private void addProfileIdentifier( IConnectionProfile profile, Properties profileIds, 
            boolean useDescriptionAsPropValue )
    {
        assert( profile != null && profileIds != null );

        String profileName = null;        
        if( useDescriptionAsPropValue )
            profileName = profile.getDescription();
        
        if( profileName == null || profileName.length() == 0 )
            profileName = profile.getName();
        
        profileIds.setProperty( profile.getInstanceID(), 
                                profileName );
    }
    
    /**
     * Returns the profile properties with values collected
     * in a connection profile instance.
     * @param profileInstanceId the instance id of a connection profile
     * @return  the properties of a connection profile instance
     * @throws IllegalArgumentException if instance is not found.
     * @deprecated  As of DTP 1.6, replaced by {@link #getProfileProperties(String, File)}
     */
    public Properties getProfileProperties( String profileInstanceId )
    {
        IConnectionProfile profile = getProfile( profileInstanceId );        
        return profile.getBaseProperties();
    }

    /**
     * Returns the profile properties with values collected
     * in the specified connection profile instance in the storageFile.
     * @param profileInstanceId     the unique static id of 
     *                              a connection profile instance
     * @param storageFile   a file that stores profile instances; 
     *                      may be null, which would use the default store 
     *                      of the Connectivity plugin
     * @return  the properties of a connection profile instance
     * @throws IllegalArgumentException if connection profile instance is not found.
     * @since DTP 1.6
     */
    public Properties getProfileProperties( String profileInstanceId,
                                            File storageFile )
    {
        IConnectionProfile profile = null;
        try
        {
            profile = getProfileById( profileInstanceId, storageFile );
        }
        catch( OdaException ex )
        {
            throw new IllegalArgumentException( ex.toString() );
        } 
        if( profile == null )
            throw new IllegalArgumentException( profileInstanceId );
        
        return profile.getBaseProperties();
    }
    
    /**
     * Returns the profile with given instance id found in ProfileManager cache.
     * @param profileInstanceId     the unique instance id of a profile
     *                              in cache, as found in the collection
     *                              returned by the getProfiles methods
     * @return  the instance of matching profile
     * @throws IllegalArgumentException if instance is not found.
     * @deprecated  As of DTP 1.6, replaced by {@link #getProfileById(String, File)}
     */
    public IConnectionProfile getProfile( String profileInstanceId )
    {
        IConnectionProfile profile =
            ProfileManager.getInstance().getProfileByInstanceID( profileInstanceId );
        if( profile == null )
            throw new IllegalArgumentException( profileInstanceId );
        return createOdaWrapper( profile );
    }
    
    /**
     * Finds and returns the profile instance in given storage file
     * that matches the given profile's static instance id.
     * @param profileInstanceId     the unique static id of 
     *                              a connection profile instance
     * @param storageFile   a file that stores profile instances; 
     *                      may be null, which would use the default store 
     *                      of the Connectivity plugin
     * @return  the matching profile instance, or null if not found
     * @since DTP 1.6
     */
    public IConnectionProfile getProfileById( String profileInstanceId,
                                              File storageFile )
        throws OdaException
    {
        // first load all profiles found in given storage file, if necessary
        IConnectionProfile[] profilesInFile = loadProfiles( storageFile );

        for( int i = 0; i < profilesInFile.length; i++ ) 
        {
            if( profilesInFile[i].getInstanceID().equals( profileInstanceId ) )
                return createOdaWrapper( profilesInFile[i] );   // a match
        }
        return null;    // no match is found
    }
    
    /**
     * Finds and returns the profile instance in given storage file
     * that matches the given profile instance name.
     * @param profileName   the unique name of a connection profile instance.
     * @param storageFile   a file that stores profile instances; 
     *                      may be null, which would use the default store 
     *                      of the Connectivity plugin
     * @return  the matching profile instance, or null if not found
     * @throws OdaException
     */
    public IConnectionProfile getProfileByName( String profileName,
                                                File storageFile )
        throws OdaException
    {
        return getProfileByName( profileName, storageFile, null );
    }

    /**
     * Finds and returns the profile instance in given profile store file
     * that matches the given profile instance name.
     * If the specified file is encrypted, use the cipher provider registered 
     * for the specified file extension.
     * @param profileName   the unique name of a connection profile instance.
     * @param profileStoreFile  a file that stores profile instances; 
     *                      may be null, which would use the default store 
     *                      of the Connectivity plugin
     * @param encryptedFileExtension   the file extension for which a cipher provider is registered.
     *         It may be different from that of the specified file.
     *         If null value, the extension of the specified file will be used by default.
     * @return  the matching profile instance, or null if not found
     * @throws OdaException
     * @since 3.2.7 (DTP 1.9.2)
     */
    public IConnectionProfile getProfileByName( String profileName, File profileStoreFile, 
            String encryptedFileExtension )
        throws OdaException
    {
        // first load all profiles found in given storage file, if necessary
        IConnectionProfile[] profilesInFile = loadProfiles( profileStoreFile, encryptedFileExtension );

        for( int i = 0; i < profilesInFile.length; i++ ) 
        {
            if( matchesProfileName( profilesInFile[i].getName(), profileName ) )
                return createOdaWrapper( profilesInFile[i] );   // a match
        }
        return null;    // no match is found
    }

    private boolean matchesProfileName( String profileName, String nameToMatch )
    {
        return profileName.equals( nameToMatch );
    }
    
    /**
     * Find and returns the connection profile instance whose name is specified 
     * in the specified connection properties.
     * The profile store file object may be provided in the given context,
     * or its file path is specified in the connection properties.
     * <br>This method delegates to the default property provider to get the external profile
     * store reference from the specified properties and application context.  
     * Clients with custom IPropertyProvider implementation should obtain own profile reference, 
     * for use as arguments to {@link #getProfileByName(String, File)}.
     * @param dataSourceDesignProps connection properties specified in a data source design
     * @param appContext      an application context Map passed thru to a connection,
     *                        and which may contain the IPropertyProvider.ODA_CONN_PROP_CONTEXT key
     *                        and corresponding connection property context object as value.
     *                        If no such nested context is found, this application context Map is used
     *                        as the connection property context.
     *                        Optional; may be null.
     * @return  the matching profile instance, or null if not found
     * @since DTP 1.6
     * @see #getProfileByName(String, File)
     */
    public IConnectionProfile getProfileByName( 
            Properties dataSourceDesignProps, Object appContext )
    {
        // use the nested context for a profile store File object, if exists
        Object connPropContext = ProviderUtil.getConnectionPropertyContext( appContext );
        if( connPropContext == null )
            connPropContext = appContext;
        
        // delegates to the default property provider to get the profile reference; 
        // re-use previously loaded profiles, if exists; client may override default re-use behavior 
        // by explicitly calling #refresh() earlier, or trigger the default property provider to refresh 
        // by setting the IPropertyProvider.ODA_RELOAD_PROFILE_STORE key entry in the 
        // connection property context
        ProfilePropertyProviderImpl profileProvider = new ProfilePropertyProviderImpl();
        profileProvider.setRefreshProfileStore( false );
        return createOdaWrapper( 
                profileProvider.getConnectionProfile( dataSourceDesignProps, connPropContext ));       
    }

    private OdaConnectionProfile createOdaWrapper( IConnectionProfile wrappedProfile )
    {
        if( wrappedProfile == null )
            return null;	// nothing to wrap
        
        if( wrappedProfile instanceof OdaConnectionProfile )
        {
            if( ((OdaConnectionProfile) wrappedProfile).hasWrappedProfile() )
                return (OdaConnectionProfile) wrappedProfile;	// already a wrapper
        }
        return new OdaConnectionProfile( wrappedProfile ) ;
    }
    
    /**
     * Verifies if the specified connection profile name is already used among 
     * those profile instances that are cached and managed by the DTP Connection Profile Manager.
     * @param profileName   unique identifier of a DTP connection profile
     * @return  true if the name is already used; false otherwise
     * @since 3.2 (DTP 1.7)
     */
    public static boolean isProfileNameUsed( String profileName )
    {
        return OdaProfileFactory.isProfileNameUsed( profileName );
    }
    
    /**
     * Creates a new transient profile instance based on the specified data source profile id 
     * and connection properties.
     * @param odaDataSourceId   an ODA data source id as specified in an oda.dataSource extension
     * @param profileProperties    connection properties to be stored as profile properties
     * @return  a new instance of {@link IConnectionProfile} that are non persistent
     * @throws OdaException
     * @since 3.2.1 (DTP 1.7.2)
     */
    public IConnectionProfile createTransientProfile( String odaDataSourceId, 
            Properties profileProperties )
        throws OdaException
    {
        String profileProviderId = odaDataSourceId;
        
        // if referencing an existing profile instance, should use its current direct provider id
        IConnectionProfile referencedProfile = getProfileByName( profileProperties, null );
        if( referencedProfile instanceof OdaConnectionProfile )
            profileProviderId = ((OdaConnectionProfile)referencedProfile).getDirectProviderId();

        return OdaProfileFactory.createTransientProfile( profileProviderId, profileProperties );
    }

}
