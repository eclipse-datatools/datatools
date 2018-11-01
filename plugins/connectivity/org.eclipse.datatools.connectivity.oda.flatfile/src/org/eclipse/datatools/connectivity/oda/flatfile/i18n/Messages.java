/*
 *******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Resource messages wrapper for the package to obtain localized message text.
 */

public class Messages
{
    private static final String BUNDLE_NAME = "org.eclipse.datatools.connectivity.oda.flatfile.i18n.messages";//$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle( BUNDLE_NAME );

    private Messages()
    {
    }

    public static String getString( String key )
    {
        try
        {
            return RESOURCE_BUNDLE.getString( key );
        }
        catch( MissingResourceException e )
        {
            return '!' + key + '!';
        }
    }
}