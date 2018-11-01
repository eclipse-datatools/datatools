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
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultSetViewerRegistryReader;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

/**
 * Removes all visible finished results. Always enabled.
 * 
 * @author Dafan Yang
 */
public class RemoveAllVisibleFinishedResultAction extends BaseSelectionListenerAction
{
    ISelectionProvider _provider;
    IResultInstance[]  _instances;
    /**
     * 
     */
    public RemoveAllVisibleFinishedResultAction(ISelectionProvider provider, IResultInstance[] results)
    {
        super(Messages.RemoveAllResultAction_remove_all_results); 
        this.setImageDescriptor(Images.DESC_REMOVEALL);
        this.setDisabledImageDescriptor(Images.DESC_REMOVEALL_DISABLE);
        this.setToolTipText(Messages.RemoveAllResultAction_remove_all_results_tooltip); 
        
        _provider = provider;
        _provider.addSelectionChangedListener(this);
        _instances = results;
        this.setEnabled(updateSelection((IStructuredSelection) _provider.getSelection()));
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.ACTION_REMOVE_ALL_VISIBLE_FINISHED_RESULT, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }

    protected boolean updateSelection(IStructuredSelection selection)
    {
        if(ResultsViewUIPlugin.getResultManager().getAllResults().length > 0)
        {
            return true;
        }
        return false;
    }
    
    public void run()
    {
        ResultsViewUIPlugin.getResultManager().removeResultInstances(_instances);
        ResultSetViewerRegistryReader.getInstance().removeViewerProviderContents();
    }

    public void setInstances(IResultInstance[] _instances)
    {
        this._instances = _instances;
    }
}
