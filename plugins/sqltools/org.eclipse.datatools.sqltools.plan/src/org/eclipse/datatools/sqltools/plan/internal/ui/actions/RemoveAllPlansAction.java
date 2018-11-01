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
package org.eclipse.datatools.sqltools.plan.internal.ui.actions;

import org.eclipse.datatools.sqltools.plan.IHelpConstants;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

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
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IHelpConstants.REMOVE_ALL_PLAN_ACTION);
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
