/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.ExceptionHandler;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
	
	private String xPathExpression = ""; //$NON-NLS-1$
	private boolean includeAttr;
	

	
	XMLTreeViewer( Composite parent, boolean supportMultiSelection )
	{
		FormData data = new FormData( );

		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 47, -5 );
		data.bottom = new FormAttachment( 100, -5 );
		data.height = 400;
				
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

		FormData data2 = new FormData( );
		data2.left = new FormAttachment( treeGroup, 5 );
		data2.bottom = new FormAttachment( 50 );

		btnComposite = new Composite( parent, SWT.NONE );
		btnComposite.setLayoutData( data2 );
		GridLayout btnLayout = new GridLayout( );
		btnLayout.verticalSpacing = 25;
		btnComposite.setLayout( btnLayout );

		btnSingleAdd = new Button( btnComposite, SWT.NONE );
		btnSingleAdd.setText( ">" ); //$NON-NLS-1$
		btnSingleAdd.setEnabled( false );

		btnMultiAdd = new Button( btnComposite, SWT.NONE );
		btnMultiAdd.setText( ">>" ); //$NON-NLS-1$
		btnMultiAdd.setToolTipText( Messages.getString( "ColumnMappingPage.AddAllButton.tooltip" ) ); //$NON-NLS-1$
		btnMultiAdd.setEnabled( true );
		
		int width = Math.max( 45, btnMultiAdd.computeSize( SWT.DEFAULT, SWT.DEFAULT ).x );
		GridData btnGd = new GridData( );
		btnGd.widthHint = width;
		
		btnSingleAdd.setLayoutData( btnGd );
		
		GridData btnGd2 = new GridData( );
		btnGd2.widthHint = width;
		btnMultiAdd.setLayoutData( btnGd2 );
		
		if( !supportMultiSelection )
		{
			btnMultiAdd.setVisible( false );
		}
		treeGroup.setText( Messages.getString( "xPathChoosePage.messages.xmlStructure" ) ); //$NON-NLS-1$
		
		treeViewer.getTree( ).addListener( SWT.Expand, new Listener( ) {

			public void handleEvent( Event event )
			{
				TreeItem currentItem = (TreeItem) event.item;

				TreeNodeData treeNodeData = (TreeNodeData) currentItem.getData( );
				if ( treeNodeData.hasBeenExpandedOnce( ) )
				{
					return;
				}

				treeNodeData.setHasBeenExpandedOnce( );
				currentItem.removeAll( );
				try
				{
					Object[] children = treeNodeData.getTreeNode( )
							.getChildren( );
					if ( children != null && children.length > 0 )
					{
						TreePopulationUtil.populateTreeItems( currentItem,
								treeNodeData.getTreeNode( ).getChildren( ),
								xPathExpression,
								includeAttr );
					}
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
		
		treeViewer.getTree( ).addDisposeListener( new DisposeListener( ){
			
			public void widgetDisposed( DisposeEvent e )
			{
				TreeItem[] items = treeViewer.getTree( ).getItems( );
				for ( int i = 0; i < items.length; i++ )
				{
					if ( ( (TreeNodeData) items[i].getData( ) ).getXPathStatus( ) )
					{
						if ( items[i].getFont( ) != null )
							items[i].getFont( ).dispose( );
						if ( items[i].getBackground( ) != null )
							items[i].getBackground( ).dispose( );
					}
				}
			}

			});

	}

	void populateTree( ATreeNode treeNode, String xPathExpression,
			boolean includeAttribute, boolean needExpand ) throws OdaException
	{
		this.xPathExpression = xPathExpression;
		this.includeAttr = includeAttribute;

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
					xPathExpression,
					includeAttr );
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
			String rootPath = TreePopulationUtil.getRootPathWithOutFilter( xPathExpression );
			String columnPath = TreePopulationUtil.generateXpathFromATreeNode( aTreeNode );
			if ( TreePopulationUtil.doesMatchXPath( rootPath, columnPath ) )
			{
				FontData fontData = new FontData( TreePopulationUtil.EMPTY_STRING,
						8,
						SWT.BOLD );
				treeItems[i].setFont( new Font( null, fontData ) );

				treeItems[i].setBackground( TreePopulationUtil.getBackGroundColor( ) );
				treeItems[i].setForeground( TreePopulationUtil.getForeGroundColor( ) );
				
				if ( treeViewer.getTree( ).getSelection( ).length == 0 )
				{
					treeViewer.getTree( ).setSelection( new TreeItem[]{
						treeItems[i]
					} );
				}
				
				treeViewer.getTree( ).setFocus( );
				children[i].setXPathStatus( true );
				findXPathNode = true;

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
