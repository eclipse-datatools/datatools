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

package org.eclipse.datatools.enablement.oda.xml.util.ui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xerces.impl.xs.XMLSchemaLoader;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.xs.XSConstants;
import org.apache.xerces.xs.XSLoader;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSNamedMap;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer;
import org.eclipse.datatools.enablement.oda.xml.util.SaxParser;
import org.eclipse.datatools.enablement.oda.xml.util.XMLDataInputStreamCreator;

/**
 * This class is used to offer GUI a utility to get an tree from certain xml/xsd
 * file.
 */
public class SchemaPopulationUtil
{

	/**
	 * Get the schema tree's root node
	 * 
	 * @param xsdFileName
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static ATreeNode getSchemaTree( String xsdFileName,
			String xmlFileName, int numberOfElementsAccessiable )
			throws OdaException, MalformedURLException, URISyntaxException
	{
		if ( xsdFileName != null
				&& xsdFileName.toUpperCase( ).endsWith( ".XSD" ) )
		{
			if ( xmlFileName != null && xmlFileName.trim( ).length( ) > 0 )
				return XSDFileSchemaTreePopulator.getSchemaTree( xsdFileName,
						xmlFileName );
			else
				return XSDFileSchemaTreePopulator.getSchemaTree( xsdFileName );
		}
		else
			return new XMLFileSchemaTreePopulator( numberOfElementsAccessiable ).getSchemaTree( xmlFileName );
	}
}

/**
 * This class is used to populate an XML schema tree from an xml file.
 * 
 */
final class XMLFileSchemaTreePopulator implements ISaxParserConsumer
{

	//
	private int rowCount;
	private ATreeNode root;
	private SaxParser sp;

	private int numberOfElementsAccessiable;
	Thread spThread;

	/**
	 * 
	 * @param numberOfElementsAccessiable
	 */
	XMLFileSchemaTreePopulator( int numberOfElementsAccessiable )
	{
		this.rowCount = 0;
		this.root = new ATreeNode( );
		this.root.setValue( "ROOT" );
		this.numberOfElementsAccessiable = numberOfElementsAccessiable == 0
				? Integer.MAX_VALUE : numberOfElementsAccessiable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer#manipulateData(java.lang.String,
	 *      java.lang.String)
	 */
	public void manipulateData( String path, String value )
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer#detectNewRow(java.lang.String,
	 *      boolean)
	 */
	public void detectNewRow( String path, boolean start )
	{
		String treamedPath = path.replaceAll( "\\Q[\\E\\d+\\Q]\\E", "" ).trim( );
		try
		{
			this.insertNode( treamedPath );
		}
		catch ( OdaException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace( );
		}
		// If not attribute
		if ( !isAttribute( path ) && start )
		{
			rowCount++;
		}

		// Only parser the first 10000 elements
		if ( rowCount >= numberOfElementsAccessiable )
		{
			assert sp != null;
			sp.setStart( false );
			sp.stopParsing( );
		}

	}

	/**
	 * Exam whether given path specified an attribute
	 * 
	 * @param path
	 * @return
	 */
	private boolean isAttribute( String path )
	{
		return path.matches( ".*\\Q[@\\E.+\\Q]\\E.*" );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer#wakeup()
	 */
	public synchronized void wakeup( )
	{
		notify( );
	}

	/**
	 * Return the root node of a schema tree.
	 * 
	 * @param xmlFileName
	 * @param includeAttribute
	 * @return
	 */
	public ATreeNode getSchemaTree( String xmlFileName )
	{
		try
		{
			sp = new SaxParser( XMLDataInputStreamCreator.getCreator( xmlFileName )
					.createXMLDataInputStream( ),
					this );

			spThread = new Thread( sp );
			spThread.start( );
			while ( sp.isAlive( ) && !sp.isSuspended( ) )
			{
				try
				{
					synchronized ( this )
					{
						wait( );
					}
				}
				catch ( InterruptedException e )
				{
					e.printStackTrace( );
				}
			}
		}
		catch ( OdaException e1 )
		{
			e1.printStackTrace( );
		}
		return root;
	}

	/**
	 * Insert a node specified by the path.
	 * 
	 * @param treatedPath
	 * @throws OdaException
	 */
	private void insertNode( String treatedPath ) throws OdaException
	{
		boolean isAttribute = isAttribute( treatedPath );

		// Remove the leading "/" then split the path.
		String[] path = treatedPath.replaceFirst( "/", "" ).split( "/" );

		// If the path specified an attribute then re-build the path array so
		// that it can divid element and
		// its attribute to two array items.
		if ( isAttribute )
		{
			String[] temp = path[path.length - 1].split( "\\Q[@\\E" );

			assert temp.length == 2;

			String[] temp1 = new String[path.length + 1];
			for ( int i = 0; i < path.length - 1; i++ )
			{
				temp1[i] = path[i];
			}
			temp1[temp1.length - 2] = temp[0];
			temp1[temp1.length - 1] = temp[1].replaceAll( "\\Q]\\E", "" );
			path = temp1;
		}

		// The parentNode
		ATreeNode parentNode = root;

		// Iterate each path array element, find or create its countpart node
		// instance.
		for ( int i = 0; i < path.length; i++ )
		{
			// This variable hosts the node instance that matches the given path
			// array item value.
			ATreeNode matchedNode = null;

			for ( int j = 0; j < parentNode.getChildren( ).length; j++ )
			{
				if ( ( (ATreeNode) parentNode.getChildren( )[j] ).getValue( )
						.equals( path[i] ) )
				{
					matchedNode = (ATreeNode) parentNode.getChildren( )[j];
					break;
				}
			}
			if ( matchedNode != null )
			{
				parentNode = matchedNode;
			}
			else
			{
				matchedNode = new ATreeNode( );

				if ( ( i == path.length - 1 ) && isAttribute )
				{
					matchedNode.setType( ATreeNode.ATTRIBUTE_TYPE );
				}
				else
				{
					matchedNode.setType( ATreeNode.ELEMENT_TYPE );
				}

				matchedNode.setValue( path[i] );
				matchedNode.setParent( parentNode );
				parentNode = matchedNode;
			}
		}
	}
}

/**
 * This class is used to populate an XML schema tree from an xml file.
 * 
 */
final class XSDFileSchemaTreePopulator
{

