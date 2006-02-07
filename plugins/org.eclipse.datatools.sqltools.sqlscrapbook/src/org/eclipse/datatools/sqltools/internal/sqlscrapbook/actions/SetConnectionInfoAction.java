/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions;

import java.util.ResourceBundle;

import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.ConnectionInfoDialog;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.SQLScrapbookEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.TextEditorAction;


/**
 * @author Li Huang
 *
 */
public class SetConnectionInfoAction extends TextEditorAction
{
    SQLScrapbookEditor _sqlEditor;

    /**
     * @param bundle
     * @param prefix
     * @param editor
     */
    public SetConnectionInfoAction(ResourceBundle bundle, String prefix, ITextEditor editor)
    {
        super(bundle, prefix, editor);
        _sqlEditor = (SQLScrapbookEditor) editor;
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IHelpContextIds.ATTACH_CONNECTION_PROFILE_ACTION);
    }

    public void run()
    {
        ConnectionInfoDialog dlg = new ConnectionInfoDialog(_sqlEditor.getEditorSite().getShell(), _sqlEditor.getConnectionInfo());
        if (dlg.open() != IDialogConstants.CANCEL_ID)
        {
        	_sqlEditor.setConnectionInfo(dlg.getConnectionInfo());
        }
    }

}
