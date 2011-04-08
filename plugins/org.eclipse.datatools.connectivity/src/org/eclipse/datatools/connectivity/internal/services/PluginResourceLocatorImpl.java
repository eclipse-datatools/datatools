/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation (Bugzilla 338997)
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.internal.services;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;

/**
 *  Internal implementation class of the
 *  {@link org.eclipse.datatools.connectivity.services.PluginResourceLocator}
 *  to locate plug-in entries and resources.
 *  <br>It delegates to the appropriate service to locate entries and resources,
 *  depending on whether this is running on the Eclipse OSGi or OSGi-less platform.
 *  @since 1.9
 */
public class PluginResourceLocatorImpl
{
    static final String PLUGIN_ROOT_PATH = "/"; //$NON-NLS-1$
    private static final String WORKSPACE_PATH_SUFFIX = "_workspacepath"; //$NON-NLS-1$
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
    private static final String DATATOOLS_NAMESPACE = "org.eclipse.datatools"; //$NON-NLS-1$
    
    private static ResourceLocatorDelegate sm_delegate;

    private static ResourceLocatorDelegate getDelegate()
    {
        if( sm_delegate == null )
        {
            // first try use OSGi platform bundle service, if available
            ResourceLocatorDelegate delegate = ConnectivityPlugin.isRunningOSGiPlatform() ? 
                            new BundleResourceLocator() :
                            new ClassLoaderResourceLocator();

            synchronized( PluginResourceLocatorImpl.class )
            {
                if( sm_delegate == null )
                    sm_delegate = delegate;
            }
        }
        return sm_delegate;
    }
    
    /**
     * Implementation of
     * {@link org.eclipse.datatools.connectivity.services.PluginResourceLocator#getPluginRootPath(IConfigurationElement)}
     */
    public static IPath getPluginRootPath( IConfigurationElement configElement )
    {
        return adaptToPath( getPluginEntry( configElement, PLUGIN_ROOT_PATH ) );
    }

    /**
     * Implementation of
     * {@link org.eclipse.datatools.connectivity.services.PluginResourceLocator#getPluginRootPath(String)}
     */
    public static IPath getPluginRootPath( String pluginId )
    {
        return adaptToPath( getPluginEntry( pluginId, PLUGIN_ROOT_PATH, null ) );
    }
    
    /**
     * Implementation of
     * {@link org.eclipse.datatools.connectivity.services.PluginResourceLocator#getPluginEntryPath(String, String)}
     */
    public static IPath getPluginEntryPath( String pluginId, String path )
    {
        return adaptToPath( getPluginEntry( pluginId, path, null ) );
    }

    /**
     * Implementation of
     * {@link org.eclipse.datatools.connectivity.services.PluginResourceLocator#getPluginEntry(IConfigurationElement, String)}
     */
    public static URL getPluginEntry( IConfigurationElement configElement, String path )
    {
        String bundleName = configElement.getContributor().getName();
        return getPluginEntry( bundleName, path, configElement.getClass().getClassLoader() );
    }
    
    /**
     * Returns a URL to the entry at the specified path in the plug-in
     * from the specified class loader.
     * @param pluginId    unique identifier of a plug-in or bundle
     * @param path  the path name of the entry
     * @param cl    the class loader to search for the specified entry when
     *              the OSGi platform bundle service is not available; 
     *              may be null to use this class' loader. 
     * @return  A URL to the entry, or null if no entry could be found 
     */
    public static URL getPluginEntry( String pluginId, String path, ClassLoader cl )
    {
        return getDelegate().findPluginEntry( pluginId, path, cl );
    }
    
    /**
     * Returns the specified resource in the plugin from the specified class loader. 
     * @param pluginId  unique identifier of a plug-in or bundle 
     * @param name  The name of the resource. See {@link ClassLoader#getResource(String)} 
     *              for a description of the format of a resource name.
     * @param cl    the class loader to search for the specified resource when
     *              the OSGi platform bundle service is not available; 
     *              may be null to use this class' loader. 
     * @return  A URL to the named resource, or null if the resource could not be found 
     */
    public static URL getPluginResource( String pluginId, String name, ClassLoader cl )
    {
        return getDelegate().findPluginResource( pluginId, name, cl );
    }
    
