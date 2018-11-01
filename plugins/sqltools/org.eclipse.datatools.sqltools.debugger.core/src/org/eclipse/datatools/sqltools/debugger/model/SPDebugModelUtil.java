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
package org.eclipse.datatools.sqltools.debugger.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifierImpl;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.debugger.breakpoint.SPLineBreakpoint;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.routineeditor.util.RoutineEditorConstants;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;

/**
 * Utility class to process routine debug model objects.
 * @author Yang Liu
 */
public class SPDebugModelUtil
{
    /**
     * Returns the identifier of the debug model presentation the <code>SPLineBreakpoint</code> is
     * associated with.
     */
    public static String getModelIdentifier()
    {
        return DebuggerCorePlugin.PLUGIN_ID + ".debugModelPresentation"; //$NON-NLS-1$
    }

    /**
     * Creates a line breakpoint at the specified line for the specified proc.
     * 
     * @param procid <code>Routine</code> identifier
     * @param lineNumber breakpoint line
     */
    public static SPLineBreakpoint createLineBreakpoint(ProcIdentifier procid, int lineNumber, boolean register)
        throws CoreException
    {
        SPLineBreakpoint bp = new SPLineBreakpoint(procid, lineNumber, true);
        if (register)
        {
            DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(bp);
        }
        return bp;
    }

    /**
     * Returns the breakpoint object in a <code>Routine</code> identified by <code>procid</code>, at line <code>lineNumber</code>
     * @param procid <code>Routine</code> identifier
     * @param lineNumber line number
     * @return a <code>SPLineBreakpoint</code> if any, otherwise null.
     */
    public static SPLineBreakpoint findLineBreakpoint(ProcIdentifier procid, int lineNumber)
    {
        String modelId = getModelIdentifier();

        IBreakpointManager manager = DebugPlugin.getDefault().getBreakpointManager();
        IBreakpoint[] breakpoints = manager.getBreakpoints(modelId);
        for (int i = 0; i < breakpoints.length; i++)
        {
            if (!(breakpoints[i] instanceof SPLineBreakpoint))
            {
                continue;
            }
            SPLineBreakpoint breakpoint = (SPLineBreakpoint) breakpoints[i];
            IMarker marker = breakpoint.getMarker();

            if (marker != null && marker.exists())
            {
                try
                {
                    ProcIdentifier pi = breakpoint.getProcIdentifier();
                    if (!(procid.equals(pi)))
                    {
                        continue;
                    }

                    if (breakpoint.getLineNumber() == lineNumber) 
                    {
                        return breakpoint; 
                    }
                }
                catch (CoreException ex)
                {
                    continue;
                }
            }
        }
        return null;
    }

    /**
     * Finds breakpoints of all types in a <code>Routine</code> identified by <code>procid</code>, at line <code>lineNumber</code>
     * @param procid <code>Routine</code> identifier
     * @param lineNumber line number
     * @return list of <code>IBreakpoint</code>, may be empty
     */
    public static List findAllLineBreakpoint(ProcIdentifier procid, int lineNumber)
    {
        List ret = new ArrayList();
        String modelId = getModelIdentifier();

        IBreakpointManager manager = DebugPlugin.getDefault().getBreakpointManager();
        IBreakpoint[] breakpoints = manager.getBreakpoints(modelId);
        for (int i = 0; i < breakpoints.length; i++)
        {
            if (!(breakpoints[i] instanceof SPLineBreakpoint))
            {
                continue;
            }
            SPLineBreakpoint breakpoint = (SPLineBreakpoint) breakpoints[i];
            IMarker marker = breakpoint.getMarker();

            if (marker != null && marker.exists())
            {
                try
                {
                    ProcIdentifier pi = breakpoint.getProcIdentifier();
                    if (!(procid.equals(pi)))
                    {
                        continue;
                    }

                    if (breakpoint.getLineNumber() == lineNumber)
                    {
                        ret.add(breakpoint);
                    }
                }
                catch (CoreException ex)
                {
                    continue;
                }
            }
        }
        return ret;
    }

