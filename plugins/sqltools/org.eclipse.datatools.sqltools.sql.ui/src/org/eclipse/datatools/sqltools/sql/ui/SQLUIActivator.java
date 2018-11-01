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
package org.eclipse.datatools.sqltools.sql.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class SQLUIActivator extends AbstractUIPlugin 
{
	private static final int INTERNAL_ERROR = 0;
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.sql.ui";

	// The shared instance
	private static SQLUIActivator plugin;
	
	/**
	 * The constructor
	 */
	public SQLUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
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
	public static SQLUIActivator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.datatools.sqltools.sql", path);
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
     * Logs an error message with an exception.
     * 
     * @param e Exception.
     */
    public void log(String message, Throwable e) {
        log(createErrorStatus(message, e));
    }
    
    public IStatus createErrorStatus(String message, Throwable e) {
        return new Status(IStatus.ERROR, getBundle().getSymbolicName(),
                INTERNAL_ERROR, message, e);
    }

}
