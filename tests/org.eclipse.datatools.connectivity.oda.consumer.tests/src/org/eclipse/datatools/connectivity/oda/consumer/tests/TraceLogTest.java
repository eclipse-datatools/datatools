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

import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaConsumerPlugin;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaDriver;
import org.eclipse.datatools.connectivity.oda.util.logging.Level;

public class TraceLogTest extends FlatFileTestCase
{
	private static final String CONSUMER_LOG_PREFIX = TraceLogTestUtil.CONSUMER_LOG_PREFIX;
    private static File sm_consumerLogDir;
    private String m_driverLogRelativeDirName;
	private File m_driverLogAbsoluteDir;
    
    protected void setUp() throws Exception
    {
        super.setUp();
            
        if( sm_consumerLogDir == null )
        {
	        sm_consumerLogDir = 
	        		OdaConsumerPlugin.getDefault().getStateLocation()
	        			.append( "logs" )
	        			.append( TEST_FLATFILE_ID ).toFile();
        }
        cleanupTestLogFiles( sm_consumerLogDir );       
        
        if( getName() == "testDefaultLogDir" )
            m_driverLogRelativeDirName = "./OdaLogs";
        else if( getName() == "testSetLogDir" )
            m_driverLogRelativeDirName = "testSetLogDir";
        
        if( m_driverLogRelativeDirName != null )
            m_driverLogAbsoluteDir = 
                OdaConsumerPlugin.getDefault().getStateLocation()
                    .append( "logs" )
                    .append( m_driverLogRelativeDirName ).toFile();
        cleanupTestLogFiles( m_driverLogAbsoluteDir );
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
        
        if( getOdaConn() != null )
            getOdaConn().close();

        // turn off OdaDriver's logging before log files can be deleted
		LogConfiguration offLogConfig = 
		    new LogConfiguration( 
		    		TEST_FLATFILE_ID,
					 Level.OFF, 
                     m_driverLogRelativeDirName,
					 CONSUMER_LOG_PREFIX, 
					 null );
    	getOdaDriver().setLogConfiguration( offLogConfig );
		
        cleanupTestLogFiles( sm_consumerLogDir );
        cleanupTestLogFiles( m_driverLogAbsoluteDir );
    }
    
	private void cleanupTestLogFiles( File logDir ) throws IOException
    {      
        TraceLogTestUtil.cleanupTestLogFiles( logDir );
    }

    public void testDefaultLogDir() throws Exception
    {
    	LogConfiguration logConfig = new LogConfiguration( TEST_FLATFILE_ID,
    						 Level.FINE, 
    						 m_driverLogRelativeDirName,
    						 CONSUMER_LOG_PREFIX, 
    						 null );
    	
    	assertFalse( hasTestLogFiles( sm_consumerLogDir ) );
        if( getOdaDriver() instanceof OdaDriver )
        {
            // reset to use default odaconsumer log dir
            ((OdaDriver) getOdaDriver()).setLogDirectory( sm_consumerLogDir.getPath() );
        }
    	getOdaDriver().setLogConfiguration( logConfig );
    	
    	assertTrue( hasTestLogFiles( sm_consumerLogDir ) );
    }
    
    public void testSetLogDir() throws Exception
    {
    	LogConfiguration logConfig = new LogConfiguration( TEST_FLATFILE_ID,
    						 Level.FINE, 
                             m_driverLogRelativeDirName,
    						 CONSUMER_LOG_PREFIX, 
    						 null );
    	
    	assertFalse( hasTestLogFiles( sm_consumerLogDir ) );
    	
    	String ownLogDir = m_driverLogRelativeDirName;
    	if( getOdaDriver() instanceof OdaDriver )
    	{
    		// overrides the default log dir
    		((OdaDriver) getOdaDriver()).setLogDirectory( ownLogDir );
    	}
    		
        // this is expected to use odaconsumer's logs/$m_logRelativeDirName 
        // to log driver's unsupported operation exception
    	getOdaDriver().setLogConfiguration( logConfig );
    	
    	// default log dir still should not exist
    	assertFalse( hasTestLogFiles( sm_consumerLogDir ) );
    	    	
    	// expects to find log file relative to the odaconsumer logs base dir
    	assertTrue( hasTestLogFiles( m_driverLogAbsoluteDir ) );
    }
    
    private boolean hasTestLogFiles( File logDir )
    {
        return TraceLogTestUtil.hasTestLogFiles( logDir, CONSUMER_LOG_PREFIX );
    }
}
