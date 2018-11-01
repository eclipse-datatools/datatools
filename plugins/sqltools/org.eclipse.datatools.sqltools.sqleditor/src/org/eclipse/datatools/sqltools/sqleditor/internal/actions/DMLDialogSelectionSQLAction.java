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

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.sql.ui.dialogs.SQLPainterDlg;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.UIComponentService;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.sqleditor.IPageUpdate;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * @author Jeremy Lindop
 *  
 */
public class DMLDialogSelectionSQLAction extends Action  implements ISelectionChangedListener, IPageUpdate, IUpdate
{
    private SQLEditor     _sqlEditor;
    private ITextSelection _selection;
    private boolean        _isSourcePage = true;
    /**
     * @param text
     * @param image
     */
    public DMLDialogSelectionSQLAction(SQLEditor targetEditor)
    {
        setText(Messages.DMLDialogSelectionSQLAction_label);
        setToolTipText(Messages.DMLDialogSelectionSQLAction_tooltip);
        setImageDescriptor(SQLEditorResources.getImageDescriptor("sql_execute_selection"));
        setActionDefinitionId(ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID);
        setId(ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID);
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
    	SQLDevToolsUIConfiguration conf = SQLToolsUIFacade.getConfigurationByProfileName(_sqlEditor.getConnectionInfo().getConnectionProfileName());
    	UIComponentService componentService = conf.getUIComponentService();
		if (componentService.supportsDMLDialog())
    	{
			HashMap map = new HashMap();
			if (getFile() != null){
				map.put(UIComponentService.KEY_FILE, getFile());
			}
			SQLPainterDlg dlg = componentService.getDMLDialog(
					_sqlEditor.getSite().getShell(),
					null, 
					getSQLStatements(),
					_sqlEditor.getConnectionInfo().getConnectionProfileName(),
					null, null, null, null, map);
    	
			String sql = dlg.load();
			if (sql != null){
    			// Insert the new SQL - this overwrites the selected text
    			_sqlEditor.insert(sql);
			}
    	}
    	
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(_sqlEditor);
    }

    
    public void update()
    {
        setEnabled(_isSourcePage && _sqlEditor != null && _sqlEditor.isConnected());
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
     *  (non-Javadoc)
     */
    public IFile getFile(){
    	IFile file = null;
    	if (_sqlEditor != null){
    		IEditorInput input = _sqlEditor.getEditorInput();
            file = (input instanceof IFileEditorInput) ? ((IFileEditorInput) input).getFile() : null;
    	}
    	return file;
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
/*
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

    public void update(boolean isSQLEditorPage)
    {
        _isSourcePage = isSQLEditorPage;
        update();
    }

}

