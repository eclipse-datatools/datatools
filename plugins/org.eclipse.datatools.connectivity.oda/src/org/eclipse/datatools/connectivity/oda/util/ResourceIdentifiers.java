/*
 *************************************************************************
 * Copyright (c) 2008, 2009 Actuate Corporation.
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
 * <b>Provisional</b>
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
    
    private static final String APPL_RESOURCE_TYPE = "ApplResourceType"; //$NON-NLS-1$
    private static final String DESIGN_RESOURCE_TYPE = "DesignResourceType"; //$NON-NLS-1$
    
    private HashMap m_uriLocators;

    public ResourceIdentifiers()
    {
        m_uriLocators = new HashMap(2);
    }

    /**
     * Resolves the specified URI against the base URI of an ODA consumer application's 
     * general purpose resources.
     * It uses the application resource URI Locator registered by an ODA consumer application 
     * to resolve an application resource URI.
     * @param uri   the URI to be resolved against the application resource base URI
     * @return      the resulting URI
     * @since DTP 1.7
     */
    public URI resolveApplResource( URI uri )
    {
        return resolveResourceURI( APPL_RESOURCE_TYPE, uri );
    }
    
    /**
     * Returns the base URI of general purpose resources of an ODA consumer application.
     * The returned URI is application-dependent, and may be not well defined. 
     * In such case, the client should use {@link #resolveApplResource(URI)} or
     * get the URI locator in {@link #getApplResourceURILocator()} to resolve a relative URI reference.
     * @return  base URI; may be null
     * @see #resolveApplResource(URI)
     * @see #getApplResourceURILocator()
     */
    public URI getApplResourceBaseURI()
    {
        return getResourceBaseURI( APPL_RESOURCE_TYPE );
    }

    /**
     * Specifies the base URI of general purpose resources of an ODA consumer application.
     * It registers an {@link URILocator} that can be used by a client
     * to resolve a resource URI against the specified baseURI.
     * Any previously registered application resource URILocator and its base URI is replaced.
     * @param baseURI   base URI; may be null
     * @see #registerApplResourceURILocator(URILocator)
     */
    public void setApplResourceBaseURI( URI baseURI )
    {
        setResourceBaseURI( APPL_RESOURCE_TYPE, baseURI );
    }

    /**
     * Returns the URI Locator registered by an ODA consumer application for its application resources.
     * A custom ODA designer may use the URI locator to resolve an application resource URI.
     * @return  application resource URI locator; may be null if none is available
     * @see #getApplResourceBaseURI()
     */
    public URILocator getApplResourceURILocator()
    {
        return getResourceURILocator( APPL_RESOURCE_TYPE );
    }

    /**
     * Registers an URILocator defined by an ODA consumer application for its application resources.
     * Replaces any previously registered application resources URILocator and its base URI.
     * @param uriLocator    an application resource URI locator
     * @see #setApplResourceBaseURI(URI)
     */
    public void registerApplResourceURILocator( URILocator uriLocator )
    {
        registerResourceURILocator( APPL_RESOURCE_TYPE, uriLocator );
    }

    /**
     * Resolves the specified URI against the base URI of an ODA consumer application's design resources.
     * It uses the design resource URI Locator registered by an ODA consumer application 
     * to resolve a design resource URI.
     * @param uri   the URI to be resolved against the design resource base URI
     * @return      the resulting URI
     * @since DTP 1.7
     */
    public URI resolveDesignResource( URI uri )
    {
        return resolveResourceURI( DESIGN_RESOURCE_TYPE, uri );
    }
    
    /**
     * Returns the base URI of the design resources of an ODA consumer application.
     * The definition of a design resource is dependent on individual consumer application.
     * The returned URI is application-dependent, and may be not well defined. 
     * In such case, the client should use {@link #resolveDesignResource(URI)} or
     * get the URI locator in {@link #getDesignResourceURILocator()} to resolve a relative URI reference.
     * @return  base URI reference; may be null
     * @see #resolveDesignResource(URI)
     * @see #getDesignResourceURILocator()
     */
    public URI getDesignResourceBaseURI()
    {
        return getResourceBaseURI( DESIGN_RESOURCE_TYPE );
    }

    /**
     * Specifies the base URI of the design resources of an ODA consumer application.
     * The definition of a design resource is dependent on individual consumer application.
     * It registers an {@link URILocator} that can be used by a client
     * to resolve a resource URI against the specified baseURI.
     * Any previously registered design resource URILocator and its base URI is replaced.
     * @param baseURI  base URI; may be null
     * @see #registerDesignResourceURILocator(URILocator)
     */
    public void setDesignResourceBaseURI( URI baseURI )
    {
        setResourceBaseURI( DESIGN_RESOURCE_TYPE, baseURI );
    }

    /**
     * Returns the URI Locator registered by an ODA consumer application for its design resources.
     * A custom ODA designer may use the URI locator to resolve a design resource URI.
     * @return  design resource URI locator; may be null if none is available
     * @see #getDesignResourceBaseURI()
     */
    public URILocator getDesignResourceURILocator()
    {
        return getResourceURILocator( DESIGN_RESOURCE_TYPE );
    }

    /**
     * Registers a URILocator defined by an ODA consumer application for its design resources.
     * Replaces any previously registered design resources URILocator and its base URI.
     * @param uriLocator    a design resource URI locator
     * @see #setDesignResourceBaseURI(URI)
     */
    public void registerDesignResourceURILocator( URILocator uriLocator )
    {
        registerResourceURILocator( DESIGN_RESOURCE_TYPE, uriLocator );
    }

    private URI getResourceBaseURI( String resourceType )
    {
        URILocator locator = getResourceURILocator( resourceType );
        return ( locator != null ) ? locator.getBaseURI() : null;
    }
    
    private void setResourceBaseURI( String resourceType, URI baseURI )
    {
        // create a new URILocator with the specified baseURI to
        // replace any previously registered URILocator and its base URI
        URILocator locator = createURILocator( baseURI );
        registerResourceURILocator( resourceType, locator );
    }
    
    private URI resolveResourceURI( String resourceType, URI uri )
    {
        URILocator locator = getResourceURILocator( resourceType );
        return ( locator != null ) ? locator.resolve( uri ) : uri;
    }

    private URILocator getResourceURILocator( String resourceType )
    {
        return (URILocator) m_uriLocators.get( resourceType );
    }

    private void registerResourceURILocator( String resourceType, URILocator uriLocator )
    {
        m_uriLocators.put( resourceType, uriLocator );
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
     * The default locator of a resource URI relative to a specified base URI.
     * An ODA consumer application may extend to customize how it proposes to
	 * resolve a relative URI reference against its resource base URI.
     * An ODA data provider may use the locator to resolve a resource's relative URI;
     * or if the application-dependent base URI is well defined, get the base URI 
     * for its own processing.
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
