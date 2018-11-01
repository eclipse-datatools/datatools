/*******************************************************************************
 * Copyright (c) 2005-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.properties.IPropertySource;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ConnectivityUIPlugin extends AbstractUIPlugin {

	public static final int INTERNAL_ERROR = 10001;

	public static final String FILTER_PROPERTY_CONTEN_EXTENSION_ID = "org.eclipse.datatools.connectivity.contentextension.id"; //$NON-NLS-1$

	public static final String FILTER_PROPERTY_CONTEN_EXTENSION_STATE = "org.eclipse.datatools.connectivity.contentextension.state"; //$NON-NLS-1$

	public static final String SERVERS_VIEW_CONTENT_EXTENSION_ID = "org.eclipse.datatools.connectivity.dsexplorer.content"; //$NON-NLS-1$
	
	public static final String PROP_SHOW_CATEGORIES = "showCategories"; //$NON-NLS-1$

	public static final String SERVERS_VIEW_VIEWER_ID = "org.eclipse.datatools.connectivity.DataSourceExplorerNavigator"; //$NON-NLS-1$

	// The shared instance.
	private static ConnectivityUIPlugin plugin;
	// Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public ConnectivityUIPlugin() {
		super();
		plugin = this;
	}

	/**
	 * Returns the shared instance.
	 */
	public static ConnectivityUIPlugin getDefault() {
		return plugin;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		addIConnectionProfileAdapter();
		addICategoryAdapter();
		addIWorkbenchWindowAdapter();
	}

	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle
						.getBundle("org.eclipse.datatools.connectivity.internal.ui.resources"); //$NON-NLS-1$
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

	/*
	 * Register an IPropertySource for IConnectionProfile.
	 */
	private static void addIConnectionProfileAdapter() {
		IAdapterFactory pr = new IAdapterFactory() {

			public Class[] getAdapterList() {
				Class[] c = new Class[2];
				c[0] = IPropertySource.class;
				c[1] = IActionFilter.class;
				return c;
			}

			public Object getAdapter(Object adaptableObject, Class adapterType) {

				if (adapterType.isAssignableFrom(IPropertySource.class)) {
					final IConnectionProfile icp = (IConnectionProfile) adaptableObject;
					return new ConnectionProfilePropertySource(icp);
				}
				else if (adapterType.isAssignableFrom(IActionFilter.class)) {
					return new ConnectionProfileActionFilter();
				}
				return null;
			}
		};
		Platform.getAdapterManager().registerAdapters(pr,
				IConnectionProfile.class);
	}

	/*
	 * Register an IPropertySource for ICategory.
	 */
	private static void addICategoryAdapter() {
		IAdapterFactory pr = new IAdapterFactory() {

			public Class[] getAdapterList() {
				Class[] c = new Class[2];
				c[0] = IPropertySource.class;
				c[1] = IActionFilter.class;
				return c;
			}

			public Object getAdapter(Object adaptableObject, Class adapterType) {

				if (adapterType.isAssignableFrom(IPropertySource.class)) {
					final ICategory icp = (ICategory) adaptableObject;
					return new CategoryPropertySource(icp);
				}
				else if (adapterType.isAssignableFrom(IActionFilter.class)) {
					return new CategoryActionFilter();
				}
				return null;
			}
		};
		Platform.getAdapterManager().registerAdapters(pr, ICategory.class);
	}

	/*
	 * Register an IPropertySource for ICategory.
	 */
	private static void addIWorkbenchWindowAdapter() {
		IAdapterFactory pr = new IAdapterFactory() {

			public Class[] getAdapterList() {
				Class[] c = new Class[2];
				c[0] = IPropertySource.class;
				c[1] = IActionFilter.class;
				return c;
			}

			public Object getAdapter(Object adaptableObject, Class adapterType) {

				if (adapterType.isAssignableFrom(IActionFilter.class)) {
					return new WorkbenchWindowPerspectiveActionFilter();
				}
				return null;
			}
		};
		Platform.getAdapterManager().registerAdapters(pr, IWorkbenchWindow.class);
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
				INTERNAL_ERROR, getResourceString("plugin.internal_error"), e); //$NON-NLS-1$
	}

}
