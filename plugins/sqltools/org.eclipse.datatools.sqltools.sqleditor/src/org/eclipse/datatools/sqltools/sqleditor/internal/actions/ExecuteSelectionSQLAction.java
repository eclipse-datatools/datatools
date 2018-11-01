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
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import java.util.HashMap;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sqleditor.IPageUpdate;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.PlatformUI;

/**
 * @author Li Huang
 *  
 */
public class ExecuteSelectionSQLAction extends BaseExecuteAction  implements ISelectionChangedListener, IPageUpdate
{
    private SQLEditor     _sqlEditor;
    private ITextSelection _selection;
    private boolean        _isSourcePage = true;
    /**
     * @param text
     * @param image
     */
    public ExecuteSelectionSQLAction(SQLEditor targetEditor)
    {
        setText(Messages.ExecuteSelectionSQLAction_label);
        setToolTipText(Messages.ExecuteSelectionSQLAction_tooltip);
        setImageDescriptor(SQLEditorResources.getImageDescriptor("sql_execute_selection"));
        setActionDefinitionId(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID);
        setId(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID);
        setActiveEditor(targetEditor);
        targetEditor.getSelectionProvider().addSelectionChangedListener(this);
        update();

        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpContextIds.EXECUTE_SELECTED_TEXT_ACTION, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void setActiveEditor(SQLEditor targetEditor)
    {
        _sqlEditor = targetEditor;
    }

    public void run()
    {
        super.run();
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(_sqlEditor);
    }

    public void update()
    {
        setEnabled(_isSourcePage && _sqlEditor != null && _sqlEditor.isConnected() && _sqlEditor.getSelectedText() != null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.actions.BaseExplainAction#getSQLStatements()
     */
    public String getSQLStatements()
    {
    	return _sqlEditor == null ? null : SQLToolsFacade.getDBHelper(getDatabaseIdentifier()).preprocessSQLScript(_sqlEditor.getSelectedText());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.actions.BaseExplainAction#getPostRun()
     */
    public Runnable getPostRun()
    {
        Runnable postRun = new Runnable()
        {
            public void run()
            {
            	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(_sqlEditor);
            }
        }
        ;
        return postRun;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event)
    {
        if (event.getSelection() instanceof ITextSelection)
        {
            _selection = (ITextSelection) event.getSelection();
            update();
        }
    }

    protected boolean promptVariable()
    {
        return true;
    }

    protected HashMap getVariableDeclarations()
    {
        int start = 0;
        int length = 0;
        if (_selection == null)
        {
            _selection = (ITextSelection) _sqlEditor.getSelectionProvider().getSelection();
        }
        //get the offset of the selection
        if (_selection != null && !_selection.isEmpty())
        {
            start = _selection.getOffset();
            length = _selection.getLength();
            if (length < 0)
            {
                length = -length;
                start -= length;
            }
        }
        //when user selects a range
        int offset = length > 0 ? start + 1 : start;

        IDocument document = _sqlEditor.getDocumentProvider().getDocument(_sqlEditor.getEditorInput());
        ParsingResult result = _sqlEditor.getParsingResult();

        HashMap variables = null;
        if (result != null)
        {
            variables = result.getVariables(document, offset);
            HashMap sp_params = result.getParameters(document, offset);
            variables.putAll(sp_params);
        }

        return variables;
    }

    protected SQLEditor getEditor()
    {
        return _sqlEditor;
    }

    public DatabaseIdentifier getDatabaseIdentifier()
    {
    	if (_sqlEditor != null)
    	{
    		String profileName = _sqlEditor.getConnectionInfo().getConnectionProfileName();
    		String dbName = _sqlEditor.getConnectionInfo().getDatabaseName();
    		return new DatabaseIdentifier(profileName, dbName);
    	}
        return null;
    }

    public void update(boolean isSQLEditorPage)
    {
        _isSourcePage = isSQLEditorPage;
        update();
    }

}

