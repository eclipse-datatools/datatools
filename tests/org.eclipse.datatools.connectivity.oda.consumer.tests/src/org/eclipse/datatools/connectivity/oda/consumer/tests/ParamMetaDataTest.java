/*
 *************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.tests;

import java.sql.Types;

import org.eclipse.datatools.connectivity.oda.IAdvancedQuery;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.testdriver.TestParamMetaDataImpl;

public class ParamMetaDataTest extends ConnectionTest 
{
	private IQuery m_query = null;
	private IAdvancedQuery m_advQuery = null;
	private IParameterMetaData m_paramMetaData;
	private final static int NATIVETYPE_TABLE_CODE = TestParamMetaDataImpl.NATIVETYPE_TABLE_CODE;

	protected void setUp() throws Exception
	{
		super.setUp( );
	}

	protected void tearDown() throws Exception
	{
		if ( m_query != null )
			m_query.close();
		
		if ( m_advQuery != null )
			m_advQuery.close();
		
		super.tearDown();
	}
	
    public final void testGetParameterCount() throws OdaException
    {
    	prepareComplexQuery();
    	
        assertTrue( m_paramMetaData.getParameterCount() == 9 );
    }
    
    public final void testGetParameterName() throws OdaException
    {
        prepareComplexQuery();
        
        assertNull( m_paramMetaData.getParameterName( 1 ) );
    }

    public final void testGetParameterMode() throws OdaException
    {
    	prepareAdvancedQuery();
    	
    	// Test getting parameter mode for a few parameters.
    	assertTrue( m_paramMetaData.getParameterMode( 1 ) == IParameterMetaData.parameterModeIn );
    	assertTrue( m_paramMetaData.getParameterMode( 3 ) == IParameterMetaData.parameterModeOut );
    }

    public final void testGetParameterType() throws OdaException
    {        	
    	prepareComplexQuery();
    	
    	// Test getting parameter type for some parameters.
    	assertTrue( m_paramMetaData.getParameterType( 1 ) == Types.DECIMAL );
    	assertTrue( m_paramMetaData.getParameterType( 2 ) == Types.DATE );
    	assertTrue( m_paramMetaData.getParameterType( 3 ) == Types.DOUBLE );
    	assertTrue( m_paramMetaData.getParameterType( 4 ) == Types.INTEGER );
    	assertTrue( m_paramMetaData.getParameterType( 5 ) == Types.CHAR );
    	assertTrue( m_paramMetaData.getParameterType( 6 ) == Types.TIME );
    	assertTrue( m_paramMetaData.getParameterType( 7 ) == Types.TIMESTAMP );
        assertTrue( m_paramMetaData.getParameterType( 8 ) == Types.BOOLEAN );
        assertTrue( m_paramMetaData.getParameterType( 9 ) == Types.JAVA_OBJECT );
    	
    	prepareAdvancedQuery();
    	
    	// Test getting parameter type for some parameters.
    	assertTrue( m_paramMetaData.getParameterType( 1 ) == Types.STRUCT );
    	assertTrue( m_paramMetaData.getParameterType( 2 ) == NATIVETYPE_TABLE_CODE );
    	assertTrue( m_paramMetaData.getParameterType( 4 ) == Types.BLOB );
    	assertTrue( m_paramMetaData.getParameterType( 6 ) == Types.CLOB );
    }
    	
    public final void testGetParameterTypeName() throws OdaException
    {
    	prepareComplexQuery();
    	
    	// Test getting parameter type name for some parameters.
    	assertTrue( m_paramMetaData.getParameterTypeName( 1 ).equals( "BCD" ) );
    	assertTrue( m_paramMetaData.getParameterTypeName( 2 ).equals( "DATE" ) );
    	assertTrue( m_paramMetaData.getParameterTypeName( 3 ).equals( "DOUBLE" ) );
    	assertTrue( m_paramMetaData.getParameterTypeName( 4 ).equals( "INT" ) );
    	assertTrue( m_paramMetaData.getParameterTypeName( 5 ).equals( "CHAR" ) );
    	assertTrue( m_paramMetaData.getParameterTypeName( 6 ).equals( "TIME" ) );
    	assertTrue( m_paramMetaData.getParameterTypeName( 7 ).equals( "TIMESTAMP" ) );
        assertTrue( m_paramMetaData.getParameterTypeName( 8 ).equals( "BOOLEAN" ) );
        assertTrue( m_paramMetaData.getParameterTypeName( 9 ).equals( "JAVAOBJECT" ) );
   	
    	prepareAdvancedQuery();
    	
    	// Test getting parameter type for some parameters.
    	assertTrue( m_paramMetaData.getParameterTypeName( 1 ) == "STRUCT" );
    	assertTrue( m_paramMetaData.getParameterTypeName( 2 ) == "TABLE" );
    	assertTrue( m_paramMetaData.getParameterTypeName( 4 ) == "BLOB" );
    	assertTrue( m_paramMetaData.getParameterTypeName( 6 ) == "CLOB" );
    }

    public final void testGetPrecision() throws OdaException
    {
    	prepareComplexQuery();
    	
    	// Test getting parameter precision for some parameters.
        assertTrue( m_paramMetaData.getPrecision( 1 ) == 10 );
        assertTrue( m_paramMetaData.getPrecision( 2 ) == -1 );
    }

    public final void testGetScale() throws OdaException
    {
    	prepareComplexQuery();
    	
    	// Test getting parameter precision for some parameters.
        assertTrue( m_paramMetaData.getScale( 1 ) == 2 );
        assertTrue( m_paramMetaData.getScale( 2 ) == -1 );
    }

    public final void testIsNullable( int param ) throws OdaException
    {
    	prepareComplexQuery();
    	
    	// Test getting parameter precision for some parameters.
        assertTrue( m_paramMetaData.isNullable( 1 ) == 2 );
        assertTrue( m_paramMetaData.isNullable( 2 ) == -1 );
    }
    
    private void prepareComplexQuery() throws OdaException
    {
    	m_query = getConnection().newQuery( null );
    	m_query.prepare( "Complex Query" );
    	m_paramMetaData = m_query.getParameterMetaData();    	
    }
    
    private void prepareAdvancedQuery() throws OdaException
    {
    	m_advQuery = ( IAdvancedQuery ) getConnection().newQuery( null );
    	m_advQuery.prepare( "Advanced Query" );
    	m_paramMetaData = m_advQuery.getParameterMetaData();       	
    }
}
