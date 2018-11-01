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
package org.eclipse.datatools.sqltools.debugger.launching;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.ConnectionException;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.SQLEditorService;
import org.eclipse.datatools.sqltools.debugger.core.AutoAttachUtil;
import org.eclipse.datatools.sqltools.debugger.core.IDebugHandler;
import org.eclipse.datatools.sqltools.debugger.core.SQLDebuggerConfiguration;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerCoreUIPlugin;
import org.eclipse.datatools.sqltools.debugger.debug.ISourceLocatorProvider;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugTarget;
import org.eclipse.datatools.sqltools.debugger.model.SPThread;
import org.eclipse.datatools.sqltools.debugger.sourcelookup.SPSourceLocator;
import org.eclipse.datatools.sqltools.launching.IExtendedLaunchSupport;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.datatools.sqltools.routineeditor.result.CallableSQLResultRunnable;
import org.eclipse.datatools.sqltools.sqleditor.result.SimpleSQLResultRunnable;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.IPersistableSourceLocator;
import org.eclipse.osgi.util.NLS;

/**
 * This launch configuration delegate handles the routine debug launch mode. 
 * @author Hui Cao
 *
 */
public class SPDebuggerLaunchConfigurationDelegate implements ILaunchConfigurationDelegate
{

    private HashMap fSourceLocators;

