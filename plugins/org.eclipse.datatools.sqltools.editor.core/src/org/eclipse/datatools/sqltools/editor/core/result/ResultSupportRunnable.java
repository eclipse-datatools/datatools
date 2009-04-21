/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.editor.core.result;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.SQLEditorService;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionInitializer;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * This is an utility class, that intended to be used by other parts of the system that want to utilize the result view
 * to display their run information.
 * 
 * Child class should override certain methods of this class.
 * TODO add extension point for this class
 * @author Yang Liu
 */
public abstract class ResultSupportRunnable extends Job implements Runnable
{
	static String _LINESEPARATOR = System.getProperty("line.separator");
    protected OperationCommand _parentOperCommand;
    /**
     * @param name
     */
    public ResultSupportRunnable(String name, IProgressMonitor parentMonitor, DatabaseIdentifier databaseIdentifier)
    {
        super(name == null ? Messages.ResultSupportRunnable_name
				: name);
        _databaseIdentifier = databaseIdentifier;
        _parentMonitor = parentMonitor;
    }

    private static int    TASK_TOTAL         = 100;
    private static int    TASK_CONNECTION    = 10;
    private static int    TASK_STATEMENT     = 10;
    private static int    TASK_RUN           = 50;
    private static int    TASK_ITERATE       = 30;

    protected Statement _stmt;

	protected boolean _terminated = false;

	protected IProgressMonitor _monitor = null;

	protected IProgressMonitor _parentMonitor = null;

	protected DatabaseIdentifier _databaseIdentifier = null;

	protected Runnable _connKiller = null;

	protected ResultsViewAPI resultsViewAPI = ResultsViewAPI.getInstance();

	protected int                   _lastUpdateCount   = -1;
	
	protected OperationCommand _operationCommand = null;
	protected int _actionType = -1;
	protected String _consumerName = null;
	protected boolean _needsInitConnection = true;
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.internal.jobs.InternalJob#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
    protected IStatus run(IProgressMonitor monitor)
    {
        if (monitor == null)
        {
            monitor = new NullProgressMonitor();
        }
        _monitor = monitor;

        monitor.beginTask(Messages.ResultSupportRunnable_name, TASK_TOTAL);
        monitor.subTask(Messages.ResultSupportRunnable_task_connection);
        if(_parentOperCommand == null)
        {
    		resultsViewAPI.createNewInstance(getOperationCommand(), getTerminateHandler());
        }
        else
        {    
            resultsViewAPI.createSubInstance(_parentOperCommand, getOperationCommand(), getTerminateHandler());
        }
		
        Connection connection = getConnection();

        initConnection(connection);

        try
        {
            monitor.worked(TASK_CONNECTION);
            if (monitor.isCanceled())
            {
                terminateExecution();
                return Status.CANCEL_STATUS;
            }
            monitor.subTask(Messages.ResultSupportRunnable_task_statement);

            try
            {
                _stmt = prepareStatement(connection);
                
                //try-catch block is used to catch exception considering some database (avaki) can't use this method.
                try
                {
                    _stmt.setMaxFieldSize(16384);
                }
                catch (Exception e)
                {
                    //ignore
                }                
                monitor.worked(TASK_STATEMENT);
                if (monitor.isCanceled())
                {
                    terminateExecution();
                    return Status.CANCEL_STATUS;
                }
                monitor.subTask(Messages.ResultSupportRunnable_task_run);
            }
            catch (Throwable th)
            {
        		synchronized (getOperationCommand()) {
        			resultsViewAPI.appendThrowable(getOperationCommand(), th);
        			resultsViewAPI.appendStatusMessage(getOperationCommand(), th.getMessage());
        			resultsViewAPI.updateStatus(getOperationCommand(), OperationCommand.STATUS_FAILED);
        		}
                return Status.CANCEL_STATUS;
            }

            boolean moreResult = false;
            try
            {
                moreResult = runStatement(_stmt);
                monitor.worked(TASK_RUN);
                if (monitor.isCanceled())
                {
                    terminateExecution();
                    return Status.CANCEL_STATUS;
                }
                monitor.subTask(Messages.ResultSupportRunnable_task_iterate);
            }
            catch (Throwable th)
            {
                resultsViewAPI.appendThrowable(getOperationCommand(), th);
                if (th instanceof SQLException)
                {
                    handleSQLException((SQLException) th);
                }
                else
                {
                	synchronized (getOperationCommand()) {
	                	resultsViewAPI.appendStatusMessage(getOperationCommand(), th.getMessage());
	                	resultsViewAPI.updateStatus(getOperationCommand(), OperationCommand.STATUS_FAILED);
                	}
                }
                return Status.CANCEL_STATUS;
            }
            boolean success = handleSuccess(moreResult);
            if (success)
            {
                monitor.worked(TASK_ITERATE);
            }
            else
            {
                return Status.CANCEL_STATUS;
            }
        }
        finally
        {
            handleEnd(connection, _stmt);
            monitor.done();
        }

        return Status.OK_STATUS;
    }

