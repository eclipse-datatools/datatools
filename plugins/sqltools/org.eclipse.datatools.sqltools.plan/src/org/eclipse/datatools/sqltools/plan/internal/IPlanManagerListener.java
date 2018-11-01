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

/**
 * Listener interface for changes to an <code>IPlanManager</code>.
 * 
 * @author Hui Cao
 */
public interface IPlanManagerListener
{
    /**
     * Notified when a new instance is created
     * 
     * @param instance the new instance
     */
    public void planInstanceCreated(IPlanInstance instance);

    /**
     * Notified when an instance if removed
     * 
     * @param instance the instance been removed
     */
    public void planInstanceRemoved(IPlanInstance instance);

    /**
     * Notified when all plan instances have been removed in one operation
     *  
     */
    public void planInstancesRemoved();
    
    /**
     * Notified when the plan instance is finished (Fail or Success)
     * 
     * @param instance the plan instance
     */
    public void planInstanceFinished(IPlanInstance instance);
}
