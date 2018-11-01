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

package org.eclipse.datatools.connectivity.oda.internal;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.osgi.framework.BundleContext;

/**
 * [<b>Non-API</b>] Bundle activator class.
 */
public class OdaPlugin extends Plugin
{
    private static OdaPlugin sm_plugin;

    public OdaPlugin()
    {
        super();
        sm_plugin = this;
    }

    /**
     * Returns the shared instance of this plugin activator.
     * @return
     */
    public static OdaPlugin getDefault()
    {
        return sm_plugin;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext context ) throws Exception
    {
        // release plugin's singleton instance(s)
        ResultExtensionExplorer.releaseInstance();
        ManifestExplorer.releaseInstance();
        
        super.stop( context );
        sm_plugin = null;
    }

}
