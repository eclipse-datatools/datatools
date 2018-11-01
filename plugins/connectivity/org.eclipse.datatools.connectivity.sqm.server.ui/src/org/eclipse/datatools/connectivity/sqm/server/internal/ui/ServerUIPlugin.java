/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui;

import java.net.URL;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObjectListener;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.content.ServerExplorerRefreshListener;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The main plugin class to be used in the desktop.
 */
public class ServerUIPlugin extends AbstractUIPlugin
{
    //The shared instance.
    private static ServerUIPlugin plugin;
    
    private ICatalogObjectListener refreshListener;

    /**
     * The constructor.
     */
    public ServerUIPlugin()
    {
        super();
        plugin = this;
    }

    /**
     * Returns the shared instance.
     */
    public static ServerUIPlugin getDefault()
    {
        return plugin;
    }

    public static URL getInstallURL()
    {
        return getDefault().getBundle().getEntry("/"); //$NON-NLS-1$
    }
    
    public void start(BundleContext context) throws Exception 
    {
        super.start(context);
        RefreshManager.getInstance().AddListener(null, refreshListener = new ServerExplorerRefreshListener ());
    }
    
    public void stop (BundleContext context) throws Exception 
    {
        super.stop(context);
        RefreshManager.getInstance().removeListener(null, refreshListener);
    }
}
