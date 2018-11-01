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
package org.eclipse.datatools.sqltools.routineeditor.parameter;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.routineeditor.internal.RoutineEditorActivator;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.datatools.sqltools.routineeditor.launching.RoutineLaunchConfigurationAttribute;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;

/**
 * This listener tries to rename all the parameters configuration file when launch configuration's name is changed by
 * the user. When the configuration is deleted, we can not read the configuration anymore(the file is already deleted),
 * so we just leave all the files there, this will not cause problem, but will produce some garbage files. When the
 * ILaunchManager provides more friendly interface, we also need to update this. So CR383033-2 is fired to track this
 * issue.
 * 
 * @author Dafan Yang
 * 
 */
public class LaunchConfigurationParamsHistoryListener implements ILaunchConfigurationListener
{
    private static LaunchConfigurationParamsHistoryListener instance = null;
    private String                                          _configDir;
    private ILaunchConfiguration                            _moveFrom;

    private LaunchConfigurationParamsHistoryListener()
    {
        _configDir = EditorCorePlugin.getDefault().getStateLocation().append("params_config").toOSString();
        File dir = new File(_configDir);
        if (!dir.exists())
        {
            dir.mkdir();
        }
    }

    synchronized public static LaunchConfigurationParamsHistoryListener getInstance()
    {
        if (instance == null)
        {
            instance = new LaunchConfigurationParamsHistoryListener();
        }
        return instance;
    }

    /**
     * In fact, when the name of a launch configuration is changed, this event also will be fired.
     */
    public void launchConfigurationAdded(ILaunchConfiguration configuration)
    {
        // a newly-created launch configuration or renamed configuration
        try
        {
            if ((configuration.getType() == LaunchHelper.getLaunchConfigType()) && _moveFrom != null)
            {
                // must be renamed from other configuration
                File configDir = new File(_configDir + File.separator +  _moveFrom.getName());
                File configDirNew = new File(_configDir + File.separator + configuration.getName());
                configDir.renameTo(configDirNew);
                    _moveFrom = null;
            }
        }
        catch (CoreException ce)
        {
            RoutineEditorActivator.getDefault().log(ce);
        }
    }

    public void launchConfigurationChanged(ILaunchConfiguration configuration)
    {
        // do nothing for now
    }

    /**
     * RENAME or DELETE event
     * 
     */
    public void launchConfigurationRemoved(ILaunchConfiguration configuration)
    {
        // deleted
        if (DebugPlugin.getDefault().getLaunchManager().getMovedTo(configuration) == null)
        {
            // can not read the configuration, so we delete all the parameter files in this configuration directory.
            File configDir = new File(_configDir + File.separator + configuration.getName());
            if (configDir.exists() && configDir.isDirectory()) 
            {
                File[] fileArray = configDir.listFiles();
                for(int i=0; i< fileArray.length; i++)
                fileArray[i].delete();
                configDir.delete();		
            }
        }
        // name changed
        else
        {
            _moveFrom = configuration;
        }
    }

    /**
     * read all existing configurations from Launch Configuration
     * 
     */
    private ArrayList readExistingConfigurations(ILaunchConfiguration configuration)
    {
        ArrayList configs = new ArrayList();
        try
        {
            int configNum = configuration
                .getAttribute(RoutineLaunchConfigurationAttribute.ROUTINE_LAUNCH_CONFIGURATION_NUM, -1);

            if (configNum == -1)
            {
                return configs;
            }
            else
            {
                // read all configurations
                for (int i = 1; i < configNum + 1; i++)
                {
                    String configName = configuration.getAttribute(
                        RoutineLaunchConfigurationAttribute.ROUTINE_LAUNCH_CONFIGURATION_NAME + i, (String) null);
                    if (configName != null)
                    {
                        configs.add(configName);
                    }
                }
            }
        }
        catch (CoreException ce)
        {
        	RoutineEditorActivator.getDefault().log(ce);
        }
        return configs;
    }
}
