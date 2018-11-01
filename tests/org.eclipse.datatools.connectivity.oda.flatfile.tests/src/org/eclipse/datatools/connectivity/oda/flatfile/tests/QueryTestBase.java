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

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.Connection;
import org.eclipse.datatools.connectivity.oda.flatfile.FlatFileQuery;
import org.eclipse.datatools.connectivity.oda.flatfile.util.querytextutil.QueryTextUtil;

import junit.framework.TestCase;

/**
 * Base class for testing Query implementation.
 */

public abstract class QueryTestBase extends TestCase
{

	protected Connection connection = null;
	protected Properties defaltProp = null;
	protected Properties propWithNoTypeLine = null;
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
	public void executeQueryTest( IResultSet resultSet, boolean includeTypeLine, boolean validateCols )
			throws OdaException
	{
		if (validateCols)
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
		}
		
		int id = 1;
		while ( resultSet.next( ) )
		{
			assertEquals( resultSet.getRow( ), id++ );
		}
	}
	
	public void executeQueryTest( IResultSet resultSet, boolean includeTypeLine )
			throws OdaException
	{
		executeQueryTest( resultSet, includeTypeLine, true );
	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testStatement( ) throws OdaException
	{
		try
		{
			new FlatFileQuery( null );

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
		
		try
		{
			statement.prepare( "select * from table10"
					+ getSuffix( ) + getExtension( ) );
			fail( "Should not arrive here." );
		}
		catch ( OdaException e )
		{
		}

		try
		{
			statement.prepare( "select * from table11"
					+ getSuffix( ) + getExtension( ) );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}
		
		try
		{
			statement.prepare( "select * from table12"
					+ getSuffix( ) + getExtension( ) );
		}
		catch ( OdaException e )
		{
			assertTrue( false );
		}
		
		try
		{
			statement.prepare( "select * from table13"
					+ getSuffix( ) + getExtension( ) );
			fail( "Should not arrive here." );
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
		defaltProp = new Properties( );
		defaltProp.setProperty( CommonConstants.CONN_HOME_DIR_PROP, TestUtil.getHomeDir( ) );
		defaltProp.setProperty( CommonConstants.CONN_CHARSET_PROP, TestUtil.DATASET );
		defaltProp.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
				getDelimiterName( ) );
		defaltProp.setProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP, CommonConstants.TRAIL_NULL_COLS_NO );
		connection.open( defaltProp );
		statement = connection.newQuery( "FLATFILE" );
		
		Connection connection_noTypeLine = new Connection( );
		propWithNoTypeLine = new Properties( );
		propWithNoTypeLine.setProperty( CommonConstants.CONN_HOME_DIR_PROP, TestUtil.getHomeDir( ) );
		propWithNoTypeLine.setProperty( CommonConstants.CONN_CHARSET_PROP, TestUtil.DATASET );
		propWithNoTypeLine.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
				getDelimiterName( ) );
		propWithNoTypeLine.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP, "No" );
		propWithNoTypeLine.setProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP, CommonConstants.TRAIL_NULL_COLS_NO );
		connection_noTypeLine.open( propWithNoTypeLine );

		statement_noTypeLine = connection_noTypeLine.newQuery( "FLATFILE" );
	}

	// setup the connection properties where no column names are specified in
	// the CSV file
	protected void setUpWithNoColumnNameSpecified( ) throws OdaException
	{
		defaltProp.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
				CommonConstants.INC_COLUMN_NAME_NO );
		connection.open( defaltProp );

		Connection connection_noTypeLine = new Connection( );
		defaltProp.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP, "No" );
		connection_noTypeLine.open( defaltProp );

		Connection connection_noColumnNames = new Connection( );
		connection_noColumnNames.open( defaltProp );

		statement = connection.newQuery( "FLATFILE" );
		statement_noTypeLine = connection_noTypeLine.newQuery( "FLATFILE" );
		statement_noColumnNames = connection_noColumnNames.newQuery( "FLATFILE" );
	}

	// setup the connection properties where column names are specified in the
	// CSV file
	protected void setUpwithColunmNameSpecified( ) throws OdaException
	{
		defaltProp.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
				CommonConstants.INC_COLUMN_NAME_YES );
		connection.open( defaltProp );

		Connection connection_noTypeLine = new Connection( );
		defaltProp.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP, "No" );
		connection_noTypeLine.open( defaltProp );

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
	
	/**
	 * Test query parsing logic.
	 */
//	public void testCompliateNames( )
//	{
//		try
//		{
//			setUpwithColunmNameSpecified();
//		}
//		catch ( OdaException ex )
//		{
//			ex.printStackTrace( );
//			fail( ex.getMessage( ) );
//		}
//		
//		for (String tab : TestUtil.COMPLICATE_TAB_NAMES)
//		{
//			try
//			{
//				String query = "select "
//					+ "\"" + TestUtil.COMPLICATE_TAB_COLNAMES[0] + "\",\""
//					+ TestUtil.COMPLICATE_TAB_COLNAMES[1] + "\",\""
//					+ TestUtil.COMPLICATE_TAB_COLNAMES[2] + "\",\""
//					+ TestUtil.COMPLICATE_TAB_COLNAMES[3] + "\" from "
//					+ "\"" + tab + getSuffix( ) + getExtension( ) + "\"";
//				
//				String[] queryMetaData = QueryTextUtil.getQueryMetaData( query );
//				
//				assertTrue( tab.equalsIgnoreCase( queryMetaData[2].substring( 0,
//						tab.length( ) ) ) );
//				
//				String[] cols = queryMetaData[0].split( "," );
//				for ( int i = 0; i < cols.length; i++ )
//				{
//					assertTrue( TestUtil.COMPLICATE_TAB_COLNAMES[i].equalsIgnoreCase( QueryTextUtil.getUnQuotedName( cols[i] ) ) );
//				}
//				
//				statement_noTypeLine.prepare( query );
//				IResultSet resultSet = statement_noTypeLine.executeQuery( );
//				
//				assertEquals( 4, resultSet.getMetaData( ).getColumnCount( ) );
//				assertTrue( TestUtil.COMPLICATE_TAB_COLNAMES[0].equalsIgnoreCase( resultSet.getMetaData( )
//						.getColumnName( 1 ) ) );
//				assertTrue( TestUtil.COMPLICATE_TAB_COLNAMES[1].equalsIgnoreCase( resultSet.getMetaData( )
//						.getColumnName( 2 ) ) );
//				assertTrue( TestUtil.COMPLICATE_TAB_COLNAMES[2].equalsIgnoreCase( resultSet.getMetaData( )
//						.getColumnName( 3 ) ) );
//				assertTrue( TestUtil.COMPLICATE_TAB_COLNAMES[3].equalsIgnoreCase( resultSet.getMetaData( )
//						.getColumnName( 4 ) ) );
//				
//				executeQueryTest( resultSet, false, false );
//			}
//			catch ( OdaException ex )
//			{
//				ex.printStackTrace( );
//				fail( ex.getMessage( ) );
//			}
//		}
//	}
}
