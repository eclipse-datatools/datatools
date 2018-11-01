/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 *  Internal implementation class responsible for reading large binary object data from
 *  an input stream.
 *  This provides default implementation for those ODA drivers that do not implement
 *  the optional methods of ODA IBlob interface.
 */
class BlobReader extends OdaObject
{
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private static final String COMMA_SEPARATOR = ", "; //$NON-NLS-1$
    
    private static String sm_className = null;    // lazy initialization
    
    private IBlob m_odaBlob;
    private int m_defaultBufferSize;
        
    protected BlobReader( IBlob odaBlob )
    {
        init( odaBlob, DEFAULT_BUFFER_SIZE );
    }
    
    protected BlobReader( IBlob odaBlob, int defaultBufferSize )
    {
        init( odaBlob, defaultBufferSize );
    }
    
    private void init( IBlob odaBlob, int defaultBufferSize )
    {
        assert( odaBlob != null );
        m_odaBlob = odaBlob;
        m_defaultBufferSize = ( defaultBufferSize >= 0 ) ?
                defaultBufferSize : DEFAULT_BUFFER_SIZE;
        
        // for logging context
        if( sm_className == null )
            sm_className = getClassName();
    }
    
    /**
     * Returns the default buffer size to use for incremental read 
     * when unable to determine the total number of bytes to read 
     * till end of stream is reached.
     * @return  default size of each byte array buffer
     */
    private int getDefaultBufferSize()
    {
        return m_defaultBufferSize;
    }
    
    /**
     * Provides default implementation to retrieve all or part of
     * the BLOB data from the wrapped Blob.
     * Returns null if not able to retrieve from stream.
     * @param startPos  the 1-based ordinal position of the first byte 
     *                  in the specified input stream to be extracted
     * @param length    the number of consecutive bytes to be copied
     * @return          a byte array containing up to <code>length</code> 
     *                  consecutive bytes from the specified input stream, 
     *                  starting with the byte at <code>startPos</code>;
     *                  or null if not able to retrieve from stream as specified
     * @throws IOException      if error in reading from the input stream
     * @throws OdaException     if data source error occurs
     */
    byte[] getBytes( long startPos, int length )
        throws IOException, OdaException
    {
        final String context = sm_className + ".getBytes( " +  //$NON-NLS-1$
                                startPos + COMMA_SEPARATOR + length + " )\t"; //$NON-NLS-1$
        logMethodCalled( context );
        
        // first get the underlying driver's stream
        InputStream driverStream = null;
        try
        {
            driverStream = m_odaBlob.getBinaryStream();
        }
        catch( OdaException e1 )
        {
            log( context, e1.toString() );
        }

        if( driverStream == null )
        {
            logMethodExitWithReturn( context, null );
            return null;
        }
        
        // use java.io utility to handle reading specified length of bytes
        // in a single call
        BufferedInputStream bufferedStream = new BufferedInputStream( driverStream );
        
        // if the first byte to retrieve is after the first position in BLOB,
        // first skip all the bytes before startPos
        long numToSkip = 0;
        if( startPos > 1 )  
        {
            numToSkip = startPos - 1;
            long numSkipped = bufferedStream.skip( numToSkip );
            if( numSkipped != numToSkip )
            {
                bufferedStream.close();
                logMethodExitWithReturn( context, null );
                return null;    // not able to skip to given position
            }
        }
        
        int numToRead = length;
        // check if the specified length is within the remaining bytes in stream
        long streamLen = getStreamLength();
        if( streamLen >= 0 )    // driver is able to determine the stream length 
        {
            int numAvailable = ( streamLen > numToSkip ) ?
                    ( new Long( streamLen - numToSkip )).intValue() : 
                     0;     // nothing available to read beyond starting position
                    
            // caller specifies to read till end of stream, or more than what's available
            if( length < 0 || length > numAvailable )
                numToRead = numAvailable;   // read remaining bytes till end of stream
        }
        
        // next, retrieve the exact numToRead bytes from stream
        if( numToRead >= 0 )
        {
            byte[] bytesData = readBytesFromStream( bufferedStream, numToRead );
            bufferedStream.close();
            
            int arraySize = ( bytesData != null ) ? bytesData.length : -1;
            logMethodExitWithReturn( context, arraySize );
            return bytesData;
        }
        
        // do not know exact bytes length till end of stream, 
        // do incremental read till end of stream
        byte[] remainingBytes = null;
        try
        {
            remainingBytes = readRemainingBytes( bufferedStream );
        }
        catch( OutOfMemoryError err )   // attempts to catch error
        {
            err.printStackTrace();
        }
        bufferedStream.close();
        
        int arraySize = ( remainingBytes != null ) ? remainingBytes.length : -1;
        logMethodExitWithReturn( context, arraySize );
        
        return remainingBytes;
    }

