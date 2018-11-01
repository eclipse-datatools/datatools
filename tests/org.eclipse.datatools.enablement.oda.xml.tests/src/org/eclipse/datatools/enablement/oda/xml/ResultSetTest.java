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

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.impl.Connection;
import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestConstants;

import com.ibm.icu.util.TimeZone;

/**
 * ResultSet test case.
 */
public class ResultSetTest extends BaseTest
{
	Connection conn;
	TimeZone defaultZone = null;
	IResultSet rs;
	String queryText;
	String[] columnNames = new String[]{
			"book.category","book.title","book.title.language",
			"book.author.name","book.author.country","book.date","book.price",
			"book.isn","book.timestamp","book.time"
	};
	String[] columnTypes = new String[]{
			"String","String","String","String","String","Date","Double","Int","Timestamp","Time"
	};
	String[] titles = new String[]{"Everyday Italian","Harry Potter","XQuery Kick Start",
			"Learning XML",};
	Date[] dates = null; 
	double[] prices = new double[]{25.99,14.59,33.55,222.10};
	int[] isns = new int[]{12325423,12325323,12325423,52325423};
	String[] timestamps = new String[]{"2000-02-02 00:00:00.000000001","2000-02-01 00:00:00.000000001",
			"2000-12-02 00:00:00.000000001","2000-02-12 00:00:00.000000001"};
	String[] time = new String[]{"12:21:55","12:21:56","12:21:57","12:21:58","12:21:59"};
	
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		defaultZone = TimeZone.getDefault( );
		TimeZone.setDefault( TimeZone.getTimeZone( "GMT" ) );
		dates = new Date[]{Date.valueOf("2005-2-2"), Date.valueOf("2004-4-4"),Date.valueOf("2003-3-3"),Date.valueOf("2002-2-2")}; 
		Properties prop = new Properties();
		queryText =	"book#-TNAME-#book#:#[//book]#:#{book.category;String;/@category}," +
				"{book.title;String;title}," +
				"{book.title.language;String;title/@lang}," +
				"{book.author.name;String;author/@name}," +
				"{book.author.country;String;author/@country}," +
				"{book.date;Date;date}," +
				"{book.price;Double;price}," +
				"{book.isn;Int;isn}," +
				"{book.timestamp;Timestamp;timestamp},"+
				"{book.time;Time;time}";
		prop.put(Constants.CONST_PROP_FILELIST, TestConstants.SMALL_XML_FILE);
		conn = new Connection( );
		conn.open(prop);
		IQuery query = conn.newQuery( null );
		query.prepare( queryText );
		rs = query.executeQuery();
	}

	protected void tearDown( ) throws Exception
	{
		TimeZone.setDefault( this.defaultZone );
		rs.close( );
		conn.close( );
		super.tearDown( );
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getMetaData()'
	 */
	public void testGetMetaDataWithoutXMLFile( ) throws OdaException
	{
		rs.close( );
		conn.close( );
		Properties prop = new Properties();
		prop.put(Constants.CONST_PROP_SCHEMA_FILELIST,TestConstants.SMALL_XML_FILE);
		conn = new Connection( );
		conn.open( prop ); //no xml file is specified
		IQuery query = conn.newQuery( null );
		query.prepare( queryText );
		rs = query.executeQuery( );
		IResultSetMetaData meta = rs.getMetaData();
		assertEquals( meta.getColumnCount(), 10);
		for( int i = 1; i <= meta.getColumnCount(); i ++)
		{
			assertEquals( meta.getColumnName( i ), columnNames[i-1]);
			assertEquals( meta.getColumnLabel( i ), columnNames[i-1]);
			assertEquals( meta.getColumnType( i ), DataTypes.getType( columnTypes[i-1]));
		}
	}
	
	public void testGetMetaData( ) throws OdaException
	{
		IQuery query = conn.newQuery( null );
		query.prepare( queryText );
		rs = query.executeQuery( );
		IResultSetMetaData meta = rs.getMetaData();
		assertEquals( meta.getColumnCount(), 10);
		for( int i = 1; i <= meta.getColumnCount(); i ++)
		{
			assertEquals( meta.getColumnName( i ), columnNames[i-1]);
			assertEquals( meta.getColumnLabel( i ), columnNames[i-1]);
			assertEquals( meta.getColumnType( i ), DataTypes.getType( columnTypes[i-1]));
		}
	}
	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.close()'
	 */
	public void testClose( ) throws OdaException
	{
		rs.next();
		assertEquals( 1, rs.getRow() );
		rs.close();
		try{
			rs.getRow();
			fail( "Should not arrive here");
		}catch ( OdaException e )
		{}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.setMaxRows(int)'
	 */
	public void testSetMaxRows( ) throws OdaException
	{
		rs.setMaxRows( 2 );
		for( int i = 0; i < 2; i ++)
		{
			assertTrue(rs.next());
		}
		assertFalse( rs.next());
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getRow()'
	 */
	public void testGetRow( ) throws OdaException
	{
		int count = 0;
		assertEquals( 0, rs.getRow() );
		while( rs.next() )
		{
			count++;
			assertEquals( count, rs.getRow() );
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getString(int)'
	 */
	public void testGetStringInt( ) throws OdaException
	{
		for( int i = 0; i < 4; i++ )
		{
			assertTrue( rs.next() );
			assertEquals( rs.getString(2), titles[i]);
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getString(String)'
	 */
	public void testGetStringString( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getString("book.title"), titles[count]);
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getInt(int)'
	 */
	public void testGetIntInt( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getInt(8), isns[count]);
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getInt(String)'
	 */
	public void testGetIntString( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getInt("book.isn"), isns[count]);
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getDouble(int)'
	 */
	public void testGetDoubleInt( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getDouble(7), prices[count],10);
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getDouble(String)'
	 */
	public void testGetDoubleString( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getDouble("book.price"), prices[count],10);
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getBigDecimal(int)'
	 */
	public void testGetBigDecimalInt( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getBigDecimal(8), new BigDecimal(isns[count]));
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getBigDecimal(String)'
	 */
	public void testGetBigDecimalString( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getBigDecimal("book.isn"), new BigDecimal(isns[count]));
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getDate(int)'
	 */
	public void testGetDateInt( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getDate(6), dates[count]);
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getDate(String)'
	 */
	public void testGetDateString( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getDate("book.date"), dates[count]);
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getTime(int)'
	 */
	public void testGetTimeInt( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			//Some locale will cause the 
			if( rs.getTime(10) == null)
				break;
			assertEquals( rs.getTime(10).toString(), time[count]);
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getTime(String)'
	 */
	public void testGetTimeString( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			if( rs.getTime("book.time") == null)
				break;
			assertEquals( rs.getTime("book.time").toString(), time[count]);
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getTimestamp(int)'
	 */
	public void testGetTimestampInt( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getTimestamp(9).toString(), timestamps[count].toString());
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.getTimestamp(String)'
	 */
	public void testGetTimestampString( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			assertEquals( rs.getTimestamp("book.timestamp").toString(), timestamps[count].toString());
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.wasNull()'
	 */
	public void testWasNull( ) throws OdaException
	{
		int count = 0;
		while( rs.next() )
		{
			rs.getTimestamp("book.category");
			assertTrue(rs.wasNull());
			rs.getInt("book.isn");
			assertFalse(rs.wasNull());
			count++;
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.findColumn(String)'
	 */
	public void testFindColumn( ) throws OdaException
	{
		for(int i = 1; i <= columnNames.length; i++)
		{
			assertEquals( rs.findColumn(columnNames[i-1]), i);
		}
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.manipulateData(String, String)'
	 */
	public void testManipulateData( )
	{

	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.detectNewRow(String)'
	 */
	public void testDetectNewRow( )
	{

	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.ResultSet.wakeup()'
	 */
	public void testWakeup( )
	{

	}

}
