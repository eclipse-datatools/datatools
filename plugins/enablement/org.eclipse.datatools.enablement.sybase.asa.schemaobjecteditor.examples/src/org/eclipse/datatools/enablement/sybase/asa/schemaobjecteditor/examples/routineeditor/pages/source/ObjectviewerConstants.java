/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.source;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author asarvesh
 *  
 */
public interface ObjectviewerConstants
{
    public static final String   CREATE_FUNCTION                  = "CREATE FUNCTION";
    public static final String   BEGIN                            = "BEGIN";
    public static final String   AS_BEGIN                         = "AS \nBEGIN";
    public static final String   END                              = "END";
    public static final String   RETURN                           = "RETURN";
    public static final String   DECLARE                          = "DECLARE";
    public static final String   RETURNS                          = "RETURNS";
    public static final String   DETERMINISTIC                    = "DETERMINISTIC";
    public static final String   ON_EXCEPTION_RESUME              = "ON EXCEPTION RESUME";
    public static final String   NOT_DETERMINISTIC                = "NOT DETERMINISTIC";

    public static final String   CREATE_PROCEDURE                 = "CREATE PROCEDURE";
    public static final String   RESULT                           = "RESULT";

    public static final String   PRINT                            = "\tPrint ";
    public static final String   WITH_RECOMPILE                   = "WITH RECOMPILE";

    public static final String   ONE_SPACE                        = " ";
    public static final String   ONE_TAB                          = "\t";
    public static final String   ONE_NEW_LINE                     = "\n";
    public static final String   AFTER                            = "AFTER";
    public static final String   BEFORE                           = "BEFORE";
    public static final String   FOR_EACH_ROW                     = "FOR EACH ROW";
    public static final String   FOR_EACH_STATMENT                = "FOR EACH STATEMENT";
    public static final String   REFERENCING                      = "REFERENCING";
    public static final String   NEW                              = "NEW";
    public static final String   OLD                              = "OLD";
    public static final String   AS                               = "AS";
    public static final String   CREATE_TRIGGER                   = "CREATE TRIGGER";
    public static final String   FOR                              = "FOR";
    public static final String   ON                               = "ON";
    public static final String   INSERT                           = "INSERT";
    public static final String   UPDATE                           = "UPDATE";
    public static final String   DELETE                           = "DELETE";
    public static final String   ORDER                            = "ORDER";
    public static final String   UPDATE_OF                        = "UPDATE OF";

    public static final String   DAYS_OF_THE_WEEK[]               = 
    {
        "Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"                      
    }
    ;
    public static final String[] TIME_UNITS                       = 
    {
        "Hours", "Minutes", "Seconds"
    }
    ;

    public final static int      MANUAL_EVENT                     = 1;
    public final static int      CONDITION_EVENT                  = 2;
    public final static int      SCHEDULE_EVENT                   = 3;
    public static final String   SYSEVENT_TYPE_MAPPING[]		  = 
    {
        "GrowDB", "GrowLog", "GrowTemp", "DatabaseStart", "ServerIdle", "Connect", "Disconnect", "ConnectFailed", "RAISERROR", "BackupEnd", "DBDiskSpace", "LogDiskSpace", "TempDiskSpace", "GlobalAutoincrement" 
    }
    ;
    public static final String   CREATE_EVENT                     = "CREATE EVENT";
    public static final String   HANDLER                          = "HANDLER";
    public static final String   AT                               = "AT";
    public static final String   ENABLE                           = "ENABLE";
    public static final String   DISABLE                          = "DISABLE";
    public static final String   ALL_LOCATION                     = "ALL";
    public static final String   CONSOLIDATED_LOCATION            = "CONSOLIDATED";
    public static final String   REMOTE_LOCATION                  = "REMOTE";
    public static final String   LOCATION_LIST[]                  = 
    {
        ALL_LOCATION, CONSOLIDATED_LOCATION,
            REMOTE_LOCATION                                       
    }
    ;
    public static final String   SCHEDULE                         = "SCHEDULE";
    public static final String   BETWEEN                          = "BETWEEN";
    public static final String   AND                              = "AND";
    public static final String   START_TIME                       = "START TIME";
    public static final String   START_DATE                       = "START DATE";
    public static final String   EVERY                            = "EVERY";
    public static final String   TYPE                             = "TYPE";
    public static final String   EVENT_CONDITION                  = "EVENT_CONDITION";
    public static final String   WHERE                             = "WHERE";


    public final static int      START_TIME_TYPE_AT               = 1;
    public final static int      START_TIME_TYPE_BTWN             = 2;
    //Connect, Disconnect, and RAISERROR can only used under quoted identifier on mode
    public static final String   SYSTEM_EVENTS_LIST[]             = 
    {
        "BackupEnd", "\"Connect\"", "ConnectFailed",
            "DatabaseStart", "DBDiskSpace", "\"Disconnect\"", "GlobalAutoincrement", "GrowDB", "GrowLog", "GrowTemp",
            "LogDiskSpace", "\"RAISERROR\"", "ServerIdle", "TempDiskSpace"
    }
    ;

