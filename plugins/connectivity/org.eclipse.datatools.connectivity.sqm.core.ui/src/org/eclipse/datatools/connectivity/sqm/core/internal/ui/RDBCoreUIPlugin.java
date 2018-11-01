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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui;
import java.net.URL;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.CatalogTaskLabelProvider;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.CatalogUtil;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * @author ljulien
 */
public class RDBCoreUIPlugin extends AbstractUIPlugin
{
	private static RDBCoreUIPlugin plugin;
	
	/**
	 * Will keep in memory the images needed by our plugin
	 */
	private ImageRegistry imageRegistry;

	/**
	 * @param descriptor
	 */
    public RDBCoreUIPlugin()
    {
        super ();
        plugin = this;
    }
	/**
	 * @return The core.ui plugin
	 */
	public static RDBCoreUIPlugin getDefault() 
	{
		return plugin;
	}
    
	/**
	 * To use for manipulating images
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#getImageRegistry()
	 */
	public ImageRegistry getImageRegistry ()
	{
		return imageRegistry;
	}
	
	/**
	 * @return Will return the plugin ID
	 */
	public static String getPluginId ()
	{
		return getDefault().getBundle().getSymbolicName();
	}
	
	/**
	 * @return
	 */
	public static URL getInstallURL ()
	{
	   return getDefault().getBundle().getEntry("/");  //$NON-NLS-1$
	}
	
	/**
	 * 
	 */
	public void start(BundleContext ctx) throws Exception
	{
        try
        {
            super.start(ctx);
            CatalogUtil.setDefaultCatalogTaskLabelProvider(new CatalogTaskLabelProvider());
            imageRegistry = this.createImageRegistry();
        }
        catch (Throwable e)
        {
        }
	}
	
	/**
	 * 
	 */
	public void stop(BundleContext ctx) throws Exception
	{
        try
        {
            CatalogUtil.setDefaultCatalogTaskLabelProvider(null);
            super.stop(ctx);
        }
        catch (Throwable e)
        {
        }
	}

}
