/*******************************************************************************
 * Copyright 2004, 2014 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 *               Actuate Corporation - re-factored to an extendable base class and
 *                  added the optional properties tab
 ******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.internal.ui.util;

import org.eclipse.osgi.util.NLS;

public final class ResourceLoader extends NLS
{

	private static final String BUNDLE_NAME = "org.eclipse.datatools.enablement.ibm.db2.luw.ui.l10n.DB2LUWUI";//$NON-NLS-1$
	
	private ResourceLoader()
	{
		// Do not instantiate
	}

	public static String CUI_NEWCW_DATABASE_LBL_UI_;
	public static String CUI_NEWCW_HOST_LBL_UI_;
	public static String CUI_NEWCW_PORT_LBL_UI_;
	public static String CUI_NEWCW_JDBCDRIVERCLS_LBL_UI_;
	public static String CUI_NEWCW_JDBCCLSLOCATION_LBL_UI_;
	public static String CUI_NEWCW_JARBROWSE_BTN_UI_;
	public static String CUI_NEWCW_CONNECTIONURL_LBL_UI_;
	public static String CUI_NEWCW_DEFDBNAME_VAL_UI_;
	public static String CUI_NEWCW_CLIENTAUTHENTICATION_BTN_UI_;
	public static String CUI_NEWCW_USERNAME_LBL_UI_;
	public static String CUI_NEWCW_PASSWORD_LBL_UI_;
	public static String CUI_NEWCW_SAVE_PASSWORD_LBL_UI_;
	public static String CUI_NEWCW_ENTER_SERVER_PRINCIPAL_MESSAGE_UI_;
	public static String CUI_NEWCW_SERVER_PRINCIPAL_LBL_UI_;
	public static String CUI_NEWCW_ENTER_BASE_DN_MESSAGE_UI_;
	public static String CUI_NEWCW_USE_BASE_DN_BTN_UI_;
	public static String CUI_NEWCW_BASE_DN_LBL_UI_;
	public static String CUI_NEWCW_DATABASE_SUMMARY_DATA_TEXT_;
	public static String CUI_NEWCW_USERNAME_SUMMARY_DATA_TEXT_;
	public static String CUI_NEWCW_SAVE_PASSWORD_SUMMARY_DATA_TEXT_;
	public static String CUI_NEWCW_HOST_SUMMARY_DATA_TEXT_;
	public static String CUI_NEWCW_PORT_SUMMARY_DATA_TEXT_;
	public static String CUI_NEWCW_URL_SUMMARY_DATA_TEXT_;
	public static String CUI_NEWCW_BASEDN_SUMMARY_DATA_TEXT_;
	public static String CUI_NEWCW_TRUE_SUMMARY_DATA_TEXT_;
	public static String CUI_NEWCW_FALSE_SUMMARY_DATA_TEXT_;
	public static String CUI_NEWCW_VALIDATE_DATABASE_REQ_UI_;
	public static String CUI_NEWCW_VALIDATE_HOST_REQ_UI_;
	public static String CUI_NEWCW_VALIDATE_PORT_REQ_UI_;
	public static String CUI_NEWCW_VALIDATE_USERID_REQ_UI_;
	public static String CUI_NEWCW_VALIDATE_PASSWORD_REQ_UI_;
	public static String CUI_NEWCW_DEFAULT_SCHEMA_LBL_UI_;
	public static String CUI_NEWCW_DEFAULT_SCHEMA_SUMMARY_DATA_TEXT_;
	public static String CUI_NEWCW_KERBOROS_KINIT_AUTHENTICATION;
	
	static
	{
		NLS.initializeMessages( BUNDLE_NAME, ResourceLoader.class );
	}
}
