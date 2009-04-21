/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.ui;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.Images;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultsView;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

public class ResultViewUIUtil {
    
    /**
     * Returns the image of given OperationCommand status
     * 
     * @param statusId the status id
     * @return the image of this status
     */
    public static Image getOperationCommandStatusImage(int statusId)
    {
        switch (statusId)
        {
            case OperationCommand.STATUS_STARTED:
                return Images.get(Images.IMG_STARTED);
            case OperationCommand.STATUS_RUNNING:
                return Images.get(Images.IMG_RUNNING);
            case OperationCommand.STATUS_SUCCEEDED:
                return Images.get(Images.IMG_SUCCESS);
            case OperationCommand.STATUS_FAILED:
                return Images.get(Images.IMG_FAIL);
            case OperationCommand.STATUS_TERMINATED:
                return Images.get(Images.IMG_TERMINATE);
            case OperationCommand.STATUS_WARNING:
                return Images.get(Images.IMG_WARNING);
            case OperationCommand.STATUS_CRITICAL_ERROR:
                return Images.get(Images.IMG_CRITICAL);
            default:
                return Images.get(Images.IMG_FAIL);
        }
    }
    
    /**
     * Checks if the SQL Results View is active, if not, create it and bring it to the top.
     * 
     * @return <code>true</code> if operation succeeds; <code>false</code> otherwise
     */
    static public ResultsView checkResultView()
    {
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
                return null;
            }
        }

        // get the active page in this window
        final IWorkbenchPage activePage = getActivePage(activeWindow);

        if (activePage == null)
        {
        	return null;
        }

        activeWindow.getShell().getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                try
                {
                	IViewPart view = activePage.findView(ResultsConstants.SQL_RESULTS_VIEW_ID);
                    if(activePage.isPartVisible(view))
                    {
                    	return;
                    }
                    activePage.showView(ResultsConstants.SQL_RESULTS_VIEW_ID, null, IWorkbenchPage.VIEW_VISIBLE);
                }
                catch (PartInitException ex)
                {
                    ResultsViewUIPlugin.getLogger(null).error("ResultsViewAPI_checkview_error", ex); //$NON-NLS-1$
                }
            }
        });
        
        return (ResultsView)activePage.findView(ResultsConstants.SQL_RESULTS_VIEW_ID);
    }
    
    static private IWorkbenchPage getActivePage(IWorkbenchWindow activeWindow)
    {
    	// get the active page in this window
        IWorkbenchPage activePage = activeWindow.getActivePage();

        // if can not find the active page, select one from page list
        if (activePage == null)
        {
            IWorkbenchPage[] pages = activeWindow.getPages();
            for (int i = 0; i < pages.length; i++)
            {
                activePage = pages[0];
                if (activePage != null)
                {
                    break;
                }
            }
        }
        
        return activePage;
    }
}
