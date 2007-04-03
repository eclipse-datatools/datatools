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

package org.eclipse.datatools.enablement.oda.ws.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.wsdl.BindingInput;
import javax.wsdl.BindingOperation;
import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.Types;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.UnknownExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */

public class WSDLAdvisor
{

	public static String RE_DELIMITER_OPEARTION = "\\Q$-$\\E";//$NON-NLS-1$
	public static final String XML_DECLARATION = "<?xml version=\"1.0\"?>";//$NON-NLS-1$
	public static final String SOAP_ENVELOPE_START = "<SOAP-ENV:Envelope";//$NON-NLS-1$
	public static final String SOAP_ENVELOPE_END = "</SOAP-ENV:Envelope>";//$NON-NLS-1$
	public static final String SOAP_BODY_START = "<SOAP-ENV:Body>";//$NON-NLS-1$
	public static final String SOAP_BODY_END = "</SOAP-ENV:Body>";//$NON-NLS-1$
	public static final String NS_SOAP_ENV = "http://schemas.xmlsoap.org/soap/envelope/";//$NON-NLS-1$
	public static final String NS_SOAP_ENC = "http://schemas.xmlsoap.org/soap/encoding/";//$NON-NLS-1$
	public static final String NS_xsi = "http://www.w3.org/2001/XMLSchema-instance";//$NON-NLS-1$
	public static final String NS_xsd = "http://www.w3.org/2001/XMLSchema";//$NON-NLS-1$
	public static final String NS_KEY_SOAP_ENV = "SOAP-ENV";//$NON-NLS-1$
	public static final String NS_KEY_SOAP_ENC = "SOAP-ENC";//$NON-NLS-1$
	public static final String NS_KEY_XSI = "xsi";//$NON-NLS-1$
	public static final String NS_KEY_XSD = "xsd";//$NON-NLS-1$
	public static final String NS_KEY_DEFAULT = "ns1";//$NON-NLS-1$

	private static String defaultNS = NS_KEY_DEFAULT + ":";
	private static Map definitionMap = new HashMap( );
	private static String namespaceURI = "";

	/**
	 * Get the definition, it's a starting point to browse a operation tree
	 * 
	 * @param wsdlURI
	 * @return
	 */
	public static Definition getDefinition( String wsdlURI )
	{
		if ( definitionMap.containsKey( wsdlURI ) )
			return (Definition) definitionMap.get( wsdlURI );

		Definition definition;
		try
		{
			WSDLReader reader = WSDLFactory.newInstance( ).newWSDLReader( );
			reader.setFeature( "javax.wsdl.verbose", true );
			reader.setFeature( "javax.wsdl.importDocuments", true );
			definition = reader.readWSDL( null, wsdlURI );

			definitionMap.put( wsdlURI, definition );
		}
		catch ( WSDLException e )
		{
			return null;
		}

		return definition;
	}

	public static String getLocationURI( String wsdlURI, String operationTrace )
	{
		if ( !checkOperationTrace( operationTrace ) )
			return null;

		Definition definition = getDefinition( wsdlURI );
		if ( WSUtil.isNull( definition ) )
			return null;

		String[] opSplit = operationTrace.split( RE_DELIMITER_OPEARTION );
		Service service = definition.getService( new QName( definition.getTargetNamespace( ),
				opSplit[0] ) );// service
		Port port = service.getPort( opSplit[1] );// port
		List extElements = port.getExtensibilityElements( );
		String locationURI = "";
		if ( !WSUtil.isNull( extElements ) )
		{
			for ( int i = 0; i < extElements.size( ); i++ )
			{
				if ( extElements.get( i ) instanceof SOAPAddress )
				{
					locationURI = ( (SOAPAddress) extElements.get( i ) ).getLocationURI( );
					break;
				}
			}
		}

		return locationURI;
	}

	private static boolean checkOperationTrace( String operationTrace )
	{
		if ( WSUtil.isNull( operationTrace ) )
			return false;

		String[] opSplit = operationTrace.split( RE_DELIMITER_OPEARTION );
		if ( opSplit.length != 3 )
			return false;

		return true;
	}

	public static BindingOperation getBindingOperation( String wsdlURI,
			String operationTrace )
	{
		if ( !checkOperationTrace( operationTrace ) )
			return null;

		Definition definition = getDefinition( wsdlURI );
		if ( WSUtil.isNull( definition ) )
			return null;

		String[] opSplit = operationTrace.split( RE_DELIMITER_OPEARTION );
		Service service = definition.getService( new QName( definition.getTargetNamespace( ),
				opSplit[0] ) );// service
		Port port = service.getPort( opSplit[1] );// port
		BindingOperation bindingOperation = port.getBinding( )
				.getBindingOperation( opSplit[2],// operation
						null,
						null );

		return bindingOperation;
	}

