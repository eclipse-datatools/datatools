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
import java.util.Iterator;

import org.eclipse.datatools.sqltools.plan.IExecutionPlanDocument;

/**
 * This is the model for tree-structure graphic execution plan. <code>TreePlanDrawer</code> is responsible for drawing
 * a tree-structure graphic plan on a <code>Canvas</code>
 * 
 * @see TreePlanDrawer
 * @author Dafan Yang
 */
public class TreeExecutionPlanDocument implements IExecutionPlanDocument
{
    /* Arbitrary data of this plan document */
    private Object                _data;
    /* Name of this plan document */
    private String                _name;
    private ArrayList             _nodeCount;

    /* The root node of this tree */
    private TreePlanNodeComponent _rootNode;

    /**
     * Constructor
     * 
     * @param rootNode the root node
     * @param name the name for this document
     * @param data the arbitray data
     */
    public TreeExecutionPlanDocument(TreePlanNodeComponent rootNode, String name, Object data)
    {
        _rootNode = rootNode;
        _name = name == null ? "" : name;
        _data = data;
        _nodeCount = new ArrayList();
    }

    /**
     * Returns the data of this plan document
     * 
     * @return the data of this plan document
     */
    public Object getData()
    {
        return _data;
    }

    /**
     * Returns the max number of child nodes at the given depth
     * 
     * @param root the root node
     * @param depth starts from 1
     */
    private void getMaxNodeCount(TreePlanNodeComponent root, int depth)
    {
        int max = root.getChildrenCount();
        if (_nodeCount.size() <= depth)
        {
            _nodeCount.add(new Integer(max));
        }
        else
        {
            _nodeCount.set(depth, new Integer(((Integer) _nodeCount.get(depth)).intValue() + max));
        }
        ArrayList children = root.getChildren();
        Iterator iter = null;
        if (children != null)
        {
            iter = children.iterator();
        }
        for (; iter != null && iter.hasNext();)
        {
            getMaxNodeCount((TreePlanNodeComponent) iter.next(), depth + 1);
        }
    }

    /**
     * Returns the maximum number of child nodes at the same depth
     * 
     * @return the maximum number of child nodes at the same depth
     */
    public int getMaxWidth()
    {
        _nodeCount = new ArrayList();
        _nodeCount.add(new Integer(1));
        getMaxNodeCount(_rootNode, 1);
        int max = 1;
        for (Iterator iter = _nodeCount.iterator(); iter.hasNext();)
        {
            int level = ((Integer) iter.next()).intValue();
            if (level > max)
            {
                max = level;
            }
        }
        return max;
    }

    /**
     * Returns the name of this document
     * 
     * @return the name of this document
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Returns the root node
     * 
     * @return the root node
     */
    public TreePlanNodeComponent getRootNode()
    {
        return _rootNode;
    }

    /**
     * Returns the width at the given depth
     * @param depth the depth
     * @return the withd at the given depth
     */
    public int getWidth(int depth)
    {
        return ((Integer) _nodeCount.get(depth)).intValue();
    }

    /**
     * Sets the data of this plan document
     * 
     * @param data the data
     */
    public void setData(Object data)
    {
        this._data = data;
    }

    /**
     * Sets the name for this document
     * 
     * @param name the name for this document
     */
    public void setName(String name)
    {
        this._name = name;
    }

    /**
     * Sets the root node for this execution plan document
     * 
     * @param rootNode the root node
     */
    public void setRootNode(TreePlanNodeComponent rootNode)
    {
        this._rootNode = rootNode;
    }
}
