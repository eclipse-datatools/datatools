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
package org.eclipse.datatools.sqltools.routineeditor.launching;

import java.text.ParseException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifierImpl;
import org.eclipse.datatools.sqltools.core.profile.ConnectProfile;
import org.eclipse.datatools.sqltools.core.profile.ISQLToolsProfileListener;
import org.eclipse.datatools.sqltools.routineeditor.internal.RoutineEditorActivator;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

/**
 * This class keeps the SQL Dev Tools launch configuration in sync with the connection profile.
 * @author Hui Cao
 * 
 */
public class SQLToolsLaunchProfileListener implements ISQLToolsProfileListener {

	public void profileChanged(IConnectionProfile profile, String oldName,
			String oldDesc, Boolean oldAutoConnect, boolean onlyNameChanged, ConnectProfile oldProfile) {

		ILaunchConfigurationType type = LaunchHelper.getLaunchConfigType();
		try {
			// first update all launch configurations of this profile
			ILaunchConfiguration[] configs = DebugPlugin.getDefault()
					.getLaunchManager().getLaunchConfigurations(type);
			for (int i = 0; i < configs.length; i++) {
				try {
					DatabaseIdentifier databaseIdentifier = LaunchHelper
							.readDatabaseIdentifier(configs[i]);
					String pName = databaseIdentifier.getProfileName();
					if (pName.equals(oldName)) {
						// get the working copy of this configuration and modify
						// it then doSave()
						ILaunchConfigurationWorkingCopy copy = configs[i]
								.getWorkingCopy();
						String encodedProc = configs[i]
								.getAttribute(
										RoutineLaunchConfigurationAttribute.ROUTINE_LAUNCH_PROCID,
										"");
						ProcIdentifier procId = ProcIdentifierImpl
								.decodeWithNewProfile(encodedProc, profile
										.getName());
						copy
								.setAttribute(
										RoutineLaunchConfigurationAttribute.ROUTINE_LAUNCH_PROCID,
										procId.encode());
						copy
								.setAttribute(
										RoutineLaunchConfigurationAttribute.ROUTINE_LAUNCH_PROFILENAME,
										profile.getName());
						copy.doSave();
					}
				} catch (ParseException pe) {
					RoutineEditorActivator.getDefault().log(pe);
				} catch (CoreException ce) {
					/*
					 * This error may be caused by the eclipse,there is a
					 * configuration named
					 * "org.eclipse.datatools.sqltools.routineeditor.launching.launchConfigurationType.SHARED_INFO"
					 * which is created by eclipse automatically we can not read
					 * the profile name from this configuration,if we do, then
					 * error occurs.
					 */
				}
			}

		} catch (CoreException ce) {
			RoutineEditorActivator.getDefault().log(ce);
		}

	}

	public void profileAdded(IConnectionProfile profile) {
		// TODO Auto-generated method stub

	}

	public void profileDeleted(IConnectionProfile profile) {
		ILaunchConfigurationType type = LaunchHelper.getLaunchConfigType();
		try {
			// first delete all launch configurations of this profile
			ILaunchConfiguration[] configs = DebugPlugin.getDefault()
					.getLaunchManager().getLaunchConfigurations(type);
			for (int i = 0; i < configs.length; i++) {
				try {
					DatabaseIdentifier databaseIdentifier = LaunchHelper
							.readDatabaseIdentifier(configs[i]);

					String pName = databaseIdentifier.getProfileName();
					if (pName.equals(profile.getName())) {
						configs[i].delete();
					}
				} catch (CoreException ce) {
					/*
					 * This error may be caused by the eclipse,there is a
					 * configuration named
					 * "org.eclipse.datatools.sqltools.routineeditor.launching.launchConfigurationType.SHARED_INFO"
					 * which is created by eclipse automatically we can not read
					 * the profile name from this configuration,if we do, then
					 * error occurs.
					 */
				}
			}

		} catch (CoreException ce) {
			RoutineEditorActivator.getDefault().log(ce);
		}

	}

}
