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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.OdaResources;

/**
 * A single mapping from a data provider's native data type to one or more ODA 
 * data types. A single native data type must be mapped to a primary ODA scalar 
 * data type. The driver can optionally provide a list of alternate ODA data types 
 * to which it is capable of converting the native data type.
 */
public class DataTypeMapping
{
	private int m_nativeTypeCode;
	private String m_nativeType;
	private String m_odaScalarType;
	private String[] m_alternativeDataTypes;
	
	DataTypeMapping( IConfigurationElement dataTypeMapping,
					 String dataSetTypeName ) throws OdaException
	{
		
		m_nativeType = dataTypeMapping.getAttribute( "nativeDataType" ); //$NON-NLS-1$
		if( m_nativeType == null )
			throw new OdaException( ManifestExplorer.getLocalizedMessage( OdaResources.NO_NATIVE_TYPE_NAME_DEFINED,
																	   new Object[] { dataSetTypeName } ) );
		
		String nativeDataTypeCode = dataTypeMapping.getAttribute( "nativeDataTypeCode" ); //$NON-NLS-1$
		if( nativeDataTypeCode == null )
			throw new OdaException( ManifestExplorer.getLocalizedMessage( OdaResources.NO_NATIVE_TYPE_CODE_DEFINED,
																	   new Object[] { m_nativeType, dataSetTypeName } ) );
		
		try
		{
			m_nativeTypeCode = Integer.parseInt( nativeDataTypeCode );
		}
		catch( NumberFormatException ex )
		{
			throw new OdaException( ManifestExplorer.getLocalizedMessage( OdaResources.INVALID_NATIVE_TYPE_CODE_VALUE,
																	   new Object[] { m_nativeType, nativeDataTypeCode, dataSetTypeName } ) );
		}

		m_odaScalarType = dataTypeMapping.getAttribute( "odaScalarDataType" ); //$NON-NLS-1$
		sanityCheckOdaScalarType( m_odaScalarType, false /* isForAlternatives */ );
		
		IConfigurationElement[] alternativeDataTypes = 
			dataTypeMapping.getChildren( "alternativeOdaDataType" ); //$NON-NLS-1$
		int length = alternativeDataTypes.length;
		m_alternativeDataTypes = new String[length];
		
		for( int i = 0; i < length; i++ )
		{
			m_alternativeDataTypes[i] = 
				alternativeDataTypes[i].getAttribute( "odaScalarDataType" ); //$NON-NLS-1$
			sanityCheckOdaScalarType( m_alternativeDataTypes[i], true /* isForAlternatives */ );
		}
	}
	
	private void sanityCheckOdaScalarType( String odaScalarType, boolean isForAlternatives )
		throws OdaException
	{
		if( odaScalarType == null )
			throw new OdaException( ManifestExplorer.getLocalizedMessage( isForAlternatives ? 
																	   OdaResources.NO_ODA_SCALAR_DATA_TYPE_DEFINED_2 :
																	   OdaResources.NO_ODA_SCALAR_DATA_TYPE_DEFINED_1,
																	   new Object[] { m_nativeType } ) );
			
		if( ! odaScalarType.equalsIgnoreCase( "Date" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Double" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Integer" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "String" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Time" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Timestamp" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Decimal" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Blob" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Clob" ) ) //$NON-NLS-1$
			throw new OdaException( ManifestExplorer.getLocalizedMessage( OdaResources.INVALID_ODA_SCALAR_DATA_TYPE_VALUE,
																	   new Object[] { odaScalarType, m_nativeType } ) );
	}
	
	/**
	 * Returns the native type name of the data type mapping.
	 * @return	the native type name.
	 */
	public String getNativeType()
	{
		return m_nativeType;
	}
	
	/**
	 * Returns the native type code of the data type mapping. 
	 * Its value must match one of the data type codes returned in the 
	 * driver's ODA interface implementation.
	 * @return	the native type code.
	 */
	public int getNativeTypeCode()
	{
		return m_nativeTypeCode;
	}
	
	/**
	 * Returns the primary ODA scalar data type which the native type maps to
	 * @return	the primary ODA scalar data type.
	 */
	public String getOdaScalarDataType()
	{
		return m_odaScalarType;
	}
	
	/**
	 * Returns the alternative ODA data types of the data type mapping, or 
	 * an empty array if no alternative ODA data types exist.
	 * @return	the alternative ODA data types, or an empty array if no alternative 
	 * 			ODA data types exist.
	 */
	public String[] getAlternativeOdaDataTypes()
	{
		return m_alternativeDataTypes;
	}
}
