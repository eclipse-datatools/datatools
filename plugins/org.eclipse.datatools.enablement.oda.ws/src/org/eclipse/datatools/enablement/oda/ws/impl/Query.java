/*
 *************************************************************************
 * Copyright (c) 2006 <<Your Company Name here>>
 *  
 *************************************************************************
 */

package org.eclipse.datatools.enablement.oda.ws.impl;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
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
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPRequest;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPResponse;
import org.eclipse.datatools.enablement.oda.ws.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.util.Java2SOAPManager;
import org.eclipse.datatools.enablement.oda.ws.util.RawMessageSender;
import org.eclipse.datatools.enablement.oda.ws.util.WSDLAdvisor;
import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;

/**
 * Implementation class of IQuery for an ODA runtime driver. <br>
 * For demo purpose, the auto-generated method stubs have hard-coded
 * implementation that returns a pre-defined set of meta-data and query results.
 * A custom ODA driver is expected to implement own data source specific
 * behavior in its place.
 */
public class Query implements IQuery
{

	private SOAPRequest soapRequest;
	private RawMessageSender rawMessageSender;
	private Java2SOAPManager java2SOAPManager;
	private IQuery xmlQuery;

	private int m_maxRows;
	private boolean isCustom = false;
	private String xmlQueryText = "";
	private String soapEndPoint = "";
	private String operationTrace = "";
	private String wsdlURI = "";

	public Query( RawMessageSender rawMessageSender, Properties connProperties )
	{
		this.rawMessageSender = rawMessageSender;
		this.wsdlURI = ( (Properties) connProperties ).getProperty( Constants.WSDL_URI );
		this.soapEndPoint = ( (Properties) connProperties ).getProperty( Constants.SOAP_ENDPOINT );
		this.m_maxRows = 0;
	}

