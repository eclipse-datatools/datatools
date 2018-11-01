/*
 ******************************************************************************
 * Copyright (c) 2012 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 ******************************************************************************
 */

package org.eclipse.datatools.enablement.oda.ws.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.enablement.oda.ws.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;

public class InternalXMLQuery implements IQuery
{

	private IConnection conn;
	private IQuery dataQuery;
	private IResultSetMetaData metadata;

	public InternalXMLQuery( InputStream inputStream, IDriver xmlDriver,
			String xmlQueryText, int maxRows ) throws OdaException
	{
		if ( WSUtil.isNull( inputStream ) )
			throw new OdaException( );

		this.conn = xmlDriver.getConnection( null );
		Map<String, Object> map = new HashMap<String, Object>( );
		map.put( Constants.INPUTSTREAM_XML, inputStream );
		this.conn.setAppContext( map );
		this.conn.open( new Properties( ) );

		this.dataQuery = this.conn.newQuery( null );
		this.dataQuery.setMaxRows( maxRows );
		this.dataQuery.prepare( xmlQueryText );
	}

	public void prepare( String queryText ) throws OdaException
	{
		this.dataQuery.prepare( queryText );
	}

	public void setAppContext( Object context ) throws OdaException
	{
		this.dataQuery.setAppContext( context );
	}

	public void setProperty( String name, String value ) throws OdaException
	{
		this.dataQuery.setProperty( name, value );
	}

	public void close( ) throws OdaException
	{
		try
		{
			this.conn.close( );
		}
		catch ( OdaException ignore )
		{
		}
		this.conn = null;
		this.dataQuery = null;
	}

	public void setMaxRows( int max ) throws OdaException
	{
		this.dataQuery.setMaxRows( max );
	}

	public int getMaxRows( ) throws OdaException
	{
		return this.dataQuery.getMaxRows( );
	}

	public IResultSetMetaData getMetaData( ) throws OdaException
	{
		if ( this.metadata == null )
		{
			this.metadata = this.dataQuery.getMetaData( );
		}
		return this.metadata;
	}

	public IResultSet executeQuery( ) throws OdaException
	{
		return this.dataQuery.executeQuery( );
	}

	public void clearInParameters( ) throws OdaException
	{
		this.dataQuery.clearInParameters( );
	}

	public void setInt( String parameterName, int value ) throws OdaException
	{
		this.dataQuery.setInt( parameterName, value );
	}

	public void setInt( int parameterId, int value ) throws OdaException
	{
		this.dataQuery.setInt( parameterId, value );
	}

	public void setDouble( String parameterName, double value )
			throws OdaException
	{
		this.dataQuery.setDouble( parameterName, value );
	}

	public void setDouble( int parameterId, double value ) throws OdaException
	{
		this.dataQuery.setDouble( parameterId, value );
	}

	public void setBigDecimal( String parameterName, BigDecimal value )
			throws OdaException
	{
		this.dataQuery.setBigDecimal( parameterName, value );
	}

	public void setBigDecimal( int parameterId, BigDecimal value )
			throws OdaException
	{
		this.dataQuery.setBigDecimal( parameterId, value );
	}

	public void setString( String parameterName, String value )
			throws OdaException
	{
		this.dataQuery.setString( parameterName, value );
	}

	public void setString( int parameterId, String value ) throws OdaException
	{
		this.dataQuery.setString( parameterId, value );
	}

	public void setDate( String parameterName, Date value ) throws OdaException
	{
		this.dataQuery.setDate( parameterName, value );
	}

	public void setDate( int parameterId, Date value ) throws OdaException
	{
		this.dataQuery.setDate( parameterId, value );
	}

	public void setTime( String parameterName, Time value ) throws OdaException
	{
		this.dataQuery.setTime( parameterName, value );
	}

	public void setTime( int parameterId, Time value ) throws OdaException
	{
		this.dataQuery.setTime( parameterId, value );
	}

	public void setTimestamp( String parameterName, Timestamp value )
			throws OdaException
	{
		this.dataQuery.setTimestamp( parameterName, value );
	}

	public void setTimestamp( int parameterId, Timestamp value )
			throws OdaException
	{
		this.dataQuery.setTimestamp( parameterId, value );
	}

	public void setBoolean( String parameterName, boolean value )
			throws OdaException
	{
		this.dataQuery.setBoolean( parameterName, value );
	}

	public void setBoolean( int parameterId, boolean value )
			throws OdaException
	{
		this.dataQuery.setBoolean( parameterId, value );
	}

	public void setObject( String parameterName, Object value )
			throws OdaException
	{
		this.dataQuery.setObject( parameterName, value );
	}

	public void setObject( int parameterId, Object value ) throws OdaException
	{
		this.dataQuery.setObject( parameterId, value );
	}

	public void setNull( String parameterName ) throws OdaException
	{
		this.dataQuery.setNull( parameterName );
	}

	public void setNull( int parameterId ) throws OdaException
	{
		this.dataQuery.setNull( parameterId );
	}

	public int findInParameter( String parameterName ) throws OdaException
	{
		return this.dataQuery.findInParameter( parameterName );
	}

	public IParameterMetaData getParameterMetaData( ) throws OdaException
	{
		return this.dataQuery.getParameterMetaData( );
	}

	public void setSortSpec( SortSpec sortBy ) throws OdaException
	{
		this.dataQuery.setSortSpec( sortBy );
	}

	public SortSpec getSortSpec( ) throws OdaException
	{
		return this.dataQuery.getSortSpec( );
	}

	public void setSpecification( QuerySpecification querySpec )
			throws OdaException, UnsupportedOperationException
	{
		this.dataQuery.setSpecification( querySpec );
	}

	public QuerySpecification getSpecification( )
	{
		return this.dataQuery.getSpecification( );
	}

	public String getEffectiveQueryText( )
	{
		return this.dataQuery.getEffectiveQueryText( );
	}

	public void cancel( ) throws OdaException, UnsupportedOperationException
	{
		this.dataQuery.cancel( );
	}
}
