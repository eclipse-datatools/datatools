/*
 *************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util.manifest;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.birt.core.framework.IConfigurationElement;
import org.eclipse.birt.core.framework.IExtension;
import org.eclipse.birt.core.framework.IExtensionPoint;
import org.eclipse.birt.core.framework.IExtensionRegistry;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.OdaResources;
import org.eclipse.datatools.connectivity.oda.util.ResourceCache;
import org.eclipse.datatools.connectivity.oda.util.ResourceManager;

/**
 * The Manifest Explorer is the entry point to explore and access
 * the manifest of all the ODA plug-ins extensions that implement the 
 * <code>org.eclipse.datatools.connectivity.oda.dataSource</code> extension point.
 * The <code>ManifestExplorer</code> singleton instance can be retrieved 
 * using the <code>getInstance()</code> method.
 */
public class ManifestExplorer
{
	private static ManifestExplorer sm_instance = new ManifestExplorer();

    // trace logging variables
	private static String sm_loggerName = ManifestExplorer.class.getPackage().getName();
	private static Logger sm_logger = Logger.getLogger( sm_loggerName );
	
	/**
	 * Gets the <code>ManifestExplorer</code> instance to  
	 * explore the manifest of the data source extensions.
	 * @return	the <code>ManifestExplorer</code> instance.
	 */
	public static ManifestExplorer getInstance()
	{
		return sm_instance;
	}
	
	private ManifestExplorer()
	{
	}
	
	/**
	 * Returns a collection of identifiers of 
	 * all ODA data source extensions.
	 * The extension's data source element ID and display name
	 * are stored as the key and value in the returned Properties instance.
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
				String dataSourceId = dsElement.getAttribute( "id" );
				String dataSourceDisplayName = getElementDisplayName( dsElement );
				extensionIds.setProperty( dataSourceId, dataSourceDisplayName );
			}
			catch( OdaException ex )
			{
				sm_logger.log( Level.WARNING, "Ignoring invalid extension.", ex );
			}
		}
		
		return extensionIds;
	}

	/**
	 * Returns the extension configuration information found 
	 * in the plugin manifest file
	 * for the specified data source extension of the extension
	 * point org.eclipse.datatools.connectivity.oda.dataSource.
	 * @param extensionId	the unique id of the data source element
	 * 						in a data source extension.
	 * @return				the extension manifest information
	 * @throws OdaException	if the extension manifest is invalid.
	 * @throws IllegalArgumentException if no extension is found.
	 */
	public ExtensionManifest getExtensionManifest( String extensionId ) 
		throws OdaException
	{
	    ExtensionManifest manifest = getExtensionManifest( extensionId, 
	            "org.eclipse.datatools.connectivity.oda.dataSource" );
	    
	    if ( manifest != null )
	        return manifest;
	    else
	        throw new IllegalArgumentException( extensionId );
	}
	
