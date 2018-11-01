/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
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

import org.eclipse.datatools.connectivity.oda.IConnection;

public class BinaryCompatibiltyTest extends OdaTestCase
{
    private IConnection m_connection;

    protected void setUp( ) throws Exception
    {
        super.setUp( );

        m_connection = getTestDriver().getConnection( null );
        assertNotNull( m_connection );
    }
    
    protected String getTestDriverId()
    {
        return "org.eclipse.datatools.connectivity.oda.flatfile";
    }

    protected void tearDown( ) throws Exception
    {
        if ( m_connection.isOpen() )
            m_connection.close( );

        super.tearDown( );
    }

    public void testSetLocale() throws Exception
    {
        boolean hasException = false;
        try
        {
            m_connection.setLocale( null );
        }
        catch( UnsupportedOperationException ex )
        {
            hasException = true;
        }
        assertTrue( hasException );
    }
}
