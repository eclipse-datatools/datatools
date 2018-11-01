/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.intropage;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String         BUNDLE_NAME     = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Messages()
    {
    }

    public static String getString(String key)
    {
        try
        {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key + '!';
        }
    }

    public static String IntroductionPage_page_title;
    public static String IntroductionPage_section_title;
    public static String start;
    public static String start_desc;
    public static String cheatsheet;
    public static String cheatsheet_desc;
    public static String help;
    public static String help_desc;
    public static String default_heading;
    public static String properites_loading_exception;

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
