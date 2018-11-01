/*
 *************************************************************************
 * Copyright (c) 2006, 2010 Actuate Corporation.
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

import java.io.File;
import java.net.URI;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaConsumerPlugin;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;

/**
 * Internal helper of the oda consumer component to determine 
 * its trace logging path. 
 */
public class LogPathHelper
{
    private static final String LOG_SUBFOLDER_NAME = "logs"; //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.datatools.connectivity.oda.consumer"; //$NON-NLS-1$
    
    private static IPath sm_consumerLogPath = null;
    
    /**
     * Returns the plugin's default log parent file
     * under its workspace state location.
     * @param resourceIdentifiers resource identifiers for resolving the log path, if exists and applicable
     * @return	the plugin's log path
     * @throws  IllegalStateException when the plugin activator 
     * 					is not instantiated yet
     */
    static IPath getPluginLogPath( ResourceIdentifiers resourceIdentifiers ) throws IllegalStateException
    {
        if( sm_consumerLogPath == null )
        {
            // try to use plugin's default state location's log folder as its parent
            OdaConsumerPlugin thePlugin = OdaConsumerPlugin.getDefault();
            IPath consumerLogPath = null;
            if( thePlugin != null )
            {
                consumerLogPath = thePlugin.getStateLocation().append( LOG_SUBFOLDER_NAME );
            }
            else    // non-OSGi platform
            {
                URI logUri = null;
                if( resourceIdentifiers != null )
                    logUri = resourceIdentifiers.resolveResourceLocation( LOG_SUBFOLDER_NAME );
                if( logUri == null )
                    throw new IllegalStateException( 
                            Messages.bind( Messages.helper_unknownPluginLogPath,
                                            resourceIdentifiers ) );
                
                // set path to <hostResource>/logs/org.eclipse.datatools.connectivity.oda.consumer/
                consumerLogPath = new Path( logUri.getPath() ).append( BUNDLE_NAME );
            }
    
            synchronized( LogPathHelper.class )
            {
                if( sm_consumerLogPath == null )
                {
                    sm_consumerLogPath = consumerLogPath;
                }
            }
        }

        return sm_consumerLogPath;
    }
    
    /**
     * Returns the log directory of the ODA consumer helper,
     * appended with the sub-directory, if specified.
     * @param subdirName the name of sub-directory to append in the
     * 					 odaconsumer plugin's log path; may be null
     * 					 or empty for no sub-directory
     * @param resourceIdentifiers
     * @return
     */
    static File getConsumerLogParent( String subdirName, ResourceIdentifiers resourceIdentifiers )
    {
    	IPath pluginLogPath = getPluginLogPath( resourceIdentifiers );
    	
    	if( subdirName == null || subdirName.length() == 0 )
    		return pluginLogPath.toFile();
    	return pluginLogPath.append( subdirName ).toFile();
    }
    
    /**
     * Returns the specified logDirectory as an absolute path name.
     * If specified logDirectory is already absolute, use as is.
     * Otherwise, use the odaconsumer plugin's default logs folder
     * as the parent directory.
     * @param logDirectory  non-empty log directory which may be a 
     *                      relative or absolute path
     * @return  an absolute directory path for the log files; 
     *          or null if specified argument is null or empty
     */
    public static String getAbsoluteLogDirName( String logDirectory )
    {
        return getAbsoluteLogDirName( logDirectory, null );
    }

    /**
     * Returns the specified logDirectory as an absolute path name.
     * If specified logDirectory is already absolute, use as is.
     * Otherwise, use the odaconsumer plugin's default logs folder
     * as the parent directory.  Uses the specified ResourceIdentifiers
     * to resolve the default logs path when on non-OSGi platform.
     * @param logDirectory  non-empty log directory which may be a 
     *                      relative or absolute path
     * @param resourceIdentifiers   a ResourceIdentifiers instance, normally
     *          set by an ODA consumer application in its application context
     * @return  an absolute directory path for the log files; 
     *          or null if specified argument is null or empty
     * @since 3.2.4
     */
    public static String getAbsoluteLogDirName( String logDirectory, 
            ResourceIdentifiers resourceIdentifiers )
    {
        if( logDirectory == null || logDirectory.length() == 0 )
            return null;
        File logParent = new File( logDirectory );
        if( logParent.isAbsolute() )
            return logDirectory;   // use as is
        
        // the specified logDirectory is relative, 
        // set its parent to the odaconsumer plugin's default logs folder 
        return getConsumerLogParent( logDirectory, resourceIdentifiers ).getPath();
    }
    
}
