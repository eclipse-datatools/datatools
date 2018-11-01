/*
 *******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Random;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.util.FlatFileDataReader;

/**
 * TestUtil class for generating test data
 */

public class TestUtil
{

	public static final String DATASET = "UTF-8";
	public static final String SUFFIX_COMMA = "-comma";
	public static final String SUFFIX_SEMICOLON = "-semicolon";
	public static final String SUFFIX_PIPE = "-pipe";
	public static final String SUFFIX_TAB = "-tab";
	public static final String CSV_EXTENSION = ".csv";
	public static final String SSV_EXTENSION = ".ssv";
	public static final String TSV_EXTENSION = ".tsv";
	public static final String PSV_EXTENSION = ".psv";
	private static String fileExtension = "";
	
	public static int HUGE_TABLE_ROW_COUNT = 2 * FlatFileDataReader.MAX_ROWS_PER_FETCH + 5;

	public static String[] COMPLICATE_TAB_NAMES = {
			"table_complicateColName1",
			"table_complicate Col Name2",
			"table_complicate Col Name3 from name2",
			"table_complicate Col Name4 from",
			"from table_complicate Col Name5 from from name4"
	};

	public static String[] COMPLICATE_TAB_COLNAMES = {
			"INT COL from Integer",
			"Double coL from from double",
			"from int to int from from int",
			"from int to int from from int from"
	};

