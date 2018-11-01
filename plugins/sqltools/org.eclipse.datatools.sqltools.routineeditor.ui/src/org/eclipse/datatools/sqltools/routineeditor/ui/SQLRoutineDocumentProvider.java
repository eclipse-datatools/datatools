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

package org.eclipse.datatools.sqltools.routineeditor.ui;

import java.sql.SQLException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.editor.IExtendedSaveSupport;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLStorageDocumentProvider;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;


/**
 * This document provider class is responsible for mapping routine objects and routine editor documents.
 * @author Hui Cao
 *  
 */
public class SQLRoutineDocumentProvider extends SQLStorageDocumentProvider implements IRoutineEditorDocumentProvider
{

    private SQLEditor     _editor;

    /**
     *  
     */
    public SQLRoutineDocumentProvider()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#createDocument(java.lang.Object)
     */
    protected IDocument createDocument(Object element) throws CoreException
    {
        IDocument document;

        if (element instanceof ProcEditorInput)
        {
            ProcIdentifier procid = ((ProcEditorInput) element).getProcIdentifier();
            String text = null;

            try
            {
                ProcEditorInput input = ((ProcEditorInput) element);
                text = input.getSourceCode();
                if (text == null || "".equals(text.trim()))
                {
                    IControlConnection controlconnection = EditorCorePlugin.getControlConnectionManager()
                        .getOrCreateControlConnection(procid.getDatabaseIdentifier());
                    text = controlconnection.getProcSource(procid);
                    input.setSourceCode(text);
                }
                if (text == null || "".equals(text.trim()))
                {
                    text = Messages.sqlEditor_SQLEditorDocumentProvider_failGetSource;
                }
            }
            catch (SQLException e)
            {
                throw new CoreException(new Status(IStatus.ERROR, RoutineEditorUIActivator.PLUGIN_ID, 0, Messages.sqlEditor_SQLEditorDocumentProvider_failGetSource, e));
            }
            catch (NoSuchProfileException e)
            {
                throw new CoreException(new Status(IStatus.ERROR, RoutineEditorUIActivator.PLUGIN_ID, 0, Messages.sqlEditor_SQLEditorDocumentProvider_failGetSource, e));
            }
            document = new Document(text);
        }
        else
        {
            // is FileEditorInput, delegate to super.
            document = super.createDocument(element);
        }
        return document;
    }

    /*
     * @see IDocumentProviderExtension#isModifiable(Object)
     */
    public boolean isModifiable(Object element)
    {
        if (element instanceof ProcEditorInput)
        {
            return true;
        }
        else
        {
            return super.isModifiable(element);
        }
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
            ProcIdentifier proc = ((ProcEditorInput) element).getProcIdentifier();
            return new RoutineAnnotationModel(proc);
        }
        return super.createAnnotationModel(element);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#doSaveDocument(org.eclipse.core.runtime.IProgressMonitor,
     *      java.lang.Object, org.eclipse.jface.text.IDocument, boolean)
     */

    protected void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite)
        throws CoreException
    {
        IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        _editor = (SQLEditor) activeEditor.getAdapter(SQLEditor.class);

        if (element instanceof ProcEditorInput)
        {
            // we need save the stored procedure to database
            ProcIdentifier proc = ((ProcEditorInput) element).getProcIdentifier();
            try
            {
                IControlConnection controlCon = EditorCorePlugin.getControlConnectionManager()
                    .getOrCreateControlConnection(proc.getDatabaseIdentifier());

                SQLDevToolsUIConfiguration factory = SQLToolsUIFacade.getConfiguration(null, proc.getDatabaseIdentifier());
                IExtendedSaveSupport extendedSaveSupport = factory.getSQLEditorUIService().getExtendedSaveSupport();
                if(extendedSaveSupport != null)
                {
                    extendedSaveSupport.preSave(controlCon.getReusableConnection(), _editor);
                }

                controlCon.saveRoutine(proc, document.get());                

                refreshFromDatabase(element, controlCon, proc);
                ((ProcEditorInput) element).setSourceCode(document.get());

                //Add status line message when the action 'save to database' succeeded.
                //((WorkbenchWindow) PlatformUI.getWorkbench().getActiveWorkbenchWindow()).getStatusLineManager().setMessage(
                //    Messages.getString("SQLEditorDocumentProvider.savetodatabase.success"));
            }
            catch (SQLException e)
            {
                throw new CoreException(new Status(IStatus.ERROR, RoutineEditorUIActivator.PLUGIN_ID, 0, NLS.bind(Messages
                    .sqlEditor_SQLEditorDocumentProvider_saveDbFail, e.toString()), e));
            }
            catch (NoSuchProfileException e)
            {
                throw new CoreException(new Status(IStatus.ERROR, RoutineEditorUIActivator.PLUGIN_ID, 0, NLS.bind(Messages
                    .sqlEditor_SQLEditorDocumentProvider_saveDbFail, e.toString()), e));
            }
        }
        else
        {
            super.doSaveDocument(monitor, element, document, overwrite);
        }
    }

    public void refreshFromDatabase(Object element, IControlConnection controlCon, ProcIdentifier proc)
        throws CoreException, SQLException
    {
        IDocument document = getDocument(element);
        document.set(controlCon.getProcSource(proc));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#getOperationRunner(org.eclipse.core.runtime.IProgressMonitor)
     */
    protected IRunnableContext getOperationRunner(IProgressMonitor monitor)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.editors.text.StorageDocumentProvider#isReadOnly(java.lang.Object)
     */
    public boolean isReadOnly(Object element)
    {
        if (element instanceof ProcEditorInput)
        {
            return false;
        }
        else
        {
            return super.isReadOnly(element);
        }
    }
}
