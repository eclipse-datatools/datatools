/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.manifest;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.birt.core.framework.IConfigurationElement;
import org.eclipse.birt.core.framework.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;


/**
 * The UI Manifest Explorer is the entry point to explore and access
 * the manifest of all the ODA design time plug-ins extensions that implement the 
 * <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code> extension point.
 * The <code>UIManifestExplorer</code> singleton instance is accessed 
 * using the <code>getInstance()</code> static method.
 */
public class UIManifestExplorer
{
    private static UIManifestExplorer sm_instance = null;
    
    // trace logging variables
    private static String sm_loggerName = UIManifestExplorer.class.getPackage().getName();
    private static Logger sm_logger = Logger.getLogger( sm_loggerName );

    private static final String DTP_ODA_UI_EXT_POINT = 
            "org.eclipse.datatools.connectivity.oda.design.ui.dataSource";  //$NON-NLS-1$
    
    /**
     * Returns the <code>UIManifestExplorer</code> instance to  
     * explore the manifest of all ODA data source UI extensions.
     * @return  the <code>UIManifestExplorer</code> singleton instance.
     */
    public static UIManifestExplorer getInstance()
    {
        if( sm_instance == null )
            sm_instance = new UIManifestExplorer();
        return sm_instance;
    }

    private UIManifestExplorer()
    {
    }
    
    /**
     * Returns the extension configuration information found 
     * in the plugin manifest file of the ODA data source UI extension
     * that contains the specified data source element and 
     * implements the DTP ODA design-time extension point -
     * org.eclipse.datatools.connectivity.oda.design.ui.dataSource.
     * @param dataSourceId  the unique id of the data source element
     *                      in the data source extension.
     * @return              the UI extension manifest information
     * @throws OdaException if the extension manifest is invalid.
     * @throws IllegalArgumentException if no extension is found.
     */
    public UIExtensionManifest getExtensionManifest( String dataSourceId ) 
        throws OdaException
    {
        UIExtensionManifest manifest = 
            getExtensionManifest( dataSourceId, DTP_ODA_UI_EXT_POINT );
        
        if( manifest != null )
            return manifest;
    
        throw new IllegalArgumentException( dataSourceId );
    }

    /**
     * Returns the extension configuration information found 
     * in the plugin manifest file of the data source extension
     * that contains the specified data source element and 
     * implements the specified ODA extension point.
     * @param dataSourceId      the unique id of the data source element
     *                          in a data source extension.
     * @param extensionPoint    the id of the extension point to search
     * @return                  the extension manifest information,
     *                          or null if no extension configuration is found.
     * @throws OdaException     if the extension manifest is invalid.
     */
    private UIExtensionManifest getExtensionManifest( String dataSourceId, 
                                                   String extensionPoint ) 
        throws OdaException
    {
        if ( dataSourceId == null || dataSourceId.length() == 0 )
            throw new IllegalArgumentException( dataSourceId );
        
        if ( extensionPoint == null || extensionPoint.length() == 0 )
            throw new IllegalArgumentException( extensionPoint );
    
        IExtension[] extensions = 
            ManifestExplorer.getExtensions( extensionPoint );
        
        IExtension extension = findExtension( dataSourceId, extensions );
    
        if ( extension != null )    // found
            return new UIExtensionManifest( extension );
        // not found
        return null;
    }
    
    /*
     * Finds the extension that matches the given data source element ID
     * among the given collection of extensions.
     */
    private IExtension findExtension( String dataSourceId, IExtension[] extensions )
        throws OdaException
    {
        int length = ( extensions == null ) ? 
                0 : extensions.length;

        for( int i = 0; i < length; i++ )
        {
            IExtension extension = extensions[i];
            
            String extnDataSourceId = null;
            try
            {
                /* Each odaDataSource extension should have only 
                 * one dataSource element.
                 */
                IConfigurationElement dataSourceElement = 
                    ManifestExplorer.getDataSourceElement( extension, 
                            UIExtensionManifest.DATA_SOURCE_ELEMENT_NAME );
                extnDataSourceId = dataSourceElement.getAttribute( "id" );  //$NON-NLS-1$
            }
            catch( OdaException ex )
            {
                sm_logger.log( Level.WARNING, "Ignoring invalid extension.", ex );
                continue;
            }
            
            /* The first extension found with matching dataSourceId 
             * in its dataSourceUI element is considered a match.
             */
            if( extnDataSourceId != null &&
                extnDataSourceId.equalsIgnoreCase( dataSourceId ) )
                return extension;
        }
        
        return null;
    }

}
