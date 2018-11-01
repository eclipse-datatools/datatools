/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.ws.ui.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
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
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPParameter;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPRequest;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPResponse;
import org.eclipse.datatools.enablement.oda.ws.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ws.util.Java2SOAPManager;
import org.eclipse.datatools.enablement.oda.ws.util.PropertyValueUtil;
import org.eclipse.datatools.enablement.oda.ws.util.RawMessageSender;
import org.eclipse.datatools.enablement.oda.ws.util.WSDLAdvisor;
import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;
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
	private static final int BACKWARD = 0;
	private static final int FORWARD = 1;
	
	private boolean needsRefreshTempFile = false;

	private File templateFile, sampleXMLFile;

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
		needsRefreshTempFile = false;
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
			String value = dataSetDesign.getPublicProperties( )
					.getProperty( Constants.XML_FILE_URI );
			String xmlFileURI = PropertyValueUtil.getQualifiedValueForDataSet( value,
					Constants.XML_FILE_URI,
					dataSetDesign.getOdaExtensionDataSetId( ) );
			setPropertyValue( Constants.XML_FILE_URI, xmlFileURI );

			value = dataSetDesign.getPublicProperties( )
					.getProperty( Constants.XSD_FILE_URI );
			String xsdFileURI = PropertyValueUtil.getQualifiedValueForDataSet( value,
					Constants.XSD_FILE_URI,
					dataSetDesign.getOdaExtensionDataSetId( ) );
			setPropertyValue( Constants.XSD_FILE_URI, xsdFileURI );
		}
		if ( dataSetDesign.getPrivateProperties( ) != null )
		{
			String value = dataSetDesign.getPrivateProperties().getProperty(
					Constants.XML_FILE_URI);
			String xmlFileURI = PropertyValueUtil.getQualifiedValueForDataSet(
					value, Constants.XML_FILE_URI, dataSetDesign
							.getOdaExtensionDataSetId());
			if( xmlFileURI != null )
				setPropertyValue(Constants.XML_FILE_URI, xmlFileURI);

			value = dataSetDesign.getPrivateProperties().getProperty(
					Constants.XSD_FILE_URI);
			String xsdFileURI = PropertyValueUtil.getQualifiedValueForDataSet(
					value, Constants.XSD_FILE_URI, dataSetDesign
							.getOdaExtensionDataSetId());
			if( xsdFileURI != null )
				setPropertyValue(Constants.XSD_FILE_URI, xsdFileURI);
	
			value = dataSetDesign.getPrivateProperties( )
					.getProperty( Constants.OPERATION_TRACE );
			String operationTrace = PropertyValueUtil.getQualifiedValueForDataSet( value,
					Constants.OPERATION_TRACE,
					dataSetDesign.getOdaExtensionDataSetId( ) );
			setPropertyValue( Constants.OPERATION_TRACE, operationTrace );

			value = dataSetDesign.getPrivateProperties( )
					.getProperty( Constants.XML_QUERYTEXT );
			String xmlQueryText = PropertyValueUtil.getQualifiedValueForDataSet( value,
					Constants.XML_QUERYTEXT,
					dataSetDesign.getOdaExtensionDataSetId( ) );
			setPropertyValue( Constants.XML_QUERYTEXT, xmlQueryText );
		}
		if ( dataSetDesign.getDataSourceDesign( ) != null )
		{
			DataSourceDesign dataSourceDesign = dataSetDesign.getDataSourceDesign( );
			java.util.Properties props = null;
			try
			{
				props = DesignSessionUtil.getEffectiveDataSourceProperties( dataSourceDesign );
			}
			catch ( OdaException e )
			{
				props = new java.util.Properties( );
			}

			String value = props.getProperty( Constants.SOAP_ENDPOINT );
			String soapEndPoint = PropertyValueUtil.getQualifiedValueForDataSource( value,
					Constants.SOAP_ENDPOINT );
			setPropertyValue( Constants.SOAP_ENDPOINT, soapEndPoint );

			value = props.getProperty( Constants.CUSTOM_CONNECTION_CLASS );
			String customConnectionClass = PropertyValueUtil.getQualifiedValueForDataSource( value,
					Constants.CUSTOM_CONNECTION_CLASS );
			setPropertyValue( Constants.CUSTOM_CONNECTION_CLASS,
						customConnectionClass );

			value = props.getProperty( Constants.CUSTOM_DRIVER_CLASS_PATH );
			String customDriverPath = PropertyValueUtil.getQualifiedValueForDataSource( value,
					Constants.CUSTOM_DRIVER_CLASS_PATH );
			setPropertyValue( Constants.CUSTOM_DRIVER_CLASS_PATH,
						customDriverPath );

			value = props.getProperty( Constants.CONNECTION_TIMEOUT );
			String connectionTimeOut = PropertyValueUtil.getQualifiedValueForDataSource( value,
					Constants.CUSTOM_DRIVER_CLASS_PATH );
			setPropertyValue( Constants.CONNECTION_TIMEOUT,
						connectionTimeOut );
			
			value = props.getProperty( Constants.WSDL_URI );
			String wsdlURI = PropertyValueUtil.getQualifiedValueForDataSource( value,
					Constants.WSDL_URI );
			setPropertyValue( Constants.WSDL_URI, wsdlURI );
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
			String xmlFile = dataSetDesign.getPublicProperties( )
					.getProperty( Constants.XML_FILE_URI );
			setXMLPropertyValue( Constants.CONST_PROP_FILELIST, xmlFile == null
					? WSUtil.EMPTY_STRING : xmlFile );

			String schema = dataSetDesign.getPublicProperties( )
					.getProperty( Constants.XSD_FILE_URI );
			setXMLPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST,
					schema == null ? WSUtil.EMPTY_STRING : schema );
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
		if ( sampleXMLFile != null && !sampleXMLFile.delete( ) )
		{
			sampleXMLFile.deleteOnExit( );
		}
		if ( templateFile != null && !templateFile.delete( ) )
		{
			templateFile.deleteOnExit( );
		}
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
	 * @param soapParameters
	 */
	public void updateParameters( SOAPParameter[] soapParameters )
	{
		parameters = soapParameters;
//		if( parameters == null || soapParameters == null || parameters.length == soapParameters.length )
//		{
//			parameters = soapParameters;
//			return;
//		}
//		for ( int i = 0; soapParameters != null && i < soapParameters.length; i++ )
//		{
//			if ( !WSUtil.isNull( soapParameters[i] ) )
//			{
//				int pos = -1;
//				for ( int j = 0; j < parameters.length; j++ )
//				{
//					if ( !WSUtil.isNull( parameters[j].getName( ) )
//							&& parameters[j].getName( )
//									.equals( soapParameters[i].getName( ) ) )
//					{
//						pos = j;
//						break;
//					}
//				}
//				if( pos != -1 )
//				{
//					parameters[pos].setDefaultValue( soapParameters[i].getDefaultValue( ) );
//					parameters[pos].setUsed( soapParameters[i].isUsed( ) );
//				}
//			}
//		}
	}
	
	/**
	 * 
	 * @return
	 * @throws OdaException 
	 */
	public String getTemplate( ) throws OdaException
	{
		WSDLAdvisor wsdlAdvisor = new WSDLAdvisor( );
		return wsdlAdvisor.getSOAPRequestTemplate( getPropertyValue( Constants.WSDL_URI ),
				getPropertyValue( Constants.OPERATION_TRACE ) );
	}

	/**
	 * 
	 */
	public void updateXSDFileURI( )
	{
		String xsdFileURI = getPropertyValue( Constants.XSD_FILE_URI );
		if ( xsdFileURI != null
				&& !xsdFileURI.equals( getXMLPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST ) ) )
			setXMLPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST,
					xsdFileURI );
	}

	/**
	 * 
	 * @return
	 * @throws OdaException
	 */
	public void updateXMLFileURI( ) throws OdaException
	{
		// Constants.XML_FILE_URI and Constants.CONST_PROP_FILELIST are
		// initially same, both of which are gotten from design, the difference
		// is the former will then be accoutable for design while the latter is
		// for xmlHolder alone

		// check if there is explicit xmlURI
		String xmlFileURI = getPropertyValue( Constants.XML_FILE_URI );
		if ( !WSUtil.isNull( xmlFileURI ) )
		{
			if ( !xmlFileURI.equals( getXMLPropertyValue( Constants.CONST_PROP_FILELIST ) ) )
				setXMLPropertyValue( Constants.CONST_PROP_FILELIST, xmlFileURI );

			return;
		}

		// check if there is implicit xmlURI
		String xmlTempFileURI = getPropertyValue( Constants.XML_TEMP_FILE_URI );
		if ( WSUtil.isNull( xmlTempFileURI ) || needsRefreshTempFile )
		{
			// there is no xml temp file, create one
			setXMLPropertyValue( Constants.CONST_PROP_FILELIST,
					WSUtil.EMPTY_STRING );
			createXMLTempFileURI( );
			xmlTempFileURI = getPropertyValue( Constants.XML_TEMP_FILE_URI );
			needsRefreshTempFile = false;
		}
		if ( !WSUtil.isNull( xmlTempFileURI )
				&& !xmlTempFileURI.equals( getXMLPropertyValue( Constants.CONST_PROP_FILELIST ) ) )
			setXMLPropertyValue( Constants.CONST_PROP_FILELIST, xmlTempFileURI );
	}

	/**
	 * 
	 * @throws OdaException
	 */
	public void createXMLTempFileURI( ) throws OdaException
	{
		String fileLocation = getPropertyValue( Constants.XML_TEMP_FILE_URI ) == null
				? null
				: getPropertyValue( Constants.XML_TEMP_FILE_URI ).toString( );
		if ( fileLocation != null && !new File( fileLocation ).delete( ) )
		{
			new File( fileLocation ).deleteOnExit( );
			setPropertyValue( Constants.XML_TEMP_FILE_URI, null );
		}
		InputStream stream = null;
		if( WSUtil.isNull( getPropertyValue( Constants.RESPONSE_SCHEMA ) ) || getPropertyValue( Constants.RESPONSE_SCHEMA ).equals( Constants.FROM_WSDL ) )
		{
			stream = getInputStream( true  );
		}
		else
		{
			stream = getInputStream( false  );
		}
		if ( WSUtil.isNull( stream ) )
			return;

		templateFile = generateTempXMLFile( stream );
		try
		{
			stream.close( );
		}
		catch ( IOException e )
		{
		}
		if ( templateFile != null )
		{
			String xmlTempFileURI = templateFile.getAbsolutePath( );
			setPropertyValue( Constants.XML_TEMP_FILE_URI, xmlTempFileURI );
		}
	}
	
	/**
	 * 
	 * @throws OdaException
	 */
	public void createSampleXMLFile( ) throws OdaException
	{
		String fileLocation = getPropertyValue( Constants.CONST_PROP_SAMPLE_XML ) == null
				? null
				: getPropertyValue( Constants.CONST_PROP_SAMPLE_XML ).toString( );
		if ( fileLocation != null && new File( fileLocation ).delete( ) )
		{
			new File( fileLocation ).deleteOnExit( );
		}
		setXMLPropertyValue( Constants.CONST_PROP_SAMPLE_XML, "" ); //$NON-NLS-1$

		InputStream stream = getInputStream( false );
		if ( WSUtil.isNull( stream ) )
			return;
		sampleXMLFile = generateTempXMLFile( stream );
		try
		{
			stream.close( );
		}
		catch ( IOException e )
		{
		}
		if ( sampleXMLFile != null )
		{
			String xmlTempFileURI = sampleXMLFile.getAbsolutePath( );
			setPropertyValue( Constants.CONST_PROP_SAMPLE_XML, xmlTempFileURI );
			setXMLPropertyValue( Constants.CONST_PROP_SAMPLE_XML,
					xmlTempFileURI );
		}
	}

	public void setRefreshTempFile( boolean needsRefreshTempFile )
	{
		this.needsRefreshTempFile = needsRefreshTempFile;
	}

	private File generateTempXMLFile( InputStream stream ) throws OdaException
	{

		File file;
		try
		{
			file = File.createTempFile( XML_TEMP_FILE, null );
			file.deleteOnExit( );
			FileOutputStream fos = new FileOutputStream( file );
			InputStream bis = new BufferedInputStream( stream );
			int abyte;

			while ( ( abyte = bis.read( ) ) != -1 )
			{
				fos.write( abyte );
			}
			bis.close( );
			fos.close( );
		}
		catch ( IOException e )
		{
			return null;
		}

		return file;
	}

	private InputStream getInputStream( boolean fromWSDL ) throws OdaException
	{
		if ( !WSUtil.isNull( getPropertyValue( Constants.CUSTOM_CONNECTION_CLASS ) ) )
		{
			return byCustom( );
		}
		else
		{
			SOAPResponse soapResponse = connectNow( fromWSDL );
			if ( soapResponse == null || soapResponse.getInputStream( ) == null )
				throw new OdaException( Messages.getString( "wsConsole.message.error.cantRetrieveSOAPResponse" ) ); //$NON-NLS-1$

			return soapResponse.getInputStream( );
		}
	}

	private InputStream byCustom( ) throws OdaException
	{
		Java2SOAPManager j2s = new Java2SOAPManager( );

		j2s.setConnectionProperties( retrieveConnProperties( ) );
		j2s.setQueryText( getPropertyValue( Constants.WS_QUERYTEXT ) );
		if ( !WSUtil.isNull( parameters ) )
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
			j2s.newQuery( getPropertyValue( Constants.CUSTOM_CONNECTION_CLASS ),getPropertyValue( Constants.CUSTOM_DRIVER_CLASS_PATH ) );

			Object o = j2s.executeQuery( );
			if ( o instanceof InputStream )
				return (InputStream) o;
			else if ( o instanceof String )
				return new ByteArrayInputStream( o.toString( ).getBytes( ) );

			return null;
		}
		catch ( Exception e )
		{
			throw new OdaException( e );
		}
	}

	private Properties retrieveConnProperties( )
	{
		Properties p = new Properties( );
		String value = getPropertyValue( Constants.SOAP_ENDPOINT );
		if ( value != null )
			p.put( Constants.SOAP_ENDPOINT, value );
		value = getPropertyValue( Constants.CUSTOM_CONNECTION_CLASS );
		if ( value != null )
			p.put( Constants.CUSTOM_CONNECTION_CLASS, value );
		value = getPropertyValue( Constants.CUSTOM_DRIVER_CLASS_PATH );
		if ( value != null )
			p.put( Constants.CUSTOM_DRIVER_CLASS_PATH, value );
		value = getPropertyValue( Constants.CONNECTION_TIMEOUT );
		if ( value != null )
			p.put( Constants.CONNECTION_TIMEOUT, value );

		return p;
	}

	private SOAPResponse connectNow( boolean fromWSDL ) throws OdaException
	{
		String spec = getPropertyValue( Constants.SOAP_ENDPOINT );
		// if soapEndPoint is not explicitly specified by the user, try locate
		// it from the wsdl file
		if ( WSUtil.isNull( spec ) )
			spec = WSDLAdvisor.getLocationURI( getPropertyValue( Constants.WSDL_URI ),
					getPropertyValue( Constants.OPERATION_TRACE ) );
		String query = getPropertyValue( Constants.WS_QUERYTEXT );
		if ( WSUtil.isNull( spec ) || WSUtil.isNull( query ) )
			return null;

		SOAPRequest soapRequest = new SOAPRequest( query );
		populateSOAPParameterValues( soapRequest, parameters );

		SOAPResponse soapResponse = null;
		if ( fromWSDL )
		{
			WSDLAdvisor wsdlAdvisor = new WSDLAdvisor( );
			String temlate = wsdlAdvisor.getLocalSOAPResponseTemplate( getPropertyValue( Constants.WSDL_URI ),
					getPropertyValue( Constants.OPERATION_TRACE ) );
			soapResponse = new SOAPResponse( new ByteArrayInputStream( temlate.toString( )
					.getBytes( ) ) );
		}
		else
		{
			RawMessageSender rawMessageSender = new RawMessageSender( );
			rawMessageSender.setMessage( soapRequest.toXML( ) );
			String soapEndPoint = getPropertyValue( Constants.SOAP_ENDPOINT );
			String wsdlURI = getPropertyValue( Constants.WSDL_URI );
			String operationTrace = getPropertyValue( Constants.OPERATION_TRACE );
			String connTimeOut = getPropertyValue( Constants.CONNECTION_TIMEOUT );
			
			int connectionTimeout = 0;
			if ( connTimeOut != null )
				connectionTimeout = Integer.parseInt( getPropertyValue( Constants.CONNECTION_TIMEOUT ) );

			if ( WSUtil.isNull( soapEndPoint ) )
				soapEndPoint = WSDLAdvisor.getLocationURI( wsdlURI,
						operationTrace );
			rawMessageSender.setSpec( soapEndPoint );
			rawMessageSender.setSoapAction( WSDLAdvisor.getSOAPActionURI( wsdlURI,
					operationTrace ) );
			soapResponse = rawMessageSender.getSOAPResponse( connectionTimeout );
		}
		return soapResponse;
	}

	private void populateSOAPParameterValues( SOAPRequest soapRequest,
			SOAPParameter[] soapParameters )
	{
		if ( WSUtil.isNull( soapRequest ) || WSUtil.isNull( soapParameters ) )
			return;

		SOAPParameter[] parameters = soapRequest.getParameters( );
		
		for ( int i = 0; parameters != null && i < parameters.length; i++ )
		{
			if( soapParameters == null || soapParameters.length != parameters.length )
				return;
			if ( !WSUtil.isNull( soapParameters[i] ) )
			{
				int pos = -1;
				for ( int j = 0; j < soapParameters.length; j++ )
				{
					if ( !WSUtil.isNull( soapParameters[j].getName( ) )
							&& soapParameters[j].getName( )
									.equals( parameters[i].getName( ) ) )
					{
						pos = j;
						break;
					}
				}
				if( pos != -1 )
				{
					parameters[i].setDefaultValue( soapParameters[pos].getDefaultValue( ) );
				}
			}
		}
	}
	

	/**
	 * 
	 * @param dataSetParams
	 */
	public void initSOAPParameters( DataSetParameters dataSetParams )
	{
		EList parameterDefinitions = dataSetParams.getParameterDefinitions( );
		if ( WSUtil.isNull( parameterDefinitions )
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
		SOAPParameter[] usedParameters = WSUIUtil.getUsedParameter( parameters );
		if ( !canMerge( usedParameters, parameterDefinitions ) )
			return;

		for ( int i = 0; i < usedParameters.length; i++ )
		{
			// apply name & defaultValue
			if ( !WSUtil.isNull( usedParameters[i] ) )
			{
				ParameterDefinition paramDef = (ParameterDefinition) parameterDefinitions.get( i );
				paramDef.getAttributes( ).setName( usedParameters[i].getName( ) );
				paramDef.setDefaultScalarValue( usedParameters[i].getDefaultValue( ) );
			}
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

	/**
	 * Convenient method to manipulate query after parameters have been changed
	 * 
	 * @param queryText
	 * @param params
	 * @return
	 * @throws OdaException
	 */
	public String manipulateTemplate( ) throws OdaException
	{
		SOAPRequest soapRequest = new SOAPRequest( getPropertyValue( Constants.WS_QUERYTEXT ) );
		String[] template = soapRequest.getTemplate( );

		if ( WSUtil.isNull( template ) || WSUtil.isNull( parameters ) )
			return getTemplate( );

		StringBuffer wsQueryText = new StringBuffer( );
		// retrieve whole queryText with defaultValue if applicable
		for ( int i = 0; i < parameters.length; i++ )
		{
			wsQueryText.append( template[i] );
			wsQueryText.append( buildParameter( parameters[i].getName( ) ) );
		}
		wsQueryText.append( template[template.length - 1] );
		
		// eliminate unused parameters
		StringBuffer buffer = new StringBuffer( wsQueryText.toString( ) );

		for ( int i = 0; i < parameters.length; i++ )
		{
			if ( !parameters[i].isUsed( ) )
			{
				int offset = getOffset( wsQueryText.toString( ), parameters[i].getName( ) );
				int start = getFirstIndex( wsQueryText.toString( ), offset, BACKWARD, '<' );
				start = getFirstIndex( wsQueryText.toString( ), start, BACKWARD, '\n' );
				int end = getFirstIndex( wsQueryText.toString( ), offset, FORWARD, '>' );
				end = getFirstIndex( wsQueryText.toString( ), end, FORWARD, '\n' );

				buffer.delete( start, end );
				wsQueryText = buffer;
			}
		}

		return wsQueryText.toString( );
	}

	private String buildParameter( String paramName )
	{
		return "&?" + paramName + "?&"; //$NON-NLS-1$//$NON-NLS-2$
	}

	private int getOffset( String queryText, String paramName )
	{
		return queryText.indexOf( buildParameter( paramName ) );
	}

	private int getFirstIndex( String string, int index, int dir, char ch )
	{
		while ( string.charAt( index ) != ch )
		{
			switch ( dir )
			{
				case BACKWARD :
					index--;
					break;
				case FORWARD :
					index++;
					break;
			}
		}

		return index;
	}

}
