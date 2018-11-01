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

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.plan.IHelpConstants;
import org.eclipse.datatools.sqltools.plan.IPlanDrawer;
import org.eclipse.datatools.sqltools.plan.IPlanParser;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.plan.PlanServiceRegistry;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.IPlanManagerListener;
import org.eclipse.datatools.sqltools.plan.internal.PlanConstants;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.plan.internal.ui.actions.LoadPlanAction;
import org.eclipse.datatools.sqltools.plan.internal.ui.actions.PlanDropDownAction;
import org.eclipse.datatools.sqltools.plan.internal.ui.actions.PlanTypeDropDownAction;
import org.eclipse.datatools.sqltools.plan.internal.ui.actions.RemoveAllPlansAction;
import org.eclipse.datatools.sqltools.plan.internal.ui.actions.RemovePlanAction;
import org.eclipse.datatools.sqltools.plan.internal.ui.actions.SavePlanAction;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.ViewPart;

/**
 * The SQL Execution Plan view
 * 
 * @author Hui Cao
 */
public class PlanView extends ViewPart
{
    // Group definitions
    public static final String     GROUP_NAVIGATE    = "group.navigate";                      //$NON-NLS-1$
    public static final String     GROUP_REMOVE      = "group.remove";                        //$NON-NLS-1$
    public static final String     GROUP_IO          = "group.io";                            //$NON-NLS-1$
    public static final String     GROUP_HISTORY     = "group.history";                       //$NON-NLS-1$
    public static final String     GROUP_MODE        = "group.mode";                          //$NON-NLS-1$
    
    private Text                   _textPlan;
    private PageBook               _fPagebook;
    private Label                  _fNoPlanShownLabel;
    private Label                  _fWorkingLabel;
    private GraphicsPlanControl    _graphicsControl;
    private IPlanInstance          _currentPlan;
    private PlanDropDownAction     _plansDropDownAction;
    private RemovePlanAction       _removePlanAction;
    private RemoveAllPlansAction   _removeAllPlansAction;
    private SavePlanAction         _savePlanAction;
    private LoadPlanAction         _loadPlanAction;
    private PlanTypeDropDownAction _planModeDropDownAction;
    private Action                 _preferenceAction;
    private static final String    _LINE_BREAK       = System.getProperty("line.separator");
    private VerticalLayoutAction   _vLayoutAction;
    private HorizontalLayoutAction _hLayoutAction;
    private static final String    ORIENTATION_GROUP = "orientation";

    private static final String    FONT_STYLE        = "Courier New";
    private static final int       FONT_SIZE         = 10;
    
    /**
     * 
     */
    public PlanView()
    {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent)
    {
    	PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, HelpUtil.getContextId(IHelpConstants.PLAN_VIEW, PlanViewPlugin.getDefault().getBundle().getSymbolicName()));
        _fPagebook = new PageBook(parent, SWT.NONE);

        // Page 1 of page book (no plan label)
        _fNoPlanShownLabel = new Label(_fPagebook, SWT.TOP + SWT.LEFT + SWT.WRAP);
        _fNoPlanShownLabel.setText(Messages.getString("PlanView.no.plan.shown")); //$NON-NLS-1$

        // Page 2 of page book (working label)
        _fWorkingLabel = new Label(_fPagebook, SWT.TOP + SWT.LEFT + SWT.WRAP);
        _fWorkingLabel.setText(Messages.getString("PlanView.working")); //$NON-NLS-1$

        // Page 3 of page book (text Control )
        _textPlan = new Text(_fPagebook, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        _textPlan.setEditable(false);
        _textPlan.setBackground(ColorConstants.white);
        FontData fd = new FontData(FONT_STYLE, FONT_SIZE, SWT.NORMAL);
        final Font font = new Font(_textPlan.getDisplay(), fd);
        _textPlan.setFont(font);
        _textPlan.addDisposeListener(new DisposeListener()
        {
            public void widgetDisposed(DisposeEvent e)
            {
                if ( font != null && !font.isDisposed() )
                {
                    font.dispose();
                }
            }
        });
        
        // Page 4 of page book (graphics Control )
        _graphicsControl = new GraphicsPlanControl(_fPagebook, SWT.NONE);

        _fPagebook.showPage(_fNoPlanShownLabel);

        createActions();
        initializeToolBar();

        MenuManager menuBar = (MenuManager) this.getViewSite().getActionBars().getMenuManager();
        menuBar.add(_preferenceAction);
        menuBar.add(new Separator(ORIENTATION_GROUP));
        menuBar.appendToGroup(ORIENTATION_GROUP, _vLayoutAction);
        menuBar.appendToGroup(ORIENTATION_GROUP, _hLayoutAction);

        PlanViewPlugin.getPlanManager().addPlanManagerListener(_listener);
    }

