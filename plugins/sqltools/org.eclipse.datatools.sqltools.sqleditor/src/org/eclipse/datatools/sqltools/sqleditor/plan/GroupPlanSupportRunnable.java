/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.plan;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.plan.EPVFacade;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.plan.PlanRequest;
import org.eclipse.datatools.sqltools.plan.PlanSupportRunnable;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;

/**
 * Support explaining a group of SQL statements in SQL Editor.
 * 
 * @author Hui Cao
 * @author Dafan Yang
 */
public class GroupPlanSupportRunnable extends PlanSupportRunnable
{
    private final class ConfirmRunnable implements Runnable
    {
        boolean goon = false;

        public void run()
        {

            IPreferenceStore store = SQLEditorPlugin.getDefault().getPreferenceStore();
            MessageDialogWithToggle dlg = MessageDialogWithToggle.openYesNoQuestion(SQLEditorPlugin
                    .getActiveWorkbenchShell(), Messages.getString("GroupPlanSupportRunnable.continue_explain"), //$NON-NLS-1$
                    Messages.getString("GroupPlanSupportRunnable.error_info"), //$NON-NLS-1$
                    null/* use default toggle message */, false, store, PreferenceConstants.EXECUTE_SQL_ERROR_MODE);
            int result = dlg.getReturnCode();

            // If we are not going to prompt anymore propogate the choice.
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

                store.setValue(PreferenceConstants.EXECUTE_SQL_ERROR_MODE, preferenceValue);
            }
            if (result == IDialogConstants.YES_ID)
            {
                goon = true;
            }
            else
            {
                goon = false;
            }
        }

    }

    private Runnable _postRun;

    /**
     * Constructs a runnable to execute a group of SQL statements.
     * 
     * @param request the plan request
     * @param databaseIdentifier the database identifier
     * @param postRun the runnable which will be invoked at last
     */
    public GroupPlanSupportRunnable(PlanRequest request, DatabaseIdentifier databaseIdentifier, Runnable postRun)
    {
        super(request, databaseIdentifier.getProfileName(), databaseIdentifier.getDBname());
        this._postRun = postRun;
    }

    protected String explainPlan(Statement stmt) throws SQLException
    {
        return null;
    }

    public void run()
    {
        run(null);
    }

    protected void handleEnd(Connection connection, Statement stmt)
    {

    }

    protected IStatus run(IProgressMonitor monitor)
    {

        IJobManager manager = Platform.getJobManager();
        if (monitor == null)
        {
            monitor = manager.createProgressGroup();
        }

        String[] groups = new String[]
        {
            _request.getSql()
        };

        DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(_profileName, _dbName);
        SQLService helper = SQLToolsFacade.getSQLService(databaseIdentifier, "" //$NON-NLS-1$
                + _request.getDatabaseDefinitionId().toString());
        if (helper != null)
        {
            groups = helper.splitSQL(_request.getSql());
        }

        String task = Messages.getString("GroupPlanSupportRunnable.sql_execution_plan"); //$NON-NLS-1$
        if (groups.length > 1)
        {
            task += NLS.bind(Messages.getString("GroupPlanSupportRunnable.groups"), (new Object[] //$NON-NLS-1$
            {
                "" + groups.length //$NON-NLS-1$
            }));
        }
        monitor.beginTask(task, groups.length);
        for (int i = 0; i < groups.length; i++)
        {
            if (groups.length > 1)
            {
                monitor.subTask(NLS.bind(Messages.getString("GroupPlanSupportRunnable.group"), (new Object[] //$NON-NLS-1$
                {
                    "" + i //$NON-NLS-1$
                })));
            }

            PlanSupportRunnable planRunnable = null;
            PlanRequest request = new PlanRequest(groups[i], _request.getDatabaseDefinitionId(),
                    _request.getPlanType(), _request.getMode());
            request.setNoexec(_request.isNoexec());

            // request.setSql(SQLUtil.getSQLWithDefOfAssAndRefAndEvaluations(
            // request.getSql(), databaseIdentifier, _varDecs));
            request.setSql(processVarDecs(request.getSql(), databaseIdentifier, _request.getVarDecs()));

            // Returning null indicates that the user cancelled this job
            if (request.getSql() == null)
            {
                monitor.worked(1);
                continue;
            }

            IPlanService planService = SQLToolsUIFacade.getPlanService(databaseIdentifier);
            if (planService != null)
            {
                planRunnable = planService.createPlanSupportRunnable(request, _profileName, _dbName);
            }

            if (planRunnable != null)
            {
                if(getConnection() != null)
                {
                    planRunnable.setConnection(getConnection());
                }
                planRunnable.setProgressGroup(monitor, 1);
                planRunnable.schedule();
                try
                {
                    // wait until it finishes
                    planRunnable.join();
                }
                catch (InterruptedException e)
                {
                    // TODO:log
                    // _log
                    // .error(
                    // EditorMessages.GroupPlanSupportRunnable_error_interrupted,
                    // e);
                }

                monitor.worked(1);
                // checks if the user cancels the operation
                if (monitor.isCanceled())
                {
                    return Status.CANCEL_STATUS;
                }
                if (EPVFacade.getInstance().getStatus(request) == PlanRequest.FAILED && i < groups.length - 1)
                {
                    IPreferenceStore store = SQLEditorPlugin.getDefault().getPreferenceStore();
                    String errorpm = store.getString(PreferenceConstants.EXECUTE_SQL_ERROR_MODE);
                    if (errorpm == null || errorpm.equals("") || PreferenceConstants.PROMPT_MODE_PROMPT.equals(errorpm)) //$NON-NLS-1$
                    {
                        // prompt user whether to continue
                        ConfirmRunnable run = new ConfirmRunnable();
                        SQLEditorPlugin.getDisplay().syncExec(run);
                        if (!run.goon)
                        {
                            break;
                        }
                    }
                    else if (PreferenceConstants.PROMPT_MODE_ALWAYS.equals(errorpm))
                    {
                        continue;
                    }
                    else
                    {
                        // never
                        break;
                    }
                }
            }
        }
        if (_postRun != null)
        {
            SQLEditorPlugin.getDisplay().syncExec(_postRun);
        }

        return Status.OK_STATUS;
    }

    /**
     * Subclass may override this method to add variable definitions to the sql statement, such that the statements can
     * be explained even if the variable definitions statements are not selected.
     * 
     * @param sql
     * @param databaseIdentifier
     * @param varDecs
     * @return
     */
    protected String processVarDecs(String sql, DatabaseIdentifier databaseIdentifier, Map varDecs)
    {
        return sql;
    }
}
