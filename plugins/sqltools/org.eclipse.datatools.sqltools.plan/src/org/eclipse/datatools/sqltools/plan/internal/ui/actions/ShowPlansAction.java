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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.ui.view.PlanView;
import org.eclipse.datatools.sqltools.plan.internal.ui.view.PlansDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;

/**
 * @author Hui Cao
 * 
 */
public class ShowPlansAction extends Action
{
    private PlanView _planView;

    /**
     * Constructor
     * 
     * @param planView the Execution Plan view
     */
    public ShowPlansAction(PlanView planView)
    {
        super(Messages.ShowPlansAction_previous_plan); 
        setToolTipText(Messages.ShowPlansAction_show_history); 
        this._planView = planView;
    }

    public void run()
    {
        IPlanInstance[] instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();

        ArrayList input = new ArrayList();
        for (int j = 0; j < instances.length; j++)
        {
            IPlanInstance inst = instances[j];
            input.add(inst);
        }

        PlansDialog dlg = new PlansDialog(PlanViewPlugin.getActiveWorkbenchShell(), input);

        IPlanInstance current = _planView.getCurrentPlan();
        if (current != null)
        {
            Object[] selected = new Object[1];
            selected[0] = current;
            dlg.setInitialSelections(selected);
        }
        if (dlg.open() == Window.OK)
        {
            List result = Arrays.asList(dlg.getResult());
            if (result != null && result.size() == 1)
            {
                _planView.showPlan((IPlanInstance) result.get(0));
            }
        }

    }
}
