package org.eclipse.datatools.sqltools.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    private Messages()
    {
    }
    
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    public static String DataTypeValidator_can_not_use_in_current_context;
    public static String DataTypeValidator_invalid_type;
    public static String DataTypeValidator_no_db_definition;
    public static String PredefinedDataTypeValidator_length_exceed_max;
    public static String PredefinedDataTypeValidator_length_shouldbe_positive;
    public static String PredefinedDataTypeValidator_no_datatype_definition;
    public static String PredefinedDataTypeValidator_no_db_definition;
    public static String PredefinedDataTypeValidator_precision_exceed_max;
    public static String PredefinedDataTypeValidator_precision_lessthan_scale;
    public static String PredefinedDataTypeValidator_precision_positive;
    public static String PredefinedDataTypeValidator_scale_below_min;
}
