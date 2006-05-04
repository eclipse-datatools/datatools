/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	public static String ConnectionInfoGroup_database_name;
}