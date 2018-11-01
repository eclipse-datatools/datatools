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

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.consumer.testdriver.TestData;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationFactory;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;

@SuppressWarnings("restriction")
public class QueryTest extends ConnectionTest
{
	private IQuery m_query;

	IQuery getQuery()
	{
		return m_query;
	}

	protected void setUp() throws Exception
	{
		super.setUp( );

		m_query = getConnection().newQuery( null );
	}

	protected void tearDown() throws Exception
	{
		m_query.close();

		super.tearDown( );
	}

	public final void testSortSpecMessages( )
	{
		try
		{
			new SortSpec( 100 );
			fail();
		}
		catch ( IllegalArgumentException ex )
		{
			assertEquals( "Invalid sort mode specified: 100.", ex.getMessage( ) );
		}
	}

	public final void testSetProperty( ) throws OdaException
	{
		//
		// Test the setting of property.
		//
		final String propName = "TEST_QUERY_PROP_NAME";
		final String propVal = "propVal_123";
		m_query.setProperty( propName, propVal );
		
		//
		// Test with a bad property name.  The test driver throws an exception. 
		// 
		final String badPropertyName = "bad_prop_123";

		try
		{
			m_query.setProperty( badPropertyName, "propVal_123" );
			fail(); // shouldn't get here
		}
		catch ( OdaException ex )
		{
			// Expected exception. 
			// Exception message contains the bad property name
			assertTrue( ex.toString().indexOf( badPropertyName ) >= 0 );
		}
	}

	public final void testSetAndGetSortSpec( ) throws OdaException
	{
		SortSpec ssDesc = new SortSpec( SortSpec.sortDesc );
		
		// Test setting sort spec without preparing the query.
		// A exception should be caught.
		try
		{
			m_query.setSortSpec( ssDesc );
			
			// Should not get here.
			fail();
		}
		catch( OdaException e )
		{
			String error = e.toString();
			
			// Expect an exception complaining about statement not being prepared yet.
			assertTrue( error.indexOf( "sort specification" ) >= 0 &&
					error.indexOf( "prepared" ) >= 0 );
		}
		
		// Prepare the query.
		m_query.prepare( "Simple Query" );
		
		// Set sort spec.
		m_query.setSortSpec( ssDesc );
		
		// Test retrieving sort spec.
		SortSpec ss = m_query.getSortSpec();
		assertSame( ss, ssDesc );
	}

	private void doTestSetMaxRows( int max, int expected, boolean setMax )
			throws OdaException
	{
		if ( setMax )
			m_query.setMaxRows( max );

		m_query.prepare( "Simple Query" );
		IResultSet result = m_query.executeQuery( );
		assertNotNull( result );
		int count = 0;
		while ( result.next( ) )
			count++;
		assertEquals( expected, count );
	}

	public final void testSetAndGetMaxRows() throws OdaException
	{
		int maxRows = 1001;
		m_query.setMaxRows( maxRows );
		
		assertTrue( m_query.getMaxRows() == maxRows );
	}
	
	public final void testSetMaxRows0( ) throws OdaException
	{
		doTestSetMaxRows( 0, 5, false /* setMax */);
	}

	public final void testSetMaxRows1( ) throws OdaException
	{
		doTestSetMaxRows( 2, 2, true /* setMax */);
	}

	public final void testSetMaxRows2( ) throws OdaException
	{
		// Setting max to 0 means there is no limit.
		doTestSetMaxRows( 0, 5, true /* setMax */);
	}

	public final void testSetMaxRows3( ) throws OdaException
	{
		doTestSetMaxRows( 10, 5, true /* setMax */);
	}

	public final void testGetResultSetMetaDataFromQuery() throws OdaException
	{
		// Test getting meta data without preparing the query.
		// A exception should be caught.
		try
		{
			m_query.getMetaData();
			
			// Should not get here.
			fail();
		}
		catch( OdaException e )
		{
			String error = e.toString();
			
			// Expect an exception complaining about statement not being prepared yet.
			assertTrue( error.indexOf( "result set metadata" ) >= 0 &&
					error.indexOf( "prepared" ) >= 0 );
		}
		
		// Test getting non-null meta data from the test driver.
		// The oda consumer layer will return an OdaResultSetMetaData
		// object encapsulating the meta data.
		m_query.prepare( "Simple Query" );
		IResultSetMetaData md = m_query.getMetaData( );
		assertNotNull( md );
		
		// Test getting non-null meta data from the test driver
		// by issuing a query that returns null meta data.
		// The oda consumer layer will return null.
		m_query.prepare( "Limit Rows Command" );
		md = m_query.getMetaData();
		assertNull( md );
	}
	
	public final void testPrepareNullQuery() throws OdaException
	{
		try
		{
			// Test prepare a query in the form of a null string.
			m_query.prepare( null );
		}
		catch( OdaException e )
		{
			// The oda consumer layer will detect the null string
			// and convert it to an empty string.  The Test driver will
			// throw an exception indicating that it cannot prepare
			// a query with an empty string.
			assertTrue( e.toString().indexOf( "Query text cannot be an empty string." ) >= 0 );
		}
	}
	
