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

package org.eclipse.datatools.sqltools.internal.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

/**
 * Reads org.eclipse.datatools.sqltools.editor.core.dbFactories extensions.
 * @author Hui Cao
 *  
 */
public final class SQLDevToolsConfigRegistryImpl implements SQLDevToolsConfigRegistry
{
    public static final SQLDevToolsConfigRegistry INSTANCE                     = new SQLDevToolsConfigRegistryImpl();
    private static SQLDevToolsConfiguration DEFAULT_CONFIG                = SQLDevToolsConfiguration.getDefaultInstance();
	private static ArrayList _listeners = new ArrayList();
    //Hui Cao: we lazy load the factory classes to avoid circular dependency. Consequently all getXXX methods should check this field.
    private volatile Boolean                    _factoriesLoaded             = Boolean.FALSE;
    private Map                                 _products                    = new TreeMap();
    private Map                                 _factoriesById               = new TreeMap();
    private Map                                 _factoriesByVendorIdentifier = new TreeMap();
    private Map                                 _factoriesByName             = new TreeMap();
    private ArrayList                           _debuggerFactories           = new ArrayList();

    /**
     * Compares DBFactory by version number (type names starting with lower case characters).
     */
    public static class DBFactoryVersionComparator implements Comparator
    {
        public int compare(Object left, Object right)
        {
            SQLDevToolsConfiguration f1 = (SQLDevToolsConfiguration) left;
            SQLDevToolsConfiguration f2 = (SQLDevToolsConfiguration) right;

            return new DatabaseVendorDefinitionId.VersionComparator().compare(f1.getDatabaseVendorDefinitionId().getVersion(), f2.getDatabaseVendorDefinitionId().getVersion());
        }
    }

    public Collection getProducts()
    {
        init();
        return this._products.keySet();
    }

    public Collection getVersions(String product)
    {
        init();
        Map versions = (Map) this._products.get(product);
        if (versions == null)
        {
            return new TreeMap().keySet();
        }
        else
        {
            return versions.keySet();
        }
    }

    public Collection getConfigurations()
    {
        init();
        return _factoriesById.values();
    }

    public Collection getDebuggerConfigurations()
    {
    	init();
    	return _debuggerFactories;
    }
    
    public SQLDevToolsConfiguration getConfiguration(String product, String version)
    {
        init();
        Map versions = (Map) this._products.get(product);
        if (versions == null)
        {
            return null;
        }
        else
        {
            return (SQLDevToolsConfiguration) versions.get(version);
        }
    }

    public SQLDevToolsConfiguration getConfigurationById(String id)
    {
        init();
        return (SQLDevToolsConfiguration) this._factoriesById.get(id);
    }

    public SQLDevToolsConfiguration getConfigurationByVendorIdentifier(DatabaseVendorDefinitionId id)
    {
        init();
        return (SQLDevToolsConfiguration) this._factoriesByVendorIdentifier.get(id);
    }

    public SQLDevToolsConfiguration getConfigurationByName(String name)
    {
        init();
        return (SQLDevToolsConfiguration) this._factoriesByName.get(name);
    }

    private SQLDevToolsConfigRegistryImpl()
    {
    }

