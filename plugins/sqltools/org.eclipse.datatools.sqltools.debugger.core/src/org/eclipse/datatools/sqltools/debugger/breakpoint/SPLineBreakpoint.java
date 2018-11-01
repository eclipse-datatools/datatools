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
package org.eclipse.datatools.sqltools.debugger.breakpoint;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifierImpl;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.datatools.sqltools.routineeditor.util.RoutineEditorConstants;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.LineBreakpoint;

/**
 * Representing a line breakpoint in a <code>Routine</code>. This class is not intended to be subclassed.
 * 
 * @author Yang Liu
 */
public class SPLineBreakpoint extends LineBreakpoint implements ISPBreakpoint
{
    /**
     * The maker type associated with this breakpoint.
     */
    public static final String MARKER_TYPE         = DebuggerCorePlugin.PLUGIN_ID + ".ui.spLineBreakpointMarker"; //$NON-NLS-1$
    /**
     * Breakpoint attribute describing the breakpoint condition.
     */
    static final String        ATTR_CONDITION      = DebuggerCorePlugin.PLUGIN_ID + ".condition"; //$NON-NLS-1$

    /**
     * Breakpoint attribute indicating whether the breakpoint supports condition. 
     */
    static final String        ATTR_CONDITIONENABLED = DebuggerCorePlugin.PLUGIN_ID + ".conditionEnabled"; //$NON-NLS-1$

    public SPLineBreakpoint()
    {
    }

    /**
     * 
     */
    public SPLineBreakpoint(final ProcIdentifier procId, final int lineNumber, final boolean enabled)
        throws DebugException
    {
        super();
        IWorkspaceRunnable wr = new IWorkspaceRunnable()
        {
            public void run(IProgressMonitor monitor) throws CoreException
            {

                // create the marker
                setMarker(ResourcesPlugin.getWorkspace().getRoot().createMarker(MARKER_TYPE));

                // add attributes
                Map attributes = new HashMap();
                attributes.put(IBreakpoint.ID, SPDebugModelUtil.getModelIdentifier());
                attributes.put(IBreakpoint.ENABLED, new Boolean(enabled));
                attributes.put(IMarker.LINE_NUMBER, new Integer(lineNumber));
                attributes.put(IMarker.CHAR_START, new Integer(-1));
                attributes.put(IMarker.CHAR_END, new Integer(-1));

                attributes.put(RoutineEditorConstants.ATTR_PROCIDENTIFIER, procId.encode());

                // set attributes
                ensureMarker().setAttributes(attributes);
            }
        }
        ;
        try
        {
            ResourcesPlugin.getWorkspace().run(wr, ResourcesPlugin.getWorkspace().getRoot(), 0, null);
        }
        catch (CoreException e)
        {
            throw new DebugException(e.getStatus());
        }
    }

    public void setConditionEnabled(boolean enabled) throws CoreException 
    {
        if (enabled != isConditionEnabled()) 
        {
            setAttribute(ATTR_CONDITIONENABLED, enabled);
        }
    }


    public boolean isConditionEnabled() throws CoreException 
    {
        return getMarker().getAttribute(ATTR_CONDITIONENABLED, false);
    }


    public String getCondition()
    {
        IMarker m = this.getMarker();
        return m.getAttribute(ATTR_CONDITION, (String) null);
    }

    public void setCondition(String condition) throws CoreException
    {
        setAttribute(ATTR_CONDITION, condition);
    }

    public void setLineNumber(int number) throws CoreException
    {
        setAttribute(IMarker.LINE_NUMBER, number);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.core.model.IBreakpoint#getModelIdentifier()
     */
    public String getModelIdentifier()
    {
        return SPDebugModelUtil.getModelIdentifier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.debug.model.ISPBreakpoint#getProcIdentifier()
     */
    public ProcIdentifier getProcIdentifier() throws CoreException
    {
        IMarker m = this.getMarker();
        String name = (String) m.getAttribute(RoutineEditorConstants.ATTR_PROCIDENTIFIER, ""); //$NON-NLS-1$
        if (name == null || name.length() == 0) 
        {
            throw new CoreException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID, 0, DebuggerMessages.SPLineBreakpoint_invalidProcIdentifier, null));  
        }

        try
        {
            return ProcIdentifierImpl.decode(name);
        }
        catch (ParseException ex)
        {
            throw new CoreException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID, 0,
                DebuggerMessages.SPLineBreakpoint_invalidProcIdentifier + name, null)); 
        }
    }

    public String getEncodedProcIdentifier() throws CoreException
    {
    	IMarker m = this.getMarker();
    	String name = (String) m.getAttribute(RoutineEditorConstants.ATTR_PROCIDENTIFIER, ""); //$NON-NLS-1$
    	if (name == null || name.length() == 0) 
    	{
    		throw new CoreException(new Status(IStatus.ERROR, DebuggerCorePlugin.PLUGIN_ID, 0, DebuggerMessages.SPLineBreakpoint_invalidProcIdentifier, null));  
    	}
    	
    	return name;
    }
    
    public void setProcId(String encodedProc) throws CoreException
    {
        Map attris = this.ensureMarker().getAttributes();
        attris.remove(RoutineEditorConstants.ATTR_PROCIDENTIFIER);
        attris.put(RoutineEditorConstants.ATTR_PROCIDENTIFIER,encodedProc);
        this.ensureMarker().setAttributes(attris);
    }
}
