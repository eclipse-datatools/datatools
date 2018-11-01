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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPResponse;

/**
 * 
 */

public class RawMessageSender
{

	private String spec = WSUtil.EMPTY_STRING;
	private String message = WSUtil.EMPTY_STRING;
	private String soapAction = WSUtil.EMPTY_STRING;

	private SOAPResponse soapResponse;
	private HttpURLConnection connection;

	/**
	 * 
	 * @param spec
	 * @param message
	 * @param soapAction
	 */
	public RawMessageSender( String spec, String message, String soapAction )
	{
		this.spec = spec;
		this.message = message;
		this.soapAction = soapAction;
	}

	/**
	 * 
	 */
	public RawMessageSender( )
	{
	}

	/**
	 * 
	 * @param message
	 */
	public void setMessage( String message )
	{
		this.message = message;
	}

	/**
	 * 
	 * @param soapAction
	 */
	public void setSoapAction( String soapAction )
	{
		this.soapAction = soapAction;
	}

	/**
	 * 
	 * @param spec
	 */
	public void setSpec( String spec )
	{
		this.spec = spec;
	}

	/**
	 * 
	 * @param timeout
	 * @return
	 * @throws OdaException 
	 */
	public SOAPResponse getSOAPResponse( long timeout ) throws OdaException
	{
		SOAPResponseCollector collector = new SOAPResponseCollector( );
		Thread t = new Thread( collector );
		t.start( );
		try
		{
			timeout = timeout == 0 ? 0 : Math.max( 60000, timeout );
			t.join( timeout );
		}
		catch ( InterruptedException e )
		{
		}
		if( collector.getException( ) != null )
		{
			throw new OdaException( collector.getException( ).getMessage( ) );
		}

		return soapResponse;
	}

	class SOAPResponseCollector implements Runnable
	{
		private Exception e = null;
		
		/**
		 * 
		 * @return
		 */
		Exception getException( )
		{
			return e;
		}
		
		public void run( )
		{
			execute( );
			if ( e != null
					&& e.getMessage( ) != null
					&& e.getMessage( ).indexOf( "NoSOAPAction" ) != -1 )
			{
				try
				{
					soapAction = ( new URL( spec ) ).toString( );
					execute( );
				}
				catch ( MalformedURLException e1 )
				{
					soapAction = WSUtil.EMPTY_STRING;
				}
				soapAction = WSUtil.EMPTY_STRING;
			}
		}

		private void execute( )
		{
			try
			{
				e = null;
				
				URL url = new URL( spec );

				connection = (HttpURLConnection) url.openConnection( );
				connection.setRequestMethod( "POST" ); //$NON-NLS-1$
				connection.setRequestProperty( "Content-Length", //$NON-NLS-1$
						String.valueOf( message.length( ) ) );
				connection.setRequestProperty( "Content-Type", "text/xml" ); //$NON-NLS-1$//$NON-NLS-2$
				connection.setRequestProperty( "Connection", "Close" ); //$NON-NLS-1$ //$NON-NLS-2$
				connection.setRequestProperty( "SoapAction", soapAction ); //$NON-NLS-1$
				connection.setDoOutput( true );

				PrintWriter pw = new PrintWriter( connection.getOutputStream( ) );
				pw.write( message );
				pw.flush( );

				connection.connect( );

				soapResponse = new SOAPResponse( getSOAPResponseStream( connection.getInputStream( ) ) );
			}
			catch ( MalformedURLException e )
			{
				if ( !WSUtil.isNull( connection ) )
					soapResponse = new SOAPResponse( connection.getErrorStream( ),
							SOAPResponse.ERROR_STREAM,
							e.getMessage( ) );
				this.e = e;

			}
			catch ( IOException e )
			{
				if ( !WSUtil.isNull( connection ) )
					soapResponse = new SOAPResponse( connection.getErrorStream( ),
							SOAPResponse.ERROR_STREAM,
							e.getMessage( ) );
				this.e = e;
			}
			catch ( OdaException e )
			{
				if ( !WSUtil.isNull( connection ) )
					soapResponse = new SOAPResponse( connection.getErrorStream( ),
							SOAPResponse.ERROR_STREAM,
							e.getMessage( ) );
				this.e = e;
			}
		}
	}
	
