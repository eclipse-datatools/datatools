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
package org.eclipse.datatools.enablement.oda.xml;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.impl.Connection;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestConstants;

/**
 * 
 */
public class QueryTest extends BaseTest
{	
	IQuery query;
	Connection conn;
	String queryText = "book#-TNAME-#book#:#[//book]#:#{book.category;String;[@category]}";
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		conn = new Connection();
		Properties prop = new Properties();
		prop.put(Constants.CONST_PROP_FILELIST, TestConstants.SMALL_XML_FILE);
		conn.open(prop);
		query = conn.newQuery( null );
	}

	protected void tearDown( ) throws Exception
	{
		super.tearDown( );
		conn.close();
	}
	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Query.prepare(String)'
	 */
	public void testPrepare( ) throws OdaException
	{
		try{
			query.executeQuery();
			fail("should not arrive here");
		}catch (OdaException e) 
		{
			
		}
		query.prepare( queryText );
		
		assertNotNull( query.executeQuery() );
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Query.setProperty(String, String)'
	 */
	public void testSetProperty( ) throws OdaException
	{
		try
		{
			query.setProperty("","");			
		}catch (UnsupportedOperationException e)
		{
			fail("Should not arrive here.");
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Query.close()'
	 */
	public void testClose( ) throws OdaException
	{
		query.prepare( queryText );
		query.setMaxRows( 2 );
		query.executeQuery( );
		query.close( );
		try
		{
			query.setMaxRows( 3 );
			fail( "Should not arrive here" );
		}
		catch ( OdaException e )
		{
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Query.setMaxRows(int)'
	 */
	public void testSetMaxRows( ) throws OdaException
	{
		query.prepare( queryText );
		
		query.setMaxRows( 2 );
		IResultSet rs = query.executeQuery();
		for(int i = 0; i < 2; i ++)
		{	
			rs.next();
		}
		assertFalse( rs.next());
		
		query.setMaxRows( -20 );
		query.prepare( queryText );
		rs=query.executeQuery();
		int count = 0;
		while( rs.next() )
		{
			count++;
		}
		assertEquals( count, 4);
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Query.getMaxRows()'
	 */
	public void testGetMaxRows( ) throws OdaException
	{
		query.setMaxRows( 10 );
		assertEquals( 10, query.getMaxRows() );
		query.setMaxRows( -10 );
		assertEquals( 0, query.getMaxRows());
		query.setMaxRows( 0 );
		assertEquals( 0, 0 );
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Query.getMetaData()'
	 */
	public void testGetMetaData( ) throws OdaException
	{
		query.prepare( queryText );
		IResultSetMetaData rsMetaData = query.getMetaData();
		assertNotNull( rsMetaData );
		assertEquals( rsMetaData.getColumnCount(), 1);
		assertEquals( rsMetaData.getColumnName(1), "book.category");
	}
}
