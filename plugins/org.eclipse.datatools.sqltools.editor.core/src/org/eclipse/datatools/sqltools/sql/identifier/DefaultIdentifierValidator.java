/**
 * Created on 2005-12-21
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.identifier;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.sql.internal.identifier.Messages;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;
import org.eclipse.osgi.util.NLS;

/**
 * This class is the default implementation of IIdentifierValidator interface
 * 
 * @author wanh
 */
public class DefaultIdentifierValidator implements IIdentifierValidator
{
    public static final int MAXIMUM_ID_LENGTH = 30;
    /**
     * the DefaultIdentifierValidator Instance
     */
    private static IIdentifierValidator _instance = null;
    /**
     * Message level
     */
    private int _level = ValidatorMessage.ALL; 
    /**
     * Get the DefaultIdentifierValidator Instance
     * 
     * @return
     */
    public synchronized static IIdentifierValidator getInstance()
    {
        if (_instance == null)
        {
            return new DefaultIdentifierValidator();
        }
        else
        {
            return _instance;
        }
    }

    /**
     * This method is not encouraged to overrided, if you want to add new validate rule, you can add it in
     * checkCustomization method
     * 
     * @param value
     * @param identifierType
     * @param databaseIdentifier
     * @return
     */
    public ValidatorMessage isValid(String value, int identifierType, DatabaseIdentifier databaseIdentifier)
    {
        int oldLevel = _level;
        setLevel(ValidatorMessage.ERROR);
        ValidatorMessage errorMsg = doValidCheck(value, identifierType, databaseIdentifier);
        setLevel(ValidatorMessage.WARNING);
        ValidatorMessage warnMsg = doValidCheck(value, identifierType, databaseIdentifier);
        // restore the old level
        setLevel(oldLevel);
        ValidatorMessage result = null;
        // if the level is set to ALL level, if has error then return error, else return warning message if has
        if (_level == ValidatorMessage.ALL)
        {
            if (errorMsg != null && errorMsg.getType() == ValidatorMessage.ERROR)
            {
                result = errorMsg;
            }
            else if (warnMsg != null && warnMsg.getType() == ValidatorMessage.WARNING)
            {
                result = warnMsg;
            }
        }
        // if the level is set to ERROR level, only get error level message
        else if (_level == ValidatorMessage.ERROR)
        {
            if (errorMsg != null && errorMsg.getType() == ValidatorMessage.ERROR)
            {
                result = errorMsg;
            }
        }
        // if the level is set to WARNING level, only get WARNING level message
        else if (_level == ValidatorMessage.WARNING)
        {
            if (warnMsg != null && warnMsg.getType() == ValidatorMessage.WARNING)
            {
                result = warnMsg;
            }
        }
        return result;
    }

    /**
     * This method is not encouraged to overrided, if you want to add new validate rule, you can add it in
     * checkCustomization method
     * 
     * @param value
     * @param identifierType
     * @param databaseIdentifier
     * @return
     */
    public ValidatorMessage doValidCheck(String value, int identifierType, DatabaseIdentifier databaseIdentifier)
    {
        // step 1 check character length
        ValidatorMessage errorMsg = checkIdentiferLength(value, identifierType);
        if (ValidatorMessage.hasError(errorMsg, _level))
        {
            return errorMsg;
        }
        boolean quotedIdentifier = SQLDevToolsUtil.isQuotedIdentifierOn(databaseIdentifier);
        if (quotedIdentifier)
        {
            if (identifierType != IDENTIFIER_TYPE_TABLE)
            {
                // Check start character, only in quoted identfier on
                errorMsg = checkStartCharacterQuotedIdentifierOn(value, identifierType, databaseIdentifier);
            }
            if (ValidatorMessage.hasError(errorMsg, _level))
            {
                return errorMsg;
            }
            // Other checks
            errorMsg = checkQuotedIdentifierOnOthers(value, identifierType, databaseIdentifier);
            if (ValidatorMessage.hasError(errorMsg, _level))
            {
                return errorMsg;
            }
        }
        if (supportsDelimitedIdentifier(identifierType)
        && quotedIdentifier
            && (SQLUtil.findQuotes(value) == SQLUtil.MATCHING_DOUBLE_QUOTES || value != null
            && value.startsWith("[") && value.endsWith("]")))
        {
            // Delimited identifiers check
            errorMsg = checkDelimitedIdentifier(value);
            if (ValidatorMessage.hasError(errorMsg, _level))
            {
                return errorMsg;
            }
        }
        else
        {
            // check the start character
            errorMsg = checkStartCharacter(value, identifierType, databaseIdentifier);
            if (ValidatorMessage.hasError(errorMsg, _level))
            {
                return errorMsg;
            }
            // check the value has quotes
            errorMsg = checkquotePermit(value);
            if (ValidatorMessage.hasError(errorMsg, _level))
            {
                return errorMsg;
            }
            // check the part
            errorMsg = checkIdentifierPart(value, identifierType, databaseIdentifier);
            if (ValidatorMessage.hasError(errorMsg, _level))
            {
                return errorMsg;
            }
            // check the reserved words
            errorMsg = checkReservedwords(value, databaseIdentifier);
            if (ValidatorMessage.hasError(errorMsg, _level))
            {
                return errorMsg;
            }
            // check others
            errorMsg = checkCustomization(value, identifierType, databaseIdentifier);
            if (ValidatorMessage.hasError(errorMsg, _level))
            {
                return errorMsg;
            }
        }
        return new ValidatorMessage(ValidatorMessage.NO_ERROR, "");
    }

