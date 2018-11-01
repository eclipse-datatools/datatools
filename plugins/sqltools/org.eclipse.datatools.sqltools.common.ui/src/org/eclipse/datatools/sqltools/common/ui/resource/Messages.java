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
package org.eclipse.datatools.sqltools.common.ui.resource;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";//$NON-NLS-1$

	public static String ContainerSelectionGroup_createProjectAction_label;

	public static String showAdvanced;

	public static String hideAdvanced;

	public static String ResourceGroup_resource;

	public static String ResourceGroup_nameExists;

	public static String ResourceGroup_folderEmpty;

	public static String ResourceGroup_noProject;

	public static String ResourceGroup_emptyName;

	public static String ResourceGroup_invalidFilename;

	public static String WizardNewFileCreationPage_progress;

	public static String WizardNewFileCreationPage_errorTitle;

	public static String WizardNewFileCreationPage_fileLabel;

	public static String WizardNewFileCreationPage_file;

	public static String WizardNewFileCreationPage_internalErrorTitle;

	public static String WizardNewFileCreationPage_internalErrorMessage;
	public static String WizardNewFileCreationPage_error_create_file;

	public static String Editor_error_save_message;

	public static String Editor_error_save_title;

	public static String Editor_warning_save_delete;
	public static String CreateFolderAction_text ;
	public static String CreateFolderAction_toolTip;
	public static String CreateFolderAction_title;

	public static String ResourceAndContainerGroup_error_linked_project;

	//from IDEWorkbenchMessages
	public static String ContainerGroup_message;
	public static String ContainerGroup_selectFolder;

	private Messages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}