    public static final String   SYSTEM_EVENTS_DESC_LIST[]             = 
    {
        "Backup completed", "User connected", "Connect failed",
            "Database started", "Database diskSpace checked", "User disconnected", 
            "Global autoincrement near end of range", 
            "Database file extended", "Transaction log extended", "Temprory file extended",
            "Transaction log diskspace checked", "RAISERROR issued", 
            "Server idle", "Temprory diskSpace checked"
    }
    ;

    public static final String   INTERVAL                         = "Interval";
    public static final String   DBFREEPERCENT                    = "DBFreePercent";
    public static final String   DBFREESPACE                      = "DBFreeSpace";
    public static final String   REMAINNGVALUES                   = "RemainingValues";
    public static final String   DBSIZE                           = "DBSize";
    public static final String   LOGSIZE                          = "LogSize";
    public static final String   TEMPSIZE                         = "TempSize";
    public static final String   LOGFREEPERCENT                   = "LogFreePercent";
    public static final String   LOGFREESPACE                     = "LogFreeSpace";
    public static final String   ERRORNUMBER                      = "ErrorNumber";
    public static final String   IDLETIME                         = "IdleTime";
    public static final String   TEMPFREEPERCENT                  = "TempFreePercent";
    public static final String   TEMPFREESPACE                    = "TempFreeSpace";


    public static final String   OPERATORS[]                      = 
    {
        "", "=", "<>", "<", "<=", ">", ">="
    }
    ;
    public static final String   BackupEnd_CONDITIONS[]           = 
    {
        "",INTERVAL
    }
    ;
    public static final String   Connect_CONDITIONS[]             = 
    {
        "",INTERVAL
    }
    ;
    public static final String   ConnectFailed_CONDITIONS[]       = 
    {
        "",INTERVAL
    }
    ;
    public static final String   DatabaseStart_CONDITIONS[]       = 
    {
        "",INTERVAL
    }
    ;
    public static final String   DBDiskSpace_CONDITIONS[]         = 
    {
        "",INTERVAL, DBFREEPERCENT, DBFREESPACE
    }
    ;
    public static final String   Disconnect_CONDITIONS[]          = 
    {
        "",INTERVAL
    }
    ;
    public static final String   GlobalAutoincrement_CONDITIONS[] = 
    {
        "",INTERVAL, REMAINNGVALUES
    }
    ;
    public static final String   GrowDB_CONDITIONS[]              = 
    {
        "",INTERVAL, DBSIZE
    }
    ;
    public static final String   GrowLog_CONDITIONS[]             = 
    {
        "",INTERVAL, LOGSIZE
    }
    ;
    public static final String   GrowTemp_CONDITIONS[]            = 
    {
        "",INTERVAL, TEMPSIZE
    }
    ;
    public static final String   LogDiskSpace_CONDITIONS[]        = 
    {
        "",INTERVAL, LOGFREEPERCENT, LOGFREESPACE
    }
    ;
    public static final String   RAISERROR_CONDITIONS[]           = 
    {
        "",INTERVAL, ERRORNUMBER
    }
    ;
    public static final String   ServerIdle_CONDITIONS[]          = 
    {
        "",INTERVAL, IDLETIME
    }
    ;
    public static final String   TempDiskSpace_CONDITIONS[]       = 
    {
        "",INTERVAL, TEMPFREEPERCENT, TEMPFREESPACE
    }
    ;
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    public static final int EVENT_TYPE_GLOBALAUTOINCREMENT = 13;
    public static final int EVENT_TYPE_TEMPDISKSPACE = 12;
    public static final int EVENT_TYPE_LOGDISKSPACE = 11;
    public static final int EVENT_TYPE_DBDISKSPACE = 10;
    public static final int EVENT_TYPE_RAISERROR = 9;
    public static final int EVENT_TYPE_BACKUPEND = 8;
    public static final int EVENT_TYPE_CONNECTFAILED = 7;
    public static final int EVENT_TYPE_DISCONNECT = 6;
    public static final int EVENT_TYPE_CONNECT = 5;
    public static final int EVENT_TYPE_SERVERIDLE = 4;
    public static final int EVENT_TYPE_DATABASESTART = 3;
    public static final int EVENT_TYPE_GROWTEMP = 2;
    public static final int EVENT_TYPE_GROWLOG = 1;
    public static final int EVENT_TYPE_GROWNDB = 0;
    
    /**
     * Used for event schedule FORMATTING only 
     */
    public DateFormat          DATE_FORMAT         = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
    public DateFormat          TIME_FORMAT         = new SimpleDateFormat("HH:mm");

}
