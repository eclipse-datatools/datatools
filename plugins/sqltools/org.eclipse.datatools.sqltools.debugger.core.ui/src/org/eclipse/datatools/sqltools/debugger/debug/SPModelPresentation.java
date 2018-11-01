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


import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.DBHelper;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.debugger.breakpoint.ISPBreakpoint;
import org.eclipse.datatools.sqltools.debugger.breakpoint.SPLineBreakpoint;
import org.eclipse.datatools.sqltools.debugger.core.SQLDebuggerConfiguration;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerImages;
import org.eclipse.datatools.sqltools.debugger.model.SPStackFrame;
import org.eclipse.datatools.sqltools.debugger.model.SPVariable;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditor;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IDisconnect;
import org.eclipse.debug.core.model.IExpression;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.ITerminate;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.core.model.IWatchExpression;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;

/**
 * @author Yang Liu
 */
public class SPModelPresentation implements IDebugModelPresentation
{

    Map _attributes = new HashMap();

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.IDebugModelPresentation#setAttribute(java.lang.String, java.lang.Object)
     */
    public void setAttribute(String attribute, Object value)
    {
        if (value == null)
        return;
        _attributes.put(attribute, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.IDebugModelPresentation#computeDetail(org.eclipse.debug.core.model.IValue,
     *      org.eclipse.debug.ui.IValueDetailListener)
     */
    public void computeDetail(IValue value, IValueDetailListener listener)
    {
        // For the moment, only plan variable is supported in the variables view,
        //so, display the string value of the selected variable is enough.
        String result = "";
        try
        {
            result = value.getValueString();
        }
        catch (DebugException e)
        {
        	DebuggerCorePlugin.getDefault().log( e);
        }
        listener.detailComputed(value,result);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ISourcePresentation#getEditorInput(java.lang.Object)
     */
    public IEditorInput getEditorInput(Object item)
    {
        try
        {
            if (item instanceof IMarker)
            {
                item = getBreakpoint((IMarker) item);
            }
            if (item instanceof ISPBreakpoint)
            {
                // Figure out the proc this breakpoint is attached to, and that proc will be openned as the editor input
                item = ((ISPBreakpoint) item).getProcIdentifier();
            }
            if (item instanceof ProcIdentifier) 
            {
                return getEditorInput((ProcIdentifier) item); 
            }
            return null;
        }
        catch (CoreException e)
        {
        	DebuggerCorePlugin.getDefault().log(Messages.SPModelPresentation_errorGetEditorInput, e); 
            return null;
        }
    }

    protected IEditorInput getEditorInput(ProcIdentifier procid) throws CoreException
    {
        if (procid == null) return null;
        SQLDevToolsConfiguration sqlDTC = SQLToolsFacade.getConfiguration(null, procid.getDatabaseIdentifier());
        DBHelper helper = sqlDTC.getDBHelper();
        if (helper != null && helper.isAdHocProc(procid))
        {
            return null;
        }
        if (sqlDTC instanceof SQLDebuggerConfiguration) {
            SQLDebuggerConfiguration sqlDebugConf = (SQLDebuggerConfiguration)sqlDTC;
            IDebugModelPresentation debugModelPresentation = sqlDebugConf.getDebugModelPresentation();
            if (debugModelPresentation != null)
            {
                return debugModelPresentation.getEditorInput(procid);
            }            
        }
        return new ProcEditorInput(procid);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ISourcePresentation#getEditorId(org.eclipse.ui.IEditorInput, java.lang.Object)
     */
    public String getEditorId(IEditorInput input, Object element)
    {
        try
        {
            if (element instanceof IMarker)
            {
                element = getBreakpoint((IMarker) element);
            }
            if (element instanceof ISPBreakpoint)
            {
                element = ((ISPBreakpoint) element).getProcIdentifier();
            }
            if (element instanceof ProcIdentifier)
            {
                ProcIdentifier procid = (ProcIdentifier) element;
                SQLDevToolsConfiguration sqlDTC = SQLToolsFacade.getConfiguration(null, procid.getDatabaseIdentifier());

                if (sqlDTC != null && sqlDTC instanceof SQLDebuggerConfiguration)
                {
                    SQLDebuggerConfiguration sqlDebugConf = (SQLDebuggerConfiguration) sqlDTC;
                    IDebugModelPresentation debugModelPresentation = sqlDebugConf.getDebugModelPresentation();
                    if (debugModelPresentation != null)
                    {
                        return debugModelPresentation.getEditorId(input, element);
                    }
                }
            }
        }
        catch (CoreException e)
        {
            DebuggerCorePlugin.getDefault().log(Messages.SPModelPresentation_errorGetEditorInput, e);
            return null;
        }

        if (input instanceof ProcEditorInput)
        {
            return RoutineEditor.EDITOR_ID;
        }
        return EditorConstants.EDITOR_ID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.IDebugModelPresentation#getText(java.lang.Object)
     */
    public String getText(Object item)
    {
        try
        {
            if (item instanceof IVariable)
            {
                return getVariableText((IVariable) item);
            }
            else if (item instanceof IStackFrame)
            {
                return getStackFrameText((IStackFrame) item);
            }
            else if (item instanceof IMarker)
            {
                IBreakpoint breakpoint = getBreakpoint((IMarker) item);
                if (breakpoint != null) 
                {
                    return getBreakpointText(breakpoint); 
                }
                return null;
            }
            else if (item instanceof IBreakpoint)
            {
                return getBreakpointText((IBreakpoint) item);
            }
            else if (item instanceof IWatchExpression)
            {
                return getWatchExpressionText((IWatchExpression) item);
            }
            else if (item instanceof IExpression)
            {
                return getExpressionText((IExpression) item);
            }
            else
            {
                StringBuffer label = new StringBuffer();
                if (item instanceof IThread)
                {
                    label.append(getThreadText((IThread) item));
                }
                else if (item instanceof IDebugTarget)
                {
                    label.append(getDebugTargetText((IDebugTarget) item));
                }
                else if (item instanceof IValue)
                {
                    label.append(getValueText((IValue) item));
                }
                if (item instanceof ITerminate)
                {
                    if (((ITerminate) item).isTerminated())
                    {
                        label.insert(0, Messages.SPModelPresentation_terminatedFlag); 
                        return label.toString();
                    }
                }
                if (item instanceof IDisconnect)
                {
                    if (((IDisconnect) item).isDisconnected())
                    {
                        label.insert(0, Messages.SPModelPresentation_disconnectedFlag); 
                        return label.toString();
                    }
                }
                if (label.length() > 0) 
                {
                    return label.toString(); 
                }
            }
        }
        catch (CoreException e)
        {
            return Messages.SPModelPresentation_notRespondingFlag; 
        }
        return null;

    }

    /**
     * @param target
     * @return
     */
    private String getDebugTargetText(IDebugTarget target) throws CoreException
    {
        return Messages.SPModelPresentation_debugTarget; 
    }

    /**
     * @param value
     * @return
     */
    private String getValueText(IValue value)
    {
        try
        {
            return value.getValueString();
        }
        catch (DebugException e)
        {
            // will not happen. Since our SPValue.getValueString will not throw exception
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @param expression
     * @return
     */
    private String getExpressionText(IExpression expression)
    {
        StringBuffer buff= new StringBuffer();
        IValue value= (IValue) expression.getValue();
        buff.append('"' + expression.getExpressionText() + '"');

        if (value != null) 
        {
            String valueString= getValueText(value);
            if (valueString != null && valueString.length() > 0) 
            {
                buff.append("= "); //$NON-NLS-1$
                buff.append(valueString);
            }
        }
        return buff.toString();

    }

    /**
     * @param expression
     * @return
     */
    private String getWatchExpressionText(IWatchExpression expression)
    {
        if (expression.hasErrors())
        {
            String[] errors = expression.getErrorMessages();
            String error = (errors!=null&&errors.length>0)? errors[0] : Messages.SPModelPresentation_evaluateFailMessage; 
            return NLS.bind(Messages.SPModelPresentation_ERROR2, (new Object[]
			{
			    expression.getExpressionText(), error
			}));
        }
        else
        {
            return getExpressionText(expression);
        }
    }

    /**
     * @param breakpoint
     * @return
     */
    private String getBreakpointText(IBreakpoint breakpoint)
    {
        if (breakpoint instanceof SPLineBreakpoint)
        {
            SPLineBreakpoint spb = (SPLineBreakpoint) breakpoint;
            try
            {
                return spb.getProcIdentifier().getProfileName()+ ": " + spb.getProcIdentifier().getDisplayString() + " " + NLS.bind(Messages.SPModelPresentation_line, (new Object[]
				{
				    "" + spb.getLineNumber()
				})); //$NON-NLS-1$
            }
            catch (Exception ex)
            {
            	DebuggerCorePlugin.getDefault().log(Messages.SPModelPresentation_breakpointTextFail, ex);
                return Messages.SPModelPresentation_breakpoint; 
            }

        }
        return Messages.SPModelPresentation_breakpointUnknown; 
    }

    /**
     * @param marker
     * @return
     */
    private IBreakpoint getBreakpoint(IMarker marker)
    {
        return DebugPlugin.getDefault().getBreakpointManager().getBreakpoint(marker);
    }

    /**
     * @param frame
     * @return
     */
    private String getStackFrameText(IStackFrame frame)
    {
        SPStackFrame spframe = (SPStackFrame) frame;
        StringBuffer buffer = new StringBuffer(spframe.getProcIdentifier().getProcName());
        String paramstr = spframe.getParamStr();
        if (paramstr != null && paramstr.length() > 0)
        {
            buffer.append("(").append(paramstr).append(")"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        try
        {
            buffer.append(": " + spframe.getLineNumber()); //$NON-NLS-1$
        }
        catch (DebugException ex)
        {
            // ignore
        }
        return buffer.toString();
    }

    /**
     * @param variable
     * @return
     */
    private String getVariableText(IVariable variable)
    {
        //FIXME:
        try
        {
            IValue v = variable.getValue();
            return variable.getName() + "= " + (v == null ? "null" : v.getValueString()); //$NON-NLS-1$ //$NON-NLS-2$
        }
        catch (DebugException ex)
        {
            DebuggerCorePlugin.getDefault().log(Messages.DebugException_occurred, ex);
            return Messages.SPModelPresentation_getVariableInfoFail; 
        }
    }

    /**
     * @param thread
     * @return
     */
    private String getThreadText(IThread thread)
    {
        try
        {
            return thread.getName();
        }
        catch (DebugException ex)
        {
            return Messages.SPModelPresentation_thread; // should not happen //$NON-NLS-1$
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.IDebugModelPresentation#getImage(java.lang.Object)
     */
    public Image getImage(Object element)
    {
        if (element instanceof SPVariable)
        {
            if (((SPVariable) element).isGlobal())
            {
                return DebuggerImages.getImage("globalvariable");
            }
            else
            {
                return DebuggerImages.getImage("localvariable");
            }
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void addListener(ILabelProviderListener listener)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose()
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    public boolean isLabelProperty(Object element, String property)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void removeListener(ILabelProviderListener listener)
    {
        // TODO Auto-generated method stub

    }
}
