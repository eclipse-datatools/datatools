/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.debugger.core.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.debugger.core.DebugHandlerManager;
import org.eclipse.datatools.sqltools.debugger.core.IDebugHandlerManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class DebuggerCorePlugin extends AbstractUIPlugin {

    private static final int INTERNAL_ERROR = 0;
    public static String PLUGIN_ID="org.eclipse.datatools.sqltools.debugger.core";
	//The shared instance.
	private static DebuggerCorePlugin plugin;
    private IDebugHandlerManager      _debugHandlerManager;


	/**
	 * The constructor.
	 */
	public DebuggerCorePlugin() {
		plugin = this;
        
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
        _debugHandlerManager = new DebugHandlerManager();

	}

	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
		_debugHandlerManager.dispose();
	}
	/**
	 * Returns the shared instance.
	 */
	public static DebuggerCorePlugin getDefault() {
		return plugin;
	}



	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.datatools.sqltools.debugger.core", path);
	}
    
    /**
     * Logs runtime status.
     * 
     * @param status Runtime status.
     */
    public void log(IStatus status) {
        getLog().log(status);
    }

    /**
     * Logs error message.
     * 
     * @param message Error message.
     */
    public void log(String message) {
        log(createErrorStatus(message));
    }

    /**
     * Logs an exception.
     * 
     * @param e Exception.
     */
    public void log(Throwable e) {
        log(createErrorStatus(e));
    }

    /**
     * Logs an error message with an exception.
     * 
     * @param e Exception.
     */
    public void log(String message, Throwable e) {
        log(createErrorStatus(message, e));
    }
    
    public IStatus createErrorStatus(String message) {
        return new Status(IStatus.ERROR, getBundle().getSymbolicName(),
                INTERNAL_ERROR, message, null);
    }

    public IStatus createErrorStatus(String message, Throwable e) {
        return new Status(IStatus.ERROR, getBundle().getSymbolicName(),
                INTERNAL_ERROR, message, e);
    }
    
    public IStatus createErrorStatus(Throwable e) {
        return new Status(IStatus.ERROR, getBundle().getSymbolicName(),
                INTERNAL_ERROR, DebuggerMessages.plugin_internal_error, e); 
    }

    /**
     * Gets active workbench page.
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
     * Gets active workbench window.
     * <p>
     * This method exists as a convenience for plug-in implementors.
     * </p>
     * 
     * @return IWorkbenchWindow the workbench for this plug-in
     */
    public static IWorkbenchWindow getActiveWorkbenchWindow()
    {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
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
     * Gets active workbench shell.
     * <p>
     * This method exists as a convenience for plug-in implementors.
     * </p>
     * 
     * @return Shell the workbench shell for this plug-in
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
     * Gets the <code>Display</code>.
     * <p>
     * This method exists as a convenience for plug-in implementors.
     * </p>
     * 
     */
    public static Display getDisplay()
    {
        Shell shell = getActiveWorkbenchShell();
        if (shell != null)
        return shell.getDisplay();
        else
        return Display.getDefault();
    }

    public IDebugHandlerManager getDebugHandlerManager()
    {
        if(_debugHandlerManager == null)
        {
            _debugHandlerManager = new DebugHandlerManager();
        }
        return _debugHandlerManager;
    }

}
