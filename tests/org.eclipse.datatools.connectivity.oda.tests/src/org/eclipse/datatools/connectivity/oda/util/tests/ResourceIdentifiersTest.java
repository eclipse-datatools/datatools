/*
 *************************************************************************
 * Copyright (c) 2008, 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.util.tests;

import java.io.IOException;
import java.net.URI;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers.URILocator;
import org.osgi.framework.Bundle;

import junit.framework.TestCase;

/**
 * Test cases of the {@link ResourceIdentifiers} class.
 */
public class ResourceIdentifiersTest extends TestCase
{

    private static final String TEST_BUNDLE_NAME = "org.eclipse.datatools.connectivity.oda.tests"; //$NON-NLS-1$
    
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testDefaultURILocators() throws Exception
    {
        URI baseURI = new URI( getPluginTestFilePath() );
        ResourceIdentifiers ids = new ResourceIdentifiers();
        ids.registerApplResourceURILocator( ids.new URILocator( baseURI ) );    // register the default URILocator
        assertEquals( baseURI, ids.getApplResourceBaseURI() );
        
        URI expectedResource = new URI( getPluginTestFilePath() + "dummy" ); //$NON-NLS-1$
        assertEquals( expectedResource, ids.getApplResourceURILocator().resolve( new URI( "dummy") ) ); //$NON-NLS-1$
        assertEquals( expectedResource, ids.resolveApplResource(  new URI( "dummy") ) ); //$NON-NLS-1$
        
        // test that a default URILocator is automatically registered
        ids.setDesignResourceBaseURI( baseURI );
        assertTrue( ids.getDesignResourceURILocator() instanceof URILocator );
        assertEquals( expectedResource, ids.getDesignResourceURILocator().resolve( new URI( "dummy") ) ); //$NON-NLS-1$
        assertEquals( expectedResource, ids.resolveDesignResource( new URI( "dummy") ) ); //$NON-NLS-1$
    }
  
    public void testCustomURILocators() throws Exception
    {
        URI baseURI = new URI( getPluginTestFilePath() );
        ResourceIdentifiers ids = new ResourceIdentifiers();
        ids.registerDesignResourceURILocator( new CustomURILocator( ids, baseURI ) );  // CustomURILocator always resolves to the baseURI
        
        assertEquals( baseURI, ids.getDesignResourceBaseURI() );
        assertEquals( baseURI, ids.getDesignResourceURILocator().resolve( new URI( "dummy") ) ); //$NON-NLS-1$
        assertEquals( baseURI, ids.resolveDesignResource( new URI( "dummy") ) ); //$NON-NLS-1$
    }
    
    private String getPluginTestFilePath()
        throws IOException
    {       
        Bundle bundle = Platform.getBundle( TEST_BUNDLE_NAME );
        String testFilePath = FileLocator.getBundleFile( bundle ).toURL().toString();
        return testFilePath;
    }
    
    private class CustomURILocator extends URILocator
    {

        public CustomURILocator( ResourceIdentifiers identifiers,
                URI baseURI )
        {
            identifiers.super( baseURI );
        }

        /* (non-Javadoc)
         * @see org.eclipse.datatools.connectivity.oda.consumer.services.impl.ResourceIdentifiers.URILocator#locate(java.net.URI)
         */
        public URI resolve( URI uri )
        {
            // always resolves to the baseURI
            return super.getBaseURI();
        }
        
    }
}
