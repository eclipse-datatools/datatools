/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.profile.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * Common test utility methods used in test package.
 */
public class TestUtil
{
    
    static String getPluginTestDirectory() throws Exception
    {
        Bundle bundle = Platform.getBundle( TestUtil.class.getPackage().getName() );
        URL url = bundle.getEntry( "src" );
        return FileLocator.toFileURL( url ).getPath();
    }

    static File copyTestStoreFileFromTemplate( IPath storeLocation, String storeFilename )
        throws IOException
    {
        // getting profile has the side effect of adding to 
        // the profiles cache, which updates the profiles store.
        // So use a temporary store file copied from a template
        // for use in tests
        File profileStoreTemplate = storeLocation.append( 
                "tmplt_" + storeFilename ).toFile();
        if ( ! profileStoreTemplate.exists() )
            throw new IOException( profileStoreTemplate.getPath() + " template store file does not exist." );

        File testFile = storeLocation.append( storeFilename ).toFile();
        if ( testFile.exists() )
            testFile.delete();
        testFile.createNewFile();
        
        FileChannel sourceChan = new FileInputStream( profileStoreTemplate ).getChannel();
        FileChannel destChan = new FileOutputStream( testFile ).getChannel();
        destChan.transferFrom( sourceChan, 0, sourceChan.size() );
        sourceChan.close();
        destChan.close();
        return testFile;
    }

}
