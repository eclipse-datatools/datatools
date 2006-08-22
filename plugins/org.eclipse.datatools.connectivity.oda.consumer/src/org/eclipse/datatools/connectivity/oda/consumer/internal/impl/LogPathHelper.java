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

package org.eclipse.datatools.connectivity.oda.consumer.internal.impl;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaConsumerPlugin;

/**
 * Internal helper of the oda consumer component to determine 
 * its trace logging path. 
 */
public class LogPathHelper
{
    private static final String LOG_SUBFOLDER_NAME = "logs"; //$NON-NLS-1$

    /**
     * Returns the plugin's default log parent file
     * under its workspace state location.
     * @return	the plugin's log path
     * @throws  IllegalStateException when the plugin activator 
     * 					is not instantiated yet
     */
    public static IPath getPluginLogPath() throws IllegalStateException
    {
        // try to use plugin's default state location's log folder as its parent
        OdaConsumerPlugin thePlugin = OdaConsumerPlugin.getDefault();
        if( thePlugin == null )
            throw new IllegalStateException( "OdaConsumerPlugin.getDefault()" ); //$NON-NLS-1$

        return thePlugin.getStateLocation()
               		.append( LOG_SUBFOLDER_NAME ); 	
    }
    
    /**
     * Returns the log directory of the ODA consumer helper,
     * appended with the sub-directory, if specified.
     * @param subdirName the name of sub-directory to append in the
     * 					 odaconsumer plugin's log path; may be null
     * 					 or empty for no sub-directory
     * @return
     */
    public static File getConsumerLogParent( String subdirName )
    {
    	IPath pluginLogPath = getPluginLogPath();
    	
    	if( subdirName == null || subdirName.length() == 0 )
    		return pluginLogPath.toFile();
    	return pluginLogPath.append( subdirName ).toFile();
    }

}
