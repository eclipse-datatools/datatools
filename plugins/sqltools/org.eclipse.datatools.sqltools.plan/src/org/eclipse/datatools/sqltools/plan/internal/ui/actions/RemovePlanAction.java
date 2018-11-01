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


import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.plan.IHelpConstants;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.ui.view.PlanView;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

/**
 * @author Hui Cao
 *
 */
public class RemovePlanAction extends Action
{
    private PlanView _planView;

    /**
     * Constructor
     * @param planView the Execution Plan view
     */
    public RemovePlanAction(PlanView planView)
    {
        setText(Messages.RemovePlanAction_remove_plan_name); 
        setToolTipText(Messages.RemovePlanAction_remove_plan_tooltip); 
        this.setImageDescriptor(Images.DESC_REMOVE);
        this.setDisabledImageDescriptor(Images.DESC_REMOVE_DISABLE);
        this._planView = planView;
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.REMOVE_PLAN_ACTION, PlanViewPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void run()
    {
        if (_planView.getCurrentPlan() != null)
        {
            PlanViewPlugin.getPlanManager().removePlanInstance(_planView.getCurrentPlan());
        }

        //Select the last plan by default when the current one is removed
        IPlanInstance[] instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
        if (instances != null && instances.length > 0)
        {
            _planView.showPlan(instances[instances.length - 1]);
        }
        else
        {
            _planView.showPlan(null);
        }
    }

    public void update()
    {
        setEnabled(_planView.getCurrentPlan() != null && _planView.getCurrentPlan().isFinished());
    }
}