    /**
     * Checks the identifier type if it is allowed in delmited identifier
     * 
     * @param identifierType Identfier Type
     * @return If suppport delimited return true else return false
     */
    protected boolean supportsDelimitedIdentifier(int identifierType)
    {
        if (identifierType == IDENTIFIER_TYPE_LOCAL_VARAIBLE || identifierType == IDENTIFIER_TYPE_PARAMETER)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Other check need to be done when quoted identifer option is on,subclass can override this method if subclass want
     * to do more check
     * 
     * @param value identfier
     * @param identifierType identfier Type
     * @param databaseIdentifier databaseIdentfier
     * @return error message if have
     */
    protected ValidatorMessage checkQuotedIdentifierOnOthers(String value, int identifierType,
        DatabaseIdentifier databaseIdentifier)
    {
        // other checks
        return null;
    }

    /**
     * Generally this method is not encouraged to overide
     * 
     * @param value
     * @param databaseIdentifier
     * @return
     */
    protected ValidatorMessage checkReservedwords(String value, DatabaseIdentifier databaseIdentifier)
    {
        SQLDevToolsConfiguration conf = SQLToolsFacade.getConfiguration(null, databaseIdentifier); 
        String[] reserved = conf.getSQLService().getSQLSyntax().getReservedwords();
        String[] types = conf.getSQLService().getSQLSyntax().getTypes();
        for (int i = 0; i < reserved.length; i++)
        {
            if (value.equalsIgnoreCase(reserved[i]))
            {
                return new ValidatorMessage(ValidatorMessage.ERROR, Messages.IdentifierValidator_invalid_reservedword);
            }
        }
        for (int i = 0; i < types.length; i++)
        {
            if (value.equalsIgnoreCase(types[i]))
            {
                return new ValidatorMessage(ValidatorMessage.ERROR, Messages.IdentifierValidator_invalid_reservedword);
            }
        }
        return null;
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
        return null;
    }

    /**
     * This method need to be override, beacause different database has different idenfier length
     * 
     * @param value
     * @return
     */
    protected ValidatorMessage checkIdentiferLength(String value, int identifierType)
    {
        return null;
    }

    /**
     * Check the start character of input string when quoted identifer is set to off
     * 
     * @param value
     * @return
     */
    protected ValidatorMessage checkStartCharacter(String value, int identfierType, DatabaseIdentifier dbIdentifier)
    {
        String[] validStart = getValidStart(identfierType);
        StringBuffer result = new StringBuffer();
        if (validStart.length == 1)
        {
            result.append(validStart[0]);
        }
        else if (validStart.length > 1)
        {
            for (int i = 0; i < validStart.length - 1; i++)
            {
                result.append(validStart[i] + ", ");
            }
            result.append(validStart[validStart.length - 1]);
        }
        if (!checkFirstEquals(value, validStart))
        {
            switch (identfierType)
            {
                case IDENTIFIER_TYPE_TABLE:
                    return new ValidatorMessage(ValidatorMessage.ERROR, Messages.IdentifierValidator_table_invalidstart);
                case IDENTIFIER_TYPE_LOCAL_VARAIBLE:
                    return new ValidatorMessage(ValidatorMessage.ERROR, Messages.IdentifierValidator_localvariable_invalidstart);
                case IDENTIFIER_TYPE_PARAMETER:
                    return new ValidatorMessage(ValidatorMessage.ERROR, Messages.IdentifierValidator_parameter_invalidstart);
                default:
                    return new ValidatorMessage(ValidatorMessage.ERROR, NLS.bind(Messages.IdentifierValidator_other_invalidstart,(new Object[]
                    {
                        result.toString()
                    }
                    )));
            }
        }
        return null;
    }

    /**
     * Checks the start character when quote identfier option is on, subclass should override if need check
     * 
     * @param value the vale be checked
     * @param identfierType Identifier type
     * @param dbIdentifier datbase Identifier
     * @return the error message
     */
    protected ValidatorMessage checkStartCharacterQuotedIdentifierOn(String value, int identfierType,
        DatabaseIdentifier dbIdentifier)
    {
        return new ValidatorMessage(ValidatorMessage.NO_ERROR, "");
    }

    /**
     * Get all valid start characters
     * 
     * @param identfierType Identifier Type
     * @return All vaild start characters
     */
    protected String [] getValidStart(int identfierType)
    {

        String[] validStart = null;
        switch (identfierType)
        {
            case IDENTIFIER_TYPE_TABLE:
                validStart = new String[]
                {
                    "_", "#"
                }
                ;
                break;
            case IDENTIFIER_TYPE_LOCAL_VARAIBLE:
                validStart = new String[]
                {
                    "_", "@"
                }
                ;
                break;
            case IDENTIFIER_TYPE_PARAMETER:
                validStart = new String[]
                {
                    "_", "@"
                }
                ;
                break;
            default:
                validStart = new String[]
                {
                    "_"
                }
                ;
        }
        return validStart;
    }
    /**
     * Check the first character is vaild in given vaild array
     * 
     * @param value
     * @param validStart
     * @return
     */
    private boolean checkFirstEquals(String value, String[] validStart)
    {
        boolean firstValid = false;
        // if start with alphabetic character
        if (Character.isLetter(value.charAt(0)))
        {
            firstValid = true;
        }
        else
        {
            // not start with alphabetic character,maybe start with "_"
            for (int i = 0; i < validStart.length; i++)
            {
                if (value.startsWith(validStart[i]))
                {
                    firstValid = true;
                    break;
                }
            }
        }
        return firstValid;
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

        if (!Character.isLetterOrDigit(c))
        {
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
            if (isCurrencySymbol(c))
            {
                return true;
            }
            return false;
        }
        else

        {
            return true;
        }
    }

    /**
     * Check the given if it is a currency symbol
     * 
     * @param c given character
     * @return boolean value
     */
    public static boolean isCurrencySymbol(char c)
    {
        String s = String.valueOf(c);
        // \u0024 dollar \u00A2 cent \u00A3 Pounds \u00A4 currency sign \u00A5 Yen
        // unicode 1.4 http://www.unicode.org/charts/PDF/U20A0.pdf
        if ("\u00A2".equals(s) || "\u00A3".equals(s) || "\u00A4".equals(s) || "\u00A5".equals(s) || "\u0024".equals(s)
        || (s.compareTo("\u20a0") >= 0 && s.compareTo("\u20cf") <= 0))
        {
            return true;
        }
        // other full currencySymbols
        String[] currencySymbols = SQLUtil.getAvaiableCurrencySymbols();
        for (int i = 0; i < currencySymbols.length; i++)
        {
            if (String.valueOf(c).indexOf(currencySymbols[i]) > -1)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * If the string has single or double quote when quoted identifier is set to off,it will return error message
     * 
     * @param value
     * @return
     */
    private ValidatorMessage checkquotePermit(String value)
    {
        if (value == null || "".equals(value.trim()))
        {
            return null;
        }
        int doublequoteNum = 0;
        for (int i = 0; i < value.length(); i++)
        {
            if ("\"".equals(String.valueOf(value.charAt(i))))
            {
                doublequoteNum++;
            }
            if (doublequoteNum > 0)
            {
                return new ValidatorMessage(ValidatorMessage.ERROR, Messages.IdentifierValidator_doublequotation_notpermit);
            }
        }
        return null;
    }

    /**
     * Check the number of double quotes, if any number is odd or the input is totally made of quotes, it will return
     * error message
     * 
     * @param value
     * @return
     */
    private ValidatorMessage checkDelimitedIdentifier(String value)
    {
        if (value == null || "".equals(value.trim()))
        {
            return new ValidatorMessage(ValidatorMessage.NO_ERROR, "");
        }
        String[] values = SQLUtil.parseDatabaseObject(value);
        if (values == null || values.length == 0 || !SQLUtil.equalsIgnoreQuote(values[0], value, false))
        {
            return new ValidatorMessage(ValidatorMessage.ERROR, Messages.IdentifierValidator_doublequotation_invalid);
        }
        return new ValidatorMessage(ValidatorMessage.NO_ERROR, "");
    }

    /**
     * The default implementation returns the minimum supported maximum length across vendors
     */
    public int getMaximumIdLength(int idType)
    {
        return MAXIMUM_ID_LENGTH;
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.sqltools.parser.IIdentifierValidator#setLevel(int)
     */
    public void setLevel(int level)
    {
        _level = level;
    }

}
