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

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.IPath;

/**
 *  Internal abstract base class for implementation delegated 
 *  by {@link PluginResourceLocatorImpl} to locate plug-in entries and resources.
 *  @since 1.9
 */
abstract class ResourceLocatorDelegate
{
    /**
     * Find the entry at the specified path in the plug-in
     * from the specified class loader.
     * @param pluginId    unique identifier of a plug-in or bundle
     * @param path  the path name of the entry
     * @param cl    the class loader to search for the specified entry when
     *              the OSGi platform bundle service is not available; 
     *              may be null to use this class' loader. 
     * @return  A URL to the entry, or null if no entry could be found 
     */
    abstract URL findPluginEntry( String pluginId, String path, ClassLoader cl );
    
    /**
     * Find the specified resource in the plugin from the specified class loader. 
     * @param pluginId  unique identifier of a plug-in or bundle 
     * @param name  The name of the resource. See {@link ClassLoader#getResource(String)} 
     *              for a description of the format of a resource name.
     * @param cl    the class loader to search for the specified resource when
     *              the OSGi platform bundle service is not available; 
     *              may be null to use this class' loader. 
     * @return  A URL to the named resource, or null if the resource could not be found 
     */
    abstract URL findPluginResource( String pluginId, String name, ClassLoader cl );
    
    /**
     * Find the location in the local file system of the plug-in state area 
     * for the specified plug-in. 
     * <br>The platform-defined state location is returned, if available.
     * Otherwise, returns the configurable workspace location of the specified plug-in. 
     * @param pluginId the unique, symbolic name of a plug-in or bundle
     * @return  A URL to the location path of the state area for the specified plug-in,
     *          or null if none is available
     */
    abstract IPath findPluginStateLocation( String pluginId );
    
    /**
     * Find the resource string corresponding to the given argument value. 
     * @param pluginId    unique identifier of a plug-in or bundle
     *           whose resource bundle is being queried
     * @param value the value to look for
     * @return  the resource string
     */
    abstract String findResourceString( String pluginId, String value );
    
    /**
     * Converts a URL that uses a client-defined protocol into 
     * a URL that uses a protocol which is native to the Java class library (file, jar, http, etc). 
     * @param url   the original URL
     * @return  the resolved URL or the original if the protocol is unknown to the converter
     * @throws IOException  if unable to resolve URL, or if an error occurs during the resolution
     */
    abstract URL resolveURL( URL url ) throws IOException;
    
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
    abstract URL toFileURL( URL url ) throws IOException;        

}
