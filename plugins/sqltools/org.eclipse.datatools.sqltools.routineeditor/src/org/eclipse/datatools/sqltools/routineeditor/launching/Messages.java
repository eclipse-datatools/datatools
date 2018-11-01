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

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.routineeditor.launching.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String common_error;
	public static String NotSupportedSettingException_cause;
	public static String LaunchHelper_invalid_launch_configuration;
	public static String LaunchHelper_error_readconfiguration;
	public static String SPDebugTarget_Run_notSupported;
	public static String SPDebugTarget_Run_debug_exception;
	public static String SPLaunchConfigurationDelegate_Launching;
	public static String SPLaunchConfigurationDelegate_UnsupportedServerType;
	public static String SPLaunchConfigurationDelegate_notsupported;
	public static String SPLaunchConfigurationDelegate_CreatingClientConn;
	public static String SPLaunchConfigurationDelegate_AttachingConn;
	public static String SPLaunchConfigurationDelegate_NoPermission;
	public static String SPLaunchConfigurationDelegate_CreatingDebugger;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}