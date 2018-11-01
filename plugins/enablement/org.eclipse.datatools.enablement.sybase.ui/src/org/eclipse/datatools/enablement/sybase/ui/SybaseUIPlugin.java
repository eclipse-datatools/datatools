/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class SybaseUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.enablement.sybase.ui";

	// The shared instance
	private static SybaseUIPlugin plugin;
	
	/**
	 * The constructor
	 */
	public SybaseUIPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);	
		IPreferenceStore store = this.getPreferenceStore();
		SybaseDatabaseProfileSettingManager manager = SybaseDatabaseProfileSettingManager.getInstance();
		manager.setShowSchemaGlobal(store.getBoolean(SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_SCHEMA));
		manager.setShowOwnerGlobal(store.getBoolean(SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_OWNER));
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
	public static SybaseUIPlugin getDefault() {
		return plugin;
	}

    /** 
     * Logs runtime status. 
     * 
     * @param status Runtime status. 
     */ 
 
    /**
     * Get active workbench page.
     * <p>
     * This method acts as a convenience for plug-in implementors.
     * </P>
     * 
     * @return IWorkbenchPage the workbench page for this plug-in
     */
    public static IWorkbenchPage getActiveWorkbenchPage()
    {
        IWorkbenchPage workbenchPage = getActiveWorkbenchWindow().getActivePage();
        if (workbenchPage != null)
        {
            return workbenchPage;
        }
        IWorkbenchPage[] workbenchPages = getActiveWorkbenchWindow().getPages();
        if (workbenchPages.length > 0)
        {
            return workbenchPages[0];
        }
        return null;
    }

    /**
     * Get active workbench window.
     * <p>
     * This method exists as a convenience for plug-in implementors.
     * </p>
     * 
     * @return IWorkbenchWindow the workbench for this plug-in
     */
    public static IWorkbenchWindow getActiveWorkbenchWindow()
    {
        IWorkbenchWindow window = getDefault().getWorkbench().getActiveWorkbenchWindow();
        if (window != null)
        {
            return window;
        }
        IWorkbenchWindow[] windows = getDefault().getWorkbench().getWorkbenchWindows();
        if (windows.length > 0)
        {
            return windows[0];
        }
        return null;
    }

}
