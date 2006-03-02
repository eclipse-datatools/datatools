/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.launching;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.IDatabaseSetting;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifierImpl;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.IDatabaseSetting.NotSupportedSettingException;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.routineeditor.internal.RoutineEditorActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.launching.LaunchUI;
import org.eclipse.datatools.sqltools.routineeditor.util.RoutineUtil;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

/**
 * Helper methods for dealing with DTP routine launch configuration. We expect to let this class to manipulate all the
 * attributes.
 * 
 * @author Yang Liu
 */
public class LaunchHelper implements RoutineLaunchConfigurationAttribute
{

    public static ILaunchConfigurationType getLaunchConfigType()
    {
        return DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurationType(ROUTINE_LAUNCH_CONFIGURATION_TYPE);
    }

    //----------------------------------------------------------------------------------------------------------------------
    // 
    /**
     * Creates a launch configuration that can be used to launch a ILaunch that debug an external client connection.
     * 
     * @param sd
     * @param connid
     * @return
     */
    public static ILaunchConfigurationWorkingCopy createExternalClientConfiguration(DatabaseIdentifier databaseIdentifier, String connid)
        throws CoreException
    {
        ILaunchConfigurationWorkingCopy wc = null;

        ILaunchConfigurationType configType = getLaunchConfigType();
        wc = configType.newInstance(null, "externalCon" + connid);
        wc.setAttribute(ROUTINE_LAUNCH_PROFILENAME, databaseIdentifier.getProfileName());
        wc.setAttribute(ROUTINE_LAUNCH_DATABASENAME, databaseIdentifier.getDBname());
        wc.setAttribute(ROUTINE_LAUNCH_EXTERNALID, connid);

        return wc;
    }

    public static void saveSPUDF(ILaunchConfigurationWorkingCopy configuration, ProcIdentifier proc, List parameters)
    {
        configuration.setAttribute(ROUTINE_LAUNCH_PROFILENAME, proc == null ? "" : proc.getProfileName());
        configuration.setAttribute(ROUTINE_LAUNCH_DATABASENAME, proc == null ? "" : proc.getDatabaseIdentifier().getDBname());
        configuration.setAttribute(ROUTINE_LAUNCH_PROCID, proc == null ? "" : proc.encode());
        configuration.setAttribute(ROUTINE_LAUNCH_TYPE, 0);
        saveParameterList(configuration, parameters);
        setConnectionLevelOptions(configuration);
    }

    private static void saveParameterList(ILaunchConfigurationWorkingCopy configuration, List parameters)
    {
        // see comments in readParameterList
        if (parameters == null || parameters.size() == 0)
        {
            configuration.setAttribute(ROUTINE_LAUNCH_PARAMETERS, (List) null);
            return;
        }
        List l = new ArrayList();
        for (int i = 0; i < parameters.size(); i++)
        {
            String s = (String) parameters.get(i);
            if (s == null)
            {
                l.add("N");
            }
            else
            {
                l.add("Y" + s);
            }
        }
        configuration.setAttribute(ROUTINE_LAUNCH_PARAMETERS, l);
    }

    public static void saveEvent(ILaunchConfigurationWorkingCopy configuration, ProcIdentifier proc, Map eventparams)
    {
        configuration.setAttribute(ROUTINE_LAUNCH_PROFILENAME, proc == null ? "" : proc.getProfileName());
        configuration.setAttribute(ROUTINE_LAUNCH_DATABASENAME, proc == null ? "" : proc.getDatabaseIdentifier().getDBname());
        configuration.setAttribute(ROUTINE_LAUNCH_PROCID, proc == null ? "" : proc.encode());
        configuration.setAttribute(ROUTINE_LAUNCH_TYPE, 0);
        saveEventParameter(configuration, eventparams);
    }

    /**
     * @param configuration
     * @param triggerParams
     */
    private static void saveEventParameter(ILaunchConfigurationWorkingCopy wc, Map triggerParams)
    {
        wc.setAttribute(ROUTINE_LAUNCH_EVENTPARAMS, triggerParams);
    }

    public static void saveAdHocSQL(ILaunchConfigurationWorkingCopy configuration, String profileName, String dbName, String sql)
    {
        configuration.setAttribute(ROUTINE_LAUNCH_PROFILENAME, profileName);
        configuration.setAttribute(ROUTINE_LAUNCH_DATABASENAME, dbName);
        configuration.setAttribute(ROUTINE_LAUNCH_TYPE,3);
        configuration.setAttribute(ROUTINE_LAUNCH_ADHOCSQL, sql);
    }

