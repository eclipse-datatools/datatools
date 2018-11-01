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
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection;

import java.util.ArrayList;
import java.util.Collection;
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
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;

/**
 * @author Hui Cao
 */
public class DBDefinitionFilterRegistry {
	
	private static DBDefinitionFilterRegistry _instance = null;
	private ArrayList _dbdefs = null;
	
	private DBDefinitionFilterRegistry()
	{
	}
	
	public static DBDefinitionFilterRegistry getInstance()
	{
		if (_instance == null)
		{
			_instance = new DBDefinitionFilterRegistry();
		}
		return _instance;
	}
	
	/**
	 * Reads the extension registry and get all the dbdefinitions that should be filtered out in SQL scrapbook/SQL file editor.
	 * Ignores those dbdefinitions who have corresponding dbConfiguration extensions.
	 * @return
	 */
	public synchronized ArrayList getFilteredDefinitions()
	{
		if (_dbdefs == null)
		{
			_dbdefs = new ArrayList();
    		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
    		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(SqlscrapbookPlugin.PLUGIN_ID, "dbdeffilter"); //$NON-NLS-1$ //$NON-NLS-2$
    		IExtension[] extensions = extensionPoint.getExtensions();
    		for (int i = 0; i < extensions.length; ++i)
    		{
    			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
    			for (int j = 0; j < configElements.length; ++j)
    			{
    				if (configElements[j].getName().equals("dbdefinition")) 
    				{
    					String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
    					String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
    					DatabaseVendorDefinitionId id = new DatabaseVendorDefinitionId(product, version);
    					_dbdefs.add(id);
    				}
    			}
    		}
    		
    		Collection configs = SQLToolsFacade.getSupportedDBDefinitionNames();
    		for (Iterator it = configs.iterator(); it
					.hasNext();) {
				String name = (String) it.next();
				DatabaseVendorDefinitionId id = new DatabaseVendorDefinitionId(name);
				if (_dbdefs.contains(id))
				{
					_dbdefs.remove(id);
				}
			}
		}
		return _dbdefs;
	}
}