    private SPThread createThread(DatabaseIdentifier databaseIdentifier, SPDebugTarget target, String id, Connection debuggeeCon)
        throws CoreException, DebugException, SQLException, NoSuchProfileException, ConnectionException
    {
        DatabaseVendorDefinitionId vendorId = ProfileUtil.getDatabaseVendorDefinitionId(databaseIdentifier.getProfileName());
        SQLDevToolsConfiguration factory = SQLToolsFacade.getConfigurationByProfileName(databaseIdentifier.getProfileName());
        if (factory == null || !(factory instanceof SQLDebuggerConfiguration))
        {
            throw new CoreException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID, 0, DebuggerMessages.SPLaunchConfigurationDelegate_UnsupportedServerType + vendorId, null)); //$NON-NLS-1$
        }
        String errorMessage = DebuggerMessages.SPLaunchConfigurationDelegate_UnsupportedServerType + vendorId;
        
        SPThread th = null;
        try
        {
            th = ((SQLDebuggerConfiguration)factory).getSPThread(target, databaseIdentifier, id, debuggeeCon);
        }
        catch (Exception e)
        {
            th = null;
            errorMessage = (e.getMessage() == null || e.getMessage().length() < 1) ? errorMessage : e.getMessage();
        }
                
        if (th == null)
        {
            throw new CoreException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID, 0, errorMessage, null)); //$NON-NLS-1$
        }
        return th;
    }


    /**
     * Launches the configuration.
     */
    public void launch_debug(final ILaunchConfiguration configuration, String mode, ILaunch launch,
        IProgressMonitor monitor) throws CoreException
    {
        // first create the debuggee connection and the connid (or spid)
        final Connection debuggeeCon;
        final SPThread thread;
        if (monitor != null)
        {
            monitor.beginTask(DebuggerMessages.SPLaunchConfigurationDelegate_Launching, 3); 
        }
        try
        {
            String[] id = new String[1];
            DatabaseIdentifier databaseIdentifier = LaunchHelper.readDatabaseIdentifier(configuration);

            IControlConnection controlCon = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(
                    databaseIdentifier);

            IDebugHandler debugHandler = DebuggerCoreUIPlugin.getDefault().getDebugHandlerManager()
                    .getOrCreateDebugHandler(databaseIdentifier.getProfileName());

            String readyToDebug = debugHandler.readyToDebug();
            if (readyToDebug != null)
            {
                throw new CoreException(new Status(IStatus.INFO, DebuggerCorePlugin.PLUGIN_ID, 0, readyToDebug, null)); //$NON-NLS-1$
            }

            // config the DebugTarget and IThread
            SPDebugTarget target = new SPDebugTarget(launch, null, databaseIdentifier.toString());

            Runnable postrun = null;
            String externalId = LaunchHelper.readExternalClientId(configuration);
            if (externalId != null && !externalId.equals(""))
            {
                // we are debugging an external connection.
                if (monitor != null)
                {
                    monitor.subTask(DebuggerMessages.SPLaunchConfigurationDelegate_CreatingDebugger); 
                }

                synchronized (debugHandler)
                {
                    if (debugHandler.getNumOfDebugProcess() == 3)
                    {
                        throw new CoreException(
                            new Status(
                            IStatus.INFO,
                            DebuggerCorePlugin.PLUGIN_ID,
                            0,
                            DebuggerMessages.SPDebuggerLaunchConfigurationDelegate_debug_process_number_limitation, null)); //$NON-NLS-1$
                    }
                thread = createThread(databaseIdentifier, target, externalId, null);
            }
            }
            else
            {
                // we are debugging a user specified command.
                if (monitor != null)
                {
                    monitor.subTask(DebuggerMessages.SPLaunchConfigurationDelegate_CreatingClientConn); 
                }

                // we don't want to this connection to be automatically attached. Since we need to
                // associate the connection to this ILaunch. So tell the ControlConnection not to
                // auto attach (the parameter false indicate this)
                debuggeeCon = AutoAttachUtil.createConnection(controlCon, false, id);

                //Now we get a connection.We can handle the temporary table issue here. by stephen
                IExtendedLaunchSupport extendedLaunchSupport = null;
                SQLDevToolsConfiguration config = SQLToolsFacade.getConfiguration(null, databaseIdentifier);
				SQLEditorService s = config.getSQLEditorService();
                if (s != null)
                {
                    extendedLaunchSupport = s.getExtendedLaunchSupport();
                }
                if (extendedLaunchSupport != null)
                {
                    extendedLaunchSupport.preLaunch(configuration, debuggeeCon, mode);
                }

                monitor.worked(1);

                if (monitor != null)
                {
                    monitor.subTask(DebuggerMessages.SPLaunchConfigurationDelegate_AttachingConn); 
                }

                synchronized (debugHandler)
                {
                    if (debugHandler.getNumOfDebugProcess() == 3)
                    {
                        throw new CoreException(
                            new Status(
                            IStatus.INFO,
                            DebuggerCorePlugin.PLUGIN_ID,
                            0,
                            DebuggerMessages.SPDebuggerLaunchConfigurationDelegate_debug_process_number_limitation, null)); //$NON-NLS-1$
                    }
                thread = createThread(databaseIdentifier, target, id[0], debuggeeCon);
                }

                // if is ASA event handler, we need to start auto attach

                if (!LaunchHelper.isAdHocSQL(configuration))
                {
                    ProcIdentifier proc = LaunchHelper.readProcIdentifier(configuration);
                    if (proc != null && proc.getType() == ProcIdentifier.TYPE_EVENT)
                    {
                        AutoAttachUtil.enableAutoAttach(controlCon);
                    }
                }
                //postrun = new DmpRuntime.PostRunnable(LaunchHelper.constructFinalSQLString(configuration),
                // debuggeeCon, thread);
                //FIXME: if is SP/UDF, then should use other kinds resultrunnable that can handle return value and
                // output parameters.
                ProcIdentifier proc = LaunchHelper.readProcIdentifier(configuration);

                if (proc == null)
                {
                    // ad hoc sql
                    postrun = new SimpleSQLResultRunnable(debuggeeCon, LaunchHelper
                        .constructDirectInvocationSQLString(configuration), true, thread, null, databaseIdentifier, configuration);
                }
                else
                {
                    switch (proc.getType())
                    {
                        case ProcIdentifier.TYPE_SP:
                        	postrun = config.getExecutionService().createCallableSQLResultRunnable(debuggeeCon, configuration, true, thread,
                                    databaseIdentifier);
                        	if (postrun == null)
                        	{
                        		postrun = new CallableSQLResultRunnable(debuggeeCon, configuration, true, thread,
                        				databaseIdentifier);
                        	}
                            break;
                        case ProcIdentifier.TYPE_EVENT:
                            postrun = new SimpleSQLResultRunnable(debuggeeCon, LaunchHelper
                                .constructDirectInvocationSQLString(configuration), true, thread, null, databaseIdentifier, configuration);
                            break;
                        default:
                            postrun = config.getExecutionService().createSimpleSQLResultRunnable(debuggeeCon,
                                    LaunchHelper.constructDirectInvocationSQLString(configuration), true, thread, null,
                                    databaseIdentifier, configuration, null);
                            if (postrun == null)
                            {
                                postrun = new SimpleSQLResultRunnable(debuggeeCon, LaunchHelper
                                        .constructDirectInvocationSQLString(configuration), true, thread, null,
                                        databaseIdentifier, configuration);
                            }
                    }
                }
            }

            // setting source locator.
            launch.setSourceLocator(getSourceLocator(configuration));

            target.setSPThread(thread);
            monitor.worked(1);
            launch.addDebugTarget(target);
            if (postrun != null)
            {
                new Thread(postrun).start();
            }

            //force debug handler to refresh to get the breakpoint information
            IDebugHandler handler = DebuggerCoreUIPlugin.getDefault().getDebugHandlerManager().getOrCreateDebugHandler(
                controlCon.getDatabaseIdentifier().getProfileName());
            handler.refreshExternalClients();
            if (monitor != null)
            {
                monitor.done();
            }
        }
        catch (CoreException ex)
        {
            DebugPlugin.getDefault().getLaunchManager().removeLaunch(launch);
            if (ex.getStatus().getSeverity() == IStatus.INFO)
            {
                throw new CoreException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID, 0, ex
                    .getLocalizedMessage(), null));
            }
            else
            {
                DebuggerCorePlugin.getDefault().log(DebuggerMessages.SPLaunchConfigurationDelegate_Launching, ex);
                throw new CoreException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID, 0, ex
                    .getLocalizedMessage(), ex));
            }
        }
        catch(Exception ex)
        {
        	DebuggerCorePlugin.getDefault().log(DebuggerMessages.SPLaunchConfigurationDelegate_Launching, ex);
            throw new CoreException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID, 0, ex.getLocalizedMessage(),
                ex));
        }
    }

    public void launch(final ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor)
        throws CoreException
    {
        if (ILaunchManager.DEBUG_MODE.equals(mode))
        {
            launch_debug(configuration, mode, launch, monitor);
        }
    }
    
    public IPersistableSourceLocator getSourceLocator(ILaunchConfiguration configuration) throws CoreException {
        initializeSourceLocators();
        IPersistableSourceLocator sourceLocator = null;
        for (Iterator it = fSourceLocators.values().iterator(); it.hasNext();)
        {
            ISourceLocatorProvider provider = (ISourceLocatorProvider) it.next();
            sourceLocator = provider.getSourceLocator(configuration);
            if (sourceLocator != null)
            {
                return sourceLocator;
            }
        }
        
        if (sourceLocator == null )
        {
            sourceLocator = new SPSourceLocator(); 
        }
        return sourceLocator;
    }

    private synchronized void initializeSourceLocators() {
        if (fSourceLocators  == null) {
            IExtensionPoint extensionPoint= Platform.getExtensionRegistry().getExtensionPoint(DebuggerCorePlugin.PLUGIN_ID, DebuggerCorePlugin.EXTENSION_POINT_SOURCE_LOCATORS);
            IConfigurationElement[] infos= extensionPoint.getConfigurationElements();
            fSourceLocators= new HashMap(infos.length);
            IConfigurationElement configurationElement = null;
            String id = null;
            for (int i= 0; i < infos.length; i++) {
                configurationElement = infos[i];
                id = configurationElement.getAttribute("id");          
                if (id != null) {
                    try
                    {
                        ISourceLocatorProvider provider = (ISourceLocatorProvider)configurationElement.createExecutableExtension("class");
                        fSourceLocators.put(id,provider);
                    }
                    catch (CoreException e)
                    {
                        DebuggerCorePlugin.getDefault().log(e);
                    }
                } else {
                    // invalid status handler
                    IStatus s = new Status(IStatus.ERROR, DebugPlugin.getUniqueIdentifier(), DebugException.INTERNAL_ERROR,
                    NLS.bind(DebuggerMessages.SPLaunchConfigurationDelegate_Invalid_locator, new String[] {configurationElement.getContributor().getName()} ), null);   //$NON-NLS-1$
                    DebuggerCorePlugin.getDefault().log(s);
                }
            }
        }
    }

}
