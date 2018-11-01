/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.impl.Driver;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.ResourceIdentifiersUtil;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.ParameterMode;
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
	
	private static final String EMPTY_STRING = "";     //$NON-NLS-1$

    public static void populateMetaData( DataSetDesign dataSetDesign )
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
					xmlFile == null ? EMPTY_STRING : xmlFile );
			
			String xmlEncoding = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST );
			properties.setProperty( Constants.CONST_PROP_ENCODINGLIST,
					xmlEncoding == null ? EMPTY_STRING : xmlEncoding );
			
			conn.setAppContext( DesignSessionUtil.createResourceIdentifiersContext( dataSetDesign.getDataSourceDesign( )
					.getHostResourceIdentifiers( ) ) );
			
			conn.open( properties );

			IQuery query = conn.newQuery( null );

			query.setMaxRows( 1 );
			query.prepare( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );

			IResultSetMetaData metadata = query.getMetaData( );
			setParameterMetaData( dataSetDesign, query.getParameterMetaData( ) );
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

		if ( columns != null )
		{
			ResultSetDefinition resultSetDefn = DesignFactory.eINSTANCE.createResultSetDefinition( );
			resultSetDefn.setResultSetColumns( columns );

			// no exception; go ahead and assign to specified dataSetDesign
			dataSetDesign.setPrimaryResultSet( resultSetDefn );
			dataSetDesign.getResultSets( ).setDerivedMetaData( true );
		}
		else
		{
			dataSetDesign.setResultSets( null );
		}
	}
	
	/**
	 * Set parameter metadata in dataset design
	 * 
	 * @param design
	 * @param query
	 */
	private static void setParameterMetaData( DataSetDesign dataSetDesign,
			IParameterMetaData paramMeta )
	{
		try
		{
			// set parameter metadata
			mergeParameterMetaData( dataSetDesign, paramMeta );
		}
		catch ( OdaException e )
		{
			// do nothing, to keep the parameter definition in dataset design
			// dataSetDesign.setParameters( null );
		}
	}
    /**
	 * merge paramter meta data between dataParameter and datasetDesign's
	 * parameter.
	 * 
	 * @param dataSetDesign
	 * @param md
	 * @throws OdaException
	 */
	private static void mergeParameterMetaData( DataSetDesign dataSetDesign,
			IParameterMetaData md ) throws OdaException
	{
		if ( md == null || dataSetDesign == null )
			return;
		DataSetParameters dataSetParameter = DesignSessionUtil.toDataSetParametersDesign( md,
				ParameterMode.IN_LITERAL );

		dataSetDesign.setParameters( dataSetParameter );
	}
}
