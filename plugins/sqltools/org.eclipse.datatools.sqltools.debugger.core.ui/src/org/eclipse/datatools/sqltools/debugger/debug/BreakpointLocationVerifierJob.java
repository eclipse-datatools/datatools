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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.debugger.breakpoint.ISPBreakpoint;
import org.eclipse.datatools.sqltools.debugger.breakpoint.SPLineBreakpoint;
import org.eclipse.datatools.sqltools.debugger.core.IDebugHandler;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerCoreUIPlugin;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.texteditor.IEditorStatusLine;

/**
 * Job used to verify the position of a breakpoint
 */
public class BreakpointLocationVerifierJob extends Job 
{

    static final String NOT_VALID_LOCATION = Messages.BreakpointLocationVerifierJob_notValidLocation; 
    static final String BREAKPOINT_REMOVED = Messages.BreakpointLocationVerifierJob_breakpointRemoved; 
    static final String BREAKPOINT_SET = Messages.BreakpointLocationVerifierJob_breakpointSet; 
    static final String BREAKPOINT_MOVED_TO_VALID = Messages.BreakpointLocationVerifierJob_breakpointMovedToValid; 

    /**
     * all the breakpoint MUST belong to the single proc.
     */
    private ProcIdentifier  _proc;

    /**
	 * The temporary breakpoint that has been set.
	 */	
    private SPLineBreakpoint[] _breakpoints;

    /**
	 * The status line to use to display errors
	 */
    private IEditorStatusLine _statusLine;

    private boolean         _silentMode = true;

    /**
	 * 
	 * @param document
	 * @param breakpoint
	 * @param proc	can't be null
	 * @param lineNumber
	 * @param statusLine
     * @throws CoreException 
	 */
    public BreakpointLocationVerifierJob(SPLineBreakpoint breakpoint, IEditorStatusLine statusLine) throws CoreException 
    {
        this(breakpoint.getProcIdentifier(), new SPLineBreakpoint[]
        {
            breakpoint
        }
        , statusLine);
        _silentMode = false;
    }

    /**
     * @param bps
     * @param proc
     * @param line
     */
    public BreakpointLocationVerifierJob(ProcIdentifier proc, SPLineBreakpoint[] bps, IEditorStatusLine line)
    {
        super(Messages.BreakpointLocationVerifierJob_label); 
        _proc = proc;
        _breakpoints = bps;
        _statusLine = line;
        setSystem(true);
    }

    /**
	 * Given the known _lineNumber, need to find out a valid line number
	 * @return
	 */
    private int[] getValidLocation(ProcIdentifier proc, int lineNumbers[])
    {
    	IControlConnection con = EditorCorePlugin.getControlConnectionManager().getControlConnection(proc.getDatabaseIdentifier());
        if (con == null)
        {
            return lineNumbers; // no source.
        }
        IDebugHandler debugHandler = DebuggerCoreUIPlugin.getDefault().getDebugHandlerManager().getOrCreateDebugHandler(
            proc.getDatabaseIdentifier().getProfileName());
        
        //Change for Bug207122. To check the debugHandler to avoid NullPointException.
        if (debugHandler == null)
        {
            return lineNumbers;
        }
        
        try
        {
            return debugHandler.getValidBreakpointLocations(proc, lineNumbers, con);
        }
        catch (SQLException e)
        {
        	DebuggerCorePlugin.getDefault().log( e); //$NON-NLS-1$
            return lineNumbers;
        }
    }

    /**
     * Selects status with highest severity out of
     * several supplied in a parameter.
     * 
     * @param status Status messages.
     * 
     * @return Status with highest severity
     */
    public static IStatus findMostSevere(List statuses)
    {
        IStatus highest = null;

        for (int i = 0; i < statuses.size(); i++)
        {
            IStatus s = (IStatus) statuses.get(i);
            if (s.getSeverity() == IStatus.ERROR)
            {
                highest = s;
                break;
            }

            highest =
                (highest == null)
                ? s
                : (highest.getSeverity() > s.getSeverity())
                ? highest
                : s;
        }

        return highest;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public IStatus run(IProgressMonitor monitor) 
    {
        int lines[] = new int[_breakpoints.length];
        for (int i=0; i<_breakpoints.length; i++)
        {
            try
            {
                lines[i] = _breakpoints[i].getLineNumber();
            }
            catch (CoreException e)
            {
                lines[i] = -1;  // this breakpoint is invalid.
            }
        }
        int lineNumbers[] = getValidLocation(_proc, lines);

        List statuses = new ArrayList(_breakpoints.length);
        for (int i=0; i<_breakpoints.length; i++)
        {
            if (_breakpoints[i] instanceof SPLineBreakpoint)
            {
                IStatus status = runOne(monitor, _proc, _breakpoints[i], lines[i], lineNumbers[i]);
                if (status != null) statuses.add(status);
            }
        }
        return findMostSevere(statuses);
    }

    public IStatus runOne(IProgressMonitor monitor, ProcIdentifier proc, SPLineBreakpoint bp, int originLine, int valieLine)
    {
        try 
        {
            if (valieLine == -1) 
            {
                // cannot found a valid line
                report(NOT_VALID_LOCATION);
                if (bp != null) 
                {
                    DebugPlugin.getDefault().getBreakpointManager().removeBreakpoint(bp, true);
                }
                return new Status(IStatus.OK, DebuggerCorePlugin.PLUGIN_ID, IStatus.ERROR, NOT_VALID_LOCATION, null);
            }
            boolean differentLineNumber= valieLine != originLine;
            ISPBreakpoint breakpoint= SPDebugModelUtil.findLineBreakpoint(proc, valieLine);
            boolean breakpointExist= breakpoint != null;

            if (differentLineNumber) 
            {
                if (breakpointExist) 
                {
                    // there is already a breakpoint on the valid line.
                    //                        report(NOT_VALID_LOCATION);
                    DebugPlugin.getDefault().getBreakpointManager().removeBreakpoint(bp, true);
                    return new Status(IStatus.OK, DebuggerCorePlugin.PLUGIN_ID, IStatus.ERROR, NOT_VALID_LOCATION, null);
                }

                // we can't simply remove it and recreate, since that may lose 'condition' information in the bp.
                bp.setRegistered(false);
                bp.setLineNumber(valieLine);
                bp.setRegistered(true);
                //                    DMPDebugModel.createLineBreakpoint(proc, valieLine, true);
                //                    DebugPlugin.getDefault().getBreakpointManager().removeBreakpoint(bp, true);
                return new Status(IStatus.OK, DebuggerCorePlugin.PLUGIN_ID, IStatus.WARNING, BREAKPOINT_MOVED_TO_VALID, null);
            }
            //            }
        }
        catch (CoreException e) 
        {
        	DebuggerCorePlugin.getDefault().log(Messages.BreakpointLocationVerifierJob_breakpoint_verification_fail, e); 
        }
        return new Status(IStatus.OK, DebuggerCorePlugin.PLUGIN_ID, IStatus.OK, BREAKPOINT_SET, null);
    }


    protected void report(final String message) 
    {
        if (_silentMode) return;
        Display display;
        display= Display.getCurrent();
        if (display == null)
        display= Display.getDefault();

        if (display == null) return;
        display.asyncExec(new Runnable() 
        {
            public void run() 
            {
                if (_statusLine != null) 
                {
                    _statusLine.setMessage(true, message, null);
                }
                if (message != null && DebuggerCoreUIPlugin.getActiveWorkbenchShell() != null) 
                {
                    Display.getCurrent().beep();
                }
            }
        }
        );
    }
}
