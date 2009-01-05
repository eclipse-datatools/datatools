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
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.ExceptionHandler;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * XML Tree viewer component, including two buttons in btnComposite, this viewer
 * is to be used in row/column mapping page.
 * 
 */
class XMLTreeViewer
{
	private Button btnSingleAdd;
	private Button btnMultiAdd;
	private TreeViewer treeViewer;
	private Composite btnComposite;

	
	XMLTreeViewer( Composite parent, boolean supportMultiSelection )
	{
		FormData data = new FormData( );

		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 47, -5 );
		data.bottom = new FormAttachment( 100, -5 );
		
		Group treeGroup = new Group( parent, SWT.NONE );
		FillLayout layout = new FillLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		treeGroup.setLayout( layout );
		treeGroup.setLayoutData( data );
		if ( supportMultiSelection )
		{
			treeViewer = new TreeViewer( treeGroup, SWT.MULTI
					| SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.VIRTUAL );
		}
		else
		{
			treeViewer = new TreeViewer( treeGroup, SWT.SINGLE
					| SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.VIRTUAL );
		}

		data = new FormData( );
		data.left = new FormAttachment( treeGroup, 5 );
		data.bottom = new FormAttachment( 50 );
		data.width = 28;

		btnComposite = new Composite( parent, SWT.NONE );
		btnComposite.setLayoutData( data );
		FillLayout btnLayout = new FillLayout( SWT.VERTICAL );
		btnLayout.spacing = 5;
		btnComposite.setLayout( btnLayout );

		btnSingleAdd = new Button( btnComposite, SWT.NONE );
		btnSingleAdd.setText( ">" ); //$NON-NLS-1$
		btnSingleAdd.setEnabled( false );

		btnMultiAdd = new Button( btnComposite, SWT.NONE );
		btnMultiAdd.setText( ">>" ); //$NON-NLS-1$
		btnMultiAdd.setToolTipText( Messages.getString( "ColumnMappingPage.AddAllButton.tooltip" ) ); //$NON-NLS-1$
		btnMultiAdd.setEnabled( true );
		
