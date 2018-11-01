/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
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

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IAdvancedQuery;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.testdriver.TestAdvQueryImpl;
import org.eclipse.datatools.connectivity.oda.consumer.testdriver.TestConnectionImpl;
import org.eclipse.datatools.connectivity.oda.consumer.testdriver.TestDriverImpl;

/**
 *  Test ODA Consumer handling of passing thru application context objects
 *  to an underlying ODA driver.
 */
public class AppContextTest extends OdaTestCase
{
    private Properties sm_appContextMap = null;
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        
	    if( sm_appContextMap == null )
	    {
	        sm_appContextMap = new Properties();

	        sm_appContextMap.put( getTestDriverId(), getTestDriver() );
	        sm_appContextMap.put( TestDriverImpl.TEST_DRIVER_CONN_STATE, "" );
	    }
    }
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
        sm_appContextMap.put( TestDriverImpl.TEST_DRIVER_CONN_STATE, "" );
    }
  
    /*
     * Test that setAppContext is passed thru the ODA Consumer
     * to an ODA driver's IDriver, IConnection and IQuery setAppContext calls
     * in the normal ODA calls sequence.
     */
    public void testSetAppContext() throws OdaException
    {
        verifyDriverSetAppContext();
        
       	doTestSetAppContext( false );
    }
       
    /*
     * Test that setAppContext is passed thru to an opened connection,
     * i.e. the driver's IConnection.isOpen returns true *before* IConnection.open is called.
     */
    public void testSetAppContextOpenConnection() throws OdaException
    {
        // indicate to test driver to set connection state to isOpen() == true
        // without waiting for the open call
        sm_appContextMap.put( TestDriverImpl.TEST_DRIVER_CONN_STATE, 
                			  TestDriverImpl.TEST_DRIVER_CONN_STATE_OPEN );
        
       	doTestSetAppContext( true );
    } 
    
    /*
     * Perform the actual setting of the app context to the oda driver.
     * After creating a query object, preparing and executing it,
     * then verify whether the app context has been successfly passed to the 
     * query object.
     */
    private void doTestSetAppContext( boolean isDriverConnStateOpen ) throws OdaException
    {
    	IDriver odaDriver = getTestDriver();
    	
    	// Assign the app context to the driver
    	odaDriver.setAppContext( sm_appContextMap );
    	
        /* creates a new Oda connection.  The oda consumer passes thru
         * driver context to the underlying connection as a driver context,
         * before calling open on underlying connection.
         */
     	IConnection hostConn = odaDriver.getConnection( null );
        assertNotNull( hostConn );
        
        /* verifies appContext got passed thru to connection instance,
         * setting the testdriver's connection state, 
         * before connection.open is called 
         */
        if( isDriverConnStateOpen )
            assertTrue( hostConn.isOpen() );
        else
            assertFalse( hostConn.isOpen() );
       
        /* Open the connection.  The oda consumer sets
         * driver context as the underlying connection's own appContext.
         */
        hostConn.open( null );        
            
        // uses default dataSetType in plugin.xml
        IAdvancedQuery hostQuery = ( IAdvancedQuery ) hostConn.newQuery( null );
        assertNotNull( hostQuery );
            
        /* Prepare the query with a statement.
         * During prepare(), the oda consumer passes thru
         * the connection's appContext as the query's own appContext.
         */
        hostQuery.prepare( "Simple Query" );
            
        // execute the query.
        boolean result = hostQuery.execute();
        assertTrue( result );
            
        // The string representation of the query's appContext will
        // be returned as the first output parameter value.
        String queryContextStr = hostQuery.getString( 1 );

        // Verify if the app context returned by the
        // query object is same as the one originally assigned to the driver.
        assertEquals( sm_appContextMap.toString(), queryContextStr );
        
        // close the query
        hostQuery.close();
        hostConn.close();
    } 
    
    /*
     * Verify that the ODA driver tester has implemented
     * the setAppContext method.
     */
    private void verifyDriverSetAppContext()
    {
    	IDriver odaDriver = new TestDriverImpl();
    	
        try
        {
            // test call to IDriver.setAppContext
            odaDriver.setAppContext( sm_appContextMap );
        }
        catch( OdaException e )
        {
            fail( "The ODA driver tester is not properly setup.");
        }
        
        Object driverContext = ( ( TestDriverImpl ) odaDriver ).getAppContext();
        assertNotNull( driverContext );
        assertEquals( driverContext, sm_appContextMap );
        assertEquals( sm_appContextMap.toString(), driverContext.toString() );
        
        // test call to IConnection.setAppContext
        IConnection odaConn = null;
        try
        {
            // test call to IConnection.setAppContext
            odaConn = odaDriver.getConnection( null );
            odaConn.setAppContext( sm_appContextMap );
        }
        catch( OdaException e1 )
        {
            fail( "The ODA driver tester is not properly setup.");
        }
        
        driverContext = ( ( TestConnectionImpl ) odaConn ).getAppContext();
        assertNotNull( driverContext );
        assertEquals( driverContext, sm_appContextMap );
        assertEquals( sm_appContextMap.toString(), driverContext.toString() );
        
        // test call to IQuery.setAppContext
        IQuery odaQuery = null;
        try
        {
            // test call to IConnection.setAppContext
            odaQuery = odaConn.newQuery( "dummy" );
            odaQuery.setAppContext( sm_appContextMap );
        }
        catch( OdaException e1 )
        {
            fail( "The ODA driver tester is not properly setup.");
        }
        
        driverContext = ( ( TestAdvQueryImpl ) odaQuery ).getAppContext();
        assertNotNull( driverContext );
        assertEquals( driverContext, sm_appContextMap );
        assertEquals( sm_appContextMap.toString(), driverContext.toString() );
    }
}
