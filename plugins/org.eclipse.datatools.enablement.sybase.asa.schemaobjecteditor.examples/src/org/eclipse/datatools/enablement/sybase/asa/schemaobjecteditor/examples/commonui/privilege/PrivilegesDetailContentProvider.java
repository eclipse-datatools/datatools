/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * Content provider for permission detail table
 * 
 * @author Idull
 */
public class PrivilegesDetailContentProvider implements ITreeContentProvider
{
    public Object[] getChildren(Object parentElement)
    {
        if (parentElement instanceof FolderNode)
        {
            FolderNode folder = (FolderNode) parentElement;
            return folder.getChildren().toArray();
        }
        return null;
    }

    public Object getParent(Object element)
    {
        if (element instanceof FolderNode)
        {
            FolderNode node = (FolderNode) element;
            return node.getParent();
        }
        return null;
    }

    public boolean hasChildren(Object element)
    {
        if (element instanceof FolderNode)
        {
            FolderNode node = (FolderNode) element;
            return node.getChildren().size() > 0;
        }
        return false;
    }

    public Object[] getElements(Object inputElement)
    {
        return getChildren(inputElement);
    }

    public void dispose()
    {

    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {

    }
}
