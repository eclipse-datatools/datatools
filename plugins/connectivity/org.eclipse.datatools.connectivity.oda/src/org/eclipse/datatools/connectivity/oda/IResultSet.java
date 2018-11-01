/*
 *************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
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

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * The interface used to access a set of data rows retrieved 
 * by an IQuery object.
 * An IResultSet object maintains a cursor pointing to its current row of data. 
 * Initially, the cursor is positioned before the first row.  The next() method 
 * moves the cursor to the next row until there are no more rows, or if the 
 * MaxRows limit has been reached.
 * <p>
 * A result set column may be referenced by name or position.  
 * <br>
 * The case-sensitivity of a name is implementation-dependent.
 * All indices in this interface are 1-based.
 */
public interface IResultSet
{
	/**
	 * Returns the metadata associated with this IResultSet.
	 * @return	the metadata for this IResultSet
	 * @throws OdaException		if data source error occurs
	 */
	public IResultSetMetaData getMetaData( ) throws OdaException;
	
	/**
	 * Closes the cursor associated with this IResultSet.
	 * @throws OdaException		if data source error occurs
	 */
	public void close( ) throws OdaException;
	
	/**
	 * Specifies the maximum number of rows that can be fetched from this result set.
	 * <br>An optional method.
	 * @param max	maximum number of rows that can be fetched from this IResultSet;
	 * 				zero means there is no limit.
	 * 				This value should not be greater than the maximum number of rows
	 * 				specified in the related IQuery.
	 * @throws OdaException		if data source error occurs
	 * @since	1.1
	 */
	public void setMaxRows( int max ) throws OdaException;
	
	/**
	 * Moves the cursor down one row from its current position.
	 * @return	true, if next data row exists and the maxRows limit has  
	 * 			not been reached.
	 * @throws OdaException		if data source error occurs
	 */
	public boolean next( ) throws OdaException;
	
