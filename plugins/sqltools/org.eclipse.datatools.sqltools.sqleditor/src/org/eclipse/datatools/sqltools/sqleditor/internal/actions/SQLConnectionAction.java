/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import java.util.ResourceBundle;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.TextEditorAction;

/**
 * This action calls the "SQL connection" dialog which allows user to specify <code>ISQLEditorConnectionInfo</code>
 * for the SQL Editor and allows user to connect/disconnect.
 * 
 * @author Hui Cao
 */
public class SQLConnectionAction extends TextEditorAction
{
    SQLEditor _sqlEditor;

    /**
     * @param bundle
     * @param prefix
     * @param editor
     */
    protected SQLConnectionAction(ResourceBundle bundle, String prefix, ITextEditor editor)
    {
        super(bundle, prefix, editor);
        _sqlEditor = (SQLEditor) editor;
        setActionDefinitionId(ISQLEditorActionConstants.ATTACHE_PROFILE_ACTION_ID);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpContextIds.ATTACH_CONNECTION_PROFILE_ACTION, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void run()
    {
        _sqlEditor.requestConnectionFromUser();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.IUpdate#update()
     */
    public void update()
    {
        _sqlEditor = (SQLEditor) getTextEditor();
    }
}