    public static void createStandardGroups(IContributionManager menu)
    {
        menu.add(new Separator(GROUP_NAVIGATE));
        menu.add(new GroupMarker(GROUP_REMOVE));
        menu.add(new Separator(GROUP_IO));
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menu.add(new Separator(GROUP_HISTORY));
        menu.add(new Separator(GROUP_MODE));
    }

    private void initializeToolBar()
    {
        IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
        createStandardGroups(tbm);
        tbm.appendToGroup(GROUP_REMOVE, _removePlanAction);
        tbm.appendToGroup(GROUP_REMOVE, _removeAllPlansAction);
        tbm.appendToGroup(GROUP_IO, _savePlanAction);
        tbm.appendToGroup(GROUP_IO, _loadPlanAction);
        tbm.appendToGroup(GROUP_HISTORY, _plansDropDownAction);
        tbm.appendToGroup(GROUP_MODE, _planModeDropDownAction);
        _preferenceAction = new Action(Messages.getString("PlanView.preference")) //$NON-NLS-1$
        {
            public void run()
            {
                String[] preferencePages =
                {
                    PreferenceConstants.PLAN_PREFERENCE_PAGE_ID
                };
                PreferencesUtil.createPreferenceDialogOn(null, preferencePages[0], preferencePages, null).open();
            }
        };
        _vLayoutAction = new VerticalLayoutAction(_graphicsControl.getSash());
        _hLayoutAction = new HorizontalLayoutAction(_graphicsControl.getSash());

        configPlanLayout();
        
        getViewSite().getActionBars().updateActionBars();
    }

    private void createActions()
    {
        _removePlanAction = new RemovePlanAction(this);
        _removeAllPlansAction = new RemoveAllPlansAction();
        _plansDropDownAction = new PlanDropDownAction(this);
        _savePlanAction = new SavePlanAction();
        _loadPlanAction = new LoadPlanAction();
        _planModeDropDownAction = new PlanTypeDropDownAction(this);
        updateActions();
    }

