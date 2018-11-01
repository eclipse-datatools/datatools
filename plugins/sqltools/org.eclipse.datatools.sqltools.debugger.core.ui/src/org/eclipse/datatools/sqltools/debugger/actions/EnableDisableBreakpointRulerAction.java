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
package org.eclipse.datatools.sqltools.debugger.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * @author Yang Liu
 */
public class EnableDisableBreakpointRulerAction extends AbstractBreakpointRulerAction
{
    /**
     * Creates the action to enable/disable breakpoints
     */
    public EnableDisableBreakpointRulerAction(ITextEditor editor, IVerticalRulerInfo info)
    {
        setInfo(info);
        setTextEditor(editor);
        setText(DebuggerMessages.EnableDisableBreakpointRulerAction_enableBreakpoint); 
    }

    /**
     * @see Action#run()
     */
    public void run()
    {
        if (getBreakpoint() != null)
        {
            try
            {
                getBreakpoint().setEnabled(!getBreakpoint().isEnabled());
            }
            catch (CoreException e)
            {
                ErrorDialog.openError(getTextEditor().getEditorSite().getShell(), DebuggerMessages.EnableDisableBreakpointRulerAction_error, DebuggerMessages.EnableDisableBreakpointRulerAction_enableFailed, 
                e.getStatus());
            }
        }
    }

    /**
     * @see IUpdate#update()
     */
    public void update()
    {
        setBreakpoint(determineBreakpoint());
        if (getBreakpoint() == null)
        {
            setEnabled(false);
            return;
        }
        setEnabled(true);
        try
        {
            boolean enabled = getBreakpoint().isEnabled();
            setText(enabled ? DebuggerMessages.EnableDisableBreakpointRulerAction_disableBreakpoint : DebuggerMessages.EnableDisableBreakpointRulerAction_enableBreakpoint); 
        }
        catch (CoreException ce)
        {
            //			JDIDebugUIPlugin.log(ce);
        }
    }
}
