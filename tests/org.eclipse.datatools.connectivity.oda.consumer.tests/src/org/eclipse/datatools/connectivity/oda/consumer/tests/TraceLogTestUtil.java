/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Platform;

public class TraceLogTestUtil
{
    static final String CONSUMER_LOG_PREFIX = "OdaHelperLog"; //$NON-NLS-1$

    static String getOptionsLogDir( String pluginId )
    {
        String logDirOption = pluginId + "/traceLogging/logDirectory";
        return Platform.getDebugOption( logDirOption );        
    }

    static void cleanupTestLogFiles( File logDir ) throws IOException
    {        
        if( logDir == null || ! logDir.exists() )
            return;     // nothing to clear
        File[] filesInDir = logDir.listFiles();
        if( filesInDir == null )
            return;     // nothing to clear
        
        for ( int i = 0; i < filesInDir.length; i += 1 )
        {         
            boolean deleted = filesInDir[i].delete();
            if ( ! deleted )
                throw new IOException( "Cannot delete file: " + filesInDir[i].getName() );
        }
    }
    
    static boolean hasTestLogFiles( File logDir, String logFilePrefix )
    {
        if( logDir == null )
            return false;
        String[] filesInDir = logDir.list();
        if( filesInDir == null )
            return false;

        for( int i = 0; i < filesInDir.length; i++ )
        {
            if( filesInDir[i].startsWith( logFilePrefix ) )
                return true;
        }       
        return false;
    }

}
