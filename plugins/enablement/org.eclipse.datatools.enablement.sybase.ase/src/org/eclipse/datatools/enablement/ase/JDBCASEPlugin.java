/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.ase;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class JDBCASEPlugin extends Plugin
{

    private static final int     INTERNAL_ERROR = 0;
    // The shared instance.
    private static JDBCASEPlugin plugin;

    /**
     * The constructor.
     */
    public JDBCASEPlugin()
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
        super.stop(context);
        plugin = null;
    }

    /**
     * Returns the shared instance.
     */
    public static JDBCASEPlugin getDefault()
    {
        return plugin;
    }

    /**
     * Build a jConnect driver URL from a host, port, and dbName
     * 
     * @param host
     * @param port
     * @param dbName
     * 
     * @return urlString
     */
    public static String makeDriverURL(String host, String port, String dbName)
    {
        String driverURL = "jdbc:sybase:Tds:" + host + ":" + port; //$NON-NLS-1$//$NON-NLS-2$
        if (dbName != null && dbName.trim().length() > 0)
        {
            driverURL = driverURL + "/" + dbName + "?ServiceName=" + dbName; //$NON-NLS-1$//$NON-NLS-2$
        }
        return driverURL;
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
        return new Status(IStatus.ERROR, getBundle().getSymbolicName(), INTERNAL_ERROR,
                JDBCASEProfileMessages.plugin_internal_error, e);
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

}