	public static String getSOAPActionURI( String wsdlURI, String operationTrace )
	{
		BindingOperation bindingOperation = getBindingOperation( wsdlURI,
				operationTrace );
		if ( WSUtil.isNull( bindingOperation ) )
			return null;

		List extElements = bindingOperation.getExtensibilityElements( );
		String soapActionURI = "";
		if ( !WSUtil.isNull( extElements ) )
		{
			for ( int i = 0; i < extElements.size( ); i++ )
			{
				if ( extElements.get( i ) instanceof SOAPOperation )
				{
					soapActionURI = ( (SOAPOperation) extElements.get( i ) ).getSoapActionURI( );
					break;
				}
			}
		}

		return soapActionURI;
	}

	/**
	 * Due to the various definitions and the parser used, the template
	 * generated is for the time being not guaranteed to be accurate.
	 * 
	 * @param wsdlURI
	 * @param operationTrace
	 * @return
	 */
	public static String getSOAPRequestTemplate( String wsdlURI,
			String operationTrace )
	{
		// The following is the steps used to read through the wsdl file,
		// every step will be proceeded with fault-tolerance.
		// Definition->Service->Port->BindingOperation
		// ->BindingInput->namespaceURI
		// ->extElements->soapActionURI
		// ->Operation->Input->Message->OrderedParts

		BindingOperation bindingOperation = getBindingOperation( wsdlURI,
				operationTrace );
		if ( WSUtil.isNull( bindingOperation ) )
			return null;

		// BindingInput
		BindingInput bindingInput = bindingOperation.getBindingInput( );
		List extElements = bindingInput.getExtensibilityElements( );

		if ( !WSUtil.isNull( extElements ) )
		{
			for ( int i = 0; i < extElements.size( ); i++ )
			{
				if ( extElements.get( i ) instanceof SOAPBody )
				{
					namespaceURI = ( (SOAPBody) extElements.get( i ) ).getNamespaceURI( );
					break;
				}
			}
		}

		// Operation
		Operation operation = bindingOperation.getOperation( );
		List partOrder = operation.getParameterOrdering( );
		List parts = operation.getInput( )
				.getMessage( )
				.getOrderedParts( partOrder );
		// TODO: there is no way to tell complexTypes at this point
		List paramNameList = new ArrayList( );
		List paramTypeList = new ArrayList( );
		if ( !WSUtil.isNull( parts ) && !parts.isEmpty( ) )
		{
			for ( int i = 0; i < parts.size( ); i++ )
			{
				Part part = (Part) parts.get( i );
				if ( !WSUtil.isNull( part.getElementName( ) ) )
					addParamComplexType( wsdlURI,
							part.getElementName( ),
							paramNameList,
							paramTypeList );
				else
				{
					paramNameList.add( part.getName( ) );
					QName typeName = part.getTypeName( );
					paramTypeList.add( WSUtil.getNonNullString( typeName == null
							? "" : typeName.getLocalPart( ) ) );
				}
			}
		}

		// in case complexTypes were used
		if ( WSUtil.isNull( namespaceURI ) )
			setDefaultNS( "" );
		else
			setDefaultNS( NS_KEY_DEFAULT + ":" );

		String operationName = operation.getInput( ).getName( ) != null
				? operation.getInput( ).getName( )
				: operationTrace.split( RE_DELIMITER_OPEARTION )[2];
		String template = buildStart( )
				+ buildNamespaceDeclarations( namespaceURI )
				+ buildBody( operationName, paramNameList, paramTypeList )
				+ buildEnd( );

		return template;
	}

	private static void addParamComplexType( String wsdlURI, QName qName,
			List paramNameList, List paramTypeList )
	{
		Definition definition = getDefinition( wsdlURI );
		Types types = definition.getTypes( );
		List extElements = types.getExtensibilityElements( );
		for ( int i = 0; i < extElements.size( ); i++ )
		{
			if ( extElements.get( i ) instanceof UnknownExtensibilityElement )
			{
				Element element = ( (UnknownExtensibilityElement) extElements.get( i ) ).getElement( );
				namespaceURI = element.getAttribute( "targetNamespace" );
				Element te = retrieveTargetElement( element, qName );
				if ( !WSUtil.isNull( te ) )
				{
					paramNameList.add( te.getAttribute( "name" ) );
					paramTypeList.add( te.getAttribute( "type" ) );
				}
			}
		}
	}

