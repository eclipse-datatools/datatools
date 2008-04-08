
/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
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

	private static final String ROOT = "ROOT";         //$NON-NLS-1$
    private static final String ATTRIBUTE_MARKER = "@";  //$NON-NLS-1$

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
			ATreeNode aTreeNode = (ATreeNode) node[i];
			TreeNodeData treeNode = new TreeNodeData( aTreeNode );
			if( aTreeNode.getType( ) == ATreeNode.ELEMENT_TYPE )
			{
				if ( aTreeNode.getParent() != null && ROOT.equals( aTreeNode.getParent( ).getValue( )) )
				{
					treeItem.setImage( TreeNodeDataUtil.getSourceFileImage( ) );
				}
				else if ( aTreeNode.getChildren() == null || aTreeNode.getChildren( ).length == 0 )
				{
					treeItem.setImage( TreeNodeDataUtil.getColumnImage( ) );
				}
				else
				{
					treeItem.setImage( TreeNodeDataUtil.getXmlElementImage( ) );
				}
					
			}

			treeItem.setData( treeNode );
			int type = treeNode.getTreeNode().getType( );
			if ( type == ATreeNode.ATTRIBUTE_TYPE )
				treeItem.setText( ATTRIBUTE_MARKER + treeNode.getTreeNode().getValue( ).toString( ) );
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
