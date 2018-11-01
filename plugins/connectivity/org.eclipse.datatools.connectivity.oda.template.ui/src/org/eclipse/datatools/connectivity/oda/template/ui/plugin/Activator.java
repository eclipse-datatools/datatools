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

package org.eclipse.datatools.connectivity.oda.template.ui.plugin;

import java.net.URL;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * [Non-API] The activator class controls the plug-in life cycle.
 * Intended for use within this project only.
 */
public class Activator extends AbstractUIPlugin 
{
	// The plug-in ID
	private static final String PLUGIN_ID = 
        "org.eclipse.datatools.connectivity.oda.template.ui.plugin";  //$NON-NLS-1$

	// The shared instance
	private static Activator sm_plugin;
	
	/**
	 * The constructor
	 */
	public Activator() 
    {
		sm_plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start( BundleContext context ) throws Exception 
    {
		super.start( context );
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop( BundleContext context ) throws Exception 
    {
		sm_plugin = null;
		super.stop( context );
	}

	/**
	 * Returns the shared instance
	 */
	public static Activator getDefault() 
    {
		return sm_plugin;
	}
    
    /**
     * Returns the installation URL of this plugin.
     */
    public URL getInstallURL() 
    {
        return getDefault().getBundle().getEntry( "/" ); //$NON-NLS-1$
    }
    
    public static String getPluginId() 
    {
        if( getDefault() == null )  // not instantiated yet
            return PLUGIN_ID;
        return getDefault().getBundle().getSymbolicName();
    }

}
