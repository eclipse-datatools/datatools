/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.enablement.oda.xml.impl.Driver;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;

/**
 * Populate the resultset metadata and parameter meta data in dataset design
 * when they are available
 * 
 */
public class DataSetDesignPopulator
{
	
	public static void populateResultSet( DataSetDesign dataSetDesign )
	{
		IConnection conn = null;
		try
		{
			IDriver jdbcDriver = new Driver( );
			conn = jdbcDriver.getConnection( null );

			Properties properties = new Properties( );
			String xmlFile = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XML_FILE );
			if ( xmlFile == null || xmlFile.trim( ).length( ) == 0 )
			{
				xmlFile = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
			}

			properties.setProperty( Constants.CONST_PROP_FILELIST,
					xmlFile == null ? "" : xmlFile );

			conn.open( properties );

			IQuery query = conn.newQuery( null );

			query.setMaxRows( 1 );
			query.prepare( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );

			IResultSetMetaData metadata = query.getMetaData( );
			setResultSetMetaData( dataSetDesign, metadata );
		}
		catch ( OdaException e )
		{
			// no result set definition available, reset in dataSetDesign
			dataSetDesign.setResultSets( null );
		}
		finally
		{
			closeConnection( conn );
		}

	}

	/**
	 * close the connection
	 * 
	 * @param conn
	 */
	private static void closeConnection( IConnection conn )
	{
		try
		{
			if ( conn != null )
				conn.close( );
		}
		catch ( OdaException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace( );
		}

	}

	/**
	 * set resultset meta data
	 * 
	 * @param dataSetDesign
	 * @param md
	 * @throws OdaException
	 */
	private static void setResultSetMetaData( DataSetDesign dataSetDesign,
			IResultSetMetaData md ) throws OdaException
	{
		ResultSetColumns columns = DesignSessionUtil.toResultSetColumnsDesign( md );

		ResultSetDefinition resultSetDefn = DesignFactory.eINSTANCE.createResultSetDefinition( );
		// jdbc does not support result set name
		resultSetDefn.setResultSetColumns( columns );

		// no exception; go ahead and assign to specified dataSetDesign
		dataSetDesign.setPrimaryResultSet( resultSetDefn );
		dataSetDesign.getResultSets( ).setDerivedMetaData( true );
	}
}
