/*
 *******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;
import org.eclipse.datatools.connectivity.oda.flatfile.util.querytextutil.ColumnsInfoUtil;

/**
 * Flat file data provider's implementation of the ODA IResultSetMetaData
 * interface.
 */

public class ResultSetMetaData implements IResultSetMetaData
{

	private String[] columnNames = null;
	private String[] columnTypeNames = null;
	private String[] columnLabels = null;
	private String[] originalColumnNames = null;

	/**
	 * Constructor
	 * 
	 * @param colNames
	 * @param colTypes
	 * @param colLabels
	 * @throws OdaException
	 */
	public ResultSetMetaData( String[] colNames, String[] colTypes, String[] colLabels )
			throws OdaException
	{
		if ( colNames == null )
			throw new OdaException( Messages.getString( "common_ARGUMENT_CANNOT_BE_NULL" ) ); //$NON-NLS-1$

		this.columnNames = colNames;
		this.columnTypeNames = colTypes;
		this.columnLabels = colLabels;
		this.originalColumnNames = colNames;

		trimMetaDataStrings( );
	}

	/**
	 * Constructor
	 * 
	 * @param savedSelectedColumnsInfoString
	 *            the saved selected columns information string
	 * 
	 */
	ResultSetMetaData( String savedSelectedColumnsInfoString )
	{
		assert savedSelectedColumnsInfoString !=null;
		this.columnNames = ColumnsInfoUtil.getColumnNames( savedSelectedColumnsInfoString );
		this.columnTypeNames = ColumnsInfoUtil.getColumnTypeNames( savedSelectedColumnsInfoString );
		this.originalColumnNames = ColumnsInfoUtil.getOriginalColumnNames( savedSelectedColumnsInfoString );
		this.columnLabels = this.columnNames;

		trimMetaDataStrings( );

	}

	private void trimMetaDataStrings( )
	{
		assert columnNames.length == columnTypeNames.length
				&& columnTypeNames.length == originalColumnNames.length
				&& originalColumnNames.length == columnLabels.length;

		for ( int i = 0; i < columnNames.length; i++ )
		{
			columnNames[i] = columnNames[i].trim( );
			columnTypeNames[i] = columnTypeNames[i].trim( );
			columnLabels[i] = columnLabels[i].trim( );
			originalColumnNames[i] = originalColumnNames[i].trim( );
		}
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnCount()
	 */
	public int getColumnCount( ) throws OdaException
	{
		return this.columnNames.length;
	}

	String getOriginalColumnName( int index ) throws OdaException
	{
		validateColumnIndex( index );
		if ( originalColumnNames == null )
			originalColumnNames = columnNames;

		return this.originalColumnNames[index - 1].trim( );
	}

	String getOriginalColumnName( String columnName )
	{
		String originName = null;

		for ( int i = 0; i < columnNames.length; i++ )
		{
			if ( columnName.equals( columnNames[i] ) )
			{
				originName = originalColumnNames[i];
			}
		}

		return originName;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnName(int)
	 */
	public String getColumnName( int index ) throws OdaException
	{
		validateColumnIndex( index );
		return this.columnNames[index - 1].trim( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnLabel(int)
	 */
	public String getColumnLabel( int index ) throws OdaException
	{
		validateColumnIndex( index );
		// "null" in lower case represents null label value;
		if ( this.columnLabels == null
				|| this.columnLabels[index - 1].equals( "null" ) ) //$NON-NLS-1$
			return this.getColumnName( index ); // use column name instead

		return this.columnLabels[index - 1].trim( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnType(int)
	 */
	public int getColumnType( int index ) throws OdaException
	{
		validateColumnIndex( index );
		return ( this.columnTypeNames == null ) ? DataTypes.STRING
				: DataTypes.getTypeCode( columnTypeNames[index - 1] );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnTypeName(int)
	 */
	public String getColumnTypeName( int index ) throws OdaException
	{
		validateColumnIndex( index );
		return ( this.columnTypeNames == null ) ? DataTypes.NULL_LITERAL :
				columnTypeNames[index - 1].trim( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnDisplayLength(int)
	 */
	public int getColumnDisplayLength( int index ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getPrecision(int)
	 */
	public int getPrecision( int index ) throws OdaException
	{
		return -1;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getScale(int)
	 */
	public int getScale( int index ) throws OdaException
	{
		return -1;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#isNullable(int)
	 */
	public int isNullable( int index ) throws OdaException
	{
		return columnNullableUnknown;
	}

	/**
	 * Evaluate whether the value of given column number is valid.
	 * 
	 * @param index
	 *            column number (1-based)
	 * @throws OdaException
	 *             if the given index value is invalid
	 */
	private void validateColumnIndex( int index ) throws OdaException
	{
		if ( index > getColumnCount( ) || index < 1 )
			throw new OdaException( Messages.getString( "resultSetMetaData_INVALID_COLUMN_INDEX" ) + index ); //$NON-NLS-1$
	}
}