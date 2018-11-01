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
package org.eclipse.datatools.sqltools.plan.treeplan;

import java.util.ArrayList;

/**
 * The composite node
 * 
 * @author Dafan Yang
 */
public class TreePlanNodeComposite extends TreePlanNodeComponent
{
    private ArrayList _children;           

    public TreePlanNodeComposite(String name, String tip, String detail, Object data, String label1,
            boolean isLabel1Highlighted, String label2, boolean isLabel2Highlighted, TreePlanNodeComponent parent)
    {
        super(name, tip, detail, data, label1, isLabel1Highlighted, label2, isLabel2Highlighted, parent);
        _children = new ArrayList();
    }

    /**
     * Empty constructor
     *
     */
    public TreePlanNodeComposite()
    {
        super();
        _children = new ArrayList();
    }


    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.treeplan.TreePlanNodeComponent#getChildren()
     */
    public ArrayList getChildren()
    {
        return _children;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.treeplan.TreePlanNodeComponent#getChildrenCount()
     */
    public int getChildrenCount()
    {
        return _children.size();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.treeplan.TreePlanNodeComponent#getChild(int)
     */
    public TreePlanNodeComponent getChild(int index)
    {
        return (TreePlanNodeComponent)_children.get(index);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.treeplan.TreePlanNodeComponent#addChild(org.eclipse.datatools.sqltools.plan.treeplan.TreePlanNodeComponent)
     */
    public void addChild(TreePlanNodeComponent child)
    {
        _children.add(child);
        child.setParent(this);
    }
}
