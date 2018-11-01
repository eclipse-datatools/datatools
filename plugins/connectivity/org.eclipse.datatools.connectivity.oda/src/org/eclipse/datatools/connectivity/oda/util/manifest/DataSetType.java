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

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * Defines a type of data set supported by the ODA data source extension. 
 * A data set definition has a unique id, display name
 * and a set of driver-specific data type to ODA data type(s) mappings.
 */
public class DataSetType
{
	private String m_id;
	private String m_displayName;
	private Hashtable<Integer, DataTypeMapping> m_dataTypeMappingsByNativeCode;
    private Hashtable<String, DataTypeMapping> m_dataTypeMappingsByNativeName;
	private Property[] m_properties = null;
	private Properties m_propsVisibility;
    private IConfigurationElement m_configElement;
    private List<Relationship> m_relationships;
	
	DataSetType( IConfigurationElement dataSetElement ) throws OdaException
    {
        init( dataSetElement );
    }
    
    protected DataSetType()
    {
    }

    protected void init( IConfigurationElement dataSetElement ) throws OdaException
	{
		m_id = dataSetElement.getAttribute( "id" ); //$NON-NLS-1$
		assert( m_id != null );		// this check is already done by caller		
		m_displayName = ManifestExplorer.getElementDisplayName( dataSetElement );

		// dataTypeMapping elements
		m_dataTypeMappingsByNativeCode = new Hashtable<Integer, DataTypeMapping>();
        m_dataTypeMappingsByNativeName = new Hashtable<String, DataTypeMapping>();
		IConfigurationElement[] typeMappings = dataSetElement.getChildren( "dataTypeMapping" ); //$NON-NLS-1$
		int numOfTypeMappings = typeMappings.length;
		if( numOfTypeMappings == 0 )
			throw new OdaException( Messages.bind( Messages.manifest_NO_DATA_TYPE_MAPPINGS_DEFINED,
													m_id ) );
		
		for( int i = 0; i < numOfTypeMappings; i++ )
		{
			IConfigurationElement typeMapping = typeMappings[i];
            
            DataTypeMapping dataTypeMap = new DataTypeMapping( typeMapping, m_id );
			m_dataTypeMappingsByNativeCode.put( 
                    new Integer( dataTypeMap.getNativeTypeCode() ), dataTypeMap );
            m_dataTypeMappingsByNativeName.put( dataTypeMap.getNativeType(), dataTypeMap );
		}
		
		// properties element
		IConfigurationElement[] propertiesElements = dataSetElement.getChildren( "properties" ); //$NON-NLS-1$
		if ( propertiesElements.length > 0 )
		{
			// if multiple properties elements exist, use the last one
		    IConfigurationElement propertiesElement =
	            propertiesElements[ propertiesElements.length - 1 ];

		    ArrayList<Property> extensionProps = ExtensionManifest.getPropertyDefinitions( propertiesElement );
            m_properties = (Property[]) extensionProps.toArray( new Property[ extensionProps.size() ] );      

            m_propsVisibility = ExtensionManifest.getPropertyVisibilities( propertiesElement );
		}
        
        // relationship element
        m_relationships = Relationship.createInstances( dataSetElement );
        
        // successfully initialized
        m_configElement = dataSetElement;
	}
	
	/**
     * Returns the configuration element used to initialize this instance.
     */
    protected IConfigurationElement getConfigurationElement()
    {
        return m_configElement;
    }

    /**
	 * Returns the ID which uniquely identifes the type of data set among 
	 * all ODA data sets supported by the driver.
	 * @return	the data set type name.
	 */
	public String getID()
	{
		return m_id;
	}
	
	/**
	 * Returns the display name of the ODA data set type element.
	 * Defaults to element ID if no display name is specified.
	 * It can be used by the designer tool of an ODA consumer application
	 * to display a list of ODA data sets.
	 * @return	The display name of the ODA data set element.
	 */
	public String getDisplayName()
	{
	    return m_displayName;
	}
	
	/**
	 * Returns the data type mapping for the specified native data type code.
	 * @param nativeDataTypeCode	a native data type code.
     * @return  the data type mapping for the specified native type code, or null 
     *          if there is no corresponding data type mapping defined in 
     *          this data set type.
	 */
	public DataTypeMapping getDataTypeMapping( int nativeDataTypeCode )
	{
		Integer typeCode = new Integer( nativeDataTypeCode );
		return (DataTypeMapping) m_dataTypeMappingsByNativeCode.get( typeCode );
	}
    
