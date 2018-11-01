/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  brianf - added ProviderIDMappingRegistry reference
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.sqm.core;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.mappings.ProviderIDMappingRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;

/**
 * Public accessor of the SQL Query Model services.
 */
public class SQMServices
{
    private SQMServices() {}
    
    /**
     * Gets the system registry for access to available {@link DatabaseDefinition}.
     * @return  a {@link DatabaseDefinitionRegistry}
     */
    public static DatabaseDefinitionRegistry getDatabaseDefinitionRegistry() 
    {
        return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
    }
    
    /**
     * Gets the system containment service.
     * @return  a {@link ContainmentService}
     */
    public static ContainmentService getContainmentService()
    {
        return RDBCorePlugin.getDefault().getContainmentService();
    }
    
    /**
     * Gets the provider ID mapping registry service.
     * @return  a {@link ProviderIDMappingRegistry}
     */
    public static ProviderIDMappingRegistry getProviderIDMappingRegistry()
    {
        return ProviderIDMappingRegistry.getInstance();
    }
}
