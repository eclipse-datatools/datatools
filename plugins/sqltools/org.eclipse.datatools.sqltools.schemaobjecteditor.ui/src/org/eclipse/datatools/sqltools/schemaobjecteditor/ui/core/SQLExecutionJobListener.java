/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;

/**
 * When the delta ddl scripts are successfully executed against the database, need to perform the following operations:
 * <ul>
 * <li>1.Refresh the edit model from the database and re-clone it;
 * <li>2.Re-register the model listener for all the re-cloned SQL objects;
 * <li>3.Refresh all the editor pages based on the refreshed SQL objects.
 * </ul>
 * All these jobs will be delegated to <code>ISchemaObjectEditorHandler</code>.
 * 
 * @author Idull
 */
public class SQLExecutionJobListener implements IJobChangeListener
{
    private ISchemaObjectEditor _editor;
    private IProgressMonitor    _monitor;

    public SQLExecutionJobListener(ISchemaObjectEditor editor, IProgressMonitor monitor)
    {
        super();
        _editor = editor;
        _monitor = monitor;
    }

    public void aboutToRun(IJobChangeEvent event)
    {

    }

    public void awake(IJobChangeEvent event)
    {

    }

    public void done(IJobChangeEvent event)
    {
        if (_editor == null)
        {
            SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
            {
                public void run()
                {
                    if (_monitor != null)
                    {
                        _monitor.setCanceled(true);
                    }
                }
            });

            return;
        }

        if (event.getResult().isOK())
        {
            final ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) _editor.getEditorInput();
            input.getEditModelObject().stopLogging();

            // Check if the editor is disposed
            // [544833-1] we also need to refresh database while use close dirty editor part and save the edit result at
            // the same time
            IEditorPart part = SOEUIPlugin.getActiveWorkbenchPage().findEditor(_editor.getEditorInput());
            if (part != null)
            {
                SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
                {
                    public void run()
                    {
                        _editor.setEditorPartName(input.getEditModelObject().getMainSQLObject().getName());
                    }
                });
            }

            ISchemaObjectEditorHandler handler = _editor.getEditorHandler();
            if (handler != null && _editor.needRefreshAfterSave())
            {
                // Set the name of the immutable model to make it consistent with edit model
                RefreshEditModelJob refreshJob = new RefreshEditModelJob(Messages.SQLExecutionJobListener_refreshing,
                        handler);
                refreshJob.setUser(true);
                refreshJob.schedule();
                refreshJob.addJobChangeListener(new JobChangeAdapter()
                {
                    public void done(IJobChangeEvent event)
                    {
                        super.done(event);
                        SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
                        {
                            public void run()
                            {
                                if (_monitor != null)
                                {
                                    _monitor.done();
                                }
                            }
                        });

                    }
                });
            }

        }
        // not all the scripts are successfully executed
        else
        {
            SOEUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
            {
                public void run()
                {
                    String[] buttons = new String[]
                    {
                        IDialogConstants.YES_LABEL
                    };
                    MessageDialog d = new MessageDialog(SOEUIPlugin.getActiveWorkbenchShell(),
                            Messages.SavePreviewDialog_problem, null, Messages.SavePreviewDialog_execution_failed_msg,
                            MessageDialog.ERROR, buttons, 0);
                    d.open();
                    if (_monitor != null)
                    {
                        _monitor.setCanceled(true);
                    }
                }
            });
        }
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
}
