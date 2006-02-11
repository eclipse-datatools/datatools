/*
 * Created on 2005-3-11
 *
 * Copyright (c) Sybase, Inc. 2004   
 * All rights reserved.                                    
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import java.sql.Connection;
import java.util.HashMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.IDBFactory;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.ISQLService;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.result.GroupSQLResultRunnable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * @author Hui Cao
 *
 */
public abstract class BaseExecuteAction extends Action implements IUpdate
{
    private Job _job;

    public abstract DatabaseIdentifier getDatabaseIdentifier();
    public abstract String getSQLStatements();
    public abstract Runnable getPostRun();

    public void run()
    {
        String sql = getSQLStatements();
        DatabaseIdentifier databaseIdentifier = getDatabaseIdentifier(); 
        if (databaseIdentifier == null || sql == null)
        {
            return;
        }
        Connection conn = null;
        try
        {
            conn = ProfileUtil.createConnection(databaseIdentifier.getProfileName(), databaseIdentifier.getDBname());

            String[] groups = new String[] 
            {
                sql
            }
            ;
            IDBFactory f = SQLToolsFacade.getDBFactoryByProfileName(databaseIdentifier.getProfileName());
            ISQLService sqlService = f.getSQLService();
            if (sqlService != null)
            {
                groups = sqlService.splitSQL(sql);
            }

            _job = new GroupSQLResultRunnable(conn, groups, null, getPostRun(), databaseIdentifier, promptVariable(), getVariableDeclarations());
            _job.setUser(true);
            _job.schedule();

            // In fact, currently, this ExecuteParallelRunnable is especially used for "Show plan while executing SQL
            // statements in vendor option page"
            Runnable parallelRunnable = f.createExecuteParallelRunnable(getSQLStatements(), databaseIdentifier);
            if(parallelRunnable != null)
            {
                new Thread(parallelRunnable).start();
            }
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(EditorConstants.RESULTS_VIEW);
        }
        catch (Exception e)
        {
            processError(Messages.getString("ExecuteSQLActionDelegate.error.execute"), e, null); //$NON-NLS-1$
        }
        finally {
			if (conn != null) {
				try {
					Connection sharedConn = ProfileUtil.getReusableConnection(databaseIdentifier);
					//Only close the connection when it's not the shared connection, 
					//e.g. for embeded derby, only one connection per VM is allowed.
					if (conn != sharedConn)
					{
						conn.close();
					}
				} catch (Throwable ex) {
					// skip
				}
			}
		}
    }

    /**
	 * Whether we should pop up a dialog to prompt user for variable values.
	 * Default is false.
	 * 
	 * @return
	 */
    protected boolean promptVariable()
    {
        return false;
    }

    /**
     * Variable declarations used for prompt variables
     * @return
     */
    protected HashMap getVariableDeclarations()
    {
        return null;
    }

    protected void processError(final String msg, final Exception error, final IStatus status)
    {
    	PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                IStatus stat = status;
                if (stat == null)
                {
                    stat = new Status(IStatus.ERROR, SQLEditorPlugin.PLUGIN_ID, 0, error.getMessage() == null ? "" : error
                        .getMessage(), error);
                }
                String title = Messages.getString("common.error");
                ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, msg, stat);
            }
        }
        );
        SQLEditorPlugin.getDefault().log(msg, error); //$NON-NLS-1$
    }

    protected SQLEditor getEditor()
    {
        return null;
    }
}