	public Query( Java2SOAPManager java2SOAPManager )
	{
		this.java2SOAPManager = java2SOAPManager;
		this.isCustom = true;
		this.m_maxRows = 0;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#prepare(java.lang.String)
	 */
	public void prepare( String queryText ) throws OdaException
	{
		if ( isCustom )
			java2SOAPManager.setQueryText( queryText );
		else
			soapRequest = new SOAPRequest( queryText );

	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setAppContext(java.lang.Object)
	 */
	public void setAppContext( Object context ) throws OdaException
	{
		// do nothing; assumes no support for pass-through context
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#close()
	 */
	public void close( ) throws OdaException
	{
		// TODO Auto-generated method stub
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getMetaData()
	 */
	public IResultSetMetaData getMetaData( ) throws OdaException
	{
		if ( xmlQuery == null )
			xmlQuery = initXMLQuery( );

		return xmlQuery.getMetaData( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#executeQuery()
	 */
	public IResultSet executeQuery( ) throws OdaException
	{
		if ( xmlQuery == null )
			xmlQuery = initXMLQuery( );

		return xmlQuery.executeQuery( );
	}

	private IQuery initXMLQuery( ) throws OdaException
	{
		InputStream inputStream = getInputStream( );
		if ( WSUtil.isNull( inputStream ) )
			throw new OdaException( );

		IDriver xmlDriver = new org.eclipse.datatools.enablement.oda.xml.impl.Driver( );
		IConnection conn = xmlDriver.getConnection( null );

		Map map = new HashMap( );
		map.put( Constants.INPUTSTREAM_XML, inputStream );

		conn.setAppContext( map );
		conn.open( new Properties( ) );

		IQuery query = conn.newQuery( null );
		query.setMaxRows( getMaxRows( ) );
		query.prepare( xmlQueryText );

		return query;
	}

	private InputStream getInputStream( ) throws OdaException
	{
		if ( isCustom )
		{
			return byCustom( );
		}
		else
		{
			rawMessageSender.setMessage( soapRequest.toXML( ) );
			if ( WSUtil.isNull( soapEndPoint ) )
				soapEndPoint = WSDLAdvisor.getLocationURI( wsdlURI,
						operationTrace );
			rawMessageSender.setSpec( WSUtil.getNonNullString( soapEndPoint ) );
			rawMessageSender.setSoapAction( WSUtil.getNonNullString( WSDLAdvisor.getSOAPActionURI( wsdlURI,
					operationTrace ) ) );
			SOAPResponse soapResponse = rawMessageSender.getSOAPResponse( );

			if ( WSUtil.isNull( soapResponse ) )
				return null;

			return soapResponse.getInputStream( );
		}
	}

	private InputStream byCustom( ) throws OdaException
	{
		try
		{
			return (InputStream) java2SOAPManager.executeQuery( );
		}
		catch ( IllegalArgumentException e )
		{
			throw new OdaException( e );
		}
		catch ( SecurityException e )
		{
			throw new OdaException( e );
		}
		catch ( IllegalAccessException e )
		{
			throw new OdaException( e );
		}
		catch ( InvocationTargetException e )
		{
			throw new OdaException( e );
		}
		catch ( NoSuchMethodException e )
		{
			throw new OdaException( e );
		}
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setProperty(java.lang.String,
	 *      java.lang.String)
	 */
	public void setProperty( String name, String value ) throws OdaException
	{
		if ( Constants.XML_QUERYTEXT.equals( name ) )
			xmlQueryText = value;
		else if ( Constants.OPERATION_TRACE.equals( name ) )
			operationTrace = value;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setMaxRows(int)
	 */
	public void setMaxRows( int max ) throws OdaException
	{
		m_maxRows = max;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getMaxRows()
	 */
	public int getMaxRows( ) throws OdaException
	{
		return m_maxRows;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#clearInParameters()
	 */
	public void clearInParameters( ) throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to input parameter
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(java.lang.String,
	 *      int)
	 */
	public void setInt( String parameterName, int value ) throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to named input parameter
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(int, int)
	 */
	public void setInt( int parameterId, int value ) throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(java.lang.String,
	 *      double)
	 */
	public void setDouble( String parameterName, double value )
			throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to named input parameter
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(int, double)
	 */
	public void setDouble( int parameterId, double value ) throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(java.lang.String,
	 *      java.math.BigDecimal)
	 */
	public void setBigDecimal( String parameterName, BigDecimal value )
			throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to named input parameter
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(int,
	 *      java.math.BigDecimal)
	 */
	public void setBigDecimal( int parameterId, BigDecimal value )
			throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(java.lang.String,
	 *      java.lang.String)
	 */
	public void setString( String parameterName, String value )
			throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to named input parameter
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(int,
	 *      java.lang.String)
	 */
	public void setString( int parameterId, String value ) throws OdaException
	{
		if ( WSUtil.isNull( soapRequest ) )
			return;

		soapRequest.setParameterValue( parameterId, value );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(java.lang.String,
	 *      java.sql.Date)
	 */
	public void setDate( String parameterName, Date value ) throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to named input parameter
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(int,
	 *      java.sql.Date)
	 */
	public void setDate( int parameterId, Date value ) throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(java.lang.String,
	 *      java.sql.Time)
	 */
	public void setTime( String parameterName, Time value ) throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to named input parameter
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(int,
	 *      java.sql.Time)
	 */
	public void setTime( int parameterId, Time value ) throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to input parameter
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(java.lang.String,
	 *      java.sql.Timestamp)
	 */
	public void setTimestamp( String parameterName, Timestamp value )
			throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to named input parameter
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(int,
	 *      java.sql.Timestamp)
	 */
	public void setTimestamp( int parameterId, Timestamp value )
			throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to input parameter
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#findInParameter(java.lang.String)
	 */
	public int findInParameter( String parameterName ) throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to named input parameter
		return 0;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getParameterMetaData()
	 */
	public IParameterMetaData getParameterMetaData( ) throws OdaException
	{
		if ( WSUtil.isNull( soapRequest ) )
			return null;

		return new ParameterMetaData( soapRequest.getParameters( ) );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setSortSpec(org.eclipse.datatools.connectivity.oda.SortSpec)
	 */
	public void setSortSpec( SortSpec sortBy ) throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to sorting, assumes not supported
		throw new UnsupportedOperationException( );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getSortSpec()
	 */
	public SortSpec getSortSpec( ) throws OdaException
	{
		// TODO Auto-generated method stub
		// only applies to sorting
		return null;
	}

	public void setBoolean( String parameterName, boolean value )
			throws OdaException
	{
		// TODO Auto-generated method stub

	}

	public void setBoolean( int parameterId, boolean value )
			throws OdaException
	{
		setString( parameterId, String.valueOf( value ) );
	}

	public void setNull( String parameterName ) throws OdaException
	{
		// TODO Auto-generated method stub

	}

	public void setNull( int parameterId ) throws OdaException
	{
		// TODO Auto-generated method stub

	}

}