	/**
	 * 
	 * @param connectionStream
	 * @return
	 * @throws IOException 
	 */
	private InputStream getSOAPResponseStream( InputStream connectionStream ) throws IOException, OdaException
	{
		byte[] buffer = new byte[4000];
		try
		{
			int pos = 0;
			int readLen = 1;
			do
			{
				readLen = connectionStream.read( buffer, pos, buffer.length - pos );
				if( readLen >= 0 )
				{
					pos += readLen;
				}
			}
			while( readLen >= 0 && pos < buffer.length );
			if( pos == 0 )
			{
				return connectionStream;
			}
			if( pos < buffer.length )
			{	 
				InputStream faultStream = new ByteArrayInputStream( buffer, 0, pos );
				SOAPFaultParser parser = new SOAPFaultParser( );
				parser.parse( faultStream );
				if( parser.isSoapFault( ) )
				{
					throw new OdaException( parser.getErrorMessage( ) + '\n' + new String( buffer, 0, pos ) );
				}
				else
				{
					byte[] buffer1 = new byte[pos];
					System.arraycopy( buffer, 0, buffer1, 0, pos );
					return new CompositeInputStream(  buffer1, connectionStream );
				}
			}
			else
			{
				return new CompositeInputStream(  buffer, connectionStream );
			}
		}
		catch ( IOException e )
		{
			throw e;
		}
		
	}

}

class CompositeInputStream extends InputStream
{

	private byte buf[];
	private int pos;
    private InputStream stream;
    
    public CompositeInputStream( byte[] buffer, InputStream stream )
    {
    	this.buf = buffer;
    	this.stream = stream;
    	this.pos = 0;
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.InputStream#read()
     */
    public synchronized int read( ) throws IOException
    {
    	if ( pos < buf.length )
		{
			return buf[pos++] & 0xff;
		}
		else
		{
			pos++;
			return stream.read( );
		}
    }

    /*
     * (non-Javadoc)
     * @see java.io.InputStream#read(byte[], int, int)
     */
    public synchronized int read( byte b[], int off, int len ) throws IOException
	{
    	if ( pos + len <= buf.length )
		{
    		System.arraycopy( buf, pos, b, off, len );
			pos += len;
			return len;
		}
		if ( pos < buf.length )
		{
			int readLen = buf.length - pos;
			System.arraycopy( buf, pos, b, off, readLen );
			int sReadLen = readFromStream( stream, b, off + readLen, len - readLen );
			if( sReadLen != -1 )
			{
				readLen += sReadLen;
			}
			pos += readLen;
			return readLen;
		}
		else
		{
			int readLen = readFromStream( stream, b, off, len );
			if( readLen != -1 )
				pos += readLen;
			return readLen;
		}
	}
    
    /**
     * 
     * @param is
     * @param b
     * @param off
     * @param len
     * @return
     * @throws IOException
     */
    private int readFromStream( InputStream is, byte b[], int off, int len ) throws IOException
    {
    	int readLen = 0;
    	int oneTimeReadLen = -1;
    	
		do
		{
			oneTimeReadLen = stream.read( b, off + readLen, len - readLen );
			if( oneTimeReadLen >= 0 )
			{
				readLen += oneTimeReadLen;
			}
		}
		while( oneTimeReadLen >= 0 && readLen < len );
		
		if( readLen == 0 )
			return -1;
		return readLen;
    }

    /*
     * (non-Javadoc)
     * @see java.io.InputStream#skip(long)
     */
    public synchronized long skip( long n ) throws IOException
	{
		if ( pos + n <= buf.length )
		{
			pos += n;
			return n;
		}
		if ( pos < buf.length )
		{
			pos += n;
			return stream.skip( pos - buf.length );
		}
		else
		{
			return stream.skip( n );
		}
	}
    /*
     * (non-Javadoc)
     * @see java.io.InputStream#available()
     */
    public synchronized int available( ) throws IOException
	{
		return stream.available( ) + buf.length - pos;
	}

    /*
     * (non-Javadoc)
     * @see java.io.InputStream#markSupported()
     */
    public boolean markSupported( )
	{
		return false;
	}

    /*
     * (non-Javadoc)
     * @see java.io.InputStream#mark(int)
     */
	public void mark( int readAheadLimit )
	{
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.InputStream#reset()
	 */
	public synchronized void reset( )
	{
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.InputStream#close()
	 */
	public void close( ) throws IOException
	{
		stream.close( );
	}
}