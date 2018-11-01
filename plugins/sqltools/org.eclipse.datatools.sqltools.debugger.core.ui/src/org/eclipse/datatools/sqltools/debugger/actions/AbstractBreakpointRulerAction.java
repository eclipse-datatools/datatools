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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.debugger.breakpoint.ISPBreakpoint;
import org.eclipse.datatools.sqltools.debugger.breakpoint.SPLineBreakpoint;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.AbstractMarkerAnnotationModel;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * copied and modified from the eclipse jdt plugin.
 * 
 * @author Yang Liu
 */
public abstract class AbstractBreakpointRulerAction extends Action implements IUpdate
{

    private IVerticalRulerInfo fInfo;

    private ITextEditor        fTextEditor;

    private IBreakpoint        fBreakpoint;

    protected IBreakpoint determineBreakpoint()
    {
        IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(
            SPDebugModelUtil.getModelIdentifier());
        for (int i = 0; i < breakpoints.length; i++)
        {
            IBreakpoint breakpoint = breakpoints[i];
            if (breakpoint instanceof ISPBreakpoint)
            {
                SPLineBreakpoint jBreakpoint = (SPLineBreakpoint) breakpoint;
                try
                {
                    if (breakpointAtRulerLine(jBreakpoint)) 
                    {
                        return jBreakpoint; 
                    }
                }
                catch (CoreException ce)
                {
                    // log.
                    continue;
                }
            }
        }
        return null;
    }

    protected IVerticalRulerInfo getInfo()
    {
        return fInfo;
    }

    protected void setInfo(IVerticalRulerInfo info)
    {
        fInfo = info;
    }

    protected ITextEditor getTextEditor()
    {
        return fTextEditor;
    }

    protected void setTextEditor(ITextEditor textEditor)
    {
        fTextEditor = textEditor;
    }

    /**
     * Returns the resource for which to create the marker, or <code>null</code> if there is no applicable resource.
     * 
     * @return the resource for which to create the marker or <code>null</code>
     */
    protected IResource getResource()
    {
        IEditorInput input = fTextEditor.getEditorInput();
        IResource resource = (IResource) input.getAdapter(IFile.class);
        if (resource == null)
        {
            resource = (IResource) input.getAdapter(IResource.class);
        }
        return resource;
    }

    protected boolean breakpointAtRulerLine(SPLineBreakpoint jBreakpoint) throws CoreException
    {
        AbstractMarkerAnnotationModel model = getAnnotationModel();
        if (model != null)
        {
            Position position = model.getMarkerPosition(jBreakpoint.getMarker());
            if (position != null)
            {
                IDocumentProvider provider = getTextEditor().getDocumentProvider();
                IDocument doc = provider.getDocument(getTextEditor().getEditorInput());
                try
                {
                    int markerLineNumber = doc.getLineOfOffset(position.getOffset());
                    int rulerLine = getInfo().getLineOfLastMouseButtonActivity();
                    if (rulerLine == markerLineNumber)
                    {
                        if (getTextEditor().isDirty()) 
                        {
                            return jBreakpoint.getLineNumber() == markerLineNumber + 1; 
                        }
                        return true;
                    }
                }
                catch (BadLocationException x)
                {
                }
            }
        }

        return false;
    }

    protected IBreakpoint getBreakpoint()
    {
        return fBreakpoint;
    }

    protected void setBreakpoint(IBreakpoint breakpoint)
    {
        fBreakpoint = breakpoint;
    }

    /**
     * Returns the <code>AbstractMarkerAnnotationModel</code> of the editor's input.
     * 
     * @return the marker annotation model
     */
    protected AbstractMarkerAnnotationModel getAnnotationModel()
    {
        IDocumentProvider provider = fTextEditor.getDocumentProvider();
        IAnnotationModel model = provider.getAnnotationModel(getTextEditor().getEditorInput());
        if (model instanceof AbstractMarkerAnnotationModel) 
        {
            return (AbstractMarkerAnnotationModel) model; 
        }
        return null;
    }
}