    private void updateActions()
    {
        _removePlanAction.update();
        _removeAllPlansAction.update();
        _plansDropDownAction.update();
        _savePlanAction.update();
        _planModeDropDownAction.update();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#dispose()
     */
    public void dispose()
    {
        super.dispose();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    public void setFocus()
    {
        // Do nothing
    }

    /**
     * Returns the current plan shown
     * 
     * @return the current plan shown
     */
    public IPlanInstance getCurrentPlan()
    {
        return _currentPlan;
    }

    /**
     * Get the plan layout according to preference store, and configure the layout immediately.
     */
    private void configPlanLayout()
    {
        if (PlanViewPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.VERTICAL_LAYOUT_PLAN_VIEW))
        {
            _vLayoutAction.setChecked(true);
            _hLayoutAction.setChecked(false);
            _vLayoutAction.run();
        }
        else
        {
            _hLayoutAction.setChecked(true);
            _vLayoutAction.setChecked(false);
            _hLayoutAction.run();
        }
    }
    
    /**
     * Shows the given execution plan
     * 
     * @param instance an execution plan instance
     */
    public void showPlan(final IPlanInstance instance)
    {
        if (_fPagebook.isDisposed())
        {
            return;
        }

        _currentPlan = instance;
        _fPagebook.getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                // when getting the plan, refresh plan layout according to preference store.
                configPlanLayout();
                
                Control control = _textPlan;
                String label = "";
                if (instance == null)
                {
                    control = _fNoPlanShownLabel;
                }

                if (instance != null)
                {
                    if (!instance.isFinished())
                    {
                        control = _fWorkingLabel;
                    }
                    else
                    {
                        String rawPlan;
                        if (instance.getStatus() == IPlanInstance.SUCCESS)
                        {
                            rawPlan = instance.getRawPlan();
                            int planType = instance.getPlanRequest().getPlanType();
                        	IPlanService service = PlanServiceRegistry.getInstance().getPlanService(instance.getPlanRequest().getDatabaseDefinitionId());
                        	boolean isGraphicPlan = service.getPlanOption().isGraphicPlan(planType);

                            if (isGraphicPlan) {
								if (rawPlan == null || rawPlan.trim().equals("")) {
									control = _fNoPlanShownLabel;
								} else {
									if (!isGraphicPlanSupported(instance)) {
										return;
									}
									_graphicsControl.setPlan(instance);
									_graphicsControl.update();
									control = _graphicsControl;
								}
							} else {
								control = _textPlan;
								_textPlan
										.setText(Messages
												.getString("PlanView.sql") + "\n" + instance.getPlanRequest().getSql() + "\n" //$NON-NLS-1$
												+ rawPlan);
							}
                        }
                        else if (instance.getStatus() == IPlanInstance.FAILED)
                        {
                            String errorMsg = instance.getFailThrowable().getMessage();
                            _textPlan.setText(errorMsg);
                            control = _textPlan;
                        }
                    }
                    // Content description label
                    label = Messages.getString("PlanView.sql") + " " + instance.getPlanRequest().getSql(); //$NON-NLS-1$
                    label = label.trim();
                    label = label.replaceAll(_LINE_BREAK, " ");

                    // In case the editor uses \n as line break
                    label = label.replaceAll("\n", " ");
                }
                setContentDescription(label);
                _fPagebook.showPage(control);
            }
        });
        updateActions();
    }

    /**
     * Checks if graphic plan is supported by the given database
     * 
     * @param instance a plan instance
     * @return <code>false</code> if graphic plan is not supported for the given database
     */
    public boolean isGraphicPlanSupported(IPlanInstance instance)
    {
        IPlanService planService = PlanServiceRegistry.getInstance().getPlanService(
                instance.getPlanRequest().getDatabaseDefinitionId());
        if (planService == null)
        {
            Exception noExtensionFound = new Exception(Messages.getString("ExecutionPlansDrawer.no.extension", instance
                    .getPlanRequest().getDatabaseDefinitionId(), instance.getPlanRequest().getDatabaseDefinitionId(),
                    PlanConstants.PLUGIN_ID, PlanConstants.PLAN_SERVICE_EXTENSION_POINT));
            instance.finishFail(noExtensionFound);
            return false;
        }
        IPlanDrawer drawer = planService.getPlanDrawer();
        if (drawer == null)
        {
            Exception noDrawerFound = new Exception(Messages.getString("ExecutionPlansDrawer.no.drawer", instance
                    .getPlanRequest().getDatabaseDefinitionId()));
            instance.finishFail(noDrawerFound);
            return false;
        }
        IPlanParser parser = planService.getPlanParser();
        if (parser == null)
        {
            Exception noParserFound = new Exception(Messages.getString("ExecutionPlansDrawer.no.parser", instance
                    .getPlanRequest().getDatabaseDefinitionId()));
            instance.finishFail(noParserFound);
            return false;
        }
        return true;
    }
    
    IPlanManagerListener _listener = new IPlanManagerListener()
                                   {
                                       /*
                                         * (non-Javadoc)
                                         * 
                                         * @see org.eclipse.datatools.sqltools.plan.internal.IPlanManagerListener#planInstanceCreated(org.eclipse.datatools.sqltools.plan.internal.IPlanInstance)
                                         */
                                       public void planInstanceCreated(final IPlanInstance plan)
                                       {
                                           showPlan(plan);
                                       }

                                       /*
                                         * (non-Javadoc)
                                         * 
                                         * @see org.eclipse.datatools.sqltools.plan.internal.IPlanManagerListener#planInstanceRemoved(org.eclipse.datatools.sqltools.plan.internal.IPlanInstance)
                                         */
                                       public void planInstanceRemoved(IPlanInstance plan)
                                       {
                                           // _plansDropDownAction.disposeMenu();
                                           if (_currentPlan == plan)
                                           {
                                               showPlan(null);
                                           }

                                       }

                                       /*
                                         * (non-Javadoc)
                                         * 
                                         * @see org.eclipse.datatools.sqltools.plan.internal.IPlanManagerListener#planInstancesRemoved()
                                         */
                                       public void planInstancesRemoved()
                                       {
                                           _plansDropDownAction.disposeMenu();
                                           showPlan(null);
                                       }

                                       /*
                                        * (non-Javadoc)
                                        * @see org.eclipse.datatools.sqltools.plan.internal.IPlanManagerListener#planInstanceFinished(org.eclipse.datatools.sqltools.plan.internal.IPlanInstance)
                                        */
                                       public void planInstanceFinished(final IPlanInstance instance)
                                       {
                                           if (_currentPlan == instance)
                                           {
                                               showPlan(instance);
                                           }
                                       }
                                   };

}

class VerticalLayoutAction extends Action
{
    SashForm _sash;

    /**
     * 
     */
    public VerticalLayoutAction(SashForm sash)
    {
        super(Messages.getString("PlanView.vertical.orientation"), IAction.AS_RADIO_BUTTON); //$NON-NLS-1$
        _sash = sash;
    }

    public void run()
    {
        _sash.setOrientation(SWT.VERTICAL);
    }

    public ImageDescriptor getImageDescriptor()
    {
        return Images.DESC_VERTICAL_PLAN_VIEW;
    }
}

class HorizontalLayoutAction extends Action
{
    SashForm _sash;

    /**
     *  
     */
    public HorizontalLayoutAction(SashForm sash)
    {
        super(Messages.getString("PlanView.horizontal.orientation"), IAction.AS_RADIO_BUTTON); //$NON-NLS-1$
        _sash = sash;
    }

    public void run()
    {
        _sash.setOrientation(SWT.HORIZONTAL);
    }

    public ImageDescriptor getImageDescriptor()
    {
        return Images.DESC_HORIZONTAL_PLAN_VIEW;
    }
}
