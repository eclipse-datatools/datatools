/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ws.util;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.enablement.oda.ws.i18n.Messages;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 */

class SOAPFaultParser extends DefaultHandler
{
	
	private final String[] elementNames = {
			"faultcode", "faultstring", "reason"}; //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
	private final String[] errorNames = {
			Messages.getString( "webservice.soap.fault.faultcode" ), //$NON-NLS-1$
			Messages.getString( "webservice.soap.fault.faultstring" ), //$NON-NLS-1$
			Messages.getString( "webservice.soap.fault.reason" ) //$NON-NLS-1$
	};
	private final String SOAP_ENV_COLON_MARK = ":"; //$NON-NLS-1$
	private final String SOAP_ENV_NAMESPACE = "http://schemas.xmlsoap.org/soap/envelope/"; //$NON-NLS-1$
	private final String SOAP_ENV_FAULT = "Fault"; //$NON-NLS-1$
	private final String SOAP_ENV_BLANK_STRING = " "; //$NON-NLS-1$

	private String[] elementValues = new String[elementNames.length];
	private int elementIndex;
	private boolean isSoapFault = false;
	private Map prefixMap = new HashMap( );

	private InputStream inputStream;
	
	public void startElement( String uri, String localName, String qName,
			Attributes attributes ) throws SAXException
	{
		elementIndex = -1;
		String[] names = qName.split( SOAP_ENV_COLON_MARK );
		if ( names.length == 2 && prefixMap.containsKey( names[0] ) )
		{
			String namespace = prefixMap.get( names[0] ).toString( ).trim( );
			String name = names[1].trim( );
			if ( SOAP_ENV_NAMESPACE.equalsIgnoreCase( namespace )
					&& name.equalsIgnoreCase( SOAP_ENV_FAULT ) )
			{
				isSoapFault = true;
			}
		}
		else
		{
			for ( int i = 0; i < elementNames.length; i++ )
			{
				if ( qName.equalsIgnoreCase( elementNames[i] ) )
				{
					elementIndex = i;
					break;
				}
			}
		}
	}
	
	/*
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters( char ch[], int start, int length )
			throws SAXException
	{
		if ( elementIndex != -1 && ( length > 1 || ch[start] != '\n' ) )
		{
			elementValues[elementIndex] = new String( ch, start, length );
		}
	}

	public boolean isSoapFault( )
	{
		return isSoapFault
				&& elementValues[0] != null && elementValues[1] != null;
	}

	public String getErrorMessage( )
	{
		StringBuffer buffer = new StringBuffer( );
		buffer.append( Messages.getString( "webservice.soap.fault" ) ); //$NON-NLS-1$

		for ( int i = 0; i < elementNames.length; i++ )
		{
			if ( elementValues[i] != null )
			{
				buffer.append( errorNames[i] );
				buffer.append( SOAP_ENV_BLANK_STRING
						+ SOAP_ENV_COLON_MARK + SOAP_ENV_BLANK_STRING );
				buffer.append( elementValues[i] );
				buffer.append( SOAP_ENV_BLANK_STRING );
			}
		}
		return buffer.toString( );
	}
	
	/**
	 * 
	 */
	public void parse( InputStream stream )
	{
		Object xmlReader;
		try
		{
			inputStream = stream;

			xmlReader = createXMLReader( );
			setContentHandler( xmlReader );
			setErrorHandler( xmlReader );
			parse( xmlReader );

		}
		catch ( Exception e )
		{
		}
	}
	
	private void parse( Object xmlReader ) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException
	{
		Method parse = this.getMethod( "parse", //$NON-NLS-1$
				xmlReader.getClass( ),
				new Class[]{
					InputSource.class
				} );
		try
		{
			InputSource source = new InputSource( inputStream );
			source.setEncoding( source.getEncoding( ) );
			parse.invoke( xmlReader, new Object[]{
				source
			} );
			inputStream.close( );
		}
		catch ( Exception e )
		{
		}
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
		Method setErrorHandler = this.getMethod( "setErrorHandler", //$NON-NLS-1$
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
		Method setContentHandler = this.getMethod( "setContentHandler", //$NON-NLS-1$
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
					.loadClass( "org.apache.xerces.parsers.SAXParser" ) //$NON-NLS-1$
					.newInstance( );
			return xmlReader;
		}
		catch ( ClassNotFoundException e )
		{
			return Class.forName( "org.apache.xerces.parsers.SAXParser" ) //$NON-NLS-1$
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
	private void invokeMethod( Method method, Object targetObject,
			Object[] argument ) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException
	{
		method.invoke( targetObject, argument );
	}

	/*
	 * @see org.xml.sax.helpers.DefaultHandler#startPrefixMapping(java.lang.String,
	 *      java.lang.String)
	 */
	public void startPrefixMapping( String prefix, String uri )
			throws SAXException
	{
		prefixMap.put( prefix, uri );
	}
}
