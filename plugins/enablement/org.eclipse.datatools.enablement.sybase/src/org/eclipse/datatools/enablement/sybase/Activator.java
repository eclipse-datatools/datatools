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
package org.eclipse.datatools.enablement.sybase;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.enablement.sybase";

    private static final int INTERNAL_ERROR = 100000000;
   
	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);	
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
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
     * Logs error message. 
     * 
     * @param message Error message. 
     */ 
    public void debug(String message) { 
        log(createInfoStatus(message)); 
    } 
    
    /** 
     * Logs and exception. 
     * 
     * @param e Exception. 
     */ 
    public void log(Throwable e) { 
            log(createErrorStatus(e)); 
    } 

    public IStatus createErrorStatus(String message) { 
            return new Status(IStatus.ERROR, getBundle().getSymbolicName(), 
                            INTERNAL_ERROR, message, null); 
    } 

    public IStatus createInfoStatus(String message) { 
        return new Status(IStatus.INFO, getBundle().getSymbolicName(), 
                INTERNAL_ERROR, message, null); 
    } 
    
    public IStatus createErrorStatus(Throwable e) { 
            return new Status(IStatus.ERROR, getBundle().getSymbolicName(), 
                            INTERNAL_ERROR, Messages.plugin_internal_error, e); 
    } 

    public IStatus createErrorStatus(String message, Throwable e) { 
       return new Status(IStatus.ERROR, getBundle().getSymbolicName(), 
               INTERNAL_ERROR, message, e); 
    } 
    
    /** 
    * Logs an error message with an exception. 
    * 
    * @param e Exception. 
    */ 
    public void log(String message, Throwable e) { 
       log(createErrorStatus(message, e)); 
    } 

}
