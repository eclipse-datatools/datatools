/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;
import org.eclipse.datatools.enablement.oda.xml.ui.UiPlugin;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.preference.DataSetPreferencePage;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.ExceptionHandler;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.datatools.enablement.oda.xml.util.RelationInformation;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.datatools.enablement.oda.xml.util.ui.SchemaPopulationUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Column mapping page to define the colum mapping with xml data set
 */

public class ColumnMappingPage extends DataSetWizardPage
		implements
			ITableLabelProvider
{

	private Tree availableXmlTree;
	private Button btnAddOne;
	private Button btnAddAll;
	private Button btnPreview;
	private Composite btnComposite;

	private ColumnMappingTableViewer columnMappingTable;
	private Group treeGroup;
	private Group tableViewerGroup;
	private ATreeNode treeNode;

	private String tableName;
	private String xsdFileName;
	private String xmlFileName;
	private String xmlEncoding;
	private Map columnMap;
	private List columnMappingList = new ArrayList( );

	private String selectedTreeItemText;

	private static String COLUMN_NAME = Messages.getString( "dataset.editor.columnName" );
	private static String XPATH_NAME = Messages.getString( "dataset.editor.xpathexpression" );
	private static String TYPE_NAME = Messages.getString( "dataset.editor.datatype" );
	private static String DEFAULT_PAGE_NAME = Messages.getString( "xPathChoosePage.messages.xmlColumnMapping" );
	private static String DEFAULT_PAGE_Message = Messages.getString( "wizard.title.defineColumnMapping" );
	private static String PATH_SEPERATOR = "/";
	
	private static String[] dataTypeDisplayNames = new String[]{
			Messages.getString( "datatypes.dateTime" ), //$NON-NLS-1$
			Messages.getString( "datatypes.decimal" ), //$NON-NLS-1$
			Messages.getString( "datatypes.float" ), //$NON-NLS-1$
			Messages.getString( "datatypes.integer" ), //$NON-NLS-1$
			Messages.getString( "datatypes.date" ),
			Messages.getString( "datatypes.time" ),
			Messages.getString( "datatypes.string" ),
			Messages.getString( "datatypes.boolean" )
	};
	
	private static Logger logger = Logger.getLogger( ColumnMappingPage.class.getName( ) );
	
	/**
	 * @param string
	 */
	public ColumnMappingPage( )
	{
		this( Messages.getString( "wizard.title.newDataSet" ) );
		Arrays.sort( dataTypeDisplayNames );
	}

	/**
	 * @param pageName
	 */
	public ColumnMappingPage( String pageName )
	{
		super( pageName );
		this.setTitle( pageName );
		DEFAULT_PAGE_Message = Messages.getString( "wizard.title.defineColumnMapping" );
		this.setMessage( DEFAULT_PAGE_Message );
		this.columnMap = new HashMap( );
		this.columnMappingList = new ArrayList( );
		this.setPageComplete( false );
		Arrays.sort( dataTypeDisplayNames );
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		setControl( createPageControl( parent ) );
		if( XMLInformationHolder.hasDestroyed( ) )
			XMLInformationHolder.start( this.getInitializationDesign( ) );
		initializeControl( );
		if ( selectedTreeItemText != null )
			populateXMLTree( );
		
		XMLRelationInfoUtil.setSystemHelp( getControl( ),
				IHelpConstants.CONEXT_ID_DATASET_XML_COLUMNMAPPING );
	}
	
	/**
	 * initial the page info property after create the page control
	 * 
	 */
	private void initializeControl( )
	{
		xsdFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
		xmlFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
		xmlEncoding = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST );
		String queryText = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION );
		tableName = XMLRelationInfoUtil.getTableName( queryText );

		if ( tableName != null && tableName.trim( ).length( ) > 0 )
		{
			selectedTreeItemText = XMLRelationInfoUtil.getXPathExpression( queryText,
					tableName );
			RelationInformation info = null;
			try
			{
				info = new RelationInformation( queryText );
			}
			catch ( OdaException e )
			{
				setMessage( Messages.getString( "error.columnMapping.createPage" ),
						ERROR );
				logger.log( Level.INFO, e.getMessage( ), e );
			}

			this.columnMap = new HashMap( );
			this.columnMappingList = columnMappingTable.refresh( info,
					tableName,
					this.columnMap );
			refreshColumnMappingViewer( );
		}
		else
		{
			selectedTreeItemText = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XPATH );
			tableName = XMLRelationInfoUtil.getUniqueName( null );
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_TABLE_NAME,
					tableName );
		}
		setPageProperties( );
	}
    
    /**
     * refresh the tree for refocus on the tree item  
     */
	public void refresh( )
	{
		selectedTreeItemText = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XPATH );
		xsdFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
		xmlFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
		xmlEncoding = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST );
		/*if ( xsdFileName == null || xsdFileName.trim( ).equals( "" ) )
			xsdFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );*/
		if ( selectedTreeItemText != null )
		{
			populateXMLTree( );
		}
		setPageProperties( );
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#refresh(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void refresh( DataSetDesign dataSetDesign )
	{
		DEFAULT_PAGE_Message = Messages.getString( "xPathChoosePage.messages.xmlColumnMapping" );
		if ( XMLInformationHolder.hasDestroyed( ) )
			XMLInformationHolder.start( dataSetDesign );
		this.setMessage( DEFAULT_PAGE_Message );
		refresh( );
	}
	
	/**
	 * 
	 * @param parent
	 * @return
	 */
	public Control createPageControl( Composite parent )
	{
		DEFAULT_PAGE_Message = Messages.getString( "wizard.title.defineColumnMapping" );
		Composite composite = new Composite( parent, SWT.NONE );

		GridLayout layout = new GridLayout( );
		composite.setLayout( layout );

		createPageComposite( composite );
		setPageProperties( );
		return composite;
	}

	/**
	 * create the column mapping page composite
	 * 
	 * @param parent
	 */
	private void createPageComposite( Composite parent )
	{
		Composite composite = new Composite( parent, SWT.NONE );

		FormLayout layout = new FormLayout( );
		composite.setLayout( layout );
		composite.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		createLeftGroup( composite );

		FormData data = new FormData( );
		data.left = new FormAttachment( treeGroup, 5 );
		data.bottom = new FormAttachment( 50 );

		btnComposite = new Composite( composite, SWT.NONE );
		btnComposite.setLayoutData( data );
		FillLayout btnLayout = new FillLayout( SWT.VERTICAL );
		btnLayout.spacing = 5;
		btnComposite.setLayout( btnLayout );

		btnAddOne = new Button( btnComposite, SWT.NONE );
		btnAddOne.setText( ">" ); //$NON-NLS-1$
		// TODO to externalize into message file
		btnAddOne.setToolTipText( Messages.getString( "ColumnMappingPage.AddSingleButton.tooltip" ) );
		btnAddOne.setEnabled( false );
		btnAddOne.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				TreeItem[] selectedMultiItems = availableXmlTree.getSelection( );
				if ( selectedMultiItems == null
						|| selectedMultiItems.length != 1 )
				{
					setMessage( Messages.getString( "error.columnMapping.SelectedTreeItem.notNull" ),
							ERROR );
					btnAddOne.setEnabled( false );
					return;
				}
				TreeItem selectedItem = selectedMultiItems[0];
				String pathStr = createXPath( selectedItem );
				String name = selectedItem.getText( );
				int type = -1;
				if ( selectedItem.getData( ) instanceof TreeNodeData )
				{
					// if the select treeItem is attribute, the name should not
					// start with '@'
					TreeNodeData node = (TreeNodeData) selectedItem.getData( );
					if ( node.getTreeNode( ).getType( ) == ATreeNode.ATTRIBUTE_TYPE )
						name = (String) node.getTreeNode( ).getValue( );
					try
					{
						type = DataTypes.getType( node.getTreeNode( )
								.getDataType( ) );
					}
					catch ( OdaException e1 )
					{
						type = DataTypes.STRING;
					}
				}
				ColumnMappingDialog columnDialog = new ColumnMappingDialog( getShell( ),
						DEFAULT_PAGE_NAME,
						name,
						pathStr,
						type,
						false );
				if ( columnDialog.open( ) == Window.OK )
				{
					ColumnMappingElement columnElement = columnDialog.getColumnMapping( );
					updateColumnMappingElement( columnElement );
					selectedItem = null;
					btnAddOne.setEnabled( false );
					btnAddAll.setEnabled( false );
				}
			}
		} );
		btnAddAll = new Button( btnComposite, SWT.NONE );
		btnAddAll.setText( ">>" ); //$NON-NLS-1$
		// TODO to externalize into message file
		btnAddAll.setToolTipText( Messages.getString( "ColumnMappingPage.AddAllButton.tooltip" ) );
		btnAddAll.setEnabled( false );
		btnAddAll.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				TreeItem[] selectedMultiItems = availableXmlTree.getSelection( );
				if ( selectedMultiItems == null
						|| selectedMultiItems.length == 0 )
				{
					btnAddAll.setEnabled( false );
					setMessage( Messages.getString( "error.columnMapping.SelectedTreeItem.notNull" ),
							ERROR );
				}
				if ( selectedMultiItems.length == 1 )
				{
					/* Only one node is selected at a time */
					if ( !handleSelectedItem( selectedMultiItems[0] ) )
					{
						setMessage( Messages.getString( "error.columnMapping.columnElement.create" ),
								ERROR );
					}
				}
				else
				{
					/* Multiple nodes are selected at the same time */
					for ( int i = 0; i < selectedMultiItems.length; i++ )
					{
						TreeItem selectedItem = selectedMultiItems[i];
						if ( !handleSelectedItem( selectedItem ) )
						{
							setMessage( Messages.getString( "error.columnMapping.columnElement.create" ),
									ERROR );
							break;
						}
					}
				}
				selectedMultiItems = null;
				btnAddOne.setEnabled( false );
				btnAddAll.setEnabled( false );
			}
		} );
		// create the right table viewer group
		createRightGroup( composite );
	}

	/**
	 * create preview button
	 * 
	 * @param parent
	 */
	private void createPreviewButton( Composite parent )
	{
		btnPreview = new Button( parent, SWT.PUSH );
		btnPreview.setText( Messages.getString( "menu.button.preview" ) );
		btnPreview.setToolTipText( Messages.getString( "ColumnMappingTable.previewButton.tooltip" ) );
		btnPreview.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				XMLDataPreviewDialog previewDialog = new XMLDataPreviewDialog( getShell( ) );
				if ( previewDialog.open( ) == IDialogConstants.CLOSE_ID )
				{
					previewDialog.close( );
				}
			}
		} );

		GridData gd = new GridData( );
		gd.horizontalAlignment = SWT.END;
		btnPreview.setLayoutData( gd );
	}

	private void createRightGroup( Composite composite )
	{
		Composite rightComposite = new Composite( composite, SWT.NONE );
		FormData data = new FormData( );
		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( btnComposite, 5 );
		data.right = new FormAttachment( 100, -5 );
		data.bottom = new FormAttachment( 100, -5 );
		rightComposite.setLayoutData( data );

		rightComposite.setLayout( new GridLayout( ) );
		rightComposite.setEnabled( true );
		createTableViewerGroup( rightComposite );
		createPreviewButton( rightComposite );
	}

	/**
	 * create right group composite
	 * 
	 * @param composite2
	 */
	private void createTableViewerGroup( Composite composite2 )
	{
		tableViewerGroup = new Group( composite2, SWT.NONE
				| SWT.H_SCROLL | SWT.V_SCROLL );
		tableViewerGroup.setLayout( new GridLayout( ) );
		tableViewerGroup.setText( Messages.getString( "xPathChoosePage.messages.xmlColumnMapping" ) );
		tableViewerGroup.setLayoutData( new GridData( GridData.FILL_BOTH ) );

		tableViewerGroup.setEnabled( true );
		columnMappingTable = new ColumnMappingTableViewer( tableViewerGroup,
				true,
				true,
				true );
		columnMappingTable.getControl( ).setLayoutData( new GridData( GridData.FILL_BOTH ) );

		columnMappingTable.getViewer( ).getTable( ).setHeaderVisible( true );
		columnMappingTable.getViewer( ).getTable( ).setLinesVisible( true );

		TableColumn column = new TableColumn( columnMappingTable.getViewer( )
				.getTable( ), SWT.LEFT );
		column.setText( COLUMN_NAME ); //$NON-NLS-1$
		column.setWidth( 100 );
		column = new TableColumn( columnMappingTable.getViewer( ).getTable( ),
				SWT.LEFT );
		column.setText( XPATH_NAME ); //$NON-NLS-1$
		column.setWidth( 100 );
		column = new TableColumn( columnMappingTable.getViewer( ).getTable( ),
				SWT.LEFT );
		column.setText( TYPE_NAME ); //$NON-NLS-1$
		column.setWidth( 60 );

		columnMappingTable.getViewer( )
				.setContentProvider( new IStructuredContentProvider( ) {

					public Object[] getElements( Object inputElement )
					{
						if ( inputElement instanceof ArrayList )
						{
							ArrayList inputList = new ArrayList( 10 );
							inputList.addAll( columnMappingList );
							return inputList.toArray( );
						}
						return new Object[0];
					}

					public void inputChanged( Viewer viewer, Object oldInput,
							Object newInput )
					{
					}

					public void dispose( )
					{
					}
				} );

		columnMappingTable.getViewer( ).setLabelProvider( this );
		columnMappingTable.getViewer( ).setInput( columnMappingList );
		refreshColumnMappingViewer( );

		setupEditors( );
		addListenersAndToolTip( );
	}
	
	/**
	 * Map all the children elements of the specific TreeItem to be the columns
	 * If the TreeItem has no child, it itself is mapped to be a column
	 * 
	 * @param aTreeNode
	 * @param treeItem
	 */
	private void addChildrenElements( ATreeNode aTreeNode, TreeItem treeItem )
	{
		try
		{
			if ( aTreeNode.getType( ) == ATreeNode.ATTRIBUTE_TYPE
					|| ( aTreeNode.getType( ) == ATreeNode.ELEMENT_TYPE && aTreeNode.getChildren( ).length == 0 ) )
			{
				String pathStr = createXPath( treeItem );
				updateColumnMappingElement( createSingleElement( aTreeNode,
						pathStr ) );
			}
			else
			{
				Object[] children = aTreeNode.getChildren( );
				for ( int i = 0; i < children.length; i++ )
				{
					addChildrenElements( (ATreeNode)children[i], treeItem.getItems( )[i] );
				}
			}
		}
		catch ( OdaException e )
		{
			logger.log( Level.INFO, e.getMessage( ), e );
			setMessage( Messages.getString( "error.columnMapping.ATreeNode.getChildren" ),
					ERROR );
		}
	}
	
	/**
	 * check whether the column is duplicated
	 * 
	 * @param columnName
	 * @return
	 */
	private boolean isUniqueName( String columnName, ColumnMappingElement actualElement )
	{
		boolean success = true;
		if ( columnMap != null )
		{
			if ( columnMap.containsKey( columnName )
					&& columnMap.get( columnName ) != null )
			{				
				success = false;
			}
			else
			{
				setDetailsMessage( DEFAULT_PAGE_Message, IMessageProvider.NONE );
			}
		}
		else
		{
			setDetailsMessage( DEFAULT_PAGE_Message, IMessageProvider.NONE );
			columnMap = new HashMap( );
			columnMappingList = new ArrayList( );
		}
		return success;
	}

	/**
	 * get the xpath from the selected item of tree
	 * 
	 * @param selectedItem
	 * @return
	 */
	private String createXPath( TreeItem selectedItem )
	{
		if ( selectedItem == null )
			return null;
		TreeItem select = selectedItem;

		// TODO Automatically generate the available column xpath
		String columnPath = "";
		Object data = selectedItem.getData( );
		if ( data instanceof TreeNodeData )
		{
			columnPath = generateXpathFromTreeItem( select );
		}
		return XPathPopulationUtil.populateColumnPath( getRootPathWithOutFilter( ),
				columnPath );
	}

	/**
	 * This method is used to generate the XPath expression from a TreeItem.
	 * 
	 * @param treeItem
	 * @return
	 */
	private String generateXpathFromTreeItem( TreeItem treeItem )
	{
		String columnPath = treeItem.getText( );

		while ( treeItem.getParentItem( ) != null
				&& treeItem.getParentItem( ).getData( ) instanceof TreeNodeData )
		{
			treeItem = treeItem.getParentItem( );

			if ( ( (TreeNodeData) treeItem.getData( ) ).getTreeNode( )
					.getType( ) == ATreeNode.ELEMENT_TYPE
					&& columnPath.trim( ).length( ) > 0 )
				columnPath = treeItem.getText( ) + PATH_SEPERATOR + columnPath;
			else if ( ( (TreeNodeData) treeItem.getData( ) ).getTreeNode( )
					.getType( ) == ATreeNode.ATTRIBUTE_TYPE )
				columnPath = columnPath + PATH_SEPERATOR + treeItem.getText( );
		}
		if ( !columnPath.startsWith( PATH_SEPERATOR ) )
			columnPath = PATH_SEPERATOR + columnPath;
		return columnPath;
	}
	
	/**
	 * create the left group composite
	 * 
	 * @param composite2
	 */
	private void createLeftGroup( Composite composite2 )
	{
		FormData data = new FormData( );

		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 40, -5 );
		data.bottom = new FormAttachment( 100, -5 );
		treeGroup = new Group( composite2, SWT.VERTICAL );
		treeGroup.setLayout( new FillLayout( ) );
		treeGroup.setLayoutData( data );
		availableXmlTree = new Tree( treeGroup, SWT.MULTI
				| SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		availableXmlTree.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				TreeItem[] selectedMultiItems = availableXmlTree.getSelection( );
				if ( selectedMultiItems.length > 1 )
				{
					for ( int i = 0; i < selectedMultiItems.length; i++ )
					{
						if ( selectedMultiItems[i].getGrayed( ) )
						{
							availableXmlTree.setRedraw( false );
							availableXmlTree.deselectAll( );
							availableXmlTree.setRedraw( true );
							availableXmlTree.redraw( );
						}
					}
					setMessage( DEFAULT_PAGE_Message );
					btnAddAll.setEnabled( true );
					btnAddOne.setEnabled( false );
				}
				else if ( selectedMultiItems.length == 1 )
				{
					TreeItem selectedItem = selectedMultiItems[0];
					selectedMultiItems = null;
					if ( selectedItem.getGrayed( ) )
					{
						availableXmlTree.setRedraw( false );
						availableXmlTree.deselectAll( );
						availableXmlTree.setRedraw( true );
						availableXmlTree.redraw( );
					}
					if ( selectedItem != null )
					{
						setMessage( DEFAULT_PAGE_Message );
						btnAddOne.setEnabled( true );
						btnAddAll.setEnabled( true );
					}
					else
					{
						btnAddOne.setEnabled( false );
						btnAddAll.setEnabled( false );
					}
				}
			}

		} );

		treeGroup.setText( Messages.getString( "xPathChoosePage.messages.xmlStructure" ) );
	}

	/**
	 * add the listener to the columnMappingTable.
	 */
	private void addListenersAndToolTip( )
	{
		columnMappingTable.getViewer( )
				.getTable( )
				.addKeyListener( new KeyListener( ) {

					public void keyPressed( KeyEvent e )
					{
					}

					public void keyReleased( KeyEvent e )
					{
						if ( e.keyCode == SWT.DEL )
						{
							removeSelectedItems( );
							setPageProperties( ); 
						}
					}

				} );

		columnMappingTable.getViewer( ).getTable( ).addListener( SWT.MouseDown,
				new Listener( ) {

					public void handleEvent( Event event )
					{
						if ( columnMappingTable.getViewer( )
								.getTable( )
								.getSelectionIndices( ).length > 1 )
						{
							columnMappingTable.getEditButton( )
									.setEnabled( false );
							columnMappingTable.getUpButton( )
									.setEnabled( false );
							columnMappingTable.getDownButton( )
									.setEnabled( false );
						}
					}
				} );

		columnMappingTable.getEditButton( )
				.addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						doEdit( );
					}

					public void widgetDefaultSelected( SelectionEvent e )
					{
					}

				} );
		columnMappingTable.getEditButton( )
				.setToolTipText( Messages.getString( "ColumnMappingTable.editButton.tooltip" ) );

		columnMappingTable.getRemoveButton( )
				.addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						removeSelectedItems( );
						setPageProperties( );
					}

					public void widgetDefaultSelected( SelectionEvent e )
					{
					}

				} );
		// TODO to externalize into message file
		columnMappingTable.getRemoveButton( )
				.setToolTipText( Messages.getString( "ColumnMappingTable.removeButton.tooltip" ) );

		columnMappingTable.getRemoveMenuItem( )
				.addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						removeSelectedItems( );
						setPageProperties( );
					}

					public void widgetDefaultSelected( SelectionEvent e )
					{
					}

				} );

		columnMappingTable.getRemoveAllMenuItem( )
				.addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						removeAllItem( );
						setPageProperties( );
					}

					public void widgetDefaultSelected( SelectionEvent e )
					{
						widgetSelected( e );
					}
				} );

		columnMappingTable.getUpButton( )
				.addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						upMoveSelectedItem( );
					}

					public void widgetDefaultSelected( SelectionEvent e )
					{
					}

				} );
		// TODO to externalize into message file
		columnMappingTable.getUpButton( )
				.setToolTipText( Messages.getString( "ColumnMappingTable.upButton.tooltip" ) );

		columnMappingTable.getDownButton( )
				.addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						downMoveSelectedItem( );
					}

					public void widgetDefaultSelected( SelectionEvent e )
					{
					}

				} );
		// TODO to externalize into message file
		columnMappingTable.getDownButton( )
				.setToolTipText( Messages.getString( "ColumnMappingTable.downButton.tooltip" ) );
	}
	
	/**
	 * Edit the single table viewer element
	 * 
	 */
	private void doEdit( )
	{
		int index = columnMappingTable.getViewer( )
				.getTable( )
				.getSelectionIndex( );
		if ( index == -1 )
			return;

		ColumnMappingElement columnMappingElement = (ColumnMappingElement) columnMappingTable.getViewer( )
				.getTable( )
				.getItem( index )
				.getData( );
		String originColumnName = columnMappingElement.getColumnName( );
		try
		{
			ColumnMappingDialog columnDialog = new ColumnMappingDialog( getShell( ),
					DEFAULT_PAGE_NAME,
					columnMappingElement.getColumnName( ),
					columnMappingElement.getXPath( ),
					DataTypes.getType( columnMappingElement.getTypeStandardString( ) ),
					true );
			if ( columnDialog.open( ) == Window.OK )
			{
				columnMap.remove( columnMappingElement.getColumnName( ) );
				ColumnMappingElement columnElement = columnDialog.getColumnMapping( );
				String editedColumnName = columnElement.getColumnName( );
				if( !originColumnName.equalsIgnoreCase( editedColumnName ) )
				{
					int appendix = 0;
					while ( !isUniqueName( editedColumnName, columnElement ) )
					{
						appendix++;
						break;
					}
					if ( appendix > 0 )
					{
						editedColumnName = editedColumnName + "_" + appendix;
					}
					columnElement.setColumnName( editedColumnName );
				}
				columnMappingElement.setColumnName( editedColumnName );
				columnMappingElement.setType( columnElement.getType( ) );
				columnMappingElement.setXPath( columnElement.getXPath( ) );
				columnMap.put( editedColumnName, columnMappingElement );
				if( columnMappingList.size( ) > index )
				{
					columnMappingList.set( index, columnMappingElement );
				}
				setXMLInformationHolderProps( );
				refreshColumnMappingViewer( );
				setPageProperties( );
				refreshXMLConnection( );
			}
		}
		catch ( OdaException e1 )
		{
			logger.log( Level.INFO, e1.getMessage( ), e1 );
			setMessage( Messages.getString( "error.columnMapping.columnElement.edit" ),
					ERROR );
		}
	}

	/**
	 * To remove all the selected column mapping items from table
	 * 
	 */
	private void removeSelectedItems( )
	{
		int[] indices = columnMappingTable.getViewer( )
				.getTable( )
				.getSelectionIndices( );
		for( int i = 0; i < indices.length; i ++ )
		{
			removeSingleColumnItem( indices[i] - i );
		}
		refreshColumnMappingViewer( );
	}

	/**
	 * Remove single table item by the specific item index
	 * 
	 * @param items
	 * @param index
	 */
	private void removeSingleColumnItem( int index )
	{
		Object element = columnMappingTable.getViewer( )
				.getTable( )
				.getItem( index )
				.getData( );
		String elementName = "";
		if ( element instanceof ColumnMappingElement )
		{
			ColumnMappingElement entry = (ColumnMappingElement) element;
			elementName = (String) entry.getColumnName( );

			columnMappingTable.getViewer( ).getTable( ).remove( index );

			this.columnMap.remove( elementName );
			this.columnMappingList.remove( index );

			String str = XMLRelationInfoUtil.replaceInfo( this.tableName,
					saveQueryString( ),
					XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
					str );
		}
	}

	/**
	 * Remove all of the column mapping items from the table
	 * 
	 */
	private void removeAllItem( )
	{
		int count = columnMappingTable.getViewer( ).getTable( ).getItemCount( );
		for ( int index = 0; index < count ; index++ )
		{
			removeSingleColumnItem( 0 );
		}
		String str = XMLRelationInfoUtil.replaceInfo( this.tableName,
				"",
				XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
		XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
				str );
		refreshColumnMappingViewer( );
	}

	/**
	 * up move action
	 */
	private void upMoveSelectedItem( )
	{
		int count = columnMappingTable.getViewer( ).getTable( ).getItemCount( );
		int index = columnMappingTable.getViewer( )
				.getTable( )
				.getSelectionIndex( );

		if ( index > 0 && index < count )
		{
			Object obj = this.columnMappingList.get( index );
			this.columnMappingList.set( index,
					this.columnMappingList.get( index - 1 ) );
			this.columnMappingList.set( index - 1, obj );
			String str = XMLRelationInfoUtil.replaceInfo( this.tableName,
					saveQueryString( ),
					XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
					str );
			refreshColumnMappingViewer( );
		}
	}

	/**
	 * down move action
	 * 
	 */
	private void downMoveSelectedItem( )
	{
		int count = columnMappingTable.getViewer( ).getTable( ).getItemCount( );
		int index = columnMappingTable.getViewer( )
				.getTable( )
				.getSelectionIndex( );
		if ( index > -1 && index < count - 2 )
		{
			Object obj = this.columnMappingList.get( index );
			this.columnMappingList.set( index,
					this.columnMappingList.get( index + 1 ) );
			this.columnMappingList.set( index + 1, obj );
			String str = XMLRelationInfoUtil.replaceInfo( this.tableName,
					saveQueryString( ),
					XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
					str );
			refreshColumnMappingViewer( );
		}
	}

	/**
	 * populate xml tree from schema file
	 * 
	 */
	private void populateXMLTree( )
	{
		if ( ( xsdFileName == null || xsdFileName.trim( ).length( ) == 0 )
				&& ( xmlFileName == null || xmlFileName.trim( ).length( ) == 0 ) )
			return;
		
		try
		{
			this.treeNode = null;
			this.availableXmlTree.removeAll( );
			
			int numberOfElement = 0;
			Preferences preferences = UiPlugin.getDefault( )
					.getPluginPreferences( );
			if ( preferences.contains( DataSetPreferencePage.USER_MAX_NUM_OF_ELEMENT_PASSED ) )
			{
				numberOfElement = preferences.getInt( DataSetPreferencePage.USER_MAX_NUM_OF_ELEMENT_PASSED );
			}
			else
			{
				numberOfElement = DataSetPreferencePage.DEFAULT_MAX_NUM_OF_ELEMENT_PARSED;
				preferences.setValue( DataSetPreferencePage.USER_MAX_NUM_OF_ELEMENT_PASSED,
						numberOfElement );
			}
			// TODO For migrate into ODA3.0,the relative path cannot be
			// retrieved
			// Object url = StructureFactory.getModuleHandle( ).findResource(
			// schemaFileName,IResourceLocator.LIBRARY );
			// if( url != null )
			treeNode = SchemaPopulationUtil.getSchemaTree( xsdFileName, xmlFileName,xmlEncoding, numberOfElement );
			Object[] childs = treeNode.getChildren( );
			populateTreeItems( availableXmlTree, childs, 0 );
			availableXmlTree.addListener(SWT.Expand, new Listener(){

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
									true );
					}
					catch ( OdaException e )
					{
						setMessage( Messages.getString( "error.columnMapping.createPage" ),
								ERROR );
					}

				}
			} );

			TreeItem[] selectedMultiItems = availableXmlTree.getSelection( );
			if ( selectedMultiItems == null ||
					selectedMultiItems.length == 0 )
			{
				btnAddOne.setEnabled( false );
				btnAddAll.setEnabled( false );
				this.setMessage( Messages.getString( "error.columnMapping.tableMappingXPathNotExist" ),
						ERROR );
			}
			else
			{
				btnAddOne.setEnabled( true );
				btnAddAll.setEnabled( true );
				this.setMessage( DEFAULT_PAGE_Message );
			}
		}
		catch ( Exception e )
		{
			logger.log( Level.INFO, e.getMessage( ), e );
			setMessage( Messages.getString( "error.columnMapping.createPage" ),
					ERROR );
		}
	}

	/**
	 * 
	 * @param tree
	 * @param node
	 * @throws OdaException
	 */
	private void populateTreeItems( Object tree, Object[] node, int level )
			throws OdaException
	{
		level ++;
		
		for ( int i = 0; i < node.length; i++ )
		{
			TreeItem treeItem;
			if ( tree instanceof Tree )
			{
				treeItem = new TreeItem( (Tree) tree, 0 );
			}
			else
				treeItem = new TreeItem( (TreeItem) tree, 0 );
			ATreeNode treeNode = (ATreeNode) node[i];
			TreeNodeData data = new TreeNodeData( treeNode );
			
			treeItem.setData( data );
			int type = treeNode.getType( );
			if ( type == ATreeNode.ATTRIBUTE_TYPE )
			{
				treeItem.setImage( TreeNodeDataUtil.getColumnImage( ) );
				treeItem.setText( "@" + treeNode.getValue( ).toString( ) );
			}
			else if ( type == ATreeNode.ELEMENT_TYPE )
			{
				if ( treeNode.getParent( ).getValue( ).equals( "ROOT" ) )
				{
					treeItem.setImage( TreeNodeDataUtil.getSourceFileImage( ) );
				}
				else if ( treeNode.getChildren( ).length == 0 )
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
				treeItem.setText( treeNode.getValue( ).toString( ) );
			}
			String populateString = XPathPopulationUtil.populateColumnPath( getRootPathWithOutFilter( ),
					generateXpathFromTreeItem( treeItem ) );
			if ( populateString != null )
			{
				if ( populateString.equals( "" ) )
				{
					FontData fontData = new FontData( "", 8, SWT.BOLD );
					treeItem.setFont( new Font( null, fontData ) );
					
					availableXmlTree.setSelection( new TreeItem[]{
						treeItem
					} );
					availableXmlTree.setFocus();
					availableXmlTree.setSelection( treeItem );
				}
				
				setExpanded( treeItem );
	
			}
			if ( treeNode.getChildren( ) != null
					&& treeNode.getChildren( ).length > 0 )
			{
				if ( level > ( ( selectedTreeItemText == null || selectedTreeItemText.split( "/" ).length < 5 )
						? 5 : selectedTreeItemText.split( "/" ).length ) )
					new TreeItem( treeItem, 0 );
				else
				{
					data.setHasBeenExpandedOnce( );
					populateTreeItems( treeItem, treeNode.getChildren( ), level );
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
		return selectedTreeItemText.replaceAll( "\\Q[\\E.*\\Q]\\E", "" );
	}
	
	// expand the tree
	private void setExpanded( TreeItem treeItem )
	{
		if ( treeItem.getParentItem( ) != null )
			setExpanded( treeItem.getParentItem( ) );
		treeItem.setExpanded( true );
	}

	/**
	 * set up editor for mapping table
	 *
	 */
	private void setupEditors( )
	{
		CellEditor[] editors = new CellEditor[3];

		editors[0] = new TextCellEditor( columnMappingTable.getViewer( )
				.getTable( ), SWT.NONE );
		editors[1] = new TextCellEditor( columnMappingTable.getViewer( )
				.getTable( ), SWT.NONE );
		editors[2] = new ComboBoxCellEditor( columnMappingTable.getViewer( )
				.getTable( ), dataTypeDisplayNames, SWT.READ_ONLY );
		columnMappingTable.getViewer( ).setCellEditors( editors );
		columnMappingTable.getViewer( ).setColumnProperties( new String[]{
				COLUMN_NAME, //$NON-NLS-1$ 
				XPATH_NAME, //$NON-NLS-1$
				TYPE_NAME, //$NON-NLS-1$
		} );
		
		columnMappingTable.getViewer( )
				.addDoubleClickListener( new IDoubleClickListener( ) {

					public void doubleClick( DoubleClickEvent e )
					{
						doEdit( );
					}
				} );

	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isXpathValid( String value )
	{
		//TODO add xpath validation codes
		return true;
		/*
		if( value == null )
			return true;
		return !value.trim( ).matches( ".*\\Q[\\E.*\\Q]\\E.+" );
		*/
	}
	
	/**
	 * 
	 *
	 */
	private void refreshXMLConnection( )
	{
		String str = XMLRelationInfoUtil.replaceInfo( this.tableName,
				saveQueryString( ),
				XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
		if ( str != null )
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
					str );
	}

	/**
	 * 
	 * @return
	 */
	public boolean performOk( )
	{
		return isValid( ) ;
	}

	/**
	 * whether the column mapping is valid, if valid, perform ok. else show the
	 * warning message.
	 * 
	 * @return
	 */
	private boolean isValid( )
	{
		String queryText = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION );
		if ( queryText == null || queryText.trim( ).length( ) == 0 )
			return false;
		else
			return true;
	}

	/**
	 * set the detail error/warning/info messages
	 * 
	 * @param message
	 * @param type
	 */
	private void setDetailsMessage( String message, int type )
	{
		this.setMessage( message, type );
	}

	/**
	 * get the query string from the list of column element
	 * 
	 * @return
	 */
	private String saveQueryString( )
	{
		if ( !this.columnMappingList.isEmpty( ) )
		{
			String tablePath = "[" + selectedTreeItemText + "]";
			String queryString = tableName +
					RelationInformation.CONST_TABLE_COLUMN_DELIMITER +
					tablePath +
					RelationInformation.CONST_TABLE_COLUMN_DELIMITER;
			String rowStr = "";
			Iterator rowObj = this.columnMappingList.iterator( );
			while ( rowObj.hasNext( ) )
			{
				ColumnMappingElement element = (ColumnMappingElement) rowObj.next( );
				rowStr = "{" +
						element.getColumnName( ) + ";" +
						element.getTypeStandardString( ) + ";" +
						element.getXPath( ) + "}";
				if ( rowObj.hasNext( ) )
					rowStr = rowStr + ",";
				queryString = queryString + rowStr;
			}
			return queryString;
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean performCancel( )
	{
		return true;
	}

	/**
	 * refresh the column mapping viewer
	 * 
	 * @param columnMap
	 */
	private void refreshColumnMappingViewer( )
	{
		columnMappingTable.getViewer( ).setInput( columnMappingList );
		for ( int i = 0; i < columnMappingTable.getViewer( )
				.getTable( )
				.getItemCount( ) - 1; i++ )
		{
			TableItem ti = columnMappingTable.getViewer( )
					.getTable( )
					.getItem( i );

			Object element = ti.getData( );

			String c1 = "", c2 = "", c3 = ""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			if ( element instanceof ColumnMappingElement )
			{
				ColumnMappingElement colElement = (ColumnMappingElement) element;

				c1 = colElement.getColumnName( ) == null ? ""
						: colElement.getColumnName( );
				c2 = colElement.getXPath( ) == null ? ""
						: colElement.getXPath( );
				c3 = colElement.getType( ) == null ? "" : colElement.getType( );
			}
			ti.setText( 0, c1 );
			ti.setText( 1, c2 );
			ti.setText( 2, c3 );
		}
		columnMappingTable.getViewer( ).refresh( );
	}

	/**
	 * 
	 * @return
	 */
	public String getToolTip( )
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * set the schema file
	 * 
	 * @param fileName
	 */
	public void setSchemaFile( String fileName )
	{
		this.xsdFileName = fileName;

	}

	public Image getColumnImage( Object element, int columnIndex )
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnText( Object element, int columnIndex )
	{
		String value = null;
		try
		{
			switch ( columnIndex )
			{
				case 0 :
				{
					value = (String) ( (ColumnMappingElement) element ).getColumnName( ); //$NON-NLS-1$
					break;
				}
				case 1 :
				{
					value = (String) ( (ColumnMappingElement) element ).getXPath( );
					break;
				}
				case 2 :
				{
					value = (String) ( (ColumnMappingElement) element ).getType( ); //$NON-NLS-1$
					break;
				}
			}
		}
		catch ( Exception ex )
		{
			logger.log( Level.INFO, ex.getMessage( ), ex );
			ExceptionHandler.showException( getShell( ),
					Messages.getString( "error.label" ),
					ex.getMessage( ),
					ex );
		}
		if ( value == null )
		{
			value = ""; //$NON-NLS-1$
		}
		return value;
	}

	/**
	 * Depending on the column mapping, the properties of various controls
	 * on this page are set
	 */
	private void setPageProperties( )
	{
		boolean columnMappingExist = false;
		boolean dataFileExist = true;
		Object dataSourceXmlDataFile = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
		Object dataSetXmlDataFile = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XML_FILE );
		
		if( dataSetXmlDataFile == null || dataSetXmlDataFile.toString( ).length( ) == 0 )
			dataFileExist = false;
		
		if( (!dataFileExist)&&(dataSourceXmlDataFile != null && dataSourceXmlDataFile.toString( ).trim( ).length( ) > 0) )
			dataFileExist = true;

		columnMappingExist = ( columnMappingList != null && columnMappingList.size( ) > 0 );
		columnMappingTable.getEditButton( ).setEnabled( columnMappingExist );
		columnMappingTable.getDownButton( ).setEnabled( columnMappingExist );
		columnMappingTable.getUpButton( ).setEnabled( columnMappingExist );
		columnMappingTable.getRemoveButton( ).setEnabled( columnMappingExist );
		columnMappingTable.getRemoveMenuItem( ).setEnabled( columnMappingExist );
		columnMappingTable.getRemoveAllMenuItem( )
				.setEnabled( columnMappingExist );
		setPageComplete( columnMappingExist );
	}

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
     */
    protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		try
		{
			savePage( design );
		}
		catch ( OdaException e )
		{
			logger.log( Level.INFO, e.getMessage( ), e );
		}
		return design;
	}

	
    /**
     * Updates the given dataSetDesign with the query and its metadata defined
     * in this page.
     * 
     * @param dataSetDesign
     * @throws OdaException 
     */
	private void savePage( DataSetDesign dataSetDesign ) throws OdaException
	{
    	if( XMLInformationHolder.hasDestroyed( ) )
    		return;
    	
		if ( dataSetDesign != null )
		{
			if ( getQueryText( dataSetDesign ) == null )
			{
				setQueryText( dataSetDesign,
						XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
			}
			if ( getQueryText( dataSetDesign ) != null
					&& !getQueryText( dataSetDesign ).equals( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) ) )
			{
				setQueryText( dataSetDesign,
						XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
				
				updateDesign(dataSetDesign);
			}
		}
	}
	
	protected void updateDesign( DataSetDesign dataSetDesign )
	{
		DataSetDesignPopulator.populateResultSet( dataSetDesign );
	}
	
	protected String getQueryText( DataSetDesign dataSetDesign )
	{
		return dataSetDesign.getQueryText( );
	}

	protected void setQueryText( DataSetDesign dataSetDesign, String queryText )
	{
		dataSetDesign.setQueryText( queryText );
	}
	
	public void addListener( ILabelProviderListener listener )
	{
		// TODO Auto-generated method stub
		
	}

	public boolean isLabelProperty( Object element, String property )
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener( ILabelProviderListener listener )
	{
		// TODO Auto-generated method stub
		
	}
	
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	public void setVisible( boolean visible )
	{
		super.setVisible( visible );
		getControl( ).setFocus( );
	}
	
	/*
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#cleanup()
	 */
    protected void cleanup()
    {
    	XMLInformationHolder.destory( );
    }	
	
	/**
	 * Create a single column mapping element by the specific ATreeNode object and its path
	 * 
	 * @param node
	 * @param path
	 * @return
	 */
    private ColumnMappingElement createSingleElement( ATreeNode node, String path )
	{
		ColumnMappingElement columnElement =  new ColumnMappingElement( );
		columnElement.setColumnName( node.getValue( ).toString( ) );
		columnElement.setXPath( path );
		String type = null;
		try
		{
			type = node.getDataType( );
			if( type == null )
			{
				type = dataTypeDisplayNames[6];
			}
		}
		catch ( OdaException e )
		{
			type = dataTypeDisplayNames[6];
		}
		columnElement.setType( type );
		return columnElement;
	}

	/**
	 * @param columnElement
	 */
	private void updateColumnMappingElement( ColumnMappingElement columnElement )
	{
		addNewColumnElement( columnElement );
		refreshColumnMappingViewer( );
		setPageProperties( );
	}

	/**
	 * @param columnElement
	 */
	private void addNewColumnElement( ColumnMappingElement columnElement )
	{
		int index = 0;
		String columnName = columnElement.getColumnName( );
		while ( !isUniqueName( columnName, columnElement ) )
		{
			index++;
			String alias = columnName + "_" + index;
			if ( isUniqueName( alias, columnElement ) )
			{
				columnElement.setColumnName( alias );
				columnMap.put( alias, columnElement );
				break;
			}
		}
		if ( index == 0 )
		{
			columnMap.put( columnName, columnElement );
		}
		columnMappingList.add( columnElement );
		setXMLInformationHolderProps( );
	}

	/**
	 * Set the XMLInformationHolder properties
	 * 
	 */
	private void setXMLInformationHolderProps( )
	{
		String relationInfo = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION );
		if ( relationInfo != null && relationInfo.trim( ).length( ) > 0 )
		{
			String tableInfo = XMLRelationInfoUtil.getTableRelationInfo( relationInfo,
					tableName );
			if ( tableInfo != null && tableInfo.trim( ).length( ) > 0 )
				XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
						XMLRelationInfoUtil.replaceInfo( tableName,
								saveQueryString( ),
								relationInfo ) );
			else
				XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
						XMLRelationInfoUtil.concatRelationInfo( relationInfo,
								saveQueryString( ) ) );
		}
		else
		{
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
					saveQueryString( ) );
		}
	}

	/**
	 * Handler for selecting a tree item
	 * 
	 */
	private boolean handleSelectedItem( TreeItem selectedItem )
	{
		if ( selectedItem.getData( ) != null
				&& selectedItem.getData( ) instanceof TreeNodeData )
		{
			ATreeNode aTreeNode = ( (TreeNodeData) selectedItem.getData( ) ).getTreeNode( );
			addChildrenElements( aTreeNode, selectedItem );
			return true;
		}
		return false;
	}
	
}
