/**
 * Created on 2005-1-27
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.preferences;

import org.eclipse.osgi.util.NLS;

public final class PreferenceMessages extends NLS 
{

    private static final String BUNDLE_NAME = PreferenceMessages.class.getPackage().getName()+".messages";//$NON-NLS-1$

    private PreferenceMessages() 
    {
        // Do not instantiate
    }

    public static String SQLFilePage_description;
    public static String SQLFilePage_default_connection;
    public static String SQLFilePage_override_profile;
    public static String SQLFilePage_default_open;
    public static String SQLFilePage_commit_mode;
    public static String SQLFilePage_commit_auto;
    public static String SQLFilePage_commit_manual;
    
    static 
    {
        NLS.initializeMessages(BUNDLE_NAME, PreferenceMessages.class);
    }
}