	/**
	 * 
	 * @param delimiter
	 * @throws OdaException
	 */
	public static void createTestFile( String delimiter ) throws OdaException
	{
		String path = createTestFileDirectory( );

		if ( delimiter.equals( CommonConstants.DELIMITER_COMMA_VALUE ) )
			fileExtension = CSV_EXTENSION;
		else if ( delimiter.equals( CommonConstants.DELIMITER_SEMICOLON_VALUE ) )
			fileExtension = SSV_EXTENSION;
		else if ( delimiter.equals( CommonConstants.DELIMITER_TAB_VALUE ) )
			fileExtension = TSV_EXTENSION;
		else if ( delimiter.equals( CommonConstants.DELIMITER_PIPE_VALUE ) )
			fileExtension = PSV_EXTENSION;

		createTestFile_test1( path, delimiter );
		createTestFile_test2( path, delimiter );
		createTestFile_test3( path, delimiter );
		createTestFile_test4( path, delimiter );
		createTestFile_test5( path, delimiter );
		createTestFile_test6( path, delimiter );
		createTestFile_test7( path, delimiter );
		createTestFile_test8( path, delimiter );
		createTestFile_test9( path, delimiter );
		createTestFile_test10( path, delimiter );
		createTestFile_test11( path, delimiter );
		createTestFile_test12( path, delimiter );
		createTestFile_test13( path, delimiter );
		createTestFile_test14( path, delimiter );
		createTestFile_hugeRows( path, delimiter );
		createTestFile_compliateColName( path, delimiter );
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static String getHomeDir( )
	{
		URL url = TestUtil.class.getProtectionDomain( ).getCodeSource( ).getLocation( );
		String pathBase = url.toString( );
		
		if( !pathBase.matches( ".*\\Q \\E.*" ))
			try
			{
				pathBase = new URI( pathBase ).getPath( );
			}
			catch ( URISyntaxException e1 )
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if ( pathBase.endsWith( "bin/" ) ) //$NON-NLS-1$
			pathBase = pathBase.substring( 0,
					pathBase.length( ) - 4 );
		if ( pathBase.endsWith( "bin" ) ) //$NON-NLS-1$
			pathBase = pathBase.substring( 0,
					pathBase.length( ) - 3 );
		
		return pathBase + "testdatabase";
	}

	/**
	 * 
	 * @throws OdaException
	 * @throws URISyntaxException 
	 */
	private static String createTestFileDirectory( ) throws OdaException
	{
		
		String path = getHomeDir();
		File file = new File( path );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return path;
		}
		try
		{
			file.mkdirs( );
			file.deleteOnExit( );
		}
		catch ( SecurityException e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		return path;
	}

	private static void createTestFile_test1( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table1" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			Random r = new Random( );
			String endOfLine = new String( "\n" );
			osw.flush( );
			String header = "INT0_COL"
					+ delimiter + "DOUBLE0_COL" + delimiter + "STRING_COL"
					+ delimiter + "DATE_COL" + delimiter + "TIME_COL"
					+ delimiter + "TIMESTAMP_COL" + delimiter + "BLOB_COL"
					+ delimiter + "INT1_COL" + delimiter + "DOUBLE1_COL"
					+ delimiter + "BIGDECIMAL_COL\n";
			String types = "INT"
					+ delimiter + "DOUBLE" + delimiter + " STRING" + delimiter
					+ " DATE" + delimiter + " TIME" + delimiter + " TIMESTAMP"
					+ delimiter + " BLOB" + delimiter + " INT" + delimiter
					+ " DOUBLE" + delimiter + "BIGDECIMAL\n";
			osw.write( header );
			osw.write( types );
			for ( int i = 0; i < 1234; i++ )
			{
				for ( int j = 0; j < 10; j++ )
				{
					if ( j == 0 )
						osw.write( Integer.toString( i ) );
					if ( j == 1 || j == 8 )
						osw.write( Double.toString( r.nextDouble( ) ) );
					if ( j == 2 )
						osw.write( new String( "S"
								+ Float.toString( r.nextFloat( ) ) ) );
					if ( j == 3 )
					{
						int year = 1000 + i;
						int month = i % 12 + 1;
						int day = i % 28 + 1;
						String s = Integer.toString( year )
								+ "-" + Integer.toString( month ) + "-"
								+ Integer.toString( day );
						osw.write( s );
					}

					if ( j == 4 )
						osw.write( Time.valueOf( i % 24 + ":" + j + ":00" )
								.toString( ) );
					if ( j == 5 )
						osw.write( Integer.toString( new Timestamp( System.currentTimeMillis( ) ).getNanos( ) ) );
					if ( j == 6 )
					{
						osw.write( Double.toString( Double.MAX_VALUE ) );
						osw.write( new String( "BLOB" ) );
						osw.write( Long.toString( System.currentTimeMillis( ) ) );
					}
					if ( j == 7 )
						osw.write( Integer.toString( r.nextInt( ) ) );
					if ( j == 9 )
						osw.write( Double.toString( r.nextDouble( ) ) );
					if ( j < 9 )
						osw.write( delimiter );
				}
				osw.write( endOfLine );
			}
			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}

	
	private static void createTestFile_test2( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table2" + getSuffix( delimiter )
				+ fileExtension );

		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );
			String header = "INT0_COL"
					+ delimiter + "DOUBLE0_COL" + delimiter + "STRING_COL"
					+ delimiter + "DATE_COL" + delimiter + "TIME_COL"
					+ delimiter + "TIMESTAMP_COL" + delimiter + "BLOB_COL"
					+ delimiter + "INT1_COL" + delimiter + "DOUBLE1_COL"
					+ delimiter + "BIGDECIMAL_COL\n";
			String types = "INT"
					+ delimiter + "DOUBLE" + delimiter + " STRING" + delimiter
					+ " DATE" + delimiter + " TIME" + delimiter + " TIMESTAMP"
					+ delimiter + " BLOB" + delimiter + " INT" + delimiter
					+ " DOUBLE" + delimiter + "BIGDECIMAL\n";

