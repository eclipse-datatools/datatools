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
package org.eclipse.datatools.sqltools.sqleditor.result;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker;
import org.eclipse.datatools.sqltools.editor.core.result.ResultSupportRunnable;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;
import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * This is a simple ResultSupportRunnalbe, used to run a simple SQL statement. It do not support things like showing SP
 * return value, output parameter, etc.
 * 
 * @author Yang Liu
 */
public class SimpleSQLResultRunnable extends ResultSupportRunnable
{
	protected static final int SQL_SHORT_DESC_LENGTH = 40;
	protected Connection         _connection;
    protected String             _sql;
    protected boolean            _closeCon;
    protected int                _connid;
    protected IConnectionTracker _tracker;
    protected boolean            _promptVar = false;
    protected ILaunchConfiguration _configuration;

//    /**
//     * 
//     * @param con
//     * @param sql
//     * @param closeCon
//     * @param tracker
//     * @param profileName
//     * @param configuration
//     */
//    public SimpleSQLResultRunnable(Connection con, String sql, boolean closeCon, IConnectionTracker tracker,
//        DatabaseIdentifier databaseIdentifier, ILaunchConfiguration configuration)
//    {
//        this(con, sql, closeCon, tracker, null, databaseIdentifier, configuration);
//    }
//    /**
//     * 
//     * @param con the connection
//     * @param sql the SQL statement to be run
//     * @param closeCon whether should close connection
//     * @param tracker if closeCon is true and tracker is not null, will notify it when close the connection
//     */
//    public SimpleSQLResultRunnable(Connection con, String sql, boolean closeCon, IConnectionTracker tracker,
//        DatabaseIdentifier databaseIdentifier)
//    {
//        this(con, sql, closeCon, tracker, null, databaseIdentifier, null);
//    }

    /**
     * Constructs a <code>SimpleSQLResultRunnable</code> to run the specified sql statement.
     * @param con the connection
     * @param sql the SQL statement to be run
     * @param closeCon whether should close connection
     * @param tracker if closeCon is true and tracker is not null, will notify it when close the connection
     * @param parentMonitor the parent monitor, used to cancel
     * @param databaseIdentifier contains connection information
     * @param configuration the launch configuration if this constructor is called by eclipse launch mechanism, or null. 
     */
    public SimpleSQLResultRunnable(Connection con, String sql, boolean closeCon, IConnectionTracker tracker,
        IProgressMonitor parentMonitor, DatabaseIdentifier databaseIdentifier, ILaunchConfiguration configuration)
    {
        super(SQLUtil.describeSQL(sql, SQL_SHORT_DESC_LENGTH), parentMonitor, databaseIdentifier);
        _connection = con;
        _sql = sql;
        _closeCon = closeCon;
        _tracker = tracker;
        _configuration = configuration;
        _connid = SQLToolsFacade.getConnectionId(databaseIdentifier, _connection);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.launching.ResultSupportRunnable#getConnection()
     */
    protected Connection getConnection()
    {
        return _connection;
    }

    protected void setConnection(Connection conn)
    {
        _connection = conn;
    }
    
    protected OperationCommand createDefaultOperationCommand() {
		return new OperationCommand(getActionType(), _sql, getConsumerName(), _databaseIdentifier.getProfileName(), _databaseIdentifier.getDBname());
	}

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.launching.ResultSupportRunnable#prepareStatement(java.sql.Connection)
     */
    protected Statement prepareStatement(Connection connection) throws SQLException
    {
        return connection.createStatement();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.launching.ResultSupportRunnable#runStatement(java.sql.Statement)
     */
    protected boolean runStatement(Statement stmt) throws SQLException
    {
        return stmt.execute(_sql);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.launching.ResultSupportRunnable#handleEnd(java.sql.Connection, java.sql.Statement)
     */
    protected void handleEnd(Connection connection, Statement stmt)
    {
        try
        {
            if (_tracker != null)
            {
                _tracker.connectionAboutToBeClosed();
            }

            if (stmt != null)
            {
                stmt.close();    
            }
            //Hui Cao: since terminating is a separate thread, even though connection is not closed during the evaluation, it might be later.
            //so as long as user gestures to terminate, we won't do the cleaning job again. 
            //Shifeng Yu: here we can not invoke "connection.isClosed()", because jConnector will run sp_mda to 
            //test whether the connection is closed. Running the sp_mds will cause that the debugger doest not
            //finish in the end of debugged sp and goes on debugging in sp_mda.
            if (connection != null && !isTerminated() && !isCanceled())
            {
                //Shifeng Yu:
                //in original code,before connection.close(),we do init.revert(getProfileName(), connection);
                //but this will cause ASIQ debugger works abnormal. see cr414559.
                //in fact, It's not neccessary to do revert, because every connection from pool will be initiated
                //before we use it.
                if (_closeCon)
                {
                    SQLDevToolsConfiguration f = SQLToolsFacade.getConfigurationByProfileName(_databaseIdentifier
                            .getProfileName());
                    ConnectionService conService = f.getConnectionService();
                    conService.closeConnection(connection, _connid, _databaseIdentifier);
                }
            }
        }
        catch (Exception e)
        {
            // ignore
        }

        if (_closeCon)
        {
            if (_tracker != null)
            {
                _tracker.connectionClosed();
            }
        }
    }


    /**
     * @return Returns the _configuration.
     */
    public ILaunchConfiguration getConfiguration()
    {
        return _configuration;
    }

}