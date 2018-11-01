/*
 *************************************************************************
 * Copyright (c) 2004, 2010 Actuate Corporation.
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * Encapsulates access to the content of an ODA data source
 * plug-in extension manifest.
 */
public class ExtensionManifest
{
    public static final String CLASS_ATTRIBUTE_NAME = "driverClass";  //$NON-NLS-1$
    static final String ATTR_SET_THREAD_LOADER = "setThreadContextClassLoader"; //$NON-NLS-1$
    static final String ATTR_OVERRIDE_FILTERING = "overrideExplorerFiltering"; //$NON-NLS-1$

    private String m_namespace;
	private String m_dataSourceElementId;
	private String m_odaVersion;
	private String m_displayName;
	private RuntimeInterface m_runtime;
	private Hashtable<String, DataSetType> m_dataSetTypes;
	private TraceLogging m_traceLogging;
    private Property[] m_extensionDefinedProperties = null;
	private Property[] m_properties = null;
	private Properties m_propsVisibility;
    private IConfigurationElement m_dataSourceElement;
    private IExtension m_dataSourceExtn;
    private List<Relationship> m_relationships;
    private boolean m_overrideFiltering = false;

	ExtensionManifest( IExtension dataSourceExtn ) throws OdaException
    {
        init( dataSourceExtn );
    }
    
    protected ExtensionManifest()
    {
    }
    
    protected void init( IExtension dataSourceExtn ) throws OdaException
	{
        m_dataSourceElement = 
	        ManifestExplorer.getDataSourceElement( dataSourceExtn );
		assert( m_dataSourceElement != null );
		m_namespace = dataSourceExtn.getContributor().getName();
		
		// first cache the data source element's attributes
		m_dataSourceElementId = m_dataSourceElement.getAttribute( "id" ); //$NON-NLS-1$
		if( m_dataSourceElementId == null || m_dataSourceElementId.length() == 0 )
			throw new OdaException( Messages.manifest_NO_DATA_SOURCE_EXTN_ID_DEFINED );
		
		m_odaVersion = m_dataSourceElement.getAttribute( "odaVersion" ); //$NON-NLS-1$

		m_displayName = ManifestExplorer.getElementDisplayName( m_dataSourceElement );

		// runtime interface
		String driverClass = m_dataSourceElement.getAttribute( CLASS_ATTRIBUTE_NAME );
		if( driverClass == null )
			throw new OdaException( Messages.bind( Messages.manifest_NO_DRIVER_CLASS_DEFINED,
												m_dataSourceElementId ) );
				
		boolean needSetThreadContextClassLoader = ManifestUtil.getBooleanAttributeValue( 
		            m_dataSourceElement, ATTR_SET_THREAD_LOADER, false );
		m_runtime = 
			new JavaRuntimeInterface( driverClass, needSetThreadContextClassLoader, m_namespace );
		
		m_overrideFiltering = ManifestUtil.getBooleanAttributeValue( 
		            m_dataSourceElement, ATTR_OVERRIDE_FILTERING, false );
		
		// data set definition elements in the same extension
		m_dataSetTypes = ManifestExplorer.getDataSetElements( dataSourceExtn, m_dataSourceElementId );
		
		// trace logging element
		IConfigurationElement[] traceLogging = m_dataSourceElement.getChildren( "traceLogging" ); //$NON-NLS-1$
		int numOfTraceLogging = traceLogging.length;
		// if multiple trace logging configuration exist, use the last one
		if( numOfTraceLogging > 0 )
			m_traceLogging = new TraceLogging( traceLogging[ numOfTraceLogging - 1 ], m_dataSourceElementId );

		// properties element
		IConfigurationElement[] propertiesElements = m_dataSourceElement.getChildren( "properties" ); //$NON-NLS-1$
		if( propertiesElements.length > 0 )
		{
			// if multiple properties elements exist, use the last one
		    IConfigurationElement propertiesElement =
	            propertiesElements[ propertiesElements.length - 1 ];
		    
		    ArrayList<Property> extensionProps = getPropertyDefinitions( propertiesElement );		    
		    m_extensionDefinedProperties = (Property[]) extensionProps.toArray( new Property[ extensionProps.size() ] );      

		    // appends framework-defined data source properties to extension-defined ones
		    m_properties = addDataSourceFrameworkProperties( extensionProps );
		    
		    m_propsVisibility = getPropertyVisibilities( propertiesElement );
		}
        
        // relationship element
        m_relationships = Relationship.createInstances( m_dataSourceElement );
        
        // successfully initialized
        m_dataSourceExtn = dataSourceExtn;
	}
    
