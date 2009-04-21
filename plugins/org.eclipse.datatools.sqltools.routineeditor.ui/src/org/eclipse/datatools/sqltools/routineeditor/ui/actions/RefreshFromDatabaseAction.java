/**
 * Created on Jan. 7, 2005
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.routineeditor.ui.actions;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.routineeditor.ui.IRoutineEditorDocumentProvider;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorImages;
import org.eclipse.datatools.sqltools.sqleditor.IPageUpdate;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.TextEditorAction;

/**
 * @author Li Huang
 */
public class RefreshFromDatabaseAction extends TextEditorAction implements IPageUpdate
{
    SQLEditor _sqlEditor;
    private boolean        _isSourcePage = true;
    /**
     * @param bundle
     * @param prefix
     * @param editor
     */
    public RefreshFromDatabaseAction(ResourceBundle bundle, String prefix, ITextEditor editor)
    {
        super(bundle, prefix, editor);
        _sqlEditor = (SQLEditor) editor;
        setImageDescriptor(RoutineEditorImages.getImageDescriptor("refresh_from_database"));
        setActionDefinitionId(ISQLEditorActionConstants.REFRESH_FROM_DATABASE_ACTION_ID);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpContextIds.REFRESH_FROM_DATABASE_ACTION, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void run()
    {
        try
        {
        	IRoutineEditorDocumentProvider sqlEditorDocumentProvider = null;
            if (_sqlEditor.getParentEditor() != null)
            {
                sqlEditorDocumentProvider = (IRoutineEditorDocumentProvider) _sqlEditor.getParentEditor().getAdapter(IRoutineEditorDocumentProvider.class);
            }
            if (sqlEditorDocumentProvider == null)
            {
                sqlEditorDocumentProvider = (IRoutineEditorDocumentProvider) _sqlEditor
                .getDocumentProvider();
            }
            ProcIdentifier procIdentifier = ((ProcEditorInput) _sqlEditor.getEditorInput()).getProcIdentifier();
            IControlConnection controlConnection = EditorCorePlugin.getControlConnectionManager()
                .getOrCreateControlConnection(procIdentifier.getDatabaseIdentifier());
            sqlEditorDocumentProvider.refreshFromDatabase(_sqlEditor.getEditorInput(), controlConnection,
                procIdentifier);
            //Add status line message when the action 'refresh from database' succeeded.
            SQLEditorPlugin.getActiveEditor().getEditorSite().getActionBars().getStatusLineManager().setMessage(
                Messages.RefreshFromDatabase_success);

        }
        catch (Exception e)
        {
            try
            {
                throw new CoreException(new Status(IStatus.ERROR, RoutineEditorUIActivator.PLUGIN_ID, 0, NLS.bind(Messages.RefreshFromDatabase_fail,(new Object[]
                {
                    e.toString()
                }
                )), e));
            }
            catch (CoreException e1)
            {
            	RoutineEditorUIActivator.getDefault().log(e1);
            }

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.IUpdate#update()
     */
    public void update()
    {
        _sqlEditor = (SQLEditor) getTextEditor();
        if (_sqlEditor.getEditorInput() instanceof ProcEditorInput)
        {
            setEnabled(true);
        }
        else
        {
            setEnabled(false);
        }
    }

    public void update(boolean isSQLEditorPage)
    {
        _isSourcePage = isSQLEditorPage;
        update();
    }

}
