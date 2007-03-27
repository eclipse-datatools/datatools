
/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * 
 */

final class TreePopulationUtil
{

	/**
	 * populate tree items
	 * @param tree
	 * @param node
	 * @throws OdaException 
	 */
	static void populateTreeItems( Object tree, Object[] node, boolean includeAttr ) throws OdaException
	{
		for ( int i = 0; i < node.length; i++ )
		{
			if ( !includeAttr )
			{
				if (((ATreeNode) node[i]).getType( ) == ATreeNode.ATTRIBUTE_TYPE)
					continue;
			}
			TreeItem treeItem;
			if ( tree instanceof TreeItem )
		    	treeItem = new TreeItem( (TreeItem)tree, 0 );
			else
				treeItem = new TreeItem((Tree)tree,0);
			TreeNodeData treeNode = new TreeNodeData((ATreeNode) node[i]);

			treeItem.setData( treeNode );
			int type = treeNode.getTreeNode().getType( );
			if ( type == ATreeNode.ATTRIBUTE_TYPE )
				treeItem.setText( "@" + treeNode.getTreeNode().getValue( ).toString( ) );
			else
				treeItem.setText( treeNode.getTreeNode().getValue( ).toString( ) );
		
			if( treeNode.getTreeNode( ).getChildren( ).length > 0 )
				new TreeItem( treeItem, 0);
		}
	}
}	

class TreeNodeData
{

	//
	private ATreeNode node;
	private boolean hasBeenExpandedOnce;

	/**
	 * 
	 * @param node
	 */
	public TreeNodeData( ATreeNode node )
	{
		this.node = node;
		this.hasBeenExpandedOnce = false;
	}

	/**
	 * 
	 */
	public void setHasBeenExpandedOnce( )
	{
		this.hasBeenExpandedOnce = true;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasBeenExpandedOnce( )
	{
		return this.hasBeenExpandedOnce;
	}

	public ATreeNode getTreeNode( )
	{
		return this.node;
	}
}
