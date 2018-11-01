/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.tests;

import java.io.IOException;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaBlob;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaConnection;
import org.eclipse.datatools.connectivity.oda.impl.Blob;

public class OdaBlobTest extends FlatFileTestCase
{
    private static IBlob sm_driverBlob;
    
    private static final byte[] sm_bytesData = { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 
            0x6, 0x7, 0x8, 0x9, 0xa, 0xb, 0xc, 0xd, 0xe, 0xf };

    protected void setUp() throws Exception
    {
        super.setUp();
        if( sm_driverBlob == null )
        {
            sm_driverBlob = new DriverTestBlob( sm_bytesData );
        }
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
        
/*        if( getOdaConn() != null )
            getOdaConn().close();
*/    
        sm_driverBlob = null;
    }

    /*
     * Test getting specific number of bytes starting from beginning.
     */
    public void testGetLeadingBytes() throws OdaException, IOException
    {       
        IBlob testBlob = new OdaTestBlob( sm_driverBlob, 
        						(OdaConnection) getOdaConn(), 5 );
        int startPos = 1;
        int fetchLen = 7;
        
        byte[] retrievedBytes = testBlob.getBytes( startPos, fetchLen );
        
        assertEquals( fetchLen, retrievedBytes.length );
        for( int i= 0, dataIndexValue = startPos - 1;  // offset 1-based startPos
             i < retrievedBytes.length; i++, dataIndexValue++ )
        {
            assertEquals( dataIndexValue, retrievedBytes[i] );
        }        
    }

    /*
     * Test getting specific number of bytes skipping some at beginning.
     */
    public void testGetSkippedBytes() throws OdaException, IOException
    {       
        IBlob testBlob = new OdaTestBlob( sm_driverBlob, 
        								(OdaConnection) getOdaConn(), 5 );
        int startPos = 4;
        int fetchLen = 7;
        
        byte[] retrievedBytes = testBlob.getBytes( startPos, fetchLen );
        
        assertEquals( fetchLen, retrievedBytes.length );
        for( int i= 0, dataIndexValue = startPos - 1;  // offset 1-based startPos
             i < retrievedBytes.length; i++, dataIndexValue++ )
        {
            assertEquals( dataIndexValue, retrievedBytes[i] );
        }        
    }

    /*
     * Test getting specific number of bytes that are more than what is available in
     * remaining stream.
     */
    public void testGetMoreThanAvailableBytes() throws OdaException, IOException
    {       
        IBlob testBlob = new OdaTestBlob( sm_driverBlob, (OdaConnection) getOdaConn(), 5 );
        int startPos = 13;
        int fetchLen = 7;
        int streamLen = sm_bytesData.length;
        
        byte[] retrievedBytes = testBlob.getBytes( startPos, fetchLen );
        
        assertEquals( (streamLen - startPos + 1), retrievedBytes.length );
        for( int i= 0, dataIndexValue = startPos - 1;  // offset 1-based startPos
             i < retrievedBytes.length; i++, dataIndexValue++ )
        {
            assertEquals( dataIndexValue, retrievedBytes[i] );
        }        
    }

    /*
     * Test getting all remaining bytes with the driver able to determine stream length.
     */
    public void testGetRemainingBytes() throws OdaException, IOException
    {       
        IBlob testBlob = new OdaTestBlob( sm_driverBlob, (OdaConnection) getOdaConn(), 5 );
        int startPos = 13;
        int fetchLen = -1;
        int streamLen = sm_bytesData.length;
        
        byte[] retrievedBytes = testBlob.getBytes( startPos, fetchLen );
        
        assertEquals( (streamLen - startPos + 1), retrievedBytes.length );
        for( int i= 0, dataIndexValue = startPos - 1;  // offset 1-based startPos
             i < retrievedBytes.length; i++, dataIndexValue++ )
        {
            assertEquals( dataIndexValue, retrievedBytes[i] );
        }        
    }

    /*
     * Test getting all remaining bytes with the driver *not* able to 
     * determine stream length, using full buffers
     */
    public void testGetWholeBufferedBytes() throws OdaException, IOException
    {
        int startPos = 2;
        byte[] retrievedBytes = getBufferedBytes( startPos, -1, 5 );
        assertRetrievedData( retrievedBytes, startPos );
    }

    /*
     * Test getting all remaining bytes with the driver *not* able to 
     * determine stream length, whose
     * last buffer has data less than buffer size
     */
    public void testGetPartialBufferedBytes() throws OdaException, IOException
    {
        int startPos = 1;
        byte[] retrievedBytes = getBufferedBytes( startPos, -1, 5 );
        assertRetrievedData( retrievedBytes, startPos );
    }
    
    /*
     * Test getting all remaining bytes with the driver able to 
     * determine stream length, where no data remained since startPos is at end of stream
     */
    public void testGetBytesNoData() throws OdaException, IOException
    {
        IBlob testBlob = new OdaTestBlob( sm_driverBlob, (OdaConnection) getOdaConn(), 5 );
        
        byte[] retrievedBytes = testBlob.getBytes( 17, 3 );
        assertEquals( 0, retrievedBytes.length );
    }
    
