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
package org.eclipse.datatools.sqltools.result.internal.ui.actions;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.Images;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

/**
 * To teminate an none-finished result instance
 * 
 * @author Dafan Yang
 */
public class TerminateInstanceAction extends BaseSelectionListenerAction
{
    ISelectionProvider _provider;

    /**
     * 
     */
    public TerminateInstanceAction(ISelectionProvider provider)
    {
        super(Messages.TerminateInstanceAction_terminate); 
        this.setImageDescriptor(Images.DESC_TERMINATE);
        this.setDisabledImageDescriptor(Images.DESC_TERMINATE_DISABLE);
        setToolTipText(Messages.TerminateInstanceAction_terminate_tooltip); 

        _provider = provider;
        _provider.addSelectionChangedListener(this);
        this.setEnabled(updateSelection((IStructuredSelection) _provider.getSelection()));
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.ACTION_TERMINATE_INSTANCE, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void dispose()
    {
        _provider.removeSelectionChangedListener(this);
    }

    protected boolean updateSelection(IStructuredSelection selection)
    {
        if (selection == null)
        return false;
        Object[] obj = selection.toArray();
        if (obj == null || obj.length == 0)
        {
            return false;
        }
        else
        {
            for (int i = 0; i < obj.length; i++)
            {
                if (obj[i] instanceof IResultInstance)
                {
                    if (((IResultInstance) obj[i]).isFinished()/* || !((IResultInstance) obj[i]).hasTerminateHandler() */)
                    {
                        // finished results
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            // all are running.
            return true;
        }
    }

    public void run()
    {
        IStructuredSelection sel = this.getStructuredSelection();
        if (sel == null)
        {
            return;    
        }

        Object[] objs = sel.toArray();
        if (objs != null)
        {
            for (int i = 0; i < objs.length; i++)
            {
                if (objs[i] instanceof IResultInstance)
                {
                    ((IResultInstance) objs[i]).terminate();
                }
            }
        }
    }

    /**
     * This is called when there is instance created/finished, may resulting in update the status of this action.
     */
    public void update()
    {
        this.setEnabled(updateSelection(this.getStructuredSelection()));
    }
}
