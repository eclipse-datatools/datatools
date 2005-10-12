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

	public static final String CONTEXT_ID_CONNECTIVITY = ConnectivityUIPlugin
			.getDefault().getBundle().getSymbolicName()
			+ ".connectivity"; //$NON-NLS-1$

	public static final String CONTEXT_ID_CONNECTIVITY_DSE_VIEW = CONTEXT_ID_CONNECTIVITY
			+ "_dse_view"; //$NON-NLS-1$

	public static final String CONTEXT_ID_CP_PROPERTY_PAGE = CONTEXT_ID_CONNECTIVITY
			+ "_cp_property_page"; //$NON-NLS-1$

	public static final String CONTEXT_ID_CP_WIZARD_PAGE = CONTEXT_ID_CONNECTIVITY
			+ "_cp_wizard_page"; //$NON-NLS-1$

	public static final String CONTEXT_ID_INTRO_WIZARD_PAGE = CONTEXT_ID_CONNECTIVITY
			+ "_intro_wizard_page"; //$NON-NLS-1$

	public static final String CONTEXT_ID_CONNECTION_PROFILE_DETAILS_PAGE = CONTEXT_ID_CONNECTIVITY
			+ "_connection_profile_details_page"; //$NON-NLS-1$

	public static final String CONTEXT_ID_CONNECTION_PROFILE_SUMMARY_PAGE = CONTEXT_ID_CONNECTIVITY
			+ "_connection_profile_summary_page"; //$NON-NLS-1$

	public static final String CONTEXT_ID_NEW_CONNECTION_PROFILE_PAGE = CONTEXT_ID_CONNECTIVITY
			+ "_new_connection_profile_page"; //$NON-NLS-1$

	public static final String CONTEXT_ID_PROFILE_DETAILS_PROPERTY_PAGE = CONTEXT_ID_CONNECTIVITY
			+ "_profile_details_property_page"; //$NON-NLS-1$

}
