/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
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
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private static final String COMMA_SEPARATOR = ", "; //$NON-NLS-1$

    protected OdaBlob( IBlob blob, OdaConnection connection, 
            boolean switchContextClassloader, ClassLoader driverClassLoader )
    {
        super( blob, connection, switchContextClassloader, driverClassLoader );
        
        final String context = "OdaBlob( Blob , " + connection + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
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
        final String context = "OdaBlob.getBinaryStream()\t"; //$NON-NLS-1$
		final String unsupportedOpContext = "IBlob.getBinaryStream()"; //$NON-NLS-1$
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
        final String context = "OdaBlob.getBytes( " + //$NON-NLS-1$
                                position + COMMA_SEPARATOR + length + " )\t"; //$NON-NLS-1$
		final String unsupportedOpContext = "IBlob.getBytes()"; //$NON-NLS-1$
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
        final String context = "OdaBlob.length()\t"; //$NON-NLS-1$
		final String unsupportedOpContext = "IBlob.length()"; //$NON-NLS-1$
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
     * @param startPos  the 1-based ordinal position of the first byte 
     *                  in the specified input stream to be extracted
     * @param length    the number of consecutive bytes to be copied
	 * @return			a byte array containing up to length
	 * 					consecutive bytes from the BLOB value, 
	 * 					starting with the byte at position;
	 * 					or null if not able to retrieve from stream for any reason
     */
    private byte[] getBytesFromStream( long startPos, int length )
    {
        final String context = "OdaBlob.getBytesFromStream( " +  //$NON-NLS-1$
                                startPos + COMMA_SEPARATOR + length + " )\t"; //$NON-NLS-1$
        
		byte[] ret = null;
	    try
        {
	        setContextClassloader();
            
            BlobReader reader = new BlobReader( this, getReaderBufferSize() );
            ret = reader.getBytes( startPos, length );
        }
        catch( RuntimeException rte )
        {
			handleError( rte );		// log and throw
        }
        catch( IOException e )
        {
            log( context, e.toString() );
        }
        catch( OdaException ex )
        {
            log( context, ex.toString() );
        }
		finally
		{
			resetContextClassloader();
		}

		return ret;		// could be null if exception was caught
    }
    
    /**
     * Returns the default buffer size to use for incremental read 
     * when unable to determine the total number of bytes to read 
     * till end of stream is reached.
     * @return  default size of each byte array buffer
     */
    protected int getReaderBufferSize()
    {
        // sub-class may override
        return DEFAULT_BUFFER_SIZE;
    }

}
