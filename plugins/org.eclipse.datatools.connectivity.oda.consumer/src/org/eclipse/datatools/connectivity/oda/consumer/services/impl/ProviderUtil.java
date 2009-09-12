/*
 *************************************************************************
 * Copyright (c) 2007, 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.services.impl;

import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.ExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.PropertyProviderManifest;
import org.eclipse.datatools.connectivity.oda.util.logging.LogManager;
import org.eclipse.datatools.connectivity.oda.util.logging.Logger;

/**
 * Utility class for the providers of ODA consumer services.
 */
public class ProviderUtil
{
    private static final String sm_loggerName = "org.eclipse.datatools.connectivity.oda.consumer"; //$NON-NLS-1$

    
    /**
     * Instantiates and returns the property provider of the specified application
     * that implements the ODA consumer propertyProvider extension point.
     * @param applicationId     the identifier of an ODA consumer application; 
     *          its value must match the consumerApplicationId attribute of 
     *          a propertyProvider extension
     * @return  an instance of the providerClass implemented in the specified
     *          propertyProvider extension
     * @throws OdaException
     */
    public static IPropertyProvider createPropertyProvider( String applicationId )
        throws OdaException
    {
        final String methodName = "ProviderUtil.getPropertyProvider( String )\t"; //$NON-NLS-1$
    
        if( applicationId == null || applicationId.length() == 0 )
        {
            log( methodName, "No application id specified in method argument." ); //$NON-NLS-1$
            return null;    // no consumer application id specified in appContext
        }
        
        PropertyProviderManifest providerManifest = 
            ExtensionExplorer.getInstance().getPropertyProviderManifest( applicationId );
        if( providerManifest == null )
        {
            log( methodName, "No IPropertyProvider found for " + applicationId + "." ); //$NON-NLS-1$ //$NON-NLS-2$
            return null;    // no extension defined by application
        }
          
        IPropertyProvider extnProvider = providerManifest.createProvider();
        log( methodName, "Returning IPropertyProvider instance (" + extnProvider  //$NON-NLS-1$
                            + ") of application id (" + applicationId + ")." ); //$NON-NLS-1$ //$NON-NLS-2$
        return extnProvider;
    }

    /**
     * Provides the effective property values to use at runtime to open
     * a connection to a data source.
     * @param candidateProperties  the set of candidate property name-value pairs 
     *              specified by an ODA consumer application
     *              in its call to an ODA driver's IConnection.open method.
     *              It may contain a configuration id or key 
     *              specific to the consumer application
     *              to uniquely identify a set of externalized property values.
     *              Such use is entirely implementation dependent.
     * @param appContext   the application context provided by an 
     *          ODA consumer application in its call to an ODA driver's
     *          IDriver.setAppContext method.
     *          <br>The provider may use this application-specific context to
     *          assist in its lookup and processing of its externalized 
     *          configuration framework.  May be null.
     * @return  the set of effective property name-value pairs to use
     * @throws OdaException     if provider error occurs
     * @since 3.2.2 (DTP 1.7.2)
     */
    public static Properties getEffectiveProperties( Properties candidateProperties, Object appContext )
        throws OdaException
    {
        IPropertyProvider propProvider = createPropertyProvider( getConsumerApplicationId( appContext ) );

        // no configured service provider, use original properties
        if( propProvider == null )
            return candidateProperties; 
        
        // calls the implementation of the interface method, passing in 
        // the connection properties and context specified by the consumer
        Properties effectiveProps =
            propProvider.getDataSourceProperties( candidateProperties, 
                                getConnectionPropertyContext( appContext ) );
        return effectiveProps;
    }
    
    /**
     * Returns the consumer application id, if exists, in the specified application context.
     * @param appContext    the application context provided by an ODA consumer application
     * @return  the value mapped to the IPropertyProvider.ODA_CONSUMER_ID key
     *      in the specified application context map; may be null
     * @since 3.2.2 (DTP 1.7.2)
     */
    public static String getConsumerApplicationId( Object appContext ) 
    {
        if( appContext == null || ! ( appContext instanceof Map ) )
            return null;     // no context map to obtain value
        
        // get the consumer application id from context map, if exists
        Object value = ((Map) appContext).get( IPropertyProvider.ODA_CONSUMER_ID );
        return ( value instanceof String ) ? (String) value : null;
    }
    
    /**
     * Returns the nested connection profile context instance, if exists, 
     * in the specified application context.
     * @param appContext    the application context provided by an ODA consumer application
     * @return  the value mapped to the IPropertyProvider.ODA_CONN_PROP_CONTEXT key
     *      in the specified application context map; may be null
     * @since 3.2.2 (DTP 1.7.2)
     */
    public static Object getConnectionPropertyContext( Object appContext )
    {
        if( appContext == null || ! ( appContext instanceof Map ) )
            return null;     // no context map to obtain value
        return ((Map) appContext).get( IPropertyProvider.ODA_CONN_PROP_CONTEXT );         
    }
    
    private static void log( String context, String msg )
    {
        Logger logger = getLogger();
        if( logger != null )
            logger.fine( context + msg );
    }
    
    private static Logger getLogger()
    {
        return LogManager.getLogger( sm_loggerName );
    }

}
