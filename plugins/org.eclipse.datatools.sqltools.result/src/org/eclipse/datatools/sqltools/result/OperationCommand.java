/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

import org.eclipse.datatools.sqltools.result.internal.utils.Messages;

/**
 * The <code>OperationCommand</code> is used to uniquely identify an execution result in SQL Results View, it is the
 * starting point to use SQL Results View.
 * <p>
 * The consumer needs to initiate an instance of <code>OperationCommand</code> first, and then uses
 * <code>ResultsViewAPI</code> to append message or result set, or set parameters to SQL Results View. When using the
 * <code>ResultsViewAPI</code> to append result item to SQL Results View, this instance should always be given.
 * 
 * @see org.eclipse.datatools.sqltools.result.ResultsViewAPI
 * @author Dafan Yang
 */
public class OperationCommand
{
    /* type of the operation */
    private int             _actionType;
    /* script of this operation, for example, SQL statement */
    private String          _sqlScript;
    /* name of the consumer */
    private String          _consumerName;
    /* connection profile name */
    private String          _profileName;
    /* database name */
    private String          _databaseName;

    /* 7 statuses defined */
    public static final int STATUS_CRITICAL_ERROR = 7;
    public static final int STATUS_WARNING        = 6;
    public static final int STATUS_TERMINATED     = 5;
    public static final int STATUS_FAILED         = 4;
    public static final int STATUS_SUCCEEDED      = 3;
    public static final int STATUS_RUNNING        = 2;
    public static final int STATUS_STARTED        = 1;

    /* 1 action type(s) defined */
    public static final int ACTION_EXECUTE        = 1;

    /**
     * Constructs an instance of OperationCommand.
     * 
     * @param type the action type, should be one of the types defined in this class(for now, we have only 1 type)
     * @see #ACTION_EXECUTE
     * @param script for example SQL statement, should not be null
     * @param consumerName name of the caller
     * @param profileName connection profile name, should not be null
     * @param databaseName database name, should not be null
     */
    public OperationCommand(int type, String script, String consumerName, String profileName, String databaseName)
    {
        // will append the condition when action types are increased
        if(type != ACTION_EXECUTE)
        {
            _actionType = ACTION_EXECUTE;
        }
        else
        {
            _actionType = type;
        }
        _sqlScript = script == null ? "" : script;
        _consumerName = consumerName == null ? "" : consumerName;
        _profileName = profileName == null ? "" : profileName;
        _databaseName = databaseName == null ? "" : databaseName;
    }

    /**
     * Returns the script of this operation
     * @return the script of this operation
     */
    public String getScript()
    {
        return _sqlScript;
    }

    /**
     * Returns the action type
     * 
     * @see #ACTION_EXECUTE
     * @return the action type
     */
    public int getActionType()
    {
        return _actionType;
    }

    /**
     * Returns the consumer's name
     * 
     * @return the consumer's name
     */
    public String getConsumerName()
    {
        return _consumerName;
    }

    /**
     * Returns the connection profile name
     * 
     * @return the connection profile name
     */
    public String getProfileName()
    {
        return _profileName;
    }

    /**
     * Returns the database name
     * 
     * @return the database name
     */
    public String getDatabaseName()
    {
        return _databaseName;
    }
    
    /**
     * Converts the action id to action string.
     * @param actionId the action type
     * @return the action string
     */
    public String getActionString(int actionId)
    {
        switch (actionId)
        {
            case ACTION_EXECUTE:
                return Messages.getString("OperationCommand.action.execute");
            default:
                return Messages.getString("OperationCommand.unknown.action");
        }
    }
}
