/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RoutineEditorUIActivator extends AbstractUIPlugin {

	private static final int INTERNAL_ERROR = 0;
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.routineeditor.ui";

	// The shared instance
	private static RoutineEditorUIActivator plugin;
	
	/**
	 * The constructor
	 */
	public RoutineEditorUIActivator() {
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
	public static RoutineEditorUIActivator getDefault() {
		return plugin;
	}

    /**
     * Returns the standard display to be used. The method first checks, if the thread calling this method has an
     * associated dispaly. If so, this display is returned. Otherwise the method returns the default display.
     */
    public static Display getStandardDisplay()
    {
        Display display;
        display = Display.getCurrent();
        if (display == null)
        display = Display.getDefault();
        return display;
    }

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
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
