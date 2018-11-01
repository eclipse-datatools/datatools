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
package org.eclipse.datatools.sqltools.result.internal.ui.viewer;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for result set viewer
 * 
 * @author Dafan Yang
 *  
 */
public class ResultSetContentProvider implements IStructuredContentProvider
{

    /**
     *  
     */
    public ResultSetContentProvider()
    {
        super();
    }

    public Object[] getElements(Object inputElement)
    {
        if (inputElement instanceof IResultSetObject)
        {
            int rowsize = ((IResultSetObject) inputElement).getRowCount();
            Integer[] ret = new Integer[rowsize];
            for (int i = 0; i < ret.length; i++)
            {
                ret[i] = new Integer(i);
            }
            return ret;
        }
        else
        {
            // should not happen
            return new Object[0];
        }
    }

    public void dispose()
    {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
    }
}
