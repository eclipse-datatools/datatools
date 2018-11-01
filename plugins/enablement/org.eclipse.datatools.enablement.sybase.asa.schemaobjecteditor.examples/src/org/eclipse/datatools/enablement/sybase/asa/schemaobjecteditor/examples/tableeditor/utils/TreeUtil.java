/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.FolderNode;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.LeafNode;


public class TreeUtil
{
    public static LeafNode searchNode(String name, FolderNode folder)
    {
        if (folder == null)
        {
            return null;
        }
        List children = folder.getChildren();
        if (children == null || children.size() == 0)
        {
            return null;
        }
        Iterator iter = folder.getChildren().iterator();
        while (iter.hasNext())
        {
            Object obj = iter.next();
            if (obj instanceof LeafNode)
            {
                LeafNode leaf = (LeafNode) obj;
                if (leaf.getName() != null && leaf.getName().equals(name))
                {
                    return leaf;
                }
            }
            else if (obj instanceof FolderNode)
            {
                LeafNode leaf = searchNode(name, (FolderNode) obj);
                if (leaf != null)
                {
                    return leaf;
                }
            }
        }
        return null;
    }
}
