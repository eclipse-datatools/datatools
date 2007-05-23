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
package org.eclipse.datatools.sqltools.result.internal.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultSetObject;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;

/**
 * A standard implementation of <code>IResultInstance</code>
 * 
 * @author Dafan Yang
 */
public class ResultInstance implements IResultInstance
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long  serialVersionUID = 1L;
    private static ILogger     _log             = ResultsViewPlugin.getLogger(null);
    /* the operation command */
    private OperationCommand   _operationCommand;
    /* the manager */
    private IResultManager     _resultManager;
    /* the result item list */
    private List               _resultList;
    /* initial status is STATUS_STARTED */
    private int                _status          = OperationCommand.STATUS_STARTED;
    /* handler to terminate this instance */
    transient private Runnable _terminateHandler;
    private List               _parameters;
    private String             _date;

    private int                _execFrequency;
    
    private transient List     _throwables;
    
    private List               _subResults; 
    
    private IResultInstance    _parentResult;
    
    public ResultInstance(IResultManager resultmanager, OperationCommand command, Runnable terminateHandler)
    {
        this._resultManager = resultmanager;
        this._operationCommand = command;
        _resultList = new ArrayList(5);
        this._terminateHandler = terminateHandler;
        _date = ResultsConstants.FORMATTER.format(new Date());
        _execFrequency = 1;
        _subResults = new ArrayList(5);
    }
    
    public ResultInstance(IResultManager resultmanager, OperationCommand command, Runnable terminateHandler, IResultInstance parentResult)
    {
        this(resultmanager, command, terminateHandler);
        _parentResult = parentResult;
    }

    public void morePlainMessage(String msg)
    {
        if (msg == null)
        {
            msg = ""; //$NON-NLS-1$
        }
        moreResultItem(new ResultItem(msg, true));
    }

    public void moreStatusMessage(String msg)
    {
        if (msg == null)
        {
            msg = ""; //$NON-NLS-1$
        }
        moreResultItem(new ResultItem(msg, false));
    }

    public void moreUpdateCount(int updateCount)
    {
        if (updateCount < 0)
        {
            updateCount = 0;
        }
        moreResultItem(new ResultItem(updateCount));
    }

    public void moreResultSet(ResultSet resultset) throws SQLException
    {
        IResultSetObject r = null;
        try
        {
            // settings in preference page
            int maxRowCount = ResultsViewPlugin.getDefault().getPreferenceStore().getInt(
                    PreferenceConstants.SQL_RESULTS_VIEW_MAX_ROW_COUNT);
            int maxDisplayRowCount = ResultsViewPlugin.getDefault().getPreferenceStore().getInt(
                    PreferenceConstants.SQL_RESULTS_VIEW_MAX_DISPLAY_ROW_COUNT);
            r = new ResultSetObject(resultset, maxRowCount, maxDisplayRowCount);
        }
        catch (SQLException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            _log.error("ResultInstance_error_moreResultSet", e); //$NON-NLS-1$
            return;
        }
        moreResultItem(new ResultItem(r));
    }

    public void moreResultItem(ResultItem item)
    {
        _resultList.add(item);
        if (_resultManager != null)
        {
            _resultManager.fireAppended(this, item, _resultList.size() - 1);
        }
    }

    public OperationCommand getOperationCommand()
    {
        return _operationCommand;
    }

    public int getStatus()
    {
        return _status;
    }

    public boolean isFinished()
    {
        return (_status == OperationCommand.STATUS_FAILED || _status == OperationCommand.STATUS_SUCCEEDED
                || _status == OperationCommand.STATUS_CRITICAL_ERROR || _status == OperationCommand.STATUS_WARNING || _status == OperationCommand.STATUS_TERMINATED);
    }

    public ResultItem getItem(int index)
    {
        return (ResultItem) _resultList.get(index);
    }

    public int getItemCount()
    {
        return _resultList.size();
    }

    public void terminate()
    {
        if (_terminateHandler != null)
        {
            _terminateHandler.run();
        }
        _status = OperationCommand.STATUS_TERMINATED;
        
        // terminate all the sub-results
        Iterator iter = _subResults.iterator();
        while(iter.hasNext())
        {
            IResultInstance subIns = (IResultInstance)iter.next();
            if(!subIns.isFinished())
            {
                subIns.terminate();
            }
        }
        
        if (_resultManager != null)
        {
            _resultManager.fireStatusUpdated(this);
        }
    }
    
    public void dispose()
    {
        for (int i = 0; i < _resultList.size(); i++)
        {
            ResultItem ri = getItem(i);
            if (ri != null)
            {
                Object o = ri.getResultObject();
                if (o != null && o instanceof IResultSetObject)
                {
                    ((IResultSetObject) o).dispose();
                }
            }
        }
    }

    public String getExecuteTime()
    {
        return _date;
    }

    public void updateStatus(int status)
    {
        _status = status;
        if (_resultManager != null)
        {
            _resultManager.fireStatusUpdated(this);
        }
    }

    public void resetInstance()
    {
        _resultList = new ArrayList(5);
        _parameters = null;
        _status = OperationCommand.STATUS_STARTED;
        if (_resultManager != null)
        {
            _resultManager.fireInstanceReset(this);
        }
    }

    public void moreResultSet(IResultSetObject resultset)
    {
        moreResultItem(new ResultItem(resultset));
    }

    public void showParameters(List params)
    {
        _parameters = params;
        if (_resultManager != null)
        {
            _resultManager.fireParametersShow(this, _parameters);
        }
    }

    public boolean hasTerminateHandler()
    {
        return _terminateHandler != null;
    }

    public List getParameters()
    {
        return _parameters;
    }

    public int getFrequency()
    {
        return _execFrequency;
    }

    public void increaseFrequency()
    {
        _execFrequency ++;
        // Fire the status updated event to cause the result history to refresh itself
        if (_resultManager != null)
        {
            _resultManager.fireStatusUpdated(this);
        }
    }

    public Throwable[] getFailThrowables()
    {
        if(_throwables == null)
        {
            _throwables = new ArrayList();
        }
        return (Throwable[]) _throwables.toArray(new Throwable[_throwables.size()]);
    }

    public void moreThrowable(Throwable th)
    {
        if(_throwables == null)
        {
            _throwables = new ArrayList();
        }
        _throwables.add(th);
    }

    public void createSubResult(OperationCommand cmd, Runnable terminateHandler)
    {
        IResultInstance instance = new ResultInstance(_resultManager, cmd, terminateHandler, this);
        synchronized (_subResults)
        {
            _subResults.add(instance);
        }
        _resultManager.newSubResultCreated(cmd, instance);
    }

    public List getSubResults()
    {
        return _subResults;
    }

    public IResultInstance getParentResult()
    {
        return _parentResult;
    }

    public boolean isParentResult()
    {
        if(_parentResult == null)
        {
            return true;
        }
        return false;
    }

    public int calculateStatus()
    {
        if (getSubResults().size() == 0)
        {
            return getStatus();
        }
        Iterator iter = getSubResults().iterator();
        int severeFirstStatus = OperationCommand.STATUS_SUCCEEDED;
        while (iter.hasNext())
        {
            IResultInstance ins = (IResultInstance) iter.next();
            int status = ins.calculateStatus();
            // it should be STARTED or RUNNING if one of its sub-result is still started/running
            if (status == OperationCommand.STATUS_STARTED || status == OperationCommand.STATUS_RUNNING)
            {
                return status;
            }
            // it should be terminated if one of its sub-result is terminated
            if (status == OperationCommand.STATUS_TERMINATED)
            {
                return status;
            }
            if (status > severeFirstStatus)
            {
                severeFirstStatus = status;
            }
        }
        return severeFirstStatus;
    }
    
    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        stream.defaultReadObject();
        if (_subResults == null) 
        {
			_subResults = new ArrayList(5);
		}
        
        if(_resultList == null)
        {
        	_resultList = new ArrayList(5);
        }
    }
}
