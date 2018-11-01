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

package org.eclipse.datatools.sqltools.debugger.editorext;

import java.sql.SQLException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.debugger.core.SQLDebuggerConfiguration;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerCoreUIPlugin;
import org.eclipse.datatools.sqltools.debugger.debug.BreakpointLocationHandler;
import org.eclipse.datatools.sqltools.routineeditor.ui.IRoutineEditorDocumentProvider;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.routineeditor.ui.SQLRoutineDocumentProvider;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;

/**
 * This document provider adds the breakpoints verification feature to the
 * default routine editor document provider.
 * 
 * @author Hui Cao
 * 
 */
public class SQLDebuggerDocumentProvider extends SQLRoutineDocumentProvider implements IRoutineEditorDocumentProvider
{

    /**
     *  
     */
    public SQLDebuggerDocumentProvider()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#createAnnotationModel(java.lang.Object)
     */
    protected IAnnotationModel createAnnotationModel(Object element) throws CoreException
    {
        if (element instanceof ProcEditorInput)
        {
            if (SQLToolsFacade.getConfiguration(null, ((ProcEditorInput) element).getProcIdentifier().getDatabaseIdentifier()) instanceof SQLDebuggerConfiguration)
            {
                //Hui Cao: don't store this model object as instance variable since this document provider can be shared by 
                //multiple document/annotationmodel pairs.
                return new SPBreakpointAnnotationModel(((ProcEditorInput) element).getProcIdentifier());
            }
        }
        return super.createAnnotationModel(element);
    }

    protected void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite)
        throws CoreException
    {
        super.doSaveDocument(monitor, element, document, overwrite);

        if (element instanceof ProcEditorInput)
        {
            SQLEditor editor = (SQLEditor) DebuggerCoreUIPlugin.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            BreakpointLocationHandler.handleEditorSave(((ProcEditorInput)element).getProcIdentifier(), editor); 
        }
    }


    public void refreshFromDatabase(Object element, IControlConnection controlCon, ProcIdentifier proc) throws CoreException, SQLException
    {
        if (SQLToolsFacade.getConfiguration(null, proc.getDatabaseIdentifier()) instanceof SQLDebuggerConfiguration)
        {
            IAnnotationModel model = getAnnotationModel(element);
            IDocument document = getDocument(element);
            // ok, save succeeded.
            if (model instanceof SPBreakpointAnnotationModel)
            {
                // because doing document.set will clear all positions. So we want to
                // update the marker before that.
                // NOTE: as the new proc source may be different than the source in editor (database
                // may change source), so no way to guarantee after the update the breakpoint location
                // will still be valid.
                ((SPBreakpointAnnotationModel) model).updateMarkers(document);
                document.set(controlCon.getProcSource(proc));
                // bug 363935

                ElementInfo info = this.getElementInfo(element);
                info.fCanBeSaved = false;
                addUnchangedElementListeners(element, info);
                fireElementDirtyStateChanged(element, false);

                ((SPBreakpointAnnotationModel) model).documentSaved(document);
                controlCon.refresh(proc);
            }
        }
        else
        {
            super.refreshFromDatabase(element, controlCon, proc);
        }
    }
    
//    public boolean isModifiable(Object element)
//    {
//        if (element instanceof ProcEditorInput)
//        {
//            if (SPDebugModelUtil.isProcInDebugging(((ProcEditorInput)element).getProcIdentifier()))
//            {
//                return false;
//            }
//        }
//        return super.isModifiable(element);
//    }

}
