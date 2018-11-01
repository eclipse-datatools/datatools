/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.services.impl;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider;

/**
 * Exemplary base class implementation of the Java interface defined
 * by the ODA consumer extension point -
 * <code>org.eclipse.datatools.connectivity.oda.consumer.propertyProvider</code>.
 * <p>
 * Extracts the application-specific configuration id value that an extension
 * may use to look up configurable, externalized data source property values.
 * <br>An ODA consumer application that implements the
 * ODA consumer propertyProvider extension point may extend and override its
 * method(s), such as <code>adjustDataSourceProperties</code>,
 * to adjust the property name-value pairs that are passed through
 * to an ODA run-time driver to open a connection to its data source.
 * <p>
 * The use of this exemplary implementation is optional.
 * A propertyProvider extension may use its own IPropertyProvider implementation.
 */
public class PropertyProviderImpl implements IPropertyProvider
{
    public static final String DEFAULT_ODA_CONFIGURATION_ID_PROP_NAME = 
                                    "OdaPropertyConfigId"; //$NON-NLS-1$

    private String m_propConfigId;
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider#getDataSourceProperties(java.util.Properties, java.lang.Object)
     */
    public Properties getDataSourceProperties( Properties candidateProperties,
                                                Object appContext )
        throws OdaException
    {
        String configIdPropName = getConfigurationIdPropertyName();
        if( candidateProperties == null ||
            ! candidateProperties.containsKey( configIdPropName ) )
        {
            m_propConfigId = null;
            return candidateProperties;
        }
        
        m_propConfigId = candidateProperties.getProperty( configIdPropName );        

        // further adjust properties as appropriate
        return adjustDataSourceProperties(  candidateProperties, appContext  );
    }
    
    /**
     * Returns the property configuration id found in the data source properties
     * collection. 
     * @return  the property configuration id; may be null if none is specified
     */
    public String getConfigurationId()
    {
        return m_propConfigId;
    }

    /**
     * Specifies the property name of the configuration id 
     * included in the data source properties collection.
     * Sub-class may override to specify own application-specific property name.
     * @return  the property name of the configuration id
     */
    protected String getConfigurationIdPropertyName()
    {
        // sub-class may override to specify application-specific property name
        return DEFAULT_ODA_CONFIGURATION_ID_PROP_NAME;
    }
    
    /**
     * Adjusts the specified properties and returns the collection
     * that an ODA run-time driver would use to open a connection.
     * Sub-class may override to provide own logic to adjust specified properties.
     * The default implementation merges the specified properties with any
     * externalized properties configured by the configuration id.
     * @return  the effective data source property name-value pairs
     * @throws OdaException     if provider error occurs
     */
    protected Properties adjustDataSourceProperties( final Properties candidateProperties, 
                                                    Object appContext )
        throws OdaException
    {
        if( getConfigurationId() == null )
            return candidateProperties;   // done, nothing to adjust
        
        Properties configProps = getExternalizedProperties( getConfigurationId(), appContext );
        if( configProps == null || configProps.isEmpty() )
            return candidateProperties;   // done, nothing to adjust
        
        // merges the 2 sets of properties, replacing with those in externalized properties
        Properties mergedProps = new Properties();
        mergedProps.putAll( candidateProperties );
        mergedProps.putAll( configProps );
        return mergedProps;
    }
    
    /**
     * Looks up and returns the properties configured with the specified
     * configuration id and application context.
     * Sub-class to override to use own externalized property configuration.
     * @return  the matching data source property name-value pairs;
     *          may be null if no match is found for specified configuration id
     * @throws OdaException     if provider error occurs
     */
    protected Properties getExternalizedProperties( String configId, Object appContext )
        throws OdaException
    {
        // sub-class to override to look up externalized properties
        return null;
    }
    
}