    /**
     * Returns the data type mapping for the specified native data type name.
     * @param nativeDataTypeName    a native data type name.
     * @return  the data type mapping for the specified native type name, or null 
     *          if there is no corresponding data type mapping defined in 
     *          this data set type.
     * @since 3.0.3
     */
    public DataTypeMapping getDataTypeMapping( String nativeDataTypeName )
    {
        return (DataTypeMapping) m_dataTypeMappingsByNativeName.get( nativeDataTypeName );
    }
	
	/**
	 * Returns the data type mappings for the data set type, or an 
	 * empty array if no mappings exist.
	 * @return	the data type mappings for this data set type, or an 
	 * 			empty array if no mappings exist.
	 */
	public DataTypeMapping[] getDataTypeMappings()
	{
		Collection<DataTypeMapping> typeMappings = m_dataTypeMappingsByNativeCode.values();
		int count = typeMappings.size();
		return ( DataTypeMapping[] ) typeMappings.toArray( new DataTypeMapping[count] );
	}
    
    /**
     * Returns the primary ODA scalar data type code
     * that the specified native data type is mapped to.
     * <br>If none or unknown native data type value (0) is specified, 
     * maps to an ODA String data type by default.  
     * If no default ODA data type mapping is defined by the driver
     * for the specified native data type, returns Types.NULL
     * for unknown ODA data type.
     * @return  the primary ODA scalar data type code;
     *          may be java.sql.Types.NULL if no mapping is found.
     */
    public int getDefaultOdaDataTypeCode( int nativeDataTypeCode )
    {
        DataTypeMapping mapping = getDataTypeMapping( nativeDataTypeCode );
        if( mapping != null )
            return mapping.getOdaScalarDataTypeCode();

        // no mapping is defined by the ODA driver
        // for the specified nativeDataTypeCode,
        // maps a 0 native data type (defined as none or unknown value in
        // the oda.design model) to an ODA String data type by default
        if( nativeDataTypeCode == 0 )   
            return Types.CHAR;          

        // unknown ODA data type
        return Types.NULL;           
    }
	
	/**
	 * Returns an array of Property definition instances that represent
	 * all the properties defined by this data set element.
	 * The collection includes both top-level properties and
	 * those in a group, and could be defined as either visible or hidden.
	 * @return	an array of all property definitions; 
	 * 			an empty array is returned if no properties are defined.
	 */
	public Property[] getProperties()
	{
	    if ( m_properties == null )
	    {
	        // creates an empty array to return
	        m_properties = new Property[ 0 ];
	    }
	    return m_properties;
	}

    /**
     * A convenience method to return an array of Property definition instances 
     * that represent the properties defined as visible by this data set element.
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
     * that represent the properties defined as hidden by this data set element.
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
     * in the list of properties defined by this data set element.
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
     * Indicates whether this data set type is defined to be deprecated.
     * @return  true if this data set type is deprecated; false otherwise
     * @since 3.0.3
     */
    public boolean isDeprecated()
    {
        List<Relationship> replacedBy = getRelationships( Relationship.TYPE_REPLACED_BY_CODE );
        return ( replacedBy != null && ! replacedBy.isEmpty() );
    }

    /**
     * Indicates whether this data set type is defined to be a wrapper.
     * @since 3.1.2
     */
    public boolean isWrapper()
    {
        List<Relationship> wrappersOf = getRelationships( Relationship.TYPE_WRAPPER_OF_CODE );
        return ( wrappersOf != null && ! wrappersOf.isEmpty() );
    }

    /**
     * Gets the related oda data set element id, if specified.
     * For backward compatibility, this returns the first related replacedBy id.
     * @return  the related oda data set element id, or 
     *          null if none is specified.
     * @since 3.0.3
     * @deprecated  As of 3.1.2, replaced by {@link #getRelationships(int)}
     */
    public String getRelatedDataSetId()
    {
        List<Relationship> relationships = getRelationships( Relationship.TYPE_REPLACED_BY_CODE );
        if( relationships == null )
            return null;
        Relationship replacedBy = (Relationship) relationships.get( 0 );
        return ( replacedBy != null ) ? replacedBy.getRelatedId() : null;
    }

    /**
     * Returns a list of data set relationships defined with the specified type. 
     * @param relationshipType    constant for the type of relationship
     * @return  a list of specified type of relationships, or
     *          null if the specified relationshipType is not defined in this data set type.
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

}
