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
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A utility class that handles all wsdl-involved operation
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
	public static final String NS_KEY_DEFAULT = "m";//$NON-NLS-1$

	private static String defaultNS = NS_KEY_DEFAULT + ":";
	private static Map definitionMap = new HashMap( );

	/**
	 * Get the definition, this is the starting point to browse a operation tree
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

	/**
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

	/**
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
		Port port = service.getPort( opSplit[1] );// port
		BindingOperation bindingOperation = port.getBinding( )
				.getBindingOperation( opSplit[2],// operation
						null,
						null );

		return bindingOperation;
	}

	/**
	 * 
	 * @param wsdlURI
	 * @param operationTrace
	 * @return
	 */
	public static String getSOAPRequestTemplate( String wsdlURI,
			String operationTrace )
	{
		String template = "";
		if ( !checkOperationTrace( operationTrace ) )
			return template;

		Definition definition = getDefinition( wsdlURI );
		if ( WSUtil.isNull( definition ) )
			return template;

		template = buildStart( )
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
		return "\n";
	}

	private static String tab( int num )
	{
		String tabs = "";
		for ( int i = 0; i < num; i++ )
			tabs += "\t";

		return tabs;
	}

	private static String buildNamespaceDeclarations( )
	{
		Map namespaceURIs = new HashMap( );

		checkNamespace( namespaceURIs, NS_KEY_SOAP_ENV, NS_SOAP_ENV );
		checkNamespace( namespaceURIs, NS_KEY_SOAP_ENC, NS_SOAP_ENC );
		checkNamespace( namespaceURIs, NS_KEY_XSI, NS_xsi );
		checkNamespace( namespaceURIs, NS_KEY_XSD, NS_xsd );

		String result = "";
		Set uris = namespaceURIs.keySet( );
		Iterator iterator = uris.iterator( );
		while ( iterator.hasNext( ) )
		{
			String uri = (String) iterator.next( );
			String prefix = (String) namespaceURIs.get( uri );
			result += enter( ) + "xmlns:" + prefix + "=\"" + uri + "\"";//$NON-NLS-1$//$NON-NLS-1$//$NON-NLS-1$
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
		String result = "";
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
			result += enter( )
					+ tab( 2 ) + "<" + defaultNS + operation.getName( ) + " "
					+ getQNameSpace( getNameSpaceRPC( bindingOperation ) )
					+ ">";

			for ( int i = 0; i < parts.size( ); i++ )
			{
				Part part = (Part) parts.get( i );
				List paramNameList = new ArrayList( );
				List paramTypeList = new ArrayList( );

				QName typeName = part.getTypeName( );
				if ( WSUtil.isNull( typeName )
						|| typeName.getNamespaceURI( )
								.equalsIgnoreCase( NS_xsd ) )
				{
					paramNameList.add( part.getName( ) );
					paramTypeList.add( WSUtil.getNonNullString( WSUtil.isNull( typeName )
							? "" : typeName.getLocalPart( ) ) );

					result += buildInputParameters( "",
							paramNameList,
							paramTypeList );
				}
				else
				{
					result += enter( ) + tab( 3 ) + "<" + part.getName( ) + ">";
					addParamComplexType( wsdlURI,
							typeName,
							paramNameList,
							paramTypeList );
					result += buildInputParameters( "",
							paramNameList,
							paramTypeList );
					result += enter( )
							+ tab( 3 ) + "</" + part.getName( ) + ">";
				}
			}

			result += enter( )
					+ tab( 2 ) + "</" + defaultNS + operation.getName( ) + ">";
		}

		return result;
	}

	private static String getNameSpaceRPC( BindingOperation bindingOperation )
	{
		BindingInput bindingInput = bindingOperation.getBindingInput( );
		List extElements = bindingInput.getExtensibilityElements( );
		String nameSpace = "";

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
			return "";
		else
			return "xmlns:" + NS_KEY_DEFAULT + "=\"" + nameSpace + "\"";//$NON-NLS-1$//$NON-NLS-1$//$NON-NLS-1$;
	}

	private static void addParamComplexType( String wsdlURI, QName qName,
			List paramNameList, List paramTypeList )
	{
		String namespace = "";
		Definition definition = getDefinition( wsdlURI );
		Types types = definition.getTypes( );
		List extElements = types.getExtensibilityElements( );
		for ( int i = 0; i < extElements.size( ); i++ )
		{
			if ( extElements.get( i ) instanceof UnknownExtensibilityElement )
			{
				Element element = ( (UnknownExtensibilityElement) extElements.get( i ) ).getElement( );
				List teList = retrieveTargetElements( element, qName );
				for ( int j = 0; j < teList.size( ); j++ )
				{
					Element te = (Element) teList.get( j );
					if ( !WSUtil.isNull( te ) )
					{
						paramNameList.add( te.getAttribute( "name" ) );//$NON-NLS-1$
						paramTypeList.add( te.getAttribute( "type" ) );//$NON-NLS-1$
					}
				}
			}
		}
	}

	private static List retrieveTargetElements( Element element, QName qName )
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
					&& qName.getLocalPart( )
							.equals( nodeMap.getNamedItem( "name" )//$NON-NLS-1$
									.getNodeValue( ) ) )
			{
				retrieveNamedElements( teList, node );
			}
		}

		return teList;
	}

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

	private static String buildInputParameters( String nameSpace,
			List paramNames, List paramTypes )
	{
		String result = "";
		for ( int i = 0; i < paramNames.size( ); i++ )
		{
			String paramName = paramNames.get( i ).toString( );
			String paramType = paramTypes.get( i ).toString( );

			result += enter( )
					+ tab( 3 )
					+ "<"
					+ nameSpace
					+ paramName
					+ " "
					+ NS_KEY_XSI
					+ ":type=\""
					+ ( paramType.indexOf( NS_KEY_XSD ) == -1
							? ( NS_KEY_XSD + ":" ) : "" ) + paramType + "\">&?"
					+ paramName + "?&</" + nameSpace + paramName + ">";
		}

		return result;
	}

	private static String buildBodyDoc( String wsdlURI, String operationTrace )
	{
		String result = "";
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

				List paramNameList = new ArrayList( );
				List paramTypeList = new ArrayList( );

				addParamComplexType( wsdlURI,
						part.getElementName( ),
						paramNameList,
						paramTypeList );

				result += enter( )
						+ tab( 2 )
						+ "<"
						+ defaultNS
						+ part.getElementName( ).getLocalPart( )
						+ " "
						+ getQNameSpace( getNameSpaceDoc( wsdlURI ) )
						+ ">"
						+ buildInputParameters( defaultNS,
								paramNameList,
								paramTypeList ) + enter( ) + tab( 2 ) + "</"
						+ defaultNS + part.getElementName( ).getLocalPart( )
						+ ">";
			}
		}

		return result;
	}

	private static String getNameSpaceDoc( String wsdlURI )
	{
		String namespace = "";
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

}
