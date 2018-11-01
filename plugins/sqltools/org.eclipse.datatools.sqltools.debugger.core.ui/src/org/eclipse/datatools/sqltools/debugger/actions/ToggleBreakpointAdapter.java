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
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.debugger.breakpoint.SPLineBreakpoint;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerCoreUIPlugin;
import org.eclipse.datatools.sqltools.debugger.debug.BreakpointLocationHandler;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.IEditorStatusLine;

/**
 * @author Yang Liu
 */
public class ToggleBreakpointAdapter implements IToggleBreakpointsTarget
{
    protected void report(String message, IWorkbenchPart part)
    {
        IEditorStatusLine statusLine = (IEditorStatusLine) part.getAdapter(IEditorStatusLine.class);
        if (statusLine != null)
        {
            if (message != null)
            {
                statusLine.setMessage(true, message, null);
            }
            else
            {
                statusLine.setMessage(true, null, null);
            }
        }

        if (message != null && DebuggerCoreUIPlugin.getActiveWorkbenchShell() != null)
        {
            DebuggerCoreUIPlugin.getActiveWorkbenchShell().getDisplay().beep();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.actions.IToggleBreakpointsTarget#toggleLineBreakpoints(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException
    {
        if (selection instanceof ITextSelection)
        {
            IEditorPart editorPart = (IEditorPart) part;
            ITextSelection textSelection = (ITextSelection) selection;
            int lineNumber = textSelection.getStartLine() + 1;

            toggleLineBreakpoints(editorPart, lineNumber);
        }
    }

    /**
     * @param editorInput
     * @param lineNumber
     * @throws CoreException
     */
    public void toggleLineBreakpoints(IEditorPart editorPart, int lineNumber) throws CoreException
    {
        report(null, editorPart);
        IEditorInput editorInput = editorPart.getEditorInput();
        if (editorInput instanceof ProcEditorInput)
        {
            ProcIdentifier procid = ((ProcEditorInput) editorInput).getProcIdentifier();
            SPLineBreakpoint breakpoint = SPDebugModelUtil.findLineBreakpoint(procid, lineNumber);
            if (breakpoint != null)
            {
                // remove existing breakpoint
                DebugPlugin.getDefault().getBreakpointManager().removeBreakpoint(breakpoint, true);
            }
            else
            {
                breakpoint = SPDebugModelUtil.createLineBreakpoint(procid, lineNumber, true);
                BreakpointLocationHandler.handleNewBreakpoint(breakpoint, procid, lineNumber, editorPart);
            }
        }
        else
        {
            return;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.actions.IToggleBreakpointsTarget#canToggleLineBreakpoints(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public boolean canToggleLineBreakpoints(IWorkbenchPart part, ISelection selection)
    {
        if (!(selection instanceof ITextSelection))
        {
            return false;
        }
        if (!(part instanceof IEditorPart))
        {
            return false;
        }

        IEditorInput editorInput = ((IEditorPart) part).getEditorInput();
        if (!(editorInput instanceof ProcEditorInput))
        {
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.actions.IToggleBreakpointsTarget#toggleMethodBreakpoints(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void toggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.actions.IToggleBreakpointsTarget#canToggleMethodBreakpoints(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public boolean canToggleMethodBreakpoints(IWorkbenchPart part, ISelection selection)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.actions.IToggleBreakpointsTarget#toggleWatchpoints(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void toggleWatchpoints(IWorkbenchPart part, ISelection selection) throws CoreException
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.actions.IToggleBreakpointsTarget#canToggleWatchpoints(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public boolean canToggleWatchpoints(IWorkbenchPart part, ISelection selection)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
