/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
    public static String        EditSchemaObjectAction_error;
    public static String        EditSchemaObjectAction_model_null;
    public static String        EditSchemaObjectAction_no_vendor_name;
    public static String        EditSchemaObjectAction_open_schema_editor;
    public static String        EditSchemaObjectAction_opening;
    public static String        RefreshSchemaEditorAction_question;
    public static String        RefreshSchemaEditorAction_referesh_editor;
    public static String        RefreshSchemaEditorAction_refresh_from_server;
    public static String        RefreshSchemaEditorAction_refresh_job;
    public static String        RefreshSchemaEditorAction_sync_with_db;
    public static String        RevertSchemaEditorAction_revert;
}
