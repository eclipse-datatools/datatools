/*******************************************************************************
 * Copyright (c) 2005, 2011 Sybase, Inc. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.ui;

import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.Images;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultsView;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

public class ResultViewUIUtil 
{
	private static ResultViewUIUtil instance = new ResultViewUIUtil();    
    private UIRunnable uiRunnable;
    
    public ResultViewUIUtil()
	{
		uiRunnable = new UIRunnable();
	}
    
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
     	ResultsViewUIPlugin.getDefault().getWorkbench().getDisplay().syncExec(ResultViewUIUtil.instance.uiRunnable);
     	if ( ResultViewUIUtil.instance.uiRunnable.getActiveWindow() == null ||
     			ResultViewUIUtil.instance.uiRunnable.getActivePage() == null )
     	{
     		return null;
     	}
     	return (ResultsView)ResultViewUIUtil.instance.uiRunnable.getActivePage().findView(ResultsConstants.SQL_RESULTS_VIEW_ID);    	
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
    
    private class UIRunnable implements Runnable
    {
    	private IWorkbenchWindow activeWindow = null;
    	private IWorkbenchPage activePage = null;

    	public void run() 
    	{
    	    activeWindow = ResultsViewUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();

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
    	    activePage = ResultViewUIUtil.getActivePage(activeWindow);

    	    if (activePage == null)
    	    {
    	        return;
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
    	}

    	public IWorkbenchWindow getActiveWindow()
    	{
    	    return activeWindow;
    	}

    	public IWorkbenchPage getActivePage()
    	{
    	    return activePage;
    	}    	
    }
}
