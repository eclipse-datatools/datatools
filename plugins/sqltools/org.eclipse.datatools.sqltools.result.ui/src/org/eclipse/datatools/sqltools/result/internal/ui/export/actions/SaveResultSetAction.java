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
package org.eclipse.datatools.sqltools.result.internal.ui.export.actions;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.export.SaveResultSetDialog;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Saves the result set object.
 * 
 * @author Dafan Yang
 */
public class SaveResultSetAction extends Action
{
    IResultSetObject _result;
    Shell            _shell;

    public SaveResultSetAction(Shell shell, IResultSetObject result)
    {
        super(Messages.ResultSetAction_Title); 
        this._shell = shell;
        _result = result;
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.ACTION_SAVE_RESULTSET, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }

    Shell getShell()
    {
        return _shell;
    }

    IResultSetObject getResultSetObject()
    {
        return _result;
    }

    public void run()
    {
        SaveResultSetDialog dialog = new SaveResultSetDialog(getShell(), getResultSetObject());
        dialog.setOriginalName("result.txt");
        dialog.open();
    }
}
