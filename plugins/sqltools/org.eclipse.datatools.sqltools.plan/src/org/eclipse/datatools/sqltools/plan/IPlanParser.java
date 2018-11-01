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


/**
 * Parse the execution plan from raw data to modeled data --- <code>IExecutionPlanDocument</code>, then
 * <code>IPlanDrawer</code> will draw it on a <code>Canvas</code>.<br>
 * Generally speaking, the raw data of the plan is expected to be a well-formed xml string.
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
