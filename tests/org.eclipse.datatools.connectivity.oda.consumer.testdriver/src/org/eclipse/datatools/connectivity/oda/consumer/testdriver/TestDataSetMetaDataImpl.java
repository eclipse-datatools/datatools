/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.testdriver;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Default implementation of IDataSetMetaData 
 * for a simple ODA runtime driver.
 */
public class TestDataSetMetaDataImpl implements IDataSetMetaData
{
	private IConnection m_connection;
	private boolean m_supportsMultipleResultSets;
	private boolean m_supportsMultipleOpenResults;
	private boolean m_supportsNamedResultSets;
	private boolean m_supportsNamedParameters;
	private boolean m_supportsInParameters;
	private boolean m_supportsOutParameters;
	
	private static final String MULTIPLE_RESULT_SETS_DATA_SET = "Multiple Result Sets Data Set";
	private static final String NAMED_RESULT_SETS_DATA_SET = "Named Result Sets Data Set";
	
	TestDataSetMetaDataImpl( IConnection connection, String dataSetType )
	{
		m_connection = connection;
		
		m_supportsMultipleResultSets = ( dataSetType != null &&
				dataSetType.equals( MULTIPLE_RESULT_SETS_DATA_SET ) );
		m_supportsMultipleOpenResults = false;
		m_supportsNamedResultSets = ( dataSetType != null && 
				dataSetType.equals( NAMED_RESULT_SETS_DATA_SET ) );
		m_supportsNamedParameters = true;
		m_supportsInParameters = true;
		m_supportsOutParameters = true;
	}
	
	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getConnection()
	 */
	public IConnection getConnection() throws OdaException
	{
		return m_connection;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceObjects(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public IResultSet getDataSourceObjects( String catalog, String schema, String object, String version ) throws OdaException
	{
	    throw new UnsupportedOperationException();
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceMajorVersion()
	 */
	public int getDataSourceMajorVersion() throws OdaException
	{
		return 2;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceMinorVersion()
	 */
	public int getDataSourceMinorVersion() throws OdaException
	{
		return 3;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceProductName()
	 */
	public String getDataSourceProductName() throws OdaException
	{
		return "Simple Data Source";
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceProductVersion()
	 */
	public String getDataSourceProductVersion() throws OdaException
	{
		return Integer.toString( getDataSourceMajorVersion() ) + "." + 
			   Integer.toString( getDataSourceMinorVersion() );
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getSQLStateType()
	 */
	public int getSQLStateType() throws OdaException
	{
		return IDataSetMetaData.sqlStateSQL99;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsMultipleResultSets()
	 */
	public boolean supportsMultipleResultSets() throws OdaException
	{
		return m_supportsMultipleResultSets;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsMultipleOpenResults()
	 */
	public boolean supportsMultipleOpenResults() throws OdaException
	{
		return m_supportsMultipleOpenResults;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsNamedResultSets()
	 */
	public boolean supportsNamedResultSets() throws OdaException
	{
		return m_supportsNamedResultSets;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsNamedParameters()
	 */
	public boolean supportsNamedParameters() throws OdaException
	{
		return m_supportsNamedParameters;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsInParameters()
	 */
	public boolean supportsInParameters() throws OdaException
	{
		return m_supportsInParameters;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsOutParameters()
	 */
	public boolean supportsOutParameters() throws OdaException
	{
		return m_supportsOutParameters;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getSortMode()
	 */
	public int getSortMode()
	{
		return IDataSetMetaData.sortModeNone;
	}
}
