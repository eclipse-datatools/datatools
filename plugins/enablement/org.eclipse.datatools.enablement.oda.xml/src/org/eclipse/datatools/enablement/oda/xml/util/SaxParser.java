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

package org.eclipse.datatools.enablement.oda.xml.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;

import org.eclipse.datatools.connectivity.XMLUtil;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This instance interacts with a SaxParserConsumer instance to populate
 * the ResultSet data.
 * 
 */
public class SaxParser extends DefaultHandler implements Runnable
{
	private static Logger logger = Logger.getLogger( SaxParser.class.getName( ) );

    private InputStream inputStream;
    private String encoding;
	
	//The ISaxParserConsumer instance that servers as middle-man between
	//ResultSet and SaxParser.
	private ISaxParserConsumer spConsumer;
	
    /*	We will override method	org.xml.sax.helpers.DefaultHandler.characters(char[], int start, int length) to
	rechieve value of an xml element.

	In the Xerces2 Java Parser 2.6.2 implementation (the one we used), the
	first argument, that is, char[], which is a cache of xml input stream, passed
	by the Xerces parser would always be of 2048 bytes in length. If a value of an
	xml element exceeds 2048 bytes, or only parts of its value being cached on the
	rear of the char array, then the method characters() will be called multiple
	times so that the whole value could be achieved.

	Based on the above consideration, we decide to cache the chars fetched from the 
	characters method and proceed them when endElement method is called */
	final private Map<String, StringBuilder> cachedValues = new HashMap<String, StringBuilder>();

	private boolean stopFlag;
	private boolean useNamespace;
	
	final private Map<String, String> prefixMap = new HashMap<String, String>();

	final private List<Exception> exceptions = new ArrayList<Exception>();
	
	private XMLPathHolder pathHolder;
	private XMLPath currentElementPath;

	/**
	 * 
	 * @param fileName
	 * @param consumer
	 * @throws OdaException 
	 */
	public SaxParser(IXMLSource xmlSource, ISaxParserConsumer consumer, boolean useNamespace ) throws OdaException
	{
		this.inputStream = xmlSource.openInputStream( );
		//bypass using empty string to represent no specific encoding provided
		this.encoding = "".equals( xmlSource.getEncoding( ) ) ? null : xmlSource.getEncoding( );
		spConsumer = consumer;
		this.useNamespace = useNamespace;
		stopFlag = false;
	}

	/*
	 *  (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run( )
	{
		try
		{
			SAXParser saxParser = XMLUtil.createSAXParser(true);
			InputSource source = new InputSource(inputStream);
			source.setEncoding( encoding );
			saxParser.parse(source, this);
		}
		catch ( Exception e )
		{
			if ( !(e.getCause( ) instanceof ThreadStopException) )
			{
				logger.log( Level.WARNING, "Exceptions occur during xml parsing", e );
				exceptions.add( e );
			}
		}
		finally
		{
			try
			{
				inputStream.close( );
			}
			catch ( IOException e )
			{
			}
			spConsumer.finish( );
		}
	}
	
	/**
	 * Indicates whether exception occurred
	 * 
	 * @return
	 */
	public boolean exceptionOccurred( )
	{
		return !exceptions.isEmpty( );
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	public void startDocument( )
	{
		pathHolder = new XMLPathHolder( );

	}

	/*
	 *  (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	public void endDocument( )
	{
	}

	/*
	 *  (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement( String uri, String name, String qName,
			Attributes atts )
	{
		//If the current thread should be stopped and current parsing should not continue any more, then
		//throw a ThreadStopException so that it can be caught later in run method to stop the current thread
		//execution.
		if( this.stopFlag )
			throw new ThreadStopException();
		
		String elementName = qName;
		if ( useNamespace && !qName.equals( name ) )
		{
			elementName = uri.replaceAll( UtilConstants.XPATH_SLASH,
					UtilConstants.BACK_SLASH )
					+ UtilConstants.NAMESPACE_COLON + name;
		}
		pathHolder.startElement( elementName );
		currentElementPath = pathHolder.getCurrentElementPath( );
		
		spConsumer.startElement( currentElementPath );
		
		for ( int i = 0; i < atts.getLength( ); i++ )
		{
			spConsumer.manipulateData( pathHolder.getCurrentAttrPath( atts.getQName( i ) ),
					atts.getValue( i ) );
		}
		
		/* 
		 * Workaround patch for https://bugs.eclipse.org/bugs/show_bug.cgi?id=412269
		 * 
		 * SaxParserConsumer.manipulateData() calls SaxParserConsumer.fillNotNestColumn() which is the cause of the bug.
		 * 
		 * This ODA SaxParser uses temporary XML columns as helper columns for xpath expressions which contain a filter.
		 * In case the filter "matches" this xml sub-tree, the corresponding helper column will be set to the filter value.
		 * This "marks" this temporary column that the filter matched.
		 * As soon as the real column will be processed, it is checked whether the filter matched previously.
		 * If so, the value will be assigned to the row.
		 *  
		 * The problem is: If the real column value will be processed before the filter column,
		 * the value is not written to the row, hence the value is discarded.
		 * Even more, the value from the next matching xml-subtree is used instead (=> wrong value used!)
		 * 
		 * This workaround just invokes the faulty method twice. After the first run,
		 * all filter columns are set correctly set so the next pass will cause the mapping to be right.
		 * 
		 * This workaround should be replaced by a real fix which fixes the root cause of the problem (most probably 
		 * refactoring the filter architecture of ODA datatools) as this workaround may degrade performance.
		 * 
		 * 
		 */		
		if(spConsumer instanceof SaxParserConsumer)
		{
			for ( int i = 0; i < atts.getLength( ); i++ )
			{
				spConsumer.manipulateData( pathHolder.getCurrentAttrPath( atts.getQName( i ) ),
						atts.getValue( i ) );
			}
		}
	}


