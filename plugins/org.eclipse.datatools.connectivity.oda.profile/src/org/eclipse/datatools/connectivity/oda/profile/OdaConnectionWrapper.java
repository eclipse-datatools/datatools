/*
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.profile;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.VersionProviderConnection;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaDriver;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;

public class OdaConnectionWrapper extends VersionProviderConnection
{
    private String m_odaDataSourceId;

    private IConnection m_odaConnectionHelper;
    private IDriver m_odaDriverHelper;
    private IDataSetMetaData m_odaMetadataHelper;
    private Exception m_connectException;
    
    public OdaConnectionWrapper( IConnectionProfile profile )
    {
        super( profile, OdaConnectionFactory.class );
        
        Properties props = profile.getBaseProperties();
        m_odaDataSourceId = props.getProperty( Constants.ODA_DATA_SOURCE_ID_PROP );
        String dataSetId = props.getProperty( Constants.ODA_DATA_SET_ID_PROP );

        try
        {
            // calls the oda.IDriver.getConnection( dataSourceId ) 
            // which returns a wrapped oda.IConnection object
            m_odaConnectionHelper = getOdaConnectionHelper( m_odaDataSourceId );
            m_connectException = null;

            // should be able to get metadata, without first open a connection
            m_odaMetadataHelper = m_odaConnectionHelper.getMetaData( dataSetId );
            updateVersionCache();
        }
        catch( OdaException e )
        {
            m_connectException = e;
            clearVersionCache();
        }
    }

    public Object getRawConnection()
    {
        return m_odaConnectionHelper;
    }

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

    public Throwable getConnectException()
    {
        return m_connectException;
    }

    protected String getTechnologyRootKey()
    {
        if( m_odaDataSourceId == null || m_odaDataSourceId.length() == 0 )
            return this.getClass().getName();
        return m_odaDataSourceId;
    }

    public Version getProviderVersion()
    {
        if( m_odaMetadataHelper == null )
            return null;
        
        try
        {
            return Version.valueOf( m_odaMetadataHelper.getDataSourceProductVersion() );
        }
        catch( OdaException e )
        {
            // ignore
        }
        return Version.valueOf( "" );
    }

    public String getProviderName()
    {
        if( m_odaMetadataHelper == null )
            return null;
        
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

    public Version getTechnologyVersion()
    {
        return Version.valueOf( Constants.ODA_COMPONENT_VERSION );
    }

    public String getTechnologyName()
    {
        return Constants.ODA_COMPONENT_NAME;
    }

    private IConnection getOdaConnectionHelper( String odaDataSourceElementId )
        throws OdaException
    {
        if( m_odaDriverHelper == null )
        {
            ExtensionManifest driverManifest = 
                ManifestExplorer.getInstance().getExtensionManifest( odaDataSourceElementId );
    
            if( driverManifest != null )
                m_odaDriverHelper = new OdaDriver( driverManifest );
        }
        
        if( m_odaDriverHelper == null )
        {           
            // error handling; TODO - externalize error message
            throw new OdaException( "Unable to locate the ODA driver: " + odaDataSourceElementId );
        }

        return m_odaDriverHelper.getConnection( odaDataSourceElementId );
    }
    
}
