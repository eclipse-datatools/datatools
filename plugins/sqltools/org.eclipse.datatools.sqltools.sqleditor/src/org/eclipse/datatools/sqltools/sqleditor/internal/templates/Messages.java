/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.templates;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author lihuang
 */
public class Messages extends NLS
{
    private static final String   RESOURCE_BUNDLE  = Messages.class.getPackage().getName()+".messages";
    private static ResourceBundle _resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);

    private Messages()
    {
    }

    public static String getString(String key)
    {
        try
        {
            return _resourceBundle.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key + '!';
        }
    }

    public static String TemplateProposal_displayString;
    public static String TemplateProposal_errorDialog_title;
    public static String ContributionTemplateStore_ignore_no_id;
    public static String ContributionTemplateStore_ignore_deleted;
    public static String ContributionTemplateStore_ignore_validation_failed;
    public static String SQLTemplateReaderWriter_duplicate_id;
    public static String SQLTemplateReaderWriter_error_missing_attribute;
    public static String SQLTemplateReaderWriter_error_illegal_boolean_attribute;
    public static String SQLTemplateProposal_proposalInfo;
    
    static {
        NLS.initializeMessages(RESOURCE_BUNDLE, Messages.class);
    }
    
    /**
     * Formats the given string with the given argument.
     *
     * @param message the message to format, must not be <code>null</code>
     * @param argument the argument used to format the string
     * @return the formatted string
     */
    public static String format(String message, Object argument) {
        return MessageFormat.format(message, new Object[] { argument });
    }

    /**
     * Formats the given string with the given argument.
     *
     * @param message the message to format, must not be <code>null</code>
     * @param arguments the arguments used to format the string
     * @return the formatted string
     */
    public static String format(String message, Object[] arguments) {
        return MessageFormat.format(message, arguments);
    }
}
