/*******************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.impl;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.util.MappedTables;
import org.eclipse.datatools.enablement.oda.xml.util.RelationInformation;
import org.eclipse.datatools.enablement.oda.xml.util.SaxParserConsumer;
import org.eclipse.datatools.enablement.oda.xml.util.date.DateUtil;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.util.ULocale;

/**
 * This class implement IResultSet class 
 */
public class ResultSet implements IResultSet
{
	private static final String FALSE_LITERAL = "false";	//$NON-NLS-1$
    private static final String TRUE_LITERAL = "true";	//$NON-NLS-1$
    private static final String SINGLE_SPACE = " ";	//$NON-NLS-1$

	private MappedTables mt;
	
	//the max number of rows can be fetched from this result set.
	private int maxRows;
	
	//indicate whether the last getX() returns null.
	private boolean wasNull;
	
	//indicate whether the result set has been closed.
	private boolean isClosed;
	
	//The ISaxParserConsumer class used to help populating the data.
	private SaxParserConsumer spConsumer;
	
	private String tableName;
	
	private RelationInformation relationInfo;
	
	private Connection connection;
	
	private static ULocale JRE_DEFAULT_LOCALE = ULocale.getDefault( );
	
	private int rowID;
	
	/**
	 * 
	 * @param fileName
	 * @param ri
	 * @param tableName
	 * @throws OdaException
	 */
	public ResultSet( Connection connection, MappedTables mt, String tableName, int maxRows )
			throws OdaException
	{
		this.mt = mt;

		this.maxRows = maxRows;
		
		this.relationInfo = new RelationInformation( mt, true );
		this.tableName = tableName;
		this.connection = connection;
		
		isClosed = false;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getMetaData()
	 */
	public IResultSetMetaData getMetaData( ) throws OdaException
	{
		testClosed();
		return new ResultSetMetaData( mt, tableName );
	}

	/**
	 * If the result set is closed then throw an OdaException. This method is invoked
	 * before an method defined in IResultSet is called.
	 * 
	 * @throws OdaException
	 */
	private void testClosed() throws OdaException
	{
		if( isClosed )
			throw new OdaException( Messages.getString("ResultSet.ResultSetClosed")); //$NON-NLS-1$
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#close()
	 */
	public void close( ) throws OdaException
	{
		if ( this.spConsumer != null )
		{
			this.spConsumer.close( );
		}
		this.isClosed = true;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#setMaxRows(int)
	 */
	public void setMaxRows( int max ) throws OdaException
	{
		testClosed();
		this.maxRows = max;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#next()
	 */
	public boolean next( ) throws OdaException
	{
		testClosed();
		
		if ( spConsumer == null )
		{
			spConsumer = new SaxParserConsumer( relationInfo, connection.getXMLSource( ), tableName );
		}
		
		boolean hasNext = spConsumer.next( );
		if ( hasNext )
		{
			rowID++;
		}
		//If the row number exceeds the defined maxRows then return false;
		if ( rowID > maxRows && maxRows != 0 )
		{
			return false;
		}
		return hasNext;
	}

	

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getRow()
	 */
	public int getRow( ) throws OdaException
	{
		testClosed();
		return rowID;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getString(int)
	 */
	public String getString( int index ) throws OdaException
	{
		testClosed();
		String[] resultRow = spConsumer.getRowValue( );
		String result = null;
		if( resultRow != null)
			result = resultRow[ index - 1];
		this.wasNull = result == null ? true : false;
		return result;
	}


	
	/**
	 * Return the index of a column
	 * 
	 * @param columnName
	 * @return
	 * @throws OdaException
	 */
	private int getColumnIndex( String columnName ) throws OdaException
	{
		return mt.getColumnIndex( tableName, columnName );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getString(java.lang.String)
	 */
	public String getString( String name ) throws OdaException
	{
		testClosed();
		return this.getString(this.getColumnIndex( name));
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getInt(int)
	 */
	public int getInt( int index ) throws OdaException
	{
		return stringToInt ( getString(index) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getInt(java.lang.String)
	 */
	public int getInt( String name ) throws OdaException
	{
		return stringToInt( getString( name));
	}

	 /**
     * Transform a String value to an int value.
     * 
     * @param stringValue String value
     * @return Corresponding int value
	 * @throws OdaException 
     */
    private int stringToInt( String stringValue ) throws OdaException
    {
    	testClosed();
    	if( stringValue != null )
        {
            try
            {
                return new Integer( stringValue ).intValue();
            }
            catch( NumberFormatException e )
            {
				try
				{
					Number number = NumberFormat.getInstance( JRE_DEFAULT_LOCALE ).parse( stringValue );
					if ( number != null )
					{
						return number.intValue( );
					}
				}
				catch ( ParseException e1 )
				{
				}
            }
        }
    	this.wasNull = true;
        return 0;
    }

    /*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDouble(int)
	 */
	public double getDouble( int index ) throws OdaException
	{
		return stringToDouble( getString(index));
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDouble(java.lang.String)
	 */
	public double getDouble( String name ) throws OdaException
	{
		return stringToDouble( getString( name));
	}

	/**
     * Transform a String value to a double value
     * 
     * @param stringValue String value
     * @return Corresponding double value
	 * @throws OdaException 
     */
    private double stringToDouble( String stringValue ) throws OdaException
    {
    	testClosed();
    	if( stringValue != null )
        {
            try
            {
                return new Double( stringValue ).doubleValue();
            }
            catch( NumberFormatException e )
            {
				try
				{
					Number number = NumberFormat.getInstance( JRE_DEFAULT_LOCALE ).parse( stringValue );
					if ( number != null )
					{
						return number.doubleValue( );
					}
				}
				catch ( ParseException e1 )
				{
				}
            }
        }
    	this.wasNull = true;
        return 0;
    }

    /*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBigDecimal(int)
	 */
	public BigDecimal getBigDecimal( int index ) throws OdaException
	{
		return stringToBigDecimal( getString( index ));
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBigDecimal(java.lang.String)
	 */
	public BigDecimal getBigDecimal( String name ) throws OdaException
	{
		return stringToBigDecimal( getString( name ));
	}

	/**
     * Transform a String value to a big decimal value
     * 
     * @param stringValue String value
     * @return Corresponding BigDecimal value
	 * @throws OdaException 
     */
    private BigDecimal stringToBigDecimal( String stringValue ) throws OdaException
    {
    	testClosed( );
    	if( stringValue != null )
        {
            try
            {
                return new BigDecimal( stringValue );
            }
            catch( NumberFormatException e )
            {
				try
				{
					Number number = NumberFormat.getInstance( JRE_DEFAULT_LOCALE ).parse( stringValue );
					if ( number != null )
					{
						return new BigDecimal( number.toString( ) );
					}
				}
				catch ( ParseException e1 )
				{
				}
            }
        }
    	this.wasNull = true;
        return null;
    }

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDate(int)
	 */
	public Date getDate( int index ) throws OdaException
	{
		return stringToDate( getString( index ));
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDate(java.lang.String)
	 */
	public Date getDate( String columnName ) throws OdaException
	{
		return stringToDate( getString( columnName ) );
	}

	/**
     * Transform a String value to a date value
     * 
     * @param stringValue String value
     * @return Corresponding date value
	 * @throws OdaException 
     */

    private Date stringToDate( String stringValue ) throws OdaException
    {
    	testClosed();
    	if ( stringValue != null )
		{
			try
			{
				return DateUtil.toSqlDate( stringValue );
			}
			catch ( OdaException oe )
			{
				this.wasNull = true;
				return null;
			}
		}
        return null;
    }
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTime(int)
	 */
	public Time getTime( int index ) throws OdaException
	{
		return stringToTime( this.getString( index ));
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTime(java.lang.String)
	 */
	public Time getTime( String columnName ) throws OdaException
	{
		return stringToTime( this.getString( columnName ));
	}

	 /**
     * Transform a String value to a Time value
     * @param stringValue String value
     * @return Corresponding Time value
	 * @throws OdaException 
     */
    private Time stringToTime( String stringValue ) throws OdaException
    {
    	testClosed();
    	if ( stringValue != null )
		{
			try
			{
				return DateUtil.toSqlTime( stringValue );
			}
			catch ( OdaException oe )
			{
				this.wasNull = true;
				return null;
			}
		}
		this.wasNull = true;
		return null;
	}
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTimestamp(int)
	 */
	public Timestamp getTimestamp( int index ) throws OdaException
	{
		return stringToTimestamp( this.getString( index ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTimestamp(java.lang.String)
	 */
	public Timestamp getTimestamp( String columnName ) throws OdaException
	{
		return stringToTimestamp( this.getString( columnName) );
	}

	/**
     * Transform a String value to a Timestamp value
     * @param stringValue String value
     * @return Corresponding Timestamp value
	 * @throws OdaException 
     */
    private Timestamp stringToTimestamp( String stringValue ) throws OdaException
    {
    	testClosed();
    	if( stringValue != null )
        {
            try
            {
            	if ( !stringValue.endsWith( "Z" ))
				{
            	stringValue = stringValue.replaceAll("\\QT\\E", SINGLE_SPACE).split("\\QZ\\E")[0];//$NON-NLS-1$	//$NON-NLS-2$
				}
            	return Timestamp.valueOf( stringValue );
            }
            catch( IllegalArgumentException e )
            {
            	try{
            		long timeMills = new Long(stringValue).longValue();
            		return new Timestamp( timeMills );
            	}catch ( NumberFormatException e1)
            	{
            		try
					{
						java.util.Date date = DateUtil.toDate( stringValue );
						Timestamp timeStamp = new Timestamp( date.getTime( ) );

						return timeStamp;
					}
					catch ( OdaException oe )
					{
						this.wasNull = true;
						return null;
					}
            	}
            	
            }
        }
    	this.wasNull = true;
        return null;
    }
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#wasNull()
	 */
	public boolean wasNull( ) throws OdaException
	{
		testClosed();
		return this.wasNull;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#findColumn(java.lang.String)
	 */
	public int findColumn( String columnName ) throws OdaException
	{
		testClosed();
		return this.getColumnIndex( columnName );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBlob(int)
	 */
	public IBlob getBlob( int index ) throws OdaException
	{
		throw new UnsupportedOperationException ();	
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBlob(java.lang.String)
	 */
	public IBlob getBlob( String columnName ) throws OdaException
	{
		throw new UnsupportedOperationException ();	
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getClob(int)
	 */
	public IClob getClob( int index ) throws OdaException
	{
		throw new UnsupportedOperationException ();	
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getClob(java.lang.String)
	 */
	public IClob getClob( String columnName ) throws OdaException
	{
		throw new UnsupportedOperationException ();	
	}
	
	    
	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBoolean(java.lang.String)
	 */
	public boolean getBoolean( String columnName ) throws OdaException
	{
		return stringToBoolean( getString( columnName ) ).booleanValue( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBoolean(int)
	 */
	public boolean getBoolean( int index ) throws OdaException
	{
		return stringToBoolean( getString( index ) ).booleanValue( );
	}
	
	/**
     * Transform a String value to a Boolean value
     * 
     * @param stringValue String value
     * @return Corresponding boolean value
	 * @throws OdaException 
     */
    private Boolean stringToBoolean( String stringValue ) throws OdaException
	{
		testClosed( );
		if ( stringValue != null )
		{
			if ( stringValue.equalsIgnoreCase( TRUE_LITERAL ) )
				return Boolean.TRUE;
			else if ( stringValue.equalsIgnoreCase( FALSE_LITERAL ) )
				return Boolean.FALSE;
			else
			{
				try
				{
					if ( Integer.parseInt( (String) stringValue ) == 0 )
						return Boolean.FALSE;
					else
						return Boolean.TRUE;
				}
				catch ( NumberFormatException e )
				{
					try
					{
						Number number = NumberFormat.getInstance( JRE_DEFAULT_LOCALE ).parse( stringValue );
						if ( number != null )
						{
							return number.intValue( ) == 0 ? Boolean.FALSE : Boolean.TRUE;
						}
					}
					catch ( ParseException e1 )
					{
					}
				}
			}
		}
		return Boolean.FALSE;
	}

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getObject(int)
     */
    public Object getObject( int index ) throws OdaException
    {
        throw new UnsupportedOperationException(); 
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getObject(java.lang.String)
     */
    public Object getObject( String columnName ) throws OdaException
    {
        throw new UnsupportedOperationException(); 
    }
	
}
