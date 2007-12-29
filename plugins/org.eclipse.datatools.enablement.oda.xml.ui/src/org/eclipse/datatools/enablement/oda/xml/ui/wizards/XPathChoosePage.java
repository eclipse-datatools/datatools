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

import java.util.List;

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
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.datatools.enablement.oda.xml.util.ui.SchemaPopulationUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtil;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Root xpath choose page. It expands the xml tree list to choose the prefered
 * root path
 */

public class XPathChoosePage extends DataSetWizardPage
{
	private static String DEFAULT_MESSAGE = Messages.getString( "wizard.defaultMessage.selectXPath" );
	private static final String PATH_SEPERATOR = "/";
	
	private transient Tree availableXmlTree;
	private transient Composite centerComposite;
	private Button absolutePathButton;
	private Button anyLocationButton;
	private Button customButton;
	private Combo xmlPathField;
	private transient Group treeGroup;
	private transient Group rightGroup;
	
	private ATreeNode treeNode;
	private TreeItem selectedItem;
	private String xsdFileName;
	private String xmlFileName;
	private String xmlEncoding;

	private String rootPath;
	private String initRootPath;

	private Label absolutePathLabel;
	private Label anyLocationLabel;
	private Label customPathLabel;
	
	private List xpathList;

	/**
	 * @param string
	 */
	public XPathChoosePage( )
	{
		this( Messages.getString( "wizard.title.newDataSet" ) );
	}

	/**
	 * @param pageName
	 */
	public XPathChoosePage( String pageName )
	{
		super( pageName );
		this.setTitle( pageName );
		this.setMessage( DEFAULT_MESSAGE );
		this.setPageComplete( false );
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		setControl( createPageControl( parent ) );
		
		if( XMLInformationHolder.hasDestroyed() )
			XMLInformationHolder.start( this.getInitializationDesign( ) );
		initializeControl( );
		populateXMLTree( );
		
		XMLRelationInfoUtil.setSystemHelp( getControl( ),
				IHelpConstants.CONEXT_ID_DATASET_XML_XPATH );
	}
	
	/**
	 * initial the info after create the control
	 * 
	 */
	private void initializeControl( )
	{
		xsdFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
		xmlFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
		xmlEncoding = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST );
	
