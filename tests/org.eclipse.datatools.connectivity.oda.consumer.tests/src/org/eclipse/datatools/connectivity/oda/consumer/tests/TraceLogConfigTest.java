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

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaConsumerPlugin;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaDriver;
import org.eclipse.datatools.connectivity.oda.util.logging.Level;

/**
 *
 */
public class TraceLogConfigTest extends TestCase
{
    static final String TEST_DRIVER_ID = 
        "org.eclipse.datatools.connectivity.oda.consumer.testdriver"; //$NON-NLS-1$

    private static IDriver sm_odaDriver;
    private static File sm_consumerLogDir;
    private String m_driverLogRelativeDirName;
    private File m_driverLogAbsoluteDir;

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        
        if( sm_odaDriver == null )
            sm_odaDriver = new OdaDriver( TEST_DRIVER_ID );

        if( sm_consumerLogDir == null )
        {
            sm_consumerLogDir = 
                    OdaConsumerPlugin.getDefault().getStateLocation()
                        .append( "logs" )
                        .append( TEST_DRIVER_ID ).toFile();
        }
        
        m_driverLogRelativeDirName = TraceLogTestUtil.getOptionsLogDir( TEST_DRIVER_ID );
        // change driver log dir in explicit setLogConfig
        if( getName() == "testDriverTraceOptionsAndSetLogConfig" )
            m_driverLogRelativeDirName = "testSetLogDir";   
        
        if( m_driverLogRelativeDirName != null )
        {
            m_driverLogAbsoluteDir = 
                OdaConsumerPlugin.getDefault().getStateLocation()
                    .append( "logs" )
                    .append( m_driverLogRelativeDirName ).toFile();
        }
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
        // turn off OdaDriver's logging before log files can be deleted
        String logDir = m_driverLogAbsoluteDir != null ?
                m_driverLogAbsoluteDir.getPath() :
                m_driverLogRelativeDirName;
        LogConfiguration offLogConfig = 
            new LogConfiguration( 
                     TEST_DRIVER_ID,
                     Level.OFF, 
                     logDir,
                     TraceLogTestUtil.CONSUMER_LOG_PREFIX, 
                     null );
        sm_odaDriver.setLogConfiguration( offLogConfig );
        
        cleanupTestLogFiles( sm_consumerLogDir ); // reset for new test case
        if( sm_consumerLogDir != null )
            sm_consumerLogDir.delete();
        
        try
        {
            cleanupTestLogFiles( m_driverLogAbsoluteDir );
            if( m_driverLogAbsoluteDir != null )
                m_driverLogAbsoluteDir.delete();
        }
        catch( IOException e )
        {
            // ignore
            e.printStackTrace();
        }
   }
    
    public void testDriverTraceOptions() throws Exception
    {
        // use the default one set by trace options or traceLogging element
        // change driver log dir in explicit setLogConfig
        String logFilePrefix = "testTraceOptions";
        String logDir = m_driverLogAbsoluteDir != null ?
                m_driverLogAbsoluteDir.getPath() :
                m_driverLogRelativeDirName;
        LogConfiguration logConfig = new LogConfiguration( TEST_DRIVER_ID,
                 Level.FINE,  
                 logDir,
                 logFilePrefix, 
                 "throwsException" );   // triggers test driver to throw exception
        ((OdaDriver) sm_odaDriver ).setLogDirectory( sm_consumerLogDir.getAbsolutePath() );
        sm_odaDriver.setLogConfiguration( logConfig );

        // expects driver has thrown exception, so no log is written
        assertFalse( hasTestLogFiles( m_driverLogAbsoluteDir, logFilePrefix ) );
        
        // expected to use odaconsumer's logs/driver directory 
        // to log driver's unsupported operation exception
        assertTrue( hasTestLogFiles( sm_consumerLogDir, TraceLogTestUtil.CONSUMER_LOG_PREFIX ) );
    }
    
    public void testDriverTraceOptionsAndSetLogConfig() throws Exception
    {
        // change driver log dir in explicit setLogConfig
        String logFilePrefix = "testTraceOptionsAndSetLogConfig";
        LogConfiguration logConfig = new LogConfiguration( TEST_DRIVER_ID,
                 Level.FINE,  // triggers test driver to log
                 m_driverLogAbsoluteDir.getPath(),
                 logFilePrefix, 
                 null );
        sm_odaDriver.setLogConfiguration( logConfig );
      
        // expects driver log gets written to explicit logConfig's logDir
        assertTrue( hasTestLogFiles( m_driverLogAbsoluteDir, logFilePrefix ) );

        // expects default log dir is not used
        String optionsLogDir = TraceLogTestUtil.getOptionsLogDir( TEST_DRIVER_ID );
        if( optionsLogDir != null )
        {
            File defaultLogDir =
                OdaConsumerPlugin.getDefault().getStateLocation()
                    .append( "logs" )
                    .append( optionsLogDir ).toFile();
            assertFalse( defaultLogDir.exists() );
            assertFalse( hasTestLogFiles( defaultLogDir, logFilePrefix ) );  
        }
    }
    
    private void cleanupTestLogFiles( File logDir ) throws IOException
    {      
        TraceLogTestUtil.cleanupTestLogFiles( logDir );
    }
    
    private boolean hasTestLogFiles( File logDir, String logFilePrefix )
    {
        return TraceLogTestUtil.hasTestLogFiles( logDir, logFilePrefix );
    }
    
}
