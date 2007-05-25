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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.Property;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPParameter;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPRequest;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPResponse;
import org.eclipse.datatools.enablement.oda.ws.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ws.util.Java2SOAPManager;
import org.eclipse.datatools.enablement.oda.ws.util.RawMessageSender;
import org.eclipse.datatools.enablement.oda.ws.util.WSDLAdvisor;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.datatools.enablement.oda.xml.ui.wizards.XMLInformationHolder;
import org.eclipse.emf.common.util.EList;

/**
 * A utility class to handle ws design-time model which involves some properties
 * and util methods.
 * 
 * standard case-scenario: initial: design->model->page
 * intermedial:canLeave/getNextPage(refresh) page->model final:model->design
 */

public class WSConsole
{

	private static String XML_TEMP_FILE = "xmlTempFile";// //$NON-NLS-1$
	private static WSConsole instance;

	private Properties props;
	private SOAPParameter[] parameters;
	private boolean isSessionOK = false;

	/**
	 * 
	 * @return
	 */
	public static synchronized WSConsole getInstance( )
	{
		if ( instance == null )
			instance = new WSConsole( );

		return instance;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getPropertyValue( String key )
	{
		if ( props == null )
			return null;

		return props.getProperty( key );
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setPropertyValue( String key, String value )
	{
		if ( props == null )
			props = new Properties( );;

		if ( value != null )
			props.setProperty( key, value );
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getXMLPropertyValue( String key )
	{
		return XMLInformationHolder.getPropertyValue( key );
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setXMLPropertyValue( String key, String value )
	{
		XMLInformationHolder.setPropertyValue( key, value );
	}

	/**
	 * Populates all the required properties available in dataSetDesign
	 * 
	 * @param dataSetDesign
	 */
	public void start( DataSetDesign dataSetDesign )
	{
		if ( dataSetDesign == null )
			return;

		startWS( dataSetDesign );
		startXML( dataSetDesign );
		isSessionOK = true;
	}

	private void startWS( DataSetDesign dataSetDesign )
	{
		String queryText = dataSetDesign.getQueryText( );
		if ( queryText != null && queryText.trim( ).length( ) > 0 )
		{
			setPropertyValue( Constants.WS_QUERYTEXT, queryText );
		}
		DataSetParameters params = dataSetDesign.getParameters( );
		if ( params != null )
		{
			initSOAPParameters( params );
		}
		if ( dataSetDesign.getPublicProperties( ) != null )
		{
			Property xmlFileURI = dataSetDesign.getPublicProperties( )
					.findProperty( Constants.XML_FILE_URI );
			setPropertyValue( Constants.XML_FILE_URI, xmlFileURI == null
					? WSUIUtil.EMPTY_STRING : xmlFileURI.getValue( ) );

			Property xsdFileURI = dataSetDesign.getPublicProperties( )
					.findProperty( Constants.XSD_FILE_URI );
			setPropertyValue( Constants.XSD_FILE_URI, xmlFileURI == null
					? WSUIUtil.EMPTY_STRING : xsdFileURI.getValue( ) );
		}
		if ( dataSetDesign.getPrivateProperties( ) != null )
		{
			Property operationTrace = dataSetDesign.getPrivateProperties( )
					.findProperty( Constants.OPERATION_TRACE );
			setPropertyValue( Constants.OPERATION_TRACE, operationTrace == null
					? WSUIUtil.EMPTY_STRING : operationTrace.getValue( ) );

			Property xmlQueryText = dataSetDesign.getPrivateProperties( )
					.findProperty( Constants.XML_QUERYTEXT );
			setPropertyValue( Constants.XML_QUERYTEXT, xmlQueryText == null
					? WSUIUtil.EMPTY_STRING : xmlQueryText.getValue( ) );
		}
		if ( dataSetDesign.getDataSourceDesign( ) != null )
		{
			DataSourceDesign dataSourceDesign = dataSetDesign.getDataSourceDesign( );

			if ( dataSourceDesign.getPublicProperties( ) != null )
			{
				Property soapEndPoint = dataSourceDesign.getPublicProperties( )
						.findProperty( Constants.SOAP_ENDPOINT );
				setPropertyValue( Constants.SOAP_ENDPOINT, soapEndPoint == null
						? WSUIUtil.EMPTY_STRING : soapEndPoint.getValue( ) );

				Property customConnectionClass = dataSourceDesign.getPublicProperties( )
						.findProperty( Constants.CUSTOM_CONNECTION_CLASS );
				setPropertyValue( Constants.CUSTOM_CONNECTION_CLASS,
						customConnectionClass == null ? WSUIUtil.EMPTY_STRING
								: customConnectionClass.getValue( ) );
			}
			if ( dataSourceDesign.getPrivateProperties( ) != null )
			{
				Property wsdlURI = dataSourceDesign.getPrivateProperties( )
						.findProperty( Constants.WSDL_URI );
				setPropertyValue( Constants.WSDL_URI, wsdlURI == null
						? WSUIUtil.EMPTY_STRING : wsdlURI.getValue( ) );
			}
		}
	}

	private void startXML( DataSetDesign dataSetDesign )
	{
		if ( dataSetDesign.getPrivateProperties( ) != null )
		{
			String queryText = dataSetDesign.getPrivateProperties( )
					.findProperty( Constants.XML_QUERYTEXT )
					.getValue( );

			if ( queryText != null && queryText.trim( ).length( ) > 0 )
			{
				setXMLPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
						queryText );
				String tableName = XMLRelationInfoUtil.getTableName( queryText );
				setXMLPropertyValue( Constants.CONST_PROP_TABLE_NAME, tableName );

				String xpath = XMLRelationInfoUtil.getXPathExpression( queryText,
						tableName );
				setXMLPropertyValue( Constants.CONST_PROP_XPATH, xpath );
			}
		}
		if ( dataSetDesign.getPublicProperties( ) != null )
		{
			Property xmlFile = dataSetDesign.getPublicProperties( )
					.findProperty( Constants.XML_FILE_URI );
			setXMLPropertyValue( Constants.CONST_PROP_FILELIST, xmlFile == null
					? WSUIUtil.EMPTY_STRING : xmlFile.getValue( ) );

			Property schema = dataSetDesign.getPublicProperties( )
					.findProperty( Constants.XSD_FILE_URI );
			setXMLPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST,
					schema == null ? WSUIUtil.EMPTY_STRING : schema.getValue( ) );
		}

		setXMLPropertyValue( Constants.CONST_PROP_MAX_ROW, "-1" ); //$NON-NLS-1$
	}

	/**
	 * 
	 */
	public void end( )
	{
		instance = null;
	}

	/**
	 * 
	 */
	public void terminateSession( )
	{
		isSessionOK = false;
		props = null;
		parameters = null;
		XMLInformationHolder.destory( );
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSessionOK( )
	{
		return isSessionOK;
	}

	/**
	 * 
	 * @return
	 */
	public SOAPParameter[] getParameters( )
	{
		return parameters;
	}

	/**
	 * 
	 * @param soapParameters
	 */
	public void setParameters( SOAPParameter[] soapParameters )
	{
		this.parameters = soapParameters;
	}

	/**
	 * 
	 * @return
	 */
	public String getTemplate( )
	{
		return WSDLAdvisor.getSOAPRequestTemplate( getPropertyValue( Constants.WSDL_URI ),
				getPropertyValue( Constants.OPERATION_TRACE ) );
	}

	/**
	 * 
	 * @return
	 * @throws OdaException
	 */
	public void updateXMLFileURI( ) throws OdaException
	{
		String xmlFileURI = getXMLPropertyValue( Constants.CONST_PROP_FILELIST );
		if ( !WSUIUtil.isNull( xmlFileURI ) )
			return;

		String xmlTempFileURI = getPropertyValue( Constants.XML_TEMP_FILE_URI );
		if ( WSUIUtil.isNull( xmlTempFileURI ) )
		{
			// there is no xml temp file, create one
			createXMLTempFileURI( );
			xmlTempFileURI = getPropertyValue( Constants.XML_TEMP_FILE_URI );
		}

		xmlFileURI = xmlTempFileURI;
		setXMLPropertyValue( Constants.CONST_PROP_FILELIST,
				WSUIUtil.getNonNullString( xmlFileURI ) );
	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void createXMLTempFileURI( ) throws OdaException
	{
		File file = generateTempXMLFile( );
		String xmlTempFileURI = file == null ? WSUIUtil.EMPTY_STRING
				: file.getAbsolutePath( );

		setPropertyValue( Constants.XML_TEMP_FILE_URI, xmlTempFileURI );
	}

	private File generateTempXMLFile( ) throws OdaException
	{
		InputStream in = getInputStream( );
		if ( WSUIUtil.isNull( in ) )
			return null;

		File file;
		try
		{
			file = File.createTempFile( XML_TEMP_FILE, null );
			file.deleteOnExit( );
			FileOutputStream fos = new FileOutputStream( file );
			BufferedInputStream bis = new BufferedInputStream( in );
			int abyte;

			while ( ( abyte = bis.read( ) ) != -1 )
			{
				fos.write( abyte );
			}
			fos.close( );
			in.close( );
		}
		catch ( IOException e )
		{
			return null;
		}

		return file;
	}

	private InputStream getInputStream( ) throws OdaException
	{
		if ( !WSUIUtil.isNull( getPropertyValue( Constants.CUSTOM_CONNECTION_CLASS ) ) )
		{
			return byCustom( );
		}
		else
		{
			SOAPResponse soapResponse = connectNow( );
			if ( soapResponse == null )
				throw new OdaException( Messages.getString( "wsConsole.message.error.cantRetrieveSOAPResponse" ) ); //$NON-NLS-1$

			return soapResponse.getInputStream( );
		}
	}

	private InputStream byCustom( ) throws OdaException
	{
		Java2SOAPManager j2s = new Java2SOAPManager( );

		j2s.setConnectionProperties( retrieveConnProperties( ) );
		j2s.setQueryText( getPropertyValue( Constants.WS_QUERYTEXT ) );
		if ( !WSUIUtil.isNull( parameters ) )
		{
			Map parameterMap = new HashMap( );
			for ( int i = 0; i < parameters.length; i++ )
			{
				parameterMap.put( parameters[i].getName( ),
						parameters[i].getDefaultValue( ) );
			}
			j2s.setParameterValues( parameterMap );
		}

		try
		{
			j2s.newQuery( getPropertyValue( Constants.CUSTOM_CONNECTION_CLASS ) );
			return (InputStream) j2s.executeQuery( );
		}
		catch ( Exception e )
		{
			throw new OdaException( e );
		}
	}

	private Properties retrieveConnProperties( )
	{
		Properties p = new Properties( );
		p.put( Constants.SOAP_ENDPOINT,
				WSUIUtil.getNonNullString( getPropertyValue( Constants.SOAP_ENDPOINT ) ) );
		p.put( Constants.CUSTOM_CONNECTION_CLASS,
				WSUIUtil.getNonNullString( getPropertyValue( Constants.CUSTOM_CONNECTION_CLASS ) ) );
		p.put( Constants.CONNECTION_TIMEOUT,
				WSUIUtil.getNonNullString( getPropertyValue( Constants.CONNECTION_TIMEOUT ) ) );

		return p;
	}

	private SOAPResponse connectNow( )
	{
		String spec = getPropertyValue( Constants.SOAP_ENDPOINT );
		// if soapEndPoint is not explicitly specified by the user, try locate
		// it from the wsdl file
		if ( WSUIUtil.isNull( spec ) )
			spec = WSDLAdvisor.getLocationURI( getPropertyValue( Constants.WSDL_URI ),
					getPropertyValue( Constants.OPERATION_TRACE ) );
		String query = getPropertyValue( Constants.WS_QUERYTEXT );
		if ( WSUIUtil.isNull( spec ) || WSUIUtil.isNull( query ) )
			return null;

		String soapAction = WSUIUtil.getNonNullString( WSDLAdvisor.getSOAPActionURI( getPropertyValue( Constants.WSDL_URI ),
				getPropertyValue( Constants.OPERATION_TRACE ) ) );

		SOAPRequest soapRequest = new SOAPRequest( );
		soapRequest.setQueryText( query );
		soapRequest.generateTemplate( );
		soapRequest.setParameters( parameters );
		String message = soapRequest.toXML( );

		RawMessageSender rawMessageSender = new RawMessageSender( spec,
				message,
				soapAction );
		SOAPResponse soapResponse = rawMessageSender.getSOAPResponse( );

		return soapResponse;
	}

	/**
	 * 
	 * @param dataSetParams
	 */
	public void initSOAPParameters( DataSetParameters dataSetParams )
	{
		EList parameterDefinitions = dataSetParams.getParameterDefinitions( );
		if ( WSUIUtil.isNull( parameterDefinitions )
				|| parameterDefinitions.size( ) == 0 )
			return;

		parameters = new SOAPParameter[parameterDefinitions.size( )];
		for ( int i = 0; i < parameterDefinitions.size( ); i++ )
		{
			// apply name & defaultValue
			ParameterDefinition paramDef = (ParameterDefinition) parameterDefinitions.get( i );
			parameters[i] = new SOAPParameter( i + 1, paramDef.getAttributes( )
					.getName( ), paramDef.getDefaultScalarValue( ) );
		}

	}

	/**
	 * 
	 * @param parameterDefinitions
	 */
	public void merge2ParameterDefinitions( EList parameterDefinitions )
	{
		if ( !canMerge( parameters, parameterDefinitions ) )
			return;

		for ( int i = 0; i < parameters.length; i++ )
		{
			// apply name & defaultValue
			ParameterDefinition paramDef = (ParameterDefinition) parameterDefinitions.get( i );
			paramDef.getAttributes( ).setName( parameters[i].getName( ) );
			paramDef.setDefaultScalarValue( parameters[i].getDefaultValue( ) );
		}
	}

	private boolean canMerge( SOAPParameter[] soapParameters,
			EList parameterDefinitions )
	{
		if ( soapParameters == null || parameterDefinitions == null )
			return false;

		if ( soapParameters.length != parameterDefinitions.size( ) )
			return false;

		return true;
	}

}
