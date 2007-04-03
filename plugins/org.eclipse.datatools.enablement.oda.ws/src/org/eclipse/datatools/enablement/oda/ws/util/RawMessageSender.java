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

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.datatools.enablement.oda.ws.soap.SOAPResponse;

/**
 * 
 */

public class RawMessageSender
{

	private String spec = "";
	private String message = "";
	private String soapAction = "";

	private SOAPResponse soapResponse;
	private HttpURLConnection connection;

	public RawMessageSender( String spec, String message, String soapAction )
	{
		this.spec = spec;
		this.message = message;
		this.soapAction = soapAction;
	}

	public RawMessageSender( )
	{
	}

	public void setMessage( String message )
	{
		this.message = message;
	}

	public void setSoapAction( String soapAction )
	{
		this.soapAction = soapAction;
	}

	public void setSpec( String spec )
	{
		this.spec = spec;
	}

	public SOAPResponse getSOAPResponse( )
	{
		Thread t = new Thread( new SOAPResponseCollector( ) );
		t.start( );
		while ( t.isAlive( ) )
		{
		}

		return soapResponse;
	}

	class SOAPResponseCollector implements Runnable
	{

		public void run( )
		{
			try
			{
				URL url = new URL( spec );

				connection = (HttpURLConnection) url.openConnection( );
				connection.setRequestMethod( "POST" );
				connection.setRequestProperty( "Content-Length",
						String.valueOf( message.length( ) ) );
				connection.setRequestProperty( "Content-Type", "text/xml" );
				connection.setRequestProperty( "Connection", "Close" );
				connection.setRequestProperty( "SoapAction", soapAction );
				connection.setDoOutput( true );

				PrintWriter pw = new PrintWriter( connection.getOutputStream( ) );
				pw.write( message );
				pw.flush( );

				connection.connect( );

				soapResponse = new SOAPResponse( connection.getInputStream( ) );
			}
			catch ( MalformedURLException e )
			{
				soapResponse = new SOAPResponse( connection.getErrorStream( ),
						SOAPResponse.ERROR_STREAM,
						e.getMessage( ) );

			}
			catch ( IOException e )
			{
				soapResponse = new SOAPResponse( connection.getErrorStream( ),
						SOAPResponse.ERROR_STREAM,
						e.getMessage( ) );
			}
		}
	}

}
