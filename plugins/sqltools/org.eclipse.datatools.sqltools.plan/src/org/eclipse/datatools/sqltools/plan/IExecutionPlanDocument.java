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
 * This is the model for drawing purpose. An instance of this interface renders a step (sub execution plan) in a
 * multiple steps execution plan, for example, the execution plan of the following stored procedure has two steps:
 * <p>
 * 
 * <pre>
 *   create procedure test_proc as
 *   begin
 *    select * from test_table
 *    select * from test_table1 where id = 5
 *   end
 * </pre>
 * 
 * For a normal SQL query statement, there is only one step in the whole execution plan. For example, the following SQL
 * statement has only one step:
 * <p>
 * 
 * <pre>
 *   select * from test_table
 * </pre>
 * 
 * Instance of this interface can be obtained through <code>IPlanParser</code>, <code>IPlanParser</code> can parse
 * the raw data of execution plan into instances of <code>IExecutionPlanDocument</code>
 * <p>
 * <code>IPlanDrawer</code> can draw instance of this class on a <code>Canvas</code>
 * 
 * @see IPlanParser
 * @author Dafan Yang
 */
public interface IExecutionPlanDocument
{
    /**
     * Returns the name of this execution plan documemnt, this name will be displayed in combo box if there are multiple
     * steps in an execution plan.
     * 
     * @return the name of this document
     */
    public String getName();
}
