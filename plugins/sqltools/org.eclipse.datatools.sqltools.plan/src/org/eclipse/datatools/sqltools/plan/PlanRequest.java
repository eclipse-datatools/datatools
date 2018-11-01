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

import java.util.Map;


/**
 * Start point to use EPV (SQL Execution Plan View), every time when the consumer needs to display an execution plan on
 * EPV, it must construct an instance of <code>PlanRequest</code> first. This class encapsulates all necessary
 * information:
 * <ul>
 * <li>DatabaseDefinitionId: This is used to uniquely identify a database product, the format is: "product name" plus
 * "_" plus "product version", for example, Derby_10.0. This id will be displayed together with the SQL statement as the
 * name of this plan item
 * <li>PlanType: Can be text execution plan or graphic execution plan
 * <li>SQL: The SQL statement from which the execution is generated
 * </ul>
 * 
 * @author Hui Cao
 */
public class PlanRequest
{
    /**
     * Make the SQL Execution Plan view visible and activated
     */
    
    public static final int VIEW_ACTIVATE = 1;

    /**
     * 
     */
    public static final int VIEW_VISIBLE  = 2;
    
    /**
     * Make the SQL Execution Plan view to be made created but not necessarily be made visible
     */
    public static final int VIEW_CREATE   = 3;
    
    /**
     * Indicates that the plan is generating, still not finished
     */
    public static final int RUNNING = 0;
    
    /**
     * Indicates that the plan is successfully generated
     */
    public static final int SUCCESS = 1;
    
    /**
     * Indicates it's failed to generated the execution plan
     */
    public static final int FAILED  = 2;
    
    
    //The database definition id, use "product_name"_"version" to uniquely identify a database product 
    private String          _databaseDefinitionId;
    
    private String          _profileName;
    
    private String          _dbName;
    
    /**
     * The plan type, each vendor can define their own plan types. The framework will use GRAPHIC_PLAN or TEXT_PLAN to
     * bitwise and this plan type to determine whether it's a graphic plan or text plan.
     */
    private int             _planType;
    
    // The SQL statement from which the execution plan is generated
    private String          _sql;
    
    // The show view mode
    private int             _mode;
    
    /**
     * Since the statements to be explained may reference some variables, but the
     * declarations of the variable are not included
     */
    private Map             _varDecs;
    
    // Application data
    private Object          _data;
    /**
     * Whether to execute the sql statement when retrieving the execution plan
     */
    private boolean _noexec;
    
    /**
     * Constructs a plan request
     * 
     * @param sql the SQL statement from which the execution plan is generated
     * @param databaseDefinitionId the database definition id, use "product_name"_"version" to uniquely identify a
     *            database product
     * @param planType the plan type, vendor-specific plan types
     * @param mode the show view mode
     */
    public PlanRequest(String sql, String databaseDefinitionId, int planType, int mode)
    {
        super();
        this._sql = sql == null ? "" : sql;
        this._databaseDefinitionId = databaseDefinitionId == null?"":databaseDefinitionId;
        this._planType = planType;
        
        if(mode != VIEW_ACTIVATE && mode != VIEW_CREATE && mode != VIEW_VISIBLE)
        {
            _mode = VIEW_ACTIVATE;
        }
        else
        {
            _mode = mode;
        }
    }
    
    public PlanRequest(String sql, String databaseDefinitionId, int planType, int mode, Map varDecs)
    {
        this(sql, databaseDefinitionId, planType, mode);
        _varDecs = varDecs;
    }

    /**
     * Returns the database definition id
     * 
     * @return the database definition id
     */
    public String getDatabaseDefinitionId()
    {
        return _databaseDefinitionId;
    }

    /**
     * Returns the plan type
     * 
     * @return the plan type
     */
    public int getPlanType()
    {
        return _planType;
    }

    /**
     * Returns the sql statement
     * 
     * @return the sql statement
     */
    public String getSql()
    {
        return _sql;
    }

    /**
     * Sets the database definition id
     *  
     * @param databaseDefinitionId the database definition id
     */
    public void setDatabaseDefinitionId(String databaseDefinitionId)
    {
        this._databaseDefinitionId = databaseDefinitionId;
    }

    /**
     * Sets the plan type
     * 
     * @param type the plan type
     */
    public void setPlanType(int type)
    {
        _planType = type;
    }

    /**
     * Sets the sql statement
     * 
     * @param sql the sql statement
     */
    public void setSql(String sql)
    {
        this._sql = sql;
    }

    /**
     * Returns the show view mode
     * 
     * @return the show view mode
     */
    public int getMode()
    {
        return _mode;
    }
    
    /**
     * @return Returns whether to execute the sql statement when retrieving the execution plan
     */
    public boolean isNoexec()
    {
        return _noexec;
    }
    /**
     * @param _noexec Sets whether to execute the sql statement when retrieving the execution plan
     */
    public void setNoexec(boolean _noexec)
    {
        this._noexec = _noexec;
    }

    public Object getData()
    {
        return _data;
    }

    public void setData(Object data)
    {
        this._data = data;
    }

    public Map getVarDecs()
    {
        return _varDecs;
    }

    public void setVarDecs(Map decs)
    {
        _varDecs = decs;
    }

    public String getProfileName()
    {
        return _profileName;
    }

    public void setProfileName(String name)
    {
        _profileName = name;
    }

    public String getDbName()
    {
        return _dbName;
    }

    public void setDbName(String name)
    {
        _dbName = name;
    }
    
    
}
