/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.Messages;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.IDatabaseSetting;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.IDatabaseSetting.NotSupportedSettingException;
import org.eclipse.datatools.sqltools.sql.identifier.DefaultIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;

/**
 * This class will check ASA identifier
 * 
 * @author wanh
 */
public class ASAIdentifierValidator extends DefaultIdentifierValidator
{
    public static final int MAXIMUM_ID_LENGTH = 128;
    /**
     * the ASAIdentifierValidator Instance
     */
    private static IIdentifierValidator _instance = null;

    /**
     * Get the ASAIdentifierValidator Instance
     * 
     * @return
     */
    public synchronized static IIdentifierValidator getInstance()
    {
        if (_instance == null)
        {
            return new ASAIdentifierValidator();
        }
        else
        {
            return _instance;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.sqltools.parser.DefaultIdentifierValidator#checkIdentiferLength(java.lang.String)
     */
    protected ValidatorMessage checkIdentiferLength(String value, int identifierType)
    {
        if (value != null && value.length() > 128)
        {
            return new ValidatorMessage(ValidatorMessage.ERROR, Messages.IdentifierValidator_asa_invalidlength);
        }
        return new ValidatorMessage(ValidatorMessage.NO_ERROR, "");
    }

    protected String[] getValidStart(int identfierType)
    {
        return new String[]
        {
            "_", "@", "$", "#"
        }
        ;
    }

    public int getMaximumIdLength(int idType)
    {
        return MAXIMUM_ID_LENGTH;
    }

    /**
     * Checks the identifier type if it is allowed in delmited identifier
     * 
     * @param identifierType Identfier Type
     * @return If suppport delimited return true else return false
     */
    protected boolean supportsDelimitedIdentifier(int identifierType)
    {
        // all identifier supports delimited identifier (even local var or parameter)
        return true;
    }

    /**
     * Others validator,If subclass has own customization validate rules, it can be overrided.
     * 
     * @param value
     * @param databaseIdentifier
     * @return
     */
    protected ValidatorMessage checkCustomization(String value, int identiferType, DatabaseIdentifier databaseIdentifier)
    {
        // Cannot be consecutive double slashes(//)
        boolean quoted = isQuotedIdentifierSet(databaseIdentifier);
        if (!quoted && value.indexOf("//") > -1)
        {
            return new ValidatorMessage(ValidatorMessage.ERROR, Messages.IdentifierValidator_invalid_part);
        }
        return null;
    }


    /**
     * Check the part of the identifier part except the first character
     * 
     * @param value
     * @param identifierType
     * @param dbIdentifier
     * @return
     */
    protected ValidatorMessage checkIdentifierPart(String value, int identifierType, DatabaseIdentifier dbIdentifier)
    {
        if (value.length() == 1)
        {
            // only one character
            return null;
        }
        else
        {
            // subsequent
            for (int i = 1; i < value.length(); i++)
            {
                if (!isSQLIdentifierPart(value.charAt(i)))
                {
                    return new ValidatorMessage(ValidatorMessage.ERROR, Messages.IdentifierValidator_invalid_part);
                }
            }

        }
        return null;
    }

    /**
     * The method can be overrided if database has different logic to check whtehter the character is SQL Identifier
     * part or not
     * 
     * @param c
     * @return
     */
    protected boolean isSQLIdentifierPart(char c)
    {
        // Cannot have double quotes, control chars
        if (!Character.isLetterOrDigit(c))
        {
            // @,$,#,_ are valid characters
            String[] validPart = new String[]
            {
                "@", "#", "_"
            }
            ;
            for (int i = 0; i < validPart.length; i++)
            {
                if (String.valueOf(c).equals(validPart[i]))
                {
                    return true;
                }
            }
            // others, such as  double quotes, control chars,are invalid
            return false;
        }
        else
        {
            return true;
        }
    }
    
    private boolean isQuotedIdentifierSet(DatabaseIdentifier databaseIdentifier)
    {
        boolean quoted_id = true;
        SQLDevToolsConfiguration conf = SQLToolsFacade.getConfiguration(null, databaseIdentifier);
        IDatabaseSetting setting = conf.getDatabaseSetting(databaseIdentifier);
        if (setting != null)
        {
            try
            {
                Boolean value = (Boolean) setting
                    .getConnectionConfigProperty(IDatabaseSetting.C_QUOTED_IDENTIFIER);
                quoted_id = value.booleanValue();
            }
            catch (NotSupportedSettingException e)
            {
            	ExamplePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, Messages.NotSupportedSettingException_cause));
            }
        }
        return quoted_id;
    }
}
