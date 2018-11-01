/*
 *************************************************************************
 * Copyright (c) 2006, 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider;
import org.eclipse.datatools.connectivity.oda.consumer.services.impl.ProviderUtil;

import com.ibm.icu.util.ULocale;

/**
 * Handles ODA connection properties and the
 * ODA consumer's propertyProvider extension point.
 */
class ConnectionPropertyHandler extends OdaObject
{
    private String m_consumerApplId;
    private Object m_connPropContext;
    private IPropertyProvider m_propertyProvider;
    private ULocale m_appLocale;
    
    ConnectionPropertyHandler( Object context )
    {
        processConsumerAppContext( context );
    }
    
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
     * Returns the locale instance in the connection application context map.
     * @return the locale instance set in the context; may be null if none is available
     */
    protected ULocale getAppLocale()
    {
        return m_appLocale;
    }

    /**
     * Processes the consumer application entries specified for property handling 
     * in the application context.
     * @param context   connection application context set by the consumer application
     */
    private void processConsumerAppContext( Object context )
    {
        final String methodName = "ConnectionPropertyHandler.processConsumerAppContext"; //$NON-NLS-1$
        logMethodCalled( methodName );

        // check for the consumer application id
        m_consumerApplId = ProviderUtil.getConsumerApplicationId( context );
        log( methodName, "Consumer Application ID: " + m_consumerApplId ); //$NON-NLS-1$
        
        // check for optional externalized properties context
        m_connPropContext = ProviderUtil.getConnectionPropertyContext( context );  
        log( methodName, "Externalized property context: " + m_connPropContext );        //$NON-NLS-1$

        // check for the optional locale instance in the context
        m_appLocale = getAppLocale( context );
        log( methodName, "Application Locale in context: " + m_appLocale ); //$NON-NLS-1$
        
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
        if( m_propertyProvider == null )
        {
            String applicationId = getConsumerApplicationId();
            m_propertyProvider = ProviderUtil.createPropertyProvider( applicationId );
        }
        return m_propertyProvider;
    }
    
    /**
     * Returns the locale instance specified by an ODA consumer in 
     * the specified application context map.
     * @param context   a Map that contains the IPropertyProvider.APP_RUNTIME_LOCALE_KEY key
     *             with a value of locale object in either Locale, ULocale, or String type
     */
    private ULocale getAppLocale( Object appContext )
    {
        if( appContext == null || ! ( appContext instanceof Map ) )
            return null;     // no context map to obtain value
        
        // get the locale instance from context map, if exists
        String localeKey = IPropertyProvider.APP_RUNTIME_LOCALE_KEY;
        Object localeValue = ((Map) appContext).get( localeKey );
        if( localeValue == null )
            return null;   // does not have locale info
        
        if( localeValue instanceof Locale )
            return ULocale.forLocale( (Locale) localeValue );
        
        if( localeValue instanceof ULocale )
            return (ULocale) localeValue;

        if( localeValue instanceof String )
        {
            String localeString = (String) localeValue;
            return new ULocale( localeString );
        }
        
        // not a supported locale value type
        logWarning( "getAppLocale(Object)",  //$NON-NLS-1$
                "Ignoring invalid object type (" + localeValue.getClass().getName() //$NON-NLS-1$
                + ") specified for the application locale key " + localeKey ); //$NON-NLS-1$
        return null;
    }   

}
