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
 * Leaf node.
 * 
 * @author Dafan Yang
 */
public class TreePlanNodeLeaf extends TreePlanNodeComponent
{
    public TreePlanNodeLeaf(String name, String tip, String detail, Object data, String label1,
            boolean isLabel1Highlighted, String label2, boolean isLabel2Highlighted, TreePlanNodeComponent parent)
    {
        super(name, tip, detail, data, label1, isLabel1Highlighted, label2, isLabel2Highlighted, parent);
    }

    /**
     * Empty constructor
     *
     */
    public TreePlanNodeLeaf()
    {
        super();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.treeplan.TreePlanNodeComponent#getChildren()
     */
    public ArrayList getChildren()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.treeplan.TreePlanNodeComponent#getChildrenCount()
     */
    public int getChildrenCount()
    {
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.treeplan.TreePlanNodeComponent#getChild(int)
     */
    public TreePlanNodeComponent getChild(int index)
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.treeplan.TreePlanNodeComponent#addChild(org.eclipse.datatools.sqltools.plan.treeplan.TreePlanNodeComponent)
     */
    public void addChild(TreePlanNodeComponent child)
    {
        // do nothing
    }
}
