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

package org.eclipse.datatools.connectivity.oda.design.internal.ui;

import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConfigurationType;
import org.eclipse.datatools.connectivity.IConnectListener;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.internal.UUID;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.ui.IPropertyListener;

/**
 * Implementation of connection profile for 
 * an ODA data source design.
 * This is intended for use as a contributing property node to
 * a property dialog, whose node can be adapted 
 * to create a custom ODA Data Source Editor Page.
 */
public class AdaptableDataSourceProfile extends PlatformObject implements
        IConnectionProfile
{
    /* TODO - complete partial implementation */
    
    private DataSourceDesign m_dataSourceDesign;
    private IConnectionProfile m_linkedProfile;
    private String m_instanceID;
        
    /**
     * 
     * @param design
     * @throws OdaException if a connection profile is referenced,
     *                      but not found
     */
    public AdaptableDataSourceProfile( DataSourceDesign design )
        throws OdaException
    {
        super();
        m_dataSourceDesign = design;
        m_linkedProfile = 
            DesignSessionUtil.getLinkedProfile( design );
    }
    
    protected AdaptableDataSourceProfile()
    {
        super();
        // creates an empty design
        m_dataSourceDesign = DesignFactory.eINSTANCE.createDataSourceDesign();
    }

    /**
     * Returns the associated ODA Data Source Design instance.
     * @return
     */
    public DataSourceDesign getDataSourceDesign()
    {
        return m_dataSourceDesign;
    }
    
    /**
     * Indicates whether this is associated with an external profile in a profile store.
     * @return
     */
    public boolean hasLinkedProfile()
    {
        return ( m_linkedProfile != null );
    }
    
    /**
     * Returns the connection profile instance referenced or linked by
     * the associated data source design.
     * @return  linked connection profile instance, or null
     *          if no external profile is referenced
     */
    public IConnectionProfile getLinkedProfile()
    {
        return m_linkedProfile;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getName()
     */
    public String getName()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getName();
        
        return getDataSourceDesign().getName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getDescription()
     */
    public String getDescription()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getDescription();
        
        return getDataSourceDesign().getDisplayName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#isAutoConnect()
     */
    public boolean isAutoConnect()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().isAutoConnect();
        
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getInstanceID()
     */
    public String getInstanceID()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getInstanceID();
        
        if( m_instanceID == null ) 
        {
            m_instanceID = UUID.createUUID().toString();
        }
        return m_instanceID;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getParentProfile()
     */
    public IConnectionProfile getParentProfile()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getParentProfile();
        
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getBaseProperties()
     */
    public Properties getBaseProperties()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getBaseProperties();
        
        return DesignUtil.convertDataSourceProperties( getDataSourceDesign() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#setBaseProperties(java.util.Properties)
     */
    public void setBaseProperties( Properties props )
    {
        if( hasLinkedProfile() )
        {
            getLinkedProfile().setBaseProperties( props );
            return;
        }

        String dataSourceElmtId = getDataSourceDesign().getOdaExtensionDataSourceId();
        if( dataSourceElmtId == null )
            return;
        
        try
        {
            getDataSourceDesign().setPublicProperties( 
                    DesignSessionUtil.createDataSourcePublicProperties( 
                            dataSourceElmtId, props ) );
            getDataSourceDesign().setPrivateProperties( 
                    DesignSessionUtil.createDataSourceNonPublicProperties( 
                            dataSourceElmtId, props ) );
        }
        catch( OdaException e )
        {
            // TODO log error and ignore
            return;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProperties(java.lang.String)
     */
    public Properties getProperties( String type )
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getProperties( type );
        
        // only this data source type is supported
        return getBaseProperties();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#setProperties(java.lang.String, java.util.Properties)
     */
    public void setProperties( String type, Properties props )
    {
        if( hasLinkedProfile() )
        {
            getLinkedProfile().setProperties( type, props );
            return;
        }

        // only this data source type is supported
        setBaseProperties( props );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#setConnected(boolean)
     */
    public void setConnected( boolean connected )
    {
        if( hasLinkedProfile() )
        {
            if( connected ) 
                getLinkedProfile().connect();
            else
                getLinkedProfile().disconnect();
            return;
        }

        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#isConnected()
     */
    public boolean isConnected()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().isConnected();
        
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#connect()
     */
    public IStatus connect()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().connect();

        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#connect(org.eclipse.core.runtime.jobs.IJobChangeListener)
     */
    public void connect( IJobChangeListener listener )
    {
        if( hasLinkedProfile() )
        {
            getLinkedProfile().connect( listener );
            return;
        }

        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#disconnect()
     */
    public IStatus disconnect()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().disconnect();

        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#disconnect(org.eclipse.core.runtime.jobs.IJobChangeListener)
     */
    public void disconnect( IJobChangeListener listener )
    {
        if( hasLinkedProfile() )
        {
            getLinkedProfile().disconnect( listener );
            return;
        }

        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#addConnectListener(org.eclipse.datatools.connectivity.IConnectListener)
     */
    public void addConnectListener( IConnectListener listener )
    {
        if( hasLinkedProfile() )
        {
            getLinkedProfile().addConnectListener( listener );
            return;
        }

        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#removeConnectListener(org.eclipse.datatools.connectivity.IConnectListener)
     */
    public void removeConnectListener( IConnectListener listener )
    {
        if( hasLinkedProfile() )
        {
            getLinkedProfile().removeConnectListener( listener );
            return;
        }

        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getManagedConnection(java.lang.String)
     */
    public IManagedConnection getManagedConnection( String type )
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getManagedConnection( type );

        // TODO Auto-generated method stub
        return null;
    }

    public void addPropertySetListener(IPropertySetListener listener) {
        if( hasLinkedProfile() )
        {
            getLinkedProfile().addPropertySetListener( listener );
            return;
        }
	}

	public void removePropertySetListener(IPropertySetListener listener) {
        if( hasLinkedProfile() )
        {
            getLinkedProfile().removePropertySetListener( listener );
            return;
        }
	}

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProviderName()
     */
    public String getProviderName()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getProviderName();

        // same as provider id
        return getDataSourceDesign().getOdaExtensionDataSourceId();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProviderId()
     */
    public String getProviderId()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getProviderId();

        return getDataSourceDesign().getOdaExtensionDataSourceId();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getCategory()
     */
    public ICategory getCategory()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getCategory();

        // TODO - get IConfigurationElement for oda extension's
        // connection profile category
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getConfigurationType()
     */
    public IConfigurationType getConfigurationType()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getConfigurationType();

        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProfileExtensions()
     */
    public Map getProfileExtensions()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getProfileExtensions();

        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#createConnection(java.lang.String)
     */
    public IConnection createConnection( String factory )
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().createConnection( factory );

        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#createConnection(java.lang.String, java.lang.String, java.lang.String)
     */
    public IConnection createConnection( String factoryId, String uid,
            String pwd )
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().createConnection( factoryId, uid, pwd );

        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProvider()
     */
    public IConnectionProfileProvider getProvider()
    {
        if( hasLinkedProfile() )
            return getLinkedProfile().getProvider();

        // TODO Auto-generated method stub
        return null;
    }

	public boolean arePropertiesComplete() {
        if( hasLinkedProfile() )
            return getLinkedProfile().arePropertiesComplete();

        // TODO Auto-generated method stub
        return true;
	}

	public boolean arePropertiesComplete(String type) {
        if( hasLinkedProfile() )
            return getLinkedProfile().arePropertiesComplete(type);

        // TODO Auto-generated method stub
        return true;
	}

}
