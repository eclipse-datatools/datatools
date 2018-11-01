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
package org.eclipse.datatools.sqltools.routineeditor.ui.launching;

import java.sql.SQLException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author lihuang
 */
public class LaunchingJob extends Job 
{

    private ProcIdentifier _procIdentifier ;

    private String           _mode;


    public LaunchingJob(ProcIdentifier identifier, String mode)
    {
        super(Messages.LaunchingJob_name);

        setUser(true);

        _procIdentifier = identifier;

        _mode = mode;
    }

    protected IStatus run(IProgressMonitor monitor)
    {

        if (monitor == null)
        {
            monitor = new NullProgressMonitor();
        }

        monitor.beginTask(Messages.LaunchingJob_name, IProgressMonitor.UNKNOWN);

        String procName = _procIdentifier.getProcName();
        if (_mode.equals(ILaunchManager.RUN_MODE))
        {
            monitor.subTask(NLS.bind(Messages.LaunchingJob_subTaskRun, procName));
        }
        else if (_mode.equals(ILaunchManager.DEBUG_MODE))
        {
            monitor.subTask(NLS.bind(Messages.LaunchingJob_subTaskDebug, procName));
        }

        try
        {
            SPLaunchShortcut.launch(_procIdentifier, _mode);
        }
        catch (NoSuchProfileException e)
        {
            RoutineEditorUIActivator.getDefault().log(Messages.LaunchingJob_runError, e);
        }
        catch (CoreException e)
        {
            RoutineEditorUIActivator.getDefault().log(Messages.LaunchingJob_runError, e);
        }
        catch (SQLException e)
        {
            RoutineEditorUIActivator.getDefault().log(Messages.LaunchingJob_runError, e);
        }

        monitor.worked(IProgressMonitor.UNKNOWN);
        if (monitor.isCanceled())
        {
            return Status.CANCEL_STATUS;
        }
        return Status.OK_STATUS;
    }
}
