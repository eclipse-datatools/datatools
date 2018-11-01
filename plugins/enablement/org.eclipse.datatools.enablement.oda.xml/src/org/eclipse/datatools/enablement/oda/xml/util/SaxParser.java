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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This instance interacts with a SaxParserConsumer instance to populate
 * the ResultSet data.
 * 
 */
public class SaxParser extends DefaultHandler implements Runnable
{
	private static Logger logger = Logger.getLogger( SaxParser.class.getName( ) );
	private static final String SAX_PARSER ="org.apache.xerces.parsers.SAXParser";  //$NON-NLS-1$

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
	private Map<String, StringBuilder> cachedValues;

	private boolean stopFlag;
	private boolean useNamespace;
	
	private Map prefixMap;

	private List exceptions;
	
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
		cachedValues = new HashMap( );
		exceptions = new ArrayList( );
		prefixMap = new HashMap( );
	}

	/*
	 *  (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run( )
	{
		try
		{
			//We should use reflect to create SAXParser and execute its methods. For in 
			//apache Xerces SAXParser it will create other necessary objects using class loader.
			//And sometimes the classloader it uses is not the one which load itself,especially
			//in case that several version of Xerces coexist in the environment, say, IBM java 5.0
			//has include Xerces in its jre library, which takes higher priority to be loaded, take 
			//Tomcat's classloading mechanism into consideration, than eclipse OSGi classloader.We 
			//use reflect here to ensure the class and its object can be successfully created.Please 
			//note that all the item created here using reflecting are loaded by classloader of higher
			//priority. In case of IBM java 5.0, the Xerces in its lib is loaded.Meanwhile, all the
			//other Xerces classes referenced here are loaded by OSGi classloader.
			//
			//This implementation cannot resolve all the conflict but at least it works for the problem
			//we are meeting now.
			Object xmlReader = createXMLReader( );
	
			setContentHandler( xmlReader );
			
			setErrorHandler( xmlReader );
			
			parse( xmlReader );
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

	/**
	 * 
	 * @param xmlReader
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void parse( Object xmlReader ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Method parse = this.getMethod( "parse",//$NON-NLS-1$
				xmlReader.getClass( ),
				new Class[]{
					InputSource.class
				} );
		InputSource source = new InputSource(inputStream);
		source.setEncoding( encoding );
		parse.invoke( xmlReader, new Object[]{
			source
		} );
	}

	/**
	 * 
	 * @param xmlReader
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void setErrorHandler( Object xmlReader ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Method setErrorHandler = this.getMethod( "setErrorHandler",//$NON-NLS-1$
				xmlReader.getClass( ),
				new Class[]{
					ErrorHandler.class
				} );
		this.invokeMethod( setErrorHandler, xmlReader, new Object[]{this} );
	}

	/**
	 * 
	 * @param xmlReader
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void setContentHandler( Object xmlReader ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Method setContentHandler = this.getMethod( "setContentHandler",//$NON-NLS-1$
				xmlReader.getClass( ),
				new Class[]{
					ContentHandler.class
				} );
		
		this.invokeMethod( setContentHandler, xmlReader, new Object[]{
				this
			} );
	}

	/**
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private Object createXMLReader( ) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException
	{
		try
		{
			Object xmlReader = Thread.currentThread( )
					.getContextClassLoader( )
					.loadClass( SAX_PARSER )
					.newInstance( );
			return xmlReader;
		}
		catch ( ClassNotFoundException e )
		{
			return Class.forName( SAX_PARSER )
					.newInstance( );
		}

	}

	/**
	 * Return a method using reflect.
	 * 
	 * @param methodName
	 * @param targetClass
	 * @param argument
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	private Method getMethod(String methodName, Class targetClass, Class[] argument) throws SecurityException, NoSuchMethodException
	{
		assert methodName != null;
		assert targetClass != null;
		assert argument != null;
		
		return targetClass.getMethod( methodName, argument );
	}
	
	/**
	 * Invoke a method.
	 * 
	 * @param method
	 * @param targetObject
	 * @param argument
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void invokeMethod( Method method, Object targetObject, Object[] argument ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		method.invoke( targetObject, argument );
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
	public Map getPrefixMapping( )
	{
		return this.prefixMap;
	}
	

	public void stopParsing()
	{
		this.stopFlag = true;
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
	private List elementBlocks = new ArrayList( );
	
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
		private Map childCounts = new HashMap( );
		
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
				index = ((Integer)childCounts.get( elementName )).intValue( );
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