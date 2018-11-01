/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.helper.DriverExtensionHelper;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaDriver;
import org.eclipse.datatools.connectivity.oda.consumer.test.impl.DummyDriver;

import junit.framework.TestCase;

/**
 * Test implementation of the driverBridge extension point.
 */
public class DriverBridgeTest extends TestCase
{
    // requires the org.eclipse.datatools.connectivity.oda.consumer.testdriver plugin,
    // and the test extension of the oda.consumer.driverBridge extension point 
    // defined in this test plugin
    private final String m_testOdaDataSourceId = 
        "org.eclipse.datatools.connectivity.oda.consumer.testdriver"; //$NON-NLS-1$
    /* 
     * Tests the public API to obtain the effective data source id that overrides 
     * via the driverBridge extension point.
     */
    public void testGetEffectiveDataSourceId()
    {
        String expectedBridgeId = "org.eclipse.datatools.connectivity.oda.consumer.tests"; //$NON-NLS-1$
        String actualBridgeId = null;
        try
        {
            actualBridgeId = DriverExtensionHelper.getEffectiveDataSourceId( m_testOdaDataSourceId );
        }
        catch( OdaException ex )
        {
            fail();
        }
        assertEquals( expectedBridgeId, actualBridgeId );
    }
    
    public void testGetEffectiveBridgeDriver()
    {
        IDriver consumerDriver = null;
        try
        {
            consumerDriver = new OdaDriver( m_testOdaDataSourceId );
        }
        catch( OdaException e )
        {
            fail();
        }       
        assertNotNull( consumerDriver );

        Map contextMap = new HashMap(1);
        try
        {
            // the test bridge IDriver has implemented #setAppContext to put its classname in the Map
            consumerDriver.setAppContext( contextMap );
        }
        catch( OdaException e )
        {
            fail();
        }

        assertEquals( 1, contextMap.size() );
        assertEquals( DummyDriver.class.getName(), contextMap.get( DummyDriver.DRIVER_NAME_CONTEXT_KEY ));
    }
    
}
