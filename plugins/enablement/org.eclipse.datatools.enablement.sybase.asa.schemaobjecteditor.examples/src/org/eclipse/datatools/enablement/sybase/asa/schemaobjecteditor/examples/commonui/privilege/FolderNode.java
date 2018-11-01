/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance of this class represents a folder node in a tree.
 * 
 * @author Idull
 */
public class FolderNode
{
    private String     _nodeName;
    private List       _children;
    private FolderNode _parent;

    public FolderNode(String name)
    {
        super();
        _nodeName = name;
        _children = new ArrayList(5);
    }

    public String getName()
    {
        return _nodeName;
    }

    public List getChildren()
    {
        return _children;
    }

    public void addChild(FolderNode child)
    {
        _children.add(child);
        child.setParent(this);
    }

    public FolderNode getParent()
    {
        return _parent;
    }

    public void setParent(FolderNode node)
    {
        _parent = node;
    }
}
