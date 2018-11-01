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

import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaClob;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaConnection;
import org.eclipse.datatools.connectivity.oda.impl.Clob;

public class OdaClobTest extends FlatFileTestCase
{
    private static IClob sm_driverClob;
    
    private static final String sm_testData = "abcdefghijklmnopqrstuvwxyz";

    protected void setUp() throws Exception
    {
        super.setUp();
        if( sm_driverClob == null )
        {
            sm_driverClob = new DriverTestClob( sm_testData );
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
        sm_driverClob = null;
    }

    /*
     * Test getting specific number of characters starting from beginning.
     */
    public void testGetLeadingChars() throws OdaException, IOException
    {       
        IClob testClob = new OdaTestClob( sm_driverClob, 
        						(OdaConnection) getOdaConn(), 5 );
        int startPos = 1;
        int fetchLen = 7;
        
        String retrievedData = testClob.getSubString( startPos, fetchLen );        
        assertRetrievedData( retrievedData, startPos, fetchLen );
    }

    /*
     * Test getting specific number of chars skipping some at beginning.
     */
    public void testGetSkippedChars() throws OdaException, IOException
    {       
        IClob testClob = new OdaTestClob( sm_driverClob, 
        								(OdaConnection) getOdaConn(), 5 );
        int startPos = 6;
        int fetchLen = 7;
        
        String retrievedData = testClob.getSubString( startPos, fetchLen );        
        assertRetrievedData( retrievedData, startPos, fetchLen );
    }

    /*
     * Test getting specific number of chars that are more than what is available in
     * remaining stream.
     */
    public void testGetMoreThanAvailableChars() throws OdaException, IOException
    {       
        IClob testClob = new OdaTestClob( sm_driverClob, 
        								(OdaConnection) getOdaConn(), 5 );
        int startPos = 23;
        int fetchLen = 7;

        String retrievedData = testClob.getSubString( startPos, fetchLen );        
        assertRetrievedData( retrievedData, startPos, -1 );
    }

    /*
     * Test getting all remaining chars with the driver able to determine stream length.
     */
    public void testGetRemainingChars() throws OdaException, IOException
    {       
        IClob testClob = new OdaTestClob( sm_driverClob, 
        								(OdaConnection) getOdaConn(), 5 );
        int startPos = 16;
        int fetchLen = -1;

        String retrievedData = testClob.getSubString( startPos, fetchLen );        
        assertRetrievedData( retrievedData, startPos, fetchLen );
    }

    /*
     * Test getting all remaining chars with the driver *not* able to 
     * determine stream length, using full buffers.
     */
    public void testGetWholeBufferedChars() throws OdaException, IOException
    {
        int startPos = 3;
        int fetchLen = -1;
        String retrievedData = getBufferedSubString( startPos, fetchLen, 8 );
        assertRetrievedData( retrievedData, startPos, fetchLen );
    }

    /*
     * Test getting all remaining chars with the driver *not* able to 
     * determine stream length, whose
     * last buffer has data less than buffer size
     */
    public void testGetPartialBufferedChars() throws OdaException, IOException
    {
        int startPos = 7;
        int fetchLen = -1;
        String retrievedData = getBufferedSubString( startPos, fetchLen, 8 );
        assertRetrievedData( retrievedData, startPos, fetchLen );
    }
    
    /*
     * Test getting all remaining chars with the driver able to 
     * determine stream length, where no data remained since startPos is at end of stream
     */
    public void testGetCharsNoData() throws OdaException, IOException
    {
        IClob testClob = new OdaTestClob( sm_driverClob, 
        								(OdaConnection) getOdaConn(), 5 );
        int startPos = 27;
        int fetchLen = 3;
       
        String retrievedData = testClob.getSubString( startPos, fetchLen );        
        assertEquals( 0, retrievedData.length() );
    }
    
    /*
     * Test getting all remaining chars with the driver *not* able to 
     * determine stream length, where no data remained since startPos is at end of stream
     */
    public void testGetBufferedCharsNoData() throws OdaException, IOException
    {
        int startPos = 27;
        int fetchLen = -1;
        String retrievedData = getBufferedSubString( startPos, fetchLen, 5 );
        assertEquals( 0, retrievedData.length() );
    }

    /*
     * Test getting all remaining chars with the driver able to 
     * determine stream length, and skipping chars beyond the end of stream
     */
    public void testSkipCharsBeyondStream() throws OdaException, IOException
    {
        IClob testClob = new OdaTestClob( sm_driverClob, 
        								(OdaConnection) getOdaConn(), 5 );
        
        boolean caughtException = false;
        try
        {
            testClob.getSubString( 30, 3 );        
        }
        catch( UnsupportedOperationException e )
        {
            caughtException = true;
        }
        assertTrue( caughtException );
    }
    
    /*
     * Test getting all remaining chars with the driver *not* able to 
     * determine stream length, and skipping chars beyond the end of stream
     */
    public void testSkipCharsBeyondBufferedStream() throws OdaException, IOException
    {
        boolean caughtException = false;
        try
        {
            getBufferedSubString( 30, -1, 5 );
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
        String retrievedData = getBufferedSubString( 0, -1, 0 );   
        assertEquals( 0, retrievedData.length() );
    }
    
    /*
     * Testing reading from an empty stream.
     */
    public void testGetEmptyStream() throws OdaException, IOException
    {       
        IClob driverClob = new DriverTestClob( new String() );
        IClob testClob = new OdaTestClob( driverClob, 
        								(OdaConnection) getOdaConn(), 5 );
        int startPos = 0;
        int fetchLen = -1;
        
        String retrievedData = testClob.getSubString( startPos, fetchLen );        
        
        assertEquals( 0, retrievedData.length() );
    }
    
    /*
     * Utility test functions to similate driver not knowing stream length,
     * and need to keep reading subsequence of characters till end of stream.
     */
    private String getBufferedSubString( int startPos, int fetchLen, int defaultBufferSize ) 
        throws OdaException, IOException
    {
        IClob testClob = new OdaTestClob( sm_driverClob, 
        								(OdaConnection) getOdaConn(), defaultBufferSize );
        
        // triggers test driver to throw exception on the optional method - length()
        DriverTestClob driverBlob = (DriverTestClob) sm_driverClob;
        driverBlob.setThrowsOnLength( true );
        
        return testClob.getSubString( startPos, fetchLen );        
    }
    
    private void assertRetrievedData( String retrievedData, int startPos, int fetchLen )
    {
        if( fetchLen < 0 )
            fetchLen = (sm_testData.length() - startPos + 1);
        
        assertEquals( fetchLen, retrievedData.length() );
        for( int i= 0, dataIndexValue = startPos - 1;  // offset 1-based startPos
             i < retrievedData.length(); i++, dataIndexValue++ )
        {
            assertEquals( 'a' + dataIndexValue, retrievedData.charAt(i) );
        }        
    }
    
    /**
     * Extends the odaconsumer.helper OdaClob implementation to control 
     * test configuration.
     */
    class OdaTestClob extends OdaClob
    {
        private int m_defaultBufferSize = 5;
        
        OdaTestClob( IClob clob, OdaConnection connection, int defaultBufferSize )
        {
            super( clob, connection, false, null );
            m_defaultBufferSize = defaultBufferSize;
        }

        /* (non-Javadoc)
         * @see org.eclipse.datatools.connectivity.oda.consumer.helper.OdaClob#getReaderBufferSize()()
         */
        protected int getReaderBufferSize()
        {
            return m_defaultBufferSize;
        }
        
    }
    
    /**
     * Extends the simple ODA driver implementation of IClob
     * to control test configuration.
     */
    class DriverTestClob extends Clob
    {
        private int m_streamLen;
        private int m_configStreamLen;     
        private boolean m_throwsOnLength = false;
        
        DriverTestClob( String charArray )
        {
            super( charArray );
            m_streamLen = charArray.length();
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
         * @see org.eclipse.datatools.connectivity.oda.impl.Clob#length()
         */
        public long length() throws OdaException
        {
            if( m_throwsOnLength )
                throw new UnsupportedOperationException();

            return m_configStreamLen;
        }

        /* (non-Javadoc)
         * @see org.eclipse.datatools.connectivity.oda.impl.Clob#getSubString(long, int)
         */
        public String getSubString( long position, int length ) throws OdaException
        {
            // claims unsupported operation to trigger default implementation in odaconsumer.helper
            throw new UnsupportedOperationException();
        }
        
    }

}
