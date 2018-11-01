/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.internal.ui.actions;

import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.plan.PlanRequest;
import org.eclipse.datatools.sqltools.plan.PlanServiceRegistry;
import org.eclipse.datatools.sqltools.plan.internal.ui.view.PlanView;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * This class is building item of plan types according to support of different vendors.
 * 
 * @see org.eclipse.datatools.sqltools.plan.internal.ui.actions.PlanTypeDropDownAction
 * @author juewu
 */
public class SwitchPlanTypeAction extends Action
{
    private PlanView _fView;
    private int      _planType;

    public SwitchPlanTypeAction(PlanView view, int modeType, String text, ImageDescriptor image, String tooltip)
    {
        _fView = view;
        _planType = modeType;

        setText(text);
        setImageDescriptor(image);
        setToolTipText(tooltip);
    }

    public void run()
    {
        PlanRequest request = _fView.getCurrentPlan().getPlanRequest();
        String dbDefId = request.getDatabaseDefinitionId();
        IPlanService service = PlanServiceRegistry.getInstance().getPlanService(dbDefId);

        PlanRequest newReq = new PlanRequest(request.getSql(), dbDefId, _planType, request.getMode());

        service.createPlanSupportRunnable(newReq, request.getProfileName(), request.getDbName()).schedule();
    }
}
