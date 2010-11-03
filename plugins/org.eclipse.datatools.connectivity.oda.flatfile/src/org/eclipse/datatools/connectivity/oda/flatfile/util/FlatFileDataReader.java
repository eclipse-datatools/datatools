/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *******************************************************************************/

package org.eclipse.datatools.connectivity.oda.flatfile.util;

//import java.io.File;
//import java.io.FileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
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
	private int[] selectColumIndexes; 
	
	private List nextDataLine;

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
	
	private void initNameIndexMap( ) throws OdaException
	{
		assert originalColumnNames != null;
		HashMap originalColumnNameIndexMap = new HashMap( ); 
		for (int i = 0; i < originalColumnNames.length; i++)
		{
			originalColumnNameIndexMap.put( originalColumnNames[i].trim( ).toUpperCase( ), 
					Integer.valueOf( i ) );
		}
		selectColumIndexes = new int[rsmd.getColumnCount( )];

		for ( int i = 0; i < rsmd.getColumnCount( ); i++ )
		{
			selectColumIndexes[i] = findIndex(rsmdHelper.getOriginalColumnName( rsmd.getColumnName( i + 1 )), originalColumnNameIndexMap);
		}
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
		List v = fetchQueriedDataFromFileToList( );
		return copyDataFromListToTwoDimensionArray( v );
	}
	
	/**
	 * Read a row from the source data
	 * @return
	 * @throws OdaException
	 * @throws IOException
	 */
	public List readLine( ) throws OdaException, IOException
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
	
				this.flatFileBufferedReader = new FlatFileBufferedReader( new FileInputStream( dataFilePath ),
						this.charSet, this.delimiter);
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
			FlatFileBufferedReader br = new FlatFileBufferedReader( fis, this.charSet, this.delimiter);
			List columnLine;
			while ( isEmptyRow( columnLine = br.readLine( ) ) )
			{
				continue;
			}
			count = columnLine.size( );
			br.close( );
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
			throw new OdaException( Messages.getString( "query_invalidTableName" )
					+ this.homeDir
					+ File.separator
					+ this.currentTableName ); //$NON-NLS-1$
		return file.getAbsolutePath( );
	}

	/**
	 * See if this row is empty or not
	 * @param row
	 * @return
	 * @throws OdaException
	 */
	public static boolean isEmptyRow( List line ) throws OdaException
	{
		if ( line == null )
			throw new OdaException( Messages.getString( "query_INVALID_FLAT_FILE" ) ); //$NON-NLS-1$

		return line.isEmpty( ) || ( line.size( ) == 1 && line.get( 0 ).equals( "" )); //$NON-NLS-1$
	}

	/**
	 * 
	 * @return
	 * @throws OdaException
	 */
	private List fetchQueriedDataFromFileToList( ) throws OdaException
	{
		List result = new ArrayList( );
		try
		{
			if ( isFirstTimeToReadSourceData )
			{
				// make a copy of column names if there are
				if ( this.hasColumnNames )
				{
					List columeNameLine;
					while ( isEmptyRow( columeNameLine = flatFileBufferedReader.readLine( ) ) )
					{
						continue;
					}
					this.originalColumnNames = getColumnNameArray( columeNameLine );
					initNameIndexMap( );
				}
					

				// skip Type information. The type information is in the second
				// line
				// of file
				if ( this.hasTypeLine )
				{
					while ( isEmptyRow( flatFileBufferedReader.readLine( ) ) )
						continue;
				}
				
				if ( !this.hasColumnNames )
				{
					while ( isEmptyRow( nextDataLine = flatFileBufferedReader.readLine( ) ))
					{
						continue;
					}
					this.originalColumnNames = createTempColumnNames( nextDataLine );
					initNameIndexMap( );
				}
				else
				{
					nextDataLine = flatFileBufferedReader.readLine( );
				}
				isFirstTimeToReadSourceData = false;
			}

			// temporary variable which is used to store the data of a row
			// fetched from a flat file
			
			
			int counterLimitPerFetch = fetchCounter + MAX_ROWS_PER_FETCH;

			while ( ( this.maxRowsToRead <= 0 ? true
					: this.fetchCounter < this.maxRowsToRead )
					&& this.fetchCounter < counterLimitPerFetch 
					&& nextDataLine != null )
			{
				if ( !isEmptyRow( nextDataLine ) )
				{
					fetchCounter++;
					result.add( fetchQueriedDataFromRow( nextDataLine) );
				}
				nextDataLine = flatFileBufferedReader.readLine( );
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
	public String[] getColumnNameArray( List line )
			throws OdaException
	{
		if ( line == null )
			throw new OdaException( Messages.getString( "common_CANNOT_FIND_COLUMN" ) ); //$NON-NLS-1$
		return getStringArrayFromList( line );
	}

	/**
	 * Put the contants of the list into a string array
	 * @param list
	 * @return
	 */
	public static String[] getStringArrayFromList( List list )
	{
		String[] array = null;
		if ( list != null )
		{
			array = new String[list.size( )];
			for ( int i = 0; i < list.size( ); i++ )
				array[i] = (String) list.get( i );
		}
		return array;
	}

	/**
	 * Feed the row data from a List to a two-dimension array. The string
	 * value is trimmed before being copied into array.
	 * 
	 * @param v
	 * @return a String two dimension array with each horizontal array contains
	 *         a row
	 * @throws OdaException
	 */
	private String[][] copyDataFromListToTwoDimensionArray( List v )
			throws OdaException
	{
		String[][] rowSet = new String[v.size( )][this.rsmd.getColumnCount( )];
		for ( int i = 0; i < v.size( ); i++ )
		{
			String[] temp = (String[]) v.get( i );
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
	 * @throws OdaException 
	 */
	private String[] createTempColumnNames( List aRow ) throws OdaException
	{
		String[] tempColumnNames = new String[aRow.size()];

		for ( int i = 0; i < aRow.size(); i++ )
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
	 * @return an array of data values for each specified column names from a
	 *         row. The "specified column names" are obtained from meta data
	 * @throws OdaException
	 */
	private String[] fetchQueriedDataFromRow( List aRow ) throws OdaException
	{
		String[] sArray = new String[rsmd.getColumnCount( )];
		for ( int i = 0; i < sArray.length; i++ )
		{
			int location = selectColumIndexes[i];
			if ( location != -1 )
			{
				if ( location >= aRow.size( ) )
					throw new OdaException( Messages.getString( "query_INVALID_FLAT_FILE" ) ); //$NON-NLS-1$
				sArray[i] = aRow.get( location ).toString( );
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
	 */
	private int findIndex( String value, HashMap  originalColumnNameIndexMap)
	{
		Integer index = (Integer)(originalColumnNameIndexMap.get( value.trim( ).toUpperCase( ) ));
		if (index == null)
		{
			return -1;
		}
		else
		{
			return index.intValue( );
		}
	}

}
