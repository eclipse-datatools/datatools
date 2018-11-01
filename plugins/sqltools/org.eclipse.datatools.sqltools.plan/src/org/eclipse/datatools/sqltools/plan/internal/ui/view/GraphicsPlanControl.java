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
package org.eclipse.datatools.sqltools.plan.internal.ui.view;

import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;


/**
 * This class implements a standard <code>Control</code> which is used to display graphic execution plan.
 * 
 * @author Hui Cao
 */
public class GraphicsPlanControl extends Composite
{
    private SashForm             _planSplitter;
    private ViewForm             _detailForm;
    private ViewForm             _planForm;

    private ExecutionPlansDrawer _drawer;

    // Use browser to show the detail of each node, vendor should organize the details of nodes to html document
    private Browser              _browser;

    private IPreferenceStore     _store = PlanViewPlugin.getDefault().getPreferenceStore();

    /**
     * Creates a graphic plan control
     * 
     * @param parent the parent
     * @param style the style
     */
    public GraphicsPlanControl(Composite parent, int style)
    {
        super(parent, style);
        init(this);
        _drawer = new ExecutionPlansDrawer(_planForm, _browser);
    }

    private void init(Composite parent)
    {
        //Must set layout for it, else nothing will show.
        GridLayout gridLayout = new GridLayout();
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        parent.setLayout(gridLayout);

        _planSplitter = new SashForm(parent, SWT.HORIZONTAL);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        _planSplitter.setLayoutData(gridData);

        _planForm = new ViewForm(_planSplitter, SWT.NONE);

        _detailForm = new ViewForm(_planSplitter, SWT.NONE);
        createDetailForm(_detailForm);

        _planSplitter.setOrientation(_store.getBoolean(PreferenceConstants.VERTICAL_LAYOUT_PLAN_VIEW) ? SWT.VERTICAL
                : SWT.HORIZONTAL);

        _planSplitter.setWeights(new int[]
        {
            50, 50
        });
        this.redraw();
    }

    /**
     * Creates the Browser instance on the detail ViewForm
     * @param detailForm the form on which the browser will be created
     */
    private void createDetailForm(ViewForm detailForm)
    {
    	try
    	{
    		_browser = new Browser(detailForm, SWT.NONE);
    	}
    	catch (SWTError e)
    	{
    		PlanViewPlugin.getLogger(null).error(e.getMessage(), e);
    		return;
    	}
        _browser.setLayoutData(new GridData(GridData.FILL_BOTH));

        _browser.setText("");
        detailForm.setContent(_browser);
    }

    /**
     * Invokes the drawer to draw the graphic plan
     * @param instance the execution plan instance
     */
    public void setPlan(IPlanInstance instance)
    {
        _drawer.setPlan(instance);
    }

    public SashForm getSash()
    {
        return _planSplitter;
    }
}
