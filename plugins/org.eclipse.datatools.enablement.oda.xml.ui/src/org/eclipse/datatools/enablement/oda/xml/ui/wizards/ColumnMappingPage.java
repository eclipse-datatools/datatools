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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;
import org.eclipse.datatools.enablement.oda.xml.util.RelationInformation;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.datatools.enablement.oda.xml.util.ui.SchemaPopulationUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.xml.ui.UiPlugin;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.preference.DataSetPreferencePage;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.ExceptionHandler;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
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
import org.eclipse.swt.graphics.Color;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Column mapping page to define the colum mapping with xml data set
 */

public class ColumnMappingPage extends DataSetWizardPage implements ITableLabelProvider
{

	private final int DEFAULT_WIDTH = 300;
	private final int DEFAULT_HEIGHT = 200;
	private Tree availableXmlTree;
	private Button btnAdd;
	private Composite btnComposite;

	private ColumnMappingTableViewer columnMappingTable;
	private Group treeGroup;
	private Group rightGroup;
	private ATreeNode treeNode;
	private TreeItem selectedItem;

	private ResultSetTableViewer previewViewer;

	private String tableName;
	private String schemaFileName;
	private Map columnMap;
	private List columnMappingList = new ArrayList( );
	private ColumnMappingElement newColumn;

	private String selectedTreeItemText;

	private static String COLUMN_NAME = Messages.getString( "dataset.editor.columnName" );
	private static String XPATH_NAME = Messages.getString( "dataset.editor.xpathexpression" );
	private static String TYPE_NAME = Messages.getString( "dataset.editor.datatype" );
	private static String DEFAULT_PAGE_NAME = Messages.getString( "xPathChoosePage.messages.xmlColumnMapping" );
	private static String DEFAULT_PAGE_Message = Messages.getString( "wizard.title.defineColumnMapping" );
	
	private static String[] dataTypeDisplayNames = new String[]{
			Messages.getString( "datatypes.dateTime" ), //$NON-NLS-1$
			Messages.getString( "datatypes.decimal" ), //$NON-NLS-1$
			Messages.getString( "datatypes.float" ), //$NON-NLS-1$
			Messages.getString( "datatypes.integer" ), //$NON-NLS-1$
			Messages.getString( "datatypes.date" ),
			Messages.getString( "datatypes.time" ),
			Messages.getString( "datatypes.string" )
	};
	
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
		schemaFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
		if ( schemaFileName == null || schemaFileName.trim( ).equals( "" ) )
			schemaFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
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
				// TODO Auto-generated catch block
				e.printStackTrace( );
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
		schemaFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
		if ( schemaFileName == null || schemaFileName.trim( ).equals( "" ) )
			schemaFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
		if ( selectedTreeItemText != null )
			populateXMLTree( );
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#refresh(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void refresh( DataSetDesign dataSetDesign )
	{
		DEFAULT_PAGE_Message = Messages.getString( "xPathChoosePage.messages.xmlColumnMapping" );
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
		layout.numColumns = 1;
		composite.setLayout( layout );

		createTopComposite( composite );
		createBottomComposite( composite );

		setPageProperties( );		
		return composite;

	}

	/**
	 * create the top composite
	 * 
	 * @param parent
	 */
	private void createTopComposite( Composite parent )
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

