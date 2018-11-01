/*
 *************************************************************************
 * Copyright (c) 2005, 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.profile.nls;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.datatools.connectivity.oda.profile.nls.messages"; //$NON-NLS-1$

    private Messages()
    {
    }

    static
    {
        // initialize resource bundle
        NLS.initializeMessages( BUNDLE_NAME, Messages.class );
    }

    public static String constants_componentName;
    public static String profileFileExtension_APPLIED_DEFAULT_FILE_EXT;
    public static String profileFileExtension_MULTIPLE_EXTENSIONS_FOUND;
    public static String propertyProvider_CANNOT_FIND_PROFILE;
    public static String propertyProvider_NO_RESOURCE_IDENTIFIERS;
    public static String propertyProvider_UNABLE_TO_RESOLVE_PATH;
}
