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

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.routineeditor.ui.launching.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String ConnectionLevelOptionsTab_10;
    public static String ConnectionLevelOptionsTab_9;
    public static String ConnectionLevelOptionsTab_maintab;
    public static String ConnectionLevelOptionsTab_no_page_defined;
    public static String ConnectionLevelOptionsTab_options;
    public static String ConnectionLevelOptionsTab_use_default;
    public static String ConnectionLevelOptionsTab_user_customize;
    public static String SPLaunchShortcut_error;
	public static String SPLaunchShortcut_selectNotSP;
	public static String SPLaunchShortcut_selectNotEnableEvent;
	public static String SPLaunchShortcut_launchFailMessage;
	public static String SPLaunchShortcut_notStoreProcedure;
	public static String LaunchUI_selectSP;
	public static String LaunchUI_selectSPMessage;
	public static String LaunchUI_selectUDF;
	public static String LaunchUI_selectUDFMessage;
	public static String LaunchUI_selectEVENT;
	public static String LaunchUI_selectEVENTMessage;
	public static String LaunchUI_selectTRIGGER;
	public static String LaunchUI_selectTRIGGERMessage;
	public static String LaunchUI_selectProfileMessage;
	public static String LaunchUI_selectProfile;
	public static String RoutineMainTab_status_invalid_profile;
	public static String RoutineMainTab_status_invalid_dbName;
	public static String RoutineMainTab_sql;
	public static String RoutineMainTab_status_function;
	public static String RoutineMainTab_invalid_size;
	public static String RoutineMainTab_name;
	public static String RoutineMainTab_profile;
	public static String RoutineMainTab_dbname;
	public static String RoutineMainTab_profile_browse;
	public static String RoutineMainTab_run_browse;
	public static String RoutineMainTab_type;
	public static String RoutineMainTab_storedprocedure;
	public static String RoutineMainTab_udf;
	public static String RoutineMainTab_eventhandler;
	public static String RoutineMainTab_adhoc;
	public static String RoutineMainTab_trigger;
	public static String RoutineMainTab_objectname;
	public static String RoutineMainTab_msg_error;
	public static String RoutineMainTab_parameters;
	public static String RoutineMainTab_configparam;
	public static String RoutineMainTab_msg_error_metadata;
	public static String RoutineMainTab_status_event;
	public static String RoutineMainTab_status_sp;
	public static String RoutineMainTab_status_adhoc;
	public static String RoutineMainTab_status_trigger_noselect;
	public static String RoutineMainTab_status_trigger;
	public static String RoutineMainTab_sql_unimplemented_title;
	public static String RoutineMainTab_sql_unimplemented_msg;
    public static String pasteSQL;
    public static String pasteSQL_select;
    public static String pasteSQL_select_label;
    public static String pasteSQL_select_tooltip;
    public static String pasteSQL_insert;
    public static String pasteSQL_insert_label;
    public static String pasteSQL_insert_tooltip;
    public static String pasteSQL_delete;
    public static String pasteSQL_delete_label;
    public static String pasteSQL_delete_tooltip;
    public static String pasteSQL_update;
    public static String pasteSQL_update_label;
    public static String pasteSQL_update_tooltip;
    public static String LaunchingJob_runError;
    public static String LaunchingJob_name;
    public static String LaunchingJob_subTaskRun;
    public static String LaunchingJob_subTaskDebug;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}