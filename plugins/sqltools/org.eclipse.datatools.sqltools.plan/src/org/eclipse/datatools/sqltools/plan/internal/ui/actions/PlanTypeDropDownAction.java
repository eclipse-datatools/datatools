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

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.plan.IHelpConstants;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.plan.PlanServiceRegistry;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.ui.view.PlanView;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.PlatformUI;

/**
 * This class is for building a pull-down style action to switch plan types in Plan View toolbar.
 * 
 * @author juewu
 */
public class PlanTypeDropDownAction extends Action implements IMenuCreator
{
    private Menu     _fMenu;
    private PlanView _planView;

    public PlanTypeDropDownAction(PlanView planView)
    {
        super();
        setText(Messages.PlanTypeDropDownAction_text);
        setToolTipText(Messages.PlanTypeDropDownAction_tooltip);
        this.setImageDescriptor(Images.DESC_PLAN_TYPE);
        this.setDisabledImageDescriptor(Images.DESC_PLAN_TYPE);
        this._planView = planView;

        setMenuCreator(this);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(
                this,
                HelpUtil.getContextId(IHelpConstants.PLAN_TYPE_DROP_DOWN_ACTION, PlanViewPlugin.getDefault()
                        .getBundle().getSymbolicName()));
    }

    public void dispose()
    {
        disposeMenu();
    }

    public void disposeMenu()
    {
        if (_fMenu != null)
        {
            _fMenu.dispose();
        }
    }

    public Menu getMenu(Control parent)
    {
        String dbDefinitionId = _planView.getCurrentPlan().getPlanRequest().getDatabaseDefinitionId();
        IPlanService service = PlanServiceRegistry.getInstance().getPlanService(dbDefinitionId);
        String[] modeTypes = service.getPlanOption().getPlanTypes();

        disposeMenu();

        _fMenu = new Menu(parent);

        for (int i = 0; i < modeTypes.length; i++)
        {
            int modeType = service.getPlanOption().getTypeIdByName(modeTypes[i]);

            SwitchPlanTypeAction action = new SwitchPlanTypeAction(_planView, modeType, modeTypes[i], null, "");

            if (_planView.getCurrentPlan().getPlanRequest().getPlanType() == modeType)
            {
                action.setChecked(true);
            }
            addActionToMenu(_fMenu, action);
        }

        return _fMenu;
    }

    public Menu getMenu(Menu parent)
    {
        return null;
    }

    protected void addActionToMenu(Menu parent, Action action)
    {
        ActionContributionItem item = new ActionContributionItem(action);
        item.fill(parent, -1);
    }

    public void update()
    {
        IPlanInstance plan = _planView.getCurrentPlan();

        if (plan == null || plan.getPlanRequest().getProfileName() == null)
        {
            setEnabled(false);
            return;
        }

        setEnabled(PlanServiceRegistry.getInstance().getPlanService(
                _planView.getCurrentPlan().getPlanRequest().getDatabaseDefinitionId()).getPlanOption().getPlanTypes().length != 0);
    }
}
