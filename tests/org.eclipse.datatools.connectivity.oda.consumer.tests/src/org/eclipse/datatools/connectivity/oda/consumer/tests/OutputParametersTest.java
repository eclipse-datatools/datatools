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

import org.eclipse.datatools.connectivity.oda.IAdvancedQuery;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Test ODA Consumer handling of output parameters.
 */
public class OutputParametersTest extends OdaTestCase
{
    protected void setUp() throws Exception
    {
        super.setUp();
    }
    
    public void testOutputParamDataTypeMapping()
    {
        Object outParam2Value = null;
        Object outParam3Value = null;
        
        try
        {
        	IDriver odaDriver = getTestDriver();
            IConnection hostConn = odaDriver.getConnection( null );
            assertNotNull( hostConn );
            
            hostConn.open( null );
            
            // uses default dataSetType in the test driver.
            IAdvancedQuery hostQuery = ( IAdvancedQuery ) hostConn.newQuery( null );
            
            // Prepare query.
            hostQuery.prepare( "Simple Query" );
            
            // Execute query.
            boolean result = hostQuery.execute();
            assertTrue( result );
            
            outParam2Value = hostQuery.getDate( 2 );
            outParam3Value = hostQuery.getString( 3 );
            
            // close query and connection
            hostQuery.close();
            hostConn.close();
        }
        catch( OdaException e )
        {
            fail( "testOutputParamDataTypeMapping failed: " + e.toString() );
        }

        // parameter 2 is expected to have a data type with mapping
        // in test driver's plugin.xml, and would thus trigger the
        // correct call to getDate, returning a Date value
        assertNotNull( outParam2Value );
        assertTrue( outParam2Value instanceof java.util.Date );

        // parameter 3 is not expected to have a data type with mapping
        // in test driver's plugin.xml; so it will be mapped to a String by default,
        // and getString will be called, returning a String value
        assertNotNull( outParam3Value );
        assertTrue( outParam3Value instanceof String );
    } 
}
