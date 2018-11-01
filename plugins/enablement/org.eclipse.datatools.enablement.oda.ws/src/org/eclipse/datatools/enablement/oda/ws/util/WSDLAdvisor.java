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

package org.eclipse.datatools.enablement.oda.ws.util;

import java.util.ArrayList;
import java.util.Collection;
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
import javax.wsdl.extensions.http.HTTPAddress;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.wsdl.extensions.soap.SOAPHeader;
import javax.wsdl.extensions.soap.SOAPOperation;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.ws.i18n.Messages;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ibm.wsdl.extensions.schema.SchemaImpl;
import com.ibm.wsdl.extensions.schema.SchemaImportImpl;

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
	public static final String NAME = "name"; //$NON-NLS-1$
	public static final String TYPE = "type"; //$NON-NLS-1$
	public static final String REF = "ref"; //$NON-NLS-1$
	public static final String BASE = "base"; //$NON-NLS-1$
	public static final String SIMPLE_TYPE = "simpleType"; //$NON-NLS-1$
	public static final String COMPLEX_TYPE = "complexType"; //$NON-NLS-1$
	public static final String CHOICE = "choice"; //$NON-NLS-1$
	private static Map definitionMap = new HashMap( );
	private static List primitiveDataTypeList;

	/**
	 * Gets the definition, this is the starting point to browse a operation
	 * tree
	 * 
	 * @param wsdlURI
	 * @return
	 */
	public static Definition getDefinition( String wsdlURI ) throws WSDLException
	{
		if ( definitionMap.containsKey( wsdlURI ) )
			return (Definition) definitionMap.get( wsdlURI );

		Definition definition;
		WSDLReader reader = WSDLFactory.newInstance( ).newWSDLReader( );
		reader.setFeature( "javax.wsdl.verbose", true ); //$NON-NLS-1$
		reader.setFeature( "javax.wsdl.importDocuments", true ); //$NON-NLS-1$
		definition = reader.readWSDL( null, wsdlURI );

		definitionMap.put( wsdlURI, definition );

		return definition;
	}

	public static Definition getDefinitionWithoutExcpe( String wsdlURI )
	{
		try
		{
			return getDefinition( wsdlURI );
		}
		catch ( WSDLException e )
		{
			return null;
		}
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
		String locationURI = EMPTY_STRING;
		if ( !checkOperationTrace( operationTrace ) )
			return locationURI;

		Definition definition = getDefinitionWithoutExcpe( wsdlURI );
		if ( WSUtil.isNull( definition ) )
			return locationURI;

		String[] opSplit = operationTrace.split( RE_DELIMITER_OPEARTION );
		Service service = definition.getService( new QName( definition.getTargetNamespace( ),
				opSplit[0] ) );// service
		if ( WSUtil.isNull( service ) )
			return null;

		Port port = service.getPort( opSplit[1] );// port
		List extElements = port.getExtensibilityElements( );
		if ( !WSUtil.isNull( extElements ) )
		{
			for ( int i = 0; i < extElements.size( ); i++ )
			{
				if ( extElements.get( i ) instanceof SOAPAddress )
				{
					locationURI = ( (SOAPAddress) extElements.get( i ) ).getLocationURI( );
					break;
				}
				if ( extElements.get( i ) instanceof HTTPAddress )
				{
					locationURI = ( (HTTPAddress) extElements.get( i ) ).getLocationURI( );
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
		String soapActionURI = EMPTY_STRING;
		BindingOperation bindingOperation = getBindingOperation( wsdlURI,
				operationTrace );
		if ( WSUtil.isNull( bindingOperation ) )
			return soapActionURI;

		List extElements = bindingOperation.getExtensibilityElements( );
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
		StringBuffer result = new StringBuffer( );
		if ( WSUtil.isNull( operation ) )
			return result.toString( );

		Element element = operation.getDocumentationElement( );
		if ( !WSUtil.isNull( element ) )
		{
			NodeList nodes = element.getChildNodes( );
			for ( int i = 0; i < nodes.getLength( ); i++ )
			{
				Node node = nodes.item( i );
				if ( node.getNodeType( ) != Node.TEXT_NODE )
					continue;

				result.append( node.getNodeValue( ) );
			}
		}

		return result.toString( );
	}

	private static BindingOperation getBindingOperation( String wsdlURI,
			String operationTrace )
	{
		if ( !checkOperationTrace( operationTrace ) )
			return null;

		Definition definition = getDefinitionWithoutExcpe( wsdlURI );
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
	 * @throws OdaException 
	 */
	public String getSOAPRequestTemplate( String wsdlURI, String operationTrace )
			throws OdaException
	{
		String template = EMPTY_STRING;
		if ( !checkOperationTrace( operationTrace ) )
			return template;

		Definition definition = getDefinitionWithoutExcpe( wsdlURI );
		if ( WSUtil.isNull( definition ) )
			return template;
		String inOrOutput = "in"; //$NON-NLS-1$
		template = buildStart( )
				+ buildHeader( wsdlURI, operationTrace, inOrOutput )
				+ buildBody( wsdlURI, operationTrace, inOrOutput ) + buildEnd( );

		return template;
	}

	/**
	 * Generates the Response template when the system can not get the response
	 * from the target wsdlURI,will use the sample data to generate the template
	 * 
	 * @param wsdlURI
	 * @param operationTrace
	 * @return
	 * @throws OdaException
	 */
	public String getLocalSOAPResponseTemplate( String wsdlURI,
			String operationTrace ) throws OdaException
	{
		String template = EMPTY_STRING;
		if ( !checkOperationTrace( operationTrace ) )
			return template;

		Definition definition = getDefinitionWithoutExcpe( wsdlURI );
		if ( WSUtil.isNull( definition ) )
			return template;
		String inOrOutput = "out"; //$NON-NLS-1$
		template = buildStart( )
				+ buildHeader( wsdlURI, operationTrace, inOrOutput )
				+ buildBody( wsdlURI, operationTrace, inOrOutput ) + buildEnd( );

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
		StringBuffer tabs = new StringBuffer(); //$NON-NLS-1$
		for ( int i = 0; i < num; i++ )
			tabs.append( "\t" ); //$NON-NLS-1$

		return tabs.toString( );
	}

	private static String buildNamespaceDeclarations( )
	{
		Map namespaceURIs = new HashMap( );

		checkNamespace( namespaceURIs, NS_KEY_SOAP_ENV, NS_SOAP_ENV );
		checkNamespace( namespaceURIs, NS_KEY_SOAP_ENC, NS_SOAP_ENC );
		checkNamespace( namespaceURIs, NS_KEY_XSI, NS_XSI );
		checkNamespace( namespaceURIs, NS_KEY_XSD, NS_XSD );

		StringBuffer result = new StringBuffer();
		Set uris = namespaceURIs.keySet( );
		Iterator iterator = uris.iterator( );
		while ( iterator.hasNext( ) )
		{
			String uri = (String) iterator.next( );
			String prefix = (String) namespaceURIs.get( uri );
			result.append( enter( ) + "xmlns:" + prefix + "=\"" + uri + "\"" );//$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}

		return result.toString( ) + ">"; //$NON-NLS-1$
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

	private String buildHeader( String wsdlURI, String operationTrace,
			String inOrOutput )
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
						if ( inOrOutput == "in" )//$NON-NLS-1$
						{
							result += enter( )
									+ tab( 2 )
									+ "<" + NS_DEFAULT + localPart + " " //$NON-NLS-1$//$NON-NLS-2$
									+ getQNameSpace( nameSpace ) + ">"; //$NON-NLS-1$

						}
						else
						{
							result += enter( )
									+ tab( 2 ) + "<" + localPart + " " //$NON-NLS-1$//$NON-NLS-2$
									+ getQNameSpace( nameSpace ) + ">"; //$NON-NLS-1$
						}

						result += buildParametersByList( wsdlURI,
								NS_DEFAULT,
								paramNameList,
								paramTypeList,
								3,
								inOrOutput );

						if ( inOrOutput.equals( "in" ) )//$NON-NLS-1$
						{
							result += enter( )
									+ tab( 2 )
									+ "</" + NS_DEFAULT + localPart + ">"; //$NON-NLS-1$ //$NON-NLS-2$
						}
						else
						{
							result += enter( )
									+ tab( 2 ) + "</" + localPart + ">"; //$NON-NLS-1$ //$NON-NLS-2$

						}
						result += enter( ) + tab( 1 ) + SOAP_HEADER_END;

					}
					break;
				}
			}
		}
		return result;
	}

	private WSNonLeafNode generateTargetNode( String wsdlURI, String localPart )
	{
		Definition definition = getDefinitionWithoutExcpe( wsdlURI );
		Types types = definition.getTypes( );

		if ( types != null && types.getExtensibilityElements( ) != null )
		{
			List extElements = types.getExtensibilityElements( );
			List<Element> elementList = new ArrayList<Element>( );
			for ( int i = 0; i < extElements.size( ); i++ )
			{
				if ( extElements.get( i ) instanceof SchemaImpl )
				{
					elementList.add( ( (SchemaImpl) extElements.get( i ) ).getElement( ) );
					for ( Object value : ( (SchemaImpl) extElements.get( i ) ).getImports( )
							.values( ) )
					{
						if ( value instanceof Collection )
						{
							for ( Object o : (Collection) value )
							{
								if ( o instanceof SchemaImportImpl )
								{
									SchemaImportImpl sii = (SchemaImportImpl) o;
									if ( sii.getReferencedSchema( ) instanceof SchemaImpl )
										elementList.add( ( (SchemaImpl) sii.getReferencedSchema( ) ).getElement( ) );
								}
							}
						}
					}
				}
			}
			String[] parentNode = {
				EMPTY_STRING
			};
			WSNonLeafNode node = generateNode( localPart,
					elementList.toArray( new Element[0] ),
					parentNode,
					null );
			if ( node.getNodeList( ).size( ) >= 0 )
				return node;
		}
		WSNonLeafNode newNode = new WSNonLeafNode( );
		newNode.setName( localPart );
		List lowerLeverList = new ArrayList( );
		newNode.setNodeList( lowerLeverList );
		return newNode;
	}

	/**
	 * Handle the anonymous complex type such as 
	 * <xs:element name="BinParameter" minOccurs="0" maxOccurs="unbounded">
	 *  	<xs:complexType>
	 *  		<xs:sequence>
	 *  			<xs:element name="Name" type="xs:string" /> 
	 * 				<xs:element name="Value" type="xs:string" /> 
	 * 			</xs:sequence>
	 * 		</xs:complexType>
	 * </xs:element>

	 * @param node
	 * @param element
	 * @param parentNode
	 * @param anonymousComplexParentNode
	 * @return
	 */

	private WSNonLeafNode handleAnonymousComplexNode( Node node,
			Element[] element, String[] parentNode,
			String[] anonymousComplexParentNode )
	{
		WSNonLeafNode newNode = new WSNonLeafNode( );
		List lowerLeverList = new ArrayList( );
		Node nodeName = node.getAttributes( ).getNamedItem( NAME );
		String stringName = null;
		if( nodeName != null )
		{
			stringName = nodeName.getNodeValue( );
		}
		else
		{
			stringName = node.getNodeName( );
		}
		
		newNode.setName( stringName );

		for ( int k = 0; k < anonymousComplexParentNode.length; k++ )
		{
			if ( stringName.equals( anonymousComplexParentNode[k].toString( ) ) )
			{
				newNode.setNodeList( lowerLeverList );
				return newNode;
			}
		}

		String[] subNodeParents = generateSubNodeParents( stringName, parentNode );
		String[] subAnonymousNodeParents = generateSubNodeParents( stringName,
				anonymousComplexParentNode );

		Node middleNode = getSignificantNode( node );

		if ( middleNode != null )
		{
			NodeList subs = middleNode.getChildNodes( );
			for ( int i = 0; i < subs.getLength( ); i++ )
			{
				Node sub = subs.item( i );
				if ( sub.getNodeType( ) != Node.ELEMENT_NODE )
					continue;
				NamedNodeMap nodeMap = sub.getAttributes( );

				// handle the lower level node

				if ( !WSUtil.isNull( nodeMap.getNamedItem( NAME ) ) )
				{

					handleNodes( element,
							lowerLeverList,
							subNodeParents,
							subAnonymousNodeParents,
							sub );

				}
				else
				{
					if ( !WSUtil.isNull( nodeMap.getNamedItem( REF ) ) )
					{
						addRef( lowerLeverList, sub, element, subNodeParents );
					}
					else
					{
						handleAnonymousComplexNode( sub,
								element,
								subNodeParents,
								subAnonymousNodeParents );
					}

				}

			}
		}

		newNode.setNodeList( lowerLeverList );
		return newNode;
	}

	private boolean isComplexType( Node node, Element[] element )
	{
		NodeList nodes = getChildNodes( element );
		for ( int i = 0; i < nodes.getLength( ); i++ )
		{
			Node XMLNode = nodes.item( i );
			if ( XMLNode.getNodeType( ) != Node.ELEMENT_NODE )
				continue;

			NamedNodeMap nodeMap = XMLNode.getAttributes( );
			if ( !WSUtil.isNull( nodeMap ) )
			{
				if ( !WSUtil.isNull( nodeMap.getNamedItem( NAME ) )
						&& !WSUtil.isNull( node.getAttributes( )
								.getNamedItem( TYPE ) ) )
				{
					if ( getParamTypeLocalPart( node.getAttributes( )
							.getNamedItem( TYPE )
							.getNodeValue( ) ).equalsIgnoreCase( nodeMap.getNamedItem( NAME )
							.getNodeValue( ) ) )
					{
						if ( getParamTypeLocalPart( XMLNode.getNodeName( ) ).equals( COMPLEX_TYPE ) )
						{
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	private boolean isNestedSimpleType( Node XMLNode )
	{
		NodeList nodes = XMLNode.getChildNodes( );
		for ( int i = 0; i < nodes.getLength( ); i++ )
		{
			Node node = nodes.item( i );
			if ( node.getNodeType( ) != Node.ELEMENT_NODE )
				continue;

			if ( getParamTypeLocalPart( node.getNodeName( ) ).equals( SIMPLE_TYPE ) )
			{
				return true;
			}
		}
		return false;
	}

	private String getSimpleTypeBase( String wsdlURI, String typeName )
	{
		Definition definition = getDefinitionWithoutExcpe( wsdlURI );
		Types types = definition.getTypes( );

		if ( types != null && types.getExtensibilityElements( ) != null )
		{
			List extElements = types.getExtensibilityElements( );
			List<Element> elementList = new ArrayList<Element>( );
			for ( int i = 0; i < extElements.size( ); i++ )
			{
				if ( extElements.get( i ) instanceof SchemaImpl )
				{
					elementList.add( ( (SchemaImpl) extElements.get( i ) ).getElement( ) );
				}
			}
			
			NodeList nodes = getChildNodes( elementList.toArray( new Element[0] ) );
			Node XMLNode = findElementNodeByName( nodes, typeName );
			if ( XMLNode != null )
			{
				// skip the middle node such as - <xs:sequence>, get
				// the
				// significant node
				if ( getParamTypeLocalPart( XMLNode.getNodeName( ) ).equals( SIMPLE_TYPE ) )
				{
					return getSimpleTypeBase( XMLNode );
				}
			}
		}
		return null;
	}
	
	private String getSimpleTypeBase( Node node )
	{
		NodeList subNodes = node.getChildNodes( );
		for ( int i = 0; i < subNodes.getLength( ); i++ )
		{
			Node subNode = subNodes.item( i );
			NamedNodeMap subNodeMap = subNode.getAttributes( );
			if ( !WSUtil.isNull( subNodeMap ) && !WSUtil.isNull( subNodeMap.getNamedItem( BASE ) ) )
			{
				return subNodeMap.getNamedItem( BASE ).getNodeValue( );
			}
		}
		return null;
	}
	
	private void addNestedSimpleType( List lowerLeverList, Node node )
	{
		NodeList subs = node.getChildNodes( );
		for ( int i = 0; i < subs.getLength( ); i++ )
		{
			Node sub = subs.item( i );
			if ( sub.getNodeType( ) != Node.ELEMENT_NODE )
				continue;
			if ( getParamTypeLocalPart( sub.getNodeName( ) ).equals( SIMPLE_TYPE ) )
			{
				NodeList subNodes = sub.getChildNodes( );
				for ( int j = 0; j < subs.getLength( ); j++ )
				{
					Node subNode = subNodes.item( i );
					if ( subNode.getNodeType( ) != Node.ELEMENT_NODE )
						continue;

					NamedNodeMap subNodeMap = subNode.getAttributes( );
					if ( !WSUtil.isNull( subNodeMap.getNamedItem( BASE ) )
							&& !WSUtil.isNull( subNode.getParentNode( )
									.getParentNode( )
									.getAttributes( )
									.getNamedItem( NAME ) ) )
					{
						WSLeafNode leafNode = new WSLeafNode( );
						leafNode.setName( subNode.getParentNode( )
								.getParentNode( )
								.getAttributes( )
								.getNamedItem( NAME )
								.getNodeValue( ) );

						leafNode.setType( subNodeMap.getNamedItem( BASE )
								.getNodeValue( ) );

						lowerLeverList.add( leafNode );
						break;
					}

				}

			}
		}
	}

	private boolean isAnonymousComplexType( Node XMLNode )
	{
		NodeList nodes = XMLNode.getChildNodes( );
		for ( int i = 0; i < nodes.getLength( ); i++ )
		{
			Node node = nodes.item( i );
			if ( node.getNodeType( ) != Node.ELEMENT_NODE )
				continue;

			if ( getParamTypeLocalPart( node.getNodeName( ) ).equals( COMPLEX_TYPE )
					&& !WSUtil.isNull( XMLNode.getAttributes( )
							.getNamedItem( NAME ) ) )
			{
				return true;
			}
		}
		return false;
	}

	private Node getSignificantNode( Node XMLNode )
	{
		NodeList nodes = XMLNode.getChildNodes( );
		for ( int i = 0; i < nodes.getLength( ); i++ )
		{
			Node node = nodes.item( i );
			if ( node.getNodeType( ) != Node.ELEMENT_NODE )
				continue;

			NamedNodeMap nodeMap = node.getAttributes( );
			if ( !WSUtil.isNull( nodeMap ) )
			{
				if ( !WSUtil.isNull( nodeMap.getNamedItem( NAME ) )
						|| !WSUtil.isNull( nodeMap.getNamedItem( REF ) ) )
				{
					return getSignificantParentNode( node );
				}
				else
				{
					return getSignificantNode( node );
				}

			}
		}
		return null;
	}

	private Node getSignificantParentNode( Node node )
	{
		Node parentNode = node.getParentNode( );
		while( isChoiceNode( parentNode ) )
		{
			if( !WSUtil.isNull( parentNode.getParentNode( ) ) )
			{
				parentNode = parentNode.getParentNode( );
			}
			else
			{
				return parentNode;
			}
		}
		return parentNode;
	}
	
	private String[] generateSubNodeParents( String nodeName,
			String[] parentNode )
	{
		String[] subNodeParents = new String[parentNode.length + 1];
		System.arraycopy( parentNode, 0, subNodeParents, 0, parentNode.length );
		subNodeParents[subNodeParents.length - 1] = nodeName;
		return subNodeParents;

	}

	private NodeList getChildNodes( Element[] element )
	{
		return new MultipleNodeList( element );
	}
	
	public WSNonLeafNode generateNode( String nodeName, Element[] element,
			String[] parentNode, String complexTypeName )
	{
		String localPart;
		if ( complexTypeName != null )
		{
			localPart = complexTypeName;
		}
		else
		{
			localPart = nodeName;
		}
		List lowerLeverList = new ArrayList( );
		WSNonLeafNode newNode = new WSNonLeafNode( );
		newNode.setName( nodeName );

		for ( int k = 0; k < parentNode.length; k++ )
		{
			if ( nodeName.equals( parentNode[k].toString( ) ) )
			{
				newNode.setNodeList( lowerLeverList );
				return newNode;
			}
		}

		String[] subNodeParents = generateSubNodeParents( localPart, parentNode );
		NodeList nodes = getChildNodes( element );
		Node XMLNode = null;
		if ( complexTypeName != null )
		{
			XMLNode = findComplexTypeNodeByName( nodes, localPart );;
		}
		if( XMLNode == null )
			XMLNode = findElementNodeByName( nodes, localPart );
		if ( XMLNode != null )
		{
			// skip the middle node such as - <xs:sequence>, get
			// the
			// significant node
			Node middleNode = getSignificantNode( XMLNode );

			genetateLowerLeverList( element,
					lowerLeverList,
					subNodeParents,
					middleNode,
					null );
		}

		newNode.setNodeList( lowerLeverList );

		return newNode;

	}

	private Node findElementNodeByName( NodeList nodes, String name )
	{
		for ( int i = 0; i < nodes.getLength( ); i++ )
		{
			Node XMLNode = nodes.item( i );
//			if ( XMLNode.getNodeType( ) != Node.ELEMENT_NODE )
//				continue;

			NamedNodeMap XMLNodeNodeMap = XMLNode.getAttributes( );
			if ( !WSUtil.isNull( XMLNodeNodeMap ) )
			{
				if ( !WSUtil.isNull( XMLNodeNodeMap.getNamedItem( NAME ) ) )
				{
					if ( name.equalsIgnoreCase( XMLNodeNodeMap.getNamedItem( NAME )
							.getNodeValue( ) ) )
					{
						return XMLNode;
					}

				}
			}

		}
		return null;
	}
	
	private Node findComplexTypeNodeByName( NodeList nodes, String name )
	{
		for ( int i = 0; i < nodes.getLength( ); i++ )
		{
			Node XMLNode = nodes.item( i );

			NamedNodeMap XMLNodeNodeMap = XMLNode.getAttributes( );
			if ( !WSUtil.isNull( XMLNodeNodeMap ) )
			{
				if ( !WSUtil.isNull( XMLNodeNodeMap.getNamedItem( NAME ) ) )
				{
					if ( name.equalsIgnoreCase( XMLNodeNodeMap.getNamedItem( NAME )
							.getNodeValue( ) ) )
					{
						if( XMLNode.getLocalName( ).equalsIgnoreCase( "complexType" ) )
						return XMLNode;
					}

				}
			}

		}
		return null;
	}

	private void genetateLowerLeverList( Element[] element, List lowerLeverList,
			String[] subNodeParents, Node middleNode,
			String[] anonymousComplexParentNode )
	{
		if ( middleNode == null )
		{
			return;
		}
		NodeList subs = middleNode.getChildNodes( );
		for ( int j = 0; j < subs.getLength( ); j++ )
		{
			Node sub = subs.item( j );

			if ( sub == null )
			{
				return;
			}
			if( isChoiceNode( sub ) )
			{
				genetateLowerLeverList( element,
						lowerLeverList,
						subNodeParents,
						sub,
						anonymousComplexParentNode );
			}
			if ( sub.getNodeType( ) != Node.ELEMENT_NODE )
				continue;

			handleNodes( element,
					lowerLeverList,
					subNodeParents,
					anonymousComplexParentNode,
					sub );
		}
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private boolean isChoiceNode( Node node )
	{
		return getParamTypeLocalPart( node.getNodeName( ) ).equals( CHOICE );
	}

	private void handleNodes( Element[] element, List lowerLeverList,
			String[] subNodeParents, String[] anonymousComplexParentNode,
			Node sub )
	{
		NamedNodeMap nodeMap = sub.getAttributes( );
		// handle with simple and complex type
		if ( !WSUtil.isNull( nodeMap.getNamedItem( TYPE ) )
				&& nodeMap.getNamedItem( TYPE ).getNodeValue( ) != REF )
		{

			String XMLType = getParamTypeLocalPart( nodeMap.getNamedItem( TYPE )
					.getNodeValue( ) );

			// simple type with <name= ,
			// type='isPrimitiveDataType'>
			if ( isPrimitiveDataType( XMLType ) )
			{
				addSimpleType( lowerLeverList, nodeMap );
			}
			else
			{
				// complex type with <name= ,
				// type=>
				if ( isComplexType( sub, element ) )
				{
					addComplexType( lowerLeverList,
							nodeMap,
							element,
							subNodeParents );

				}
				else
				// simple type with <name= ,
				// type='NonPrimitiveDataType'>
				{
					addSimpleType( lowerLeverList, nodeMap );
				}
			}
		}
		else if ( !WSUtil.isNull( nodeMap.getNamedItem( REF ) ) )
		{
			addRef( lowerLeverList, sub, element, subNodeParents );
		}
		else
		{
			// handle with anonymous simple
			// type
			// such as
			// <name><simpletype base=>
			if ( isNestedSimpleType( sub ) )
			{
				addNestedSimpleType( lowerLeverList, sub );
			}

			// handle with anonymous complex
			// type
			if ( isAnonymousComplexType( sub ) )
			{
				if ( anonymousComplexParentNode == null )
					anonymousComplexParentNode = new String[]{
						EMPTY_STRING
					};
				lowerLeverList.add( handleAnonymousComplexNode( sub,
						element,
						subNodeParents,
						anonymousComplexParentNode ) );
			}

		}
	}

	private void addSimpleType( List lowerLeverList, NamedNodeMap nodeMap )
	{
		String XMLType = getParamTypeLocalPart( nodeMap.getNamedItem( TYPE )
				.getNodeValue( ) );

		WSLeafNode leafNode = new WSLeafNode( );
		leafNode.setName( nodeMap.getNamedItem( NAME ).getNodeValue( ) );
		leafNode.setType( XMLType );
		lowerLeverList.add( leafNode );
	}

	private void addComplexType( List lowerLeverList, NamedNodeMap nodeMap,
			Element[] element, String[] parentNode )
	{
		String nodeName = getParamTypeLocalPart( nodeMap.getNamedItem( NAME )
				.getNodeValue( ) );

		String complexTyneName = getParamTypeLocalPart( nodeMap.getNamedItem( TYPE )
				.getNodeValue( ) );

		lowerLeverList.add( generateNode( nodeName,
				element,
				parentNode,
				complexTyneName ) );
	}

	private void addRef( List lowerLeverList, Node node, Element[] element,
			String[] parentNode )
	{
		// judge whether is a simple-type kind ref 
		NodeList nodes = getChildNodes( element );
		for ( int i = 0; i < nodes.getLength( ); i++ )
		{
			Node XMLNode = nodes.item( i );
			if ( XMLNode.getNodeType( ) != Node.ELEMENT_NODE )
				continue;

			NamedNodeMap nodeMap = XMLNode.getAttributes( );
			if ( !WSUtil.isNull( nodeMap )
					&& !WSUtil.isNull( nodeMap.getNamedItem( NAME ) ) )
			{
				if ( getParamTypeLocalPart( node.getAttributes( )
						.getNamedItem( REF )
						.getNodeValue( ) ).equalsIgnoreCase( nodeMap.getNamedItem( NAME )
						.getNodeValue( ) ) )
				{
					if ( isNestedSimpleType( XMLNode ) )
					{
						addNestedSimpleType( lowerLeverList, XMLNode );
						return;
					}
				}
			}
		}

		// otherwise it's a complex-type kind ref
		NamedNodeMap nodeMap = node.getAttributes( );
		String nodeName = getParamTypeLocalPart( nodeMap.getNamedItem( REF )
				.getNodeValue( ) );

		lowerLeverList.add( generateNode( nodeName, element, parentNode, null ) );

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
	 * @param name
	 * @param tabCount
	 * 
	 * @return
	 */
	private String buildInputParameters( String wsdlURI, String nameSpace,
			String name, int tabCount )
	{
		String result = EMPTY_STRING;
		WSNonLeafNode newNode = generateTargetNode( wsdlURI, name );
		setParentName( newNode );
		result = builderRequestParameters( newNode, nameSpace, tabCount );
		return result;
	}

	private void setParentName( WSNonLeafNode rootNode )
	{
		List<WSLeafNode> leafNodeList = new ArrayList<WSLeafNode>( );
		getAllLeafNode( rootNode, leafNodeList );
		boolean existSameName;
		for ( int i = 0; i < leafNodeList.size( ); i++ )
		{
			existSameName = false;
			for ( int j = 0; j < leafNodeList.size( ); j++ )
			{
				if ( leafNodeList.get( i )
						.getName( )
						.equals( leafNodeList.get( j ).getName( ) ) && j != i )
				{
					existSameName = true;
					break;
				}
			}
			if( !existSameName )
			{
				leafNodeList.get( i ).setPrefix( "" );
			}
		}
	}
	
	private void getAllLeafNode( WSNonLeafNode node, List<WSLeafNode> leafNodeList )
	{
		List nodeList = node.getNodeList( );
		for ( int i = 0; i < nodeList.size( ); i++ )
		{
			if ( nodeList.get( i ) instanceof WSLeafNode )
			{
				( (WSLeafNode) nodeList.get( i ) ).setPrefix( node.getName( ) + "." );
				leafNodeList.add( (WSLeafNode) nodeList.get( i ) );
			}
			else if ( nodeList.get( i ) instanceof WSNonLeafNode )
			{
				getAllLeafNode( (WSNonLeafNode) nodeList.get( i ), leafNodeList );
			}
		}
	}
	
	private String builderRequestParameters( WSNonLeafNode newNode,
			String nameSpace, int tabCount )
	{
		String result = EMPTY_STRING;
		List nodeList = newNode.getNodeList( );
		for ( int i = 0; i < nodeList.size( ); i++ )
		{
			if ( nodeList.get( i ) instanceof WSLeafNode )
			{
				WSLeafNode leafnode = (WSLeafNode) nodeList.get( i );

				result += enter( )
						+ tab( tabCount )
						+ "<" + nameSpace + leafnode.getName( ) //$NON-NLS-1$
						+ buildParamType( leafnode.getType( ) )
						+ ">&?" + leafnode.getPrefix( ) + leafnode.getName( ) //$NON-NLS-1$
						+ "?&</" + nameSpace + leafnode.getName( ) + ">"; //$NON-NLS-1$ //$NON-NLS-2$

			}
			else if ( nodeList.get( i ) instanceof WSNonLeafNode )
			{
				WSNonLeafNode nonLeafnode = (WSNonLeafNode) nodeList.get( i );

				result += enter( )
						+ tab( tabCount )
						+ "<" + nameSpace + nonLeafnode.getName( ) + ">"; //$NON-NLS-1$//$NON-NLS-2$

				result += builderRequestParameters( nonLeafnode,
						nameSpace,
						tabCount + 1 );

				result += enter( )
						+ tab( tabCount )
						+ "</" + nameSpace + nonLeafnode.getName( ) + ">"; //$NON-NLS-1$//$NON-NLS-2$

			}
		}
		return result;
	}

	private String builderResponseParameters( WSNonLeafNode newNode )
	{
		StringBuffer result = new StringBuffer();
		List nodeList = newNode.getNodeList( );
		for ( int i = 0; i < nodeList.size( ); i++ )
		{
			if ( nodeList.get( i ) instanceof WSLeafNode )
			{
				WSLeafNode leafnode = (WSLeafNode) nodeList.get( i );
				result.append( enter( ) + "<" + leafnode.getName( ) + ">" //$NON-NLS-1$ //$NON-NLS-2$
						+ "</" + leafnode.getName( ) + ">" ); //$NON-NLS-1$ //$NON-NLS-2$		
			}
			else if ( nodeList.get( i ) instanceof WSNonLeafNode )
			{
				WSNonLeafNode nonLeafnode = (WSNonLeafNode) nodeList.get( i );
				result.append( enter( ) + "<" + nonLeafnode.getName( ) + ">" );//$NON-NLS-1$ //$NON-NLS-2$
				result.append( builderResponseParameters( nonLeafnode ) );
				result.append( enter( ) + "</" + nonLeafnode.getName( ) + ">" );//$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return result.toString( );
	}

	/**
	 * Output response parameters
	 * 
	 * @param wsdlURI
	 * @param name
	 * 
	 * @return
	 */
	private String buildOutputParameters( String wsdlURI, String name )
	{
		String result = EMPTY_STRING;

		WSNonLeafNode newNode = generateTargetNode( wsdlURI, name );

		result = builderResponseParameters( newNode );

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

	/**
	 * Generate request XML body when inOrOutput is value "in", otherwise,
	 * Generate response XML body
	 * 
	 * @param wsdlURI
	 * @param operationTrace
	 * @param inOrOutput(value
	 *            is "in" or "out")
	 * @throws OdaException 
	 */
	private String buildBody( String wsdlURI, String operationTrace,
			String inOrOutput ) throws OdaException
	{
		String result = enter( ) + tab( 1 ) + SOAP_BODY_START;

		if ( isRPC( wsdlURI, operationTrace ) )
		{
			result += buildBodyRPC( wsdlURI, operationTrace, inOrOutput );
		}
		else
		{
			result += buildBodyDoc( wsdlURI, operationTrace, inOrOutput );
		}

		return result;
	}

	private static boolean isRPC( String wsdlURI, String operationTrace )
			throws OdaException
	{
		boolean isRPC = false;

		Definition definition = getDefinitionWithoutExcpe( wsdlURI );
		String[] opSplit = operationTrace.split( RE_DELIMITER_OPEARTION );
		Service service = definition.getService( new QName( definition.getTargetNamespace( ),
				opSplit[0] ) );// service
		if ( service == null )
		{
			throw new OdaException( Messages.getString( "service.notexist" ) );
		}
		Port port = service.getPort( opSplit[1] );// port
		if ( port == null )
		{
			throw new OdaException( Messages.getString( "port.notexist" ) );
		}
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

	private String buildBodyRPC( String wsdlURI, String operationTrace,
			String inOrOutput )
	{
		String result = EMPTY_STRING;

		BindingOperation bindingOperation = getBindingOperation( wsdlURI,
				operationTrace );
		if ( WSUtil.isNull( bindingOperation ) )
			return result;

		Operation operation = bindingOperation.getOperation( );
		List partOrder = operation.getParameterOrdering( );
		List parts = null;
		if( inOrOutput.equals( "in" ) ) //$NON-NLS-1$
		{
			parts = operation.getInput( )
				.getMessage( )
				.getOrderedParts( partOrder );
		}
		else
		{
			parts = operation.getOutput( )
			.getMessage( )
			.getOrderedParts( null );
		}

		if ( !WSUtil.isNull( parts ) && !parts.isEmpty( ) )
		{
			List paramNameList = new ArrayList( );
			List paramTypeList = new ArrayList( );
			for ( int i = 0; i < parts.size( ); i++ )
			{
				Part part = (Part) parts.get( i );

				QName typeName = part.getTypeName( );
				paramNameList.add( part.getName( ) );
				paramTypeList.add( typeName == null ? EMPTY_STRING
						: typeName.getLocalPart( ) );
			}

			if ( inOrOutput.equals( "in" ) ) //$NON-NLS-1$
			{
				result += enter( )
						+ tab( 2 )
						+ "<" + NS_DEFAULT + operation.getName( ) + " " //$NON-NLS-1$ //$NON-NLS-2$
						+ getQNameSpace( getNameSpaceRPC( bindingOperation ) )
						+ ">"; //$NON-NLS-1$
			}
			else
			{
				result += enter( )
						+ tab( 2 ) + "<" + operation.getName( ) + " " //$NON-NLS-1$ //$NON-NLS-2$
						+ getQNameSpace( getNameSpaceRPC( bindingOperation ) )
						+ ">"; //$NON-NLS-1$
			}

			result += buildParametersByList( wsdlURI,
					EMPTY_STRING,
					paramNameList,
					paramTypeList,
					3,
					inOrOutput );
			if ( inOrOutput == "in" ) //$NON-NLS-1$
			{
				result += enter( )
						+ tab( 2 )
						+ "</" + NS_DEFAULT + operation.getName( ) + ">"; //$NON-NLS-1$ //$NON-NLS-2$
			}
			else
			{
				result += enter( )
						+ tab( 2 ) + "</" + operation.getName( ) + ">"; //$NON-NLS-1$ //$NON-NLS-2$
			}
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

	private String buildBodyDoc( String wsdlURI, String operationTrace,
			String inOrOutput )
	{
		String result = EMPTY_STRING;
		BindingOperation bindingOperation = getBindingOperation( wsdlURI,
				operationTrace );
		if ( WSUtil.isNull( bindingOperation ) )
			return result;

		Operation operation = bindingOperation.getOperation( );
		List partOrder = operation.getParameterOrdering( );
		List parts;
		if ( inOrOutput == "in" ) //$NON-NLS-1$
		{
			parts = operation.getInput( )
					.getMessage( )
					.getOrderedParts( partOrder );
		}
		else
		{
			parts = operation.getOutput( )
					.getMessage( )
					.getOrderedParts( partOrder );
		}

		if ( !WSUtil.isNull( parts ) && !parts.isEmpty( ) )
		{
			for ( int i = 0; i < parts.size( ); i++ )
			{
				Part part = (Part) parts.get( i );
				if ( WSUtil.isNull( part.getName( ) ) && WSUtil.isNull( part.getElementName( ) ) && WSUtil.isNull( part.getTypeName( ) ) )
					continue;
				if ( !WSUtil.isNull( part.getElementName( ) ) )
				{

					if ( inOrOutput == "in" ) //$NON-NLS-1$
					{
						result = compositeInputBodyDoc( wsdlURI,
								part.getElementName( ).getLocalPart( ),
								result );
					}
					else
					{
						result = compositeOutputBodyDoc( wsdlURI,
								part.getElementName( ).getLocalPart( ),
								result );
					}
				}
				else
				{
					if ( inOrOutput.equals( "in" ) )//$NON-NLS-1$
					{
						result += compositeSimpleInputParameter( part.getName( ),
								part.getTypeName( ).getLocalPart( ) );
					}
					else
					{
						result += compositeSimpleOutputParameter( part.getName( ) );
					}
				}
			}
		}

		return result;
	}

	private String compositeSimpleInputParameter( String name, String type )
	{
		String nameSpace = NS_DEFAULT;
		int tabCount = 3;
		String result = enter( )
				+ tab( tabCount ) + "<" + nameSpace + name //$NON-NLS-1$
				+ buildParamType( type )
				+ ">&?" + name //$NON-NLS-1$
				+ "?&</" + nameSpace + name + ">";  //$NON-NLS-1$//$NON-NLS-2$
		return result;
	}
	
	private String compositeSimpleOutputParameter( String name )
	{
		String result = enter( ) + tab( 2 ) + "<" + name + ">"; //$NON-NLS-1$ //$NON-NLS-2$
		
		result += enter( ) + tab( 2 ) + "</" //$NON-NLS-1$
				+ name + ">"; //$NON-NLS-1$	

		return result;
	}
	
	private String compositeInputBodyDoc( String wsdlURI, String name,
			String result )
	{
		result += enter( ) + tab( 2 ) + "<" + NS_DEFAULT //$NON-NLS-1$
				+ name + " " //$NON-NLS-1$
				+ getQNameSpace( getNameSpaceDoc( wsdlURI ) ) + ">"; //$NON-NLS-1$
		result += buildInputParameters( wsdlURI, NS_DEFAULT, name, 3 );
		result += enter( ) + tab( 2 ) + "</" + NS_DEFAULT //$NON-NLS-1$
				+ name + ">"; //$NON-NLS-1$

		return result;
	}

	private String compositeOutputBodyDoc( String wsdlURI, String name,
			String result )
	{
		result += enter( ) + tab( 2 ) + "<" + name + ">"; //$NON-NLS-1$ //$NON-NLS-2$
		result += buildOutputParameters( wsdlURI, name );

		result += enter( ) + tab( 2 ) + "</" //$NON-NLS-1$
				+ name + ">"; //$NON-NLS-1$	

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
		Definition definition = getDefinitionWithoutExcpe( wsdlURI );
		Types types = definition.getTypes( );
		List extElements = types.getExtensibilityElements( );
		for ( int i = 0; i < extElements.size( ); i++ )
		{
			if ( extElements.get( i ) instanceof SchemaImpl )
			{
				Element element = ( (SchemaImpl) extElements.get( i ) ).getElement( );
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
	 * @param inOrOutput
	 * 
	 * @return
	 */
	private String buildParametersByList( String wsdlURI,
			String nameSpace, List paramNames, List paramTypes, int tabCount,
			String inOrOutput )
	{
		String result = EMPTY_STRING;
		for ( int i = 0; i < paramNames.size( ); i++ )
		{
			String paramName = paramNames.get( i ).toString( );
			String paramType = paramTypes.get( i ).toString( );

			// complexType reference
			if ( !isPrimitiveDataType( paramType ) )
			{
				
				WSNonLeafNode newNode = generateTargetNode( wsdlURI, paramType );

				// temporarily solution to handle simpleType
				if ( newNode.getNodeList() == null || newNode.getNodeList().size( ) == 0 )
				{
					if ( inOrOutput == "in" ) //$NON-NLS-1$
					{
						String simpleType = getSimpleTypeBase( wsdlURI, paramType );
						if ( simpleType == null )
						{
							result += enter( )
									+ tab( tabCount )
									+ "<" + nameSpace + paramName //$NON-NLS-1$
									+ ">&?" + paramName //$NON-NLS-1$
									+ "?&</" + nameSpace + paramName + ">"; //$NON-NLS-1$ //$NON-NLS-2$
						}
						else
						{
							result += enter( )
									+ tab( tabCount )
									+ "<" + nameSpace + paramName //$NON-NLS-1$
									+ buildParamType( simpleType )
									+ ">&?" + paramName //$NON-NLS-1$
									+ "?&</" + nameSpace + paramName + ">"; //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
					else
					{
						result += enter( ) + "<" + paramName + ">" //$NON-NLS-1$  //$NON-NLS-2$						
								+ "</" + paramName + ">"; //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
				else
				{
					if ( inOrOutput == "in" ) //$NON-NLS-1$
					{
						result += enter( )
								+ tab( tabCount )
								+ "<" + nameSpace + paramName + ">"; //$NON-NLS-1$//$NON-NLS-2$
						result += buildParametersByTypeTree( wsdlURI,
								nameSpace,
								newNode,
								tabCount + 1,
								inOrOutput );
						result += enter( )
								+ tab( tabCount )
								+ "</" + nameSpace + paramName + ">"; //$NON-NLS-1$//$NON-NLS-2$
					}
					else
					{
						result += enter( ) + "<" + paramName + ">"; //$NON-NLS-1$//$NON-NLS-2$
						result += buildParametersByTypeTree( wsdlURI,
								nameSpace,
								newNode,
								tabCount,
								inOrOutput );
						result += enter( ) + "</" + paramName + ">"; //$NON-NLS-1$//$NON-NLS-2$
					}
				}
			}
			else
			{
				if ( inOrOutput.equals( "in" ) ) //$NON-NLS-1$
				{
					result += enter( )
							+ tab( tabCount ) + "<" + nameSpace + paramName //$NON-NLS-1$
							+ buildParamType( paramType ) + ">&?" + paramName //$NON-NLS-1$
							+ "?&</" + nameSpace + paramName + ">"; //$NON-NLS-1$ //$NON-NLS-2$
				}
				else
				{
					result += enter( ) + "<" + paramName + ">" //$NON-NLS-1$ //$NON-NLS-2$					
							+ "</" + paramName + ">"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}

		return result;
	}

	/**
	 * 
	 * @param wsdlURI
	 * @param nameSpace
	 * @param typeTree
	 * @param tabCount
	 * @param inOrOutput
	 * @return
	 */
	private String buildParametersByTypeTree( String wsdlURI,
			String nameSpace, WSNonLeafNode typeTree, int tabCount,
			String inOrOutput )
	{
		String result = EMPTY_STRING;
		List subNode = typeTree.getNodeList();
		for( int i=0; i<subNode.size(); i++ )
		{
			if ( subNode.get( i ) instanceof WSLeafNode )
			{
				WSLeafNode leafnode = (WSLeafNode) subNode.get( i );
				result += buildParametersByType( wsdlURI,
						nameSpace, leafnode.getName(), leafnode.getType(), tabCount,
						inOrOutput );
			}
			else if ( subNode.get( i ) instanceof WSNonLeafNode )
			{
				WSNonLeafNode nonLeafnode = (WSNonLeafNode) subNode.get( i );

				if ( inOrOutput.equals( "in" ) )  //$NON-NLS-1$
				{
					result += enter( )
							+ tab( tabCount )
							+ "<" + nameSpace + nonLeafnode.getName() + ">"; //$NON-NLS-1$//$NON-NLS-2$
					result += buildParametersByTypeTree( wsdlURI,
							nameSpace,
							nonLeafnode,
							tabCount + 1,
							inOrOutput );
					result += enter( )
							+ tab( tabCount )
							+ "</" + nameSpace + nonLeafnode.getName() + ">"; //$NON-NLS-1$//$NON-NLS-2$
				}
				else
				{
					result += enter( ) + "<" + nonLeafnode.getName() + ">"; //$NON-NLS-1$//$NON-NLS-2$
					result += buildParametersByTypeTree( wsdlURI,
							nameSpace,
							nonLeafnode,
							tabCount,
							inOrOutput );
					result += enter( ) + "</" + nonLeafnode.getName() + ">"; //$NON-NLS-1$//$NON-NLS-2$
				}

			}
		}
	
		return result;
	}

	/**
	 * 
	 * @param wsdlURI
	 * @param nameSpace
	 * @param paramName
	 * @param paramType
	 * @param tabCount
	 * @param inOrOutput
	 * @return
	 */
	private String buildParametersByType( String wsdlURI,
			String nameSpace, String paramName, String paramType, int tabCount,
			String inOrOutput )
	{
		String result = EMPTY_STRING;
		if ( !isPrimitiveDataType( paramType ) )
		{
			if ( inOrOutput == "in" ) //$NON-NLS-1$
			{
				result += enter( )
						+ tab( tabCount ) + "<" + nameSpace + paramName //$NON-NLS-1$
						+ buildParamType( paramType ) + ">&?" + paramName //$NON-NLS-1$
						+ "?&</" + nameSpace + paramName + ">"; //$NON-NLS-1$ //$NON-NLS-2$
			}
			else
			{
				result += enter( ) + "<" + paramName + ">" //$NON-NLS-1$ //$NON-NLS-2$					
						+ "</" + paramName + ">"; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		else
		{
			if ( inOrOutput.equals( "in" ) ) //$NON-NLS-1$
			{
				result += enter( )
						+ tab( tabCount ) + "<" + nameSpace + paramName //$NON-NLS-1$ 
						+ ">&?" + paramName //$NON-NLS-1$
						+ "?&</" + nameSpace + paramName + ">";  //$NON-NLS-1$//$NON-NLS-2$
			}
			else
			{
				result += enter( ) + "<" + paramName + ">" //$NON-NLS-1$ //$NON-NLS-2$ 					
						+ "</" + paramName + ">";  //$NON-NLS-1$//$NON-NLS-2$
			}
		}
		return result;
	}
	
	private static String getNameSpaceDoc( String wsdlURI )
	{
		String namespace = EMPTY_STRING;
		Definition definition = getDefinitionWithoutExcpe( wsdlURI );
		Types types = definition.getTypes( );
		if( types == null )
			return namespace;
		List extElements = types.getExtensibilityElements( );
		for ( int i = 0; i < extElements.size( ); i++ )
		{
			if ( extElements.get( i ) instanceof SchemaImpl )
			{
				Element element = ( (SchemaImpl) extElements.get( i ) ).getElement( );
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
		primitiveDataTypeList.clear( );
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
		primitiveDataTypeList.add( "datetime" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "date" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "time" ); //$NON-NLS-1$
		primitiveDataTypeList.add( "integer" ); //$NON-NLS-1$
	}

}


class MultipleNodeList implements NodeList
{
	private NodeList[] nodeLists;
	MultipleNodeList( Element[] elements )
	{
		nodeLists = new NodeList[elements.length];
		for ( int i = 0; i < elements.length; i++ )
		{
			nodeLists[i] = elements[i].getChildNodes( );
		}
	}
	
	public int getLength( )
	{
		int length = 0;
		for ( int i = 0; i < nodeLists.length; i++ )
		{
			length += nodeLists[i].getLength( );
		}
		return length;
	}

	public Node item( int index )
	{
		if( index < 0 || index >= getLength( ) )
			return null;
		for ( int i = 0; i < nodeLists.length; i++ )
		{
			if( index < nodeLists[i].getLength( ) )
			{
				return nodeLists[i].item( index );
			}
			index -= nodeLists[i].getLength( );
		}
		return null;
	}
}