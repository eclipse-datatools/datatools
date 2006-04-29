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
package org.eclipse.datatools.sqltools.plan.internal.ui.actions;

import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.jface.action.Action;

/**
 * @author Hui Cao
 *
 */
public class RemoveAllPlansAction extends Action
{
    /**
     * Constructor
     *
     */
    public RemoveAllPlansAction()
    {
        super(Messages.RemoveAllPlansAction_remove_all_plans_name); 
        setToolTipText(Messages.RemoveAllPlansAction_remove_all_plans_name_tooltip); 
        setImageDescriptor(Images.DESC_REMOVEALL);
        setDisabledImageDescriptor(Images.DESC_REMOVEALL_DISABLE);
    }

    public void run()
    {
        PlanViewPlugin.getPlanManager().removeAllFinished();
    }

    public void update()
    {
        boolean shouldEnable = false;
        IPlanInstance[] instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
        for(int i=0;i<instances.length;i++)
        {
            if(instances[i].isFinished())
            {
                shouldEnable = true;
                break;
            }
        }
        setEnabled(shouldEnable);
    }
}