	public final void testExecuteQuery() throws OdaException
	{
		// Test execute query without prepare.  Error occurs.
		try
		{
			m_query.executeQuery();
		}
		catch( OdaException e )
		{
			String error = e.toString();
			
			// Expect an exception complaining about statement not being prepared yet.
			assertTrue( error.indexOf( "execute" ) >= 0 &&
					error.indexOf( "prepared" ) >= 0 );
		}
		
		// Test execute a query after preparing it.  Query execution should succeed.
		m_query.prepare( "Simple Query" );
		IResultSet md = m_query.executeQuery();
		assertNotNull( md );
	}

	public void testCancelQuery() throws Exception
	{
        m_query.prepare( "Simple Query" );
        
        boolean hasException = false;
        try
        {
            m_query.cancel();
        }
        catch( OdaException ex )
        {
            hasException = true;
        }
        assertTrue( hasException );
        
        m_query.executeQuery();
        hasException = false;
        try
        {
            m_query.cancel();
        }
        catch( OdaException ex )
        {
            hasException = true;
        }
        assertTrue( hasException );
	}

    public void testPreparedQueryText() throws Exception
    {
        QuerySpecification querySpec = new QuerySpecificationHelper( (QuerySpecificationFactory)null ).createQuerySpecification();
        m_query.setSpecification( querySpec );
        m_query.prepare( "Simple Query" );
        
        String preparedText = m_query.getEffectiveQueryText();
        assertEquals( "Simple Query" + querySpec, preparedText );
    }
    
	public final void testSetParametersByPos() throws OdaException
	{
		// Test setting parameters by position, without preparing
		// the query first.  Exceptions will be thrown and caught.
		setParameterValuesByPos( false );		
		
		// Prepare a complex query with parameters.
		m_query.prepare( "Complex Query" );
		
		// Set parameters with valid positions.  This should
		// work without throwing any exceptions.
		setParameterValuesByPos( true );
		
		// Try setting parameter using invalid index.
		// An exception will be thrown.
		int badParamIndex = 100;
		try
		{
			m_query.setBigDecimal( badParamIndex, null );
			
			// Should not get here.
			fail();
		}
		catch( OdaException e )
		{
			// Expected exception.
			// Exception message contains the bad parameter index.
			assertTrue( e.toString().indexOf( badParamIndex ) >= 0 );
		}
	}
	
	private void CheckExceptionSetParamBeforePrepare( OdaException e )
	{
		// Confirming exception message about setting input parameters 
		// before the statement is prepared.
		assertTrue( e.toString().indexOf( "set input parameters" ) >= 0 &&
				e.toString().indexOf( "prepared" ) >= 0 );
	}
	
