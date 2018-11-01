/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.testdriver.TestDriverImpl;
import org.osgi.framework.Bundle;

public class DriverTest extends OdaTestCase
{
	private static final String TEST_DATA_SOURCE_ID = 
		"org.eclipse.datatools.connectivity.oda.consumer.testdriver";
	
    protected void setUp() throws Exception
    {
        super.setUp();
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public final void testSetAppContext() throws OdaException
    {
    	IDriver driver = new TestDriverImpl();
    	String appContext = "Application Context String";
    	driver.setAppContext( appContext );
    	
    	// Retrieve the app context back and compare.
    	String returnedAppContext = ( String ) ( ( TestDriverImpl ) driver ).getAppContext();
    	assertSame( appContext, returnedAppContext );
    }
    
    public final void testGetConnection() throws OdaException
    {
    	IConnection connection = getTestDriver().getConnection( TEST_DATA_SOURCE_ID );
    	assertNotNull( connection );
    }
    
    public final void testGetMaxConnections() throws OdaException
    {
        IDriver testDriver = getNewTestDriver();
    	// Verify that the test driver's maximum number of concurrent connections is 2
    	int maxConnections = 2;
        assertTrue( testDriver.getMaxConnections() == maxConnections );

		Properties connProperties = null;
        ArrayList openConnList = new ArrayList();
        boolean testPassed = false;
        
        // Try opening connection "maxConnections + 1" times.
        // The last time should trigger an exception.
		for( int i = 1 ; i <= maxConnections + 1; i++ )
		{
			try
			{
				IConnection connection = testDriver.getConnection( TEST_DATA_SOURCE_ID );
				assertNotNull( connection );
				connection.open( connProperties );
                
				// cache the connection in a collection for cleanup
                openConnList.add( connection );
			}
			catch( OdaException e )
			{
				/* Expect an ODA exception when number of concurrently opened connections
                 * has exceeded the maximum allowed.
				 */
				assertTrue( i == maxConnections + 1 );
                testPassed = true;
			}
		}
		
		// verify if expected exception did occur
		assertTrue( testPassed );
        
        // Close all opened connections
        Iterator openConnItr = openConnList.iterator();
        while( openConnItr.hasNext() )
        {
            IConnection conn = ( IConnection ) openConnItr.next();
            conn.close();
        }
    }
    
    public final void testSetLogConfiguration() throws OdaException
    {
    	String logDirectory = getTestLogDir();

    	File logDirectoryFileObject = new File( logDirectory );
    	
    	// First, remove this log directory and its contents, if exists.
    	recursiveDelete( logDirectoryFileObject );
    	
 	   // Re-create the log directory.
        boolean success = logDirectoryFileObject.mkdir();

        // Make sure the creation of directory succeeds.  Otherwise, stop 
        // the test.
        assertTrue( success );
        
        String logFilePrefix = "TestDriverLog";
    	LogConfiguration logConfig = 
    		new LogConfiguration( TEST_DATA_SOURCE_ID, 0, logDirectory, logFilePrefix, null );
    	
    	getTestDriver().setLogConfiguration( logConfig );
    	
    	// Detect the existing of the log file.
    	assertTrue( findLogFile( logDirectoryFileObject, logFilePrefix ) );
    	
    	// Notice that the log file cannot be cleaned-up/deleted
    	// at this point, since it is still being held onto.
    	// However, the directory will be deleted the next time
    	// before the real test begins.
    }
    
    private void recursiveDelete( File dirPath ) 
    {
    	String [] ls = dirPath.list();
    	if ( ls == null )
    		return;
      
    	for ( int idx = 0; idx < ls.length; idx++ ) 
    	{
    		File file = new File( dirPath, ls[ idx ] );
    		if ( file.isDirectory () )
    			recursiveDelete( file );
    		file.delete ();
    	}
    	
    	dirPath.delete();
    }
    
    private boolean findLogFile( File dirPath, String prefix ) 
    {
    	String [] ls = dirPath.list();
    	if ( ls == null )
    		return false;
      
    	// We only need to look at the first file and see if 
    	// it has the log file prefix.
   		return ( ls[ 0 ].indexOf( prefix ) >= 0 );
    }

    private String getTestLogDir()
	{
    	String pluginPath = null;
		try 
		{
			Bundle b = Platform.getBundle( getPluginName() );
			pluginPath = FileLocator.toFileURL( b.getEntry( "/" ) ).getFile();
		} 
		catch ( IOException e ) 
		{
			fail();
		}       
		
		return pluginPath + "TestLogDir\\";
	}
    
    private String getPluginName()
    {
    	return getClass().getPackage().getName();
    }
}
