/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.profile;

import java.util.logging.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.profile.nls.Messages;

/**
 * Provides helper methods for loading and processing extensions that implement
 * the org.eclipse.datatools.connectivity.oda.profile.profileStore of extension point.
 * @since 3.2.7 (DTP 1.9.2)
 */
public class ProfileFileExtension 
{
    public static final String FILE_EXT_SEPARATOR = "."; //$NON-NLS-1$
    
    private static final String PROFILE_STORE_EXT_POINT = "org.eclipse.datatools.connectivity.oda.profile.profileStore"; //$NON-NLS-1$
    private static final String FILE_EXT_ELEMENT = "fileExtension"; //$NON-NLS-1$
    private static final String DEFAULT_ATTRIBUTE = "default"; //$NON-NLS-1$
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$

    private static String sm_defaultFileExtension;
    // logging variables
    private static final String sm_className = ProfileFileExtension.class.getName();
    
    /**
     * Returns the default file extension specified in an extension.
     * The first extension found will be applied.
     * @return the default file extension; or null if no valid extension exists
     */
    public static String getDefault()
    {
        if( sm_defaultFileExtension == null )
        {
            synchronized( ProfileFileExtension.class )
            {
                if( sm_defaultFileExtension == null )
                    sm_defaultFileExtension = loadDefaultFileExtension();
            }
        }
        return sm_defaultFileExtension;
    }
    
    /**
     * Convenience method to indicate whether the specified defaultFileExtension exists
     * and can be applied to a profile store file.
     * @param defaultFileExtension a default file extension, normally returned by {@link #getDefault()}
     * @return true if the specified defaultFileExtension can be applied to a profile store file;
     *         false otherwise
     */
    public static boolean exists( String defaultFileExtension )
    {
        return defaultFileExtension != null && defaultFileExtension.trim().length() > 0;
    }
    
    private static String loadDefaultFileExtension()
    {
        IExtensionRegistry extReg = Platform.getExtensionRegistry( );
        IExtensionPoint extPoint = extReg.getExtensionPoint( PROFILE_STORE_EXT_POINT );
        if ( extPoint == null )
            return EMPTY_STRING;

        IExtension[] extensions = extPoint.getExtensions( );
        if( extensions.length < 1 )
            return EMPTY_STRING;    // no extension is found in registry

        if( extensions.length > 1 )
        {
            // log that multiple extensions are found, but only one will get used
            String warningMsg = Messages.bind( Messages.profileFileExtension_MULTIPLE_EXTENSIONS_FOUND,
                                                PROFILE_STORE_EXT_POINT );
            Logger.getLogger( sm_className ).warning( warningMsg );
        }
        
        for( int i = 0; i < extensions.length; i++ )
        {
            IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
            if( configElements.length < 1 )
                continue;   // skip this extension with no configuration element

            for ( int j = 0; j < configElements.length; j++ )
            {
                IConfigurationElement configElement = configElements[j];
                if( ! configElement.getName().equals( FILE_EXT_ELEMENT ) )
                    continue;   // skip

                String defaultFileExt = configElement.getAttribute( DEFAULT_ATTRIBUTE ); 
                if ( defaultFileExt != null )
                {
                    defaultFileExt = defaultFileExt.trim();
                    // strips out any leading file extension separator
                    if( defaultFileExt.startsWith( FILE_EXT_SEPARATOR ) )
                        defaultFileExt = defaultFileExt.substring( FILE_EXT_SEPARATOR.length() );

                    if( defaultFileExt.length() > 0 )
                    {
                        Logger.getLogger( sm_className ).info( 
                                Messages.bind( Messages.profileFileExtension_APPLIED_DEFAULT_FILE_EXT, 
                                                new Object[]{ defaultFileExt, PROFILE_STORE_EXT_POINT, 
                                                                extensions[i].getContributor().getName()} ));
                        return defaultFileExt;     // accepts the first valid extension element
                    }
                }
            }
        }
        return EMPTY_STRING;
    }           

}
