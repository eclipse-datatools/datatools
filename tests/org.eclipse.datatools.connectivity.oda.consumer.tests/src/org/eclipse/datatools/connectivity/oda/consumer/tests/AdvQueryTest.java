/*************************************************************************
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

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.eclipse.datatools.connectivity.oda.IAdvancedQuery;
import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IParameterRowSet;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.consumer.testdriver.TestData;
import org.eclipse.datatools.connectivity.oda.impl.Blob;

public class AdvQueryTest extends ConnectionTest 
{
	private IAdvancedQuery m_query;
	
	protected void setUp() throws Exception
	{
		super.setUp( );

		IConnection conn = getConnection();
		m_query = ( IAdvancedQuery ) conn.newQuery( null );
	}

	protected void tearDown() throws Exception
	{
		m_query.close();

		super.tearDown( );
	}
	
	public final void testFindOutParameter() throws OdaException
	{
		// Test FindOutParmaeter without preparing the query.
		// A exception should be caught.
		try
		{
			m_query.findOutParameter( "Dummy name" );
			
			// Should not get here.
			fail();
		}
		catch( OdaException e )
		{
			String error = e.toString();
			
			// Expect an exception complaining about statement not being prepared yet.
			assertTrue( error.indexOf( "parameter metadata" ) >= 0 &&
					error.indexOf( "prepared" ) >= 0 );
		}
		
		// Prepare a complex query which takes input parameters.
		m_query.prepare( "Advanced Query" );
		
		// Try look up a valid parameter name.
		int paramPos = m_query.findOutParameter( "BigDecimalParamOut" );
		assertTrue( paramPos == 3 );
		
		// Try look up an invalid parameter name.
		String badParamName = "param_unknown";
		try
		{
			paramPos = m_query.findOutParameter( badParamName );
		}
		catch( OdaException e )
		{
			assertTrue( e.toString().indexOf( badParamName ) >= 0 );
		}
	}
	
	public final void testSetAndGetParameterValuesByPos() throws OdaException
	{
		testSetAndGetParameterValue( true );
	}
	
	public final void testSetAndGetParameterValuesByName() throws OdaException
	{
		testSetAndGetParameterValue( false );
	}
	
	public final void testGetResultSetNames() throws OdaException
	{
		// Prepare a multiple result set query.
		m_query.prepare( "Multiple Result Sets Query" );
		
		// Call execute().
		boolean ret = m_query.execute();
		assertTrue( ret );
		
		String[] nameArray = m_query.getResultSetNames();
		assertTrue( nameArray.length == 2 );
			
		for( int i = 0; i < nameArray.length; i++ )
		{
			assertEquals( nameArray[ i ], "ResultSet" + ( i + 1 ) );
		}
	}
	
	public final void testIterateResultSets1()  throws OdaException
	{
		// If mulitple result sets is not supported in the data set meta data,
		// executeQuery() will be called instead of execute(), and only
		// one result set will be returned.
		testIterateResultSetsHelper( false );
	}
	
	public final void testIterateResultSets2()  throws OdaException
	{
		// If mulitple result sets is supported in the data set meta data,
		// execute() will be called, and multiple result sets
		// can be accessed.
		testIterateResultSetsHelper( true );
	}
	
	
	public final void testSetAndGetSortSpecByResultSetName() throws OdaException
	{
		String resultSetName = "ResultSet1";
		SortSpec ssIn = new SortSpec( SortSpec.sortAsc );
		
		try
		{
			// Set sort spec without preparing a query.  An exception should be thrown.
			m_query.setSortSpec( resultSetName, ssIn );
			
			// Should not get here.
			fail();
		}
		catch( OdaException e )
		{
			// An exception will be thrown complaining about 
			// query not prepared yet.
			assertTrue( e.toString().indexOf( "sort specification" ) >= 0 
					&& e.toString().indexOf( "prepared" ) >= 0 );
		}
		
		// Prepare a multiple result set query.
		m_query.prepare( "Multiple Result Sets Query" );

		// Set the sort specification.
		ssIn = new SortSpec( SortSpec.sortAsc );
		m_query.setSortSpec( resultSetName, ssIn );
		
		// Retrieve the sort specification.  Verify if it is identical to the value set.
		SortSpec ssOut = m_query.getSortSpec( resultSetName );
		assertEquals( ssIn, ssOut );
	}
	
	public final void testGetNamedResultSets1() throws OdaException
	{
		boolean supportsMultipleResultSets = true;
		boolean supportsNamedResultSets = false;

		testGetNamedResultSet( supportsMultipleResultSets, supportsNamedResultSets );
	}
	
	public final void testGetNamedResultSets2() throws OdaException
	{
		boolean supportsMultipleResultSets = false;
		boolean supportsNamedResultSets = true;
		
		testGetNamedResultSet( supportsMultipleResultSets, supportsNamedResultSets );
	}
	
	public final void testGetNamedResultSets3() throws OdaException
	{
		boolean supportsMultipleResultSets = false;
		boolean supportsNamedResultSets = false;
		
		testGetNamedResultSet( supportsMultipleResultSets, supportsNamedResultSets );
	}
	
	private final void testIterateResultSetsHelper( boolean supportMultipleResultSets ) throws OdaException
	{
		m_query = ( IAdvancedQuery ) getConnection().newQuery( supportMultipleResultSets ?
				"Multiple Result Sets Data Set" : null );
		
		// Prepare a multiple result set query.
		m_query.prepare( "Multiple Result Sets Query" );
		
		// Call execute().
		boolean ret = m_query.execute();
		assertTrue( ret );
		
		int count = 0;
		while( true )
		{
			IResultSet rs = m_query.getResultSet();
			assertNotNull( rs );
			
			count++;
			
			if ( count == 1 && ! supportMultipleResultSets )
			{
				assertFalse( m_query.getMoreResults() );
				break;
			}
			
			assertTrue( m_query.getMoreResults() == ( count < 2 ) );
			
			if ( count == 2 )
				break;
		}
		
		assertTrue( count == ( supportMultipleResultSets ? 2 : 1 ) );
	}

	private void testGetNamedResultSet( boolean supportsMultipleResultSets,
			boolean supportsNamedResultSets )
		throws OdaException
	{	
		if ( supportsMultipleResultSets )
			m_query = ( IAdvancedQuery ) getConnection().newQuery( "Multiple Result Sets Data Set" );
		else if ( supportsNamedResultSets )
			m_query = ( IAdvancedQuery ) getConnection().newQuery( "Named Result Sets Data Set" );
		
		// Prepare a multiple result set query.
		m_query.prepare( "Multiple Result Sets Query" );
		
		// Call execute().  If multiple result sets is not supported,
		// this should internally trigger executeQuery() instead.
		boolean ret = m_query.execute();
		assertTrue( ret );
		
		try 
		{
			for( int i = 1; i <= 2; i++ )
			{
				String resultSetName = "ResultSet" + i;
				IResultSet resultSet = m_query.getResultSet( resultSetName );
			
				if ( ! ( supportsMultipleResultSets || supportsNamedResultSets ) )
					fail();
			
				assertNotNull( resultSet );
				
				IResultSetMetaData rsmd = m_query.getMetaDataOf( resultSetName );
				assertNotNull( rsmd );
			}
		}
		catch( OdaException e )
		{
			// Check for the error indicating that cannot get result
			// sets by name after executeQuery() has been called.
			String error = e.toString();
			assertTrue( error.indexOf( "result sets by name" ) >= 0 &&
					error.indexOf( "executeQuery" ) >= 0 );
			return;
		}
	}
	
	private void testSetAndGetParameterValue( boolean byPos ) throws OdaException
	{
		// Try setting parameter values before preparing the query (i.e. isPrepared=false).
		// A number of exceptions should be thrown and captured.
		setInputParameterValues( byPos, false );
		
		// Try getting parameter values before executing the query (i.e. isExecuted=false).
		// A number of exceptions should be thrown and captured.
		getParameterValues( byPos, false );
		
		// Prepare an advanced query which takes input parameters and return output
		// parameters.
		m_query.prepare( "Advanced Query" );
		
		// Set the input parameters.
		setInputParameterValues( byPos, true );
		
		// Execute the query.
		IResultSet rs = m_query.executeQuery();
		assertNotNull( rs );
		
		// Get Parameter values again.  This time it should succeed since
		// the query has been executed (isExecuted=true).
		getParameterValues( byPos, true );		
	}
	
	private void setInputParameterValues( boolean byPos, boolean isPrepared ) throws OdaException
	{
		setStructureInputParamValues( byPos, isPrepared );
		setTableInputParamValues( byPos, isPrepared );
	}
	
	private void setStructureInputParamValues( boolean byPos, boolean isPrepared ) throws OdaException
	{
		IParameterRowSet prs = null;
		try
		{
			// Create Structure parameter.
			prs = ( byPos ? m_query.setNewRow( 1 ) : m_query.setNewRow( "StructParamIn" ) );
			
			if ( ! isPrepared )
				fail();
		}
		catch( OdaException e )
		{
			String error = e.toString();
			
			// Expect an exception complaining about statement not being prepared yet.
			assertTrue( error.indexOf( "input parameters" ) >= 0 &&
					error.indexOf( "prepared" ) >= 0 );
			
			return;
		}		
		
		setRowData( byPos, prs );
	}
	
	private void setTableInputParamValues( boolean byPos, boolean isPrepared ) throws OdaException
	{
		IParameterRowSet prs = null;
		try
		{
			// Create Table parameter.
			prs = ( byPos ? m_query.setNewRowSet( 2 ) : m_query.setNewRowSet( "TableParamIn" ) );
			
			if ( ! isPrepared )
				fail();
		}
		catch( OdaException e )
		{
			String error = e.toString();
			
			// Expect an exception complaining about statement not being prepared yet.
			assertTrue( error.indexOf( "input parameters" ) >= 0 &&
					error.indexOf( "prepared" ) >= 0 );
			
			return;
		}
		
		// Add 2 rows of data.
		for( int i = 1 ; i <= 2 ; i++ )
		{
			int curPos = prs.add();
			assertTrue( curPos == i );
			setRowData( byPos, prs );
		}
	}
	
	private void setRowData( boolean byPos, IParameterRowSet prs ) throws OdaException
	{
		if ( byPos )
		{
			prs.setBigDecimal( 1, TestData.createBigDecimalData() );
			prs.setDate( 2, TestData.createDateData() );
			prs.setDouble( 3, TestData.createDoubleData() );
			prs.setInt( 4, TestData.createIntData() );
			prs.setString( 5, TestData.createStringData() );
			prs.setTime( 6, TestData.createTimeData() );
			prs.setTimestamp( 7, TestData.createTimestampData() );
            prs.setBoolean( 8, TestData.createBooleanFalseData() );
		}
		else
		{
			prs.setBigDecimal( "BigDecimalCol", TestData.createBigDecimalData() );
			prs.setDate( "DateCol", TestData.createDateData() );
			prs.setDouble( "DoubleCol", TestData.createDoubleData() );
			prs.setInt( "IntCol", TestData.createIntData() );
			prs.setString( "StringCol", TestData.createStringData() );
			prs.setTime( "TimeCol", TestData.createTimeData() );
			prs.setTimestamp( "TimestampCol", TestData.createTimestampData() );			
            prs.setBoolean( "BooleanCol", TestData.createBooleanTrueData() );         
		}
	}
	
	private void getParameterValues( boolean byPos, boolean isExecuted ) throws OdaException
	{
		int outParamStartIndex = 3;
		
		try
		{
			BigDecimal val = byPos ? 
					m_query.getBigDecimal( outParamStartIndex ) :
					m_query.getBigDecimal( "BigDecimalParamOut" );

			if ( isExecuted )
			{
				BigDecimal refVal = TestData.createBigDecimalData();
				assertEquals( val, refVal );
				assertFalse( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			IBlob val = byPos ? 
					m_query.getBlob( outParamStartIndex + 1 ) :
					m_query.getBlob( "BlobParam1Out" );

			if ( isExecuted )
			{
				Blob refVal = TestData.createBlobData();
				assertTrue( TestData.checkBlobData( val, refVal ) );
				assertFalse( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			IBlob val = byPos ? 
					m_query.getBlob( outParamStartIndex + 2 ) :
					m_query.getBlob( "BlobParam2Out" );

			if ( isExecuted )
			{
				// The IBlob value returned should be null.
				assertNull( val );
				assertTrue( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			IClob val = byPos ? 
					m_query.getClob( outParamStartIndex + 3 ) :
					m_query.getClob( "ClobParam1Out" );
 
			if ( isExecuted )
			{
				String refValStr = TestData.createClobDataString();
				assertTrue( TestData.checkClobData( val, refValStr ) );
				assertFalse( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			IClob val = byPos ? 
					m_query.getClob( outParamStartIndex + 4 ) :
					m_query.getClob( "ClobParam2Out" );

			if ( isExecuted )
			{
				// The IClob value returned should be null.
				assertNull( val );
				assertTrue( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			Date val = byPos ? 
					m_query.getDate( outParamStartIndex + 5 ) :
					m_query.getDate( "DateParamOut" );

			if ( isExecuted )
			{
				Date refVal = TestData.createDateData();
				assertEquals( val, refVal );
				assertFalse( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			double val = byPos ? 
					m_query.getDouble( outParamStartIndex + 6 ) :
					m_query.getDouble( "DoubleParamOut");

			if ( isExecuted )
			{
				double refVal = TestData.createDoubleData();
				assertEquals( val, refVal, 0 );
				assertFalse( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			int val = byPos ? 
					m_query.getInt( outParamStartIndex + 7 ) :
					m_query.getInt( "IntegerParamOut" );

			if ( isExecuted )
			{
				int refVal = TestData.createIntData();
				assertEquals( val, refVal );
				assertFalse( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			String val = byPos ? 
					m_query.getString( outParamStartIndex + 8 ) :
					m_query.getString( "StringParamOut" );

			if ( isExecuted )
			{
				String refVal = TestData.createStringData();
				assertEquals( val, refVal );
				assertFalse( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			Time val = byPos ? 
					m_query.getTime( outParamStartIndex + 9 ) :
					m_query.getTime( "TimeParamOut" );

			if ( isExecuted )
			{
				Time refVal = TestData.createTimeData();
				assertEquals( val, refVal );
				assertFalse( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			Timestamp val = byPos ? 
					m_query.getTimestamp( outParamStartIndex + 10 ) :
					m_query.getTimestamp( "TimestampParamOut" );

			if ( isExecuted )
			{
				Timestamp refVal = TestData.createTimestampData();
				assertEquals( val, refVal );
				assertFalse( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
		
		try
		{
			IParameterRowSet val = byPos ? 
					m_query.getRow( outParamStartIndex + 11 ) :
					m_query.getRow( "StructParamOut" );

			if ( isExecuted )
			{
				IParameterRowSet refVal = TestData.createStructData();
				assertSameParamRowSetData( val, refVal );
				assertFalse( m_query.wasNull() );
			}
			else
				fail(); // should not get here.
		}
		catch( OdaException e )
		{
			checkGetParamValueBeforeExecutedError( e, isExecuted );
		}
        
        try
        {
            boolean val = byPos ? 
                    m_query.getBoolean( outParamStartIndex + 12 ) :
                    m_query.getBoolean( "BooleanParamOut" );

            if ( isExecuted )
            {
                boolean refVal = TestData.createBooleanFalseData();
                assertEquals( val, refVal );
                assertFalse( m_query.wasNull() );
            }
            else
                fail(); // should not get here.
        }
        catch( OdaException e )
        {
            checkGetParamValueBeforeExecutedError( e, isExecuted );
        }
        
        try
        {
            Object val = byPos ? 
                    m_query.getObject( outParamStartIndex + 13 ) :
                    m_query.getObject( "ObjectParamOut" );

            if ( isExecuted )
            {
                Object refVal = TestData.createObjectData();
                assertEquals( val, refVal );
                assertFalse( m_query.wasNull() );
            }
            else
                fail(); // should not get here.
        }
        catch( OdaException e )
        {
            checkGetParamValueBeforeExecutedError( e, isExecuted );
        }
	}
	
	private void checkGetParamValueBeforeExecutedError( OdaException e, boolean isExecuted )
	{
		if ( ! isExecuted )
			assertTrue( e.toString().indexOf( "execute" ) >= 0 );
		else
		{
			// Since we already get an exception, mark the test as failed here.
			fail();
		}
	}
	
	private void assertSameParamRowSetData( IParameterRowSet val1, IParameterRowSet val2 )
		throws OdaException
	{
		assertEquals( val1.size(), val2.size() );
		
		boolean ret1 = val1.absolute( 1 );
		boolean ret2 = val2.absolute( 1 );
		assertTrue( ret1 == ret2 );
		
		while( ret1 && ret2 )
		{
			assertEquals( val1.getBigDecimal( 1 ), val2.getBigDecimal( 1 ) );
			assertTrue( TestData.checkBlobData( val1.getBlob( 2 ), val2.getBlob( 2 ) ) );
			assertTrue( TestData.checkBlobData( val1.getBlob( 3 ), val2.getBlob( 3 ) ) );
			assertTrue( TestData.checkClobData( val1.getClob( 4 ), val2.getClob( 4 ) ) );
			assertTrue( TestData.checkClobData( val1.getClob( 5 ), val2.getClob( 5 ) ) );
			assertEquals( val1.getDate( 6 ), val2.getDate( 6 ) );
			assertEquals( val1.getDouble( 7 ), val2.getDouble( 7 ), 0 );
			assertEquals( val1.getInt( 8 ), val2.getInt( 8 ) );
			assertEquals( val1.getString( 9 ), val2.getString( 9 ) );
			assertEquals( val1.getTime( 10 ), val2.getTime( 10 ) );
			assertEquals( val1.getTimestamp( 11 ), val2.getTimestamp( 11 ) );
			
			ret1 = val1.next();
			ret2 = val2.next();
			
			assertTrue( ret1 == ret2 );
		}
	}
}
