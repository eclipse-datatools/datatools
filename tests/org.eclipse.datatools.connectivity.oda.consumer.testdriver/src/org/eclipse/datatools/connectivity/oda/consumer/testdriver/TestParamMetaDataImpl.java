/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.testdriver;

import java.sql.Types;
import java.util.ArrayList;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;


public class TestParamMetaDataImpl implements IParameterMetaData 
{
	ArrayList m_params = null;
	public final static int QUERY_TYPE_SIMPLE = 0;
	public final static int QUERY_TYPE_COMPLEX = 1;	
	public final static int QUERY_TYPE_ADVANCED = 2;
	public final static int NATIVETYPE_TABLE_CODE = Types.OTHER;
	
    @SuppressWarnings("unchecked")
    public TestParamMetaDataImpl( int queryType )
    {
    	m_params = new ArrayList();
    	
    	if ( queryType == QUERY_TYPE_SIMPLE )
    	{
	    	m_params.add( new TestParameterMetaData( "StringParamIn", Types.VARCHAR, "CHAR", -1, -1, 
	    		IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "DateParamIn", Types.DATE, "DATE", -1, -1, 
		    	IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
		    
		    // Notice that here the data type is unknown in the plugin.xml.
		    m_params.add( new TestParameterMetaData( "DummyParamIn", Types.NULL, "Unknown type", -1, -1, 
		    	IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );    		
    	}
    	else if ( queryType == QUERY_TYPE_COMPLEX )
    	{
	    	// Input parameters only.
	    	m_params.add( new TestParameterMetaData( "BigDecimalParamIn", Types.DECIMAL, "BCD", 10, 2, 
	    		IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "DateParamIn", Types.DATE, "DATE", -1, -1, 
		    	IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "DoubleParamIn", Types.DOUBLE, "DOUBLE", -1, -1, 
		    	IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "IntegerParamIn", Types.INTEGER, "INT", 10, -1, 
		    	IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "StringParamIn", Types.CHAR, "CHAR", -1, -1, 
		    	IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "TimeParamIn", Types.TIME, "TIME", -1, -1, 
		    	IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "TimestampParamIn", Types.TIMESTAMP, "TIMESTAMP", -1, -1, 
		    	IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullableUnknown ) );
            m_params.add( new TestParameterMetaData( "BooleanParamIn", Types.BOOLEAN, "BOOLEAN", -1, -1, 
                IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullableUnknown ) );
            m_params.add( new TestParameterMetaData( "ObjectParamIn", Types.JAVA_OBJECT, "JAVAOBJECT", -1, -1, 
                    IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullable ) );
    	}
    	else if ( queryType == QUERY_TYPE_ADVANCED )
    	{
    		// Input parameters.
		    m_params.add( new TestParameterMetaData( "StructParamIn", Types.STRUCT, "STRUCT", -1, -1, 
			    	IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "TableParamIn", NATIVETYPE_TABLE_CODE, "TABLE", -1, -1, 
			    	IParameterMetaData.parameterModeIn, IParameterMetaData.parameterNullableUnknown ) );
		    
	    	// Output parameters.
	    	m_params.add( new TestParameterMetaData( "BigDecimalParamOut", Types.DECIMAL, "BCD", 10, 2, 
	    		IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
	    	m_params.add( new TestParameterMetaData( "BlobParam1Out", Types.BLOB, "BLOB", -1, -1, 
	    		IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNoNulls ) );
	    	m_params.add( new TestParameterMetaData( "BlobParam2Out", Types.BLOB, "BLOB", -1, -1, 
	    		IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullable ) );
	    	m_params.add( new TestParameterMetaData( "ClobParam1Out", Types.CLOB, "CLOB", -1, -1, 
	    		IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNoNulls ) );
	    	m_params.add( new TestParameterMetaData( "ClobParam2Out", Types.CLOB, "CLOB", -1, -1, 
	    		IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullable ) );
		    m_params.add( new TestParameterMetaData( "DateParamOut", Types.DATE, "DATE", -1, -1, 
		    	IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "DoubleParamOut", Types.DOUBLE, "DOUBLE", -1, -1, 
		    	IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "IntegerParamOut", Types.INTEGER, "INT", 10, -1, 
		    	IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "StringParamOut", Types.CHAR, "CHAR", -1, -1, 
		    	IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "TimeParamOut", Types.TIME, "TIME", -1, -1,
		    	IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "TimestampParamOut", Types.TIMESTAMP, "TIMESTAMP", -1, -1,
		    	IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
		    m_params.add( new TestParameterMetaData( "StructParamOut", Types.STRUCT, "STRUCT", -1, -1, 
			    IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
            m_params.add( new TestParameterMetaData( "BooleanParamOut", Types.BOOLEAN, "BOOLEAN", -1, -1, 
                IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
            m_params.add( new TestParameterMetaData( "ObjectParamOut", Types.JAVA_OBJECT, "OBJECT", -1, -1, 
                    IParameterMetaData.parameterModeOut, IParameterMetaData.parameterNullableUnknown ) );
    	}
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterCount()
     */
    public int getParameterCount() throws OdaException
    {
        return m_params.size();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterMode(int)
     */
    public int getParameterMode( int index ) throws OdaException
    {
    	return getParameter( index ).getMode();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterName(int)
     */
    public String getParameterName( int param ) throws OdaException
    {
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterType(int)
     */
    public int getParameterType( int index ) throws OdaException
    {        	
    	return getParameter( index ).getType();
    }
    	

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterTypeName(int)
     */
    public String getParameterTypeName( int index ) throws OdaException
    {
    	return getParameter( index ).getTypeName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getPrecision(int)
     */
    public int getPrecision( int index ) throws OdaException
    {
    	return getParameter( index ).getPrecision();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getScale(int)
     */
    public int getScale( int index ) throws OdaException
    {
    	return getParameter( index ).getScale();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#isNullable(int)
     */
    public int isNullable( int param ) throws OdaException
    {
        return IParameterMetaData.parameterNullableUnknown;
    }
    
    public int findInParameter( String name ) throws OdaException
    {
    	for( int i = 0; i < m_params.size(); i++ )
    	{
    		TestParameterMetaData param = ( TestParameterMetaData ) m_params.get( i );
    		if ( param.getName().equals( name ) && 
    				( param.getMode() == IParameterMetaData.parameterModeIn || 
    				  param.getMode() == IParameterMetaData.parameterModeInOut ) )
    			return i + 1;
    	}
    	
    	return 0;
    }
    
    public int findOutParameter( String name ) throws OdaException
    {
    	for( int i = 0; i < m_params.size(); i++ )
    	{
    		TestParameterMetaData param = ( TestParameterMetaData ) m_params.get( i );
    		if ( param.getName().equals( name ) && 
    				( param.getMode() == IParameterMetaData.parameterModeOut || 
    				  param.getMode() == IParameterMetaData.parameterModeInOut ) )
    			return i + 1;
    	}
    	
    	return 0;
    }
    
    private TestParameterMetaData getParameter( int index ) throws OdaException
    {
    	int paramCount = getParameterCount();
    	
    	if ( index < 1 || index > paramCount )
    		throw new OdaException( "Invalid parameter index: " + index );    
    	
    	return ( TestParameterMetaData ) m_params.get( index - 1 );
    }
    
    private class TestParameterMetaData
    {
    	private String m_name;
    	private int m_type;
    	private String m_typeName;
    	private int m_precision;
    	private int m_scale;
    	private int m_mode;
    	private int m_isNullable;
    	
    	TestParameterMetaData( String name, int type, String typeName, int precision, 
    			int scale, int mode, int isNullable )
    	{
    		m_name = name;
    		m_type = type;
    		m_typeName = typeName;
    		m_precision = precision;
    		m_scale = scale;
    		m_mode = mode;
    		m_isNullable = isNullable;
    	}
    	
    	String getName()
    	{
    		return m_name;
    	}
    	
    	int getType()
    	{
    		return m_type;
    	}
    	
    	String getTypeName()
    	{
    		return m_typeName;
    	}
    	
    	int getPrecision()
    	{
    		return m_precision;
    	}
    	
    	int getScale()
    	{
    		return m_scale;
    	}
    	
    	int getMode()
    	{
    		return m_mode;
    	}
    	
    	int getIsNullable()
    	{
    		return m_isNullable;
    	}
    }
}