    protected void initConnection(Connection connection)
    {
        //obtain the killer before execution to ensure we can get the connection id
        _connKiller = SQLToolsFacade.getConfiguration(null, _databaseIdentifier).getConnectionService().getConnectionKiller(_databaseIdentifier, connection);

        if (_needsInitConnection)
        {
            //MUST initialize this connection after getting the connection killer.
            IConnectionInitializer init = SQLToolsFacade.getConfiguration(null, _databaseIdentifier).getConnectionService().getConnectionInitializer();
            if (init != null)
            {
                //if the configuration is null, will use the default options to initialize
                init.init(_databaseIdentifier, connection, getConfiguration());
            }
        }
    }
    
    public void setNeedsInitConnection(boolean needInit)
    {
    	this._needsInitConnection = needInit;
    }

    public void run()
    {
        run(null);
    }

    /**
     * @return
     */
    protected abstract Connection getConnection();


    /**
     * Creates the default <code>OperationCommand</code> object which will be
     * used for results view display.
     */
    protected abstract OperationCommand createDefaultOperationCommand();
    
    /**
     * Creates the <code>OperationCommand</code> object which will be
     * used for results view display.
     */
	public OperationCommand getOperationCommand() {
		if (_operationCommand == null)
		{
			_operationCommand = createDefaultOperationCommand();
		}
		return _operationCommand;
	}

    /**
     * Returns the parent operation command
     * @return
     */
    public OperationCommand getParentOperationCommand()
    {
        return _parentOperCommand;
    }

    /**
     * @return
     * @throws SQLException
     */
    protected abstract Statement prepareStatement(Connection connection) throws SQLException;

    /**
     * @param stmt
     * @return <code>true</code> if the first result is a <code>ResultSet</code> object; <code>false</code> if it
     *         is an update count or there are no results
     * @throws SQLException
     */
    protected abstract boolean runStatement(Statement stmt) throws SQLException;

    /**
     * by default do nothing, child class should override to do things such as close statemnt or connection (if needed)
     * 
     * @param connection
     * @param stmt can be null.
     */
    protected void handleEnd(Connection connection, Statement stmt)
    {
    }

    /**
     *  
     */
    protected boolean handleSuccess(boolean moreResult)
    {
        if (isTerminated() || isCanceled())
        {
            return false;
        }

        try
        {
            loopThroughResults(_stmt, moreResult);
        	synchronized (getOperationCommand()) {
        		resultsViewAPI.updateStatus(getOperationCommand(), OperationCommand.STATUS_SUCCEEDED);
        	}
        }
        catch (SQLException ex)
        {
            resultsViewAPI.appendThrowable(getOperationCommand(), ex);
        	synchronized (getOperationCommand()) {
        		resultsViewAPI.appendStatusMessage(getOperationCommand(), ex.getMessage());
        		resultsViewAPI.updateStatus(getOperationCommand(), OperationCommand.STATUS_FAILED);
        	}
            return false;
        }
        return true;
    }

    /**
     * @param exception
     */
    protected void handleSQLException(SQLException exception)
    {
    	resultsViewAPI.appendStatusMessage(getOperationCommand(), exception.getMessage());

        if (isTerminated() || isCanceled())
        {
            return;
        }
        try
        {
            loopThroughResults(_stmt, _stmt.getMoreResults());
        }
        catch (SQLException ex)
        {
            // ignore exception when looping results, since we already got a more important exception
        }
    	synchronized (getOperationCommand()) {
			resultsViewAPI.updateStatus(getOperationCommand(),
					OperationCommand.STATUS_FAILED);
		}
    }

