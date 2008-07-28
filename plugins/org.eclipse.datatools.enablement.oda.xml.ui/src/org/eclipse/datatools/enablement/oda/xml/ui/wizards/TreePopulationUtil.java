
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
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * 
 */

final class TreePopulationUtil
{

	static final String ATTRIBUTE_MARK = "@"; //$NON-NLS-1$
	static final String ROOT = "ROOT"; //$NON-NLS-1$
	static final String EMPTY_STRING = ""; //$NON-NLS-1$
	static final String PATH_SEPERATOR = "/"; //$NON-NLS-1$
	
    /**
	 * populate tree items
	 * @param tree
	 * @param node
	 * @throws OdaException
	 */
	static void populateTreeItems( Object tree, Object[] node,
			String xPathExpression, boolean includeAttr ) throws OdaException
	{
		for ( int i = 0; i < node.length; i++ )
		{
			if ( !includeAttr )
			{
				if ( ( (ATreeNode) node[i] ).getType( ) == ATreeNode.ATTRIBUTE_TYPE )
					continue;
			}
			TreeItem treeItem;
			if ( tree instanceof TreeItem )
				treeItem = new TreeItem( (TreeItem) tree, 0 );
			else
				treeItem = new TreeItem( (Tree) tree, 0 );
			ATreeNode aTreeNode = (ATreeNode) node[i];
			TreeNodeData treeNode = new TreeNodeData( aTreeNode );
			if ( aTreeNode.getType( ) == ATreeNode.ELEMENT_TYPE )
			{
				if ( aTreeNode.getParent( ) != null
						&& ROOT.equals( aTreeNode.getParent( ).getValue( ) ) )
				{
					treeItem.setImage( TreeNodeDataUtil.getSourceFileImage( ) );
				}
				else if ( aTreeNode.getChildren( ) == null
						|| aTreeNode.getChildren( ).length == 0 )
				{
					treeItem.setImage( TreeNodeDataUtil.getColumnImage( ) );
				}
				else
				{
					treeItem.setImage( TreeNodeDataUtil.getXmlElementImage( ) );
				}

			}

			treeItem.setData( treeNode );
			int type = treeNode.getTreeNode( ).getType( );
			if ( type == ATreeNode.ATTRIBUTE_TYPE )
				treeItem.setText( ATTRIBUTE_MARK
						+ treeNode.getTreeNode( ).getValue( ).toString( ) );
			else
			{
				treeItem.setText( treeNode.getTreeNode( )
						.getValue( )
						.toString( ) );

				String populateString = XPathPopulationUtil.populateColumnPath( getRootPathWithOutFilter( xPathExpression ),
						generateXpathFromATreeNode( aTreeNode ) );
				if ( TreePopulationUtil.EMPTY_STRING.equals( populateString ) )
				{
					FontData fontData = new FontData( TreePopulationUtil.EMPTY_STRING,
							8,
							SWT.BOLD );
					treeItem.setFont( new Font( null, fontData ) );
				}
			}
			if ( treeNode.getTreeNode( ).getChildren( ).length > 0 )
				new TreeItem( treeItem, 0 );
		}
	}
	
	/**
	 * Return the tailored root path without filter definition.
	 * @return
	 */
	static String getRootPathWithOutFilter( String xPathExpression )
	{
		return xPathExpression.replaceAll( "\\Q[\\E.*\\Q]\\E", EMPTY_STRING ); //$NON-NLS-1$
	}
	
	/**
	 * This method is used to generate the XPath expression from an ATreeNode object.
	 * 
	 * @param ATreeNode aTreeNode
	 * @return
	 */
	static String generateXpathFromATreeNode( ATreeNode aTreeNode )
	{
		String columnPath = (String) aTreeNode.getValue( );
		if ( aTreeNode.getType( ) == ATreeNode.ATTRIBUTE_TYPE )
		{
			columnPath = ATTRIBUTE_MARK + columnPath;
		}

		ATreeNode parent = aTreeNode.getParent( );
		while ( parent != null )
		{
			if ( parent.getType( ) == ATreeNode.ELEMENT_TYPE
					&& ( columnPath != null && columnPath.trim( ).length( ) > 0 ) )
				columnPath = parent.getValue( ) + PATH_SEPERATOR + columnPath;
			else if ( parent.getType( ) == ATreeNode.ATTRIBUTE_TYPE )
				columnPath = columnPath
						+ PATH_SEPERATOR + ATTRIBUTE_MARK + parent.getValue( );
			parent = parent.getParent( );
		}
		if ( !columnPath.startsWith( PATH_SEPERATOR ) )
		{
			columnPath = PATH_SEPERATOR + columnPath;
		}
		return columnPath;
	}
	
}	

class TreeNodeData
{

	//
	private ATreeNode node;
	private boolean hasBeenExpandedOnce, matched;

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

	public boolean getXPathStatus( )
	{
		return this.matched;
	}
	
	public void setXPathStatus( boolean matched )
	{
		this.matched = matched;
	}
	
	public ATreeNode getTreeNode( )
	{
		return this.node;
	}
}
