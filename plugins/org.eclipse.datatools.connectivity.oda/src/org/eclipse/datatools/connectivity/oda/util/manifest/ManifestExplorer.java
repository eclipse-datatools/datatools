/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.util.manifest;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * The Manifest Explorer is the entry point to explore and access
 * the manifest of all the ODA plug-ins extensions that implement the 
 * <code>org.eclipse.datatools.connectivity.oda.dataSource</code> extension point.
 * The <code>ManifestExplorer</code> singleton instance is retrieved 
 * using the <code>getInstance()</code> method.
 */
public class ManifestExplorer
{
	private static ManifestExplorer sm_instance = null;
	
    // trace logging variables
	private static Logger sm_logger = null;

	private static final String DTP_ODA_EXT_POINT = 
	    	"org.eclipse.datatools.connectivity.oda.dataSource";  //$NON-NLS-1$

    // works around bug in some J2EE servers - Bugzilla #126073
    private static final String PACKAGE_NAME  = 
            "org.eclipse.datatools.connectivity.oda.util.manifest";  //$NON-NLS-1$
	
	/**
	 * Gets the <code>ManifestExplorer</code> instance to  
	 * explore the manifest of the data source extensions.
	 * @return	the <code>ManifestExplorer</code> instance.
	 */
	public static ManifestExplorer getInstance()
	{
	    if( sm_instance == null )
	        sm_instance = new ManifestExplorer();            

        return sm_instance;
	}
    
    /**
     * Singleton instance release method.
     */
    public static void releaseInstance()
    {
        sm_instance = null;
        sm_logger = null;
    }
	
	private ManifestExplorer()
	{
	}

    private static Logger getLogger()
    {
        if( sm_logger == null )
            sm_logger = Logger.getLogger( PACKAGE_NAME );
        
        return sm_logger;
    }
	
	/**
	 * Returns a collection of identifiers of 
	 * all ODA data source extensions.
	 * The extension's data source element ID and display name
	 * are stored as the key and value in the returned Properties instance.
     * The returned collection includes all matching extensions, including those
     * with no dataSet elements defined.
	 * Returns an empty <code>Properties</code> if there are 
	 * no data source extensions found.
	 * @return	a <code>Properties</code> containing the id
	 * 			and display name of all data source extensions.
	 */
	public Properties getDataSourceIdentifiers()
	{
		IExtension[] extensions = getDataSourceExtensions();
		int length = ( extensions == null ) ? 
		        		0 : extensions.length;
		Properties extensionIds = new Properties();
		for( int i = 0; i < length; i++ )
		{
			IExtension extension = extensions[i];			
			try
			{
				IConfigurationElement dsElement = getDataSourceElement( extension );
				String dataSourceId = dsElement.getAttribute( "id" );  //$NON-NLS-1$
				String dataSourceDisplayName = getElementDisplayName( dsElement );
				extensionIds.setProperty( dataSourceId, dataSourceDisplayName );
			}
			catch( OdaException ex )
			{
                getLogger().log( Level.WARNING, "Ignoring invalid extension.", ex );  //$NON-NLS-1$
			}
		}
		
		return extensionIds;
	}

	/**
	 * Returns the extension configuration information found 
	 * in the plugin manifest file of the data source extension
	 * that contains the specified data source element and 
	 * implements the DTP ODA run-time extension point -
	 * org.eclipse.datatools.connectivity.oda.dataSource.
	 * @param dataSourceId	the unique id of the data source element
	 * 						in a data source extension.
	 * @return				the extension manifest information
	 * @throws OdaException	if the extension manifest is invalid.
	 * @throws IllegalArgumentException if no extension is found.
	 */
	public ExtensionManifest getExtensionManifest( String dataSourceId ) 
		throws OdaException
	{
	    ExtensionManifest manifest = 
	        getExtensionManifest( dataSourceId, DTP_ODA_EXT_POINT );
	    
	    if( manifest != null )
	        return manifest;

	    throw new IllegalArgumentException( dataSourceId );
	}
	
