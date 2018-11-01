/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.ddl;

import org.eclipse.datatools.sqltools.common.ui.dialog.SaveAsDialog;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.DefaultSchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.SOEUIPagePlugin;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;

/**
 * Save as action in DDL page
 * 
 * @author Idull
 */
public class SaveAsAction extends Action
{
    private ISchemaObjectEditor _editor;
    private String              _content;
    private DatabaseIdentifier  _databaseIdentifier;

    public SaveAsAction(String content, ISchemaObjectEditor editor, DatabaseIdentifier databaseIdentifier)
    {
        super();
        _content = content;
        _editor = editor;
        _databaseIdentifier = databaseIdentifier;
    }

    public void run()
    {
        SaveAsDialog dlg = new SaveAsDialog(SOEUIPagePlugin.getActiveWorkbenchShell(), _content);
        dlg.setOriginalName(_editor.getDisplayName() + "_ddl.sql");

        ISchemaObjectEditorHandler handler = _editor.getEditorHandler();
        if (handler instanceof DefaultSchemaObjectEditorHandler)
        {
            boolean isOpen = ((DefaultSchemaObjectEditorHandler) handler).getOpenFileAfterSaveasOption();
            dlg.setOpenMode(isOpen);
        }
        dlg.open();
        IEditorPart editor = dlg.getEditor();
        if (editor != null && (editor instanceof SQLEditor))
        {
            ISQLEditorConnectionInfo connInfo = new SQLEditorConnectionInfo(SQLToolsFacade
                    .getConfigurationByProfileName(_databaseIdentifier.getProfileName())
                    .getDatabaseVendorDefinitionId(), _databaseIdentifier.getProfileName(), _databaseIdentifier
                    .getDBname(), _databaseIdentifier.getDBname());
            ((SQLEditor) editor).setConnectionInfo(connInfo);
        }
    }
}
