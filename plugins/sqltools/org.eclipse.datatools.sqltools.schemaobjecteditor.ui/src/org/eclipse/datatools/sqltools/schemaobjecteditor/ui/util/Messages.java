package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages";
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    public static String SchemaObjectEditorUtils_always_open;
    public static String        SchemaObjectEditorUtils_editor_id;
    public static String        SchemaObjectEditorUtils_error;
    public static String        SchemaObjectEditorUtils_error_open;
    public static String        SchemaObjectEditorUtils_error_when_open;
    public static String        SchemaObjectEditorUtils_internal_error_when_opening;
    public static String        SchemaObjectEditorUtils_no_editor;
    public static String        SchemaObjectEditorUtils_no_exact_editor;
    public static String        SchemaObjectEditorUtils_no_extension;
    public static String        SchemaObjectEditorUtils_no_suitable_editor;
    public static String        SchemaObjectEditorUtils_object_type;
    public static String        SchemaObjectEditorUtils_question;
    public static String        SchemaObjectEditorUtils_use_higest_version_editor;
    public static String        SchemaObjectEditorUtils_vendor_name;
    public static String        SchemaObjectEditorUtils_version;
    public static String        StatusLogger_no_bundle;
    public static String        StatusLogger_possible_args;
    public static String EditSchemaObjectAction_open_schema_editor;
    public static String EditSchemaObjectAction_opening;
}
