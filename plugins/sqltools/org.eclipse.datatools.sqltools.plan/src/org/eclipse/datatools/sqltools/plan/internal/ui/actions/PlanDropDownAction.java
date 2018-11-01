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
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.plan.PlanServiceRegistry;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.ui.view.PlanView;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;

/**
 * @author Hui Cao
 */
public class PlanDropDownAction extends Action implements IMenuCreator
{

    private Menu     _fMenu;
    private PlanView _planView;

    /**
     * Constructor
     * @param planView the Execution Plan view
     */
    public PlanDropDownAction(PlanView planView)
    {
        super();
        setText(Messages.PlanDropDownAction_previous_plans); 
        setToolTipText(Messages.PlanDropDownAction_show_history); 
        this.setImageDescriptor(Images.DESC_SHOWPLAN);
        this.setDisabledImageDescriptor(Images.DESC_SHOWPLAN);

        this._planView = planView;
        setMenuCreator(this);
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.PLAN_DROP_DOWN_ACTION, PlanViewPlugin.getDefault().getBundle().getSymbolicName()));
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

    public Menu getMenu(Menu parent)
    {
        return null;
    }

    public Menu getMenu(Control parent)
    {
        IPlanInstance[] instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
        IPlanInstance current = _planView.getCurrentPlan();
        disposeMenu();

        _fMenu = new Menu(parent);
        for (int i = instances.length - 1; i >= 0; i--)
        {
            IPlanInstance instance = instances[i];
            String label = instance.getPlanRequest().getDatabaseDefinitionId()
                    + ": " + instance.getPlanRequest().getSql();//$NON-NLS-1$
            if (label.length() > 40)
            {
                label = label.substring(0, 37) + " ...";
            }

            ImageDescriptor image = null;
            int planType = instance.getPlanRequest().getPlanType();
            if (instance.getStatus() == IPlanInstance.FAILED)
            {
                image = Images.DESC_FAILED_PLAN;
            }
            else
            {
            	IPlanService service = PlanServiceRegistry.getInstance().getPlanService(instance.getPlanRequest().getDatabaseDefinitionId());
            	boolean isGraphicPlan = service.getPlanOption().isGraphicPlan(planType);
            	if (isGraphicPlan)
            	{
            		image = Images.DESC_GRAPHIC_PLAN;
            	}
            	else
            	{
            		image = Images.DESC_TEXT_PLAN;
            	}
            }
            ShowPlanAction action = new ShowPlanAction(_planView, instance, label, image, ""); //$NON-NLS-1$
            if (instances[i].equals(current))
            {
                action.setChecked(true);
            }
            addActionToMenu(_fMenu, action);
        }
        if (instances.length > 0)
        {
            new MenuItem(_fMenu, SWT.SEPARATOR);
            addActionToMenu(_fMenu, new RemoveAllPlansAction());
        }
        return _fMenu;
    }

    protected void addActionToMenu(Menu parent, Action action)
    {
        ActionContributionItem item = new ActionContributionItem(action);
        item.fill(parent, -1);
    }

    public void run()
    {
        new ShowPlansAction(_planView).run();
    }

    public void update()
    {
        setEnabled(PlanViewPlugin.getPlanManager().getAllPlanInstances().length != 0);
    }
}
