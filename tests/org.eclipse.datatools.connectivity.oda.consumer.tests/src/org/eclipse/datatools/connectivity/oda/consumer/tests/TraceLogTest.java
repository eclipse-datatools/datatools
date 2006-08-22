/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.datatools.connectivity.oda.util.OdaPlugin;
import org.eclipse.datatools.connectivity.oda.util.logging.Level;

public class TraceLogTest extends FlatFileTestCase
{
	private static final String TEST_LOG_PREFIX = "OdaHelperLog"; //$NON-NLS-1$
    private static File sm_testLogDir;
	
    protected void setUp() throws Exception
    {
        super.setUp();
        
        if( sm_testLogDir == null )
        {
	        sm_testLogDir = 
	        		OdaConsumerPlugin.getDefault().getStateLocation()
	        			.append( "logs" )
	        			.append( TEST_FLATFILE_ID ).toFile();
        }
        cleanupTestLogFiles( sm_testLogDir );
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
					 "./OdaLogs",
					 TEST_LOG_PREFIX, 
					 "java.util.logging.SimpleFormatter" );
    	getOdaDriver().setLogConfiguration( offLogConfig );
		
        cleanupTestLogFiles( sm_testLogDir );
    }
    
	private void cleanupTestLogFiles( File logDir ) throws IOException
    {        
		if( logDir == null || ! logDir.exists() )
			return;		// nothing to clear
        File[] filesInDir = logDir.listFiles();
        if( filesInDir == null )
            return;		// nothing to clear
        
        for ( int i = 0; i < filesInDir.length; i += 1 )
        {         
            boolean deleted = filesInDir[i].delete();
            if ( ! deleted )
                throw new IOException( "Cannot delete file: " + filesInDir[i].getName() );
        }
    }

    public void testDefaultLogDir() throws Exception
    {
    	LogConfiguration logConfig = new LogConfiguration( TEST_FLATFILE_ID,
    						 Level.FINE, 
    						 "./OdaLogs",
    						 TEST_LOG_PREFIX, 
    						 "java.util.logging.SimpleFormatter" );
    	
    	assertFalse( hasTestLogFiles( sm_testLogDir ) );
    	getOdaDriver().setLogConfiguration( logConfig );
    	
    	assertTrue( sm_testLogDir.exists() );
    	assertTrue( hasTestLogFiles( sm_testLogDir ) );
    }
    
    public void testSetLogDir() throws Exception
    {
    	LogConfiguration logConfig = new LogConfiguration( TEST_FLATFILE_ID,
    						 Level.FINE, 
    						 "./OdaLogs",
    						 TEST_LOG_PREFIX, 
    						 "java.util.logging.SimpleFormatter" );
    	
    	assertFalse( hasTestLogFiles( sm_testLogDir ) );
    	
    	String ownLogDir = "testSetLogDir";
    	if( getOdaDriver() instanceof OdaDriver )
    	{
    		// overrides the default log dir
    		((OdaDriver) getOdaDriver()).setLogDirectory( ownLogDir );
    	}
    		
    	getOdaDriver().setLogConfiguration( logConfig );
    	
    	// default log dir still should not exist
    	assertFalse( hasTestLogFiles( sm_testLogDir ) );
    	
    	// expects to find log file relative to the oda LogManager base dir
    	File relativeBaseDir = 
    		OdaPlugin.getDefault().getStateLocation()
        			.append( "logs" )
        			.append( ownLogDir ).toFile();
    	assertTrue( hasTestLogFiles( relativeBaseDir ) );
    	
    	// cleanup 
    	LogConfiguration offLogConfig = new LogConfiguration( 
    										logConfig.getDataSourceId(),
    										Level.OFF,
    										logConfig.getLogDirectory(),
    										logConfig.getLogPrefix(),
    										logConfig.getFormatterClassName() );
    	getOdaDriver().setLogConfiguration( offLogConfig );
        cleanupTestLogFiles( relativeBaseDir );
    }
    
    private boolean hasTestLogFiles( File logDir )
    {
    	String[] filesInDir = logDir.list();
    	if( filesInDir == null )
        	return false;

    	for( int i = 0; i < filesInDir.length; i++ )
    	{
    		if( filesInDir[i].startsWith( TEST_LOG_PREFIX ) )
    			return true;
    	}    	
    	return false;
    }
}
