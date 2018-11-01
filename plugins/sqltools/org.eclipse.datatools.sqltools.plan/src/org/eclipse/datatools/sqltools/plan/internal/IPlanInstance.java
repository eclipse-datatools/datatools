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
package org.eclipse.datatools.sqltools.plan.internal;

import org.eclipse.datatools.sqltools.plan.IExecutionPlanDocument;
import org.eclipse.datatools.sqltools.plan.PlanRequest;

/**
 * This class encapsulates the following information a sql plan:
 * <ul>
 * <li>The plan request
 * <li>Raw data of the execution plan
 * <li>Model for drawing purpose (An array of <code>IExecutionPlanDocument</code>)
 * <li>Result of the execution plan
 * </ul>
 * 
 * @author Hui Cao 
 */
public interface IPlanInstance
{
    /**
     * Indicates that this plan is generating, still not finished
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

    /**
     * Failed because of the throwable
     * 
     * @param th the throwable
     */
    public void finishFail(Throwable th);

    /**
     * Successfully finished with the return plan's raw data
     * 
     * @param rawPlan the plan's raw data
     */
    public void finishSuccess(String rawPlan);

    /**
     * Returns the fail reason throwable
     * 
     * @return <code>null</code> if not finished yet, or not fail.
     */
    public Throwable getFailThrowable();

    /**
     * Returns the plan request
     * 
     * @return the plan request
     */
    public PlanRequest getPlanRequest();

    /**
     * Returns the raw data of this plan
     * 
     * @return the raw data of this plan
     */
    public String getRawPlan();

    /**
     * Returns the current status
     * 
     * @return @see #RUNNING
     * @see #SUCCESS
     * @see #FAILED
     */
    public int getStatus();
    
    /**
     * Checks if this plan is finished or not
     * 
     * @return <code>true</code> if finished (success or fail), <code>false</code> otherwise
     */
    public boolean isFinished();
    
    /**
     * Returns the execution plan documents of this plan. The documents are parsed from raw data of this execution plan
     * 
     * @return the execution plan documents of this plan
     */
    public IExecutionPlanDocument[] getPlanDocuments();
    
    /**
     * Sets the execution plan documents, they should be parsed from the raw data. Note: this is for performance
     * consideration, that is, we don't need to parse the raw data every time when it is shown
     * 
     * @param docs the execution plan documents
     */
    public void setPlanDocuments(IExecutionPlanDocument[] docs);
}
