/**
 * Created on 2004-11-16
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.plan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;

/**
 * The base class which performs the operations to retrieve plan from database.
 * TODO: implement run(IProgress) and handleEnd
 * @author Hui Cao
 *  
 */
public abstract class PlanSupportRunnable extends Job
{
    protected PlanRequest        _request;
    protected Connection         _conn;
    protected String             _plan;
    protected Statement          _stmt;
    protected IPlanInstance      _planInstance;
    protected Map                _varDecs;
    protected String _profileName;
    protected String _dbName;

    public PlanSupportRunnable()
    {
        super(Messages.PlanSupportRunnable_getplan_name);
    }
    /**
     * @param request
     * @param profileName
     */
    public PlanSupportRunnable(PlanRequest request, String profileName, String dbName)
    {
        super(Messages.PlanSupportRunnable_getplan_name);
        this._request = request;
        this._profileName = profileName;
        this._dbName = dbName;
    }

    /**
     * The default behaviour is just to call connection.createStatement(). Subclass is free to provide their own
     * implementations.
     * 
     * @param connection
     * @return @throws SQLException
     */
    protected Statement prepareStatement(Connection connection) throws SQLException
    {
        return connection.createStatement();
    }

    /**
     * Subclass should override this method to provide database-specific operations to get plan.
     * 
     * @param stmt
     * @return the plan
     * @throws SQLException
     */
    protected abstract String explainPlan(Statement stmt) throws SQLException;

    /**
     * @return
     */
    protected String getPlan()
    {
        return _plan;
    }

    /**
     * The plan is retrieved successfully
     */
    protected void handleSuccess()
    {
        _planInstance.finishSuccess(getPlan());
    }

    /**
     * Cleaning up
     * 
     * @param connection
     * @param stmt
     */
    protected abstract void handleEnd(Connection connection, Statement stmt);


    /**
     * Retrieve plan from the result set on the assumption that there's only one row in the result set and the plan is
     * the <code>columnIndex</code> column.
     * 
     * @param rs
     * @param columnIndex starting from 1
     * @throws SQLException
     */
    protected String retrievePlan(ResultSet rs, int columnIndex) throws SQLException
    {
        String plan = null;
        if (rs.next())
        {
            plan = rs.getString(columnIndex);
        }
        rs.close();
        return plan;
    }

    public Connection getConnection()
    {
        return _conn;
    }

    public PlanRequest getRequest()
    {
        return _request;
    }

    /**
     * @param _request The _request to set.
     */
    public void setRequest(PlanRequest _request)
    {
        this._request = _request;
    }

    public void setVarDecs(Map decs)
    {
        _varDecs = decs;
    }
    
    public IPlanInstance getPlanInstance()
    {
    	return _planInstance;
    }
}
