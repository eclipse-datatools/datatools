/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.constraints;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.FolderNode;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.LeafNode;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditorModelListener;
import org.eclipse.emf.common.notify.Notification;


/**
 * Given a table, this class can generate a tree (The content) for the constraints tree viewer.
 * <p>
 * 
 * <pre>
 *   root
 *   |-- Constraint
 *        |
 *        |---Primary Key
 *        |---Unique Constraints
 *        |---Foreign Keys
 *        |---Check Constraints
 * </pre>
 * 
 * @author Idull
 */
public class SQLConstraintsTreeViewerInput implements ISchemaObjectEditorModelListener
{
    public static final String CONSTRAINTS_NODE             = Messages.SQLConstraintsTreeViewerInput_constraints;
    public static final String PRIMAEY_KEY_NODE             = Messages.SQLConstraintsTreeViewerInput_pk;
    public static final String FOREIGN_KEY_NODE             = Messages.SQLConstraintsTreeViewerInput_fk;
    public static final String UNIQUE_CONSTRAINT_NODE       = Messages.SQLConstraintsTreeViewerInput_unique_constraint;
    public static final String CHECK_CONSTRAINT_NODE        = Messages.SQLConstraintsTreeViewerInput_ck;
    // This will be used as the constraint type, not a node
    public static final String COLUMN_CHECK_CONSTRAINT_NODE = Messages.SQLConstraintsTreeViewerInput_column_ck;

    private BaseTable          _table;
    private FolderNode         _constraintsFolderNode;
    private FolderNode         _uniqueFolderNode;
    private FolderNode         _pkFolderNode;
    private FolderNode         _fkFolderNode;
    private FolderNode         _ckFolderNode;
    private FolderNode         _root;
    private boolean            _isGenerated = false;
    /**
     * Remember the dirty status of the constraints on the tree
     */
    private List               _dirtyList;

    public SQLConstraintsTreeViewerInput(BaseTable table)
    {
        _table = table;
        generateInput();
    }

    /**
     * This should be called when the constraints of the table change.<br>
     * ATTN: The folder nodes will be kept, all the child nodes will be re-generated.
     * @param table
     */
    public void resetInput(BaseTable table)
    {
        _table = table;
        generateInput();
    }
    
    /**
     * Generates the constraints tree and return the root node.<br>
     * ATTN: When this method is called for the second time, should remember the dirty status of the constraints on the
     * tree, and re-set dirty status for the newly generated input
     * 
     * @return
     */
    private void generateInput()
    {
        _dirtyList = new ArrayList();
        
        if(_isGenerated)
        {
            addDirtyConstraint(_pkFolderNode, _dirtyList);
            addDirtyConstraint(_uniqueFolderNode, _dirtyList);
            addDirtyConstraint(_fkFolderNode, _dirtyList);
            addDirtyConstraint(_ckFolderNode, _dirtyList);
        }
        if (!_isGenerated)
        {
            _root = new FolderNode("root");
            _constraintsFolderNode = new FolderNode(CONSTRAINTS_NODE);
            _root.addChild(_constraintsFolderNode);
            _pkFolderNode = new FolderNode(PRIMAEY_KEY_NODE);

            _constraintsFolderNode.addChild(_pkFolderNode);

            _uniqueFolderNode = new FolderNode(UNIQUE_CONSTRAINT_NODE);

            _constraintsFolderNode.addChild(_uniqueFolderNode);

            _fkFolderNode = new FolderNode(FOREIGN_KEY_NODE);
            _constraintsFolderNode.addChild(_fkFolderNode);

            _ckFolderNode = new FolderNode(Messages.SQLConstraintsTreeViewerInput_ck);
            _constraintsFolderNode.addChild(_ckFolderNode);
        }

        _isGenerated = true;
        refresh();
    }

    public FolderNode getRoot()
    {
        return _root;
    }

    /**
     * Returns the constraints node
     * 
     * @return
     */
    public FolderNode getConstraintsNode()
    {
        return _constraintsFolderNode;
    }

    /**
     * Returns the pk folder node
     * 
     * @return
     */
    public FolderNode getPKFolderNode()
    {
        return _pkFolderNode;
    }

