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

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.ExtensionExplorer;
import org.osgi.framework.BundleContext;

/**
 * Bundle activator class.
 */
public class OdaConsumerPlugin extends Plugin
{
    private static OdaConsumerPlugin sm_plugin;

    public OdaConsumerPlugin()
    {
        super();
        sm_plugin = this;
    }

    /**
     * Returns the shared instance of this plugin activator.
     * @return
     */
    public static OdaConsumerPlugin getDefault()
    {
        return sm_plugin;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext context ) throws Exception
    {
        // release plugin's singleton instance(s)
        ExtensionExplorer.releaseInstance();
        
        super.stop( context );
        sm_plugin = null;
    }

}
