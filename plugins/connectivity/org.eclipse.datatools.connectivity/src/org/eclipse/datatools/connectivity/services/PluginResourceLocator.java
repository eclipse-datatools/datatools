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

package org.eclipse.datatools.connectivity.services;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.connectivity.internal.services.PluginResourceLocatorImpl;

/**
 *  Locator of plug-in entries and resources.
 *  It supports locating entries and service resources in 
 *  both Eclipse OSGi and OSGi-less platforms.
 *  @since 1.9
 */
public class PluginResourceLocator
{
    
    /**
     * Returns a IPath to the root entry of the plugin-in 
     * that contributes the specified configuration element.
     * @param configElement configuration element 
     * @return  A IPath to the plug-in root entry, or null if not found
     */
    public static IPath getPluginRootPath( IConfigurationElement configElement )
    {
        return PluginResourceLocatorImpl.getPluginRootPath( configElement );
    }

    /**
     * Returns a IPath to the root entry of the specified plug-in.
     * @param pluginId    unique identifier of a plug-in or bundle
     * @return  A IPath to the plug-in root entry, or null if not found
     */
    public static IPath getPluginRootPath( String pluginId )
    {
        return PluginResourceLocatorImpl.getPluginRootPath( pluginId );
    }
    
    /**
     * Returns a IPath to the entry at the specified path in the specified plug-in.
     * @param pluginId    unique identifier of a plug-in or bundle
     * @param path  the path name of the entry
     * @return  A IPath to the entry, or null if no entry could be found
     */
    public static IPath getPluginEntryPath( String pluginId, String path )
    {
        return PluginResourceLocatorImpl.getPluginEntryPath( pluginId, path );
    }

    /**
     * Returns a URL to the entry at the specified path in the plugin-in 
     * that contributes the specified configuration element.
     * @param configElement configuration element 
     * @param path  the path name of the entry
     * @return  A URL to the entry, or null if no entry could be found
     */
    public static URL getPluginEntry( IConfigurationElement configElement, String path )
    {
        return PluginResourceLocatorImpl.getPluginEntry( configElement, path );
    }
    
    /**
     * Returns a URL to the entry at the specified path in the specified plug-in.
     * @param pluginId    unique identifier of a plug-in or bundle
     * @param path  the path name of the entry
     * @return  A URL to the entry, or null if no entry could be found 
     */
    public static URL getPluginEntry( String pluginId, String path )
    {
        return PluginResourceLocatorImpl.getPluginEntry( pluginId, path, null );
    }

    /**
     * Returns a URL to the specified resource in the specified plug-in. 
     * @param pluginId    unique identifier of a plug-in or bundle
     * @param name The name of the resource. See {@link ClassLoader#getResource(String)} 
     *              for a description of the format of a resource name.
     * @return A URL to the named resource, or null if the resource could not be found
     * @since 1.2.4 (DTP 1.9.2)
     */
    public static URL getResource( String pluginId, String name )
    {
        return PluginResourceLocatorImpl.getPluginResource( pluginId, name, null );
    }
    
    /**
     * Returns the location in the local file system of the plug-in state area 
     * for the specified plug-in. 
     * <br>The platform-defined state location is returned, if available.
     * Otherwise, returns the configurable workspace location of the specified plug-in. 
     * @param pluginId the unique, symbolic name of a plug-in or bundle
     * @return  A URL to the location path of the state area for the specified plug-in,
     *          or null if none is available
     * @see {@link #getConfigurableWorkspaceLocation(String)}
     */
    public static IPath getPluginStateLocation( String pluginId )
    {
        return PluginResourceLocatorImpl.getPluginStateLocation( pluginId );
    }
    
    /**
     * Returns the user configurable workspace location of the specified plug-in. 
     * It returns the value of the system property named "{pluginName}_workspacepath" 
     * if set, or the value of the environment variable under the same name.
     * <br>If no workspace path is specifically defined for the plug-in, 
     * return the path named with the plug-in namespace,
     * under a shared, parent workspace path.
     * The parent workspace path is the value of the system property 
     * named "org.eclipse.datatools_workspacepath" if set, or 
     * the value of the environment variable under the same name.
     * @param pluginId the unique, symbolic name of a plug-in or bundle
     * @return A URL to the absolute workspace location of the specified plug-in, 
     *          or null if no configurable workspace path is specified
     */
    public static URL getConfigurableWorkspaceLocation( String pluginId )
    {
        return PluginResourceLocatorImpl.getConfigurableWorkspaceLocation( pluginId );
    }
    
    /**
     * Converts a URL that uses a client-defined protocol into 
     * a URL that uses a protocol which is native to the Java class library (file, jar, http, etc). 
     * @param url   the original URL
     * @return  the resolved URL or the original if the protocol is unknown to the converter
     * @throws IOException  if unable to resolve URL, or if an error occurs during the resolution
     */
    public static URL resolve( URL url ) throws IOException
    {
        return PluginResourceLocatorImpl.resolve( url );
    }
    
    /**
     * Converts a URL that uses a user-defined protocol into a URL that uses the file
     * protocol. 
     * <p>
     * If the protocol for the given URL is not recognized by this converter, the original
     * URL is returned as-is.
     * @param url   the original URL
     * @return the converted file URL or the original URL passed in if it is 
     *          not recognized by this converter
     * @throws IOException if an error occurs during the conversion
     */
    public static URL toFileURL( URL url ) throws IOException 
    {
        return PluginResourceLocatorImpl.toFileURL( url );
    }
    
    /**
     * Returns a resource string corresponding to the given argument value. 
     * @param pluginId    unique identifier of a plug-in or bundle
     *           whose resource bundle is being queried
     * @param value the value to look for
     * @return  the resource string
     */
    public static String getResourceString( String pluginId, String value )
    {
        return PluginResourceLocatorImpl.getResourceString( pluginId, value );
    }
    
}
