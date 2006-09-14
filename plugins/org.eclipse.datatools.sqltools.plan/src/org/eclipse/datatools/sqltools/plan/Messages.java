/**
 * Created on 2005-11-10 Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.plan;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS 
{

    private static final String BUNDLE_NAME = Messages.class.getPackage().getName()+".messages";//$NON-NLS-1$

    private Messages() 
    {
        // Do not instantiate
    }

    public static String PlanSupportRunnable_getplan_name;
    public static String common_ignoreException;

    static 
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}
