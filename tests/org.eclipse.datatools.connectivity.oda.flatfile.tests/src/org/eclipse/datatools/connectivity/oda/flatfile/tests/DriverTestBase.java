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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.Connection;
import org.eclipse.datatools.connectivity.oda.flatfile.FlatFileDriver;

import junit.framework.TestCase;

/**
 * Base Class for testing FlatFileDriver
 */

public abstract class DriverTestBase extends TestCase
{

	protected FlatFileDriver cf = null;

	/**
	 * 
	 * @throws OdaException
	 */
	public DriverTestBase( ) throws OdaException
	{
		TestUtil.createTestFile( getDelimiter( ) );
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		cf = new FlatFileDriver( );
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
	public void testGetConnection( ) throws OdaException
	{
		assertTrue( cf.getConnection( null ) instanceof Connection );
	}

	protected abstract String getDelimiter( );

	protected abstract String getDelimiterName( );

	protected abstract String getExtension( );
}
