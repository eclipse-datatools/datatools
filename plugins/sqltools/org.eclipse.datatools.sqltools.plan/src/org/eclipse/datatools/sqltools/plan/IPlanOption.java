/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan;

/**
 * Generally speaking, a database server may support several types of execution plan, for example, a text type and a
 * graphic type, which renders the execution plan in text string or using graph respectively.<br>
 * This interface tries to build a relationship between the type id and type name, and check which type is graphic one,
 * such that the framework can draw the execution plan in a canvas instead of displaying it as a literal string.<br>
 * Also, the consumer needs to create the preference section on "Execution Plan View Option" page to let the end user
 * select the plan type, the framework does not implement this part.
 * 
 * @author Dafan Yang
 */
public interface IPlanOption
{
    public static final int TYPE_SP      = 0;
    public static final int TYPE_UDF     = 1;
    public static final int TYPE_EVENT   = 2;
    public static final int TYPE_TRIGGER = 3;
    
    /**
     * Returns all available plan types, in string mode.
     * 
     * @return all plan types
     */
    public String[] getPlanTypes();

    /**
     * Checks if the given type id is graphic type
     * 
     * @param type a given plan type
     * @return <code>true</code> if the given type is grahic type, <code>false</code> otherwise
     */
    public boolean isGraphicPlan(int type);
 
    /**
     * Returns the default plan type id
     * 
     * @return the default plan type id
     */
    public int getDefaultOption();

    /**
     * Returns type name by id
     * 
     * @param type the plan type id
     * @return the plan type name
     */
    public String getTypeNameById(int type);

    /**
     * Returns type id by name
     * 
     * @param name the name of the plan type
     * @return the id of the plan type
     */
    public int getTypeIdByName(String name);

    /**
     * Returns the current plan type configured by the user
     * 
     * @return the current plan type
     */
    public int getCurrentType();
    
    /**
     * Returns if the execution plan is supported for the given proc type
     * 
     * @param procType the type of procedural object
     * @see #TYPE_SP
     * @see #TYPE_UDF
     * @see #TYPE_TRIGGER
     * @see #TYPE_EVENT
     * @return <code>true</code> if the execution plan for the given type is supported
     */
    public boolean supportPlan(int procType);
}
