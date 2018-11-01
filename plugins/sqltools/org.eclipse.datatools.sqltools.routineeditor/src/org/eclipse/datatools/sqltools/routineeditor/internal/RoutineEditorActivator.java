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
package org.eclipse.datatools.sqltools.routineeditor.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.profile.SQLToolsProfileListenersManager;
import org.eclipse.datatools.sqltools.routineeditor.launching.SQLToolsLaunchProfileListener;
import org.eclipse.datatools.sqltools.routineeditor.parameter.LaunchConfigurationParamsHistoryListener;
import org.eclipse.debug.core.DebugPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class RoutineEditorActivator extends Plugin {

	private static final int INTERNAL_ERROR = 0;
	public static String PLUGIN_ID = "org.eclipse.datatools.sqltools.routineeditor";
	//The shared instance.
	private static RoutineEditorActivator plugin;
	private SQLToolsLaunchProfileListener _toolsLaunchProfileListener;
	
	/**
	 * The constructor.
	 */
	public RoutineEditorActivator() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
        SQLToolsProfileListenersManager pManager = SQLToolsProfileListenersManager.getInstance();
        _toolsLaunchProfileListener = new SQLToolsLaunchProfileListener();
        pManager.addProfileListener(_toolsLaunchProfileListener);
        DebugPlugin.getDefault().getLaunchManager().addLaunchConfigurationListener(LaunchConfigurationParamsHistoryListener.getInstance());
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
        SQLToolsProfileListenersManager pManager = SQLToolsProfileListenersManager.getInstance();
        pManager.removeProfileListener(_toolsLaunchProfileListener);
        super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 *
	 * @return the shared instance.
	 */
	public static RoutineEditorActivator getDefault() {
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
