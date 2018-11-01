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

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.util.MappedTables;

/**
 * This class implements IQuery interface.
 */
public class Query implements IQuery
{
	//The name of the XML table that will be prepared
	private String tableName;

	//The max rows of result set created by this query that might returned
	private int maxRows;
	
	//	indicate whether the result set has been closed.
	private boolean isClosed;
	
	private Connection connection;
	
	private MappedTables mt;
	/**
	 * 
	 * @param file
	 * @param ri
	 */
	public Query( Connection connection )
	{
		tableName = null;
		maxRows = 0;
		isClosed = false;
		this.connection = connection;
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#prepare(java.lang.String)
	 */
	public void prepare( String queryText ) throws OdaException
	{
		testClosed();
		if ( queryText == null )
			throw new org.eclipse.datatools.connectivity.oda.OdaException( Messages.getString("Query.InvalidQueryText") ); //$NON-NLS-1$
	
		String[] temp = queryText.trim().split(Constants.QUERYTEXT_TABLE_NAME_DEFN_DELIMITER);
		
		if ( temp.length != 2 )
			throw new org.eclipse.datatools.connectivity.oda.OdaException( Messages.getString("Query.InvalidQueryText") ); //$NON-NLS-1$
	
		this.tableName = temp[0];
		
		this.mt = new MappedTables( temp[1] );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setProperty(java.lang.String, java.lang.String)
	 */
	public void setProperty( String name, String value ) throws OdaException
	{
		//Do nothing.
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#close()
	 */
	public void close( ) throws OdaException
	{
		this.isClosed = true;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setMaxRows(int)
	 */
	public void setMaxRows( int max ) throws OdaException
	{
		testClosed();
		this.maxRows = max > 0 ? max:0;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getMaxRows()
	 */
	public int getMaxRows( ) throws OdaException
	{
		testClosed();
		return this.maxRows;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getMetaData()
	 */
	public IResultSetMetaData getMetaData( ) throws OdaException
	{
		testClosed();
		return new ResultSetMetaData( mt, tableName);
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#executeQuery()
	 */
	public IResultSet executeQuery( ) throws OdaException
	{
		testClosed( );
		if ( this.tableName == null || this.tableName.trim( ).length( ) == 0 )
			throw new OdaException( Messages.getString( "Query.QueryHasNotBeenPrepared" ) ); //$NON-NLS-1$
		
		ResultSet result = new ResultSet( connection, mt,
				tableName,
				this.getMaxRows( ));
	
		return result;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#clearInParameters()
	 */
	public void clearInParameters( ) throws OdaException
	{
		throw new UnsupportedOperationException ();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(java.lang.String, int)
	 */
	public void setInt( String parameterName, int value ) throws OdaException
	{
		setString( parameterName, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(int, int)
	 */
	public void setInt( int parameterId, int value ) throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(java.lang.String, double)
	 */
	public void setDouble( String parameterName, double value )
			throws OdaException
	{
		setString( parameterName, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(int, double)
	 */
	public void setDouble( int parameterId, double value ) throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(java.lang.String, java.math.BigDecimal)
	 */
	public void setBigDecimal( String parameterName, BigDecimal value )
			throws OdaException
	{
		setString( parameterName, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(int, java.math.BigDecimal)
	 */
	public void setBigDecimal( int parameterId, BigDecimal value )
			throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(java.lang.String, java.lang.String)
	 */
	public void setString( String parameterName, String value )
			throws OdaException
	{
		if ( mt != null )
		{
			mt.setParameterValue( parameterName, value );
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(int, java.lang.String)
	 */
	public void setString( int parameterId, String value ) throws OdaException
	{
		if ( mt != null )
		{
			if ( parameterId >= 1 && parameterId <= mt.getParameters( ).length )
			{
				mt.setParameterValue( mt.getParameters( )[parameterId-1], value );
			}
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(java.lang.String, java.sql.Date)
	 */
	public void setDate( String parameterName, Date value ) throws OdaException
	{
		setString( parameterName, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(int, java.sql.Date)
	 */
	public void setDate( int parameterId, Date value ) throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(java.lang.String, java.sql.Time)
	 */
	public void setTime( String parameterName, Time value ) throws OdaException
	{
		setString( parameterName, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(int, java.sql.Time)
	 */
	public void setTime( int parameterId, Time value ) throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(java.lang.String, java.sql.Timestamp)
	 */
	public void setTimestamp( String parameterName, Timestamp value )
			throws OdaException
	{
		setString( parameterName, String.valueOf( value ) );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(int, java.sql.Timestamp)
	 */
	public void setTimestamp( int parameterId, Timestamp value )
			throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(java.lang.String, boolean)
     */
    public void setBoolean( String parameterName, boolean value )
            throws OdaException
    {
    	setString( parameterName, String.valueOf( value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(int, boolean)
     */
    public void setBoolean( int parameterId, boolean value )
            throws OdaException
    {
    	setString( parameterId, String.valueOf( value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setObject(int, java.lang.Object)
     */
    public void setObject( int parameterId, Object value ) throws OdaException
    {
    	setString( parameterId, String.valueOf( value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setObject(java.lang.String, java.lang.Object)
     */
    public void setObject( String parameterName, Object value )
            throws OdaException
    {
    	setString( parameterName, String.valueOf( value ) );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(java.lang.String)
     */
    public void setNull( String parameterName ) throws OdaException
    {
    	setString( parameterName, "" ); //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(int)
     */
    public void setNull( int parameterId ) throws OdaException
    {
    	setString( parameterId, "" ); //$NON-NLS-1$
    }

    /*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#findInParameter(java.lang.String)
	 */
	public int findInParameter( String parameterName ) throws OdaException
	{
		if ( mt == null )
		{
			return -1;
		}
		for ( int i=0; i<mt.getParameters( ).length; i++ )
		{
			if ( mt.getParameters( )[i].equals( parameterName ))
			{
				return i+1;
			}
		}
		return -1;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getParameterMetaData()
	 */
	public IParameterMetaData getParameterMetaData( ) throws OdaException
	{
		if ( mt == null )
		{
			return null;
		}
		return new IParameterMetaData( )
		{

			public int getParameterCount( ) throws OdaException
			{
				return mt.getParameters( ).length;
			}

			public int getParameterMode( int param ) throws OdaException
			{
				return IParameterMetaData.parameterModeIn;
			}

			public String getParameterName( int param ) throws OdaException
			{
				return mt.getParameters( )[param - 1];
			}

			public int getParameterType( int param ) throws OdaException
			{
				return DataTypes.STRING;
			}

			public String getParameterTypeName( int param ) throws OdaException
			{
				return DataTypes.getTypeString( DataTypes.STRING );
			}

			public int getPrecision( int param ) throws OdaException
			{
				return -1;
			}

			public int getScale( int param ) throws OdaException
			{
				return -1;
			}

			public int isNullable( int param ) throws OdaException
			{
				return IParameterMetaData.parameterNullableUnknown;
			}
			
		};
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setSortSpec(org.eclipse.datatools.connectivity.oda.SortSpec)
	 */
	public void setSortSpec( SortSpec sortBy ) throws OdaException
	{
		throw new UnsupportedOperationException ();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getSortSpec()
	 */
	public SortSpec getSortSpec( ) throws OdaException
	{
		throw new UnsupportedOperationException ();
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getEffectiveQueryText()
     */
    public String getEffectiveQueryText()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getSpecification()
     */
    public QuerySpecification getSpecification()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setSpecification(org.eclipse.datatools.connectivity.oda.spec.QuerySpecification)
     */
    public void setSpecification( QuerySpecification querySpec )
            throws OdaException, UnsupportedOperationException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#cancel()
     */
    public void cancel() throws OdaException, UnsupportedOperationException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setAppContext(java.lang.Object)
	 */
	public void setAppContext( Object context ) throws OdaException
	{
		throw new UnsupportedOperationException ();		
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
			throw new OdaException( Messages.getString("Query.ResultSetClosed")); //$NON-NLS-1$
	}
}

