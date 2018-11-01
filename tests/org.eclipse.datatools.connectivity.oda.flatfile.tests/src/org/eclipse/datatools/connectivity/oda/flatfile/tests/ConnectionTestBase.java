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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.Connection;
import org.eclipse.datatools.connectivity.oda.flatfile.FlatFileQuery;

import junit.framework.TestCase;

/**
 * Base class for testing Connection implementation.
 */

public abstract class ConnectionTestBase extends TestCase
{

	protected Connection connection = null;

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		connection = new Connection( );

	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown( ) throws Exception
	{
		super.tearDown( );

	}

	public ConnectionTestBase( ) throws OdaException
	{
		TestUtil.createTestFile( getDelimiter( ) );
	}

	public void testClose( )
	{
		Properties prop = this.setUpDefaultProperties( );

		try
		{
			connection.open( prop );
			connection.close( );
			assertFalse( connection.isOpen( ) );
		}
		catch ( OdaException e )
		{
			fail( "Failed in testClose()" );
		}

	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testCreateStatement( ) throws OdaException
	{
		Properties prop = this.setUpDefaultProperties( );
		connection.open( prop );
		assertTrue( connection.newQuery( null ) instanceof FlatFileQuery );

	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testIsOpen( ) throws OdaException
	{
		assertEquals( connection.isOpen( ), false );

		try
		{
			Properties prop = this.setUpDefaultProperties( );
			connection.open( prop );
			assertEquals( connection.isOpen( ), true );
		}
		catch ( OdaException e )
		{
			fail( "Failed in testIsOpen()" );
		}
	}

	/**
	 * 
	 * @return
	 */
	protected Properties setUpDefaultProperties( )
	{
		Properties prop = new Properties( );
		prop.setProperty( CommonConstants.CONN_HOME_DIR_PROP, TestUtil.getHomeDir( ) );
		prop.setProperty( CommonConstants.CONN_CHARSET_PROP, "UTF-16LE" );
		return prop;
	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void testOpen( ) throws OdaException
	{
		Properties prop = new Properties( );

		// test open connection with null property
		boolean hasOpenFailed = false;
		try
		{
			connection.open( null );
			assertTrue( connection.isOpen( ) );
		}
		catch ( OdaException e )
		{
			hasOpenFailed = true;
		}
		assertTrue( hasOpenFailed );

		// test open connection with invalid property
		hasOpenFailed = false;
		try
		{
			prop.clear( );
			prop.setProperty( CommonConstants.CONN_HOME_DIR_PROP, "" );
			prop.setProperty( CommonConstants.CONN_CHARSET_PROP, "US-ASCII" );
			connection.open( prop );
			assertTrue( connection.isOpen( ) );
		}
		catch ( OdaException e )
		{
			hasOpenFailed = true;
		}
//		assertTrue( hasOpenFailed );

        // test open connection without HOME property
        hasOpenFailed = false;
        try
        {
            prop.clear( );
            connection.open( prop );
        }
        catch ( OdaException e )
        {
            hasOpenFailed = true;
        }
//        assertTrue( hasOpenFailed );

		// test open connection with valid properties
		hasOpenFailed = false;
		try
		{
			prop = this.setUpDefaultProperties( );
			connection.open( prop );
			assertTrue( connection.isOpen( ) );
		}
		catch ( OdaException e )
		{
			hasOpenFailed = true;
		}
		assertFalse( hasOpenFailed );

		// test open connection with valid delimiter comma
		hasOpenFailed = false;
		try
		{
			prop.clear( );
			prop = this.setUpDefaultProperties( );
			prop.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
					getDelimiterName( ) );
			connection.open( prop );
			assertTrue( connection.isOpen( ) );
		}
		catch ( OdaException e )
		{
			hasOpenFailed = true;
		}
		assertFalse( hasOpenFailed );

		// test open connection with invalid delimiter
		hasOpenFailed = false;
		try
		{
			prop.clear( );
			prop = this.setUpDefaultProperties( );
			prop.setProperty( CommonConstants.CONN_DELIMITER_TYPE, "." );
			connection.open( prop );
			assertTrue( connection.isOpen( ) );
		}
		catch ( OdaException e )
		{
			hasOpenFailed = true;
		}
		assertTrue( hasOpenFailed );
	}

	/**
	 * 
	 * @return
	 */
	protected abstract String getDelimiter( );

	protected abstract String getDelimiterName( );

	protected abstract String getExtension( );
}
