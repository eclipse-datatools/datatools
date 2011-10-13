/*
 *************************************************************************
 * Copyright (c) 2007, 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.profile.provider;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider;
import org.eclipse.datatools.connectivity.oda.consumer.services.impl.ProviderUtil;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.oda.profile.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.util.manifest.ConnectionProfileProperty;
import org.eclipse.datatools.connectivity.services.PluginResourceLocator;

/**
 *  Provider of externalized properties defined in a linked connection profile.
 *  @since 3.0.4
 */
public class ProfilePropertyProviderImpl implements IPropertyProvider
{
    
    // logging variables
    private static final String sm_className = ProfilePropertyProviderImpl.class.getName();
    private static Logger sm_logger;
    
    private boolean m_refreshProfileStore = true;

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider#getDataSourceProperties(java.util.Properties, java.lang.Object)
     */
    public Properties getDataSourceProperties( Properties candidateProperties,
            Object appContext ) throws OdaException
    {
        final String methodName = "getDataSourceProperties(Properties,Object)"; //$NON-NLS-1$
        
        IConnectionProfile connProfile = 
            getConnectionProfile( candidateProperties, appContext );
        if( connProfile == null )   // no linked profile found
        {
            // a profile is specified, just couldn't find it
            if( hasProfileName( candidateProperties ) && hasProfileStoreFilePath( candidateProperties ) )
            {
                throw new OdaException( Messages.bind( Messages.propertyProvider_CANNOT_FIND_PROFILE,
                        getProfileName( candidateProperties ), getProfileStoreFilePath( candidateProperties ) ));
            }
            
            // no other effective data source properties; use the original ones
            return candidateProperties;   
        }
        
        // merges the 2 sets of properties; 
        // override candidate properties with those in profile instance
        
        Properties mergedProps = new Properties();
        mergedProps.putAll( candidateProperties );
                
        Properties profileProps = connProfile.getBaseProperties();
        if( profileProps != null )
            mergedProps.putAll( profileProps );

        // add a data source property entry for the resolved file path of the connection profile store 
        // used to get the profile instance
        File profileStore = getProfileStoreFile( candidateProperties, appContext );
        if( profileStore != null && profileStore.exists() )
        {
            String profileStoreResolvedPath = profileStore.getPath();
            if( ! profileStoreResolvedPath.equals( getProfileStoreFilePath( candidateProperties ) ))
                    mergedProps.setProperty( ConnectionProfileProperty.TRANSIENT_PROFILE_STORE_RESOLVED_PATH_PROP_KEY, 
                                            profileStoreResolvedPath );
        }
        
        // log count of merged properties
        if( getLogger().isLoggable( Level.FINER ) )
        {
            String logMsg = sm_className + "." + methodName + ": "; //$NON-NLS-1$ //$NON-NLS-2$
            logMsg += "Number of Candidate Properties = " + candidateProperties.size(); //$NON-NLS-1$
            logMsg += "; Number of Properties in profile = " + profileProps.size(); //$NON-NLS-1$
            logMsg += "; Number of Merged Effective Properties = " + mergedProps.size(); //$NON-NLS-1$
            getLogger().finer( logMsg );
        }
        
        return mergedProps;
    }

    /**
     * Finds and returns the connection profile
     * instance whose name is specified in the candidate connection properties.
     * The profile store file object may be provided in the connection property context,
     * or its file path is specified in the connection properties.
     * @param candidateProperties   local connection properties
     * @param connPropContext       connection property context object mapped to 
     *                        the IPropertyProvider.ODA_CONN_PROP_CONTEXT key
     *                        in an application context passed thru to a connection
     * @return  the connection profile instance if found; may be null 
     * @throws OdaException
     */
    public IConnectionProfile getConnectionProfile( Properties candidateProperties,
            Object connPropContext ) 
    {
        if( candidateProperties == null || candidateProperties.isEmpty() )
            return null;
        
        // first get the profile reference from the specified arguments
        
        String profileName = getProfileName( candidateProperties );
        if( profileName == null )
            return null;    // no external profile is specified in properties
        
        // determine the profile store file to use
        File profileStore = getProfileStoreFile( candidateProperties, connPropContext );
        
        // now get the referenced profile from the profile explorer
        IConnectionProfile profile = null;
        try
        {
            // if null profile store file is specified, the default profile store path is used
            profile = OdaProfileExplorer.getInstance()
                        .getProfileByName( profileName, profileStore );
        }
        catch( OdaException ex )
        {
            getLogger().warning( getStackTraceStrings( ex ) );
        }

        if( profile == null )
        {
            // log warning that no profile is found
            getLogger().warning( "No connection profile is found by its specified name: " + profileName ); //$NON-NLS-1$
        }
        
        return profile;
    }
    
