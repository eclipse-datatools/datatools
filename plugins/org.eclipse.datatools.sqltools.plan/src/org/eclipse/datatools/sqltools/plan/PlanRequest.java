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
package org.eclipse.datatools.sqltools.plan;

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
    public static final int GRAPHIC_PLAN = 1;
    public static final int TEXT_PLAN    = 0;
    /* The database definition id, use "product_name"_"version" to uniquely identify a database product */
    private String          _databaseDefinitionId;
    /* The plan type, can be TEXT_PLAN or GRAPHIC_PLAN */
    private int             _planType;
    /* The SQL statement from which the execution plan is generated */
    private String          _sql;

    /**
     * Constructs a plan request
     * 
     * @param sql the SQL statement from which the execution plan is generated
     * @param databaseDefinitionId the database definition id, use "product_name"_"version" to uniquely identify a
     *            database product
     * @param planType the plan type, can be TEXT_PLAN or GRAPHIC_PLAN
     */
    public PlanRequest(String sql, String databaseDefinitionId, int planType)
    {
        super();
        this._sql = sql;
        this._databaseDefinitionId = databaseDefinitionId;
        if (planType != TEXT_PLAN && planType != GRAPHIC_PLAN)
        {
            this._planType = TEXT_PLAN;
        }
        else
        {
            this._planType = planType;
        }
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
}
