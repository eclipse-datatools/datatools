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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.launching.internal.SaveRoutineStatusHandler;
import org.eclipse.datatools.sqltools.routineeditor.util.RoutineUtil;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchGroup;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * This is launch shortcut.
 * 
 * @author Yang Liu
 */
public class SPLaunchShortcut implements ILaunchShortcut {

	public static final IStatus PROMPT_STATUS = new Status(IStatus.INFO, RoutineEditorUIActivator.PLUGIN_ID, 200, "", null);  //$NON-NLS-1$//$NON-NLS-2$
	public static final String LAUNCH_VIA_SHORTCUT = "LAUNCH_VIA_SHORTCUT";
	
	public static class SPLaunchListener implements ILaunchListener {
		ILaunchConfiguration config = null;
		public SPLaunchListener(ILaunchConfiguration config)
		{
			this.config = config;
		}
		public void launchAdded(ILaunch launch) {
			if (config != null && config.equals(launch.getLaunchConfiguration()))
			{
				launch.setAttribute(LAUNCH_VIA_SHORTCUT, "true");
				DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(this);
			}
		}

		public void launchChanged(ILaunch launch) {

		}

		public void launchRemoved(ILaunch launch) {

		}
	}
	
	/**
	 * Gets a launch configuration to run the specified proc. It may use
	 * existing configuration, or create new configuration.
	 * 
	 * @param proc
	 * @return
	 * @throws NoSuchProfileException
	 * @throws SQLException
	 * @throws CoreException
	 */
	public static ILaunchConfiguration getLaunchConfiguration(
			ProcIdentifier proc, String mode) throws CoreException,
			SQLException, NoSuchProfileException {
		
		IStatusHandler handler = new SaveRoutineStatusHandler();
		Boolean status = (Boolean)handler.handleStatus(PROMPT_STATUS, proc);
		if (!status.booleanValue())
		{
			return null;
		}
		
		ILaunchConfigurationType type = LaunchHelper.getLaunchConfigType();
		List candidates = Collections.EMPTY_LIST;
		try {
			ILaunchConfiguration[] configs = DebugPlugin.getDefault()
					.getLaunchManager().getLaunchConfigurations(type);
			candidates = new ArrayList(configs.length);
			for (int i = 0; i < configs.length; i++) {
				if (isSameProc(proc, configs[i])) {
					candidates.add(configs[i]);
				}
			}
		} catch (CoreException ex) {
			RoutineEditorUIActivator.getDefault().log(ex);
		}

		int candidateSize = candidates.size();
		if (candidateSize == 0) {
			// non found, need to create one
			return createConfiguration(proc, mode);
		} else if (candidateSize == 1) {
			// only one found, good, we'll use it.
			return modifyConfiguration(proc, (ILaunchConfiguration) candidates
					.get(0));
		} else {
			// more than one, what we do?
			ILaunchConfiguration configuration = chooseConfiguration(candidates);
			if (configuration != null) {
				return modifyConfiguration(proc, configuration);
			} else {
				return null;
			}
		}
	}

	/**
	 * Lets user choose a launch configuration from a list.
	 * 
	 * @param candidates
	 *            must with size > 1
	 * @return null means user canceled.
	 */
	private static ILaunchConfiguration chooseConfiguration(List candidates) {
		// FIXME:
		return (ILaunchConfiguration) candidates.get(0);
	}

