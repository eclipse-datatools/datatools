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

package org.eclipse.datatools.connectivity.oda.consumer.util.manifest;

import java.util.Hashtable;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;

/**
 * The Extension Explorer is the entry point to explore and access
 * the manifest of all the ODA consumer helper extensions that implement the 
 * <code>org.eclipse.datatools.connectivity.oda.consumer.driverBridge</code> extension point.
 * The <code>ExtensionExplorer</code> singleton instance is retrieved 
 * using the <code>getInstance()</code> method.
 */
public class ExtensionExplorer
{
    private static ExtensionExplorer sm_instance = null;

    private static final String DTP_ODA_BRIDGE_EXT_POINT = 
            "org.eclipse.datatools.connectivity.oda.consumer.driverBridge";  //$NON-NLS-1$
    
    private Hashtable m_bridgeManifests;  // cached copy of found driverBridge manifests
    private Hashtable m_propProviderManifests; // cached copy of found externalizedProperties manifests
    
    /**
     * Returns the <code>ExtensionExplorer</code> instance to  
     * explore the manifest of all ODA consumer extensions.
     * @return  the <code>ExtensionExplorer</code> singleton instance.
     */
    public static ExtensionExplorer getInstance()
    {
        if( sm_instance == null )
        {
            sm_instance = new ExtensionExplorer();
            
            sm_instance.refresh();
        }
        return sm_instance;
    }
    
    /**
     * Singleton instance release method.
     */
    public static void releaseInstance()
    {
        sm_instance = null;
    }

    private ExtensionExplorer()
    {
    }
    
    /**
     * Refresh the explorer, and allows it to get
     * the latest ODA Consumer extension manifests.
     */
    public void refresh()
    {
        // reset the cached collection of extension manifest instances
        m_bridgeManifests = new Hashtable();
        m_propProviderManifests = new Hashtable();
    }
    
    /**
     * Finds and returns the extension information defined 
     * in the plugin manifest of the extension that
     * implements the DTP ODA Consumer extension point -
     * <code>org.eclipse.datatools.connectivity.oda.consumer.driverBridge</code>.
     * @param driverType      the interface type id of an underlying ODA data provider.
     * @return              the driver bridge extension manifest information;
     *                      or null, if no matching extension is found
     * @throws OdaException if the extension manifest is invalid
     */
    public DriverExtensionManifest getDriverExtensionManifest( String driverType ) 
        throws OdaException
    {
        DriverExtensionManifest manifest = 
            getDriverExtensionManifest( driverType, DTP_ODA_BRIDGE_EXT_POINT );
        
        return manifest;
    }
    
    /**
     * Finds and returns the extension information defined 
     * in the plugin manifest of the driver bridge extension
     * that contains the specified bridgeFactory element for
     * the specified driver type.
     * @throws OdaException     if the extension manifest is invalid.
     */
    private DriverExtensionManifest getDriverExtensionManifest( 
                                        String driverType, 
                                        String extensionPoint ) 
        throws OdaException
    {
        if( driverType == null || driverType.length() == 0 )
            throw new OdaException(
                    new IllegalArgumentException( driverType ) );
        
        if( extensionPoint == null || extensionPoint.length() == 0 )
            throw new OdaException(
                    new IllegalArgumentException( extensionPoint ) );
    
        // first check if specified dataSourceId's manifest
        // is already in cache, and use it
        DriverExtensionManifest aManifest =
            (DriverExtensionManifest) m_bridgeManifests.get( driverType );
        if( aManifest != null )
            return aManifest;
        
        // not yet cached, find and create a new one
        IExtension extension = findExtensionWithAttribute( driverType, 
                                DriverExtensionManifest.DRIVER_TYPE_ATTRIBUTE, 
                                DriverExtensionManifest.BRIDGE_ELEMENT,
                                extensionPoint );    
        if( extension == null )    // not found
            return null;
        
        // found extension 
        aManifest = new DriverExtensionManifest( extension );
        
        // keep it in cached collection
        m_bridgeManifests.put( driverType, aManifest );
        
        return aManifest;
    }
    
