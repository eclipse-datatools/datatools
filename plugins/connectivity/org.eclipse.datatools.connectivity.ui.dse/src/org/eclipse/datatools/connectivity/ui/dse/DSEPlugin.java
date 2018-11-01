/*******************************************************************************
 * Copyright (c) 2005-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.dse;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class DSEPlugin extends AbstractUIPlugin {

	public static final String SERVERS_VIEW_VIEWER_ID = "org.eclipse.datatools.connectivity.DataSourceExplorerNavigator"; //$NON-NLS-1$

	public static final String SERVERS_VIEW_CONTENT_EXTENSION_ID = "org.eclipse.datatools.connectivity.dsexplorer.content"; //$NON-NLS-1$
	
	public static final String PROP_SHOW_CATEGORIES = "showCategories"; //$NON-NLS-1$

	// The shared instance.
	private static DSEPlugin plugin;
	// Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public DSEPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		/* 
		 * Note that this is here to correct an issue with plug-in
		 * loading not loading the org.eclipse.datatools.connectivity.ui
		 * plug-in in time to make plug-in xml based filters work
		 * correctly. So we are forcing the plug-in to load early.
		 * If there is a better solution to this, please let us know.
		 */
		ConnectivityUIPlugin.getDefault();
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static DSEPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(
				"org.eclipse.datatools.connectivity.ui.dse", path); //$NON-NLS-1$
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle
						.getBundle("org.eclipse.datatools.connectivity.ui.dse.resources"); //$NON-NLS-1$
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

}
