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
 * The data type mapping of a data provider's native data type to one or more ODA 
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
    private int[] m_alternativeDataTypeCodes;
	
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
        
        if( ! getOdaTypeCodes().containsKey( toOdaTypeKey( odaScalarType ) ))
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
     * Returns the alternative ODA data type codes of the data type mapping, or 
     * an empty array if no alternative ODA data types exist.
     * @return  the alternative ODA data type codes, or an empty array if no alternative 
     *          ODA data types exist.
     */
    public int[] getAlternativeOdaDataTypeCodes()
    {
        if( m_alternativeDataTypeCodes == null )
        {
            int numCodes = m_alternativeDataTypes.length;
            m_alternativeDataTypeCodes = new int[numCodes];
            
            for( int i = 0; i < numCodes; i++ )
            {
                m_alternativeDataTypeCodes[i] = 
                    toOdaDataTypeCode( m_alternativeDataTypes[i] );
            }
        }
        return m_alternativeDataTypeCodes;
    }
    
    /**
     * A convenient method to indicate whether the ODA data provider 
     * is capable of converting this mapping's native data type 
     * to the specified ODA data type code.
     * @param odaDataTypeCode   an ODA data type code
     * @return  true if the specified ODA data type can be converted from this
     *          mapping's native data type; false otherwise.
     */
    public boolean canConvertToOdaType( int odaDataTypeCode )
    {
        // check if the specified code is the primary ODA data type 
        // defined in this native type's mapping
        if( odaDataTypeCode == getOdaScalarDataTypeCode() )
            return true;
        
        // check if the specified code is one of the alternative ODA data types
        // defined in this native type's mapping
        int[] alternateOdaTypes = getAlternativeOdaDataTypeCodes();
        for( int i = 0; i < alternateOdaTypes.length; i++ )
        {
            if( odaDataTypeCode == alternateOdaTypes[i] )
                return true;
        }
        
        return false;
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
        
        Object typeCode = 
            getOdaTypeCodes().get( toOdaTypeKey( odaDataTypeLiteral ) );
        if( typeCode != null )
            return ((Integer) typeCode).intValue();
        
        return Types.NULL;
    }
    
    /**
     * Returns the cached table that maps each ODA data type name 
     * to its corresponding data type code.
     * The data type name serves as the key in the cached table.
     */
    private static Hashtable getOdaTypeCodes()
    {
        if( sm_odaTypeCodes == null )
        {
            sm_odaTypeCodes = new Hashtable( 10 );
            
            sm_odaTypeCodes.put( toOdaTypeKey( "String" ), //$NON-NLS-1$
                                new Integer( Types.CHAR )); 
            sm_odaTypeCodes.put( toOdaTypeKey( "Integer" ), //$NON-NLS-1$ 
                                new Integer( Types.INTEGER )); 
            sm_odaTypeCodes.put( toOdaTypeKey( "Double" ), //$NON-NLS-1$  
                                new Integer( Types.DOUBLE )); 
            sm_odaTypeCodes.put( toOdaTypeKey( "Decimal" ), //$NON-NLS-1$ 
                                new Integer( Types.DECIMAL )); 
            sm_odaTypeCodes.put( toOdaTypeKey( "Date" ), //$NON-NLS-1$ 
                                new Integer( Types.DATE )); 
            sm_odaTypeCodes.put( toOdaTypeKey( "Time" ), //$NON-NLS-1$ 
                                new Integer( Types.TIME )); 
            sm_odaTypeCodes.put( toOdaTypeKey( "Timestamp" ), //$NON-NLS-1$ 
                                new Integer( Types.TIMESTAMP ));
            sm_odaTypeCodes.put( toOdaTypeKey( "Blob" ), //$NON-NLS-1$ 
                                new Integer( Types.BLOB )); 
            sm_odaTypeCodes.put( toOdaTypeKey( "Clob" ), //$NON-NLS-1$ 
                                new Integer( Types.CLOB )); 
            sm_odaTypeCodes.put( toOdaTypeKey( "Boolean" ), //$NON-NLS-1$ 
                                new Integer( Types.BOOLEAN )); 
        }
        
        return sm_odaTypeCodes;        
    }
    
    private static String toOdaTypeKey( String odaDataTypeLiteral )
    {
        assert odaDataTypeLiteral != null;
        return odaDataTypeLiteral.toLowerCase( Locale.US );
    }
    
}
