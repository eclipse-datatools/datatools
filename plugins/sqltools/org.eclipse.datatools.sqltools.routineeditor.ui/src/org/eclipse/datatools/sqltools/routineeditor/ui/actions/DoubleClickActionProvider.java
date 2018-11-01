/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

/**
 * @author David Cui
 */
public class DoubleClickActionProvider extends CommonActionProvider
{

    protected IAction _doubleClickAction;
    protected ICommonActionExtensionSite _site;
    
    public void init(ICommonActionExtensionSite aSite) 
    {
        _site = aSite;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
     */
    public void fillActionBars(IActionBars actionBars) 
    {
        super.fillActionBars(actionBars);
        _doubleClickAction = new EditRoutineAction()
        {
            public void run() 
            {
                IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
                Object selectedResource = null;
                if (selection.size() == 1) 
                {
                    selectedResource = (Object) selection.getFirstElement();
                    //If you don't want the node expanded, comment the following line
                   ((TreeViewer) _site.getStructuredViewer()).setExpandedState(selectedResource,true);
                }
                init();
                initSQLObject(this, selectedResource);
                initConnectionProfile();
                super.run();
            }
        };
        actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, _doubleClickAction);
    }
}
