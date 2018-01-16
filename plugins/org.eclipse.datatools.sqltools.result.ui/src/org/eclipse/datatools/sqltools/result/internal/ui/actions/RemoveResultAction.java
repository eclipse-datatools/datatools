/*
 * Created on 2004-6-30 Copyright Sybase, Inc. All rights reserved.
 */
package org.eclipse.datatools.sqltools.result.internal.ui.actions;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.Images;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * This action is for remove the current selected result instance. It will only be enabled when the result instance is
 * finished.
 * 
 * @author Dafan Yang
 */
public class RemoveResultAction extends BaseSelectionListenerAction implements IUpdate
{
    ISelectionProvider _provider;

    /**
     * 
     */
    public RemoveResultAction(ISelectionProvider provider)
    {
        super(Messages.RemoveResultAction_remove); 
        this.setImageDescriptor(Images.DESC_REMOVE);
        this.setDisabledImageDescriptor(Images.DESC_REMOVE_DISABLE);
        this.setToolTipText(Messages.RemoveResultAction_remove_result); 

        _provider = provider;
        _provider.addSelectionChangedListener(this);
        this.setEnabled(updateSelection((IStructuredSelection) _provider.getSelection()));
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.ACTION_REMOVE_RESULT, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void dispose()
    {
        _provider.removeSelectionChangedListener(this);
    }

    protected boolean updateSelection(IStructuredSelection selection)
    {
        if (selection == null)
        {
            return false;
        }
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
                    if (!((IResultInstance) obj[i]).isFinished())
                    {
                        return false;
                    }
                    if (!((IResultInstance) obj[i]).isParentResult())
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
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
                    ResultsViewUIPlugin.getResultManager().removeResultInstance((IResultInstance) objs[i]);
                }
            }
        }
        
        IResultInstance[] instances = ResultsViewUIPlugin.getResultManager().getAllResults();
        if(instances.length > 0)
        {
            _provider.setSelection(new StructuredSelection(instances[0]));
        }
    }

    /**
     * this is called when there is instance finished, may resulting in update the status of this action.
     */
    public void update()
    {
        this.setEnabled(updateSelection(this.getStructuredSelection()));
    }
}
