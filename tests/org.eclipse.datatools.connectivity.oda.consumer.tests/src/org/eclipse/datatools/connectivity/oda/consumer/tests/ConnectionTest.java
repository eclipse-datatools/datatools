/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
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

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IAdvancedQuery;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;

public class ConnectionTest extends OdaTestCase
{
	private IConnection m_connection;

	protected void setUp( ) throws Exception
	{
		super.setUp( );

		m_connection = getTestDriver().getConnection( null );
		assertNotNull( m_connection );
		
		Properties connProperties = null;
		m_connection.open( connProperties );
	}

	protected void tearDown( ) throws Exception
	{
		if ( m_connection.isOpen() )
			m_connection.close( );

		super.tearDown( );
	}

	public final void testIsOpen() throws OdaException
	{
		// setUp() function must already have the connection opened.
		assertTrue( m_connection.isOpen() );
		
		// Close connection, and verify that isOpen() is false.
		m_connection.close();
		assertFalse( m_connection.isOpen() );
	}
	
	public final void testGetMetaData( ) throws OdaException
	{
		IDataSetMetaData md = m_connection.getMetaData( null );
		assertNotNull( md );
	}

	public final void testMetaDataCache( ) throws OdaException
	{
		IDataSetMetaData c1 = m_connection.getMetaData( null );
		assertNotNull( c1 );
	
		// The previous meta data has been cached, so the 
		// current getMetaData() call should retrieve the same 
		// meta data.
		IDataSetMetaData c2 = m_connection.getMetaData( null );
		assertNotNull( c2 );
	
		// Assert that caching works and the two meta data are the same.
		assertSame( c1, c2 );
	}

	public final void testCreateQuery( ) throws OdaException
	{
		IAdvancedQuery query = ( IAdvancedQuery ) m_connection.newQuery( null );
		assertNotNull( query );
		
		query.close( );
	}

	public final void testPrepareQueryStatement( ) throws OdaException
	{
		IAdvancedQuery query = ( IAdvancedQuery ) m_connection.newQuery( null );
		assertNotNull( query );
		
		query.prepare( "Simple Query" );
		query.close( );
	}

	public final void testClose( ) throws OdaException
	{
		m_connection.close( );
		
		assertFalse( m_connection.isOpen() );
	}

	public final void testGetMaxQueries( ) throws OdaException
	{
		// Open a new connection and make sure there is no opened queries
		if ( m_connection.isOpen() )
			m_connection.close();
		m_connection.open( null );
		
		// Max queries specified in the test driver.
		int maxQueries = 3;
		
		// Verify the max queries specified
		assertEquals( m_connection.getMaxQueries( ), maxQueries );
		
		int count = 0;
		try
		{
			// A while loop to iteratively generate new queries.
			// The testing condition "count <= maxQueries" should
			// always be true.  It is just there to prevent 
			// the possibility of having an infinite loop.
			while( count <= maxQueries )
			{
				IQuery query = m_connection.newQuery( null );
				query.prepare( "Simple Query" );
				count++;
			}
		}
		catch( OdaException e )
		{
			// Expect to get an ODA exception here complaining
			// about maxQueries has been reached.
			assertTrue( count == maxQueries );
			return;
		}
		
		// Should not get here.
		fail();
	}
	
	public final void testCommitAndRollback() throws OdaException
	{
		RunQueryAndCheckRowCount( 5 );
		
		// Execute command to "change the database".
		// The effect is that any subsequent query will only return 1 row of data.		
		IQuery queryCmd = m_connection.newQuery( null );
		queryCmd.prepare( "Limit Rows Command" );
		queryCmd.executeQuery();
		queryCmd.close();
		
		// The query should now only get 1 row of data.
		RunQueryAndCheckRowCount( 1 );	
		
		// Rollback so that the "database state" is restored.
		m_connection.rollback();
		
		// The query should now only get back the original (5) rows of data.
		RunQueryAndCheckRowCount( 5 );	
		
		// Execute command to "change the database".
		// The effect is that any subsequent query will only return 1 row of data.		
		queryCmd = m_connection.newQuery( null );
		queryCmd.prepare( "Limit Rows Command" );
		queryCmd.executeQuery();
		queryCmd.close();
		
		// Commit the change
		m_connection.commit();
		
		// The query should now only get 1 row of data.
		RunQueryAndCheckRowCount( 1 );	
		
		// Perform rollback.
		m_connection.rollback();
		
		// Rollback should have no effect.  The query should still 
		// only get 1 row of data.
		RunQueryAndCheckRowCount( 1 );	
	}
	
	public void testSetLocale() throws Exception
	{
	    boolean hasException = false;
	    try
        {
            m_connection.setLocale( null );
        }
        catch( UnsupportedOperationException ex )
        {
            hasException = true;
        }
        assertTrue( hasException );
	}

	private void RunQueryAndCheckRowCount( int count ) throws OdaException
	{
		IQuery query = m_connection.newQuery( null );
		query.prepare( "Simple Query" );
		IResultSet resultSet = query.executeQuery();
		assertNotNull( resultSet );
		
		int rowCount = 0;
		while ( resultSet.next( ) )
			rowCount++;
		assertTrue( rowCount == count );
		
		query.close();
	}
	
	IConnection getConnection()
	{
		return m_connection;
	}
}