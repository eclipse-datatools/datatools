/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorActionBarContributor;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Consumers should extend this class instead of implementing <code>ISchemaObjectEditorActionBarContributor</code>
 * 
 * @author Idull
 */
public class DefaultSchemaObjectEditorActionBarContributor implements ISchemaObjectEditorActionBarContributor
{
    protected ISchemaObjectEditor _editor;
    /**
     * The action bars; <code>null</code> until <code>init</code> is called.
     */
    private IActionBars           bars;

    /**
     * The workbench page; <code>null</code> until <code>init</code> is called.
     */
    private IWorkbenchPage        page;

    public void setEditor(ISchemaObjectEditor editor)
    {
        _editor = editor;
    }

    public void contributeToCoolBar(ICoolBarManager coolBarManager)
    {

    }

    public void contributeToMenu(IMenuManager menuManager)
    {

    }

    public void contributeToStatusLine(IStatusLineManager statusLineManager)
    {

    }

    public void contributeToToolBar(IToolBarManager toolBarManager)
    {

    }

    public void setActivePage(IEditorPart activeEditor)
    {

    }

    public void init(IActionBars bars, IWorkbenchPage page)
    {
        this.page = page;
        this.bars = bars;
        // TODO reference EditorActionBarContributor.init implementation
    }

    /**
     * Returns this contributor's action bars.
     * 
     * @return the action bars
     */
    public IActionBars getActionBars()
    {
        return bars;
    }

    /**
     * Returns this contributor's workbench page.
     * 
     * @return the workbench page
     */
    public IWorkbenchPage getPage()
    {
        return page;
    }

}
