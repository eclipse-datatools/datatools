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

package org.eclipse.datatools.connectivity.oda.design.tests;

import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class TestUtil
{

    static String getPluginTestFilePath()
        throws IOException
    {       
        Bundle bundle = Platform.getBundle( TestUtil.class.getPackage().getName() );
        String testFilePath = FileLocator.toFileURL( bundle.getEntry( "/testFiles" ) ).getFile();
        return testFilePath;
    }
    
}
