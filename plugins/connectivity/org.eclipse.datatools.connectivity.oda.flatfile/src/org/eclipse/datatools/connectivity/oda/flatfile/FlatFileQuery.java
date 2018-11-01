/*
 *******************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Actuate Corporation - initial API and implementation
 ******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;

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

	// The table that the query operates on
	private String currentTableName = null;

	// The max number of rows that can be read
	private int maxRows = 0;

	// The Connection instance associated with the Query.
	private Connection connection = null;

	// The meta data of the query's result set.
	// It is available only after a query is prepared.
	private ResultSetMetaData resultSetMetaData = null;
	
	private ResultSetMetaDataHelper resultSetMetaDataHelper = null;

	/**
	 * Constructor
	 * 
	 * @param connProperties
	 * @param host
	 * @throws OdaException 
	 */
	public FlatFileQuery(IConnection host ) throws OdaException
	{
		if ( host == null
				|| ( ((Connection) host).getHomeFolder( ) == null && ((Connection) host).getFileURI( ) == null ) )
			throw new OdaException( Messages.getString( "common_ARGUMENT_CANNOT_BE_NULL" ) ); //$NON-NLS-1$
		this.connection = (Connection) host;
		extractsHasColumnNamesInfo( );
		extractsHasColumnTypeLineInfo( );
	}
	
	/**
	 * 
	 *
	 */
	private void extractsHasColumnNamesInfo( )
	{
		this.hasColumnNames = connection.hasColumnNames( );
	}
	
	/**
	 * 
	 *
	 */
	private void extractsHasColumnTypeLineInfo( )
	{
		this.hasTypeLine = connection.hasTypeLine( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#prepare(java.lang.String)
	 */
	public void prepare( String queryText ) throws OdaException
	{
		if ( queryText != null )
		{
			QueryTextUtil qtu = new QueryTextUtil( queryText );
			String query = qtu.getQuery( );
			String colInfo = qtu.getColumnsInfo( );
			validateOpenConnection( );
			String formattedQuery = formatQueryText( query );
			prepareMetaData( formattedQuery, colInfo );
		}
		else
			throw new OdaException( Messages.getString( "common_NULL_QUERY_TEXT" ) ); //$NON-NLS-1$
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
		return new ResultSet( new FlatFileDataReader( this.connection,
				this.currentTableName,
				this.maxRows,
				this.resultSetMetaData,
				this.resultSetMetaDataHelper ),
				this.resultSetMetaData );
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#cancel()
     */
    public void cancel() throws OdaException, UnsupportedOperationException
    {
        throw new UnsupportedOperationException( );
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
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setObject(int, java.lang.Object)
     */
    public void setObject( int parameterId, Object value ) throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setObject(java.lang.String, java.lang.Object)
     */
    public void setObject( String parameterName, Object value )
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

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setSpecification(org.eclipse.datatools.connectivity.oda.spec.QuerySpecification)
     */
    public void setSpecification( QuerySpecification querySpec )
            throws OdaException, UnsupportedOperationException
    {
        throw new UnsupportedOperationException( );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getSpecification()
     */
    public QuerySpecification getSpecification()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getEffectiveQueryText()
     */
    public String getEffectiveQueryText()
    {
        throw new UnsupportedOperationException();
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

		return QueryTextUtil.getQueryMetaData( formattedQuery );
	}

	/**
	 * 
	 * @param queryColumnNames
	 * @return
	 */
	private Vector<String> getQueryColumnNamesVector( String queryColumnNames )
	{
		Vector<String> result = new Vector<String>( );
		char[] chars = queryColumnNames.toCharArray( );
		List<Integer> indiceList = new ArrayList<Integer>( );
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
	private Vector<String> stripFormatInfoFromQueryColumnNames( Vector<String> queryColumnNames )
	{
		Vector<String> columnNames = new Vector<String>( );
		
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
	 * 
	 * @param columnCount
	 * @return
	 */
	private String[] createTempColumnTypes( int columnCount )
	{
		String[] tempColumnTypes = new String[columnCount];

		for ( int i = 0; i < columnCount; i++ )
		{
			tempColumnTypes[i] = "STRING"; //$NON-NLS-1$
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
		FlatFileDataReader ffdsr = new FlatFileDataReader( this.connection,
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
			List<String> columnNameLine;
			while ( FlatFileDataReader.isEmptyRow( columnNameLine = ffdsr.readLine( ) ) )
				continue;

			String[] result = ffdsr.getColumnNameArray( columnNameLine );


			if ( metaDataType.trim( ).equalsIgnoreCase( NAME_LITERAL ) )
				this.validateUniqueName( result );
			if ( metaDataType.trim( ).equalsIgnoreCase( TYPE_LITERAL ) )
				validateColumnTypeConsistency( result );

			return trimStringArray( result );

		}
		finally 
		{
			ffdsr.clearBufferedReader( );
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
		validateNonEmptyQueryText( query );
		
		String[] queryFragments = parsePreparedQueryText( query );
		
		validateSingleTableQuery( queryFragments );
		
		// the name of table against which the query will be executed
		String tableName = getPreparedTableNames( queryFragments );

		FlatFileDataReader ffdsr = new FlatFileDataReader( this.connection , tableName, 0, null, null );
		
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
			throw new OdaException( Messages.getString( "invalid_flatfile_format" ) ); //$NON-NLS-1$

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
			queryColumnNames = FlatFileDataReader.getStringArrayFromList( stripFormatInfoFromQueryColumnNames( getQueryColumnNamesVector( ( getPreparedColumnNames( queryFragments ) ) ) ) );
			validateColumnName( queryColumnNames, allColumnNames);
			if ( savedSelectedColInfo == null
					|| savedSelectedColInfo.length( ) == 0 || hasTypeLine )
			{				
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
					break;
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
		StringBuffer result = new StringBuffer(); 
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
			result.append( temp[i] ).append( CommonConstants.DELIMITER_SPACE );
		}
		return result.toString( ).trim( );
	}

	/**
	 * CVSBufferReader will buffer the input from the specified file. Read a
	 * line of text. A line is considered to be terminated by any one of a line
	 * feed ('\n').
	 */
	public static class FlatFileBufferedReader
	{
		private Reader reader;
		private char[] charBuffer; //the buffer saved chars read from file
		private static int CHARBUFFSIZE = 8192;
		
		private char separator;
		private int endIndex; //the end of the buffer
		private int currentIndex; //current index in the buffer during parsing a line
		
		public FlatFileBufferedReader( InputStream in, String encoding, char seperator ) throws IOException
		{
			initReader( in, encoding );
			this.separator = seperator;
			endIndex = -1;
			currentIndex = -1;
			charBuffer = new char[CHARBUFFSIZE];
		}

		private void initReader( InputStream in, String encoding ) throws IOException
		{
			if ( "UTF-8".equals( encoding ) ) //$NON-NLS-1$
			{
				//Currently, only BOM for "UTF-8" is supported
				PushbackInputStream internalInputStream = new PushbackInputStream( in, 3 );
				byte bom[] = new byte[3];
				int len = internalInputStream.read( bom );
				if ( len == 3 
						&& bom[0] == (byte)0xEF
						&& bom[1] == (byte)0xBB
						&& bom[2] == (byte)0xBF )
				{
					//is BOM, first 3 bytes just be skipped
				}
				else
				{
					//not BOM, back to the start point of <code>in</code
					if ( len > 0 )
					{
						internalInputStream.unread( bom, 0, len );
					}
				}
				reader = new BufferedReader( new InputStreamReader( internalInputStream, encoding) );
			} 
			else
			{
				reader = new BufferedReader( new InputStreamReader( in, encoding ) );
			}
		}
		
		/**
		 * return a line
		 * the csv format reference: http://www.creativyst.com/Doc/Articles/CSV/CSV01.htm
		 * @return
		 * @throws OdaException
		 * @throws IOException
		 */
		public List<String> readLine( ) throws OdaException
		{
			int newLineStartIndex = currentIndex + 1;
			StringBuffer column = new StringBuffer( ); //current parsing column in the line
			boolean doubleQuoted = false; //is current parsing column double quoted
			if ( !next( ) ) 
			{
				//do not exist more char for read
				return null;
			}
			List<String> result = new ArrayList<String>( );
			do 
			{
				char curChar = getChar( ); 
					
				if ( !doubleQuoted )
				{
					if ( curChar == separator )
					{
						result.add( getColumnValue( column ) ); // this column is parsed
						column.setLength( 0 ); //prepared for next column
					}
					else if ( curChar == '"' )
					{
						if ( column.toString( ).trim( ).length( ) > 0 )
						{
							//other shown chars exist before the first double quotes
							throw new OdaException( Messages.getString( "invalid_flatfile_format" ) ); //$NON-NLS-1$
						}
						else
						{
							doubleQuoted = true;
							column.setLength( 0 ); //start of this column
							column.append( '"' );
						}
					}
					else if ( curChar == '\n' )
					{
						//Finish this line
						if ( currentIndex != newLineStartIndex )
						{
							result.add( getColumnValue( column ) );
						}
						else
						{
							//an empty line containing just a "\n"
						}
						return result;
					}
					else //other char
					{
						column.append( curChar );
					}
				}
				else
				{
					moveToEndQuotation ( column );
					moveToEndOfColumn( );
					doubleQuoted = false;
				}
			} while ( next( ) );
			
			result.add( getColumnValue( column ) );
			return result;
		}
		
		private void moveToEndOfColumn( ) throws OdaException
		{
			if ( next( ) )
			{
				char curChar = getChar( );
				if ( curChar == separator || curChar == '\n' )
				{
					back( );
					return;
				}
				else if ( isAnEmptyChar( curChar ) )
				{
					moveToEndOfColumn( );
				}
				else
				{
					//Other shown chars exists after the double quote which is the flag of end of column
					throw new OdaException( Messages.getString( "invalid_flatfile_format" ) ); //$NON-NLS-1$
				}
			}
		}
		
		private void moveToEndQuotation( StringBuffer column ) throws OdaException
		{
			do 
			{
				char curChar = getChar( );
				if ( curChar == '"' )
				{
					if ( next( ) )
					{
						curChar = getChar( );
						if ( curChar == '"' )
						{
							//transfer '""' to '"'
							column.append( '"' );
						} 
						else
						{
							back( );
							column.append('"');
							return;
						}
					}
					else
					{
						column.append('"');
						return;
					}
						
				}
				else
				{
					column.append( curChar );
				}
			} while (next( )); 
			//no end quotation is detected
			throw new OdaException( Messages.getString( "invalid_flatfile_format" ) ); //$NON-NLS-1$
		}
		
		private boolean next( ) throws OdaException
		{
			if ( currentIndex < endIndex )
			{
				++currentIndex;
				return true;
			}
			else
			{
				int len;
				try
				{
					len = reader.read( charBuffer );
					if ( len <= 0 )
					{
						return false; //eof
					}
					else
					{
						currentIndex = 0;
						endIndex = len - 1;
						return true;
					}
				}
				catch ( IOException e )
				{
					throw new OdaException( e );
				}
			}
		}
		
		private void back( )
		{
			if ( currentIndex >= 0 )
			{
				currentIndex--;
			}
		}
		
		private char getChar( )
		{
			return charBuffer[currentIndex];
		}
		
		private String getColumnValue( StringBuffer column )
		{
			if ( column.length( ) >=  2 
					&& column.charAt( 0 ) == '"'
						&& column.charAt( column.length( ) - 1 ) == '"')
			{
				//double quoted
				return column.substring( 1, column.length( ) - 1 );
			}
			else
			{
				//otherwise, applying trim
				return column.toString( ).trim( );
			}
		}
		
		private boolean isAnEmptyChar( char c )
		{
			return String.valueOf( c ).trim( ).equals( "" ); //$NON-NLS-1$
		}

		/**
		 * Close the reader. This method should be call every time the reader is
		 * finish reading.
		 * 
		 * @throws IOException
		 */
		public void close( ) throws IOException
		{
			this.charBuffer = null;
			this.reader.close( );
		}

	}
}
