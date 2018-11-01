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
package org.eclipse.datatools.sqltools.plan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * The base class which performs the operations to retrieve plan from database.
 * TODO: implement run(IProgress) and handleEnd
 * @author Hui Cao
 *  
 */
public abstract class PlanSupportRunnable extends Job
{
    protected PlanRequest _request;
    protected Connection  _conn;
    protected String      _rawPlanString;
    protected Statement   _stmt;
    protected String      _profileName;
    protected String      _dbName;
    protected boolean     _needReleaseConn = true;

    public PlanSupportRunnable()
    {
        super(Messages.PlanSupportRunnable_getplan_name);
    }
    
    /**
     * Constructor
     * @param request the plan request 
     * @param profileName the profile name
     * @param dbName the database name
     */
    public PlanSupportRunnable(PlanRequest request, String profileName, String dbName)
    {
        super(Messages.PlanSupportRunnable_getplan_name);
        this._request = request;
        this._profileName = profileName;
        this._dbName = dbName;
        request.setProfileName(profileName);
        request.setDbName(dbName);
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
     * @param stmt the statement
     * @return the raw plan string
     * @throws SQLException if database errors occur
     */
    protected abstract String explainPlan(Statement stmt) throws SQLException;

    /**
     * Returns the raw plan string
     * @return the raw plan string
     */
    protected String getPlan()
    {
        return _rawPlanString;
    }

    /**
     * The plan is retrieved successfully
     */
    protected void handleSuccess()
    {
        EPVFacade.getInstance().planGenerated(_request, getPlan());
    }

    /**
     * Cleaning up
     * 
     * @param connection
     * @param stmt
     */
    protected void handleEnd(Connection connection, Statement stmt)
    {
        if (!_needReleaseConn)
        {
            return;
        }
        try
        {
            if(stmt != null)
            {
                stmt.close();
            }
            if(connection != null && !connection.isClosed())
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            
        }
    }


    /**
     * Retrieves plan from the result set on the assumption that there's only one row in the result set and the plan is
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

    /**
     * Passes a connection in, otherwise this class will create a new connection to get the plan
     * @param conn database connection
     */
    public void setConnection(Connection conn)
    {
        _conn = conn;
        
        // Dont release the connection if it is not newly-created
        _needReleaseConn = false;
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

    protected IStatus run(IProgressMonitor monitor)
    {
        if (monitor == null)
        {
            monitor = new NullProgressMonitor();
        }
        boolean insCreated = EPVFacade.getInstance().createNewPlanInstance(getRequest());
        if(!insCreated)
        {
            // should not happen
            handleEnd(_conn, _stmt);
            return Status.CANCEL_STATUS;
        }

        try
        {
            prepareConnection();
            _stmt = prepareStatement(_conn);
            _rawPlanString = explainPlan(_stmt);
            handleSuccess();
        }
        catch (SQLException ex)
        {
            EPVFacade.getInstance().planFailed(getRequest(), ex);
        }
        catch (Throwable th)
        {
            EPVFacade.getInstance().planFailed(getRequest(), th);
        }
        finally
        {
            handleEnd(_conn, _stmt);
        }
        return Status.OK_STATUS;
    }
    
    /**
     * Subclass should override this method to prepare a connection, and assign it to <code>_conn</code>
     * 
     */
    protected void prepareConnection()
    {
        
    }
}
