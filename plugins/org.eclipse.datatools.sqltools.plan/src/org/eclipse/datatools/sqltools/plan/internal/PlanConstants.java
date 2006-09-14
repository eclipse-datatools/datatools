/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.internal;

/**
 * Constants
 * @author Dafan Yang
 */
public interface PlanConstants
{
    public static final String PLUGIN_ID                    = "org.eclipse.datatools.sqltools.plan";
    public static final String PLAN_VIEW_ID                 = "org.eclipse.datatools.sqltools.plan.planView";
    public static final String PLUGIN_RESOURCE_BUNDLE       = PLUGIN_ID + ".internal.PluginResources";
    
    // For the extension point "planService"
    public static final String PLAN_SERVICE_EXTENSION_POINT = "planService";
    public static final String DATABASE_DEFINITION_ID       = "databaseVendorDefinitionId";
    public static final String PLAN_SERVICE_CLASS           = "serviceClass";

    // help context ids
    public static final String HELP_PLAN_VIEW                = PLUGIN_ID + "plan_view";                            //$NON-NLS-1$

}
