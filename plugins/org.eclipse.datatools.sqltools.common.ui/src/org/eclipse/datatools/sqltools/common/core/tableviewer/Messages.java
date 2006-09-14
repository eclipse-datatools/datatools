/**
 * Created on 2005-11-23
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.core.tableviewer;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS 
{

    private static final String BUNDLE_NAME = Messages.class.getPackage().getName()+".messages";//$NON-NLS-1$

    private Messages() 
    {
        // Do not instantiate
    }

    public static String DataDeserializer_invalid_binary_data;
    public static String ParameterTableViewer_name;
    public static String ParameterTableViewer_type;
    public static String ParameterTableViewer_null;
    public static String ParameterTableViewer_value;
    public static String ParameterTableViewer_inout;

    static 
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}
