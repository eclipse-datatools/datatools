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
package org.eclipse.datatools.sqltools.result.core;

import java.util.List;

import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;

/**
 * Notified when the <code>IResultInstance</code> related event occurs.
 * 
 * @author Dafan Yang
 */
public interface IResultManagerListener
{
    /**
     * Will be invoked when a new instance is created
     * 
     * @param instance the result instance
     */
    public void resultInstanceCreated(IResultInstance instance);

    /**
     * Will be invoked when an instance is removed
     * 
     * @param instance the result instance
     */
    public void resultInstanceRemoved(IResultInstance instance);
    
    /**
     * Will be invoked when an arry of instances is removed
     * 
     * @param instances the result instances
     */
    public void resultInstancesRemoved(IResultInstance[] instances);

    /**
     * Will be invoked when the instance is appended
     * 
     * @param instance the result instance
     * @param result the result item
     * @param index the index of the result item
     */
    public void resultInstanceAppended(IResultInstance instance, ResultItem result, int index);

    /**
     * Will be invoked when all instances are removed
     * 
     */
    public void allResultInstancesRemoved();

    /**
     * Will be invoked when the status of the instance is changed
     * 
     * @param instance the result instance
     */
    public void resultInstanceStatusUpdated(IResultInstance instance);

    /**
     * Will be invoked when an instance if reseted
     * 
     * @param instance the result instance
     */
    public void resultInstanceReset(IResultInstance instance);
    
    /**
     * Will be invoked when showing the parameters in a result instance
     * 
     * @param instance the result instance
     * @param params the <code>Parameter</code> instances list
     * @see org.eclipse.datatools.sqltools.result.Parameter
     */
    public void parametersShow(IResultInstance instance, List params);
}
