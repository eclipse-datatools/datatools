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

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.enablement.oda.xml.impl.Connection;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestConstants;


/**
 * ResultSet test case.
 */
public class SpecialEncodingTest extends BaseTest
{
	private IResultSet rs;
	private String queryText;
	private Connection conn;
	
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		queryText =	"table0#-TNAME-#table0#:#[/xml/testElement]#:#{specialChars;STRING;/specialChars}";
		conn = new Connection( );
	}
	
	private void openConnectionFromFile( ) throws Exception
	{
		Properties p = new Properties( );
		p.put(Constants.CONST_PROP_FILELIST, TestConstants.SPECIAL_ENCODING_XML);
		p.put( Constants.CONST_PROP_ENCODINGLIST, "ISO-8859-1" );
		conn.open( p );
	}
	
	private void openConnectionFromInputStream( ) throws Exception
	{
		Map appContext = new HashMap( );
		appContext.put( Constants.APPCONTEXT_INPUTSTREAM, new FileInputStream( TestConstants.SPECIAL_ENCODING_XML ) );
		conn.setAppContext( appContext );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_ENCODINGLIST, "ISO-8859-1" );
		conn.open( p );
	}
	
	private void validateResultSet( ) throws Exception
	{
		IQuery query = conn.newQuery( null );
		query.prepare( queryText );
		rs = query.executeQuery();
		assertTrue( rs.next( ) );
		assertTrue( rs.getString( 1 ) != null && !rs.getString( 1 ).equals( "" ));
	}

	protected void tearDown( ) throws Exception
	{
		rs.close( );
		conn.close( );
		super.tearDown( );
	}
	

	
	public void testConcurrentFromFile( ) throws Exception
	{
		openConnectionFromFile( );
		validateResultSet( );
	}
	
	public void testConcurrentFromInputStream( ) throws Exception
	{
		openConnectionFromInputStream( );
		validateResultSet( );
	}
}
