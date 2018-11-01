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
package org.eclipse.datatools.enablement.oda.xml.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;
import org.eclipse.datatools.enablement.oda.xml.BaseTest;
import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.impl.Connection;
import org.eclipse.datatools.enablement.oda.xml.impl.ResultSet;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestConstants;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestUtil;

/**
 * 
 */
public class SaxParserTest extends BaseTest
{
	private IResultSet rs;
	static String lineSeparator = (String) java.security.AccessController.doPrivileged( new sun.security.action.GetPropertyAction( "line.separator" ) );

	private String testString = "book#:#[//book]#:#{book.category;String;//book/@category},{book.title;String;//book/title},{book.author_1;String;//book/author[1]/@name},{book.author_2;String;//book/author[2]/@name}"
			+ "#-# stat #:#[/library/book/title]#:#{cat9;String;},{cat10;String;/},{cat;String;../@category}"
			+ "#-# aut  hor  #:#[//book/author]#:#{title;String;../title},{lang;String;../title/@lang},{author;String;/@name},{country;String;/@country},{date;String;../date},{isn;String;../isn},{category;String;../@category}"
			+ "#-# title#:#[/library/*/ad/../title]#:#{title;String;},{lang;String;/@lang},{author;String;../*/@name}"
			+ "#-# one#:#[//author]#:#{author;String;@name},{title;String;../title},{category;String;../../book[1]/@category},{category2;String;../@category}"
			+ "#-# none#:#[//]#:#{author;String;@name}"
			+ "#-# recursive#:#[//Book]#:#{locationD;String;D/@location},{locationBook;String;@location},{locationC;String;../@location},{locationB;String;../../B/@location},{locationA;String;../../@location}"
			+ "#-# utf#:#[//book]#:#{title;String;title}"
			+ "#-# duplicate#:#[//suburb]#:#{name;String;@name}"
			+ "#-# complexNest#:#[//suburb]#:#{name;String;@name},{nest-2;String;../../nest},{nest-1;String;../nest},{nest0;String;/nest},{nest1;String;/suburb/nest},{nest3;String;suburb/suburb/nest},{nest4;String;suburb/suburb/suburb/nest}"
			+ "#-# filter1#:#[//entry]#:#{b-bar1;String;/field[@b='bar1']},{b-bar2;String;/field[@b='bar2']},{b-bar9;String;/field[@b='bar9']},{a-foo;String;/field[@a='foo']}"
			+ "#-# filter2#:#[//field]#:#{b-bar1;String;[@b='time']}"
			+ "#-# filter3#:#[//entry/field[@b='time']]#:#{time;String;}"
			+ "#-# filter4#:#[//Book[@type='fiction']]#:#{book.title;String;/Title},{book.author_paul;String;/Author[@type='firstclass']}"			
			+ "#-# recursiveFilter#:#[//Book]#:#{defaultB;String;../../B},{specificB1;String;../../B[@location='Hongkong']}, {specificB2;String;../../B[@location='London']}, {noB;String;../../B[@location='Nothing']}"
			+ "#-# relativeLocation#:#[//Book]#:#{title;String;//Title}"
			+ "#-# nestedTableRootFilter#:#[//employee[@type='employeeType1']]#:#{name;STRING;properties/property/@name},{value;STRING;properties/property/@value},{type;STRING;/@type}"
			+ "#-# emptyElement#:#[/NewDataSet/program/activity]#:#{ProgramID;Int;../ProgramID},{ProgramName;String;../ProgramName},{ActivityID;Int;/ActivityID},{ActivityName;String;/ActivityName}"
			+ "#-# tableFilter#:#[/BookStore/Book[@id=\"A\"]/Author]#:#{book.author;String;}"
			+ "#-# simple#:#[/library/book]#:#{book.category;String;/@category},{book.title;String;/title},{book.author_1;String;/author[1]/@name},{book.author_2;String;/author[2]/@name}"
			+ "#-# attributeFilter#:#[/BookStore/Book[@a=\"2\"]]#:#{b;STRING;/@b}"
			+ "#-# Asterisk#:#[/*/*/nest]#:#{b;STRING;}"
			+ "#-# soap#:#[/SOAP-ENV:Envelope/SOAP-ENV:Body/GetWeatherByZipCodeResponse/GetWeatherByZipCodeResult]#:#{Latitude;STRING;/Latitude},{Longitude;STRING;/Longitude},{AllocationFactor;STRING;/AllocationFactor},{FipsCode;STRING;/FipsCode},{PlaceName;STRING;/PlaceName},{StateCode;STRING;/StateCode},{Status;STRING;/Status},{Day;STRING;/Details/WeatherData/Day},{WeatherImage;STRING;/Details/WeatherData/WeatherImage},{MaxTemperatureF;STRING;/Details/WeatherData/MaxTemperatureF},{MinTemperatureF;STRING;/Details/WeatherData/MinTemperatureF},{MaxTemperatureC;STRING;/Details/WeatherData/MaxTemperatureC},{MinTemperatureC;STRING;/Details/WeatherData/MinTemperatureC}"
			+ "#-# anyAndRecursive#:#[//test]#:#{test_name;STRING;test_name},{area_name;STRING;../../../../area_name}"
			+ "#-# parameter#:#[//{?entry?}]#:#{b-bar1;String;/field[@{?b?}='bar1']},{b-bar2;String;/field[@{?b?}='{?bar2?}']},{b-bar9;String;/field[@b='bar9']},{a-foo;String;/field[@a='foo']}"
			+ "#-# doubleslash1#:#[//block//]#:#{id;STRING;@id}"
			+ "#-# doubleslash2#:#[/Top/Tree/blocks/block/parameters/parameter]#:#{id;STRING;//@id}"
			/*this line should be the last because name space information is saved with the last table mapping currently  */
			+ "#-# nameSpace#:#[/feed/entry/g:id]#:#{title;STRING;../title},{g:price;STRING;../g:price},{g:id;STRING;}#:#<\"openSearch\",\"http:%%a9.com%-%spec%opensearchrss%1.0%\";\"g\",\"http:%%base.google.com%ns%1.0\";\"batch\",\"http:%%schemas.google.com%gdata%batch\";\"gm\",\"http:%%base.google.com%ns-metadata%1.0\";\"\",\"http:%%www.w3.org%2005%Atom\";\"SOAP-ENV\",\"http:%%schemas.xmlsoap.org%soap%envelope%\";\"xsd\",\"http:%%www.w3.org%2001%XMLSchema\";\"SOAP-ENC\",\"http:%%schemas.xmlsoap.org%soap%encoding%\";\"xsi\",\"http:%%www.w3.org%2001%XMLSchema-instance\">";
	
