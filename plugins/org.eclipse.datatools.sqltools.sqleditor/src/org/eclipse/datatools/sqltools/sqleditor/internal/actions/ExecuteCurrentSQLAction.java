/**
 * Created on Jan 20, 2009
 * 
 * Copyright (c) Sybase, Inc. 2004-2009. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sqleditor.IPageUpdate;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.selection.ISQLSelection;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.selection.SQLSelectionFactory;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.PlatformUI;

/**
 * Execute current SQL, auto-select the SQLs by user defined delimiters based on current cursor position. 
 * BUG185501 and BUG221845.
 * 
 * @author sul
 */
public class ExecuteCurrentSQLAction extends BaseExecuteAction implements ISelectionChangedListener, IPageUpdate
{

    private SQLEditor      _sqlEditor;
    private boolean        _isSourcePage = true;

    public ExecuteCurrentSQLAction(SQLEditor targetEditor)
    {
        setText(Messages.ExecuteCurrentSQLAction_label);
        setToolTipText(Messages.ExecuteCurrentSQLAction_tooltip);
//        setImageDescriptor(SQLEditorResources.getImageDescriptor("sql_execute_selection"));
        setActionDefinitionId(ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID);
        setId(ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID);
        setActiveEditor(targetEditor);
        targetEditor.getSelectionProvider().addSelectionChangedListener(this);
        update();

        PlatformUI.getWorkbench().getHelpSystem().setHelp(
                this,
                HelpUtil.getContextId(IHelpContextIds.EXECUTE_CURRENT_SQL_ACTION, SQLEditorPlugin.getDefault()
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

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.sqltools.sqleditor.internal.actions.BaseExecuteAction#getDatabaseIdentifier()
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.sqltools.sqleditor.internal.actions.BaseExecuteAction#getPostRun()
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
     * @see org.eclipse.datatools.sqltools.sqleditor.internal.actions.BaseExecuteAction#getSQLStatements()
     */
    public String getSQLStatements()
    {
        if (_sqlEditor == null)
        {
            return null;
        }

        // bug185501: execute current statement
        IDocument doc = _sqlEditor.getDocumentProvider().getDocument(_sqlEditor.getEditorInput());
        if (doc == null)
        {
            return null;
        }

        return getCurrentStatements(doc);
    }

    private String getCurrentStatements(IDocument doc)
    {
        String selectedText = null;

        ISQLSelection selectionPolicy = SQLSelectionFactory.getInstance().getSQLSelection(_sqlEditor);
        selectedText = selectionPolicy.getStatements();

        return SQLToolsFacade.getDBHelper(getDatabaseIdentifier()).preprocessSQLScript(selectedText);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.IUpdate#update()
     */
    public void update()
    {
        setEnabled(_isSourcePage && _sqlEditor != null && _sqlEditor.isConnected() && _sqlEditor.getSelectedText() == null);
    }

    public void selectionChanged(SelectionChangedEvent event)
    {
        if (event.getSelection() instanceof ITextSelection)
        {
            update();
        }
    }

    public void update(boolean isSQLEditorPage)
    {
        _isSourcePage = isSQLEditorPage;
        update();
    }

    public SQLEditor getEditor()
    {
        return _sqlEditor;
    }

}