    protected String getProfileName( Properties candidateProperties )
    {
        String profileName =
            candidateProperties.getProperty( ConnectionProfileProperty.PROFILE_NAME_PROP_KEY );
        if( profileName == null || profileName.length() == 0 )
            return null;    // no profile name specified
        return profileName;
    }

    private boolean hasProfileName( Properties candidateProperties )
    {
        String profileName = getProfileName( candidateProperties );
        return ( profileName != null );
    }
    
    /*
     * @since 3.2.3 (DTP 1.8)
     */
    private File getProfileStoreFile( Properties candidateProperties, Object connPropContext )
    {
        // a profile store mapping specified in the connPropContext map, if exists, 
        // takes precedence over the file path specified in the properties
        File profileStore = getProfileStoreFile( connPropContext );
        if( profileStore == null )
            profileStore = getProfileStoreFileFromProperties( candidateProperties, connPropContext );

        if( profileStore != null )
        {
            // using a new profile storage File object, good opportunity
            // to free up the cached profiles of previously loaded profile store File
            Boolean refreshProfileStore = 
                ProviderUtil.getReloadProfileStoreContextValue( connPropContext );
            
            // if IPropertyProvider.ODA_RELOAD_PROFILE_STORE key entry is not specified in the context,
            // applies the value specified by #setRefreshProfileStore(boolean)
            if( refreshProfileStore == null )   
                refreshProfileStore = Boolean.valueOf( m_refreshProfileStore ); 

            if( refreshProfileStore.booleanValue() )
                OdaProfileExplorer.getInstance().refresh();
        }
        
        return profileStore;
    }
    
    protected File getProfileStoreFile( Object connPropContext )
    {
        if( connPropContext == null || ! ( connPropContext instanceof Map ) )
            return null;
        
        Object propValue = 
            ( (Map<?,?>) connPropContext ).get( ConnectionProfileProperty.PROFILE_STORE_FILE_PROP_KEY );
        if( propValue == null )
            return null;    // no non-null mapping for profile store file
        
        if( ( propValue instanceof File ) && ((File) propValue ).exists() )
            return (File) propValue;

        // mapping contains a value of unexpected type
        getLogger().warning( "getProfileStoreFile( Object ): Ignoring the PROFILE_STORE_FILE_PROP_KEY object in Connection Property Context.  The specified object must be an existing File."  );  //$NON-NLS-1$
        return null;        
    }
    
    /**
     * Find and return the connection profile store file whose
     * file path is specified in the connection properties.
     * Ensures the file path exists at runtime; otherwise returns null.
     * @param candidateProperties
     * @return
     * @deprecated
     */
    protected File getProfileStoreFile( Properties candidateProperties )
    {
        return getProfileStoreFileFromProperties( candidateProperties, null );
    }
    
    private File getProfileStoreFileFromProperties( Properties candidateProperties, Object connPropContext )
    {
        if( ! hasProfileStoreFilePath( candidateProperties ) )
            return null;    // no profile file path specified
        String profileStoreFilePath = getProfileStoreFilePath( candidateProperties );
        File profileStoreFile = getAbsoluteProfileStoreFile( profileStoreFilePath, connPropContext );
        if( profileStoreFile != null )
            return profileStoreFile;
        
        // specified file path does not exist
        getLogger().warning( "getProfileStoreFileFromProperties(Properties,Object): " +   //$NON-NLS-1$
                        "The PROFILE_STORE_FILE_PATH_PROP_KEY value (" + profileStoreFilePath +  //$NON-NLS-1$
                        ") specified in connection properties does not exist in the file system." ); //$NON-NLS-1$
        return null;
    }

    private String getProfileStoreFilePath( Properties candidateProperties )
    {
        String profileStoreFilePath = 
            candidateProperties.getProperty( ConnectionProfileProperty.PROFILE_STORE_FILE_PATH_PROP_KEY );
        if( profileStoreFilePath == null || profileStoreFilePath.length() == 0 )
            return null;    // no profile store path specified
        return profileStoreFilePath;
    }
    
    private boolean hasProfileStoreFilePath( Properties candidateProperties )
    {
        String profileStoreFilePath = getProfileStoreFilePath( candidateProperties );
        return ( profileStoreFilePath != null );
    }
    