    /**
     * Returns the fk folder node
     * 
     * @return
     */
    public FolderNode getFKFolderNode()
    {
        return _fkFolderNode;
    }

    /**
     * Returns the unique constraints folder node
     * 
     * @return
     */
    public FolderNode getUniqueFolderNode()
    {
        return _uniqueFolderNode;
    }

    /**
     * Returns the check constraints folder node
     * 
     * @return
     */
    public FolderNode getCKFolderNode()
    {
        return _ckFolderNode;
    }

    /**
     * Searches the tree to find the leaf node
     * 
     * @param constraint
     * @return
     */
    public LeafNode getNode(Constraint constraint)
    {
        Iterator iter = _pkFolderNode.getChildren().iterator();
        while (iter.hasNext())
        {
            LeafNode leaf = (LeafNode) iter.next();
            if (leaf.getData() == constraint)
            {
                return leaf;
            }
        }

        iter = _fkFolderNode.getChildren().iterator();
        while (iter.hasNext())
        {
            LeafNode leaf = (LeafNode) iter.next();
            if (leaf.getData() == constraint)
            {
                return leaf;
            }
        }

        iter = _uniqueFolderNode.getChildren().iterator();
        while (iter.hasNext())
        {
            LeafNode leaf = (LeafNode) iter.next();
            if (leaf.getData() == constraint)
            {
                return leaf;
            }
        }

        iter = _ckFolderNode.getChildren().iterator();
        while (iter.hasNext())
        {
            LeafNode leaf = (LeafNode) iter.next();
            if (leaf.getData() == constraint)
            {
                return leaf;
            }
        }
        return null;
    }

    private void addDirtyConstraint(FolderNode folder, List dirtyList)
    {
        Iterator iter = folder.getChildren().iterator();
        while(iter.hasNext())
        {
            LeafNode node = (LeafNode)iter.next();
            if(node.isDirty())
            {
                dirtyList.add(node.getData());
            }
        }
    }
    private void refresh()
    {        
        _uniqueFolderNode.getChildren().clear();
        _pkFolderNode.getChildren().clear();
        _fkFolderNode.getChildren().clear();
        _ckFolderNode.getChildren().clear();
        
        if (_table.getPrimaryKey() != null)
        {
            _pkFolderNode.addChild(new LeafNode(_table.getPrimaryKey().getName(), _table.getPrimaryKey()));
        }

        Iterator iter = _table.getUniqueConstraints().iterator();
        while (iter.hasNext())
        {
            UniqueConstraint unique = (UniqueConstraint) iter.next();
            
            // Exclude primary key
            if (!(unique instanceof PrimaryKey))
            {
                LeafNode node = new LeafNode(unique.getName(), unique);
                if(_dirtyList.contains(unique))
                {
                    node.markDirty(true);
                }
                _uniqueFolderNode.addChild(node);
            }
        }

        iter = _table.getForeignKeys().iterator();
        while (iter.hasNext())
        {
            ForeignKey fk = (ForeignKey) iter.next();
            LeafNode node = new LeafNode(fk.getName(), fk);
            if(_dirtyList.contains(fk))
            {
                node.markDirty(true);
            }
            _fkFolderNode.addChild(node);
        }

        /**
         * Collect all check constraint
         */
        List ckConstraints = new ArrayList();
        iter = _table.getConstraints().iterator();
        while (iter.hasNext())
        {
            Object obj = iter.next();
            if (obj instanceof CheckConstraint)
            {
                ckConstraints.add(obj);
            }
        }

        iter = ckConstraints.iterator();
        while (iter.hasNext())
        {
            CheckConstraint ck = (CheckConstraint) iter.next();
            LeafNode node = new LeafNode(ck.getName(), ck);
            if(_dirtyList.contains(ck))
            {
                node.markDirty(true);
            }
            _ckFolderNode.addChild(node);
        }
    }

    /**
     * Will be notified when constraint is added or removed
     */
    public void notifyChanged(Notification msg)
    {
        if ((msg.getNotifier() instanceof BaseTable)
                && (msg.getFeatureID(BaseTable.class) == SQLTablesPackage.eINSTANCE.BASE_TABLE__CONSTRAINTS))
        {
            //generateInput();
        }
    }
}