	private MappedTables mt;

	protected void setUp( ) throws Exception
	{
		super.setUp( );
	}

	protected void tearDown( ) throws Exception
	{
		super.tearDown( );
	}

	public void test0( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST0_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		System.out.println(file.getAbsolutePath());
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.SMALL_XML_FILE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"simple",
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		
		rs.close( );
		conn.close( );
		
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST0_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST0_GOLDEN_XML ) ) );
	}

	public void test1( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST1_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		System.out.println(file.getAbsolutePath());
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.SMALL_XML_FILE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"aut  hor", 
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST1_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST1_GOLDEN_XML ) ) );
	}
	

	public void test2( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST2_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.SMALL_XML_FILE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"title",
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST2_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST2_GOLDEN_XML ) ) );
	}

	public void test3( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST3_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.SMALL_XML_FILE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"stat" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST3_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST3_GOLDEN_XML ) ) );
	}

	public void test4( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST4_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );

		Connection conn = new Connection( );
		Properties prop = new Properties( );
		prop.put( Constants.CONST_PROP_FILELIST,
				TestConstants.BOOKSTORE_XSD_FILE );

		File input = new File( TestConstants.SMALL_XML_FILE );
		InputStream is = input.toURL( ).openStream( );
		HashMap map = new HashMap( );
		map.put( Constants.APPCONTEXT_INPUTSTREAM, is );
		conn.setAppContext( map );
		conn.open( prop );
		IQuery query = conn.newQuery( null );
		query.prepare( "stat#-TNAME-#" + this.testString);

		rs = (ResultSet) query.executeQuery( );

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST4_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST4_GOLDEN_XML ) ) );

		rs.close( );
		conn.close( );
	}

	public void test5( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST5_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.SMALL_XML_FILE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"none" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST5_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST5_GOLDEN_XML ) ) );
	}

	public void test6( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST6_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.SMALL_XML_FILE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"one" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST6_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST6_GOLDEN_XML ) ) );
	}

	public void test7( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST7_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.RECURSIVE_XML_FILE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"recursive" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST7_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST7_GOLDEN_XML ) ) );
	}

	// This test is used to test a bug which will cause the incomplete of values
	// of
	// certain column fetched from xml file. The test xml file is critical and
	// should
	// not be modified.
	public void test8( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST8_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );
		String text = "test#:#[//row]#:#{X;String;/axis_x},{Y;String;/axis_y},{Value;String;/value}";

		mt = new MappedTables( text );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.CRITICAL_XML_FILE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"test" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST8_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST8_GOLDEN_XML ) ) );
	}

	// Test xml encoded with UTF-8 BOM
	public void test9( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST9_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );
		BufferedOutputStream bos = new BufferedOutputStream( fos );
		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.UTF8BOM );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"utf" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			bos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( "UTF-8" ) );
		bos.write( lineSeparator.getBytes( "UTF-8" ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				bos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( "UTF-8" ) );
			bos.write( lineSeparator.getBytes( "UTF-8" ) );
		}
		assertFalse( rs.next( ) );

		bos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST9_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST9_GOLDEN_XML ) ) );
	}
	
	public void test10( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST10_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		FileInputStream iStream = new FileInputStream(new File( TestConstants.RECURSIVE_XML_FILE ));
		Connection conn = new Connection( );
		Map appContext = new HashMap( );
		appContext.put( Constants.APPCONTEXT_INPUTSTREAM, iStream );
		conn.setAppContext( appContext );
		conn.open( null );
		rs = new ResultSet( conn,
				mt,
				"recursive" ,
				0);
		rs = new ResultSet( conn,
				mt,
				"recursive" ,
				0);
		rs = new ResultSet( conn,
				mt,
				"recursive" ,
				0);
		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST10_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST10_GOLDEN_XML ) ) );
	}
	
	/**
	 * Test nest duplicate recusive xml files.
	 * @throws OdaException
	 * @throws IOException
	 */
	public void test11( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST11_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.RECURSIVE_DUPLICATENAME );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"duplicate", 
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST11_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST11_GOLDEN_XML ) ) );
	}
	
	/**
	 * Test nest duplicate recusive xml files.
	 * @throws OdaException
	 * @throws IOException
	 */
	public void test12( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST12_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.RECURSIVE_DUPLICATENAME);
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"complexNest", 
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST12_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST12_GOLDEN_XML ) ) );
	}
	
	/**
	 * Test nest duplicate recusive xml files.
	 * @throws OdaException
	 * @throws IOException
	 */
	public void test13( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST13_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.TEST_FILTER );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"filter1", 
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST13_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST13_GOLDEN_XML ) ) );
	}
	
	/**
	 * Test nest duplicate recusive xml files.
	 * @throws OdaException
	 * @throws IOException
	 */
	public void test14( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST14_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.TEST_FILTER );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"filter2", 
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST14_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST14_GOLDEN_XML ) ) );
	}
	
	public void test15( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST15_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.TEST_RELATIVE_LOCATION );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"relativeLocation",
				0 );

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST15_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST15_GOLDEN_XML ) ) );
	}
	
	/**
	 * Test table filter
	 * @throws OdaException
	 * @throws IOException
	 */
	public void test16( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST16_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.TEST_FILTER );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"filter3", 
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST16_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST16_GOLDEN_XML ) ) );
	}
	
	/**
	 * Test mixed filter
	 * @throws OdaException
	 * @throws IOException
	 */
	public void test17( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST17_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.MIXED_FILTER );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"filter4", 
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST17_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST17_GOLDEN_XML ) ) );
	}
	
	
	/**
	 * Test nested table root filter.
	 * @throws OdaException
	 * @throws IOException
	 */
	public void test18( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST18_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.NESTED_TABLE_ROOT_FILTER );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"nestedTableRootFilter", 
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST18_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST18_GOLDEN_XML ) ) );
	}
	
	/**
	 * Tests the case when there exist empty elements in the xml file
	 * 
	 * @throws OdaException
	 * @throws IOException
	 */
	public void test19( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST19_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.EMPTY_ELEMENT );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"emptyElement",
				0 );

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST19_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST19_GOLDEN_XML ) ) );
	}

	/**
	 * This is a test for table filter. A filter on an attribute of high level of root should be
	 * carry forward whenever as appropriate. 
	 * @throws OdaException
	 * @throws IOException
	 */
	public void test20( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST20_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.TABLE_FILTER );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"tableFilter",
				0 );

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST20_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST20_GOLDEN_XML ) ) );
	}

	public void test21( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST21_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.BOOKSTORE_XML_FILE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"attributeFilter",
				0 );

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST21_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST21_GOLDEN_XML ) ) );
	}
	
	public void test22( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST22_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.RECURSIVE_DUPLICATENAME );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"Asterisk",
				0 );

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST22_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST22_GOLDEN_XML ) ) );
	}
	
	public void test23( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST23_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.XML_FILE_WITH_NAMESPACE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"soap",
				0 );

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST23_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST23_GOLDEN_XML ) ) );
	}
	
	public void test24( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST24_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.RECURSIVE_XML_FILE );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"recursiveFilter" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST24_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST24_GOLDEN_XML ) ) );
	}
	
	public void test25( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST25_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.XML_FILE_WITH_NAMESPACE2 );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"nameSpace" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST25_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST25_GOLDEN_XML ) ) );
	}
	
	public void test26( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST26_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.ANY_RECURSIVE_XML );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"anyAndRecursive" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST26_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST26_GOLDEN_XML ) ) );
	}
	
	public void test27( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST27_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.DOUBLE_SLASH_XML );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"doubleslash1" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST27_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST27_GOLDEN_XML ) ) );
	}
	
	public void test28( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST28_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.DOUBLE_SLASH_XML );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"doubleslash2" ,
				0);

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST28_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST28_GOLDEN_XML ) ) );
	}
	
	/**
	 * Test relative file
	 * 
	 * @throws Exception
	 */
	public void test29( ) throws Exception
	{
		File file = new File( TestConstants.SAX_PARSER_TEST29_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );
		Connection conn = new Connection( );
		Properties p = new Properties( );
		p.put( Constants.CONST_PROP_FILELIST, TestConstants.SMALL_XML_FILE_RELATIVE );
		HashMap appContext = new HashMap();
		ResourceIdentifiers ri = new ResourceIdentifiers();
		ri.setApplResourceBaseURI( new File(TestConstants.SMALL_XML_RESOURCE).toURI( ) );
		appContext.put( ResourceIdentifiers.ODA_APP_CONTEXT_KEY_CONSUMER_RESOURCE_IDS, ri );
		conn.setAppContext( appContext );
		conn.open( p );
		rs = new ResultSet( conn,
				mt,
				"simple",
				0 );

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );
		rs.close( );
		conn.close( );
		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST29_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST29_GOLDEN_XML ) ) );
	}
	
	
	public void testParameters( ) throws OdaException, IOException
	{
		File file = new File( TestConstants.SAX_PARSER_TEST_PARAM_OUTPUT_XML );

		if ( file.exists( ) )
			file.delete( );
		File path = new File( file.getParent( ) );
		if ( !path.exists( ) )
			path.mkdir( );
		file.createNewFile( );
		FileOutputStream fos = new FileOutputStream( file );

		mt = new MappedTables( testString );

		Connection conn = new Connection( );
		Properties prop = new Properties( );
		prop.put( Constants.CONST_PROP_FILELIST,
				TestConstants.TEST_FILTER );

		conn.open( prop );
		IQuery query = conn.newQuery( null );
		query.prepare( "parameter#-TNAME-#" + this.testString);
		query.setString( "entry", "entry" );
		query.setString( "b", "b");
		query.setString( "bar2", "bar2" );
		rs = (ResultSet) query.executeQuery( );

		for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
			fos.write( ( rs.getMetaData( ).getColumnName( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
		fos.write( lineSeparator.getBytes( ) );

		while ( rs.next( ) )
		{
			for ( int i = 0; i < rs.getMetaData( ).getColumnCount( ); i++ )
				fos.write( ( rs.getString( i + 1 ) + "\t\t\t\t\t" ).getBytes( ) );
			fos.write( lineSeparator.getBytes( ) );
		}
		assertFalse( rs.next( ) );

		fos.close( );

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST_PARAM_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST_PARAM_GOLDEN_XML ) ) );

		rs.close( );
		conn.close( );
	}
}