    /**
     * Converts the specified string representation of a file pathname
     * to its abstract representation.
     * @param filePath  the string representation of a file
     * @return  the abstract representation of a file pathname,
     *          or null if the specified argument is null, invalid or
     *          the file does not exist
     * @deprecated
     */
    protected File getProfileStoreFile( String filePath )
    {
        return getAbsoluteProfileStoreFile( filePath, null );
    }
    
    private File getAbsoluteProfileStoreFile( String filePath, Object connPropContext )
    {
        if( filePath == null || filePath.length() == 0 )
            return null;

       // First try to parse the filePath argument as file name
        File file = new File( filePath );
        if( file.exists() )
            return file;

        // try to resolve a relative file path, if defined
        File resolvedStoreFile = resolveRelativePath( filePath, connPropContext );
        if( resolvedStoreFile != null && resolvedStoreFile.exists() )
            return resolvedStoreFile;

        // next try to parse the filePath argument as an url on web
        try
        {
            URL url = new URL( filePath );
            return new File( PluginResourceLocator.toFileURL( url ).toURI() );
        }
        catch( MalformedURLException ex )
        {
            getLogger().fine( "getAbsoluteProfileStoreFile(String,Object): " + ex.toString() );  //$NON-NLS-1$
        }
        catch( URISyntaxException ex )
        {
            getLogger().fine( "getAbsoluteProfileStoreFile(String,Object): " + ex.toString() );  //$NON-NLS-1$
        }
        catch( IOException ex )
        {
            getLogger().warning( "getAbsoluteProfileStoreFile(String,Object): " + ex.toString() );  //$NON-NLS-1$
        }
        return null;        
    }

    private File resolveRelativePath( String filePath, Object connPropContext )
    {
        if( connPropContext == null || ! ( connPropContext instanceof Map ) )
            return null;
        
        Object resourceIdentifiersObj = 
            ( (Map<?,?>) connPropContext ).get( ResourceIdentifiers.ODA_APP_CONTEXT_KEY_CONSUMER_RESOURCE_IDS );
        if( resourceIdentifiersObj == null )   // not available
        {
            getLogger().warning( Messages.bind( Messages.propertyProvider_NO_RESOURCE_IDENTIFIERS,
                    filePath ) );
            return null;    // no resource URI locator to resolve relative filePath
        }

        URI fileURI = ResourceIdentifiers.encodeToURI( filePath );
        URI resolvedFilePathURI = 
            ResourceIdentifiers.resolveApplResource( resourceIdentifiersObj, fileURI );
        if( resolvedFilePathURI != null && 
            ! resolvedFilePathURI.equals( fileURI ) )
        {
            return new File( resolvedFilePathURI );
        }
        
        getLogger().warning( Messages.bind( Messages.propertyProvider_UNABLE_TO_RESOLVE_PATH,
                new Object[]{
                resourceIdentifiersObj.getClass().getName(), 
                fileURI,
                ResourceIdentifiers.getApplResourceBaseURI( resourceIdentifiersObj ) } ));   
        return null;
    }

    /**
     * Internal method to control whether the call to {@link #getConnectionProfile(Properties, Object)}
     * should re-use cached profiles previously loaded from profile stores.
     * @param   refreshProfileStore <code>true</code> to refresh and get latest profile instances in a 
     *              profile storage file, <code>false</code> to re-use profiles previously 
     *              loaded from profile stores; default setting is <code>true</code>.
     */
    public void setRefreshProfileStore( boolean refreshProfileStore )
    {
        m_refreshProfileStore = refreshProfileStore;
    }

    /**
     * Returns the class logger.
     */
    private static Logger getLogger()
    {
        if( sm_logger == null )
        {
            synchronized( ProfilePropertyProviderImpl.class )
            {
                if( sm_logger == null )
                    sm_logger = Logger.getLogger( sm_className );
            }
        }
        return sm_logger;
    }
    
    /**
     * Formats and returns the exception's stack trace as a string.
     */
    private static String getStackTraceStrings( Throwable ex )
    {
        String logMsg = ex.toString() + "\n"; //$NON-NLS-1$
        Throwable cause = ( ex.getCause() != null ) ? ex.getCause() : ex;
        StackTraceElement[] stacks = cause.getStackTrace();
        for( int i = 0; i < stacks.length; i++ )
        {
            logMsg += stacks[i].toString() + "\n"; //$NON-NLS-1$
        }
        return logMsg;
    }
    
}
