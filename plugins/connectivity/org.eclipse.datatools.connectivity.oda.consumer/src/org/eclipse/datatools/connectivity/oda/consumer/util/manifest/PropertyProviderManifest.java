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

package org.eclipse.datatools.connectivity.oda.consumer.util.manifest;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;
import org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider;

/**
 * Encapsulates access to the manifest content of 
 * an ODA consumer extension of the helper's propertyProvider extension point.
 */
public class PropertyProviderManifest
{
    static final String DTP_ODA_PROPERTY_PROVIDER_EXT_POINT = 
        "org.eclipse.datatools.connectivity.oda.consumer.propertyProvider";  //$NON-NLS-1$
    static final String PROP_SERVICE_ELEMENT = "dataSourcePropertyService";  //$NON-NLS-1$
    static final String APPLICATION_ID_ATTRIBUTE = "consumerApplicationId";  //$NON-NLS-1$
    static final String PROVIDER_CLASS_ATTRIBUTE = "providerClass";  //$NON-NLS-1$

    private IExtension m_providerExtension;
    private IConfigurationElement m_propServiceElement;
    private String m_namespace;
    private String m_applicationId;
    private String m_providerClassName;

    PropertyProviderManifest( IExtension providerExtension ) 
        throws OdaException
    {
        init( providerExtension );
    }
    
    protected PropertyProviderManifest()
    {}
    
    protected void init( IExtension providerExtension ) 
        throws OdaException
    {
        assert( providerExtension != null );
        m_providerExtension = providerExtension;
        m_namespace = m_providerExtension.getContributor().getName();

        m_propServiceElement =  ExtensionExplorer.getNamedElement( m_providerExtension,                                       
                                                        PROP_SERVICE_ELEMENT,
                                                        APPLICATION_ID_ATTRIBUTE );
        if( m_propServiceElement == null )
            throw new OdaException( new IllegalArgumentException( m_namespace ) );
            
        
        m_applicationId = m_propServiceElement.getAttribute( APPLICATION_ID_ATTRIBUTE );        
        if( m_applicationId == null || m_applicationId.trim().length() == 0 )
            throw new OdaException( 
                    new IllegalArgumentException( APPLICATION_ID_ATTRIBUTE ) );

        m_providerClassName = m_propServiceElement.getAttribute( PROVIDER_CLASS_ATTRIBUTE );
        if( m_providerClassName == null || m_providerClassName.trim().length() == 0 )
            throw new OdaException( 
                    new IllegalArgumentException( PROVIDER_CLASS_ATTRIBUTE ) );
    }

    /**
     * Returns the provider extension used to initialize this instance.
     */
    protected IExtension getProviderExtension()
    {
        return m_providerExtension;
    }

    /**
     * Returns the namespace of the plugin that contributes this extension.
     * @return 
     */
    public String getNamespace()
    {
        return m_namespace;
    }

    /**
     * Returns the PROP_SERVICE_ELEMENT element found in this provider extension.
     */
    protected IConfigurationElement getPropertyServiceElement()
    {
        return m_propServiceElement;
    }

    /**
     * Returns the consumer application id for which this extension is implemented.
     * @return 
     */
    public String getApplicationId()
    {
        return m_applicationId;
    }

    /**
     * Returns the application-specific service provider class name that 
     * implements the IPropertyProvider interface for the extension.
     * @return 
     */
    public String getProviderClassName()
    {
        return m_providerClassName;
    }    

    /**
     * Instantiates the property provider implemented by an ODA consumer extension
     * of the propertyProvider extension point.
     * @return	an instance of the IPropertyProvider interface
     * @throws OdaException
     */
    public IPropertyProvider createProvider() throws OdaException
    {
        Object provider = null;
        try
        {
            provider = getPropertyServiceElement().createExecutableExtension( PROVIDER_CLASS_ATTRIBUTE );
        }
        catch( CoreException ex )
        {
            throw new OdaException( ex );
        }

        if( ! ( provider instanceof IPropertyProvider ) )
            throw new OdaException( 
                    Messages.bind( Messages.helper_extension_mustImplementInterface,
                                    getProviderClassName(), 
                                    IPropertyProvider.class.getName() ));
        
        return (IPropertyProvider) provider;
    }
    
}
