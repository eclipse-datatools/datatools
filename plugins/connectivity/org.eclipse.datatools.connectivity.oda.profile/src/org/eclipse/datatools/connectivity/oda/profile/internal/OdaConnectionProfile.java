/*
 *************************************************************************
 * Copyright (c) 2007, 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.profile.internal;

import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConfigurationType;
import org.eclipse.datatools.connectivity.IConnectListener;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile.DisconnectJob;

/**
 * An ODA wrapper of a Connectivity connection profile instance.
 * @since DTP 1.6
 */
public class OdaConnectionProfile extends PlatformObject 
    implements IConnectionProfile
{

    private IConnectionProfile m_wrappedProfile;
    private String m_odaWrapperExtensionId;
    private boolean m_hideWrapperId = false;
    private String m_directProviderId;
    
    /**
     * Constructor.
     * @param wrappedProfile    the connection proifle instance wrapped by this instance.
     * @throws NullPointerException if the specified wrappedProfile is null
     */
    public OdaConnectionProfile( IConnectionProfile wrappedProfile )
    {
        if( wrappedProfile == null )
            throw new NullPointerException();
        
        setWrappedProfile( wrappedProfile );
    }

    protected OdaConnectionProfile()
    {
        super();
    }
    
    /**
     * A subclass may choose to extend without providing a wrapped connection profile instance,
     * but it must then override all methods that require a wrapped profile.
     * @param wrappedProfile
     */
    protected void setWrappedProfile( IConnectionProfile wrappedProfile )
    {
        m_wrappedProfile = wrappedProfile;
    }
    
    /**
     * For internal use only.
     */
    public IConnectionProfile getWrappedProfile()
    {
        return m_wrappedProfile;
    }

    /* Gets the leaf level wrapped profile if this has nested level of OdaConnectionProfile instances.
    */
    private IConnectionProfile getLeafWrappedProfile()
    {
        IConnectionProfile wrappedProfile = getWrappedProfile(); 
        while( wrappedProfile instanceof OdaConnectionProfile && 
               ((OdaConnectionProfile)wrappedProfile).hasWrappedProfile() )
        {
            wrappedProfile = ((OdaConnectionProfile)wrappedProfile).getWrappedProfile(); 
        }
        return wrappedProfile;
    }

    /**
     * Indicates whether this contains a wrapped connection profile instance.
     * @return  true if a wrapped profile instance exists; false otherwise.
     */
    public boolean hasWrappedProfile()
    {
        return ( m_wrappedProfile != null );
    }
    
    /**
     * Compares this to the specified connection profile instance.
     * @param aProfile  the connection profile instance to compare this against
     * @return  true if the specified instance is equal to this; false otherwise.
     */
    public boolean equals( IConnectionProfile aProfile )
    {
        if( aProfile == null || ! hasWrappedProfile() )
            return false;
        return getWrappedProfile().getInstanceID().equals( aProfile.getInstanceID() );
    }

    /**
     * Specifies the id of an ODA data source extension that serves as a wrapper.
     * @param odaWrapperExtensionId the id of an ODA data source extension that serves 
     *              either as the direct or indirect provider of the wrapped connection profile;
     *              may be null for no separate ODA wrapper profile provider
     */
    public void setOdaWrapperExtensionId( String odaWrapperExtensionId )
    {
        // oda extension is not a direct provider of the wrapped profile
       if( hasWrappedProfile() &&
           ! getWrappedProfile().getProviderId().equals( odaWrapperExtensionId ) )    
           m_odaWrapperExtensionId = odaWrapperExtensionId;
       else
           m_odaWrapperExtensionId = null;  // no separate ODA wrapper profile provider
    }

    /**
     * Specifies the direct provider id of the wrapped connection profile.
     * @param directProviderId The id of direct provider of the wrapped connection profile,
     *              overriding the profile's own provider id info; may be null
     */
    public void setDirectProviderId( String directProviderId )
    {
        m_directProviderId = directProviderId;
    }

    /**
     * Specifies whether getProviderId() should return the direct provider id,
     * ignoring its wrapper id even when applicable.
     * This is used to provide compatibilty and minimize code migration in existing implementation.
     * @param hide  true to ignore the wrapper id even when applicable
     */
    public void setHideWrapperId( boolean hide )
    {
        m_hideWrapperId = hide;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProviderId()
     */
    public String getProviderId()
    {
        if( m_hideWrapperId )
            return getDirectProviderId();
        return hasOdaWrapperProvider() ? 
                    m_odaWrapperExtensionId : 
                    getDirectProviderId();
    }
    
    public boolean hasOdaWrapperProvider()
    {
        return m_odaWrapperExtensionId != null;
    }
    
    public String getDirectProviderId()
    {
        if( m_directProviderId != null )
            return m_directProviderId;
        return hasWrappedProfile() ? getLeafWrappedProfile().getProviderId() : null;
    }
    
    /**
     * Close and clean up this connection profile wrapper and its wrapped profile instance.
     */
    public void close()
    {
        // in case this is a nested OdaConnectionProfile, get the lowest wrapped profile
        IConnectionProfile wrappedProfile = getLeafWrappedProfile(); 
        
        // try to close and delete the wrapped profile if it is a transient profile type
        boolean isClosed = ProfileManager.getInstance().deleteTransientProfile( wrappedProfile );
        
        if( ! isClosed )    // perhaps using a persisted connection profile
            disconnect( null );   // simply disconnect this, does nothing if already disconnected
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#addConnectListener(org.eclipse.datatools.connectivity.IConnectListener)
     */
    public void addConnectListener( IConnectListener listener )
    {
        m_wrappedProfile.addConnectListener( listener );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#addPropertySetListener(org.eclipse.datatools.connectivity.IPropertySetListener)
     */
    public void addPropertySetListener( IPropertySetListener listener )
    {
        m_wrappedProfile.addPropertySetListener( listener );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#arePropertiesComplete()
     */
    public boolean arePropertiesComplete()
    {
        if( hasWrappedProfile() )
            return getWrappedProfile().arePropertiesComplete();
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#arePropertiesComplete(java.lang.String)
     */
    public boolean arePropertiesComplete( String type )
    {
        if( hasWrappedProfile() )
            return getWrappedProfile().arePropertiesComplete( type );
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#canWorkOffline()
     */
    public boolean canWorkOffline()
    {
        if( hasWrappedProfile() )
            return getWrappedProfile().canWorkOffline();
        return false;
    }
    
    /**
     * For internal use only.
     * Connect through scheduling a synchronized job. 
     * This method would wait till any pending disconnect operation(s) on this profile instance 
     * are finished, before scheduling its connect operation.
     * It handles the case of re-connecting via a profile instance, whose disconnect jobs 
     * have been scheduled but did not wait to finish for optimizing response time.
     */
    public IStatus connectSynchronously()
    {
        // first wait till all pending DisconnectJob that belong to 
        // this (lowest wrapped) profile have finished
        waitForDisconnectingJobs( getLeafWrappedProfile() );

        if( isConnected() )
            return Status.OK_STATUS;

        return this.connect();        
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#connect()
     */
    public IStatus connect()
    {
        return m_wrappedProfile.connect();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#connectWithoutJob()
     */
    public IStatus connectWithoutJob()
    {
        return m_wrappedProfile.connectWithoutJob();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#connect(org.eclipse.core.runtime.jobs.IJobChangeListener)
     */
    public void connect( IJobChangeListener listener )
    {
        m_wrappedProfile.connect( listener );
    }

    private void waitForDisconnectingJobs( IConnectionProfile disconnectingProfile )
    {
        Job[] profileJobs = Job.getJobManager().find( disconnectingProfile );
        if( profileJobs.length == 0 )
            return;   // no jobs found on the specified profile instance

        // iterate thru each pending DisconnectJob, and wait for it to finish
        for( int index = 0; index < profileJobs.length; index++ )
        {
            if( ! (profileJobs[index] instanceof DisconnectJob) )
                continue;     // not a DisconnectJob, skip

            Job disconnectProfileJob = profileJobs[index];
            if( disconnectProfileJob.getState() == Job.NONE ) 
                continue;     // DisconnectJob is not in a pending state, skip

            try
            {
                // wait for the pending DisconnectJob to finish
                disconnectProfileJob.join();
            }
            catch( InterruptedException ex )
            {}
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#disconnect()
     */
    public IStatus disconnect()
    {
        return m_wrappedProfile.disconnect();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#disconnect(org.eclipse.core.runtime.jobs.IJobChangeListener)
     */
    public void disconnect( IJobChangeListener listener )
    {
        m_wrappedProfile.disconnect( listener );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#saveWorkOfflineData()
     */
    public IStatus saveWorkOfflineData()
    {
        return m_wrappedProfile.saveWorkOfflineData();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#saveWorkOfflineData(org.eclipse.core.runtime.jobs.IJobChangeListener)
     */
    public void saveWorkOfflineData( IJobChangeListener listener )
    {
        m_wrappedProfile.saveWorkOfflineData( listener );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#workOffline()
     */
    public IStatus workOffline()
    {
        return m_wrappedProfile.workOffline();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#workOffline(org.eclipse.core.runtime.jobs.IJobChangeListener)
     */
    public void workOffline( IJobChangeListener listener )
    {
        m_wrappedProfile.workOffline( listener );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#createConnection(java.lang.String)
     */
    public IConnection createConnection( String factory )
    {
        return m_wrappedProfile.createConnection( factory );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#createConnection(java.lang.String, java.lang.String, java.lang.String)
     */
    public IConnection createConnection( String factoryId, String uid,
            String pwd )
    {
        return m_wrappedProfile.createConnection( factoryId, uid, pwd );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getBaseProperties()
     */
    public Properties getBaseProperties()
    {
        return m_wrappedProfile.getBaseProperties();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getCategory()
     */
    public ICategory getCategory()
    {
        return m_wrappedProfile.getCategory();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getConfigurationType()
     */
    public IConfigurationType getConfigurationType()
    {
        return m_wrappedProfile.getConfigurationType();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getConnectionState()
     */
    public int getConnectionState()
    {
        if( hasWrappedProfile() )
            return getWrappedProfile().getConnectionState();
        return DISCONNECTED_STATE;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getDescription()
     */
    public String getDescription()
    {
        return m_wrappedProfile.getDescription();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getInstanceID()
     */
    public String getInstanceID()
    {
        return m_wrappedProfile.getInstanceID();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getManagedConnection(java.lang.String)
     */
    public IManagedConnection getManagedConnection( String type )
    {
        return m_wrappedProfile.getManagedConnection( type );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getName()
     */
    public String getName()
    {
        return m_wrappedProfile.getName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getParentProfile()
     */
    public IConnectionProfile getParentProfile()
    {
        return m_wrappedProfile.getParentProfile();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProfileExtensions()
     */
    public Map getProfileExtensions()
    {
        return m_wrappedProfile.getProfileExtensions();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProperties(java.lang.String)
     */
    public Properties getProperties( String type )
    {
        return m_wrappedProfile.getProperties( type );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProvider()
     */
    public IConnectionProfileProvider getProvider()
    {
        return m_wrappedProfile.getProvider();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#getProviderName()
     */
    public String getProviderName()
    {
        return m_wrappedProfile.getProviderName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#isAutoConnect()
     */
    public boolean isAutoConnect()
    {
        if( hasWrappedProfile() )
            return getWrappedProfile().isAutoConnect();
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#isConnected()
     */
    public boolean isConnected()
    {
        if( hasWrappedProfile() )
            return getWrappedProfile().isConnected();
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#removeConnectListener(org.eclipse.datatools.connectivity.IConnectListener)
     */
    public void removeConnectListener( IConnectListener listener )
    {
        m_wrappedProfile.removeConnectListener( listener );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#removePropertySetListener(org.eclipse.datatools.connectivity.IPropertySetListener)
     */
    public void removePropertySetListener( IPropertySetListener listener )
    {
        m_wrappedProfile.removePropertySetListener( listener );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#setBaseProperties(java.util.Properties)
     */
    public void setBaseProperties( Properties props )
    {
        m_wrappedProfile.setBaseProperties( props );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#setConnected(boolean)
     */
    public void setConnected( boolean connected )
    {
        if( hasWrappedProfile() )
            getWrappedProfile().setConnected( connected );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#setProperties(java.lang.String, java.util.Properties)
     */
    public void setProperties( String type, Properties props )
    {
        if( ProfileManager.getInstance().isTransientProfile( m_wrappedProfile ) && 
                m_wrappedProfile instanceof ConnectionProfile )
            ((ConnectionProfile)m_wrappedProfile).internalSetProperties( type, props ); // skip event notification
        else
            m_wrappedProfile.setProperties( type, props );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnectionProfile#supportsWorkOfflineMode()
     */
    public boolean supportsWorkOfflineMode()
    {
        if( hasWrappedProfile() )
            return getWrappedProfile().supportsWorkOfflineMode();
        return false;
    }

}
