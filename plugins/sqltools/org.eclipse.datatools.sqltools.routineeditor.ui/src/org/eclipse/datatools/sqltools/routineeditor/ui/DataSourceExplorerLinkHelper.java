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

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ILinkHelper;


/**
 * @author David Cui
 */
public class DataSourceExplorerLinkHelper implements ILinkHelper
{

    /* (non-Javadoc)
     * @see org.eclipse.ui.navigator.ILinkHelper#findSelection(org.eclipse.ui.IEditorInput)
     */
    public IStructuredSelection findSelection(IEditorInput anInput) 
    {
        if (anInput instanceof ProcEditorInput)
        {
            ProcIdentifier procId = (ProcIdentifier)((ProcEditorInput)anInput).getProcIdentifier();
            return new StructuredSelection(ModelUtil.findProceduralObject(procId, false));
        }
        return StructuredSelection.EMPTY;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.navigator.ILinkHelper#activateEditor(org.eclipse.ui.IWorkbenchPage, org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void activateEditor(IWorkbenchPage aPage,IStructuredSelection aSelection) 
    {
        if (aSelection == null || aSelection.isEmpty())
        {
            return;
        }
        Object firstElement= aSelection.getFirstElement();
        if (firstElement instanceof SQLObject && SQLDevToolsUtil.getProcType((SQLObject)firstElement)!= ProcIdentifier.TYPE_SQL)
        {
            IEditorInput procInput = new ProcEditorInput(SQLDevToolsUtil.getProcIdentifier((SQLObject)firstElement));
            IEditorPart editor = null;
            if ((editor = aPage.findEditor(procInput)) != null)
            {
                aPage.bringToTop(editor);
            }
        }
    }
}