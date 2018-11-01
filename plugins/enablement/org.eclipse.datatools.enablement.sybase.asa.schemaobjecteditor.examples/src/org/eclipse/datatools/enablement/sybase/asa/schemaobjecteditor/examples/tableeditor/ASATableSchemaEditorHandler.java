/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.DefaultSchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;


/**
 * ASA table schema object editor handler
 * 
 * @author Idull
 */
public class ASATableSchemaEditorHandler extends DefaultSchemaObjectEditorHandler implements ISchemaObjectEditorHandler
{

    public ASATableSchemaEditorHandler()
    {

    }

    public String generateScript()
    {
        return super.generateScript();
    }

    public String getDisplayName()
    {
        return null;
    }

    public void setEditor(ISchemaObjectEditor editor)
    {
        super.setEditor(editor);
        // do initialization job
        _editor.setData(null);
    }

    public void pageChanged(int newPageIndex)
    {
        super.pageChanged(newPageIndex);
        ISchemaObjectEditorPage page = _editor.getAllPages()[newPageIndex];

        // Refresh page when it's set active
        if (page != null)
        {
            page.refresh();
        }
    }

	public void forceFocusObject(SQLObject object) 
	{
	    ExamplePlugin.getActiveWorkbenchPage().activate(_editor);
		if(object == null)
		{
			return;
		}
		if(object instanceof Table)
		{
			ISchemaObjectEditorPage page = _editor.getPageByName("Columns");
			if(page != null)
			{
			    ((SchemaObjectEditor)_editor).setActivePage(page.getPageDescriptor().getPageId());
			    page.setFocus(SchemaObjectEditorPage.UNKNOWN_ITEM_TYPE, object);
			}
		}
		else
		{
			super.forceFocusObject(object);
		}
	}
}
