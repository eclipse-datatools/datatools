/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal;

/**
 * Constants
 * 
 * @author Idull
 */
public class Constants
{
    public static final String PLUGIN_ID                                            = "org.eclipse.datatools.sqltools.schemaobjecteditor.ui";
    public static final String EXTENSION_POINT_ID                                   = "schemaObjectEditor";
    public static final String EXTENSION_POINT_EDITOR_ID                            = "EditorId";
    public static final String EXTENSION_POINT_EDITOR_NAME                          = "EditorName";
    public static final String EXTENSION_POINT_EDITOR_VENDOR_NAME                   = "VendorName";
    public static final String EXTENSION_POINT_EDITOR_DB_VERSION                    = "Version";
    public static final String EXTENSION_POINT_EDITOR_OBJECT_TYPE_ID                = "ObjectTypeId";
    public static final String EXTENSION_POINT_EDITOR_HANDLER                       = "Handler";
    public static final String EXTENSION_POINT_EDITOR_GENERATE_PREF_GROUP           = "VisibilityConfigurable";
    public static final String EXTENSION_POINT_EDITOR_CONTRIBUTOR_CLASS             = "ContributorClass";
    public static final String EXTENSION_POINT_EDITOR_ICON                          = "Icon";
    public static final String EXTENSION_POINT_EDITOR_MANDATORYFRISTPAGE            = "MustBeFirstWhenShown";
    public static final String EXTENSION_POINT_EDITOR_MANDATORYLASTPAGE             = "MustBeLastWhenShown";
    public static final String EXTENSION_POINT_EDITOR_OBJECT_TYPE_NAME              = "ObjectTypeName";

    public static final String EXTENSION_POINT_PAGE                                 = "EditorPage";
    public static final String EXTENSION_POINT_PAGE_HELPID                          = "ContextHelpId";
    public static final String EXTENSION_POINT_REFERENCED_PAGE                      = "ReferencedPage";
    public static final String EXTENSION_POINT_PAGE_ID                              = "PageId";
    public static final String EXTENSION_POINT_PAGE_NAME                            = "PageName";
    public static final String EXTENSION_POINT_PAGE_VISIBLE_BYDEFAULT               = "VisibleByDefault";
    public static final String EXTENSION_POINT_PAGE_CLASS                           = "Class";
    public static final String EXTENSION_POINT_PAGE_IS_REQUIRED                     = "Required";
    public static final String EXTENSION_POINT_PAGE_EXTENSION_ID                    = "PageExtensionId";
    public static final String EXTENSION_POINT_PAGE_OBJECT_CLASS_TYPE               = "ObjectClassType";

    public static final String SCHEMA_EDITOR_ID                                     = "org.eclipse.datatools.sqltools.schemaobjecteditor.editor";
    public static final String SCHEMA_EDITOR_NESTED_ID                              = "org.eclipse.datatools.sqltools.schemaobjecteditor.nested.editor";
    public static final String EXTENSION_POINT_EDITOR_REFERENCED_EDITOR             = "ReferencedEditor";
    public static final String EXTENSION_POINT_EDITOR_REFERENCED_EDITOR_EXCLUDES    = "Excludes";
    public static final String EXTENSION_POINT_EDITOR_REFERENCED_EDITOR_EXCLUDEPAGE = "ExcludePage";
    public static final String EXTENSION_POINT_EDITOR_DEFAULT_ORDER                 = "DefaultPagesOrder";
    public static final String EXTENSION_POINT_EDITOR_ORDER_ITEM                    = "OrderItem";
    public static final String EXTENSION_POINT_EDITOR_ORDER_NUM                     = "OrderNum";

    public static final String EDITOR_PAGE_VISIABILITY                              = "SchemaObjectEditor.visiability";
    public static final String EDITOR_PAGE_ORDER                                    = "SchemaObjectEditor.pageOrder";

    public static final String PREFERENCE_PREVIOUS_DB_DEFINITION                    = "SchemaObjectEditorPreferencePage.previous.configured.dbdefinition";
    public static final String PREFERENCE_PREVIOUS_EDIOR_NAME                       = "SchemaObjectEditorPreferencePage.previous.configured.editor";

    public static final String PREFERENCE_ALWAYS_SHOW_PREVIEW                       = "SchemaObjectEditorPreferencePage.show.preview.dialog";
    public static final String PREFERENCE_USE_LATEST_VERSION                        = "SchemaObjectEditorPreferencePage.use.latest.version";

    public static final String PREFERENCE_CHECK_EXISTENCE                           = "SchemaObjectEditorPreferencePage.check.existence";
    public static final String PREFERENCE_OPEN_FILE_AFTER_SAVEAS                    = "SchemaObjectEditorPreferencePage.open.file.after.saveas";
}
