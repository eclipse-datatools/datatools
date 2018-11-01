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
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.ConnectionInfoDialog;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.FilesConnectionInfoDialog;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.Messages;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.BaseExecuteAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Hui Cao
 *
 */
public class ExecuteSQLFileActionDelegate extends BaseExecuteAction implements IObjectActionDelegate 
{
    public final static int DEFAULT_FILE_SIZE = 15 * 1024;
	
    private DatabaseIdentifier _databaseIdentifier;
    private String _sql;
    private ISelection _selection = null;


    public DatabaseIdentifier getDatabaseIdentifier()
    {
        return _databaseIdentifier;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.actions.BaseExplainAction#getSQLStatements()
     */
    public String getSQLStatements()
    {
        return _sql;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.actions.BaseExplainAction#getPostRun()
     */
    public Runnable getPostRun()
    {
        return null;
    }


    /* (non-Javadoc)
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart)
    {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action)
    {
        //load sql file
        if (_selection != null)
        {
            int size = ((IStructuredSelection)_selection).size();
            IFile file = (IFile)((IStructuredSelection)_selection).getFirstElement();
            Object[] fileObjs = ((IStructuredSelection) _selection).toArray();
            IFile[] files = new IFile[fileObjs.length];
            System.arraycopy(fileObjs, 0, files, 0, fileObjs.length);
            
            try
            {
                ISQLEditorConnectionInfo connInfo = SQLFileUtil.getConnectionInfo(file);
                //for execution, SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO does not work 
                if (connInfo.equals(SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO))
                {
                    connInfo = SQLFileUtil.getDefaultConnectionInfo();
                }
                if (size == 1)
                {
                    //must have a connected profile
                    if (!connInfo.isConnected())
                    {
                        ConnectionInfoDialog dlg = new ConnectionInfoDialog(SqlscrapbookPlugin.getActiveWorkbenchShell(), connInfo, false, Messages.ConnectionInfoDialog_title_for_file);
                        dlg.setMustConnect(true);
                        if (dlg.open() == IDialogConstants.CANCEL_ID)
                        {
                            return;
                        }
                        
                        connInfo = dlg.getConnectionInfo();
                        if (!connInfo.isConnected())
                        {
                            return;
                        }
                        SQLFileUtil.setEncodedConnectionInfo(file, connInfo.encode());
                    }
                }
                else if (size > 1)
                {
                    FilesConnectionInfoDialog dlg = new FilesConnectionInfoDialog(SqlscrapbookPlugin.getActiveWorkbenchShell(), connInfo, true, files, Messages.ConnectionInfoDialog_title_for_file);
                    dlg.setMustConnect(true);
                    if (dlg.open() == IDialogConstants.CANCEL_ID)
                    {
                        return;
                    }
                    
                    fileObjs = dlg.getCheckedFiles();
                    files = new IFile[fileObjs.length];
                    System.arraycopy(fileObjs, 0, files, 0, fileObjs.length);
                    
                    if (dlg.overrideConnectionInfo())
                    {
                        for (int i = 0; i < files.length; i++)
                        {
                            IFile f = (IFile)files[i];
                            SQLFileUtil.setEncodedConnectionInfo(f, connInfo.encode());
                        }
                    }

                    connInfo = dlg.getConnectionInfo();
                    if (!connInfo.isConnected())
                    {
                        return;
                    }
                    
                }
                
                _databaseIdentifier = new DatabaseIdentifier(connInfo.getConnectionProfileName(), connInfo.getDatabaseName());

                String[] sqls = new String[files.length];
                String[] targets = new String[files.length];
                for (int i = 0; i < files.length; i++)
                {
                    sqls[i] = readFile((IFile) files[i], null);
                    targets[i] = ((IFile) files[i]).getFullPath().makeRelative().toString();
                }
                MultiGroupExecuteJob job = new MultiGroupExecuteJob(Messages.FileExecution_job_name,
                        _databaseIdentifier, sqls, targets);
                job.setUser(true);
                job.schedule();

                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(
                        ResultsConstants.SQL_RESULTS_VIEW_ID);

            }
            catch (PartInitException e)
            {
                processError("ExecuteSQLActionDelegate.error.initview", e, null); //$NON-NLS-1$
            }
            catch (CoreException e)
            {
                processError("ExecuteSQLFileActionDelegate.error.msg", e, e.getStatus());
            }
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection)
    {
        this._selection = selection;
    }

    private String readFile(IFile file, String encoding) throws CoreException
    {
        Reader in= null;
        InputStream contentStream = file.getContents();

        try 
        {

            if (encoding == null)
            {
                encoding= ResourcesPlugin.getEncoding();
            }

            in= new BufferedReader(new InputStreamReader(contentStream, encoding), DEFAULT_FILE_SIZE);
            StringBuffer buffer= new StringBuffer(DEFAULT_FILE_SIZE);
            char[] readBuffer= new char[2048];
            int n= in.read(readBuffer);
            while (n > 0) 
            {
                buffer.append(readBuffer, 0, n);
                n= in.read(readBuffer);
            }

            return buffer.toString();

        }
        catch (IOException x) 
        {
            String message= (x.getMessage() != null ? x.getMessage() : ""); //$NON-NLS-1$
            IStatus s= new Status(IStatus.ERROR, SqlscrapbookPlugin.PLUGIN_ID, IStatus.OK, message, x);
            throw new CoreException(s);
        }
        finally 
        {
            if (in != null) 
            {
                try 
                {
                    in.close();
                }
                catch (IOException x) 
                {
                }
            }
        }
    }


    /* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate2#init(org.eclipse.jface.action.IAction)
     */
    public void init(IAction action)
    {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate2#dispose()
     */
    public void dispose()
    {
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate2#runWithEvent(org.eclipse.jface.action.IAction, org.eclipse.swt.widgets.Event)
     */
    public void runWithEvent(IAction action, Event event)
    {
        run(action);
    }

    public void update() 
    {
        // TODO Auto-generated method stub

    }

}