	/**
	 * If the launch configuration require parameter, we may popup a dialog to
	 * give user the chance to change parameters.
	 * 
	 * @param proc
	 * @param configuration
	 * @return
	 */
	private static ILaunchConfiguration modifyConfiguration(
			final ProcIdentifier proc, final ILaunchConfiguration configuration)
			throws CoreException, SQLException, NoSuchProfileException {
		boolean iscopy = configuration.isWorkingCopy();
		boolean quoted_id = LaunchHelper.readQuotedIDConfig(configuration, proc);
		String sql = "";
		if (proc.getType() == ProcIdentifier.TYPE_SP
				|| proc.getType() == ProcIdentifier.TYPE_UDF) {
			final ParameterDescriptor[] pds = LaunchHelper.getParameterDescriptors(proc);
			List valueList = LaunchHelper.readParameterList(configuration);

            final LaunchObject launchObject = new LaunchObject();
            final List valueList1 = LaunchHelper.readParameterList(configuration);

            Display.getDefault().syncExec(new Runnable()
            {

                public void run()
                {
                    launchObject.setConfigParameter(LaunchUI.configParameter(getShell(), pds, valueList1, false, configuration));

                }
            }
            );

            List newValues = launchObject.getConfigParameter();
            
			if (newValues != null) {
				valueList = newValues;
				ILaunchConfigurationWorkingCopy copy;
				if (iscopy) {
					copy = (ILaunchConfigurationWorkingCopy) configuration;
				} else {
					copy = configuration.getWorkingCopy();
				}

				sql = RoutineUtil.constructSPUDFDirectInvocationString(proc, valueList,pds, quoted_id);
				LaunchHelper.saveSPUDF(copy, proc, valueList, sql);
				if (iscopy)
					return copy;
				else
					return copy.doSave();
			} else {
				return null;
			}
		} else if (proc.getType() == ProcIdentifier.TYPE_EVENT) {
			// TODO get parameters from sql model Routine
			final String[] pNames = new String[] {};
			final Map eventTriggerParams = LaunchHelper
					.readEventParameter(configuration);

            final LaunchObject launchObject = new LaunchObject();
            Display.getDefault().syncExec(new Runnable()
            {

                public void run()
                {
                    launchObject.setConfigEventParameters(LaunchUI.configEventParameters(getShell(), pNames, eventTriggerParams,proc));

                }
            }
            );
            
			if (launchObject.hasConfigEventParameters()) {
				ILaunchConfigurationWorkingCopy copy;
				if (iscopy) {
					copy = (ILaunchConfigurationWorkingCopy) configuration;
				} else {
					copy = configuration.getWorkingCopy();
				}
				
				sql = RoutineUtil.constructTriggerEventString(proc, LaunchHelper.readEventParameter(configuration), quoted_id);
				LaunchHelper.saveEvent(copy, proc, eventTriggerParams, sql);

				if (iscopy)
					return copy;
				else
					return copy.doSave();
			} else {
				return null;
			}
		} else {
			return configuration; // should not happen.
		}
	}

	/**
	 * Create a new ILaunchConfiguration to run the specified proc
	 * 
	 * @param proc
	 * @return
	 * @throws NoSuchProfileException
	 * @throws SQLException
	 * @throws CoreException
	 */
	private static ILaunchConfiguration createConfiguration(
			ProcIdentifier proc, String mode) throws CoreException,
			SQLException, NoSuchProfileException {
		ILaunchConfiguration config = null;
		ILaunchConfigurationWorkingCopy wc = null;
		try {
			ILaunchConfigurationType configType = LaunchHelper
					.getLaunchConfigType();
			wc = configType.newInstance(null, DebugPlugin.getDefault()
					.getLaunchManager()
					.generateUniqueLaunchConfigurationNameFrom(
							proc.getProcName()));
		} catch (CoreException exception) {
			RoutineEditorUIActivator.getDefault().log(exception); //$NON-NLS-1$
			return null;
		}

		LaunchHelper.initializeConfiguration(wc, proc);

		if (proc.getType() == ProcIdentifier.TYPE_EVENT
				|| proc.getType() == ProcIdentifier.TYPE_SP
				|| proc.getType() == ProcIdentifier.TYPE_UDF) {
			// only for those event/sp/udf that support parameters, we popup
			// dialog for modify them.
			if (modifyConfiguration(proc, wc) != null) {
				try {
					return wc.doSave();
				} catch (CoreException exception) {
					RoutineEditorUIActivator.getDefault().log(exception); //$NON-NLS-1$
					return null;
				}
			} else {
				return null;
			}
		} else {
			config = wc.doSave();

			// XXX: need revisit. We let the openLaunchConfigurationDialog
			// method to handle
			// both modifying the launch configuration and launch.
            ILaunchGroup group = DebugUITools.getLaunchGroup(config, mode);
            final String groupId = group==null?"":group.getIdentifier(); //$NON-NLS-1$
            final ILaunchConfiguration configuration = config;
            Display.getDefault().syncExec(new Runnable()
            {

                public void run()
                {
                    DebugUITools.openLaunchConfigurationDialog(getShell(), configuration, groupId, null);

                }
            }
            );

            
			return null;

		}

	}

