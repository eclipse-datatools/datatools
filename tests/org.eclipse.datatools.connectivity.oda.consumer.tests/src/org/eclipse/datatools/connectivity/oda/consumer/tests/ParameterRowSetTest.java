/*
 *************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
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

import org.eclipse.datatools.connectivity.oda.IAdvancedQuery;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IParameterRowSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.testdriver.TestData;

public class ParameterRowSetTest extends ConnectionTest 
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
	
	public final void testNavigateRowSet() throws OdaException
	{
		// Prepare an advanced query which takes input parameters and return output
		// parameters.
		m_query.prepare( "Advanced Query" );
		
		// Create Table parameter.
		IParameterRowSet prs = m_query.setNewRowSet( 2 );

		// Verify that the row set is empty.
		assertTrue( prs.isEmpty() );
		
		// Add 2 rows of data.
		for( int i = 1 ; i <= 2 ; i++ )
		{
			int curPos = prs.add();
			assertTrue( curPos == i );
			setRowData( prs );
		}
		
		// Verify that the size is 2 (rows)
		assertTrue( prs.size() == 2 );
		
		// Move cursor back to row 1 (first position).
		assertTrue( prs.previous() );
		
		// Check that the cursor cannot be further moved back,
		// since it is already in first position.
		assertFalse( prs.previous() );
		
		// Clear all rows
		prs.clear();
		
		// Assert that size is zero.
		assertTrue( prs.size() == 0 );
	}
	
	
	private void setRowData( IParameterRowSet prs ) throws OdaException
	{
		prs.setBigDecimal( 1, TestData.createBigDecimalData() );
		prs.setDate( 2, TestData.createDateData() );
		prs.setDouble( 3, TestData.createDoubleData() );
		prs.setInt( 4, TestData.createIntData() );
		prs.setString( 5, TestData.createStringData() );
		prs.setTime( 6, TestData.createTimeData() );
		prs.setTimestamp( 7, TestData.createTimestampData() );
	}
}
