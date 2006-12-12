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
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionTracker;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.result.Messages;
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

    private Runnable _postRun;
    private String[] _groups;
    private Runnable _currentJob = null;
    private boolean  _promptVar  = false;
    private HashMap  _varDefs    = null;
    private String   _parentDisplayName;
    private String   _consumerName;
    /**
     * @param con if con is null, corresponding ConnectionService.createConnection will be called.
     * @param sql
     * @param tracker
     */
    public GroupSQLResultRunnable(Connection con, String[] groups, IConnectionTracker tracker,
        Runnable postRun, DatabaseIdentifier databaseIdentifier, boolean promptVar, HashMap varDefs)
    {
        super(con, "", false, tracker,null, databaseIdentifier,null); //$NON-NLS-1$
        this._postRun = postRun;
        this._groups = groups;
        this._promptVar = promptVar;
        this._varDefs = varDefs;
    }

    public GroupSQLResultRunnable(Connection con, String[] groups, IConnectionTracker tracker, Runnable postRun,
            DatabaseIdentifier databaseIdentifier, boolean promptVar, HashMap varDefs, String parentDisplayName, String consumerName)
    {
        this(con, groups, tracker, postRun, databaseIdentifier, promptVar, varDefs);
        this._parentDisplayName = parentDisplayName;
        this._consumerName = consumerName;
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
        ResultsViewAPI resultsViewAPI = ResultsViewAPI.getInstance();
        IJobManager manager = Platform.getJobManager();
        if (monitor == null)
        {
            monitor = manager.createProgressGroup();
        }

        String task = Messages.GroupSQLResultRunnable_name;
        if (_groups.length > 1)
        {
            task += NLS.bind("GroupSQLResultRunnable.groups", (new Object[]{"" + _groups.length})); //$NON-NLS-1$ //$NON-NLS-2$
        }
        monitor.beginTask(task, _groups.length);

        try
        {
        	if (getConnection() == null)
        	{
                SQLDevToolsConfiguration f = SQLToolsFacade
						.getConfigurationByProfileName(_databaseIdentifier
								.getProfileName());
				ConnectionService conService = f.getConnectionService();
				Connection conn = conService.createConnection(
						_databaseIdentifier, true);
				setConnection(conn);
        	}
            if (_groups.length > 1)
            {
                resultsViewAPI.createNewInstance(getOperationCommand(), null);
            }
            for (int i = 0; i < _groups.length; i++)
            {
                OperationCommand parentCommand = _groups.length > 1 ? getOperationCommand() : null;
                if (_groups.length > 1)
                {
                    resultsViewAPI.appendStatusMessage(getOperationCommand(), _groups[i]);
                    monitor.subTask(NLS.bind("GroupSQLResultRunnable.group", (new Object[]{"" + i}))); //$NON-NLS-1$ //$NON-NLS-2$
                }
                SQLDevToolsConfiguration f = SQLToolsFacade
				.getConfigurationByProfileName(_databaseIdentifier
						.getProfileName());
                _currentJob = f.getExecutionService()
				.createAdHocScriptRunnable(getConnection(), _groups[i], false, _tracker, monitor,
	                    getDatabaseIdentifier(), null, null);
				if (_currentJob == null) {
					_currentJob = new SimpleSQLResultRunnable(getConnection(), _groups[i], false, _tracker, monitor,
							getDatabaseIdentifier(), null);
				}
				//TODO other types of Runnable
				if (_currentJob instanceof ResultSupportRunnable)
				{
					ResultSupportRunnable resultSupportRunnable = ((ResultSupportRunnable)_currentJob);
                    resultSupportRunnable.setParentOperCommand(parentCommand);
					resultSupportRunnable.setProgressGroup(monitor, 1);
					resultSupportRunnable.schedule();
                    
                    // wont generate group result
                    if (_groups.length == 1)
                    {
                        _operationCommand = resultSupportRunnable.getOperationCommand();
                    }
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
                        resultsViewAPI.updateStatus(getOperationCommand(), resultsViewAPI.calculateStatus(getOperationCommand()));
						return Status.CANCEL_STATUS;
					}
					else if (resultsViewAPI.getCurrentStatus(resultSupportRunnable.getOperationCommand()) != OperationCommand.STATUS_SUCCEEDED && i < _groups.length - 1)
					{
						//since we'll kill the connection during terminating, there's no way to continue
						if (resultsViewAPI.getCurrentStatus(resultSupportRunnable.getOperationCommand()) == OperationCommand.STATUS_TERMINATED)
						{
                            resultsViewAPI.updateStatus(getOperationCommand(), resultsViewAPI.calculateStatus(getOperationCommand()));
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
                                resultsViewAPI.updateStatus(getOperationCommand(), resultsViewAPI.calculateStatus(getOperationCommand()));
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
        catch(Exception e)
        {
        	SQLEditorPlugin.getDefault().log(e); 
        }
        finally
        {
            monitor.done();
            if (_postRun != null)
            {
            	PlatformUI.getWorkbench().getDisplay().syncExec(_postRun);
            }
            ProfileUtil.closeConnection(_databaseIdentifier.getProfileName(), _databaseIdentifier.getDBname(), getConnection());
        }
        resultsViewAPI.updateStatus(getOperationCommand(), resultsViewAPI.calculateStatus(getOperationCommand()));
        return allSucceeded ? Status.OK_STATUS : Status.CANCEL_STATUS;
    }

    public void run()
    {
        run(null);
    }

    public OperationCommand getOperationCommand()
    {
        if (_operationCommand == null)
        {
            String parentDspName = _parentDisplayName == null ? Messages.GroupSQLResultRunnable_group_exec
                    : _parentDisplayName;
            _operationCommand = new OperationCommand(OperationCommand.ACTION_EXECUTE, parentDspName, _consumerName, //$NON-NLS-2$
                    _databaseIdentifier.getProfileName(), _databaseIdentifier.getDBname());
        }
        return _operationCommand;
    }
}