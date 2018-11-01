/*
 *************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.tests;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

public class ResultSetMetaDataTest extends QueryTest
{
	private IQuery m_query = null;
	private IResultSetMetaData m_resultSetMetaData;

	protected void setUp() throws Exception
	{
		super.setUp( );
		m_query = getQuery();
		
		if ( ! getName().equals( "testExecuteQuery" ) &&
				! getName().equals( "testFindInParameter" ) &&
				! getName().equals( "testGetParameterMetaData" ) &&
				! getName().equals( "testSetAndGetSortSpec" ) &&
				! getName().equals( "testGetResultSetMetaDataFromQuery" ) &&
				! getName().equals( "testSetParametersByName" ) &&
				! getName().equals( "testSetParametersByPos" ) )
		{
			m_query.prepare( "Simple Query" );
			m_resultSetMetaData = m_query.getMetaData();
		}
	}

	protected void tearDown() throws Exception
	{
		m_query.close();
		
		super.tearDown();
	}

	public final void testGetColumnName( ) throws Exception
	{
		checkColumnNames( m_resultSetMetaData );
	}

	public final void testGetColumnName1( ) throws Exception
	{
		IResultSetMetaData metadata = getMetaDataFromResult( );

		checkColumnNames( metadata );
	}
	
	public final void testGetColumnLabel( ) throws Exception
	{
		checkColumnLabels( m_resultSetMetaData );
	}

	public final void testGetColumnLabel1( ) throws Exception
	{
		IResultSetMetaData metadata = getMetaDataFromResult( );

		checkColumnLabels( metadata );
	}

	public final void testGetColumnType( ) throws Exception
	{
		checkColumnTypes( m_resultSetMetaData );
	}

	public final void testGetColumnType1( ) throws Exception
	{
		IResultSetMetaData metadata = getMetaDataFromResult( );

		checkColumnTypes( metadata );
	}
	
	public final void testGetColumnTypeName( ) throws Exception
	{
		checkColumnTypeNames( m_resultSetMetaData );
	}

	public final void testGetColumnTypeName1( ) throws Exception
	{
		IResultSetMetaData metadata = getMetaDataFromResult( );

		checkColumnTypeNames( metadata );
	}
	
	public final void testGetColumnDisplayLength( ) throws Exception
	{
		checkColumnDisplayLengths( m_resultSetMetaData );
	}

	public final void testGetColumnDisplayLength1( ) throws Exception
	{
		IResultSetMetaData metadata = getMetaDataFromResult( );

		checkColumnDisplayLengths( metadata );
	}
	
	public final void testGetColumnPrecision( ) throws Exception
	{
		checkColumnPrecision( m_resultSetMetaData );
	}

	public final void testGetColumnPrecision1( ) throws Exception
	{
		IResultSetMetaData metadata = getMetaDataFromResult( );

		checkColumnPrecision( metadata );
	}
	
	public final void testGetColumnScale( ) throws Exception
	{
		checkColumnScale( m_resultSetMetaData );
	}

	public final void testGetColumnScale1( ) throws Exception
	{
		IResultSetMetaData metadata = getMetaDataFromResult( );

		checkColumnScale( metadata );
	}
	
	public final void testGetColumnIsNullable( ) throws Exception
	{
		checkColumnIsNullable( m_resultSetMetaData );
	}

	public final void testGetColumnIsNullable1( ) throws Exception
	{
		IResultSetMetaData metadata = getMetaDataFromResult( );

		checkColumnIsNullable( metadata );
	}
	
	public final void testGetColumnCount( ) throws OdaException
	{
		checkColumnCount( m_resultSetMetaData );
	}

	public final void testGetColumnCount1( ) throws Exception
	{
		IResultSetMetaData metadata = getMetaDataFromResult( );

		checkColumnCount( metadata );
	}

	private IResultSetMetaData getMetaDataFromResult( ) throws OdaException
	{
		IResultSet resultSet = m_query.executeQuery();
		return resultSet.getMetaData( );
	}

	private void checkColumnCount( IResultSetMetaData md ) throws OdaException
	{
		assertEquals( 13, md.getColumnCount() );
	}
	
	private void checkColumnNames( IResultSetMetaData md ) throws OdaException
	{
		assertTrue( md.getColumnName( 1 ).equals( "BigDecimalCol" ) );
		assertTrue( md.getColumnName( 2 ).equals( "BlobCol1" ) );
		assertTrue( md.getColumnName( 3 ).equals( "BlobCol2" ) );
		assertTrue( md.getColumnName( 4 ).equals( "ClobCol1" ) );
		assertTrue( md.getColumnName( 5 ).equals( "ClobCol2" ) );
		assertTrue( md.getColumnName( 6 ).equals( "DateCol" ) );
		assertTrue( md.getColumnName( 7 ).equals( "DoubleCol" ) );
		assertTrue( md.getColumnName( 8 ).equals( "IntCol" ) );
		assertTrue( md.getColumnName( 9 ).equals( "StringCol" ) );
		assertTrue( md.getColumnName( 10 ).equals( "TimeCol" ) );
		assertTrue( md.getColumnName( 11 ).equals( "TimestampCol" ) );		
	}
	
	private void checkColumnLabels( IResultSetMetaData md ) throws OdaException
	{
		assertTrue( md.getColumnLabel( 1 ).equals( "BigDecimalLabel" ) );
		assertTrue( md.getColumnLabel( 2 ).equals( "BlobLabel1" ) );
		assertTrue( md.getColumnLabel( 3 ).equals( "BlobLabel2" ) );
		assertTrue( md.getColumnLabel( 4 ).equals( "ClobLabel1" ) );
		assertTrue( md.getColumnLabel( 5 ).equals( "ClobLabel2" ) );
		assertTrue( md.getColumnLabel( 6 ).equals( "DateLabel" ) );
		assertTrue( md.getColumnLabel( 7 ).equals( "DoubleLabel" ) );
		assertTrue( md.getColumnLabel( 8 ).equals( "IntLabel" ) );
		assertTrue( md.getColumnLabel( 9 ).equals( "StringLabel" ) );
		assertTrue( md.getColumnLabel( 10 ).equals( "TimeLabel" ) );
		assertTrue( md.getColumnLabel( 11 ).equals( "TimestampLabel" ) );		
	}
	
	private void checkColumnTypes( IResultSetMetaData md ) throws OdaException
	{
		assertTrue( md.getColumnType( 1 ) == 3 );
		assertTrue( md.getColumnType( 2 ) == 97 );
		assertTrue( md.getColumnType( 3 ) == 97 );
		assertTrue( md.getColumnType( 4 ) == 98 );
		assertTrue( md.getColumnType( 5 ) == 98 );
		assertTrue( md.getColumnType( 6 ) == 91 );
		assertTrue( md.getColumnType( 7 ) == 8 );
		assertTrue( md.getColumnType( 8 ) == 4 );
		assertTrue( md.getColumnType( 9 ) == 12 );	
		assertTrue( md.getColumnType( 10 ) == 92 );	
		assertTrue( md.getColumnType( 11 ) == 93 );	
	}
	
	private void checkColumnTypeNames( IResultSetMetaData md ) throws OdaException
	{
		assertTrue( md.getColumnTypeName( 1 ).equals( "BCD" ) );
		assertTrue( md.getColumnTypeName( 2 ).equals( "BLOB" ) );
		assertTrue( md.getColumnTypeName( 3 ).equals( "BLOB" ) );
		assertTrue( md.getColumnTypeName( 4 ).equals( "CLOB" ) );
		assertTrue( md.getColumnTypeName( 5 ).equals( "CLOB" ) );
		assertTrue( md.getColumnTypeName( 6 ).equals( "DATE" ) );
		assertTrue( md.getColumnTypeName( 7 ).equals( "DOUBLE" ) );
		assertTrue( md.getColumnTypeName( 8 ).equals( "INT" ) );
		assertTrue( md.getColumnTypeName( 9 ).equals( "CHAR" ) );
		assertTrue( md.getColumnTypeName( 10 ).equals( "TIME" ) );
		assertTrue( md.getColumnTypeName( 11 ).equals( "TIMESTAMP" ) );		
	}
	
	private void checkColumnDisplayLengths( IResultSetMetaData md ) throws OdaException
	{
		assertTrue( md.getColumnDisplayLength( 1 ) == 13 );
		assertTrue( md.getColumnDisplayLength( 2 ) == 50 );
		assertTrue( md.getColumnDisplayLength( 3 ) == 50 );
		assertTrue( md.getColumnDisplayLength( 4 ) == 100 );
		assertTrue( md.getColumnDisplayLength( 5 ) == 100 );
		assertTrue( md.getColumnDisplayLength( 6 ) == 10 );
		assertTrue( md.getColumnDisplayLength( 7 ) == 10 );	
		assertTrue( md.getColumnDisplayLength( 8 ) == 11 );	
		assertTrue( md.getColumnDisplayLength( 9 ) == 20 );	
		assertTrue( md.getColumnDisplayLength( 10 ) == 17 );	
		assertTrue( md.getColumnDisplayLength( 11 ) == 17 );	
	}
	
	private void checkColumnPrecision( IResultSetMetaData md ) throws OdaException
	{
		assertTrue( md.getPrecision( 1 ) == 10 );
		assertTrue( md.getPrecision( 2 ) == -1 );
		assertTrue( md.getPrecision( 3 ) == -1 );
		assertTrue( md.getPrecision( 4 ) == -1 );
		assertTrue( md.getPrecision( 5 ) == -1 );
		assertTrue( md.getPrecision( 6 ) == -1 );
		assertTrue( md.getPrecision( 7 ) == -1 );
		assertTrue( md.getPrecision( 8 ) == 10 );
		assertTrue( md.getPrecision( 9 ) == -1 );	
		assertTrue( md.getPrecision( 10 ) == -1 );	
		assertTrue( md.getPrecision( 11 ) == -1 );	
	}
	
	private void checkColumnScale( IResultSetMetaData md ) throws OdaException
	{
		assertTrue( md.getScale( 1 ) == 2 );
		assertTrue( md.getScale( 2 ) == -1 );
		assertTrue( md.getScale( 3 ) == -1 );
		assertTrue( md.getScale( 4 ) == -1 );
		assertTrue( md.getScale( 5 ) == -1 );
		assertTrue( md.getScale( 6 ) == -1 );
		assertTrue( md.getScale( 7 ) == -1 );	
		assertTrue( md.getScale( 8 ) == -1 );	
		assertTrue( md.getScale( 9 ) == -1 );
		assertTrue( md.getScale( 10 ) == -1 );	
		assertTrue( md.getScale( 11 ) == -1 );	
	}
	
	private void checkColumnIsNullable( IResultSetMetaData md ) throws OdaException
	{
		assertTrue( md.isNullable( 1 ) == IResultSetMetaData.columnNoNulls );
		assertTrue( md.isNullable( 2 ) == IResultSetMetaData.columnNoNulls );
		assertTrue( md.isNullable( 3 ) == IResultSetMetaData.columnNullable );
		assertTrue( md.isNullable( 4 ) == IResultSetMetaData.columnNoNulls );
		assertTrue( md.isNullable( 5 ) == IResultSetMetaData.columnNullable );
		assertTrue( md.isNullable( 6 ) == IResultSetMetaData.columnNoNulls );
		assertTrue( md.isNullable( 7 ) == IResultSetMetaData.columnNoNulls );
		assertTrue( md.isNullable( 8 ) == IResultSetMetaData.columnNoNulls );
		assertTrue( md.isNullable( 9 ) == IResultSetMetaData.columnNoNulls );	
		assertTrue( md.isNullable( 10 ) == IResultSetMetaData.columnNoNulls );	
		assertTrue( md.isNullable( 11 ) == IResultSetMetaData.columnNoNulls );	
	}
}