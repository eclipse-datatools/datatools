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
package org.eclipse.datatools.sqltools.routineeditor.ui.launching;

import java.sql.Connection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.core.services.SQLEditorService;
import org.eclipse.datatools.sqltools.launching.IExtendedLaunchSupport;
import org.eclipse.datatools.sqltools.routineeditor.internal.RoutineEditorActivator;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.datatools.sqltools.routineeditor.launching.Messages;
import org.eclipse.datatools.sqltools.routineeditor.launching.SPDebugTarget_Run;
import org.eclipse.datatools.sqltools.routineeditor.result.CallableSQLResultRunnable;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.launching.internal.SaveRoutineStatusHandler;
import org.eclipse.datatools.sqltools.sqleditor.result.SimpleSQLResultRunnable;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

/**
 * This is for launch stored procedure or trigger or udf etc. This delegate can
 * launch using two different way: 1. the normal one. the launcher will create a
 * thread to run the SQL command. 2. attach an external connection. The launcher
 * will attach to the external connection by connectionid (ASA) or spid (ASE).
 * There is a special attribute for the LaunchConfiguration:
 * ROUTINE_LAUNCH_EXTERNALID. When this attribute is set, means that is
 * debugging external client.
 * 
 * @author Yang Liu
 */
public class SPLaunchConfigurationDelegate implements
		ILaunchConfigurationDelegate {

	public void launch_run(final ILaunchConfiguration configuration,
			String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {
		// first create the debuggee connection and the connid (or spid)
		final Connection debuggeeCon;
		if (monitor != null) {
			monitor.beginTask(Messages.SPLaunchConfigurationDelegate_Launching, 3); //$NON-NLS-1$
		}
		try {
			Runnable postrun = null;
			// String profileName = LaunchHelper.readProfileName(configuration);
			DatabaseIdentifier databaseIdentifier = LaunchHelper
					.readDatabaseIdentifier(configuration);

			String externalId = LaunchHelper
					.readExternalClientId(configuration);
			if (externalId != null && !externalId.equals("")) {
				return; // impossible for run mode to have external id.
			} else {
				// we are debugging a user specified command.
				if (monitor != null) {
					monitor
							.subTask(Messages.SPLaunchConfigurationDelegate_CreatingClientConn); //$NON-NLS-1$
				}

				IControlConnection controlCon = EditorCorePlugin
						.getControlConnectionManager()
						.getOrCreateControlConnection(databaseIdentifier);

				// we don't want to this connection to be automatically
				// attached. Since we need to
				// associate the connection to this ILaunch. So tell the
				// ControlConnection not to
				// auto attach (the parameter false indicate this)
				// debuggeeCon = AutoAttachUtil.createConnection(controlCon,
				// false, id);
				SQLDevToolsConfiguration f = SQLToolsFacade
						.getConfigurationByProfileName(databaseIdentifier
								.getProfileName());
				ConnectionService conService = f.getConnectionService();
				debuggeeCon = conService.createConnection(controlCon
						.getDatabaseIdentifier().getProfileName(), controlCon
						.getDatabaseIdentifier().getDBname());

				// Now we get a connection.We can handle the temporary table
				// issue here. by stephen
				IExtendedLaunchSupport extendedLaunchSupport = null;
				SQLEditorService s = SQLToolsFacade.getConfiguration(
						null, databaseIdentifier).getSQLEditorService();
				if (s != null) {
					extendedLaunchSupport = s.getExtendedLaunchSupport();
				}
				if (extendedLaunchSupport != null) {
					extendedLaunchSupport.preLaunch(configuration, debuggeeCon,
							mode);
				}

				monitor.worked(1);

				if (monitor != null) {
					monitor
							.subTask(Messages.SPLaunchConfigurationDelegate_AttachingConn); //$NON-NLS-1$
				}

				SPDebugTarget_Run target = new SPDebugTarget_Run(launch, null,
						databaseIdentifier.getProfileName(), debuggeeCon);

				// Runnable postrun = new
				// DmpRuntime.PostRunnable(LaunchHelper.constructFinalSQLString(configuration),
				// debuggeeCon, target);
				ProcIdentifier proc = LaunchHelper
						.readProcIdentifier(configuration);

				if (proc == null) {
					// proc is null means Ad Hoc SQL.
					postrun = f.getExecutionService()
					.createAdHocScriptRunnable(debuggeeCon,
							LaunchHelper.constructDirectInvocationSQLString(configuration),
							true, target, null, databaseIdentifier,
							configuration, null);
					if (postrun == null) {
						postrun = new SimpleSQLResultRunnable(
								debuggeeCon,
								LaunchHelper.constructDirectInvocationSQLString(configuration),
								true, target, null, databaseIdentifier,
								configuration);
					}
				} else {
					switch (proc.getType()) {
					case ProcIdentifier.TYPE_SP:
						postrun = f.getExecutionService()
								.createStoredProcedureRunnable(debuggeeCon,
										configuration, true, target,
										databaseIdentifier);
						if (postrun == null) {
							postrun = new CallableSQLResultRunnable(
									debuggeeCon, configuration, true, target,
									databaseIdentifier);
						}
						break;
					case ProcIdentifier.TYPE_UDF:
						postrun = f.getExecutionService()
						.createFunctionRunnable(debuggeeCon,
								LaunchHelper.constructDirectInvocationSQLString(configuration),
								true, target, null, databaseIdentifier,
								configuration, null);
						if (postrun == null) {
							postrun = new SimpleSQLResultRunnable(
									debuggeeCon,
									LaunchHelper.constructDirectInvocationSQLString(configuration),
									true, target, null, databaseIdentifier,
									configuration);
						}
						break;
					case ProcIdentifier.TYPE_EVENT:
					default:
						postrun = f.getExecutionService()
						.createAdHocScriptRunnable(debuggeeCon,
								LaunchHelper.constructDirectInvocationSQLString(configuration),
								true, target, null, databaseIdentifier,
								configuration, null);
						if (postrun == null) {
							postrun = new SimpleSQLResultRunnable(
									debuggeeCon,
									LaunchHelper.constructDirectInvocationSQLString(configuration),
									true, target, null, databaseIdentifier,
									configuration);
						}

					}
				}

				monitor.worked(1);
				launch.addDebugTarget(target);
				new Thread(postrun).start();
			}

			if (monitor != null) {
				monitor.done();
			}
		} catch (Exception ex) {
			RoutineEditorUIActivator.getDefault().log(ex);
			throw new CoreException(new Status(IStatus.ERROR,
					RoutineEditorUIActivator.PLUGIN_ID, 0, ex
							.getLocalizedMessage(), ex));
		}

	}

	public void launch(final ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		//try to connect first
        try
        {
            DatabaseIdentifier databaseIdentifier = LaunchHelper.readDatabaseIdentifier(configuration);
            if (ProfileUtil.connectProfile(databaseIdentifier.getProfileName()) == null)
            {
                return;
            }
        }
        catch (Exception e)
        {
            return;
        }
	    
		try {
			//if this launch is from launch shortcut, then no need to prompt again
			if (launch.getAttribute(SPLaunchShortcut.LAUNCH_VIA_SHORTCUT) == null)
			{
				DatabaseIdentifier databaseIdentifier = LaunchHelper
				.readDatabaseIdentifier(configuration);
				IConnectionProfile profile = ProfileUtil.getProfile(databaseIdentifier.getProfileName());
				IStatusHandler handler = new SaveRoutineStatusHandler();
				//pass in profile instead of ProcIdentifier because for trigger, there's no procidentifier in launch
				Boolean status = (Boolean)handler.handleStatus(SPLaunchShortcut.PROMPT_STATUS, profile);
				if (!status.booleanValue())
				{
					return;
				}
			}
		} catch (Exception e) {
			RoutineEditorUIActivator.getDefault().log(e);
		}
		
		if (ILaunchManager.RUN_MODE.equals(mode)) {
			launch_run(configuration, mode, launch, monitor);
		} else {
			boolean supportsDebugging = false;
			try {
				DatabaseIdentifier databaseIdentifier = LaunchHelper
						.readDatabaseIdentifier(configuration);
				IControlConnection controlCon = EditorCorePlugin
						.getControlConnectionManager()
						.getOrCreateControlConnection(databaseIdentifier);
				supportsDebugging = controlCon.supportsDebugging();
			} catch (Exception e) {
				RoutineEditorUIActivator.getDefault().log(e);
			}

			if (supportsDebugging) {
				IExtensionRegistry pluginRegistry = Platform
						.getExtensionRegistry();
				IExtensionPoint extensionPoint = pluginRegistry
						.getExtensionPoint(RoutineEditorActivator.PLUGIN_ID,
								"launchConfigurationTypes"); //$NON-NLS-1$ //$NON-NLS-2$
				IExtension[] extensions = extensionPoint.getExtensions();
				if (extensionPoint != null) {
					for (int i = 0; i < extensions.length; ++i) {
						IConfigurationElement[] configElements = extensions[i]
								.getConfigurationElements();
						for (int j = 0; j < configElements.length; ++j) {
							if (configElements[j].getName().equals(
									"launchConfigurationType")) {
								//$NON-NLS-1$
								String modesStr = configElements[j]
										.getAttribute("modes"); //$NON-NLS-1$
								String[] modes = modesStr.split(",");
								for (int k = 0; k < modes.length; k++) {
									if (mode.equals(modes[k])) {
										// String className =
										// configElements[j].getAttribute("delegate");
										try {
											ILaunchConfigurationDelegate delegate = (ILaunchConfigurationDelegate) configElements[j]
													.createExecutableExtension("delegate"); //$NON-NLS-1$
											delegate.launch(configuration,
													mode, launch, monitor);
											return;
										} catch (CoreException e) {
											RoutineEditorUIActivator.getDefault()
													.log(e);
											final CoreException e1 = e;
											PlatformUI.getWorkbench()
													.getDisplay().asyncExec(
															new Runnable() {
																public void run() {
																	MessageDialog
																			.openError(
																					PlatformUI
																							.getWorkbench()
																							.getActiveWorkbenchWindow()
																							.getShell(),
																					Messages.common_error,
																					e1
																							.getMessage());
																}
															});
											return;
										}
									}
								}

							}
						}
					}
				}
			}
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					// no contributions to this mode
					MessageDialog
							.openError(
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getShell(),
									Messages.common_error,
									Messages.SPLaunchConfigurationDelegate_notsupported);
				}
			});
		}
	}
}