		String queryText = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION );

		String tableName = XMLRelationInfoUtil.getTableName( queryText );
		if ( tableName != null )
			rootPath = XMLRelationInfoUtil.getXPathExpression( queryText,
					tableName );
		else
			rootPath = "";

		backupRootPath( );
		if ( rootPath != null && rootPath.length( ) > 0 )
			xmlPathField.setText( rootPath );
		xpathList = XPathPopulationUtil.getPathList( rootPath );
	}
	
	private void backupRootPath( )
	{
		initRootPath = rootPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#refresh(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void refresh( DataSetDesign dataSetDesign )
	{
		DEFAULT_MESSAGE = Messages.getString( "xPathChoosePage.messages.elementSelection.label" );
		if ( XMLInformationHolder.hasDestroyed( ) )
			XMLInformationHolder.start( dataSetDesign );
		
		refresh( );
	}
	
	protected void refresh( )
	{
		xmlFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
		xsdFileName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST );
		xmlEncoding = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST );
		populateXMLTree( );
		backupRootPath( );
		setMessage( DEFAULT_MESSAGE );
	}
	
    /**
    * 
    * @param parent
    * @return
    */
	public Control createPageControl( Composite parent )
	{
		DEFAULT_MESSAGE = Messages.getString( "wizard.defaultMessage.selectXPath" );
		this.setMessage( DEFAULT_MESSAGE );
		Composite composite = new Composite( parent, SWT.NONE );

		FormLayout layout = new FormLayout( );
		composite.setLayout( layout );

		createLeftGroup( composite );

		FormData data = new FormData( );
		data.left = new FormAttachment( treeGroup, 5 );
		data.bottom = new FormAttachment( 50 );
		data.width = 20;

		centerComposite = new Composite( composite, SWT.NONE );
		centerComposite.setLayoutData( data );
		FillLayout centerLayout = new FillLayout( );
		centerComposite.setLayout( centerLayout );

		createRightGroup( composite );
		return composite;
	}
	
	/**
	 * create left group composite
	 * @param composite2
	 */
	private void createLeftGroup( Composite composite2 )
	{
		FormData data = new FormData( );

		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 47, -5 );
		data.bottom = new FormAttachment( 100, -5 );

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
				{
					xpathList = getSelectedXPathList( );
					if( customButton.getSelection( ) )
					{
						resetCustomXMLPathField( );
					}
					resetButtonsAndLabels( true );
				}
				else
				{
					resetButtonsAndLabels( false);
				}
			}

		} );

		treeGroup.setText( Messages.getString( "xPathChoosePage.messages.xmlStructure" ) );
	}
	
	/**
	 * create right group composite
	 * @param composite2
	 */
	private void createRightGroup( Composite composite2 )
	{
		FormData data = new FormData( );
		data.top = new FormAttachment( 0, 5 );
		data.left = new FormAttachment( centerComposite, 5 );
		data.right = new FormAttachment( 100, -5 );
		data.bottom = new FormAttachment( 100, -5 );
		
		rightGroup = new Group( composite2, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 2;
		rightGroup.setLayout( layout );
		rightGroup.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.label" ) );
		rightGroup.setLayoutData( data );
		
		GridData buttonGd = new GridData( );
		buttonGd.verticalAlignment = SWT.BEGINNING;
		buttonGd.verticalIndent = 10;
		
		GridData labelGd = new GridData( GridData.FILL_HORIZONTAL );
		labelGd.verticalIndent = 10;

		absolutePathButton = new Button( rightGroup, SWT.RADIO | SWT.WRAP );
		absolutePathButton.setLayoutData( buttonGd );
		absolutePathLabel = new Label( rightGroup, SWT.WRAP );
		absolutePathLabel.setLayoutData( labelGd );
		anyLocationButton = new Button( rightGroup, SWT.RADIO | SWT.WRAP );
		anyLocationButton.setLayoutData( buttonGd );
		anyLocationLabel = new Label( rightGroup, SWT.WRAP );
		anyLocationLabel.setLayoutData( labelGd );
		customButton = new Button( rightGroup, SWT.RADIO | SWT.WRAP );
		customButton.setLayoutData( buttonGd );
		customButton.setSelection( true );
		customPathLabel = new Label( rightGroup, SWT.WRAP );
		customPathLabel.setLayoutData( labelGd );
		customPathLabel.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.item.custom" ) );
		
		setLabelValuesAndListeners( rightGroup );
		
		Label blankLabel = new Label( rightGroup, SWT.NONE );
		GridData txtGridData = new GridData();
		txtGridData.widthHint = 200;
		xmlPathField = new Combo( rightGroup, SWT.DROP_DOWN );
		xmlPathField.setLayoutData( txtGridData );
		xmlPathField.setVisible( true );
		xmlPathField.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
			}
		} );
		xmlPathField.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				rootPath = xmlPathField.getText( );
				resetXPathPropInHandle( rootPath );
				setPageStatus( );
			}
		} );
	}

	/**
	 * reset the custom xpath expression field
	 * 
	 */
	private void resetCustomXMLPathField( )
	{
		xmlPathField.removeAll( );
		if ( xpathList != null )
		{
			if ( xpathList.size( ) < 2 )
			{
				
			}
			xmlPathField.setText( xpathList.get( 0 ).toString( ) );
			xmlPathField.add( xpathList.get( 0 ).toString( ) );
			xmlPathField.add( xpathList.get( 1 ).toString( ) );
		}
		rootPath = xmlPathField.getText( );
		resetXPathPropInHandle( rootPath );
		setPageStatus( );
	}
	
	/**
	 * 
	 */
	private void setLabelValuesAndListeners( Composite composite )
	{
		if ( this.selectedItem != null )
		{
			xpathList = getSelectedXPathList( );
			if ( xpathList.size( ) < 2 )
			{
				setMessage( Messages.getString( "error.xpath.getPathList" ), ERROR );
			}
			resetButtonsAndLabels( true );
		}
		else
		{
			resetButtonsAndLabels( false );
		}		
		absolutePathLabel.addListener( SWT.MouseDown, new Listener(){

			public void handleEvent( Event event )
			{
				absolutePathButton.setSelection( true );	
				anyLocationButton.setSelection( false );
				customButton.setSelection( false );
				xmlPathField.setVisible( false );
			}			
		});
		anyLocationLabel.addListener( SWT.MouseDown, new Listener(){

			public void handleEvent( Event event )
			{
				anyLocationButton.setSelection( true );
				absolutePathButton.setSelection( false );	
				customButton.setSelection( false );
				xmlPathField.setVisible( false );
			}			
		});
		customPathLabel.addListener( SWT.MouseDown, new Listener(){

			public void handleEvent( Event event )
			{
				customButton.setSelection( true );
				xmlPathField.setVisible( true );
				absolutePathButton.setSelection( false );
				anyLocationButton.setSelection( false );
				xpathList = getSelectedXPathList( );
				resetCustomXMLPathField( );
				setPageStatus( );
			}			
		});
		absolutePathButton.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( absolutePathButton.getSelection( ) )
				{
					rootPath = xpathList.get( 0 ).toString( );
					xmlPathField.setVisible( false );
				}
			}
		} );
		anyLocationButton.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( anyLocationButton.getSelection( ) )
				{
					rootPath = xpathList.get( 1 ).toString( );
					xmlPathField.setVisible( false );
				}
			}
		} );
		customButton.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( customButton.getSelection( ) )
				{
					xmlPathField.setVisible( true );
					xpathList = getSelectedXPathList( );
					resetCustomXMLPathField( );
				}
				setPageStatus( );
			}
		} );
		composite.layout( );
	}

	/**
	 * 
	 */
	private void resetButtonsAndLabels( boolean visible )
	{
		if ( visible )
		{
			absolutePathLabel.setText( Messages.getFormattedString( "xPathChoosePage.messages.elementSelection.item.absolutePath",
					new String[]{
							selectedItem.getText( ),
							(String) xpathList.get( 0 )
					} ) );
			anyLocationLabel.setText( Messages.getFormattedString( "xPathChoosePage.messages.elementSelection.item.anyLocation",
					new String[]{
							selectedItem.getText( ),
							(String) xpathList.get( 1 )
					} ) );
		}
		else
		{
			absolutePathLabel.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.absolutePath" ) );
			anyLocationLabel.setText( Messages.getString( "xPathChoosePage.messages.elementSelection.disable.anyLocation" ) );
		}
		absolutePathButton.setEnabled( visible );
		absolutePathLabel.setEnabled( visible );
		anyLocationButton.setEnabled( visible );
		anyLocationLabel.setEnabled( visible );
	}

	/**
	 * get the standby XPath expression
	 * 
	 * @return
	 */
	protected List getSelectedXPathList( )
	{
		String path;
		if ( this.selectedItem == null )
		{
			path = PATH_SEPERATOR + initRootPath;
		}
		else
		{
			path = getRootPath( );
		}
		return XPathPopulationUtil.getPathList( path );
	}

	/**
	 * @return root path string
	 */
	private String getRootPath( )
	{
		TreeItem selected = this.selectedItem;

		if ( selected.getData( ) instanceof TreeNodeData )
		{
			ATreeNode node = ( (TreeNodeData) selected.getData( ) ).getTreeNode( );
			if ( node.getType( ) == ATreeNode.ATTRIBUTE_TYPE )
			{
				return null;
			}
			else
			{
				rootPath = PATH_SEPERATOR + selected.getText( );
			}
		}

		while ( selected.getParentItem( ) != null )
		{
			selected = selected.getParentItem( );
			if ( selected.getData( ) instanceof TreeNodeData )
			{
				ATreeNode node = ( (TreeNodeData) selected.getData( ) ).getTreeNode( );
				if ( node.getType( ) == ATreeNode.ELEMENT_TYPE )
				{
					rootPath = PATH_SEPERATOR + selected.getText( ) + rootPath;
				}
			}
		}
		return rootPath;
	}

	/**
	 * populate xml tree
	 * 
	 */
	private void populateXMLTree( )
	{
		if ( ( xsdFileName == null || xsdFileName.trim( ).length( ) == 0 )
				&& ( xmlFileName == null || xmlFileName.trim( ).length( ) == 0 ) )
			return;
		
		try
		{
			availableXmlTree.removeAll( );
			if ( ( xsdFileName != null && xsdFileName.trim( ).length( ) > 0 )
					|| ( xmlFileName != null && xmlFileName.trim( ).length( ) > 0 ) )
			{
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
				// TODO for migrate into ODA3.0, the relative path is not
				// supported
				// Object url = this.dataSetHandle.getModuleHandle(
				// ).findResource( fileName,IResourceLocator.LIBRARY );
				//				
				// if( url != null )
				treeNode = SchemaPopulationUtil.getSchemaTree( xsdFileName,
						xmlFileName,
						xmlEncoding,
						numberOfElement );
				if ( treeNode == null || treeNode.getChildren( ).length == 0 )
				{
					if( treeNode.getValue( ).equals( "ROOT" ) )
					{
						initRootPath = (String)( (ATreeNode)treeNode.getChildren( )[0] ).getValue( );
					}
					OdaException ex = new OdaException( Messages.getString( "dataset.error.populateXMLTree" ) );
					ExceptionHandler.showException( getShell( ),
							Messages.getString( "error.label" ),
							ex.getMessage( ),
							ex );
				}
				else
				{
					Object[] childs = treeNode.getChildren( );
					initRootPath = (String)( (ATreeNode)childs[0] ).getValue( );
					availableXmlTree.addListener( SWT.Expand, new Listener( ) {

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
											false );
							}
							catch ( OdaException e )
							{
								ExceptionHandler.showException( getShell( ),
										Messages.getString( "error.label" ),
										e.getMessage( ),
										e );
							}

						}
					} );

					TreePopulationUtil.populateTreeItems( availableXmlTree,
							childs,
							false );
				}
			}
		}
		catch ( Exception e )
		{
			ExceptionHandler.showException( getShell( ),
					Messages.getString( "error.label" ),
					e.getMessage( ),
					e );
		}
	}

	/**
	 * set page status based on row number
	 * 
	 */
	private void setPageStatus( )
	{
		if ( !isRootPathValid( ) )
		{
			setPageComplete( false );
			this.setMessage( Messages.getFormattedString( "error.invalidXpath",
					new Object[]{
						rootPath == null ? "" : rootPath
					} ), IMessageProvider.ERROR );
		}
		else
		{
			if ( initRootPath != null
					&& !initRootPath.equals( "" )
					&& !initRootPath.equals( rootPath ) )
			{
				setMessage( Messages.getString( "xPathChoosePage.messages.xpathChange" ),
						INFORMATION );
			}
			else
			{
				setMessage( DEFAULT_MESSAGE );
			}
			setPageComplete( true );
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean isRootPathValid( )
	{
		return !(rootPath == null
				|| rootPath.trim( ).length( ) == 0);
	}
	
	/**
	 * when XPath text has changed, reset the dataSetHandle.CONST_PROP_XPATH
	 * 
	 */
	private void resetXPathPropInHandle( String pathStr )
	{
		if ( pathStr != null && pathStr.trim( ).length( ) > 0 )
		{
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_XPATH,
					pathStr );
			// The relation information should be changed
			if ( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_TABLE_NAME ) != null )
			{
				String relationInfo = (String) XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION );

				if ( relationInfo == null
						|| relationInfo.trim( ).length( ) == 0 )
					return;
				String tableName = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_TABLE_NAME );
				String infoStr = XMLRelationInfoUtil.replaceXpathExpression( tableName,
						relationInfo,
						pathStr );

				XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
						infoStr );
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizardPage#getNextPage()
	 */
	public IWizardPage getNextPage( )
	{
		if ( isValid( ) )
		{
			IWizardPage page = super.getNextPage( );
			if ( page instanceof ColumnMappingPage )
			{
				( (ColumnMappingPage) page ).refresh( );
			}
			return page;
		}
		else
			return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage( )
	{
		if ( rootPath == null || rootPath.trim( ).length( ) == 0 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
    
	/**
	 * the xpath expression is valid or not
	 * @return
	 */
	private boolean isValid( )
	{
		rootPath = xmlPathField.getText( );
		if ( !isRootPathValid( ) )
		{
			this.setMessage( Messages.getFormattedString( "error.invalidXpath",
					new Object[]{
						rootPath == null ? "" : rootPath
					} ), IMessageProvider.ERROR );
			return false;
		}
		else
		{
			resetXPathPropInHandle( rootPath );
			return true;
		}
	}
	
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
    protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		try
		{
			resetXPathPropInHandle( rootPath );
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
    	if( XMLInformationHolder.hasDestroyed( ) )
    		return;
		if ( dataSetDesign != null
				&& getQueryText( dataSetDesign ) != null
				&& !getQueryText( dataSetDesign )
						.equals( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) ) )
		{
			setQueryText( dataSetDesign,
					XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
			updateDesign( dataSetDesign );
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
    
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#canLeave()
	 */
    protected boolean canLeave( )
	{
		return isValid( );
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

}
