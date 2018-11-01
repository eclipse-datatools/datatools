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
package org.eclipse.datatools.sqltools.plan.internal.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.sqltools.plan.PlanRequest;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.IPlanManager;
import org.eclipse.datatools.sqltools.plan.internal.IPlanManagerListener;

/**
 * Implementation of <code>IPlanManager</code>
 * 
 * @author Hui Cao 
 */
public class PlanManager implements IPlanManager
{
    ListenerList<IPlanManagerListener> _listeners = new ListenerList<>();
    List _plans = new ArrayList();
    Map _map = new HashMap();
    /**
     * Constructor
     * 
     */
    public PlanManager()
    {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanManager#addPlanManagerListener(org.eclipse.datatools.sqltools.plan.internal.IPlanManagerListener)
     */
    public void addPlanManagerListener(IPlanManagerListener listener)
    {
        _listeners.add(listener);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanManager#createNewPlanInstance(org.eclipse.datatools.sqltools.plan.PlanRequest)
     */
    public IPlanInstance createNewPlanInstance(PlanRequest request)
    {
        IPlanInstance instance = new PlanInstance(this, request);
        synchronized (_plans)
        {
            _plans.add(instance);
            _map.put(request, instance);
        }
        this.fireAdded(instance);
        return instance;
    }

    protected void fireAdded(IPlanInstance instance)
    {
        for (IPlanManagerListener listener : this._listeners)
        {
            listener.planInstanceCreated(instance);
        }
    }

    /**
     * Fires all the listeners of the plan finish event
     * 
     * @param instance the plan instance
     */
    public void fireFinish(IPlanInstance instance)
    {
        for (IPlanManagerListener listener : this._listeners)
        {
            listener.planInstanceFinished(instance);
        }
    }

    protected void fireRemoved()
    {
        for (IPlanManagerListener listener : this._listeners)
        {
            listener.planInstancesRemoved();
        }
    }

    protected void fireRemoved(IPlanInstance instance)
    {
        for (IPlanManagerListener listener : this._listeners)
        {
            listener.planInstanceRemoved(instance);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanManager#getAllPlanInstances()
     */
    public IPlanInstance[] getAllPlanInstances()
    {
        synchronized (_plans)
        {
            return (IPlanInstance[]) _plans.toArray(new IPlanInstance[_plans.size()]);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanManager#removeAllFinished()
     */
    public void removeAllFinished()
    {
        boolean removed = false;
        synchronized (_plans)
        {
            for (Iterator iter = _plans.iterator(); iter.hasNext();)
            {
                IPlanInstance instance = (IPlanInstance) iter.next();
                if (instance.isFinished())
                {
                    _map.remove(instance.getPlanRequest());
                    iter.remove();
                    removed = true;
                }
            }
        }
        if (removed)
        {
            fireRemoved();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanManager#removePlanInstance(org.eclipse.datatools.sqltools.plan.internal.IPlanInstance)
     */
    public void removePlanInstance(IPlanInstance instance)
    {
        boolean removed;
        synchronized (_plans)
        {
            removed = _plans.remove(instance);
            _map.remove(instance.getPlanRequest());
        }
        if (removed)
        {
            fireRemoved(instance);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.internal.IPlanManager#removePlanManagerListener(org.eclipse.datatools.sqltools.plan.internal.IPlanManagerListener)
     */
    public void removePlanManagerListener(IPlanManagerListener listener)
    {
        _listeners.remove(listener);
    }

    public IPlanInstance getPlanInstance(PlanRequest request)
    {
        return (IPlanInstance)_map.get(request);
    }
}
