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
public class ConcurrentQueriesFromOneConnection extends BaseTest
{
	private IResultSet rs1;
	private IResultSet rs2;
	private String queryText;
	private Connection conn;
	
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		queryText =	"table0#-TNAME-#"
			+ "table0#:#[/Report/Details]#:#"
			+ "{pagebreak.visibility;STRING;/pagebreak.visibility}";
		conn = new Connection( );
	}
	
	private void openConnectionFromFile( ) throws Exception
	{
		Properties p = new Properties( );
		p.put(Constants.CONST_PROP_FILELIST, TestConstants.HUGE_XML_FOR_PERFORMANCE);
		conn.open( p );
	}
	
	private void openConnectionFromInputStream( ) throws Exception
	{
		Map appContext = new HashMap( );
		appContext.put( Constants.APPCONTEXT_INPUTSTREAM, new FileInputStream( TestConstants.HUGE_XML_FOR_PERFORMANCE ) );
		conn.setAppContext( appContext );
		conn.open( null );
	}
	
	private void createResultSets( ) throws Exception
	{
		IQuery query = conn.newQuery( null );
		query.prepare( queryText );
		rs1 = query.executeQuery();
		
		query = conn.newQuery( null );
		query.prepare( queryText );
		rs2 = query.executeQuery( );
	}

	protected void tearDown( ) throws Exception
	{
		rs1.close( );
		rs2.close( );
		conn.close( );
		super.tearDown( );
	}
	
	private void concurrentRun( ) throws Exception
	{
		int count = 0;;
		while (rs1.next( ))
		{
			count++;
			rs2.next();
			assertEquals( rs1.getString( 1 ),  rs2.getString( 1 ) );
		}
		assertTrue( count > 0 );
		System.out.println( "row count: " + count);
	}
	
	public void testConcurrentFromFile( ) throws Exception
	{
		openConnectionFromFile( );
		createResultSets( );
		concurrentRun( );
	}
	
	public void testConcurrentFromInputStream( ) throws Exception
	{
		openConnectionFromInputStream( );
		createResultSets( );
		concurrentRun( );
	}
	
}