    /**
     * Returns the data source extension element used to initialize this instance.
     */
    protected IExtension getDataSourceExtension()
    {
        return m_dataSourceExtn;
    }

    /* 
     * Parse and return all the extension-defined property definitions, 
     * combining both top-level and grouped properties.
     */ 
    static ArrayList<Property> getPropertyDefinitions( IConfigurationElement propertiesElement )
		throws OdaException
	{
		IConfigurationElement[] propElements = propertiesElement.getChildren( "property" ); //$NON-NLS-1$
		IConfigurationElement[] propGroupElements = propertiesElement.getChildren( "propertyGroup" ); //$NON-NLS-1$
	    int numProperties = propElements.length + propGroupElements.length;

	    ArrayList<Property> properties = new ArrayList<Property>();
	    if ( numProperties <= 0 )
	        return properties;     // returns an empty list
 
        // first convert top-level property elements defined by an extension
		for( int i = 0, size = propElements.length; i < size; i++ )
		{
			IConfigurationElement propElement = propElements[i];
			properties.add( new Property( propElement ) );
		}
        
        // next convert property elements in each group
		for( int j = 0, size2 = propGroupElements.length; j < size2; j++ )
		{
			IConfigurationElement propGroupElement = propGroupElements[j];
	        // no validation is done; up to the consumer to process
			String groupName = propGroupElement.getAttribute( "name" );			     //$NON-NLS-1$
		    String groupDisplayName = ManifestExplorer.getElementDisplayName( propGroupElement );

		    IConfigurationElement[] groupedPropElements = propGroupElement.getChildren( "property" ); //$NON-NLS-1$
			for( int i = 0, size = groupedPropElements.length; i < size; i++ )
			{
				IConfigurationElement groupedPropElement = groupedPropElements[i];
				properties.add( new Property( groupedPropElement, groupName, groupDisplayName ) );
			}
		}
		
        return properties;
	}

    /**
     * Appends framework-defined data source properties to specified collection, 
     * if not already defined.
	 * @since 3.1
     */
    private Property[] addDataSourceFrameworkProperties( ArrayList<Property> propDefinitions )
	{
        if( propDefinitions == null )
            propDefinitions = new ArrayList<Property>();
        
        if( ! containsProperty( ConnectionProfileProperty.PROFILE_NAME_PROP_KEY, propDefinitions ) )
        {
            propDefinitions.add( 
                ConnectionProfileProperty.createPropertyDefinition( 
                        ConnectionProfileProperty.PROFILE_NAME_PROP_KEY ) );
        }
        
        if( ! containsProperty( ConnectionProfileProperty.PROFILE_STORE_FILE_PATH_PROP_KEY, propDefinitions ) )
        {
            propDefinitions.add( 
                ConnectionProfileProperty.createPropertyDefinition( 
                        ConnectionProfileProperty.PROFILE_STORE_FILE_PATH_PROP_KEY ) );
        }
        
        return (Property[]) propDefinitions.toArray( new Property[ propDefinitions.size() ] );	    
	}

    /**
     * Determines whether the specified collection contains a property
     * with the specified property name.
     */
    private static boolean containsProperty( String propertyName, ArrayList<Property> properties )
    {
        if( properties.isEmpty() )
            return false;
        
        for( Property aProp : properties )
        {
            if( aProp != null && propertyName.equals( aProp.getName() ) )
                return true;
        }
        return false;
    }
    