    /**
     * Reads the specified inputStream from its current position up to the specified
     * maximum number of bytes.
     * @param stream
     * @param maxBufSize
     * @return
     * @throws IOException
     */
    private byte[] readBytesFromStream( InputStream stream, int maxBufSize )
        throws IOException
    {
        final String context = sm_className + ".readBytesFromStream( " + //$NON-NLS-1$
                                stream + COMMA_SEPARATOR + maxBufSize + " )\t"; //$NON-NLS-1$
        assert( maxBufSize >= 0 );
        byte[] outBuffer = new byte[ maxBufSize ];
        if( maxBufSize == 0 )
        {
            log( context, "Returns an empty byte array." ); //$NON-NLS-1$
            return outBuffer;
        }
        
        int bytesRead = stream.read( outBuffer, 0, outBuffer.length );
        if( bytesRead == maxBufSize ) 
        {
            log( context, "Returns a byte array of requested size: " + outBuffer.length ); //$NON-NLS-1$
            return outBuffer;
        }
        if( bytesRead < 0 )     // end of stream
        {
            log( context, "Returns a null byte array for end of stream." ); //$NON-NLS-1$
            return null;
        }

        // in case end of stream is reached before full buffer length is read
        byte[] resizedBuf = new byte[ bytesRead ];
        System.arraycopy( outBuffer, 0, resizedBuf, 0, bytesRead );
        
        log( context, "Returns a byte array of size: " + resizedBuf.length ); //$NON-NLS-1$

        return resizedBuf;       
    }
    
    /**
     * Reads the specified inputStream from its current position up to the 
     * end of stream.
     * This approach is inefficient and takes up extra memory, and is the last
     * resort used when the underlying input stream provides no stream length info.  
     * @param stream
     * @return  read data in a single byte array; array may be empty if no data
     *          was read in a normal condition
     * @throws IOException
     */
    private byte[] readRemainingBytes( InputStream stream )
        throws IOException
    {
        final String context = sm_className + ".readRemainingBytes( " + stream + " )\t"; //$NON-NLS-1$   //$NON-NLS-2$

        // read one chunk of bytes at a time using default buffer size, 
        // and keep the chunk buffers in a temp collection
        ArrayList bufferList = new ArrayList();
        int bufferSize = getDefaultBufferSize();    // may be zero for empty array
        log( context, "Default buffer size: " + bufferSize );       //$NON-NLS-1$
        
        boolean endOfStream = false;
        byte[] aChunk = null;
        while( ! endOfStream )
        {
            aChunk = readBytesFromStream( stream, bufferSize );
            if( aChunk != null )
                bufferList.add( aChunk );
            endOfStream = ( aChunk == null || aChunk.length < bufferSize || 
                            bufferSize == 0 );  
        }

        if( bufferList.isEmpty() )  // no data was read
        {
            log( context, "Returns an empty byte array." ); //$NON-NLS-1$
            return new byte[0];
        }
        
        // first get the count of total bytes read in all buffers
        int totalBytes = 0;
        Iterator bufferIter = bufferList.iterator();
        while( bufferIter.hasNext() )
        {
            aChunk = (byte[]) bufferIter.next();
            assert( aChunk != null );
            totalBytes += aChunk.length;
        }
        
        if( totalBytes <= 0 )       // no data was read
        {
            log( context, "Returns an empty byte array." ); //$NON-NLS-1$
            return new byte[0];     
        }
        
        // concatenate all the buffers into a single byte array to return       
        byte[] remainingBytes = new byte[ totalBytes ];
        bufferIter = bufferList.iterator();
        int bytesCopied = 0;
        while( bufferIter.hasNext() )
        {
            aChunk = (byte[]) bufferIter.next();
            
            System.arraycopy( aChunk, 0, remainingBytes, bytesCopied, aChunk.length );
            bytesCopied += aChunk.length;
            assert( bytesCopied <= totalBytes );
        }
        
        log( context, "Returns a byte array of size: " + remainingBytes.length ); //$NON-NLS-1$
        return remainingBytes;
    }
    
    private long getStreamLength()
    {
        long len = -1;  // default for unknown length
        try
        {
            len = m_odaBlob.length();
        }
        catch( OdaException odaException )
        {
            // ignore
        }
        catch( UnsupportedOperationException uoException )
        {
            // ignore
        }
        catch( RuntimeException rtException )
        {
            // ignore
        }
        
        return len;
    }

}