			osw.write( header );
			osw.write( types );
			for ( int j = 0; j < 10; j++ )
			{
				if ( j == 0 )
					osw.write( "0" );
				if ( j == 1 )
					osw.write( "0.123456789" );
				if ( j == 2 )
					osw.write( "\" I'm, really, a lovely ,nice , \"\"STRING\"\"\"" );
				if ( j == 3 )
					osw.write( "2005-01-30" );
				if ( j == 4 )
					osw.write( "12:04:59" );
				if ( j == 5 )
					osw.write( "2005-01-31 12:12:12.6" );
				if ( j == 6 )
					osw.write( "BLOB?931348623157E=�D@LOB114Umߪ?:8?" );
				if ( j == 7 )
					osw.write( "-123456789" );
				if ( j == 8 )
					osw.write( "0.987654321" );
				if ( j == 9 )
					osw.write( "1234567891011121314151617.123456789" );
				if ( j < 9 )
				{
					osw.write( " " );
					osw.write( delimiter );
					osw.write( "  " );
				}
			}
			osw.write( endOfLine );
			osw.write( "1 "
					+ delimiter + delimiter + "      " + delimiter + delimiter
					+ "  " + delimiter + " " + delimiter + " " + delimiter
					+ " " + delimiter + " " + delimiter + " " + delimiter
					+ " \n" );
			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}

	private static void createTestFile_test3( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table3" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );
			String header = "NAME" + delimiter + "VALUE\n";
			String types = "String" + delimiter + "String\n";

			osw.write( header );
			osw.write( types );
			for ( int j = 0; j < 2; j++ )
			{
				if ( j == 0 )
					osw.write( "\" I'm, really, a lovely ,nice , \"\"STRING\"\"\"" );
				if ( j == 1 )
				{
					char b[] = {
							'2',
							'0',
							'0',
							'5',
							'-',
							'\r',
							'0',
							'1',
							'-',
							'3',
							'0'
					};
					osw.write( b );
				}
				if ( j < 2 )
				{
					osw.write( " " );
					osw.write( delimiter );
					osw.write( "  " );
				}
			}
			osw.write( endOfLine );
			osw.write( " "
					+ delimiter + delimiter + "      " + delimiter + delimiter
					+ "  " + delimiter + " " + delimiter + " " + delimiter
					+ " " + delimiter + " " + delimiter + " " + delimiter
					+ " \n" );
			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}

	private static void createTestFile_test4( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table4" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			Random r = new Random( );
			String endOfLine = new String( "\n" );
			osw.flush( );
			String header = "INT0_COL"
					+ delimiter + "DOUBLE0_COL" + delimiter + "STRING_COL"
					+ delimiter + "DATE_COL" + delimiter + "TIME_COL"
					+ delimiter + "TIMESTAMP_COL" + delimiter + "BLOB_COL"
					+ delimiter + "INT1_COL" + delimiter + "DOUBLE1_COL"
					+ delimiter + "BIGDECIMAL_COL\n";

			osw.write( header );
			for ( int i = 0; i < 1234; i++ )
			{
				for ( int j = 0; j < 10; j++ )
				{
					if ( j == 0 )
						osw.write( Integer.toString( i ) );
					if ( j == 1 || j == 8 )
						osw.write( Double.toString( r.nextDouble( ) ) );
					if ( j == 2 )
						osw.write( new String( "S"
								+ Float.toString( r.nextFloat( ) ) ) );
					if ( j == 3 )
					{
						int year = 1000 + i;
						int month = i % 12 + 1;
						int day = i % 28 + 1;
						String s = Integer.toString( year )
								+ "-" + Integer.toString( month ) + "-"
								+ Integer.toString( day );
						osw.write( s );
					}

					if ( j == 4 )
						osw.write( Time.valueOf( i % 24 + ":" + j + ":00" )
								.toString( ) );
					if ( j == 5 )
						osw.write( Integer.toString( new Timestamp( System.currentTimeMillis( ) ).getNanos( ) ) );
					if ( j == 6 )
					{
						osw.write( Double.toString( Double.MAX_VALUE ) );
						osw.write( new String( "BLOB" ) );
						osw.write( Long.toString( System.currentTimeMillis( ) ) );
					}
					if ( j == 7 )
						osw.write( Integer.toString( r.nextInt( ) ) );
					if ( j == 9 )
						osw.write( Double.toString( r.nextDouble( ) ) );
					if ( j < 9 )
						osw.write( delimiter );
				}
				osw.write( endOfLine );
			}
			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}

	private static void createTestFile_test5( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table5" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );
			String header = "STRING1"
					+ delimiter + " STRING2" + delimiter + "STRING3"
					+ delimiter + "STRING4" + delimiter + "STRING5 \n";
			String types = "STRING"
					+ delimiter + "STRING" + delimiter + " STRING" + delimiter
					+ " STRING" + delimiter + "STRING\n";

			osw.write( header );
			osw.write( types );
			for ( int j = 0; j < 4; j++ )
			{
				if ( j == 0 )
					osw.write( "\"\"\"\"\"\"\"\"" ); // """"""""->"""
				if ( j == 1 )
					osw.write( "\"\"\"ABC\"\"\"" ); // ""ABC"" ->"ABC"
				if ( j == 2 )
					osw.write( "" );
				if ( j == 3 )
					osw.write( "\"\"" );

				if ( j < 4 )
				{
					osw.write( " " );
					osw.write( delimiter );
					osw.write( "  " );
				}
			}
			osw.write( endOfLine );
			osw.write( "1 "
					+ delimiter + delimiter + "      " + delimiter + delimiter
					+ "\n" );
			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}

	private static void createTestFile_test6( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table6" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );
			String header = "STRING1"
					+ delimiter + " STRING2" + delimiter + "STRING3 \n";
			String types = "STRING"
					+ delimiter + "STRING" + delimiter + " STRING\n";

			osw.write( header );
			osw.write( types );
			for ( int j = 0; j < 2; j++ )
			{
				if ( j == 0 )
					osw.write( "\"" + delimiter + "\"" ); // """"""""->"""
				if ( j == 1 )
					osw.write( "\"\"\"" + delimiter + "\"\"\"" ); // ""ABC""
				// ->"ABC"
				if ( j == 2 )
					osw.write( "" );
				if ( j < 2 )
				{
					osw.write( " " );
					osw.write( delimiter );
					osw.write( " " );
				}
			}
			osw.write( endOfLine );

			osw.write( "1 "
					+ delimiter + delimiter + " " + delimiter + delimiter
					+ "\n" );

			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}

	private static void createTestFile_test7( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table7" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );

			for ( int j = 0; j < 2; j++ )
			{
				if ( j == 0 )
					osw.write( "\"" + "Here " + delimiter + " I am here" + "\"" ); // """"""""->"""
				if ( j == 1 )
					osw.write( "\"\"\"" + "A" + "\"\"\"" ); // ""ABC""
				// ->"ABC"
				if ( j == 2 )
					osw.write( "" );
				if ( j < 2 )
				{
					osw.write( " " );
					osw.write( delimiter );
					osw.write( " " );
				}
			}
			osw.write( endOfLine );

			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}

	private static void createTestFile_test8( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table8" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );

			String header = "\"\"\"Column_1\"\"\"\"\""
					+ delimiter + "\" Column_2,C2\"" + delimiter
					+ "\"Column_3, \"\", C3,\\\"\n";
			String type = "STRING"
					+ delimiter + "STRING" + delimiter + "STRING\n";
			String contant = "column1"
					+ delimiter + "column2" + delimiter + "column3";

			osw.write( header );
			osw.write( type );
			osw.write( contant );
			osw.write( endOfLine );

			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}
	
	private static void createTestFile_test9( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table9" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );

			String header = "Name"
					+ delimiter + "ID\"" + delimiter
					+ "City\n";
			String type = "STRING"
					+ delimiter + "INT" + delimiter + "STRING\n";
			String contant = "column1"
					+ delimiter + "column2" + delimiter + "column3";

			osw.write( header );
			osw.write( type );
			osw.write( contant );
			osw.write( endOfLine );

			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}
	
	private static void createTestFile_test10( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table10" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );

			String header = "Name"
					+ delimiter + "ID\"\"" + delimiter + "City\n";
			String type = "STRING" + delimiter + "INT" + delimiter + "STRING\n";
			String contant = "column1"
					+ delimiter + "column2" + delimiter + "column3";

			osw.write( header );
			osw.write( type );
			osw.write( contant );
			osw.write( endOfLine );

			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}

	private static void createTestFile_test11( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table11" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );

			String header = "\"Name\"\"\""
					+ delimiter + "ID" + delimiter + "\"\"\"\"\"\"\n";
			String type = "STRING" + delimiter + "INT" + delimiter + "STRING\n";
			String contant = "column1"
					+ delimiter + "column2" + delimiter + "column3";

			osw.write( header );
			osw.write( type );
			osw.write( contant );
			osw.write( endOfLine );

			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}
	
	private static void createTestFile_test12( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table12" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );

			String header = "Name"
					+ delimiter + "ID" + delimiter + "\"\"\n";
			String type = "STRING" + delimiter + "INT" + delimiter + "STRING\n";
			String contant = "column1"
					+ delimiter + "column2" + delimiter + "column3";

			osw.write( header );
			osw.write( type );
			osw.write( contant );
			osw.write( endOfLine );

			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}
	
	private static void createTestFile_test13( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table13" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );

			String header = "Name" + delimiter + "ID" + delimiter + "Test\"\"\n";
			String type = "STRING" + delimiter + "INT" + delimiter + "STRING\n";
			String contant = "column1"
					+ delimiter + "column2" + delimiter + "column3";

			osw.write( header );
			osw.write( type );
			osw.write( contant );
			osw.write( endOfLine );

			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}
	
	private static void createTestFile_test14( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table14" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );
			String endOfLine = new String( "\n" );
			osw.flush( );
			String header = "INT0_COL"
					+ delimiter + "STRING0_COL" + delimiter + "DOUBLE0_COL"
					+ delimiter + "DATE0_COL" + delimiter + "STRING1\n";
			String type = "INT"
					+ delimiter + "STRING" + delimiter + "DOUBLE" + delimiter
					+ "DATE" + delimiter + "STRING\n";
			osw.write( header );
			osw.write( type );

			for ( int i = 1; i <= 100; i++ )
			{
				if ( i == 1 )
				{
					osw.write( Integer.toString( i ) );
					osw.write( delimiter );
					osw.write( Integer.toString( i ) );
					osw.write( delimiter );
					osw.write( "3.1415926" );
					osw.write( delimiter );
					osw.write( "2011-12-01" );
				}
				else if ( i == 11 )
				{
					osw.write( Integer.toString( i ) );
					osw.write( delimiter );
					osw.write( Integer.toString( i ) );
					osw.write( delimiter );
					osw.write( "3.1415926" );
				}
				else if ( i == 22 )
				{
					osw.write( Integer.toString( i ) );
					osw.write( delimiter );
					osw.write( Integer.toString( i ) );
				}
				else if ( i == 33 )
				{
					osw.write( Integer.toString( i ) );
				}
				else
				{
					osw.write( Integer.toString( i ) );
					osw.write( delimiter );
					osw.write( Integer.toString( i ) );
					osw.write( delimiter );
					osw.write( "3.1415926" );
					osw.write( delimiter );
					osw.write( "2011-12-01" );
					osw.write( delimiter );
					osw.write( "default" );
				}
				osw.write( endOfLine );
			}
			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}

	private static String getSuffix( String delimiter )
	{
		if ( delimiter.equals( CommonConstants.DELIMITER_COMMA_VALUE ) )
			return SUFFIX_COMMA;
		else if ( delimiter.equals( CommonConstants.DELIMITER_SEMICOLON_VALUE ) )
			return SUFFIX_SEMICOLON;
		else if ( delimiter.equals( CommonConstants.DELIMITER_PIPE_VALUE ) )
			return SUFFIX_PIPE;
		else if ( delimiter.equals( CommonConstants.DELIMITER_TAB_VALUE ) )
			return SUFFIX_TAB;
		else
			return null;
	}

	private static void createTestFile_hugeRows( String path, String delimiter )
			throws OdaException
	{
		File file = null;
		file = new File( path
				+ File.separator + "table_hugeRows" + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			String endOfLine = new String( "\n" );
			osw.flush( );
			String header = "INT_COL\n";
			String types = "INT\n";
			osw.write( header );
			osw.write( types );
			for ( int i = 0; i < HUGE_TABLE_ROW_COUNT; i++ )
			{
				osw.write( i+1 + "" );
				osw.write( endOfLine );
			}
			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}
	
	private static void createTestFile_compliateColName( String path,
			String delimiter ) throws OdaException
	{
		for (String name: COMPLICATE_TAB_NAMES)
		{
			generateComplicateNameData(path, name, delimiter);
		}
		System.out.println();
	}
	
	private static void generateComplicateNameData( String path, String name,
			String delimiter ) throws OdaException
	{
		File file = new File( path
				+ File.separator + name + getSuffix( delimiter )
				+ fileExtension );
		if ( file.exists( ) )
		{
			file.deleteOnExit( );
			return;
		}
		try
		{
			FileOutputStream fos = new FileOutputStream( file );
			OutputStreamWriter osw = new OutputStreamWriter( fos, DATASET );

			Random r = new Random( );
			String endOfLine = new String( "\n" );
			osw.flush( );
			String header = "INT COL from Integer"
					+ delimiter + "Double coL from from double" + delimiter
					+ "from int to int from from int" + delimiter
					+ "from int to int from from int from" + "\n";
			osw.write( header );
			for ( int i = 0; i < 10; i++ )
			{
				osw.write( Integer.toString( i + 1 ));
				osw.write( delimiter );
				osw.write( Double.toString( r.nextDouble( ) ) );
				osw.write( delimiter );
				osw.write( Integer.toString( r.nextInt( ) ) );
				osw.write( delimiter );
				osw.write( Integer.toString( r.nextInt( ) ) );
				osw.write( endOfLine );
			}
			osw.close( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e.getMessage( ) );
		}
		file.deleteOnExit( );
	}
	
}