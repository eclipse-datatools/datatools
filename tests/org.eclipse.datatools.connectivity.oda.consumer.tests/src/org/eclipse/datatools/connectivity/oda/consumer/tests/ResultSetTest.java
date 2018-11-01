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

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.testdriver.TestData;

public class ResultSetTest extends QueryTest 
{
	private IQuery m_query = null;
	private IResultSet m_resultSet = null;
	
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
			m_resultSet = m_query.executeQuery();
		}
	}

	protected void tearDown() throws Exception
	{
		if ( m_resultSet != null )
			m_resultSet.close();
		
		m_query.close();
		
		super.tearDown();
	}
	
	public final void testCloseResultSet() throws OdaException 
	{
		// Close result set.
		m_resultSet.close();
		
		try
		{
			m_resultSet.next();
		}
		catch( OdaException e )
		{
			// Expect an exception from the test driver complaining that the
			// result set has been closed.
			assertTrue( e.toString().indexOf( "closed" ) >= 0 );
			return;
		}
		
		// Should not get here.
		fail();
	}

	public final void testFindColumn() throws OdaException 
	{
		// Test finding an existing column.
		int index = m_resultSet.findColumn( "BigDecimalCol" );
		assertTrue( index == 1 );
		
		// Test finding a non-existent column.
		try
		{
			index = m_resultSet.findColumn( "NonExistentColumn" );
		}
		catch( OdaException e )
		{
			// Expect an exception to be caught here.
			assertTrue( e.toString().indexOf( "Unknown column name" ) >= 0 );
		}
	}
    
    public final void testGetBoolean() throws OdaException 
    {
        boolean retVal = m_resultSet.next();
        assertTrue( retVal );
        
        boolean expectedVal = TestData.createBooleanFalseData();
        
        // Test get value by column pos. defined in TestResultSetMetaDataImpl
        boolean val = m_resultSet.getBoolean( 12 );
        assertEquals( expectedVal, val );
        
        // Test get value by column name.
        val = m_resultSet.getBoolean( "BooleanCol" );
        assertEquals( expectedVal, val );
    }
    
    public final void testGetObject() throws OdaException 
    {
        boolean retVal = m_resultSet.next();
        assertTrue( retVal );
        
        Object expectedVal = TestData.createObjectData();
        
        // Test get value by column pos. defined in TestResultSetMetaDataImpl
        Object val = m_resultSet.getObject( 13 );
        assertEquals( expectedVal, val );
        
        // Test get value by column name.
        val = m_resultSet.getObject( "ObjectCol" );
        assertEquals( expectedVal, val );
    }
	
	public final void testGetBigDecimal() throws OdaException 
	{
		boolean retVal = m_resultSet.next();
		assertTrue( retVal );
		
		BigDecimal bdVal = TestData.createBigDecimalData();
		
		// Test get value by column pos.
		BigDecimal val = m_resultSet.getBigDecimal( 1 );
		assertEquals( bdVal, val );
		
		// Test get value by column name.
		val = m_resultSet.getBigDecimal( "BigDecimalCol" );
		assertEquals( bdVal, val );
	}

	public final void testGetBlob() throws OdaException 
	{
		boolean retVal = m_resultSet.next();
		assertTrue( retVal );
		
		// 
		// Test with non-null blob data.
		//
		
		IBlob refVal = TestData.createBlobData();
		
		// Test get value by column pos.
		IBlob val1 = m_resultSet.getBlob( 2 );
		assertTrue( TestData.checkBlobData( val1, refVal ) );
		
		// Test get value by column name.
		val1 = m_resultSet.getBlob( "BlobCol1" );
		assertTrue( TestData.checkBlobData( val1, refVal ) );
		
		
		//
		// Test with null blob data.  Notice that when the blob data is null,
		// the returned IBlob value by the oda consumer will also be null and no
		// wrapper will be applied on top of the value.
		//
		
		// Test get value by column pos.
		IBlob val2 = m_resultSet.getBlob( 3 );
		assertNull( val2 );
		
		// Test get value by column name.
		val2 = m_resultSet.getBlob( "BlobCol2" );
		assertNull( val2 );
	}
	
	public final void testGetClob() throws OdaException 
	{
		boolean retVal = m_resultSet.next();
		assertTrue( retVal );
		
		// 
		// Test with non-null clob data.
		//
		String refVal = TestData.createClobDataString();
		
		// Test get value by column pos.
		IClob val1 = m_resultSet.getClob( 4 );
		assertTrue( TestData.checkClobData( val1, refVal ) );
		
		// Test get value by column name.
		val1 = m_resultSet.getClob( "ClobCol1" );
		assertTrue( TestData.checkClobData( val1, refVal ) );
		
		// 
		// Test with null clob data.  Notice that when the clob data is null,
		// the returned IClob value by the oda consumer will also be null and no
		// wrapper will be applied on top of the value.
		//
		
		// Test get value by column pos.
		IClob val2 = m_resultSet.getClob( 5 );
		assertNull( val2 );
		
		// Test get value by column name.
		val1 = m_resultSet.getClob( "ClobCol2" );
		assertNull( val2 );
	}
	
	public final void testGetDate() throws OdaException 
	{
		boolean retVal = m_resultSet.next();
		assertTrue( retVal );
		
		Date dateVal = TestData.createDateData();
		
		// Test get value by column pos.
		Date val = m_resultSet.getDate( 6 );
		assertEquals( dateVal, val );
		
		// Test get value by column name.
		val = m_resultSet.getDate( "DateCol" );
		assertEquals( dateVal, val );
	}
	
	public final void testGetDouble() throws OdaException 
	{
		boolean retVal = m_resultSet.next();
		assertTrue( retVal );
		
		double doubleVal = TestData.createDoubleData();
		
		// Test get value by column pos.
		double val = m_resultSet.getDouble( 7 );
		assertTrue( doubleVal == val );
		
		// Test get value by column name.
		val = m_resultSet.getDouble( "DoubleCol" );
		assertTrue( doubleVal == val );
	}
	
	public final void testGetInt() throws OdaException 
	{
		boolean retVal = m_resultSet.next();
		assertTrue( retVal );
		
		int intVal = TestData.createIntData();
		
		// Test get value by column pos.
		int val = m_resultSet.getInt( 8 );
        assertEquals( intVal, val );
		
		// Test get value by column name.
		val = m_resultSet.getInt( "IntCol" );
        assertEquals( intVal, val );
	}
	
	public final void testGetResultSetMetaData() throws OdaException 
	{
		IResultSetMetaData rsmd = m_resultSet.getMetaData();
		assertNotNull( rsmd );
	}

	private void doTestSetMaxRows( int max, int expected, boolean setMax )
	throws OdaException
	{
		if ( setMax )
			m_resultSet.setMaxRows( max );
		
		int count = 0;
		while ( m_resultSet.next( ) )
			count++;
		assertEquals( expected, count );
	}
	
	public final void testSetResultSetMaxRows0( ) throws OdaException
	{
		doTestSetMaxRows( 0, 5, false /* setMax */);
	}
	
	public final void testSetResultSetMaxRows1( ) throws OdaException
	{
		doTestSetMaxRows( 2, 2, true /* setMax */);
	}
	
	public final void testSetResultSetMaxRows2( ) throws OdaException
	{
		// Setting max to 0 means there is no limit.
		doTestSetMaxRows( 0, 5, true /* setMax */);
	}

	public final void testSetResultSetMaxRows3( ) throws OdaException
	{
		doTestSetMaxRows( 10, 5, true /* setMax */);
	}

	public final void testGetString() throws OdaException 
	{
		boolean retVal = m_resultSet.next();
		assertTrue( retVal );
		
		String strVal = TestData.createStringData();
		
		// Test get value by column pos.
		String val = m_resultSet.getString( 9 );
		assertEquals( strVal, val );
		
		// Test get value by column name.
		val = m_resultSet.getString( "StringCol" );
		assertEquals( strVal, val );
	}

	public final void testGetTime() throws OdaException 
	{
		boolean retVal = m_resultSet.next();
		assertTrue( retVal );
		
		Time timeVal = TestData.createTimeData();
		
		// Test get value by column pos.
		Time val = m_resultSet.getTime( 10 );
		assertEquals( timeVal, val );
		
		// Test get value by column name.
		val = m_resultSet.getTime( "TimeCol" );
		assertEquals( timeVal, val );
	}
	
	public final void testGetTimestamp() throws OdaException 
	{
		boolean retVal = m_resultSet.next();
		assertTrue( retVal );
		
		Timestamp tsVal = TestData.createTimestampData();
		
		// Test get value by column pos.
		Timestamp val = m_resultSet.getTimestamp( 11 );
		assertEquals( tsVal, val );
		
		// Test get value by column name.
		val = m_resultSet.getTimestamp( "TimestampCol" );
		assertEquals( tsVal, val );
	}
	
	public final void testWasNull() throws OdaException 
	{
		boolean retVal = m_resultSet.next();
		assertTrue( retVal );
		
		// Get column "BigDecimalCol" value.
		// The value is not null.
		BigDecimal bdVal = m_resultSet.getBigDecimal( "BigDecimalCol" );
		assertNotNull( bdVal );
		assertFalse( m_resultSet.wasNull() );
		
		// Get column "BlobCol2" value.
		// The value is null.
		IBlob blobVal = m_resultSet.getBlob( "BlobCol2" );
		assertNull( blobVal );
		assertTrue( m_resultSet.wasNull() );
	}
	
	public final void testGetRowAndNext() throws OdaException
	{
		int count = 0;
		while ( m_resultSet.next( ) )
		{
			count++;
			assertTrue( m_resultSet.getRow() == count );
		}
	}
	
	public final void testNextWithInactiveConnection() throws OdaException
	{
		IConnection conn = getConnection();
		conn.open( null );
		
		IQuery query = conn.newQuery( null );
		query.prepare( "Simple Query" );
		IResultSet rs = query.executeQuery();
		
		// Under normal situation, calling next() should point 
		// to the first row of the result set.
		assertTrue( rs.next() );
		
		// Close connection.
		conn.close();
		
		try
		{
			rs.next();
			
			// Should not get here.
			fail();
		}
		catch( OdaException e )
		{
			// Expect an exception complaining about the connection is 
			// not active.
			String error = e.toString();
			assertTrue( error.indexOf( "connection" ) >= 0 &&
					error.indexOf( "not active" ) >= 0 );
		}
		
		query.close();
		conn.close();
	}
}
