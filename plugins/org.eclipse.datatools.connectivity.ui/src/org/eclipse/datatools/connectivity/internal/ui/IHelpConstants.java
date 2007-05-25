/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brandow - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;


/**
 * @author brandow
 */
public interface IHelpConstants {

	/*
	 * CONTEXT_ID_CP_PROPERTY_PAGE =
	 * Basic profile name/description/auto-connect property page
	 */
	public static final String CONTEXT_ID_CP_PROPERTY_PAGE = 
			"CONTEXT_ID_CP_PROPERTY_PAGE"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_CP_WIZARD_PAGE =
	 * wizard selection page in New Connection Profile wizard
	 */
	public static final String CONTEXT_ID_CP_WIZARD_PAGE = 
			"CONTEXT_ID_CP_PROPERTY_PAGE"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_INTRO_WIZARD_PAGE =
	 * Basic intro page for new connection profile wizard
	 */
	public static final String CONTEXT_ID_INTRO_WIZARD_PAGE = 
			"CONTEXT_ID_INTRO_WIZARD_PAGE"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_CONNECTION_PROFILE_SUMMARY_PAGE =
	 * Basic summary page for new connection profile wizard
	 */
	public static final String CONTEXT_ID_CONNECTION_PROFILE_SUMMARY_PAGE = 
			"CONTEXT_ID_CONNECTION_PROFILE_SUMMARY_PAGE"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_NEW_CONNECTION_PROFILE_PAGE =
	 * Basic profile name/description/auto-connect wizard page
	 */
	public static final String CONTEXT_ID_NEW_CONNECTION_PROFILE_PAGE = 
			"CONTEXT_ID_NEW_CONNECTION_PROFILE_PAGE"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_PROFILE_DETAILS_PROPERTY_PAGE =
	 * Basic profile name/description/auto-connect property page
	 */
	public static final String CONTEXT_ID_PROFILE_DETAILS_PROPERTY_PAGE = 
			"CONTEXT_ID_PROFILE_DETAILS_PROPERTY_PAGE"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_DRIVER_DEFINITION_DIALOG =
	 * Driver Definition Dialog, which is the dialog equivalent to the
	 * functionality on the Driver Preferences page. This dialog is 
	 * available from the driver drop-down on most profile wizard/property
	 * pages.
	 */
	public static final String CONTEXT_ID_DRIVER_DEFINITION_DIALOG = 
		"CONTEXT_ID_DRIVER_DEFINITION_DIALOG"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_EDIT_DRIVER_DIALOG =
	 * Edit Driver Dialog, allows users to edit a driver definition's name,
	 * description, and properties
	 */
	public static final String CONTEXT_ID_EDIT_DRIVER_DIALOG = 
		"CONTEXT_ID_EDIT_DRIVER_DIALOG"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_NEW_DRIVER_DIALOG =
	 * New Driver Dialog, allows users to select a driver category and create
	 * a new driver (Edit Driver Dialog typically follows it once a category
	 * is selected)
	 */
	public static final String CONTEXT_ID_NEW_DRIVER_DIALOG = 
		"CONTEXT_ID_NEW_DRIVER_DIALOG"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_EXPORT_PROFILES_DIALOG =
	 * Export Profiles Dialog
	 */
	public static final String CONTEXT_ID_EXPORT_PROFILES_DIALOG = 
		"CONTEXT_ID_EXPORT_PROFILES_DIALOG"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_IMPORT_PROFILES_DIALOG =
	 * Import Profiles Dialog
	 */
	public static final String CONTEXT_ID_IMPORT_PROFILES_DIALOG = 
		"CONTEXT_ID_IMPORT_PROFILES_DIALOG"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_DRIVER_PREFERENCES =
	 * Driver Preferences page (see New Driver Dialog)
	 */
	public static final String CONTEXT_ID_DRIVER_PREFERENCES = 
		"CONTEXT_ID_DRIVER_PREFERENCES"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_PROFILE_VERSION_PROPERTIES =
	 * Version Property Page for connection profiles, shows the version info
	 * that comes back from the connection
	 */
	public static final String CONTEXT_ID_PROFILE_VERSION_PROPERTIES = 
		"CONTEXT_ID_PROFILE_VERSION_PROPERTIES"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_NEW_CP_WIZARD =
	 * New Connection Profile Wizard
	 */
	public static final String CONTEXT_ID_NEW_CP_WIZARD = 
		"CONTEXT_ID_NEW_CP_WIZARD"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_NEW_REPOSITORY_WIZARD =
	 * New Repository Wizard
	 */
	public static final String CONTEXT_ID_NEW_REPOSITORY_WIZARD = 
		"CONTEXT_ID_NEW_REPOSITORY_WIZARD"; //$NON-NLS-1$

	/*
	 * CONTEXT_ID_NEW_CONNECTION_PROFILE_WIZARD =
	 * New Connection Profile Wizard (overridden by each individual type of profile)
	 */
	public static final String CONTEXT_ID_NEW_CONNECTION_PROFILE_WIZARD = 
		"CONTEXT_ID_NEW_CONNECTION_PROFILE_WIZARD"; //$NON-NLS-1$
}