	/*
	 * Parse and return the property visibility definitions.
	 */
	static Properties getPropertyVisibilities( IConfigurationElement propertiesElement )
		throws OdaException
	{
		// convert propertyVisibility elements to a collection
		IConfigurationElement[] propVisibilityElements = 
		    propertiesElement.getChildren( "propertyVisibility" ); //$NON-NLS-1$
		if ( propVisibilityElements.length == 0 )
		    return null;		// done
		
		Properties propsVisibility = new Properties();
		for( int i = 0, size = propVisibilityElements.length; i < size; i++ )
		{
			IConfigurationElement propVisibltyElement = propVisibilityElements[i];
			
	        // no validation is done; up to the consumer to process
			String propName = propVisibltyElement.getAttribute( "name" ); //$NON-NLS-1$
			String propVisbility = propVisibltyElement.getAttribute( "visibility" ); //$NON-NLS-1$
			propsVisibility.setProperty( propName, propVisbility );
		}
		return propsVisibility;
	}
	
	/**
     * Returns the namespace of the plugin that contributes this ODA runtime extension.
	 * @return	the plugin namespace for the ODA driver, null if the driver does not have 
	 * 			a namespace.
	 */
	public String getNamespace()
	{
		return m_namespace;
	}
	
	/**
	 * Returns the ID that uniquely identifies this 
	 * ODA data source extension in an ODA consumer application's 
	 * environment.  This is the extension that implements
	 * the org.eclipse.datatools.connectivity.oda.dataSource extension point.
	 * <br>Since each data source extension 
	 * has one and only one data source element, the element ID
	 * is used as the extension ID.
	 * @return	the data source extension ID.
	 */
	public String getExtensionID()
	{
		return getDataSourceElementID();
	}
	
	/**
	 * Returns the ID that uniquely identifies the dataSource element defined
	 * in the ODA data source extension.
	 * @return	the data source element ID.
	 */
	public String getDataSourceElementID()
	{
		return m_dataSourceElementId;
	}

    /**
     * Returns the configuration element of this extension's
     * data source element.
     * @return  a dataSource configuration element 
     */
    public IConfigurationElement getDataSourceElement()
    {
        return m_dataSourceElement;
    }
	
	/**
	 * Returns the driver installation location.
	 * @return	the driver directory.
	 * @throws IOException	if an IO error occurs.
	 */
	public URL getDriverLocation() throws IOException
	{
		// should be same as the runtime library location in this case
		return m_runtime.getLibraryLocation();
	}
	
	/**
	 * Returns the version of the <i>org.eclipse.datatools.connectivity.oda</i> 
	 * interfaces for which this driver is developed.
	 * @return	The ODA interface version.  Its format is as defined
	 * 			in the extension point schema.
	 */
	public String getOdaVersion()
	{
		return m_odaVersion;
	}

	/**
	 * Returns the display name of the data source element
	 * defined in the ODA data source extension.  
	 * Defaults to element ID if no display name is specified.
	 * It can be used by an ODA consumer application's designer tool 
	 * to display a list of ODA data source extensions.
	 * @return	The display name of the ODA data source element.
	 */
	public String getDataSourceDisplayName()
	{
	    return m_displayName;
	}
	
	/**
	 * Returns an array of DataSetType instances that
	 * represent the dataSet elements defined in
	 * this data source extension.
	 * @return	an array of data set types; may return an empty array
     *          if no dataSet elements are defined.
	 */
	public DataSetType[] getDataSetTypes()
	{
		Collection<DataSetType> dataSetTypes = m_dataSetTypes.values();
		int size = dataSetTypes.size();
		return (DataSetType[]) dataSetTypes.toArray( new DataSetType[size] );
	}
	
