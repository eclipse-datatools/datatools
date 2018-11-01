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
 * An optional interface that represents complex data type objects 
 * such as structures and tables.  
 * <p>
 * The interface applies only if the ODA driver supports 
 * the use of complex input and/or output parameters.
 * A structure can be represented by an IParameterRowSet object with one row.
 * This interface is used to represent complex parameter data values.  
 * A complex parameter's metadata 
 * can be obtained from its inherited getMetaData() method.
 * <p>
 * A row set column may be referenced by name or position.  
 * <br>
 * The case-sensitivity of a name is implementation-dependent.
 * All indices in this interface are 1-based.
 */
public interface IParameterRowSet extends IResultSet
{
	/**
	 * Moves the cursor to the designated row number.
	 * @param rowIndex	the row number (1-based).
	 * @return	true, if cursor is moved successfully to the desired row.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean absolute( int rowIndex ) throws OdaException;
	
	/**
	 * Moves the cursor up one element from its current position.
	 * <br>An optional method.
	 * @return	true, if the cursor is moved successfully to a valid row.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean previous() throws OdaException;
	
	/**
	 * Appends a new row to the end of this collection and moves the cursor
	 * to the new row's position.
	 * <br>Only required for input parameters.
	 * @return	0 if this failed to add a new row.  Otherwise, the rowIndex 
	 * 			of the new row.
	 * @throws OdaException		if data source error occurs.
	 */
	public int add() throws OdaException;
	
	/**
	 * Removes all of the elements from this collection.
	 * <br>An optional method.
	 * @throws OdaException		if data source error occurs.
	 */
	public void clear() throws OdaException;
	
	/**
	 * Determines whether this does not contain any elements.
	 * @return	true, if this is empty.
	 * @throws OdaException		if data source error occurs.
	 */
	public boolean isEmpty() throws OdaException;
	
	/**
	 * Returns the number of elements in this collection.
	 * @return	size of this collection.
	 * @throws OdaException		if data source error occurs.
	 */
	public int size() throws OdaException;
	
	/**
	 * Sets the integer value at the designated column.
	 * @param columnIndex	index of the column.
	 * @param value			the integer value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setInt( int columnIndex, int value ) throws OdaException;
	
	/**
	 * Sets the integer value at the designated column.
	 * @param columnName	name of the column.
	 * @param value			the integer value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setInt( String columnName, int value ) throws OdaException;
	
	/**
	 * Sets the double value at the designated column.
	 * @param columnIndex	index of the column.
	 * @param value			the double value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setDouble( int columnIndex, double value ) throws OdaException;
	
	/**
	 * Sets the double value at the designated column.
	 * @param columnName	name of the column.
	 * @param value			the double value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setDouble( String columnName, double value ) throws OdaException;
	
	/**
	 * Sets the decimal value at the designated column.
	 * @param columnIndex	index of the column.
	 * @param value			the decimal value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setBigDecimal( int columnIndex, BigDecimal value ) throws OdaException;
	
	/**
	 * Sets the decimal value at the designated column.
	 * @param columnName	name of the column.
	 * @param value			the decimal value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setBigDecimal( String columnName, BigDecimal value ) throws OdaException;
	
	/**
	 * Sets the string value at the designated column.
	 * An ODA runtime driver may or may not support setString() on a non-String 
	 * type column. 
	 * The format of the string parameter is implementation-dependent.
	 * @param columnIndex	index of the column.
	 * @param value			the string value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setString( int columnIndex, String value ) throws OdaException;
	
	/**
	 * Sets the string value at the designated column.
	 * An ODA runtime driver may or may not support setString() on a non-String 
	 * type column. 
	 * The format of the string parameter is implementation-dependent.
	 * @param columnName	name of the column.
	 * @param value			the string value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setString( String columnName, String value ) throws OdaException;
	
	/**
	 * Sets the date value at the designated column.
	 * @param columnIndex	index of the column.
	 * @param value			the java.sql.Date value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setDate( int columnIndex, Date value ) throws OdaException;
	
	/**
	 * Sets the date value at the designated column.
	 * @param columnName	name of the column.
	 * @param value			the java.sql.Date value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setDate( String columnName, Date value ) throws OdaException;
	
	/**
	 * Sets the time value at the designated column.
	 * @param columnIndex	index of the column.
	 * @param value			the java.sql.Time value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setTime( int columnIndex, Time value ) throws OdaException;
	
	/**
	 * Sets the time value at the designated column.
	 * @param columnName	name of the column.
	 * @param value			the java.sql.Time value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setTime( String columnName, Time value ) throws OdaException;
	
	/**
	 * Sets the time stamp value at the designated column.
	 * @param columnIndex	index of the column.
	 * @param value			the java.sql.Timestamp value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setTimestamp( int columnIndex, Timestamp value ) throws OdaException;
	
	/**
	 * Sets the time stamp value at the designated column.
	 * @param columnName	name of the column.
	 * @param value			the java.sql.Timestamp value.
	 * @throws OdaException		if data source error occurs.
	 */
	public void setTimestamp( String columnName, Timestamp value ) throws OdaException;	
    
    /**
     * Sets the boolean value at the designated column.
     * @param columnIndex   index of the column.
     * @param value         the boolean value.
     * @throws OdaException     if data source error occurs.
     * @since 3.1
     */
    public void setBoolean( int columnIndex, boolean value ) throws OdaException;
    
    /**
     * Sets the boolean value at the designated column.
     * @param columnName    name of the column.
     * @param value         the boolean value.
     * @throws OdaException     if data source error occurs.
     * @since 3.1
     */
    public void setBoolean( String columnName, boolean value ) throws OdaException;
    
    /**
     * Sets the object value at the designated column.
     * @param columnIndex   index of the column.
     * @param value         the Java object value.
     * @throws OdaException     if data source error occurs.
     * @since 3.2 (DTP 1.7)
     */
    public void setObject( int columnIndex, Object value ) throws OdaException;
    
    /**
     * Sets the object value at the designated column.
     * @param columnName    name of the column.
     * @param value         the Java object value.
     * @throws OdaException     if data source error occurs.
     * @since 3.2 (DTP 1.7)
     */
    public void setObject( String columnName, Object value ) throws OdaException;
    
    /**
     * Sets a null value at the designated column.
     * @param columnIndex   index of the column.
     * @throws OdaException     if data source error occurs
     * @since 3.1
     */
    public void setNull( int columnIndex ) throws OdaException;
    
    /**
     * Sets a null value at the designated column.
     * @param columnName    name of the column.
     * @throws OdaException     if data source error occurs
     * @since 3.1
     */
    public void setNull( String columnName ) throws OdaException;

}

