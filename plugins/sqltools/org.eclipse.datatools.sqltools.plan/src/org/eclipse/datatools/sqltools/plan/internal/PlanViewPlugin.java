/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.internal;

import java.util.ResourceBundle;

import org.eclipse.datatools.sqltools.plan.internal.core.PlanManager;
import org.eclipse.datatools.sqltools.plan.internal.util.ILogger;
import org.eclipse.datatools.sqltools.plan.internal.util.StatusLogger;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 * 
 * @author Dafan Yang
 */
public class PlanViewPlugin extends AbstractUIPlugin
{

    private static final int INTERNAL_ERROR = -1;
	private ResourceBundle        _bundle = ResourceBundle.getBundle(PlanConstants.PLUGIN_RESOURCE_BUNDLE);
    // The shared instance.
    private static PlanViewPlugin plugin;
    
    private IPlanManager          _planManager;
    /**
     * The constructor.
     */
    public PlanViewPlugin()
    {
        plugin = this;
    }

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception
    {
        super.start(context);
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception
    {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance.
     */
    public static PlanViewPlugin getDefault()
    {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     * 
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path)
    {
        return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.datatools.sqltools.plan", path);
    }

    /**
     * Returns a logger.
     * 
     * @param bundle the bundle used in logger
     * @return a logger
     */
    public static ILogger getLogger(ResourceBundle bundle)
    {
        return new StatusLogger(getDefault().getLog(), PlanConstants.PLUGIN_ID, bundle == null ? getDefault()._bundle
                : bundle);
    }
    
    /**
     * Returns the plan manager of Execution Plan View
     * 
     * @return the plan manager
     */
    public static IPlanManager getPlanManager()
    {
        synchronized (getDefault())
        {
            if (getDefault()._planManager == null)
            {
                getDefault()._planManager = new PlanManager();
            }
            return getDefault()._planManager;
        }
    }
    
    /**
     * Returns the active workbench shell
     * @return the active workbench shell
     */
    public static Shell getActiveWorkbenchShell()
    {
        IWorkbenchWindow window = getActiveWorkbenchWindow();
        if (window != null)
        {
            return window.getShell();
        }
        IWorkbenchWindow[] windows = getDefault().getWorkbench().getWorkbenchWindows();
        if (windows.length > 0)
        {
            return windows[0].getShell();
        }
        return null;
    }
    
    /**
     * Returns active workbench window.
     * <p>
     * This method exists as a convenience for plug-in implementors.
     * </p>
     * 
     * @return IWorkbenchWindow the workbench for this plug-in
     */
    public static IWorkbenchWindow getActiveWorkbenchWindow()
    {
        IWorkbenchWindow window = getDefault().getWorkbench().getActiveWorkbenchWindow();
        if (window != null)
        {
            return window;
        }
        IWorkbenchWindow[] windows = getDefault().getWorkbench().getWorkbenchWindows();
        if (windows.length > 0)
        {
            return windows[0];
        }
        return null;
    }
}
