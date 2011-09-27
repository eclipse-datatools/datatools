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

package org.eclipse.datatools.connectivity.internal.security;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.security.ICipherProvider;

/**
 *  Internal utility that loads and processes the extensions that implement the
 *  org.eclipse.datatools.connectivity.cipherProvider extension point.
 *  @since 1.2.4 (DTP 1.9.2)
 */
public class CipherProviderExtensions
{
    private static final String CIPHER_PROVIDER_ELEMENT_NAME = "cipherProvider"; //$NON-NLS-1$
    private static final String CIPHER_PROVIDER_FILE_EXT_ATTR_NAME = "fileExtension"; //$NON-NLS-1$
    private static final String CIPHER_PROVIDER_CLASS_ATTR_NAME = "class"; //$NON-NLS-1$

    private static final String NULL_FILE_EXT_CIPHER_PROVIDER_KEY = 
        "org.eclipse.datatools.connectivity.cipherProvider.nullFileExt"; //$NON-NLS-1$
    private static final String NULL_FILE_EXT_ATTR_VALUE = "default"; //$NON-NLS-1$
    private static final String FILE_EXT_SEPARATOR = "."; //$NON-NLS-1$
    
    private static Map<String,ICipherProvider> sm_registeredCipherProviders;

    /**
     * Returns a custom ICipherProvider instance registered for the file extension
     * of the specified connection profile store file name.
     * If the specified file has no extension in its file name, 
     * returns the custom ICipherProvider instance registered for those with no file extension, 
     * if defined in an org.eclipse.datatools.connectivity.cipherProvider extension.
     * @param profileStoreFile  a connection profile store {@link File}
     * @return  the corresponding {@link ICipherProvider} instance,
     *          or null if none is defined for the specified profileStoreFile
     *          in an org.eclipse.datatools.connectivity.cipherProvider extension
     */
    static ICipherProvider getCipherProviderForFile( File profileStoreFile )
    {
        if( profileStoreFile == null )
            return null;
        
        // get the file extension from the specified File instance
        String profileStoreFilePath = profileStoreFile.getPath();
        int fileExtSeparatorIndex = profileStoreFilePath.lastIndexOf( FILE_EXT_SEPARATOR );

        // if filename has no file extension, use the default fileExtension provider, if exists
        String fileExtension = NULL_FILE_EXT_ATTR_VALUE;
        // if file has extension
        if( fileExtSeparatorIndex >= 0 && 
            profileStoreFilePath.length() > fileExtSeparatorIndex+1 )
        {
            fileExtension = profileStoreFilePath.substring( fileExtSeparatorIndex+1 );
        }
                          
        String fileExtKey = getFileExtMapKey( fileExtension );
        return getCipherProviderForFileExtension( fileExtKey ); 
    }

    /**
     * Returns a custom ICipherProvider instance defined for the specified file extension
     * of a connection profile store.
     * If the specified file has no extension in its file name, 
     * returns the custom ICipherProvider instance registered for those with no file extension, 
     * if defined in an org.eclipse.datatools.connectivity.cipherProvider extension.
     * @param fileExtension    the file extension of a connection profile store file 
     * @return  the corresponding {@link ICipherProvider} instance,
     *          or null if none is defined for the specified fileExtension
     *          in an org.eclipse.datatools.connectivity.cipherProvider extension
     */
    static ICipherProvider getCipherProviderForFileExtension( String fileExtension )
    {
        return getRegisteredCipherProviders().get( fileExtension );
    }
    
    private static Map<String,ICipherProvider> getRegisteredCipherProviders()
    {
        if( sm_registeredCipherProviders == null )
        {
            synchronized( CipherProviderExtensions.class )
            {
                if( sm_registeredCipherProviders == null )
                    sm_registeredCipherProviders = loadRegisteredCipherProviders();
            }
        }
        return sm_registeredCipherProviders;
    }

