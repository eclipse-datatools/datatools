/*******************************************************************************
 * Copyright (c) 2008 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Dimiter Dimitrov; Wolfgang Auer - initial API and implementation
 *******************************************************************************/ 

package org.eclipse.datatools.enablement.sap.maxdb;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class MaxDBEnablementPlugin extends Plugin {
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.enablement.sap.maxdb"; //$NON-NLS-1$	
	
	// The shared instance
	private static MaxDBEnablementPlugin plugin;
	
	private static ResourceBundle bundle;
	
	/**
	 * The constructor
	 */
	public MaxDBEnablementPlugin() {
		bundle = null;
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
	
	public static String getResourceString(String key){
		if(bundle == null){
			bundle = ResourceBundle.getBundle("org.eclipse.datatools.enablement.sap.maxdb.resources");  //$NON-NLS-1$
		}
		
		return bundle.getString(key);
	}
}
