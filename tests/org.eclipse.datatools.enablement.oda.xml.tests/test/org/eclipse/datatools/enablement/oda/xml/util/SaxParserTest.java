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
package org.eclipse.datatools.enablement.oda.xml.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.BaseTest;
import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.impl.Connection;
import org.eclipse.datatools.enablement.oda.xml.impl.ResultSet;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestConstants;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestUtil;

import sun.awt.AppContext;

/**
 * 
 */
public class SaxParserTest extends BaseTest
{

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
			+ "#-# relativeLocation#:#[//Book]#:#{title;String;//Title}"
			+ "#-# nestedTableRootFilter#:#[//employee[@type='employeeType1']]#:#{name;STRING;properties/property/@name},{value;STRING;properties/property/@value},{type;STRING;/@type}"
			+ "#-# emptyElement#:#[/NewDataSet/program/activity]#:#{ProgramID;Int;../ProgramID},{ProgramName;String;../ProgramName},{ActivityID;Int;/ActivityID},{ActivityName;String;/ActivityName}"
			+ "#-# tableFilter#:#[/BookStore/Book[@id=\"A\"]/Author]#:#{book.author;String;}"
			+ "#-# simple#:#[/library/book]#:#{book.category;String;/@category},{book.title;String;/title},{book.author_1;String;/author[1]/@name},{book.author_2;String;/author[2]/@name}"
			+ "#-# attributeFilter#:#[/BookStore/Book[@a=\"2\"]]#:#{b;STRING;/@b}"
			+ "#-# Asterisk#:#[/*/*/nest]#:#{b;STRING;}";
	
	private RelationInformation ri;

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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.SMALL_XML_FILE );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.SMALL_XML_FILE );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.SMALL_XML_FILE );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.SMALL_XML_FILE );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );

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

		ResultSet rs = (ResultSet) query.executeQuery( );

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

		AppContext.getAppContext( ).remove( Constants.APPCONTEXT_INPUTSTREAM );
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.SMALL_XML_FILE );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.SMALL_XML_FILE );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.RECURSIVE_XML_FILE );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( text );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.CRITICAL_XML_FILE );
		ResultSet rs = new ResultSet( content,
				ri,
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
		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.UTF8BOM );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		FileInputStream iStream = new FileInputStream(new File( TestConstants.RECURSIVE_XML_FILE ));
		XMLCreatorContent content = new XMLCreatorContent( iStream );
		ResultSet rs = new ResultSet( content,
				ri,
				"recursive" ,
				0);
		rs = new ResultSet( content,
				ri,
				"recursive" ,
				0);
		rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.RECURSIVE_DUPLICATENAME ); 
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.RECURSIVE_DUPLICATENAME );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.TEST_FILTER );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.TEST_FILTER );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.TEST_RELATIVE_LOCATION );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.TEST_FILTER );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.MIXED_FILTER );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.NESTED_TABLE_ROOT_FILTER );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.EMPTY_ELEMENT );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.TABLE_FILTER );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.BOOKSTORE_XML_FILE );
		ResultSet rs = new ResultSet( content,
				ri,
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

		ri = new RelationInformation( testString );
		XMLCreatorContent content = new XMLCreatorContent( TestConstants.RECURSIVE_DUPLICATENAME );
		ResultSet rs = new ResultSet( content,
				ri,
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

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.SAX_PARSER_TEST22_OUTPUT_XML ),
				new File( TestConstants.SAX_PARSER_TEST22_GOLDEN_XML ) ) );
	}
}
