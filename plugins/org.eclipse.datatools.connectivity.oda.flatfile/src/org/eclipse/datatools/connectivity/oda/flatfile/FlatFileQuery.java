/*
 *******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Actuate Corporation - initial API and implementation
 ******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;
import org.eclipse.datatools.connectivity.oda.flatfile.util.FlatFileDataReader;
import org.eclipse.datatools.connectivity.oda.flatfile.util.querytextutil.QueryTextUtil;

/**
 * Flat file data provider's implementation of the ODA IQuery interface. It
 * supports single result set and no input parameters.
 */
public class FlatFileQuery implements IQuery
{

	public static final int DEFAULT_MAX_ROWS = 1000;
	private static final String NAME_LITERAL = "NAME"; //$NON-NLS-1$
	private static final String TYPE_LITERAL = "TYPE"; //$NON-NLS-1$

	// whether the CSV file has the column names
	private boolean hasColumnNames;

	// Whether to use 2nd line as Type line
	private boolean hasTypeLine ;

	//the properties of the connection
	private Properties connProperties;

	// The table that the query operates on
	private String currentTableName = null;

	// The max number of rows that can be read
	private int maxRows = 0;

	// The Connection instance associated with the Query.
	private IConnection connection = null;

	// The meta data of the query's result set.
	// It is available only after a query is prepared.
	private IResultSetMetaData resultSetMetaData = null;
	
	private ResultSetMetaDataHelper resultSetMetaDataHelper = null;

	/**
	 * Constructor
	 * 
	 * @param connProperties
	 * @param host
	 * @throws OdaException 
	 */
	public FlatFileQuery(Properties connProperties, IConnection host ) throws OdaException
	{
		if ( connProperties == null
				|| connProperties.getProperty( CommonConstants.CONN_HOME_DIR_PROP ) == null
				|| host == null )
			throw new OdaException( Messages.getString( "common_ARGUMENT_CANNOT_BE_NULL" ) ); //$NON-NLS-1$
		this.connProperties = connProperties;
		this.connection = host;
		extractsHasColumnNamesInfo( );
		extractsHasColumnTypeLineInfo( );
	}
	
	/**
	 * 
	 *
	 */
	private void extractsHasColumnNamesInfo( )
	{
		this.hasColumnNames = connProperties.getProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP )
				.equalsIgnoreCase( CommonConstants.INC_COLUMN_NAME_NO ) 
				? false
				: true;
	}
	
	/**
	 * 
	 *
	 */
	private void extractsHasColumnTypeLineInfo( )
	{
		this.hasTypeLine = connProperties.getProperty( CommonConstants.CONN_INCLTYPELINE_PROP )
				.equalsIgnoreCase( CommonConstants.INC_TYPE_LINE_NO ) 
				? false
				: true;
	}

	
	/**
	 * 
	 * @param homeDir
	 * @param host
	 * @param charSet
	 * @param delimiter
	 * @param inclColumnNames
	 * @param inclTypeLine
	 * @throws OdaException
	 */
