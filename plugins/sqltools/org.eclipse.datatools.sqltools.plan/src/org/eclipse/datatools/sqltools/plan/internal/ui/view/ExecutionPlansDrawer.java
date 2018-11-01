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


import org.eclipse.datatools.sqltools.plan.IExecutionPlanDocument;
import org.eclipse.datatools.sqltools.plan.IPlanDrawer;
import org.eclipse.datatools.sqltools.plan.IPlanParser;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.plan.PlanServiceRegistry;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;


/**
 * This class draws a multiple steps execution plan on a <code>ViewForm</code>. If there is only one execution
 * document for one plan, we don't create the combo, if not, a combo will be created to show all plans.
 * <code>IPlanDrawer</code> is used to draw <code>IExecutionPlanDocument</code>.
 * 
 * @author Dafan Yang
 */
public class ExecutionPlansDrawer
{
    private IExecutionPlanDocument[] _planDocs;

    // Use combo to show multiple documents, if there is only one, this should not be displayed
    private Combo                    _comboQuery;
    private ViewForm                 _planForm;
    private ScrolledComposite        _scComposite;
    private Composite                _graphicComposite;

    // The grahpic execution plan is drawn on the canvas
    private Canvas                   _canvas;

    // Use this drawer to draw each execution plan
    private IPlanDrawer              _drawer;

    // Use browser to display node detail
    private Browser                  _browser;
    private static final int         COMBO_ITEM_MAX_LEN = 90;
    private static final int         TOOLTIP_BOX_WIDTH  = 150;

    /**
     * This class is constructed in <code>GraphicsPlanControl</code>, we use <code>ViewForm</code> to contain the
     * graphic execution plans, use <code>Browser</code> to display node detail.
     * 
     * @param planForm the view form used to display the graphic plan
     * @param browser the browser used to display the node detail
     */
    public ExecutionPlansDrawer(ViewForm planForm, Browser browser)
    {
        // We will draw the graphic and combo on the plan form
        _planForm = planForm;
        _browser = browser;
        init();
    }

