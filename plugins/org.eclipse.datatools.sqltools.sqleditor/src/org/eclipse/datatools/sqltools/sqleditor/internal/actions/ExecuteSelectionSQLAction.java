/**
 * Created on Dec 23, 2004
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import java.util.HashMap;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorResources;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.PlatformUI;

/**
 * @author Li Huang
 *  
 */
public class ExecuteSelectionSQLAction extends BaseExecuteAction  implements ISelectionChangedListener
{
    private SQLEditor     _sqlEditor;
    private ITextSelection _selection;
    /**
     * @param text
     * @param image
     */
    public ExecuteSelectionSQLAction(SQLEditor targetEditor)
    {
        setText(Messages.getString("ExecuteSelectionSQLAction.label"));//$NON-NLS-1$
        setToolTipText(Messages.getString("ExecuteSelectionSQLAction.tooltip"));//$NON-NLS-1$
        setImageDescriptor(SQLEditorResources.getImageDescriptor("sql_execute_selection"));
        setActionDefinitionId(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID);
        setActiveEditor(targetEditor);
        targetEditor.getSelectionProvider().addSelectionChangedListener(this);
        update();

        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IHelpContextIds.EXECUTE_SELECTED_TEXT_ACTION);
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
        setEnabled(_sqlEditor != null && _sqlEditor.getConnectionInfo().getSharedConnection() != null && _sqlEditor.getSelectedText() != null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.actions.BaseExplainAction#getSQLStatements()
     */
    public String getSQLStatements()
    {
        return _sqlEditor == null ? null : _sqlEditor.getSelectedText();
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

}

