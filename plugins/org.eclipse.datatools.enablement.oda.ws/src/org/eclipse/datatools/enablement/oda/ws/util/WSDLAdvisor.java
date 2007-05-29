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

import javax.wsdl.Binding;
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
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPHeader;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A utility class that handles all wsdl-involved operations
 */

public class WSDLAdvisor
{

	public static final String RE_DELIMITER_OPEARTION = "\\Q$-$\\E";//$NON-NLS-1$
	public static final String XML_DECLARATION = "<?xml version=\"1.0\"?>";//$NON-NLS-1$
	public static final String SOAP_ENVELOPE_START = "<SOAP-ENV:Envelope";//$NON-NLS-1$
	public static final String SOAP_ENVELOPE_END = "</SOAP-ENV:Envelope>";//$NON-NLS-1$
	public static final String SOAP_HEADER_START = "<SOAP-ENV:Header>";//$NON-NLS-1$
	public static final String SOAP_HEADER_END = "</SOAP-ENV:Header>";//$NON-NLS-1$
	public static final String SOAP_BODY_START = "<SOAP-ENV:Body>";//$NON-NLS-1$
	public static final String SOAP_BODY_END = "</SOAP-ENV:Body>";//$NON-NLS-1$
	public static final String NS_SOAP_ENV = "http://schemas.xmlsoap.org/soap/envelope/";//$NON-NLS-1$
	public static final String NS_SOAP_ENC = "http://schemas.xmlsoap.org/soap/encoding/";//$NON-NLS-1$
	public static final String NS_XSI = "http://www.w3.org/2001/XMLSchema-instance";//$NON-NLS-1$
	public static final String NS_XSD = "http://www.w3.org/2001/XMLSchema";//$NON-NLS-1$
	public static final String NS_KEY_SOAP_ENV = "SOAP-ENV";//$NON-NLS-1$
	public static final String NS_KEY_SOAP_ENC = "SOAP-ENC";//$NON-NLS-1$
	public static final String NS_KEY_XSI = "xsi";//$NON-NLS-1$
	public static final String NS_KEY_XSD = "xsd";//$NON-NLS-1$
	public static final String NS_KEY_DEFAULT = "m";//$NON-NLS-1$
	public static final String NS_DEFAULT = NS_KEY_DEFAULT + ":"; //$NON-NLS-1$
	public static final String EMPTY_STRING = ""; //$NON-NLS-1$

	private static Map definitionMap = new HashMap( );
	private static List primitiveDataTypeList;

	/**
	 * Gets the definition, this is the starting point to browse a operation
	 * tree
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
			reader.setFeature( "javax.wsdl.verbose", true ); //$NON-NLS-1$
			reader.setFeature( "javax.wsdl.importDocuments", true ); //$NON-NLS-1$
			definition = reader.readWSDL( null, wsdlURI );

			definitionMap.put( wsdlURI, definition );
		}
		catch ( WSDLException e )
		{
			return null;
		}

		return definition;
	}

	/**
	 * Retrieves the locationURI
	 * 
	 * @param wsdlURI
	 * @param operationTrace
	 * @return
	 */
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
		if ( WSUtil.isNull( service ) )
			return null;

		Port port = service.getPort( opSplit[1] );// port
		List extElements = port.getExtensibilityElements( );
		String locationURI = EMPTY_STRING;
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