	private static Element retrieveTargetElement( Element element, QName qName )
	{
		NodeList nodes = element.getChildNodes( );
		for ( int i = 0; i < nodes.getLength( ); i++ )
		{
			Node node = nodes.item( i );
			if ( node.getNodeType( ) != Node.ELEMENT_NODE )
				continue;

			NamedNodeMap nodeMap = node.getAttributes( );
			if ( !WSUtil.isNull( nodeMap )
					&& qName.getLocalPart( )
							.equals( nodeMap.getNamedItem( "name" )
									.getNodeValue( ) ) )
			{
				return retrieveNamedElement( node );
			}
		}

		return null;
	}

	private static Element retrieveNamedElement( Node node )
	{
		NodeList subs = node.getChildNodes( );
		Element te = null;
		for ( int i = 0; i < subs.getLength( ); i++ )
		{
			Node sub = subs.item( i );
			if ( sub.getNodeType( ) != Node.ELEMENT_NODE )
				continue;

			NamedNodeMap subMap = sub.getAttributes( );
			if ( WSUtil.isNull( subMap )
					|| WSUtil.isNull( subMap.getNamedItem( "name" ) ) )
				te = retrieveNamedElement( sub );
			else
				te = (Element) sub;
		}

		return te;
	}

	private static String enter( )
	{
		return "\n";
	}

	private static String tab( int num )
	{
		String tabs = "";
		for ( int i = 0; i < num; i++ )
			tabs += "\t";

		return tabs;
	}

	private static String buildStart( )
	{
		return XML_DECLARATION + enter( ) + SOAP_ENVELOPE_START;
	}

	private static String buildEnd( )
	{
		return enter( ) + SOAP_BODY_END + enter( ) + SOAP_ENVELOPE_END;
	}

	private static String buildNamespaceDeclarations( String namespaceURI )
	{
		Map namespaceURIs = new HashMap( );

		checkNamespace( namespaceURIs, NS_KEY_SOAP_ENV, NS_SOAP_ENV );
		checkNamespace( namespaceURIs, NS_KEY_SOAP_ENC, NS_SOAP_ENC );
		// comment out types for now
		// checkNamespace( namespaceURIs, NS_KEY_XSI, NS_xsi );
		// checkNamespace( namespaceURIs, NS_KEY_XSD, NS_xsd );
		checkNamespace( namespaceURIs, NS_KEY_DEFAULT, namespaceURI );

		String result = "";
		Set uris = namespaceURIs.keySet( );
		Iterator iterator = uris.iterator( );
		while ( iterator.hasNext( ) )
		{
			String uri = (String) iterator.next( );
			String prefix = (String) namespaceURIs.get( uri );
			result += enter( ) + "xmlns:" + prefix + "=\"" + uri + "\" ";//$NON-NLS-1$//$NON-NLS-1$//$NON-NLS-1$
		}

		return result + ">";
	}

	private static void checkNamespace( Map namespaceURIs, String namespace,
			String namespaceURI )
	{
		String resultNS = namespace;
		if ( namespaceURI != null )
		{
			resultNS = (String) namespaceURIs.get( namespaceURI );
			if ( resultNS == null )
			{
				resultNS = namespace;
				while ( namespaceURIs.containsValue( resultNS ) )
				{
					resultNS += "_" + Integer.toString( namespaceURIs.size( ) );
				}
				namespaceURIs.put( namespaceURI, resultNS );
			}
		}
	}

	private static String buildBody( String operationName, List paramNames,
			List paramTypes )
	{
		String body = enter( ) + SOAP_BODY_START;
		body += enter( )
				+ tab( 1 ) + "<" + getDefaultNS( ) + operationName + ">"
				+ buildInputParamters( paramNames, paramTypes ) + enter( )
				+ tab( 1 ) + "</" + getDefaultNS( ) + operationName + ">";

		return body;
	}

	private static String buildInputParamters( List paramNames, List paramTypes )
	{
		// TODO: types were hidden on purpose,release them
		String inputParams = "";
		for ( int i = 0; i < paramNames.size( ); i++ )
		{
			String paramName = paramNames.get( i ).toString( );
			// String paramType = paramTypes.get( i ).toString( );
			inputParams += enter( )
					+ tab( 2 ) + "<" + getDefaultNS( ) + paramName + ">&?"
					+ paramName + "?&</" + getDefaultNS( ) + paramName + ">";
			// + " " + NS_KEY_XSI
			// + ":type=\"" + NS_KEY_XSD + ":" + paramType + "\""
		}

		return inputParams;
	}

	private static String getDefaultNS( )
	{
		return defaultNS;
	}

	private static void setDefaultNS( String value )
	{
		defaultNS = value;
	}

}
