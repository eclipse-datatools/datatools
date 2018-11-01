/*
 *************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
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

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.consumer.test.impl.LoggerAccessor;
import org.eclipse.datatools.connectivity.oda.consumer.test.impl.OdaConsumerTestPlugin;
import org.eclipse.datatools.connectivity.oda.util.logging.Level;
import org.eclipse.datatools.connectivity.oda.util.logging.Logger;

/**
 * This test case is for the thread local implementation in odaconusmer.OdaDriver,
 * which caches its static logger instance in each thread.
 */
public class LoggerInstanceTest extends TestCase
{
    static final String TEST_DRIVER_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver"; //$NON-NLS-1$

    private static LoggerAccessor sm_odaDriver;

    private static File sm_consumerLogDir;

    private File m_bundleDir;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        
        sm_odaDriver = new LoggerAccessor( TEST_DRIVER_ID );
        sm_odaDriver.setLogger( null );
        
        if( sm_consumerLogDir == null )
        {
            m_bundleDir = FileLocator.getBundleFile( OdaConsumerTestPlugin
                    .getDefault().getBundle() );
            if( m_bundleDir.isDirectory() )
            {
                sm_consumerLogDir = new File( m_bundleDir.getAbsolutePath()+ "//logs" );  //$NON-NLS-1$
                if( !sm_consumerLogDir.mkdir() )
                {
                    fail( "Unable to create a test log directory." );  //$NON-NLS-1$
                }
            }
            else   
            {
                fail( "Unable to determine the test bundle directory." );  //$NON-NLS-1$
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
        String logDir = sm_consumerLogDir.getAbsolutePath();
        // turn off OdaDriver's logging before log files can be deleted
        LogConfiguration offLogConfig = new LogConfiguration( TEST_DRIVER_ID,
                Level.OFF, logDir, TraceLogTestUtil.CONSUMER_LOG_PREFIX,
                LoggerAccessor.HANDLES_LOG_CONFIG );
        sm_odaDriver.setLogConfiguration( offLogConfig );
        cleanupTestLogFiles( sm_consumerLogDir ); // reset for new test case
        if( sm_consumerLogDir != null )
            sm_consumerLogDir.delete();
        sm_odaDriver = null;
    }

    /**
     * This test evaluates ThreadLocal solution in both cases where driver has
     * logger instance and where driver lacks logger instance for ODA consumer
     * helper driver.
     * 
     * @throws Exception
     */
    public void testLoggerInstance() throws Exception
    {
        // The first test checks for the absence of logger instance
        // when a driver does not provide default logConfig
        // by setting trace options or traceLogging element.
        // For testing purpose we are setting logConfig to be null
        sm_odaDriver.setLogConfiguration( null );
        Logger loggerInstance1 = sm_odaDriver.getLogger();
        // test for null logger instance
        assertNull( loggerInstance1 );

        // The second test provides a new logConfiguration to test shared logger instances
        String logDir = sm_consumerLogDir.getAbsolutePath();
        LogConfiguration logConfig = new LogConfiguration( TEST_DRIVER_ID,
                Level.FINE, logDir, TraceLogTestUtil.CONSUMER_LOG_PREFIX,
                LoggerAccessor.HANDLES_LOG_CONFIG ); // triggers LoggerAccessor to handle log config
        sm_odaDriver.setLogConfiguration( logConfig );
        loggerInstance1 = sm_odaDriver.getLogger();
        Logger loggerInstance2 = sm_odaDriver.getLogger();

        // test of equality for logger instances in single thread
        assertNotNull( loggerInstance1 );
        assertNotNull( loggerInstance2 );
        assertEquals( loggerInstance1, loggerInstance2 );
    }

    private void cleanupTestLogFiles( File logDir ) throws IOException
    {
        TraceLogTestUtil.cleanupTestLogFiles( logDir );
    }

}
