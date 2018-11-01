/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda;

/**
 * The interface that represents the meta-data
 * of an IResultSet object.
 * An IResultSetMetaData object represents a row containing meta-data 
 * for each column in the result set.
 * <p>
 * <b>Note:</b> All indices in this interface are 1-based.
 */
public interface IResultSetMetaData
{
	/**
	 * The constant indicating that a column does not allow 
	 * <code>NULL</code> value.
	 */
	public static final int columnNoNulls = 0;
	
	/**
	 * The constant indicating that a column allows 
	 * <code>NULL</code> value.
	 */
	public static final int columnNullable = 1;
	
	/**
	 * The constant indicating that the nullability of a column's 
	 * values is unknown.
	 */
	public static final int columnNullableUnknown = 2;
	
	/**
	 * Returns the number of columns in the corresponding IResultSet object.
	 * @return	number of columns.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getColumnCount( ) throws OdaException;
		
	/**
	 * Returns the name of the specific column.
	 * @param index	column number (1-based).
	 * @return		the column name.
	 * @throws OdaException		if data source error occurs.
	 */
	public String getColumnName( int index ) throws OdaException; 
	
	/**
	 * Returns the designated column's suggested title for use 
	 * in the column heading and/or display name.
	 * @param index column number (1-based).
	 * @return		the column's suggested title.
	 * @throws OdaException		if data source error occurs.
	 */
	public String getColumnLabel( int index ) throws OdaException;
	
	/**
	 * Returns the data provider specific code of the column's data type.
	 * The valid values are implementation-specific.
	 * @param index	column number (1-based).
	 * @return		the native data type code of the column.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getColumnType( int index ) throws OdaException;
	
	/**
	 * Returns the data provider specific name of the column's data type.
	 * @param index	column number (1-based).
	 * @return		the native data type name of the column.
	 * @throws OdaException		if data source error occurs.
	 */
	public String getColumnTypeName( int index ) throws OdaException;
	
	/**
	 * Returns the display length of the specific column.
	 * @param index	column number (1-based).
	 * @return		the column display length, or -1 if unknown.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getColumnDisplayLength( int index ) throws OdaException;

	/**
	 * Returns the maximum number of decimal digits of the specific column.  
	 * This method should only apply to numeric data types; however, it is 
	 * up to an ODA data provider to determine those data types that are 
	 * applicable. The maximum precision allowed on a data type may vary 
	 * depending on the data provider.
	 * <br>An optional method.
	 * @param index	column number (1-based).
	 * @return		the column precision, or -1 if not applicable.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getPrecision( int index ) throws OdaException;
	
	/**
	 * Returns the maximum number of digits to the right of the decimal 
	 * point of the specific column.  This method should only apply 
	 * to numeric data types, however, it is up to an ODA data provider 
	 * to determine those data types that are applicable. 
	 * The maximum scale allowed on a data type may vary depending on the 
	 * data provider.
	 * <br>An optional method.
	 * @param index	column number.
	 * @return		the column scale, or -1 if not applicable.
	 * @throws OdaException		if data source error occurs.
	 */
	public int getScale( int index ) throws OdaException;
	
	/**
	 * Indicates the nullability of values in the designated column. 
	 * <br>An optional method.
	 * @param index	column number
	 * @return		the nullability status of the specified column;<br>
	 * 				one of columnNoNulls,<br>
	 * 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 				columnNullable,<br>
	 * 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 				columnNullableUnknown. 
	 * @throws OdaException		if data source error occurs.
	 */
	public int isNullable( int index ) throws OdaException;	
}

