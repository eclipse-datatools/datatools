/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.core;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ILogger;
import org.eclipse.datatools.sqltools.sqleditor.result.GroupSQLResultRunnable;

/**
 * Execute the generated scripts in the schema object editor
 * 
 * @author Idull
 */
public class ScriptsExecutionRunnable implements Runnable
{
    private String             _scripts;
    private DatabaseIdentifier _databaseIdentifier;
    private ILogger            _log                     = SOEUIPlugin.getLogger(null);
    private IJobChangeListener _jobListener;
    private boolean            _syncExec;
    private IProgressMonitor   _monitor;
    private String             _groupExecutionDspString = "Group execution";
    private String             _consumerName            = "Schema object editor";

    public ScriptsExecutionRunnable(String scripts, DatabaseIdentifier databaseIdentifier, IJobChangeListener listener,
            boolean syncExec, IProgressMonitor monitor)
    {
        _scripts = scripts;
        _databaseIdentifier = databaseIdentifier;
        _jobListener = listener;
        _syncExec = syncExec;
        _monitor = monitor;
    }

    public ScriptsExecutionRunnable(String scripts, DatabaseIdentifier databaseIdentifier, IJobChangeListener listener,
            boolean syncExec, IProgressMonitor monitor, String groupExecutionDspString, String consumerName)
    {
        _scripts = scripts;
        _databaseIdentifier = databaseIdentifier;
        _jobListener = listener;
        _syncExec = syncExec;
        _monitor = monitor;
        _groupExecutionDspString = groupExecutionDspString;
        _consumerName = consumerName;
    }

    public void run()
    {
        try
        {
            SQLDevToolsConfiguration f = SQLToolsFacade.getConfigurationByProfileName(_databaseIdentifier
                    .getProfileName());

            String[] groups = new String[]
            {
                _scripts
            };
            SQLService sqlService = f.getSQLService();
            if (sqlService != null)
            {
                groups = sqlService.splitSQL(_scripts);
            }
            GroupSQLResultRunnable job = new GroupSQLResultRunnable(null, groups, null, null, _databaseIdentifier,
                    false, null, _groupExecutionDspString, _consumerName);
            if (_jobListener != null)
            {
                job.addJobChangeListener(_jobListener);
            }
            job.addJobChangeListener(new IJobChangeListener()
            {
                public void aboutToRun(IJobChangeEvent event)
                {

                }

                public void awake(IJobChangeEvent event)
                {

                }

                public void done(final IJobChangeEvent event)
                {
                    SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
                    {
                        public void run()
                        {
                            if (!event.getResult().isOK())
                            {
                                _monitor.setCanceled(true);
                            }
                        }
                    });
                }

                public void running(IJobChangeEvent event)
                {

                }

                public void scheduled(IJobChangeEvent event)
                {

                }

                public void sleeping(IJobChangeEvent event)
                {

                }
            });
            job.setUser(true);
            job.schedule();
            if (_syncExec)
            {
                job.join();
            }
        }
        catch (Exception ex)
        {
            _log.error("ScriptsExecutionRunnable_error_execution", ex);
        }

    }
}
