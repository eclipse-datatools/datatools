/*******************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.impl;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;
import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.util.IXMLSource;
import org.eclipse.datatools.enablement.oda.xml.util.XMLSourceFromInputStream;
import org.eclipse.datatools.enablement.oda.xml.util.XMLSourceFromPath;

import com.ibm.icu.util.ULocale;

/**
 * This class is used to build an XML data source connection. 
 */
public class Connection implements IConnection
{
    private static final String TRUE_LITERAL = "true";	//$NON-NLS-1$

    private IXMLSource xmlSource;

	//The boolean indicate whether the connection is open.
	private boolean isOpen;
	
	private Map<String, Object> appContext;
	
	Properties connProperties;
	
	private Object ri;

	/*
	 *if a valid XML schema URL provided
     *	succeed in open
     *else if an InputStream instance provided from appContext
     *	succeed in open
	 *else if an valid XML source URL provided
     *	succeed in open
	 *else 
     "	throws exception
	 */
	public void open( Properties connProperties )
			throws org.eclipse.datatools.connectivity.oda.OdaException
	{
		if( isOpen == true )
		{
			return;
		}
		this.connProperties = connProperties;
		
		isOpen = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#close()
	 */
	public void close( )
			throws org.eclipse.datatools.connectivity.oda.OdaException
	{
		isOpen = false;
		if ( xmlSource != null )
		{
			xmlSource.release( );
		}
		this.appContext = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#isOpen()
	 */
	public boolean isOpen( )
			throws OdaException
	{
		return isOpen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#setAppContext(java.lang.Object)
	 */
	public void setAppContext( Object context ) throws OdaException
	{
		if ( !( context instanceof Map ) )
			throw new OdaException( Messages.getString( "Connection.InvalidAppContext" ) );//$NON-NLS-1$
		this.appContext = (Map) context;

		// The following code are for backward compatibility only.Once we
		// nolonger
		// support original BIRT ODA XML Driver this code block should be
		// removed.
		// ///////////////////////////////////////////////////////////////////
		String legacyInputStreamKey = "org.eclipse.birt.report.data.oda.xml.inputStream"; //$NON-NLS-1$
		if ( this.appContext.get( legacyInputStreamKey ) != null )
		{
			this.appContext.put( Constants.APPCONTEXT_INPUTSTREAM,
					this.appContext.get( legacyInputStreamKey ) );
			this.appContext.remove( legacyInputStreamKey );
		}

		String legacyCloseInputStreamKey = "org.eclipse.birt.report.data.oda.xml.closeInputStream"; //$NON-NLS-1$
		if ( this.appContext.get( legacyCloseInputStreamKey ) != null )
		{
			this.appContext.put( Constants.APPCONTEXT_CLOSEINPUTSTREAM,
					this.appContext.get( legacyCloseInputStreamKey ) );
			this.appContext.remove( legacyCloseInputStreamKey );
		}
		/////////////////////////////////////////////////////////////////////
		
		ri = appContext.get( ResourceIdentifiers.ODA_APP_CONTEXT_KEY_CONSUMER_RESOURCE_IDS );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#getMetaData(java.lang.String)
	 */
	public IDataSetMetaData getMetaData( String dataSetType )
			throws OdaException
	{
		return new DataSetMetaData( this );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#newQuery(java.lang.String)
	 */
	public IQuery newQuery( String dataSetType ) throws OdaException
	{
		
		return new Query( this );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#getMaxQueries()
	 */
	public int getMaxQueries( ) throws OdaException
	{
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#commit()
	 */
	public void commit( ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#rollback()
	 */
	public void rollback( ) throws OdaException
	{
		throw new UnsupportedOperationException( );
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#setLocale(com.ibm.icu.util.ULocale)
     */
    public void setLocale( ULocale locale ) throws OdaException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException( );
    }

    public IXMLSource getXMLSource( ) throws OdaException
	{
		if ( xmlSource == null )
		{
			initXMLSource( );
		}
		return xmlSource;
	}

	private void initXMLSource( ) throws OdaException
	{
		String encoding = connProperties == null ? null :(String) connProperties.get( Constants.CONST_PROP_ENCODINGLIST);
		String file = connProperties == null ? null :(String) connProperties.get( Constants.CONST_PROP_FILELIST );
		if ( appContext != null
				&& appContext.get( Constants.APPCONTEXT_INPUTSTREAM ) instanceof InputStream )
		{
			boolean closeOriginalInputStream = false;
			Object closeInputStream = appContext.get( Constants.APPCONTEXT_CLOSEINPUTSTREAM );
			if( TRUE_LITERAL.equalsIgnoreCase( closeInputStream == null ? null : closeInputStream.toString( ) ) )
			{
				closeOriginalInputStream = true;
			}
			xmlSource = new XMLSourceFromInputStream( 
					(InputStream) appContext.get( Constants.APPCONTEXT_INPUTSTREAM ), 
					encoding,
					closeOriginalInputStream );
		}
		else
		{
			xmlSource = new XMLSourceFromPath( file, encoding, ri );
		}
	}
}
