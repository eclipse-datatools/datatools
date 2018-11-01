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
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";//$NON-NLS-1$

	public static String EditorManager_operationFailed;
	
	public static String Save_Resource;
	public static String common_error;
	public static String Save;
    public static String EditorManager_saveChangesQuestion;
    public static String GroupSQLResultRunnable_group_exec;
	public static String ResultSupportRunnable_name;
	public static String ResultSupportRunnable_task_connection;
	public static String ResultSupportRunnable_task_statement;
	public static String ResultSupportRunnable_task_run;
	public static String ResultSupportRunnable_task_iterate;
	public static String ResultSupportRunnable_exception_terminated;
	public static String sqlEditorName;
	public static String GroupSQLResultRunnable_name;
	public static String GroupSQLResultRunnable_groups;
	public static String GroupSQLResultRunnable_group;
	public static String GroupSQLResultRunnable_error_interrupted;
	public static String GroupSQLResultRunnable_title;
	public static String GroupSQLResultRunnable_message;
	public static String BaseExecuteAction_job_title;
	public static String BaseExecuteAction_group_exec_title;
	public static String GettingConnection_Error;
	
	private Messages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}