		if( !supportMultiSelection )
		{
			btnMultiAdd.setVisible( false );
		}
		treeGroup.setText( Messages.getString( "xPathChoosePage.messages.xmlStructure" ) ); //$NON-NLS-1$
	}

	void populateTree( ATreeNode treeNode, String xPathExpression, boolean includeAttr, boolean needExpand ) throws OdaException
	{
		final String xPathExpr = xPathExpression;
		final boolean includeAttribute = includeAttr;

		treeViewer.getTree( ).addListener( SWT.Expand, new Listener( ) {

			public void handleEvent( Event event )
			{
				TreeItem currentItem = (TreeItem) event.item;

				if ( ( (TreeNodeData) currentItem.getData( ) ).hasBeenExpandedOnce( ) )
					return;

				( (TreeNodeData) currentItem.getData( ) ).setHasBeenExpandedOnce( );
				currentItem.removeAll( );
				try
				{
					if ( ( ( (TreeNodeData) currentItem.getData( ) ).getTreeNode( ) ).getChildren( ) != null
							&& ( (TreeNodeData) currentItem.getData( ) ).getTreeNode( )
									.getChildren( ).length > 0 )
						TreePopulationUtil.populateTreeItems( currentItem,
								( (TreeNodeData) currentItem.getData( ) ).getTreeNode( )
										.getChildren( ),
										xPathExpr,
								includeAttribute );
				}
				catch ( OdaException e )
				{
					ExceptionHandler.showException( null,
							Messages.getString( "error.label" ), //$NON-NLS-1$
							e.getMessage( ),
							e );
				}

			}
		} );
		
		Object[] childs = treeNode.getChildren( );
		if ( needExpand )
		{
			populateTreeItems( treeViewer.getTree( ),
					childs,
					0, xPathExpression,
					includeAttribute );
		}
		else
		{
			TreePopulationUtil.populateTreeItems( treeViewer.getTree( ),
					childs,
					xPathExpr,
					includeAttribute );
		}
	}
	
	Button getSingleButton( )
	{
		return this.btnSingleAdd;
	}
	
	Button getMultiButton( )
	{
		return this.btnMultiAdd;
	}
	
	Tree getTree( )
	{
		return this.treeViewer.getTree( );
	}
	
	Composite getBtnComposite( )
	{
		return btnComposite;
	}
	
	/**
	 * 
	 * @param tree
	 * @param node
	 * @throws OdaException
	 */
	private void populateTreeItems( Object tree, Object[] node, int level,
			String xPathExpression, boolean includeAttribute )
			throws OdaException
	{
		level++;

		TreeNodeData[] children = new TreeNodeData[node.length];
		TreeItem[] treeItems = new TreeItem[node.length];

		boolean findXPathNode = false;
		for ( int i = 0; i < children.length; i++ )
		{
			ATreeNode treeNode = (ATreeNode) node[i];
			children[i] = new TreeNodeData( treeNode );
			int type = treeNode.getType( );

			switch ( type )
			{
				case ATreeNode.ATTRIBUTE_TYPE :
				{
					if ( includeAttribute )
					{
						if ( tree instanceof Tree )
						{
							treeItems[i] = new TreeItem( (Tree) tree, 0 );
						}
						else
						{
							treeItems[i] = new TreeItem( (TreeItem) tree, 0 );
						}
						treeItems[i].setData( children[i] );
						treeItems[i].setImage( TreeNodeDataUtil.getColumnImage( ) );
						treeItems[i].setText( TreePopulationUtil.ATTRIBUTE_MARK
								+ treeNode.getValue( ).toString( ) );
					}
					else
					{
						continue;
					}
					break;
				}
				case ATreeNode.ELEMENT_TYPE:
				{
					if ( tree instanceof Tree )
					{
						treeItems[i] = new TreeItem( (Tree) tree, 0 );
					}
					else
					{
						treeItems[i] = new TreeItem( (TreeItem) tree, 0 );
					}
					treeItems[i].setData( children[i] );
					if ( treeNode.getParent( ) != null
							&& TreePopulationUtil.ROOT.equals( treeNode.getParent( ).getValue( ) ) )
					{
						treeItems[i].setImage( TreeNodeDataUtil.getSourceFileImage( ) );
					}
					else if ( treeNode.getChildren( ) == null
							|| treeNode.getChildren( ).length == 0 )
					{
						treeItems[i].setImage( TreeNodeDataUtil.getColumnImage( ) );
					}
					else
					{
						treeItems[i].setImage( TreeNodeDataUtil.getXmlElementImage( ) );
					}
					treeItems[i].setText( treeNode.getValue( ).toString( ) );
					break;
				}
				default :
				{
					if ( tree instanceof Tree )
					{
						treeItems[i] = new TreeItem( (Tree) tree, 0 );
					}
					else
					{
						treeItems[i] = new TreeItem( (TreeItem) tree, 0 );
					}
					treeItems[i].setData( children[i] );
					treeItems[i].setText( treeNode.getValue( ).toString( ) );
				}
			}
			ATreeNode aTreeNode = ( (TreeNodeData) treeItems[i].getData( ) ).getTreeNode( );
			String populateString = XPathPopulationUtil.populateColumnPath( TreePopulationUtil.getRootPathWithOutFilter( xPathExpression ),
					TreePopulationUtil.generateXpathFromATreeNode( aTreeNode ) );
			if ( populateString != null )
			{
				if ( populateString.equals( TreePopulationUtil.EMPTY_STRING ) )
				{
					FontData fontData = new FontData( TreePopulationUtil.EMPTY_STRING, 8, SWT.BOLD );
					treeItems[i].setFont( new Font( null, fontData ) );

					treeViewer.getTree( ).setSelection( new TreeItem[]{
						treeItems[i]
					} );
					treeViewer.getTree( ).setFocus( );
					treeViewer.getTree( ).setSelection( treeItems[i] );
					children[i].setXPathStatus( true );
					findXPathNode = true;
				}
//				setExpanded( treeItems[i] );
			}
		}
		
		for ( int i = 0; i < children.length; i++ )
		{
			ATreeNode treeNode = children[i].getTreeNode( );
			if ( treeNode.getChildren( ) != null
					&& treeNode.getChildren( ).length > 0 )
			{
				if ( level > ( ( xPathExpression == null || xPathExpression.split( TreePopulationUtil.PATH_SEPERATOR ).length < 5 )
						? 5
						: xPathExpression.split( TreePopulationUtil.PATH_SEPERATOR ).length ) )
					new TreeItem( treeItems[i], 0 );
				else
				{
					children[i].setHasBeenExpandedOnce( );
					if ( !findXPathNode || children[i].getXPathStatus( ) )
						populateTreeItems( treeItems[i],
								treeNode.getChildren( ),
								level,
								xPathExpression,
								includeAttribute );
					else
						TreePopulationUtil.populateTreeItems( treeItems[i],
								treeNode.getChildren( ),
								xPathExpression,
								includeAttribute );
				}
			}
		}
	}
}
