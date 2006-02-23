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

import java.io.Serializable;

import org.eclipse.datatools.sqltools.result.internal.utils.Images;
import org.eclipse.datatools.sqltools.result.internal.utils.Messages;
import org.eclipse.swt.graphics.Image;

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
public class OperationCommand implements Serializable
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID      = 9026813441175593165L;
    /* type of the operation */
    private int               _actionType;
    /* display string of this operation (will be used to query), for example, SQL statement */
    private String            _displayStr;
    /* name of the consumer */
    private String            _consumerName;
    /* connection profile name */
    private String            _profileName;
    /* database name */
    private String            _databaseName;
    /* consumer can use this field to store anything, this field will be persisted so it can be used for re-execution purpose */
    private Serializable      _data;

    /* 7 statuses defined */
    public static final int   STATUS_CRITICAL_ERROR = 7;
    public static final int   STATUS_WARNING        = 6;
    public static final int   STATUS_TERMINATED     = 5;
    public static final int   STATUS_FAILED         = 4;
    public static final int   STATUS_SUCCEEDED      = 3;
    public static final int   STATUS_RUNNING        = 2;
    public static final int   STATUS_STARTED        = 1;

    /* 1 action type(s) defined */
    public static final int   ACTION_EXECUTE        = 1;

    /**
     * Constructs an instance of OperationCommand.
     * 
     * @param type the action type, should be one of the types defined in this class(for now, we have only 1 type)
     * @see #ACTION_EXECUTE
     * @param displayStr string used to display, for example SQL statement, should not be null
     * @param consumerName name of the caller
     * @param profileName connection profile name, should not be null
     * @param databaseName database name, should not be null
     */
    public OperationCommand(int type, String displayStr, String consumerName, String profileName, String databaseName)
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
        _displayStr = displayStr == null ? "" : displayStr; //$NON-NLS-1$
        _consumerName = consumerName == null ? "" : consumerName; //$NON-NLS-1$
        _profileName = profileName == null ? "" : profileName; //$NON-NLS-1$
        _databaseName = databaseName == null ? "" : databaseName; //$NON-NLS-1$
    }
    
    /**
     * Construstor
     * @param data consumer can use this field to store anything
     */
    public OperationCommand(int type, String displayStr, String consumerName, String profileName, String databaseName, Serializable data)
    {
        this(type, displayStr, consumerName, profileName, databaseName);
        _data = data;
    }

    /**
     * Returns the display string of this operation
     * @return the display string of this operation
     */
    public String getDisplayString()
    {
        return _displayStr;
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
     * 
     * @param actionId the action type
     * @return the action string
     */
    public static String getActionString(int actionId)
    {
        switch (actionId)
        {
            case ACTION_EXECUTE:
                return Messages.getString("OperationCommand.action.execute"); //$NON-NLS-1$
            default:
                return Messages.getString("OperationCommand.unknown.action"); //$NON-NLS-1$
        }
    }
    
    /**
     * Returns the image of given status
     * 
     * @param statusId the status id
     * @return the image of this status
     */
    public static Image getStatusImage(int statusId)
    {
        switch (statusId)
        {
            case STATUS_STARTED:
                return Images.get(Images.IMG_STARTED);
            case STATUS_RUNNING:
                return Images.get(Images.IMG_RUNNING);
            case STATUS_SUCCEEDED:
                return Images.get(Images.IMG_SUCCESS);
            case STATUS_FAILED:
                return Images.get(Images.IMG_FAIL);
            case STATUS_TERMINATED:
                return Images.get(Images.IMG_TERMINATE);
            case STATUS_WARNING:
                return Images.get(Images.IMG_WARNING);
            case STATUS_CRITICAL_ERROR:
                return Images.get(Images.IMG_CRITICAL);
            default:
                return Images.get(Images.IMG_FAIL);
        }
    }
    
    /**
     * Converts the status id to status string
     * @param statusId the id of the status
     * @return the string that describes this status
     */
    public static String getStatusString(int statusId)
    {
        switch (statusId)
        {
            case STATUS_STARTED:
                return Messages.getString("OperationCommand.status.started"); //$NON-NLS-1$
            case STATUS_RUNNING:
                return Messages.getString("OperationCommand.status.running"); //$NON-NLS-1$
            case STATUS_SUCCEEDED:
                return Messages.getString("OperationCommand.status.succeeded"); //$NON-NLS-1$
            case STATUS_FAILED:
                return Messages.getString("OperationCommand.status.failed"); //$NON-NLS-1$
            case STATUS_TERMINATED:
                return Messages.getString("OperationCommand.status.terminated"); //$NON-NLS-1$
            case STATUS_WARNING:
                return Messages.getString("OperationCommand.status.warning"); //$NON-NLS-1$
            case STATUS_CRITICAL_ERROR:
                return Messages.getString("OperationCommand.status.critical"); //$NON-NLS-1$
            default:
                return Messages.getString("OperationCommand.status.unknown"); //$NON-NLS-1$
        }
    }

    /**
     * Returns the data
     * @return the data
     */
    public Serializable getData()
    {
        return _data;
    }

    /**
     * Sets the data
     * @param _data the data
     */
    public void setData(Serializable _data)
    {
        this._data = _data;
    }
}
