/**
 * Created on 2008-3-14
 * 
 * Copyright (c) Sybase, Inc. 2004-2008 All rights reserved.
 */
package org.eclipse.datatools.connectivity.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * @author renj
 */
public final class Messages extends NLS 
{

    private static final String BUNDLE_NAME = Messages.class.getPackage().getName()+".messages";	//$NON-NLS-1$

    private Messages() 
    {
        // Do not instantiate
    }

    public static String Connectivity_description;
    
    static 
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}
