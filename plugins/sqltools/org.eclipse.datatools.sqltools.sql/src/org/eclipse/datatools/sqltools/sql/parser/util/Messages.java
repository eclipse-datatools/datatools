/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.parser.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Hui Cao
 * 
 */
public class Messages {
	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.sql.parser.util.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
    public static String getString(String key, String arg0)
    {
        return getString(key, new Object[]{arg0});
    }

    public static String getString(String key, String arg0, String arg1)
    {
        return getString(key, new Object[]{arg0,arg1});
    }

    public static String getString(String key, String arg0, String arg1, String arg2)
    {
        return getString(key, new Object[]{arg0,arg1,arg2});
    }
    
    public static String getString(String key, Object[] args)
    {
        try
        {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key),args);
        }
        catch (MissingResourceException e)
        {
            StringBuffer argString = new StringBuffer(key);
            argString.append(":");
            for (int i=0; i< args.length; i++){
                argString.append(args[i]);
            }
            return argString.toString();
        }
    }

    /**
     * Return the Locale that is loaded during startup
     */
    public static Locale getLocale()
    {
        return RESOURCE_BUNDLE.getLocale();
    }

}
