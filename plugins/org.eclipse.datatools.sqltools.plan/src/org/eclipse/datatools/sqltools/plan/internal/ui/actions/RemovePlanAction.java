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
import org.eclipse.datatools.sqltools.plan.internal.ui.view.PlanView;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.jface.action.Action;

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
        setText(Messages.getString("RemovePlanAction.remove.plan.name")); //$NON-NLS-1$
        setToolTipText(Messages.getString("RemovePlanAction.remove.plan.tooltip")); //$NON-NLS-1$
        this.setImageDescriptor(Images.DESC_REMOVE);
        this.setDisabledImageDescriptor(Images.DESC_REMOVE_DISABLE);
        this._planView = planView;
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
        setEnabled(_planView.getCurrentPlan() != null);
    }
}
