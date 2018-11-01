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

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.osgi.framework.Bundle;

/**
 *  Internal implementation that uses Eclipse OSGi bundle services to locate
 *  bundle entries and resources running on OSGi platform.
 *  @since 1.9
 */
class BundleResourceLocator extends ResourceLocatorDelegate
{
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#findPluginEntry(java.lang.String, java.lang.String, java.lang.ClassLoader)
     */
    URL findPluginEntry( String pluginId, String path, ClassLoader cl )
    {
        Bundle bundle = Platform.getBundle( pluginId );
        if( bundle == null )
            return null;
          
        URL entryURL = bundle.getEntry( path );
        if( entryURL == null )
            return null;
        try
        {
            return resolveURL( entryURL );
        }
        catch( IOException e )
        {
            ConnectivityPlugin.getDefault().log( e );
        }        
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#findPluginResource(java.lang.String, java.lang.String, java.lang.ClassLoader)
     */
    URL findPluginResource( String pluginId, String name, ClassLoader cl )
    {
        Bundle bundle = Platform.getBundle( pluginId );
        return bundle != null ? bundle.getResource( name ) : null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#findResourceString(java.lang.String, java.lang.String)
     */
    String findResourceString( String pluginId, String value )
    {
        Bundle bundle = Platform.getBundle( pluginId );
        return bundle != null ? Platform.getResourceString( bundle, value ) : null;
    }
 
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#findPluginStateLocation(java.lang.String)
     */
    IPath findPluginStateLocation( String pluginId )
    {
        Bundle bundle = Platform.getBundle( pluginId );
        if( bundle == null )
            return null;
          
        IPath stateLocation = Platform.getStateLocation( bundle ); // get specified plugin's state location
        return stateLocation;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#resolveURL(java.net.URL)
     */
    URL resolveURL( URL url ) throws IOException
    {
        return FileLocator.resolve( url );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.services.ResourceLocatorDelegate#toFileURL(java.net.URL)
     */
    URL toFileURL( URL url ) throws IOException
    {
        return FileLocator.toFileURL( url );        
    }
    
}
