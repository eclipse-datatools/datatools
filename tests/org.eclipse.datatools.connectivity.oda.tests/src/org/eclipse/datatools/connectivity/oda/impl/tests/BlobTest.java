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
import java.io.InputStream;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.impl.Blob;

/**
 *
 */
public class BlobTest extends TestCase
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

    public void testBlob() throws OdaException, IOException
    {
        byte[] bytes = { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 
                0x6, 0x7, 0x8, 0x9, 0xa, 0xb, 0xc, 0xd, 0xe, 0xf };
        
        Blob blob = new Blob( bytes );
        InputStream stream = blob.getBinaryStream();
        
        assertEquals( 16, blob.length() );
        for ( int i = 0; i < blob.length(); i += 1 )
        {
            assertEquals( i, stream.read() );
        }
        
    }
    
}
