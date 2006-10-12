/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.plan;

import org.eclipse.datatools.sqltools.plan.internal.core.Messages;

/**
 * A default implementation of <code>IPlanOption</code>, the vendor should implement <code>IPlanOption</code> from
 * scratch
 * 
 * @author Hui Cao
 */
public class PlanOption implements IPlanOption
{
    /**
     * Graphic execution plan mask
     */
    public static final int    GRAPHIC_PLAN      = 1;

    /**
     * Text execution plan mask
     */
    public static final int    TEXT_PLAN         = 0;

    public static final String GRAPHIC_PLAN_DESC = Messages.planoption_graphic;

    public static final String TEXT_PLAN_DESC    = Messages.planoption_text;

    /**
     * The default implementation always returns TEXT_PLAN
     */
    public int getCurrentType()
    {
        return TEXT_PLAN;
    }

    public int getDefaultOption()
    {
        return TEXT_PLAN;
    }

    public int getOptionId(String name)
    {
        if (GRAPHIC_PLAN_DESC.equals(name))
        {
            return GRAPHIC_PLAN;
        }
        else
        {
            return TEXT_PLAN;
        }
    }

    public String getOptionName(int type)
    {
        if (GRAPHIC_PLAN == type)
        {
            return GRAPHIC_PLAN_DESC;
        }
        else
        {
            return TEXT_PLAN_DESC;
        }
    }

    public String[] getPlanTypes()
    {
        return new String[]
        {
            TEXT_PLAN_DESC, GRAPHIC_PLAN_DESC
        };
    }

    public String getVendorName()
    {
        return "";
    }

    public boolean isGraphicPlan(int type)
    {
        return type == GRAPHIC_PLAN;
    }
}
