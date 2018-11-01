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
package org.eclipse.datatools.sqltools.debugger.debug;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.debugger.breakpoint.SPLineBreakpoint;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.IEditorStatusLine;

/**
 * The strategy for breakpoint location handling is as this:\
 * <p>
 * We do breakpoint location validation at two points:
 * <ul>
 * <li>when the breakpoint is first added into the system.
 * <li>when the proc is saved, we need to readjust the breakpoints to valid location.
 * </ul>
 * </p>
 * 
 * @author Yang Liu
 */
public class BreakpointLocationHandler 
{

    public static void handleNewBreakpoint(SPLineBreakpoint breakpoint, ProcIdentifier proc, int lineNumber, IEditorPart editor)
    {
        if (editor.isDirty())
        {
            // when editor is dirty, we can't expect database server to tell us any information about
            // breakpoint validation.
            // So we'll do a breakpoint refresh when the editor is saved. So don't do anything here.
            // TODO: if parser support telling us whether a line is valid
            return;
        }
        else
        {

            try
            {
                new BreakpointLocationVerifierJob(breakpoint, (IEditorStatusLine) editor.getAdapter(IEditorStatusLine.class)).schedule();
            }
            catch (CoreException e)
            {
            	DebuggerCorePlugin.getDefault().log( e);
            }
        }
    }

    public static void handleEditorSave(ProcIdentifier proc, IEditorPart editor)
    {
        List breakpoints = SPDebugModelUtil.findAllSPLineBreakpointForSP(proc);
        if (breakpoints == null || breakpoints.isEmpty()) return;

        SPLineBreakpoint[] bps = (SPLineBreakpoint[]) breakpoints.toArray(new SPLineBreakpoint[breakpoints.size()]);
        // ok, next we need to refresh all the breakpoints. Since if the breakpoit is added when the editor
        // is dirty, we didn't do any adjust for the location.

        new BreakpointLocationVerifierJob(proc, bps, (IEditorStatusLine) editor.getAdapter(IEditorStatusLine.class)).schedule();
    }

}
