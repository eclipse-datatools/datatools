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
package org.eclipse.datatools.enablement.oda.xml;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.impl.Connection;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestConstants;

/**
 * 
 */
public class ConnectionTest extends BaseTest
{
	Connection conn;
	Properties prop;
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		conn = new Connection();
		prop = new Properties();
		prop.put(Constants.CONST_PROP_RELATIONINFORMATION,"book#:#[//book]#:#{book.category;String;@category},{book.title;String;title},{book.author_1;String;author[1]@name},{book.author_2;String;author[2]@name}");
		prop.put(Constants.CONST_PROP_FILELIST,TestConstants.SMALL_XML_FILE);
		prop.put(Constants.CONST_PROP_SCHEMA_FILELIST,TestConstants.SMALL_XML_FILE);
	}

	protected void tearDown( ) throws Exception
	{
		conn.close( );
		super.tearDown( );
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Connection.open(Properties)'
	 */
	public void testOpen( ) throws OdaException
	{
		conn.open( prop );
		assertTrue( conn.isOpen());
		conn.close();
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Connection.close()'
	 */
	public void testClose( ) throws OdaException
	{
		conn.open( prop );
		conn.close();
		assertTrue( !conn.isOpen() );
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Connection.isOpen()'
	 */
	public void testIsOpen( ) throws OdaException
	{
		assertFalse( conn.isOpen());
		conn.open( prop );
		assertTrue( conn.isOpen());
		conn.close();
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Connection.getMetaData(String)'
	 */
	public void testGetMetaData( ) throws OdaException
	{
		
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Connection.newQuery(String)'
	 */
	public void testNewQuery( ) throws OdaException
	{
		conn.open(prop);
		IQuery query = conn.newQuery( null );
		assertTrue(query!=null);
		conn.close();
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Connection.getMaxQueries()'
	 */
	public void testGetMaxQueries( ) throws OdaException
	{
		assertEquals(conn.getMaxQueries(),0);
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Connection.commit()'
	 */
	public void testCommit( ) throws OdaException
	{
		try
		{
			conn.commit();
			fail("Should not arrive here.");
		}
		catch ( UnsupportedOperationException e )
		{
			
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.Connection.rollback()'
	 */
	public void testRollback( ) throws OdaException
	{
		try
		{
			conn.rollback();
			fail("Should not arrive here.");
		}
		catch ( UnsupportedOperationException e )
		{
			
		}
	}

}
