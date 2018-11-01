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
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String SelectProfileDialog_title;
	public static String SelectProfileDialog_create;
	public static String SelectProfileDialog_warning;
	public static String SelectProfileDialog_error_jdbc_title;
	public static String SelectProfileDialog_profile_name;
	public static String SelectProfileDialog_profile_type;
	public static String SelectProfileDialog_donot_connect;
	public static String SelectProfileDialog_error_jdbc_message;
	public static String SelectProfileGroup_error_init;
	public static String SelectProfileDialog_noprofile;
	public static String SelectProfileDialog_wrongprofile;
	public static String ConnectionInfoGroup_profile;
	public static String ConnectionInfoGroup_profile_group;
	public static String ConnectionInfoGroup_name;
	public static String ConnectionInfoGroup_database;
	public static String ConnectionInfoGroup_status;
	public static String ConnectionInfoGroup_type;
	public static String ConnectionInfoGroup_status_connected;
	public static String ConnectionInfoGroup_status_disconnected;
    public static String ConnectionInfoGroup_status_autocommit;
    public static String ConnectionInfoGroup_status_manualcommit;
	public static String ConnectionInfoGroup_database_name;
	public static String SelectProfileDialog_override_profile;
	public static String FilesConnectionInfoDialog_column_name;
    public static String FilesConnectionInfoDialog_column_type;
    public static String FilesConnectionInfoDialog_column_profile;
    public static String FilesConnectionInfoDialog_column_database;
    public static String ConnectionInfoDialog_title_for_file;
    public static String ConnectionInfoDialog_file_conn_info;
    public static String FileExecution_job_name;
    public static String SELECT_ALL_TITLE;
    public static String DESELECT_ALL_TITLE;
    public static String UP_TITLE;
    public static String DOWN_TITLE;
    public static String SelectProfileDialog_override_profile_tooltip;

    static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}