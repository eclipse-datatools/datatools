/*
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.profile;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.VersionProviderConnection;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaDriver;

public class OdaConnectionWrapper extends VersionProviderConnection
{
    private static final String EMPTY_STR = ""; //$NON-NLS-1$
    private static final Version ODA_UNKNOWN_VERSION = 
        new Version( 3, 0, 0, EMPTY_STR ) 
        {
            public String toString() 
            {
                return ConnectionProfileConstants.UNKNOWN_VERSION;
            }
        };
    
    private String m_odaDataSourceId;

    private IConnection m_odaConnectionHelper;
    private IDriver m_odaDriverHelper;
    private IDataSetMetaData m_odaMetadataHelper;
    private Exception m_connectException;
    
    public OdaConnectionWrapper( IConnectionProfile profile )
    {
        super( profile, OdaConnectionFactory.class );
        
        // ODA profiles use the odaDataSourceId as its profile identifier
        m_odaDataSourceId = profile.getProviderId();

        try
        {
            // calls the oda.IDriver.getConnection( dataSourceId ) 
            // which returns a wrapped oda.IConnection object
            getOdaConnectionHelper( m_odaDataSourceId );
            assert( m_odaConnectionHelper != null );
            open();     // test the connection properties
            m_connectException = null;  // test succeeded
        }
        catch( OdaException ex )
        {
            m_connectException = ex;
            clearVersionCache();
            return;
        }

        try
        {
            // should now be able to get metadata
            m_odaMetadataHelper = 
                m_odaConnectionHelper.getMetaData( Constants.EMPTY_STRING );
        }
        catch( OdaException e )
        {
            // ignore, ok to not have version info available
            e.printStackTrace();
        }
        
        if( ! canProvideVersionMetaData() )
            m_odaMetadataHelper = null; // reset
        
        // update profile with version info, if available
        updateVersionCache();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnection#getRawConnection()
     */
    public Object getRawConnection()
    {
        return m_odaConnectionHelper;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnection#close()
     */
    public void close()
    {
        if( getRawConnection() == null )
            return;     // no underlying connection, nothing to close
        
        try
        {
            m_odaConnectionHelper.close();
            m_odaMetadataHelper = null;
            m_connectException = null;
        }
        catch( OdaException e )
        {
            // ignore
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IConnection#getConnectException()
     */
    public Throwable getConnectException()
    {
        return m_connectException;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.VersionProviderConnection#getTechnologyRootKey()
     */
    protected String getTechnologyRootKey()
    {
        if( m_odaDataSourceId == null || m_odaDataSourceId.length() == 0 )
            return this.getClass().getName();
        return m_odaDataSourceId;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IServerVersionProvider#getProviderVersion()
     */
    public Version getProviderVersion()
    {
        if( m_odaMetadataHelper == null )
            return ODA_UNKNOWN_VERSION;
        
        try
        {
            return Version.valueOf( m_odaMetadataHelper.getDataSourceProductVersion() );
        }
        catch( OdaException e )
        {
        	// ignore
            e.printStackTrace();
        }
        return ODA_UNKNOWN_VERSION;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IServerVersionProvider#getProviderName()
     */
    public String getProviderName()
    {
        if( m_odaMetadataHelper == null )
            return EMPTY_STR;
        
        try
        {
            return m_odaMetadataHelper.getDataSourceProductName();
        }
        catch( OdaException e )
        {
            // ignore, use data source element id as default
        }
        
        return getTechnologyRootKey();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IServerVersionProvider#getTechnologyVersion()
     */
    public Version getTechnologyVersion()
    {
        return Version.valueOf( Constants.ODA_COMPONENT_VERSION );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.IServerVersionProvider#getTechnologyName()
     */
    public String getTechnologyName()
    {
        return Constants.ODA_COMPONENT_NAME;
    }

    // Provides additional connection services for use by the
    // ODA framework.
    
    /**
     * Attempts to establish a live connection based on the 
     * connection properties of the profile instance
     * used to create this connection. 
     * It is up to individual ODA driver to process the 
     * connection properties. 
     * @throws  OdaException if the underlying ODA driver 
     *                       fails to open a live connection
     */
    public void open() throws OdaException
    {
        IConnection odaConn = m_odaConnectionHelper;
        assert( odaConn != null );
        
        try
        {
            odaConn.open( getConnectionProfile().getBaseProperties() );
        }
        catch( Throwable ex )   // catch all possible problem triggered by test connection
        {
            if( ex instanceof OdaException )
                throw (OdaException) ex;
            throw new OdaException( ex );
        }
    }
    
    private IConnection getOdaConnectionHelper( String odaDataSourceElementId )
        throws OdaException
    {
        if( m_odaConnectionHelper != null )
            return m_odaConnectionHelper;
        
        IDriver odaDriver = getOdaDriverHelper( odaDataSourceElementId );
        
        assert( odaDriver != null );    // should have thrown OdaException
        m_odaConnectionHelper = odaDriver.getConnection( odaDataSourceElementId );
        return m_odaConnectionHelper;
    }

    private IDriver getOdaDriverHelper( String odaDataSourceElementId ) 
        throws OdaException
    {
        if( m_odaDriverHelper == null )
            m_odaDriverHelper = new OdaDriver( odaDataSourceElementId );

        return m_odaDriverHelper;
    }
    
    private boolean canProvideVersionMetaData()
    {
        if( m_odaMetadataHelper == null )
            return false;
        
        // validate whether the underlying IDataSetMetaData implementation 
        // is capable of providing version info in current state
        try
        {
            getProviderVersion();
            getProviderName();
        }
        catch( RuntimeException e )
        {
            return false;
        }
        return true;
    }
}
