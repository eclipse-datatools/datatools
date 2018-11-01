/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.plan.internal.PlanConstants;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.util.ILogger;


/**
 * Registry for "planService"
 * @author Dafan Yang
 */
public class PlanServiceRegistry
{
    private static ILogger             _log            = PlanViewPlugin.getLogger(null);
    private static PlanServiceRegistry _instance;
    private static Map                 _extensions;
    private static PlanService         _defaultService = new PlanService();

    private PlanServiceRegistry()
    {
        _extensions = new HashMap();
        readRegistries();
    }
    
    public static synchronized PlanServiceRegistry getInstance()
    {
        if(_instance == null)
        {
            _instance = new PlanServiceRegistry();
        }
        return _instance;
    }
    
    /**
     * Returns the plan service given the database definition id
     * @param databaseDefinitionId the database definition id
     * @return the plan service
     */
    public IPlanService getPlanService(String databaseDefinitionId)
    {
        IPlanService service = (IPlanService)_extensions.get(databaseDefinitionId);
        if (service == null)
        {
        	return _defaultService;
        }
        return service;
    }
    
    private void readRegistries()
    {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint point = registry.getExtensionPoint(PlanConstants.PLUGIN_ID, PlanConstants.PLAN_SERVICE_EXTENSION_POINT);
        if(point == null)
        {
            // Should not happen
            return;
        }
        IExtension[] extensions = point.getExtensions();
        for (int i = 0; i < extensions.length; i++)
        {
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            for (int j = 0; j < elements.length; j++)
            {
                String databaseDefinitionId = elements[j].getAttribute(PlanConstants.DATABASE_DEFINITION_ID);
                try
                {
                    IPlanService planService = (IPlanService)elements[j].createExecutableExtension(PlanConstants.PLAN_SERVICE_CLASS);
                    if(!_extensions.containsKey(databaseDefinitionId))
                    {
                        _extensions.put(databaseDefinitionId, planService);
                    }
                    else
                    {
                        // Ingore the duplicate extension
                    }
                }
                catch(CoreException ce)
                {
                    _log.error("PlanServiceRegistry.create.runnable.error", ce);
                }
            }
        }
    }
}
