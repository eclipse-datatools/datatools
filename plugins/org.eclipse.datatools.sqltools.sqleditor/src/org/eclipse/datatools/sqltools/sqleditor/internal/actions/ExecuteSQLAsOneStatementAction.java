/**
 * Created on Jan 20, 2009
 * 
 * Copyright (c) Sybase, Inc. 2004-2009. All rights reserved.
 */
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
 * Execute selected SQL as single statement. If parser failed to parse the SQL, the SQL will be execute as one
 * statement. BUG221845 and BUG185501.
 * 
 * @author sul
 */
public class ExecuteSQLAsOneStatementAction extends BaseExecuteAction implements ISelectionChangedListener, IPageUpdate
{
    private SQLEditor      _sqlEditor;
    private ITextSelection _selection;
    private boolean        _isSourcePage = true;

    /**
     * @param text
     * @param image
     */
    public ExecuteSQLAsOneStatementAction(SQLEditor targetEditor)
    {
        setText(Messages.ExecuteSQLAsOneStatement_label);
        setToolTipText(Messages.ExecuteSQLAsOneStatement_tooltip);
        // setImageDescriptor(SQLEditorResources.getImageDescriptor("sql_execute_selection"));
        setActionDefinitionId(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID);
        setId(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID);
        setActiveEditor(targetEditor);
        targetEditor.getSelectionProvider().addSelectionChangedListener(this);
        update();

        PlatformUI.getWorkbench().getHelpSystem().setHelp(
                this,
                HelpUtil.getContextId(IHelpContextIds.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION, SQLEditorPlugin.getDefault()
                        .getBundle().getSymbolicName()));
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
        setEnabled(_isSourcePage && _sqlEditor != null && _sqlEditor.isConnected()
                && _sqlEditor.getSelectedText() != null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.actions.BaseExplainAction#getSQLStatements()
     */
    public String getSQLStatements()
    {
        if (_sqlEditor == null)
        {
            return null;
        }

        String selectedText = SQLToolsFacade.getDBHelper(getDatabaseIdentifier()).preprocessSQLScript(
                _sqlEditor.getSelectedText());

        return selectedText;

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
        };
        return postRun;
    }

    /*
     * (non-Javadoc)
     * 
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
        // get the offset of the selection
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
        // when user selects a range
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

    protected boolean isSplitByDefault()
    {
        return false;
    }

}