/*	public FlatFileQuery( String homeDir, IConnection host, String charSet,
			String delimiter, boolean inclColumnNames, boolean inclTypeLine )
			throws OdaException
	{
		if ( homeDir == null || host == null )
			throw new OdaException( Messages.getString( "common_ARGUMENT_CANNOT_BE_NULL" ) ); //$NON-NLS-1$
		this.homeDirectory = homeDir;
		this.connection = host;
		this.charSet = charSet;
		this.delimiter = delimiter.charAt( 0 );
		this.hasColumnNames = inclColumnNames;
		this.hasTypeLine = inclTypeLine;
	}*/

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#prepare(java.lang.String)
	 */
	public void prepare( String queryText ) throws OdaException
	{
		if ( queryText != null )
		{
			String query = QueryTextUtil.getQuery( queryText );
			String colInfo = QueryTextUtil.getColumnsInfo( queryText );
			validateOpenConnection( );
			String formattedQuery = formatQueryText( query );
			validateQueryText( formattedQuery );
			prepareMetaData( formattedQuery, colInfo );
		}
		else
			throw new OdaException( Messages.getString( "common_NULL_QUERY_TEXT" ) );
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
		return new ResultSet( new FlatFileDataReader( this.connProperties,
				this.currentTableName,
				this.maxRows,
				this.resultSetMetaData,
				this.resultSetMetaDataHelper ),
				this.resultSetMetaData );
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

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(java.lang.String, boolean)
     */
    public void setBoolean( String parameterName, boolean value )
            throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(int, boolean)
     */
    public void setBoolean( int parameterId, boolean value )
            throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(java.lang.String)
     */
    public void setNull( String parameterName ) throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(int)
     */
    public void setNull( int parameterId ) throws OdaException
    {
        throw new UnsupportedOperationException();
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
			throw new OdaException( Messages.getString( "common_CONNECTION_IS_NOT_OPEN" ) ); //$NON-NLS-1$
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
		if ( getPreparedTableNames( parsedQuerySegments ).split( CommonConstants.DELIMITER_COMMA_VALUE ).length != 1 )
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
		String tableName = getPreparedTableNames( parsedQuerySegments );
		if ( !isWildCard( preparedColumnNames ) )
		{
			String[] originalColumnNames = null;
			// if the existance of data column name are specified in the
			// property
			if ( this.hasColumnNames )
			{
				originalColumnNames = discoverActualColumnMetaData( getPreparedTableNames( parsedQuerySegments ),
						NAME_LITERAL );
			}
			else
			{
				originalColumnNames = createTempColumnNames( ( new FlatFileDataReader( this.connProperties,
						tableName,
						0,
						null,
						null ) ).getColumnCount( ) );
			}
			
			validateColumnName( FlatFileDataReader.getStringArrayFromVector( stripFormatInfoFromQueryColumnNames( getQueryColumnNamesVector( preparedColumnNames ) ) ),
					originalColumnNames );
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
			String[] columns = FlatFileDataReader.getStringArrayFromVector( getQueryColumnNamesVector( selectedColumns ) );

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
							+ CommonConstants.DELIMITER_COMMA_VALUE
							+ columnNameAlias[0].trim( ) );

					// append column alias, if exists, or null to
					// comma-separated column aliases in result[1]
					if ( columnNameAlias.length == 2 )
						result[1] = ( i == 0 ? columnNameAlias[1] : result[1]
								+ CommonConstants.DELIMITER_COMMA_VALUE
								+ columnNameAlias[1].trim( ) );
					else
						result[1] = ( i == 0 ? null : result[1]
								+ CommonConstants.DELIMITER_COMMA_VALUE + null );
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
	 * 
	 * @param queryColumnNames
	 * @return
	 */
	private Vector getQueryColumnNamesVector( String queryColumnNames )
	{
		Vector result = new Vector( );
		char[] chars = queryColumnNames.toCharArray( );
		List indiceList = new ArrayList( );
		boolean inQuote = false;
		boolean isEscaped = false;
		int beginIndex = 0;
		int endIndex = 0;

		for ( int i = 0; i < chars.length; i++ )
		{
			if ( chars[i] == '"' )
			{
				if ( !isEscaped )
					inQuote = !inQuote;
				else
					isEscaped = !isEscaped;
			}
			else if ( chars[i] == '\\' )
			{
				isEscaped = !isEscaped;
			}
			else if ( chars[i] == ',' )
			{
				if ( inQuote )
					continue;
				else
					indiceList.add( new Integer( i ) );
			}
		}

		if ( indiceList.size( ) > 0 )
		{
			for ( int j = 0; j < indiceList.size( ); j++ )
			{

				endIndex = ( (Integer) indiceList.get( j ) ).intValue( );

				result.add( queryColumnNames.substring( beginIndex,
						endIndex ).trim( ) );
				beginIndex = endIndex + 1;

				if ( j == indiceList.size( ) - 1 )
				{
					result.add( queryColumnNames.substring( beginIndex,
							queryColumnNames.length( ) ).trim( ) );
				}
			}
		}
		else
			result.add( queryColumnNames );

		return result;
	}
	
	/**
	 * 
	 * @param queryColumnNames
	 * @return
	 */
	private Vector stripFormatInfoFromQueryColumnNames( Vector queryColumnNames)
	{
		Vector columnNames = new Vector( );
		
		boolean isEscaped = false;
		
		for(int i = 0; i< queryColumnNames.size( ); i++)
		{
			StringBuffer sb = new StringBuffer();
			char[] chars = ((String)queryColumnNames.get( i )).toCharArray( );
			
			if(chars[0]!=CommonConstants.DELIMITER_DOUBLEQUOTE)
			{
				columnNames.add( queryColumnNames.get( i ) );
				continue;
			}
			
			for(int j = 0; j <chars.length; j++)
			{
				if(chars[j]==CommonConstants.DELIMITER_DOUBLEQUOTE)
				{
					if (isEscaped)
					{
						sb.append( chars[j] );
						isEscaped = !isEscaped;
					}
				}
				else if( chars[j] == '\\')
				{
					if(isEscaped)
						sb.append( chars[j] );
						
					isEscaped = !isEscaped;
				}
				else
					sb.append( chars[j] );
			}
			
			columnNames.add( sb.toString( ) );
		}
		
		return columnNames;
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
	 * 
	 * @param columnCount
	 * @return
	 */
	private String[] createTempColumnNames( int columnCount )
	{
		String[] tempColumnNames = new String[columnCount];

		for ( int i = 0; i < columnCount; i++ )
		{
			tempColumnNames[i] = "COLUMN_" + ( i + 1 );
		}

		return tempColumnNames;
	}

	/**
	 * 
	 * @param columnCount
	 * @return
	 */
	private String[] createTempColumnTypes( int columnCount )
	{
		String[] tempColumnTypes = new String[columnCount];

		for ( int i = 0; i < columnCount; i++ )
		{
			tempColumnTypes[i] = "STRING";
		}

		return tempColumnTypes;
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
		FlatFileDataReader ffdsr = new FlatFileDataReader( this.connProperties,
				tableName,
				0,
				null,
				null );
		try
		{
			if ( !( metaDataType.trim( ).equalsIgnoreCase( NAME_LITERAL ) || metaDataType.trim( )
					.equalsIgnoreCase( TYPE_LITERAL ) ) )
				throw new OdaException( Messages.getString( "query_ARGUMENT_ERROR" ) ); //$NON-NLS-1$

			// if want to discover type information then just skip all the empty
			// lines and the first
			// line
			if ( metaDataType.trim( ).equalsIgnoreCase( TYPE_LITERAL ) )
			{
				while ( FlatFileDataReader.isEmptyRow( ffdsr.readLine( ) ) )
					continue;
			}
			// Skip all the empty lines until reach the first line
			String columnNameLine;
			while ( FlatFileDataReader.isEmptyRow( columnNameLine = ffdsr.readLine( ) ) )
				continue;

			String[] result = ffdsr.getColumnNameArray( columnNameLine,
					metaDataType.trim( ).equalsIgnoreCase( NAME_LITERAL ) );

			ffdsr.clearBufferedReader( );

			if ( metaDataType.trim( ).equalsIgnoreCase( NAME_LITERAL ) )
				this.validateUniqueName( result );
			if ( metaDataType.trim( ).equalsIgnoreCase( TYPE_LITERAL ) )
				validateColumnTypeConsistency( result );

			return trimStringArray( result );

		}
		catch ( IOException e )
		{
			throw new OdaException( Messages.getString( "query_IO_EXCEPTION" ) //$NON-NLS-1$
					+ ffdsr.findDataFileAbsolutePath( ) );
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
		if ( !this.hasTypeLine )
			return;
		for ( int i = 0; i < aCT.length; i++ )
		{
			if ( !DataTypes.isValidType( aCT[i] ) )
			{
				throw new OdaException( Messages.getString( "dataTypes_TYPE_NAME_INVALID" ) + aCT[i] ); //$NON-NLS-1$
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
	 * Prepare the meta data which will be used in execution of a query text. It
	 * sets the value of two member variables: resultSetMetaData and
	 * currentTableName
	 * 
	 * @param queryText
	 * @throws OdaException
	 */
	private void prepareMetaData( String query, String savedSelectedColInfo )
			throws OdaException
	{
		String[] queryFragments = parsePreparedQueryText( query );
		
		// the name of table against which the query will be executed
		String tableName = getPreparedTableNames( queryFragments );

		FlatFileDataReader ffdsr = new FlatFileDataReader( this.connProperties , tableName, 0, null, null );
		
		// the array that contains the actual column names read from data file
		String[] allColumnNames = this.hasColumnNames
				? discoverActualColumnMetaData( tableName, NAME_LITERAL )
				: createTempColumnNames( ffdsr.getColumnCount( ) );

		// the array that contains the actual data type names read from data
		// file
		String[] allColumnTypes;
		
		allColumnTypes = this.hasTypeLine
				? discoverActualColumnMetaData( tableName, TYPE_LITERAL )
				: createTempColumnTypes( ffdsr.getColumnCount( ) );
				
		if ( allColumnNames.length != allColumnTypes.length )
			throw new OdaException( Messages.getString( "invalid_flatfile_format" ) );

		// the array that contains the column names read from command
		String[] queryColumnNames = null;
		String[] queryColumnTypes = null;
		String[] queryColumnLables = null;
		// dealing with "*"
		if ( isWildCard( getPreparedColumnNames( queryFragments ) ) )
		{
			queryColumnNames = allColumnNames;
			queryColumnTypes = allColumnTypes;
			queryColumnLables = allColumnNames;
			this.resultSetMetaDataHelper = new ResultSetMetaDataHelper(queryColumnNames,
					queryColumnTypes,
					queryColumnLables );
			this.resultSetMetaData = new ResultSetMetaData( this.resultSetMetaDataHelper );
		}
		else
		{
			if ( savedSelectedColInfo == null
					|| savedSelectedColInfo.length( ) == 0 )
			{
				queryColumnNames = FlatFileDataReader.getStringArrayFromVector( stripFormatInfoFromQueryColumnNames( getQueryColumnNamesVector( ( getPreparedColumnNames( queryFragments ) ) ) ) );
				
				queryColumnTypes = this.hasTypeLine
						? getQueryColumnTypes( allColumnNames,
								allColumnTypes,
								queryColumnNames )
						: createTempColumnTypes( queryColumnNames.length );
				queryColumnLables = this.hasColumnNames
						? getColumnLabels( queryFragments ) : queryColumnNames;
				if ( queryColumnLables == null )
					queryColumnLables = queryColumnNames;
				this.resultSetMetaDataHelper = new ResultSetMetaDataHelper( queryColumnNames,
						queryColumnTypes,
						queryColumnLables );
				this.resultSetMetaData = new ResultSetMetaData( this.resultSetMetaDataHelper );
			}
			else
			{
				this.resultSetMetaDataHelper = new ResultSetMetaDataHelper( savedSelectedColInfo );
				this.resultSetMetaData = new ResultSetMetaData( this.resultSetMetaDataHelper );

			}

		}

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
		if ( !this.hasTypeLine )
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
				? queryColumnLabels.split( CommonConstants.DELIMITER_COMMA_VALUE )
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
	 * CVSBufferReader will buffer the input from the specified file. Read a
	 * line of text. A line is considered to be terminated by any one of a line
	 * feed ('\n'), or a carriage return followed immediately by a linefeed.
	 */
	public static class FlatFileBufferedReader
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
		public FlatFileBufferedReader( Reader in )
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
		 * If a line has not been finished after the current char buffer end is
		 * reach, then read the infinish part of that line from input stream.
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
		 * 
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
		 * Close the reader. This method should be call every time the reader is
		 * finish reading.
		 * 
		 * @throws IOException
		 */
		public void close( ) throws IOException
		{
			this.reader.close( );
		}

		/**
		 * New a char buffer with default size.
		 * 
		 * @return
		 */
		private char[] newACharBuff( )
		{
			return new char[CHARBUFFSIZE];
		}
	}
}
