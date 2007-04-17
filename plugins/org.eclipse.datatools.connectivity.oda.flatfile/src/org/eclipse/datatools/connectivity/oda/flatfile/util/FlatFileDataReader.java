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

package org.eclipse.datatools.connectivity.oda.flatfile.util;

//import java.io.File;
//import java.io.FileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Vector;

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.ResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.flatfile.ResultSetMetaDataHelper;
import org.eclipse.datatools.connectivity.oda.flatfile.FlatFileQuery.FlatFileBufferedReader;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;

/**
 * FlatFileDataSourceReader is a utility class that help read and parse the raw data from flat file
 */

public class FlatFileDataReader
{

	private String homeDir;
	private String currentTableName;
	private char delimiter;
	private boolean hasColumnNames = true;
	private boolean hasTypeLine = true;
	private String charSet;
	private FlatFileBufferedReader flatFileBufferedReader=null;
	private IResultSetMetaData rsmd;
	private ResultSetMetaDataHelper rsmdHelper;
	private int statementMaxRows = 0;
	private int resultSetMaxRows = 0;
	private int maxRowsToRead = 0;
	private String[] originalColumnNames;
	private boolean isFirstTimeToReadSourceData = true;
	private boolean isFirstTimeToCallReadLine = true;
	private int fetchCounter = 0;

	//Max number of rows fetched each time from data source
	public static final int MAX_ROWS_PER_FETCH = 20000;
	
	/**
	 * Constructor
	 * 
	 * @param connProperties Connection properties
	 * @param currentTableName The current table name of this connection
	 * @param statementMaxRows	The max number of rows specified in the query
	 * @param rsmd	ResultSet meta-data
	 * @param rsmdHelper	ResultSet meta-data helper
	 * @throws OdaException
	 */
	public  FlatFileDataReader( Properties connProperties,String currentTableName,int statementMaxRows,
			IResultSetMetaData rsmd, ResultSetMetaDataHelper rsmdHelper ) throws OdaException
	{
		this.rsmd = rsmd;
		this.rsmdHelper = rsmdHelper;
		this.statementMaxRows = statementMaxRows;
		this.currentTableName = currentTableName;
		Properties properties = getCopyOfConnectionProperties( connProperties );
		populateHomeDir( properties );
		populateDelimiter( properties );
		populateCharSet( properties );
		populateHasColumnNames( properties );
		populateHasTypeLine( properties );
	}
	
