/*
 ******************************************************************************
 * Copyright (c) 2007, 2009 Actuate Corporation.
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.ws.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.util.Java2SOAPManager;
import org.eclipse.datatools.enablement.oda.ws.util.RawMessageSender;
import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;

import com.ibm.icu.util.ULocale;

/**
 * Implementation class of IConnection for an ODA runtime driver.
 */
public class Connection implements IConnection
{

	private boolean m_isOpen = false;
	private RawMessageSender rawMessageSender;
	private Properties connProperties;
	private Java2SOAPManager java2SOAPManager;
	private boolean isCustom = false;
	private Map appContext = null;

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#open(java.util.Properties)
	 */
	public void open( Properties connProperties ) throws OdaException
	{
		this.connProperties = connProperties;
		isCustom = !WSUtil.isNull( connProperties.getProperty( Constants.CUSTOM_CONNECTION_CLASS ) );
		if ( isCustom )
		{
			java2SOAPManager = new Java2SOAPManager( );
			java2SOAPManager.setConnectionProperties( connProperties );
			java2SOAPManager.setAppConext( this.appContext );
			String driverPath= connProperties.getProperty( Constants.CUSTOM_DRIVER_CLASS_PATH );
			try
			{
				java2SOAPManager.newQuery( connProperties.getProperty( Constants.CUSTOM_CONNECTION_CLASS ) , driverPath );
			}
			catch ( Exception e )
			{
				throw new OdaException( e );
			}		
		}
		else
		{
			rawMessageSender = new RawMessageSender( );
		}

		try
		{
			ping( connProperties );
		}
		catch ( Exception e )
		{
			throw new OdaException( );
		}
		
		m_isOpen = true;
	}
	
	private void ping( Properties connProperties ) throws OdaException,
			MalformedURLException, IOException
	{
		if ( WSUtil.isNull( connProperties.getProperty( Constants.WSDL_URI ) )
				&& WSUtil.isNull( connProperties.getProperty( Constants.SOAP_ENDPOINT ) )
				&& WSUtil.isNull( connProperties.getProperty( Constants.CUSTOM_CONNECTION_CLASS ) ) )
			throw new OdaException( );

		pingURL( connProperties.getProperty( Constants.WSDL_URI ) );
	}

    private void pingURL( String spec ) throws MalformedURLException,
            IOException
    {
        if ( !WSUtil.isNull( spec ) )
        {
            if ( !new File( spec ).exists( ) )
            {
                InputStream stream = new URL( spec ).openStream( );
                if (stream != null) {
                    stream.close( );
                }
            }
        }
    }

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#setAppContext(java.lang.Object)
	 */
	public void setAppContext( Object context ) throws OdaException
	{
		if ( context instanceof Map )
			this.appContext = (Map) context;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#close()
	 */
    public void close( ) throws OdaException
    {
        m_isOpen = false;

        if ( isCustom )
            java2SOAPManager = null;
        else
            rawMessageSender = null;
    }

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#isOpen()
	 */
	public boolean isOpen( ) throws OdaException
	{
		return m_isOpen;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#getMetaData(java.lang.String)
	 */
	public IDataSetMetaData getMetaData( String dataSetType )
			throws OdaException
	{
		// assumes that this driver supports only one type of data set,
		// ignores the specified dataSetType
		return new DataSetMetaData( this );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#newQuery(java.lang.String)
	 */
	public IQuery newQuery( String dataSetType ) throws OdaException
	{
		// assumes that this driver supports only one type of data set,
		// ignores the specified dataSetType
		if ( isCustom )
		{
			return byCustom( );
		}

		return new Query( rawMessageSender, connProperties );
	}

	private IQuery byCustom( ) throws OdaException
	{
		try
		{
			java2SOAPManager.newQuery( connProperties.getProperty( Constants.CUSTOM_CONNECTION_CLASS ),connProperties.getProperty( Constants.CUSTOM_DRIVER_CLASS_PATH ) );
		}
		catch ( Exception e )
		{
			throw new OdaException( e );
		}

		return new Query( java2SOAPManager );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#getMaxQueries()
	 */
	public int getMaxQueries( ) throws OdaException
	{
		return 0; // no limit
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#commit()
	 */
	public void commit( ) throws OdaException
	{
		// do nothing; assumes no transaction support needed
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#rollback()
	 */
	public void rollback( ) throws OdaException
	{
		// do nothing; assumes no transaction support needed
	}

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#setLocale(com.ibm.icu.util.ULocale)
     */
    public void setLocale( ULocale locale ) throws OdaException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException( );
    }

}