	/**
	 * Return the root node of a schema tree.
	 * 
	 * @param schemafileName
	 * @param xmlFileName
	 * @param incAttr
	 * @return
	 * @throws OdaException
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static ATreeNode getSchemaTree( String schemafileName,
			String xmlFileName ) throws OdaException, MalformedURLException,
			URISyntaxException
	{
		ATreeNode xmlRoot = new XMLFileSchemaTreePopulator( 2 ).getSchemaTree( xmlFileName );

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance( );
		factory.setNamespaceAware( true );
		URI uri = null;
		File f = new File( schemafileName );
		if ( f.exists( ) )
			uri = f.toURI( );
		else
		{
			URL url = new URL( schemafileName );
			uri = new URI( url.getProtocol( ),
					url.getUserInfo( ),
					url.getHost( ),
					url.getPort( ),
					url.getPath( ),
					url.getQuery( ),
					url.getRef( ) );
		}

		// Then try to parse the input string as a url in web.
		if ( uri == null )
		{
			uri = new URI( schemafileName );
		}

		XSLoader xsLoader = new XMLSchemaLoader( );
		XSModel xsModel = xsLoader.loadURI( uri.toString( ) );

		if ( xsModel == null )
			throw new OdaException( Messages.getString( "ui.invalidXSDFile" ) );
		XSNamedMap map = xsModel.getComponents( XSConstants.ELEMENT_DECLARATION );

		ATreeNode xsdRoot = new ATreeNode( );
		xsdRoot.setValue( "ROOT" );
		for ( int i = 0; i < map.getLength( ); i++ )
		{
			XSElementDecl element = (XSElementDecl) map.item( i );
			ATreeNode node = new ATreeNode( element );

			xsdRoot.addChild( node );

			// Only the element whose name is same to the tag name of the
			// xml root,as well as its sub elements will be populated in the
			// tree, if this no element that matches the xml root elememt, then
			// the tree is populated based on the xsd file structure
			if ( xmlRoot != null
					&& node.getValue( )
							.equals( ( (ATreeNode) xmlRoot.getChildren( )[0] ).getValue( ) ) )
			{
				xsdRoot = new ATreeNode( );
				xsdRoot.setValue( "ROOT" );
				xsdRoot.addChild( node );
				break;
			}

		}

		return xsdRoot;

	}

	/**
	 * Return the root node of a schema tree.
	 * 
	 * @param xsdFileName
	 * @param incAttr
	 * @return
	 * @throws OdaException
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public static ATreeNode getSchemaTree( String xsdFileName )
			throws OdaException, MalformedURLException, URISyntaxException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance( );
		factory.setNamespaceAware( true );
		URI uri = null;
		File f = new File( xsdFileName );
		if ( f.exists( ) )
			uri = f.toURI( );
		else
		{
			URL url = new URL( xsdFileName );
			uri = new URI( url.getProtocol( ),
					url.getUserInfo( ),
					url.getHost( ),
					url.getPort( ),
					url.getPath( ),
					url.getQuery( ),
					url.getRef( ) );
		}

		// Then try to parse the input string as a url in web.
		if ( uri == null )
		{
			uri = new URI( xsdFileName );
		}

		XSLoader xsLoader = new XMLSchemaLoader( );
		XSModel xsModel = xsLoader.loadURI( uri.toString( ) );
		// ATreeNode complexTypesRoot = populateComplexTypeTree( xsModel );

		XSNamedMap map = xsModel.getComponents( XSConstants.ELEMENT_DECLARATION );

		ATreeNode root = new ATreeNode( );
		root.setValue( "ROOT" );
		for ( int i = 0; i < map.getLength( ); i++ )
		{
			XSElementDecl element = (XSElementDecl) map.item( i );
			ATreeNode node = new ATreeNode( element );
			node.setValue( element.getName( ) );
			node.setType( ATreeNode.ELEMENT_TYPE );
			node.setDataType( element.getName( ) );
			root.addChild( node );
		}

		return root;

	}
}