	/**
	 * 
	 * @param connProperties
	 * @return
	 */
	private Properties getCopyOfConnectionProperties( Properties connProperties )
	{
		Properties copyConnProperites = new Properties( );

		copyConnProperites.setProperty( CommonConstants.CONN_CHARSET_PROP,
				connProperties.getProperty( CommonConstants.CONN_CHARSET_PROP ) );
		copyConnProperites.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
				connProperties.getProperty( CommonConstants.CONN_DELIMITER_TYPE ) );
		copyConnProperites.setProperty( CommonConstants.CONN_HOME_DIR_PROP,
				connProperties.getProperty( CommonConstants.CONN_HOME_DIR_PROP ) );
		copyConnProperites.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
				connProperties.getProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP ) );
		copyConnProperites.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP,
				connProperties.getProperty( CommonConstants.CONN_INCLTYPELINE_PROP ) );

		return copyConnProperites;
	}
	
	/**
	 * 
	 * @param connProperties
	 * @throws OdaException
	 */
	private void populateDelimiter( Properties connProperties )
			throws OdaException
	{
		this.delimiter = CommonConstants.getDelimiterValue( ( connProperties.getProperty( CommonConstants.CONN_DELIMITER_TYPE ) != null
				? connProperties.getProperty( CommonConstants.CONN_DELIMITER_TYPE )
				: CommonConstants.DELIMITER_COMMA ) ).charAt( 0 );
	}
	
	/**
	 * 
	 * @param connProperties
	 * @throws OdaException
	 */
	private void populateHomeDir( Properties connProperties )
			throws OdaException
	{
		this.homeDir = connProperties.getProperty( CommonConstants.CONN_HOME_DIR_PROP );
		File file = new File( this.homeDir );
		if ( !file.exists( ) )
			throw new OdaException( Messages.getString( "connection_CANNOT_OPEN_FLAT_FILE_DB_DIR" ) //$NON-NLS-1$
					+ this.homeDir );
	}

	/**
	 * 
	 * @param connProperties
	 */
	private void populateCharSet( Properties connProperties )
	{
		this.charSet = connProperties.getProperty( CommonConstants.CONN_CHARSET_PROP );
	}

	/**
	 * 
	 * @param connProperties
	 */
	private void populateHasColumnNames( Properties connProperties )
	{
		this.hasColumnNames = connProperties.getProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP )
		.equalsIgnoreCase( CommonConstants.INC_COLUMN_NAME_NO ) 
		? false
		: true;
	}

	/**
	 * 
	 * @param connProperties
	 */
	private void populateHasTypeLine( Properties connProperties )
	{
		this.hasTypeLine = connProperties.getProperty( CommonConstants.CONN_INCLTYPELINE_PROP )
		.equalsIgnoreCase( CommonConstants.INC_TYPE_LINE_NO ) 
		? false
		: true;
	}

	/**
	 * 
	 * @return
	 * @throws OdaException
	 */
	public String[][] getSourceData( ) throws OdaException
	{
		createBufferedReader( );
		Vector v = fetchQueriedDataFromFileToVector( );
		return copyDataFromVectorToTwoDimensionArray( v );
	}
	
	/**
	 * Read a row from the source data
	 * @return
	 * @throws OdaException
	 * @throws IOException
	 */
	public String readLine( ) throws OdaException, IOException
	{
		if ( isFirstTimeToCallReadLine )
		{
			createBufferedReader( );
			isFirstTimeToCallReadLine = false;
		}

		return flatFileBufferedReader.readLine( );
	}

	/**
	 * 
	 * @throws OdaException
	 */
	private void createBufferedReader( ) throws OdaException
	{
		try
		{
			if( this.flatFileBufferedReader == null )
			{
				String dataFilePath = findDataFileAbsolutePath( );
				if ( charSet == null || charSet.trim( ).length( ) == 0 )
					examCharset( dataFilePath );
	
				this.flatFileBufferedReader = new FlatFileBufferedReader( new InputStreamReader( new FileInputStream( dataFilePath ),
						this.charSet ) );
			}

		}
		catch ( IOException e )
		{
			throw new OdaException( e.getMessage( ) );
		}
	}

	/**
	 * 
	 * @param resultSetMaxRows
	 * @return
	 */
	public int getMaxRowsToRead( int resultSetMaxRows )
	{
		this.resultSetMaxRows = resultSetMaxRows;
		return this.maxRowsToRead = ( ( this.statementMaxRows != 0 && this.statementMaxRows < this.resultSetMaxRows ) || this.resultSetMaxRows == 0 )
				? this.statementMaxRows : this.resultSetMaxRows;
	}
	
	/**
	 * 
	 *
	 */
	public void clearBufferedReader( )
	{
		try
		{
			if ( this.flatFileBufferedReader != null )
				this.flatFileBufferedReader.close( );
		}
		catch ( IOException e )
		{
			
		}
		this.flatFileBufferedReader = null;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	public void finalize()
	{
		this.clearBufferedReader( );
	}

	/**
	 * If Charset is not set (that is, of null or empty value), test whether the
	 * file is encoded with "UTF-16LE" or "UTF-16BE". If neither, then treat
	 * file as using default "UTF-8"
	 */
	private void examCharset( String filepath ) throws OdaException, IOException
	{
		if ( this.charSet != null && this.charSet.length( ) > 0 )
			return;
		FileInputStream fis = new FileInputStream( filepath );
		byte[] byteMarker = new byte[2];
		fis.read( byteMarker );
		// file encoded using UTF-16LE sometimes have two bytes prefix
		// -1, -2
		// file encoded using UTF-16BE sometimes have two bytes prefix
		// -2, -1
		if ( byteMarker[0] == -1 && byteMarker[1] == -2 )
			this.charSet = "UTF-16LE"; //$NON-NLS-1$
		else if ( byteMarker[0] == -2 && byteMarker[1] == -1 )
			this.charSet = "UTF-16BE"; //$NON-NLS-1$
		else
			this.charSet = CommonConstants.CONN_DEFAULT_CHARSET;
		fis.close( );
	}

	/**
	 * 
	 * @param connProperties
	 * @param tableName
	 * @return
	 * @throws OdaException
	 */
	public int getColumnCount( ) throws OdaException
	{
		//this.currentTableName = tableName;

		int count;
		try
		{
			String dataFilePath = findDataFileAbsolutePath( );
			examCharset( dataFilePath );
			FileInputStream fis = new FileInputStream( dataFilePath );
			InputStreamReader isr = new InputStreamReader( fis, this.charSet );
			FlatFileBufferedReader br = new FlatFileBufferedReader( isr );
			String columeLine;
			while ( isEmptyRow( columeLine = br.readLine( ) ) )
			{
				continue;
			}
			count = splitDoubleQuotedString( columeLine ).size( );
			br.close( );
			isr.close( );
			fis.close( );
		}
		catch ( IOException e )
		{
			throw new OdaException( Messages.getString( "query_IO_EXCEPTION" ) //$NON-NLS-1$
					+ findDataFileAbsolutePath( ) );
		}

		return count;
	}

	/**
	 * Splites a row of data according to the delimiter, and stripped the additional double quotes
	 * @param aRow
	 * @return
	 * @throws OdaException
	 */
	private Vector splitDoubleQuotedString( String aRow ) throws OdaException
	{
		Vector result = new Vector( );
		if(aRow.endsWith( "\r" )){//$NON-NLS-1$
			aRow = aRow.substring( 0,aRow.length( )-1 );
		}
		char[] chars = aRow.toCharArray( );

		boolean startDoubleQuote = false;
		boolean finishAnElement = false;
		boolean hasConsectiveTwoDoubleQuotes = false;
		String currentString = ""; //$NON-NLS-1$
		//int x;

		for ( int i = 0; i < chars.length; i++ )
		{
			if ( i < chars.length - 1 )
			{
				// "
				if ( chars[i] == '"' )
				{
					// ""
					if ( chars[i + 1] == '"' )
					{
						if ( startDoubleQuote )
						{
							currentString += '"';
							i += 1;
							continue;
						}
						else
						{
							// """
							if ( i < chars.length - 2 && chars[i + 2] == '"' )
							{
								currentString += '"';
								i += 2;
								startDoubleQuote = !startDoubleQuote;
								continue;
							}
							else
							//""
							{
								hasConsectiveTwoDoubleQuotes = true;
								i = i + 1;
								if( i == chars.length-1 && currentString.trim( ).length( )==0 )
									result.add( currentString );
							}
						}
					}
					// "*
					else
					{
						startDoubleQuote = !startDoubleQuote;
						if ( !startDoubleQuote )
						{
							finishAnElement = true;
							continue;
						}
					}
				}
				else if ( chars[i] == this.delimiter && !startDoubleQuote )
				{
					if ( hasConsectiveTwoDoubleQuotes )
					{
						if ( currentString.trim( ).length( ) != 0 )
							throw new OdaException( Messages.getString( "invalid_flatfile_format" ) ); //$NON-NLS-1$
						else
							hasConsectiveTwoDoubleQuotes = false;
					}

					result.add( currentString );
					currentString = ""; //$NON-NLS-1$
					finishAnElement = false;
				}
				else
				{
					if ( finishAnElement == true && chars[i] != ' ' )
						throw new OdaException( Messages.getString( "invalid_flatfile_format" ) ); //$NON-NLS-1$
					currentString += chars[i];
				}
			}
			else
			{
				if ( chars[i] == '"' )
				{
					if ( !startDoubleQuote )
						throw new OdaException( Messages.getString( "invalid_flatfile_format" ) ); //$NON-NLS-1$
					else
					{
						result.add( currentString );
						startDoubleQuote = !startDoubleQuote;
						finishAnElement = true;
					}
				}
				else if ( chars[i] == this.delimiter )
				{
					result.add( currentString );
					result.add( "" ); //$NON-NLS-1$
					finishAnElement = false;
				}
				else
				{
					currentString += chars[i];
					result.add( currentString );
					finishAnElement = false;
				}

			}
		}
		
		if ( hasConsectiveTwoDoubleQuotes
				|| ( startDoubleQuote && !finishAnElement ) )
		{
			if ( currentString.trim( ).length( ) != 0 )
				throw new OdaException( Messages.getString( "invalid_flatfile_format" ) ); //$NON-NLS-1$
		}

		return result;
	}

	/**
	 * Find the absolute path of the file in which a specific table resides.
	 * 
	 * @param tableName
	 *            the name of table
	 * @return the String which contains the absolute path of that table
	 * @throws OdaException
	 *             if the table name cannot be found
	 */
	public String findDataFileAbsolutePath( )
			throws OdaException
	{
		File file = new File( this.homeDir
				+ File.separator + this.currentTableName.trim( ) );
		if ( !file.exists( ) )
			throw new OdaException( Messages.getString( "query_invalidTableName" ) + this.currentTableName ); //$NON-NLS-1$
		return file.getAbsolutePath( );
	}

	/**
	 * See if the bytecodes are in UTF8BOMFormat
	 * @param bytecodes
	 * @return
	 */
	private boolean isUTF8BOMFormat( byte[] bytecodes )
	{
		// file encoded using UTF-8 sometimes have three bytes prefix
		// -17,-69, and -65
		int[] UTF8Prefix = new int[]{
				-17, -69, -65
		};
		if ( bytecodes.length < UTF8Prefix.length )
			return false;
		for ( int i = 0; i < UTF8Prefix.length; i++ )
		{
			if ( bytecodes[i] != UTF8Prefix[i] )
				return false;
		}
		return true;
	}

	/**
	 * See if this row is empty or not
	 * @param row
	 * @return
	 * @throws OdaException
	 */
	public static boolean isEmptyRow( String row ) throws OdaException
	{
		if ( row == null )
			throw new OdaException( Messages.getString( "query_INVALID_FLAT_FILE" ) ); //$NON-NLS-1$

		return row.trim( ).length( ) <= 0;
	}

	/**
	 * 
	 * @return
	 * @throws OdaException
	 */
	private Vector fetchQueriedDataFromFileToVector( ) throws OdaException
	{
		Vector result = new Vector( );

		try
		{
			if ( isFirstTimeToReadSourceData )
			{
				// make a copy of column names if there are
				if ( this.hasColumnNames )
				{
					String columeNameLine;
					while ( isEmptyRow( columeNameLine = flatFileBufferedReader.readLine( ) ) )
					{
						continue;
					}
					this.originalColumnNames = getColumnNameArray( columeNameLine,
							true );
				}
				else
					this.originalColumnNames = createTempColumnNames( rsmd.getColumnCount( ) );

				// skip Type information. The type information is in the second
				// line
				// of file
				if ( this.hasTypeLine )
				{
					while ( isEmptyRow( flatFileBufferedReader.readLine( ) ) )
						continue;
				}

				isFirstTimeToReadSourceData = false;
			}

			// temporary variable which is used to store the data of a row
			// fetched from a flat file
			String aLine = null;

			int counterLimitPerFetch = fetchCounter + MAX_ROWS_PER_FETCH;

			while ( ( this.maxRowsToRead <= 0 ? true
					: this.fetchCounter < this.maxRowsToRead )
					&& this.fetchCounter < counterLimitPerFetch
					&& ( aLine = flatFileBufferedReader.readLine( ) ) != null )
			{
				if ( !isEmptyRow( aLine ) )
				{
					fetchCounter++;
					result.add( fetchQueriedDataFromRow( aLine,
							this.originalColumnNames ) );
				}
				// end of the file
				else
					continue;
			}

			return result;
		}
		catch ( IOException e )
		{
			throw new OdaException( e.getMessage( ) );
		}
	}

	/**
	 * Extract the column name from the line into the format of string array
	 * @param line
	 * @param isFirstLine
	 * @return
	 * @throws OdaException
	 */
	public String[] getColumnNameArray( String line, boolean isFirstLine )
			throws OdaException
	{
		if ( line == null )
			throw new OdaException( Messages.getString( "common_CANNOT_FIND_COLUMN" ) ); //$NON-NLS-1$
		String[] result = null;
		if ( isFirstLine )
		{
			try
			{
				if ( CommonConstants.CONN_DEFAULT_CHARSET.equals( this.charSet ) )
				{
					byte[] firstLineByteCodes;
					firstLineByteCodes = line.getBytes( this.charSet );
					if ( isUTF8BOMFormat( firstLineByteCodes ) )
						line = line.substring( 1 );
				}
				result = getStringArrayFromVector( splitDoubleQuotedString( line ) );
			}
			catch ( UnsupportedEncodingException e )
			{
				throw new OdaException( e );
			}
		}
		else
		{
			result = getStringArrayFromVector( splitDoubleQuotedString( line ) );

		}

		return result;
	}

	/**
	 * Put the contants of the vector into a string array
	 * @param vector
	 * @return
	 */
	public static String[] getStringArrayFromVector( Vector vector )
	{
		String[] array = null;
		if ( vector != null )
		{
			array = new String[vector.size( )];
			for ( int i = 0; i < vector.size( ); i++ )
				array[i] = (String) vector.get( i );
		}
		return array;
	}

	/**
	 * Feed the row data from a Vector to a two-dimension array. The string
	 * value is trimmed before being copied into array.
	 * 
	 * @param v
	 * @return a String two dimension array with each horizontal array contains
	 *         a row
	 * @throws OdaException
	 */
	private String[][] copyDataFromVectorToTwoDimensionArray( Vector v )
			throws OdaException
	{
		String[][] rowSet = new String[v.size( )][this.rsmd.getColumnCount( )];
		for ( int i = 0; i < v.size( ); i++ )
		{
			String[] temp = (String[]) v.elementAt( i );
			for ( int j = 0; j < temp.length; j++ )
			{
				if ( temp[j] != null )
					rowSet[i][j] = temp[j].trim( );
				else
				{
					throw new OdaException( Messages.getString( "data_read_error" ) ); //$NON-NLS-1$
				}
			}
		}
		return rowSet;
	}

	/**
	 * 
	 * @param columnCount
	 * @return
	 */
	private String[] createTempColumnNames( int columnCount )
	{
		String[] tempColumnNames = new String[columnCount];

		for ( int i = 0; i < columnCount; i++ )
		{
			tempColumnNames[i] = "COLUMN_" + ( i + 1 ); //$NON-NLS-1$
		}

		return tempColumnNames;
	}

	/**
	 * Fetch data from a row.
	 * 
	 * @param aRow
	 *            a row read from table
	 * @param originalColumnNames
	 *            an array that contains all column names of a table
	 * @return an array of data values for each specified column names from a
	 *         row. The "specified column names" are obtained from meta data
	 * @throws OdaException
	 */
	private String[] fetchQueriedDataFromRow( String aRow,
			String[] originalColumnNames ) throws OdaException
	{
		String[] sArray = new String[rsmd.getColumnCount( )];
		// Ignore all quotes which is used in the input file for the
		// clarity of the data
		Vector vTemp = splitDoubleQuotedString( aRow );
		ResultSetMetaData metadata = (ResultSetMetaData) rsmd;
		for ( int i = 0; i < sArray.length; i++ )
		{
			int location = findLocationOfValueInStringArray( rsmdHelper.getOriginalColumnName( metadata.getColumnName( i + 1 ) ),
					originalColumnNames );
			if ( location != -1 )
			{
				if ( location >= vTemp.size( ) )
					throw new OdaException( Messages.getString( "query_INVALID_FLAT_FILE" ) ); //$NON-NLS-1$
				sArray[i] = vTemp.elementAt( location ).toString( );
			}
		}
		return sArray;
	}

	/**
	 * Return the 0-based position of a value in the given array
	 * 
	 * @param value
	 * @param array
	 * @return
	 * @throws OdaException
	 */
	private int findLocationOfValueInStringArray( String value, String[] array )
			throws OdaException
	{
		int result = -1;
		try
		{
			for ( int i = 0; i < array.length; i++ )
			{
				if ( value.trim( ).equalsIgnoreCase( array[i].trim( ) ) )
				{
					result = i;
					break;
				}
			}
			return result;
		}
		catch ( Exception e )
		{
			throw new OdaException( Messages.getString( "query_COLUMN_NAME_ERROR" ) ); //$NON-NLS-1$
		}
	}

}