    private synchronized void init()
    {
    	{
    		if (_factoriesLoaded.booleanValue())
    		{
    			return;
    		}
    		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
    		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(EditorCorePlugin.PLUGIN_ID, "dbConfigurations"); //$NON-NLS-1$ //$NON-NLS-2$
    		IExtension[] extensions = extensionPoint.getExtensions();
    		for (int i = 0; i < extensions.length; ++i)
    		{
    			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
    			for (int j = 0; j < configElements.length; ++j)
    			{
    				if (configElements[j].getName().equals("dbConfiguration")) 
    				{
    					//$NON-NLS-1$
    					String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
    					String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
    					//String desc = configElements[j].getAttribute("description"); //$NON-NLS-1$
    					String name = product + "_" + version;
    					String id = configElements[j].getAttribute("id"); //$NON-NLS-1$
    					String supportsDebugging = configElements[j].getAttribute("supportsDebugging"); //$NON-NLS-1$
    					String isDefault = configElements[j].getAttribute("default"); //$NON-NLS-1$
    					if (id == null)
    					{
    						id = name;
    					}
    					String className = configElements[j].getAttribute("configurationClass");
    					try
    					{
    						//Hui Cao: caution here: if the instantiated factoryClass in turn calls this registry during initialization, error will occur. 
    						SQLDevToolsConfiguration factory = (SQLDevToolsConfiguration) configElements[j].createExecutableExtension("configurationClass"); //$NON-NLS-1$
    						DatabaseVendorDefinitionId dbVendorId = new DatabaseVendorDefinitionId(product, version);
    						factory.setDatabaseVendorDefinitionId(dbVendorId);
    						
    						if (this._products.containsKey(product))
    						{
    							((Map) this._products.get(product)).put(version, factory);
    						}
    						else
    						{
    							Map versions = new TreeMap();
    							versions.put(version, factory);
    							this._products.put(product, versions);
    						}
    						if (!this._factoriesById.containsKey(id))
    						{
    							this._factoriesById.put(id, factory);
    						}
    						
    						DatabaseVendorDefinitionId identifier = new DatabaseVendorDefinitionId(product, version); 
    						if (!this._factoriesByVendorIdentifier.containsKey(identifier))
    						{
    							this._factoriesByVendorIdentifier.put(identifier, factory);
    						}
    						
    						if (!this._factoriesByName.containsKey(name))
    						{
    							this._factoriesByName.put(name, factory);
    						}
    						if ("true".equals(supportsDebugging))
    						{
    							_debuggerFactories.add(factory);
    						}
    						if ("true".equals(isDefault))
    						{
    							DEFAULT_CONFIG = factory;
    						}
    					}
    					catch (Exception e)
    					{
    						e.printStackTrace();
    						try
    						{
    							IStatus status = new Status(IStatus.ERROR, EditorCorePlugin.PLUGIN_ID, IStatus.ERROR,
    									"The error was detected when creating the database recognizer " + className, e);
    							EditorCorePlugin.getDefault().log(status);
    						}
    						catch (Exception ee)
    						{
    							ee.printStackTrace();
    						}
    					}
    				}
    			}
    		}
    		
    		_factoriesLoaded = Boolean.TRUE;
    		
    	}
        for (Iterator iter = _listeners.iterator(); iter.hasNext();) {
			final IConfigurationRegistryListener l = (IConfigurationRegistryListener) iter.next();
			new Thread()
			{
				public void run()
				{
					waitForEditorCorePluginStart();
					l.configurationLoaded();
				}
			}.start();
		}
    }
    
    public static SQLDevToolsConfiguration getDefaultConfiguration()
    {
        ((SQLDevToolsConfigRegistryImpl)INSTANCE).init();
    	return DEFAULT_CONFIG;
    }

    public void addConfigurationRegistryListener(final IConfigurationRegistryListener listener)
    {
    	_listeners.add(listener);
    	new Thread() {
			public void run() {
				{
					waitForEditorCorePluginStart();
					if (_factoriesLoaded.booleanValue())
					{
						listener.configurationLoaded();
					}
					else
					{
						SQLDevToolsConfigRegistryImpl.this.init();
					}
				}
			}
		}.start();
    }
    
    private static Object bundleStartSemaphore = new Object();
    
    private static BundleListener listenerBundleStart = new BundleListener() {
		public void bundleChanged(BundleEvent event) {
			if (event.getBundle() == EditorCorePlugin.getDefault().getBundle() && event.getType() == BundleEvent.STARTED) {
				synchronized (bundleStartSemaphore) {
					bundleStartSemaphore.notify();
				}
			}
		}
	};
    
    private static void waitForEditorCorePluginStart() {
    	final Bundle bundle = EditorCorePlugin.getDefault().getBundle();
    	if (bundle.getState() != Bundle.STARTING)
    		return;
    	
    	bundle.getBundleContext().addBundleListener(listenerBundleStart);
    	
    	synchronized (bundleStartSemaphore) {
    		while (bundle.getState() == Bundle.STARTING) {
    			try {
    				bundleStartSemaphore.wait();
    			}
    			catch (InterruptedException ie) { }
    		}
    	}
    	
    	bundle.getBundleContext().removeBundleListener(listenerBundleStart);
    }
}
