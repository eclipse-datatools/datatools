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
import org.eclipse.datatools.sqltools.result.IReExecutionRunnable;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

public class ReExecuteAction extends Action
{
    OperationCommand     _command;
    IReExecutionRunnable _runnable;

    public ReExecuteAction(OperationCommand cmd, IReExecutionRunnable runnable)
    {
        super();
        setText(Messages.ReExecuteAction_name); 
        _command = cmd;
        _runnable = runnable;
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.ACTION_REEXECUTE, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void run()
    {
        super.run();
        IResultInstance ins = ResultsViewUIPlugin.getResultManager().getInstance(_command);

        // clear the original result first
        ins.increaseFrequency();
        ins.resetInstance();
        
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                _runnable.reExecute(_command);
            }
        };

        // to prevent the client re-execution logic to block our UI, we use a brand new thread to re-execute this
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
