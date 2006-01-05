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
package org.eclipse.datatools.sqltools.plan.internal.core;

import org.eclipse.datatools.sqltools.plan.PlanRequest;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;

/**
 * Implementation of <code>IPlanInstance</code>
 * 
 * @author Hui Cao
 */
public class PlanInstance implements IPlanInstance
{
    private Throwable   _failThrowable = null;
    private PlanManager _planManager;
    private PlanRequest _planRequest;
    /* The raw data of execution plan */
    private Object      _rawPlan;
    private int         _status        = RUNNING;

    public PlanInstance(PlanManager planManager, PlanRequest planRequest)
    {
        this._planManager = planManager;
        this._planRequest = planRequest;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanInstance#finishFail(java.lang.Throwable)
     */
    public void finishFail(Throwable th)
    {
        _status = FAILED;
        _failThrowable = th;
        if (_planManager != null)
        {
            _planManager.fireFinish(this);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanInstance#finishSuccess(java.lang.Object)
     */
    public void finishSuccess(Object rawPlan)
    {
        _status = SUCCESS;
        this._rawPlan = rawPlan;
        if (_planManager != null)
        {
            _planManager.fireFinish(this);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanInstance#getFailThrowable()
     */
    public Throwable getFailThrowable()
    {
        return _failThrowable;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanInstance#getPlanRequest()
     */
    public PlanRequest getPlanRequest()
    {
        return _planRequest;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanInstance#getRawPlan()
     */
    public Object getRawPlan()
    {
        return _rawPlan;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanInstance#getStatus()
     */
    public int getStatus()
    {
        return _status;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanInstance#isFinished()
     */
    public boolean isFinished()
    {
        return _status != RUNNING;
    }

    /**
     * Sets the plan request of this plan
     * 
     * @param planRequest the plan request
     */
    public void setPlanRequest(PlanRequest planRequest)
    {
        this._planRequest = planRequest;
    }

    /**
     * Sets the raw data for this plan
     * 
     * @param rawPlan the plan's raw data
     */
    public void setRawPlan(Object plan)
    {
        _rawPlan = plan;
    }
}