    public static void saveTrigger(ILaunchConfigurationWorkingCopy configuration,  ProcIdentifier proc, String sql)
    {
        configuration.setAttribute(ROUTINE_LAUNCH_PROFILENAME, proc == null ? "" : proc.getDatabaseIdentifier().getProfileName());
        configuration.setAttribute(ROUTINE_LAUNCH_DATABASENAME, proc == null ? "" : proc.getDatabaseIdentifier().getDBname());
        configuration.setAttribute(ROUTINE_LAUNCH_PROCID, proc == null ? "" : proc.encode());
        configuration.setAttribute(ROUTINE_LAUNCH_TYPE, 0);
        configuration.setAttribute(ROUTINE_LAUNCH_TRIGGERSQL, sql);
    }

    public static void initializeConfiguration(ILaunchConfigurationWorkingCopy configuration, ProcIdentifier proc)
    {
        if (proc.getType() == ProcIdentifier.TYPE_TRIGGER)
        {
            // for trigger, can only be AdHoc SQL
            saveTrigger(configuration, proc, "");
        }
        else if (proc.getType() == ProcIdentifier.TYPE_EVENT)
        {
            saveEvent(configuration, proc, new HashMap());
        }
        else
        {
            ArrayList values = new ArrayList();
            try
            {
                ParameterDescriptor[] pds =  LaunchUI.getParameterDescriptors(proc);
                for (int i = 0; i < pds.length; i++)
                {
                    ParameterDescriptor descriptor = pds[i];
                    if (descriptor!=null )
                    {
                        String defaultValue= descriptor.getDefaultValue();
                        if (defaultValue!=null)
                        {
                            values.add(defaultValue);
                        }
                        else 
                        {
                            values.add(null);
                        }
                    }
                    else 
                    {
                        values.add(null);
                    }

                }

            }
            catch (SQLException e)
            {
                RoutineEditorActivator.getDefault().log(e);
            }
            catch (NoSuchProfileException e)
            {
            	RoutineEditorActivator.getDefault().log(e);
            }
            saveSPUDF(configuration, proc, values);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------
    /**
     * "" means no client connection id
     * 
     * @param configuration
     * @return
     */
    public static String readExternalClientId(ILaunchConfiguration configuration) throws CoreException
    {
        return configuration.getAttribute(ROUTINE_LAUNCH_EXTERNALID, "");
    }

    public static DatabaseIdentifier readDatabaseIdentifier(ILaunchConfiguration configuration) throws CoreException
    {
        String profileName = configuration.getAttribute(ROUTINE_LAUNCH_PROFILENAME, "");
        String dbName = configuration.getAttribute(ROUTINE_LAUNCH_DATABASENAME, "");
        if ("".equals(profileName))
        {
            throw new CoreException(new Status(IStatus.ERROR, RoutineEditorActivator.PLUGIN_ID, 0,
                Messages.getString("LaunchHelper.invalid.launch.configuration"), null)); //$NON-NLS-1$
        }
        else
        {
            return new DatabaseIdentifier(profileName, dbName);
        }
    }

    public static boolean isAdHocSQL(ILaunchConfiguration configuration) throws CoreException
    {
        int type = configuration.getAttribute(ROUTINE_LAUNCH_TYPE, 0);
        return type == 3;
    }

    /**
     * This method should only be called when is not adhoc sql.
     * 
     * @param configuration
     * @return null if fail to read the proc id.
     * @throws CoreException
     */
    public static ProcIdentifier readProcIdentifier(ILaunchConfiguration configuration) throws CoreException
    {
        if (isAdHocSQL(configuration))
        {
            return null;
        }
        String encoded = configuration.getAttribute(ROUTINE_LAUNCH_PROCID, "");
        try
        {
            return ProcIdentifierImpl.decode(encoded);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * @param configuration
     * @return @throws CoreException
     */
    public static List readParameterList(ILaunchConfiguration configuration) throws CoreException
    {
        // it seemed that the configuration List don't support null element. So
        // we use the following convention internally. For "null", we use character "N".
        // for Non null, we use "Y"+following string
        List l = configuration.getAttribute(ROUTINE_LAUNCH_PARAMETERS, new ArrayList());
        List nl = new ArrayList();

        for (int i = 0; i < l.size(); i++)
        {
            String s = (String) l.get(i);
            nl.add(s);
            if (s == null || s.length() == 0)
            {
                nl.set(i, null);
            }
            else
            {
                if (s.charAt(0) == 'N')
                {
                    nl.set(i, null);
                }
                else if (s.charAt(0) == 'Y')
                {
                    nl.set(i, s.substring(1));
                }
                else
                {
                    nl.set(i, s); //should not happen. we don't do anything.
                }
            }
        }
        return nl;
    }

    /**
     * @param configuration
     * @return @throws CoreException
     */
    public static Map readEventParameter(ILaunchConfiguration configuration) throws CoreException
    {
        return configuration.getAttribute(ROUTINE_LAUNCH_EVENTPARAMS, new HashMap());
    }

    /**
     * @param configuration
     * @return @throws CoreException
     */
    public static String readAdHocSQL(ILaunchConfiguration configuration) throws CoreException
    {
        return configuration.getAttribute(ROUTINE_LAUNCH_ADHOCSQL, "");
    }

    /**
     * @param configuration
     * @return @throws CoreException
     */
    public static String readTriggerSQL(ILaunchConfiguration configuration) throws CoreException
    {
        return configuration.getAttribute(ROUTINE_LAUNCH_TRIGGERSQL, "");
    }

    /**
     * @param configuration
     * @return @throws CoreException
     */
    public static String constructFinalSQLString(ILaunchConfiguration configuration) throws CoreException,
        SQLException, NoSuchProfileException
    {
        if (isAdHocSQL(configuration))
        return readAdHocSQL(configuration);
        ProcIdentifier proc = readProcIdentifier(configuration);
        if (proc == null)
        return "";
        boolean quoted_id = readQuotedIDConfig(configuration, proc);
        switch (proc.getType())
        {
            case ProcIdentifier.TYPE_SP:
            case ProcIdentifier.TYPE_UDF:
                return RoutineUtil.constructSPUDFString(proc, readParameterList(configuration),LaunchUI.getParameterDescriptors(proc), quoted_id);   
            case ProcIdentifier.TYPE_EVENT:
                return RoutineUtil.constructTriggerEventString(proc, readEventParameter(configuration), quoted_id);
            case ProcIdentifier.TYPE_TRIGGER:
                return readTriggerSQL(configuration);
            default:
                return "";
        }
    }

    /**
     * @param configuration
     * @return @throws CoreException
     */
    public static String constructFinalCallSQLString(ILaunchConfiguration configuration) throws CoreException,
        SQLException, NoSuchProfileException
    {
        if (isAdHocSQL(configuration))
        return readAdHocSQL(configuration);
        ProcIdentifier proc = readProcIdentifier(configuration);
        if (proc == null)
        return "";
        boolean quoted_id = readQuotedIDConfig(configuration, proc);
        switch (proc.getType())
        {
            case ProcIdentifier.TYPE_SP:
            case ProcIdentifier.TYPE_UDF:
                return RoutineUtil.constructCALLSPUDFString(proc, readParameterList(configuration), LaunchUI
                    .getAllParameterDescriptors(proc), quoted_id);
            case ProcIdentifier.TYPE_EVENT:
                return RoutineUtil.constructTriggerEventString(proc, readEventParameter(configuration), quoted_id);
            default:
                return "";
        }
    }

    /**
     * @param configuration
     * @return @throws CoreException
     */
    public static String constructFinalCallDetailSQLString(ILaunchConfiguration configuration) throws CoreException,
        SQLException, NoSuchProfileException
    {
        if (isAdHocSQL(configuration))
        return readAdHocSQL(configuration);
        ProcIdentifier proc = readProcIdentifier(configuration);
        if (proc == null)
        return "";
        boolean quoted_id = readQuotedIDConfig(configuration, proc);
        switch (proc.getType())
        {
            case ProcIdentifier.TYPE_SP:
            case ProcIdentifier.TYPE_UDF:
                return RoutineUtil.constructDetailCALLSPUDFString(proc, readParameterList(configuration), LaunchUI
                    .getAllParameterDescriptors(proc), quoted_id);
            case ProcIdentifier.TYPE_EVENT:
                return RoutineUtil.constructTriggerEventString(proc, readEventParameter(configuration), quoted_id);
            default:
                return "";
        }
    }

    /**
     * @param configuration
     * @param proc
     */
    public static boolean readQuotedIDConfig(ILaunchConfiguration configuration, ProcIdentifier proc)
    {
        boolean quoted_id = false;
        SQLDevToolsConfiguration factory = SQLToolsFacade.getConfiguration(proc.getDatabaseIdentifier(), null);
        IDatabaseSetting config = factory.getDatabaseSetting(proc.getDatabaseIdentifier());
        if (config != null)
        {
            try
            {
                Boolean value = (Boolean)config.getLaunchConfigProperty(configuration, IDatabaseSetting.C_QUOTED_IDENTIFIER);
                quoted_id = value.booleanValue();
            }
            catch (NotSupportedSettingException e)
            {
                RoutineEditorActivator.getDefault().log(Messages.getString("NotSupportedSettingException.cause", IDatabaseSetting.C_QUOTED_IDENTIFIER));
            }
        }
        return quoted_id;
    }

    /**
     * Sets the default values of connection level options for newly-created launch configuration
     * @param configuration
     */
    public static void setConnectionLevelOptions(ILaunchConfigurationWorkingCopy configuration)
    {
        try
        {
            //if it is firstly initialized, set it to "use default"
            if(configuration.getAttribute(ROUTINE_LAUNCH_OPTION_TYPE, -1)==-1)
            {
                configuration.setAttribute(ROUTINE_LAUNCH_OPTION_TYPE, 1);
            }
        }
        catch(CoreException ce)
        {
        	RoutineEditorActivator.getDefault().log(Messages.getString("LaunchHelper.error.readconfiguration"),ce);
        }

    }
}
