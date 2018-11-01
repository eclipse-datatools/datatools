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
 * Use composite pattern to store the tree. Instance of this class renders a node in a tree --- either composite node or
 * a leaf. Consumer can put any data in this node using <code>_data</code> field, meanwhile, some necessary
 * informations are defined:
 * <p>
 * <ul>
 * <li>Name: This will be displayed at the top of this node
 * <li>Tooltip: When the mouse is hovered on this node, the tooltip will be displayed
 * <li>Detail: The detail information will be displayed on the detail panel, it is expected to be a HTML document
 * <li>Label1: If it is not null, it will be displayed using the first label of this node, consumer can set what they need
 * <li>Label2: If it is not null, it will be displayed using the second label of this node, consumer can set what they need
 * <li>Two boolean fields: To indicate if the label should be highlighted or not
 * </ul>
 * 
 * @author Dafan Yang
 */
public abstract class TreePlanNodeComponent
{
    /* The name of this node, it will be displayed on the top of this node */
    private String                _name;
    /* The tooltip of this node */
    private String                _tip;
    /* The detail information of this node, it is expected to be a HTML document */
    private String                _detail;
    /* The data of this node, consumer can put anything */
    private Object                _data;
    /* If it is not null, it will be displayed using the first label of this node, consumer can set what they need */
    private String                _label1;
    /* Used to control if label 1 should be highlighted */
    private boolean               _isLabel1Highlighted;
    /* If it is not null, it will be displayed using the second label of this node, consumer can set what they need */
    private String                _label2;
    /* Used to control if label 2 should be highlighted */
    private boolean               _isLabel2Highlighted;
    /* The parent node of this node, if this node is root node, its parent is null */
    private TreePlanNodeComponent _parent;
    
    
    /**
     * Constructor
     * 
     * @param name name of this node
     * @param tip tooltip of this node
     * @param detail detail information of this node
     * @param data data of this node, consumer can put anything
     * @param label1 first label
     * @param isLabel1Highlighted used to control if label 1 should be highlighted 
     * @param label2 second label
     * @param isLabel2Highlighted used to control if label 2 should be highlighted
     * @param parent parent node of this node
     */
    public TreePlanNodeComponent(String name, String tip, String detail, Object data, String label1,
            boolean isLabel1Highlighted, String label2, boolean isLabel2Highlighted, TreePlanNodeComponent parent)
    {
        super();
        this._name = name == null ? "" : name;
        this._tip = tip == null ? "" : tip;
        this._detail = detail == null ? "" : detail;
        this._data = data;
        this._label1 = label1 == null ? "" : label1;
        this._label2 = label2 == null ? "" : label2;
        this._parent = parent;
        this._isLabel1Highlighted = isLabel1Highlighted;
        this._isLabel2Highlighted = isLabel2Highlighted;
    }

    /**
     * Empty constructor, the consumer should call setXXX methods after constructing the node
     *
     */
    public TreePlanNodeComponent()
    {
        super();
    }


    /**
     * Returns the children of this node, return <code>null</code> if this node is a leaf
     * 
     * @return the children of this node
     */
    public abstract ArrayList getChildren();

    /**
     * Returns the number of children
     * 
     * @return the number of children
     */
    public abstract int getChildrenCount();

    /**
     * Returns the child at the given index (the index is based on 0)
     * 
     * @param index the index
     * @return the child at the given index
     */
    public abstract TreePlanNodeComponent getChild(int index);

    /**
     * Adds child to this node, simply return if this node is a leaf
     * 
     * @param child the child
     */
    public abstract void addChild(TreePlanNodeComponent child);

    /**
     * Returns the detail information of this node
     * 
     * @return the detail information
     */
    public String getDetail()
    {
        return _detail;
    }

    /**
     * Sets the detail information of this node 
     * 
     * @param detail the detail information
     */
    public void setDetail(String detail)
    {
        this._detail = detail;
    }

    /**
     * Returns the name of this node
     * 
     * @return the name of this node
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Sets the name of this node
     * 
     * @param name the name
     */
    public void setName(String name)
    {
        this._name = name;
    }

    /**
     * Returns the parent of this node
     * 
     * @return the parent of this node
     */
    public TreePlanNodeComponent getParent()
    {
        return _parent;
    }

    /**
     * Sets the parent of this node
     * @param parent the new parent
     */
    public void setParent(TreePlanNodeComponent parent)
    {
        this._parent = parent;
    }

    /**
     * Returns the tool tip of this node
     * 
     * @return the tool tip
     */
    public String getToolTip()
    {
        return _tip;
    }

    /**
     * Sets the tool tip of this node
     * 
     * @param tip the tool tip
     */
    public void setToolTip(String tip)
    {
        this._tip = tip;
    }

    /**
     * Returns the data of this node
     * 
     * @return the data of this node
     */
    public Object getData()
    {
        return _data;
    }

    /**
     * Sets the data of this node
     * 
     * @param data the data
     */
    public void setData(Object data)
    {
        this._data = data;
    }

    /**
     * Returns the first label of this node
     * 
     * @return the first label of this node
     */
    public String getLabel1()
    {
        return _label1;
    }

    /**
     * Sets the first label of this node
     * 
     * @param label1 the label
     */
    public void setLabel1(String label1)
    {
        this._label1 = label1;
    }

    /**
     * Returns the second label of this node
     * 
     * @return the second label of this node
     */
    public String getLabel2()
    {
        return _label2;
    }

    /**
     * Sets the second label of this node
     * 
     * @param label2 the label
     */
    public void setLabel2(String label2)
    {
        this._label2 = label2;
    }

    /**
     * Checks if should highlight label 1
     * @return <code>true</code> if label 1 should be highlighted
     */
    public boolean isLabel1Highlighted()
    {
        return _isLabel1Highlighted;
    }

    /**
     * Sets _isLabel1Highlighted
     * @param label1Highlighted the new value
     */
    public void setLabel1Highlighted(boolean label1Highlighted)
    {
        _isLabel1Highlighted = label1Highlighted;
    }
    
    /**
     * Checks if should highlight label 2
     * @return <code>true</code> if label 2 should be highlighted
     */
    public boolean isLabel2Highlighted()
    {
        return _isLabel2Highlighted;
    }

    /**
     * Sets _isLabel2Highlighted
     * @param label2Highlighted the new value
     */
    public void setLabel2Highlighted(boolean label2Highlighted)
    {
        _isLabel2Highlighted = label2Highlighted;
    }
}