		btnAdd = new Button( btnComposite, SWT.NONE );
		btnAdd.setText( ">" ); //$NON-NLS-1$
		//TODO to externalize into message file
		btnAdd.setToolTipText( "Use the selected node as column mapping" );
		btnAdd.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				String pathStr = createXPath( selectedItem );
				String name = selectedItem.getText( );
				int type = -1;
				if ( selectedItem.getData( ) instanceof ATreeNode )
				{
					// if the select treeItem is attribute, the name should not
					// start with '@'
					ATreeNode node = (ATreeNode) selectedItem.getData( );
					if ( node.getType( ) == ATreeNode.ATTRIBUTE_TYPE )
						name = (String) node.getValue( );
					try
					{
						type = DataTypes.getType( node.getDataType( ) );
					}
					catch ( OdaException e1 )
					{
						type = DataTypes.STRING;
					}
				}
				ColumnMappingDialog columnDialog = new ColumnMappingDialog( getShell( ),
						DEFAULT_PAGE_NAME,
						name,
						pathStr, type );
				String relationInfo;
				if ( columnDialog.open( ) == Window.OK )
				{
					ColumnMappingElement columnElement = columnDialog.getColumnMapping( );
					if ( isUniqueName( columnElement.getColumnName( ),
							columnElement ) )
					{
						columnMap.put( columnElement.getColumnName( ),
								columnElement );
						columnMappingList.add( columnElement );

						refreshColumnMappingViewer( );

						relationInfo = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION );
						if ( relationInfo != null
								&& relationInfo.trim( ).length( ) > 0 )
						{
							String tableInfo = XMLRelationInfoUtil.getTableRelationInfo( relationInfo,
									tableName );
							if ( tableInfo != null
									&& tableInfo.trim( ).length( ) > 0 )
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
							XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
									saveQueryString( ) );
					}
					setPageProperties( );
				}
			}
		} );
		
		createRightGroup( composite );
	}

	/**
	 * create bottom composite
	 * 
	 * @param parent
	 */
	private void createBottomComposite( Composite parent )
	{
		Group previewGroup = new Group( parent, SWT.VERTICAL );
		GridData data = new GridData( GridData.FILL_BOTH );
		data.heightHint = 80;
		previewGroup.setLayoutData( data );
		previewGroup.setText( Messages.getString( "ColumnMappingDialog.info.dataPreview" ) );
		previewGroup.setLayout( new FillLayout( ) );
		previewViewer = new ResultSetTableViewer( previewGroup,
				true,
				true,
				true );
		previewViewer.getViewer( ).setHeaderVisible( true );
	}

	/**
	 * create right group composite
	 * 
	 * @param composite2
	 */
	private void createRightGroup( Composite composite2 )
	{
		FormData data = new FormData( );
		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( btnComposite, 5 );
		data.right = new FormAttachment( 100, -5 );
		data.bottom = new FormAttachment( 100, -5 );
		data.width = DEFAULT_WIDTH;
		rightGroup = new Group( composite2, SWT.NONE );

		rightGroup.setLayout( new FillLayout( ) );
		rightGroup.setText( Messages.getString( "xPathChoosePage.messages.xmlColumnMapping" ) );
		rightGroup.setLayoutData( data );

		rightGroup.setEnabled( true );
		columnMappingTable = new ColumnMappingTableViewer( rightGroup,
				true,
				true,
				true );

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

							if ( newColumn == null )
							{
								newColumn = new ColumnMappingElement( );
							}

							inputList.add( newColumn );
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
	 * check whether the column is duplicated
	 * 
	 * @param newColumn1
	 * @return
	 */
	private boolean isUniqueName( String columnName, ColumnMappingElement actualElement )
	{
		boolean success = true;
		if ( columnMap != null )
		{
			if ( columnMap.get( columnName ) != actualElement
					&& columnMap.get( columnName ) != null )
			{
				setDetailsMessage( Messages.getFormattedString( "error.columnMapping.sameColumnName",
						new Object[]{
							columnName
						} ),
						IMessageProvider.ERROR );
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
		if ( data instanceof ATreeNode )
		{
			columnPath = generateXpathFromTreeItem( select );
		}
		return XPathPopulationUtil.populateColumnPath( this.selectedTreeItemText,
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
				&& treeItem.getParentItem( ).getData( ) instanceof ATreeNode )
		{
			treeItem = treeItem.getParentItem( );

			if ( ( (ATreeNode) treeItem.getData( ) ).getType( ) == ATreeNode.ELEMENT_TYPE
					&& columnPath.trim( ).length( ) > 0 )
				columnPath = treeItem.getText( ) + "/" + columnPath;
			else if ( ( (ATreeNode) treeItem.getData( ) ).getType( ) == ATreeNode.ATTRIBUTE_TYPE )
				columnPath = columnPath + "/" + treeItem.getText( );
		}
		if ( !columnPath.startsWith( "/" ) )
			columnPath = "/" + columnPath;
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
		data.height = DEFAULT_HEIGHT;
		treeGroup = new Group( composite2, SWT.NONE );
		treeGroup.setLayout( new FillLayout( ) );
		treeGroup.setLayoutData( data );
		availableXmlTree = new Tree( treeGroup, SWT.MULTI
				| SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		availableXmlTree.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				TreeItem items[] = availableXmlTree.getSelection( );
				for ( int i = 0; i < items.length; i++ )
				{
					selectedItem = items[0];
					if ( items[i].getGrayed( ) )
					{
						availableXmlTree.setRedraw( false );
						availableXmlTree.deselectAll( );
						availableXmlTree.setRedraw( true );
						availableXmlTree.redraw( );
					}
				}
				if ( selectedItem != null )
					btnAdd.setEnabled( true );
				else
					btnAdd.setEnabled( false );

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
							removeSelectedItem( );
							setPageProperties( ); 
						}
					}

				} );

		columnMappingTable.getRemoveButton( )
				.addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						removeSelectedItem( );
						setPageProperties( );
					}

					public void widgetDefaultSelected( SelectionEvent e )
					{
					}

				} );
    	//TODO to externalize into message file
		columnMappingTable.getRemoveButton( )
				.setToolTipText( "Remove column mapping" );

		columnMappingTable.getRemoveMenuItem( )
				.addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						removeSelectedItem( );
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
		//TODO to externalize into message file
		columnMappingTable.getUpButton( )
				.setToolTipText( "Move column mappping Up" );

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
		//TODO to externalize into message file
		columnMappingTable.getDownButton( )
				.setToolTipText("Move column mapping Down" );
	}

	/**
	 * remove the selected item in the table
	 * 
	 */
	private void removeSelectedItem( )
	{
		int index = columnMappingTable.getViewer( )
				.getTable( )
				.getSelectionIndex( );
		int count = columnMappingTable.getViewer( ).getTable( ).getItemCount( );

		if ( index > -1 && index < count )
		{
			TableItem item = columnMappingTable.getViewer( )
					.getTable( )
					.getItem( index );
			Object element = item.getData( );
			String elementName = "";
			if ( element instanceof ColumnMappingElement
					&& element != newColumn )
			{
				ColumnMappingElement entry = (ColumnMappingElement) element;
				elementName = (String) entry.getColumnName( );

				columnMappingTable.getViewer( ).getTable( ).select( index );

				this.columnMap.remove( elementName );
				this.columnMappingList.remove( index );

				String str = XMLRelationInfoUtil.replaceInfo( this.tableName,
						saveQueryString( ),
						XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
				XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
						str );
			}
		}
		if ( columnMappingList.size( ) <= 0 )
		{
			String str = XMLRelationInfoUtil.replaceInfo( this.tableName,
					"",
					XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
					str );
		}
		refreshColumnMappingViewer( );
	}

	/**
	 * remove all items of the table
	 * 
	 */
	private void removeAllItem( )
	{
		int count = columnMappingTable.getViewer( ).getTable( ).getItemCount( );
		for ( int index = 0; index < count - 1; index++ )
		{
			TableItem item = columnMappingTable.getViewer( )
					.getTable( )
					.getItem( 0 );

			Object element = item.getData( );
			String elementName = "";
			if ( element instanceof ColumnMappingElement )
			{
				ColumnMappingElement entry = (ColumnMappingElement) element;
				elementName = (String) entry.getColumnName( );
			}
			columnMappingTable.getViewer( ).getTable( ).remove( 0 );
			this.columnMap.remove( elementName );
			this.columnMappingList.remove( 0 );
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
			treeNode = SchemaPopulationUtil.getSchemaTree( schemaFileName, true, numberOfElement );
			Object[] childs = treeNode.getChildren( );
			populateTreeItems( availableXmlTree, childs, 0 );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
	}

	/**
	 * 
	 * @param tree
	 * @param node
	 */
	private void populateTreeItems( Object tree, Object[] node, int level )
	{
		level ++;
		if( level > 10 )
			return;
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

			treeItem.setData( treeNode );
			int type = treeNode.getType( );
			if ( type == ATreeNode.ATTRIBUTE_TYPE )
				treeItem.setText( "@" + treeNode.getValue( ).toString( ) );
			else
				treeItem.setText( treeNode.getValue( ).toString( ) );
			if ( treeNode.getChildren( ) != null
					&& treeNode.getChildren( ).length > 0 )
				populateTreeItems( treeItem, treeNode.getChildren( ), level );

			String populateString = XPathPopulationUtil.populateColumnPath( selectedTreeItemText,
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
					selectedItem = treeItem;
				}
				else
					treeItem.setBackground( new Color( Display.getCurrent( ),
							255,
							255,
							0 ) );
				setExpanded( treeItem );
			}
		}
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

		columnMappingTable.getViewer( ).setCellModifier( new ICellModifier( ) {

			public boolean canModify( Object element, String property )
			{

				if ( element == newColumn && !property.equals( COLUMN_NAME ) )
					return false;
				else
					return true;
			}

			public Object getValue( Object element, String property )
			{
				Object value = null;
				try
				{
					if ( property.equals( COLUMN_NAME ) )
						value = ( (ColumnMappingElement) element ).getColumnName( );
					else if ( property.equals( XPATH_NAME ) )
						value = ( (ColumnMappingElement) element ).getXPath( );
					else if ( property.equals( TYPE_NAME ) )
					{
						String temp =  ( (ColumnMappingElement) element ).getType();
						if ( temp == null )
						{
							value = new Integer( 0 );
						}
						else
						{
							for ( int i = 0; i < dataTypeDisplayNames.length; i++ )
							{
								if ( temp.equals( dataTypeDisplayNames[i] ) )
								{
									value = new Integer( i );
									break;
								}
							}
						}
					}
					
				}
				catch ( Exception ex )
				{
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

			public void modify( Object element, String property, Object value )
			{
				Object actualElement = ( (TableItem) element ).getData( );
				if ( value != null )
				{
					setDetailsMessage( DEFAULT_PAGE_Message, IMessageProvider.NONE );
					if ( property.equals( COLUMN_NAME ) )
					{
						if ( isUniqueName( (String) value , (ColumnMappingElement) actualElement ) )
						{
							( (ColumnMappingElement) actualElement ).setColumnName( (String) value );
						}
						else
							return;
					}
					else if ( property.equals( XPATH_NAME ) )
					{
						if( !isXpathValid(((String)value)) )
						{
							setDetailsMessage( Messages.getFormattedString( "error.invalidXpath",
									new Object[]{
									value
									} ),
									IMessageProvider.ERROR );
							return;
						}
						else
							( (ColumnMappingElement) actualElement ).setXPath( (String) value );
					}
					else if ( property.equals( TYPE_NAME ) )
					{
	                    int selectedType = ((Integer)value).intValue();
	                   
	            		( (ColumnMappingElement) actualElement ).setType( dataTypeDisplayNames[selectedType] );
					}
					columnMappingTable.getViewer( )
							.update( ( (TableItem) element ).getData( ), null );
					if ( actualElement instanceof ColumnMappingElement )
					{
						if ( newColumn != null
								&& newColumn.getColumnName( ) != null
								&& newColumn.getColumnName( ).trim( ).length( ) > 0 )
						{
							columnMap.put( newColumn.getColumnName( ), newColumn );
							columnMappingList.add( newColumn );
							clearNewColumnMapping( );
							columnMappingTable.getViewer( ).refresh( );
							setPageProperties( );
						}
						refreshXMLConnection( );
					}
				}
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
		if( value == null )
			return true;
		return !value.trim( ).matches( ".*\\Q[\\E.*\\Q]\\E.+" );
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

	private void clearNewColumnMapping( )
	{
		newColumn = null;
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
		String queryString = "";
		String tablePath = "[" + selectedTreeItemText + "]";
		queryString = tableName
				+ RelationInformation.CONST_TABLE_COLUMN_DELIMITER + tablePath
				+ RelationInformation.CONST_TABLE_COLUMN_DELIMITER;

		Iterator rowObj = this.columnMappingList.iterator( );
		if ( rowObj != null )
		{
			String rowStr = "";
			while ( rowObj.hasNext( ) )
			{
				ColumnMappingElement element = (ColumnMappingElement) rowObj.next( );
				rowStr = "{"
						+ element.getColumnName( ) + ";" + element.getTypeStandardString()
						+ ";" + element.getXPath( ) + "}";
				if ( rowObj.hasNext( ) )
					rowStr = rowStr + ",";
				queryString = queryString + rowStr;
			}
		}
		return queryString;
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
		clearNewColumnMapping( );
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
	public void setSchmeFile( String fileName )
	{
		this.schemaFileName = fileName;

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
			if ( element != newColumn )
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
			else if ( columnIndex == 0 )
			{
				value = Messages.getString( "ColumnMappingDialog.prompt.new" );
			}
		}
		catch ( Exception ex )
		{
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
		boolean active = columnMappingExist && dataFileExist;
		columnMappingTable.getDownButton( ).setEnabled( columnMappingExist );
		columnMappingTable.getUpButton( ).setEnabled( columnMappingExist );
		columnMappingTable.getRemoveButton( ).setEnabled( columnMappingExist );
		columnMappingTable.getRemoveMenuItem( ).setEnabled( columnMappingExist );
		columnMappingTable.getRemoveAllMenuItem( )
				.setEnabled( columnMappingExist );
		previewViewer.getRefreshButton( ).setEnabled( active );
		previewViewer.getRefreshMenu( ).setEnabled( active );
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
		if ( dataSetDesign != null )
		{
			if ( dataSetDesign.getQueryText( ) != null
					&& !dataSetDesign.getQueryText( )
							.equals( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) ) )
			{
				DataSetDesignPopulator.populateResultSet( dataSetDesign );
				dataSetDesign.setQueryText( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
			}
			dataSetDesign.getPublicProperties( )
					.findProperty( Constants.CONST_PROP_XML_FILE )
					.setNameValue( Constants.CONST_PROP_XML_FILE,
							XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XML_FILE ) );
			dataSetDesign.getPublicProperties( )
					.findProperty( Constants.CONST_PROP_MAX_ROW )
					.setNameValue( Constants.CONST_PROP_MAX_ROW,
							XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_MAX_ROW ) );
		}
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
}
