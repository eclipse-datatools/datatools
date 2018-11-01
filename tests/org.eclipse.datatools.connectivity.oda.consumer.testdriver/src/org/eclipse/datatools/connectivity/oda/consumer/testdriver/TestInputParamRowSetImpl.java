/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.testdriver;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.eclipse.datatools.connectivity.oda.OdaException;

public class TestInputParamRowSetImpl extends TestParamRowSetImpl 
{
	TestInputParamRowSetImpl( boolean isTable ) throws OdaException
	{
		super( isTable, new TestResultSetMetaDataImpl( false ) );
	}

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setBoolean(int, boolean)
     */
    public void setBoolean( int columnIndex, boolean value )
            throws OdaException
    {
        setValue( columnIndex, new Boolean( value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setBoolean(java.lang.String, boolean)
     */
    public void setBoolean( String columnName, boolean value )
            throws OdaException
    {
        setValue( columnName, new Boolean( value ) );
    }

	public void setBigDecimal( int columnIndex, BigDecimal value )
			throws OdaException 
	{
		setValue( columnIndex, value );
	}

	public void setBigDecimal( String columnName, BigDecimal value )
			throws OdaException 
	{
		setValue( columnName, value );
	}

	public void setDate( int columnIndex, Date value ) throws OdaException
	{
		setValue( columnIndex, value );
	}

	public void setDate( String columnName, Date value ) throws OdaException
	{
		setValue( columnName, value );
	}

	public void setDouble( int columnIndex, double value ) throws OdaException
	{
		setValue( columnIndex, new Double( value ) );
	}

	public void setDouble( String columnName, double value ) throws OdaException
	{
		setValue( columnName, new Double( value ) );
	}

	public void setInt( int columnIndex, int value ) throws OdaException
	{
		setValue( columnIndex, new Integer( value ) );
	}

	public void setInt( String columnName, int value ) throws OdaException
	{
		setValue( columnName, new Integer( value ) );
	}

	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setObject(int, java.lang.Object)
     */
    public void setObject( int columnIndex, Object value ) throws OdaException
    {
        setValue( columnIndex, value );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IParameterRowSet#setObject(java.lang.String, java.lang.Object)
     */
    public void setObject( String columnName, Object value )
            throws OdaException
    {
        setValue( columnName, value );
    }

    public void setString( int columnIndex, String value ) throws OdaException
	{
		setValue( columnIndex, value );
	}

	public void setString( String columnName, String value ) throws OdaException
	{
		setValue( columnName, value );
	}

	public void setTime( int columnIndex, Time value ) throws OdaException
	{
		setValue( columnIndex, value );
	}

	public void setTime( String columnName, Time value ) throws OdaException
	{
		setValue( columnName, value );
	}

	public void setTimestamp( int columnIndex, Timestamp value )
			throws OdaException
	{
		setValue( columnIndex, value );
	}

	public void setTimestamp( String columnName, Timestamp value )
			throws OdaException
	{
		setValue( columnName, value );
	}

    public void setNull( int columnIndex )
            throws OdaException
    {
        setValue( columnIndex, null );
    }

    public void setNull( String columnName )
            throws OdaException
    {
        setValue( columnName, null );
    }

	private void setValue( int columnIndex, Object val ) throws OdaException
	{
		TestParamRow row = getCurrentRow();
		row.setValue( columnIndex, val );
	}

	private void setValue( String colName, Object val ) throws OdaException
	{
		int index = getColIndex( colName );
		setValue( index, val );
	}
}