	/**
	 * Returns the extension configuration information found
	 * in the plugin manifest file for the specified data source
	 * extension of the specified extension point.
	 * @param extensionId		the unique id of the data source element
	 * 							in the data source extension.
	 * @param extensionPoint	the id of the extension point to search
	 * @return					the extension manifest information,
	 * 							or null if no extension configuration is found.
	 * @throws OdaException		if the extension manifest is invalid.
	 */
	public ExtensionManifest getExtensionManifest( String extensionId, String extensionPoint ) 
		throws OdaException
	{
	    if ( extensionId == null || extensionId.length() == 0 )
			throw new IllegalArgumentException( extensionId );
	    
	    if ( extensionPoint == null || extensionPoint.length() == 0 )
			throw new IllegalArgumentException( extensionPoint );
	
	    IExtension[] extensions = getExtensions( extensionPoint );
	    
	    IExtension extension = findExtension( extensionId, extensions );
	
	    if ( extension != null )
	        return newExtensionManifest( extension );
	    else
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
	public ExtensionManifest newExtensionManifest( IExtension platformExtension ) 
		throws OdaException
	{
	    if ( platformExtension == null )
			throw new IllegalArgumentException( "null argument" );	// TODO: localized message
	    
        return new ExtensionManifest( platformExtension );
	}

	/**
	 * Returns an array of ODA extension configuration information  
	 * found in the plugin manifest file.  Returns 
	 * an empty array if there are no data source extensions found.
	 * Invalid data source extension definitions are ignored.
	 * @return	an <code>ExtensionManifest</code> array containing 
	 * 			the definition of all valid ODA data source extensions.
	 */
	public ExtensionManifest[] getExtensionManifests()
	{
		IExtension[] extensions = getDataSourceExtensions();
		int length = ( extensions == null ) ? 
						0 : extensions.length;
		ArrayList manifestList = new ArrayList( length );
		for( int i = 0; i < length; i++ )
		{
			IExtension extension = extensions[i];	
			try
			{
				// validate and create extension manifest
				manifestList.add( newExtensionManifest( extension ) );
			}
			catch( OdaException ex )
			{
				sm_logger.log( Level.WARNING, "Ignoring invalid extension.", ex );
			}
		}
		
		int numOfValidExtensions = manifestList.size();
		return (ExtensionManifest[]) 
			manifestList.toArray( new ExtensionManifest[ numOfValidExtensions ] );
	}

	private IExtension findExtension( String extensionId, IExtension[] extensions )
		throws OdaException
	{
	    int length = ( extensions == null ) ? 
				0 : extensions.length;

		for( int i = 0; i < length; i++ )
		{
			IExtension extension = extensions[i];
			
			String dataSourceId = null;
			try
			{
				IConfigurationElement dataSourceElement = 
				    		getDataSourceElement( extension );
				dataSourceId = dataSourceElement.getAttribute( "id" );
			}
			catch( OdaException ex )
			{
				sm_logger.log( Level.WARNING, "Ignoring invalid extension.", ex );
				continue;
			}
			
			if( dataSourceId != null &&
			    dataSourceId.equals( extensionId ) )
				return extension;
		}
		
		return null;
	}
	
	private IExtension[] getExtensions( String extPoint )
	{
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = 
			pluginRegistry.getExtensionPoint( extPoint );
		if ( extensionPoint == null )
		    return null;
		return extensionPoint.getExtensions();
	}
	
	private IExtension[] getDataSourceExtensions()
	{
		return getExtensions( "org.eclipse.datatools.connectivity.oda.dataSource" );
	}
	
	// Package helper methods
	
	/*
	 * Returns the dataSource element of the given data source extension.
	 */
	static IConfigurationElement getDataSourceElement( IExtension extension ) 
		throws OdaException
	{
		IConfigurationElement[] configElements = extension.getConfigurationElements();
		for( int i = 0, n = configElements.length; i < n; i++ )
		{
			IConfigurationElement configElement = configElements[i];
			if( ! configElement.getName().equalsIgnoreCase( "dataSource" ) )
			    continue;

			// validate that the data source element has an id
			String dataSourceId = configElement.getAttribute( "id" );	
			if( dataSourceId == null || dataSourceId.length() == 0 )
				throw new OdaException( getLocalizedMessage( OdaResources.NO_DATA_SOURCE_EXTN_ID_DEFINED ) );

			return configElement;	// expects only one such element
		}
		
		throw new OdaException( getLocalizedMessage( OdaResources.NO_DRIVER_RUNTIME_CONFIGURATION_DEFINED ) );
	}
	
	/*
	 * Returns a collection of dataSet elements of the given data source extension.
	 */
	static Hashtable getDataSetElements( IExtension extension, String dataSourceElementId )
		throws OdaException
	{
		IConfigurationElement[] configElements = extension.getConfigurationElements();
		Hashtable dataSetElements = new Hashtable();
		for( int i = 0, size = configElements.length; i < size; i++ )
		{
			IConfigurationElement configElement = configElements[i];
			if( ! configElement.getName().equalsIgnoreCase( "dataSet" ) )
			    continue;
			
			// validate that the data set definition has an id
			String dataSetTypeId = configElement.getAttribute( "id" );
			if( dataSetTypeId == null || dataSetTypeId.length() == 0 )
				throw new OdaException( getLocalizedMessage( OdaResources.NO_DATA_SET_TYPE_ID_DEFINED,
															new Object[] { dataSourceElementId } ) );		
			// if duplicated data set type ids exist in the extension,  
			// only the last one applies
			dataSetElements.put( dataSetTypeId, new DataSetType( configElement ) );
		}

		if( dataSetElements.size() < 1 )
			throw new OdaException( getLocalizedMessage( OdaResources.NO_DATA_SET_TYPES_DEFINED,
														new Object[] { dataSourceElementId } ) );
		return dataSetElements;
	}
	
	/*
	 * Encapsulates the logic of finding the most appropriate
	 * display name to use for the given element.
	 */ 
	static String getElementDisplayName( IConfigurationElement dsElement )
	{
	    String displayName = dsElement.getAttribute( "defaultDisplayName" );

	    // Default to its id or name, if no display name is specified
		if ( displayName == null || displayName.length() == 0 )
		{
		    displayName = dsElement.getAttribute( "name" );
		    if ( displayName == null )  // no such attribute
			    displayName = dsElement.getAttribute( "id" );
		}
		
		return displayName;
	}

	
	static String getLocalizedMessage( int errorNumber )
	{
		ResourceManager manager = 
			ResourceCache.instance().getResources( "org.eclipse.datatools.connectivity.oda.util.OdaResources", 
												   Locale.getDefault() );
		return ( manager != null ) ? manager.getString( errorNumber ) : "";
	}
	
	static String getLocalizedMessage( int errorNumber, Object[] arguments )
	{
		return MessageFormat.format( getLocalizedMessage( errorNumber ), arguments );
	}
}
