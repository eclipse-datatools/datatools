/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.ExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.PropertyProviderManifest;

/**
 * Handles ODA connection properties and the
 * ODA consumer's propertyProvider extension point.
 */
public class ConnectionPropertyHandler extends OdaObject
{
    private String m_consumerApplId;
    private Object m_connPropContext;
    
    /**
     * Returns the ODA consumer application id, mapped to the
     * IPropertyProvider.ODA_CONSUMER_ID key 
     * in a connection application context map.
     * May be null if none is specified.
     */
    protected String getConsumerApplicationId() 
    {
        return m_consumerApplId;
    }

    /**
     * Returns the connection property context object, mapped to the
     * IPropertyProvider.ODA_CONN_PROP_CONTEXT key 
     * in a connection application context map.
     * May be null if none is specified.
     */
    protected Object getConnectionPropertyContext() 
    {
        return m_connPropContext;
    }
    
    /**
     * Processes the consumer application entries specified for property handling 
     * in the application context.
     * @param context   connection application context set by the consumer application
     */
    void processConsumerAppContext( Object context )
    {
        final String methodName = "ConnectionPropertyHandler.processConsumerAppContext( " + //$NON-NLS-1$
                                    context + " )\t"; //$NON-NLS-1$
        logMethodCalled( methodName );

        if( context == null || ! ( context instanceof Map ) )
        {
            logMethodExit( methodName );
            return;     // nothing to process
        }
        
        // check for the consumer application id
        Map contextMap = (Map) context;
        Object value = contextMap.get( IPropertyProvider.ODA_CONSUMER_ID );
        if( value == null || ! ( value instanceof String ) )
        {
            logMethodExit( methodName );
            return;     // no valid consumer application id specified in context
        }
        
        m_consumerApplId = (String) value;
        log( methodName, "Consumer Application ID: " + m_consumerApplId ); //$NON-NLS-1$
        
        // check for optional externalized properties context
        m_connPropContext = contextMap.get( IPropertyProvider.ODA_CONN_PROP_CONTEXT );  
        log( methodName, "Externalized property context: " + m_connPropContext );        //$NON-NLS-1$

        logMethodExit( methodName );
    }
    
    /**
     * Uses the extension implementation of IPropertyProvider, if available, 
     * to obtain and return the effective property names and values 
     * for opening a connection.
     */
    protected Properties getEffectiveProperties( Properties candidateProperties )
        throws OdaException
    {
        final String methodName = "ConnectionPropertyHandler.getEffectiveProperties( " + //$NON-NLS-1$
                                    candidateProperties + " )\t"; //$NON-NLS-1$
        logMethodCalled( methodName );

        IPropertyProvider propProvider = getExtensionPropertyProvider();

        // no configuration service provider, use original properties
        if( propProvider == null )
        {
            logMethodExit( methodName );
            return candidateProperties; 
        }
        
        // calls the implementation of the interface method, passing in 
        // the connection properties and context specified by the consumer
        Properties effectiveProps =
            propProvider.getDataSourceProperties( candidateProperties, 
                                getConnectionPropertyContext() );
        
        log( methodName, "Effective properties: " + effectiveProps ); //$NON-NLS-1$
        logMethodExit( methodName );
        
        return effectiveProps;
    }

    /**
     * Finds and returns the instance of extension implementation of 
     * IPropertyProvider.
     * May return null if no provider is defined.
     */
    private IPropertyProvider getExtensionPropertyProvider()
        throws OdaException
    {
        final String methodName = "ConnectionPropertyHandler.getExtensionPropertyProvider()\t"; //$NON-NLS-1$

        String applicationId = getConsumerApplicationId();
        if( applicationId == null || applicationId.length() == 0 )
        {
            log( methodName, "No ODA consumer application id specified in application context." ); //$NON-NLS-1$
            return null;    // no consumer application id specified in appContext
        }
        
        PropertyProviderManifest providerManifest = 
            ExtensionExplorer.getInstance().getPropertyProviderManifest( applicationId );
        if( providerManifest == null )
        {
            log( methodName, "No IPropertyProvider found for " + applicationId ); //$NON-NLS-1$
            return null;    // no extension defined by application
        }
          
        IPropertyProvider extnProvider = providerManifest.createProvider();
        log( methodName, "Found IPropertyProvider " + extnProvider  //$NON-NLS-1$
                            + " for " + applicationId ); //$NON-NLS-1$
        return extnProvider;
    }

}
