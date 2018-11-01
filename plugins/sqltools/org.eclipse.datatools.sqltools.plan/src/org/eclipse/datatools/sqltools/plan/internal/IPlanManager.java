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

import org.eclipse.datatools.sqltools.plan.PlanRequest;

/**
 * Manages plan instances
 * 
 * @author Hui Cao 
 */
public interface IPlanManager
{
    /**
     * Adds listener
     * 
     * @param listener a listener
     */
    public void addPlanManagerListener(IPlanManagerListener listener);

    /**
     * Creates a new plan instance
     * 
     * @param request the given plan request
     * @return a new plan instance
     */
    public IPlanInstance createNewPlanInstance(PlanRequest request);

    /**
     * Returns all currently available plan instances
     * 
     * @return all available plan instances
     */
    public IPlanInstance[] getAllPlanInstances();

    /**
     * Removes all finished plan instances
     */
    public void removeAllFinished();

    /**
     * Removes the specified plan instance
     * 
     * @param instance the specified plan instance
     */
    public void removePlanInstance(IPlanInstance instance);

    /**
     * Removes listener
     * 
     * @param listener a listener
     */
    public void removePlanManagerListener(IPlanManagerListener listener);
    
    /**
     * Returns the plan instance identified by given plan request
     * 
     * @return the plan instance identified by given plan request
     */
    public IPlanInstance getPlanInstance(PlanRequest request);
}