	private final void setParameterValuesByPos( boolean isQueryPrepared ) throws OdaException
	{
		try
		{
			BigDecimal bdVal = TestData.createBigDecimalData();
			m_query.setBigDecimal( 1, bdVal );
			
			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
	
		try
		{
			Date dateVal = TestData.createDateData();
			m_query.setDate( 2, dateVal );
			
			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
		
		try
		{
			double doubleVal = TestData.createDoubleData();
			m_query.setDouble( 3, doubleVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
		
		try
		{
			int intVal = TestData.createIntData();
			m_query.setInt( 4, intVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
		
		try
		{
			String strVal = TestData.createStringData();
			m_query.setString( 5, strVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
		
		try
		{
			Time timeVal = TestData.createTimeData();
			m_query.setTime( 6, timeVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			CheckExceptionSetParamBeforePrepare( e );
		}

		try
		{
			Timestamp tsVal = TestData.createTimestampData();
			m_query.setTimestamp( 7, tsVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}

        try
        {
            boolean val = TestData.createBooleanFalseData();
            m_query.setBoolean( 8, val );

            if ( ! isQueryPrepared )
                fail();
        }
        catch( OdaException e )
        {
            if ( ! isQueryPrepared )
                CheckExceptionSetParamBeforePrepare( e );
            else
                fail();
        }

        try
        {
            Object val = TestData.createObjectData();
            m_query.setObject( 9, val );

            if ( ! isQueryPrepared )
                fail();
        }
        catch( OdaException e )
        {
            if ( ! isQueryPrepared )
                CheckExceptionSetParamBeforePrepare( e );
            else
                fail();
        }
	}
	
	public final void testSetParametersByName() throws OdaException
	{
		// Test setting parameters by names, without preparing
		// the query first.  Exceptions will be thrown and caught.
		setParameterValuesByName( false );
		
		// Prepare a complex query with parameters.
		m_query.prepare( "Complex Query" );
		
		// Set parameters with valid names.  This should work 
		// without throwing exceptions.
		setParameterValuesByName( true );
		
		// Try setting parameter using invalid name.  An 
		// exception should be thrown.
		String badParamName = "param_unknown";		
		try
		{
			m_query.setBigDecimal( badParamName, null );
		}
		catch( OdaException e )
		{
			// Expected exception.
			// exception message contains the bad parameter name.
			assertTrue( e.toString().indexOf( badParamName ) >= 0 );
		}
	}
	
	private final void setParameterValuesByName( boolean isQueryPrepared ) throws OdaException
	{
		try
		{
			BigDecimal bdVal = TestData.createBigDecimalData();
			m_query.setBigDecimal( "BigDecimalParamIn", bdVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
		
		try
		{
			Date dateVal = TestData.createDateData();
			m_query.setDate( "DateParamIn", dateVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
		
		try
		{
			double doubleVal = TestData.createDoubleData();
			m_query.setDouble( "DoubleParamIn", doubleVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
		
		try
		{
			int intVal = TestData.createIntData();
			m_query.setInt( "IntegerParamIn", intVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
		
		try
		{
			String strVal = TestData.createStringData();
			m_query.setString( "StringParamIn", strVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
		
		try
		{
			Time timeVal = TestData.createTimeData();
			m_query.setTime( "TimeParamIn", timeVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
		
		try
		{
			Timestamp tsVal = TestData.createTimestampData();
			m_query.setTimestamp( "TimestampParamIn", tsVal );

			if ( ! isQueryPrepared )
				fail();
		}
		catch( OdaException e )
		{
			if ( ! isQueryPrepared )
				CheckExceptionSetParamBeforePrepare( e );
			else
				fail();
		}
        
        try
        {
            boolean val = TestData.createBooleanFalseData();
            m_query.setBoolean( "BooleanParamIn", val );

            if ( ! isQueryPrepared )
                fail();
        }
        catch( OdaException e )
        {
            if ( ! isQueryPrepared )
                CheckExceptionSetParamBeforePrepare( e );
            else
                fail();
        }
        
        try
        {
            Object val = TestData.createObjectData();
            m_query.setObject( "ObjectParamIn", val );

            if ( ! isQueryPrepared )
                fail();
        }
        catch( OdaException e )
        {
            if ( ! isQueryPrepared )
                CheckExceptionSetParamBeforePrepare( e );
            else
                fail();
        }
	}
	
	public final void testFindInParameter( ) throws OdaException
	{
		// Test FindInParmaeter without preparing the query.
		// A exception should be caught.
		try
		{
			m_query.findInParameter( "Dummy name" );
			
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
		m_query.prepare( "Complex Query" );
		
		// Try look up a valid parameter name.
		int paramPos = m_query.findInParameter( "BigDecimalParamIn" );
		assertTrue( paramPos == 1 );
		
		// Try look up an invalid parameter name.
		String badParamName = "param_unknown";

		try
		{
			paramPos = m_query.findInParameter( badParamName );
			
			// Should not reach here.
			fail();
		}
		catch( OdaException e )
		{
			// An exception should be thrown indicating that 
			// a parameter with that name cannot be found.
			assertTrue( e.toString().indexOf( badParamName ) >= 0 );
		}
	}
	
	public final void testGetParameterMetaData() throws OdaException
	{
		// Test getting parameter meta data without preparing the query.
		// A exception should be caught.
		try
		{
			m_query.getParameterMetaData();
			
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
		m_query.prepare( "Complex Query" );
		
		// Test getting parameter meta data.  This time it should succeed.
		IParameterMetaData pmd = m_query.getParameterMetaData();
		assertNotNull( pmd );
	}
	
	public final void testClearInParameters() throws OdaException
	{
		// Prepare a complex query which takes input parameters.
		m_query.prepare( "Complex Query" );
		
		// Set parameter values.
		setParameterValuesByName( true );
		
		// Execute Query.  The parameters are correct and no 
		// exception will be thrown.
		IResultSet resultSet = m_query.executeQuery();
		assertNotNull( resultSet );
		
		// Clear the parameter values.
		m_query.clearInParameters();
		
		try
		{
			// Execute the query again, this time with null param values.
			resultSet = m_query.executeQuery();
		}
		catch( OdaException e )
		{
			// Expected exception.
			// The query execution throws an exception if
			// the at least one input parameter is null.
			assertTrue( e.toString().indexOf( "Input parameter(s) cannot be null." ) > 0 );
		}
	}
	
	public final void testGetMetaDataFromResultSet() throws OdaException
	{
		// Prepare a simple query.
		m_query.prepare( "Simple Query" );
		
		// Execute query.
		IResultSet resultSet = m_query.executeQuery();
		assertNotNull( resultSet );
		
		// Get Result Set Meta Data.
		IResultSetMetaData rsmd = resultSet.getMetaData();
		assertNotNull( rsmd );
	}

	public final void testQueryClose( ) throws OdaException
	{
		// Close the query.
		m_query.close( );
		
		try
		{
			m_query.prepare( "Simple Query" );
		}
		catch( OdaException e )
		{
			// An exception is expected here, complaining about the query
			// already being closed.
			assertTrue( e.toString().indexOf( "closed" ) >= 0 );
			return;
		}
		
		// Should not get here.
		fail();
	}
}