	/**
	 * Returns the current row's 1-based index position.
	 * <br>An optional method.
	 * @return	current row's 1-based index position
	 * @throws OdaException		if data source error occurs
	 */
	public int getRow( ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as a String.
	 * An ODA runtime driver may or may not support getString() on a non-String 
	 * type column.  
	 * The format of the returned string is implementation-dependent.
	 * @param index	column number (1-based)
	 * @return		the string value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public String getString( int index ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as a String.
	 * An ODA runtime driver may or may not support getString() on a non-String 
	 * type column.  
	 * The format of the returned string is implementation-dependent.
	 * @param columnName	column name
	 * @return				the string value in the specific column of the current row
	 * @throws OdaException		if data source error occurs.
	 */
	public String getString( String columnName ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as an int.
	 * @param index	column number (1-based)
	 * @return		the integer value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public int getInt( int index ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as an int.
	 * @param columnName	column name
	 * @return				the integer value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public int getInt( String columnName ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as a double.
	 * @param index	column number (1-based)
	 * @return		the double value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public double getDouble( int index ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as a double.
	 * @param columnName	column name
	 * @return				the double value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public double getDouble( String columnName ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as a decimal.
	 * @param index	column number (1-based)
	 * @return		the decimal value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 * @since	1.1
	 */
	public BigDecimal getBigDecimal( int index ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as a decimal.
	 * @param columnName	column name
	 * @return				the decimal value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 * @since	1.1
	 */
	public BigDecimal getBigDecimal( String columnName ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as a java.sql.Date.
	 * @param index	column number (1-based)
	 * @return		the java.sql.Date value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public Date getDate( int index ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as a java.sql.Date.
	 * @param columnName	column name
	 * @return				the java.sql.Date value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public Date getDate( String columnName ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as a java.sql.Time.
	 * @param index	column number (1-based)
	 * @return		the java.sql.Time value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public Time getTime( int index ) throws OdaException;
    
	/**
	 * Gets the value of the designated column in the current row as a java.sql.Time.
	 * @param columnName	column name
	 * @return				the java.sql.Time value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public Time getTime( String columnName ) throws OdaException;

	/**
	 * Gets the value of the designated column in the current row as a java.sql.Timestamp.
	 * @param index	column number (1-based)
	 * @return		the java.sql.Timestamp value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public Timestamp getTimestamp( int index ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row as a java.sql.Timestamp.
	 * @param columnName	column name
	 * @return				the java.sql.Timestamp value in the specific column of the current row
	 * @throws OdaException		if data source error occurs
	 */
	public Timestamp getTimestamp( String columnName ) throws OdaException;

	/**
	 * Gets the value of the designated column in the current row 
	 * as an IBlob object.
	 * <p><b>Note:</b> The driver must guarantee that
	 * the returned object and its BLOB data would remain valid 
	 * and accessible until this result set is closed.
	 * @param index	column number (1-based)
	 * @return		an IBlob object that represents the BLOB value 
	 * 				in the specific column of the current row;
	 * 				or <code>null</code> if the specific column has null value
	 * @throws OdaException		if data source error occurs
	 * @since		3.0
	 */
	public IBlob getBlob( int index ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row 
	 * as an IBlob object.
	 * <p><b>Note:</b> The driver must guarantee that
	 * the returned object and its BLOB data would remain valid 
	 * and accessible until this result set is closed.
	 * @param columnName	column name
	 * @return		an IBlob object that represents the BLOB value 
	 * 				in the specific column of the current row;
	 * 				or <code>null</code> if the specific column has null value
	 * @throws OdaException		if data source error occurs
	 * @since		3.0
	 */
	public IBlob getBlob( String columnName ) throws OdaException;

	/**
	 * Gets the value of the designated column in the current row 
	 * as an IClob object.
	 * <p><b>Note:</b> The driver must guarantee that
	 * the returned object and its CLOB data would remain valid 
	 * and accessible until this result set is closed.
	 * @param index	column number (1-based)
	 * @return		an IClob object that represents the CLOB value 
	 * 				in the specific column of the current row;
	 * 				or <code>null</code> if the specific column has null value
	 * @throws OdaException		if data source error occurs
	 * @since		3.0
	 */
	public IClob getClob( int index ) throws OdaException;
	
	/**
	 * Gets the value of the designated column in the current row 
	 * as an IClob object.
	 * <p><b>Note:</b> The driver must guarantee that
	 * the returned object and its CLOB data would remain valid 
	 * and accessible until this result set is closed.
	 * @param columnName	column name
	 * @return		an IClob object that represents the CLOB value 
	 * 				in the specific column of the current row;
	 * 				or <code>null</code> if the specific column has null value
	 * @throws OdaException		if data source error occurs
	 * @since		3.0
	 */
	public IClob getClob( String columnName ) throws OdaException;

    /**
     * Gets the value of the designated column in the current row 
     * as a boolean.
     * @param index column number (1-based)
     * @return      the boolean value in the specific column of the current row
     * @throws OdaException     if data source error occurs
     * @since       3.1
     */
    public boolean getBoolean( int index ) throws OdaException;

    /**
     * Gets the value of the designated column in the current row 
     * as a boolean.
     * @param columnName    column name
     * @return  the boolean value in the specific column of the current row
     * @throws OdaException     if data source error occurs
     * @since       3.1
     */
    public boolean getBoolean( String columnName ) throws OdaException;

    /**
     * Gets the value of the designated column in the current row as an {@link Object}.
     * If the column value is intended to be sortable by its consumer, the type of Object 
     * returned must implement the {@link Comparable} interface.
     * @param index column number (1-based)
     * @return      an {@link Object} value in the specific column of the current row
     * @throws OdaException     if data source error occurs
     * @since 3.2 (DTP 1.7)
     */
    Object getObject( int index ) throws OdaException;

    /**
     * Gets the value of the designated column in the current row as an {@link Object}.
     * If the column value is intended to be sortable by its consumer, the type of Object 
     * returned must implement the {@link Comparable} interface.
     * @param columnName    column name
     * @return  an {@link Object} value in the specific column of the current row; may be null
     * @throws OdaException     if data source error occurs
     * @since 3.2 (DTP 1.7)
     */
    Object getObject( String columnName ) throws OdaException;

	/**
	 * Returns whether the value read from the previous get&lt;type&gt; method
	 * was invalid or null.  This needs to be called immediately after 
	 * the call to a get&lt;type&gt; method.
	 * @return 		true, if the previous get&lt;type&gt; call was invalid or null
	 * @throws OdaException		if data source error occurs
	 */
	public boolean wasNull() throws OdaException;
    
	/**
	 * Returns the column index of the specified column name.
	 * @param columnName	name or alias of the column
	 * @return				column index (1-based)
	 * @throws OdaException		if data source error occurs
	 */
	public int findColumn( String columnName ) throws OdaException;
}

