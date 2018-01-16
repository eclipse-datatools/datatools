package org.eclipse.datatools.sqltools.core.modelvalidity;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.core.modelvalidity.messages"; //$NON-NLS-1$

    private Messages()
    {
    }

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    public static String DefaultSQLDataOfflineValidator_not_a_valid_boolean;
    public static String DefaultSQLDataOfflineValidator_not_a_valid_number;
    public static String DefaultSQLDataOfflineValidator_not_a_valid_numeric;
}
