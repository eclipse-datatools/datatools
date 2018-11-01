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
package org.eclipse.datatools.sqltools.result.ui;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultsView;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

/**
 * This class is mainly used to control the UI related behaviour, and is mainly designed for WTP Output view's current
 * consumers to lessen the refactoring effort.
 * 
 * @author Dafan Yang
 */
public class ResultsViewUIAccessor
{
    private static ILogger               _log        = ResultsViewUIPlugin.getLogger(null);
    private IResultManager               _manager    = ResultsViewUIPlugin.getResultManager();
    private IWorkbenchPage               _activePage;
    private ResultsView                  _resultsView;
    private static ResultsViewUIAccessor _instance;
    private Object                       _syncMonitor = new Object();

    /* Tab types */
    public static final int              STATUS_TAB  = 1;
    public static final int              PARAM_TAB   = 2;
    public static final int              MESSAGE_TAB = 3;
    public static final int              RESULT_TAB  = 4;
    
    private ResultsViewUIAccessor()
    {

    }

    public static synchronized ResultsViewUIAccessor getInstance()
    {
        if (_instance == null)
        {
            _instance = new ResultsViewUIAccessor();
        }
        return _instance;
    }
    
    /**
     * Sets the focus to the given tab if the current result instance is the given instance. If there are multiple
     * message or result tab, we always set focus to the first one. There is no guarantee that it will succeed because
     * if the current displaying result instance is not the given one, we will do nothing.
     * 
     * @param cmd the operation request, should not be null
     * @param tabType the tab type
     * @see OperationCommand#STATUS_TAB
     * @see OperationCommand#PARAM_TAB
     * @see OperationCommand#MESSAGE_TAB
     * @see OperationCommand#RESULT_TAB
     */
    public void showTab(OperationCommand cmd, final int tabType)
    {
        checkResultsView();
        synchronized (_syncMonitor)
        {
            if (_resultsView == null)
            {
                return;
            }
            if (cmd == null)
            {
                return;
            }
            IResultInstance instance = _manager.getInstance(cmd);
            if (instance != null)
            {
                if (instance != _resultsView.getCurrentInstance())
                {
                    return;
                }
                final ResultsView view = _resultsView;
                _resultsView.getResultSection().getControl().getDisplay().asyncExec(new Runnable()
                {
                    public void run()
                    {
                        view.getResultSection().showTab(tabType);
                    }
                });
            }
        }
    }
    
    /**
     * Sets focus to the given message/result tab with the given tab number.
     * 
     * @param cmd the operation request, should not be null
     * @param tabType the tab type, can be the one of the following:
     * @see ResultsViewUIAccessor#RESULT_TAB
     * @see ResultsViewUIAccessor#MESSAGE_TAB
     * @param tabNum
     */
    public void showTab(OperationCommand cmd, final int tabType, final int tabNum)
    {
        checkResultsView();
        synchronized (_syncMonitor)
        {
            if (_resultsView == null)
            {
                return;
            }
            if (cmd == null)
            {
                return;
            }
            if (!(tabType == ResultsViewUIAccessor.RESULT_TAB || tabType == ResultsViewUIAccessor.MESSAGE_TAB))
            {
                return;
            }
            IResultInstance instance = _manager.getInstance(cmd);
            if (instance != null)
            {
                if (instance != _resultsView.getCurrentInstance())
                {
                    return;
                }
                final ResultsView view = _resultsView;
                _resultsView.getResultSection().getControl().getDisplay().asyncExec(new Runnable()
                {
                    public void run()
                    {
                        view.getResultSection().showTab(tabType, tabNum);
                    }
                });
            }
        }
    }
    
    /**
     * Sets the focus to the given result set if the current result instance is the given instance. There is no
     * guarantee that it will succeed because if the current displaying result instance is not the given one, we will do
     * nothing.
     * 
     * @param cmd the operation request, should not be null 
     * @param result the result set object
     */
    public void showTab(OperationCommand cmd, final IResultSetObject result)
    {
        checkResultsView();
        synchronized (_syncMonitor)
        {
            if (_resultsView == null)
            {
                return;
            }
            if (cmd == null || result == null)
            {
                return;
            }
            IResultInstance instance = _manager.getInstance(cmd);
            if (instance != null)
            {
                if (instance != _resultsView.getCurrentInstance())
                {
                    return;
                }
                final ResultsView view = _resultsView;
                _resultsView.getResultSection().getControl().getDisplay().asyncExec(new Runnable()
                {
                    public void run()
                    {
                        view.getResultSection().showResultSet(result);
                    }
                });
            }
        }
    }
    
    /**
     * Returns the row count of current displaying result set of the given result instance, if the current tab is not
     * ressult set or it is in single tab display mode, we will simply return -1. Also, if the current displaying result
     * instance is not the given one, will returns -1
     * 
     * @param cmd the operation request, should not be null
     * @return the row count of current displaying result
     */
    public int getRowCount(OperationCommand cmd)
    {
        checkResultsView();
        synchronized (_syncMonitor)
        {
            if (_resultsView == null)
            {
                return -1;
            }
            if (cmd == null)
            {
                return -1;
            }
            IResultInstance instance = _manager.getInstance(cmd);
            if (instance != null)
            {
                if (instance != _resultsView.getCurrentInstance())
                {
                    return -1;
                }
                return _resultsView.getResultSection().getRowCount();
            }
            return -1;
        }
    }
    
    /**
     * Shows the results view and sets the variable accordingly. When invoking this method, please make sure the caller
     * has the monitor of <code>_resultsView</code>
     * 
     */
    private void checkResultsView()
    {
        synchronized (_syncMonitor)
        {
            _resultsView = null;
            // get the active window
            IWorkbenchWindow activeWindow = ResultsViewUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();

            // if can not find the active window, select one from the workbench windows list
            if (activeWindow == null)
            {
                IWorkbenchWindow[] windows = ResultsViewUIPlugin.getDefault().getWorkbench().getWorkbenchWindows();
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
                    return;
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
                    return;
                }
            }

            activeWindow.getShell().getDisplay().syncExec(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        IViewPart view = _activePage.showView(ResultsConstants.SQL_RESULTS_VIEW_ID, null, IWorkbenchPage.VIEW_VISIBLE);
                        if (view instanceof ResultsView)
                        {
                            _resultsView = (ResultsView) view;
                        }
                    }
                    catch (PartInitException ex)
                    {
                        _log.error("ResultsViewAPI_checkview_error", ex); //$NON-NLS-1$
                    }
                }
            });
        }
    }
    
    /**
     * Gets the maximum length to display ellipsis[...] button 
     * @return the maximum length of parameter to display ellipsis[...] button
     */
    public int getMaxLengthToShowEllipses(){
        return ResultsViewUIPlugin.getDefault().getPreferenceStore().getInt(
                PreferenceConstants.ELLIPSIS_ENABLED_VALUE_LENGTH);
     }

}
