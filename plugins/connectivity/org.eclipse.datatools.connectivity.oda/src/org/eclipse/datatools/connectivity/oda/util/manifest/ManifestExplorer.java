/*
 *************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.util.manifest;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
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

	private Map<String, ExtensionManifest> m_manifestsById;  // cached copy of manifests by odaDataSourceId

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
	    {
            synchronized( ManifestExplorer.class )
            {
                if( sm_instance == null )
                {
                    sm_instance = new ManifestExplorer();
                }
            }
	    }
	    
        return sm_instance;
	}
    
    /**
     * Singleton instance release method.
     */
    public static void releaseInstance()
    {
        synchronized( ManifestExplorer.class )
        {
            sm_instance = null;
            sm_logger = null;
        }
    }
    
    static Logger getLogger()
    {
        if( sm_logger == null )
        {
            synchronized( ManifestExplorer.class )
            {
                if( sm_logger == null )
                    sm_logger = Logger.getLogger( PACKAGE_NAME );
            }
        }
        return sm_logger;
    }
	
	private ManifestExplorer()
	{
	}
    
    /**
     * Refresh the manifest explorer, and allows it to get
     * the latest ODA extension manifests.
     */
    public void refresh()
    {
        if( m_manifestsById == null || m_manifestsById.isEmpty() )
            return;     // done; nothing to reset

        // reset the cached collection of ODA extension manifest instances
        m_manifestsById.clear();
    }

    private Map<String, ExtensionManifest> getCachedManifests()
    {
    	if( m_manifestsById == null )
    	{
            synchronized( this )
            {
                if( m_manifestsById == null )
                    m_manifestsById = Collections.synchronizedMap( new HashMap<String, ExtensionManifest>() );
            }
    	}
    	return m_manifestsById;
    }
	
	/**
	 * Returns a collection of identifiers of all ODA data source extensions.
	 * The extension's data source element ID and display name
	 * are stored as the key and value in the returned Properties instance.
     * The returned collection includes all matching extensions, including those
     * with no dataSet elements defined.
     * @return  a <code>Properties</code> containing the id and display name 
     *          of all ODA data source extensions.  
     *          May be an empty collection if no data source extensions are found.
	 */
	public Properties getDataSourceIdentifiers()
	{
        // does not hide any ODA extensions 
        return getDataSourceIdentifiers( null );
	}
	
	/**
     * Returns a collection of identifiers of all ODA data source extensions that meet
     * the specified filter criteria.
     * The extension's data source element ID and display name
     * are stored as the key and value in the returned Properties instance.
     * @param collectionFilter  specifies the types of extension to exclude in
     *                          the returned collection; 
     *                          may be null if no filtering is needed
     * @return  a <code>Properties</code> containing the id and display name 
     *          of all ODA data source extensions that meet the specified filter criteria.  
     *          May be an empty collection if no data source extensions are found.
     * @since 3.1.2 (DTP 1.6)
	 */
    public Properties getDataSourceIdentifiers( Filter dataSourceFilter )
    {
        ExtensionManifest[] odaManifests = getExtensionManifests( dataSourceFilter );
        Properties extensionIds = new Properties();
        for( int i = 0; i < odaManifests.length; i++ )
        {
            ExtensionManifest odaManifest = odaManifests[i];
            
            String dataSourceId = odaManifest.getDataSourceElementID();
            String dataSourceDisplayName = odaManifest.getDataSourceDisplayName();
            extensionIds.setProperty( dataSourceId, dataSourceDisplayName );
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

	    throw new IllegalArgumentException( 
	            Messages.bind( Messages.manifest_CANNOT_FIND_EXTENSION, dataSourceId ));
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
     * @throws IllegalArgumentException if no argument(s) are specified
	 */
	public ExtensionManifest getExtensionManifest( String dataSourceId, 
	        									   String extensionPoint ) 
		throws OdaException
	{
	    if ( dataSourceId == null || dataSourceId.length() == 0 )
			throw new IllegalArgumentException( dataSourceId );
	    
	    if ( extensionPoint == null || extensionPoint.length() == 0 )
			throw new IllegalArgumentException( extensionPoint );
	    
        // first check if specified dataSourceId's manifest
        // is already in cache, and use it
        ExtensionManifest aManifest =
            (ExtensionManifest) getCachedManifests().get( dataSourceId );
        if( aManifest != null )
            return aManifest;
	
	    IExtension[] extensions = getExtensions( extensionPoint );
	    
	    IExtension dataSourceExtn = findExtension( dataSourceId, extensions );
	
        if ( dataSourceExtn == null )    // not found
            return null;
        
        // found extension 
        
        return getExtensionManifest( dataSourceExtn );
	}
    
    /**
     * Returns the extension configuration information found
     * in the plugin manifest file for the specified data source
     * extension. 
     * Applies the matching manifest from the cached collection, 
     * if exists.  Otherwise, instantiates a new manifest, and saves in cache.
     * @param dataSourceExtn    data source extension object
     * @return                  the extension manifest information
     * @throws OdaException     if the extension manifest is invalid
     */
    private ExtensionManifest getExtensionManifest( IExtension dataSourceExtn )
        throws OdaException
    {
        if( dataSourceExtn == null )
            throw new OdaException( 
                    new IllegalArgumentException( Messages.manifest_nullArgument ));

        IConfigurationElement dataSourceElement = 
            getDataSourceElement( dataSourceExtn );
        assert( dataSourceElement != null );
        
        String dataSourceId = dataSourceElement.getAttribute( "id" ); //$NON-NLS-1$

        // first check if specified extension's dataSourceId manifest
        // is already in cache, and use it
        ExtensionManifest aManifest = 
            (ExtensionManifest) getCachedManifests().get( dataSourceId );
        if( aManifest == null )
        {                   
            // validate and create its extension manifest first before locking the cached collection
            ExtensionManifest newManifest = new ExtensionManifest( dataSourceExtn );
            
            // save it in the cached collection in a synchronized manner
            aManifest = addToCachedManifests( dataSourceId, newManifest );
        }
        
        return aManifest;
    }

    /**
     * Adds specified extension manifest to the synchronized collection in cache.
     * This method expects a new manifest is first created, to minimize the locking on 
     * the cached collection.
     * @param dataSourceId  the oda data source id that serves as the mapping key
     * @param manifest		new manifest to add to collection iff data source id mapping does not exist yet
     * @return  the cached extension manifest kept in the cached collection
     */
    private ExtensionManifest addToCachedManifests( String dataSourceId, ExtensionManifest manifest )
    {
        Map<String, ExtensionManifest> manifestMap = getCachedManifests();
        ExtensionManifest cachedManifest;
        synchronized( manifestMap )
        {
            // in case another thread has added to the same key in between this checking, use
            // the currently cached value
            cachedManifest = (ExtensionManifest) manifestMap.get( dataSourceId );
            if( cachedManifest == null )
            {                                   
                // save the specified default manifest in cached collection
                cachedManifest = manifest;
                manifestMap.put( dataSourceId, cachedManifest );
            }
        }
        return cachedManifest;
    }
    
	/**
	 * Returns an array of DTP ODA dataSource extension configuration information  
	 * found in corresponding plugin manifest file.
     * Returns an empty array if there are no data source extensions found.
	 * Invalid data source extension definitions are ignored.
	 * @return	an <code>ExtensionManifest</code> array containing 
	 * 			the definition of all matching ODA data source extensions.
	 */
	public ExtensionManifest[] getExtensionManifests()
	{
        return getExtensionManifests( DTP_ODA_EXT_POINT );
    }
    
    /**
     * Returns an array of DTP ODA dataSource extension configuration information
     * of those extensions that implement the DTP ODA extension point and
     * meet the filter criteria.  
     * @param collectionFilter  specifies the types of extensions to exclude in
     *                          the returned collection; 
     *                          may be null if no filtering is needed
     * @return  an <code>ExtensionManifest</code> array containing 
     *          the definition of all matching ODA data source extensions.
     * @since 3.1.2 (DTP 1.6)
     */
    public ExtensionManifest[] getExtensionManifests( Filter collectionFilter )
    {
        return getExtensionManifests( DTP_ODA_EXT_POINT, collectionFilter );
    }

    /**
     * Returns an array of ODA dataSource extension configuration information
     * of those extensions that implement the specified extension point.  
     * Returns an empty array if there are no data source extensions found.
     * Invalid data source extension definitions are ignored.
     * @param extensionPoint    name of an ODA data source extension point  
     * @return  an <code>ExtensionManifest</code> array containing 
     *          the definition of all matching ODA data source extensions.
     */
    public ExtensionManifest[] getExtensionManifests( String extensionPoint )
    {
        // for backward compatibility, hide those extensions 
        // that have no data set elements defined, but
        // include deprecated and wrapper extensions
        Filter aFilter = createFilter();
        aFilter.setMissingDataSetTypesFilter( true );
        aFilter.setDeprecatedFilter( false );
        aFilter.setHideWrapper( false );
        return getExtensionManifests( extensionPoint, aFilter );
    }
    
    /**
     * Returns an array of DTP ODA dataSource extension configuration information  
     * found in corresponding plugin manifest file.
     * The argument specifies whether to include all matching extensions, regardless of
     * whether it has defined no dataSet element, such as a driver adapter plugin.
     * @param includesAllExtensions     true to return all matching extensions,
     *              including those with no valid dataSet element defined;
     *              false to include only those matching extensions
     *              with at least one valid dataSet element defined 
     * @return an <code>ExtensionManifest</code> array containing 
     *          the definition of all matching ODA data source extensions.
     * @deprecated  As of DTP 1.0, replaced by 
     *      {@link #getExtensionManifests(String, org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer.Filter)}
     */
    public ExtensionManifest[] getExtensionManifests( boolean includesAllExtensions )
    {
        return getExtensionManifests( DTP_ODA_EXT_POINT, includesAllExtensions );
	}
    
    /**
     * Returns an array of ODA dataSource extension configuration information
     * of those extensions that implement the specified extension point.  
     * The argument specifies whether to include all matching extensions, regardless of
     * whether it has defined no dataSet element, such as a driver adapter plugin.
     * @param extensionPoint    name of an ODA data source extension point  
     * @param includesAllExtensions     true to return all matching extensions,
     *              including those with no valid dataSet element defined;
     *              false to include only those matching extensions
     *              with at least one valid dataSet element defined 
     * @return  an <code>ExtensionManifest</code> array containing 
     *          the definition of all matching ODA data source extensions.
     * @deprecated  As of DTP 1.0, replaced by 
     *      {@link #getExtensionManifests(String, org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer.Filter)}
     */
    public ExtensionManifest[] getExtensionManifests( String extensionPoint, 
                                                boolean includesAllExtensions )
    {
        Filter aFilter = createFilter();
        aFilter.setMissingDataSetTypesFilter( includesAllExtensions == false );
        aFilter.setDeprecatedFilter( false );
        return getExtensionManifests( extensionPoint, aFilter );
    }
    
    /**
     * Returns an array of ODA dataSource extension configuration information
     * of those extensions that implement the specified extension point and
     * meet the filter criteria.  
     * @param extensionPoint    name of an ODA data source extension point  
     * @param collectionFilter  specifies the types of extensions to exclude in
     *                          the returned collection; 
     *                          may be null if no filtering is needed
     * @return  an <code>ExtensionManifest</code> array containing 
     *          the definition of all matching ODA data source extensions.
     * @since 3.0.3
     */
    public ExtensionManifest[] getExtensionManifests( String extensionPoint, 
                                                        Filter collectionFilter )
    {
		IExtension[] extensions = getExtensions( extensionPoint );
		int length = ( extensions == null ) ? 
						0 : extensions.length;
		ArrayList<ExtensionManifest> manifestList = new ArrayList<ExtensionManifest>( length );
		for( int i = 0; i < length; i++ )
		{
			IExtension dataSourceExtn = extensions[i];	
			try
			{
                ExtensionManifest manifest = getExtensionManifest( dataSourceExtn );
                
                boolean includeExtension = true;
                
                // applies filter options, if specified and not overriden by extension
                if( collectionFilter != null && ! manifest.overrideFiltering() )
                {
                    /* excludes this extension manifest if the specified filter argument  
                     * indicates to filter out those without a data set element
                     */
                    if( collectionFilter.isMissingDataSetTypesFilterOn() && 
                        manifest.getDataSetTypeCount() <= 0 )
                        includeExtension = false;
                    
                    /* excludes this extension manifest if the filter argument
                     * indicates to filter out deprecated extensions
                     */
                    else if( collectionFilter.isDeprecatedFilterOn() &&
                        manifest.isDeprecated() )
                        includeExtension = false;
                    
                    /* excludes this extension manifest if the filter argument
                     * indicates to hide wrapper extensions
                     */
                    else if( collectionFilter.isHideWrapperFilterOn() &&
                        manifest.isWrapper() )
                        includeExtension = false;
               }
                
                if( includeExtension )
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
        return ManifestUtil.getNamedElement( extension, "dataSource", "id" );  //$NON-NLS-1$ //$NON-NLS-2$
    }
	
	/*
	 * Returns a collection of dataSet elements of the given data source extension.
     * May return an empty collection if no dataSet elements are defined.
	 */
	static Hashtable<String, DataSetType> getDataSetElements( IExtension extension, 
            String dataSourceElementId )
		throws OdaException
	{
        IConfigurationElement[] configElements =
            ManifestUtil.getNamedElements( extension, "dataSet", "id" ); //$NON-NLS-1$ //$NON-NLS-2$
		Hashtable<String, DataSetType> dataSetElements = new Hashtable<String, DataSetType>();
        
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

    /**
     * Instantiates a new Filter object for the manifest explorer to apply when
     * retrieving a collection of ODA data source extension manifests.
     * @return  a new Filter object
     * @since 3.0.3
     */
    public static Filter createFilter()
    {
        return getInstance().new Filter();
    }
    
    /**
     * Filtering options for the manifest explorer to apply when
     * retrieving a collection of ODA data source extension manifests.
     * @since 3.0.3
     */
    public class Filter
    {
        private boolean m_noDataSetTypes;   // extensions with no data set types defined
        private boolean m_hideDeprecated;       // deprecated extensions
        private boolean m_hideWrapper;          // wrapper extensions
        
        Filter()
        {
            m_noDataSetTypes = false;
            m_hideDeprecated = true;
            m_hideWrapper = true;
        }
        
        /**
         * Specifies whether to hide extensions with no data set types defined.
         * @param hide   true to hide, false otherwise.
         */
        public void setMissingDataSetTypesFilter( boolean hide )
        {
            m_noDataSetTypes = hide;
        }
        
        /**
         * Specifies whether to hide deprecated extensions.
         * @param hide   true to hide, false otherwise.
         */
        public void setDeprecatedFilter( boolean hide )
        {
            m_hideDeprecated = hide;
        }

        /**
         * Specifies whether to hide wrapper extensions.
         * @param hide   true to hide, false otherwise.
         * @since 3.1.2 (DTP 1.6)
         */
        public void setHideWrapper( boolean hide )
        {
            m_hideWrapper = hide;
        }

        /**
         * Indicates whether to hide extensions with no data set types defined.
         */
        public boolean isMissingDataSetTypesFilterOn()
        {
            return m_noDataSetTypes;
        }

        /**
         * Indicates whether to hide deprecated extensions.
         */
        public boolean isDeprecatedFilterOn()
        {
            return m_hideDeprecated;
        }        

        /**
         * Indicates whether to hide wrapper extensions.
         * @since 3.1.2 (DTP 1.6)
         */
        public boolean isHideWrapperFilterOn()
        {
            return m_hideWrapper;
        }
    }
    
}
