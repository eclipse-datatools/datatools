/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.ftp;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The main plugin class to be used in the desktop.
 */
public class FtpProfilePlugin extends AbstractUIPlugin {
	// The shared instance.
	private static FtpProfilePlugin plugin;

	// The registered id for the ftp connection profile
	public final static String PROVIDER_ID = "org.eclipse.datatools.connectivity.sample.ftp"; //$NON-NLS-1$

	/**
	 * The constructor.
	 */
	public FtpProfilePlugin() {
		super();
		plugin = this;
	}

	/**
	 * Returns the shared instance.
	 */
	public static FtpProfilePlugin getDefault() {
		return plugin;
	}

	/**
	 * @return
	 * @throws MissingResourceException
	 */
	public ResourceBundle loadResourceBundle() throws MissingResourceException {
		return ResourceBundle
				.getBundle("org.eclipse.datatools.connectivity.sample.ftp.messages"); //$NON-NLS-1$
	}

}