	/**
	 * @param proc
	 * @param configuration
	 * @return
	 */
	private static boolean isSameProc(ProcIdentifier proc,
			ILaunchConfiguration configuration) {
		try {
			if (!LaunchHelper.isAdHocSQL(configuration)) {
				return proc.equals(LaunchHelper
						.readProcIdentifier(configuration));
			}

			return false; // FIXME: FIXME: FIXME: Not done
		} catch (CoreException ex) {
			return false;
		}
	}

	/**
	 * @param proc
	 * @param mode
	 * @throws NoSuchProfileException
	 * @throws SQLException
	 * @throws CoreException
	 */
	public static void launch(ProcIdentifier proc, String mode) throws CoreException, SQLException,
            NoSuchProfileException
    {
        final ILaunchConfiguration config = getLaunchConfiguration(proc, mode);
        final String spmode = mode;
        final ProcIdentifier identifier = proc;
        // If config is null, just return for avoiding NullPointException.
        if (config == null)
        {
            return;
        }

        Display.getDefault().asyncExec(new Runnable()
        {

            public void run()
            {
            	DebugPlugin.getDefault().getLaunchManager().addLaunchListener(new SPLaunchListener(config));
                if (identifier.getType() == ProcIdentifier.TYPE_TRIGGER)
                {
                    ILaunchGroup group = DebugUITools.getLaunchGroup(config, spmode);
                    String groupId = group == null ? "" : group.getIdentifier(); //$NON-NLS-1$
                    DebugUITools.openLaunchConfigurationDialog(getShell(), config, groupId, null);
                    return;
                }
                DebugUITools.launch(config, spmode);

            }
        });
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchShortcut#launch(org.eclipse.jface.viewers.ISelection,
	 *      java.lang.String)
	 */
	public void launch(ISelection selection, String mode) {
		if (selection instanceof IStructuredSelection) {
			Object first = ((IStructuredSelection) selection).getFirstElement();
			ProcIdentifier proc = null;
			if (first instanceof ProcIdentifier) {
				proc = (ProcIdentifier) first;
			} else if (first instanceof Routine || first instanceof Trigger) {
				proc = SQLDevToolsUtil.getProcIdentifier((SQLObject) first);
			}

			if (proc == null) {
				MessageDialog
						.openError(
								getShell(),
								Messages.SPLaunchShortcut_error, Messages.SPLaunchShortcut_selectNotSP); 
				return;
			}

			try {
				launch(proc, mode);
			} catch (Exception ex) {
				MessageDialog
						.openError(
								getShell(),
								Messages.SPLaunchShortcut_error, NLS.bind(Messages.SPLaunchShortcut_launchFailMessage, (new Object[]{ex.getMessage()}))); 
			}

		} else {
			MessageDialog
					.openError(
							getShell(),
							Messages.SPLaunchShortcut_error, Messages.SPLaunchShortcut_selectNotSP); 
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchShortcut#launch(org.eclipse.ui.IEditorPart,
	 *      java.lang.String)
	 */
	public void launch(IEditorPart editor, String mode) {
		IEditorInput input = editor.getEditorInput();
		if (input instanceof ProcEditorInput) {
			ProcEditorInput procinput = (ProcEditorInput) input;
			ProcIdentifier proc = procinput.getProcIdentifier();
			try {
				launch(proc, mode);
			} catch (Exception ex) {
				MessageDialog
						.openError(
								getShell(),
								Messages.SPLaunchShortcut_error, NLS.bind(Messages.SPLaunchShortcut_launchFailMessage, (new Object[]{ex.getMessage()}))); 
			}
		} else {
			MessageDialog
					.openError(
							getShell(),
							Messages.SPLaunchShortcut_error, Messages.SPLaunchShortcut_notStoreProcedure); 
		}
	}

	protected static Shell getShell() {
		IWorkbenchWindow w = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (w != null)
			return w.getShell();
		else
			return null;
	}
}
