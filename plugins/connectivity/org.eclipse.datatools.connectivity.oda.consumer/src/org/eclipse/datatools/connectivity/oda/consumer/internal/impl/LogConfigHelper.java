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

package org.eclipse.datatools.connectivity.oda.consumer.internal.impl;

import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.TraceLogging;

/**
 * Internal helper of the oda consumer component for handling
 * trace log configuration. 
 */
public class LogConfigHelper
{
    private ExtensionManifest m_driverManifest;

    /**
     * Constructor of a helper for the specified ODA driver's manifest.
     * @param driverManifest    an ODA runtime extension's manifest
     */
    public LogConfigHelper( ExtensionManifest driverManifest )
        throws OdaException
    {
        if( driverManifest == null )
            throw new OdaException( new IllegalArgumentException() );
        
        m_driverManifest = driverManifest;
    }
    
    /**
     * Returns the plug-in trace logging configuration from 
     * the .options file if set to debugging; otherwise,  
     * from the driver's traceLogging element defined in its ODA extension.
     * @return  the driver's log configuration, or null if none is found
     */
    public LogConfiguration getDriverLogConfiguration()
    {
        // get log configuration specified in plug-in .options file
        LogConfiguration logOptions = createLogConfigFromTraceOptions();
        if( logOptions != null )
            return logOptions;  // done

        // no trace options are set for ODA driver;
        // get log configuration specified in the 
        // driver's trace logging element in plug-in manifest        
        return createLogConfigFromTraceLogging();
    }
    
    /* 
     * Gets the plug-in trace logging configuration  
     * from the PDE .options file 
     */
    private LogConfiguration createLogConfigFromTraceOptions()
    {
        String pluginId = getDriverPluginId();    
        
        String debugOption = pluginId + "/debug"; //$NON-NLS-1$
        String debugOptionValue = Platform.getDebugOption( debugOption );
        Boolean isDebug = Boolean.valueOf( debugOptionValue );
        if( isDebug == Boolean.FALSE )
            return null;    // not found or not debugging
        
        String logLevelOption = pluginId + "/traceLogging/logLevel"; //$NON-NLS-1$
        int logLevel = TraceLogging.toLogLevelNumber( 
                            Platform.getDebugOption( logLevelOption ) );
        
        String logFormatterOption = pluginId + "/traceLogging/logFormatterClass"; //$NON-NLS-1$
        String logFilePrefixOption = pluginId + "/traceLogging/logFileNamePrefix"; //$NON-NLS-1$
        String logDirOption = pluginId + "/traceLogging/logDirectory"; //$NON-NLS-1$
        
        // set default configuration values if not specified
        String logFilenamePrefix = Platform.getDebugOption( logFilePrefixOption );
        String logDest = Platform.getDebugOption( logDirOption );
        
        // if either log file attribute has value, ensure
        // both attributes have values, using default value as needed
        if( isNotEmpty( logFilenamePrefix ) || isNotEmpty( logDest ) )
        {
            logFilenamePrefix = getDefaultLogFilenamePrefix( logFilenamePrefix );

            // ensure that either user-defined or default log directory
            // has an absolute path
            logDest = LogPathHelper.getAbsoluteLogDirName( 
                        getDefaultLogDirectory( logDest ) );
        }


        // instantiate object with log configuration values
        return new LogConfiguration( pluginId, 
                            logLevel,
                            logDest,
                            logFilenamePrefix,
                            Platform.getDebugOption( logFormatterOption ) );
    }
        
    /*
     * Gets the plug-in trace logging configuration settings 
     * from the plugin.xml traceLogging element
     */
    private LogConfiguration createLogConfigFromTraceLogging()
    {
        ExtensionManifest manifest = m_driverManifest;       
        assert( manifest != null );
        TraceLogging loggingElement = manifest.getTraceLogging();
        if( loggingElement == null )
            return null;    // none found
        
        // set default configuration values if not specified
        String logFilenamePrefix = loggingElement.getLogFileNamePrefix();
        String logDest = loggingElement.getLogDirectory();
        
        // if either log file attribute has value, ensure
        // both attributes have values, using default value as needed
        if( isNotEmpty( logFilenamePrefix ) || isNotEmpty( logDest ) )
        {
            logFilenamePrefix = getDefaultLogFilenamePrefix( logFilenamePrefix );

            // ensure that either user-defined or default log directory
            // has an absolute path
            logDest = LogPathHelper.getAbsoluteLogDirName( 
                        getDefaultLogDirectory( logDest ) );
        }

        // instantiate object with log configuration values
        return new LogConfiguration( getDriverPluginId(), 
                            loggingElement.getLogLevel(),
                            logDest,
                            logFilenamePrefix,
                            loggingElement.getLogFormatterClass() );
    }

    /*
     * Returns the default configuration value if 
     * the given log filename prefix is not specified
     */
    private String getDefaultLogFilenamePrefix( String prefix )
    {
        if( isNotEmpty( prefix ) )
            return prefix;  // already specified, use as is
        return getDriverPluginId();
    }
    
    /*
     * Returns the driver name as the default relative folder name if 
     * the given log directory is not specified
     */
    private String getDefaultLogDirectory( String logDir )
    {
        if( isNotEmpty( logDir ) )
            return logDir;  // already specified, use as is
        return getDriverPluginId();
    }
    
    private String getDriverPluginId()
    {
        assert( m_driverManifest != null );
        return m_driverManifest.getNamespace();
    }
    
    private boolean isNullOrEmpty( String value )
    {
        return ( value == null || value.length() == 0 );
    }    
    
    private boolean isNotEmpty( String value )
    {
        return ! isNullOrEmpty( value );
    }

}
