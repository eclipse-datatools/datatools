/*
 *************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.util;

import java.net.URI;
import java.util.HashMap;

/**
 * <b>Experimental</b>
 *  Represents the resource identifiers of an ODA consumer application.
 *  An ODA consumer application may optionally specify its resource identifiers in an instance,
 *  and pass it to an ODA runtime driver in an application context map.
 *  An application context map is normally passed through the ODA runtime interfaces' setAppContext method(s).
 *  Its support and usage by an individual ODA runtime driver is optional and implementation-dependent.
 *  @since DTP 1.6.1
 */
public class ResourceIdentifiers
{
    /**
     * A pre-defined key to associate an instance of ResourceIdentifiers in an application context map.
     */
    public static final String ODA_APP_CONTEXT_KEY_CONSUMER_RESOURCE_IDS = 
        "org.eclipse.datatools.connectivity.oda.util_" + "consumerResourceIds"; //$NON-NLS-1$ //$NON-NLS-2$
    
    private static final String APPL_RESOURCE_BASE_URI = "ApplResourceBaseURI"; //$NON-NLS-1$
    private static final String DESIGN_RESOURCE_BASE_URI = "DesignResourceBaseURI"; //$NON-NLS-1$
    
    private HashMap m_uriLocators;

    public ResourceIdentifiers()
    {
        m_uriLocators = new HashMap(2);
    }
    
    /**
     * Returns the base URI of general purpose resources of an ODA consumer application.
     * @return  base URI; may be null
     * @see #getApplResourceURILocator()
     */
    public URI getApplResourceBaseURI()
    {
        return getCustomResourceBaseURI( APPL_RESOURCE_BASE_URI );
    }

    /**
     * Specifies the base URI of general purpose resources of an ODA consumer application.
     * It registers the default {@link URILocator} that can be used by a client
     * to resolve a resource URI against the specified baseURI.
     * @param baseURI   base URI; may be null
     * @see #registerApplResourceURILocator(URILocator)
     */
    public void setApplResourceBaseURI( URI baseURI )
    {
        setCustomResourceBaseURI( APPL_RESOURCE_BASE_URI, baseURI );
    }

    /**
     * Returns the URI Locator registered by an ODA consumer application for its application resources.
     * A custom ODA designer may use the URI locator to resolve an application resource URI.
     * @return  application resource URI locator; may be null if none is available
     * @see #getApplResourceBaseURI()
     */
    public URILocator getApplResourceURILocator()
    {
        return getCustomResourceURILocator( APPL_RESOURCE_BASE_URI );
    }

    /**
     * Registers an URILocator defined by an ODA consumer application for its application resources.
     * Replaces any previously registered application resources URILocator, if exists.
     * @param uriLocator    an application resource URI locator
     * @see #setApplResourceBaseURI(URI)
     */
    public void registerApplResourceURILocator( URILocator uriLocator )
    {
        registerCustomResourceURILocator( APPL_RESOURCE_BASE_URI, uriLocator );
    }
    
    /**
     * Returns the base URI of the design resources of an ODA consumer application.
     * The definition of a design resource is dependent on individual consumer application.
     * @return  base URI; may be null
     * @see #getDesignResourceURILocator()
     */
    public URI getDesignResourceBaseURI()
    {
        return getCustomResourceBaseURI( DESIGN_RESOURCE_BASE_URI );
    }

    /**
     * Specifies the base URI of the design resources of an ODA consumer application.
     * The definition of a design resource is dependent on individual consumer application.
     * This registers the default {@link URILocator} that can be used by a client
     * to resolve a resource URI against the specified baseURI.
     * @param baseURI  base URI; may be null
     * @see #registerDesignResourceURILocator(URILocator)
     */
    public void setDesignResourceBaseURI( URI baseURI )
    {
        setCustomResourceBaseURI( DESIGN_RESOURCE_BASE_URI, baseURI );
    }

    /**
     * Returns the URI Locator registered by an ODA consumer application for its design resources.
     * A custom ODA designer may use the URI locator to resolve a design resource URI.
     * @return  design resource URI locator; may be null if none is available
     * @see #getDesignResourceBaseURI()
     */
    public URILocator getDesignResourceURILocator()
    {
        return getCustomResourceURILocator( DESIGN_RESOURCE_BASE_URI );
    }

    /**
     * Registers a URILocator defined by an ODA consumer application for its design resources.
     * Replaces any previously registered design resources URILocator, if exists.
     * @param uriLocator    a design resource URI locator
     * @see #setDesignResourceBaseURI(URI)
     */
    public void registerDesignResourceURILocator( URILocator uriLocator )
    {
        registerCustomResourceURILocator( DESIGN_RESOURCE_BASE_URI, uriLocator );
    }

    private URI getCustomResourceBaseURI( String customResourceType )
    {
        URILocator locator = getCustomResourceURILocator( customResourceType );
        return ( locator != null ) ? locator.getBaseURI() : null;
    }
    
    private void setCustomResourceBaseURI( String customResourceType, URI baseURI )
    {
        URILocator locator = createURILocator( baseURI );
        registerCustomResourceURILocator( customResourceType, locator );
    }
    
    private URILocator getCustomResourceURILocator( String customResourceType )
    {
        return (URILocator) m_uriLocators.get( customResourceType );
    }

    private void registerCustomResourceURILocator( String customResourceType, URILocator uriLocator )
    {
        m_uriLocators.put( customResourceType, uriLocator );
    }

    /**
     * Creates an URILocator based on the specified base URI.
     * An ODA consumer application may extend to create a custom URILocator.
     * @param baseURI  base URI; may be null
     * @return  a new instance of URILocator based on the specified base URI
     */
    protected URILocator createURILocator( URI baseURI )
    {
        return new URILocator( baseURI );
    }
    
    /**
     * Default locator of a resource URI relative to a specified base URI.
     * An ODA consumer application may extend to customize how it proposes to
	 * resolve a resource URI against its resource base URI.
     * An ODA provider may use it to resolve a resource URI, or simply get the base URI 
     * for its own implementation.
     */
    public class URILocator
    {
        private URI m_baseURI;
        
        public URILocator( URI baseURI )
        {
            m_baseURI = baseURI;
        }
        
        public URI getBaseURI()
        {
            return m_baseURI;
        }
        
        public URI resolve( URI uri )
        {
            return resolveImpl( uri );
        }
        
        protected URI resolveImpl( URI uri )
        {
            if( m_baseURI == null )
                return uri;
            return m_baseURI.resolve( uri );
        }
    }
    
}
