/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class SQLActivator extends Plugin {

	private static final int INTERNAL_ERROR = 0;
    public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.sql";
    //The shared instance.
	private static SQLActivator plugin;
	
	/**
	 * The constructor.
	 */
	public SQLActivator() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 */
	public static SQLActivator getDefault() {
		return plugin;
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
                INTERNAL_ERROR, Messages.plugin_internal_error, e); 
    }

}
