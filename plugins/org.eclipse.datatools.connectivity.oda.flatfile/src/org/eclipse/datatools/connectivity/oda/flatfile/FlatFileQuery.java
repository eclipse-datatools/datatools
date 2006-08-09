/*
 *******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Actuate Corporation - initial API and implementation
 ******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Vector;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;

/**
 * Flat file data provider's implementation of the ODA IQuery interface. It
 * supports single result set and no input parameters.
 */
public class FlatFileQuery implements IQuery
{

	public static final int DEFAULT_MAX_ROWS = 1000;
	private static final String NAME_LITERAL = "NAME"; //$NON-NLS-1$
	private static final String TYPE_LITERAL = "TYPE"; //$NON-NLS-1$

	// The home directory specified in associated connection
	private String homeDirectory = null;

	// The table that the query operates on
	private String currentTableName = null;

	// The max number of rows that can be read
	private int maxRows = 0;

	// The Connection instance associated with the Query.
	private IConnection connection = null;

	// The meta data of the query's result set.
	// It is available only after a query is prepared.
	private IResultSetMetaData resultSetMetaData = null;

	// The charset value to decode the flat file data
	private String charSet = null;

	// Whether to use 2nd line as Type line
	private boolean hasTypeLine = true;

	/**
	 * Constructor
	 * 
	 * @param homeDir
	 *            the directory in which the data files reside
	 * @param host
	 *            The connection which creates this query
	 * @param charSet
	 *            The character set used to decode the file
	 * @throws OdaException
	 */
	FlatFileQuery( String homeDir, IConnection host, String charSet,
			boolean inclTypeLine ) throws OdaException
	{
		if ( homeDir == null || host == null )
			throw new OdaException( Messages.getString( "common_ARGUMENT_CANNOT_BE_NULL" ) ); //$NON-NLS-1$
		this.homeDirectory = homeDir;
		this.connection = host;
		this.charSet = charSet;
		this.hasTypeLine = inclTypeLine;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#prepare(java.lang.String)
	 */
	public void prepare( String queryText ) throws OdaException
	{
		validateOpenConnection( );
		String formattedQuery = formatQueryText( queryText );
		validateQueryText( formattedQuery );
		prepareMetaData( formattedQuery );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setAppContext(java.lang.Object)
	 */
	public void setAppContext( Object context ) throws OdaException
	{
		// do nothing; no support for pass-through application context
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setProperty(java.lang.String,
	 *      java.lang.String)
	 */
	public void setProperty( String name, String value ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#close()
	 */
	public void close( ) throws OdaException
	{
		currentTableName = null;
		homeDirectory = null;
		maxRows = 0;
		connection = null;
		resultSetMetaData = null;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setMaxRows(int)
	 */
	public void setMaxRows( int max ) throws OdaException
	{
		this.maxRows = max;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getMaxRows()
	 */
	public int getMaxRows( ) throws OdaException
	{
		return this.maxRows;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getMetaData()
	 */
	public IResultSetMetaData getMetaData( ) throws OdaException
	{
		return this.resultSetMetaData;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#executeQuery()
	 */
	public IResultSet executeQuery( ) throws OdaException
	{
		// fetch data from file to a Vector
		Vector v = fetchQueriedDataFromFileToVector( );

		// initialize a String array with data from a Vector
		String[][] rowSet = copyDataFromVectorToTwoDimensionArray( v );

		return new ResultSet( rowSet, this.resultSetMetaData );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(java.lang.String,
	 *      int)
	 */
	public void setInt( String parameterName, int value ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(int, int)
	 */
	public void setInt( int parameterId, int value ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(java.lang.String,
	 *      double)
	 */
	public void setDouble( String parameterName, double value )
			throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(int, double)
	 */
	public void setDouble( int parameterId, double value ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(java.lang.String,
	 *      java.math.BigDecimal)
	 */
	public void setBigDecimal( String parameterName, BigDecimal value )
			throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(int,
	 *      java.math.BigDecimal)
	 */
	public void setBigDecimal( int parameterId, BigDecimal value )
			throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(java.lang.String,
	 *      java.lang.String)
	 */
	public void setString( String parameterName, String value )
			throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(int,
	 *      java.lang.String)
	 */
	public void setString( int parameterId, String value ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(java.lang.String,
	 *      java.sql.Date)
	 */
	public void setDate( String parameterName, Date value ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(int,
	 *      java.sql.Date)
	 */
	public void setDate( int parameterId, Date value ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(java.lang.String,
	 *      java.sql.Time)
	 */
	public void setTime( String parameterName, Time value ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(int,
	 *      java.sql.Time)
	 */
	public void setTime( int parameterId, Time value ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(java.lang.String,
	 *      java.sql.Timestamp)
	 */
	public void setTimestamp( String parameterName, Timestamp value )
			throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(int,
	 *      java.sql.Timestamp)
	 */
	public void setTimestamp( int parameterId, Timestamp value )
			throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#findInParameter(java.lang.String)
	 */
	public int findInParameter( String parameterName ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getParameterMetaData()
	 */
	public IParameterMetaData getParameterMetaData( ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#clearInParameters()
	 */
	public void clearInParameters( ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setSortSpec(org.eclipse.datatools.connectivity.oda.SortSpec)
	 */
	public void setSortSpec( SortSpec sortBy ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getSortSpec()
	 */
	public SortSpec getSortSpec( ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/**
	 * Validate whether the query's connection is open.
	 * 
	 * @throws OdaException
	 *             if connection is not open yet
	 */
	private void validateOpenConnection( ) throws OdaException
	{
		if ( connection.isOpen( ) == false )
			throw new OdaException( Messages.getString( "common_CONNECTION_HAS_NOT_OPEN" ) ); //$NON-NLS-1$
	}

	/**
	 * Validate whether the query text is valid.
	 * 
	 * @param formattedQuery
	 *            the SQL command
	 * @throws OdaException
	 *             if given text is invalid or if any other error occurs
	 */
	private void validateQueryText( String formattedQuery ) throws OdaException
	{
		validateNonEmptyQueryText( formattedQuery );

		String[] queryFragments = parsePreparedQueryText( formattedQuery );

		validateSingleTableQuery( queryFragments );

		validateQueryColumnNames( queryFragments );
	}

	/**
	 * Validate whether the given query text is empty.
	 * 
	 * @param formattedQuery
	 *            the trimed query text
	 * @throws OdaException
	 *             if the given text is empty
	 */
	private void validateNonEmptyQueryText( String formattedQuery )
			throws OdaException
	{
		if ( formattedQuery == null || formattedQuery.length( ) == 0 )
			throw new OdaException( Messages.getString( "query_COMMAND_IS_EMPTY" ) ); //$NON-NLS-1$
	}

	/**
	 * Validate whether the given query segments contains a single table name.
	 * 
	 * @param parsedQuerySegments
	 * @throws OdaException
	 *             if the given query contains multiple table names
	 */
	private void validateSingleTableQuery( String[] parsedQuerySegments )
			throws OdaException
	{
		if ( getPreparedTableNames( parsedQuerySegments ).split( CommonConstants.DELIMITER_COMMA ).length != 1 )
			throw new OdaException( Messages.getString( "query_DO_NOT_SUPPORT_CROSS_TABLE_QUERY" ) ); //$NON-NLS-1$
	}

	/**
	 * Validate the column names included in given query segments.
	 * 
	 * @param parsedQuerySegments
	 * @throws OdaException
	 *             if the given query contains invalid column name(s)
	 */
	private void validateQueryColumnNames( String[] parsedQuerySegments )
			throws OdaException
	{
		String preparedColumnNames = getPreparedColumnNames( parsedQuerySegments );
		if ( !isWildCard( preparedColumnNames ) )
		{
			validateColumnName( preparedColumnNames.split( CommonConstants.DELIMITER_COMMA ),
					discoverActualColumnMetaData( getPreparedTableNames( parsedQuerySegments ),
							NAME_LITERAL ) );
		}
	}

	/**
	 * Return the String that contains column name(s) selected in a query.
	 * Multiple column names, if any, are separated by comma.
	 * 
	 * @param parsedQueryFragments
	 *            the string array which is generated by the
	 *            parsePreparedQueryText method
	 * @return the comma-separated column names selected in a query
	 */
	private String getPreparedColumnNames( String[] parsedQueryFragments )
	{
		return parsedQueryFragments[0];
	}

	/**
	 * Return the String that contains column label(s) selected in a query.
	 * Multiple column labels, if any, are separated by comma.
	 * 
	 * @param parsedQueryFragments
	 *            the string array which is generated by the
	 *            parsePreparedQueryText method
	 * @return the comma-separated column labels selected in a query
	 */
	private String getPreparedColumnLabels( String[] parsedQueryFragments )
	{
		return parsedQueryFragments[1];
	}

	/**
	 * Return the String which contains the identifiers in the FROM clause.
	 * 
	 * @param parsedQueryFragments
	 *            the string array which is generated by the
	 *            parsePreparedQueryText method
	 * @return a String that contains table name(s) after the FROM keyword
	 */
	private String getPreparedTableNames( String[] parsedQueryFragments )
	{
		return parsedQueryFragments[2];
	}

	/**
	 * Parse the command by separating keywords and other parts of a SQL SELECT
	 * query text.
	 * 
	 * @param formattedQuery
	 *            SQL SELECT query: SELECT COLUMNNAME (AS ALIAS)[,COLUMNNAME2
	 *            (AS ALIAS)] FROM TABLENAME
	 * @return a String array with first element that holds all the
	 *         comma-separated column names, the second element that holds all
	 *         the comma-separated column display labels, and the third element
	 *         for table name(s) in FROM clause
	 * @throws OdaException
	 *             if the given query is not valid.
	 */
	private String[] parsePreparedQueryText( String formattedQuery )
			throws OdaException
	{
		String queryWithoutSELECTKeyword = stripSELECTKeyword( formattedQuery );

		/*
		 * This returned array stores two values: the fragment immediately after
		 * "SELECT" and before "FROM", and the fragment immediate after the
		 * "FROM" keyword.
		 */
		String[] querySelectAndFromFragments = stripFROMKeyword( queryWithoutSELECTKeyword );

		return stripASKeyword( querySelectAndFromFragments );
	}

	/**
	 * Split the column name from alias, stripping the "AS" keyword, from given
	 * query fragments.
	 * 
	 * @param querySelectAndFromFragments
	 * @return a String array with three elements: first element contains column
	 *         names separated by comma, second element contains column
	 *         aliases(labels) separated by comma, third element contains the
	 *         table name(s) in FROM clause
	 */
	private String[] stripASKeyword( String[] querySelectAndFromFragments )
	{
		String[] result = new String[3];
		// store the table name in given last element as the third element
		result[2] = querySelectAndFromFragments[1];

		// split the columns specified in the SELECT clause
		String selectedColumns = querySelectAndFromFragments[0];
		if ( !isWildCard( selectedColumns ) )
		{
			String[] columns = selectedColumns.split( CommonConstants.DELIMITER_COMMA );
			for ( int i = 0; i < columns.length; i++ )
			{
				String[] columnNameAlias = columns[i].split( CommonConstants.DELIMITER_SPACE
						+ CommonConstants.KEYWORD_AS
						+ CommonConstants.DELIMITER_SPACE );
				if ( columnNameAlias != null )
				{
					// append column name to comma-separated column names in
					// result[0]
					result[0] = ( i == 0 ? columnNameAlias[0] : result[0]
							+ CommonConstants.DELIMITER_COMMA
							+ columnNameAlias[0].trim( ) );

					// append column alias, if exists, or null to
					// comma-separated column aliases in result[1]
					if ( columnNameAlias.length == 2 )
						result[1] = ( i == 0 ? columnNameAlias[1] : result[1]
								+ CommonConstants.DELIMITER_COMMA
								+ columnNameAlias[1].trim( ) );
					else
						result[1] = ( i == 0 ? null : result[1]
								+ CommonConstants.DELIMITER_COMMA + null );
				}
			}
		}
		else
		{
			result[0] = CommonConstants.KEYWORD_ASTERISK;
			result[1] = null;
		}

		return result;
	}

	/**
	 * Split the given query fragments before and after the FROM keyword
	 * 
	 * @param queryWithoutSELECTKeyword
	 * @return A String array with two elements: column names (may include alias
	 *         with the AS keyword), and table names after the FROM clause.
	 * @throws OdaException
	 */
	private String[] stripFROMKeyword( String queryWithoutSELECTKeyword )
			throws OdaException
	{
		String[] result = queryWithoutSELECTKeyword.split( CommonConstants.DELIMITER_SPACE
				+ CommonConstants.KEYWORD_FROM
				+ CommonConstants.DELIMITER_SPACE );
		if ( result == null || result.length != 2 )
			throw new OdaException( Messages.getString( "query_COMMAND_NOT_VALID" ) ); //$NON-NLS-1$
		return result;
	}

	/**
	 * @param formattedQuery
	 *            a trimed query text; cannot be null.
	 * @return the given text stripped the SELECT keyword
	 * @throws OdaException
	 */
	private String stripSELECTKeyword( String formattedQuery )
			throws OdaException
	{
		// This array stores two values: "SELECT" keyword and other part of a
		// command
		String[] array = formattedQuery.split( CommonConstants.DELIMITER_SPACE,
				2 );
		if ( array == null
				|| array.length != 2
				|| !array[0].trim( )
						.equalsIgnoreCase( CommonConstants.KEYWORD_SELECT ) )
			throw new OdaException( Messages.getString( "query_COMMAND_NOT_VALID" ) ); //$NON-NLS-1$
		return array[1];
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
	private String findDataFileAbsolutePath( String tableName )
			throws OdaException
	{
		File file = new File( this.homeDirectory
				+ File.separator + tableName.trim( ) );
		if ( !file.exists( ) )
			throw new OdaException( Messages.getString( "query_invalidTableName" ) + tableName ); //$NON-NLS-1$
		return file.getAbsolutePath( );
	}

	/**
	 * Returns a specified array of metadata info
	 * 
	 * @param tableName
	 * @param metaDataType:
	 *            currently has two values: "NAME" and "TYPE"
	 * @return String[] an array that holds the specified metadata
	 * @throws OdaException
	 */
	private String[] discoverActualColumnMetaData( String tableName,
			String metaDataType ) throws OdaException
	{
		try
		{
			String dataFilePath = findDataFileAbsolutePath( tableName );
			this.examCharset( dataFilePath );
			FileInputStream fis = new FileInputStream( dataFilePath );
			InputStreamReader isr = new InputStreamReader( fis, this.charSet );
			CSVBufferedReader br = new CSVBufferedReader( isr );

			if ( !( metaDataType.trim( ).equalsIgnoreCase( NAME_LITERAL ) || metaDataType.trim( )
					.equalsIgnoreCase( TYPE_LITERAL ) ) )
				throw new OdaException( Messages.getString( "query_ARGUMENT_ERROR" ) ); //$NON-NLS-1$

			// if want to discover type information then just skip first line
			if ( metaDataType.trim( ).equalsIgnoreCase( TYPE_LITERAL ) )
				br.readLine( );

			String[] result = getColumnNameArray( br.readLine( ),
					metaDataType.trim( ).equalsIgnoreCase( NAME_LITERAL ) );

			br.close( );

			if ( metaDataType.trim( ).equalsIgnoreCase( NAME_LITERAL ) )
				this.validateUniqueName( result );
			if ( metaDataType.trim( ).equalsIgnoreCase( TYPE_LITERAL ) )
				validateColumnTypeConsistency( result );

			return trimStringArray( result );

		}

		catch ( IOException e )
		{
			throw new OdaException( Messages.getString( "query_IO_EXCEPTION" ) //$NON-NLS-1$
					+ findDataFileAbsolutePath( tableName ) );
		}

	}

	/**
	 * @param result
	 * @return
	 */
	private String[] trimStringArray( String[] array )
	{
		String[] result = new String[array.length];
		for ( int i = 0; i < result.length; i++ )
			result[i] = array[i].trim( );
		return result;
	}

	/**
	 * Check whether a column name given in query has exactly one occurance in
	 * actual table
	 * 
	 * @param cCN
	 *            the array of column names in query
	 * @param aCN
	 *            the array of column names in actual table
	 * @throws OdaException
	 */
	private void validateColumnName( String[] cCN, String[] aCN )
			throws OdaException
	{
		for ( int i = 0; i < cCN.length; i++ )
		{
			if ( this.findOccuranceOfValueInStringArray( cCN[i], aCN ) != 1 )
			{
				throw new OdaException( Messages.getString( "query_COMMAND_NOT_VALID" ) ); //$NON-NLS-1$
			}
		}
	}

	/**
	 * @param cCN
	 * @return
	 */
	private boolean isWildCard( String cCN )
	{
		if ( cCN.equalsIgnoreCase( CommonConstants.KEYWORD_ASTERISK ) )
			return true;
		return false;
	}

	/**
	 * Validate that there are no duplicate column names in a table
	 * 
	 * @param aCN
	 * @throws OdaException
	 */
	private void validateUniqueName( String[] aCN ) throws OdaException
	{
		for ( int i = 0; i < aCN.length; i++ )
		{
			if ( this.findOccuranceOfValueInStringArray( aCN[i], aCN ) > 1 )
			{
				throw new OdaException( Messages.getString( "query_SOURCE_DATA_ERROR" ) ); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Validate whether all column types in source data (line 2) are of valid
	 * type
	 * 
	 * @param aCT
	 * @throws OdaException
	 */

	private void validateColumnTypeConsistency( String[] aCT )
			throws OdaException
	{
		if ( !hasTypeLine )
			return;
		for ( int i = 0; i < aCT.length; i++ )
		{
			if ( !DataTypes.isValidType( aCT[i] ) )
			{
				throw new OdaException( Messages.getString( "query_SOURCE_DATA_ERROR" ) ); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Return the number of occurance of a value in the given array
	 * 
	 * @param value
	 * @param array
	 * @return
	 */
	private int findOccuranceOfValueInStringArray( String value, String[] array )
	{
		int count = 0;
		for ( int i = 0; i < array.length; i++ )
		{
			if ( value.trim( ).equalsIgnoreCase( array[i].trim( ) ) )
				count++;
		}
		return count;
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

	/**
	 * Fetch queried data from source file and return in a vector
	 * 
	 * @return a Vector which contains queried data
	 * @throws OdaException
	 */
	private Vector fetchQueriedDataFromFileToVector( ) throws OdaException
	{
		Vector result = new Vector( );

		try
		{
			String dataFilePath = findDataFileAbsolutePath( currentTableName );
			if ( charSet == null || charSet.trim( ).length( ) == 0 )
				this.examCharset( dataFilePath );
			FileInputStream fis = new FileInputStream( dataFilePath );

			InputStreamReader isr = new InputStreamReader( fis, this.charSet );

			CSVBufferedReader br = new CSVBufferedReader( isr );

			// make a copy of column names.
			String[] columnNames = getColumnNameArray( br.readLine( ), true );

			// skip Type information. The type information is in the second line
			// of file
			if ( hasTypeLine )
				br.readLine( );

			// temporary variable which is used to store the data of a row
			// fetched from a flat file
			String aLine = null;

			int fetchCounter = 0;
			while ( ( aLine = br.readLine( ) ) != null
					&& ( this.maxRows <= 0 ? true : fetchCounter < this.maxRows ) )
			{
				fetchCounter++;
				result.add( fetchQueriedDataFromRow( aLine, columnNames ) );
			}
			br.close( );
			return result;
		}
		catch ( IOException e )
		{
			throw new OdaException( e.getMessage( ) );
		}
	}

	/**
	 * Fetch data from a row.
	 * 
	 * @param aRow
	 *            a row read from table
	 * @param columnNames
	 *            an array that contains all column names of a table
	 * @return an array of data values for each specified column names from a
	 *         row. The "specified column names" are obtained from meta data
	 * @throws OdaException
	 */
	private String[] fetchQueriedDataFromRow( String aRow, String[] columnNames )
			throws OdaException
	{
		String[] sArray = new String[this.getMetaData( ).getColumnCount( )];
		// Ignore all quotes which is used in the input file for the
		// clarity of the data
		Vector vTemp = splitStringWithDoubleQuotes( aRow );

		for ( int i = 0; i < sArray.length; i++ )
		{
			int location = findLocationOfValueInStringArray( getMetaData( ).getColumnName( i + 1 ),
					columnNames );
			if ( location != -1 )
			{
				if ( location >= vTemp.size( ) )
					throw new OdaException( Messages.getString( "query_INVALID_FLAT_FILE" ) );
				sArray[i] = vTemp.elementAt( location ).toString( );
			}
		}
		return sArray;
	}

	/**
	 * 
	 * @param aRow
	 * @return
	 * @throws OdaException
	 */
	private Vector splitStringWithDoubleQuotes( String aRow )
			throws OdaException
	{
		Vector result = new Vector( );
		char[] chars = aRow.toCharArray( );

		boolean startDoubleQuote = false;
		boolean finishAnElement = false;
		String currentString = "";

		for ( int i = 0; i < chars.length; i++ )
		{
			if ( i < chars.length - 1 )
			{
				//"
				if ( chars[i] == '"' )
				{
					//""
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
							//"""
							if ( i < chars.length - 1 && chars[i + 2] == '"' )
							{
								currentString += '"';
								i += 2;
								startDoubleQuote = !startDoubleQuote;
								continue;
							}
							else
							{
								i +=1;
								finishAnElement = true;
								continue;
							}
						}
					}
					//"*
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
				else if ( chars[i] == ',' && !startDoubleQuote )
				{
					result.add( currentString );
					currentString = "";
					finishAnElement = false;
				}
				else
				{
					if ( finishAnElement == true && chars[i] != ' ' )
						throw new OdaException( "Invalid" );
					currentString += chars[i];
				}
			}
			else
			{
				if ( chars[i] == '"' )
				{
					if ( !startDoubleQuote )
						throw new OdaException( "Invalid" );
				}
				else if ( chars[i] == ',' )
				{
					result.add( currentString );
					result.add( "" );
				}
				else
				{
					currentString += chars[i];
				}

				result.add( currentString );
				finishAnElement = false;

			}
		}
		return result;
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
		String[][] rowSet = new String[v.size( )][this.resultSetMetaData.getColumnCount( )];
		for ( int i = 0; i < v.size( ); i++ )
		{
			String[] temp = (String[]) v.elementAt( i );
			for ( int j = 0; j < temp.length; j++ )
			{
				rowSet[i][j] = temp[j].trim( );
			}
		}
		return rowSet;
	}

	/**
	 * Prepare the meta data which will be used in execution of a query text. It
	 * sets the value of two member variables: resultSetMetaData and
	 * currentTableName
	 * 
	 * @param queryText
	 * @throws OdaException
	 */
	private void prepareMetaData( String queryText ) throws OdaException
	{
		String[] queryFragments = parsePreparedQueryText( queryText );
		// the name of table against which the query will be executed
		String tableName = getPreparedTableNames( queryFragments );

		// the array that contains the actual column names read from data file
		String[] allColumnNames = discoverActualColumnMetaData( tableName,
				NAME_LITERAL );

		// the array that contains the actual data type names read from data
		// file
		String[] allColumnTypes = hasTypeLine
				? discoverActualColumnMetaData( tableName, TYPE_LITERAL )
				: null;

		// the array that contains the column names read from command
		String[] queryColumnNames = null;
		String[] queryColumnTypes = null;
		// dealing with "*"
		if ( isWildCard( getPreparedColumnNames( queryFragments ) ) )
		{
			queryColumnNames = allColumnNames;
			queryColumnTypes = allColumnTypes;
		}
		else
		{
			queryColumnNames = getPreparedColumnNames( queryFragments ).split( CommonConstants.DELIMITER_COMMA );
			queryColumnTypes = getQueryColumnTypes( allColumnNames,
					allColumnTypes,
					queryColumnNames );
		}

		this.resultSetMetaData = new ResultSetMetaData( queryColumnNames,
				queryColumnTypes,
				getColumnLabels( queryFragments ) );
		this.currentTableName = tableName;
	}

	/**
	 * Returns the array that contains the types of column names read from a
	 * query text.
	 * 
	 * @param allColumnNames
	 *            The array contains all column names read from file
	 * @param allColumnTypes
	 *            The array contains all column types read from file
	 * @param queryColumnNames
	 *            The array contains those column names specified in a query
	 * @return
	 */
	private String[] getQueryColumnTypes( String[] allColumnNames,
			String[] allColumnTypes, String[] queryColumnNames )
	{
		if ( !hasTypeLine )
			return null;

		// the array that contains the types of column names read from a query
		String[] queryColumnTypes = new String[queryColumnNames.length];

		for ( int i = 0; i < queryColumnNames.length; i++ )
		{
			for ( int j = 0; j < allColumnNames.length; j++ )
			{
				if ( queryColumnNames[i].trim( )
						.equalsIgnoreCase( allColumnNames[j] ) )
				{
					queryColumnTypes[i] = allColumnTypes[j];
				}
			}

		}
		return queryColumnTypes;
	}

	/**
	 * Returns an array that contains column labels.
	 * 
	 * @param queryFragments
	 * @return a String array that contains column labels
	 */
	private String[] getColumnLabels( String[] queryFragments )
	{
		String queryColumnLabels = getPreparedColumnLabels( queryFragments );
		return queryColumnLabels != null
				? queryColumnLabels.split( CommonConstants.DELIMITER_COMMA )
				: null;
	}

	/**
	 * Format the given query text. Eliminates redundant spaces and convert all
	 * keywords to uppercase.
	 * 
	 * @param queryText
	 * @return
	 */
	private String formatQueryText( String queryText )
	{
		String result = ""; //$NON-NLS-1$
		String[] temp = queryText.trim( )
				.split( CommonConstants.DELIMITER_SPACE );
		for ( int i = 0; i < temp.length; i++ )
		{
			if ( temp[i].equalsIgnoreCase( CommonConstants.KEYWORD_AS ) )
				temp[i] = temp[i].toUpperCase( );
			if ( temp[i].equalsIgnoreCase( CommonConstants.KEYWORD_FROM ) )
				temp[i] = temp[i].toUpperCase( );
			if ( temp[i].equalsIgnoreCase( CommonConstants.KEYWORD_SELECT ) )
				temp[i] = temp[i].toUpperCase( );
			result = result + temp[i] + CommonConstants.DELIMITER_SPACE;
		}
		return result.trim( );
	}

	/**
	 * If Charset is not set (that is, of null or empty value), test whether the
	 * file is encoded with "UTF-16LE" or "UTF-16BE". If neither, then treat
	 * file as using default "UTF-8"
	 */
	private void examCharset( String filepath ) throws OdaException,
			IOException
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

	private String[] getColumnNameArray( String line, boolean isFirstLine )
			throws OdaException
	{
		if ( line == null )
			throw new OdaException( Messages.getString( "common_CANNOT_FIND_COLUMN" ) ); //$NON-NLS-1$
		String[] result = null;
		if ( isFirstLine )
		{
			try
			{
				if ( CommonConstants.CONN_DEFAULT_CHARSET.equals( charSet ) )
				{
					byte[] firstLineByteCodes;
					firstLineByteCodes = line.getBytes( charSet );
					if ( isUTF8BOMFormat( firstLineByteCodes ) )
						line = line.substring( 1 );
				}
				result = line.split( CommonConstants.DELIMITER_COMMA );
			}
			catch ( UnsupportedEncodingException e )
			{
				throw new OdaException( e );
			}
		}
		else
			result = line.split( CommonConstants.DELIMITER_COMMA );
		return result;
	}

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
	 * CVSBufferReader will buffer the input from the specified file. Read a
	 * line of text. A line is considered to be terminated by any one of a line
	 * feed ('\n'), or a carriage return followed immediately by a linefeed.
	 */
	private static class CSVBufferedReader
	{

		//
		private Reader reader;
		private char[] charBuffer;
		private int startingPosition;
		private int eofInPosition;
		private static int CHARBUFFSIZE = 8192;

		/**
		 * Constructor.
		 * 
		 * @param in
		 */
		CSVBufferedReader( Reader in )
		{
			reader = new BufferedReader( in );

			startingPosition = -1;

			eofInPosition = -2;
		}

		/**
		 * Read a line from input stream. The line-seperator is '\n'
		 */
		public String readLine( ) throws IOException
		{
			if ( isLastCharBuff( ) && needRefillCharBuff( ) )
				return null;

			if ( needRefillCharBuff( ) )
			{
				charBuffer = newACharBuff( );
				int close = reader.read( charBuffer );

				if ( close == -1 )
					return null;

				if ( close != CHARBUFFSIZE )
					this.eofInPosition = close;

				this.startingPosition = 0;
			}

			String candidate = "";
			int stopIn = CHARBUFFSIZE;

			if ( isLastCharBuff( ) )
			{
				stopIn = this.eofInPosition;
			}

			for ( int i = this.startingPosition; i < stopIn; i++ )
			{
				if ( this.charBuffer[i] == '\n' )
				{
					return readALine( candidate, stopIn, i );
				}
			}

			if ( isLastCharBuff( ) )
			{
				return readLastLine( candidate );
			}

			return readExtraContentOfALine( candidate );
		}

		/**
		 * If a line has not been finished after the current char buffer end is reach,
		 * then read the infinish part of that line from input stream.
		 * 
		 * @param candidate
		 * @return
		 * @throws IOException
		 */
		private String readExtraContentOfALine( String candidate )
				throws IOException
		{
			candidate = candidate
					+ String.copyValueOf( charBuffer,
							this.startingPosition,
							CHARBUFFSIZE - this.startingPosition );

			resetCharBufferStartPosition( );

			String nextLine = readLine( );

			return candidate + ( nextLine == null ? "" : nextLine );
		}

		/**
		 * Read a line from char buffer.
		 * @param candidate
		 * @param stopIn
		 * @param i
		 * @return
		 */
		private String readALine( String candidate, int stopIn, int i )
		{
			candidate = candidate
					+ String.copyValueOf( charBuffer, this.startingPosition, i
							- this.startingPosition );
			this.startingPosition = ( i == stopIn - 1 ? -1 : i + 1 );
			return candidate;
		}

		/**
		 * Read the last line of the input stream.
		 * 
		 * @param candidate
		 * @return
		 */
		private String readLastLine( String candidate )
		{
			candidate = candidate
					+ String.copyValueOf( charBuffer,
							this.startingPosition,
							this.eofInPosition - this.startingPosition );
			resetCharBufferStartPosition( );
			return candidate;
		}

		/**
		 * Reset the char buffer start position to -1.
		 *
		 */
		private void resetCharBufferStartPosition( )
		{
			this.startingPosition = -1;
		}

		/**
		 * Return whether the current char buffer has been finished reading.
		 * 
		 * @return
		 */
		private boolean needRefillCharBuff( )
		{
			return this.startingPosition == -1;
		}

		/**
		 * Return whether there are more char buffer can be read from stream.
		 *  
		 * @return
		 */
		private boolean isLastCharBuff( )
		{
			return this.eofInPosition != -2;
		}

		/**
		 * Close the reader. This method should be call every time the 
		 * reader is finish reading.
		 * 
		 * @throws IOException
		 */
		public void close( ) throws IOException
		{
			this.reader.close( );
		}

		/**
		 * New a char buffer with default size.
		 * @return
		 */
		private char[] newACharBuff( )
		{
			return new char[CHARBUFFSIZE];
		}
	}
}
