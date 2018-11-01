/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.common.ui.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.common.ui.sqlstatementarea.SharedTextColors;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 * 
 * @author Hui Cao
 */
public class Activator extends AbstractUIPlugin
{
    static public final String PLUGIN_ID      = "org.eclipse.datatools.sqltools.common.ui";
    private static final int   INTERNAL_ERROR = 0;
    // The shared instance.
    private static Activator   plugin;
    private SharedTextColors    _sharedColors = null;

    /**
     * The constructor.
     */
    public Activator()
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
    	if(_sharedColors !=null)
        {
            _sharedColors.dispose();
            _sharedColors = null;
        }
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     */
    public static Activator getDefault()
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
        return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.datatools.sqltools.common.ui", path);
    }

    /**
     * Logs runtime status.
     * 
     * @param status Runtime status.
     */
    public void log(IStatus status)
    {
        getLog().log(status);
    }

    /**
     * Logs error message.
     * 
     * @param message Error message.
     */
    public void log(String message)
    {
        log(createErrorStatus(message));
    }

    /**
     * Logs and exception.
     * 
     * @param e Exception.
     */
    public void log(Throwable e)
    {
        log(createErrorStatus(e));
    }

    public IStatus createErrorStatus(String message)
    {
        return new Status(IStatus.ERROR, getBundle().getSymbolicName(), INTERNAL_ERROR, message, null);
    }

    public IStatus createErrorStatus(Throwable e)
    {
        return new Status(IStatus.ERROR, getBundle().getSymbolicName(), INTERNAL_ERROR, "Internal error", e); //$NON-NLS-1$
    }

    public IStatus createErrorStatus(String message, Throwable e)
    {
        return new Status(IStatus.ERROR, getBundle().getSymbolicName(), INTERNAL_ERROR, message, e);
    }

    /**
     * Logs an error message with an exception.
     * 
     * @param e Exception.
     */
    public void log(String message, Throwable e)
    {
        log(createErrorStatus(message, e));
    }

    public static Display getDisplay()
    {
    	return PlatformUI.getWorkbench().getDisplay();
    }
    
    /**
     * Gets the instance of <code>SharedTextColors</code>. 
     * User is recommended to get <code>Color</code> from <code>SharedTextColors</code>. 
     * The <code>Color</code> object from <code>SharedTextColors</code> is under control of
     * this plugin and user does not need to dispose the <code>Color</code> explicitly.
     * @return
     */
    public ISharedTextColors getSharedTextColors()
    {
        if(_sharedColors ==null)
        {
            _sharedColors = new SharedTextColors();
        }
        return _sharedColors;
    }
}