    private static Map<String,ICipherProvider> loadRegisteredCipherProviders()
    {
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.cipherProvider"); //$NON-NLS-1$
        IExtension[] extensions = extensionPoint.getExtensions();
        if( extensions.length == 0 )
            return Collections.emptyMap();
        
        Map<String,ICipherProvider> cipherProviders = new HashMap<String,ICipherProvider>( extensions.length );
        for( int i = 0; i < extensions.length; ++i )
        {
            IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
            if( configElements.length < 1 )
                continue;   // skip this extension with no configuration element

            // iterate through each cipherProvider element in the extension
            for( int j = 0; j < configElements.length; ++j )
            {
                IConfigurationElement configElement = configElements[j];
                if( ! configElement.getName().equals( CIPHER_PROVIDER_ELEMENT_NAME ) )
                    continue;
    
                String fileExtension = configElement.getAttribute( CIPHER_PROVIDER_FILE_EXT_ATTR_NAME );
                String providerClassName = configElement.getAttribute( CIPHER_PROVIDER_CLASS_ATTR_NAME );
                
                // validate the attribute values are not null or empty
                if( fileExtension == null || fileExtension.trim().length() == 0 ||
                    providerClassName == null || providerClassName.trim().length() == 0 )
                {
                    // log missing attribute value(s)
                    ConnectivityPlugin.getDefault().logInfo(  
                            ConnectivityPlugin.getDefault().getResourceString( 
                                    "CipherProviderExtensions.missingRequiredAttrValue",  //$NON-NLS-1$
                                    new Object[]{ CIPHER_PROVIDER_ELEMENT_NAME, extensions[i].getUniqueIdentifier(),
                                            CIPHER_PROVIDER_FILE_EXT_ATTR_NAME, fileExtension,
                                            CIPHER_PROVIDER_CLASS_ATTR_NAME, providerClassName } ));
                    continue;   // skip element with missing required attribute value(s)
                }
    
                // create the custom ICipherProvider instance and save in cached Map
                try
                {
                    Object cipherProviderInstance = configElement.createExecutableExtension( CIPHER_PROVIDER_CLASS_ATTR_NAME );
                    if( !(cipherProviderInstance instanceof ICipherProvider) )
                    {
                        // log invalid class
                        ConnectivityPlugin.getDefault().logInfo(  
                                ConnectivityPlugin.getDefault().getResourceString( 
                                        "CipherProviderExtensions.invalidClassAttrValue",  //$NON-NLS-1$
                                        new Object[]{ providerClassName, extensions[i].getUniqueIdentifier(),
                                                ICipherProvider.class.getName() } ));
                        continue;   // skip invalid element
                    }

                    // add the ICipherProvider instance to the Map
                    String fileExtKey = getFileExtMapKey( fileExtension );
                    ICipherProvider overriddenProvider = cipherProviders.put( fileExtKey,
                                                            (ICipherProvider) cipherProviderInstance );

                    if( overriddenProvider != null )
                    {
                        // log warning that the provider is being overridden by another extension element
                        ConnectivityPlugin.getDefault().logError(  
                            ConnectivityPlugin.getDefault().getResourceString( 
                                "CipherProviderExtensions.conflictingExtensions", //$NON-NLS-1$
                                new Object[]{ overriddenProvider, cipherProviderInstance, 
                                        extensions[i].getContributor().getName(), fileExtension } ));
                    }
                }
                catch( CoreException e )
                {
                    ConnectivityPlugin.getDefault().log( e );
                    continue;   // skip the element
                }
            }
        }
        
        return cipherProviders;
    }

    /*
     *  Convert the file extension for use as a Map key.
     */
    private static String getFileExtMapKey( String fileExtension )
    {
        // strip out any leading file extension separator
        if( fileExtension.startsWith( FILE_EXT_SEPARATOR ) )
            fileExtension = fileExtension.substring( FILE_EXT_SEPARATOR.length() );

        // if it is the special character for null file extension, use the pre-defined key
        if( fileExtension.equals( NULL_FILE_EXT_ATTR_VALUE ) )
            fileExtension = NULL_FILE_EXT_CIPHER_PROVIDER_KEY;
        
        return fileExtension;
    }
    
}
