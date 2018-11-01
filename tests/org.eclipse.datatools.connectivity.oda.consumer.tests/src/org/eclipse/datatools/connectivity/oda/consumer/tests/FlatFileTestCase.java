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
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaConnection;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaDriver;

import junit.framework.TestCase;

/**
 * @author lchan
 *
 */
public class FlatFileTestCase extends TestCase
{
	static final String TEST_FLATFILE_ID = 
		"org.eclipse.datatools.connectivity.oda.flatfile"; //$NON-NLS-1$

	private static IDriver sm_odaDriver;
	private static IConnection sm_odaConn;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		
		if( sm_odaDriver == null )
			sm_odaDriver = new OdaDriver( TEST_FLATFILE_ID );
		if( sm_odaConn == null )
			sm_odaConn = sm_odaDriver.getConnection( TEST_FLATFILE_ID );
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception
	{
		super.tearDown();
		// up to subclass to close connection at tearDown
	}

	protected static IConnection getOdaConn()
	{
		return sm_odaConn;
	}

	protected static IDriver getOdaDriver()
	{
		return sm_odaDriver;
	}

	public final void testOpenConnection()
	{
		assertTrue( sm_odaConn instanceof OdaConnection );
	}
}
