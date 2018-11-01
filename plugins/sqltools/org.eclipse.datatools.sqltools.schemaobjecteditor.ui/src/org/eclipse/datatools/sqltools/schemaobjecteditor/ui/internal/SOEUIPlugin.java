/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal;

import java.util.ResourceBundle;

import org.eclipse.datatools.sqltools.core.profile.SQLToolsProfileListenersManager;
import org.eclipse.datatools.sqltools.editor.core.connection.SQLToolsConnectListenersManager;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ColorProvider;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ILogger;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.StatusLogger;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class SOEUIPlugin extends AbstractUIPlugin
{

    private ResourceBundle     _bundle   = ResourceBundle
                                                 .getBundle("org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.LogMessages");
    // The plug-in ID
    public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.schemaobjecteditor.ui";

    // The shared instance
    private static SOEUIPlugin   plugin;
    private ColorProvider      _colorProvider;

    /**
     * The constructor
     */
    public SOEUIPlugin()
    {
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception
    {
        super.start(context);
        SchemaEditorsHandler handler = new SchemaEditorsHandler();
        SQLToolsConnectListenersManager.getInstance().addConnectListener(handler);
        SQLToolsProfileListenersManager.getInstance().addProfileListener(handler);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception
    {
        // release those Color objects
        if (_colorProvider != null)
        {
            _colorProvider.dispose();
            _colorProvider = null;
        }
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static SOEUIPlugin getDefault()
    {
        return plugin;
    }

    /**
     * Get active workbench page.
     * <p>
     * This method acts as a convenience for plug-in implementors.
     * </P>
     * 
     * @return IWorkbenchPage the workbench page for this plug-in
     */
    public static IWorkbenchPage getActiveWorkbenchPage()
    {
        IWorkbenchPage workbenchPage = getActiveWorkbenchWindow().getActivePage();
        if (workbenchPage != null)
        {
            return workbenchPage;
        }
        IWorkbenchPage[] workbenchPages = getActiveWorkbenchWindow().getPages();
        if (workbenchPages.length > 0)
        {
            return workbenchPages[0];
        }
        return null;
    }

    /**
     * Get active workbench window.
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

    /**
     * @return
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
            return windows[0].getShell();
        return null;
    }

    /**
     * Returns a logger.
     * 
     * @param bundle the bundle used in logger
     * @return a logger
     */
    public static ILogger getLogger(ResourceBundle bundle)
    {
        return new StatusLogger(getDefault().getLog(), PLUGIN_ID, bundle == null ? getDefault()._bundle : bundle);
    }

    public ColorProvider getColorProvider()
    {
        if (_colorProvider == null)
        {
            _colorProvider = new ColorProvider();
        }
        return _colorProvider;
    }
}
