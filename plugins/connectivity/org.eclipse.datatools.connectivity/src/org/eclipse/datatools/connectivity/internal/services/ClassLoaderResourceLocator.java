/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.internal.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;


/**
 *  Internal implementation that uses class loader services to locate
 *  entries and resources of plug-ins running on OSGi-less platform.
 *  @since 1.9
 */
class ClassLoaderResourceLocator extends ResourceLocatorDelegate
{
    private static final String PLUGIN_VERSION_SEPARATOR = "_"; //$NON-NLS-1$
    private static final String BUNDLE_MANIFEST_RELATIVE_PATH = "META-INF/MANIFEST.MF"; //$NON-NLS-1$
    private static final String UPPER_RELATIVE_PATH = ".."; //$NON-NLS-1$
    private static final String JAR_FILE_EXTENSION = ".jar"; //$NON-NLS-1$
    private static final String RESOURCE_KEY_PREFIX = "%"; //$NON-NLS-1$
    private static final String RESOURCE_KEY_DOUBLE_PREFIX = "%%"; //$NON-NLS-1$ 
    
    private static Map<ClassLoader,Map<String,URL>> sm_loadedPluginLocs = 
        Collections.synchronizedMap(new HashMap<ClassLoader,Map<String,URL>>());
    private static URL notLoadedURL = getNotLoadedURL();       
    private static IPath datatoolsInstallPath;

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#findPluginEntry(java.lang.String, java.lang.String, java.lang.ClassLoader)
     */
    URL findPluginEntry( String pluginId, String path, ClassLoader cl )
    {
        return getLoadedPluginEntry( pluginId, path, cl );
    }
    
    private static URL getLoadedPluginEntry( String pluginId, String path, ClassLoader cl )
    {
        if( cl == null )
            cl = ClassLoaderResourceLocator.class.getClassLoader();

        if( path.equals( PluginResourceLocatorImpl.PLUGIN_ROOT_PATH ) )    // looking for the root of a plug-in
            return getLoadedPluginLoc( pluginId, cl );

        if( path.endsWith( JAR_FILE_EXTENSION ) )   // looking for a jar file entry
            return getLoadedJarEntry( pluginId, path, cl );
        
        return findLoadedResources( pluginId, path, cl );
    }
    
    /**
     * Return the URL of the root entry of the specified plug-in, using the 
     * specified class loader.
     * @param pluginId    unique identifier of a plug-in or bundle 
     * @param cl    the class loader to be used to search
     * @return  the root entry URL of the specified plug-in
     */
    private static URL getLoadedPluginLoc( String pluginId, ClassLoader cl )
    {
        // first check the cached Map to optimize repeated search for plugin location 
        Map<String,URL> loaderPluginLocs = sm_loadedPluginLocs.get( cl );
        if( loaderPluginLocs == null || loaderPluginLocs.get( pluginId ) == null )
        {
            // not in cached Map, perform the search
            URL pluginLoc = findLoadedPluginLoc( pluginId, cl );
            
            if( loaderPluginLocs == null )
            {
                synchronized( ClassLoaderResourceLocator.class )
                {
                    // re-check the latest cached Map, which might have been updated since
                    loaderPluginLocs = sm_loadedPluginLocs.get( cl );
                    if( loaderPluginLocs == null )
                    {
                        loaderPluginLocs = Collections.synchronizedMap(new HashMap<String,URL>());
                        sm_loadedPluginLocs.put( cl, loaderPluginLocs );
                    }
                }
            }
            loaderPluginLocs.put( pluginId, pluginLoc );
        }
        
        URL cachedPluginLoc = loaderPluginLocs.get( pluginId );
        return notLoadedURL.equals( cachedPluginLoc ) ? null : cachedPluginLoc;
    }
    
    private static URL findLoadedPluginLoc( String pluginId, ClassLoader cl )
    {
        // search for the plugin manifest file, which should always exist 
        // at one level below the plugin path
        URL manifestURL = getLoadedPluginEntry( pluginId, BUNDLE_MANIFEST_RELATIVE_PATH, cl );
        if( manifestURL == null )
            return notLoadedURL;
        
        try
        {
            return new URL( manifestURL, UPPER_RELATIVE_PATH );
        }
        catch( MalformedURLException ex )
        {
            ConnectivityPlugin.getDefault().logWarning( ex.getMessage() );
        }
        return notLoadedURL;
    }
    
    private static URL findLoadedResources( String pluginId, String path, ClassLoader cl )
    {
        // looking for a resource entry, use the class loader to search
        Enumeration<URL> foundURLs;
        try
        {
            foundURLs = cl.getResources( path );
        }
        catch( IOException e )
        {
            ConnectivityPlugin.getDefault().log( e );
            return null;
        }
        
        // locate the entries that are found in the specified plug-in
        ArrayList<URL> matchingEntries = new ArrayList<URL>(2);
        String pluginNameFragment = pluginId + PLUGIN_VERSION_SEPARATOR;
        while( foundURLs.hasMoreElements() )
        {
            URL resourceURL = foundURLs.nextElement();
            if( resourceURL.getPath().contains( pluginNameFragment ) )
                matchingEntries.add( resourceURL );
        }

        if( matchingEntries.isEmpty() )
            return null;        
        if( matchingEntries.size() == 1 )
            return matchingEntries.get(0);
        
        // multiple entries matching the specified path are found
        
        // filter to include only those entries, if any, found under the same parent path as this
        if( ! pluginId.equals( ConnectivityPlugin.PLUGIN_ID ) )
        {
            ArrayList<URL> installPathEntries = new ArrayList<URL>(matchingEntries.size());
            String installPath = getDatatoolsInstallPath( cl ).toString();
            for( URL entry : matchingEntries )
            {
                String entryFilePath = entry.getPath();
                if( entryFilePath.contains( installPath ) )
                    installPathEntries.add( entry );
            }
            if( installPathEntries.size() >= 1 )
            {
                if( installPathEntries.size() == 1 )
                    return installPathEntries.get(0);
                matchingEntries = installPathEntries; // include only these entries as candidates
            }
        }
        
        // return the entry at the upper-most level among the multiple entries found
        URL upperLevelEntry = null;
        int upperEntrySegments = 0;
        for( URL entry : matchingEntries )
        {
            int entrySegments = (new Path(entry.getPath())).segmentCount();
            if( upperLevelEntry == null || entrySegments < upperEntrySegments )
            {
                upperLevelEntry = entry;
                upperEntrySegments = entrySegments;
            }
        }
        return upperLevelEntry;
    }
    
