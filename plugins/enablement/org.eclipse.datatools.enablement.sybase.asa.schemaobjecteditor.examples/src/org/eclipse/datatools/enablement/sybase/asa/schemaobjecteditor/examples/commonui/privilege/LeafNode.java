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
 * An instance of this class represents a leaf node in a tree.
 * 
 * @author Idull
 */
public class LeafNode extends FolderNode
{
    /**
     * The application data
     */
    private Object  _object;
    private boolean _isDirty;
    
    public LeafNode(String name, Object object)
    {
        super(name);
        this._object = object;
        this._isDirty = false;
    }

    public Object getData()
    {
        return _object;
    }

    public void addChild(FolderNode child)
    {
        // not supported
    }

    public List getChildren()
    {
        // not supported
        return new ArrayList();
    }
    
    public boolean isDirty()
    {
        return _isDirty;
    }
    
    public void markDirty(boolean dirty)
    {
        _isDirty = dirty;
    }
}
