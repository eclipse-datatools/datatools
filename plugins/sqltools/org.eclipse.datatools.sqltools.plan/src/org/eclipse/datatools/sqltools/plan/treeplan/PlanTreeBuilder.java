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

import java.util.Iterator;
import java.util.List;

/**
 * The plan tree builder is useful for constructing a execution plan tree
 * 
 * @author Dafan Yang
 */
public class PlanTreeBuilder
{
    TreePlanNodeComponent _rootNode;
    TreePlanNodeComponent _currentNode;

    public PlanTreeBuilder()
    {
        _rootNode = new TreePlanNodeComposite();
        _currentNode = _rootNode;
    }
    
    public PlanTreeBuilder(String name, String tip, String detail, Object data, String label1,
            boolean isLabel1Highlighted, String label2, boolean isLabel2Highlighted)
    {
        _rootNode = new TreePlanNodeComposite(name, tip, detail, data, label1, isLabel1Highlighted, label2,
                isLabel2Highlighted, null);
        _currentNode = _rootNode;
    }

    public TreePlanNodeComponent getCurrentNode()
    {
        return _currentNode;
    }

    public TreePlanNodeComponent getRoot()
    {
        return _rootNode;
    }

    /**
     * Adds an child node with the specified information to current node, and the newly added node will become the current
     * node
     * 
     * @param name name of the node
     * @param tip tooltip of the node
     * @param detail detail information of the node
     * @param data the data object
     * @param label1 label 1
     * @param isLabel1Highlighted is label 1 highlighted
     * @param label2 label 2
     * @param isLabel2Highlighted is label 2 highlighted
     * @return <code>true</code> if a new child is successfully added
     */
    public boolean addChild(String name, String tip, String detail, Object data, String label1,
            boolean isLabel1Highlighted, String label2, boolean isLabel2Highlighted)
    {
        if(_currentNode == null)
        {
            return false;
        }
        TreePlanNodeComposite node = new TreePlanNodeComposite(name, tip, detail, data, label1, isLabel1Highlighted,
                label2, isLabel2Highlighted, _currentNode);
        _currentNode.addChild(node);
        _currentNode = node;
        return true;
    }

    /**
     * Adds an empty child node to current node, and the newly added node will become the current node
     * 
     * @return <code>true</code> if a new child is successfully added
     */
    public boolean addChild()
    {
        if(_currentNode == null)
        {
            return false;
        }
        TreePlanNodeComposite node = new TreePlanNodeComposite();
        _currentNode.addChild(node);
        node.setParent(_currentNode);
        _currentNode = node;
        return true;
    }

    /**
     * Adds a sibling node with specified information for current node, and the newly added node will become the current
     * node
     * 
     * @param name name of the node
     * @param tip tooltip of the node
     * @param detail detail information of the node
     * @param data the data object
     * @param label1 label 1
     * @param isLabel1Highlighted is label 1 highlighted
     * @param label2 label 2
     * @param isLabel2Highlighted is label 2 highlighted
     * @return <code>true</code> if a new child is successfully added
     */
    public boolean addSibling(String name, String tip, String detail, Object data, String label1,
            boolean isLabel1Highlighted, String label2, boolean isLabel2Highlighted)
    {
        if(_currentNode == null)
        {
            return false;
        }
        if (_currentNode.getParent() == null)
        {
            return false;
        }
        TreePlanNodeComposite node = new TreePlanNodeComposite(name, tip, detail, data, label1, isLabel1Highlighted,
                label2, isLabel2Highlighted, _currentNode.getParent());
        _currentNode.getParent().addChild(node);
        _currentNode = node;
        return true;
    }

    /**
     * Adds a sibling node for current node, and the newly added node will become the current node
     * 
     * @return <code>true</code> if a new child is successfully added
     */
    public boolean addSibling()
    {
        if(_currentNode == null)
        {
            return false;
        }
        if (_currentNode.getParent() == null)
        {
            return false;
        }
        TreePlanNodeComposite node = new TreePlanNodeComposite();
        node.setParent(_currentNode.getParent());
        _currentNode.getParent().addChild(node);
        _currentNode = node;
        return true;
    }

    /**
     * Adds a child node with specified information to the specified node, and the newly added node will become the
     * current node
     * 
     * @param name name of the node
     * @param tip tooltip of the node
     * @param detail detail information of the node
     * @param data the data object
     * @param label1 label 1
     * @param isLabel1Highlighted is label 1 highlighted
     * @param label2 label 2
     * @param isLabel2Highlighted is label 2 highlighted
     * @return <code>true</code> if a new child is successfully added
     */
    public boolean addTo(TreePlanNodeComponent parent, String name, String tip, String detail, Object data,
            String label1, boolean isLabel1Highlighted, String label2, boolean isLabel2Highlighted)
    {
        if (parent == null)
        {
            return false;
        }
        TreePlanNodeComposite node = new TreePlanNodeComposite(name, tip, detail, data, label1, isLabel1Highlighted,
                label2, isLabel2Highlighted, parent);
        parent.addChild(node);
        _currentNode = node;
        return true;
    }

    /**
     * Adds a child node to the specified node, and the newly added node will become the current node
     * 
     * @return <code>true</code> if a new child is successfully added
     */
    public boolean addTo(TreePlanNodeComponent parent)
    {
        if (parent == null)
        {
            return false;
        }
        TreePlanNodeComposite node = new TreePlanNodeComposite();
        parent.addChild(node);
        node.setParent(parent);
        _currentNode = node;
        return true;
    }

    /**
     * Finds all the nodes with the given name, this is useful if a node needs to be added to a specific parent node
     * (Not the current node)
     * 
     * @param name name of the node
     * @param nodes the search result will be stored in the list
     */
    public void findNodes(String name, List nodes)
    {
        findNodes(name, nodes, _rootNode);
    }

    protected void findNodes(String name, List nodes, TreePlanNodeComponent node)
    {
        if (name == null || nodes == null)
        {
            return;
        }
        if (node != null && node.getName() != null && node.getName().equals(name))
        {
            nodes.add(node);
        }
        if (node.getChildren() == null)
        {
            return;
        }
        Iterator iter = node.getChildren().iterator();
        while (iter.hasNext())
        {
            TreePlanNodeComponent n = (TreePlanNodeComponent) iter.next();
            findNodes(name, nodes, n);
        }
    }
}
