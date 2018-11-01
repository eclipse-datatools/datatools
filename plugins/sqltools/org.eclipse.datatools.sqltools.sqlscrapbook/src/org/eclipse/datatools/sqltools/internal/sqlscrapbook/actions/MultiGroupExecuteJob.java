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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.sqleditor.result.GroupSQLResultRunnable;
import org.eclipse.osgi.util.NLS;

/**
 * @author Hui Cao
 */
public class MultiGroupExecuteJob extends Job
{
    public MultiGroupExecuteJob(String name, DatabaseIdentifier databaseIdentifier, String[] sqls, String[] targets)
    {
        super(name);
        _databaseIdentifier = databaseIdentifier;
        _sqls = sqls;
        _targets = targets;
    }

    private DatabaseIdentifier _databaseIdentifier;
    private String[]           _sqls;
    private String[]           _targets;

    protected IStatus run(IProgressMonitor monitor)
    {
        if (_databaseIdentifier == null || _sqls == null)
        {
            return Status.CANCEL_STATUS;
        }
        
        IJobManager manager = Job.getJobManager();
        if (monitor == null)
        {
            monitor = manager.createProgressGroup();
        }
        String task = Messages.GroupSQLResultRunnable_name;
        if (_sqls.length > 1)
        {
            task += NLS.bind(Messages.GroupSQLResultRunnable_groups, (new Object[]{"" + _sqls.length})); //$NON-NLS-1$ //$NON-NLS-2$
        }
        monitor.beginTask(task, _sqls.length);

        try
        {
            SQLDevToolsConfiguration f = SQLToolsFacade.getConfigurationByProfileName(_databaseIdentifier
                    .getProfileName());
            SQLService sqlService = f.getSQLService();

            for (int i = 0; i < _sqls.length; i++)
            {
                if (_sqls.length > 1)
                {
                    monitor.subTask(NLS.bind(Messages.GroupSQLResultRunnable_group, (new Object[]{"" + i, _targets[i]}))); 
                }
                
                String[] groups = new String[]
                {
                    _sqls[i]
                };
                if (sqlService != null)
                {
                    groups = sqlService.splitSQL(_sqls[i]);
                }

                // don't pass in connection, let GroupSQLResultRunnable create and close the connection
                Job job = new GroupSQLResultRunnable(null, groups, null, null, _databaseIdentifier, false, null,
                        Messages.BaseExecuteAction_group_exec_title, _targets[i]);
                job.setName(Messages.BaseExecuteAction_job_title);
                job.setProgressGroup(monitor, 1);
                job.schedule();
                // In fact, currently, this ExecuteParallelRunnable is especially used for "Show plan while executing SQL
                // statements in vendor option page"
                Runnable parallelRunnable = f.getExecutionService().createExecuteParallelRunnable(_sqls[i],
                        _databaseIdentifier);
                if (parallelRunnable != null)
                {
                    new Thread(parallelRunnable).start();
                }
                job.join();
                
                if (monitor.isCanceled() || Status.CANCEL_STATUS.equals(job.getResult()))
                {
                    return Status.CANCEL_STATUS;
                }
                monitor.worked(1);
            }

        }
        catch (Exception e)
        {
            Status status = new Status(IStatus.ERROR, SqlscrapbookPlugin.PLUGIN_ID, 0, e.getMessage(), e); //$NON-NLS-1$
            return status;
        }
        finally
        {
            monitor.done();
        }
        return Status.OK_STATUS;
    }

}
