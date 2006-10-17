/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.result;

import java.sql.Connection;
import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;

/**
 * @author Hui Cao
 *  
 */
public class GroupSQLResultRunnable extends SimpleSQLResultRunnable
{
    private final class ConfirmAction extends Action
    {
        boolean _goon = false;

        public void run()
        {

            IPreferenceStore store = SQLEditorPlugin.getDefault().getPreferenceStore();
            MessageDialogWithToggle dlg = MessageDialogWithToggle.openYesNoQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
                , Messages.GroupSQLResultRunnable_title, 
                Messages.GroupSQLResultRunnable_message, 
                null/* use default toggle message */, false, store, PreferenceConstants.EXECUTE_SQL_ERROR_MODE);
            int result = dlg.getReturnCode();

            //If we are not going to prompt anymore propogate the choice.
            if (dlg.getToggleState())
            {
                String preferenceValue;
                if (result == IDialogConstants.YES_ID)
                {
                    preferenceValue = PreferenceConstants.PROMPT_MODE_ALWAYS;
                }
                else
                {
                    preferenceValue = PreferenceConstants.PROMPT_MODE_NEVER;
                }

                // update PreferenceConstants.EXECUTE_SQL_ERROR_MODE to correspond
                store.setValue(PreferenceConstants.EXECUTE_SQL_ERROR_MODE, preferenceValue);
            }
            if (result == IDialogConstants.YES_ID)
            {
                _goon = true;
            }
            else
            {
                _goon = false;
            }
        }

    }

    private Connection              _conn;
    private Runnable                _postRun;
    private String[]                _groups;
    private Runnable _currentJob = null;
    private boolean                 _promptVar  = false;
    private HashMap                 _varDefs    = null;
    private IStatus                 _errorStatus = new Status(IStatus.ERROR, SQLEditorPlugin.PLUGIN_ID, 0, "", null);
    /**
     * @param con
     * @param sql
     * @param tracker
     */
    public GroupSQLResultRunnable(Connection con, String[] groups, IConnectionTracker tracker,
        Runnable postRun, DatabaseIdentifier databaseIdentifier, boolean promptVar, HashMap varDefs)
    {
        super(con, "", false, tracker,null, databaseIdentifier,null);
        this._conn = con;
        this._postRun = postRun;
        this._groups = groups;
        this._promptVar = promptVar;
        this._varDefs = varDefs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.internal.jobs.InternalJob#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    protected IStatus run(IProgressMonitor monitor)
    {
        boolean allSucceeded = true;
        //Obtain the Platform job manager
        IJobManager manager = Platform.getJobManager();
        if (monitor == null)
        {
            monitor = manager.createProgressGroup();
        }

        String task = Messages.GroupSQLResultRunnable_name;
        if (_groups.length > 1)
        {
            task += NLS.bind("GroupSQLResultRunnable.groups", (new Object[]{"" + _groups.length}));
        }
        monitor.beginTask(task, _groups.length);

        try
        {
            for (int i = 0; i < _groups.length; i++)
            {
                if (_groups.length > 1)
                {
                    monitor.subTask(NLS.bind("GroupSQLResultRunnable.group", (new Object[]{"" + i})));
                }
                SQLDevToolsConfiguration f = SQLToolsFacade
				.getConfigurationByProfileName(_databaseIdentifier
						.getProfileName());
                _currentJob = f.getExecutionService()
				.createAdHocScriptRunnable(_conn, _groups[i], false, _tracker, monitor,
	                    getDatabaseIdentifier(), null, null);
				if (_currentJob == null) {
					_currentJob = new SimpleSQLResultRunnable(_conn, _groups[i], false, _tracker, monitor,
							getDatabaseIdentifier(), null);
				}
				//TODO other types of Runnable
				if (_currentJob instanceof ResultSupportRunnable)
				{
					ResultSupportRunnable resultSupportRunnable = ((ResultSupportRunnable)_currentJob);
					resultSupportRunnable.setProgressGroup(monitor, 1);
					resultSupportRunnable.schedule();
					try
					{
						//wait until it finishes
						resultSupportRunnable.join();
					}
					catch (InterruptedException e)
					{
                        resultsViewAPI.appendThrowable(getOperationCommand(), e);
						synchronized (resultSupportRunnable.getOperationCommand()) {
							resultsViewAPI.appendStatusMessage(resultSupportRunnable.getOperationCommand(), e.getLocalizedMessage());
							resultsViewAPI.updateStatus(resultSupportRunnable.getOperationCommand(), OperationCommand.STATUS_FAILED);
						}
					}
					
					monitor.worked(1);
                    if (resultsViewAPI.getCurrentStatus(resultSupportRunnable.getOperationCommand()) != OperationCommand.STATUS_SUCCEEDED)
                    {
                        allSucceeded = false;
                    }
                    
					if (monitor.isCanceled())
					{
						resultSupportRunnable.terminateExecution();
						return Status.CANCEL_STATUS;
					}
					else if (resultsViewAPI.getCurrentStatus(resultSupportRunnable.getOperationCommand()) != OperationCommand.STATUS_SUCCEEDED && i < _groups.length - 1)
					{
						//since we'll kill the connection during terminating, there's no way to continue
						if (resultsViewAPI.getCurrentStatus(resultSupportRunnable.getOperationCommand()) == OperationCommand.STATUS_TERMINATED)
						{
							return Status.CANCEL_STATUS;
						}
						
						IPreferenceStore store = SQLEditorPlugin.getDefault().getPreferenceStore();
						String errorpm = store.getString(PreferenceConstants.EXECUTE_SQL_ERROR_MODE);
						if (errorpm == null || errorpm.equals("") || PreferenceConstants.PROMPT_MODE_PROMPT.equals(errorpm)) //$NON-NLS-1$
						{
							//prompt user whether to continue
							final ConfirmAction run = new ConfirmAction();
							PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
							{
								public void run()
								{
									run.run();
								}
							}
							);
							if (!run._goon)
							{
								return Status.CANCEL_STATUS;
							}
						}
						else if (PreferenceConstants.PROMPT_MODE_ALWAYS.equals(errorpm))
						{
							continue;
						}
						else
						{
							//never
							return Status.CANCEL_STATUS;
						}
					}
				}
				
            }
        }
        finally
        {
            monitor.done();
            if (_postRun != null)
            {
            	PlatformUI.getWorkbench().getDisplay().syncExec(_postRun);
            }
        }
        return allSucceeded ? Status.OK_STATUS : _errorStatus;
    }

    public void run()
    {
        run(null);
    }

}
