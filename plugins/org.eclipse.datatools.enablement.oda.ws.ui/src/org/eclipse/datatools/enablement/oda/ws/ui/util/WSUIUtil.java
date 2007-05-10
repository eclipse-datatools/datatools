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

package org.eclipse.datatools.enablement.oda.ws.ui.util;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.enablement.oda.ws.impl.Driver;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPParameter;
import org.eclipse.datatools.enablement.oda.ws.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;

/**
 * 
 */

public class WSUIUtil
{

	public static boolean isNull( Object object )
	{
		return WSUtil.isNull( object );
	}

	public static String getNonNullString( String value )
	{
		return WSUtil.getNonNullString( value );
	}

	public static void checkExisted( DataSetDesign design )
	{
		if ( design.getPublicProperties( ) == null )
		{
			try
			{
				design.setPublicProperties( DesignSessionUtil.createDataSetPublicProperties( design.getOdaExtensionDataSourceId( ),
						design.getOdaExtensionDataSetId( ),
						new Properties( ) ) );
			}
			catch ( OdaException e )
			{
				e.printStackTrace( );
			}
		}
		if ( design.getPrivateProperties( ) == null )
		{
			try
			{
				design.setPrivateProperties( DesignSessionUtil.createDataSetNonPublicProperties( design.getOdaExtensionDataSourceId( ),
						design.getOdaExtensionDataSetId( ),
						getDataSetInitialPrivateProperties( ) ) );
			}
			catch ( OdaException e )
			{
				e.printStackTrace( );
			}
		}
	}

	private static Properties getDataSetInitialPrivateProperties( )
	{
		Properties props = new Properties( );
		props.setProperty( Constants.OPERATION_TRACE, "" );
		props.setProperty( Constants.XML_QUERYTEXT, "" );

		return props;
	}

	public static void savePage( DataSetDesign dataSetDesign )
	{
		// obtain query's current runtime metadata, and maps it to the
		// dataSetDesign
		IConnection conn = null;
		try
		{
			// instantiate your custom ODA runtime driver class
			/*
			 * Note: You may need to manually update your ODA runtime
			 * extension's plug-in manifest to export its package for visibility
			 * here.
			 */
			IDriver driver = new Driver( );

			// obtain and open a live connection
			conn = driver.getConnection( null );
			Properties connProps = DesignUtil.convertDataSourceProperties( dataSetDesign.getDataSourceDesign( ) );
			conn.open( connProps );

			// update the data set design with the
			// query's current runtime metadata
			updateDesign( dataSetDesign, conn, dataSetDesign.getQueryText( ) );
		}
		catch ( OdaException e )
		{
			// not able to get current metadata, reset previous derived metadata
			dataSetDesign.setResultSets( null );
			dataSetDesign.setParameters( null );

			e.printStackTrace( );
		}
		finally
		{
			closeConnection( conn );
		}
	}

	/**
	 * Updates the given dataSetDesign with the queryText and its derived
	 * metadata obtained from the ODA runtime connection.
	 */
	private static void updateDesign( DataSetDesign dataSetDesign,
			IConnection conn, String queryText ) throws OdaException
	{
		IQuery query = conn.newQuery( null );
		query.prepare( queryText );

		// TODO a runtime driver might require a query to first execute before
		// its metadata is available
		// query.setMaxRows( 1 );
		// query.executeQuery( );

		// set soapParameters
		SOAPParameter[] soapParameters = WSConsole.getInstance( )
				.getParameters( );
		if ( !WSUIUtil.isNull( soapParameters ) )
		{
			for ( int i = 0; i < soapParameters.length; i++ )
			{
				query.setString( soapParameters[i].getId( ),
						soapParameters[i].getDefaultValue( ) );
			}
		}

		// set xmlQuery note: it was save to design not to model cuz
		// compatibility issue
		query.setProperty( Constants.XML_QUERYTEXT,
				dataSetDesign.getPrivateProperties( )
						.getProperty( Constants.XML_QUERYTEXT ) );
		// operationTrace is needed here to get soapAction and, if applicable,
		// soapEndPoint
		query.setProperty( Constants.OPERATION_TRACE, WSConsole.getInstance( )
				.getPropertyValue( Constants.OPERATION_TRACE ) );

		try
		{
			IResultSetMetaData md = query.getMetaData( );
			updateResultSetDesign( md, dataSetDesign );
		}
		catch ( OdaException e )
		{
			// no result set definition available, reset previous derived
			// metadata
			dataSetDesign.setResultSets( null );
		}

		// proceed to get parameter design definition
		try
		{
			IParameterMetaData paramMd = query.getParameterMetaData( );
			updateParameterDesign( paramMd, dataSetDesign );
		}
		catch ( OdaException ex )
		{
			// no parameter definition available, reset previous derived
			// metadata
			dataSetDesign.setParameters( null );
		}

		/*
		 * See DesignSessionUtil for more convenience methods to define a data
		 * set design instance.
		 */
	}

	/**
	 * Updates the specified data set design's result set definition based on
	 * the specified runtime metadata.
	 * 
	 * @param md
	 *            runtime result set metadata instance
	 * @param dataSetDesign
	 *            data set design instance to update
	 * @throws OdaException
	 */
	private static void updateResultSetDesign( IResultSetMetaData md,
			DataSetDesign dataSetDesign ) throws OdaException
	{
		ResultSetColumns columns = DesignSessionUtil.toResultSetColumnsDesign( md );

		ResultSetDefinition resultSetDefn = DesignFactory.eINSTANCE.createResultSetDefinition( );
		// resultSetDefn.setName( value ); // result set name
		resultSetDefn.setResultSetColumns( columns );

		// no exception in conversion; go ahead and assign to specified
		// dataSetDesign
		dataSetDesign.setPrimaryResultSet( resultSetDefn );
		dataSetDesign.getResultSets( ).setDerivedMetaData( true );
	}

	/**
	 * Updates the specified data set design's parameter definition based on the
	 * specified runtime metadata.
	 * 
	 * @param paramMd
	 *            runtime parameter metadata instance
	 * @param dataSetDesign
	 *            data set design instance to update
	 * @throws OdaException
	 */
	private static void updateParameterDesign( IParameterMetaData paramMd,
			DataSetDesign dataSetDesign ) throws OdaException
	{
		DataSetParameters paramDesign = DesignSessionUtil.toDataSetParametersDesign( paramMd,
				DesignSessionUtil.toParameterModeDesign( IParameterMetaData.parameterModeIn ) );

		// no exception in conversion; go ahead and assign to specified
		// dataSetDesign
		paramDesign.setDerivedMetaData( true );
		dataSetDesign.setParameters( paramDesign );

		// TODO replace below with data source specific implementation;
		// hard-coded parameter's default value for demo purpose
		if ( paramDesign.getParameterDefinitions( ).size( ) > 0 )
		{
			// ParameterDefinition paramDef = (ParameterDefinition)
			// paramDesign.getParameterDefinitions( )
			// .get( 0 );
			// if ( paramDef != null )
			// paramDef.setDefaultScalarValue( "dummy default value" );
			WSConsole.getInstance( )
					.merge2ParameterDefinitions( paramDesign.getParameterDefinitions( ) );
		}

	}

	/**
	 * Attempts to close given ODA connection.
	 */
	private static void closeConnection( IConnection conn )
	{
		try
		{
			if ( conn != null && conn.isOpen( ) )
				conn.close( );
		}
		catch ( OdaException e )
		{
			// ignore
			e.printStackTrace( );
		}
	}

}
