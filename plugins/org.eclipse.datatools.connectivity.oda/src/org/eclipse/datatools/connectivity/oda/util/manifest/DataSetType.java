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
import java.util.Collection;
import java.util.Hashtable;
import java.util.Properties;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * Defines a type of data set supported by the ODA data source extension. 
 * A data set definition has a unique id, display name
 * and a set of driver to ODA data type mappings.
 */
public class DataSetType
{
	private String m_id;
	private String m_displayName;
	private Hashtable m_dataTypeMappings;
	private Property[] m_properties = null;
	private Properties m_propsVisibility;
	
	DataSetType( IConfigurationElement dataSetElement ) throws OdaException
	{
		m_id = dataSetElement.getAttribute( "id" ); //$NON-NLS-1$
		assert( m_id != null );		// this check is already done by caller		
		m_displayName = ManifestExplorer.getElementDisplayName( dataSetElement );

		// dataTypeMapping elements
		m_dataTypeMappings = new Hashtable();
		IConfigurationElement[] typeMappings = dataSetElement.getChildren( "dataTypeMapping" ); //$NON-NLS-1$
		int numOfTypeMappings = typeMappings.length;
		if( numOfTypeMappings == 0 )
			throw new OdaException( Messages.bind( Messages.manifest_NO_DATA_TYPE_MAPPINGS_DEFINED,
													m_id ) );
		
		for( int i = 0; i < numOfTypeMappings; i++ )
		{
			IConfigurationElement typeMapping = typeMappings[i];
			String nativeDataTypeCode = typeMapping.getAttribute( "nativeDataTypeCode" ); //$NON-NLS-1$
			m_dataTypeMappings.put( nativeDataTypeCode, 
									new DataTypeMapping( typeMapping, m_id ) );
		}
		
		// properties element
		IConfigurationElement[] propertiesElements = dataSetElement.getChildren( "properties" ); //$NON-NLS-1$
		if ( propertiesElements.length > 0 )
		{
			// if multiple properties elements exist, use the last one
		    IConfigurationElement propertiesElement =
	            propertiesElements[ propertiesElements.length - 1 ];
		    m_properties = ExtensionManifest.getPropertyDefinitions( propertiesElement );
		    m_propsVisibility = ExtensionManifest.getPropertyVisibilities( propertiesElement );
		}
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
	 * @param nativeDataTypeCode	the native data type code.
	 * @return	the data type mapping for the native type code, or null 
	 * 			if there is no data type mapping for the native type code in 
	 * 			the data set type.
	 */
	public DataTypeMapping getDataTypeMapping( int nativeDataTypeCode )
	{
		String typeCode = Integer.toString( nativeDataTypeCode );
		return (DataTypeMapping) m_dataTypeMappings.get( typeCode );
	}
	
	/**
	 * Returns the data type mappings for the data set type, or an 
	 * empty array if no mappings exist.
	 * @return	the data type mappings for this data set type, or an 
	 * 			empty array if no mappings exist.
	 */
	public DataTypeMapping[] getDataTypeMappings()
	{
		Collection typeMappings = m_dataTypeMappings.values();
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
	 * Returns an array of Property instances that represent
	 * the properties defined by this data set element.
	 * The collection includes both top-level properties and
	 * those in a group.
	 * @return	an array of property definitions; 
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

}
