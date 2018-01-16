/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.source.SourcePage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorActionBarContributor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.DefaultSchemaObjectEditorActionBarContributor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorActionContributor;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ProceduralObjectEditorActionContributor extends DefaultSchemaObjectEditorActionBarContributor implements
        ISchemaObjectEditorActionBarContributor
{
    private SQLEditorActionContributor _delegate = new SQLEditorActionContributor();
    
    public ProceduralObjectEditorActionContributor()
    {
        // TODO Auto-generated constructor stub
    }

    public void contributeToCoolBar(ICoolBarManager coolBarManager)
    {
        super.contributeToCoolBar(coolBarManager);
        _delegate.contributeToCoolBar(coolBarManager);
    }

    public void contributeToMenu(IMenuManager menuManager)
    {
        super.contributeToMenu(menuManager);
        _delegate.contributeToMenu(menuManager);
    }

    public void contributeToStatusLine(IStatusLineManager statusLineManager)
    {
        super.contributeToStatusLine(statusLineManager);
        _delegate.contributeToStatusLine(statusLineManager);
    }

    public void contributeToToolBar(IToolBarManager toolBarManager)
    {
        super.contributeToToolBar(toolBarManager);
        _delegate.contributeToToolBar(toolBarManager);
    }

    public void setActivePage(IEditorPart activeEditor)
    {
        super.setActivePage(activeEditor);
        if (_delegate.getActionBars() == null)
        {
            _delegate.init(this.getActionBars(), this.getPage());
        }
        if (activeEditor instanceof SQLEditor)
        {
            _delegate.setActiveEditor(activeEditor);
        }
        else
        {
            _delegate.update(false);
        }
    }

    public void setEditor(ISchemaObjectEditor editor)
    {
        super.setEditor(editor);
        SourcePage page = (SourcePage)editor.getPageById(ProceduralObjectFormEditorConstants.ROUTINE_SOURCE_PAGE_ID);
        if (page != null)
        {
            _delegate.setActiveEditor(page.getNestedEditor());
            _delegate.update(page == editor.getActiveEditorPage());
        }
    }

    public void init(IActionBars bars, IWorkbenchPage page)
    {
        super.init(bars, page);
        _delegate.init(bars, page);
    }

}
