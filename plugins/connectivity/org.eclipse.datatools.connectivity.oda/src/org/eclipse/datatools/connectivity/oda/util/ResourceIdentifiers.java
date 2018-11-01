/*
 *************************************************************************
 * Copyright (c) 2008, 2014 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 *  Represents the resource identifiers of an ODA consumer application.
 *  An ODA consumer application may optionally specify its resource identifiers in an instance,
 *  and pass it to an ODA runtime driver in an application context map.
 *  An application context map is normally passed through the ODA runtime interfaces' setAppContext method(s).
 *  Its support and usage by an individual ODA runtime driver is optional and implementation-dependent.
 *  @since 3.2 (DTP 1.7)
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
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$

    private static String sm_loggerName = ResourceIdentifiers.class.getName();
    private static Logger sm_logger = Logger.getLogger( sm_loggerName );
    
    private HashMap<String, URILocator> m_uriLocators;

    /**
     * A convenience method to obtain the ResourceIdentifiers instance stored in 
     * the specified application context Map with the pre-defined key.
     * @param appContext    an application context Map
     * @return  a ResourceIdentifiers instance, normally set by an ODA consumer application;
     *          may be null if none is available
     * @since 3.3.2 (DTP 1.9)
     */
    public static ResourceIdentifiers get( Object appContext )
    {
        if( !(appContext instanceof Map) )
            return null;
        Object mapValue = ((Map<?,?>)appContext).get( ODA_APP_CONTEXT_KEY_CONSUMER_RESOURCE_IDS );
        return ( mapValue instanceof ResourceIdentifiers ) ? 
                (ResourceIdentifiers)mapValue : null;
    }
    
    public ResourceIdentifiers()
    {
        m_uriLocators = new HashMap<String, URILocator>(2);
    }

    /**
     * A convenience method for a client to invoke the method {@link #resolveApplResource(URI)}
     * on the specified ResourceIdentifiers instance.  
     * This supports the use case where the class of the specified instance 
     * is not accessible by this class loader(s).
     * @param instance  an instance of {@link ResourceIdentifiers}
     * @param uri   the URI to be resolved against the application resource base URI
     * @return      the resulting URI
     * @since 3.3.3 (DTP 1.9.2)
     * @see #resolveApplResource(URI)
     */
    public static URI resolveApplResource( Object instance, URI uri )
    {
        if( instance == null )   // not available
            return null;    // unable to resolve the specified URI
        
        final String instMethodName = "resolveApplResource"; //$NON-NLS-1$
        URI resolvedFilePathURI = (instance instanceof ResourceIdentifiers) ?
                ((ResourceIdentifiers)instance).resolveApplResource( uri ) :
                    // use reflection to invoke the method
                ReflectionHelper.invokeResolveMethod( instance, instMethodName, uri );
        
        return resolvedFilePathURI;
    }

    /**
     * A convenience method for a client to invoke the method {@link #getApplResourceBaseURI()}
     * on the specified ResourceIdentifiers instance.  
     * This supports the use case where the class of the specified instance 
     * is not accessible by this class loader(s).
     * @param instance  an instance of {@link ResourceIdentifiers}
     * @return      the resulting URI
     * @since 3.3.3 (DTP 1.9.2)
     * @see {@link #getApplResourceBaseURI()}
     */
    public static URI getApplResourceBaseURI( Object instance )
    {
        if( instance == null )   // not available
            return null;    // unable to resolve the specified URI
        
        final String instMethodName = "getApplResourceBaseURI"; //$NON-NLS-1$
        URI resourceBaseURI = (instance instanceof ResourceIdentifiers) ?
                ((ResourceIdentifiers)instance).getApplResourceBaseURI() :
                    // use reflection to invoke method
                 ReflectionHelper.invokeGetURIMethod( instance, instMethodName );
        
        return resourceBaseURI;
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
     * A convenience method for a client to invoke the method {@link #resolveDesignResource(URI)}
     * on the specified ResourceIdentifiers instance.  
     * This supports the use case where the class of the specified instance 
     * is not accessible by this class loader(s).
     * @param instance  an instance of {@link ResourceIdentifiers}
     * @param uri   the URI to be resolved against the design resource base URI
     * @return      the resulting URI
     * @since 3.3.3 (DTP 1.9.2)
     * @see #resolveDesignResource(URI)
     */
    public static URI resolveDesignResource( Object instance, URI uri )
    {
        if( instance == null )   // not available
            return null;    // unable to resolve the specified URI
        
        final String instMethodName = "resolveDesignResource"; //$NON-NLS-1$
        URI resolvedFilePathURI = (instance instanceof ResourceIdentifiers) ?
                ((ResourceIdentifiers)instance).resolveDesignResource( uri ) :
                    // use reflection to invoke method
                 ReflectionHelper.invokeResolveMethod( instance, instMethodName, uri );
        
        return resolvedFilePathURI;
    }

    /**
     * A convenience method for a client to invoke the method {@link #getDesignResourceBaseURI()}
     * on the specified ResourceIdentifiers instance.  
     * This supports the use case where the class of the specified instance 
     * is not accessible by this class loader(s).
     * @param instance  an instance of {@link ResourceIdentifiers}
     * @return      the resulting URI
     * @since 3.3.3 (DTP 1.9.2)
     * @see {@link #getDesignResourceBaseURI()}
     */
    public static URI getDesignResourceBaseURI( Object instance )
    {
        if( instance == null )   // not available
            return null;    // unable to resolve the specified URI
        
        final String instMethodName = "getDesignResourceBaseURI"; //$NON-NLS-1$
        URI resourceBaseURI = (instance instanceof ResourceIdentifiers) ?
                ((ResourceIdentifiers)instance).getDesignResourceBaseURI() :
                    // use reflection to invoke method
                 ReflectionHelper.invokeGetURIMethod( instance, instMethodName );
        
        return resourceBaseURI;
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
        return m_uriLocators.get( resourceType );
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
     * Converts and resolves the specified resource location path to a URI, based on the 
     * registered resource URILocator and its base URI.  
     * This will first attempt to use the application resources URILocator, if exists;
     * otherwise, its design resources URILocator is used.
     * @param resourcePath  the path to be encoded and resolved to a URI;
     *              may be an absolute or relative path
     * @return  resolved URI; may be null if not able to resolve
     * @since 3.3.2 (DTP 1.9)
     */
    public URI resolveResourceLocation( String resourcePath )
    {
        // use the application resource locator, if available, to resolve runtime location;
        // otherwise, use the design resource locator if available
        URILocator locator = getApplResourceURILocator();
        if( locator == null )
            locator = getDesignResourceURILocator();
        return locator != null ?
                    locator.resolve( resourcePath ) : 
                    null;     // not able to resolve    
    }

    /**
     * Encode non-US-ASCII characters in specified file path into an URI.
     * @param filePath  the string representation of a file, 
     * @return  the encoded URI, or null if unable to encode
     * @since 3.3.3 (DTP 1.9.2)
     */
    public static URI encodeToURI( String filePath )
    {
        try
        {
            // use URI encoding implementation;
            String encodedLocation = new File( filePath ).toURI( ).toASCIIString( );
            String target =  new File( EMPTY_STRING ).toURI( ).toASCIIString( );
            // strip out the interim root path added by the file conversion
            encodedLocation = encodedLocation.replace( target, EMPTY_STRING );
            return new URI( encodedLocation );
        }
        catch( Exception ex )
        {
            // log and ignore
            sm_logger.info( "encodeToURI(String): " + ex.toString() );  //$NON-NLS-1$
        }
        return null;
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
        
        /** 
         * Resolves the specified string to a URI.
         * @param str   the string to be encoded and parsed into a URI
         * @return  resolved URI; may be null if not able to resolve
         * @since 3.3.2 (DTP 1.9)
         */
        public URI resolve( String str )
        {
            URI uri = encodeToURI( str );
            return resolve( uri );
        }
        
        protected URI resolveImpl( URI uri )
        {
            if( m_baseURI == null || uri == null )
                return uri;
            return m_baseURI.resolve( uri );
        }
    }
    
    /*
     * Internal helper to invoke method(s) by reflection.
     */
    private static class ReflectionHelper
    {
        private static URI invokeResolveMethod( Object instance, String methodName, URI argValue ) 
        {
            Method resolveMethod = getMethod( instance, methodName, URI.class );
            if( resolveMethod != null )
            {
                Object returnValue = null;
                try
                {
                    returnValue = invokeMethod( instance, resolveMethod, argValue );
                    if( returnValue instanceof URI )
                        return (URI)returnValue;
                }
                catch( OdaException ex )
                {
                    sm_logger.fine( "Unable to invoke method (" + methodName +  //$NON-NLS-1$
                            ") on the specified ResourceIdentifiers instance: " + ex.getMessage() );   //$NON-NLS-1$
                }
            }
            return null;  
        }

        private static URI invokeGetURIMethod( Object instance, String methodName ) 
        {
            Method resolveMethod = getMethod( instance, methodName, null );
            if( resolveMethod != null )
            {
                Object returnValue = null;
                try
                {
                    returnValue = invokeMethod( instance, resolveMethod, null );
                    if( returnValue instanceof URI )
                        return (URI)returnValue;
                }
                catch( OdaException ex )
                {
                    sm_logger.fine( "Unable to invoke method (" + methodName +  //$NON-NLS-1$
                            ") on the specified ResourceIdentifiers instance: " + ex.getMessage() );   //$NON-NLS-1$
                }
            }
            return null;  
        }
                        
        private static Method getMethod( Object instance, String methodName, Class<?> argClazz )
        {
            if( instance == null )
                return null;
            
            try
            {
                if( argClazz == null )
                    return instance.getClass().getMethod( methodName );
                else
                    return instance.getClass().getMethod( methodName, argClazz );
            }
            catch( SecurityException ex )
            {
                sm_logger.fine( "Unable to get method (" + methodName +  //$NON-NLS-1$
                        ") from the specified ResourceIdentifiers instance: " + ex.getMessage() );   //$NON-NLS-1$
            }
            catch( NoSuchMethodException ex )
            {
                sm_logger.fine( "Unable to get method (" + methodName +  //$NON-NLS-1$
                        ") from the specified ResourceIdentifiers instance: " + ex.getMessage() );   //$NON-NLS-1$
            }            
            return null;
        }
        
        private static Object invokeMethod( Object instance, Method method, Object argValue )
            throws OdaException
        {
            if( instance == null || method == null )
                throw new OdaException( new IllegalArgumentException( "Null" ) ); //$NON-NLS-1$

            try
            {
                if( argValue == null )
                    return method.invoke( instance );
                else
                    return method.invoke( instance, argValue );
            }
            catch ( IllegalArgumentException ex )
            {
                throw new OdaException( ex );
            }
            catch ( IllegalAccessException ex )
            {
                throw new OdaException( ex );
            }
            catch ( InvocationTargetException ex )
            {
                throw new OdaException( ex );
            }
        }
    }
    
}