    private static IPath getDatatoolsInstallPath( ClassLoader cl )
    {
        if( datatoolsInstallPath == null )
        {
            IPath foundPath = getParentInstallPath( ConnectivityPlugin.PLUGIN_ID, 
                                getLoadedPluginLoc( ConnectivityPlugin.PLUGIN_ID, cl ) );
            synchronized( ClassLoaderResourceLocator.class )
            {
                datatoolsInstallPath = foundPath;
            }
        }
        return datatoolsInstallPath;
    }
    
    /* 
     * Find and returns the parent path of the Connectivity plugin.
     */
    private static IPath getParentInstallPath( String pluginId, URL pluginLoc )
    {
        String pluginNameFragment = pluginId + PLUGIN_VERSION_SEPARATOR;
        IPath pluginPath = PluginResourceLocatorImpl.adaptToPath( pluginLoc );
        String[] pathSegments = pluginPath.segments();
        for( int i=0; i < pathSegments.length; i++ )
        {
            if( pathSegments[i].startsWith( pluginNameFragment ) )
            {
                return pluginPath.uptoSegment( i ); // return up to the parent segment
            }
        }
        return null;
    }
    
    private static URL getLoadedJarEntry( String pluginId, String jarFilePath, ClassLoader cl )
    {
        URL pluginLoc = getLoadedPluginLoc( pluginId, cl );
        if( pluginLoc == null ) // could not find specified plugin
        {
            // try find the jarFilePath resource entry directly
            pluginLoc = findLoadedResources( pluginId, jarFilePath, cl );
            if( pluginLoc == null )
                return null;    // no jar entry available
        }

        // check if the plugin URL is the jarFilePath entry itself, 
        // which contains an embedded manifest.mf file
        IPath pluginPath = PluginResourceLocatorImpl.adaptToPath( pluginLoc );
        if( pluginPath.lastSegment().equalsIgnoreCase( jarFilePath ))
            return pluginLoc;

        // check if the jarFilePath exists under the plugin root path
        File jarFile = pluginPath.append( jarFilePath ).toFile();
        if( ! jarFile.exists() )
            return null;
        
        try
        {
            return jarFile.toURL();
        }
        catch( MalformedURLException e )
        {
            ConnectivityPlugin.getDefault().logInfo( e.getMessage() );
        }
        return null;
    }
    
    private static URL getNotLoadedURL()
    {
        if( notLoadedURL == null )
        {
            synchronized( ClassLoaderResourceLocator.class )
            {
                try
                {
                    if( notLoadedURL == null )
                        notLoadedURL = new URL( "file:/" ); //$NON-NLS-1$
                }
                catch( MalformedURLException e )
                {}
            }
        }
        return notLoadedURL;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#findPluginResource(java.lang.String, java.lang.String, java.lang.ClassLoader)
     */
    URL findPluginResource( String pluginId, String name, ClassLoader cl )
    {
        return getLoadedPluginEntry( pluginId, name, cl );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#findResourceString(java.lang.String, java.lang.String)
     */
    String findResourceString( String pluginId, String value )
    {
        // adopt similar implementation as org.eclipse.core.internal.runtime.ResourceTranslator, but
        // without OSGi bundle service 
        String s = value.trim();
        if( !s.startsWith( RESOURCE_KEY_PREFIX, 0 ) )
            return s;
        if( s.startsWith( RESOURCE_KEY_DOUBLE_PREFIX, 0 ) )
            return s.substring( 1 );

        int idx = s.indexOf( ' ' );
        String key = idx == -1 ? 
                        s.substring( RESOURCE_KEY_PREFIX.length() ) : 
                        s.substring( RESOURCE_KEY_PREFIX.length(), idx );
        String defaultValue = idx == -1 ? s : s.substring( idx + 1 );

        // find the plugin properties file, and read its content for the value of the specified key
        URL pluginPropURL = findLoadedResources( pluginId, "plugin.properties",  //$NON-NLS-1$
                                this.getClass().getClassLoader() );      
        if( pluginPropURL == null )
            return defaultValue;

        Properties pluginProps = new Properties();
        try
        {
            pluginProps.load( pluginPropURL.openStream() );
        }
        catch( IOException e )
        {
            // log and ignore
            ConnectivityPlugin.getDefault().logInfo( e.getMessage() );
            return defaultValue;
        }

        return pluginProps.getProperty( key, defaultValue );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#findPluginStateLocation(java.lang.String)
     */
    IPath findPluginStateLocation( String pluginId )
    {
        // get location from configurable variable specified by consumer application
        return PluginResourceLocatorImpl.getConfigurableWorkspacePath( pluginId );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#resolveURL(java.net.URL)
     */
    URL resolveURL( URL url ) throws IOException
    {
        // return the original URL as-is
        return url;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#toFileURL(java.net.URL)
     */
    URL toFileURL( URL url ) throws IOException
    {
        // return the original URL as-is
        return url;
    }
    
}
