/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import java.sql.SQLException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.routineeditor.ui.IRoutineEditorDocumentProvider;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.texteditor.IElementStateListener;

/**
 * Provides a default empty implementation of IRoutineEditorDocumentProvider to be used inside a FormEditor
 * 
 * @author Hui Cao
 * 
 */
public class RoutineFormDocumentProviderAdapter implements IRoutineEditorDocumentProvider
{

    public void refreshFromDatabase(Object element, IControlConnection controlCon, ProcIdentifier proc)
            throws CoreException, SQLException
    {
        // TODO Auto-generated method stub

    }

    public void aboutToChange(Object element)
    {
        // TODO Auto-generated method stub

    }

    public void addElementStateListener(IElementStateListener listener)
    {
        // TODO Auto-generated method stub

    }

    public boolean canSaveDocument(Object element)
    {
        // TODO Auto-generated method stub
        return false;
    }

    public void changed(Object element)
    {
        // TODO Auto-generated method stub

    }

    public void connect(Object element) throws CoreException
    {
        // TODO Auto-generated method stub

    }

    public void disconnect(Object element)
    {
        // TODO Auto-generated method stub

    }

    public IAnnotationModel getAnnotationModel(Object element)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public IDocument getDocument(Object element)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public long getModificationStamp(Object element)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public long getSynchronizationStamp(Object element)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean isDeleted(Object element)
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean mustSaveDocument(Object element)
    {
        // TODO Auto-generated method stub
        return false;
    }

    public void removeElementStateListener(IElementStateListener listener)
    {
        // TODO Auto-generated method stub

    }

    public void resetDocument(Object element) throws CoreException
    {
        // TODO Auto-generated method stub

    }

    public void saveDocument(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite)
            throws CoreException
    {
        // TODO Auto-generated method stub

    }

    public IProgressMonitor getProgressMonitor()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void setProgressMonitor(IProgressMonitor progressMonitor)
    {
        // TODO Auto-generated method stub

    }

    public boolean isSynchronized(Object element)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
