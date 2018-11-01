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
package org.eclipse.datatools.sqltools.result.internal.index;

import java.util.List;

import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.core.IResultManagerListener;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;

/**
 * Listener to add or remove result instance from result history index writer
 * @author Dafan Yang
 */
public class HistoryIndexListener implements IResultManagerListener
{
    public void resultInstanceCreated(IResultInstance instance)
    {
        // Only when the instance is finished will we add it for searching
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
        // Do nothing for now (Remove all -> Remove visible items)
    }

    public void resultInstanceStatusUpdated(IResultInstance instance)
    {
        // if current status is FINISHED and it is a parent result
        if(instance.isFinished() && instance.isParentResult())
        {
            IResultHistoryIndex index = ResultsViewPlugin.getDefault().getResultHistoryIndex();
            index.addResult(instance);
        }
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
