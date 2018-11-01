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
package org.eclipse.datatools.sqltools.common.ui.preferences;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;

/**
 * @author Hui Cao
 *
 */
public class PreferencesRegistry
{
    public static final PreferencesRegistry INSTANCE = new PreferencesRegistry();
    /**
     * use page id as key, then use db id as key
     */
    private HashMap _pageSections = new HashMap();
    /**
     * store sections that can be used for launch configuration
     */
    private HashMap _launchSections = new HashMap();

    private PreferencesRegistry()
    {
        init();
    }

    private void init()
    {
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(Activator.PLUGIN_ID, "preferenceSections"); //$NON-NLS-1$ //$NON-NLS-2$
        IExtension[] extensions = extensionPoint.getExtensions();
        for (int i = 0; i < extensions.length; ++i)
        {
            IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
            for (int j = 0; j < configElements.length; ++j)
            {
                if (configElements[j].getName().equals("section")) 
                {
                    //$NON-NLS-1$
                    String page = configElements[j].getAttribute("page"); //$NON-NLS-1$
                    String id = configElements[j].getAttribute("dbdefinitionid"); //$NON-NLS-1$
                    Boolean launchConfig = Boolean.valueOf(configElements[j].getAttribute("launchConfig")); //$NON-NLS-1$
                    try
                    {
                        IDataServerPreferenceSection section = (IDataServerPreferenceSection) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$

                        if (this._pageSections.containsKey(page))
                        {
                            //FIXME: should we define a clone method for IDataServerPreferenceSection?
                            ((Map) this._pageSections.get(page)).put(id, section);
                        }
                        else
                        {
                            Map ids = new TreeMap();
                            ids.put(id, section);
                            this._pageSections.put(page, ids);
                        }

                        if (launchConfig.booleanValue())
                        {
                            //create a new instance for safety
                            IDataServerLaunchPreferenceSection launch = (IDataServerLaunchPreferenceSection) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$

                            if (this._launchSections.containsKey(page))
                            {
                                ((Map) this._launchSections.get(page)).put(id, launch);
                            }
                            else
                            {
                                Map ids = new TreeMap();
                                ids.put(id, launch);
                                this._launchSections.put(page, ids);
                            }
                        }
                    }
                    catch (CoreException e)
                    {
                        Activator.getDefault().log(e.getStatus());
                    }
                }
            }
        }
    }

    /**
     * Get all the registered page sections for the given preference page.
     * @param page preference page id
     * @return key: vendor name; value: IDataServerPreferenceSection
     */
    public Map getPageSections(String page)
    {
        if (_pageSections.containsKey(page))
        {
            return (Map) this._pageSections.get(page);
        }
        else
        {
            return new HashMap();
        }
    }

    /**
     * Get all the registered page sections which can be used in launch configuration for the given preference page.
     * @param page preference page id
     * @return key: vendor name; value: IDataServerLaunchPreferenceSection     */
    public Map getLaunchSections(String page)
    {
        if (_launchSections.containsKey(page))
        {
            return (Map) this._launchSections.get(page);
        }
        else
        {
            return new HashMap();
        }
    }
}