    /**
     * default return null
     * 
     * @return
     */
    protected Runnable getTerminateHandler()
    {
        Runnable run = new Runnable()
        {
            public void run()
            {
                if (isTerminated())
                {
                    return;
                }
                synchronized (getOperationCommand())
                {
                    if (_stmt != null)
                    {
                        try
                        {
                            _stmt.cancel();
                        }
                        catch (Exception e)
                        {
                        	EditorCorePlugin.getDefault().log(e);
                        }
                    }
                    //put togeter to be sync
                    _terminated = true;
                	synchronized (getOperationCommand()) {
                		resultsViewAPI.updateStatus(getOperationCommand(), OperationCommand.STATUS_TERMINATED);
                	}
                }

                //canceled from parent monitor
                if (_monitor != null && !_monitor.isCanceled())
                {
                    ResultSupportRunnable.this.cancel();
                }

                //FIXME:The following code is intended to kill the connction forcefully. That's because
                //even if we kill the job, or close the connection, or leave eclipse, a javaw.exe
                //process which takes up a lot CPU and memory resource can always be found
                //in windows task manager if we have issued a command containing infinite loop.
                if (_connKiller != null)
                {
                    //do not start a Thread here to avoid kill my own process, because we get the connection
                    //from a pool.
                    _connKiller.run();
                }
            }
        }
        ;
        return run;
    }
    
    /**
     * This method is to set if looping through results is needed, for some database servers 
     * don't support multi-statements execution. 
     *  
     * @return true if looping through results is needed.
     */
    protected boolean needLoopThroughResults()
    {
        return true;
    }
    
    /**
     * loop through the results (update count and result set), push the results into the result instance. Here attention
     * should be paid to the statement status, because the status of statement is different after running normal sql
     * command such as select operation and after running stored procedure.
     * 
     * @param cstmt
     * @param moreResult
     * @throws SQLException
     */
    public void loopThroughResults(Statement cstmt, boolean moreResult)
        throws SQLException
    {
        boolean hasException = false;//if there are some Exception, we should thrown it out to triger finishFail
        boolean lastException = false;//if the last time is an Exception, we can't getResultSet immediately,should use
        // getMoreResult()
        SQLException exception = null;
        ResultSet rs = null;
        ArrayList updateCountList = new ArrayList();//to keep the updateCount number temporarily
        while (!isTerminated() && needLoopThroughResults())
        {
            if (isCanceled())
            {
                terminateExecution();
                throw new SQLException(Messages.ResultSupportRunnable_exception_terminated);
            }
            int updateCount = 0;
            if (!lastException)
            {
                try
                {
                    if (moreResult)
                    {
                        rs = cstmt.getResultSet();
                        if (rs != null)
                        {
                            if(_lastUpdateCount != -1)
                            {
                            	resultsViewAPI.appendUpdateCountMessage(getOperationCommand(), _lastUpdateCount);
                                _lastUpdateCount = -1;
                            }
                        	resultsViewAPI.appendResultSet(getOperationCommand(), rs);
                        }
                    }
                    updateCount = cstmt.getUpdateCount();
                    if (updateCount >= 0)
                    {
                        updateCountList.add(new Integer(updateCount));
                        if(_lastUpdateCount != -1)
                        {
                        	resultsViewAPI.appendUpdateCountMessage(getOperationCommand(), _lastUpdateCount);
                        }
                        _lastUpdateCount = updateCount;
                    }
                    if (updateCount >= 0 || rs != null)
                    {
                        moreResult = cstmt.getMoreResults();
                        rs = null;
                        continue;
                    }
                    break;
                }
                catch (SQLException ex)
                {
                	resultsViewAPI.appendStatusMessage(getOperationCommand(), ex.getMessage());
                    exception = ex;
                    hasException = true;
                    lastException = true;
                    boolean isClosed = cstmt == null || getConnection() == null;
                    if (!isClosed)
                    {
                        try
                        {
                            getConnection().getMetaData();
                        }
                        catch (SQLException e)
                        {
                            isClosed = true;                        
                        }
                    }
                    if (isClosed)
                    {
                        break;
                    }
                }
            }

            try
            {
                moreResult = cstmt.getMoreResults();
                lastException = false;
            }
            catch (SQLException ex)
            {
                //Hui Cao: when there're 2 continuous exceptions with the same SQL state, we need to break to avoid deadloop.
                //Although this is not a exact condition of deadloop, we have to compromise. 
                if (ex.getSQLState() != null && exception != null && ex.getSQLState().equals(exception.getSQLState()))
                {
                    break;
                }
            	resultsViewAPI.appendStatusMessage(getOperationCommand(), ex.getMessage());

                exception = ex;
                hasException = true;
                lastException = true;
                boolean isClosed = cstmt == null || getConnection() == null;
                if (!isClosed)
                {
                    try
                    {
                        getConnection().getMetaData();
                    }
                    catch (SQLException e)
                    {
                        isClosed = true;                        
                    }
                }
                if (isClosed)
                {
                    break;
                }
            }
        }
        /**
         * The following code is to handle the updateCount. if it's a CallableStatement,we should discard the last one
         * because it's duplicated. From experiment we know that this is a defect of JConnect.
         */
        int count = updateCountList.size();

        if (!(cstmt instanceof CallableStatement))
        {
            if (_lastUpdateCount != -1)
            {
            	resultsViewAPI.appendUpdateCountMessage(getOperationCommand(), _lastUpdateCount);
                _lastUpdateCount = -1;
            }
        }
        else
        {
            /**
             * If the last two update count equal to each other, we discard the last one. Else display the last one.
             */
            if (count < 2
            || ((Integer) updateCountList.get(count - 1)).intValue() != ((Integer) updateCountList
                .get(count - 2)).intValue())
            {
                if (_lastUpdateCount != -1)
                {
                	resultsViewAPI.appendUpdateCountMessage(getOperationCommand(), _lastUpdateCount);
                    _lastUpdateCount = -1;
                }
            }
        }
        //we must have these lines to throw exceptions out,
        //or some errors will not be displayed in result set view
        if (hasException)
        {
            throw exception;
        }
    }

