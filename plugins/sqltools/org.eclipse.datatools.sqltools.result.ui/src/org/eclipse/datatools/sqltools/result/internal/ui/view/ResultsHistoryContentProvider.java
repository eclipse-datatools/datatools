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
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import java.util.List;

import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * The content provider for the results history section
 * @author Dafan Yang
 */
public class ResultsHistoryContentProvider implements ITreeContentProvider
{

    public Object[] getChildren(Object parentElement)
    {
        if (parentElement instanceof IResultInstance)
        {
            IResultInstance parentResult = (IResultInstance) parentElement;
            List subResults = parentResult.getSubResults();
            return subResults.toArray(new IResultInstance[subResults.size()]);
        }
        return null;
    }

    public Object getParent(Object element)
    {
        if (element instanceof IResultInstance)
        {
            IResultInstance result = (IResultInstance) element;
            return result.getParentResult();
        }
        return null;
    }

    public boolean hasChildren(Object element)
    {
        if (element instanceof IResultInstance)
        {
            IResultInstance parentResult = (IResultInstance) element;
            List subResults = parentResult.getSubResults();
            return subResults.size() > 0 ? true : false;
        }
        return false;
    }

    public Object[] getElements(Object inputElement)
    {
        if (inputElement instanceof IResultInstance[])
        {
            return (IResultInstance[]) inputElement;
        }
        else
        {
            return new Object[0]; // should not happen
        }
    }

    public void dispose()
    {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
    }
}