	/**
	 * Retrieves the SOAPActionURI
	 * 
	 * @param wsdlURI
	 * @param operationTrace
	 * @return
	 */
	public static String getSOAPActionURI( String wsdlURI, String operationTrace )
	{
		BindingOperation bindingOperation = getBindingOperation( wsdlURI,
				operationTrace );
		if ( WSUtil.isNull( bindingOperation ) )
			return null;

		List extElements = bindingOperation.getExtensibilityElements( );
		String soapActionURI = EMPTY_STRING;
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
	 * Retrieves the document of the operation
	 * 
	 * @param operation
	 * @return
	 */
	public static String retrieveDocument( Operation operation )
	{
		String result = EMPTY_STRING;
		if ( WSUtil.isNull( operation ) )
			return result;

		Element element = operation.getDocumentationElement( );
		if ( !WSUtil.isNull( element ) )
		{
			NodeList nodes = element.getChildNodes( );
			for ( int i = 0; i < nodes.getLength( ); i++ )
			{
				Node node = nodes.item( i );
				if ( node.getNodeType( ) != Node.TEXT_NODE )
					continue;

				result += node.getNodeValue( );
			}
		}

		return result;
	}

	private static BindingOperation getBindingOperation( String wsdlURI,
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
		if ( WSUtil.isNull( service ) )
			return null;

		Port port = service.getPort( opSplit[1] );// port
		BindingOperation bindingOperation = port.getBinding( )
				.getBindingOperation( opSplit[2],// operation
						null,
						null );

		return bindingOperation;
	}

	/**
	 * Generates the template (WSDL 1.1, SOAP 1.2 compliant) based on the given
	 * location of wsdlFile and the selected opeartion. The following attributes
	 * and their combinations are recognizable 1. style: document|rpc 2.header:
	 * with|without 3. dataType: simple|complex 4.complexType: value|reference
	 * [5.complexType: sequence|choice]
	 * 
	 * @param wsdlURI
	 * @param operationTrace
	 * @return
	 */
	public static String getSOAPRequestTemplate( String wsdlURI,
			String operationTrace )
	{
		String template = EMPTY_STRING;
		if ( !checkOperationTrace( operationTrace ) )
			return template;

		Definition definition = getDefinition( wsdlURI );
		if ( WSUtil.isNull( definition ) )
			return template;

		template = buildStart( )
				+ buildHeader( wsdlURI, operationTrace )
				+ buildBody( wsdlURI, operationTrace ) + buildEnd( );

		return template;
	}

	private static String buildStart( )
	{
		return XML_DECLARATION
				+ enter( ) + SOAP_ENVELOPE_START + buildNamespaceDeclarations( );
	}

	private static String buildEnd( )
	{
		return enter( )
				+ tab( 1 ) + SOAP_BODY_END + enter( ) + SOAP_ENVELOPE_END;
	}

	private static String enter( )
	{
		return "\n"; //$NON-NLS-1$
	}

	private static String tab( int num )
	{
		String tabs = ""; //$NON-NLS-1$
		for ( int i = 0; i < num; i++ )
			tabs += "\t"; //$NON-NLS-1$

		return tabs;
	}

	private static String buildNamespaceDeclarations( )
	{
		Map namespaceURIs = new HashMap( );

		checkNamespace( namespaceURIs, NS_KEY_SOAP_ENV, NS_SOAP_ENV );
		checkNamespace( namespaceURIs, NS_KEY_SOAP_ENC, NS_SOAP_ENC );
		checkNamespace( namespaceURIs, NS_KEY_XSI, NS_XSI );
		checkNamespace( namespaceURIs, NS_KEY_XSD, NS_XSD );

		String result = EMPTY_STRING;
		Set uris = namespaceURIs.keySet( );
		Iterator iterator = uris.iterator( );
		while ( iterator.hasNext( ) )
		{
			String uri = (String) iterator.next( );
			String prefix = (String) namespaceURIs.get( uri );
			result += enter( ) + "xmlns:" + prefix + "=\"" + uri + "\"";//$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}

		return result + ">"; //$NON-NLS-1$
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
					resultNS += "_" + Integer.toString( namespaceURIs.size( ) ); //$NON-NLS-1$
				}
				namespaceURIs.put( namespaceURI, resultNS );
			}
		}
	}

	private static String buildHeader( String wsdlURI, String operationTrace )
	{
		String result = EMPTY_STRING;
		BindingOperation bindingOperation = getBindingOperation( wsdlURI,
				operationTrace );
		if ( WSUtil.isNull( bindingOperation ) )
			return result;

		BindingInput bindingInput = bindingOperation.getBindingInput( );
		List extElements = bindingInput.getExtensibilityElements( );
		if ( !WSUtil.isNull( extElements ) )
		{
			for ( int i = 0; i < extElements.size( ); i++ )
			{
				if ( extElements.get( i ) instanceof SOAPHeader )
				{
					SOAPHeader soapHeader = ( (SOAPHeader) extElements.get( i ) );
					String nameSpace = soapHeader.getMessage( )
							.getNamespaceURI( );
					String localPart = soapHeader.getPart( );
					if ( !WSUtil.isNull( localPart ) )
					{
						List paramNameList = new ArrayList( );
						List paramTypeList = new ArrayList( );
						addParamComplexType( wsdlURI,
								localPart,
								paramNameList,
								paramTypeList );

						result += enter( ) + tab( 1 ) + SOAP_HEADER_START;
						result += enter( )
								+ tab( 2 ) + "<" + NS_DEFAULT + localPart + " " //$NON-NLS-1$//$NON-NLS-2$
								+ getQNameSpace( nameSpace ) + ">"; //$NON-NLS-1$
						result += buildInputParameters( wsdlURI,
								NS_DEFAULT,
								paramNameList,
								paramTypeList,
								3 );
						result += enter( )
								+ tab( 2 )
								+ "</" + NS_DEFAULT + localPart + ">"; //$NON-NLS-1$ //$NON-NLS-2$
						result += enter( ) + tab( 1 ) + SOAP_HEADER_END;
					}
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Retrieves all the inputParameters without concerning complexType
	 * 
	 * @param wsdlURI
	 * @param localPart
	 * @param paramNameList
	 * @param paramTypeList
	 */
	private static void addParamComplexType( String wsdlURI, String localPart,
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
				List teList = retrieveTargetElementList( element, localPart );
				for ( int j = 0; j < teList.size( ); j++ )
				{
					Element te = (Element) teList.get( j );
					if ( !WSUtil.isNull( te ) )
					{
						paramNameList.add( te.getAttribute( "name" ) );//$NON-NLS-1$
						paramTypeList.add( te.getAttribute( "type" ) ); //$NON-NLS-1$
					}
				}
			}
		}
	}

	/**
	 * Retrieves the targetElementList as specified by the localPart
	 * 
	 * @param element
	 * @param localPart
	 * @return
	 */
	private static List retrieveTargetElementList( Element element,
			String localPart )
	{
		List teList = new ArrayList( );
		NodeList nodes = element.getChildNodes( );
		for ( int i = 0; i < nodes.getLength( ); i++ )
		{
			Node node = nodes.item( i );
			if ( node.getNodeType( ) != Node.ELEMENT_NODE )
				continue;

			NamedNodeMap nodeMap = node.getAttributes( );
			if ( !WSUtil.isNull( nodeMap )
					&& !WSUtil.isNull( nodeMap.getNamedItem( "name" ) )//$NON-NLS-1$
					&& localPart.equalsIgnoreCase( nodeMap.getNamedItem( "name" )//$NON-NLS-1$
							.getNodeValue( ) ) )
			{
				if ( WSUtil.isNull( nodeMap.getNamedItem( "type" ) ) )//$NON-NLS-1$
					retrieveNamedElements( teList, node );
				// in case the type is still a reference
				else
					retrieveTargetElementList( element,
							nodeMap.getNamedItem( "type" ).getNodeName( ) );//$NON-NLS-1$
			}
		}

		return teList;
	}

	/**
	 * Adds the named elements to a list
	 * 
	 * @param teList
	 * @param node
	 */
	private static void retrieveNamedElements( List teList, Node node )
	{
		NodeList subs = node.getChildNodes( );
		for ( int i = 0; i < subs.getLength( ); i++ )
		{
			Node sub = subs.item( i );
			if ( sub.getNodeType( ) != Node.ELEMENT_NODE )
				continue;

			NamedNodeMap subMap = sub.getAttributes( );
			if ( WSUtil.isNull( subMap )
					|| WSUtil.isNull( subMap.getNamedItem( "name" ) ) )//$NON-NLS-1$;
			{
				retrieveNamedElements( teList, sub );
			}
			else
			{
				teList.add( (Element) sub );
			}
		}
	}

	/**
	 * With full knowledge of complexType(reference|value), this method builds
	 * inputParameter block, each of which follows a pattern looking like:
	 * either <i1 xsi:type="xsd:int">&?i1?&</i1> or <in0> <street
	 * xsi:type="xsd:string">&?street?&</street> <postcode
	 * xsi:type="xsd:int">&?postcode?&</postcode> </in0>
	 * 
	 * @param wsdlURI
	 * @param nameSpace
	 * @param paramNames
	 * @param paramTypes
	 * @param tabCount
	 * 
	 * @return
	 */
	private static String buildInputParameters( String wsdlURI,
			String nameSpace, List paramNames, List paramTypes, int tabCount )
	{
		String result = EMPTY_STRING;
		for ( int i = 0; i < paramNames.size( ); i++ )
		{
			String paramName = paramNames.get( i ).toString( );
			String paramType = paramTypes.get( i ).toString( );

			// complexType reference
			if ( !isPrimitiveDataType( paramType ) )
			{
				List paramNameList = new ArrayList( );
				List paramTypeList = new ArrayList( );
				addParamComplexType( wsdlURI,
						getParamTypeLocalPart( paramType ),
						paramNameList,
						paramTypeList );

				// temporarily solution to handle simpleType
				if ( paramNameList.isEmpty( ) && paramTypeList.isEmpty( ) )
				{
					result += enter( )
							+ tab( tabCount ) + "<" + nameSpace + paramName //$NON-NLS-1$
							+ ">&?" + paramName //$NON-NLS-1$
							+ "?&</" + nameSpace + paramName + ">"; //$NON-NLS-1$ //$NON-NLS-2$
				}
				else
				{
					result += enter( )
							+ tab( tabCount )
							+ "<" + nameSpace + paramName + ">"; //$NON-NLS-1$//$NON-NLS-2$
					result += buildInputParameters( wsdlURI,
							nameSpace,
							paramNameList,
							paramTypeList,
							tabCount + 1 );
					result += enter( )
							+ tab( tabCount )
							+ "</" + nameSpace + paramName + ">"; //$NON-NLS-1$//$NON-NLS-2$
				}
			}
			// complexType value
			else
			{
				result += enter( )
						+ tab( tabCount ) + "<" + nameSpace + paramName //$NON-NLS-1$
						+ buildParamType( paramType ) + ">&?" + paramName //$NON-NLS-1$
						+ "?&</" + nameSpace + paramName + ">"; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}

		return result;
	}

	private static String getParamTypeLocalPart( String paramType )
	{
		return paramType.substring( paramType.lastIndexOf( ":" ) + 1 ); //$NON-NLS-1$
	}

	private static String buildParamType( String paramType )
	{
		String result = EMPTY_STRING;
		if ( WSUtil.isNull( paramType ) )
			return result;

		result += " " //$NON-NLS-1$
				+ NS_KEY_XSI + ":type=\"" //$NON-NLS-1$
				+ NS_KEY_XSD + ":" //$NON-NLS-1$
				+ getParamTypeLocalPart( paramType ) + "\""; //$NON-NLS-1$

		return result;
	}

	private static String buildBody( String wsdlURI, String operationTrace )
	{
		String result = enter( ) + tab( 1 ) + SOAP_BODY_START;

		if ( isRPC( wsdlURI, operationTrace ) )
		{
			result += buildBodyRPC( wsdlURI, operationTrace );
		}
		else
		{
			result += buildBodyDoc( wsdlURI, operationTrace );
		}

		return result;
	}

	private static boolean isRPC( String wsdlURI, String operationTrace )
	{
		boolean isRPC = false;

		Definition definition = getDefinition( wsdlURI );
		String[] opSplit = operationTrace.split( RE_DELIMITER_OPEARTION );
		Service service = definition.getService( new QName( definition.getTargetNamespace( ),
				opSplit[0] ) );// service
		Port port = service.getPort( opSplit[1] );// port
		Binding binding = port.getBinding( );
		List extElements = binding.getExtensibilityElements( );

		if ( !WSUtil.isNull( extElements ) )
		{
			for ( int i = 0; i < extElements.size( ); i++ )
			{
				if ( extElements.get( i ) instanceof SOAPBinding )
				{
					isRPC = !WSUtil.isNull( ( (SOAPBinding) extElements.get( i ) ).getStyle( ) )
							&& ( (SOAPBinding) extElements.get( i ) ).getStyle( )
									.equalsIgnoreCase( "rpc" );//$NON-NLS-1$
					break;
				}
			}
		}

		return isRPC;
	}

	private static String buildBodyRPC( String wsdlURI, String operationTrace )
	{
		String result = EMPTY_STRING;
		BindingOperation bindingOperation = getBindingOperation( wsdlURI,
				operationTrace );
		if ( WSUtil.isNull( bindingOperation ) )
			return result;

		Operation operation = bindingOperation.getOperation( );
		List partOrder = operation.getParameterOrdering( );
		List parts = operation.getInput( )
				.getMessage( )
				.getOrderedParts( partOrder );

		if ( !WSUtil.isNull( parts ) && !parts.isEmpty( ) )
		{
			List paramNameList = new ArrayList( );
			List paramTypeList = new ArrayList( );
			for ( int i = 0; i < parts.size( ); i++ )
			{
				Part part = (Part) parts.get( i );

				QName typeName = part.getTypeName( );
				paramNameList.add( part.getName( ) );
				paramTypeList.add( WSUtil.getNonNullString( WSUtil.isNull( typeName )
						? EMPTY_STRING : typeName.getLocalPart( ) ) );
			}

			result += enter( )
					+ tab( 2 ) + "<" + NS_DEFAULT + operation.getName( ) + " " //$NON-NLS-1$ //$NON-NLS-2$
					+ getQNameSpace( getNameSpaceRPC( bindingOperation ) )
					+ ">"; //$NON-NLS-1$
			result += buildInputParameters( wsdlURI,
					EMPTY_STRING,
					paramNameList,
					paramTypeList,
					3 );
			result += enter( )
					+ tab( 2 ) + "</" + NS_DEFAULT + operation.getName( ) + ">"; //$NON-NLS-1$ //$NON-NLS-2$
		}

		return result;
	}

	private static String getNameSpaceRPC( BindingOperation bindingOperation )
	{
		BindingInput bindingInput = bindingOperation.getBindingInput( );
		List extElements = bindingInput.getExtensibilityElements( );
		String nameSpace = EMPTY_STRING;

		if ( !WSUtil.isNull( extElements ) )
		{
			for ( int i = 0; i < extElements.size( ); i++ )
			{
				if ( extElements.get( i ) instanceof SOAPBody )
				{
					nameSpace = ( (SOAPBody) extElements.get( i ) ).getNamespaceURI( );
					break;
				}
			}
		}

		return nameSpace;
	}

	private static String getQNameSpace( String nameSpace )
	{
		if ( WSUtil.isNull( nameSpace ) )
			return EMPTY_STRING;
		else
			return "xmlns:" + NS_KEY_DEFAULT + "=\"" + nameSpace + "\"";//$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$//;
	}

	private static String buildBodyDoc( String wsdlURI, String operationTrace )
	{
		String result = EMPTY_STRING;
		BindingOperation bindingOperation = getBindingOperation( wsdlURI,
				operationTrace );
		if ( WSUtil.isNull( bindingOperation ) )
			return result;

		Operation operation = bindingOperation.getOperation( );
		List partOrder = operation.getParameterOrdering( );
		List parts = operation.getInput( )
				.getMessage( )
				.getOrderedParts( partOrder );

		if ( !WSUtil.isNull( parts ) && !parts.isEmpty( ) )
		{
			for ( int i = 0; i < parts.size( ); i++ )
			{
				Part part = (Part) parts.get( i );
				if ( WSUtil.isNull( part.getElementName( ) ) )
					continue;

				List paramNameList = new ArrayList( );
				List paramTypeList = new ArrayList( );

				addParamComplexType( wsdlURI, part.getElementName( )
						.getLocalPart( ), paramNameList, paramTypeList );

				result += enter( ) + tab( 2 ) + "<" + NS_DEFAULT //$NON-NLS-1$
						+ part.getElementName( ).getLocalPart( ) + " " //$NON-NLS-1$
						+ getQNameSpace( getNameSpaceDoc( wsdlURI ) ) + ">"; //$NON-NLS-1$
				result += buildInputParameters( wsdlURI,
						NS_DEFAULT,
						paramNameList,
						paramTypeList,
						3 );
				result += enter( ) + tab( 2 ) + "</" + NS_DEFAULT //$NON-NLS-1$
						+ part.getElementName( ).getLocalPart( ) + ">"; //$NON-NLS-1$
			}
		}

		return result;
	}

	private static String getNameSpaceDoc( String wsdlURI )
	{
		String namespace = EMPTY_STRING;
		Definition definition = getDefinition( wsdlURI );
		Types types = definition.getTypes( );
		List extElements = types.getExtensibilityElements( );
		for ( int i = 0; i < extElements.size( ); i++ )
		{
			if ( extElements.get( i ) instanceof UnknownExtensibilityElement )
			{
				Element element = ( (UnknownExtensibilityElement) extElements.get( i ) ).getElement( );
				namespace = element.getAttribute( "targetNamespace" );//$NON-NLS-1$
				return namespace;
			}
		}

		return namespace;
	}

	private static boolean isPrimitiveDataType( String dataType )
	{
		if ( WSUtil.isNull( dataType ) )
			return true;

		return getDataTypeList( ).contains( dataType.substring( dataType.indexOf( ":" ) + 1 ).toLowerCase( ) ); //$NON-NLS-1$
	}

	private static List getDataTypeList( )
	{
		if ( WSUtil.isNull( primitiveDataTypeList ) )
			primitiveDataTypeList = new ArrayList( );

		if ( primitiveDataTypeList.isEmpty( ) )
			initDataTypeList( );

		return primitiveDataTypeList;
	}

	/**
	 * Initialize all the primitive dataTypes acceptable
	 */
	private static void initDataTypeList( )
	{
		primitiveDataTypeList.add( "short" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "int" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "float" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "double" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "decimal" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "string" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "boolean" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "datetime " ); //$NON-NLS-1$
		primitiveDataTypeList.add( "date" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "time" ); //$NON-NLS-1$
	}

}
