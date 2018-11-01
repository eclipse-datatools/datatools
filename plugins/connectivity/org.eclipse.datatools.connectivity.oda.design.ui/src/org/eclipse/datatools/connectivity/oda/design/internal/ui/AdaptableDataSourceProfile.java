/*
 *************************************************************************
 * Copyright (c) 2006, 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui;

import java.util.Properties;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.internal.UUID;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile;
import org.eclipse.datatools.connectivity.oda.profile.internal.ProfileCategoryUtil;

/**
 * Implementation of connection profile for an ODA data source design.
 * This is intended for use as a contributing property node to
 * a property dialog, whose node can be adapted 
 * to create a custom ODA Data Source Editor Page.
 * It might not be associated with a connection profile instance, and would then based its
 * behavior on its associated data source design.
 */
public class AdaptableDataSourceProfile extends OdaConnectionProfile implements
        IConnectionProfile
{
    
    private DataSourceDesign m_dataSourceDesign;
    private String m_instanceID;

    private static final String sm_className = AdaptableDataSourceProfile.class.getName();

    /**
     * Internal constructor.
     * @param design
     */
    public AdaptableDataSourceProfile( DataSourceDesign design )
    {
        super();
        m_dataSourceDesign = design;
        
        IConnectionProfile linkedProfile = null;
        try
        {
            linkedProfile = DesignSessionUtil.getLinkedProfile( design );
        }
        catch( OdaException ex )
        {
            // log, and proceed without having a wrapped profile
            Logger.getLogger( sm_className ).warning( 
                    Messages.bind( Messages.adaptableDataSourceProfile_unableAccessProfile,
                            design.getLinkedProfileStoreFilePath(), ex.toString() ));
        }
        if( linkedProfile != null )
            setWrappedProfile( linkedProfile ); // cache the profile referenced in the design
    }
    
    protected AdaptableDataSourceProfile( IConnectionProfile wrappedProfile )
    {
        super( wrappedProfile );
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
        return hasWrappedProfile();
    }
    
    /**
     * Returns the connection profile instance referenced or linked by
     * the associated data source design.
     * @return  linked connection profile instance, or null
     *          if no external profile is referenced
     */
    public IConnectionProfile getLinkedProfile()
    {
        return getWrappedProfile();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getName()
     */
    public String getName()
    {
        if( hasLinkedProfile() )
            return super.getName();
        
        return getDataSourceDesign().getName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getDescription()
     */
    public String getDescription()
    {
        if( hasLinkedProfile() )
            return super.getDescription();
        
        return getDataSourceDesign().getDisplayName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#isAutoConnect()
     */
    public boolean isAutoConnect()
    {
        if( hasLinkedProfile() )
            return super.isAutoConnect();
        
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getInstanceID()
     */
    public String getInstanceID()
    {
        if( hasLinkedProfile() )
            return super.getInstanceID();
        
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
            return super.getParentProfile();
        
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getBaseProperties()
     */
    public Properties getBaseProperties()
    {
        // first get all the properties defined in the design definition,
        // including inherited and custom ones
        Properties designProps = 
            DesignUtil.convertDataSourceProperties( getDataSourceDesign() );

        if( hasLinkedProfile() )    // maintaining external reference
        {
            // override with linked profile's current properties and values
            Properties linkedProfileProps = super.getBaseProperties();
            if( linkedProfileProps != null )
                designProps.putAll( linkedProfileProps );
        }
        return designProps;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#setBaseProperties(java.util.Properties)
     */
    public void setBaseProperties( Properties props )
    {
        if( hasLinkedProfile() )
        {
            super.setBaseProperties( props );
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
            return super.getProperties( type );
        
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
            super.setProperties( type, props );
            return;
        }

        // only this data source type is supported
        setBaseProperties( props );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#connect()
     */
    public IStatus connect()
    {
        if( hasLinkedProfile() )
            return super.connect();

        return createNAConnectionStatus();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#connectWithoutJob()
     */
    public IStatus connectWithoutJob()
    {
        if( hasLinkedProfile() )
            return super.connectWithoutJob();
        
        return createNAConnectionStatus();
    }

    private IStatus createNAConnectionStatus()
    {
        return new Status( IStatus.WARNING, getDataSourceDesign().getOdaExtensionId(),
                        -1, "", null ); //$NON-NLS-1$
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#disconnect()
     */
    public IStatus disconnect()
    {
        if( hasLinkedProfile() )
            return super.disconnect();

        // nothing to disconnect; return appropriate status
        return ( ! isConnected() ) ?
                    Status.OK_STATUS :
                    createNAConnectionStatus();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile#disconnect(org.eclipse.core.runtime.jobs.IJobChangeListener)
     */
    @Override
    public void disconnect( IJobChangeListener listener )
    {
        if( hasLinkedProfile() )
            super.disconnect( listener );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getManagedConnection(java.lang.String)
     */
    public IManagedConnection getManagedConnection( String type )
    {
        if( hasLinkedProfile() )
            return super.getManagedConnection( type );

        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProviderName()
     */
    public String getProviderName()
    {
        if( hasLinkedProfile() )
            return super.getProviderName();

        // same as provider id
        return getDataSourceDesign().getOdaExtensionDataSourceId();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProviderId()
     */
    public String getProviderId()
    {
        if( hasLinkedProfile() )
            return super.getProviderId();

        return getDataSourceDesign().getOdaExtensionDataSourceId();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getCategory()
     */
    public ICategory getCategory()
    {
        if( hasLinkedProfile() )
            return super.getCategory();

        // get oda extension's connection profile category
        return ProfileCategoryUtil.getCategory( getProviderId() );
    }

}
