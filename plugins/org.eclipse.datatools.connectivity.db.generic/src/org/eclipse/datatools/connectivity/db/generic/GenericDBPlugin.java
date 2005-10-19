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
package org.eclipse.datatools.connectivity.db.generic;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class GenericDBPlugin extends AbstractUIPlugin {

	// The shared instance.
	private static GenericDBPlugin plugin;
	// Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public GenericDBPlugin() {
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
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static GenericDBPlugin getDefault() {
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
				"org.eclipse.datatools.connectivity.db.generic", path);
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle
						.getBundle("org.eclipse.datatools.connectivity.db.generic.resources");
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