    /**
     * Tests whether the marker is a SP breakpoint marker for the specified routine.
     * 
     * @param marker
     * @param proc <code>Routine</code> identifier
     */
    public static boolean isSPBreakpointMarker(IMarker marker, ProcIdentifier proc)
    {
        try
        {
            String type = marker.getType();
            if (SPLineBreakpoint.MARKER_TYPE.equals(type))
            {
                // good, this is a SPbreakpoint marker, next check proc
                String str = marker.getAttribute(RoutineEditorConstants.ATTR_PROCIDENTIFIER, ""); //$NON-NLS-1$
                try
                {
                    ProcIdentifier id = ProcIdentifierImpl.decode(str);
                    return proc.equals(id);
                }
                catch (ParseException ex)
                {
                    DebuggerCorePlugin.getDefault().log(ex);
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        catch (CoreException ex)
        {
            DebuggerCorePlugin.getDefault().log(ex);
            return false;
        }
    }

    /**
     * Tests whether the markerDelta is a SP breakpoint marker delta for the specified routine.
     * @param markerDelta
     * @param identifier
     */
    public static boolean isSPBreakpointMarkerDelta(IMarkerDelta markerDelta, ProcIdentifier identifier)
    {
        String type = markerDelta.getType();
        if (SPLineBreakpoint.MARKER_TYPE.equals(type))
        {
            // good, this is a SPbreakpoint marker, next check proc
            String str = markerDelta.getAttribute(RoutineEditorConstants.ATTR_PROCIDENTIFIER, ""); //$NON-NLS-1$
            try
            {
                ProcIdentifier id = ProcIdentifierImpl.decode(str);
                return identifier.equals(id);
            }
            catch (ParseException ex)
            {
                DebuggerCorePlugin.getDefault().log(ex);
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Tests whether a breakpoint support condition. 
     * @param bp
     * @return true if no, otherwise false
     */
    public static boolean supportCondition(SPLineBreakpoint bp) throws CoreException
    {
    	//see bug 109112
        DatabaseDefinition dbDef = ProfileUtil.getDatabaseDefinition(bp.getProcIdentifier().getDatabaseIdentifier().getProfileName());
        if (dbDef != null )
        {
            return dbDef.getDebuggerDefinition()!= null && dbDef.getDebuggerDefinition().isConditionSupported();
        }
        return false;

    }

    /**
     * Finds all breakpoints belonging to the specified routine
     * @param procid <code>Routine</code> identifier
     * @return list of <code>SPLineBreakpoint</code>, may be empty
     */
    public static List findAllSPLineBreakpointForSP(ProcIdentifier procid)
    {
        List ret = new ArrayList();
        String modelId = getModelIdentifier();

        IBreakpointManager manager = DebugPlugin.getDefault().getBreakpointManager();
        IBreakpoint[] breakpoints = manager.getBreakpoints(modelId);
        for (int i = 0; i < breakpoints.length; i++)
        {
            if (!(breakpoints[i] instanceof SPLineBreakpoint))
            {
                continue;
            }
            SPLineBreakpoint breakpoint = (SPLineBreakpoint) breakpoints[i];
            try
            {
                ProcIdentifier pi = breakpoint.getProcIdentifier();
                if ((procid.equals(pi)))
                {
                    ret.add(breakpoint);
                }
            }
            catch(Exception ex)
            {
                // skip
            }
        }
        return ret;
    }

    /**
     * Removes all breakpoints for a particular routine.
     * @param procid <code>Routine</code> identifier
     */
    public static void removeAllBreakpointForSP(ProcIdentifier procid)
    {
        List list = findAllSPLineBreakpointForSP(procid);
        for (int i=0, size=list.size(); i<size; i++)
        {
            IBreakpoint bp = (IBreakpoint) list.get(i);
            try
            {
                bp.delete();
            }
            catch(Exception ex)
            {
                // skip
            }
        }
    }
    
    /**
     * Renames procid to newprocid in all breakpoints.
     * @param procid old <code>Routine</code> identifier
     * @param newprocid new <code>Routine</code> identifier
     */
    public static void changeAllBreakpointForSP(ProcIdentifier procid, ProcIdentifier newprocid)
    {
        IBreakpointManager manager = DebugPlugin.getDefault().getBreakpointManager();
        List list = findAllSPLineBreakpointForSP(procid);
        for (int i=0, size=list.size(); i<size; i++)
        {
            SPLineBreakpoint bp = (SPLineBreakpoint) list.get(i);            
            try
            {
                bp.setProcId(newprocid.encode());
            }
            catch(Exception ex)
            {
                // skip
            }
        }
    }
    
    public static boolean isProcInDebugging(ProcIdentifier procid)
    {
        if (procid == null)
        {
            return false;
        }
        ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
        IDebugTarget[] targets = manager.getDebugTargets();
        for (int i = 0; i < targets.length; i++)
        {
            if (targets[i] instanceof SPDebugTarget)
            {
                SPThread thread = ((SPDebugTarget)targets[i]).getSPThread();
                if (thread == null)
                {
                    continue;
                }
                IStackFrame[] frames;
                try
                {
                    frames = thread.getStackFrames();
                    for (int j = 0; j < frames.length; j++)
                    {
                        if (frames[j] instanceof SPStackFrame)
                        {
                            ProcIdentifier debugProc = ((SPStackFrame)frames[j]).getProcIdentifier();
                            if (procid.equalsByServer(debugProc))
                            {
                                return true;
                            }
                        }
                    }
                }
                catch (DebugException e)
                {
                }
            }
        }
        return false;
    }
    
    /**
     * Tests whether there is a procedural object is in debugging session 
     * @param connectionProfileName name of connection profile
     * @return Array of the procedural object name
     */
    public static String[] hasProcInDebugging(String connectionProfileName)
    {
        if (connectionProfileName == null || connectionProfileName.trim().length() < 1)
        {
            return null;
        }
        ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
        IDebugTarget[] targets = manager.getDebugTargets();
        List names = new ArrayList();
        for (int i = 0; i < targets.length; i++)
        {
            if (targets[i] instanceof SPDebugTarget)
            {
                SPThread thread = ((SPDebugTarget) targets[i]).getSPThread();
                if (thread == null)
                {
                    continue;
                }
                IStackFrame[] frames;
                try
                {
                    frames = thread.getStackFrames();
                    for (int j = 0; j < frames.length; j++)
                    {
                        if (frames[j] instanceof SPStackFrame)
                        {
                            ProcIdentifier debugProc = ((SPStackFrame) frames[j]).getProcIdentifier();
                            if (connectionProfileName.equals(debugProc.getDatabaseIdentifier().getProfileName()))
                            {
                                names.add(debugProc.getProcName());
                            }
                        }
                    }
                }
                catch (DebugException e)
                {
                }
            }
        }
        if (names.size() > 0)
        {
            return (String[])names.toArray(new String[names.size()]);
        }
        else 
        {
            return null;
        }
    }
    
    /**
     * Changes the profile name hold in DatabaseIdentifier of ProcIdentifier
     * @param oldName
     * @param newName
     */
    public static void changeProfileName(String oldName, String newName)
    {
        if (oldName == null || newName == null || oldName.equals(newName))
        {
            return;
        }
        
        ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
        
        IDebugTarget[] targets = manager.getDebugTargets();
        for (int i = 0; i < targets.length; i++)
        {
            if (targets[i] instanceof SPDebugTarget)
            {
                SPThread thread = ((SPDebugTarget) targets[i]).getSPThread();
                if (thread == null)
                {
                    continue;
                }
                IStackFrame[] frames;
                try
                {
                    //Change the profile name in StackFrames
                    frames = thread.getStackFrames();
                    for (int j = 0; j < frames.length; j++)
                    {
                        if (frames[j] instanceof SPStackFrame)
                        {
                            ProcIdentifier debugProc = ((SPStackFrame) frames[j]).getProcIdentifier();
                            DatabaseIdentifier databaseIdentifier = debugProc.getDatabaseIdentifier();
                            if (oldName.equals(databaseIdentifier.getProfileName()))
                            {
                                databaseIdentifier.setProfileName(newName);
                            }
                        }
                    }                    
                }
                catch (DebugException e)
                {
                }               
            }
        }
    }
}
