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
 * The facade of EPV (SQL Execution Plan View).
 * 
 * @author Dafan Yang
 */
public class EPVFacade
{
    private static EPVFacade _instance;

    private EPVFacade()
    {

    }

    /**
     * Creates an instance of execution plan, to display an execution plan on EPV, consumers must create a new plan
     * instance first
     * 
     * @param request the plan request
     * @return <code>true</code> if creation succeeds, <code>false</code> otherwise
     */
    public boolean createNewPlanInstance(PlanRequest request)
    {
        return true;
    }

    /**
     * Returns the instance of <code>EPVFacade</code>
     * 
     * @return the instance of <code>EPVFacade</code>
     */
    public synchronized EPVFacade getInstance()
    {
        if (_instance == null)
        {
            _instance = new EPVFacade();
        }
        return _instance;
    }

    /**
     * Informs the EPV that error occurs when getting the execution plan from the data server. The error information
     * will be displayed on EPV.
     * 
     * @param request the plan request
     * @param th the error information
     */
    public void planFailed(PlanRequest request, Throwable th)
    {

    }

    /**
     * Informs the EPV that the plan raw data of the given plan request is successfully generated. The raw data of the
     * execution plan will be set to the plan instance.
     * <p>
     * Note that the <code>IPlanParser</code> will be responsible for parsing the raw data of plan into modeled data.
     * 
     * @see IPlanParser
     * @param request the plan request
     * @param rawPlan the raw data of execution plan
     */
    public void planGenerated(PlanRequest request, Object rawPlan)
    {

    }
}
