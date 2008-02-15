/*******************************************************************************
 * Copyright (c) 2008 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dimiter Dimitrov; Wolfgang Auer - initial API and implementation
 *******************************************************************************/ 

package org.eclipse.datatools.enablement.sap.maxdb;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class MaxDBEnablementPlugin extends Plugin {
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.enablement.sap.maxdb"; //$NON-NLS-1$	
	
	// The shared instance
	private static MaxDBEnablementPlugin plugin;
	
	/**
	 * The constructor
	 */
	public MaxDBEnablementPlugin() {
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
	public static MaxDBEnablementPlugin getDefault() {
		return plugin;
	}
}
