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
 * Parse the execution plan from raw data to modeled data --- <code>IExecutionPlanDocument</code>, then
 * <code>IPlanDrawer</code> will draw it on a <code>Canvas</code>
 * 
 * @see IExecutionPlanDocument
 * @author Dafan Yang
 */
public interface IPlanParser
{
    /**
     * Parses the raw data of execution plan to <code>IExecutionPlanDocument</code> array
     * 
     * @param rawPlan the raw data of execution plan
     * @return the execution plan documents
     */
    public IExecutionPlanDocument[] parsePlan(String rawPlan);
}
