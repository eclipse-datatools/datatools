/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.oda.flatfile.tests;

import java.sql.Types;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.Connection;
import org.eclipse.datatools.connectivity.oda.flatfile.ResultSetMetaData;

import junit.framework.TestCase;

/**
 * 
 */

public abstract class ResultSetMetaDataTestBase extends TestCase
{

	protected IQuery statement = null;
  
    static final int INT = Types.INTEGER;
    static final int DOUBLE = Types.DOUBLE;
    static final int STRING = Types.VARCHAR;
    static final int DATE = Types.DATE;
    static final int TIME = Types.TIME;
    static final int TIMESTAMP = Types.TIMESTAMP;
    static final int BLOB = Types.BLOB;
    static final int CLOB = Types.CLOB;
    static final int BIGDECIMAL = Types.NUMERIC; 

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		Connection connection = new Connection( );
		Properties prop = new Properties( );
		prop.setProperty( CommonConstants.CONN_HOME_DIR_PROP, TestUtil.getHomeDir( ) );
		prop.setProperty( CommonConstants.CONN_CHARSET_PROP, TestUtil.DATASET );
		prop.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
				getDelimiterName( ) );
		prop.setProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP, CommonConstants.TRAIL_NULL_COLS_NO );
		connection.open( prop );
		statement = connection.newQuery( "FLATFILE" );
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown( ) throws Exception
	{
		super.tearDown( );
	}

	/**
	 * 
	 * @throws OdaException
	 */
	public ResultSetMetaDataTestBase( ) throws OdaException
	{
		TestUtil.createTestFile( getDelimiter( ) );
	}

	/**
	 * 
	 * 
	 */
	public void testResultSetMetaData( )
	{
		try
		{
			new ResultSetMetaData( null );
			assertTrue( false );
		}
		catch ( OdaException e )
		{
			
		}
		
	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testGetColumnCount( ) throws OdaException
	{
		statement.prepare( "select INT0_COL,STRING_COL from table1"
				+ getSuffix( ) + getExtension( ) );
		IResultSetMetaData resultSetMetaData = statement.getMetaData( );
		assertEquals( 2, resultSetMetaData.getColumnCount( ) );

		statement.prepare( "select INT0_COL,STRING_COL,STRING_COL,BLOB_COL from table1"
				+ getSuffix( ) + getExtension( ) );
		resultSetMetaData = statement.getMetaData( );
		assertEquals( 4, resultSetMetaData.getColumnCount( ) );

		statement.prepare( "select * from table2"
				+ getSuffix( ) + getExtension( ) );
		resultSetMetaData = statement.getMetaData( );
		assertEquals( 10, resultSetMetaData.getColumnCount( ) );

	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testGetColumnLabel( ) throws OdaException
	{
		statement.prepare( "select STRING_COL as string ,DATE_COL as date from table1"
				+ getSuffix( ) + getExtension( ) );
		IResultSetMetaData resultSetMetaData = statement.getMetaData( );
		try
		{
			assertEquals( "string", resultSetMetaData.getColumnLabel( 1 ) );
			assertEquals( "date", resultSetMetaData.getColumnLabel( 2 ) );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}

		statement.prepare( "select * from table2"
				+ getSuffix( ) + getExtension( ) );
		resultSetMetaData = statement.getMetaData( );
		try
		{
			assertEquals( "INT0_COL", resultSetMetaData.getColumnLabel( 1 ) );
			assertEquals( "DOUBLE0_COL", resultSetMetaData.getColumnLabel( 2 ) );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}

		statement.prepare( "select INT0_COL AS INTEGER, DOUBLE1_COL , DOUBLE0_COL AS NULL, Date_col from table2"
				+ getSuffix( ) + getExtension( ) );
		resultSetMetaData = statement.getMetaData( );
		try
		{
			assertEquals( "INTEGER", resultSetMetaData.getColumnLabel( 1 )
					.toUpperCase( ) );
			assertEquals( "DOUBLE1_COL", resultSetMetaData.getColumnLabel( 2 )
					.toUpperCase( ) );
			assertEquals( "NULL", resultSetMetaData.getColumnLabel( 3 )
					.toUpperCase( ) );
			assertEquals( "DATE_COL", resultSetMetaData.getColumnLabel( 4 )
					.toUpperCase( ) );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}

	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testGetColumnName( ) throws OdaException
	{
		statement.prepare( "select STRING_COL,DAtE_COL from table1"
				+ getSuffix( ) + getExtension( ) );
		IResultSetMetaData resultSetMetaData = statement.getMetaData( );
		try
		{
			assertEquals( "DAtE_COL", resultSetMetaData.getColumnName( 2 ) );
			assertEquals( "STRING_COL", resultSetMetaData.getColumnName( 1 ) );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}

		try
		{
			resultSetMetaData.getColumnName( 0 );
			assertTrue( false );
		}
		catch ( OdaException e )
		{
		}

		try
		{
			resultSetMetaData.getColumnName( Integer.MAX_VALUE );
			assertTrue( false );
		}
		catch ( OdaException e )
		{
		}

	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testGetColumnType( ) throws OdaException
	{
		statement.prepare( "select * from table2"
				+ getSuffix( ) + getExtension( ) );
		IResultSetMetaData resultSetMetaData = statement.getMetaData( );
		try
		{
			assertEquals( INT, resultSetMetaData.getColumnType( 1 ) );
			assertEquals( DOUBLE, resultSetMetaData.getColumnType( 2 ) );
			assertEquals( STRING, resultSetMetaData.getColumnType( 3 ) );
			assertEquals( DATE, resultSetMetaData.getColumnType( 4 ) );
			assertEquals( TIME, resultSetMetaData.getColumnType( 5 ) );
			assertEquals( TIMESTAMP,
					resultSetMetaData.getColumnType( 6 ) );
			assertEquals( BLOB, resultSetMetaData.getColumnType( 7 ) );
			assertEquals( INT, resultSetMetaData.getColumnType( 8 ) );
			assertEquals( DOUBLE, resultSetMetaData.getColumnType( 9 ) );
			assertEquals( BIGDECIMAL,
					resultSetMetaData.getColumnType( 10 ) );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}

		try
		{
			resultSetMetaData.getColumnType( 0 );
			assertTrue( false );
		}
		catch ( OdaException e )
		{
		}

		try
		{
			resultSetMetaData.getColumnType( Integer.MAX_VALUE );
			assertTrue( false );
		}
		catch ( OdaException e )
		{

		}

	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testGetColumnTypeName( ) throws OdaException
	{
		statement.prepare( "select INT0_COL,INT1_COL,DOUBLE0_COL,"
				+ "DOUBLE1_COL,TIME_COL,DATE_COL,BLOB_COL,STRING_COL,BIGDECIMAL_COL from table2"
				+ getSuffix( ) + getExtension( ) );
		IResultSetMetaData resultSetMetaData = statement.getMetaData( );
		try
		{
			assertEquals( "INT", resultSetMetaData.getColumnTypeName( 1 ) );
			assertEquals( "INT", resultSetMetaData.getColumnTypeName( 2 ) );
			assertEquals( "DOUBLE", resultSetMetaData.getColumnTypeName( 3 ) );
			assertEquals( "DOUBLE", resultSetMetaData.getColumnTypeName( 4 ) );
			assertEquals( "TIME", resultSetMetaData.getColumnTypeName( 5 ) );
			assertEquals( "DATE", resultSetMetaData.getColumnTypeName( 6 ) );
			assertEquals( "BLOB", resultSetMetaData.getColumnTypeName( 7 ) );
			assertEquals( "STRING", resultSetMetaData.getColumnTypeName( 8 ) );
			assertEquals( "BIGDECIMAL", resultSetMetaData.getColumnTypeName( 9 ) );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}

		try
		{
			resultSetMetaData.getColumnTypeName( 0 );
			assertTrue( false );
		}
		catch ( OdaException e )
		{
		}

		try
		{
			resultSetMetaData.getColumnTypeName( Integer.MAX_VALUE );
			assertTrue( false );
		}
		catch ( OdaException e )
		{
		}

	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testGetPrecision( ) throws OdaException
	{
		statement.prepare( "select * from table2"
				+ getSuffix( ) + getExtension( ) );
		IResultSetMetaData resultSetMetaData = statement.getMetaData( );
		for ( int i = 1; i <= resultSetMetaData.getColumnCount( ); i++ )
		{
			assertEquals( -1, resultSetMetaData.getPrecision( i ) );
		}

	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testGetScale( ) throws OdaException
	{
		statement.prepare( "select * from table2"
				+ getSuffix( ) + getExtension( ) );
		IResultSetMetaData resultSetMetaData = statement.getMetaData( );
		for ( int i = 1; i <= resultSetMetaData.getColumnCount( ); i++ )
		{
			assertEquals( -1, resultSetMetaData.getScale( i ) );
		}

	}

	/**
	 * 
	 * @return
	 */
	protected abstract String getDelimiter( );

	protected abstract String getDelimiterName( );

	/**
	 * 
	 * @return
	 */
	protected abstract String getSuffix( );

	protected abstract String getExtension( );
}
