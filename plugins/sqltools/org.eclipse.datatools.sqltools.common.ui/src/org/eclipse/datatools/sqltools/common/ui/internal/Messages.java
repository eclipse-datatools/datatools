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
package org.eclipse.datatools.sqltools.common.ui.internal;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String AbstractDBPreferenceFieldPage_nodb;
    public static String SaveAsDialog_error;
    public static String SaveAsDialog_error_msg;
    public static String SaveAsDialog_message;
    public static String SaveAsDialog_title;
    public static String SaveAsDialog_filetype_label;
    public static String SaveAsDialog_question;
    public static String SaveAsDialog_filename;
    public static String SaveAsDialog_export_error;
	public static String SaveAsDialog_text;
	public static String SaveAsDialog_file;
	public static String SaveAsDialog_overwriteQuestion;
	public static String SaveAsDialog_closedProjectMessage;

	public static String Question;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}