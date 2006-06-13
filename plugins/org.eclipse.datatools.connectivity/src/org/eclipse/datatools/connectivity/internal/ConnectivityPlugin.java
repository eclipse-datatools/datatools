/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

import com.ibm.icu.text.MessageFormat;

/**
 * The main plugin class to be used in the desktop.
 */
public class ConnectivityPlugin extends Plugin {

	public static final int INTERNAL_ERROR = 10001;
	
	public static final String PLUGIN_ID = "org.eclipse.datatools.connectivity";//$NON-NLS-1$

	// The shared instance.
	private static ConnectivityPlugin plugin;

	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public ConnectivityPlugin() {
		super();
		plugin = this;
	}

	/**
	 * Returns the shared instance.
	 */
	public static ConnectivityPlugin getDefault() {
		return plugin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		InternalProfileManager.getInstance().dispose();
		super.stop(context);
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle
						.getBundle("org.eclipse.datatools.connectivity.internal.resources"); //$NON-NLS-1$
		}
		catch (MissingResourceException x) {
			resourceBundle = null;
		}
		return resourceBundle;
	}

	public String getResourceString(String key) {
		try {
			ResourceBundle resBundle = getResourceBundle();
			if (resBundle == null) {
				return key;
			}

			return resBundle.getString(key);
		}
		catch (MissingResourceException e) {
			return key;
		}
	}

	public String getResourceString(String key, Object[] arguments) {
		MessageFormat f = new MessageFormat(getResourceString(key));

		return f.format(arguments);
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
		String message;
		if (e == null || e.getMessage() == null) {
			message = getResourceString("plugin.internal_error"); //$NON-NLS-1$
		}
		else {
			message = e.getMessage();
		}
		return new Status(IStatus.ERROR, getBundle().getSymbolicName(),
				INTERNAL_ERROR, message, e);
	}
}