/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.OperationCommand;

/**
 * Represents a result in SQL Results View. There are two groups of methods defined in this interface: <ll>
 * <li>Methods that updates the result instance. Adding more information to it. They are called by the
 * <code>ResultsViewAPI</code>.</li>
 * <li>Methods that get information of the result instance.</li>
 * </ll>
 * <p>
 * An instance of <code>IResultInstance</code> contains a list of <code>ResultItem</code> instances and a list of
 * <code>Parameter</code> instances.
 * 
 * @see org.eclipse.datatools.sqltools.result.Parameter
 * @see org.eclipse.datatools.sqltools.result.model.ResultItem
 * @author Dafan Yang
 */
public interface IResultInstance extends Serializable
{
    /**
     * Appends a plain message. For example, through "print" in the SQL code.
     * 
     * @param text the plain message
     */
    public void morePlainMessage(String text);

    /**
     * Appends a status message, which will be displayed on 'Status' tab when using multiple tabs display mode
     * 
     * @param message the status message
     */
    public void moreStatusMessage(String message);
    
    /**
     * Appends a new update count.
     * 
     * @param updateCount the update count
     */
    public void moreUpdateCount(int updateCount);

    /**
     * Updates the status of this result instance
     * 
     * @param status the new status
     * @see OperationCommand#STATUS_STARTED
     * @see OperationCommand#STATUS_RUNNING
     * @see OperationCommand#STATUS_SUCCEEDED
     * @see OperationCommand#STATUS_FAILED
     * @see OperationCommand#STATUS_TERMINATED
     * @see OperationCommand#STATUS_WARNING
     * @see OperationCommand#STATUS_CRITICAL_ERROR
     */
    public void updateStatus(int status);
    
    /**
     * Shows the given parameters on SQL Results View
     * 
     * @see org.eclipse.datatools.sqltools.result.Parameter
     * @param params the <code>Parameter</code> instances list
     */
    public void showParameters(List params);
    
    /**
     * Appends a new result set.
     * 
     * @param resultset instance of JDBC <code>ResultSet</code>
     * @throws SQLException - if a database access error occurs
     */
    public void moreResultSet(ResultSet resultset) throws SQLException;
    
    /**
     * Appends a new result set.
     * 
     * @param resultset the <code>IResultSetObject</code> instance
     */
    public void moreResultSet(IResultSetObject resultset);

    /**
     * Appends a new result item.
     * 
     * @param item the result item
     */
    public void moreResultItem(ResultItem item);

    /**
     * Returns the operation command that produces this result instance.
     * 
     * @return the operation command
     */
    public OperationCommand getOperationCommand();

    /**
     * Returns number of result items. Including result sets, messages, update counts.
     * 
     * @return result item count
     */
    public int getItemCount();

    /**
     * Returns the result item at given index (base on 0)
     * 
     * @param index the index
     * @return the result item
     */
    public ResultItem getItem(int index);
    
    /**
     * Returns the current status.
     * 
     * @return the current status of this result
     * @see OperationCommand#STATUS_RUNNING
     * @see OperationCommand#STATUS_SUCCEEDED
     * @see OperationCommand#STATUS_FAILED
     * @see OperationCommand#STATUS_TERMINATED
     * @see OperationCommand#STATUS_STARTED
     * @see OperationCommand#STATUS_CRITICAL_ERROR
     * @see OperationCommand#STATUS_WARNING
     */
    public int getStatus();

    /**
     * Checks if this result is finished
     * @return <code>true</code> if this instance finishs
     */
    public boolean isFinished();

    /**
     * Terminates the instance.
     */
    public void terminate();

    /**
     * Disposes resources after the instance terminated.
     */
    public void dispose();

    /**
     * Returns the time in string this result instance was created
     *  
     * @return the execution time
     */
    public String getExecuteTime();  
    
    /**
     * Returns the date-time this result instance was created
     *  
     * @return the execution date-time
     */
    public Date getExecuteDate();  
    
    /**
     * Clears all the result items and parameters
     */
    public void resetInstance();
    
    /**
     * Checks if there is a terminate handler 
     * @return <code>true</code> if there is a terminate handler
     */
    public boolean hasTerminateHandler();
    
    /**
     * Returns the parameters
     * @return the <code>Parameter</code> instances list
     */
    public List getParameters();
    
    /**
     * Returns the execution frequency
     * @return the execution frequency
     */
    public int getFrequency();
    
    /**
     * Inceases the execution frequency by 1, will only be called when this instance is re-executing
     * 
     */
    public void increaseFrequency();
    
    /**
     * Returns the exceptions thrown when generating the result instance
     * @return the exceptions thrown
     */
    public Throwable[] getFailThrowables();
    
    /**
     * Appends an exception
     * @param th the exception
     */
    public void moreThrowable(Throwable th);
    
    /**
     * Returns the sub-results of this result
     * @return the sub-results
     */
    public List getSubResults();
    
    /**
     * Creates a sub results
     * @param cmd the operation request object of the new result
     * @param terminateHandler the terminate handler
     */
    public void createSubResult(OperationCommand cmd, Runnable terminateHandler);
    
    /**
     * Returns the parent result of this result
     * @return the parent result
     */
    public IResultInstance getParentResult();
    
    /**
     * Checks if the result instance is a parent result
     * @return <code>true</code> if it is a parent result, <code>false</code> if it is a sub-result
     */
    public boolean isParentResult();
    
    /**
     * Calculates the status of this result instance based on the status of its sub-results
     * @return the status of this result instance
     */
    public int calculateStatus();
    
    
    /**
     * Return the current flag value which indicates that this IResultInstance object may have sub results.
     * @return the current flag value
     */
    public boolean isMayHaveSubResults();
    
    /**
     * Set new value to the flag which indicates that this IResultInstance object may have sub results.
     * @param flag the new flag value
     */
    public void setMayHaveSubResults(boolean flag);
}
