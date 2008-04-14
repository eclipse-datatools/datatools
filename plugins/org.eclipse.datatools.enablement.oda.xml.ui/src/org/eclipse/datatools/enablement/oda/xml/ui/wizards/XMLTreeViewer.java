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
	private String xPathExpression;
	
	private static String ATTRIBUTE_MARK = "@"; //$NON-NLS-1$
	private static final String ROOT = "ROOT"; //$NON-NLS-1$
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static String PATH_SEPERATOR = "/"; //$NON-NLS-1$
	
	XMLTreeViewer( Composite parent, boolean supportMultiSelection )
	{
		FormData data = new FormData( );

		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 47, -5 );
		data.bottom = new FormAttachment( 100, -5 );
		
		Group treeGroup = new Group( parent, SWT.NONE );
		treeGroup.setLayout( new FillLayout( ) );
		treeGroup.setLayoutData( data );
		treeViewer = new TreeViewer( treeGroup, SWT.MULTI
				| SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );

		data = new FormData( );
		data.left = new FormAttachment( treeGroup, 5 );
		data.bottom = new FormAttachment( 50 );
		data.width = 20;

		data = new FormData( );
		data.left = new FormAttachment( treeGroup, 5 );
		data.bottom = new FormAttachment( 50 );

		btnComposite = new Composite( parent, SWT.NONE );
		btnComposite.setLayoutData( data );
		FillLayout btnLayout = new FillLayout( SWT.VERTICAL );
		btnLayout.spacing = 5;
		btnComposite.setLayout( btnLayout );

		btnSingleAdd = new Button( btnComposite, SWT.NONE );
		btnSingleAdd.setText( ">" ); //$NON-NLS-1$
		btnSingleAdd.setEnabled( false );
		btnSingleAdd.setToolTipText( "xPathChoosePage.messages.btnAdd.tooltip" ); //$NON-NLS-1$

		btnMultiAdd = new Button( btnComposite, SWT.NONE );
		btnMultiAdd.setText( ">>" ); //$NON-NLS-1$
		btnMultiAdd.setToolTipText( Messages.getString( "ColumnMappingPage.AddAllButton.tooltip" ) ); //$NON-NLS-1$
		btnMultiAdd.setEnabled( true );
		btnMultiAdd.setToolTipText( "xPathChoosePage.messages.btnAdd.tooltip" ); //$NON-NLS-1$
		
		if( !supportMultiSelection )
		{
			btnMultiAdd.setVisible( false );
		}
		treeGroup.setText( Messages.getString( "xPathChoosePage.messages.xmlStructure" ) ); //$NON-NLS-1$
	}

	void populateTree( ATreeNode treeNode, String xPathExpression, boolean includeAttr ) throws OdaException
	{
		this.xPathExpression = xPathExpression;
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
		populateTreeItems( treeViewer.getTree( ), childs, 0 , includeAttribute );
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
	private void populateTreeItems( Object tree, Object[] node, int level , boolean includeAttribute )
			throws OdaException
	{
		level ++;
		
		for ( int i = 0; i < node.length; i++ )
		{
			ATreeNode treeNode = (ATreeNode) node[i];
			TreeNodeData data = new TreeNodeData( treeNode );
			int type = treeNode.getType( );
			TreeItem treeItem;

			if ( type == ATreeNode.ATTRIBUTE_TYPE )
			{
				if ( includeAttribute )
				{
					if ( tree instanceof Tree )
					{
						treeItem = new TreeItem( (Tree) tree, 0 );
					}
					else
					{
						treeItem = new TreeItem( (TreeItem) tree, 0 );
					}
					treeItem.setData( data );
					treeItem.setImage( TreeNodeDataUtil.getColumnImage( ) );
					treeItem.setText( ATTRIBUTE_MARK
							+ treeNode.getValue( ).toString( ) );
				}
				else
				{
					continue;
				}
			}
			else if ( type == ATreeNode.ELEMENT_TYPE )
			{
				if ( tree instanceof Tree )
				{
					treeItem = new TreeItem( (Tree) tree, 0 );
				}
				else
				{
					treeItem = new TreeItem( (TreeItem) tree, 0 );
				}
				treeItem.setData( data );
				if ( treeNode.getParent( )!= null && ROOT.equals( treeNode.getParent( ).getValue( )) )  
				{
					treeItem.setImage( TreeNodeDataUtil.getSourceFileImage( ) );
				}
				else if ( treeNode.getChildren( ) == null || treeNode.getChildren( ).length == 0 )
				{
					treeItem.setImage( TreeNodeDataUtil.getColumnImage( ) );
				}
				else
				{
					treeItem.setImage( TreeNodeDataUtil.getXmlElementImage( ) );
				}
				treeItem.setText( treeNode.getValue( ).toString( ) );
			}
			else
			{
				if ( tree instanceof Tree )
				{
					treeItem = new TreeItem( (Tree) tree, 0 );
				}
				else
				{
					treeItem = new TreeItem( (TreeItem) tree, 0 );
				}
				treeItem.setData( data );
				treeItem.setText( treeNode.getValue( ).toString( ) );
			}
			ATreeNode aTreeNode = ( (TreeNodeData)  treeItem.getData( ) ).getTreeNode( );
			String populateString = XPathPopulationUtil.populateColumnPath( getRootPathWithOutFilter( ),
					generateXpathFromATreeNode( aTreeNode ) );
			if ( populateString != null )
			{
				if ( populateString.equals( EMPTY_STRING ) )
				{
					FontData fontData = new FontData( EMPTY_STRING, 8, SWT.BOLD );
					treeItem.setFont( new Font( null, fontData ) );
					
					treeViewer.getTree( ).setSelection( new TreeItem[]{
						treeItem
					} );
					treeViewer.getTree( ).setFocus();
					treeViewer.getTree( ).setSelection( treeItem );
				}
				
				setExpanded( treeItem );
	
			}
			if ( treeNode.getChildren( ) != null
					&& treeNode.getChildren( ).length > 0 )
			{
				if ( level > ( ( xPathExpression == null || xPathExpression.split( PATH_SEPERATOR ).length < 5 )   
						? 5 : xPathExpression.split( PATH_SEPERATOR ).length ) )                                        
					new TreeItem( treeItem, 0 );
				else
				{
					data.setHasBeenExpandedOnce( );
					populateTreeItems( treeItem, treeNode.getChildren( ), level, includeAttribute );
				}
			}
		}
	}
	
	/**
	 * Return the tailored root path without filter definition.
	 * @return
	 */
	private String getRootPathWithOutFilter( )
	{
		return xPathExpression.replaceAll( "\\Q[\\E.*\\Q]\\E", EMPTY_STRING ); //$NON-NLS-1$
	}
	
	/**
	 * This method is used to generate the XPath expression from an ATreeNode object.
	 * 
	 * @param ATreeNode aTreeNode
	 * @return
	 */
	private String generateXpathFromATreeNode( ATreeNode aTreeNode )
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
	
	/**
	 * 
	 * @param treeItem
	 */
	private void setExpanded( TreeItem treeItem )
	{
		if ( treeItem.getParentItem( ) != null )
			setExpanded( treeItem.getParentItem( ) );
		treeItem.setExpanded( true );
	}
}
