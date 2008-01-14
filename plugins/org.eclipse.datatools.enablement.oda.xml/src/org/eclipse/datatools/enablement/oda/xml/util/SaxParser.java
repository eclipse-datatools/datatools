/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private XMLDataInputStream inputStream;
	
	//The XPathHolder instance that hold the information of element currently
	//being proceed
	private XPathHolder pathHolder;
	
	//The ISaxParserConsumer instance that servers as middle-man between
	//ResultSet and SaxParser.
	private ISaxParserConsumer spConsumer;
	
	//This HashMap records the occurance of element being proceed.
	private HashMap currentElementRecoder;
	
	//The boolean indicates that whether the parsing has started.
	private boolean start;
	
	//The boolean indicates that whether the parsing thread is alive or not.
	private boolean alive;
	
    /*	We will override method	org.xml.sax.helpers.DefaultHandler.characters(char[], int start, int length) to
	rechieve value of an xml element.

	In the Xerces2 Java Parser 2.6.2 implementation (the one we used), the
	first argument, that is, char[], which is a cache of xml input stream, passed
	by the Xerces parser would always be of 2048 bytes in length. If a value of an
	xml element exceeds 2048 bytes, or only parts of its value being cached on the
	rear of the char array, then the method characters() will be called multiple
	times so that the whole value could be achieved.

	Based on the above consideration, we decide to cache the chars fetched from the 
	characters method and proceed them when endDocument method is called */
	private String currentCacheValue;

	private boolean stopCurrentThread;
	
	private Map cachedValues;

	private List exceptions;
	/**
	 * 
	 * @param fileName
	 * @param consumer
	 */
	public SaxParser( XMLDataInputStream stream, ISaxParserConsumer consumer )
	{
		inputStream = stream;
		spConsumer = consumer;
		start = true;
		alive = true;
		currentCacheValue = "";
		currentElementRecoder = new HashMap();
		stopCurrentThread = false;
		cachedValues = new HashMap( );
		exceptions = new ArrayList( );
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
			
			
			
			this.inputStream.init( );
			
			parse( xmlReader );
		}
		catch ( Exception e )
		{
			exceptions.add( e );
		}
		finally
		{
			this.alive = false;
			spConsumer.wakeup( );
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
		Method parse = this.getMethod( "parse",
				xmlReader.getClass( ),
				new Class[]{
					InputSource.class
				} );
		InputSource source = new InputSource(inputStream);
		source.setEncoding( inputStream.getEncoding( ) );
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
		Method setErrorHandler = this.getMethod( "setErrorHandler",
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
		Method setContentHandler = this.getMethod( "setContentHandler",
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
					.loadClass( "org.apache.xerces.parsers.SAXParser" )
					.newInstance( );
			return xmlReader;
		}
		catch ( ClassNotFoundException e )
		{
			return Class.forName( "org.apache.xerces.parsers.SAXParser" )
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
		pathHolder = new XPathHolder( );

	}

	/*
	 *  (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	public void endDocument( )
	{
		this.alive = false;
		this.cleanUp( );
		this.spConsumer.wakeup();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement( String uri, String name, String qName,
			Attributes atts )
	{
		//If the current thread should be stopped and current parsing should not continue any more, then
		//throw a ThreadStopException so that it can be catched later in run method to stop the current thread
		//execution.
		if( this.stopCurrentThread )
			throw new ThreadStopException();
		
		String elementName = qName;
		String parentPath = pathHolder.getPath();
		//Record the occurance of elements
		if(this.currentElementRecoder.get(parentPath+UtilConstants.XPATH_SLASH+elementName)==null)
		{
			this.currentElementRecoder.put(parentPath+UtilConstants.XPATH_SLASH+elementName,new Integer(1));
		}else
		{
			this.currentElementRecoder.put(parentPath+UtilConstants.XPATH_SLASH+elementName, new Integer(((Integer)this.currentElementRecoder.get(parentPath+UtilConstants.XPATH_SLASH+elementName)).intValue()+1 )); 
		}
		pathHolder.push( elementName+"["+((Integer)this.currentElementRecoder.get(parentPath+UtilConstants.XPATH_SLASH+elementName)).intValue()+"]" );
		spConsumer.detectNewRow( pathHolder.getPath( ), true );
		
		for ( int i = 0; i < atts.getLength( ); i++ )
		{
			spConsumer.manipulateData( getAttributePath( atts, i ),
					atts.getValue( i ) );
		}
	}

	/**
	 * Build the xpath of an attribute.
	 * 
	 * @param atts
	 * @param i
	 * @return
	 */
	private String getAttributePath( Attributes atts, int i )
	{
		return pathHolder.getPath( )
				+ "/@"
				+ atts.getQName( i );
	}

	/*
	 *  (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement( String uri, String localName, String qName )
			throws SAXException
	{
		//Manipulate the data. The currentCacheValue is trimed to delimite
		//the heading and tailing junk spaces.
		spConsumer.manipulateData( pathHolder.getPath( ),
				(String) cachedValues.get( pathHolder.getPath( ) ) );
		cachedValues.remove( pathHolder.getPath( ) );
		spConsumer.detectNewRow( pathHolder.getPath( ), false );
		//	this.currentElementRecoder.clear();
		
		String path = pathHolder.getPath();
	
		Object[] keys = this.currentElementRecoder.keySet().toArray();
		if (!path.equals( "" ))
		{
			for(int i= 0; i < keys.length; i++)
			{
				if (keys[i].toString().startsWith(path)&&(!keys[i].toString().equals(path)))
				{
					this.currentElementRecoder.remove(keys[i]);
				}
			}
		}
		pathHolder.pop( );
	}



	/*
	 *  (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	public void characters( char ch[], int start, int length )
	{
		currentCacheValue = new String( ch, start, length );
		if ( !currentCacheValue.trim( ).equals( "" ) )
		{
			if ( cachedValues.containsKey( pathHolder.getPath( ) ) )
				currentCacheValue = (String) cachedValues.get( pathHolder.getPath( ) )
						+ currentCacheValue;
			
			cachedValues.put( pathHolder.getPath( ), currentCacheValue );
		}
	}

	/**
	 * Set the status of current thread, might either be "started" or "suspended"
	 * @param start
	 */
	public void setStart( boolean start )
	{
		this.start = start;
		if ( start )
		{
			synchronized ( this )
			{
				notify( );
			
			}
		}else
		{
			synchronized ( this )
			{
				try
				{
					spConsumer.wakeup();
					wait( );
				}
				catch ( InterruptedException e )
				{
					e.printStackTrace();
				}
			
			}
		}
	}
	
	/**
	 * Set the member data stopCurrentThread to "true" value so that the current thread can be stopped afterwise.
	 *
	 */
	public void stopParsing()
	{
		this.cleanUp( );
		this.stopCurrentThread = true;
	}

	/**
	 * Return whether the thread that host the SaxParser is suspended.
	 * @return
	 */
	public boolean isSuspended( )
	{
		return !start;
	}

	/**
	 * Return whether the thread that host the SaxParser is alive or destoried.
	 * 
	 * @return
	 */
	public boolean isAlive( )
	{
		return this.alive;
	}
	
	/**
	 * Prepare for the stop execution of parsing.
	 *
	 */
	private void cleanUp( )
	{
		try
		{
			if ( this.inputStream != null )
				this.inputStream.close( );
		}
		catch ( IOException e )
		{
			//Simply ignore this.
		}
	}
	
	/**
	 * This class wrapps a RuntimeException. It is used to stop the execution of
	 * current thread.
	 */
	private class ThreadStopException extends RuntimeException
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7871277314833138093L;

		ThreadStopException(){}
	}
}

/**
 * The instance of this class is used to populate the Xpath expression of 
 * current XML path.
 * 
 */
class XPathHolder
{
	private List holder;
	private StringBuffer pathBuffer;

	public XPathHolder( )
	{
		holder = new ArrayList( );
		pathBuffer = new StringBuffer();
	}

	/**
	 * Get the path string according to the current status of XPathHolder instance.
	 * @return
	 */
	public String getPath( )
	{
		return pathBuffer.toString( );
	}

	/**
	 * Pop a value from stack.
	 *
	 */
	public void pop( )
	{
		if (holder.size( ) > 1) 
		{
			String lastPath = (String)holder.get( holder.size( ) - 1 );
			holder.remove( holder.size( ) - 1 );
			//remove "/lastPath"
			this.pathBuffer.delete( pathBuffer.length( ) - 1 - lastPath.length( ), pathBuffer.length( ));
		}
	}

	/**
	 * Push a value to stack.
	 * 
	 * @param path
	 */
	public void push( String path )
	{
		assert path != null;
		holder.add( path );
		this.pathBuffer.append("/").append( path );
	}
}