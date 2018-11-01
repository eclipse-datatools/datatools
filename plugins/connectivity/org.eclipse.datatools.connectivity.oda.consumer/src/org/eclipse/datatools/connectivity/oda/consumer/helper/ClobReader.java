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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 *  Internal implementation class responsible for reading large character object data from
 *  an input stream reader.
 *  This provides default implementation for those ODA drivers that do not implement
 *  the optional methods of ODA IClob interface.
 */
class ClobReader extends OdaObject
{
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private static final String EMPTY_STR = ""; //$NON-NLS-1$
    private static final String COMMA_SEPARATOR = ", "; //$NON-NLS-1$

    private static String sm_className = null;    // lazy initialization
    
    private IClob m_odaClob;
    private int m_defaultBufferSize;
        
    protected ClobReader( IClob odaClob )
    {
        init( odaClob, DEFAULT_BUFFER_SIZE );
    }
    
    protected ClobReader( IClob odaClob, int defaultBufferSize )
    {
        init( odaClob, defaultBufferSize );
    }
    
    private void init( IClob odaClob, int defaultBufferSize )
    {
        assert( odaClob != null );
        m_odaClob = odaClob;
        m_defaultBufferSize = ( defaultBufferSize >= 0 ) ?
                                defaultBufferSize : DEFAULT_BUFFER_SIZE;
        
        // for logging context
        if( sm_className == null )
            sm_className = getClassName();
    }
    
    /**
     * Returns the default buffer size to use for incremental read 
     * when unable to determine the total number of characters to read 
     * till end of stream is reached.
     * @return  default size of each char array buffer
     */
    private int getDefaultBufferSize()
    {
        return m_defaultBufferSize;
    }
    
    /**
     * Provides default implementation to retrieve all or part of
     * the CLOB data from the wrapped Clob.
     * Returns null if not able to retrieve from reader.
     * @param startPos  the 1-based ordinal position of the first character 
     *                  in the specified input reader to be extracted
     * @param length    the number of consecutive characters to be copied
     * @return          a string containing up to <code>length</code> 
     *                  consecutive characters from the specified input stream reader, 
     *                  starting with the character at <code>startPos</code>;
     *                  or null if not able to retrieve from stream as specified
     * @throws IOException      if error in reading from the input stream reader
     * @throws OdaException     if data source error occurs
     */
    String getSubString( long startPos, int length )
        throws IOException, OdaException
    {
        final String context = sm_className + ".getSubString( " +  //$NON-NLS-1$
                                startPos + COMMA_SEPARATOR + length +  " )\t"; //$NON-NLS-1$
        logMethodCalled( context );
        
        // first get the underlying driver's stream
        Reader driverReader = null;
        try
        {
            driverReader = m_odaClob.getCharacterStream();
        }
        catch( OdaException e1 )
        {
            log( context, e1.toString() );
        }

        if( driverReader == null )
        {
            logMethodExitWithReturn( context, null );
            return null;
        }
        
        // use java.io utility to handle reading specified length of chars
        // in a single call
        BufferedReader bufferedStream = new BufferedReader( driverReader );
        
        // if the first char to retrieve is after the first position in CLOB,
        // first skip all the characters before startPos
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
        // check if the specified length is within the remaining characters in stream
        long streamLen = getStreamLength();
        if( streamLen >= 0 )    // driver is able to determine the stream length 
        {
            int numAvailable = ( streamLen > numToSkip ) ?
                    ( new Long( streamLen - numToSkip )).intValue() : 
                     0;     // nothing available to read beyond starting position
                    
            // caller specifies to read till end of stream, or more than what's available
            if( length < 0 || length > numAvailable )
                numToRead = numAvailable;   // read remaining chars till end of stream
        }
        
        // next, retrieve the exact numToRead characters from stream
        if( numToRead >= 0 )
        {
            char[] readData = readCharsFromStream( bufferedStream, numToRead );
            bufferedStream.close();
            String readString = new String( readData );
            
            logMethodExitWithReturnLen( context, readString );
            return readString;
        }
        
        // do not know exact length of remaining stream; 
        // do incremental read till end of stream
        String remainingStream = null;
        try
        {
            remainingStream = readRemainingString( bufferedStream );
        }
        catch( OutOfMemoryError err )   // attempts to catch error
        {
            err.printStackTrace();
        }
        bufferedStream.close();
        
        logMethodExitWithReturnLen( context, remainingStream );        
        return remainingStream;
    }