    /**
     * This method will check whether this job has already been terminated.
     * 
     * @return true if already terminated. false means not terminated.
     */
    protected boolean isTerminated()
    {
        return _terminated;
    }

    /**
     * This method will check whether this job or its parent job has already been canceled.
     * 
     * @return true if already canceled. false means not canceled.
     */
    protected boolean isCanceled()
    {
        return (_monitor != null && _monitor.isCanceled()) || (_parentMonitor != null && _parentMonitor.isCanceled());
    }

    /**
     * When the job is canceled from UI, synchorinize the statement status and the job status.
     *  
     */
    public void terminateExecution()
    {
        //terminate the statement
        getTerminateHandler().run();
    }

    abstract public ILaunchConfiguration getConfiguration();

    public DatabaseIdentifier getDatabaseIdentifier()
    {
        return _databaseIdentifier;
    }
    
    public int getOperationStatus()
    {
    	return resultsViewAPI.getCurrentStatus(getOperationCommand());
    }

    public void setParentOperCommand(OperationCommand operCommand)
    {
        _parentOperCommand = operCommand;
    }
    

	/**
	 * Gets the action type for <code>OperationCommand</code>. The default
	 * value is OperationCommand.ACTION_EXECUTE.
	 * 
	 */
	public int getActionType() {
		if (_actionType == -1)
		{
			_actionType = OperationCommand.ACTION_EXECUTE;
		}
		return _actionType;
	}

	/**
	 * Sets the action type for <code>OperationCommand</code>. The default
	 * value is OperationCommand.ACTION_EXECUTE.
	 * 
	 * @param type
	 * @see OperationCommand
	 */
	public void setActionType(int type) {
		_actionType = type;
	}

	/**
	 * Gets the consumer name for <code>OperationCommand</code>. The default
	 * value is "SQL Editor".
	 * 
	 */
	public String getConsumerName() {
		if (_consumerName == null)
		{
			_consumerName = Messages.sqlEditorName;
		}
		return _consumerName;
	}

	/**
	 * Sets the consumer name for <code>OperationCommand</code>. The default
	 * value is "SQL Editor".
	 * 
	 * @param type
	 * @see OperationCommand
	 */
	public void setConsumerName(String name) {
		_consumerName = name;
	}

    
}
