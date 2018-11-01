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

package org.eclipse.datatools.connectivity.oda.flatfile.ui;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class FlatFileUIPlugin extends Plugin
{
    public void start( BundleContext context ) throws Exception
    {
        super.start( context );
        if( isDebugging() )
        {
            // TODO - set up logger based on debug option
        }
    }
}
