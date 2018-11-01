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
package org.eclipse.datatools.sqltools.debugger.core.internal;

import org.eclipse.osgi.util.NLS;

public final class DebuggerMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.debugger.core.internal.messages";//$NON-NLS-1$

	private DebuggerMessages() {
		// Do not instantiate
	}

	public static String plugin_internal_error;
	public static String RowsView_name;
	public static String RowsView_errorWhenUpdate;
	public static String GlobalVariablesLabelProvider_unknown;
	public static String DisconnectAction_label;
	public static String DisconnectAction_fail;
	public static String DisconnectAction_disconnectFailInuse;
	public static String DebugAttachAction_label;
	public static String DebugAttachAction_error;
	public static String DebugAttachAction_attachFailMessage;
	public static String DebugDetachAction_label;
	public static String RefreshAction_label;
	public static String SPBreakpointPage_error;
	public static String SPLaunchShortcut_error;
	public static String SPBreakpointPage_errorSaving;
	public static String SPLaunchShortcut_selectNotSP;
	public static String SPLaunchShortcut_selectNotEnableEvent;
	public static String SPBreakpointPage_procedureName;
	public static String SPLineBreakpoint_invalidProcIdentifier;
	public static String SPBreakpointPage_lineNumber;
	public static String SPDebugElement_notSupported;
	public static String SPDebugTarget_Run_notSupported;
	public static String SPBreakpointPage_enabledCondition;
	public static String SPBreakpointPropertiesRulerAction_label;
	public static String SPBreakpointPage_failed_to_create_labels;
	public static String SPBreakpointPage_failed_to_create_page;
	public static String SPBreakpointPage_enabled;
	public static String SPLaunchShortcut_launchFailMessage;
	public static String SPLaunchShortcut_notStoreProcedure;
	public static String SPLocalVariable_threadNotSuspended;
	public static String ManageBreakpointRulerAction_label;
	public static String ManageBreakpointRulerAction_error;
	public static String ManageBreakpointRulerAction_fail;
	public static String EnableDisableBreakpointRulerAction_enableBreakpoint;
	public static String EnableDisableBreakpointRulerAction_error;
	public static String EnableDisableBreakpointRulerAction_enableFailed;
	public static String EnableDisableBreakpointRulerAction_disableBreakpoint;
	public static String SPWatchExpressionDelegate_error;
	public static String SPWatchExpressionDelegate_unsupported;
	public static String AbstractControlConnection_connection_already_being_debugged;
	public static String AbstractControlConnection_invalid_store_procedure_description;
	public static String SPLaunchConfigurationDelegate_Launching;
	public static String SPLaunchConfigurationDelegate_UnsupportedServerType;
	public static String SPLaunchConfigurationDelegate_notsupported;
	public static String SPLaunchConfigurationDelegate_CreatingClientConn;
	public static String SPLaunchConfigurationDelegate_AttachingConn;
	public static String SPLaunchConfigurationDelegate_NoPermission;
	public static String SPLaunchConfigurationDelegate_CreatingDebugger;
	public static String SPLaunchConfigurationDelegate_Invalid_locator;
	public static String perspective_DebuggerPerspective_activeWinNull1;
	public static String perspective_DebuggerPerspective_activeWinNull2;
	public static String perspective_DebuggerPerspective_changePerspectiveException;
	public static String SPVariable_unknown;
	public static String ToggleBreakpointAdapter_canToggleLineBreakpoints_title;
	public static String ToggleBreakpointAdapter_canToggleLineBreakpoints_message;
	public static String ToggleBreakpointAdapter_canToggleLineBreakpoints_toggleMessage;
	public static String AutoAttachUtil_information;
	public static String AutoAttachUtil_auto_attach_for_profile;
	public static String AutoAttachUtil_enabled_by_system;
	public static String ManageBreakpointRulerAction_exception_getControlConnection;
	public static String SPDebuggerLaunchConfigurationDelegate_debug_process_number_limitation;

	static {
		NLS.initializeMessages(BUNDLE_NAME, DebuggerMessages.class);
	}
}