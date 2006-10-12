/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan;

/**
 * An instance of this interface not only holds the staitic information of the execution plan component -- plan types,
 * what types are graphic ones, etc., but also some dynamic options are included -- such as current plan type. The
 * implementing class of this interface can be useful when getting the execution plan; also it's used to display the
 * execution plan preference page.
 * 
 * @author Dafan Yang
 */
public interface IPlanOption
{
    /**
     * Returns the vendor name of this kind of database
     * 
     * @return the vendor name
     */
    public String getVendorName();

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
     * Returns the default type
     * 
     * @return the default type
     */
    public int getDefaultOption();

    /**
     * Gets name by id
     * 
     * @param type the plan type id
     * @return the plan type name
     */
    public String getOptionName(int type);

    /**
     * Gets id by name
     * 
     * @param name the name of the plan type
     * @return the id of the plan
     */
    public int getOptionId(String name);

    /**
     * Returns the current plan type configured by the user
     * 
     * @return the current plan type
     */
    public int getCurrentType();
}
