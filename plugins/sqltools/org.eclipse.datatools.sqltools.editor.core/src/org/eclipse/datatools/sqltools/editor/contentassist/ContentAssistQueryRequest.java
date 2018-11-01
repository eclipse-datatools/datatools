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

package org.eclipse.datatools.sqltools.editor.contentassist;

/**
 * This class represents a request for providing content assist about database objects.
 * 
 * @author Hui Cao
 *
 */
public class ContentAssistQueryRequest 
{

    public static final int QUERY_GET_DATABASES = 1;
    public static final int QUERY_GET_TABLES = 2;
    public static final int QUERY_GET_STORED_PROCEDURES = 3;
    public static final int QUERY_GET_TRIGGERS = 4;
    public static final int QUERY_GET_UDFS = 5;
    public static final int QUERY_GET_EVENTS = 6; // ASA only
    public static final int QUERY_GET_OWNERS = 7;
    public static final int QUERY_GET_COLUMNS = 8;
    // following indicate interest to get source for the object.
    public static final int QUERY_STORED_PROCEDURE_TEXT = 9;
    public static final int QUERY_UDF_TEXT = 10;
    public static final int QUERY_EVENT_TEXT = 11;
    public static final int QUERY_TRIGGER_TEXT = 12;
    public static final int QUERY_USER_DATATYPE = 13;
    public static final int QUERY_ALLOWED_USER_DATATYPE = 14;

    private String databaseName = null;
    private String ownerName = null;
    private String tablename = null;
    private String storedProcName = null;
    private String triggerName = null;

    private String udfName = null;
    private String eventName = null;
    private int queryType;

    public int getQueryType() 
    {
        return queryType;
    }

    public void setQueryType(int queryType) 
    {
        this.queryType = queryType;
    }

    public String getDatabaseName() 
    {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) 
    {
        this.databaseName = databaseName;
    }

    public String getEventName() 
    {
        return eventName;
    }

    public void setEventName(String eventName) 
    {
        this.eventName = eventName;
    }

    public String getOwnerName() 
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName) 
    {
        this.ownerName = ownerName;
    }

    public String getStoredProcName() 
    {
        return storedProcName;
    }

    public void setStoredProcName(String storedProcName) 
    {
        this.storedProcName = storedProcName;
    }

    public String getTriggerName() 
    {
        return triggerName;
    }

    public void setTriggerName(String triggerName) 
    {
        this.triggerName = triggerName;
    }

    public String getTableName() 
    {
        return tablename;
    }

    public void setTableName(String tablename) 
    {
        this.tablename = tablename;
    }

    public String getUdfName() 
    {
        return udfName;
    }

    public void setUdfName(String udfName) 
    {
        this.udfName = udfName;
    }
}
