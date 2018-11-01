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
package org.eclipse.datatools.sqltools.result.internal.core;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.IReExecutionRunnable;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;

/**
 * Utility class to read the extensions
 * @author Dafan Yang
 */
public class ReExecutionRegistryReader
{
    /**
     * Given a operation, this method is to find a approporiate IReExecutionRunnable handler to re-execute this
     * operaion.
     * 
     * @param cmd the operation command
     * @return a runnable class which implements <code>IReExecutionRunnable</code>
     */
    public static IReExecutionRunnable readProperReExecutionHandler(OperationCommand cmd)
    {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint point = registry.getExtensionPoint(ResultsViewPlugin.getPluginId(), ResultsConstants.RE_EXECUTION_POINT_ID);
        if(point == null)
        {
            // Should not happen
            return null;
        }
        IExtension[] extensions = point.getExtensions();
        for (int i = 0; i < extensions.length; i++)
        {
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            for (int j = 0; j < elements.length; j++)
            {
                String database_id = elements[j].getAttribute(ResultsConstants.EXTENSION_POINT_DATABASE_ID);
                String consumer_name = elements[j].getAttribute(ResultsConstants.EXTENSION_POINT_CONSUMER_NAME);
                IConnectionProfile profile = ProfileManager.getInstance().getProfileByName(cmd.getProfileName());
                if (profile == null)
                {
                    continue;
                }
                if (cmd.getConsumerName().equals(consumer_name) && (profile.getProviderId().equals(database_id)))
                {
                    try
                    {
                        IReExecutionRunnable reExecutionRunnable = (IReExecutionRunnable) elements[j]
                                .createExecutableExtension(ResultsConstants.EXTENSION_POINT_CLASS_NAME);
                        return reExecutionRunnable;
                    }
                    catch (Exception e)
                    {
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
