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
package org.eclipse.datatools.sqltools.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObjectListener;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.sqltools.core.modelvalidity.SQLModelValidatorRegistry;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.profile.SQLToolsProfileProxyListener;
import org.eclipse.datatools.sqltools.internal.core.ControlConnectionManager;
import org.eclipse.datatools.sqltools.internal.core.IConfigurationRegistryListener;
import org.eclipse.datatools.sqltools.internal.core.Messages;
import org.eclipse.datatools.sqltools.internal.core.SQLDevToolsConfigRegistry;
import org.eclipse.datatools.sqltools.internal.core.SQLDevToolsConfigRegistryImpl;
import org.osgi.framework.BundleContext;


/**
 * The main plugin class to be used in the desktop.
 * 
 * @author Hui Cao
 */
public class EditorCorePlugin extends Plugin {

	private static final int INTERNAL_ERROR = 0;
	public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.editor.core";
	//The shared instance.
	private static EditorCorePlugin plugin;
	private IControlConnectionManager       _controlConnectionManager;
	boolean _registered = false;
	private SQLModelValidatorRegistry _registry;

	/**
	 * The constructor.
	 */
	public EditorCorePlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		EditorCorePlugin.addConfigurationRegistryListener(new IConfigurationRegistryListener(){
			public void configurationLoaded() {
				register();
			}
        });
		
		_registry = new SQLModelValidatorRegistry();
	}

	private synchronized void register() {
		if (!_registered)
        {
            ProfileManager pManager = ProfileManager.getInstance();
            IConnectionProfile[] profiles = ProfileUtil.getProfiles();
            SQLToolsProfileProxyListener listener = SQLToolsProfileProxyListener.getInstance();
            listener.init(profiles);
            pManager.addProfileListener(listener);
            _registered = true;
        }
	}
	
	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
        if (_controlConnectionManager == null)
        {
            RefreshManager.getInstance().removeListener(null, (ICatalogObjectListener)_controlConnectionManager);
        }
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static EditorCorePlugin getDefault() {
		return plugin;
	}

    public static synchronized IControlConnectionManager getControlConnectionManager()
    {
        if (getDefault()._controlConnectionManager == null)
        {
            ControlConnectionManager controlConnectionManager = new ControlConnectionManager();
			getDefault()._controlConnectionManager = controlConnectionManager;
            RefreshManager.getInstance().AddListener(null, controlConnectionManager);
        }
        return getDefault()._controlConnectionManager;
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

	public static SQLDevToolsConfigRegistry getDatabaseFactoryRegistry()
    {
        return SQLDevToolsConfigRegistryImpl.INSTANCE;
    }
	

    public static void addConfigurationRegistryListener(IConfigurationRegistryListener listener)
    {
    	getDatabaseFactoryRegistry().addConfigurationRegistryListener(listener);
    }

    public SQLModelValidatorRegistry getSQLModelValidatorRegistry()
    {
        return _registry;
    }
}
