/*
 *************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.test.impl;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * Bundle activator class.
 */
public class OdaConsumerTestPlugin extends Plugin
{
    private static OdaConsumerTestPlugin sm_plugin;
    
    public OdaConsumerTestPlugin()
    {
        super();
        sm_plugin = this;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext context ) throws Exception
    {
        // release plugin's singleton instance(s)
        super.stop( context );
        sm_plugin = null;
    }
   
    /**
     * Returns the shared instance of this plugin activator.
     * @return
     */
    public static OdaConsumerTestPlugin getDefault()
    {
        return sm_plugin;
    }

}
