/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.index;

import java.util.List;

import org.eclipse.datatools.sqltools.result.internal.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManagerListener;
import org.eclipse.datatools.sqltools.result.internal.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.internal.model.ResultItem;

/**
 * Listener to add or remove result instance from result history index writer
 * @author Dafan Yang
 */
public class HistoryIndexListener implements IResultManagerListener
{
    public void resultInstanceCreated(IResultInstance instance)
    {
        IResultHistoryIndex index = ResultsViewPlugin.getDefault().getResultHistoryIndex();
        index.addResult(instance);
    }

    public void resultInstanceRemoved(IResultInstance instance)
    {
        IResultHistoryIndex index = ResultsViewPlugin.getDefault().getResultHistoryIndex();
        index.removeResult(instance);
    }

    public void resultInstanceAppended(IResultInstance instance, ResultItem result, int index)
    {
        // Do nothing for now
    }

    public void allResultInstancesRemoved()
    {
        //TODO: Implement method to remove all documents in index
    }

    public void resultInstanceStatusUpdated(IResultInstance instance)
    {
        // Do nothing for now
    }

    public void resultInstanceReset(IResultInstance instance)
    {
        IResultHistoryIndex index = ResultsViewPlugin.getDefault().getResultHistoryIndex();
        index.refreshResult(instance);
    }

    public void parametersShow(IResultInstance instance, List params)
    {
        // Do nothing for now
    }

    public void resultInstancesRemoved(IResultInstance[] instances)
    {
        IResultHistoryIndex index = ResultsViewPlugin.getDefault().getResultHistoryIndex();
        index.removeResults(instances);
    }
}
