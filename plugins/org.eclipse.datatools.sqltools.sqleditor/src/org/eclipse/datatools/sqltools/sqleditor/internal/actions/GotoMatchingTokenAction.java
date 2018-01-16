/**
 * Created on Jul 23, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2008. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

/**
 * Action to go to matching token in SQL Editor.
 * 
 * @author juewu
 */
public class GotoMatchingTokenAction extends Action
{
    /**
     * The SQL Editor.
     */
    protected SQLEditor _sqlEditor;

    /**
     * Create a new action for going to the matching token.
     * 
     * @param editor is the sql editor which is able to go to matching token.
     */
    public GotoMatchingTokenAction(SQLEditor editor)
    {
        Assert.isNotNull(editor);
        setText(Messages.GotoMatchingTokenAction_label);
        setToolTipText(Messages.GotoMatchingTokenAction_tooltip);
        setActionDefinitionId(ISQLEditorActionConstants.GOTO_MATCHING_TOKEN_ACTION_ID);
        setId(ISQLEditorActionConstants.GOTO_MATCHING_TOKEN_ACTION_ID);

        _sqlEditor = editor;
        setEnabled(true);

        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpContextIds.GOTO_MATCHING_TOKEN_ACTION, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void run()
    {
        _sqlEditor.gotoMatchingToken();
    }
}