	/*
	 *  (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement( String uri, String localName, String qName )
			throws SAXException
	{	
		String pathString = currentElementPath.getPathString( );
		StringBuilder cachedValue = cachedValues.get( pathString );
		String value;
		if ( cachedValue == null )
		{
			value = "";
		}
		else
		{
			value = cachedValue.toString( );
			cachedValues.remove( currentElementPath.getPathString( ) );
		}
		
		spConsumer.manipulateData( currentElementPath,
				value );
		
		spConsumer.endElement( currentElementPath );
		pathHolder.endElement( );
		currentElementPath = pathHolder.getCurrentElementPath( );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	public void characters( char ch[], int start, int length )
	{
		String pathString = currentElementPath.getPathString( );	
		StringBuilder cachedValue = cachedValues.get( pathString );
		if ( cachedValue == null )
		{
			cachedValue = new StringBuilder( length > 0 ? length : 64 );
			cachedValues.put( pathString, cachedValue );
		}
		cachedValue.append( ch, start, length );
	}
	
	
	/*
	 * @see org.xml.sax.helpers.DefaultHandler#startPrefixMapping(java.lang.String, java.lang.String)
	 */
	public void startPrefixMapping( String prefix, String uri )
	{
		this.prefixMap.put( prefix, uri.replaceAll( UtilConstants.XPATH_SLASH,
				UtilConstants.BACK_SLASH ) );
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, String> getPrefixMapping( )
	{
		return this.prefixMap;
	}
	

	public void stopParsing()
	{
		this.stopFlag = true;
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		spConsumer.warning(e);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		spConsumer.error(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		spConsumer.fatalError(e);
	}

	/**
	 * This class wrapps a RuntimeException. It is used to stop the execution of
	 * current thread.
	 */
	private static class ThreadStopException extends RuntimeException
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7871277314833138093L;

		ThreadStopException(){}
	}
}

class XMLPathHolder
{
	//List<XMLElementBlock>
	private List<XMLElementBlock> elementBlocks = new ArrayList<XMLElementBlock>( );
	
	public XMLPathHolder(  )
	{
		//add dummy root
		elementBlocks.add( new XMLElementBlock( new XMLElement( "/", 1)) );
	}
	
	public void startElement( String elementName )
	{
		XMLElementBlock parent = (XMLElementBlock)elementBlocks.get( elementBlocks.size( )-1 );
		XMLElement child = parent.addSubElement( elementName );
		elementBlocks.add( new XMLElementBlock( child ) );
	}
	
	public void endElement( )
	{
		elementBlocks.remove( elementBlocks.size( )-1 );
	}
	
	public XMLPath getCurrentElementPath( )
	{
		//the dummy root is ignored
		IXMLPathNode[] nodes = new IXMLPathNode[ elementBlocks.size( ) - 1];
		for ( int i=1; i<elementBlocks.size( ); i++)
		{
			nodes[i-1] = ((XMLElementBlock)elementBlocks.get( i )).getElement( );
		}
		return new XMLPath( nodes );
	}
	
	public XMLPath getCurrentAttrPath( String attrName )
	{
		IXMLPathNode[] nodes = new IXMLPathNode[ elementBlocks.size( )];
		
		//the dummy root is ignored
		for ( int i=1; i<elementBlocks.size( ); i++)
		{
			nodes[i-1] = ((XMLElementBlock)elementBlocks.get( i )).getElement( );
		}
		nodes[nodes.length-1] = new XMLAttr( attrName );
		return new XMLPath( nodes );
	}
	
	private static class XMLElementBlock
	{
		private XMLElement element;
		
		//Map<String, int>
		private Map<String, Integer> childCounts = new HashMap<String, Integer>( );
		
		public XMLElementBlock( XMLElement element )
		{
			assert element != null;
			this.element = element;
		}
		
		public XMLElement addSubElement(  String elementName )
		{
			assert elementName != null;
			int index = 0;
			if ( childCounts.get( elementName ) != null )
			{
				index = childCounts.get( elementName );
			}
			index++;
			childCounts.put( elementName, Integer.valueOf(index) );
			return new XMLElement( elementName, index );
		}
		
		public XMLElement getElement( )
		{
			return element;
		}
	}
}