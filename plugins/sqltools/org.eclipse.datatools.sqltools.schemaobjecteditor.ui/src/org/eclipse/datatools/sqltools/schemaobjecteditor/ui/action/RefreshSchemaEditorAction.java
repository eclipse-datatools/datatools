/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Action to refresh the schema editor
 * 
 * @author Idull
 */
public class RefreshSchemaEditorAction extends Action implements IHandler
{
    protected ISchemaObjectEditor _editor;

    public RefreshSchemaEditorAction()
    {
        super();
        setText(Messages.RefreshSchemaEditorAction_refresh_from_server);
        setImageDescriptor(Images.DESC_REFRESH);
    }

    public void run()
    {
        if (_editor == null)
        {
            return;
        }
        if (_editor.isDirty())
        {
            String[] buttons = new String[]
            {
                IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL
            };
            MessageDialog d = new MessageDialog(SOEUIPlugin.getActiveWorkbenchShell(),
                    Messages.RefreshSchemaEditorAction_referesh_editor, null,
                    Messages.RefreshSchemaEditorAction_question, MessageDialog.QUESTION, buttons, 0);
            int result = d.open();
            switch (result)
            {
                case IDialogConstants.CANCEL_ID:
                    return;
                default:
                    break;
            }
        }
        ISchemaObjectEditorHandler handler = _editor.getEditorHandler();
        RefreshSchemaEditorJob refreshJob = new RefreshSchemaEditorJob(Messages.RefreshSchemaEditorAction_refresh_job,
                handler);
        refreshJob.setUser(true);
        refreshJob.schedule();
    }

    public void setEditor(ISchemaObjectEditor _editor)
    {
        this._editor = _editor;
    }

    public static class RefreshSchemaEditorJob extends Job
    {
        ISchemaObjectEditorHandler _handler;

        public RefreshSchemaEditorJob(String name, ISchemaObjectEditorHandler handler)
        {
            super(name);
            _handler = handler;
        }

        protected IStatus run(IProgressMonitor monitor)
        {
            if (monitor == null)
            {
                monitor = manager.createProgressGroup();
            }
            if (_handler == null)
            {
                return Status.OK_STATUS;
            }
            monitor.beginTask(Messages.RefreshSchemaEditorAction_sync_with_db, monitor.UNKNOWN);
            _handler.refreshFromDB(monitor);
            return Status.OK_STATUS;
        }
    }

    /*
     * Since this class will be casted in HandlerProxy.loadHandler(), it should implements IHandler. The four methods
     * blow are the implementation of IHandler's.
     */
    public void addHandlerListener(IHandlerListener handlerListener)
    {

    }

    public void dispose()
    {

    }

    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        return null;
    }

    public void removeHandlerListener(IHandlerListener handlerListener)
    {

    }
}
