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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Encapsulates access to the manifest content of 
 * an ODA consumer helper driverBridge extension.
 */
public class DriverExtensionManifest
{
    static final String BRIDGE_ELEMENT = "bridge";  //$NON-NLS-1$
    static final String BRIDGE_ATTRIBUTE = "bridgeId";  //$NON-NLS-1$
    static final String DRIVER_TYPE_ATTRIBUTE = "driverType";  //$NON-NLS-1$

    private IConfigurationElement m_bridgeElement;
    private String m_namespace;
    private String m_bridgeDataSourceId;
    private String m_driverType;
    private IExtension m_bridgeExtension;

    DriverExtensionManifest( IExtension bridgeExtension ) 
        throws OdaException
    {
        init( bridgeExtension );
    }
    
    protected DriverExtensionManifest()
    {}
    
    protected void init( IExtension bridgeExtension ) 
        throws OdaException
    {
        assert( bridgeExtension != null );
        m_bridgeExtension = bridgeExtension;
        m_namespace = m_bridgeExtension.getContributor().getName();

        m_bridgeElement =  ExtensionExplorer.getNamedElement( m_bridgeExtension,
                                                  BRIDGE_ELEMENT,
                                                  DRIVER_TYPE_ATTRIBUTE );
        if( m_bridgeElement == null )
            throw new OdaException( new IllegalArgumentException( m_namespace ) );           
        
        m_driverType = m_bridgeElement.getAttribute( DRIVER_TYPE_ATTRIBUTE );        
        if( m_driverType == null || m_driverType.trim().length() == 0 )
            throw new OdaException( 
                    new IllegalArgumentException( DRIVER_TYPE_ATTRIBUTE ) );

        m_bridgeDataSourceId = m_bridgeElement.getAttribute( BRIDGE_ATTRIBUTE );
        if( m_bridgeDataSourceId == null || m_bridgeDataSourceId.trim().length() == 0 )
            throw new OdaException( 
                    new IllegalArgumentException( BRIDGE_ATTRIBUTE ) );
    }

    /**
     * Returns the bridge extension used to initialize this instance.
     */
    protected IExtension getBridgeExtension()
    {
        return m_bridgeExtension;
    }

    /**
     * Returns the bridge element found in this bridge extension.
     */
    protected IConfigurationElement getBridgeElement()
    {
        return m_bridgeElement;
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
     * Returns the driver type for which a driver bridge extension is implemented.
     * @return 
     */
    public String getDriverType()
    {
        return m_driverType;
    }

    /**
     * Returns the oda data source id of the driver bridge plugin extension
     * that implements the ODA datasource extension point. 
     * @return 
     */
    public String getBridgeDataSourceId()
    {
        return m_bridgeDataSourceId;
    }
    
}