    static IPath adaptToPath( URL pluginRoot )
    {
        if( pluginRoot == null ) 
            return null;
        
        try
        {
            pluginRoot = resolve( pluginRoot );

            String path = pluginRoot.getPath();
            if( path.endsWith("!/") )  //$NON-NLS-1$
            {
                path = path.substring(0, path.length() - 2);
                pluginRoot = new URL( path );
            }

            pluginRoot = toFileURL( pluginRoot );
        }
        catch( IOException ex )
        {
            // log and ignore
            ConnectivityPlugin.getDefault().logWarning( 
                    ConnectivityPlugin.getDefault().getResourceString( 
                            "PluginResourceLocator.invalidURL", //$NON-NLS-1$
                            new Object[]{ pluginRoot, ex.getMessage() }));
        }

        return new Path( pluginRoot.getFile() ).removeTrailingSeparator();
    }

    /**
     * Implementation of
     * {@link org.eclipse.datatools.connectivity.services.PluginResourceLocator#getPluginStateLocation(String)}.
     * @see {@link #getConfigurableWorkspaceLocation(String)}
     */
    public static IPath getPluginStateLocation( String pluginId )
    {
        return getDelegate().findPluginStateLocation( pluginId );
    }
    
    static IPath getConfigurableWorkspacePath( String pluginId )
    {
        URL wsURL = getConfigurableWorkspaceLocation( pluginId );
        return wsURL != null ? new Path( wsURL.getPath() ).removeTrailingSeparator() : null;
    }
    
    /**
     * Implementation of
     * {@link org.eclipse.datatools.connectivity.services.PluginResourceLocator#getConfigurableWorkspaceLocation(String)}
     */
    public static URL getConfigurableWorkspaceLocation( String pluginId )
    {
        String wsLocKey = pluginId + WORKSPACE_PATH_SUFFIX;
        String value = null;
        try
        {
            // first check if the key is set in system property
            value = System.getProperty( wsLocKey );
        }
        catch( SecurityException  ex )
        {
            // log  and ignore
            ConnectivityPlugin.getDefault().logInfo( ex.getMessage() );
        }

        // if the system property value is not set, then try the key in an environment variable name
        if( value == null )
        {
            value = System.getenv( wsLocKey );
            
            // if no system property or environment variable defined for specified pluginId
            if( value == null )
            {
                if( pluginId.equals( DATATOOLS_NAMESPACE ) )
                    return null;
                // try get the shared parent workspace location
                URL commonWsLoc = getConfigurableWorkspaceLocation( DATATOOLS_NAMESPACE );
                if( commonWsLoc == null )
                    return null;    // no workspace path defined for specified pluginId

                // append specified pluginId to the parent workspace location
                IPath commonWsPath = new Path( commonWsLoc.getPath() );
                value = commonWsPath.append( pluginId ).toString();
            }
        }
        
        // handles relative path to the plug-in installation folder
        if( ! new File( value ).isAbsolute() )
        {
            IPath pluginLoc = getPluginRootPath( pluginId );
            if( pluginLoc != null )
                value = pluginLoc.append( value ).toString();
        }

        return encode( value );
    }
    
    private static URL encode( String location )
    {
        //  encode non-US-ASCII characters in specified path into an URI
        try
        {
            // use URI encoding implementation
            String encodedLocation = new File( location ).toURI( ).toASCIIString( );
            String target =  new File( EMPTY_STRING ).toURI( ).toASCIIString( );
            // strip out the interim root path added by the file conversion
            encodedLocation = encodedLocation.replace( target, EMPTY_STRING );
            return new URI( encodedLocation ).toURL();
        }
        catch( Exception ex )
        {
            ConnectivityPlugin.getDefault().logWarning( ex.getMessage() );
        }
        return null;
    }
    
    /**
     * Implementation of
     * {@link org.eclipse.datatools.connectivity.services.PluginResourceLocator#getResourceString(String, String)}.
     */
    public static String getResourceString( String pluginId, String value )
    {
        String resourceString = getDelegate().findResourceString( pluginId, value );
        return resourceString != null ? resourceString :
                Platform.getResourceString( null, value );    // return the default value
    }

    /**
     * Implementation of
     * {@link org.eclipse.datatools.connectivity.services.PluginResourceLocator#resolve(URL)}.
     */
    public static URL resolve( URL url ) throws IOException
    {
        return getDelegate().resolveURL( url );
    }
    
    /**
     * Implementation of
     * {@link org.eclipse.datatools.connectivity.services.PluginResourceLocator#toFileURL(URL)}.
     */
    public static URL toFileURL( URL url ) throws IOException 
    {
        return getDelegate().toFileURL( url );
    }
    
}
