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
package org.eclipse.datatools.sqltools.plan;

import org.eclipse.datatools.sqltools.plan.internal.PlanConstants;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.IPlanManager;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.util.ILogger;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

/**
 * The facade of EPV (SQL Execution Plan View).
 * 
 * @author Dafan Yang
 */
public class EPVFacade
{
    private static ILogger   _log = PlanViewPlugin.getLogger(null);
    private static EPVFacade _instance;
    private IPlanManager     _manager = PlanViewPlugin.getPlanManager();
    private IWorkbenchPage   _activePage;
    
    private EPVFacade()
    {

    }

    /**
     * Creates an instance of execution plan, to display an execution plan on EPV, consumers must create a new plan
     * instance first
     * 
     * @param request the plan request
     * @return <code>true</code> if creation succeeds, <code>false</code> otherwise
     */
    public boolean createNewPlanInstance(PlanRequest request)
    {
        checkView(request.getMode());
        if(request == null)
        {
            return false;
        }
        if(_manager.getPlanInstance(request) != null)
        {
            return false;
        }
        IPlanInstance instance = _manager.createNewPlanInstance(request);
        return instance == null ? false : true;
    }

    /**
     * Returns the instance of <code>EPVFacade</code>
     * 
     * @return the instance of <code>EPVFacade</code>
     */
    public static synchronized EPVFacade getInstance()
    {
        if (_instance == null)
        {
            _instance = new EPVFacade();
        }
        return _instance;
    }

    /**
     * Informs the EPV that error occurs when getting the execution plan from the data server. The error information
     * will be displayed on EPV.
     * 
     * @param request the plan request
     * @param th the exception thrown out when retrieving the plan
     */
    public void planFailed(PlanRequest request, Throwable th)
    {
        checkView(request.getMode());
        if(request == null)
        {
            return;
        }
        IPlanInstance instance = _manager.getPlanInstance(request);
        if(instance == null || instance.isFinished())
        {
            return;
        }
        instance.finishFail(th);
    }

    /**
     * Informs the EPV that the plan raw data of the given plan request is successfully generated. The raw data of the
     * execution plan will be set to the plan instance.
     * <p>
     * Note that the <code>IPlanParser</code> will be responsible for parsing the raw data of plan into modeled data.
     * 
     * @see IPlanParser
     * @param request the plan request
     * @param rawPlan the raw data of execution plan
     */
    public void planGenerated(PlanRequest request, String rawPlan)
    {
        checkView(request.getMode());
        if(request == null)
        {
            return;
        }
        IPlanInstance instance = _manager.getPlanInstance(request);
        if(instance == null || instance.isFinished())
        {
            return;
        }
        instance.finishSuccess(rawPlan);
    }
    
    /**
     * Returns the status of the plan request
     * @param request the plan request
     * @return the status of the plan request
     */
    public int getStatus(PlanRequest request)
    {
        if(request == null)
        {
            return IPlanInstance.FAILED;
        }
        IPlanInstance instance = _manager.getPlanInstance(request);
        if(instance == null)
        {
            return IPlanInstance.FAILED;
        }
        return instance.getStatus();
    }
    
    /**
     * Checks if the SQL Execution Plan View is active, if not, create it.
     * 
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    private boolean checkView(final int mode)
    {
        // get the active window
        IWorkbenchWindow activeWindow = PlanViewPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();

        // if can not find the active window, select one from the workbench windows list
        if (activeWindow == null)
        {
            IWorkbenchWindow[] windows = PlanViewPlugin.getDefault().getWorkbench().getWorkbenchWindows();
            for (int i = 0; i < windows.length; i++)
            {
                activeWindow = windows[0];
                if (activeWindow != null)
                {
                    break;
                }
            }
            if (activeWindow == null)
            {
                return false;
            }
        }

        // get the active page in this window
        _activePage = activeWindow.getActivePage();

        // if can not find the active page, select one from page list
        if (_activePage == null)
        {
            IWorkbenchPage[] pages = activeWindow.getPages();
            for (int i = 0; i < pages.length; i++)
            {
                _activePage = pages[0];
                if (_activePage != null)
                {
                    break;
                }
            }
            if (_activePage == null)
            {
                return false;
            }
        }

        activeWindow.getShell().getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                try
                {
                    _activePage.showView(PlanConstants.PLAN_VIEW_ID, null, mode);
                }
                catch (PartInitException ex)
                {
                    _log.error("EPVFacade.checkview.error", ex); //$NON-NLS-1$
                }
            }
        });
        return true;
    }
}
