/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
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

import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.DriverExtensionManifest;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.ExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.JavaRuntimeInterface;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.RuntimeInterface;
import org.osgi.framework.Bundle;

/**
 *  Utility class to help with handling extensions of an ODA data source.
 *  @since 3.2.4
 */
public class DriverExtensionHelper
{
    /**
     * Returns the id of the effective ODA data source extension that will be accessed by the 
     * ODA consumer framework at runtime execution.  
     * <br>It takes into consideration any loaded extensions of
     * of the <i>org.eclipse.datatools.connectivity.oda.consumer.driverBridge</i> extension point
     * that overrides the specified odaDataSourceId.
     * @param odaDataSourceId   an ODA data source extension id
     * @return  the effective ODA data source extension id that would override the specified
     *      odaDataSourceId at runtime.  If no overriding driverBridge extension is found,
     *      the specified odaDataSourceId is returned.
     * @throws OdaException 
     */
    public static String getEffectiveDataSourceId( String odaDataSourceId )
        throws OdaException
    {
        if( odaDataSourceId == null )
            return null;
        
        ExtensionManifest odaDataSourceManifest;
        try
        {
            odaDataSourceManifest = ManifestExplorer.getInstance().getExtensionManifest( odaDataSourceId );
        }
        catch( IllegalArgumentException ex )
        {
            throw new OdaException( ex );    // invalid odaDataSourceId 
        }

        Class driverClass = loadDriverClass( odaDataSourceManifest );

        String bridgeDataSourceId = getDriverBridgeId( driverClass );
        
        // no separate driver bridge is found
        if( bridgeDataSourceId == null ||
            bridgeDataSourceId.equalsIgnoreCase( odaDataSourceId ) )
            return odaDataSourceId;
        
        // try to find the bridge's corresponding bridge id, if available
        return getEffectiveDataSourceId( bridgeDataSourceId );
    }

    /**
     * Looks up and returns a driver bridge extension's
     * data source element id for the given type of ODA driver.
     * A bridge extension defined for a driver class takes precedence
     * over those defined for a driver's interface(s).
     * Returns null if no corresponding driverBridge extension is found.
     */
    static String getDriverBridgeId( Class odaDriverClass )
    {
        if( odaDriverClass == null )
            throw new IllegalArgumentException();
        
        // first try look up bridge extension for driver class
        String bridgeDataSourceId = getDriverBridgeId( odaDriverClass.getName() );        
        if( bridgeDataSourceId != null )
            return bridgeDataSourceId;    // found
        
        // next try look up bridge extension for driver's interface(s)
        Class[] driverTypes = odaDriverClass.getInterfaces();
        for( int i = 0; i < driverTypes.length; i++ )
        {
            bridgeDataSourceId = getDriverBridgeId( driverTypes[i].getName() );
            if( bridgeDataSourceId != null )
                return bridgeDataSourceId;    // found
        }
        
        return null;    // no bridge extension found for specified odaDriverClass
    }
    
    private static String getDriverBridgeId( String driverType ) 
    {
        // look for bridge extension of the specified driver type
        DriverExtensionManifest manifest = null;
        try
        {
            manifest = ExtensionExplorer.getInstance()
                            .getDriverExtensionManifest( driverType );
        }
        catch( OdaException e )
        {
            // ignore
        }
       
        return manifest != null ? manifest.getBridgeDataSourceId() : null;
    }
    
    static Class loadDriverClass( ExtensionManifest driverConfig ) 
        throws OdaException 
    {
        String driverClassName = getRuntimeInterface( driverConfig ).getDriverClass();
                
        Bundle bundle = Platform.getBundle( driverConfig.getNamespace() );
        try
        {
            return ( bundle != null ) ? 
                        bundle.loadClass( driverClassName ) :
                        Class.forName( driverClassName );
        }
        catch( ClassNotFoundException ex )
        {
            throw new OdaException( ex );
        }
    }
    
    static JavaRuntimeInterface getRuntimeInterface( ExtensionManifest driverConfig )
    {
        if( driverConfig == null )
            throw new IllegalArgumentException();

        RuntimeInterface runtime = driverConfig.getRuntimeInterface();
        assert( runtime instanceof JavaRuntimeInterface );
        return (JavaRuntimeInterface) runtime;       
    }

}