	/**
	 * Returns the extension configuration information found 
	 * in the plugin manifest file of the data source extension
	 * that contains the specified data source element and 
	 * implements the specified ODA extension point.
	 * @param dataSourceId		the unique id of the data source element
	 * 							in a data source extension.
	 * @param extensionPoint	the id of the extension point to search
	 * @return					the extension manifest information,
	 * 							or null if no extension configuration is found.
	 * @throws OdaException		if the extension manifest is invalid.
	 */
	public ExtensionManifest getExtensionManifest( String dataSourceId, 
	        									   String extensionPoint ) 
		throws OdaException
	{
	    if ( dataSourceId == null || dataSourceId.length() == 0 )
			throw new IllegalArgumentException( dataSourceId );
	    
	    if ( extensionPoint == null || extensionPoint.length() == 0 )
			throw new IllegalArgumentException( extensionPoint );
	
	    IExtension[] extensions = getExtensions( extensionPoint );
	    
	    IExtension extension = findExtension( dataSourceId, extensions );
	
	    if ( extension != null )
	        return newExtensionManifest( extension );

	    return null;
	}

	/**
	 * Returns the extension configuration information found
	 * in the plugin manifest file for the specified data source
	 * extension of the specified extension point.
	 * @param platformExtension	core platform extension object
	 * @return					the extension manifest information
	 * @throws OdaException		if the extension manifest is invalid
	 */
	ExtensionManifest newExtensionManifest( IExtension platformExtension ) 
		throws OdaException
	{
	    if ( platformExtension == null )
			throw new IllegalArgumentException( Messages.manifest_nullArgument );
	    
        return new ExtensionManifest( platformExtension );
	}

	/**
	 * Returns an array of DTP ODA dataSource extension configuration information  
	 * found in the plugin manifest file.  Includes all matching extensions,
     * regardless of whether an extension has no dataSet elements defined.
     * Returns an empty array if there are no data source extensions found.
	 * Invalid data source extension definitions are ignored.
	 * @return	an <code>ExtensionManifest</code> array containing 
	 * 			the definition of all matching ODA data source extensions.
	 */
	public ExtensionManifest[] getExtensionManifests()
	{
        // for 0.7 backward compatibility, do not include those extensions 
        // that have no data set elements defined
        return getExtensionManifests( false );
    }
    
    /**
     * Returns an array of DTP ODA dataSource extension configuration information  
     * found in the plugin manifest file.
     * The argument specifies whether to include all matching extensions, regardless of
     * whether it has defined no dataSet element, such as a driver adapter plugin.
     * @param includesAllExtensions     true to include all matching extensions;
     *              false to include only those matching extensions
     *              with at least one valid dataSet element defined 
     * @return an <code>ExtensionManifest</code> array containing 
     *          the definition of all matching ODA data source extensions.
     */
    public ExtensionManifest[] getExtensionManifests( boolean includesAllExtensions )
    {
        return getExtensionManifests( DTP_ODA_EXT_POINT, includesAllExtensions );
	}

    /**
     * Returns an array of ODA dataSource extension configuration information
     * of those extensions that implement the specified extension point.  
     * Includes all matching extensions, regardless of whether an extension 
     * has no dataSet elements defined.
     * Returns an empty array if there are no data source extensions found.
     * Invalid data source extension definitions are ignored.
     * @param extensionPoint    name of an ODA data source extension point  
     * @return  an <code>ExtensionManifest</code> array containing 
     *          the definition of all matching ODA data source extensions.
     */
	public ExtensionManifest[] getExtensionManifests( String extensionPoint )
	{
        // for 0.7 backward compatibility, do not include those extensions 
        // that have no data set elements defined
        return getExtensionManifests( extensionPoint, false );
    }
    
