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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.xml.Constants;
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
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
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
import org.eclipse.swt.widgets.TreeItem;

/**
 * Column mapping page to define the column mapping with xml data set
 */

public class ColumnMappingPage extends DataSetWizardPage
		implements
			ITableLabelProvider
{

    private static final String RIGHT_SQUARE_BRACKET = "]";     //$NON-NLS-1$
    private static final String LEFT_SQUARE_BRACKET = "[";      //$NON-NLS-1$
    private static final String LEFT_CURLY_BRACKET = "{";       //$NON-NLS-1$
    private static final String RIGHT_CURLY_BRACKET = "}";      //$NON-NLS-1$
    private static final String LEFT_SHARP_ANGLED_BRACKET = "<";       //$NON-NLS-1$
    private static final String RIGHT_SHARP_ANGLED_BRACKET = ">";      //$NON-NLS-1$
    
    private static final String COMMA = ",";                    //$NON-NLS-1$
    private static final String SEMICOLON = ";";                //$NON-NLS-1$
    private static final String UNDERSCORE = "_";               //$NON-NLS-1$
    private static final String EMPTY_STRING = "";              //$NON-NLS-1$
    private static final String DOUBLE_QUOTE = "\"";              //$NON-NLS-1$
    
    private XMLTreeViewer availableXmlTree;
	private Button btnPreview;

	private ColumnMappingTableViewer columnMappingTable;
	private Group tableViewerGroup;
	private ATreeNode treeNode;

	private String tableName;
	private String xsdFileName;
	private String lastXsdFileName;
	private String xmlFileName;
	private String lastXmlFileName;
	private String xmlEncoding;
	private String nameSpace;
	private Map columnMap;
	private List columnMappingList = new ArrayList( );

	private String selectedTreeItemText;
	
	protected boolean supportsXMLParameter;

	private static String COLUMN_NAME = Messages.getString( "dataset.editor.columnName" );                         //$NON-NLS-1$
	private static String XPATH_NAME = Messages.getString( "dataset.editor.xpathexpression" );                     //$NON-NLS-1$
	private static String TYPE_NAME = Messages.getString( "dataset.editor.datatype" );                             //$NON-NLS-1$
	private static String DEFAULT_PAGE_NAME = Messages.getString( "xPathChoosePage.messages.xmlColumnMapping" );   //$NON-NLS-1$
	private static String DEFAULT_PAGE_Message = Messages.getString( "wizard.title.defineColumnMapping" );         //$NON-NLS-1$
	private static String PATH_SEPERATOR = "/"; //$NON-NLS-1$
	private static String ATTRIBUTE_MARK = "@"; //$NON-NLS-1$
	
	private static String[] dataTypeDisplayNames = new String[]{
			Messages.getString( "datatypes.dateTime" ),  //$NON-NLS-1$
			Messages.getString( "datatypes.decimal" ),   //$NON-NLS-1$
			Messages.getString( "datatypes.float" ),     //$NON-NLS-1$
			Messages.getString( "datatypes.integer" ),   //$NON-NLS-1$
			Messages.getString( "datatypes.date" ),      //$NON-NLS-1$
			Messages.getString( "datatypes.time" ),      //$NON-NLS-1$
			Messages.getString( "datatypes.string" ),    //$NON-NLS-1$
			Messages.getString( "datatypes.boolean" )    //$NON-NLS-1$
	};
	
	private static Logger logger = Logger.getLogger( ColumnMappingPage.class.getName( ) );
	
	/**
	 * @param string
	 */
	public ColumnMappingPage( )
	{
		this( Messages.getString( "wizard.title.newDataSet" ) ); //$NON-NLS-1$
		Arrays.sort( dataTypeDisplayNames );
	}

	/**
	 * @param pageName
	 */
	public ColumnMappingPage( String pageName )
	{
		super( pageName );
		this.setTitle( pageName );
		DEFAULT_PAGE_Message = Messages.getString( "wizard.title.defineColumnMapping" ); //$NON-NLS-1$
		this.setMessage( DEFAULT_PAGE_Message );
		this.columnMap = new HashMap( );
		this.columnMappingList = new ArrayList( );
		this.setPageComplete( false );
		this.supportsXMLParameter = true;
		Arrays.sort( dataTypeDisplayNames );
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		ScrolledComposite sComposite = new ScrolledComposite( parent,
				SWT.H_SCROLL | SWT.V_SCROLL );
		sComposite.setLayout( new GridLayout( ) );
		sComposite.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		sComposite.setMinWidth( 600 );
		sComposite.setExpandHorizontal( true );

		Control control = createPageControl( sComposite );

		if ( XMLInformationHolder.hasDestroyed( ) )
			XMLInformationHolder.start( this.getInitializationDesign( ) );
		initializeControl( );
		if ( selectedTreeItemText != null )
			populateXMLTree( );

		Point size = control.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		control.setSize( size.x, size.y );

		sComposite.setContent( control );
		setControl( sComposite );

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
				setMessage( Messages.getString( "error.columnMapping.createPage" ), //$NON-NLS-1$
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
		boolean needsRefresh = false;
		if( !valueEquals( selectedTreeItemText, XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XPATH ) ) )
		{
			selectedTreeItemText = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XPATH );
			needsRefresh = true;
		}
		if( !valueEquals( xsdFileName, XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST ) ) )
		{
			xsdFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
			needsRefresh = true;
		}
		if( !valueEquals( xmlFileName, XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST ) ) )
		{
			xmlFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
			needsRefresh = true;
		}
		if( !valueEquals( xmlEncoding, XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST ) ) )
		{
			xmlEncoding = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST );
			needsRefresh = true;
		}

		/*if ( xsdFileName == null || xsdFileName.trim( ).equals( "" ) )
			xsdFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );*/
		if ( needsRefresh && selectedTreeItemText != null )
		{
			populateXMLTree( );
			setPageProperties( );
		}
	}
	
	private boolean valueEquals( String value1, String value2 )
	{
		if ( value1 == null )
		{
			return value2 == null;
		}
		return value1.equals( value2 );
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#refresh(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void refresh( DataSetDesign dataSetDesign )
	{
		DEFAULT_PAGE_Message = Messages.getString( "xPathChoosePage.messages.xmlColumnMapping" ); //$NON-NLS-1$
		if ( XMLInformationHolder.hasDestroyed( ) )
			XMLInformationHolder.start( dataSetDesign );
		refresh( );
		if ( getMessageType( ) == IMessageProvider.NONE )
		{
			this.setMessage( DEFAULT_PAGE_Message );
		}
	}
	
	/**
	 * 
	 * @param parent
	 * @return
	 */
	public Control createPageControl( Composite parent )
	{
		DEFAULT_PAGE_Message = Messages.getString( "wizard.title.defineColumnMapping" ); //$NON-NLS-1$
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
		GridData gridData = new GridData( GridData.FILL_BOTH );
		composite.setLayoutData( gridData );
		createLeftGroup( composite );
		availableXmlTree.getSingleButton( ).addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				TreeItem[] selectedMultiItems = availableXmlTree.getTree( ).getSelection( );
				if ( selectedMultiItems == null )
				{
					setMessage( Messages.getString( "error.columnMapping.SelectedTreeItem.notNull" ), //$NON-NLS-1$
							ERROR );
					availableXmlTree.getSingleButton( ).setEnabled( false );
					availableXmlTree.getMultiButton( ).setEnabled( false );
					return;
				}
				for ( int i = 0; i < selectedMultiItems.length; i++ )
				{
					TreeItem selectedItem = selectedMultiItems[i];
					ATreeNode treeNode = ( (TreeNodeData) selectedItem.getData( ) ).getTreeNode( );
					String pathStr = createXPath( treeNode );
					ColumnMappingElement columnElement = null;
					if ( selectedMultiItems.length > 1 )
					{
						columnElement = createSingleElement( treeNode, pathStr );
					}
					else
					{
						String name = (String) treeNode.getValue( );
						int type = -1;
						try
						{
							type = DataTypes.getType( treeNode.getDataType( ) );
						}
						catch ( OdaException e1 )
						{
							type = DataTypes.STRING;
						}
								ColumnMappingDialog columnDialog = new ColumnMappingDialog( getShell( ),
										DEFAULT_PAGE_NAME,
										name,
										pathStr,
										type,
										true,
										supportsXMLParameter );
						if ( columnDialog.open( ) == Window.OK )
						{
							columnElement = columnDialog.getColumnMapping( );
						}
						else
						{
							return;
						}
					}
					if ( columnElement != null )
					{
						updateColumnMappingElement( columnElement );
					}
				}
				availableXmlTree.getTree( ).setSelection( new TreeItem[0] );
				availableXmlTree.getSingleButton( ).setEnabled( false );
			}
		} );
		availableXmlTree.getMultiButton( ).addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				TreeItem[] selectedMultiItems = availableXmlTree.getTree( ).getSelection( );
				if ( selectedMultiItems == null
						|| selectedMultiItems.length == 0 )
				{
					selectedMultiItems = availableXmlTree.getTree( ).getItems( );
				}
				HashSet selectedNodes = new HashSet( );
				if ( selectedMultiItems.length == 1 )
				{
					/* Only one node is selected at a time */
					if ( !handleSelectedItem( selectedMultiItems[0], selectedNodes ) )
					{
						setMessage( Messages.getString( "error.columnMapping.columnElement.create" ),     //$NON-NLS-1$
								ERROR );
					}
				}
				else
				{
					/* Multiple nodes are selected at the same time */
					for ( int i = 0; i < selectedMultiItems.length; i++ )
					{
						TreeItem selectedItem = selectedMultiItems[i];
						if ( !handleSelectedItem( selectedItem, selectedNodes ) )
						{
							setMessage( Messages.getString( "error.columnMapping.columnElement.create" ),    //$NON-NLS-1$
									ERROR );
							break;
						}
					}
				}
				availableXmlTree.getTree( ).setSelection( availableXmlTree.getTree( ).getItem( 0 ) );
				availableXmlTree.getSingleButton( ).setEnabled( false );
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
		btnPreview.setText( Messages.getString( "menu.button.preview" ) ); //$NON-NLS-1$
		btnPreview.setToolTipText( Messages.getString( "ColumnMappingTable.previewButton.tooltip" ) ); //$NON-NLS-1$
		btnPreview.addSelectionListener( getPreviewButtonAdapter( ) );

		GridData gd = new GridData( );
		gd.horizontalAlignment = SWT.END;
		btnPreview.setLayoutData( gd );
	}

	protected SelectionAdapter getPreviewButtonAdapter( )
	{
		return new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				XMLDataPreviewDialog previewDialog = new XMLDataPreviewDialog( getShell( ), getHostResourceIdentifiers( ) );
				if ( previewDialog.open( ) == IDialogConstants.CLOSE_ID )
				{
					previewDialog.close( );
				}
			}
		};
	}

	private void createRightGroup( Composite composite )
	{
		tableViewerGroup = new Group( composite, SWT.NONE );
		tableViewerGroup.setText( Messages.getString( "xPathChoosePage.messages.xmlColumnMapping" ) ); //$NON-NLS-1$

		GridLayout layout = new GridLayout( );
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		layout.verticalSpacing = 10;
		tableViewerGroup.setLayout( layout );
		FormData data = new FormData( );
		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( availableXmlTree.getBtnComposite( ), 5 );
		data.right = new FormAttachment( 100, 0 );
		data.bottom = new FormAttachment( 100, -5 );
		tableViewerGroup.setLayoutData( data );
		tableViewerGroup.setEnabled( true );
		createTableViewerGroup( tableViewerGroup );
		createPreviewButton( tableViewerGroup );
	}

	/**
	 * create right group composite
	 * 
	 * @param composite2
	 */
	private void createTableViewerGroup( Composite composite2 )
	{
		columnMappingTable = new ColumnMappingTableViewer( composite2,
				true,
				true,
				true );
		columnMappingTable.getControl( ).setLayoutData( new GridData( GridData.FILL_BOTH ) );

		columnMappingTable.getViewer( ).getTable( ).setHeaderVisible( true );
		columnMappingTable.getViewer( ).getTable( ).setLinesVisible( true );

		TableColumn column = new TableColumn( columnMappingTable.getViewer( )
				.getTable( ), SWT.LEFT );
		column.setText( COLUMN_NAME ); 
		column.setWidth( 100 );
		column = new TableColumn( columnMappingTable.getViewer( ).getTable( ),
				SWT.LEFT );
		column.setText( XPATH_NAME );
		column.setWidth( 100 );
		column = new TableColumn( columnMappingTable.getViewer( ).getTable( ),
				SWT.LEFT );
		column.setText( TYPE_NAME ); 
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

		addListenersAndToolTip( );
	}
	
	/**
	 * Map all the children elements of the specific TreeItem to be the columns
	 * If the TreeItem has no child, it itself is mapped to be a column
	 * 
	 * @param aTreeNode
	 * @param treeItem
	 */
	private void addChildrenElements( ATreeNode aTreeNode, HashSet selectedNodes )
	{
		try
		{
			if ( aTreeNode.getType( ) == ATreeNode.ATTRIBUTE_TYPE
					|| ( aTreeNode.getType( ) == ATreeNode.ELEMENT_TYPE && ( aTreeNode.getChildren( ) == null || aTreeNode.getChildren( ).length == 0 ) ) )
			{
				if ( selectedNodes != null
						&& !selectedNodes.contains( aTreeNode ) )
				{
					String pathStr = createXPath( aTreeNode );
					updateColumnMappingElement( createSingleElement( aTreeNode,
							pathStr ) );
					selectedNodes.add( aTreeNode );
				}
			}
			else
			{
				Object[] children = aTreeNode.getChildren( );
				if ( children != null )
				{
					for ( int i = 0; i < children.length; i++ )
					{
						addChildrenElements( (ATreeNode) children[i], selectedNodes );
					}
				}
			}
		}
		catch ( OdaException e )
		{
			logger.log( Level.INFO, e.getMessage( ), e );
			setMessage( Messages.getString( "error.columnMapping.ATreeNode.getChildren" ),   //$NON-NLS-1$
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
	 * get the xpath according to a specific ATreeNode object
	 * 
	 * @param ATreeNode aTreeNode
	 * @return
	 */
	private String createXPath( ATreeNode aTreeNode )
	{
		if ( aTreeNode == null )
			return null;

		String columnPath = generateXpathFromATreeNode( aTreeNode );
		return XPathPopulationUtil.populateColumnPath( getRootPathWithOutFilter( ),
				columnPath );
	}
	
	/**
	 * Return the tailored root path without filter definition.
	 * @return
	 */
	private String getRootPathWithOutFilter( )
	{
		return selectedTreeItemText.replaceAll( "\\Q[\\E.*\\Q]\\E", EMPTY_STRING ); //$NON-NLS-1$
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
	 * create the left group composite
	 * 
	 * @param composite2
	 */
	private void createLeftGroup( Composite composite2 )
	{
		availableXmlTree = new XMLTreeViewer( composite2, true );
		availableXmlTree.getSingleButton( )
				.setToolTipText( Messages.getString( "ColumnMappingPage.AddSingleButton.tooltip" ) ); //$NON-NLS-1$
		availableXmlTree.getMultiButton( )
		.setToolTipText( Messages.getString( "ColumnMappingPage.AddAllButton.tooltip" ) ); //$NON-NLS-1$
		availableXmlTree.getTree( ).addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				TreeItem[] selectedMultiItems = availableXmlTree.getTree( ).getSelection( );
				if ( selectedMultiItems.length > 1 )
				{
					for ( int i = 0; i < selectedMultiItems.length; i++ )
					{
						if ( selectedMultiItems[i].getGrayed( ) )
						{
							availableXmlTree.getTree( ).setRedraw( false );
							availableXmlTree.getTree( ).deselectAll( );
							availableXmlTree.getTree( ).setRedraw( true );
							availableXmlTree.getTree( ).redraw( );
						}
					}
					setMessage( DEFAULT_PAGE_Message );
					availableXmlTree.getMultiButton( ).setEnabled( true );
					availableXmlTree.getSingleButton( ).setEnabled( true );
				}
				else if ( selectedMultiItems.length == 1 )
				{
					TreeItem selectedItem = selectedMultiItems[0];
					if ( selectedItem.getGrayed( ) )
					{
						availableXmlTree.getTree( ).setRedraw( false );
						availableXmlTree.getTree( ).deselectAll( );
						availableXmlTree.getTree( ).setRedraw( true );
						availableXmlTree.getTree( ).redraw( );
					}
					if ( selectedItem != null )
					{
						setMessage( DEFAULT_PAGE_Message );
						availableXmlTree.getSingleButton( ).setEnabled( true );
						availableXmlTree.getMultiButton( ).setEnabled( true );
					}
					else
					{
						availableXmlTree.getSingleButton( ).setEnabled( false );
						availableXmlTree.getMultiButton( ).setEnabled( false );
					}
				}
				else
				{
					availableXmlTree.getSingleButton( ).setEnabled( false );
					availableXmlTree.getMultiButton( ).setEnabled( true );
				}
				enableAllTableSideButtons( false );
			}

		} );

	}

	/**
	 * add the listener to the columnMappingTable.
	 */
	private void addListenersAndToolTip( )
	{
		columnMappingTable.getViewer( )
				.addDoubleClickListener( new IDoubleClickListener( ) {

					public void doubleClick( DoubleClickEvent e )
					{
						doEdit( );
						resetUpDownButton( );
					}
				} );

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
							int index = columnMappingTable.getViewer( )
									.getTable( )
									.getSelectionIndex( );
							removeSelectedItems( );
							updateSelectedItemsSelection( index );
							setPageProperties( ); 
						}
					}

				} );

		columnMappingTable.getViewer( ).getTable( ).addListener( SWT.MouseDown,
				new Listener( ) {

					public void handleEvent( Event event )
					{
						availableXmlTree.getTree( ).deselectAll( );
						if ( columnMappingTable.getViewer( )
								.getTable( )
								.getSelectionCount( ) == 1 )
						{
							enableAllTableSideButtons( true );
							resetUpDownButton( );
						}
						else
						{
							enableAllTableSideButtons( false );
							if ( columnMappingTable.getViewer( )
									.getTable( )
									.getSelectionCount( ) > 1 )
							{
								columnMappingTable.getRemoveButton( )
										.setEnabled( true );
							}
						}
						availableXmlTree.getSingleButton( ).setEnabled( false );
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
				.setToolTipText( Messages.getString( "ColumnMappingTable.editButton.tooltip" ) ); //$NON-NLS-1$

		columnMappingTable.getRemoveButton( )
				.addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						int index = columnMappingTable.getViewer( )
								.getTable( )
								.getSelectionIndex( );
						removeSelectedItems( );
						updateSelectedItemsSelection( index );
						setPageProperties( );
					}

					public void widgetDefaultSelected( SelectionEvent e )
					{
					}

				} );
		columnMappingTable.getRemoveButton( )
				.setToolTipText( Messages.getString( "ColumnMappingTable.removeButton.tooltip" ) ); //$NON-NLS-1$

		columnMappingTable.getRemoveMenuItem( )
				.addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						int index = columnMappingTable.getViewer( )
								.getTable( )
								.getSelectionIndex( );
						removeSelectedItems( );
						updateSelectedItemsSelection( index );
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
						updateSelectedItemsSelection( 0 );
						setPageProperties( );
					}

					public void widgetDefaultSelected( SelectionEvent e )
					{
						widgetSelected( e );
					}
				} );
		
		columnMappingTable.getAddButton( ).addSelectionListener( new SelectionListener( ) {

					public void widgetSelected( SelectionEvent e )
					{
						manuallyAddOneColumn( );
						columnMappingTable.getViewer( )
								.getTable( )
								.setSelection( -1 );
						enableAllTableSideButtons( false );
					}

					public void widgetDefaultSelected( SelectionEvent e )
					{
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

		columnMappingTable.getUpButton( )
				.setToolTipText( Messages.getString( "ColumnMappingTable.upButton.tooltip" ) ); //$NON-NLS-1$

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

		columnMappingTable.getDownButton( )
				.setToolTipText( Messages.getString( "ColumnMappingTable.downButton.tooltip" ) ); //$NON-NLS-1$
	}
	
	/**
	 * Add a single column created by the user typing via a mapping dialog
	 * 
	 */
	private void manuallyAddOneColumn( )
	{
		ColumnMappingElement columnElement;
		ColumnMappingDialog columnDialog = new ColumnMappingDialog( getShell( ),
				DEFAULT_PAGE_NAME,
				null,
				null,
				-1,
				false,
				supportsXMLParameter );
		if ( columnDialog.open( ) == Window.OK )
		{
			columnElement = columnDialog.getColumnMapping( );
			if ( columnElement != null )
			{
				updateColumnMappingElement( columnElement );
			}
		}
	}
	
	/**
	 * Enable or disable all the buttons the right table, say, "Edit", "Remove", "Up" and "Down" buttons 
	 * 
	 * @param enabled
	 */
	private void enableAllTableSideButtons( boolean enabled )
	{
		columnMappingTable.getEditButton( )
				.setEnabled( enabled );
		columnMappingTable.getRemoveButton( )
				.setEnabled( enabled );
		columnMappingTable.getUpButton( ).setEnabled( enabled );
		columnMappingTable.getDownButton( )
				.setEnabled( enabled );
	}

	private void updateSelectedItemsSelection( int nextIndex )
	{
		int itemCount = columnMappingTable.getViewer( ).getTable( ).getItemCount( );
		
		availableXmlTree.getTree( ).deselectAll( );
		columnMappingTable.getViewer( ).getTable( ).deselectAll( );

		if ( itemCount > nextIndex )
		{
			columnMappingTable.getViewer( ).getTable( ).select( nextIndex );
		}
		else if ( itemCount > 0 )
		{
			columnMappingTable.getViewer( ).getTable( ).select( itemCount - 1 );
		}
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
					false,
					supportsXMLParameter );
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
						editedColumnName = editedColumnName + UNDERSCORE + appendix;
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
			setMessage( Messages.getString( "error.columnMapping.columnElement.edit" ),      //$NON-NLS-1$
					ERROR );
		}
	}
	
	/**
	 * Reset the up and down buttons' state
	 * 
	 */
	private void resetUpDownButton( )
	{
		if ( columnMappingTable.getViewer( ).getTable( ).getItemCount( ) == 1 )
		{
			columnMappingTable.getUpButton( ).setEnabled( false );
			columnMappingTable.getDownButton( ).setEnabled( false );
		}
		else if ( columnMappingTable.getViewer( )
				.getTable( )
				.getSelectionIndex( ) == 0 )
		{
			columnMappingTable.getUpButton( ).setEnabled( false );
			columnMappingTable.getDownButton( ).setEnabled( true );
		}
		else if ( columnMappingTable.getViewer( )
				.getTable( )
				.getSelectionIndex( ) == columnMappingTable.getViewer( )
				.getTable( )
				.getItemCount( ) - 1 )
		{
			columnMappingTable.getDownButton( ).setEnabled( false );
			columnMappingTable.getUpButton( ).setEnabled( true );
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
		String elementName = EMPTY_STRING;
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
				saveQueryString( ),
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
			resetUpDownButton( );
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
		if ( index > -1 && index <= count - 2 )
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
			resetUpDownButton( );
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
			this.availableXmlTree.getTree( ).removeAll( );
			
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

			// retrieved
			// Object url = StructureFactory.getModuleHandle( ).findResource(
			// schemaFileName,IResourceLocator.LIBRARY );
			// if( url != null )
			treeNode = SchemaPopulationUtil.getSchemaTree( xsdFileName,
					xmlFileName,
					xmlEncoding,
					numberOfElement,
					DesignSessionUtil.createRuntimeResourceIdentifiers( getHostResourceIdentifiers( ) ) );
			availableXmlTree.populateTree( treeNode, selectedTreeItemText, true, true );


			TreeItem[] selectedMultiItems = availableXmlTree.getTree( ).getSelection( );
			if ( selectedMultiItems == null ||
					selectedMultiItems.length == 0 )
			{
				availableXmlTree.getSingleButton( ).setEnabled( false );
				availableXmlTree.getMultiButton( ).setEnabled( false );
				this.setMessage( Messages.getString( "error.columnMapping.tableMappingXPathNotExist" ),      //$NON-NLS-1$
						ERROR );
			}
			else
			{
				availableXmlTree.getSingleButton( ).setEnabled( true );
				availableXmlTree.getMultiButton( ).setEnabled( true );
				this.setMessage( DEFAULT_PAGE_Message );
			}
		}
		catch ( Exception e )
		{
			logger.log( Level.INFO, e.getMessage( ), e );
			setMessage( Messages.getString( "error.columnMapping.createPage" ),           //$NON-NLS-1$
					ERROR );
		}
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
		String tablePath = LEFT_SQUARE_BRACKET
				+ selectedTreeItemText + RIGHT_SQUARE_BRACKET;
		StringBuffer queryString = new StringBuffer( tableName
				+ Constants.CONST_TABLE_COLUMN_DELIMITER + tablePath
				+ Constants.CONST_TABLE_COLUMN_DELIMITER);
		String rowStr = EMPTY_STRING;
		Iterator rowObj = this.columnMappingList.iterator( );
		while ( rowObj.hasNext( ) )
		{
			ColumnMappingElement element = (ColumnMappingElement) rowObj.next( );
			rowStr = LEFT_CURLY_BRACKET
					+ element.getColumnName( ) + SEMICOLON
					+ element.getTypeStandardString( ) + SEMICOLON
					+ element.getXPath( ) + RIGHT_CURLY_BRACKET;
			if ( rowObj.hasNext( ) )
				rowStr = rowStr + COMMA;
			queryString.append( rowStr );
		}
		if ( getNamespace( ).length( ) == 0 )
			return queryString.toString( );
		else
			return queryString.append( Constants.CONST_TABLE_COLUMN_DELIMITER + this.nameSpace ).toString( );

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

			String c1 = EMPTY_STRING, c2 = EMPTY_STRING, c3 = EMPTY_STRING; 

			if ( element instanceof ColumnMappingElement )
			{
				ColumnMappingElement colElement = (ColumnMappingElement) element;

				c1 = colElement.getColumnName( ) == null ? EMPTY_STRING
						: colElement.getColumnName( );
				c2 = colElement.getXPath( ) == null ? EMPTY_STRING
						: "version@/";
				c3 = colElement.getType( ) == null ? EMPTY_STRING : colElement.getType( );
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
					value = (String) ( (ColumnMappingElement) element ).getColumnName( ); 
					break;
				}
				case 1 :
				{
					value = TextProcessor.process((String) ( (ColumnMappingElement) element ).getXPath( ),"/");
					break;
				}
				case 2 :
				{
					value = (String) ( (ColumnMappingElement) element ).getType( ); 
					break;
				}
			}
		}
		catch ( Exception ex )
		{
			logger.log( Level.INFO, ex.getMessage( ), ex );
			ExceptionHandler.showException( getShell( ),
					Messages.getString( "error.label" ),       //$NON-NLS-1$
					ex.getMessage( ),
					ex );
		}
		if ( value == null )
		{
			value = EMPTY_STRING; 
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
		enableAllTableSideButtons( columnMappingExist
				&& columnMappingTable.getViewer( )
						.getTable( )
						.getSelectionCount( ) > 0 );
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
	
	/**
	 * 
	 * @return
	 * @throws OdaException 
	 */
	private String getNamespace( )
	{
		if ( this.nameSpace != null
				&& ( lastXsdFileName == null || lastXsdFileName.equals( xsdFileName ) )
				&& ( lastXmlFileName == null || lastXmlFileName.equals( xmlFileName ) ) )
		{
			return this.nameSpace;
		}
		Map prefixMapping = null;
		boolean start = true;
		try{
		if ( this.xsdFileName != null
					&& this.xsdFileName.trim( ).length( ) > 0 )
				prefixMapping = SchemaPopulationUtil.getPrefixMapping( xsdFileName,
						xmlEncoding,
						DesignSessionUtil.createRuntimeResourceIdentifiers( getHostResourceIdentifiers( ) ) );
			else
				prefixMapping = SchemaPopulationUtil.getPrefixMapping( xmlFileName,
						xmlEncoding,
						DesignSessionUtil.createRuntimeResourceIdentifiers( getHostResourceIdentifiers( ) ) );
		}
		catch ( OdaException ex )
		{
			return null;
		}
		StringBuffer nameSpaceBuf = new StringBuffer( );
		if ( prefixMapping.size( ) > 0 )
		{
			nameSpaceBuf.append( LEFT_SHARP_ANGLED_BRACKET );
			Iterator entryIter = prefixMapping.entrySet( ).iterator( );
			String key, value;
			while ( entryIter.hasNext( ) )
			{
				Entry entry = (Entry) entryIter.next( );
				key = entry.getKey( ).toString( );
				value = entry.getValue( ).toString( );
				if ( !start )
				{
					nameSpaceBuf.append( SEMICOLON
							+ DOUBLE_QUOTE + key + DOUBLE_QUOTE + COMMA
							+ DOUBLE_QUOTE + value + DOUBLE_QUOTE );
				}
				else
				{
					nameSpaceBuf.append( DOUBLE_QUOTE
							+ key + DOUBLE_QUOTE + COMMA + DOUBLE_QUOTE + value
							+ DOUBLE_QUOTE );
					start = false;
				}
			}
			nameSpaceBuf.append( RIGHT_SHARP_ANGLED_BRACKET );
		}
		this.nameSpace = nameSpaceBuf.toString( ).trim( );
		this.lastXsdFileName = this.xsdFileName;
		this.lastXmlFileName = this.xmlFileName;
		return this.nameSpace;
	}
	
	protected void updateDesign( DataSetDesign dataSetDesign )
	{
		DataSetDesignPopulator.populateMetaData( dataSetDesign );
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
		
	}

	public boolean isLabelProperty( Object element, String property )
	{
		return false;
	}

	public void removeListener( ILabelProviderListener listener )
	{
		
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
			type = DataTypeUtil.getDataTypeDisplayName( DataTypes.getType( node.getDataType( ) ) );
		}
		catch ( OdaException e )
		{
			type = DataTypeUtil.getDataTypeDisplayName( DataTypes.STRING );
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
			String alias = columnName + UNDERSCORE + index;
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
	private boolean handleSelectedItem( TreeItem selectedItem, HashSet selectedNodes )
	{
		if ( selectedItem.getData( ) != null
				&& selectedItem.getData( ) instanceof TreeNodeData )
		{
			ATreeNode aTreeNode = ( (TreeNodeData) selectedItem.getData( ) ).getTreeNode( );
			if ( selectedNodes != null && !selectedNodes.contains( aTreeNode ) )
			{
				addChildrenElements( aTreeNode, selectedNodes );
			}
			return true;
		}
		return false;
	}
}
