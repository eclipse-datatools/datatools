/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.tests;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

public class DataSetMetaDataTest extends ConnectionTest
{
	IDataSetMetaData m_dataSetMetaData = null;
	
	protected void setUp() throws Exception
	{
		super.setUp( );
		
		IConnection conn = getConnection();
		m_dataSetMetaData = conn.getMetaData( null );
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}	
	
	public final void testGetConnection() throws OdaException
	{
		IConnection conn = m_dataSetMetaData.getConnection();
		assertNotNull( conn );
	}

	public final void testGetDataSourceObjects() throws OdaException
	{
		try
		{
			m_dataSetMetaData.getDataSourceObjects( null, null, null, null );
		}
		catch( UnsupportedOperationException e )
		{
			// The test driver does not support this.  An 
			// unsupported operation exception should be thrown.
			return;
		}
		
		fail();
	}

	public final void testGetDataSourceMajorVersion() throws OdaException
	{
		assertTrue( m_dataSetMetaData.getDataSourceMajorVersion() == 2 );
	}

	public final void testGetDataSourceMinorVersion() throws OdaException
	{
		assertTrue( m_dataSetMetaData.getDataSourceMinorVersion() == 3 );
	}

	public final void testGetDataSourceProductName() throws OdaException
	{
		assertTrue( m_dataSetMetaData.getDataSourceProductName().
				equals( "Simple Data Source" ) );
	}

	public final void testGetDataSourceProductVersion() throws OdaException
	{
		assertTrue( m_dataSetMetaData.getDataSourceProductVersion().equals( "2.3" ) );
	}

	public final void testGetSQLStateType() throws OdaException
	{
		assertTrue( m_dataSetMetaData.getSQLStateType() == 
			IDataSetMetaData.sqlStateSQL99 );
	}

	public final void testSupportsMultipleResultSets() throws OdaException
	{
		assertFalse( m_dataSetMetaData.supportsMultipleResultSets() );
	}

	public final void testSupportsMultipleOpenResults() throws OdaException
	{
		assertFalse( m_dataSetMetaData.supportsMultipleOpenResults() );
	}

	public final void testSupportsNamedResultSets() throws OdaException
	{
		assertFalse( m_dataSetMetaData.supportsNamedResultSets() );
	}

	public final void testSupportsNamedParameters() throws OdaException
	{
		assertTrue( m_dataSetMetaData.supportsNamedParameters() );
	}

	public final void testSupportsInParameters() throws OdaException
	{
		assertTrue( m_dataSetMetaData.supportsInParameters() );
	}

	public final void testSupportsOutParameters() throws OdaException
	{
		assertTrue( m_dataSetMetaData.supportsOutParameters() );
	}

	public final void testGetSortMode() throws OdaException
	{
		assertTrue( m_dataSetMetaData.getSortMode() == 
			IDataSetMetaData.sortModeNone );
	}

}
