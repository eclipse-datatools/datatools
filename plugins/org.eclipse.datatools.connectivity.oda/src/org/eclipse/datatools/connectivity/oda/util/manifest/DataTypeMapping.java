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
import java.util.Hashtable;
import java.util.Locale;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * A single mapping from a data provider's native data type to one or more ODA 
 * data types. A single native data type must be mapped to a primary ODA scalar 
 * data type. The driver can optionally provide a list of alternate ODA data types 
 * to which it is capable of converting the native data type.
 */
public class DataTypeMapping
{
    private static Hashtable sm_odaTypeCodes;
    
	private int m_nativeTypeCode;
	private String m_nativeType;
	private String m_odaScalarType;
	private String[] m_alternativeDataTypes;
	
	DataTypeMapping( IConfigurationElement dataTypeMapping,
					 String dataSetTypeName ) throws OdaException
	{
		
		m_nativeType = dataTypeMapping.getAttribute( "nativeDataType" ); //$NON-NLS-1$
		if( m_nativeType == null )
			throw new OdaException( Messages.bind( Messages.manifest_NO_NATIVE_TYPE_NAME_DEFINED,
													dataSetTypeName ) );
		
		String nativeDataTypeCode = dataTypeMapping.getAttribute( "nativeDataTypeCode" ); //$NON-NLS-1$
		if( nativeDataTypeCode == null )
			throw new OdaException( Messages.bind( Messages.manifest_NO_NATIVE_TYPE_CODE_DEFINED,
													m_nativeType, dataSetTypeName ) );
		
		try
		{
			m_nativeTypeCode = Integer.parseInt( nativeDataTypeCode );
		}
		catch( NumberFormatException ex )
		{
			throw new OdaException( Messages.bind( Messages.manifest_INVALID_NATIVE_TYPE_CODE_VALUE,
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
    
    protected DataTypeMapping( int nativeTypeCode, String nativeType, 
                                String odaScalarType, 
                                String[] alternativeDataTypes )
    {
        m_nativeTypeCode = nativeTypeCode;
        m_nativeType = nativeType;
        m_odaScalarType = odaScalarType;
        m_alternativeDataTypes = alternativeDataTypes;
    }
	
	private void sanityCheckOdaScalarType( String odaScalarType, boolean isForAlternatives )
		throws OdaException
	{
		if( odaScalarType == null )
			throw new OdaException( isForAlternatives ? 
					Messages.bind( Messages.manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_2, m_nativeType ) :
					Messages.bind( Messages.manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_1, m_nativeType ) );
			
		if( ! odaScalarType.equalsIgnoreCase( "Date" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Double" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Integer" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "String" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Time" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Timestamp" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Decimal" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Blob" ) && //$NON-NLS-1$
			! odaScalarType.equalsIgnoreCase( "Clob" ) ) //$NON-NLS-1$
			throw new OdaException( Messages.bind( Messages.manifest_INVALID_ODA_SCALAR_DATA_TYPE_VALUE,
													odaScalarType, m_nativeType ) );
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
	 * Returns the primary ODA scalar data type that the native data type maps to
	 * @return	the primary ODA scalar data type.
	 */
	public String getOdaScalarDataType()
	{
		return m_odaScalarType;
	}

    /**
     * Returns the primary ODA scalar data type code
     * that the native data type maps to.
     * @return  the primary ODA scalar data type code.
     */
    public int getOdaScalarDataTypeCode()
    {
        return toOdaDataTypeCode( m_odaScalarType );
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
    
    /**
     * Converts an ODA data type literal value to its
     * corresponding code value.
     * @param odaDataTypeLiteral    a literal value of an ODA data type 
     * @return  corresponding ODA data type code value,
     *          or Types.NULL if specified literal value is
     *          not recognized
     */
    public static int toOdaDataTypeCode( String odaDataTypeLiteral )
    {
        if( odaDataTypeLiteral == null ||
            odaDataTypeLiteral.length() == 0 )
            return Types.NULL;
            
        Locale caseLocl = Locale.US;
        if( sm_odaTypeCodes == null )
        {
            sm_odaTypeCodes = new Hashtable( 10 );
            
            sm_odaTypeCodes.put( "String".toLowerCase( caseLocl ), //$NON-NLS-1$
                                new Integer( Types.CHAR )); 
            sm_odaTypeCodes.put( "Integer".toLowerCase( caseLocl ), //$NON-NLS-1$ 
                                new Integer( Types.INTEGER )); 
            sm_odaTypeCodes.put( "Double".toLowerCase( caseLocl ), //$NON-NLS-1$  
                                new Integer( Types.DOUBLE )); 
            sm_odaTypeCodes.put( "Decimal".toLowerCase( caseLocl ), //$NON-NLS-1$ 
                                new Integer( Types.DECIMAL )); 
            sm_odaTypeCodes.put( "Date".toLowerCase( caseLocl ), //$NON-NLS-1$ 
                                new Integer( Types.DATE )); 
            sm_odaTypeCodes.put( "Time".toLowerCase( caseLocl ), //$NON-NLS-1$ 
                                new Integer( Types.TIME )); 
            sm_odaTypeCodes.put( "Timestamp".toLowerCase( caseLocl ), //$NON-NLS-1$ 
                                new Integer( Types.TIMESTAMP ));
            sm_odaTypeCodes.put( "Blob".toLowerCase( caseLocl ), //$NON-NLS-1$ 
                                new Integer( Types.BLOB )); 
            sm_odaTypeCodes.put( "Clob".toLowerCase( caseLocl ), //$NON-NLS-1$ 
                                new Integer( Types.CLOB )); 
        }
        
        Object typeCode = 
            sm_odaTypeCodes.get( odaDataTypeLiteral.toLowerCase( caseLocl ) );
        if( typeCode != null )
            return ((Integer) typeCode).intValue();
        
        return Types.NULL;
    }

}
