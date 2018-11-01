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
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.impl.tests;

import java.io.IOException;
import java.io.Reader;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.impl.Clob;

/**
 *
 */
public class ClobTest extends TestCase
{

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testClob() throws OdaException, IOException
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        Clob clob = new Clob( alphabet );
        Reader reader = clob.getCharacterStream();
        
        assertEquals( 26, clob.length() );
        for ( int i = 0; i < clob.length(); i += 1)
        {
            assertEquals( 'a' + i, reader.read() );
        }
    }

}
