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

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;

/**
 * Flat file data provider's implementation of the ODA IResultSet interface.
 */

public class ResultSet implements IResultSet
{

    public static final int DEFAULT_MAX_ROWS = 1000;
    private static final int CURSOR_INITIAL_VALUE = -1;
    private String[][] sourceData = null;
    private IResultSetMetaData resultSetMetaData = null;
    private int maxRows = 0;
    private int cursor = CURSOR_INITIAL_VALUE;

    //Boolean which marks whether it is successful of last call to getXXX();
    private boolean wasNull = false;

    /**
     * Constructor
     * @param sData a two-dimension array which holds the data extracted from a
     *            flat file.
     * @param rsmd the metadata of sData
     */
    ResultSet( String[][] sData, IResultSetMetaData rsmd )
    {
        this.sourceData = sData;
        this.resultSetMetaData = rsmd;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getMetaData()
     */
    public IResultSetMetaData getMetaData() throws OdaException
    {
        return this.resultSetMetaData;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#close()
     */
    public void close() throws OdaException
    {
        this.cursor = 0;
        this.sourceData = null;
        this.resultSetMetaData = null;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#setMaxRows(int)
     */
    public void setMaxRows( int max ) throws OdaException
    {
        this.maxRows = max;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#next()
     */
    public boolean next() throws OdaException
    {
        if( ( this.maxRows <= 0 ? false : cursor >= this.maxRows - 1 )
                || cursor >= this.sourceData.length - 1 )
        {
            cursor = CURSOR_INITIAL_VALUE;
            return false;
        }
        cursor++;
        return true;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getRow()
     */
    public int getRow() throws OdaException
    {
        validateCursorState();
        return this.cursor;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getString(int)
     */
    public String getString( int index ) throws OdaException
    {
        validateCursorState();
        String result = sourceData[cursor][index - 1];
        if( result.length() == 0 )
            result = null;
        this.wasNull = result == null ? true : false;
        return result;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getString(java.lang.String)
     */
    public String getString( String columnName ) throws OdaException
    {
        validateCursorState();
        int columnIndex = findColumn( columnName );
        return getString( columnIndex );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getInt(int)
     */
    public int getInt( int index ) throws OdaException
    {
        return stringToInt( getString( index ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getInt(java.lang.String)
     */
    public int getInt( String columnName ) throws OdaException
    {
        return stringToInt( getString( columnName ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDouble(int)
     */
    public double getDouble( int index ) throws OdaException
    {
        return stringToDouble( getString( index ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDouble(java.lang.String)
     */
    public double getDouble( String columnName ) throws OdaException
    {
        return stringToDouble( getString( columnName ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBigDecimal(int)
     */
    public BigDecimal getBigDecimal( int index ) throws OdaException
    {
        return stringToBigDecimal( getString( index ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBigDecimal(java.lang.String)
     */
    public BigDecimal getBigDecimal( String columnName ) throws OdaException
    {
        return stringToBigDecimal( getString( columnName ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDate(int)
     */
    public Date getDate( int index ) throws OdaException
    {
        return stringToDate( getString( index ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDate(java.lang.String)
     */
    public Date getDate( String columnName ) throws OdaException
    {
        return stringToDate( getString( columnName ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTime(int)
     */
    public Time getTime( int index ) throws OdaException
    {
        return stringToTime( getString( index ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTime(java.lang.String)
     */
    public Time getTime( String columnName ) throws OdaException
    {
        return stringToTime( getString( columnName ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTimestamp(int)
     */
    public Timestamp getTimestamp( int index ) throws OdaException
    {
        return stringToTimestamp( getString( index ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTimestamp(java.lang.String)
     */
    public Timestamp getTimestamp( String columnName ) throws OdaException
    {
        return stringToTimestamp( getString( columnName ) );
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBlob(int)
     */
    public IBlob getBlob( int index ) throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBlob(java.lang.String)
     */
    public IBlob getBlob( String columnName ) throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getClob(int)
     */
    public IClob getClob( int index ) throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getClob(java.lang.String)
     */
    public IClob getClob( String columnName ) throws OdaException
    {
        throw new UnsupportedOperationException();
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#wasNull()
     */
    public boolean wasNull() throws OdaException
    {
        return this.wasNull;
    }

    /*
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#findColumn(java.lang.String)
     */
    public int findColumn( String columnName ) throws OdaException
    {
        String trimmedColumnName = columnName.trim();
        int columnCount = this.getMetaData().getColumnCount();
        for( int i = 1; i <= columnCount; i++ )
        {
            if( trimmedColumnName.equalsIgnoreCase(
                    this.getMetaData().getColumnName( i ) ) )
            {
                return i;
            }
        }
        throw new OdaException( Messages
                .getString( "ResultSet.COLUMN_NOT_FOUND" ) + columnName ); //$NON-NLS-1$
    }

    /**
     * Validate whether the cursor has been initialized and at a valid row.
     * @throws OdaException if the cursor is not initialized
     */
    private void validateCursorState() throws OdaException
    {
        if( this.cursor < 0 )
            throw new OdaException( Messages
                    .getString( "ResultSet.CURSOR_HAS_NOT_BEEN_INITIALIZED" ) ); //$NON-NLS-1$
    }

    /**
     * Transform a String value to an int value
     * @param stringValue String value
     * @return Corresponding int value
     */
    private int stringToInt( String stringValue )
    {
        if( stringValue != null )
        {
            try
            {
                return new Integer( stringValue ).intValue();
            }
            catch( NumberFormatException e )
            {
                this.wasNull = true;
            }
        }
        return 0;
    }

    /**
     * Transform a String value to a double value
     * @param stringValue String value
     * @return Corresponding double value
     */
    private double stringToDouble( String stringValue )
    {
        if( stringValue != null )
        {
            try
            {
                return new Double( stringValue ).doubleValue();
            }
            catch( NumberFormatException e )
            {
                this.wasNull = true;
            }
        }
        return 0;
    }

    /**
     * Transform a String value to a big decimal value
     * @param stringValue String value
     * @return Corresponding BigDecimal value
     */
    private BigDecimal stringToBigDecimal( String stringValue )
    {
        if( stringValue != null )
        {
            try
            {
                return new BigDecimal( stringValue );
            }
            catch( NumberFormatException e )
            {
                this.wasNull = true;
            }
        }
        return null;
    }

    /**
     * Transform a String value to a date value
     * @param stringValue String value
     * @return Corresponding date value
     */

    private Date stringToDate( String stringValue )
    {
        if( stringValue != null )
        {
            try
            {
                return Date.valueOf( stringValue );
            }
            catch( IllegalArgumentException e )
            {
                try
                {
                    return new Date( stringToLongDate( stringValue ) );
                }
                catch( ParseException e1 )
                {
                    this.wasNull = true;
                }
            }
        }
        return null;
    }

    /**
     * Transform a String value to a Time value
     * @param stringValue String value
     * @return Corresponding Time value
     */
    private Time stringToTime( String stringValue )
    {
        if( stringValue != null )
        {
            try
            {
                return Time.valueOf( stringValue );
            }
            catch( IllegalArgumentException e )
            {
                try
                {
                    return new Time( stringToLongDate( stringValue ) );
                }
                catch( ParseException e1 )
                {
                    this.wasNull = true;
                }
            }
        }
        return null;
    }

    /**
     * Transform a String value to a Timestamp value
     * @param stringValue String value
     * @return Corresponding Timestamp value
     */
    private Timestamp stringToTimestamp( String stringValue )
    {
        if( stringValue != null )
        {
            try
            {
                return Timestamp.valueOf( stringValue );
            }
            catch( IllegalArgumentException e )
            {
                this.wasNull = true;
            }
        }
        return null;
    }

    private long stringToLongDate( String stringValue ) throws ParseException
    {
        DateFormat dateFormat = null;
        java.util.Date resultDate = null;

        //For each pattern, we try to format a date for a default Locale
        //If format fails, we format it for Locale.US

        //Date style is SHORT such as 12.13.52
        //Time sytle is MEDIUM such as 3:30:32pm
        try
        {
            dateFormat = DateFormat.getDateTimeInstance( DateFormat.SHORT,
                    DateFormat.MEDIUM );
            resultDate = dateFormat.parse( stringValue );
        }
        catch( ParseException e )
        {
            try
            {
                dateFormat = DateFormat.getDateTimeInstance( DateFormat.SHORT,
                        DateFormat.MEDIUM, Locale.US );
                resultDate = dateFormat.parse( stringValue );
            }
            catch( ParseException e1 )
            {
            }
        }

        if( resultDate == null )
        {
            //Date style is SHORT such as 12.13.52
            //Time sytle is SHORT such as 3:30pm
            try
            {
                dateFormat = DateFormat.getDateTimeInstance( DateFormat.SHORT,
                        DateFormat.SHORT );
                resultDate = dateFormat.parse( stringValue );
            }
            catch( ParseException e )
            {
                try
                {
                    dateFormat = DateFormat.getDateTimeInstance(
                            DateFormat.SHORT, DateFormat.SHORT, Locale.US );
                    resultDate = dateFormat.parse( stringValue );
                }
                catch( ParseException e1 )
                {
                }
            }
        }

        if( resultDate == null )
        {
            //No Date style
            //Time style is short such as 13:05:55
            try
            {
                dateFormat = DateFormat.getTimeInstance( DateFormat.MEDIUM );
                resultDate = dateFormat.parse( stringValue );
            }
            catch( ParseException e )
            {
            }
        }

        if( resultDate == null )
        {
            //Date style is SHORT such as 12.13.52
            //No Time sytle
            try
            {
                dateFormat = DateFormat.getDateInstance( DateFormat.SHORT );
                resultDate = dateFormat.parse( stringValue );
            }
            catch( ParseException e )
            {
                dateFormat = DateFormat.getDateInstance( DateFormat.SHORT,
                        Locale.US );
                resultDate = dateFormat.parse( stringValue );
            }
        }

        return resultDate.getTime();
    }

}