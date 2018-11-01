/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.profile;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * ODA Profile bundle activator class.
 * @since DTP 1.6
 */
public class OdaProfilePlugin extends Plugin
{
    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.datatools.connectivity.oda.profile"; //$NON-NLS-1$

    private static OdaProfilePlugin sm_plugin;
    
    public OdaProfilePlugin()
    {        
        super();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext context ) throws Exception 
    {
        super.start( context );
        sm_plugin = this;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext context ) throws Exception
    {
        // release plugin's singleton instance
        OdaProfileExplorer.releaseInstance();
        
        sm_plugin = null;
        super.stop( context );
    }

    /**
     * Returns the shared instance of this plugin.
     * @return  the shared plugin instance
     */
    public static OdaProfilePlugin getDefault()
    {
        return sm_plugin;
    }

}
