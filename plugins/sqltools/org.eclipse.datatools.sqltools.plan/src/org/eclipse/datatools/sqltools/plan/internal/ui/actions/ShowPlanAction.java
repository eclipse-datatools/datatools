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

import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.ui.view.PlanView;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author Hui Cao
 *  
 */
public class ShowPlanAction extends Action
{

    private IPlanInstance _instance;
    private PlanView      _fView;

    /**
     * Constructor
     * 
     * @param view the Execution Plan view
     * @param instance a given plan instance
     * @param text the action name
     * @param image the action image
     * @param tooltip the action tooltip
     */
    public ShowPlanAction(PlanView view, IPlanInstance instance, String text, ImageDescriptor image, String tooltip)
    {
        this._instance = instance;
        _fView = view;
        if (text.indexOf('@') >= 0)
        {
            text += '@';
        }
        setText(text);
        setImageDescriptor(image);
        setToolTipText(tooltip);
    }

    public void run()
    {
        _fView.showPlan(_instance);
    }

}
