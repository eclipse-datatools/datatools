/*
 *************************************************************************
 * Copyright (c) 2006, 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.manifest;

import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ProfileWizardProvider;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestUtil;


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
    
    private Hashtable m_manifestsById;  // cached copy of manifests by odaDataSourceId
    private Hashtable m_manifestsWithWizName; // cached copy of manifests with wizard name
    
    // trace logging variables
    private static String sm_loggerName;
    private static Logger sm_logger = null;
    
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
            
            // works around bug in some J2EE servers - Bugzilla #126073
            sm_loggerName = sm_instance.getClass().getPackage().getName();
        }
        return sm_instance;
    }

    private UIManifestExplorer()
    {
    }
    
    /**
     * Refresh the manifest explorer, and allows it to get
     * the latest ODA Design UI extension manifests.
     */
    public void refresh()
    {
        // reset the cached collection of ODA Design UI extension manifest instances
        m_manifestsById = null;
        m_manifestsWithWizName = null;
    }

    private Hashtable getCachedManifests()
    {
    	if( m_manifestsById == null )
            m_manifestsById = new Hashtable();
    	return m_manifestsById;
    }

    /**
     * Returns a collection of all DTP ODA design-time extension configuration information 
     * found in plugins registry.  
     * Each extension's UIExtensionManifest and its corresponding new wizard name
     * are stored as the key and value in the returned collection.
     * Returns an empty collection if there are no design-time extensions found.
     * Invalid extension definitions are ignored.
     * @return  a collection of all DTP ODA design-time extension manifests, with
     *          each extension's UIExtensionManifest and its corresponding new wizard name
     *          as the key and value.
     */
    public Map getExtensionManifestsWithWizardName()
    {
        if( m_manifestsWithWizName == null )
        {
            IExtension[] extensions = getExtensions( DTP_ODA_UI_EXT_POINT );
            
            int length = ( extensions == null ) ? 0 : extensions.length;
            m_manifestsWithWizName = new Hashtable( length );
            for( int i = 0; i < length; i++ )
            {
                IExtension extension = extensions[i];
            
                UIExtensionManifest uiManifest = null;
                try
                {
                    if( extension != null )
                        uiManifest = new UIExtensionManifest( extension );
                }
                catch( OdaException ex )
                {
                    getLogger().log( Level.WARNING, "Ignoring invalid extension.", ex ); //$NON-NLS-1$
                }
                if( uiManifest == null )
                    continue;   // skip
                
                // for each oda designer extension, find its corresponding new wizard name
                // and add to a collection
                
                String wizardName = getDataSourceWizardName( 
                                        uiManifest.getDataSourceElementId() );
                m_manifestsWithWizName.put( uiManifest, wizardName );
            }
        }
        
        return m_manifestsWithWizName;
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
        return getExtensionManifest( dataSourceId, DTP_ODA_UI_EXT_POINT );
    }
    
    /**
     * Returns the DataSetUIElement instance that
     * represents the dataSetUI element with the given id defined
     * in the ODA extension manifest of the specified data source 
     * element id. 
     * An ODA extension is one that
     * implements the DTP ODA design-time extension point -
     * <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code>.
     * If the given data set element id is null and the data source UI
     * extension has only one data set element, that
     * data set element will be returned by default.
     * @param dataSourceId  the unique id of the data source element
     *                      in the ODA designer data source extension.
     * @param dataSetElementID  the id of the data set ui element.
     * @return  the data set element definition.
     * @throws OdaException if specified data source element id 
     * 					 or data set element id is not found, or 
     *                   if there are more than one data set elements 
     *                   but no dataSetElementID is specified.
     */
    public DataSetUIElement getDataSetUIElement( String dataSourceId,
    								String dataSetElementID ) 
    	throws OdaException
    {
    	UIExtensionManifest manifest = getExtensionManifest( dataSourceId );
    	if( manifest == null )	// no such data source element id found
            throw new OdaException(
                    new IllegalArgumentException( dataSourceId ) );
    	
    	return manifest.getDataSetUIElement( dataSetElementID );
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
     * @throws OdaException     if any specified argument is invalid, or
     *                          the manifest found is not valid.
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
            (UIExtensionManifest) getCachedManifests().get( dataSourceId );
        if( aManifest != null )
            return aManifest;
        
        // not yet cached, find and create a new one
        IExtension[] extensions = 
            getExtensions( extensionPoint );
        
        IExtension extension = findExtension( dataSourceId, extensions );
    
        if ( extension == null )    // not found
            return null;
        
        // found extension 
        aManifest = new UIExtensionManifest( extension );
        
        // keep it in cached collection
        getCachedManifests().put( dataSourceId, aManifest );
        
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
            
            String extnDataSourceId = getDataSourceId( extension );
            if( extnDataSourceId == null )  // not a valid extension, skip
                continue;
            
            /* The first extension found with matching dataSourceId 
             * in its dataSourceUI element is considered a match.
             */
            if( extnDataSourceId.equalsIgnoreCase( dataSourceId ) )
                return extension;
        }
        
        return null;
    }
    
    private String getDataSourceId( IExtension extension )
    {
        try
        {
            /* Each odaDataSource extension should have only 
             * one dataSource element.
             */
            IConfigurationElement dataSourceElement = 
                getNamedElement( extension, 
                        UIExtensionManifest.DATA_SOURCE_ELEMENT_NAME );
            return dataSourceElement.getAttribute( "id" );  //$NON-NLS-1$
        }
        catch( OdaException ex )
        {
            // log and ignore
            getLogger().log( Level.WARNING, "Ignoring invalid extension.", ex ); //$NON-NLS-1$
        }
        
        return null;
    }
    
    /**
     * Returns the name defined in the connectionProfile.newWizard element of
     * the specified odaDataSourceId.
     * @param odaDataSourceId
     * @return
     */
    private String getDataSourceWizardName( String odaDataSourceId )
    {
        IExtension[] extensions = 
            getExtensions( ConnectionProfileManager.EXTENSION_ID );
        
        int length = ( extensions == null ) ? 0 : extensions.length;
        for( int i = 0; i < length; i++ )
        {
            IConfigurationElement wizardElement = null;
            try
            {
                wizardElement = getNamedElement( extensions[i], 
                                            ConnectionProfileManager.EXT_ELEM_NEW_WIZARD );
            }
            catch( OdaException ex )
            {
                // log and ignore
                getLogger().log( Level.WARNING, "Ignoring invalid extension.", ex ); //$NON-NLS-1$
            }            
            if( wizardElement == null )
                continue;
            
            ProfileWizardProvider wizardProvider = new ProfileWizardProvider( wizardElement );
            
            /* The first extension found with matching odaDataSourceId 
             * in its newWizard.profile attribute is considered a match.
             * An ODA profile uses the odaDataSourceId as its profile identifier,
             * which uniquely identifies an ODA run-time data source extension.
             */
            if( odaDataSourceId.equalsIgnoreCase( wizardProvider.getProfile() ) )
            {
                String wizardName = wizardProvider.getName();
                if( wizardName != null && wizardName.length() > 0 )
                    return wizardName;
                return odaDataSourceId;     // no name defined, use id instead
            }
            // element does not match given odaDataSourceId, continue with next extension
        }
        
        // could not find the connectionProfile.newWizard element for given odaDataSourceId
        return odaDataSourceId;
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
                    getNamedElement( extensions[i],
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
    
    private static Logger getLogger()
    {
        if( sm_logger == null )
            sm_logger = Logger.getLogger( sm_loggerName );
        return sm_logger;
    }
    
    /* 
     * Returns all the plugin extensions that implements the given
     * extension point.
     */
    private static IExtension[] getExtensions( String extPoint )
    {
        return ManifestExplorer.getExtensions( extPoint );
    }
    
    /** 
     * Returns the configuration element of the given extension
     * and element name.
     * <br>For internal use only.
     */
    static IConfigurationElement getNamedElement( IExtension extension,
            String elementName ) 
        throws OdaException
    {
        return ManifestUtil.getNamedElement( extension, elementName, "id" ); //$NON-NLS-1$
    }
    
    /**
     * Returns a collection of configuration elements with the given name
     * in the given extension.  
     * Validates that each element has an id attribute defined.
     * @return a collection of matching configuration elements
     * <br>For internal use only.
     */
    static IConfigurationElement[] getNamedElements( 
                                            IExtension extension,
                                            String elementName ) 
        throws OdaException
    {
        return ManifestUtil.getNamedElements( extension, elementName, "id" ); //$NON-NLS-1$
    }

}
