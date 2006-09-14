/**
 * Created on 2006-1-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.identifier;

/**
 * Validator message class
 * 
 * @author wanh
 */
public class ValidatorMessage
{
    /**
     * No error message <code>NO</code>
     */
    public static final int NO_ERROR      = 0;
    /**
     * Warning message,just show warning message to user, user can go on <code>WARNING</code>
     */
    public static final int WARNING = 1;
    /**
     * Error message,validator is not valid <code>ERROR</code>
     */
    public static final int ERROR   = 2;

    /**
     * All message including warning, error messenge
     */
    public static final int ALL   = 3;
    /**
     * Message type <code>_type</code>
     */
    private int       _type   = NO_ERROR;
    /**
     * Message <code>_message</code>
     */
    private String    _message;

    public ValidatorMessage(int type, String message)
    {
        _type = type;
        _message = message;
    }

    /**
     * @return Returns the _messag.
     */
    public String getMessage()
    {
        return _message;
    }

    /**
     * @return Returns the _type.
     */
    public int getType()
    {
        return _type;
    }

    /**
     * Check whether the message has error or not
     * 
     * @param message
     * @param level error message level
     * @return
     */
    public static boolean hasError(ValidatorMessage message, int level)
    {
        if (message == null || message.getType() == NO_ERROR)
        {
            return false;
        }
        switch (level)
        {
            case NO_ERROR:
                return false;
            case ALL:
                if (message != null
                && (message.getType() == WARNING || message.getType() == ALL || message.getType() == ERROR))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case WARNING:
                if (message != null && message.getType() == WARNING)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case ERROR:
                if (message != null && (message.getType() == ALL || message.getType() == ERROR))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            default:
                return false;
        }
    }

    /**
     * Check whether the message has error or not 
     * 
     * @param message
     * @return
     */
    public static boolean hasWarning(ValidatorMessage message)
    {
        if (message != null && message.getType() == WARNING)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
