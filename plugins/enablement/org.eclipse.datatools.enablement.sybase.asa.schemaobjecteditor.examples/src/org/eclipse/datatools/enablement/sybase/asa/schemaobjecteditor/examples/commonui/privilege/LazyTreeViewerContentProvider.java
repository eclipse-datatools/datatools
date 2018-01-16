/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;


/**
 * A lazy content provider for a tree viewer which uses <code>FloderNode</code> as the floder on the tree and use
 * <code>LeafNode</code> as the leaf on the tree.
 * 
 * @see FolderNode
 * @see LeafNode
 * 
 * @author renj
 */
public class LazyTreeViewerContentProvider implements ILazyTreeContentProvider
{
    private TreeViewer _viewer;

    public Object getParent(Object element)
    {
        if (element instanceof FolderNode)
        {
            FolderNode node = (FolderNode) element;
            return node.getParent();
        }
        return null;
    }

    public void updateChildCount(Object element, int currentChildCount)
    {
        _viewer.setChildCount(element, ((FolderNode) element).getChildren().size());
    }

    public void updateElement(Object parent, int index)
    {
        if (parent instanceof FolderNode && index < ((FolderNode) parent).getChildren().size())
        {
            Object element = ((FolderNode) parent).getChildren().get(index);
            _viewer.replace(parent, index, element);
            _viewer.setChildCount(element, ((FolderNode) element).getChildren().size());
        }
    }

    public void dispose()
    {

    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
        if (viewer instanceof TreeViewer && _viewer == null)
        {
            _viewer = (TreeViewer) viewer;
        }
    }

}
