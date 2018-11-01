/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.editor.ui.internal.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
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
import org.eclipse.datatools.sqltools.editor.ui.core.EditorCoreUIPlugin;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;


/**
 * 
 * @author linsong
 *  
 */
public final class SQLDevToolsUIConfigRegistryImpl implements SQLDevToolsUIConfigRegistry
{
    public static final SQLDevToolsUIConfigRegistry INSTANCE                     = new SQLDevToolsUIConfigRegistryImpl();
    private static SQLDevToolsUIConfiguration DEFAULT_CONFIG                = SQLDevToolsUIConfiguration.getDefaultInstance();
	private static ArrayList _listeners = new ArrayList();
    //Hui Cao: we lazy load the factory classes to avoid circular dependency. Consequently all getXXX methods should check this field.
    private volatile Boolean                    _factoriesLoaded             = Boolean.FALSE;
    private Map                                 _products                    = new TreeMap();
    private Map                                 _factoriesById               = new TreeMap();
    private Map                                 _factoriesByVendorIdentifier = new TreeMap();
    private Map                                 _factoriesByName             = new TreeMap();

    /**
     * Compares DBFactory by version number (type names starting with lower case characters).
     */
    public static class DBFactoryVersionComparator implements Comparator
    {
        public int compare(Object left, Object right)
        {
            SQLDevToolsUIConfiguration f1 = (SQLDevToolsUIConfiguration) left;
            SQLDevToolsUIConfiguration f2 = (SQLDevToolsUIConfiguration) right;

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

    public SQLDevToolsUIConfiguration getConfiguration(String product, String version)
    {
        init();
        Map versions = (Map) this._products.get(product);
        if (versions == null)
        {
            return null;
        }
        else
        {
            return (SQLDevToolsUIConfiguration) versions.get(version);
        }
    }

    public SQLDevToolsUIConfiguration getConfigurationById(String id)
    {
        init();
        return (SQLDevToolsUIConfiguration) this._factoriesById.get(id);
    }

    public SQLDevToolsUIConfiguration getConfigurationByVendorIdentifier(DatabaseVendorDefinitionId id)
    {
        init();
        return (SQLDevToolsUIConfiguration) this._factoriesByVendorIdentifier.get(id);
    }

    public SQLDevToolsUIConfiguration getConfigurationByName(String name)
    {
        init();
        return (SQLDevToolsUIConfiguration) this._factoriesByName.get(name);
    }

    private SQLDevToolsUIConfigRegistryImpl()
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
    		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(EditorCoreUIPlugin.PLUGIN_ID, "dbUIConfigurations"); //$NON-NLS-1$ //$NON-NLS-2$
    		IExtension[] extensions = extensionPoint.getExtensions();
    		for (int i = 0; i < extensions.length; ++i)
    		{
    			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
    			for (int j = 0; j < configElements.length; ++j)
    			{
    				if (configElements[j].getName().equals("dbUIConfiguration")) 
    				{
    					//$NON-NLS-1$
    					String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
    					String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
    					String name = product + "_" + version;
    					String id = configElements[j].getAttribute("id"); //$NON-NLS-1$
    					String isDefault = configElements[j].getAttribute("default"); //$NON-NLS-1$
    					if (id == null)
    					{
    						id = name;
    					}
    					String className = configElements[j].getAttribute("configurationClass");
    					try
    					{
    						SQLDevToolsUIConfiguration factory = (SQLDevToolsUIConfiguration) configElements[j].createExecutableExtension("configurationClass"); //$NON-NLS-1$
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
    }
    
    public static SQLDevToolsUIConfiguration getDefaultConfiguration()
    {
        ((SQLDevToolsUIConfigRegistryImpl)INSTANCE).init();
    	return DEFAULT_CONFIG;
    }

}
