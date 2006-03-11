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

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.birt.core.framework.IConfigurationElement;
import org.eclipse.birt.core.framework.IExtension;
import org.eclipse.birt.core.framework.IExtensionRegistry;
import org.eclipse.birt.core.framework.Platform;
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

    private static final String DTP_ODA_UI_EXT_POINT = 
            "org.eclipse.datatools.connectivity.oda.design.ui.dataSource";  //$NON-NLS-1$
    private static final String UI_PROPERTY_PAGE_EXT_PT = 
            "org.eclipse.ui.propertyPages";  //$NON-NLS-1$
    private static final String PAGE_ELEMENT_NAME = "page";  //$NON-NLS-1$
    
    private Hashtable m_manifests;  // cached copy of found manifests
    
    // trace logging variables
    private static String sm_loggerName;
    private static Logger sm_logger;
    
    /**
     * Returns the <code>UIManifestExplorer</code> instance to  
     * explore the manifest of all ODA data source UI extensions.
     * @return  the <code>UIManifestExplorer</code> singleton instance.
     */
    public static UIManifestExplorer getInstance()
    {
        if( sm_instance == null )
        {
            sm_instance = new UIManifestExplorer();
            
            sm_instance.m_manifests = new Hashtable();
            
            // works around bug in some J2EE servers; see Bugzilla #126073
            sm_loggerName = sm_instance.getClass().getPackage().getName();
            sm_logger = Logger.getLogger( sm_loggerName );
        }
        return sm_instance;
    }

    private UIManifestExplorer()
    {
    }
    
    /**
     * Finds and returns the extension configuration information defined 
     * in the plugin manifest of the ODA data source UI extension that
     * implements the DTP ODA design-time extension point -
     * <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code>.
     * @param dataSourceId  the unique id of the data source element
     *                      in the ODA designer data source extension.
     * @return              the UI extension manifest information;
     *                      or null, if no extension is found
     * @throws OdaException if the extension manifest is invalid
     */
    public UIExtensionManifest getExtensionManifest( String dataSourceId ) 
        throws OdaException
    {
        UIExtensionManifest manifest = 
            getExtensionManifest( dataSourceId, DTP_ODA_UI_EXT_POINT );
        
        return manifest;
    }

    /**
     * Finds and returns the extension configuration information defined 
     * in the plugin manifest of the data source extension
     * that contains the specified data source element and 
     * implements the specified ODA extension point.
     * @param dataSourceId      the unique id of the data source element
     *                          in a data source extension.
     * @param extensionPoint    the id of the extension point to search
     * @return                  the extension manifest information,
     *                          or null if no extension configuration is found.
     * @throws OdaException     if the extension manifest is invalid.
     */
    private UIExtensionManifest getExtensionManifest( 
                                        String dataSourceId, 
                                        String extensionPoint ) 
        throws OdaException
    {
        if ( dataSourceId == null || dataSourceId.length() == 0 )
            throw new OdaException(
                    new IllegalArgumentException( dataSourceId ) );
        
        if ( extensionPoint == null || extensionPoint.length() == 0 )
            throw new OdaException(
                    new IllegalArgumentException( extensionPoint ) );
    
        // first check if specified dataSourceId's manifest
        // is already in cache, and use it
        UIExtensionManifest aManifest =
            (UIExtensionManifest) m_manifests.get( dataSourceId );
        if( aManifest != null )
            return aManifest;
        
        // not yet cached, find and create a new one
        IExtension[] extensions = 
            ManifestExplorer.getExtensions( extensionPoint );
        
        IExtension extension = findExtension( dataSourceId, extensions );
    
        if ( extension == null )    // not found
            return null;
        
        // found extension 
        aManifest = new UIExtensionManifest( extension );
        
        // keep it in cached collection
        m_manifests.put( dataSourceId, aManifest );
        
        return aManifest;
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
                    ManifestExplorer.getNamedElement( extension, 
                            UIExtensionManifest.DATA_SOURCE_ELEMENT_NAME );
                extnDataSourceId = dataSourceElement.getAttribute( "id" );  //$NON-NLS-1$
            }
            catch( OdaException ex )
            {
                sm_logger.log( Level.WARNING, "Ignoring invalid extension.", ex ); //$NON-NLS-1$
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
    
    /**
     * Finds and returns the propertyPages extension configuration element 
     * in the plugin manifest of an extended ODA Designer UI plugin,
     * which implements the 
     * <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code> and
     * <code>org.eclipse.ui.propertyPages</code> extension points.
     * @param odaDataSourceId  an ODA data source extension element ID
     * @return  the configuration element of the <code>org.eclipse.ui.propertyPages</code>
     *          extension
     */
    public IConfigurationElement getPropertyPageElement( String odaDataSourceId )
    {
        String odaUIPluginId = getOdaDesignerId( odaDataSourceId );
        
        // find all the extensions of the ODA UI plugin
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtension[] extensions = pluginRegistry.getExtensions( odaUIPluginId );
        
        // look for the propertyPage extension element
        for( int i = 0; i < extensions.length; i++ )
        {
            String extnPointId = extensions[i].getExtensionPointUniqueIdentifier();
            if( ! extnPointId.equals( UI_PROPERTY_PAGE_EXT_PT ) )
                continue;   // check next extension
            
            try
            {
                // check whether the extension is for the given ODA data source id
                IConfigurationElement element =
                    ManifestExplorer.getNamedElement( extensions[i],
                        PAGE_ELEMENT_NAME );
                if( odaDataSourceId.equals( element.getAttribute( "id" ) ))  //$NON-NLS-1$
                        return element;     // found matching element
            }
            catch( OdaException e )
            {
                continue;   // ignore, check next extension
            }
        }
        
        return null;    // none is found
    }

    /**
     * Finds the plugin id that implements the
     * <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code>
     * extension point for the given ODA run-time data source element id.
     * @param odaDataSourceId   an ODA data source extension element ID
     * @return
     */
    public String getOdaDesignerId( String odaDataSourceId )
    {
        UIExtensionManifest manifest;
        try
        {
            manifest = getExtensionManifest( odaDataSourceId );
        }
        catch( Exception e )
        {
            return null;
        }
         
        return ( manifest == null ) ?
                null : manifest.getNamespace();
    }

}