	/**
	 * Returns an array of ids of the dataSet elements 
	 * defined in this data source extension.
	 * @return	an array of data set type IDs; may return an empty array
     *          if no dataSet elements are defined.
	 */
	public String[] getDataSetTypeIDs()
	{
		Set<String> dataSetTypeIDs = m_dataSetTypes.keySet();
		int size = dataSetTypeIDs.size();
		return (String[]) dataSetTypeIDs.toArray( new String[size] );
	}
	
    /**
     * Returns the number of data set types defined in this ODA data source extension.
     * @return  number of data set types defined; may be zero if none is defined
     */
    public int getDataSetTypeCount()
    {
       return m_dataSetTypes.size(); 
    }
    
	/**
	 * Returns the DataSetType instance that
	 * represents the dataSet element with the given ID
	 * defined in this data source extension.
	 * If the given data set element ID is null and the data source
	 * extension supports only one data set type, that
	 * data set element will be returned by default.
	 * @param dataSetElementID	the id of the data set element.
	 * @return	the data set element definition.
	 * @throws OdaException	if there is no data set definition associated 
	 * 									with the specified data set element ID, or 
	 * 									if there are more than one data set elements 
	 * 									that match the ID.
	 */
	public DataSetType getDataSetType( String dataSetElementID ) throws OdaException
	{
		if( dataSetElementID == null )
		{
			// find default data set element and return it if found
			if( m_dataSetTypes.size() != 1 )
			    throwsIllegalArgumentOdaException( dataSetElementID );

			Collection<DataSetType> dataSetTypes = m_dataSetTypes.values();
			assert( dataSetTypes.size() == 1 );
			return (DataSetType) dataSetTypes.toArray()[0];
		}
		
		DataSetType dsType = (DataSetType) m_dataSetTypes.get( dataSetElementID );

		if( dsType == null )
		    throwsIllegalArgumentOdaException( dataSetElementID );
		
		return dsType;
	}
	
	private void throwsIllegalArgumentOdaException( String arg ) throws OdaException
	{
	    Exception illegalArg = new IllegalArgumentException( arg );
	    OdaException ex = new OdaException( illegalArg.toString() );
	    ex.initCause( illegalArg );
	    throw ex;
	}
	
	/**
	 * Returns the optional trace logging configuration for the driver.
	 * @return	the trace logging configuration, or null if no trace logging 
	 * 			configuration was specified.
	 */
	public TraceLogging getTraceLogging()
	{
		return m_traceLogging;
	}
	
	/**
	 * Returns the runtime interface configuration.
	 * @return	the runtime interface configuration.
	 */
	public RuntimeInterface getRuntimeInterface()
	{
		return m_runtime;
	}
	
	/**
     * Returns an array of Property definition instances that represent
     * all the properties defined and inherited by this data source extension.
     * The collection includes both top-level properties and
     * those in a group, and could be defined as either visible or hidden.
     * @return  an array of all property definitions; 
     *          an empty array is returned if no properties are defined.
	 */
	public Property[] getProperties()
	{
	    return getProperties( true );
	}

	/**
     * Returns an array of Property definition instances that represent
     * all the properties defined by this data source extension, plus
     * optionally include inherited ones.
     * The collection includes both top-level properties and
     * those in a group, and could be defined as either visible or hidden.
	 * @param includeInheritedProps    indicates whether to include
	 *          property definitions inherited from the ODA framework
     * @return  an array of all property definitions; 
     *          an empty array is returned if no properties are defined.
	 * @since 3.1
	 */
	public Property[] getProperties( boolean includeInheritedProps )
	{
	    Property[] props = includeInheritedProps ? 
	                        m_properties : m_extensionDefinedProperties;
        if ( props == null )
        {
            // creates an empty array to return
            props = new Property[ 0 ];
        }
        return props;
	}

    /**
     * A convenience method to return an array of Property definition instances 
     * that represent the properties defined as visible by this data source extension.
     * The collection includes both top-level properties and those in a group.
     * @return  an array of visible property definitions; 
     *          an empty array is returned if no visible properties are defined.
     */
    public Property[] getVisibleProperties()
    {
        return ManifestUtil.getVisiblePropertiesDefn( getProperties(), 
                getPropertiesVisibility());
    }