    /**
     * Returns an array of ODA dataSource extension configuration information
     * of those extensions that implement the specified extension point.  
     * The argument specifies whether to include all matching extensions, regardless of
     * whether it has defined no dataSet element, such as a driver adapter plugin.
     * @param extensionPoint    name of an ODA data source extension point  
     * @param includesAllExtensions     true to include all matching extensions;
     *              false to include only those matching extensions
     *              with at least one valid dataSet element defined 
     * @return  an <code>ExtensionManifest</code> array containing 
     *          the definition of all matching ODA data source extensions.
     */
    public ExtensionManifest[] getExtensionManifests( String extensionPoint, 
                                                boolean includesAllExtensions )
    {
		IExtension[] extensions = getExtensions( extensionPoint );
		int length = ( extensions == null ) ? 
						0 : extensions.length;
		ArrayList manifestList = new ArrayList( length );
		for( int i = 0; i < length; i++ )
		{
			IExtension extension = extensions[i];	
			try
			{
				// validate and create extension manifest
                ExtensionManifest manifest = newExtensionManifest( extension );
                
                /* includes this extension manifest if the specified argument indicates 
                 * to include those without data set element
                 */
                if( includesAllExtensions || manifest.getDataSetTypeCount() > 0 )
                    manifestList.add( manifest );
			}
			catch( OdaException ex )
			{
                getLogger().log( Level.WARNING, "Ignoring invalid extension.", ex );  //$NON-NLS-1$
			}
		}
		
		int numOfValidExtensions = manifestList.size();
		return (ExtensionManifest[]) 
			manifestList.toArray( new ExtensionManifest[ numOfValidExtensions ] );
	}

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
				    		getDataSourceElement( extension );
				extnDataSourceId = dataSourceElement.getAttribute( "id" );  //$NON-NLS-1$
			}
			catch( OdaException ex )
			{
                getLogger().log( Level.WARNING, "Ignoring invalid extension.", ex );  //$NON-NLS-1$
				continue;
			}
			
			/* The first extension found with matching dataSourceId 
			 * in its dataSource element is considered a match.
			 */
			if( extnDataSourceId != null &&
			    extnDataSourceId.equalsIgnoreCase( dataSourceId ) )
				return extension;
		}
		
		return null;
	}
	
	private IExtension[] getDataSourceExtensions()
	{
		return getExtensions( DTP_ODA_EXT_POINT );
	}
	
	// Package static helper methods
    
    /*
     * Returns all the plugin extensions that implements the given
     * extension point.
     */
    public static IExtension[] getExtensions( String extPoint )
    {
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        if( pluginRegistry == null )    // possible in web deployment
            return null;
        IExtensionPoint extensionPoint = 
            pluginRegistry.getExtensionPoint( extPoint );
        if ( extensionPoint == null )
            return null;
        return extensionPoint.getExtensions();
    }
	
	/*
	 * Returns the dataSource element of the given data source extension.
	 */
	static IConfigurationElement getDataSourceElement( IExtension extension ) 
		throws OdaException
    {
        return getNamedElement( extension, "dataSource" );  //$NON-NLS-1$
    }
    
    /**
     * Returns the configuration element of the given extension
     * and element name.
     * <br>For internal use only.
     */
    public static IConfigurationElement getNamedElement( IExtension extension,
            String elementName ) 
        throws OdaException
    {
        IConfigurationElement[] configElements =
                        getNamedElements( extension, elementName );
        if( configElements.length == 0 )
            throw new OdaException( Messages.manifest_NO_DRIVER_RUNTIME_CONFIGURATION_DEFINED );

        return configElements[0];   // returns the first matching element
    }
    
    /**
     * Returns a collection of configuration elements with the given name
     * in the given extension.  
     * Validates that each element has an id attribute defined.
     * @return a collection of matching configuration elements
     * <br>For internal use only.
     */
    public static IConfigurationElement[] getNamedElements( 
                                            IExtension extension,
                                            String elementName ) 
        throws OdaException
    {
        return getNamedElements( extension, elementName, "id" );    //$NON-NLS-1$
    }
    
    /**
     * Returns a collection of configuration elements with the given name
     * in the given extension.  
     * Validates that each element has the specified attribute defined.
     * @return a collection of matching configuration elements
     * <br>For internal use only.
     */
    public static IConfigurationElement[] getNamedElements( 
                                            IExtension extension,
                                            String elementName, 
                                            String requiredAttributeName ) 
        throws OdaException
	{
		IConfigurationElement[] configElements = extension.getConfigurationElements();
        ArrayList matchedElements = new ArrayList();
		for( int i = 0, n = configElements.length; i < n; i++ )
		{
			IConfigurationElement configElement = configElements[i];
			if( ! configElement.getName().equalsIgnoreCase( elementName ) )
			    continue;

			// validate that the element has the required attribute with non-empty value
			String attrValue = configElement.getAttribute( requiredAttributeName );
			if( attrValue == null || attrValue.length() == 0 )
				throw new OdaException( 
                        Messages.bind( Messages.manifest_NO_ATTRIBUTE_ID_DEFINED, 
                                        requiredAttributeName, elementName ));

            matchedElements.add( configElement );
		}
		
		return (IConfigurationElement[]) matchedElements.toArray( 
                    new IConfigurationElement[ matchedElements.size() ] );
	}
	
	/*
	 * Returns a collection of dataSet elements of the given data source extension.
     * May return an empty collection if no dataSet elements are defined.
	 */
	static Hashtable getDataSetElements( IExtension extension, 
            String dataSourceElementId )
		throws OdaException
	{
        IConfigurationElement[] configElements =
            getNamedElements( extension, "dataSet" ); //$NON-NLS-1$
		Hashtable dataSetElements = new Hashtable();
        
        int numConfigElements = configElements.length;
		for( int i = 0; i < numConfigElements; i++ )
		{
			IConfigurationElement configElement = configElements[i];

			String dataSetTypeId = configElement.getAttribute( "id" );  //$NON-NLS-1$

			// if duplicated data set type ids exist in the extension,  
			// only the last one applies
			dataSetElements.put( dataSetTypeId, new DataSetType( configElement ) );
		}

		if( dataSetElements.size() < 1 )
        {
			String msg = Messages.bind( Messages.manifest_NO_DATA_SET_TYPES_DEFINED,
										dataSourceElementId );
            if( numConfigElements >= 1 )    // defined elements are all invalid
                throw new OdaException( msg );
            
            // no dataSet elements are defined; ok to proceed
            getLogger().log( Level.CONFIG, msg );
        }
        
		return dataSetElements;
	}

	/*
	 * Encapsulates the logic of finding the most appropriate
	 * display name to use for the given element.
	 */ 
	static String getElementDisplayName( IConfigurationElement dsElement )
	{
	    String displayName = dsElement.getAttribute( "defaultDisplayName" );  //$NON-NLS-1$

	    // Default to its id or name, if no display name is specified
		if ( displayName == null || displayName.length() == 0 )
		{
		    displayName = dsElement.getAttribute( "name" );  //$NON-NLS-1$
		    if ( displayName == null )  // no such attribute
			    displayName = dsElement.getAttribute( "id" );  //$NON-NLS-1$
		}
		
		return displayName;
	}

    /**
     * Converts the specified native data type code to 
     * its default ODA data type code,
     * based on the data type mapping defined
     * by the specified ODA data source and data set types.
     * @param nativeTypeCode    native type code specific to the ODA data source
     * @param odaDataSourceId   the ODA data source element id
     * @param dataSetType       the type of data set
     * @return  the converted ODA data type code, 
     *          or java.sql.Types.NULL if no valid mapping is found
     */
    public int getDefaultOdaDataTypeCode( int nativeTypeCode, 
                                String odaDataSourceId, String dataSetType )
    {
        DataSetType setType = null;
        try
        {
            ExtensionManifest manifest = getExtensionManifest( odaDataSourceId );
            if( manifest == null )
                return Types.NULL;
            
            setType = manifest.getDataSetType( dataSetType );
        }
        catch( OdaException e )
        {
            // ignore
        }
        
        if( setType == null )
            return Types.NULL;
        
        return setType.getDefaultOdaDataTypeCode( nativeTypeCode );        
    }

}