    /*
     * Test getting all remaining bytes with the driver *not* able to 
     * determine stream length, where no data remained since startPos is at end of stream
     */
    public void testGetBufferedBytesNoData() throws OdaException, IOException
    {
        byte[] retrievedBytes = getBufferedBytes( 17, -1, 5 );
        assertEquals( 0, retrievedBytes.length );
    }

    /*
     * Test getting all remaining bytes with the driver able to 
     * determine stream length, and skipping bytes beyond the end of stream
     */
    public void testSkipBytesBeyondStream() throws OdaException, IOException
    {
        IBlob testBlob = new OdaTestBlob( sm_driverBlob, (OdaConnection) getOdaConn(), 5 );
        
        boolean caughtException = false;
        try
        {
            testBlob.getBytes( 20, 3 );
        }
        catch( UnsupportedOperationException e )
        {
            caughtException = true;
        }
        assertTrue( caughtException );
    }
    
    /*
     * Test getting all remaining bytes with the driver *not* able to 
     * determine stream length, and skipping bytes beyond the end of stream
     */
    public void testSkipBytesBeyondBufferedStream() throws OdaException, IOException
    {
        boolean caughtException = false;
        try
        {
            getBufferedBytes( 20, -1, 5 );
        }
        catch( UnsupportedOperationException e )
        {
            caughtException = true;
        }
        assertTrue( caughtException );
    }
    
    /*
     * Test incremental read where the buffer size specified is 0.
     */
    public void testBufferSizeZero() throws OdaException, IOException
    {       
        byte[] retrievedBytes = getBufferedBytes( 0, -1, 0 );        
        assertEquals( 0, retrievedBytes.length );
    }
    
    /*
     * Testing reading from an empty stream.
     */
    public void testGetEmptyStream() throws OdaException, IOException
    {       
        IBlob driverBlob = new DriverTestBlob( new byte[0] );
        IBlob testBlob = new OdaTestBlob( driverBlob, (OdaConnection) getOdaConn(), 5 );
        int startPos = 0;
        int fetchLen = -1;
        
        byte[] retrievedBytes = testBlob.getBytes( startPos, fetchLen );
        
        assertEquals( 0, retrievedBytes.length );
    }
    
    /*
     * Utility test functions to similate driver not knowing stream length,
     * and need to keep reading subsequence of bytes till end of stream.
     */
    private byte[] getBufferedBytes( int startPos, int fetchLen, int defaultBufferSize ) 
        throws OdaException, IOException
    {
        IBlob testBlob = new OdaTestBlob( sm_driverBlob, (OdaConnection) getOdaConn(), defaultBufferSize );
        
        // triggers test driver to throw exception on the optional method - length()
        DriverTestBlob driverBlob = (DriverTestBlob) sm_driverBlob;
        driverBlob.setThrowsOnLength( true );
        
        return testBlob.getBytes( startPos, fetchLen );
    }
    
    private void assertRetrievedData( byte[] retrievedBytes, int startPos )
    {
        int streamLen = sm_bytesData.length;
        assertEquals( (streamLen - startPos + 1), retrievedBytes.length );
        for( int i= 0, dataIndexValue = startPos - 1;  // offset 1-based startPos
             i < retrievedBytes.length; i++, dataIndexValue++ )
        {
            assertEquals( dataIndexValue, retrievedBytes[i] );
        }        
    }
    
    /**
     * Extends the odaconsumer.helper OdaBlob implementation to control 
     * test configuration.
     */
    class OdaTestBlob extends OdaBlob
    {
        private int m_defaultBufferSize = 5;
        
        OdaTestBlob( IBlob blob, OdaConnection connection, int defaultBufferSize )
        {
            super( blob, connection, false, null );
            m_defaultBufferSize = defaultBufferSize;
        }

        /* (non-Javadoc)
         * @see org.eclipse.datatools.connectivity.oda.consumer.helper.OdaBlob#getReaderBufferSize()()
         */
        protected int getReaderBufferSize()
        {
            return m_defaultBufferSize;
        }
        
    }
    
    /**
     * Extends the simple ODA driver implementation of IBlob
     * to control test configuration.
     */
    class DriverTestBlob extends Blob
    {
        private int m_streamLen;
        private int m_configStreamLen;     
        private boolean m_throwsOnLength = false;
        
        DriverTestBlob( byte[] byteArray )
        {
            super( byteArray );
            m_streamLen = byteArray.length;
            resetTestLength();
        }

        // Overrides the stream length returned value for testing configuration
        void setTestLength( int len )
        {
            m_configStreamLen = len;
        }
        
        void setThrowsOnLength( boolean doThrow )
        {
            m_throwsOnLength = doThrow;
        }
        
        // Resets the stream length return value to its bytes array size
        void resetTestLength()
        {
            m_configStreamLen = m_streamLen;
        }
        
        /* (non-Javadoc)
         * @see org.eclipse.datatools.connectivity.oda.impl.Blob#length()
         */
        public long length() throws OdaException
        {
            if( m_throwsOnLength )
                throw new UnsupportedOperationException();

            return m_configStreamLen;
        }

        /* (non-Javadoc)
         * @see org.eclipse.datatools.connectivity.oda.impl.Blob#getBytes(long, int)
         */
        public byte[] getBytes( long position, int length ) throws OdaException
        {
            // claims unsupported operation to trigger default implementation in odaconsumer.helper
            throw new UnsupportedOperationException();
        }
        
    }
}
