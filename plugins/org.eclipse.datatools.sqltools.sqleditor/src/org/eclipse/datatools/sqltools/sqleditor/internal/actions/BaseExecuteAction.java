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
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.SQLService;
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
    protected Job _job;

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
        try
        {
            SQLDevToolsConfiguration f = SQLToolsFacade.getConfigurationByProfileName(databaseIdentifier.getProfileName());

            String[] groups = new String[] 
            {
                sql
            }
            ;
            SQLService sqlService = f.getSQLService();
            if (sqlService != null)
            {
                groups = sqlService.splitSQL(sql, isSplitByDefault());
            }

            //don't pass in connection, let GroupSQLResultRunnable create and close the connection
            _job = new GroupSQLResultRunnable(getExecutionConnection(), groups, null, getPostRun(), databaseIdentifier, promptVariable(), getVariableDeclarations(), Messages.BaseExecuteAction_group_exec_title, "SQL Editor");
            _job.setName(Messages.BaseExecuteAction_job_title);
            _job.setUser(true);
            //don't call job.join() to prevent blocking eclipse
            _job.schedule();

            // In fact, currently, this ExecuteParallelRunnable is especially used for "Show plan while executing SQL
            // statements in vendor option page"
            Runnable parallelRunnable = f.getExecutionService().createExecuteParallelRunnable(getSQLStatements(), databaseIdentifier);
            if(parallelRunnable != null)
            {
                new Thread(parallelRunnable).start();
            }
            // BZ 220685
            // Don't show ResultsView here because ResultsViewAPI takes care of it
            //PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(EditorConstants.RESULTS_VIEW);
        }
        catch (Exception e)
        {
            processError(Messages.ExecuteSQLActionDelegate_error_execute, e, null); 
        }
    }

    /**
     * Get the connection which is used to execute job.
     * 
     * @return A connection for job execution. <p>Return <code>null</code> if want job to initialize connection by itself.
     */
    protected Connection getExecutionConnection()
    {
        return null;
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
                    stat = new Status(IStatus.ERROR, SQLEditorPlugin.PLUGIN_ID, 0, error.getMessage() == null ? "" : error //$NON-NLS-1$
                        .getMessage(), error);
                }
                String title = Messages.common_error;
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
    
    protected boolean isSplitByDefault()
    {
        return true;
    }
}
