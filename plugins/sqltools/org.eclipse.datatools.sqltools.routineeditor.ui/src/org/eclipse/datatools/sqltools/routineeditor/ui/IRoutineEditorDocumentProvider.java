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
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.IDocumentProviderExtension2;
import org.eclipse.ui.texteditor.IDocumentProviderExtension3;


/**
 * This interface defines document provider operations specific to SQL Routine Editor.
 * @author Hui Cao
 *
 */
public interface IRoutineEditorDocumentProvider extends IDocumentProvider, IDocumentProviderExtension2, IDocumentProviderExtension3 
{
    /**
     * Refreshs the routine object from database.
     * @param element the routine object
     * @param controlCon control connection that's used by the routine editor
     * @param proc the routine identifier
     */
    public void refreshFromDatabase(Object element, IControlConnection controlCon, ProcIdentifier proc) throws CoreException, SQLException;
}
