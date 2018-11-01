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
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.sqleditor.internal.actions.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String AddTemplateAction_error_write_title;
	public static String AddTemplateAction_error_write_message;
    public static String AddTemplateAction_label;
    public static String AddTemplateAction_tooltip;
    public static String BaseExecuteAction_group_exec_title;
	public static String common_error;
	public static String CutAction_label;
	public static String CutAction_tooltip;
	public static String CopyAction_label;
	public static String CopyAction_tooltip;
	public static String CopyToClipboard_error_title;
	public static String CollectionUtil_invalid_size;
	public static String CopyToClipboard_error_message;
	public static String DeleteAction_label;
	public static String DeleteAction_0;
	public static String DeleteAction_tooltip;
	public static String DeleteAction_confirm_title;
	public static String DeleteAction_confirm_message;
	public static String PasteAction_label;
	public static String PasteAction_1;
	public static String PasteAction_tooltip;
	public static String ExecuteSQLAction_label;
	public static String ExecuteSQLAction_tooltip;
	public static String ExecuteSelectionSQLAction_label;
	public static String ExecuteSelectionSQLAction_tooltip;
	public static String ExecuteCurrentSQLAction_label;
	public static String ExecuteCurrentSQLAction_tooltip;
	public static String ExecuteSQLAsOneStatement_label;
	public static String ExecuteSQLAsOneStatement_tooltip;
	public static String GotoMatchingTokenAction_label;
	public static String GotoMatchingTokenAction_tooltip;
	public static String DMLDialogSelectionSQLAction_label;
	public static String DMLDialogSelectionSQLAction_tooltip;
	public static String RunAction_label;
	public static String RunAction_tooltip;
	public static String RunAction_runError;
	public static String DebugAction_label;
	public static String DebugAction_tooltip;
	public static String DebugAction_runError;
	public static String ExplainSQLAction_label;
	public static String ExplainSQLAction_tooltip;
	public static String ExplainSQLActionDelegate_error_initview;
	public static String ExplainSQLActionDelegate_error_proc_qp;
	public static String ExecuteSQLActionDelegate_error_execute;
	public static String ExecuteSQLActionDelegate_error_profile;
	public static String ExecuteSQLActionDelegate_error_initview;
	public static String ExecuteSQLActionDelegate_error_interrupted;
	public static String ToggleComment_error_title;
	public static String ToggleComment_error_message;
	public static String DeployAction_label;
	public static String DeployAction_error_title;
	public static String DeployAction_error_message;
	public static String DeployAction_save_information;
	public static String DeployAction_save_message;
	public static String BaseExecuteAction_job_title;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}