/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;

/**
 * To refresh the edit model and re-display them in the schema object editor. It will delegate to
 * <code>ISchemaObjectEditorHandler</code> to do the real job.
 * 
 * @author Idull
 */
public class RefreshEditModelJob extends Job
{
    ISchemaObjectEditorHandler _handler;

    public RefreshEditModelJob(String name, ISchemaObjectEditorHandler handler)
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
        monitor.beginTask("", monitor.UNKNOWN);
        _handler.refreshFromDB(monitor);
        monitor.done();
        return Status.OK_STATUS;
    }
}
