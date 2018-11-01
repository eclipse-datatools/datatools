/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.udteditor.action;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorPageDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.intropage.IIntroHyperAction;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.forms.editor.FormEditor;

/**
 * @author renj
 */
public class SetGeneralPageAction extends Action implements IIntroHyperAction
{
    protected ISchemaObjectEditorPage _generalPage;
    private FormEditor                _editor;
    private String                    _pageID;

    public void setPage(ISchemaObjectEditorPage page)
    {
        _editor = page.getEditor();
        IEditorPageDescriptor[] eps = page.getEditorDescriptor().getDefaultSortedPages();
        for (int i = 0; i < eps.length; i++)
        {
            if (eps[i].getPageName().equalsIgnoreCase("general"))
            {
                _pageID = eps[i].getPageId();
                break;
            }
        }
    }

    public void run()
    {
        if (_editor != null && _pageID != null)
        {
            _editor.setActivePage(_pageID);
        }
    }
}
