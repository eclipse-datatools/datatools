/**
 * Created on 2004-10-26
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import java.sql.SQLException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeployable;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;

/**
 * @author Hui Cao
 *  
 */
public class DeployAction extends SelectionDispatchAction
{
    /**
     *  
     */
    public DeployAction(IWorkbenchSite site)
    {
        super(site);

        setText(Messages.DeployAction_label); //$NON-NLS-1$

        update(getSelection());

        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpContextIds.SAVE_TO_DATABASE_ACTION, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jdt.ui.actions.SelectionDispatchAction#selectionChanged(org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void selectionChanged(IStructuredSelection selection)
    {
        setEnabled(canEnable(selection));
    }

    private boolean canEnable(IStructuredSelection selection)
    {
        IEditorPart activeEditor = getSite().getPage().getActiveEditor();
        if (activeEditor != null && activeEditor.getAdapter(SQLEditor.class) != null)
        {
            SQLEditor editor = (SQLEditor) activeEditor.getAdapter(SQLEditor.class);
            return selection.size() == 1 && selection.getFirstElement() instanceof IASTDeployable && editor.isConnected();
        }
        return false;
        //FIXME: Shall we check the syntax errors?
    }

    public void run(IStructuredSelection selection)
    {
        if (!(selection.size() == 1 && selection.getFirstElement() instanceof IASTDeployable))
        {
            return;
        }
        IASTDeployable node = (IASTDeployable) selection.getFirstElement();
        SQLEditor editor = (SQLEditor) getSite().getPage().getActiveEditor();
        if (editor.getSQLType() != SQLParserConstants.TYPE_SQL_ROOT)
        {
            //just delegate to SQLEditor
            try
            {
                editor.doSave(new NullProgressMonitor());
            }
            catch (RuntimeException e)
            {
                SQLEditorPlugin.getDefault().log( e);
            }
        }
        else
        {
            if (editor.isConnected())
            {
                try
                {
                    DatabaseIdentifier databaseIdentifier = editor.getDatabaseIdentifier();
                    IControlConnection controlCon = EditorCorePlugin.getControlConnectionManager()
                        .getOrCreateControlConnection(databaseIdentifier);
                    //TODO: Actually, this util method should be in a class called SQLUtil
                    controlCon.executeDDL(new String[]{node.getSQLText()});
                    String dbObjectName = node.getDBObjectName();
                    int parseType = node.getType();
                    int type = getObjectType(parseType);  
                    //TODO DSE refresh
                    MessageDialog.openInformation(getShell(), Messages.DeployAction_save_information, //$NON-NLS-1$
                    		NLS.bind(Messages.DeployAction_save_message, dbObjectName)); //$NON-NLS-1$

                }
                catch (SQLException e)
                {
                    final IStatus fstatus = new Status(IStatus.ERROR, SQLEditorPlugin.PLUGIN_ID, IStatus.OK, e
                        .getMessage(), e);
                    final String title = Messages.DeployAction_error_title; //$NON-NLS-1$
                    final String msg = Messages.DeployAction_error_message; //$NON-NLS-1$
                    Display display = getShell().getDisplay();
                    display.asyncExec(new Runnable()
                    {
                        public void run()
                        {
                            ErrorDialog.openError(getShell(), title, msg, fstatus);
                        }
                    }
                    );

                    SQLEditorPlugin.getDefault().log(e);
                }
                catch (NoSuchProfileException e)
                {
                    SQLEditorPlugin.getDefault().log(e);
                }

            }
        }
    }

    /**
	 * @return
	 */
    private int getObjectType(int parseType) 
    {
        int type = ProcIdentifier.TYPE_SP;
        switch (parseType)
        {
            case  SQLParserConstants.TYPE_SQL_CREATE_PROCEDURE:
                type = ProcIdentifier.TYPE_SP;
                break;
            case SQLParserConstants.TYPE_SQL_CREATE_FUNCTION:
                type = ProcIdentifier.TYPE_UDF;
                break;
            case SQLParserConstants.TYPE_SQL_CREATE_TRIGGER:
                type = ProcIdentifier.TYPE_TRIGGER;
                break;              
            case SQLParserConstants.TYPE_SQL_CREATE_EVENT:
                type = ProcIdentifier.TYPE_EVENT;
                break;
        }
        return type;
    }
}
