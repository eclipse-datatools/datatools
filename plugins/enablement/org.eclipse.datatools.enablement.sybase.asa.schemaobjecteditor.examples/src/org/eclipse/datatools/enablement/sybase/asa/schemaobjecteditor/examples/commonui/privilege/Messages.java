/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS
{

    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    private Messages()
    {
        // Do not instantiate
    }

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    public static String PrivilegesDetailPage_grant_all;
    public static String PrivilegesDetailPage_grant_all_button_tip;
    public static String PrivilegesDetailPage_grant_all_context;
    public static String PrivilegesDetailPage_grant_all_go_context;
    public static String PrivilegesDetailPage_grant_all_tip;
    public static String PrivilegesDetailPage_revoke_all;
    public static String PrivilegesDetailPage_revoke_all_button_tip;
    public static String PrivilegesDetailPage_revoke_all_context;
    public static String PrivilegesDetailPage_with_grant_option;
    public static String PrivilegesDetailPage_without_grant_option;
    public static String PrivilegesTreeViewerInput_grantee;
    public static String PrivilegesTreeViewerInput_groups;
    public static String PrivilegesTreeViewerInput_users;
    public static String TablePrivilegesBlock_all_columns;
    public static String TablePrivilegesBlock_columns;
    public static String PrivilegesTreeViewerInput_roles;
    public static String PrivilegesDetailPage_grant_options;
    public static String PrivilegesDetailPage_permissions;
    public static String PrivilegesDetailPage_revoke_grant_options;
    public static String PrivilegesDetailPage_filter_tip;
    public static String PrivilegesDetailPage_filter_grantee;
    public static String PrivilegesDetailPage_filter;
}