    private void init()
    {
        _scComposite = new ScrolledComposite(_planForm, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        _scComposite.setLayout(new GridLayout());
        _scComposite.setExpandHorizontal(true);
        _scComposite.setExpandVertical(true);

        _graphicComposite = new Composite(_scComposite, SWT.NONE);
        _graphicComposite.setLayout(new GridLayout());

        _graphicComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        _scComposite.setContent(_graphicComposite);

        createComboQuery(_graphicComposite);
        _canvas = new Canvas(_graphicComposite, SWT.NONE);
        _canvas.setLayout(new GridLayout());
        _canvas.setLayoutData(new GridData(GridData.FILL_BOTH));

        _planForm.setContent(_scComposite);
        _scComposite.setMinSize(_graphicComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    }

    /**
     * This method initializes _comboQuery
     */
    private void createComboQuery(Composite parent)
    {
        GridData gridData11 = new GridData();

        _comboQuery = new Combo(parent, SWT.READ_ONLY);
        gridData11.grabExcessHorizontalSpace = true;
        gridData11.horizontalAlignment = GridData.FILL;
        gridData11.verticalAlignment = GridData.CENTER;
        _comboQuery.setLayoutData(gridData11);
        _comboQuery.addSelectionListener(_comboSelectionListener);
        //tooltip to show full sql statement for every combo item
        _comboQuery.addMouseTrackListener(new MouseTrackAdapter()
        {
            public void mouseHover(MouseEvent e)
            {
                _comboQuery.setToolTipText(addLineSeparator(_planDocs[_comboQuery.getSelectionIndex()].getName()));
            }   
        });
    }

    /**
     * Draws the selected execution plan.
     * 
     */
    private void drawPlan()
    {
        IExecutionPlanDocument doc = null;
        if (_planDocs != null && _planDocs.length > 1 && _comboQuery.getSelectionIndex() >= 0)
        {
            doc = _planDocs[_comboQuery.getSelectionIndex()];
        }
        else if (_planDocs != null && _planDocs.length == 1)
        {
            doc = _planDocs[0];
        }

        if (doc != null)
        {
            _drawer.drawPlan(doc);
        }
        else
        {
            // error occurs? how to handle?
        }
        
        // Note: setMinimumSize has no effect upon the scroll bar
        _scComposite.setMinSize(_graphicComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        _scComposite.redraw();
    }
    
    private String addLineSeparator(String str)
    {
        StringBuffer sb = new StringBuffer("");
        int length = str.length();
        while (length > TOOLTIP_BOX_WIDTH)
        {
            if (sb.toString().length() > 0)
            {
                sb.append(System.getProperty("line.separator"));
            }
            int i = TOOLTIP_BOX_WIDTH;
            for (; i > 0; i--)
            {
                if (str.charAt(i) == ',' || str.charAt(i) == ' ')
                {
                    break;
                }
            }
            sb.append(str.substring(0, i));
            str = str.substring(i);
            length = str.length();
        }
        if (sb.toString().length() > 0)
        {
            sb.append(System.getProperty("line.separator"));
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * Sets the new plan will redraw the graph
     * 
     * @param instance the execution plan instance
     */
    public void setPlan(IPlanInstance instance)
    {
        // Sets the plan drawer accordingly
        IPlanService planService = PlanServiceRegistry.getInstance().getPlanService(
                instance.getPlanRequest().getDatabaseDefinitionId());
        _drawer = planService.getPlanDrawer();
        
        // Clear the content and re-init
        if(!_graphicComposite.isDisposed())
        {
            _graphicComposite.dispose();
        }
        _graphicComposite = new Composite(_scComposite, SWT.NONE);
        _graphicComposite.setLayout(new GridLayout());
        _graphicComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        _scComposite.setContent(_graphicComposite);

        //createComboQuery(_graphicComposite);
        _canvas = new Canvas(_graphicComposite, SWT.NONE);
        _canvas.setLayout(new GridLayout());
        _canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        _drawer.setCanvas(_canvas);
        _drawer.setBrowser(_browser);
        _drawer.init();

        if (instance.getPlanDocuments() == null && instance.getRawPlan() != null)
        {
            IPlanParser parser = planService.getPlanParser();
            _planDocs = parser.parsePlan(instance.getRawPlan());
            // Cache the execution plan documents
            instance.setPlanDocuments(_planDocs);
        }
        //set tooltip on canvas to show full sql statement when there is only one plan 
        if (instance.getPlanDocuments() != null && instance.getPlanDocuments().length == 1)
        {
            _canvas.setToolTipText(addLineSeparator(instance.getPlanDocuments()[0].getName()));
        }
        _planDocs = instance.getPlanDocuments();
        
        // if there is only one document, we don't display the combo
        if (_planDocs != null && _planDocs.length > 1)
        {
            if (_comboQuery.isDisposed())
            {
                createComboQuery(_graphicComposite);
            }
            _comboQuery.moveAbove(null);
            _graphicComposite.layout(true);
            _comboQuery.removeAll();
            for (int i = 0; i < _planDocs.length; i++)
            {
                String itemName = _planDocs[i].getName();
                if (itemName != null)
                {
                    if (itemName.length() > COMBO_ITEM_MAX_LEN)
                    {
                        itemName = itemName.substring(0, COMBO_ITEM_MAX_LEN) + "...";
                    }
                }
                _comboQuery.add(itemName);
            }
            _comboQuery.setVisibleItemCount(_comboQuery.getItemCount() > 25 ? 25 : _comboQuery.getItemCount());
            _comboQuery.select(0);
        }
        else
        {
            if (!_comboQuery.isDisposed())
            {
                _comboQuery.dispose();
            }
            _canvas.moveAbove(null);
            _graphicComposite.layout(true);
        }
        drawPlan();
    }

    private SelectionListener _comboSelectionListener = new SelectionListener()
                                                      {
                                                          public void widgetSelected(SelectionEvent e)
                                                          {
                                                              // Change execution plan
                                                              drawPlan();
                                                          }

                                                          public void widgetDefaultSelected(SelectionEvent e)
                                                          {
                                                              // Do nothing for now
                                                          }
                                                      };
}