    /**
     * A convenience method to return an array of Property definition instances 
     * that represent the properties defined as hidden by this data source extension.
     * The collection includes both top-level properties and those in a group.
     * @return  an array of hidden property definitions; 
     *          an empty array is returned if no hidden properties are defined.
     */
    public Property[] getHiddenProperties()
    {
        return ManifestUtil.getHiddenPropertiesDefn( getProperties(), 
                getPropertiesVisibility());
    }
    
    /**
     * Returns the Property definition instance that matches the specified name
     * in the list of properties defined by this data source extension.
     * @param propertyName  the name of a property
     * @return  the matching Property definition, or null if no match is found.
     */
    public Property getProperty( String propertyName )
    {
        if ( propertyName == null || propertyName.length() == 0 )
            return null;
        
        Property[] props = getProperties();
        for( int i = 0; i < props.length; i++ )
        {
            if ( propertyName.equals( props[ i ].getName() ))
                return props[ i ];
        }
        
        return null;    // no matching property
    }

	/**
	 * Returns a Properties collecton of property visibilty settings.
	 * @return	Properties with the property name as key, and
	 * 			its visibility setting as value.
	 * 			An empty collection if no property visibility is defined.
	 */
	public Properties getPropertiesVisibility()
	{
	    if ( m_propsVisibility == null )
	        m_propsVisibility = new Properties();
	    return m_propsVisibility;
	}
    
    /**
     * Indicates whether this extension is defined to be deprecated.
     * @since 3.0.3
     */
    public boolean isDeprecated()
    {
        List<Relationship> replacedBy = getRelationships( Relationship.TYPE_REPLACED_BY_CODE );
        return ( replacedBy != null && ! replacedBy.isEmpty() );
    }

    /**
     * Indicates whether this extension is defined to be a wrapper.
     * @since 3.1.2
     */
    public boolean isWrapper()
    {
        List<Relationship> wrappersOf = getRelationships( Relationship.TYPE_WRAPPER_OF_CODE );
        return ( wrappersOf != null && ! wrappersOf.isEmpty() );
    }

    /**
     * Returns the related oda data source element id, if specified.
     * For backward compatibility, this returns the first related replacedBy id.
     * @return  the related oda data source element id, or 
     *          null if none is specified.
     * @since 3.0.3
     * @deprecated  As of 3.1.2, replaced by {@link #getRelationships(int)}
     */
    public String getRelatedDataSourceId()
    {
        List<Relationship> relationships = getRelationships( Relationship.TYPE_REPLACED_BY_CODE );
        if( relationships == null )
            return null;
        Relationship replacedBy = (Relationship) relationships.get( 0 );
        return ( replacedBy != null ) ? replacedBy.getRelatedId() : null;
    }

    /**
     * Returns a list of data source relationships defined with the specified type. 
     * @param relationshipType    constant for the type of relationship
     * @return  a list of specified type of relationships, or
     *          null if the specified relationshipType is not defined in this extension.
     * @see {@link Relationship.TYPE_* constants}
     * @since 3.1.2
     */
    public List<Relationship> getRelationships( int relationshipType )
    {
        if( m_relationships == null || m_relationships.isEmpty() )
            return null;
        
        Vector<Relationship> matchingRelationships = new Vector<Relationship>( m_relationships.size() );
        for( Relationship aRelationship : m_relationships )
        {
            if( aRelationship.getType() == relationshipType )
                matchingRelationships.add( aRelationship );
        }
        return matchingRelationships.isEmpty() ? null : matchingRelationships;
    }

    /**
     * Indicates whether the visibility of this extension should override that of the filtering by the
     * ODA extension explorer.
     * @return 
     * @since 3.2.2 (DTP 1.7.2)
     */
    public boolean overrideFiltering()
    {
        return m_overrideFiltering;
    }
    
}
