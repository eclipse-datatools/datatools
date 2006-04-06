/*
 *************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * OdaBlob is the Oda wrapper for Blobs.
 */
public class OdaBlob extends OdaDriverObject implements IBlob
{
    
    protected OdaBlob( IBlob blob, OdaConnection connection, 
            boolean switchContextClassloader, ClassLoader driverClassLoader )
    {
        super( blob, connection, switchContextClassloader, driverClassLoader );
        
        String context = "OdaBlob( Blob , " + connection + " )\t";
        logMethodCalled( context );
    }

    private IBlob getDriverBlob()
    {
        return (IBlob) getObject();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IBlob#getBinaryStream()
     */
    public InputStream getBinaryStream() throws OdaException
    {
        final String context = "OdaBlob.getBinaryStream()\t";
		final String unsupportedOpContext = "IBlob.getBinaryStream()";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			InputStream ret = getDriverBlob().getBinaryStream();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, unsupportedOpContext );
			return null;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return null;
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
			return null;
		}
		finally
		{
			resetContextClassloader();
		}
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IBlob#getBytes(long, int)
     */
    public byte[] getBytes( long position, int length ) throws OdaException
    {
        final String context = "OdaBlob.getBytes()\t";
		final String unsupportedOpContext = "IBlob.getBytes()";
		logMethodCalled( context );
		
		byte[] ret = null;
		try
		{
			setContextClassloader();
			
			ret = getDriverBlob().getBytes( position, length );
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
            /* underlying driver does not support short-cut method;
             * provides default implementation to retrieve from driver's input stream
             */
            ret = getBytesFromStream( position, length );
    		if( ret == null )
            {
                handleUnsupportedOp( uoException, unsupportedOpContext );
                return null;
            }

			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return null;
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
			return null;
		}
		finally
		{
			resetContextClassloader();
		}
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IBlob#length()
     */
    public long length() throws OdaException
    {
        final String context = "OdaBlob.length()\t";
		final String unsupportedOpContext = "IBlob.length()";
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			long ret = getDriverBlob().length();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException, unsupportedOpContext );
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnZero( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnZero( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
    }
    
    /**
     * Provides default implementation to retrieve all or part of
     * the BLOB data from the driver's input stream.
     * Set/reset context class loader around accessing driver's object,
     * and log caught exception.
	 * @return			a byte array containing up to length
	 * 					consecutive bytes from the BLOB value, 
	 * 					starting with the byte at position;
	 * 					or null if not able to retrieve from stream
     * @throws OdaException
     */
    private byte[] getBytesFromStream( long position, int length )
    	throws OdaException
    {
        final String context = "OdaBlob.getBytesFromStream()\t";

        // first get the underlying driver's stream
        InputStream driverStream = getBinaryStream();
        
		byte[] ret = null;
	    try
        {
	        setContextClassloader();
            ret = doGetBytesFromStream( position, length, driverStream );
        }
        catch( RuntimeException rte )
        {
			handleError( rte );		// log and throw
        }
        catch( IOException e )
        {
            log( context, e.toString() );
        }
		finally
		{
			resetContextClassloader();
		}

		return ret;		// could be null if exception was caught
    }
    
    /*
     * Provides default implementation to retrieve all or part of
     * the BLOB data from the driver's stream.
	 * Returns null if not able to retrieve from stream.
     */
    private byte[] doGetBytesFromStream( long position, int length,
            							InputStream driverStream )
    	throws IOException
    {		
        if( driverStream == null || length < 0 )
            return null;
        
        // if the first byte to retrieve is after the first position in BLOB,
        // first skip all the bytes before position
        if( position > 1 )	
        {
            long numToSkip = position - 1;
            long numSkipped = driverStream.skip( numToSkip );
            if( numSkipped != numToSkip )
                return null;	// not able to skip to given position
        }
        
        // next, retrieve the length of bytes from stream
        byte[] outBuffer = new byte[ length ];
        int bytesRead = driverStream.read( outBuffer, 0, length );
        if( bytesRead >= 0 )
	        return outBuffer;
        
        return null;			// problem reading from stream
    }
}