    /**
     * Finds the extension that matches the given attribute value and name in specified element
     * among the given collection of extensions.
     */
    private IExtension findExtensionWithAttribute( String attributeValue, String attributeName,
                                                    String elementName, String extensionPoint )
        throws OdaException
    {
        IExtension[] extensions = 
            ManifestExplorer.getExtensions( extensionPoint );
        
        int length = ( extensions == null ) ? 
                0 : extensions.length;

        for( int i = 0; i < length; i++ )
        {
            IExtension extension = extensions[i];
            
             // Find an driverBridge extension 
            IConfigurationElement foundElement =
                getNamedElement( extension, elementName, attributeName );
            if( foundElement == null )
                continue;   // ignores invalid extension
            
            String value = foundElement.getAttribute( attributeName );
            
            /* The first extension found with matching driverType 
             * in its bridgeFactory element is considered a match.
             */
            if( value != null &&
                value.equalsIgnoreCase( attributeValue ) )
                return extension;
        }
        
        return null;
    }
    
    /**
     * Finds and returns the extension information defined 
     * in the plugin manifest of the extension that
     * implements the DTP ODA Consumer extension point -
     * <code>org.eclipse.datatools.connectivity.oda.consumer.propertyProvider</code>.
     * @param applicationId      the unique identifier of an ODA consumer application, 
     *                      which embeds an engine that uses the ODA consumer helper framework.
     * @return              the property provider extension manifest information;
     *                      or null, if no matching extension is found
     * @throws OdaException if the extension manifest is invalid
     */
    public PropertyProviderManifest getPropertyProviderManifest( String applicationId ) 
        throws OdaException
    {
        PropertyProviderManifest manifest = 
            getPropertyProviderManifest( applicationId, 
            		PropertyProviderManifest.DTP_ODA_PROPERTY_PROVIDER_EXT_POINT );
        
        return manifest;
    }
    
    private PropertyProviderManifest getPropertyProviderManifest( String applicationId, 
                                                    String extensionPoint ) 
        throws OdaException
    {
        if ( applicationId == null || applicationId.length() == 0 )
            throw new OdaException(
                    new IllegalArgumentException( applicationId ) );
        
        if ( extensionPoint == null || extensionPoint.length() == 0 )
            throw new OdaException(
                    new IllegalArgumentException( extensionPoint ) );
    
        // first check if specified dataSourceId's manifest
        // is already in cache, and use it
        PropertyProviderManifest aManifest =
            (PropertyProviderManifest) m_propProviderManifests.get( applicationId );
        if( aManifest != null )
            return aManifest;
        
        // not yet cached, find and create a new one
        IExtension extension = findExtensionWithAttribute( applicationId, 
                                    PropertyProviderManifest.APPLICATION_ID_ATTRIBUTE, 
                                    PropertyProviderManifest.PROP_SERVICE_ELEMENT,
                                    extensionPoint );
        if ( extension == null )    // not found
            return null;
        
        // found extension 
        aManifest = new PropertyProviderManifest( extension );
        
        // keep it in cached collection
        m_propProviderManifests.put( applicationId, aManifest );
        
        return aManifest;       
    }
        
    /**
     * Returns the element with given name and required attribute
     * defined in the given extension.  The required attribute must have a non-empty value.
     * <br>For internal use only.
     */
    static IConfigurationElement getNamedElement( IExtension extension,
                                    String elementName, String requiredAttributeName ) 
    {
        IConfigurationElement[] namedElements = null;
        try
        {
            namedElements = ManifestExplorer.getNamedElements( extension, 
                                elementName, requiredAttributeName );
        }
        catch( OdaException e )
        {
            // ignore
        }
        
        if( namedElements == null || namedElements.length == 0 )
            return null;

        // expects only one, as defined in extension point schema
        return namedElements[0];
    }

}