    /**
     * Reads the specified inputStream from its current position up to the specified
     * maximum number of characters.
     * @param stream
     * @param maxBufSize
     * @return
     * @throws IOException
     */
    private char[] readCharsFromStream( Reader stream, int maxBufSize )
        throws IOException
    {
        final String context = sm_className + ".readCharsFromStream( " +   //$NON-NLS-1$
                                stream + COMMA_SEPARATOR + maxBufSize + " )\t";  //$NON-NLS-1$
        assert( maxBufSize >= 0 );
        char[] outBuffer = new char[ maxBufSize ];
        if( maxBufSize == 0 )
        {
            log( context, "Returns an empty char array." ); //$NON-NLS-1$
            return outBuffer;
        }
        
        int numRead = stream.read( outBuffer, 0, outBuffer.length );
        if( numRead == maxBufSize ) 
        {
            log( context, "Returns a char array of requested size: " + outBuffer.length ); //$NON-NLS-1$
            return outBuffer;
        }
        if( numRead < 0 )     // end of stream
        {
            log( context, "Returns a null char array for end of stream." ); //$NON-NLS-1$
            return null;
        }

        // data read till end of stream, which may be before full buffer length is read       
        char[] resizedBuf = new char[ numRead ];
        System.arraycopy( outBuffer, 0, resizedBuf, 0, numRead );
        
        log( context, "Returns a char array of size: " + resizedBuf.length ); //$NON-NLS-1$

        return resizedBuf;       
    }
    
    /**
     * Reads the specified Reader from its current position up to the 
     * end of stream.
     * This approach is inefficient and takes up extra memory; it is the last
     * resort used only when the underlying input stream provides no stream length info.  
     * @param stream
     * @return  data read in a String; may be empty if no data
     *          was read in a normal condition
     * @throws IOException
     */
    private String readRemainingString( Reader stream )
        throws IOException
    {
        final String context = sm_className + ".readRemainingString( " + stream + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$

        // read one chunk of char at a time using default buffer size, 
        // and keep the chunk buffers in a temp collection
        ArrayList bufferList = new ArrayList();
        int bufferSize = getDefaultBufferSize();    // may be zero for empty array
        log( context, "Default buffer size: " + bufferSize );       //$NON-NLS-1$
        
        boolean endOfStream = false;
        char[] aChunk = null;
        while( ! endOfStream )
        {
            aChunk = readCharsFromStream( stream, bufferSize );
            if( aChunk != null )
                bufferList.add( aChunk );
            endOfStream = ( aChunk == null || aChunk.length < bufferSize || 
                            bufferSize == 0 );  
        }

        if( bufferList.isEmpty() )  // no data was read
        {
            log( context, "Returns an empty String." ); //$NON-NLS-1$
            return EMPTY_STR;
        }
        
        // first get the count of total chars read in all buffers
        int totalChars = 0;
        Iterator bufferIter = bufferList.iterator();
        while( bufferIter.hasNext() )
        {
            aChunk = (char[]) bufferIter.next();
            assert( aChunk != null );
            totalChars += aChunk.length;
        }
        
        if( totalChars <= 0 )       // no data was read
        {
            log( context, "Returns an empty String." ); //$NON-NLS-1$
            return EMPTY_STR;     
        }
        
        // concatenate all the buffers into a single StringBuffer to return       
        StringBuffer remainingString = new StringBuffer( totalChars );
        bufferIter = bufferList.iterator();
        while( bufferIter.hasNext() )
        {
            aChunk = (char[]) bufferIter.next();
            
            remainingString = remainingString.append( aChunk );
        }
        
        log( context, "Returns a String of size: " + remainingString.length() ); //$NON-NLS-1$
        return remainingString.toString();
    }
    
    private long getStreamLength()
    {
        long len = -1;  // default for unknown length
        try
        {
            len = m_odaClob.length();
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
