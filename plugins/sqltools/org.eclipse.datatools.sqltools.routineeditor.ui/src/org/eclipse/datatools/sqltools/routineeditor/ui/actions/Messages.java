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
package org.eclipse.datatools.sqltools.routineeditor.ui.actions;

import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";//$NON-NLS-1$
	private static final String BUNDLE_FOR_CONSTRUCTED_KEYS= Messages.class.getPackage().getName() + ".constructedMessages";//$NON-NLS-1$
	private static ResourceBundle fgBundleForConstructedKeys= ResourceBundle.getBundle(BUNDLE_FOR_CONSTRUCTED_KEYS);

	/**
	 * Returns the message bundle which contains constructed keys.
	 *
	 * @return the message bundle
	 */
	public static ResourceBundle getResourceBundle() {
		return fgBundleForConstructedKeys;
	}

	private Messages() {
		// Do not instantiate
	}

	public static String common_error;
	public static String EditRoutineAction_label;
	public static String EditRoutineAction_tooltip;
	public static String DropRoutineAction_title;
	public static String DropRoutineAction_message;
	public static String DropRoutineAction_label;
	public static String DropRoutineAction_tooltip;
	public static String RunAction_label;
	public static String RunAction_tooltip;
	public static String DebugAction_label;
	public static String DebugAction_tooltip;
	public static String RefreshFromDatabase_success;
	public static String RefreshFromDatabase_fail;
    public static String DebugAction_runError;
	public static String dmpActionHandler_deleteLaunchConfigration_NameOrOwnerNull;
    public static String RunAction_warn;
    public static String RunAction_warn_dirty;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}