/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.internal.ui.view;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class is for i18n purpose.
 * 
 * @author Dafan Yang
 */
public class Messages
{
    private static final String         RESOURCE_BUNDLE = "org.eclipse.datatools.sqltools.plan.internal.ui.view.messages";
    private static final ResourceBundle bundle          = ResourceBundle.getBundle(RESOURCE_BUNDLE);

    private Messages()
    {
        
    }

    public static String getString(String key)
    {
        try
        {
            return bundle.getString(key);
        }
        catch (MissingResourceException e)
        {
            return key;
        }
    }

    public static String getString(String key, String arg0)
    {
        return getString(key, new Object[]
        {
            arg0
        });
    }

    public static String getString(String key, String arg0, String arg1)
    {
        return getString(key, new Object[]
        {
            arg0, arg1
        });
    }

    public static String getString(String key, String arg0, String arg1, String arg2)
    {
        return getString(key, new Object[]
        {
            arg0, arg1, arg2
        });
    }

    public static String getString(String key, String arg0, String arg1, String arg2, String arg3)
    {
        return getString(key, new Object[]
        {
            arg0, arg1, arg2, arg3
        });
    }
    
    public static String getString(String key, Object[] args)
    {
        try
        {
            return MessageFormat.format(bundle.getString(key), args);
        }
        catch (MissingResourceException e)
        {
            StringBuffer argString = new StringBuffer(key);
            argString.append(":");
            for (int i = 0; i < args.length; i++)
            {
                argString.append(args[i]);
            }
            return argString.toString();
        }
    }

    /**
     * Return the Locale that is loaded during startup
     * 
     * @return the locale
     */
    public static Locale getLocale()
    {
        return bundle.getLocale();
    }
}
