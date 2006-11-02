/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.oda.flatfile.tests;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.Connection;
import org.eclipse.datatools.connectivity.oda.flatfile.FlatFileQuery;

import junit.framework.TestCase;

/**
 * Base class for testing Query implementation.
 */

public abstract class QueryTestBase extends TestCase
{

	protected Connection connection = null;
	protected Properties prop = null;
	protected IQuery statement = null;
	protected IQuery statement_noTypeLine = null;
	protected IQuery statement_noColumnNames = null;

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		setUpwithDefaultProperties( );
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
	public QueryTestBase( ) throws OdaException
	{
		TestUtil.createTestFile( getDelimiter( ) );
	}

	/**
	 * 
	 * @param resultSet
	 * @param includeTypeLine
	 * @throws OdaException
	 */
	public void executeQueryTest( IResultSet resultSet, boolean includeTypeLine )
			throws OdaException
	{
		assertEquals( 3, resultSet.getMetaData( ).getColumnCount( ) );
		assertEquals( includeTypeLine ? "INT" : "STRING",
				resultSet.getMetaData( ).getColumnTypeName( 1 ).toUpperCase( ) );
		assertEquals( includeTypeLine ? "TIMESTAMP" : "STRING",
				resultSet.getMetaData( ).getColumnTypeName( 2 ).toUpperCase( ) );
		assertEquals( includeTypeLine ? "STRING" : "STRING",
				resultSet.getMetaData( )
						.getColumnTypeName( 3 )
						.toUpperCase( )
						.toUpperCase( ) );
		int id = 0;
		while ( resultSet.next( ) )
		{
			assertEquals( resultSet.getRow( ), id++ );
		}
	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testStatement( ) throws OdaException
	{
		try
		{
			new FlatFileQuery( null, null, null, null, true, true );

			fail( "Should not arrive here" );
		}
		catch ( OdaException e )
		{
		}

	}

	/**
	 * 
	 * 
	 */
	public void testPrepare( )
	{
		// Try to parse an invalid string
		try
		{
			statement.prepare( "adfasf" );
			fail("Should not arrive here.");
		}
		catch ( OdaException e )
		{
		}

		try
		{
			statement.prepare( "select Int0_col from table1"
					+ getSuffix( ) + getExtension( ) + ",table2" + getSuffix( )
					+ getExtension( ) );
			fail("Should not arrive here.");
		}
		catch ( OdaException e )
		{
		}

		// Try to parse a valid input command
		try
		{
			statement.prepare( " select INT0_col ,  int1_col as   Integer from table1"
					+ getSuffix( ) + getExtension( ) + " " );
		}
		catch ( OdaException e )
		{
			e.printStackTrace( );
			assertTrue( false );
		}

		try
		{
			statement.prepare( " select * from table2"
					+ getSuffix( ) + getExtension( ) );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}

		// Try to parse an invalid input command
		try
		{
			statement.prepare( "select INT0_COL ,C from table1"
					+ getSuffix( ) + getExtension( ) );
			fail("Should not arrive here.");
		}
		catch ( OdaException e )
		{
		}

		// Try to parse an invalid input command
		try
		{
			statement.prepare( "select INT0_COL from table12" );
			fail("Should not arrive here.");
		}
		catch ( OdaException e )
		{
		}

		try
		{
			statement.prepare( "select \"\"Column_1\"\"\" from table8"
					+ getSuffix( ) + getExtension( ) );
			fail("Should not arrive here.");
		}
		catch ( OdaException e )
		{
		}

		try
		{
			statement.prepare( "select \"\\\"Column_1\\\"\\\"\" , \" Column_2,C2\" from table8"
					+ getSuffix( ) + getExtension( ) );
		}
		catch ( OdaException e )
		{
			e.printStackTrace( );
			assertTrue( false );
		}
		
		try
		{
			statement.prepare( "select \"Column_3, \\\", C3,\\\\\" from table8"
					+ getSuffix( ) + getExtension( ) );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}
		
		// Try to parse an invalid flatfile with one double quote in flatfile column name
		try
		{
			statement.prepare( "select * from table9"
					+ getSuffix( ) + getExtension( ) );
			
			fail("Should not arrive here.");
		}
		catch ( OdaException e )
		{
			
		}

		// re-setUp the connection properties where the column names are
		// indicated as none
		try
		{
			setUpWithNoColumnNameSpecified( );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}

		// Try to parse an invalid query text
		try
		{
			statement_noColumnNames.prepare( " select INT0_col ,  int1_col from table7"
					+ getSuffix( ) + getExtension( ) );
			fail("Should not arrive here.");
		}
		catch ( OdaException e )
		{
		}

		//Try to parse an valid query
		try
		{
			statement_noColumnNames.prepare( " select COLUMN_1 from table7"
					+ getSuffix( ) + getExtension( ) );
		}
		catch ( OdaException e )
		{
			assert( false );
		}

		// Try to parse a valid query text
		try
		{
			statement_noColumnNames.prepare( " select * from table7"
					+ getSuffix( ) + getExtension( ) );
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
	public void testExecuteQuery( ) throws OdaException
	{
		this.statement.prepare( " select int0_col ,timestamp_col , string_col from table1"
				+ getSuffix( ) + getExtension( ) );
		IResultSet resultSet = statement.executeQuery( );
		executeQueryTest( resultSet, true );

		this.statement_noTypeLine.prepare( "select int0_col ,timestamp_col , string_col from table4"
				+ getSuffix( ) + getExtension( ) );
		resultSet = statement_noTypeLine.executeQuery( );
		executeQueryTest( resultSet, false );

		setUpWithNoColumnNameSpecified( );

		this.statement_noColumnNames.prepare( "select * from table7"
				+ getSuffix( ) + getExtension( ) );
		resultSet = statement_noColumnNames.executeQuery( );
		executeQueryTest( resultSet, false );
	}

	/**
	 * 
	 * 
	 * 
	 */
	public void testCSVBufferReader( )
	{
		try
		{
			statement.prepare( "select * from table3"
					+ getSuffix( ) + getExtension( ) );
			IResultSet resultSet = statement.executeQuery( );
			char b[] = {
					'2', '0', '0', '5', '-', '\r', '0', '1', '-', '3', '0'
			};
			String str = new String( b );
			if ( resultSet.next( ) )
			{
				assertEquals( resultSet.getString( 1 ),
						"I'm, really, a lovely ,nice , \"STRING\"" );
				assertEquals( resultSet.getString( 2 ), str );
			}
		}
		catch ( OdaException e )
		{
			fail( "Should not arrive here.");
		}
	}

	// setup the connection properties where the existance of column names is
	// left default
	protected void setUpwithDefaultProperties( ) throws OdaException
	{
		connection = new Connection( );
		prop = new Properties( );
		prop.setProperty( CommonConstants.CONN_HOME_DIR_PROP, TestUtil.getHomeDir( ) );
		prop.setProperty( CommonConstants.CONN_CHARSET_PROP, TestUtil.DATASET );
		prop.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
				getDelimiterName( ) );
		connection.open( prop );

		Connection connection_noTypeLine = new Connection( );
		prop.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP, "No" );
		connection_noTypeLine.open( prop );

		statement = connection.newQuery( "FLATFILE" );
		statement_noTypeLine = connection_noTypeLine.newQuery( "FLATFILE" );
	}

	// setup the connection properties where no column names are specified in
	// the CSV file
	protected void setUpWithNoColumnNameSpecified( ) throws OdaException
	{
		prop.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
				CommonConstants.INC_COLUMN_NAME_NO );
		connection.open( prop );

		Connection connection_noTypeLine = new Connection( );
		prop.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP, "No" );
		connection_noTypeLine.open( prop );

		Connection connection_noColumnNames = new Connection( );
		connection_noColumnNames.open( prop );

		statement = connection.newQuery( "FLATFILE" );
		statement_noTypeLine = connection_noTypeLine.newQuery( "FLATFILE" );
		statement_noColumnNames = connection_noColumnNames.newQuery( "FLATFILE" );
	}

	// setup the connection properties where column names are specified in the
	// CSV file
	protected void setUpwithColunmNameSpecified( ) throws OdaException
	{
		prop.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
				CommonConstants.INC_COLUMN_NAME_YES );
		connection.open( prop );

		Connection connection_noTypeLine = new Connection( );
		prop.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP, "No" );
		connection_noTypeLine.open( prop );

		statement = connection.newQuery( "FLATFILE" );
		statement_noTypeLine = connection_noTypeLine.newQuery( "FLATFILE" );
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
